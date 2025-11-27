package kr.java.mybatis.domain;

import lombok.Data;

import java.util.List;

@Data
public class Post {
    private Long id;
    private Long authorId; // 작성자 ID -> UserInfoId
    private String title;
    private String content;
    private Integer likeCount; // -> PostLike
    private String createdAt;

    // 1:N 관계 : 작성자 정보
    private UserInfo author;

    // M:N 관계 : 좋아요 목록
    private List<UserInfo> likedUsers;
}
