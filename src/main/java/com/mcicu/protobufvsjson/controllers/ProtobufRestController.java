package com.mcicu.protobufvsjson.controllers;

import com.mcicu.protobufvsjson.json_pojos.Beacon;
import com.mcicu.protobufvsjson.proto_pojos.Payload;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/protobuf")
public class ProtobufRestController {

    @GetMapping(path = "/beacon", produces = "application/x-protobuf")
    public Payload.Beacon getBeacon() {
        Payload.Beacon beacon = Payload.Beacon.newBuilder()
                .setId("29if3i9i49f4i9")
                .setLatitude(333)
                .setLongitude(555)
                .build();

        return beacon;
    }

    @PostMapping(path = "/add-beacon", consumes = "application/x-protobuf", produces = "application/json")
    public Beacon readProtoBeacon(@RequestBody Payload.Beacon protoBeacon) {
        Beacon beacon = new Beacon();
        beacon.setId(protoBeacon.getId());
        beacon.setLatitude(protoBeacon.getLatitude());
        beacon.setLongitude(protoBeacon.getLongitude());

        return beacon;
    }
}
