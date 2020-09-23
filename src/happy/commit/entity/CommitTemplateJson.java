package happy.commit.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @desc    json文件解析对象 CommitTemplateJson.java
 * @author  yijie
 * @date    2020-09-22 21:52
 * @logs[0] 2020-09-22 21:52 yijie 创建了CommitTemplateJson.java文件
 */
public class CommitTemplateJson {
    @SerializedName("ci-template")
    private String ciTemplate;
    private List<Type> types;
    private List<Scope> scopes;

    @Override
    public String toString() {
        return "CommitTemplateJson{" +
                "types=" + types +
                ", scopes=" + scopes +
                '}';
    }

    public String getCiTemplate() {
        return ciTemplate;
    }
    public List<Type> getTypes() {
        return types;
    }

    public List<Scope> getScopes() {
        return scopes;
    }
}
