package fmi.repository;

import fmi.hibernate.model.User;
import fmi.hibernate.model.UserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sbaltov
 * Date: 13-8-23
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public interface  UserRepository extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {
    public User findByUsernameAndArchivedFalse(String username);
    public User findByUsernameAndPassword(String username, String password);
    public List<User> findByForValidationTrue();
    public List<User> findByRoleAndForValidationTrue(UserRole userRole);
    public List<User> findByUsernameContainingAndRoleAndArchivedFalse(String username, UserRole userRole);
    public List<User> findByRoleAndArchivedFalse(UserRole userRole);
    public List<User> findByArchivedFalse();
    public List<User> findByGraduatedTrueAndGraduatedOnBetween(Date startDate, Date endDate);
    public User findByUsername(String username);

}
