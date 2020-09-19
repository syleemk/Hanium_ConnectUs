package com.connect_us.backend;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.category.Category;
import com.connect_us.backend.domain.category.CategoryRepository;
import com.connect_us.backend.domain.enums.Gender;
import com.connect_us.backend.domain.enums.Role;
import com.connect_us.backend.domain.enums.Social;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.service.cart.CartService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(CartService cartService, AccountRepository accountRepository, ProductRepository productRepository,
                                    CategoryRepository categoryRepository, PasswordEncoder passwordEncoder) throws Exception {
        return (args) -> {
            //initialize
            accountRepository.deleteAll();

            //user, admin+password encode
            Account user = new Account("user", passwordEncoder.encode("1234"),"user", Social.FALSE, Gender.MALE, Role.USER);
            Account seller = new Account("seller", passwordEncoder.encode("1234"),"seller",Social.FALSE, Gender.MALE, Role.SELLER);
            Account admin = new Account("admin", passwordEncoder.encode("1234"),"admin",Social.FALSE,Gender.MALE, Role.ADMIN);

            List<Account> accounts = Arrays.asList(user,seller,admin);
            accountRepository.saveAll(accounts);

            Account account = accountRepository.save(Account.builder()
                    .email("syleemk@naver.com")
                    .password("1234")
                    .name("sylee")
                    .role(Role.SELLER)
                    .build()
            );

            //장바구니 생성
            cartService.create(user);
            cartService.create(seller);
            cartService.create(admin);

            IntStream.rangeClosed(1, 100).forEach(index -> {
                        Category category = categoryRepository.save(Category.builder()
                                .name("카테고리" + index)
                                .build());

                        productRepository.save(Product.builder()
                                .category(category)
								.account(seller)
								.name("상품" + index)
								.price(1000)
								.stock(40)
								.build());
                    }
            );
        };
    }
}
