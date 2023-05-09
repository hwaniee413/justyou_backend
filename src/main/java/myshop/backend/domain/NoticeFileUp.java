package myshop.backend.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name="NOTICE_FILES")
@SequenceGenerator(name="NOTICE_FILES_SEQ_GENERATOR", sequenceName = "NOTICE_FILES_SEQ", initialValue = 1, allocationSize = 1)
public class NoticeFileUp {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICE_FILES_SEQ_GENERATOR") //Oracle일 경우 추가
    private long fid;

    private String fname;

    private String ofname;

    private String savedpath;

    private long nid;


}
