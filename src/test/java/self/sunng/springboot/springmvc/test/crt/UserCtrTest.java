package self.sunng.springboot.springmvc.test.crt;

import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import self.sunng.springboot.springmvc.common.Response;
import self.sunng.springboot.springmvc.common.util.JsonUtil;
import self.sunng.springboot.springmvc.ent.User;
import self.sunng.springboot.springmvc.srv.UserSrv;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sunxiaodong on 2016/12/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class UserCtrTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserSrv userSrv;

    @Test
    public void doGetTest() throws Exception {
        // List<User> userList = new ArrayList<>();
        // User user = new User();
        // user.setId(1);
        // user.setName("sunng");
        // user.setCreateTime(DateTime.now().toDate());
        // userList.add(user);
        // given(userSrv.batchQuery(any())).willReturn(userList);
        //
        // this.mvc.perform(get("/user").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).param("name", "sunng"))
        //     .andExpect(status().isOk())
        //     .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
        //     .andExpect(content().json(JsonUtil.obj2Json(new Response(userList))));

    }

    @Test
    public void doPostTest() throws Exception {

        User user = new User();
        user.setId(1);
        user.setName("sunng");
        user.setCreateTime(DateTime.now().toDate());
        given(userSrv.create(any())).willReturn(user);

        this.mvc.perform(post("/user").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(JsonUtil.obj2Json(user)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(JsonUtil.obj2Json(new Response(user))));

    }

    @Test
    public void doPutTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("sunng");
        user.setCreateTime(DateTime.now().toDate());
        given(userSrv.updateById(any())).willReturn(user);

        this.mvc.perform(put("/user").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(JsonUtil.obj2Json(user)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(JsonUtil.obj2Json(new Response(user))));
    }

    @Test
    public void doDeleteTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("sunng");
        user.setCreateTime(DateTime.now().toDate());
        // given(userSrv.deleteById(any()))

        this.mvc.perform(delete("/user").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(JsonUtil.obj2Json(user)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().json(JsonUtil.obj2Json(Response.OK)));
    }

}
