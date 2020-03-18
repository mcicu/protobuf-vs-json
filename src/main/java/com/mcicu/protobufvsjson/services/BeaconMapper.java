package com.mcicu.protobufvsjson.services;

import com.mcicu.protobufvsjson.entities.Beacon;
import com.mcicu.protobufvsjson.proto_pojos.ProtoMessages;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeaconMapper {

    private static final MapperFacade mapperFacade;

    static {
        //considering that proto messages are immutable,
        // we can use orika to map unidirectionally from proto messages to entities
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        factory.classMap(ProtoMessages.Beacon.class, Beacon.class).byDefault();
        mapperFacade = factory.getMapperFacade();
    }

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
        return mapperFacade.map(message, Beacon.class);
    }
}
