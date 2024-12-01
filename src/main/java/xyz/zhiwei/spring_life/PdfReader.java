package xyz.zhiwei.spring_life;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.IOException;

/**
 * @author zhiweicoding.xyz
 * @date 14/11/2024
 * @email diaozhiwei2k@gmail.com
 */
public class PdfReader {
    public static void main(String[] args) {
        try {
            // 加载 PDF 文件
            File file = new File("/Users/zhiwei/Desktop/工作表 1.pdf");
            PDDocument document = PDDocument.load(file);

            // 创建 PDFTextStripper 实例
            PDFTextStripper pdfStripper = new PDFTextStripper();

            // 提取文本
            String text = pdfStripper.getText(document);

            // 输出提取的文本
            System.out.println(text);

            // 关闭文档
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
