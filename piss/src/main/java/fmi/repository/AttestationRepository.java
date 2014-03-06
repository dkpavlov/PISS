package fmi.repository;

import fmi.hibernate.model.Attestation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created with IntelliJ IDEA.
 * User: dkpavlov
 * Date: 1/7/14
 * Time: 22:15
 * To change this template use File | Settings | File Templates.
 */
public interface AttestationRepository extends PagingAndSortingRepository<Attestation, Long>, JpaSpecificationExecutor<Attestation> {
}
