package com.mcicu.protobufvsjson.json;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/json")
@AllArgsConstructor
public class JsonRestController {

    private final BeaconJsonService beaconJsonService;

    @GetMapping(path = "/beacons", produces = "application/json")
    public List<BeaconDTO> getBeacons() {
        return beaconJsonService.getBeacons();
    }

    @GetMapping(path = "/beacons/{beaconId}", produces = "application/json")
    public BeaconDTO getBeacon(@PathVariable("beaconId") String beaconId) {
        return beaconJsonService.getBeacon(beaconId);
    }

    @PostMapping(path = "/beacons", consumes = "application/json")
    public ResponseEntity<?> createBeacon(@RequestBody BeaconDTO beaconDTO) {
        String resourceId = beaconJsonService.createBeacon(beaconDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .pathSegment("{beaconId}")
                .buildAndExpand(resourceId).toUri();

        return ResponseEntity.created(location).build();
    }
}
