package br.com.periodscheduler.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cron_expression")
    private String cronExpression;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "log_message")
    private String logMessage;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
