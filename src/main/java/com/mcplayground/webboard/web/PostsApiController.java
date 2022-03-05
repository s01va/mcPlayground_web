package com.mcplayground.webboard.web;

import com.mcplayground.webboard.service.posts.PostsService;
import com.mcplayground.webboard.web.dto.PostsResponseDto;
import com.mcplayground.webboard.web.dto.PostsSaveRequestDto;
import com.mcplayground.webboard.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    // Create
    @PostMapping("/api/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    // Read
    @GetMapping("/api/posts")
    public List<PostsResponseDto> findAllDesc() {
        return postsService.findAllDesc();
    }

    @GetMapping("/api/posts/{seq}")
    public PostsResponseDto findByPostsSeq(@PathVariable Long seq) {
        return postsService.findBySeq(seq);
    }

    // Update
    @PutMapping("/api/posts/{seq}")
    public Long update(@PathVariable Long seq, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(seq, requestDto);
    }

    // Delete
    @DeleteMapping("/api/posts/{seq}")
    public Long delete(@PathVariable Long seq) {
        postsService.delete(seq);
        return seq;
    }
}
