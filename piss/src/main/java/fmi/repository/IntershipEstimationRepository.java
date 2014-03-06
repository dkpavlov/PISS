package fmi.repository;

import fmi.hibernate.model.Internship;
import fmi.hibernate.model.IntershipEstimation;
import fmi.hibernate.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-2
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */
public interface IntershipEstimationRepository extends PagingAndSortingRepository<IntershipEstimation, Long>, JpaSpecificationExecutor<IntershipEstimation> {
    public List<IntershipEstimation> findByValidated(Boolean validated);
    public IntershipEstimation findByUser(User user);
}
