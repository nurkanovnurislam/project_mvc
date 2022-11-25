package com.peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Table(name = "tasks")
@Setter
@Getter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_gen")
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;

    @Column(length = 500, name = "task_name")
    private String taskName;

    @Column(length = 500, name = "task_text")
    private String taskText;

    @Column(name = "dead_line")
    private LocalDate deadLine;

    public Task(String taskName, String taskText, LocalDate deadLine) {
        this.taskName = taskName;
        this.taskText = taskText;
        this.deadLine = deadLine;
    }

    @ManyToOne(cascade = {DETACH, REFRESH, MERGE}, fetch = LAZY)
    private Lesson lesson;
}
