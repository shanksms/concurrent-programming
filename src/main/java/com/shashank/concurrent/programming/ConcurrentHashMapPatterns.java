package com.shashank.concurrent.programming;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapPatterns {

  public static void main(String[] args) {
    ConcurrentHashMap<String, List<String>> moviesByActor = new ConcurrentHashMap<>();
    moviesByActor.put("Prateek Gandhi", Arrays.asList("scam"));
    moviesByActor.put("Ajay Devgan", Arrays.asList("Gangajal", "Apaharan"));

    int maxMovies = moviesByActor.reduce(10000, (actor, movies) -> movies.size(), Integer::max);
    System.out.println(maxMovies);

    String maxMovieActor = moviesByActor.search(10000, (actor, movies) -> movies.size() == maxMovies ? actor : null);
    System.out.println(maxMovieActor);
  }

}
