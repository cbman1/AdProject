package ru.kpfu.itis.models;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNumber;
    private String city;
    private String address;
    private String avatar_uuid;
}
