package br.com.periodscheduler.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class ScheduledTask {

    private Integer id;

    @EqualsAndHashCode.Exclude
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private Date scheduledDate;
}
