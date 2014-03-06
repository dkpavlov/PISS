package fmi.repository;

import fmi.hibernate.model.Internship;
import fmi.hibernate.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 12/29/13
 * Time: 15:58
 * To change this template use File | Settings | File Templates.
 */
public interface InternshipRepository extends PagingAndSortingRepository<Internship, Long>, JpaSpecificationExecutor<Internship> {
    public List<Internship> findByValidated(Boolean validated);
    public Internship findByUser(User user);
}
