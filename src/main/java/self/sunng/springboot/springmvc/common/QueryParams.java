package self.sunng.springboot.springmvc.common;

import lombok.Data;
import self.sunng.springboot.springmvc.common.enums.OrderByTypeEnum;

/**
 * Created by sunxiaodong on 2016/12/27.
 */
@Data
public abstract class QueryParams {
    // *** start from 0 ***
    private int pageIndex;
    private int pageSize;
    private int startIndex;
    private String orderByField;
    private OrderByTypeEnum orderByType;

    private int setStartIndex() {
        return pageIndex * pageSize;
    }

    public abstract boolean valid() throws IllegalArgumentException;
}
