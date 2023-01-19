package com.green.utils;


import com.green.domain.dto.DocumentListDTO;

import java.time.LocalDateTime;
import java.util.Comparator;


public class DocuCompare implements Comparator<DocumentListDTO> {
    @Override
    public int compare(DocumentListDTO first, DocumentListDTO second) {
        LocalDateTime firstValue = first.getCreatedDate();
        LocalDateTime secondValue =second.getCreatedDate();

        // Order by descending
        if (firstValue.isAfter(secondValue)) {
            return -1;
        } else if (firstValue.isBefore(secondValue)) {
            return 1;
        } else {
            return 0;
        }
    }
}
