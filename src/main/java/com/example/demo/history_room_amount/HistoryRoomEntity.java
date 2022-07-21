package com.example.demo.history_room_amount;

import com.example.demo.analyses.AnalysesEntity;
import com.example.demo.generic.AuditingGeneric;
import com.example.demo.room.RoomEntity;
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
@Table(name = "history_room")
@SQLDelete(sql = "update history_room set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
public class HistoryRoomEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private Float amount;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomEntity room;

    @Column(name = "room_id",insertable = false,updatable = false)
    private UUID roomId;


}
