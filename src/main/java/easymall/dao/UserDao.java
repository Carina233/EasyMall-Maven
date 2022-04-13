package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.User;

@Mapper
@Repository
public interface UserDao {

	public User login(String username);

	public int regist(User user);
}
