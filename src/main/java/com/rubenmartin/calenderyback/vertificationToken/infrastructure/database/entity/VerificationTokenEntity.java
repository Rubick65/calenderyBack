package com.rubenmartin.calenderyback.vertificationToken.infrastructure.database.entity;

import com.rubenmartin.calenderyback.user.infrastructure.database.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "VERIFICATION_TOKEN")
public class VerificationTokenEntity {
    private static final int EXPIRATION = 60 * 24;

    public VerificationTokenEntity(UserEntity user, String token) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_usuario")
    private UserEntity user;

    @Column(name = "expiryDate")
    private Date expiryDate;

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }


}
