package de.mathit.wahlprogramm;

import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

  @SuppressWarnings("unchecked")
  public static void main(final String[] args) throws IOException {
    if (args.length < 1) {
      throw new IllegalArgumentException("First parameter must be path to input yml.");
    }
    if (args.length < 2) {
      throw new IllegalArgumentException("Second parameter must be path to output.");
    }
    final String input = args[0];
    final String output = args[1];

    final List<Renderer> renderers = new LinkedList<>();
    // renderers.add(new RendererDiskussionMarkdown());
    renderers.add(new RendererDiskussionHtml());

    // renderers.add(new RendererFlyerMarkdown());
    // renderers.add(new RendererFlyerHtml());

    // renderers.add(new RendererDokumentMarkdown(2, false));
    // renderers.add(new RendererDokumentHtml(2, false));

    // renderers.add(new RendererDokumentMarkdown(3, true));
    renderers.add(new RendererDokumentHtml(3, true));

    renderers.add(new RendererWordpress(2, false));
    renderers.add(new RendererWordpress(3, true));

    final Map<String, Object> content = new Yaml().load(Main.class.getResourceAsStream(input));
    final String slogan = (String) content.get("slogan");
    renderers.forEach(r -> r.slogan(slogan));

    final List<Map<String, Object>> ziele = (List<Map<String, Object>>) content.get("ziele");

    for (int k = 0; k < ziele.size(); k++) {
      final Map<String, Object> ziel = ziele.get(k);
      final String zielText = (String) ziel.get("ziel");
      final int indexZiel = k + 1;
      renderers.forEach(r -> r.startZiel(zielText, indexZiel));

      final List<Map<String, Object>> ideen = (List<Map<String, Object>>) ziel.get("ideen");
      for (int i = 0; ideen != null && i < ideen.size(); i++) {
        final Map<String, Object> idee = ideen.get(i);
        final String text = (String) idee.get("idee");
        if (text == null) {
          System.err.println("Empty idea (" + indexZiel + ")");
          continue;
        }
        final boolean entwurf = Boolean.TRUE.equals(idee.get("entwurf"));
        final Integer level = (Integer) idee.get("level");
        final String kommentar = (String) idee.get("kommentar");
        final List<String> beispiele = (List<String>) idee.get("beispiele");
        final int indexIdee = i + 1;

        final StringBuilder builder = new StringBuilder();
        builder.append(text);
        if (beispiele != null && beispiele.size() > 0) {
          builder.append(", wie etwa ");
          String delimiter = "";
          for (final String beispiel : beispiele) {
            builder.append(delimiter).append(beispiel);
            delimiter = ", ";
          }
        }
        final String ideeMitBeispielen = builder.toString();

        renderers.forEach(r -> r.idee(text, ideeMitBeispielen, entwurf, level, kommentar,
            beispiele == null ? Collections.emptyList() : beispiele, indexZiel, indexIdee));

      }
      renderers.forEach(r -> r.endZiel());
    }

    final File path = new File("target\\" + output);
    path.mkdirs();
    for (final Renderer renderer : renderers) {
      final BufferedWriter writer = new BufferedWriter(
          new FileWriter(new File(path, renderer.filename())));
      for (final String line : renderer.content()) {
        writer.write(line);
        writer.write("\n");
      }
      writer.close();
    }
  }

}