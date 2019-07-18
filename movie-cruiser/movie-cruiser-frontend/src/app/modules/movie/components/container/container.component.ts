import { Component, OnInit, Input } from '@angular/core';
import { Movie } from '../../movie';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from './../../movie.service';


@Component({
  selector: 'movie-container',
  templateUrl: './container.component.html',
  styleUrls: ['./container.component.css']
})

export class ContainerComponent implements OnInit {

  @Input()
  movies: Array<Movie>;

  @Input()
  header: string;

  @Input()
  watchlist: boolean;

  constructor(private movieService: MovieService, private snackBar: MatSnackBar) {     
  }

  ngOnInit() { }

  addToWatchlist(movie) {     
    this.movieService.addMoviesToWatchList(movie)
    .subscribe((data) => {
      this.snackBar.open("Movie added to watchlist","",{
        duration:2000
      })
    });      
  }

  deleteFromWatchlist(movie) {     
    let movieIndex = this.movies.findIndex((mv) => {
      return mv.id === movie.id;
    });
    
    this.movies.splice(movieIndex,1);

    this.movieService.deleteFromWatchlist(movie)
    .subscribe((data) => {
      this.snackBar.open("Movie deleted from watchlist","",{
        duration:2000
      })
    });      
  }

}
