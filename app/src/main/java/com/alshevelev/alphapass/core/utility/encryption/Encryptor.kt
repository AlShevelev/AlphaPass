@file:Suppress("DEPRECATION")

package com.alshevelev.alphapass.core.utility.encryption

import android.security.KeyPairGeneratorSpec
import com.alshevelev.alphapass.app.App
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.math.BigInteger
import java.security.*
import java.util.*
import javax.crypto.Cipher
import javax.crypto.CipherInputStream
import javax.crypto.CipherOutputStream
import javax.security.auth.x500.X500Principal

/** */
class Encryptor: EncryptorInterface {
    companion object {
        private val KEYSTORE_PROVIDER = "AndroidKeyStore"
        private val KEY_ALIAS = "alphapass_encryption_key"
    }

    private val keyStore: KeyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)

    init {
        keyStore.load(null)

        if (!keyStore.containsAlias(KEY_ALIAS)) {
            createInternalKey()
        }
    }


    /** */
    override fun encryptByInternalKey(data: ByteArray?): ByteArray? {
        if(data == null) {
            return null
        }

        if(data.isEmpty()) {
            return kotlin.ByteArray(0)
        }

        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val publicKey = privateKeyEntry.certificate.publicKey;

        val input = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        input.init(Cipher.ENCRYPT_MODE, publicKey)

        lateinit var result: ByteArray
        ByteArrayOutputStream().use { byteStream ->
            CipherOutputStream(byteStream, input).use {
                it.write(data)
            }
            result = byteStream.toByteArray()
        }
        return result
    }

    /** */
    override fun decryptByInternalKey(data: ByteArray?): ByteArray? {
        if(data == null) {
            return null
        }

        if(data.isEmpty()) {
            return kotlin.ByteArray(0)
        }

        val privateKeyEntry = keyStore.getEntry(KEY_ALIAS, null) as KeyStore.PrivateKeyEntry
        val privateKey = privateKeyEntry.privateKey;

        val output = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        output.init(Cipher.DECRYPT_MODE, privateKey)

        val values = mutableListOf<Byte>()
        ByteArrayInputStream(data).use { byteStream ->
            CipherInputStream(byteStream, output).use {
                while (true) {
                    val nextByte = it.read()
                    if(nextByte==-1)
                        break
                    values.add(nextByte.toByte())
                }
            }
        }
        return values.toByteArray()
    }

    @Throws(InvalidAlgorithmParameterException::class, NoSuchAlgorithmException::class, NoSuchProviderException::class)
    private fun createInternalKey() {
        val start = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 100)

        // note[AS] we have to use KeyPairGeneratorSpec as our MIN_SDK less than 23
        val spec = KeyPairGeneratorSpec.Builder(App.context)
            .setAlias(KEY_ALIAS)
            .setSubject(X500Principal("CN=Sample Name, O=Android Authority"))
            .setSerialNumber(BigInteger.ONE)
            .setStartDate(start.time)
            .setEndDate(end.time)
            .build()

        val generator = KeyPairGenerator.getInstance("RSA", KEYSTORE_PROVIDER)
        generator.initialize(spec)

        generator.generateKeyPair()
    }
}