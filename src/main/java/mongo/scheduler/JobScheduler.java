package mongo.scheduler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mongo.batch.mongoDbBatch.MongoDbJob;
import mongo.batch.openApiBatch.OpenApiJob;

@Slf4j
@Component
@RequiredArgsConstructor
public class JobScheduler {

	private final JobLauncher jobLauncher;
	
	private final MongoDbJob mongoDbJob;
	private final OpenApiJob openApiJob;
	
	//@fixedDelay는 해당 작업이 끝난 시점부터 시간을 세고, @fixedRate는 해당 작업의 시작 시점부터 시간을 센다
	// 10초에 한번씩 실행, Spring Application이 정상 실행되고 10초 후에 실행
//	@Scheduled(cron = " 0/10 * * * * ?") 
//	public void trendingMovieScheduler() {
//		
//		Map<String, JobParameter> confMap = new HashMap<>();
//        confMap.put("time", new JobParameter(System.currentTimeMillis()));
//        JobParameters jobParameters = new JobParameters(confMap);
//		try {
//			JobExecution jobExecution = jobLauncher.run(mongoDbJob.executeMongoDbJob(), jobParameters);
//			
//			while (jobExecution.isRunning()) {
//				log.info("...");
//			}
//				
//		} catch (JobExecutionAlreadyRunningException
//				|JobRestartException
//				|JobInstanceAlreadyCompleteException
//				|JobParametersInvalidException e) {
//			e.getMessage();
//		}
//	}
	
	@Scheduled(cron = "0/30 * * * * ?") // 3분에 1번씩 실행
	public void openApiRequestSchedule() {
		
		// 넘기는 파라미터를 매번 다르게 해서 별개의 JobInstance로 인식하게 함
		Map<String, JobParameter> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters jobParameters = new JobParameters(confMap);
		try {
			JobExecution jobExecution = jobLauncher.run(openApiJob.trendingMovieJob(), jobParameters);
			
			while (jobExecution.isRunning()) {
				log.info("...");
			}
				
			log.info("Job Execution: " + jobExecution.getStatus());
			log.info("Job getJobConfigurationName: " + jobExecution.getJobConfigurationName());
			log.info("Job getJobId: " + jobExecution.getJobId());
			log.info("Job getExitStatus: " + jobExecution.getExitStatus());
			log.info("Job getJobInstance: " + jobExecution.getJobInstance());
			log.info("Job getStepExecutions: " + jobExecution.getStepExecutions());
			log.info("Job getLastUpdated: " + jobExecution.getLastUpdated());
			log.info("Job getFailureExceptions: " + jobExecution.getFailureExceptions());
			
		} catch (JobExecutionAlreadyRunningException
				|JobRestartException
				|JobInstanceAlreadyCompleteException
				|JobParametersInvalidException e) {
			e.getMessage();
		}
	}
	
}
