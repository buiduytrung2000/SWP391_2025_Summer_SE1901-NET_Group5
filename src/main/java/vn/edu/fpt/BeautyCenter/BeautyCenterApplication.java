package vn.edu.fpt.BeautyCenter;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeautyCenterApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		// Set biến env cho toàn bộ ứng dụng
		dotenv.entries().forEach(entry -> {
			System.setProperty(entry.getKey(), entry.getValue());
		});
		SpringApplication.run(BeautyCenterApplication.class, args);
	}

}
