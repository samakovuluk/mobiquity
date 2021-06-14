package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.helper.LineHelper;
import com.mobiquity.model.Package;
import com.mobiquity.service.PackageServiceImpl;
import com.mobiquity.service.PackerService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Packer {

    private static final PackageServiceImpl packageService = new PackerService();

    public static String pack(String filePath) throws APIException {
        var stringBuilder = new StringBuilder();
        try {
            var inputStream = new FileInputStream(filePath);
            var streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            var bufferedReader = new BufferedReader(streamReader);
            while (bufferedReader.ready()) {
                var inputPackage = LineHelper.processLine(bufferedReader.readLine());
                if (stringBuilder.length() != 0)
                    stringBuilder.append("\n");
                var res = packageService.process(inputPackage)
                        .getPackages()
                        .stream()
                        .sorted(Comparator.comparing(Package::getIndex))
                        .map(e -> e.getIndex().toString())
                        .collect(Collectors.joining(", ")
                        );
                stringBuilder.append(res.isEmpty() ? "-" : res);
            }
        } catch (IOException e) {
            throw new APIException("File is not found!");
        }
        return stringBuilder.toString();
    }
}
