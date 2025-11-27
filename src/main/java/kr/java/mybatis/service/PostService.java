package kr.java.mybatis.service;

import kr.java.mybatis.domain.Post;
import kr.java.mybatis.domain.PostLike;
import kr.java.mybatis.domain.UserInfo;
import kr.java.mybatis.domain.UserLogin;
import kr.java.mybatis.mapper.PostMapper;
import kr.java.mybatis.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    @Transactional
    public void createPost(
        long authorId,
        String title,
        String content
    ) {
        Post post = new Post();
        post.setAuthorId(authorId);
        post.setTitle(title);
        post.setContent(content);

        postMapper.insertPost(post);
    }

    // 게시글 목록 조회 (읽기 전용)
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postMapper.findAllWithAuthor();
    }

    // 게시글 상세 조회 (읽기 전용)
    @Transactional(readOnly = true)
    public Post getPost(long postId) {
        return postMapper.findByIdWithDetails(postId);
    }

    /**
     * 좋아요 토글 (M:N 관계 처리)
     * 좋아요 추가/삭제 + 좋아요 수 증가/감소를 하나의 트랜잭션으로
     */
    @Transactional
    public boolean toggleLike(Long userId, Long postId) {
        PostLike postLike = new PostLike();
        postLike.setUserId(userId);
        postLike.setPostId(postId);
        if (postMapper.isLiked(userId, postId)) {
            // 좋아요 취소
            postMapper.deleteLike(postLike);
            postMapper.decreaseLikeCount(postId);
            return false;
        } else {
            // 좋아요 추가
            postMapper.insertLike(postLike);
            postMapper.increaseLikeCount(postId);
            return true;
        }
    }
}
