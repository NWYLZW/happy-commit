package happy.git.commit.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import happy.git.commit.views.commitTemplate.CommitTemplate;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class CommitTemplateDialog extends DialogWrapper {

    private final CommitTemplate panel;

    public CommitTemplateDialog(@Nullable Project project) {
        super(project);
        panel = new CommitTemplate(project);
        setTitle("Commit Template Sel");
        setOKButtonText("OK");
        init();
    }
    public String message () {
        return panel.getCommitMessage();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }
}
