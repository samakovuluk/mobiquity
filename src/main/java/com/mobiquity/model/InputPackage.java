package com.mobiquity.model;

import java.util.ArrayList;
import java.util.List;

public class InputPackage {

    private Double weightLimit = 0.0;
    private Double cost = 0.0;
    private List<Package> packages = new ArrayList<>();

    public InputPackage(){
    }
    public InputPackage(Double weightLimit, List<Package> packages) {
        this.weightLimit = weightLimit;
        this.packages = packages;
    }

    public Double getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(Double weightLimit) {
        this.weightLimit = weightLimit;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }

    public InputPackage add(Package item)
    {
        this.weightLimit = this.weightLimit + item.getWeight();
        this.cost = this.cost + item.getPrice();
        this.packages.add(item);
        return this;
    }

    @Override
    public String toString() {
        return  "weightLimit=" + weightLimit +
                ", cost=" + cost +
                ", packages=" + packages ;
    }
}
