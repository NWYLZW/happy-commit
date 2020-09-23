package happy.git.commit.entity;
/**
 * @desc    下拉选择框 SelItem.java
 * @author  yijie
 * @date    2020-09-23 10:17
 * @logs[0] 2020-09-23 10:17 yijie 创建了SelItem.java文件
 */
public class SelItem {
    // 名字
    private String name;
    // 标题
    private String title;
    // 解释
    private String desc;

    @Override
    public String toString() {
        if (this.desc!=null) {
            return String.format("%s [%s]", this.name, this.desc);
        } else {
            return String.format("%s", this.name);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
