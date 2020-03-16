package com.mcicu.protobufvsjson;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

@SpringBootApplication
public class ProtobufVsJsonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProtobufVsJsonApplication.class, args);
    }

    @Bean
    ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        ProtobufHttpMessageConverter converter = new ProtobufHttpMessageConverter();
        return new ProtobufHttpMessageConverter();
    }
}
