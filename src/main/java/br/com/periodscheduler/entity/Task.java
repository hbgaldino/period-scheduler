package br.com.periodscheduler.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "scheduled_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private Date scheduledTime;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "log_message")
    private String logMessage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
