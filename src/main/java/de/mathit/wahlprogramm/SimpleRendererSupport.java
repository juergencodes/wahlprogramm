package de.mathit.wahlprogramm;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Support class for renderers that simply output 'ziele' and 'idee' filtered by level.
 */
public class SimpleRendererSupport implements Renderer {

  protected static final String TEXT = "Zur Erreichung dieses Ziels, haben wir folgende Ideen.";

  protected static Function<String, String> HTML_SLOGAN = s -> "<h2>&ldquo;" + s + "&ldquo;</h2>";
  protected static BiFunction<String, List<String>, List<String>> HTML_ITEM = (x, b) -> Arrays
      .asList("<li>" + x + "</li>");
  protected static final Function<String, List<String>> HTML_HEADER = z -> Arrays
      .asList("<h3>" + z + "</h3>", "<ul>");
  protected static final Function<String, List<String>> HTML_HEADER_TEXT = z -> Arrays
      .asList("<h3>" + z + "</h3>", "<p>" + TEXT + "</p>", "<ul>");
  protected static Supplier<List<String>> HTML_END = () -> Arrays.asList("</ul>");

  protected static Function<String, List<String>> MD_HEADER = x -> Arrays.asList("### " + x);
  protected static final Function<String, List<String>> MD_HEADER_TEXT = z -> Arrays
      .asList("### " + z, TEXT);
  protected static BiFunction<String, List<String>, List<String>> MD_ITEM = (x, b) -> Arrays
      .asList("* " + x);

  private final String filename;
  private final Integer maxLevel;
  private final Function<String, String> slogan;
  private final Function<String, List<String>> startZiel;
  private final BiFunction<String, List<String>, List<String>> idee;
  private final Supplier<List<String>> endeZiel;
  private final boolean withIndex;
  private final boolean withBeispiele;
  private final List<String> content = new LinkedList<>();
  private List<String> buffer;
  private boolean anyIdee;

  public SimpleRendererSupport(final String filename, final Integer maxLevel,
      final Function<String, String> slogan, final Function<String, List<String>> startZiel,
      final BiFunction<String, List<String>, List<String>> idee,
      final Supplier<List<String>> endeZiel, final boolean withIndex, final boolean withBeispiele) {
    this.filename = filename;
    this.maxLevel = maxLevel;
    this.slogan = slogan;
    this.startZiel = startZiel;
    this.idee = idee;
    this.endeZiel = endeZiel;
    this.withIndex = withIndex;
    this.withBeispiele = withBeispiele;
  }


  @Override
  public String filename() {
    return filename;
  }

  @Override
  public void slogan(final String slogan) {
    if (this.slogan == null) {
      return;
    }
    content.add(this.slogan.apply(slogan));
  }

  @Override
  public void startZiel(final String ziel, final int index) {
    buffer = new LinkedList<>();
    anyIdee = false;
    buffer.addAll(startZiel.apply(withIndex ? "[" + index + "] " + ziel : ziel));
  }

  @Override
  public void idee(final String idee, final String ideeMitBeispielen, final boolean entwurf,
      final Integer level, final String kommentar, final List<String> beispiele,
      final int indexZiel, final int indexIdee) {
    if (maxLevel != null && (level == null || level > maxLevel)) {
      return;
    }
    anyIdee = true;
    if (this.idee != null) {
      final String text = String
          .format("%s%s", withIndex ? "[" + indexZiel + "." + indexIdee + "] " : "",
              withBeispiele ? ideeMitBeispielen : idee);
      buffer.addAll(this.idee.apply(text, beispiele));
    }
  }

  @Override
  public void endZiel() {
    if (endeZiel != null) {
      buffer.addAll(endeZiel.get());
    }
    if (anyIdee) {
      content.addAll(buffer);
    }
  }

  @Override
  public List<String> content() {
    return content;
  }

}