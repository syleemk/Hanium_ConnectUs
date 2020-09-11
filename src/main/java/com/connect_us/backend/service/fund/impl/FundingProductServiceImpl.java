package com.connect_us.backend.service.fund.impl;

import com.connect_us.backend.domain.fund.FundingProduct;
import com.connect_us.backend.domain.fund.FundingProductRepository;
import com.connect_us.backend.service.fund.FundingProductService;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductCreateRequestDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductListResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductResponseDto;
import com.connect_us.backend.web.dto.v1.fund.product.FundingProductUpdateResquestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundingProductServiceImpl implements FundingProductService {

    private final FundingProductRepository fundingProductRepository;

    /**
     * interface가 아닌 class속에 @Transactional 어노테이션 선언하는게 바람직하다.
     * save : 펀드생성
     * */
    @Transactional
    @Override
    public Long save(FundingProductCreateRequestDto createRequestDto) {
        return fundingProductRepository.save(createRequestDto.toEntity()).getId();
    }

    /**
     * 펀드 정보 수정 (FundingStatus 수정)
     * */
    @Transactional
    @Override
    public Long update(Long id, FundingProductUpdateResquestDto resquestDto) {
        FundingProduct fundingProduct = fundingProductRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 펀드가 없습니다. id="+id));

        fundingProduct.update(
                resquestDto.getName(),
                resquestDto.getImage(),
                resquestDto.getGoalPrice(),
                resquestDto.getAddress(),
                resquestDto.getInformation(),
                resquestDto.getDue(),
                resquestDto.getFundingStatus());

        return id;
    }

    /**
     * 펀드 삭제 (soft delete)
     * */
    @Override
    public void delete(Long id) {
        FundingProduct fundingProduct = fundingProductRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 펀드가 없습니다. id="+id));

//        fundingProductRepository.delete(fundingProduct); // 완전 삭제
        fundingProduct.setStatusDelete(); // 소프트 딜리트
    }

    /**
     * 펀드 찾기
     * */
    @Transactional(readOnly = true)
    @Override
    public FundingProductResponseDto findById(Long id) {
        FundingProduct entity = fundingProductRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 펀드가 없습니다. id="+id));

        return new FundingProductResponseDto(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public List<FundingProductListResponseDto> findAllDesc() {
        return fundingProductRepository.findAllDesc().stream()
                .map(FundingProductListResponseDto::new)
                .collect(Collectors.toList());
    }

}
