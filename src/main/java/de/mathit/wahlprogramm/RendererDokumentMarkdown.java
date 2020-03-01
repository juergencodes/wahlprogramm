package de.mathit.wahlprogramm;

public class RendererDokumentMarkdown extends SimpleRendererSupport {

  public RendererDokumentMarkdown() {
    this(null, true);
  }

  public RendererDokumentMarkdown(final Integer maxLevel, final boolean withBeispiele) {
    super(maxLevel == null ? "dokument.md" : String.format("dokument-%s.md", maxLevel), maxLevel,
        s -> "# " + s, MD_HEADER_TEXT, MD_ITEM, null, false, withBeispiele);
  }

}