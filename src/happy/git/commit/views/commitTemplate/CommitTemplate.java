package happy.git.commit.views.commitTemplate;

import com.google.gson.Gson;
import com.intellij.openapi.project.Project;
import happy.git.commit.entity.CommitTemplateJson;
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
    private JTextField closedIssues;
    private JTextArea breakingChanges;
    enum DefaultChange {
        FEAT("Features", "A new feature"),
        FIX("Bug Fixes", "A bug fix"),
        DOCS("Documentation", "Documentation only changes"),
        STYLE("Styles", "Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)"),
        REFACTOR("Code Refactoring", "A code change that neither fixes a bug nor adds a feature"),
        PERF("Performance Improvements", "A code change that improves performance"),
        TEST("Tests", "Adding missing tests or correcting existing tests"),
        BUILD("Builds", "Changes that affect the build system or external dependencies (example scopes: gulp, broccoli, npm)"),
        CI("Continuous Integrations", "Changes to our CI configuration files and scripts (example scopes: Travis, Circle, BrowserStack, SauceLabs)"),
        CHORE("Chores", "Other changes that don't modify src or test files"),
        REVERT("Reverts", "Reverts a previous commit");
        public final String title;
        public final String description;
        DefaultChange(String title, String description) {
            this.title = title;
            this.description = description;
        }
        public String label() {
            return this.name().toLowerCase();
        }
        @Override
        public String toString() {
            return String.format("%s - %s", this.label(), this.description);
        }
    }

    public CommitTemplate(Project project) {
        for (DefaultChange type : DefaultChange.values()) {
            changeType.addItem(type);
        }
        FileTool.setP(project);
        CommitTemplateJson CommitTemplateJson = new Gson().fromJson(FileTool.getCommitTemplateFileContent(), CommitTemplateJson.class);
        System.out.println(CommitTemplateJson.toString());
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
