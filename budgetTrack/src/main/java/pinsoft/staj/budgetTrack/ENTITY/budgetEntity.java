package pinsoft.staj.budgetTrack.ENTITY;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "budget_track_table")
public class budgetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime date;
    private String Description;
    private int Money;
    private String Type;

    public budgetEntity() {
    }

    public budgetEntity(Long id, LocalDateTime date, String description, int money, String type) {
        this.id = id;
        this.date = date;
        Description = description;
        Money = money;
        Type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getMoney() {
        return Money;
    }

    public void setMoney(int money) {
        Money = money;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
