package happy.commit.tool;

import com.google.gson.Gson;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VfsUtil;
import happy.commit.entity.CommitTemplateJson;

import java.io.*;

/**
 * @desc    文件帮助类 FileTool.java
 * @author  yijie
 * @date    2020-09-22 20:59
 * @logs[0] 2020-09-22 20:59 yijie 创建了FileTool.java文件
 */
public class FileTool {
    private static Project project;
    public static void setProject(Project project) {
        FileTool.project = project;
    }

    /**
     * 读取模板文件中的字符内容
     * @param fileName 模板文件名
     */
    private static String readTemplateFile(String fileName) {
        String content = "";
        try {
            InputStream in = FileTool.class.getClassLoader()
                    .getResource(fileName).openStream();
            content = StreamTool.inputStream2String(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    /**
     * 生成文件
     * @param content   中的内容
     * @param path      文件路径
     * @param fileName  文件名称
     */
    private static void writeToFile(String content, String path, String fileName) {
        try {
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(path + '/' + fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(file.getAbsoluteFile(),true
                            ),"UTF-8"
                    )
            );
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化一个默认的提交模板文件
     */
    public static boolean initCommitTemplateJson () {
        String fileContent = readTemplateFile("template/.commit-template.json");
        writeToFile(
                fileContent,
                VfsUtil.virtualToIoFile( project.getBaseDir() ).getPath(),
                ".commit-template.json"
        );
        return true;
    }

    /**
     * 判断是否有提交模板文件
     */
    public static boolean haveCommitTemplateFile () {
        return commitTemplateFile() != null;
    }

    /**
     * 获取提交模板文件对象
     */
    private static File commitTemplateFile () {
        File[] commitTemplates =
                VfsUtil.virtualToIoFile( project.getBaseDir() )
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
            return StreamTool.inputStream2String(
                    new FileInputStream(commitTemplateFile)
            ).replace(" ", "");
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
