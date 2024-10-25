package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "paper")
public class Paper {
    @Id
    @Column("paper_id")
    private Long paperId;
    private String title;
    @Column("abstract_of_paper")
    private String abstractOfPaper;
    @Column("publication_date")
    private Date publicationDate;
    private String status;
    @Column("file_url")
    private String fileUrl;
}
