package com.example.demo.revoke_refresh_token;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import javax.persistence.*;
import java.util.UUID;


@Entity
@Table(name = "revoke_refresh_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class RevokeRefreshTokenEntity{

    @Id
    @Column(name = "refresh_token", unique = true)
    private String refreshToken;

    @CreatedDate
    @Column(name = "created_date")
    private Long createdDate;


    @Column(name = "user_id")
    private UUID userId;

    public RevokeRefreshTokenEntity(String refreshToken, UUID userId) {
        this.refreshToken = refreshToken;
        this.userId = userId;
    }

    public String getId() {
        return refreshToken;
    }


    public boolean isDeleted() {
        return false;
    }
}
