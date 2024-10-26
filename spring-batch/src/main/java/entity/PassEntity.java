package entity;

import entity.status.PassStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    private Integer passSeq;        //이용권 순번
    private Integer packageSeq;     //패키지 순번
    private String userId;          //사용자 ID

    @Enumerated(EnumType.STRING)
    private PassStatus status;      //상태
    private Integer remainingCount; //잔여 이용권 수, NULL인 경우 무제한

    private LocalDateTime startedAt;    //시작 일시
    private LocalDateTime endedAt;      //종료 일시, NULL인 경우 무제한
    private LocalDateTime expiredAt;    //만료 일시

}
