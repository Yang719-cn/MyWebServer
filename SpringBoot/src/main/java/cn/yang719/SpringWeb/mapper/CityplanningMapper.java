package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Cityplanning;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityplanningMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cityplanning record);

    int insertSelective(Cityplanning record);

    Cityplanning selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cityplanning record);

    int updateByPrimaryKey(Cityplanning record);
}