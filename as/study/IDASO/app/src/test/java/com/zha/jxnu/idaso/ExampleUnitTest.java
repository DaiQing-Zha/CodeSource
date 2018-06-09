package com.zha.jxnu.idaso;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test(){
        String content = "[]";
        Pattern p = Pattern.compile("[^\\d]+([\\d]+)[^\\d]+.*");
        Matcher m = p.matcher(content);
        boolean result = m.find();
        String find_result = null;
        if (result) {
            find_result = m.group(1);
        }
        System.out.println(find_result);
    }
}