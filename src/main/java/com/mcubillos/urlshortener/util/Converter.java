package com.mcubillos.urlshortener.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.IntStream;

@Scope(value = "singleton")
@Component
public class Converter {
    private Character FIRST_LOWER_LETTER = 'a';
    private Character FIRST_UPPER_LETTER = 'A';
    private Character FIRST_NUMBER= '0';

    //Dictionary key
    private static Map<Character, Integer> charToIndexTable;
    //Conversion chart in the base10 to base62
    private static List<Character> indexToCharTable;

    private Converter() {
        charToIndexTable = new HashMap<>();
        indexToCharTable = new ArrayList<>();
        initTables();
    }

    private void initTables() {
        // 0->a, 1->b, ..., 25->z
        IntStream.rangeClosed(0,25).forEach(i ->{
            char c = (char)( FIRST_LOWER_LETTER + i);
            charToIndexTable.put(c, i);
            indexToCharTable.add(c);
        });

        // 26->A, 27->B, ..., 51->Z
        IntStream.rangeClosed(26,51).forEach(i ->{
            char c = (char)( FIRST_UPPER_LETTER + i - 26);
            charToIndexTable.put(c, i);
            indexToCharTable.add(c);
        });

        // 52->0, ... , 61->9
        IntStream.rangeClosed(52,61).forEach(i ->{
            char c = (char)( FIRST_NUMBER + i - 52);
            charToIndexTable.put(c, i);
            indexToCharTable.add(c);
        });
    }


    public static String createID(Long id){
        List<Integer> base62 = convertBase10ToBase62(id);
        StringBuilder uniqueURLID = new StringBuilder();
        for (int digit: base62) {
            uniqueURLID.append(indexToCharTable.get(digit));
        }
        return uniqueURLID.toString();
    }

    private static List<Integer> convertBase10ToBase62(Long id){
        LinkedList<Integer> digits = new LinkedList<>();
        if(id == 0){
            digits.addFirst(0);
        }else{
            getDigits(id, digits);
        }
        return digits;
    }

    private static void getDigits(Long id, LinkedList<Integer> digits){
        while(id > 0) {
            int remainder = (int)(id % 62);
            digits.addFirst(remainder);
            id /= 62;
        }
    }
    public static Long getDictionaryKeyFromUniqueID(String uniqueID) {
        List<Character> base62IDs = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62IDs.add(uniqueID.charAt(i));
        }
        return convertBase62ToBase10(base62IDs);
    }

    private static Long convertBase62ToBase10(List<Character> ids) {
        long id = 0L;
        for (int i = 0, exp = ids.size() - 1; i < ids.size(); ++i, --exp) {
            int base10 = charToIndexTable.get(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }
}
