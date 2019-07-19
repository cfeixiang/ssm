package com.qf.service;

import com.qf.AcTest;
import com.qf.pojo.Item;
import com.qf.pojo.PageInfo;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Administrator 2019/7/16 0016 17:54
 */
public class ItemServiceTest extends AcTest {
    @Autowired
    private ItemService itemService;
    @Test
    public void findItremByNameAndLimit() {
        PageInfo<Item> itemPageInfo = itemService.findItremByNameAndLimit(null, 1, 5);
        System.out.println(itemPageInfo);
        for (Item item : itemPageInfo.getList()) {
            System.out.println(item);
        }
    }
    @Test
    @Transactional
    public void save(){

    }
}