package com.mcicu.protobufvsjson.json;

import com.mcicu.protobufvsjson.entities.Beacon;
import com.mcicu.protobufvsjson.repositories.BeaconRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BeaconJsonService {

    private final BeaconRepository beaconRepository;

    public List<BeaconDTO> getBeacons() {
        List<Beacon> beacons = beaconRepository.findAll();
        return BeaconDTOMapper.toDTOList(beacons);
    }

    public BeaconDTO getBeacon(String id) {
        Optional<Beacon> beacon = beaconRepository.findById(id);
        return beacon.map(BeaconDTOMapper::toDTO)
                .orElse(null);
    }

    public String createBeacon(BeaconDTO beaconDTO) {
        Beacon beacon = BeaconDTOMapper.toEntity(beaconDTO);
        return beaconRepository.save(beacon).getId();
    }
}
