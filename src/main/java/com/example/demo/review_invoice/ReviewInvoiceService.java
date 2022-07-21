package com.example.demo.review_invoice;


import com.example.demo.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ReviewInvoiceService {
    ReviewInvoiceEntity update(ReviewInvoiceRequest obj);
    String delete(UUID id);
    ReviewInvoiceEntity getId(UUID id);
    ReviewInvoiceEntity save(ReviewInvoiceRequest obj, UserEntity user);
    Page<ReviewInvoiceEntity> all(Specification<ReviewInvoiceEntity> spec, Pageable page);
    List<ReviewInvoiceCount> getReviewsCount(Date from, Date to,UserEntity user);
}
