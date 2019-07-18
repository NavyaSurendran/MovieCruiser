import { Component, OnInit } from '@angular/core';
import { Movie } from '../../movie';
import { MovieService } from '../../movie.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'movie-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  movies: Array<Movie>;
  header: string;

  constructor(private movieService: MovieService,private route: ActivatedRoute) {
    this.movies = [];
    this.route.data.subscribe(data => {   
      this.header = data.header;
    });
   }

  ngOnInit() {
  }

  onEnter(searchKey) {
    console.log("Search key: " + searchKey);
    this.movieService.searchTmdbMovie(searchKey)
      .subscribe((movies) => {
        this.movies.push(...movies);
      });
  }

}
