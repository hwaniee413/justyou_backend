package myshop.backend.domain;


import lombok.*;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity(name = "DEPT")
@Table(name="DEPT")
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deptno;
    private String deptname;
}
