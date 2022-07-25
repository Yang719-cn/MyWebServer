package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Useruploadpic;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UseruploadpicMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Useruploadpic record);

    int insertSelective(Useruploadpic record);

    Useruploadpic selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Useruploadpic record);

    int updateByPrimaryKey(Useruploadpic record);

    List<Useruploadpic> selectWithPicInfoByUserId(Integer id);
}