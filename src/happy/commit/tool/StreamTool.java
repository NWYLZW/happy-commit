package happy.commit.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @desc    文件流帮助 StreamTool.java
 * @author  yijie
 * @date    2020-09-23 16:40
 * @logs[0] 2020-09-23 16:40 yijie 创建了StreamTool.java文件
 */
public class StreamTool {
    static String inputStream2String(InputStream is) throws IOException {
        BufferedReader bReader = new BufferedReader(
                new InputStreamReader(
                        is,"UTF-8"
                )
        );
        StringBuilder sb = new StringBuilder();
        String s = "";
        String os = System.getProperty("os.name");
        while ((s = bReader.readLine()) != null) {
            sb.append(s);
            if(os.toLowerCase().startsWith("win")){
                sb.append("\r\n");
            } else if (os.toLowerCase().startsWith("linux")) {
                sb.append("\r");
            } else if (os.toLowerCase().startsWith("mac")) {
                sb.append("\n");
            }
        }
        bReader.close();
        return sb.toString();
    }
}
