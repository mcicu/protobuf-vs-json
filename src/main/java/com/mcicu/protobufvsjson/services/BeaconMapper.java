package com.mcicu.protobufvsjson.services;

import com.mcicu.protobufvsjson.entities.Beacon;
import com.mcicu.protobufvsjson.proto_pojos.ProtoMessages;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeaconMapper {

    public ProtoMessages.Beacon toProtoMessage(Beacon entity) {
        return ProtoMessages.Beacon.newBuilder()
                .setId(entity.getId())
                .setLatitude(entity.getLatitude())
                .setLongitude(entity.getLongitude())
                .build();
    }

    public ProtoMessages.Beacons toProtoListMessage(List<Beacon> entities) {
        ProtoMessages.Beacons.Builder builder = ProtoMessages.Beacons.newBuilder();
        for (Beacon entity : entities) {
            builder.addBeacons(toProtoMessage(entity));
        }
        return builder.build();
    }

    public Beacon toEntity(ProtoMessages.Beacon message) {
        //TODO maybe use orika to map unidirectionally from proto message to entity
        throw new RuntimeException("Not implemented");
    }
}
