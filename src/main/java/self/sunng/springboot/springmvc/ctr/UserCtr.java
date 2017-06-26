package self.sunng.springboot.springmvc.ctr;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import self.sunng.springboot.springmvc.common.Paging;
import self.sunng.springboot.springmvc.common.Response;
import self.sunng.springboot.springmvc.common.annotations.Authorization;
import self.sunng.springboot.springmvc.dto.UserQueryParams;
import self.sunng.springboot.springmvc.ent.User;
import self.sunng.springboot.springmvc.srv.UserSrv;

import javax.annotation.Resource;


/**
 * Created by sunxiaodong on 16/7/27.
 */
@Api(value = "请填写接口类名称")
@RestController
@Slf4j
public class UserCtr {

    @Resource
    private UserSrv userSrv;

    @ApiOperation(value = "请填写接口名称", response = Response.class, notes = "请填写接口说明")
    @ApiImplicitParams
        ({
            @ApiImplicitParam(name = "pageNum", value = "页数", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页记录数量"),
            @ApiImplicitParam(name = "orderByField", value = "排序字段"),
            @ApiImplicitParam(name = "orderByType", value = "排序类型"),
        })
    @Authorization
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Response getUsers(@RequestParam UserQueryParams queryParams) {
        Paging userPage = userSrv.batchQuery(queryParams);
        return new Response(userPage);
    }

    @ApiOperation(value = "根据ID查询单个用户", response = Response.class, notes = "根据ID查询单个用户")
    @ApiParam(name = "userID", value = "用户ID", required = true)
    @Authorization
    @RequestMapping(value = "/user/{userID}", method = RequestMethod.GET)
    public Response getUser(@PathVariable long userID) {
        User user = userSrv.query(userID);
        return new Response(user);
    }

    @ApiOperation(value = "创建用户", response = Response.class, notes = "创建用户", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Authorization
    @RequestMapping(value = "/user", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response createUser(@RequestBody User user) {
        user = userSrv.create(user);
        return new Response(user);
    }

    // @ApiOperation(value = "批量创建用户", response = Response.class, notes = "批量创建用户", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // @Authorization
    // @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // public Response createUsers(@RequestBody List<User> users) {
    //     int createCount = userSrv.batchCreate(users);
    //     return new Response(user);
    // }

    @ApiOperation(value = "更新用户信息", response = Response.class, notes = "更新用户信息", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Authorization
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response updateUser(@RequestBody User user) {
        user = userSrv.updateById(user);
        return new Response(user);
    }

    // @ApiOperation(value = "删除用户", response = Response.class, notes = "删除用户", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // @Authorization
    // @RequestMapping(value = "/user/{userID}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // public Response delUser(@PathVariable long userID) {
    //     if (userSrv.delete(userID)) {
    //         return Response.OK;
    //     } else {
    //         return Response.DELETE_FAIL;
    //     }
    //
    // }
    //
    // @ApiOperation(value = "删除用户", response = Response.class, notes = "删除用户", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // @Authorization
    // @RequestMapping(value = "/user/{userIDs}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    // public Response delUsers(@PathVariable long userIDs) {
    //     if (userSrv.delete(userIDs)) {
    //         return Response.OK;
    //     } else {
    //         return Response.DELETE_FAIL;
    //     }
    //
    // }

    @RequestMapping(value = "/test")
    public Response test() {
        // return Response.OK;
        return Response.DELETE_FAIL;
    }
}
