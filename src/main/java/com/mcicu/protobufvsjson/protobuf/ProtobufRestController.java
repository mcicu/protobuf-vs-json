package com.mcicu.protobufvsjson.protobuf;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/protobuf")
@AllArgsConstructor
public class ProtobufRestController {

    private final BeaconProtoService beaconProtoService;

    @GetMapping(path = "/beacons", produces = "application/x-protobuf")
    public ProtoMessages.Beacons getBeacons() {
        return beaconProtoService.getBeacons();
    }

    @GetMapping(path = "/beacons/{beaconId}", produces = "application/x-protobuf")
    public ProtoMessages.Beacon getBeacon(@PathVariable("beaconId") String beaconId) {
        return beaconProtoService.getBeacon(beaconId);
    }

    @PostMapping(path = "/beacons", consumes = "application/x-protobuf")
    public ResponseEntity<?> createBeacon(@RequestBody ProtoMessages.Beacon beaconMessage) {
        String resourceId = beaconProtoService.createBeacon(beaconMessage);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{beaconId}")
                .buildAndExpand(resourceId).toUri();

        return ResponseEntity.created(location).build();
    }
}
