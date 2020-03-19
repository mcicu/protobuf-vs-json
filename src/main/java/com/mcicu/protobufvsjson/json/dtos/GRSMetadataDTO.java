package com.mcicu.protobufvsjson.json.dtos;

import lombok.Data;

@Data
public class GRSMetadataDTO {

    private String id;
    private Long receptionDateMillis;


    public static final class Builder {
        private GRSMetadataDTO gRSMetadataDTO;

        private Builder() {
            gRSMetadataDTO = new GRSMetadataDTO();
        }

        public static Builder aGRSMetadataDTO() {
            return new Builder();
        }

        public Builder setId(String id) {
            gRSMetadataDTO.setId(id);
            return this;
        }

        public Builder setReceptionDateMillis(Long receptionDateMillis) {
            gRSMetadataDTO.setReceptionDateMillis(receptionDateMillis);
            return this;
        }

        public GRSMetadataDTO build() {
            return gRSMetadataDTO;
        }
    }
}
