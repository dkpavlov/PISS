package fmi.repository;

import fmi.hibernate.model.ThesisCommittee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 15:33
 * To change this template use File | Settings | File Templates.
 */
public interface ThesisCommitteeRepository extends PagingAndSortingRepository<ThesisCommittee, Long>, JpaSpecificationExecutor<ThesisCommittee>{

    public List<ThesisCommittee> findByDateBetween(Date startDate, Date endDate);
    public List<ThesisCommittee> findByDateAfterOrderByDateAsc(Date date);

}
