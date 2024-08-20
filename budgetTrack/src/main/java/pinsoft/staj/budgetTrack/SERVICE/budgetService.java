package pinsoft.staj.budgetTrack.SERVICE;

import org.springframework.stereotype.Service;
import pinsoft.staj.budgetTrack.REPOSITORY.budgetRepository;
import pinsoft.staj.budgetTrack.ENTITY.budgetEntity;

import java.util.List;

@Service
public class budgetService {
    private budgetRepository budgetRepository;

    public void BudgetService(budgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public budgetEntity createBudgetEntry(budgetEntity budgetEntry) {
        return budgetRepository.save(budgetEntry);
    }

    public List<budgetEntity> getAllBudgetEntries() {
        return budgetRepository.findAll();
    }

    // Add more CRUD methods as needed
}