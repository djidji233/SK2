package rs.raf.sk.weatherinfo_service.util;

import org.quartz.*;
import org.springframework.stereotype.Component;

@Component
public class QuartzUtil {

    public JobDetail buildJobDetail (String jobId, Class<? extends Job> resolver) {
        return JobBuilder.newJob(resolver)
                .withIdentity(jobId)
                .storeDurably()
                .build();
    }

    public CronTrigger buildCronTrigger (String jobId, String cronExpression) {
        return TriggerBuilder.newTrigger()
                .withIdentity(jobId)
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
    }

}
