package mongo.movie.controller;

import java.nio.charset.Charset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mongo.movie.domain.TrendingMovie;
import mongo.movie.service.MovieService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api")
public class MovieController {

	private final MovieService movieService;
	
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping(value = "/movieList")
	public ResponseEntity<Mono<TrendingMovie[]>> saveMovieList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<Mono<TrendingMovie[]>>(movieService.getTrendingByDay(), headers, HttpStatus.OK);
	}
	
	@GetMapping(value = "/trending/{id}")
	public ResponseEntity<Optional<TrendingMovie>> getTrendingMovieById(@PathVariable int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<Optional<TrendingMovie>>(movieService.getTrendingMovieById(id), headers, HttpStatus.OK);
	}
	
	@PostMapping("/trending/movie")
	public void createTrendingMovie() {
		// 원래는 매개변수로 TrendingMovie trendingMovie를 받지만 임의로 추가합니다.
		TrendingMovie movie = TrendingMovie.builder()
			.id(3)
			.adult(true)
			.genre_ids(new int[] {4, 7})
			.overview("아이언맨 죽음")
			.title("아이언맨")
			.vote_average(9)
			.vote_count(20000)
			.build();
		movieService.createTrendingMovie(movie);
	}
}
