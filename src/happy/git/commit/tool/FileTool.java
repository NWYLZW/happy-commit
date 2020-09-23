package happy.git.commit.tool;

import com.google.gson.Gson;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import happy.git.commit.entity.CommitTemplateJson;

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

    /**
     * 判断是否有提交模板文件
     */
    public static boolean haveCommitTemplateFile () {
        return commitTemplateFile() == null;
    }

    /**
     * 获取提交模板文件对象
     */
    private static File commitTemplateFile () {
        File[] commitTemplates =
                VfsUtil.virtualToIoFile( p.getBaseDir() )
                        .listFiles( (dir, name) -> name.equals(".commit-template.json") );
        if ( commitTemplates.length == 1
                && commitTemplates[0].isFile()) {
            return commitTemplates[0];
        }
        return null;
    }

    /**
     * 获取提交模板文件内容
     */
    private static String getCommitTemplateFileContent () {
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

    /**
     * 获取CommitTemplateJson实体对象
     */
    public static CommitTemplateJson getCommitTemplateJson () {
        String jsonStr = getCommitTemplateFileContent();
        if (jsonStr == null) return null;
        return new Gson().fromJson(jsonStr, CommitTemplateJson.class);
    }
}
