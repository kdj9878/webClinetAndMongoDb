package mongo.movie.repository;

import mongo.movie.domain.TrendingMovie;

// 커스텀 메소드를 위한 인터페이스
public interface CustomizedMongoRepository {
	// 특정 DTO의 커스텀 레포지토리 메서드를 작성할 때, 메소드 이름에 "By"키워드가 없는 경우 오류 발생
	void findTrendingMovieBy();

}
