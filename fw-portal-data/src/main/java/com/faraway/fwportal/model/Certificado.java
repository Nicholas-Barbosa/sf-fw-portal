package com.faraway.fwportal.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Profile;

@Entity
public final class Certificado extends BaseEntity {

	private String path;

	private char[] senha;

	private String cnpj;

	private String ultimoNsuPesquisado;

	@Enumerated(EnumType.STRING)
	private PortaRest porta;

	public Certificado() {
		// TODO Auto-generated constructor stub
	}

	public String getUltimoNsuPesquisado() {
		if (this.ultimoNsuPesquisado == null) {
			this.ultimoNsuPesquisado = "000000000000000";
		}
		return ultimoNsuPesquisado;
	}

	public CloseableHttpClient getHttpClient() {
		return this.new Ssl().httpClient();
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setUltimoNsuPesquisado(String ultimoNsuPesquisado) {
		this.ultimoNsuPesquisado = ultimoNsuPesquisado;
	}

	public PortaRest getPorta() {
		return porta;
	}

	private final class Ssl {

		private final Path keyStorePath = Path
				.of("C:\\Users\\Nicholas Henrique\\Documents\\keystore\\server-keystore.jks");
		// .of("/home/ph161169/farawaybr.com/keystore/server-keystore.jks");

		private char[] keyStore = "server-keystore-p455w0rd".toCharArray();

		private CloseableHttpClient httpClient() {

			return HttpClientBuilder.create().setSSLSocketFactory(sslConnectionSocketFactory()).build();
		}

		private SSLConnectionSocketFactory sslConnectionSocketFactory() {
			return new SSLConnectionSocketFactory(sslContext());
		}

		private SSLContext sslContext() {
			try (var in = new BufferedInputStream(new FileInputStream(keyStorePath.toFile()));
					var bufferedCertifi = new BufferedInputStream(
							new FileInputStream(new File(Certificado.this.path)))) {

				KeyStore clientStore = KeyStore.getInstance("PKCS12");
				clientStore.load(bufferedCertifi, Certificado.this.senha);

				KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
				kmf.init(clientStore, "M@ster!@#".toCharArray());

				KeyManager[] kms = kmf.getKeyManagers();
				// Assuming that you imported the CA Cert "Subject: CN=MBIIS CA, OU=MBIIS,
				// O=DAIMLER, C=DE"
				// to your cacerts Store.
				KeyStore trustStore = KeyStore.getInstance("JKS");
				trustStore.load(in, keyStore);

				TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
				tmf.init(trustStore);
				TrustManager[] tms = tmf.getTrustManagers();

				SSLContext sslcontext = SSLContext.getInstance("TLS");

				sslcontext.init(kms, tms, new SecureRandom());
				SSLContext.setDefault(sslcontext);

				return sslcontext;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

			}
			return null;
		}
	}
}
