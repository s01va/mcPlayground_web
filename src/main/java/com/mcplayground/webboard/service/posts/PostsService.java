package com.mcplayground.webboard.service.posts;

import com.mcplayground.webboard.domain.posts.Posts;
import com.mcplayground.webboard.domain.posts.PostsRepository;
import com.mcplayground.webboard.web.dto.PostsResponseDto;
import com.mcplayground.webboard.web.dto.PostsSaveRequestDto;
import com.mcplayground.webboard.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    // Create
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getSeq();
    }

    // Read
    @Transactional
    public List<PostsResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    public PostsResponseDto findBySeq(Long seq) {
        Posts entity = postsRepository.findById(seq).orElseThrow(
                () -> new IllegalArgumentException("no data. seq=" + seq.toString())
        );
        return new PostsResponseDto(entity);
    }

    // Update
    @Transactional
    public Long update(Long seq, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(seq).orElseThrow(
                () -> new IllegalArgumentException("no data. seq=" + seq.toString())
        );
        posts.update(
                requestDto.getTitle(),
                requestDto.getContent()
        );
        return seq;
    }

    // Delete
    @Transactional
    public void delete(Long seq) {
        Posts posts = postsRepository.findById(seq).orElseThrow(
                () -> new IllegalArgumentException("no data. seq=" + seq.toString())
        );
        postsRepository.delete(posts);
    }
}
