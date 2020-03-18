package com.mcicu.protobufvsjson.controllers;

import com.mcicu.protobufvsjson.proto_pojos.ProtoMessages;
import com.mcicu.protobufvsjson.services.BeaconService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

    @PostMapping(path = "/beacons", consumes = "application/x-protobuf")
    public ResponseEntity<?> createBeacon(@RequestBody ProtoMessages.Beacon beaconMessage) {
        String resourceId = beaconService.createBeacon(beaconMessage);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{beaconId}")
                .buildAndExpand(resourceId).toUri();

        return ResponseEntity.created(location).build();
    }
}
