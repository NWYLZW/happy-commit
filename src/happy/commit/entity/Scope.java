package happy.commit.entity;
/**
 * @desc    命名域 Scope.java
 * @author  yijie
 * @date    2020-09-22 21:55
 * @logs[0] 2020-09-22 21:55 yijie 创建了Scope.java文件
 */
public class Scope extends SelItem{
    @Override
    public String toString() {
        return String.format("%s", getName());
    }
}
