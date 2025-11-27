package kr.java.mybatis.service;

import kr.java.mybatis.domain.Post;
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
}
