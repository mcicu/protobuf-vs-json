package com.mcicu.protobufvsjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcicu.protobufvsjson.json.dtos.*;
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

    private Logger logger = LoggerFactory.getLogger(JsonRestTests.class);

    private RestTemplate restTemplate = new RestTemplate();

    @LocalServerPort
    private int port;

    private List<BeaconMessageDTO> beaconMessages = new LinkedList<>();

    @PostConstruct
    private void generateBeaconMessages() {
        for (int i = 0; i < 10000; i++) {
            BeaconMessageDTO beaconMessage = BeaconMessageDTO.Builder.aBeaconMessageDTO()
                    .setId(UUID.randomUUID().toString())
                    .setUserData(
                            UserDataDTO.Builder.anUserDataDTO()
                                    .setRandomUserData("eririjee0eierffgkfgj234349")
                                    .build())
                    .setSatelliteMetadata(
                            SatelliteMetadataDTO.Builder.aSatelliteMetadataDTO()
                                    .setId(UUID.randomUUID().toString())
                                    .setModulation(333.3f)
                                    .setBlockNumber(55)
                                    .setToaMillis(454565465545L)
                                    .setFoaMhz(555.5f)
                                    .setReceptionLevel(5f)
                                    .setSnr(44.4f)
                                    .setQualityIndicator(SatelliteMetadataDTO.QualityIndicator.LEVEL_3)
                                    .build()
                    )
                    .setGrsMetadata(
                            GRSMetadataDTO.Builder.aGRSMetadataDTO()
                                    .setId(UUID.randomUUID().toString())
                                    .setReceptionDateMillis(456564564564L)
                                    .build()
                    )
                    .setMcsMetadata(
                            MCSMetadataDTO.Builder.aMCSMetadataDTO()
                                    .setMcsId(MCSMetadataDTO.MCSId.NOMINAL)
                                    .setTimeOfArrivalAtMCSMillis(456564564564L)
                                    .setTimeOfAvailabilityAtSCMillis(456564564564L)
                                    .build()
                    )
                    .build();
            beaconMessages.add(beaconMessage);
        }
    }

    @Test
    public void sendAndAcknowledgeBeaconMessagesJSON() {
        String url = MessageFormat.format("http://localhost:{0,number,#}/json/acknowledge-beacon-message", port);
        int millis = 0;
        for (BeaconMessageDTO beaconMessage : beaconMessages) {
            long start = Instant.now().toEpochMilli();
            ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(beaconMessage), String.class);
            long end = Instant.now().toEpochMilli();
            millis += end - start;
        }

        double average = Double.valueOf(millis) / Double.valueOf(beaconMessages.size());

        logger.info("Average time millis = {}", average);
    }

    @Test
    public void sizeBeaconMessageJSON() throws Throwable {
        BeaconMessageDTO beaconMessage = beaconMessages.get(0);

        ObjectMapper mapper = new ObjectMapper();

        int byteCount = mapper.writeValueAsBytes(beaconMessage).length;
        logger.info("Beacon message size = {}", byteCount);
    }
}
