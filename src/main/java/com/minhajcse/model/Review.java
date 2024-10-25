package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "review")
public class Review {
    @Id
    @Column("review_id")
    private Long reviewId;
    @Column("paper_id")
    private int paperId;
    @Column("reviewer_id")
    private int reviewerId;
    private String comments;
    @Column("review_date")
    private Date reviewDate;
    @Column("decision_id")
    private Long decisionId;
}
