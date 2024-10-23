package com.postech.lending.creditanalysis.service.calculator;

import java.util.Random;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneratedBarCode {

    private GeneratedBarCode(){

    }

    private static final String PREFIX_EAN_BRASIL = "789";
    private static final Random random = new Random();

    public static String generateEAN13BarCode() {
        StringBuilder barcode = new StringBuilder(PREFIX_EAN_BRASIL);

        for (int i = 0; i < 6; i++) {
            int digit = random.nextInt(10);
            barcode.append(digit);
        }

        return barcode.toString();
    }

    public static String generateBarCodeBlocks() {
        String b1 = generateEAN13BarCode();
        String b2 = generateEAN13BarCode();
        String b3 = generateEAN13BarCode();
        String b4 = generateEAN13BarCode();
        return b1 + ".0" + b2 + ".1" + b3 + ".00" + b4;
    }

}
