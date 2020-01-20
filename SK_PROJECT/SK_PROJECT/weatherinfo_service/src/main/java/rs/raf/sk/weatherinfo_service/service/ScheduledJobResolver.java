package rs.raf.sk.weatherinfo_service.service;

import lombok.RequiredArgsConstructor;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import rs.raf.sk.weatherinfo_service.dao.ScheduledJobDao;
import rs.raf.sk.weatherinfo_service.domain.ScheduledJob;
import rs.raf.sk.weatherinfo_service.domain.enums.JobType;

@Component
@RequiredArgsConstructor
public class ScheduledJobResolver extends QuartzJobBean {

    private final Logger logger = LoggerFactory.getLogger(ScheduledJobResolver.class);

    private final ScheduledJobDao scheduledJobDao;

    private final WeatherInfoService weatherInfoService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        var key = jobExecutionContext.getJobDetail().getKey().getName();

        logger.info("This is a job time! Job: {}", key);
        ScheduledJob scheduledJob = scheduledJobDao.findById(key).orElseThrow(RuntimeException::new);

        JobType jobType = scheduledJob.getType();

        switch (jobType) {
            case MEASUREMENT:
//                weatherInfoService.updateStore();
                break;
            case NOTIFICATION:
                break;
        }

    }

}
