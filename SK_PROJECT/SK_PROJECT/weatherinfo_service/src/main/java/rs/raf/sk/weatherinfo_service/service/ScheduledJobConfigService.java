package rs.raf.sk.weatherinfo_service.service;

import lombok.RequiredArgsConstructor;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import rs.raf.sk.weatherinfo_service.dao.ScheduledJobDao;
import rs.raf.sk.weatherinfo_service.domain.ScheduledJob;
import rs.raf.sk.weatherinfo_service.domain.enums.JobType;
import rs.raf.sk.weatherinfo_service.util.QuartzUtil;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ScheduledJobConfigService {

    private final Scheduler scheduler;

    private final QuartzUtil quartzUtil;

    private final ScheduledJobDao scheduledJobDao;

    @EventListener(ApplicationReadyEvent.class)
    public void initialize() {
        if(!scheduledJobDao.existsByType(JobType.MEASUREMENT))storeJobByType(JobType.MEASUREMENT);
        if(!scheduledJobDao.existsByType(JobType.NOTIFICATION)) storeJobByType(JobType.NOTIFICATION);
    }

    private void storeJobByType(JobType type) {
        ScheduledJob scheduledJob = ScheduledJob.builder()
                .id(UUID.randomUUID().toString())
                .type(type)
                .cron(type.defValue).build();
        scheduledJobDao.save(scheduledJob);
        try {
            scheduler.scheduleJob(quartzUtil.buildJobDetail(scheduledJob.getId(), ScheduledJobResolver.class),
                    quartzUtil.buildCronTrigger(scheduledJob.getId(), scheduledJob.getCron()));
        } catch (SchedulerException e) {
            scheduledJobDao.delete(scheduledJob);
        }
    }

}
