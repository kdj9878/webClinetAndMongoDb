package mongo.batch.mongoDbBatch;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import mongo.movie.domain.TrendingMovie;

@Transactional
@Slf4j
public class MongoDbProcessor implements ItemProcessor<TrendingMovie, TrendingMovie>{

	@Override
	public TrendingMovie process(TrendingMovie item) throws Exception {
		log.info("processor method is started");
		return item;
	}
}
