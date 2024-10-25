package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.util.Date;
import java.util.List;

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
