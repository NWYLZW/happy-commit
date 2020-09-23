package happy.git.commit.tool;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;

import java.io.*;

/**
 * @desc    文件帮助类 FileTool.java
 * @author  yijie
 * @date    2020-09-22 20:59
 * @logs[0] 2020-09-22 20:59 yijie 创建了FileTool.java文件
 */
public class FileTool {
    private static Project p;
    public static Project getP() {
        return p;
    }
    public static void setP(Project p) {
        FileTool.p = p;
    }

    public static boolean haveCommitTemplateFile () {
        return commitTemplateFile() == null;
    }
    public static File commitTemplateFile () {
        File[] commitTemplates =
                VfsUtil.virtualToIoFile( p.getBaseDir() )
                        .listFiles( (dir, name) -> name.equals(".commit-template.json") );
        if ( commitTemplates.length == 1
                && commitTemplates[0].isFile()) {
            return commitTemplates[0];
        }
        return null;
    }
    public static String getCommitTemplateFileContent () {
        File commitTemplateFile = commitTemplateFile();
        if (commitTemplateFile == null) {
            return null;
        }

        try {
            BufferedReader bReader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(commitTemplateFile),"UTF-8"
                    )
            );
            StringBuilder sb = new StringBuilder();
            String s = "";
            while ((s = bReader.readLine()) != null) {
                sb.append(s);
            }
            bReader.close();
            return sb.toString().replace(" ", "");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
