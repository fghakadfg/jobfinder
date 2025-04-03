package com.example.jobfinder.service;

import com.example.jobfinder.entity.Bookmark;
import com.example.jobfinder.repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {
    @Autowired
    private BookmarkRepository bookmarkRepository;

    public Bookmark addBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    public List<Bookmark> getBookmarksByUserId(Long userId) {
        return bookmarkRepository.findByUserId(userId);
    }
}