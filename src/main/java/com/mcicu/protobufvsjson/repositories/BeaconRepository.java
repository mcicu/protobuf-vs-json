package com.mcicu.protobufvsjson.repositories;

import com.mcicu.protobufvsjson.entities.Beacon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeaconRepository extends JpaRepository<Beacon, String> {
}
