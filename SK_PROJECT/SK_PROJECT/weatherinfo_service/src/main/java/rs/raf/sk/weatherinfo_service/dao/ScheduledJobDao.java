package rs.raf.sk.weatherinfo_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.sk.weatherinfo_service.domain.ScheduledJob;
import rs.raf.sk.weatherinfo_service.domain.enums.JobType;

public interface ScheduledJobDao extends JpaRepository<ScheduledJob, String> {

    boolean existsByType (JobType type);

}
