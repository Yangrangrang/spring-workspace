package entity;

import entity.status.BulkPassStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "bulk_pass")
public class BulkPassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    private Integer bulkPassSeq;    //대량 이용권 순번
    private Integer packageSeq;     //패키지 순번
    private String userGroupId;     //사용자 그룹 ID

    @Enumerated(EnumType.STRING)
    private BulkPassStatus status;  //상태
    private Integer count;          //이용권 수, NULL인 경우 무제한

    private LocalDateTime startedAt;    //시작 일시
    private LocalDateTime endedAt;      //종료 일시, NULL인 경우 무제한
}
