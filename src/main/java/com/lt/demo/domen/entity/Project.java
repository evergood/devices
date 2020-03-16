package com.lt.demo.domen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class Project {

    @Id
    @Column(name = "project_id")
    private Integer id;

    @Column(name = "project_name")
    private String name;

    @OneToMany(mappedBy = "project", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Device> devices;
}
