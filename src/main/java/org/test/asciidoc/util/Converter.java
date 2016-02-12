package org.test.asciidoc.util;

/**
 * @author luismoramedina
 */

import org.asciidoctor.Asciidoctor;
import org.asciidoctor.Attributes;
import org.asciidoctor.Options;
import org.asciidoctor.SafeMode;

import java.io.File;

public class Converter {
    public static void main(String[] args) {
        Asciidoctor asciidoctor = Asciidoctor.Factory.create();

        Attributes attributes = new Attributes();
        attributes.setBackend("html");
        attributes.setAttribute("sourcedir", "c:\\datos\\Snippets\\asciidocs-poc\\src\\main\\");
        attributes.setAttribute(Attributes.ALLOW_URI_READ, "true");

        Options options = new Options();
        options.setAttributes(attributes);
        options.setSafe(SafeMode.UNSAFE);


        String s = asciidoctor.convertFile(
                new File("c:\\datos\\Snippets\\asciidocs-poc\\src\\docs\\asciidoc\\my-test.adoc"),
                options);

        System.out.println("s = " + s);

    }
}