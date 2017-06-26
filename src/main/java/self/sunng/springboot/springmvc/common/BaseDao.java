package self.sunng.springboot.springmvc.common;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;


public class BaseDao {

    public static final int DEFAULT_PAGE_SIZE = 15;

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionReader;

    @Resource(name = "sqlSessionTemplate")
    protected SqlSessionTemplate sqlSessionWriter;
}
