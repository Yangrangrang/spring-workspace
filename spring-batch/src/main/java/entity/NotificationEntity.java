package entity;

import entity.status.NotificationEvent;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "notification")
public class NotificationEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
    private Integer notificationSeq;    //알람 순번
    private String uuid;                //사용자 uuid (카카오톡)

    private NotificationEvent event;     //이벤트
    private String text;                //알람 내용
    private boolean sent;               //발송 여부
    private LocalDateTime sentAt;       //발송 일시
}
