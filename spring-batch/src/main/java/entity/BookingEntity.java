package entity;

import entity.status.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "booking")
public class BookingEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    private Integer bookingSeq;     //예약 순번
    private Integer passSeq;        //이용권 순번
    private String userId;          //사용자 ID

    @Enumerated(EnumType.STRING)
    private BookingStatus status;   //상태
    private boolean usedPass;       //이용권 사용 여부
    private boolean attended;       //출석 여부

    private LocalDateTime startedAt;    //시작 일시
    private LocalDateTime endedAt;      //종료 일시
    private LocalDateTime cancelledAt;  //취소 일시

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passSeq", insertable = false, updatable = false)
    private PassEntity passEntity;

    // endedAt 기준, yyyy-MM-HH 00:00:00
    public LocalDateTime getStatisticsAt() {
        return this.endedAt.withHour(0).withMinute(0).withSecond(0).withNano(0);

    }

}
