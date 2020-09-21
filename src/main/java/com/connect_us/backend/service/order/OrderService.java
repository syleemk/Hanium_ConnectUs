package com.connect_us.backend.service.order;

import com.connect_us.backend.domain.account.Account;
import com.connect_us.backend.domain.account.AccountRepository;
import com.connect_us.backend.domain.cart.CartItemRepository;
import com.connect_us.backend.domain.cart.CartRepository;
import com.connect_us.backend.domain.enums.OrderStatus;
import com.connect_us.backend.domain.enums.OrderType;
import com.connect_us.backend.domain.order.BaseOrder;
import com.connect_us.backend.domain.order.BaseOrderRepository;
import com.connect_us.backend.domain.order.ProductOrderItem;
import com.connect_us.backend.domain.order.ProductOrderItemRepository;
import com.connect_us.backend.domain.product.Product;
import com.connect_us.backend.domain.product.ProductRepository;
import com.connect_us.backend.web.dto.v1.order.OrderListResponseDto;
import com.connect_us.backend.web.dto.v1.order.OrderSaveRequestDto;
import com.connect_us.backend.web.dto.v1.order.OrderSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final BaseOrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductOrderItemRepository productOrderItemRepository;

    @Transactional
    public OrderSaveResponseDto save(Account account, OrderSaveRequestDto requestDto){
        // 주문 생성
        BaseOrder productOrder = orderRepository.save(BaseOrder.builder()
                .account(account)
                .name(requestDto.getName())
                .address(requestDto.getAddress())
                .number(requestDto.getNumber())
                .orderType(OrderType.PRODUCT)
                .build());
       
        // 주문 상품 목록 생성
        List<OrderSaveRequestDto.OrderItem> productOrderItems = requestDto.getProducts();

        productOrderItems.stream().forEach((item)-> {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("해당 상품이 없습니다 id = " + item.getProductId()));

            // product 재고 수 줄이는 로직 추가되어야함 (계산 완료시)
            productOrderItemRepository.save(ProductOrderItem.builder()
                    .baseOrder(productOrder)
                    .product(product)
                    .product_cnt(item.getProductCnt())
                    .build());
        });

        // 주문서 작성 성공 리턴
        return OrderSaveResponseDto.builder()
                .success(true)
                .message("주문서 작성 완료")
                .build();
    }

    @Transactional
    public OrderListResponseDto findByAccount(Account account, Pageable pageable){
        List<BaseOrder> orderList = orderRepository.findByAccountId(pageable, account.getId()).getContent();
        return OrderListResponseDto.builder()
                .success(true)
                .message("주문 목록 조회 성공")
                .data(orderList)
                .build();
    }
}
