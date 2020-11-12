package com.softuni.exercice.booksystem.utils;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String[] readFileContent(String filePath) {

        File file = new File(filePath);

        Set<String> result = new LinkedHashSet<>();

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null){
                if (line.trim().isEmpty()){
                    continue;
                }
                result.add(line);
            }
        }catch (IOException exception){
            System.err.println(exception.getMessage());
        }


        return result.toArray(String[]::new);
    }
}
