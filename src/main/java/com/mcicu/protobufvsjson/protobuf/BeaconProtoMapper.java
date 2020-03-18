package com.mcicu.protobufvsjson.protobuf;

import com.mcicu.protobufvsjson.entities.Beacon;

import java.util.List;

public class BeaconProtoMapper {

    public static ProtoMessages.Beacon toProtoMessage(Beacon entity) {
        return ProtoMessages.Beacon.newBuilder()
                .setId(entity.getId())
                .setLatitude(entity.getLatitude())
                .setLongitude(entity.getLongitude())
                .build();
    }

    public static ProtoMessages.Beacons toProtoListMessage(List<Beacon> entities) {
        ProtoMessages.Beacons.Builder builder = ProtoMessages.Beacons.newBuilder();
        for (Beacon entity : entities) {
            builder.addBeacons(toProtoMessage(entity));
        }
        return builder.build();
    }

    public static Beacon toEntity(ProtoMessages.Beacon message) {
        Beacon entity = new Beacon();
        entity.setId(message.getId());
        entity.setLatitude(message.getLatitude());
        entity.setLongitude(message.getLongitude());
        return entity;
    }
}
