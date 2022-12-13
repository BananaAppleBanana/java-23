package com.example.java23;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class FizzBuzzTest {
    @Test
    public void testSingleValueInput() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        assertTrue(fizzBuzz.helper(5).equals("buzz"));
        assertTrue(fizzBuzz.helper(3).equals("fizz"));
        assertTrue(fizzBuzz.helper(1).equals("1"));
        assertTrue(fizzBuzz.helper(1).equals("1"));
        assertTrue(fizzBuzz.helper(15).equals("fizzbuzz"));
        assertTrue(fizzBuzz.helper(15).equals("fizzbuzz"));
        assertTrue(fizzBuzz.helper(15).equals("fizzbuzz"));
        assertTrue(fizzBuzz.helper(15).equals("fizzbuzz"));
        assertThrows(IllegalArgumentException.class, () -> fizzBuzz.helper(-1));
    }

    @Test
    public void testListInput() {
        FizzBuzz fizzBuzz = new FizzBuzz();
        List<String> expected = Arrays.asList("1", "2", "fizz", "4", "buzz");
        assertIterableEquals(expected, fizzBuzz.helper(1, 5));
    }
}

/**
 *  fizz buzz
 *  requirement
 *      1. input number ? range ? list of number ?
 *      2. range ? min + max :
 *          if(min > max) -> throw exceptions
 *          else {
 *              print fizz buzz for every number
 *          }
 *      3. input type : string / integer / long
 *      4. 0 ? negative ?
 *
 *      min + max / integer / throw exceptions when input is not valid
 *      need to print fizz buzz
 *  solution
 *      1. public String helper(int val) {}
 *      2. public List<String> helper(int min, int max) {}
 *      3. constructor preload fizz buzz mapping
 *      4. load number + fizz buzz to map during run time
 *
 */
class FizzBuzz {
    private final Map<Integer, String> cache = new HashMap<>();

    public void solution(int min, int max) {
        System.out.println(helper(min, max));
    }

    public List<String> helper(int min, int max) {
        if(min > max || min <= 0) {
            throw new IllegalArgumentException("..");
        }
        List<String> ans = new ArrayList<>();
        for(; min <= max; min++) {
            ans.add(helper(min));
        }
        return ans;
    }

    public String helper(int val) {
        if(val <= 0) {
            throw new IllegalArgumentException("input not valid");
        }
        if(cache.containsKey(val)) {
            return cache.get(val);
        }
        String res = "";
        if(val % 3 == 0 && val % 5 == 0) {
            res = "fizzbuzz";
        } else if(val % 3 == 0) {
            res = "fizz";
        } else if(val % 5 == 0) {
            res = "buzz";
        } else {
            res = String.valueOf(val);
        }
        cache.put(val, res);
        return res;
    }
}