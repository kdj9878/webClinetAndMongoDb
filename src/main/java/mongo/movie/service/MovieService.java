package mongo.movie.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mongo.movie.domain.TrendingMovie;
import mongo.movie.domain.TrendingObject;
import mongo.movie.repository.MovieRepository;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class MovieService {

	@Value("${movie.openApi.trending.uri}")
	private String trendingMovieUri;
	
	@Value("${movie.openApi.apiKey}")
	private String apiKey;
	
	private final WebClient.Builder wcBuilder;
	
	private final MovieRepository movieRepo;
	
	// 영화 OPEN API에 오늘의 인기 급상승 영화 리스트를 불러오는 메소드
	public Mono<TrendingMovie[]> getTrendingByDay() {
		return wcBuilder.build()    
		        .get()      
		        .uri(trendingMovieUri + "/trending/movie/day?api_key={apiKey}", apiKey)
		        .retrieve()
		        .bodyToMono(TrendingObject.class)
		        .map(fileds -> fileds.getResults());
	}	
	
	// 내 로컬 DB에 저장되어 있는 특정 아이디의 트랜딩 영화를 가져오는 메소드
	public Optional<TrendingMovie> getTrendingMovieById(int id) {
		return movieRepo.findById(id);
	}

	// 트랜딩 영화를 내 DB에 저장하는 메소드
	public void createTrendingMovie(TrendingMovie movie) {
		movieRepo.insert(movie);
	}
}
