package com.lt.demo.domen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devices")
@Data
public class Device {

    @Id
    @Column(name = "device_id")
    @JsonIgnore
    private Integer id;

    @Column(name = "serial_number")
    @JsonIgnore
    private String serialNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @JsonIgnore
    private Project project;

    @OneToMany(mappedBy = "device", cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Event> events;

}
