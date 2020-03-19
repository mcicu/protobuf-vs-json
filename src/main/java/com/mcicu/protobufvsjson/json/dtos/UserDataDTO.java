package com.mcicu.protobufvsjson.json.dtos;

import lombok.Data;

@Data
public class UserDataDTO {

    private String randomUserData;


    public static final class Builder {
        private UserDataDTO userDataDTO;

        private Builder() {
            userDataDTO = new UserDataDTO();
        }

        public static Builder anUserDataDTO() {
            return new Builder();
        }

        public Builder setRandomUserData(String randomUserData) {
            userDataDTO.setRandomUserData(randomUserData);
            return this;
        }

        public UserDataDTO build() {
            return userDataDTO;
        }
    }
}
