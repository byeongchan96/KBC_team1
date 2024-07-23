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
@Table(name = "board_comment")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 게시글 댓글 id, pk, board 테이블 fk

    @Column(nullable = false)
    private String contents; // 본문

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 작성일

    @CreatedBy
    @Column(nullable = false)
    private String createdBy; // 작성자

    @LastModifiedDate
    private String updatedAt; // 수정일

    @LastModifiedBy
    private String updatedBy; // 수정자
}
