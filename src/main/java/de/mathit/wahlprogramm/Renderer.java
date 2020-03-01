package de.mathit.wahlprogramm;

import java.util.List;

public interface Renderer {

  String filename();

  void slogan(String slogan);

  void startZiel(String ziel, int index);

  void idee(String idee, String ideeMitBeispielen, boolean entwurf, Integer level, String kommentar,
      List<String> beispiele, int indexZiel, int indexIdee);

  void endZiel();

  List<String> content();

}