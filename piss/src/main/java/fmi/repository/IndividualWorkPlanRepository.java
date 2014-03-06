package fmi.repository;

import fmi.hibernate.model.IndividualWorkPlan;
import fmi.hibernate.model.Internship;
import fmi.hibernate.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-7
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public interface IndividualWorkPlanRepository extends PagingAndSortingRepository<IndividualWorkPlan, Long>, JpaSpecificationExecutor<IndividualWorkPlan> {
    public IndividualWorkPlan findByPhd(User phd);
    public IndividualWorkPlan findByPhdId(Long id);
}
