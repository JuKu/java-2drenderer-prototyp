package com.jukusoft.renderer2d.prototyp.engine.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Utils for file operations
 *
 * Created by Justin on 24.08.2016.
 */
public class FileUtils {

    /**
    * read content from file
     *
     * @param path path to file
     * @param encoding file encoding
     *
     * @throws IOException
     *
     * @return content of file as string
    */
    public static String readFile (String path, Charset encoding) throws IOException {
        //read bytes from file
        byte[] encoded = Files.readAllBytes(Paths.get(path));

        //convert bytes to string with specific encoding and return string
        return new String(encoded, encoding);
    }

    /**
    * read lines from file
     *
     * @param path path to file
     * @param charset encoding of file
     *
     * @throws IOException
     *
     * @return list of lines from file
    */
    public static List<String> readLines (String path, Charset charset) throws IOException {
        return Files.readAllLines(Paths.get(path), charset);
    }

    /**
    * write text to file
     *
     * @param path path to file
     * @param content content of file
     * @param encoding file encoding
     *
     * @throws IOException
    */
    public static void writeFile (String path, String content, Charset encoding) throws IOException {
        Files.write(Paths.get(path), content.getBytes(encoding), StandardOpenOption.CREATE);
    }

}
