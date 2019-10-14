package com.lcq.my;

import com.lcq.my.bean.UserInfo;
import com.lcq.my.mapper.UserInfoMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyApplicationTests {

	@Autowired
	UserInfoMapper userInfoMapper;


	@Autowired
	DataSource dataSource;



	@Test
	public void TestConn() throws Exception{
		String name = dataSource.getConnection().getClass().getName();
		System.out.println("连接的数据源为："+ name);
	}

	@Test
	public void contextLoads() {
		UserInfo userInfo = new UserInfo();
		for (int i = 0; i < 5; i++) {
			userInfo.setAge((int) (Math.random() * 29));
			userInfo.setName("张三");
			userInfo.setJob("云计算");
			userInfoMapper.insert(userInfo);
			System.out.println("插入成功");
		}
	}

	@Test
	public void testUpdate() {
		UserInfo userInfo = new UserInfo();
		userInfo.setJob("区块链");
		userInfo.setAge(26);
		userInfo.setName("霸王花");
		userInfo.setId(4);
		int i = userInfoMapper.updateByPrimaryKey(userInfo);
		System.out.println("更新成功");
	}

	@Test
	public void TestDelete() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(7);
		userInfoMapper.deleteByPrimaryKey(userInfo.getId());
		System.out.println("删除成功");
	}

	@Test
	public void querySelect() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(7);
		List<UserInfo> userInfos = userInfoMapper.selectAll();
		System.out.println(userInfos);
		userInfo.setName("张三");
		Example example = new Example(UserInfo.class);
		final Example.Criteria criteria = example.createCriteria().andEqualTo("name", userInfo.getName());
		List<UserInfo> userInfoList = userInfoMapper.selectByExample(example);
		System.out.println("名字为张三的人信息如下所示：=======>"+userInfoList);
	}

}
