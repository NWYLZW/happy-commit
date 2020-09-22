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
        setTitle("Commit");
        setOKButtonText("OK");
        init();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return panel.getMainPanel();
    }
}
