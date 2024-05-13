package codoacodo.vuelosapi.service;

import codoacodo.vuelosapi.exceptions.ResourceNotFoundException;
import codoacodo.vuelosapi.model.Company;
import codoacodo.vuelosapi.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company getCompanyById(Long id) throws ResourceNotFoundException {
        return companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company","id", id));
    }

    public void deleteCompany(Long id) throws ResourceNotFoundException {
        Company company = companyRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Company", "id", id));
        companyRepository.deleteById(company.getId());
    }

    public Optional<Company> updateCompany(Company company) {
        companyRepository.save(company);
        return companyRepository.findById(company.getId());
    }
}
