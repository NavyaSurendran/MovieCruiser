import { Component, OnInit, Inject } from '@angular/core';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { MovieService } from '../../movie.service';
import { Movie } from './../../movie';

@Component({
  selector: 'movie-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  movie: Movie;
  comments: string;
  actionType: string;

  constructor(public snackBar: MatSnackBar, 
              public dialogRef: MatDialogRef<DialogComponent>, 
              @Inject(MAT_DIALOG_DATA) public data: any, 
              private movieService: MovieService
            ) { 
    this.comments = data.comments;
    this.movie = data.movie;
    this.actionType = data.actionType;
  }

  ngOnInit() {
  }

  onNoClick() {
    this.dialogRef.close();
  }

  updateComments() {
    this.movie.comments = this.comments;
    console.log(this.comments);
    this.dialogRef.close();
    this.movieService.updateMovieComments(this.movie)
        .subscribe((mov) => {
          this.snackBar.open("Movie comment updated","",{
            duration:2000
          });
        });
  }

}
