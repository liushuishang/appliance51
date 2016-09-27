package appliance51.rest;

import appliance51.security.service.MobileCodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by yuananyun on 2016/9/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MobileCodeTest {

    @Autowired
    private MobileCodeService mobileCodeService;

    @Test
    public void 测试发送手机动态验证码() {
        Assert.assertTrue(mobileCodeService.sendMobileCode("15811866424", "123456"));
    }

}
