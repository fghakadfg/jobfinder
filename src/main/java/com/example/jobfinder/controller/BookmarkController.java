package com.example.jobfinder.controller;

import com.example.jobfinder.entity.Bookmark;
import com.example.jobfinder.service.BookmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applicant")
public class BookmarkController {
    @Autowired
    private BookmarkService bookmarkService;

    @PostMapping("/bookmarks")
    public ResponseEntity<Bookmark> addBookmark(@RequestBody Bookmark bookmark) {
        return ResponseEntity.ok(bookmarkService.addBookmark(bookmark));
    }

    @GetMapping("/bookmarks")
    public ResponseEntity<List<Bookmark>> getBookmarksByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(bookmarkService.getBookmarksByUserId(userId));
    }
}