package com.mcicu.protobufvsjson.json.dtos;

import lombok.Data;

@Data
public class MCSMetadataDTO {

    private MCSId mcsId;
    private Long timeOfArrivalAtMCSMillis;
    private Long timeOfAvailabilityAtSCMillis;

    public enum MCSId {
        NOMINAL, BACKUP
    }


    public static final class Builder {
        private MCSMetadataDTO mCSMetadataDTO;

        private Builder() {
            mCSMetadataDTO = new MCSMetadataDTO();
        }

        public static Builder aMCSMetadataDTO() {
            return new Builder();
        }

        public Builder setMcsId(MCSId mcsId) {
            mCSMetadataDTO.setMcsId(mcsId);
            return this;
        }

        public Builder setTimeOfArrivalAtMCSMillis(Long timeOfArrivalAtMCSMillis) {
            mCSMetadataDTO.setTimeOfArrivalAtMCSMillis(timeOfArrivalAtMCSMillis);
            return this;
        }

        public Builder setTimeOfAvailabilityAtSCMillis(Long timeOfAvailabilityAtSCMillis) {
            mCSMetadataDTO.setTimeOfAvailabilityAtSCMillis(timeOfAvailabilityAtSCMillis);
            return this;
        }

        public MCSMetadataDTO build() {
            return mCSMetadataDTO;
        }
    }
}
