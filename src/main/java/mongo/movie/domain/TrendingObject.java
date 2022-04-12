package mongo.movie.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrendingObject {
	
	private int page;
	private TrendingMovie[] results;
	private int total_pages;
	private int total_results;
	
	@Builder
	public TrendingObject(int page, TrendingMovie[] results, int total_pages, int total_results) {
		this.page = page;
		this.results = results;
		this.total_pages = total_pages;
		this.total_results = total_results;
	}
}
