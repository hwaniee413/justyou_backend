package myshop.backend.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ADMINCODE")
@Entity
@Data
@SequenceGenerator(name="ADMINCODE_SEQ_GENERATOR", sequenceName = "ADMINCODE_SEQ", initialValue = 1, allocationSize = 1) //Oracle일 경우 추가
public class Admincode {
    private long id;
    private String admin_code;
}
