package bitc.fullstack405.bitcteam3prj.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "board_like")
@NoArgsConstructor
@AllArgsConstructor
public class BoardLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // 게시물 추천/비추천 id, pk, 사용자,게시글 fk

    @Column
    private String likeYn;
}
