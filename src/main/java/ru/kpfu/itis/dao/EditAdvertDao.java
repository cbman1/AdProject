package ru.kpfu.itis.dao;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditAdvertDao {
    private String name;
    private String description;
    private Integer price;
}
