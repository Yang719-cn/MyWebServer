package cn.yang719.SpringWeb.service;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.User;

/**
 * @author Yang719 <br/>
 * @version code in Java17 <br/>
 * 处理跟用户账号的相关服务
 */

public interface UserService {

    /**
     * 添加新用户
     * @param user 所需添加新用户的信息
     * @return 统一业务层返回结果对象，返回是否添加成功
     */
    Result add(User user);

    /**
     * 现有用户登录
     * @param user 要登录的现有用户的信息
     * @return 统一业务层返回结果对像，返回是否登录成功
     */
    Result login(User user);

    /**
     * 删除现有用户
     * @param user 所要删除现有用户的信息
     * @return 统一业务层返回结果对像，返回是否删除成功
     */
    Result remove(User user);

    /**
     * 禁用现有用户
     * @param user 所要禁用的现有用户的信息
     * @return 统一业务层返回结果对像，返回是否删除成功
     */
    Result disable(User user);

    /**
     * 更新现有用户的信息
     * @param user 所需更新现有用户的信息，注意->根据user.id进行更改，
     * 输入的user中断user.id为要更改数据的唯一标识符，user.id并不是要改变的对象
     * @return 统一业务层返回结果对像，返回是否更新成功
     */
    Result update(User user);

    /**
     * 给管理员用来管理搜寻现有用户的
     * @param id 要搜寻用户的信息
     * @return 统一业务层返回结果对像，返回是否查询成功,成功的话包含了用户数据列表
     */
    Result searchById(Integer id);



}
