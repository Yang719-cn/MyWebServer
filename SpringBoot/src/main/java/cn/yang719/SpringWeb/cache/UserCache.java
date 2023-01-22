package cn.yang719.SpringWeb.cache;

import cn.yang719.SpringWeb.entity.User;
import org.springframework.cache.annotation.Cacheable;

public class UserCache {

    @Cacheable()
    public String getEmailCode(User user){
        return null;
    }

}
