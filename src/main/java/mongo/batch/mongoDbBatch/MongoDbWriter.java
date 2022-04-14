package mongo.batch.mongoDbBatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mongo.movie.domain.TrendingMovie;


@Slf4j
public class MongoDbWriter implements ItemWriter<TrendingMovie> {

	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public void write(List<? extends TrendingMovie> items) throws Exception {
		// TODO Auto-generated method stub
//		for(int i = 0; i < items.size(); i++) {
//			TrendingMovie updateMovie = items.get(i);
//			int prevVoteCount = updateMovie.getVote_count();
//			log.info("updateMovie : {}", updateMovie.toString());
//			updateMovie.setVote_count(prevVoteCount + 1); 
//			log.info("updatedMovie : {}", updateMovie.toString());
//			mongoOperations.save(updateMovie);
//		}
	}
}
