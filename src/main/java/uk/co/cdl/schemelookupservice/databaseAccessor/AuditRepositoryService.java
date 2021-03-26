package uk.co.cdl.schemelookupservice.databaseAccessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.co.cdl.schemelookupservice.model.Audit;

@Service
public class AuditRepositoryService {

    @Autowired
    AuditRepository auditRepository;

    public void save(Audit audit){
        auditRepository.save(audit);
    }


}
