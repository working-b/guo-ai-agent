package com.gs.guoaiagent.tools;

import cn.hutool.core.io.FileUtil;
import com.gs.guoaiagent.constant.FileConstant;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;

/**
 * @Author: hzt
 * @CreateTime: 2025-11-04  21:51
 * @Description: 文件操作工具类
 * @Version: 1.0
 */
public class FileOperationTool {

    private final String FILE_DIR = FileConstant.FILE_SAVE_DIR + "/file";

    @Tool(description = "Read content from the file")
    public String readFile(@ToolParam(description = "Name of a file to read") String fileName) {
        try {
            String filePath = FILE_DIR + "/" + fileName;
            return FileUtil.readUtf8String(filePath);
        }catch (Exception e) {
            return "read file error" + e.getMessage();
        }

    }

    @Tool(description = "Write content to the file")
    public String writeFile(@ToolParam(description = "Name of the file to write") String fileName,
                          @ToolParam(description = "Content to write to the file") String content
    ) {
        try {
            String filePath = FILE_DIR + "/" + fileName;
            FileUtil.mkdir(FILE_DIR);
            FileUtil.writeString(content, filePath, "utf-8");
            return "File written successfully to: " + filePath;
        }catch (Exception e) {
            return "write file error" + e.getMessage();
        }

    }
}
