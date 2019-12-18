package com.company;

import com.company.Tool.HTTPTools;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        HTTPTools.post("https://cpefiresimulation.azurewebsites.net/send","4.84671,45,9");
    }
}
