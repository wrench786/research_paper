package com.minhajcse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaperAndAuthor {
    private Long paperAuthorId;
    private Long paperId;
    private Long authorId;
}
