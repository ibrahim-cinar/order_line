package com.cinarcorp.orderLine.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateUserRequest {
 private String userId;
 private String firstName;
 private String lastName;
 private String address;
 private String email;}
