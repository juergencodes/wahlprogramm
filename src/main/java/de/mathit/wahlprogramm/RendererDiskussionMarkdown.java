package de.mathit.wahlprogramm;

import java.util.LinkedList;
import java.util.List;

public class RendererDiskussionMarkdown implements Renderer {

  private final List<String> content = new LinkedList<>();


  @Override
  public String filename() {
    return "diskussion.md";
  }

  @Override
  public void slogan(final String slogan) {
    content.add("# " + slogan);
  }

  @Override
  public void startZiel(final String ziel, final int index) {
    content.add("### [" + index + "] " + ziel);
  }

  @Override
  public void idee(final String idee, final String ideeMitBeispielen, final boolean entwurf,
      final Integer level, final String kommentar, final List<String> beispiele,
      final int indexZiel, final int indexIdee) {

    content.add(
        "* " + (entwurf ? "(Entwurf) " : "") + "[" + indexZiel + "." + indexIdee + "] " + idee
            + "");
    if (beispiele.size() > 0) {
      content.add("    * *Beispiele*:");
      for (final String beispiel : beispiele) {
        content.add("        * " + beispiel);
      }
    }
    if (kommentar != null && !kommentar.equals("")) {
      content.add("    *  *Interner Kommentar:* " + kommentar.trim() + "");
    }
    if (level != null) {
      content.add("    *  *Level:* " + level + "");
    }
  }


  @Override
  public void endZiel() {

  }

  @Override
  public List<String> content() {
    return content;
  }

}