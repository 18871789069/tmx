package com.tmx;

import com.tmx.list.linkedList.LinkedList;
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
        Map<String, String> map = new HashMap<>();
//        map.put("string", "测试");
//        int nei = 16 >> 4;
//        System.out.println("1 << 4 等于" + Integer.toString(nei));
//
//        RivenArrayLsit rivenArrayLsit = new RivenArrayLsit();
//        rivenArrayLsit.add("String");
//        rivenArrayLsit.add("1111111");
//        for (int i = 0; i < rivenArrayLsit.getSize(); i++) {
//            System.out.println(rivenArrayLsit.get(i));
//        }
//        rivenArrayLsit.set(1,"new String");
//        rivenArrayLsit.remove(0);
//        for (int i = 0; i < rivenArrayLsit.getSize(); i++) {
//            System.out.println("新生成的数据" + rivenArrayLsit.get(i));
//        }
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("string");
        linkedList.add("11111");
        linkedList.add("22222");
        linkedList.add("33333");
        linkedList.add("44444");
        linkedList.add(1, "abc");
        linkedList.add(3, "55555");
        for (int i = 0; i < linkedList.getSize(); i++) {
            System.out.println(linkedList.get(i));
        }
//        LinkedList
    }
}
