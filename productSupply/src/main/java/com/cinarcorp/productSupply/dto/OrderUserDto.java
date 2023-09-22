package com.cinarcorp.productSupply.dto;
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
public class OrderUserDto {
    private String id;
    private int totalPaid;
    private int piece;
    private LocalDateTime orderDate;
    private boolean isComplete;
    private List<ProductDto> product;

}
