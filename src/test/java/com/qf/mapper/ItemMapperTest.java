package com.qf.mapper;

import com.qf.AcTest;
import com.qf.pojo.Item;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Administrator 2019/7/16 0016 17:42
 */
public class ItemMapperTest extends AcTest {
    @Autowired
    private ItemMapper itemMapper;
    @Test
    public void findCountByname() {
        Long count = itemMapper.findCountByname(null);
        System.out.println(count);
    }

    @Test
    public void findItemByNameLikeAndLimit() {
        List<Item> itemList = itemMapper.findItemByNameLikeAndLimit(null, 1, 5);
        for (Item item : itemList) {
            System.out.println(item);
        }
    }
    @Test
    @Transactional
    public void save(){
        Item item=new Item();
    }
    @Test
    @Transactional
    public void del(){
        Integer count = itemMapper.delById(8L);
        Assert.assertEquals(new Integer(1),count);
    }
    @Test
    @Transactional
    public void update(){
        Item item=new Item();
        item.setId(13);
        item.setName("cwy");
        item.setDescription("666");
        item.setPic("hhh");
        item.setProductionDate(new Date());
        item.setPrice(new BigDecimal(888));
        Integer count = itemMapper.update(item);
        Assert.assertEquals(new Integer(1),count);
    }
}