package com.vlas.gradpro.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SuppressWarnings("JpaQlInspection")
//@NamedQueries({
//        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user.id=:userId ORDER BY m.dateTime DESC"),
//        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
//
//        @NamedQuery(name = Meal.UPDATE, query = "UPDATE Meal m SET m.dateTime = :datetime, m.description=:description where m.id=:id and m.restaurant.id=:restId")
//})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"rest_id", "date_time"}, name = "meals_unique_user_description_idx")})
public class Meal extends BaseEntity {
//    public static final String GET = "Meal.get";
//    public static final String ALL_SORTED = "Meal.getAll";
//    public static final String DELETE = "Meal.delete";
//    public static final String UPDATE = "Meal.update";

    @Column(name = "date_time", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = false)
    @NotEmpty
    @SafeHtml
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    private Restaurant restaurant;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description) {
        this(null, dateTime, description);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", restaurant=" + restaurant +
                '}';
    }
}
