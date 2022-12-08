package ru.kpfu.itis.dao;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdvertDao {
    private String name;
    private String description;
    private Integer price;
    private String category;
}
