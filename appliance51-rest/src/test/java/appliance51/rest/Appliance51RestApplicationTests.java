package appliance51.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Appliance51RestApplicationTests {

	@Resource(name = "authRedis")
	private StringRedisTemplate authRedisTemplate;
	@Test
	public void contextLoads() {
	}

	@Test
	public void Redis测试()
	{
		BoundHashOperations<String, String, String> hashOps = authRedisTemplate.boundHashOps("HTest:My_");
		for(int i=1;i<100;i++) {
			hashOps.put("name"+i, "yuananyun");
			hashOps.put("age"+i, "28");
		}
		authRedisTemplate.boundValueOps("HTest:valu").set("liushefwe", 60 * 1000, TimeUnit.SECONDS);
		authRedisTemplate.boundSetOps("HTest:mySet").add("1", "2", "3");
	}
}
