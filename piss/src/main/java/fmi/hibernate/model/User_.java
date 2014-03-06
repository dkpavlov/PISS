package fmi.hibernate.model;

import javax.persistence.metamodel.SingularAttribute;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 12/24/13
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */
public class User_ {
    public static volatile SingularAttribute<User, String> username;
    public static volatile SingularAttribute<User, UserRole> role;
    public static volatile SingularAttribute<User, Boolean> archived;
    public static volatile SingularAttribute<User, Boolean> forValidation;
}
