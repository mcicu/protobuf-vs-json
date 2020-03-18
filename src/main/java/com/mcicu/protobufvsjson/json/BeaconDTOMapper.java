package com.mcicu.protobufvsjson.json;

import com.mcicu.protobufvsjson.entities.Beacon;

import java.util.LinkedList;
import java.util.List;

public class BeaconDTOMapper {

    public static BeaconDTO toDTO(Beacon entity) {
        return BeaconDTO.Builder.aBeaconDTO()
                .withId(entity.getId())
                .withLatitude(entity.getLatitude())
                .withLongitude(entity.getLongitude())
                .build();
    }

    public static List<BeaconDTO> toDTOList(List<Beacon> entities) {
        List<BeaconDTO> output = new LinkedList<>();
        for (Beacon entity : entities) {
            output.add(BeaconDTOMapper.toDTO(entity));
        }
        return output;
    }

    public static Beacon toEntity(BeaconDTO dto) {
        Beacon entity = new Beacon();
        entity.setId(dto.getId());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        return entity;
    }
}
