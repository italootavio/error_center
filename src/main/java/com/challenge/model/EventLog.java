package com.challenge.model;

import com.challenge.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EventLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Level level;

    @Column
    @NotEmpty(message = "description field cannot be empty")
    private String description;

    @Column
    @NotEmpty(message = "source field cannot be empty")
    private String source;

    @Column
    @Positive
    private Integer quantity;

    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    @Column
    @NotEmpty(message = "log field cannot be empty")
    private String log;

    /*
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
     */
}
