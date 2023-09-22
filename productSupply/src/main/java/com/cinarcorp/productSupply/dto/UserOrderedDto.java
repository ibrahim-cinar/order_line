package com.cinarcorp.productSupply.dto;

import com.cinarcorp.productSupply.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class UserOrderedDto {
        //private String id;
        private String firstName;
        private String lastName;
        private String address;
        private String email;
        private List<OrderUserDto> order;
}
