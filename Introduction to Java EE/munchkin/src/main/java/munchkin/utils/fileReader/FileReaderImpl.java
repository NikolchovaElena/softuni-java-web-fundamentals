package munchkin.utils.fileReader;

import java.io.*;


public class FileReaderImpl implements FileReader {

    @Override
    public String getFileContent(String filePath) throws IOException {
        BufferedReader buff = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(filePath))));

        StringBuilder fileContent = new StringBuilder();
        String currentLine;
        while ((currentLine = buff.readLine()) != null) {
            fileContent.append(currentLine).append(System.lineSeparator());
        }
        return fileContent.toString().trim();
    }
}
