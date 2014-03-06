package fmi.specification;

import fmi.hibernate.model.User;
import fmi.hibernate.model.UserRole;
import fmi.hibernate.model.User_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 12/24/13
 * Time: 22:49
 * To change this template use File | Settings | File Templates.
 */
public class UserSpecification {

    public static Specification<User> searchByUsername(final String username){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(username != null){
                    if(username.trim().length() > 0){
                        return criteriaBuilder.like(userRoot.get(User_.username), username);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        };
    }

    public static Specification<User> roleIs(final UserRole role){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                if(role != null){
                    return criteriaBuilder.equal(userRoot.get(User_.role), role);
                } else {
                    return null;
                }
            }
        };
    }

    public static Specification<User> archiveIs(final Boolean archived){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(userRoot.get(User_.archived), archived);
            }
        };
    }

    public static Specification<User> validatedIs(final Boolean forValidation){
        return new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> userRoot, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(userRoot.get(User_.forValidation), forValidation);
            }
        };
    }


}
