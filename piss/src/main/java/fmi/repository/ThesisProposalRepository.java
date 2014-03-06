package fmi.repository;


import fmi.hibernate.model.ThesisProposal;
import fmi.hibernate.model.ThesisProposalStatus;
import fmi.hibernate.model.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dpavlov
 * Date: 14-1-2
 * Time: 16:31
 * To change this template use File | Settings | File Templates.
 */
public interface ThesisProposalRepository extends PagingAndSortingRepository<ThesisProposal, Long>, JpaSpecificationExecutor<ThesisProposal> {
    public List<ThesisProposal> findByStatus(ThesisProposalStatus status);
    public List<ThesisProposal> findByStatusAndTutor(ThesisProposalStatus status, User tutor);
    public ThesisProposal findByUserUsername(String username);
    public List<ThesisProposal> findByTutorAndUserGraduatedOnBetween(User user, Date startDate, Date endDate);
}
