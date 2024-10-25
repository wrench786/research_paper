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
@Table(name = "paper_and_author")
public class PaperAndAuthor {
    @Id
    @Column("mapping_id")
    private Long paperAuthorId;
    @Column("paper_id")
    private Long paperId;
    @Column("author_id")
    private Long authorId;
}
