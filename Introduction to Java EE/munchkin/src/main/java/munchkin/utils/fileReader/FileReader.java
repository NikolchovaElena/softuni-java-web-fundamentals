package munchkin.utils.fileReader;

import java.io.IOException;

public interface FileReader {
    String getFileContent(String filePath) throws IOException;
}
