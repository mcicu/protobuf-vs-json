package com.mcicu.protobufvsjson.json.dtos;

import lombok.Data;

@Data
public class SatelliteMetadataDTO {

    private String id;
    private Float modulation;
    private Integer blockNumber;
    private Long toaMillis;
    private Float foaMhz;
    private Float receptionLevel;
    private Float snr;
    private QualityIndicator qualityIndicator;

    public enum QualityIndicator {
        LEVEL_1, LEVEL_2, LEVEL_3
    }


    public static final class Builder {
        private SatelliteMetadataDTO satelliteMetadataDTO;

        private Builder() {
            satelliteMetadataDTO = new SatelliteMetadataDTO();
        }

        public static Builder aSatelliteMetadataDTO() {
            return new Builder();
        }

        public Builder setId(String id) {
            satelliteMetadataDTO.setId(id);
            return this;
        }

        public Builder setModulation(Float modulation) {
            satelliteMetadataDTO.setModulation(modulation);
            return this;
        }

        public Builder setBlockNumber(Integer blockNumber) {
            satelliteMetadataDTO.setBlockNumber(blockNumber);
            return this;
        }

        public Builder setToaMillis(Long toaMillis) {
            satelliteMetadataDTO.setToaMillis(toaMillis);
            return this;
        }

        public Builder setFoaMhz(Float foaMhz) {
            satelliteMetadataDTO.setFoaMhz(foaMhz);
            return this;
        }

        public Builder setReceptionLevel(Float receptionLevel) {
            satelliteMetadataDTO.setReceptionLevel(receptionLevel);
            return this;
        }

        public Builder setSnr(Float snr) {
            satelliteMetadataDTO.setSnr(snr);
            return this;
        }

        public Builder setQualityIndicator(QualityIndicator qualityIndicator) {
            satelliteMetadataDTO.setQualityIndicator(qualityIndicator);
            return this;
        }

        public SatelliteMetadataDTO build() {
            return satelliteMetadataDTO;
        }
    }
}
