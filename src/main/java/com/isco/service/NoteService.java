package com.isco.service;

import com.isco.model.Note;
import com.isco.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Page<Note> findPaginated(int page, int size) {
        return noteRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Note> findPaginatedSearch(int page, int size, String query){
        return noteRepository.findAll(query, PageRequest.of(page, size));
    }

}
