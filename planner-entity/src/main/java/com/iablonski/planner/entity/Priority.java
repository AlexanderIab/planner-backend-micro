package com.iablonski.planner.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "priority", schema = "todo", catalog = "planner_todo")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Priority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String color;
    @Column(name = "user_id")
    private Long userId;
}