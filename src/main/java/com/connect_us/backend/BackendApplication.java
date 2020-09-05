package com.connect_us.backend;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.category.CategoryRepository;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.IntStream;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(AccountRepository accountRepository, ProductRepository productRepository,
                                    CategoryRepository categoryRepository) throws Exception {
        return (args) -> {
            Account account = accountRepository.save(Account.builder()
                    .email("syleemk@naver.com")
                    .password("1234")
                    .name("sylee")
                    .role(Role.SELLER)
                    .build()
            );

            IntStream.rangeClosed(1, 100).forEach(index -> {
                        Category category = categoryRepository.save(Category.builder()
                                .name("카테고리" + index)
                                .build());

                        productRepository.save(Product.builder()
                                .category(category)
								.account(account)
								.name("상품" + index)
								.price(1000)
								.stock(40)
								.build());
                    }
            );
        };
    }
}
