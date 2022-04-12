package mongo.movie.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import mongo.movie.domain.TrendingMovie;

// 커스텀 메소드를 만들기 위한 구현체
public class CustomizedMognoRepoImpl implements CustomizedMongoRepository {

	// MongoRepository와는 다르게 MongoTemplate는 세세한 쿼리 작업을 할 수 있다.
	private final MongoTemplate mongoTemplate;
	
	@Autowired
	public CustomizedMognoRepoImpl(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}
	
	@Override
	public void findTrendingMovieBy() {
		// TODO Auto-generated method stub
	}
}
