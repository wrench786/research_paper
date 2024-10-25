package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Review {
    private Long reviewId;
    private int paperId;
    private int reviewerId;
    private String comments;
    private Date reviewDate;
    private Long decisionId;
}
