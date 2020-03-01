package de.mathit.wahlprogramm;

import java.util.LinkedList;
import java.util.List;

public class RendererDiskussionHtml implements Renderer {

  private final List<String> content = new LinkedList<>();


  @Override
  public String filename() {
    return "diskussion.html";
  }

  @Override
  public void slogan(final String slogan) {
    content.add("<h2>&ldquo;" + slogan + "&ldquo;</h2>");
  }

  @Override
  public void startZiel(final String ziel, final int index) {
    content.add("<h3>[" + index + "] " + ziel + "</h3>");
    content.add("<ul>");
  }

  @Override
  public void idee(final String idee, final String ideeMitBeispielen, final boolean entwurf,
      final Integer level, final String kommentar, final List<String> beispiele,
      final int indexZiel, final int indexIdee) {

    content.add(
        "<li> " + (entwurf ? "(Entwurf) " : "") + "[" + indexZiel + "." + indexIdee + "] " + idee
            + "</li>");

    final boolean hasBeispiele = beispiele.size() > 0;
    final boolean hasKommentar = kommentar != null && !kommentar.equals("");
    final boolean hasLevel = level != null;
    if (hasBeispiele || hasKommentar || hasLevel) {
      content.add("<ul>");

      if (hasBeispiele) {
        content.add("<li><i>Beispiele:</i></li>");
        content.add("<ul>");
        for (final String beispiel : beispiele) {
          content.add("<li>" + beispiel + "</li>");
        }
        content.add("</ul>");
      }
      if (hasKommentar) {
        content.add("<li><i>Interner Kommentar:</i> " + kommentar + "</li>");
      }
      if (hasLevel) {
        content.add("<li><i>Level:</i> " + level + "</li>");
      }
      content.add("</ul>");
    }

  }


  @Override
  public void endZiel() {
    content.add("</ul>");
  }

  @Override
  public List<String> content() {
    return content;
  }

}