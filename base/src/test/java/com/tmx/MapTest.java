package com.tmx;

import com.tmx.list.arrayList.RivenArrayLsit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created By Riven on 2020-8-27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = MapTest.class)
public class MapTest {

    @Test
    public void test() {
//        Map<String, String> map = new HashMap<>();
//        map.put("string", "测试");
//        int i = 16 >> 4;
//        System.out.println("1 << 4 等于" + Integer.toString(i));

        RivenArrayLsit rivenArrayLsit = new RivenArrayLsit();
        rivenArrayLsit.add("String");
        rivenArrayLsit.add("1111111");
        for (int i = 0; i < rivenArrayLsit.getSize(); i++) {
            System.out.println(rivenArrayLsit.get(i));
        }
        rivenArrayLsit.set(1,"new String");
        for (int i = 0; i < rivenArrayLsit.getSize(); i++) {
            System.out.println("新生成的数据" + rivenArrayLsit.get(i));
        }
    }
}
