package com.cinarcorp.orderLine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
