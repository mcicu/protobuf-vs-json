package com.mcicu.protobufvsjson.json;

import lombok.Data;

@Data
public class BeaconDTO {

    private String id;
    private Float latitude;
    private Float longitude;


    public static final class Builder {
        private String id;
        private Float latitude;
        private Float longitude;

        private Builder() {
        }

        public static Builder aBeaconDTO() {
            return new Builder();
        }

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public Builder withLatitude(Float latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder withLongitude(Float longitude) {
            this.longitude = longitude;
            return this;
        }

        public BeaconDTO build() {
            BeaconDTO beaconDTO = new BeaconDTO();
            beaconDTO.setId(id);
            beaconDTO.setLatitude(latitude);
            beaconDTO.setLongitude(longitude);
            return beaconDTO;
        }
    }
}
