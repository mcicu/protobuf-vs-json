package com.mcicu.protobufvsjson.protobuf;

import com.mcicu.protobufvsjson.entities.Beacon;
import com.mcicu.protobufvsjson.repositories.BeaconRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BeaconProtoService {

    private final BeaconRepository beaconRepository;

    public ProtoMessages.Beacons getBeacons() {
        List<Beacon> beacons = beaconRepository.findAll();
        return BeaconProtoMapper.toProtoListMessage(beacons);
    }

    public ProtoMessages.Beacon getBeacon(String id) {
        Optional<Beacon> beacon = beaconRepository.findById(id);
        return beacon.map(BeaconProtoMapper::toProtoMessage)
                .orElse(ProtoMessages.Beacon.newBuilder().build());
    }

    public String createBeacon(ProtoMessages.Beacon beaconMessage) {
        Beacon beacon = BeaconProtoMapper.toEntity(beaconMessage);
        return beaconRepository.save(beacon).getId();
    }
}
