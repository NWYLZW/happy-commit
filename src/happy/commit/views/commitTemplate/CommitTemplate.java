package happy.commit.views.commitTemplate;

import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import happy.commit.entity.CommitTemplateJson;
import happy.commit.entity.Scope;
import happy.commit.entity.Type;
import happy.commit.tool.FileTool;

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
    private JTextField description;
    private JTextArea longDescription;
    private JButton initButton;

    public static final NotificationGroup NOTIFICATION_GROUP =
            new NotificationGroup("Happy git commit notification group",
                    NotificationDisplayType.BALLOON, true);

    CommitTemplateJson commitTemplateJson;
    private final Project PROJECT;

    public CommitTemplate(Project project) {
        PROJECT = project;
        reloadData();
        initEvent();
    }

    /**
     * 重载数据
     */
    private void reloadData() {
        commitTemplateJson = FileTool.getCommitTemplateJson();
        if (commitTemplateJson != null) {
            changeType.removeAllItems();
            changeScope.removeAllItems();
            for (Type type : commitTemplateJson.getTypes()) {
                changeType.addItem(type);
            }
            for (Scope scope : commitTemplateJson.getScopes()) {
                changeScope.addItem(scope);
            }
        }
    }

    /**
     * 初始化事件
     */
    private void initEvent () {
        initButton.addActionListener(e -> {
            if (FileTool.haveCommitTemplateFile()) {
                // 返回值为1的时候 点击了取消按钮
                if (JOptionPane.showConfirmDialog(
                        null,
                        "项目下已有该文件是否删除重建", "请确认",
                        JOptionPane.YES_NO_OPTION)==1) {
                    return;
                }
            }
            String msg; MessageType type;
            if (FileTool.initCommitTemplateJson()) {
                msg = "提交模板文件初始化成功";
                type = MessageType.INFO;
            } else {
                msg = "提交模板文件初始化失败";
                type = MessageType.ERROR;
            }
            Notifications.Bus.notify(
                    NOTIFICATION_GROUP.createNotification(msg, type), PROJECT
            );
            reloadData();
        });
    }

    /**
     * 获取提交信息
     */
    public String getCommitMessage () {
        String
                ciTemplate = commitTemplateJson.getCiTemplate(),
                type = ((Type) changeType.getSelectedItem()).getName(),
                scope = ((Scope) changeScope.getSelectedItem()).getName(),
                desc = description.getText().trim(),
                longDesc = longDescription.getText().trim();
        return ciTemplate
                .replace("${type}", type)
                .replace("${scope}", scope)
                .replace("${desc}", desc)
                .replace("${long-desc}", longDesc);
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
