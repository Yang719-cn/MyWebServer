package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Pluginsuggest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PluginsuggestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pluginsuggest record);

    int insertSelective(Pluginsuggest record);

    Pluginsuggest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pluginsuggest record);

    int updateByPrimaryKey(Pluginsuggest record);
}