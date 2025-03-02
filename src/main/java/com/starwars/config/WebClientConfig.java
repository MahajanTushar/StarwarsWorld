package com.starwars.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@Component
public class WebClientConfig {

    @Bean("appWebclient")
    WebClient createWebClient(@Value("${base.url}") String baseUrl) {
        TcpClient tcpClient = TcpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));
        return WebClient.builder().baseUrl(baseUrl)
                .build();
    }

    @Bean("noUriWebclient")
    WebClient noUriWebclient() {
        return WebClient.builder()
                .build();
    }

}
