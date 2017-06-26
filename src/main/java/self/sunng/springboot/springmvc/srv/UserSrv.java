package self.sunng.springboot.springmvc.srv;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import self.sunng.springboot.springmvc.common.Paging;
import self.sunng.springboot.springmvc.dao.UserDao;
import self.sunng.springboot.springmvc.dto.UserQueryParams;
import self.sunng.springboot.springmvc.ent.User;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by sunxiaodong on 16/7/27.
 */
@Service
@Slf4j
public class UserSrv {

    @Resource
    private UserDao userDao;

    public User create(User user) {
        userDao.insert(user);
        return user;
    }

    public int batchCreate(List<User> user) {
        // TODO: 2016/12/27
        return 0;
    }

    public User updateById(User user) {
        userDao.updateById(user);
        return user;
    }

    public User query(long id) {
        return userDao.selectById(id);
    }

    public Paging batchQuery(UserQueryParams userQueryParams) {
        return userDao.batchQuery(userQueryParams);
    }
}
