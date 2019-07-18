import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { Router } from '@angular/router';
import { Observable, of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterTestingModule } from '@angular/router/testing';
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatCardModule, MatDialog, MatDialogModule } from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { By } from '@angular/platform-browser';
import { Location } from '@angular/common';
import { ThumbnailComponent } from './thumbnail.component';

 
   var movieData =  {
      "vote_count": 1861,
      "id": 19404,
      "video": false,
      "vote_average": 9.3,
      "title": "Dilwale Dulhania Le Jayenge",
      "popularity": 10.936,
      "poster_path": "https://image.tmdb.org/t/p/w500/uC6TTUhPpQCmgldGyYveKRAu8JN.jpg",
      "original_language": "hi",
      "original_title": "दिलवाले दुल्हनिया ले जायेंगे",
      "genre_ids": [
        35,
        18,
        10749
      ],
      "backdrop_path": "/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg",
      "adult": false,
      "overview": "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
      "release_date": "1995-10-20"
    };
    




describe('ThumbnailComponent', () => {
  let component: ThumbnailComponent;
  let fixture: ComponentFixture<ThumbnailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations:[
        ThumbnailComponent
      ],
      imports:[FormsModule,MatCardModule, HttpClientModule, MatFormFieldModule,MatDialogModule, MatInputModule, MatButtonModule, BrowserAnimationsModule]
      
     
  }).compileComponents();

 }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ThumbnailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should  have a movie title', () => {
    let title = fixture.debugElement.query(By.css('.movie-title'));
    let titleInput = title.nativeElement;       
    expect(titleInput).toBeTruthy();   
  });

  it('should have a poster image', () => {
    let poster = fixture.debugElement.query(By.css('.movie-image'));
    let posterInput = poster.nativeElement;       
    expect(posterInput).toBeTruthy(); 
  });

  it('should have a description', () => {
    let desc = fixture.debugElement.query(By.css('.movie-overview'));
    let descInput = desc.nativeElement;       
    expect(descInput).toBeTruthy(); 
  });

  it('should have buttons', () => {
    expect(component).toBeTruthy();
  });



});
