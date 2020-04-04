/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author sonnt
 */
public class HtmlHelper {

    public static String hyperlink(String label, String href) {
        return "<a class=\"page_hyperlink\" href=\"" + href + "\">" + label + "</a>";
    }

    public static String pager(int pageindex, int pagecount, int gap) {
        String result = "";
        if (pageindex > gap + 1) {
            result += hyperlink("First", "ca?page=" + 1);
        }

        for (int i = gap; i > 0; i--) {
            if (pageindex - i > 0) {
                result += hyperlink("" + (pageindex - i), "ca?page=" + (pageindex - i));
            }
        }

        result += "<span class=\"page_pageindex\">" + pageindex + "</span>";

        for (int i = 1; i <= gap; i++) {
            if (pageindex + i <= pagecount) {
                result += hyperlink("" + (pageindex + i), "ca?page=" + (pageindex + i));
            }
        }

        if (pageindex + gap < pagecount - 1) {
            result += hyperlink("Last", "ca?page=" + pagecount);
        }
        return result;
    }
}
