package rs.raf.sk.weatherinfo_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.raf.sk.weatherinfo_service.domain.enums.JobType;

import javax.persistence.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
@Table(name = "scheduled_job")
public class ScheduledJob {

    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    private JobType type;

    private String cron;

}
