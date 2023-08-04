package com.blog.controller;

import com.blog.payload.PostDto;
import com.blog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/posts")
@RestController
public class PostController {



    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }



    //http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){

       PostDto dto = postService.createPost(postDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3&sortBy=content&sortDir=asc
    //http://localhost:8080/api/posts?pageSize=3
    //http://localhost:8080/api/posts?pageNO=0

    @GetMapping
    public  List<PostDto>listAllPost(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false)int pageNo,
             @RequestParam(value = "pageSize",defaultValue = "10",required = false)int pageSize,
              @RequestParam(value = "sortBy",defaultValue = "id",required = false)String sortBy,
               @RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
        List<PostDto> postDtos = postService.listAllPosts(pageNo,pageSize,sortBy,sortDir);

        return  postDtos;
    }

    //http://localhost:8080/api/posts/1
    @GetMapping("/{id}")
    public  ResponseEntity<PostDto>getPostById(@PathVariable("id")long id){

        PostDto dto = postService.getPostById(id);

        return  new ResponseEntity<>(dto,HttpStatus.OK);


    }

    //http://localhost:8080/api/posts/1
    @PutMapping("/{id}")
    public  ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto , @PathVariable("id") long id){
        PostDto dto = PostService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePostById(@PathVariable("id") long id){
        PostDto postById = postService.getPostById(id);
        return new ResponseEntity<>("post is deleted",HttpStatus.OK);
    }


}
