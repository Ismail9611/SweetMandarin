package com.isco.repository;


import com.isco.model.Note;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long>, JpaRepository<Note, Long> {
    Page<Note> findAll(Pageable pageable);

    @Query(value = "select n from Note n where n.author.username like %:query%")
    Page<Note> findAll(@Param(value = "query") String query, Pageable pageable);
}
