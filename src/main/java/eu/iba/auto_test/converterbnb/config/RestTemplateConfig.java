package eu.iba.auto_test.converterbnb.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.config.ConnectionConfig;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.apache.hc.core5.http.io.SocketConfig;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Конфигурация для RestTemplate
 */
@Configuration
public class RestTemplateConfig {

    @Value("${restTemplate.maxTotalPooling:500}")
    private int maxTotalPooling;

    @Value("${restTemplate.defaultMaxPerRoute:500}")
    private int defaultMaxPerRoute;

    @Value("${restTemplate.requestTimeout:70000}")
    private int requestTimeout;

    @Value("${restTemplate.poolTimeout:10000}")
    private int poolTimeout;

    @Value("${restTemplate.connectionTimeout:10000}")
    private int connectionTimeout;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                .<ConnectionSocketFactory>create().register("https", cutstomSSLConnectionSocketFactory())
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .build();
        PoolingHttpClientConnectionManager result = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        result.setMaxTotal(maxTotalPooling);
        result.setDefaultMaxPerRoute(defaultMaxPerRoute);
        result.setDefaultSocketConfig(SocketConfig.custom()
                .setSoTimeout(Timeout.ofMinutes(10))
                .build());
        result.setDefaultConnectionConfig(ConnectionConfig.custom()
                .setSocketTimeout(Timeout.ofMinutes(10))
                .setConnectTimeout(Timeout.ofMinutes(10))
                .setTimeToLive(TimeValue.ofMinutes(10))
                .build());
        return result;
    }

    @Bean
    public RequestConfig requestConfig() {
        return RequestConfig.custom()
                .setConnectionRequestTimeout(poolTimeout, TimeUnit.MILLISECONDS)
                .setConnectionRequestTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
                .build();
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory(CloseableHttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }

    @Bean
    public SSLConnectionSocketFactory cutstomSSLConnectionSocketFactory() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        HostnameVerifier hostnameVerifier = (s, sslSession) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
        return new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
    }

    @Bean
    public CloseableHttpClient httpClient(PoolingHttpClientConnectionManager manager, RequestConfig requestConfig) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        return HttpClients.custom()
                .setConnectionManager(manager)
                .disableRedirectHandling()
                .setDefaultRequestConfig(requestConfig)
                .disableAutomaticRetries()
                .setConnectionManagerShared(true)
                .build();
    }

    @Bean
    public RestTemplateBuilder restTemplateBuilder(HttpComponentsClientHttpRequestFactory requestFactory) {
        return new RestTemplateBuilder()
                .requestFactory(() -> requestFactory);
    }

    @Bean
    public RestTemplate restTemplate(ObjectMapper mapper) {
        return createRestTemplate(new RestTemplateBuilder(), mapper, connectionTimeout);
    }

    @Bean
    @Primary
    public RestTemplate apimAuthRestTemplate(RestTemplateBuilder restTemplateBuilder,
                                             ObjectMapper mapper) {
        RestTemplate authTemplate = createRestTemplate(restTemplateBuilder, mapper, connectionTimeout);
        List<ClientHttpRequestInterceptor> interceptors = authTemplate.getInterceptors();
        authTemplate.setInterceptors(interceptors);
        return authTemplate;
    }

    private RestTemplate createRestTemplate(RestTemplateBuilder restTemplateBuilder, ObjectMapper mapper, int connectionTimeout) {
        RestTemplate restTemplate = restTemplateBuilder
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .build();
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> messageConverter : messageConverters) {
            if (MappingJackson2HttpMessageConverter.class.isAssignableFrom(messageConverter.getClass())) {
                MappingJackson2HttpMessageConverter c = (MappingJackson2HttpMessageConverter) messageConverter;
                c.setObjectMapper(mapper);
            }
        }
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setErrorHandler(getDefaultResponseErrorHandler());
        return restTemplate;
    }

    private DefaultResponseErrorHandler getDefaultResponseErrorHandler() {
        return new DefaultResponseErrorHandler() {

            @Override
            protected boolean hasError(HttpStatusCode statusCode) {
                return false;
            }

        };
    }


}
