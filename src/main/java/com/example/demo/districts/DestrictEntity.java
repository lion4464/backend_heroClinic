package com.example.demo.districts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "districts")
public class DestrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private Long id;
    @Column(name = "region_id")
    private Long regionId;
    @Column(name = "name_oz")
    private String nameOz;
    @Column(name = "name_ru")
    private String nameRu;
}
