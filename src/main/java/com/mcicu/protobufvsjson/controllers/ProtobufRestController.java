package com.mcicu.protobufvsjson.controllers;

import com.mcicu.protobufvsjson.proto_pojos.ProtoMessages;
import com.mcicu.protobufvsjson.services.BeaconService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/protobuf")
@AllArgsConstructor
public class ProtobufRestController {

    private final BeaconService beaconService;

    @GetMapping(path = "/beacons", produces = "application/x-protobuf")
    public ProtoMessages.Beacons getBeacons() {
        return beaconService.getBeacons();
    }

    @GetMapping(path = "/beacons/{beaconId}", produces = "application/x-protobuf")
    public ProtoMessages.Beacon getBeacon(@PathVariable("beaconId") String beaconId) {
        return beaconService.getBeacon(beaconId);
    }
}
