package com.isco.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class (model) represent extra info about {@link Note}
 * e.g likes and dislike count, or views count and etc.,
 * By {@link javax.persistence.OneToOne} mapping between {@link Note}
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "note_info")
public class NoteInfo {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "views_count")
    private Long viewsCount;

    @Column(name = "likes_count")
    private Long likesCount;

    @Column(name = "dislikes_count")
    private Long dislikesCount;

    @OneToOne
    private Note note;

}
