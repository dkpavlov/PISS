package fmi.repository;

import fmi.hibernate.model.Internship;
import fmi.hibernate.model.Thesis;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-3
 * Time: 14:19
 * To change this template use File | Settings | File Templates.
 */
public interface ThesisRepository extends PagingAndSortingRepository<Thesis, Long>, JpaSpecificationExecutor<Thesis> {
    public List<Thesis> findByThesisProposalTutorUsername(String username);
    public Thesis findByUserId(Long id);
}
