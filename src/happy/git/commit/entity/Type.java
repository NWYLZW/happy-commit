package happy.git.commit.entity;
/**
 * @desc    提交类型 Type.java
 * @author  yijie
 * @date    2020-09-22 21:57
 * @logs[0] 2020-09-22 21:57 yijie 创建了Type.java文件
 */
public class Type {
    // 名字
    private String name;
    // 解释
    private String desc;

    @Override
    public String toString() {
        return "Type{" +
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
