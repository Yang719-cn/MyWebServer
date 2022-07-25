package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Mcplayer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface McplayerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Mcplayer record);

    int insertSelective(Mcplayer record);

    Mcplayer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mcplayer record);

    int updateByPrimaryKey(Mcplayer record);
}