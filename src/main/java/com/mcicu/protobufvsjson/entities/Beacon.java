package com.mcicu.protobufvsjson.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "BEACONS")
@Data
public class Beacon {

    @Id
    private String id;
    private Float latitude;
    private Float longitude;
}
