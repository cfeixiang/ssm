package com.qf.mapper;

import com.qf.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Administrator 2019/7/16 0016 11:37
 */
public interface ItemMapper {
    Long findCountByname(@Param("name")String name);

    List<Item> findItemByNameLikeAndLimit(@Param("name")String name,
                                          @Param("offset")Integer offset,
                                          @Param("size")Integer size);
    Integer save(Item item);

    Integer delById(@Param("id") Long id);

    Integer update(Item item);
}
