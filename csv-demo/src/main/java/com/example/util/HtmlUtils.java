package com.example.util;

import java.util.List;

import org.owasp.html.ElementPolicy;
import org.owasp.html.HtmlPolicyBuilder;
import org.owasp.html.PolicyFactory;

public class HtmlUtils {

    // 允许的标签
    private static final String[] allowedTags = { "h1", "h2", "h3", "h4", "h5", "h6", "span", "strong", "img", "video",
            "source", "blockquote", "p", "div", "ul", "ol", "li", "table", "thead", "caption", "tbody", "tr", "th",
            "td", "br", "a" };

    // 需要转化的标签
    private static final String[] needTransformTags = { "article", "aside", "command", "datalist", "details",
            "figcaption", "figure", "footer", "header", "hgroup", "section", "summary" };

    // 带有超链接的标签
    private static final String[] linkTags = { "img", "video", "source", "a" };

    public static String sanitizeHtml(String htmlContent) {
        PolicyFactory policy = new HtmlPolicyBuilder()
                // 所有允许的标签
                .allowElements(allowedTags)
                // 内容标签转化为div
                .allowElements(new ElementPolicy() {
                    @Override
                    public String apply(String elementName, List<String> attributes) {
                        return "div";
                    }
                }, needTransformTags)
                .allowAttributes("src", "href", "target").onElements(linkTags)
                // 校验链接中的是否为http
//                .allowUrlProtocols("https")
                .toFactory();
        String safeHTML = policy.sanitize(htmlContent);
        return safeHTML;
    }

    public static void main(String[] args) {
        String inputHtml = "<img src=\"https://a.jpb\"/>";
        System.out.println(sanitizeHtml(inputHtml));
    }
}