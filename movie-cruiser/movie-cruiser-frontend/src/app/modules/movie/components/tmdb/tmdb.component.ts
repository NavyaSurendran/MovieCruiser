import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from './../../movie.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'movie-tmdb',
  templateUrl: './tmdb.component.html',
  styleUrls: ['./tmdb.component.css']
})
export class TmdbComponent implements OnInit {

  movies: Array<Movie>;
  header: string;
  movieType: string;

  constructor(private movieService: MovieService, private route: ActivatedRoute) { 
    this.movies = [];
    this.route.data.subscribe(data => {
      this.movieType = data.movieType;
      this.header = data.header;
    });
  }

  ngOnInit() {
    this.movieService.getMovies(this.movieType)
      .subscribe((movies) => {
        this.movies.push(...movies);
      });
  }

}
