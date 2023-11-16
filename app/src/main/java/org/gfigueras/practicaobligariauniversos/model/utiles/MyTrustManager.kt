package org.gfigueras.practicaobligariauniversos.model.utiles

import java.io.IOException
import java.net.URL
import java.security.KeyStore
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class MyTrustManager(private val defaultTrustManager: X509TrustManager, private val host: String) : X509TrustManager {

    private val sslSocketFactory: SSLSocketFactory

    init {
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf<TrustManager>(defaultTrustManager), null)
        sslSocketFactory = sslContext.socketFactory
    }

    @Throws(CertificateException::class)
    override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
        for (cert in chain) {
            cert.checkValidity() // optional: checks if the certificate is expired
        }
        defaultTrustManager.checkServerTrusted(chain, authType)

        var socket: SSLSocket? = null
        try {
            socket = sslSocketFactory.createSocket(host, 443) as SSLSocket
            socket.soTimeout = 5000 // Timeout for the SSL handshake
            socket.startHandshake()
        } catch (e: IOException) {
            throw CertificateException("Could not complete SSL handshake", e)
        } finally {
            socket?.close()
        }
    }

    // Delegate other methods to the default trust manager
    @Throws(CertificateException::class)
    override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
        defaultTrustManager.checkClientTrusted(chain, authType)
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return defaultTrustManager.acceptedIssuers
    }
}

fun main() {
    val host = "gfserver.onrender.com"
    val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
    tmf.init(null as KeyStore?)
    val defaultTrustManager = tmf.trustManagers[0] as X509TrustManager
    val trustManagers = arrayOf<TrustManager>(MyTrustManager(defaultTrustManager, host))
    val sslContext = SSLContext.getInstance("TLS")
    sslContext.init(null, trustManagers, null)
    val urlConnection = URL("https://gfserver.onrender.com").openConnection() as HttpsURLConnection
    urlConnection.sslSocketFactory = sslContext.socketFactory
}
