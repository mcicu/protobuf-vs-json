package com.mcicu.protobufvsjson;

import com.mcicu.protobufvsjson.protobuf.ProtoMessages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProtoBufRestTests {

    private Logger logger = LoggerFactory.getLogger(ProtoBufRestTests.class);

    private RestTemplate restTemplate = new RestTemplate(Arrays.asList(new ProtobufHttpMessageConverter()));

    @LocalServerPort
    private int port;

    private List<ProtoMessages.Beacon> beaconMessages = new LinkedList<>();

    @PostConstruct
    private void generateBeaconMessages() {
        for (int i = 0; i < 10000; i++) {
            ProtoMessages.Beacon beaconMessage = ProtoMessages.Beacon.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setLatitude(111.111f)
                    .setLongitude(222.222f)
                    .build();
            beaconMessages.add(beaconMessage);
        }
    }

    @Test
    public void createBeacons() {
        String url = MessageFormat.format("http://localhost:{0,number,#}/protobuf/beacons", port);
        int millis = 0;
        for (ProtoMessages.Beacon beaconMessage : beaconMessages) {
            long start = Instant.now().toEpochMilli();
            ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(beaconMessage), Object.class);
            long end = Instant.now().toEpochMilli();
            millis += end - start;
        }

        double average = Double.valueOf(millis) / Double.valueOf(beaconMessages.size());

        logger.info("Average time = {}", average);
    }
}
