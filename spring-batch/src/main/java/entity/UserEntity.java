package entity;

import entity.status.UserStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Id
    private String userId;      //사용자 ID

    private String userName;    //사용자 이름
    @Enumerated(EnumType.STRING)
    private UserStatus status;  //상태
    private String phone;       //연락처

    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> meta;   //메타 정보, JSON

    public String getUuid() {
        String uuid = null;
        if (meta.containsKey("uuid")) {
            uuid = String.valueOf(meta.get("uuid"));
        }
        return uuid;

    }

}
