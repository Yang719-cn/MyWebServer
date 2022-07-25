package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Regemail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegemailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Regemail record);

    int insertSelective(Regemail record);

    Regemail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Regemail record);

    int updateByPrimaryKey(Regemail record);

    Regemail selectLatestByEmail(Regemail regemail);
}