import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HelloworldComponent } from './components/helloworld/helloworld.component';
import { ThumbnailComponent } from './components/thumbnail/thumbnail.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { MovieService } from './movie.service';
import { ContainerComponent } from './components/container/container.component';
import { MovieRouterModule } from './movie.router.module';
import { MatCardModule } from '@angular/material/card';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { TmdbComponent } from './components/tmdb/tmdb.component';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { DialogComponent } from './components/dialog/dialog.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { SearchComponent } from './components/search/search.component';
import { TokenInterceptor } from './interceptor.service';

@NgModule({
  imports: [
    CommonModule,
    HttpClientModule,
    MovieRouterModule,
    MatCardModule,
    MatButtonModule,
    MatSnackBarModule,
    MatDialogModule,
    MatInputModule,
    FormsModule,
  ],
  declarations: [
    HelloworldComponent,
    ThumbnailComponent,
    ContainerComponent,
    WatchlistComponent,
    TmdbComponent,
    DialogComponent,
    SearchComponent
  ],
  exports: [
    HelloworldComponent,
    ThumbnailComponent,
    MovieRouterModule,   
    SearchComponent,
  ],
  providers: [
    MovieService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  entryComponents:[DialogComponent]
})
export class MovieModule { }
