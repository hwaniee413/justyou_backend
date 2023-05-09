package myshop.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NOTICE")
@Entity
@Data
@SequenceGenerator(name="NOTICE_SEQ_GENERATOR", sequenceName = "NOTICE_SEQ", initialValue = 1, allocationSize = 1) //Oracle일 경우 추가
@DynamicUpdate
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GENERATOR")
    private long nid;

    private String title;
    private String content;
    private long lookup;

    @Column(name = "rdate", updatable = false, nullable = false)
    private LocalDateTime rdate;
    @Column(name = "udate", nullable = false)
    private LocalDateTime udate;

    private Admin admin;

    public long getAdminNum() {
        return admin.getAdmin_num();
    }
    public String getAdminName() {
        return admin.getAdmin_name();
    }




}
