package org.example.springboot.service.posts;

import lombok.RequiredArgsConstructor;
import org.example.springboot.domain.posts.Posts;
import org.example.springboot.domain.posts.PostsRepository;
import org.example.springboot.web.dto.PostsListResponseDto;
import org.example.springboot.web.dto.PostsResponseDto;
import org.example.springboot.web.dto.PostsSaveRequestDto;
import org.example.springboot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);  // JpaRepository에서 이미 delete 메소드를 지원하고 있다. 엔티티를 파라미터로 삭제할 수도 있고, deleteByid 메소드를 이용하면 id로 삭제할 수도 있다. 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
    }

    @Transactional(readOnly = true)  // (readOnly = true) 옵션을 주면 트랜잭션 범위는 유지하고 조회 기능만 남겨두어 조회 속도가 개선된다. 등록, 수정, 삭제 기능이 전혀 없는 서비스 메소드에서 사용하는 것이 좋다.
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)  // .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

}
