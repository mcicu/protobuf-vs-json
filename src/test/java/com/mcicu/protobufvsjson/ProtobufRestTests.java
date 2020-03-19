package com.mcicu.protobufvsjson;

import com.mcicu.protobufvsjson.protobuf.ProtoMessages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.ByteArrayOutputStream;
import java.text.MessageFormat;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProtobufRestTests {

    private Logger logger = LoggerFactory.getLogger(ProtobufRestTests.class);

    private RestTemplate restTemplate = new RestTemplate(Arrays.asList(new ProtobufHttpMessageConverter(), new StringHttpMessageConverter()));

    @LocalServerPort
    private int port;

    private List<ProtoMessages.BeaconMessage> beaconMessages = new LinkedList<>();

    @PostConstruct
    private void generateBeaconMessages() {
        for (int i = 0; i < 10000; i++) {
            ProtoMessages.BeaconMessage beaconMessage = ProtoMessages.BeaconMessage.newBuilder()
                    .setId(UUID.randomUUID().toString())
                    .setUserData(
                            ProtoMessages.UserData.newBuilder()
                                    .setRandomUserData("eririjee0eierffgkfgj234349")
                                    .build())
                    .setSatelliteMetadata(
                            ProtoMessages.SatelliteMetadata.newBuilder()
                                    .setId(UUID.randomUUID().toString())
                                    .setModulation(333.3f)
                                    .setBlockNumber(55)
                                    .setToaMillis(454565465545L)
                                    .setFoaMhz(555.5f)
                                    .setReceptionLevel(5f)
                                    .setSnr(44.4f)
                                    .setQualityIndicator(ProtoMessages.SatelliteMetadata.QualityIndicator.LEVEL_3)
                                    .build()
                    )
                    .setGrsMetadata(
                            ProtoMessages.GRSMetadata.newBuilder()
                                    .setId(UUID.randomUUID().toString())
                                    .setReceptionDateMillis(456564564564L)
                                    .build()
                    )
                    .setMcsMetadata(
                            ProtoMessages.MCSMetadata.newBuilder()
                                    .setMcsId(ProtoMessages.MCSMetadata.MCSId.NOMINAL)
                                    .setTimeOfArrivalAtMcsMillis(456564564564L)
                                    .setTimeOfAvailabilityAtScMillis(456564564564L)
                                    .build()
                    )
                    .build();
            beaconMessages.add(beaconMessage);
        }
    }

    @Test
    public void sendAndAcknowledgeBeaconMessagesProtobuf() {
        String url = MessageFormat.format("http://localhost:{0,number,#}/protobuf/acknowledge-beacon-message", port);
        int millis = 0;
        for (ProtoMessages.BeaconMessage beaconMessage : beaconMessages) {
            long start = Instant.now().toEpochMilli();
            ResponseEntity<?> responseEntity = restTemplate.exchange(url, HttpMethod.POST, new HttpEntity<>(beaconMessage), String.class);
            long end = Instant.now().toEpochMilli();
            millis += end - start;
        }

        double average = Double.valueOf(millis) / Double.valueOf(beaconMessages.size());

        logger.info("Average time millis = {}", average);
    }

    @Test
    public void sizeBeaconMessageProtobuf() throws Throwable {
        ProtoMessages.BeaconMessage beaconMessage = beaconMessages.get(0);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        beaconMessage.writeTo(outputStream);
        outputStream.close();

        int byteCount = outputStream.toByteArray().length;
        logger.info("Beacon message size = {}", byteCount);
    }
}
