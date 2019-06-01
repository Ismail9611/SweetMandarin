package com.isco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "published")
    private Date published;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "note")
    private Set<Comment> comments;

    @OneToOne(mappedBy = "note", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private NoteInfo noteInfo;

    public Note(String title, String content, Date published, User author, Set<Comment> comments){
        this.title = title;
        this.content = content;
        this.published = published;
        this.author = author;
        this.comments = comments;
    }
}
