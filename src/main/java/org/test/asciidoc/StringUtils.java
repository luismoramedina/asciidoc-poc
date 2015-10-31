package org.test.asciidoc;

public class StringUtils {
    // tag::contains[]
    public boolean contains(String haystack, String needle) {
        return haystack.contains(needle);
    }
    // end::contains[]
    // tag::other[]
    public boolean other(String haystack, String needle) {
        return haystack.contains(needle);
    }
    // end::other[]
}
