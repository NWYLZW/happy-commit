package happy.git.commit.entity;
/**
 * @desc    命名域 Scope.java
 * @author  yijie
 * @date    2020-09-22 21:55
 * @logs[0] 2020-09-22 21:55 yijie 创建了Scope.java文件
 */
public class Scope {
    // 名字
    private String name;
    // 解释
    private String desc;

    @Override
    public String toString() {
        return "Scope{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
