package com.qf.service;

import com.qf.pojo.Item;
import com.qf.pojo.PageInfo;

/**
 * Administrator 2019/7/16 0016 11:36
 */
public interface ItemService {


    PageInfo<Item> findItremByNameAndLimit(String name, Integer page, Integer size);

    Integer save(Item item);

    Integer del(Long id);

    Integer update(Item item);
}
