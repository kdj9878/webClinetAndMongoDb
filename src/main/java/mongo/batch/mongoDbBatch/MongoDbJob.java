package mongo.batch.mongoDbBatch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mongo.movie.domain.TrendingMovie;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MongoDbJob {

	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	// MongoOperations의 bulkOperation이라는 데이터 대량 삽입 기능 때문에
	// MongoTemplate 대신에 사용
	private final MongoOperations mongoOperations;
	
	@Bean
	public Job executeMongoDbJob() {
		
		Job excuteJob = jobBuilderFactory.get("excuteJob")
				.start(firstStep())
				.build();
		
		return excuteJob;
	}
	
	@Bean(name = "mongoJobFirstStep")
	@JobScope // 역할 공부하기
	public Step firstStep() {
		return stepBuilderFactory.get("firstStep")
				.<TrendingMovie, TrendingMovie>chunk(10) // Input, Output, chunk 사이즈
				.reader(dbDataReader())
				.processor(movieProcessor())
				.writer(movieWriter())
				.build();
	}
	
	@Bean
	public MongoDbProcessor movieProcessor() {
		return new MongoDbProcessor();
	}
	
	@Bean
	public MongoDbWriter movieWriter() {
		return new MongoDbWriter();
	}
	
	public MongoItemReader<TrendingMovie> dbDataReader(){
		log.info("firstStep reader is running");
		MongoItemReader<TrendingMovie> trendingMovieItemReader = new MongoItemReader<>();
		trendingMovieItemReader.setTemplate(mongoOperations);
		trendingMovieItemReader.setCollection("TrendingMovie");
		trendingMovieItemReader.setTargetType(TrendingMovie.class);
		trendingMovieItemReader.setQuery(new Query());
		Map<String, Sort.Direction> sort = new HashMap<>(1);
		sort.put("_id", Sort.Direction.ASC);
		trendingMovieItemReader.setSort(sort);
		trendingMovieItemReader.setPageSize(1);
		return trendingMovieItemReader;
	}
	
}
