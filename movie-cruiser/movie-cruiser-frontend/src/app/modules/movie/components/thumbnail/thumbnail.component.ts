import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Movie } from '../../movie';
import { DialogComponent } from './../dialog/dialog.component';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'movie-thumbnail',
  templateUrl: './thumbnail.component.html',
  styleUrls: ['./thumbnail.component.css']
})

export class ThumbnailComponent implements OnInit {
  @Input()
  movie: Movie;

  @Input()
  watchlist: boolean;

  @Output()
  addMovie = new EventEmitter;

  @Output()
  deleteMovie = new EventEmitter;

  @Output()
  updateMovie = new EventEmitter;

  comments: string;

  constructor(public dialog: MatDialog) { }

  ngOnInit() { }

  addToWatchlist() {
    console.log(this.movie);
    this.addMovie.emit(this.movie);
  }

  deleteFromWatchlist() {
    console.log(this.movie);
    this.deleteMovie.emit(this.movie);
  } 

  updateFromWatchlist(actionType) {
    const dialogRef = this.dialog.open(DialogComponent, {
      width: '250px',
      data: {movie: this.movie, actionType:actionType, comments: this.movie.comments}
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');   
    });
  }

}
