package com.mcicu.protobufvsjson.json;

import com.mcicu.protobufvsjson.json.dtos.BeaconMessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BeaconMessageJsonService {

    public String acknowledgeBeaconMessage(BeaconMessageDTO beaconMessageDTO) {
        return beaconMessageDTO.getId();
    }
}
