package happy.git.commit.views.commitTemplate;

import com.intellij.openapi.project.Project;
import happy.git.commit.entity.CommitTemplateJson;
import happy.git.commit.entity.Scope;
import happy.git.commit.entity.Type;
import happy.git.commit.tool.FileTool;

import javax.swing.*;

/**
 * @desc    提交模板面板 CommitTemplate.java
 * @author  yijie
 * @date    2020-09-22 20:08
 * @logs[0] 2020-09-22 20:08 yijie 创建了CommitTemplate.java文件
 */
public class CommitTemplate {
    private JPanel mainPanel;
    private JComboBox changeType;
    private JComboBox changeScope;
    private JTextField shortDescription;
    private JTextArea longDescription;

    CommitTemplateJson commitTemplateJson = FileTool.getCommitTemplateJson();

    public CommitTemplate(Project project) {
        FileTool.setP(project);
        for (Type type : commitTemplateJson.getTypes()) {
            changeType.addItem(type);
        }
        for (Scope scope : commitTemplateJson.getScopes()) {
            changeScope.addItem(scope);
        }
    }

    /**
     * 获取提交信息
     */
    public String getCommitMessage () {
        String.format(
                commitTemplateJson.getCiTemplate(),
                changeScope.getInputContext()
        );
        return "测试";
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
