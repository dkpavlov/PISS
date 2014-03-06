package fmi.repository;

import fmi.hibernate.model.OneYearWorkPlan;
import fmi.hibernate.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-8
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */
public interface OneYearWorkPlanRepository extends PagingAndSortingRepository<OneYearWorkPlan, Long>, JpaSpecificationExecutor<OneYearWorkPlan> {
    public OneYearWorkPlan findByPhdAndYear(User phd, Long year);
}
