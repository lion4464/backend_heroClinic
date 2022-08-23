package com.example.demo.room;

import com.example.demo.generic.AuditingGeneric;
import com.example.demo.generic.DataStatusEnum;
import com.example.demo.room_type.RoomTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room")
@SQLDelete(sql = "update room set deleted = 'true' where id = ?")
@Where(clause = "deleted = 'false'")
@NamedEntityGraph(
        name = "RoomEntity.graph_1",
        attributeNodes = {
                @NamedAttributeNode("roomType")
        }
)
public class RoomEntity extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;

    private String name;
    private Integer capacity;
    private Float payAmount;
    private Float protcent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomTypeEntity roomType;

    @Column(name = "room_type_id",insertable = false,updatable = false)
    private UUID roomTypeId;

    @Enumerated(EnumType.STRING)
    private DataStatusEnum status = DataStatusEnum.ACTIVE;

    @Transient
    private Integer freePlace;





}
