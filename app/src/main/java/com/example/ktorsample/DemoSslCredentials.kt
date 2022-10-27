package com.example.ktorsample

import android.content.Context
import java.security.KeyStore

class DemoSslCredentials(
    context: Context
) : SslCredentials {

    override val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType()).apply {
        context.assets.open(FILENAME).use {
            load(it, PASSWORD.toCharArray())
        }
    }

    override val keyAlias: String
        get() = ALIAS

    override val aliasPassword: String
        get() = PASSWORD

    override val keyPassword: String
        get() = PASSWORD

    private companion object {
        const val ALIAS = "alias"
        const val PASSWORD = "password"
        const val FILENAME = "keystore.bks"
    }
}

interface SslCredentials {

    val keyStore: KeyStore

    val keyAlias: String

    val keyPassword: String

    val aliasPassword: String
}