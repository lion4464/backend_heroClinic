package com.example.demo.history_anaylse_amount;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.generic.AuditingGeneric;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "history_analyse")
@SQLDelete(sql = "update history_analyse set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class HistoryAnalysesEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private Float amount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analyse_id", referencedColumnName = "id")
    private AnalysesEntity analyse;
}
