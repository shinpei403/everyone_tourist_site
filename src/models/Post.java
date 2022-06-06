package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//投稿データのDTOモデル

@Entity
@Table(name = "posts")

public class Post {


//    id

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    タイトル

    @Column(name = "title", length = 255, nullable = false)
    private String title;

//    内容

    @Column(name = "content", nullable = false)
    private String content;

//    投稿をした会員

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

//    登録日時

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

//    更新日時

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;

}
