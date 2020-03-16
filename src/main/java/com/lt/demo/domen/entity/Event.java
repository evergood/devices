package com.lt.demo.domen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lt.demo.domen.Type;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @Column(name = "event_id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id")
    @JsonIgnore
    private Device device;

    @Column(name = "date")
    private LocalDateTime time;

    @Column(name = "is_read")
    private boolean isRead;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private Type type;


}
