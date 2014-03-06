package fmi.repository;

import fmi.hibernate.model.StudentList;
import fmi.hibernate.model.ThesisCommittee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-7
 * Time: 10:18
 * To change this template use File | Settings | File Templates.
 */
public interface StudentListRepository extends PagingAndSortingRepository<StudentList, Long>, JpaSpecificationExecutor<StudentList> {
    public List<StudentList> findByCommittee(ThesisCommittee committee);
    public List<StudentList> findByUserGraduatedOnBetween(Date startDate, Date endDate);
    public List<StudentList> findByCommitteeIn(List<ThesisCommittee> committeeList);
}
