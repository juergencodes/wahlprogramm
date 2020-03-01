package de.mathit.wahlprogramm;

import java.util.Arrays;

public class RendererZieleHtml extends SimpleRendererSupport {

  public RendererZieleHtml() {
    super("ziele.html", null, null, z -> Arrays.asList("<h3>" + z + "</h3>"), null, null, false,
        false);
  }

}