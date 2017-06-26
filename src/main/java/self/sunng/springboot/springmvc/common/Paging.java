package self.sunng.springboot.springmvc.common;

import lombok.Data;

import java.util.List;

/**
 * Created by sunxiaodong on 2016/12/27.
 */
@Data
public class Paging {
    private int pageIndex;
    private int pageSize;
    private int totalCount;
    private List data;

    public Paging(int pageIndex, int pageSize, int totalCount, List data) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.data = data;
    }
}
