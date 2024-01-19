package com.demo.gym;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.demo.gym.domain.Product;
import com.demo.gym.repository.ProductRepository;

@SpringBootApplication
public class GymApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(ProductRepository productRepository) {
		return args->{
			if(productRepository.findAll().isEmpty()) {
				Product data1 = new Product();
				
				data1.setProductName("Berat Badan Berlebih/obesitas");
				data1.setDescription("Program gym untuk pelanggan obesitas atau berat badan berlebih");
				data1.setDuration("120 menit");
				data1.setMeeting("10 Pertemuan");
				data1.setPrice("200000");
				data1.setSchedule("Sabtu 16:00");
				
				productRepository.save(data1);
				
				Product data2 = new Product();

				data2.setProductName("Menjaga Kesehatan");
				data2.setDescription("Program gym untuk menjaga kebugaran pelanggan");
				data2.setDuration("120 menit");
				data2.setMeeting("10 Pertemuan");
				data2.setPrice("150000");
				data2.setSchedule("Sabtu 08:00");
				
				productRepository.save(data2);

			}
			return;
			
		};
	}

}
