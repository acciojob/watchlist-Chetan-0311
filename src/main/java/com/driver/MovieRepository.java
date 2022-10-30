package com.driver;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MovieRepository {
    HashMap<String , Movie> movies = new HashMap<>();
    HashMap<String, Director> directors = new HashMap<>();

    HashMap<String, HashSet<String>> pair = new HashMap<>();
    public void addMovie(Movie movie){
        movies.put(movie.name, movie);
    }
    public void addDirector(Director director){
        directors.put(director.getName(), director);
    }
    public void addPair(String movieName, String directorName){
        if(!pair.containsKey(directorName)){
            pair.put(directorName,new HashSet<>());
        }

        pair.get(directorName).add(movieName);
    }
    public Movie getMovieByName(String movieName){
        return movies.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return directors.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName){
        List<String> movieNames = new ArrayList<>();

        for(String movieName : pair.get(directorName)){
            movieNames.add(movieName);
        }

        return movieNames;
    }

    public List<String> findAllMovies(){
        List<String> allMovies = new ArrayList<>();

        for(String movieName : movies.keySet()){
            allMovies.add(movieName);
        }
        return allMovies;
    }
    public void deleteDirectorByName(String name){
        for(String movieName : pair.get(name)){
            movies.remove(movieName);
        }
        directors.remove(name);
        pair.remove(name);
    }
    public void deleteAllDirectors(){
        for(String name: pair.keySet()){
            deleteDirectorByName(name);
        }
    }
}
