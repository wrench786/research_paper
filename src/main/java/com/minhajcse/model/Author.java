package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Author {
    private Long authorId;
    private String bio;
    private String institution;
}
