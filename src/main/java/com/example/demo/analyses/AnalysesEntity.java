package com.example.demo.analyses;

import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.GenericEntity;
import com.example.demo.workers.WorkersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "analyses")
@SQLDelete(sql = "update analyses set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
@NamedEntityGraph(name = "AnalysesEntity.graph_1",
        attributeNodes = {
                @NamedAttributeNode("worker"),
        }
)
public class AnalysesEntity  extends AuditingGeneric<UUID>  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String name;

    @Column(name = "pay_amount",nullable = false)
    private Float payAmount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private WorkersEntity worker;

    @Column(name = "worker_id",insertable = false,updatable = false)
    private UUID workerId;


}
