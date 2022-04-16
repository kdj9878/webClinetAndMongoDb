package mongo.batch.openApiBatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import mongo.movie.domain.TrendingMovie;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class OpenApiJob {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Job trendingMovieJob() {
		
		Job trendingMovieJob = jobBuilderFactory.get("trendingMovieJob")
				.start(openApiFristStep())
				.build();
		
		return trendingMovieJob;
	}
	
	@Bean
	@JobScope // Job parameter를 사용할 경우 JobScope와 StepScope를 사용해줘야함
	public Step openApiFristStep() {
		return stepBuilderFactory.get("openApiFristStep")
				.<Mono<TrendingMovie[]>, TrendingMovie[]>chunk(1) // Input, Output, chunk 사이즈
				.reader(openApiReader())
				.processor(dataEditProcessor())
				.writer(dataInsertWrite())
				.build();
	}
	
	@Bean
	@StepScope
	public OpenApiProcessor dataEditProcessor() {
		return new OpenApiProcessor();
	}
	
	@Bean
	@StepScope
	public OpenApiWriter dataInsertWrite() {
		return new OpenApiWriter();
	}
	
	@Bean
	@StepScope
	public OpenApiReader openApiReader(){
		return new OpenApiReader();
	}
	
}
