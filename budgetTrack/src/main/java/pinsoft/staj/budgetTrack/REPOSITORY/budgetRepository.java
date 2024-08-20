package pinsoft.staj.budgetTrack.REPOSITORY;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pinsoft.staj.budgetTrack.ENTITY.budgetEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface budgetRepository extends JpaRepository<budgetEntity, Long> {
    @Query("SELECT b FROM budgetEntity b WHERE b.date BETWEEN :startDate AND :endDate")
    List<budgetEntity> findByDateBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT b FROM budgetEntity b WHERE b.date > :startDate")
    List<budgetEntity> findByDateAfter(@Param("startDate") LocalDateTime startDate);

    @Query("SELECT b FROM budgetEntity b WHERE b.date < :endDate")
    List<budgetEntity> findByDateBefore(@Param("endDate") LocalDateTime endDate);
}
