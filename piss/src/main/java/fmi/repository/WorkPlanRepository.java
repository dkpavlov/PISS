package fmi.repository;

import fmi.hibernate.model.User;
import fmi.hibernate.model.WorkPlan;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-8
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */
public interface WorkPlanRepository extends PagingAndSortingRepository<WorkPlan, Long>, JpaSpecificationExecutor<WorkPlan> {
    public WorkPlan findByPhd(User phd);
    public WorkPlan findByPhdId(Long id);
}
