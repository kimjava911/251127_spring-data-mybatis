package kr.java.mybatis.mapper;

import kr.java.mybatis.domain.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {
    // 게시글 작성
    void insertPost(Post post);

    // 게시글 조회 (N:1)
    List<Post> findAllWithAuthor();
}
