package com.minhajcse.dto;

import com.minhajcse.model.Paper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaperDTO {
    private Paper paper;
    private List<Long> authorList;
}
