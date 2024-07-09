package com.fg.grow_control.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrowingParameter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long value;

    @ManyToOne
    @JoinColumn(name = "id_grow_stage")
    private GrowStage growStage;

    @ManyToOne
    @JoinColumn(name = "id_growing_parameter_type")
    private GrowingParameterType growingParameterType;


    @OneToMany(fetch = FetchType.EAGER)
    @Singular
    private List<OptimalGrowingParameter> optimalGrowingParameters;


    @OneToOne(mappedBy = "growingParameter")
    private MeasurementDevice measurementDevice;
}
