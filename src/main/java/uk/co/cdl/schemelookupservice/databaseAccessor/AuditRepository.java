package uk.co.cdl.schemelookupservice.databaseAccessor;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.co.cdl.schemelookupservice.model.Audit;

public interface AuditRepository extends JpaRepository<Audit , Integer> {

    @Query("SELECT a FROM Audit a WHERE a.updated >= :yesterday")
    List<Audit> findAllWhereUpdatedAfter(@Param("yesterday")Date date);
}
