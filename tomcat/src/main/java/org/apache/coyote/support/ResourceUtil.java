package org.apache.coyote.support;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceUtil {

    private static final String PREFIX = "static";

    public static String getResource(final String fileName) {
        try {
            return new String(Files.readAllBytes(getPath(fileName)));
        } catch (IOException e) {
            throw new InternalServerError();
        }
    }

    public static String getContentType(final String fileName) {
        try {
            return Files.probeContentType(getPath(fileName));
        } catch (IOException e) {
            throw new InternalServerError();
        }
    }

    private static Path getPath(final String fileName) {
        String filePath = ClassLoader.getSystemResource(PREFIX + fileName)
                .getPath();

        return new File(filePath).toPath();
    }
}
