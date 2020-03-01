package de.mathit.wahlprogramm;

public class RendererFlyerHtml extends SimpleRendererSupport {

  public RendererFlyerHtml() {
    super("flyer.html", 1, null, HTML_HEADER, HTML_ITEM, HTML_END, true, false);
  }

}