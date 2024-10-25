package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private Long userId;
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private Integer age;
    private Long authorId;
}

