package com.qf.service.impl;

import com.qf.mapper.ItemMapper;
import com.qf.pojo.Item;
import com.qf.pojo.PageInfo;
import com.qf.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Administrator 2019/7/16 0016 11:37
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;
    @Override
    public PageInfo<Item> findItremByNameAndLimit(String name, Integer page, Integer size) {
        Long count = itemMapper.findCountByname(name);
        PageInfo pageInfo=new PageInfo(page,size,count);
        List<Item> list = itemMapper.findItemByNameLikeAndLimit(name, pageInfo.getOffset(), pageInfo.getSize());
        pageInfo.setList(list);
        return pageInfo;
    }

    @Override
    public Integer save(Item item) {
        return itemMapper.save(item);
    }

    @Override
    @Transactional
    public Integer del(Long id) {
        return itemMapper.delById(id);
    }

    @Override
    @Transactional
    public Integer update(Item item) {
        return itemMapper.update(item);
    }
}
