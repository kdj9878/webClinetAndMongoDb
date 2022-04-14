package mongo.batch.openApiBatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import lombok.extern.slf4j.Slf4j;
import mongo.movie.domain.TrendingMovie;


@Slf4j
public class OpenApiWriter implements ItemWriter<TrendingMovie[]> {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void write(List<? extends TrendingMovie[]> items) throws Exception {
		// chunk 사이즈가 1이므로 한번만 돌음
		for(int i = 0; i < items.size(); i++) {
			TrendingMovie[] movies = items.get(i);
			for(TrendingMovie movie : movies) {
				log.info("movie : {}", movie.toString());
				mongoOperations.save(movie);
			}
		}
	}
}
