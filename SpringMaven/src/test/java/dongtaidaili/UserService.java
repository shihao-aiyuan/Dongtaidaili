package dongtaidaili;

/**
 * @author shihao
 * @create 2020-09-02 10:55
 */
public class UserService implements IUserService {
    @Override
    public void insert(String name) {
        System.out.println(String.format("用户[name:%s]插入成功",name));
    }
}
