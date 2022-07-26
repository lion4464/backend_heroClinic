package com.example.demo.room_type;

import com.example.demo.generic.AuditingGeneric;
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
@Table(name = "room_type")
@SQLDelete(sql = "update room_type set deleted = 'true' where id = ?")
public class RoomTypeEntity  extends AuditingGeneric<UUID> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", insertable = false, updatable = false, nullable = false)
    private UUID id;
    private String name;

    private Float paymentAmount;

}
