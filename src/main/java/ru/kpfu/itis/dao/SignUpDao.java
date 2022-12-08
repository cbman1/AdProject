package ru.kpfu.itis.dao;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDao {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phoneNumber;
}
