package happy.commit.action;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import happy.commit.dialog.CommitTemplateDialog;
import happy.commit.tool.FileTool;
import org.jetbrains.annotations.Nullable;

public class CreateCommitTemplateAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent actionEvent) {
        // action行为启动前初始化FileTool的project
        FileTool.setProject(actionEvent.getProject());
        final CommitMessageI commitPanel = getCommitPanel(actionEvent);
        if (commitPanel == null)
            return;
        CommitTemplateDialog dialog = new CommitTemplateDialog(actionEvent.getProject());
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            commitPanel.setCommitMessage(dialog.message());
        }
    }

    /**
     * 获取提交面板
     * @param e AnActionEvent 当前Action事件对象
     */
    private static CommitMessageI getCommitPanel(@Nullable AnActionEvent e) {
        if (e == null) {
            return null;
        }
        Refreshable data = Refreshable.PANEL_KEY.getData(e.getDataContext());
        if (data instanceof CommitMessageI) {
            return (CommitMessageI) data;
        }
        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(e.getDataContext());
    }
}
