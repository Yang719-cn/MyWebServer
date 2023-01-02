package cn.yang719.SpringWeb.mapper;

import cn.yang719.SpringWeb.entity.Picture;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PictureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Picture record);

    int insertSelective(Picture record);

    Picture selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Picture record);

    int updateByPrimaryKey(Picture record);

    List<Picture> selectByUserId(Integer id);

    Picture selectByPicUrl(String url);

    List<Picture> selectPageByUserId(Integer id,Integer start,Integer size);
}