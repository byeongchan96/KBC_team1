package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "board")
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 게시글 id, pk, user 테이블 FK

    @Column(nullable = false)
    private String title; // 제목

    @Column(nullable = false)
    private String content; // 본문

    @Column(nullable = false)
    private int visitCnt; // 조회수

    @Column(nullable = false)
    private String category; // 카테고리

    @Column(nullable = false)
    private String warning; // 주의

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 작성일

    @CreatedBy
    @Column(nullable = false)
    private String createdBy; // 작성자

    @LastModifiedDate
    private LocalDateTime updatedAt; // 수정일

    @LastModifiedBy
    private String updatedBy; // 수정자
}
