package mongo.movie.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import mongo.movie.domain.TrendingMovie;

public interface MovieRepository extends MongoRepository<TrendingMovie, Integer>, CustomizedMongoRepository{
	
}
