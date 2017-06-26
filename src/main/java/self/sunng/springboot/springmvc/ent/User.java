package self.sunng.springboot.springmvc.ent;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by sunxiaodong on 16/7/27.
 */
@Data
@ApiModel(value = "用户信息", description="用户信息")
public class User {

    @ApiModelProperty(value = "用户ID")
    private long id;

    @ApiModelProperty(value = "用户名", required = true)
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
