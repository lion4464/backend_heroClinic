package com.example.demo.room_place;

import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.room.RoomEntity;
import com.example.demo.room_type.RoomTypeEntity;
import com.example.demo.workers.WorkersEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room_place")

@NamedEntityGraph(
        name = "RoomPlaceEntity.graph_1",
        attributeNodes = {
                @NamedAttributeNode("room")
        }
)
public class RoomPlaceEntity extends AuditingGeneric<UUID> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    @Column(name="pay_amount")
    private Float payAmount;

    private Integer placeNumber;

    private Float protcent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", referencedColumnName = "id")
    private WorkersEntity worker;

    @Column(name = "worker_id",insertable = false,updatable = false)
    private UUID workerId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",referencedColumnName = "id")
    private RoomEntity room;

    @Column(name = "room_id",insertable = false,updatable = false)
    private UUID roomId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomTypeEntity roomType;

    @Column(name = "room_type_id",insertable = false,updatable = false)
    private UUID roomTypeId;

    @Enumerated(EnumType.STRING)
    private DataStatusEnum status = DataStatusEnum.ACTIVE;

    @Column(name = "close_date")
    private Long closedDate;
    
}
