package com.peaksoft.repository.repositoryimpl;

import com.peaksoft.model.Company;
import com.peaksoft.repository.CompanyRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Company> getAllCompany() {
        return manager.createQuery("select c from Company c").getResultList();
    }

    @Override
    public Company getCompanyById(Long id) {
        return manager.find(Company.class, id);
    }

    @Override
    public void saveCompany(Company company) {
        manager.persist(company);
    }

    @Override
    public void updateCompany(Company company) {
        manager.merge(company);
    }

    @Override
    public void deleteCompany(Company company) {
        manager.remove(manager.contains(company) ? company : manager.merge(company));
    }
}
