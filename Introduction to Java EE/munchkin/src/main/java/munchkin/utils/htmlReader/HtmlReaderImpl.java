package munchkin.utils.htmlReader;

import munchkin.utils.htmlReader.HtmlReader;

import javax.annotation.ManagedBean;
import java.io.*;

@ManagedBean
public class HtmlReaderImpl implements HtmlReader {

    @Override
    public String readHtmlFile(String htmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFilePath))));

        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = reader.readLine()) != null) {

            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
