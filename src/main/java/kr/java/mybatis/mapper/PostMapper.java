package kr.java.mybatis.mapper;

import kr.java.mybatis.domain.Post;
import kr.java.mybatis.domain.PostLike;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    // 게시글 작성
    void insertPost(Post post);

    // 게시글 조회 (N:1)
    List<Post> findAllWithAuthor();

    // 게시글 상세조회 (작성자 + 좋아요 목록 -> M:N -> Post-PostLike-UserInfo)
    Post findByIdWithDetails(long postId);

    // 좋아요 추가
    void insertLike(PostLike postLike);

    // 좋아요 취소
    void deleteLike(PostLike postLike);

    // 좋아요 수 증가/감소
    void increaseLikeCount(long postId);
    void decreaseLikeCount(long postId);

    // 좋아요 여부 확인
    boolean isLiked(Long userId, Long postId);
}
