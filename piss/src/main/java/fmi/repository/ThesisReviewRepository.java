package fmi.repository;

import fmi.hibernate.model.ThesisCommittee;
import fmi.hibernate.model.ThesisReview;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

public interface ThesisReviewRepository extends PagingAndSortingRepository<ThesisReview, Long>, JpaSpecificationExecutor<ThesisReview> {

}
