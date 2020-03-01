package de.mathit.wahlprogramm;

public class RendererDokumentHtml extends SimpleRendererSupport {

  public RendererDokumentHtml() {
    this(null, true);
  }

  public RendererDokumentHtml(final Integer maxLevel, final boolean withBeispiele) {
    super(maxLevel == null ? "dokument.html" : String.format("dokument-%s.html", maxLevel),
        maxLevel, HTML_SLOGAN, HTML_HEADER_TEXT, HTML_ITEM, HTML_END, false, withBeispiele);
  }

}