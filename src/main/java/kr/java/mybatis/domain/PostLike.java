package kr.java.mybatis.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostLike {
    private Long id;
    private Long userId; // FK
    private Long postId; // FK
    private LocalDateTime createdAt;
}
