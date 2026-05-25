package net.zomis.brainf.ui

/**
 * Utility class for Java code to make it easier to read from resources or files
 */
class GroovyRead {
    static String read(String s) {
        return GroovyRead.getClassLoader().getResource(s).text;
    }
    static String file(File file) {
        return file.text;
    }
}
