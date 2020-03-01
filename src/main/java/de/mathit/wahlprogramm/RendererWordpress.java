package de.mathit.wahlprogramm;

import java.util.Arrays;

public class RendererWordpress extends SimpleRendererSupport {

  public RendererWordpress() {
    this(null, true);
  }

  public RendererWordpress(final Integer maxLevel, boolean withBeispiele) {
    super(maxLevel == null ? "wordpress.html" : String.format("wordpress-%s.html", maxLevel),
        maxLevel, null, z -> Arrays.asList("",
            "<!-- wp:paragraph {\"customTextColor\":\"#46962b\",\"fontSize\":\"medium\"} -->",
            "<p style=\"color:#46962b\" class=\"has-text-color has-medium-font-size\">" + z
                + "</p>", "<!-- /wp:paragraph -->", "", "<!-- wp:list -->", "<ul>"),
        (x, b) -> Arrays.asList("<li>" + x + "</li>"),
        () -> Arrays.asList("</ul>", "<!-- /wp:list -->"), false, withBeispiele);
  }

}