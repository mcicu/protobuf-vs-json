package com.mcicu.protobufvsjson;

import com.mcicu.protobufvsjson.json.BeaconDTO;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonRestTests {

    private Logger logger = LoggerFactory.getLogger(ProtoBufRestTests.class);

    private RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    private int port;

    private List<BeaconDTO> beaconMessages = new LinkedList<>();

    @PostConstruct
    private void generateBeaconMessages() {
        for (int i = 0; i < 10000; i++) {
            BeaconDTO beaconMessage = BeaconDTO.Builder.aBeaconDTO()
                    .withId(UUID.randomUUID().toString())
                    .withLatitude(111.111f)
                    .withLongitude(222.222f)
                    .build();
            beaconMessages.add(beaconMessage);
        }
    }

    @Test
    public void createBeacons() {
        String url = MessageFormat.format("http://localhost:{0,number,#}/json/beacons", port);
        int millis = 0;
        for (BeaconDTO beaconMessage : beaconMessages) {
            long start = Instant.now().toEpochMilli();
            ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(beaconMessage), Object.class);
            long end = Instant.now().toEpochMilli();
            millis += end - start;
        }

        double average = Double.valueOf(millis) / Double.valueOf(beaconMessages.size());

        logger.info("Average time = {}", average);
    }
}
