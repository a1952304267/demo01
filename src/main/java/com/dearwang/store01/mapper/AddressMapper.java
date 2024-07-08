package com.dearwang.store01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.User;
import org.apache.ibatis.annotations.Mapper;

/*收货地址持久层接口*/
//@Mapper
public interface AddressMapper extends BaseMapper<User> {
    /**
     * 插入用户收货地址数据
     *
     * @param address 收货地址
     * @return 行数
     */
    Integer insert(Address address);

    /**
     * 根据uid获取地址总数
     *
     * @param uid 用户id
     * @return 收货地址的总数
     */
    Integer countByUid(Integer uid);
}
