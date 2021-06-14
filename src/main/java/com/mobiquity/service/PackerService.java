package com.mobiquity.service;

import com.mobiquity.model.InputPackage;
import com.mobiquity.model.Package;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PackerService implements PackageServiceImpl {


    public InputPackage process(InputPackage inputPackage) {
        var weightLimit = inputPackage.getWeightLimit();
        List<Package> packages = new ArrayList<>(inputPackage.getPackages())
                .stream()
                .filter(e -> e.getWeight() <= weightLimit)
                .collect(Collectors.toList());

        if (inputPackage.getWeightLimit() == 0 || packages.isEmpty())
            return new InputPackage();

        var currentPackage = packages.remove(0);
        inputPackage.setPackages(packages);

        var inputPackageLeft = new InputPackage(weightLimit - currentPackage.getWeight(), packages);

        var p1 = process(inputPackage);
        var p2 = process(inputPackageLeft).add(currentPackage);

        //with the recursion method, we getting just these packages which are with big price
        //and with less weight and items
        return p1.getCost() < p2.getCost() ? p2 :
                p1.getCost() > p2.getCost() ? p1 :
                        p1.getWeightLimit() < p2.getWeightLimit() ? p1 :
                                p1.getWeightLimit() > p2.getWeightLimit() ? p2 :
                                        p1.getPackages().size() < p2.getPackages().size() ? p1 : p2;
    }

}
