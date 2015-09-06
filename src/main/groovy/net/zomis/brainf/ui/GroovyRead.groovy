package net.zomis.brainf.ui

class GroovyRead {
    static String read(String s) {
        return GroovyRead.getClassLoader().getResource(s).text;
    }
}
