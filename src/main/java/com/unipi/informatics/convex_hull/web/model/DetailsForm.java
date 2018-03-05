package com.unipi.informatics.convex_hull.web.model;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.*;

public class DetailsForm {

    @NotNull(message = "This field is required!")
    @Min(value = 3, message = "The lower limit is 3.")
    @Max(value = 50000, message = "The upper limit is 50000.")
    private Integer pointsCount;

    @NotNull(message = "This field is required!")
    @DecimalMin("0.01")
    @DecimalMax("1.0")
    private Double mutationRate;


    @NotNull(message = "This field is required!")
    @Min(value = 50, message = "The lower limit is 5.")
    @Max(value = 5000, message = "The upper limit is 5000.")
    private Integer populationCount;
    public Integer getPointsCount() {
        return pointsCount;
    }

    public void setPointsCount(Integer pointsCount) {
        this.pointsCount = pointsCount;
    }

    public Double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(Double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public Integer getPopulationCount() {
        return populationCount;
    }

    public void setPopulationCount(Integer populationCount) {
        this.populationCount = populationCount;
    }
}
