import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from './../../movie.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'movie-watchlist',
  templateUrl: './watchlist.component.html',
  styleUrls: ['./watchlist.component.css']
})
export class WatchlistComponent implements OnInit {

  movie: Movie;
  movies: Array<Movie>;
  header: string;
  watchlist = true;

  constructor(private movieService: MovieService, private route: ActivatedRoute) { 
    this.movies = [];
    this.route.data.subscribe(data => {   
      this.header = data.header;
    });
  }

  ngOnInit() {
    this.movieService.getMoviesFromWatchList()
      .subscribe((movies) => {
        this.movies.push(...movies);
      });
  }

}
