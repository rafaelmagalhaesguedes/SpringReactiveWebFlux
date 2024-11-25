package com.web.flux.entities;

import com.web.flux.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tb_categories")
public class Category {
    @Id
    private UUID id;
    private CategoryType type;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
