package com.dearwang.store01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dearwang.store01.entity.Address;
import com.dearwang.store01.entity.User;
import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

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

    /**
     * 根据用户id查询用户的收货地址数据
     *
     * @param uid 用户id
     * @return 收货地址数据
     */
    List<Address> findByUid(Integer uid);

    /**
     * 根据aid查询收货地址数据
     *
     * @param aid 收货地址id
     * @return 收货地址数据
     */
    Address findByAid(Integer aid);

    /**
     * 根据aid删除默认收货
     *
     * @param uid 用户id
     * @return 行数
     */
    Integer updateNonDefault(Integer uid);

    /**
     * 更新默认地址
     *
     * @param aid          地址id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     * @return 行数
     */
    Integer updateDefault(Integer aid, String modifiedUser, Date modifiedTime);

    /**
     * 根据aid删除地址信息
     *
     * @param aid 地址id
     * @return 行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 根据用户id查询最新修改的地址信息
     *
     * @param uid 用户id
     * @return 地址信息
     */
    Address findNewModifiedTime(Integer uid);

    Integer updateAddress(Address address);


}
