import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, retry } from 'rxjs/operators';
import { Movie } from './movie';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  tmdbEndpoint: string;
  tmdbImageUrlPrefix: string;
  apiKey: string;
  page: string;
  movieEndpoint: string;
  tmdbSearchEndpoint: string;

  constructor(private http: HttpClient) { 
    this.apiKey = 'api_key=6f50c7a13e0fad2acf4abfec65a059be';
    this.tmdbEndpoint ='https://api.themoviedb.org/3/movie';
    this.tmdbSearchEndpoint ='https://api.themoviedb.org/3/search/movie';
    this.tmdbImageUrlPrefix = 'https://image.tmdb.org/t/p/w500';
    this.page = '1';
    this.movieEndpoint='http://localhost:8082/api/v1/movie';
   }

   getMovies(type: string): Observable<Array<Movie>> {   
    const endpoint = `${this.tmdbEndpoint}/${type}?${this.apiKey}&page=${this.page}`; 
    return this.http.get(endpoint )
      .pipe(
          map(this.pickMovieResults),
          map(this.transformPosterPath.bind(this))
        );   
  }

  transformPosterPath(movies): Array<Movie> {
    console.log('movie.poster_path');
    return movies.map( movie =>  {      
      movie.poster_path = `${this.tmdbImageUrlPrefix}${movie.poster_path}`;
      movie.movie_id = movie.id;
      console.log(movie.poster_path);
      return movie;
    });   
  }

  pickMovieResults(response) {
    return response['results'];
  }

  addMoviesToWatchList(movie) {
    return this.http.post(this.movieEndpoint, movie);
  }

  getMoviesFromWatchList(): Observable<Array<Movie>> {
    return this.http.get<Array<Movie>>(this.movieEndpoint);
  }

  deleteFromWatchlist(movie: Movie) {
    const deleteEndpoint = `${this.movieEndpoint}/${movie.id}`;
    return this.http.delete(deleteEndpoint,{responseType: 'text'});
  }

  updateMovieComments(movie: Movie) {
    console.log(movie);
    const updateEndpoint = `${this.movieEndpoint}/${movie.id}`;
    return this.http.put(updateEndpoint, movie);
  }

  searchTmdbMovie(searchKey: string): Observable<Array<Movie>> {
    if(searchKey.length > 0) {
      const searchEndpoint = `${this.tmdbSearchEndpoint}?${this.apiKey}&page=${this.page}&query=${searchKey}&language=en-US&include_adult=false`;   
    return this.http.get<Array<Movie>>(searchEndpoint)
      .pipe(
        map(this.pickMovieResults),
        map(this.transformPosterPath.bind(this))
      );  
    }
  }
  
}
