package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user_table")
public class User {
    @Id
    @Column("user_id")
    private Long userId;
    @Column("user_name")
    private String userName;
    @Column("full_name")
    private String fullName;
    private String email;
    private String password;
    private Integer age;
    @Column("author_id")
    private Long authorId;
}

