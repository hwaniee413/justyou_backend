package myshop.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADMIN")
@Entity
@Data
@SequenceGenerator(name="ADMIN_SEQ_GENERATOR", sequenceName = "ADMIN_SEQ", initialValue = 1, allocationSize = 1) //Oracle일 경우 추가
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_SEQ_GENERATOR")
    @Column(name="ADMIN_NUM")
    private long admin_num;

    @Column(name="ADMIN_ID")
    private String admin_id;
    private String admin_pwd;

    @Column(name="ADMIN_NAME")
    private String admin_name;

    private String admin_code;

    private LocalDateTime rdate;

    private LocalDateTime udate;
}
