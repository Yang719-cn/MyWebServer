package cn.yang719.SpringWeb.service;

import cn.yang719.SpringWeb.dto.Result;
import cn.yang719.SpringWeb.entity.Admin;

/**
 * @author Yang719 <br/>
 * @version code in Java17 <br/>
 * 处理跟管理员账号的相关服务
 */
public interface AdminService {

    /**
     * 添加新管理员
     * @param admin 所需添加新管理员的信息
     * @return 统一业务层返回结果对象，返回是否添加成功
     */
    Result add(Admin admin);

    /**
     * 现有管理员登录
     * @param admin 要登录的现有管理员的信息
     * @return 统一业务层返回结果对像，返回是否登录成功
     */
    Result login(Admin admin);

    /**
     * 删除现有管理员
     * @param admin 所要删除现有管理员的信息
     * @return 统一业务层返回结果对像，返回是否删除成功
     */
    Result remove(Admin admin);

    /**
     * 更新现有管理员的信息
     * @param admin 所需更新现有管理员的信息，注意->根据admin.id进行更改，
     * 输入的admin中断admin.id为要更改数据的唯一标识符，admin.id并不是要改变的对象
     * @return 统一业务层返回结果对像，返回是否更新成功
     */
    Result update(Admin admin);


}
