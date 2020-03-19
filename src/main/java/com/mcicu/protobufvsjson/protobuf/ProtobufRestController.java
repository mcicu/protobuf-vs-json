package com.mcicu.protobufvsjson.protobuf;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/protobuf")
@AllArgsConstructor
public class ProtobufRestController {

    private final BeaconMessageProtobufService beaconMessageProtobufService;

    @PostMapping(path = "/acknowledge-beacon-message", consumes = "application/x-protobuf", produces = MediaType.TEXT_PLAIN_VALUE)
    public String acknowledgeBeaconMessage(@RequestBody ProtoMessages.BeaconMessage beaconMessage) {
        return beaconMessageProtobufService.acknowledgeBeaconMessage(beaconMessage);
    }
}
