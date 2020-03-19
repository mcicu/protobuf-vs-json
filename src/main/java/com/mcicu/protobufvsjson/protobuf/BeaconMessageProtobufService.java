package com.mcicu.protobufvsjson.protobuf;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BeaconMessageProtobufService {

    public String acknowledgeBeaconMessage(ProtoMessages.BeaconMessage beaconMessage) {
        return beaconMessage.getId();
    }
}
