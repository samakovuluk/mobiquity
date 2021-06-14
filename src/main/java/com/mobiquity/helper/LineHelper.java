package com.mobiquity.helper;

import com.mobiquity.model.InputPackage;
import com.mobiquity.model.Package;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LineHelper {

    public static InputPackage processLine(String line) {
        String[] lineParts = line.split(" : ");
        double weight = Double.parseDouble(lineParts[0]);
        List<Package> packages = parsePackages(lineParts[1]);
        return new InputPackage(weight, packages);
    }

    private static List<Package> parsePackages(String line) {
        String[] items = line.split("\\s+");
        return Arrays.stream(items)
                .map(LineHelper::parseItem)
                .collect(Collectors.toList());
    }

    private static Package parseItem(String item) {
        String[] itemParts = item.replace("(", "").replace(")", "").split(",");
        int index = Integer.parseInt(itemParts[0]);
        double weight = Double.parseDouble(itemParts[1]);
        double cost = Double.parseDouble(itemParts[2].replace("€", ""));
        return new Package(index, weight, cost);
    }

}
