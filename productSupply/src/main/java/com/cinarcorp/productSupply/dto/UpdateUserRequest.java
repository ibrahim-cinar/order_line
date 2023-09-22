package com.cinarcorp.productSupply.dto;

import com.cinarcorp.productSupply.model.Order;
import com.cinarcorp.productSupply.model.Product;
import com.cinarcorp.productSupply.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
