package com.mcicu.protobufvsjson.json;

import com.mcicu.protobufvsjson.json.dtos.BeaconMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/json")
@AllArgsConstructor
public class JsonRestController {

    private final BeaconMessageJsonService beaconMessageJsonService;

    @PostMapping(path = "/acknowledge-beacon-message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String acknowledgeBeaconMessage(@RequestBody BeaconMessageDTO beaconMessageDTO) {
        return beaconMessageJsonService.acknowledgeBeaconMessage(beaconMessageDTO);
    }
}
