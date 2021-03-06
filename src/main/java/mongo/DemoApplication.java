package mongo;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

// 스프링 부트 시작 시 설정 파일(application.yml)에서 데이타소스의 url을 찾게 되는데 MongoDb는 uri라서 못 찾는 것 같습니다.
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableBatchProcessing
@EnableScheduling
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
