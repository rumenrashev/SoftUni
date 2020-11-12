package com.softuni.springadvancedquering.lab.shampoocompany.io.impl;

import com.softuni.springadvancedquering.lab.shampoocompany.io.InputReader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleReader implements InputReader {

    private BufferedReader reader;

    public ConsoleReader() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        try {
         return this.reader.readLine();
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
