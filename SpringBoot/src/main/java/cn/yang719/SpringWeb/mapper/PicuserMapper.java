package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Picuser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PicuserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picuser record);

    int insertSelective(Picuser record);

    Picuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picuser record);

    int updateByPrimaryKey(Picuser record);

    Picuser selectByUid(Integer uid);
}