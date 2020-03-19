package com.mcicu.protobufvsjson.json.dtos;

import lombok.Data;

@Data
public class BeaconMessageDTO {

    private String id;
    private UserDataDTO userData;
    private SatelliteMetadataDTO satelliteMetadata;
    private GRSMetadataDTO grsMetadata;
    private MCSMetadataDTO mcsMetadata;


    public static final class Builder {
        private BeaconMessageDTO beaconMessageDTO;

        private Builder() {
            beaconMessageDTO = new BeaconMessageDTO();
        }

        public static Builder aBeaconMessageDTO() {
            return new Builder();
        }

        public Builder setId(String id) {
            beaconMessageDTO.setId(id);
            return this;
        }

        public Builder setUserData(UserDataDTO userData) {
            beaconMessageDTO.setUserData(userData);
            return this;
        }

        public Builder setSatelliteMetadata(SatelliteMetadataDTO satelliteMetadata) {
            beaconMessageDTO.setSatelliteMetadata(satelliteMetadata);
            return this;
        }

        public Builder setGrsMetadata(GRSMetadataDTO grsMetadata) {
            beaconMessageDTO.setGrsMetadata(grsMetadata);
            return this;
        }

        public Builder setMcsMetadata(MCSMetadataDTO mcsMetadata) {
            beaconMessageDTO.setMcsMetadata(mcsMetadata);
            return this;
        }

        public BeaconMessageDTO build() {
            return beaconMessageDTO;
        }
    }
}
