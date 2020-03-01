package de.mathit.wahlprogramm;

public class RendererFlyerMarkdown extends SimpleRendererSupport {

  public RendererFlyerMarkdown() {
    super("flyer.md", 1, null, MD_HEADER, MD_ITEM, null, true, false);
  }

}