package self.sunng.springboot.springmvc.dao;

import org.springframework.stereotype.Repository;
import self.sunng.springboot.springmvc.common.BaseDao;
import self.sunng.springboot.springmvc.common.Paging;
import self.sunng.springboot.springmvc.dto.UserQueryParams;
import self.sunng.springboot.springmvc.ent.User;

import java.util.List;

/**
 * Created by sunxiaodong on 16/7/27.
 */
@Repository
public class UserDao extends BaseDao {
    public void insert(User user) {
        sqlSessionWriter.insert("User.insert", user);
    }

    public void updateById(User user) {
        sqlSessionWriter.update("User.updateById", user);
    }

    public User selectById(Long id) {
        return sqlSessionReader.selectOne("User.selectById", id);
    }

    public Paging batchQuery(UserQueryParams userQueryParams) {
        List<User> users = sqlSessionReader.selectList("User.batchQuery", userQueryParams);
        int totalCount = sqlSessionReader.selectOne("User.queryCount", userQueryParams);
        return new Paging(userQueryParams.getPageIndex(), userQueryParams.getPageSize(), totalCount, users);
    }
}
