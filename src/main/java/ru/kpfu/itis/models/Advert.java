package ru.kpfu.itis.models;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Advert {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Long authorId;
    private String category;
    private String city;
    private String address;
    private String salesStartDate;
    private String dateOfSale;
    private Boolean status;
}
