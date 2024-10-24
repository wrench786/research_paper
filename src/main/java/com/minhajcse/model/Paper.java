package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Paper {
    private Long paperId;
    private String title;
    private String abstractOfPaper;
    private Date publicationDate;
    private String status;
    private String fileUrl;
}
