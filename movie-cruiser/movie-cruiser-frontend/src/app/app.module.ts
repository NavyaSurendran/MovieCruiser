import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router';
import { AppComponent } from './app.component';
import { MovieModule } from './modules/movie/movie.module';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { AuthGuard } from './auth.guard.service';

const appRoutes: Routes = [ 
  {
      path: '',
      redirectTo: 'user',
      pathMatch: 'full'
  } 
];


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MovieModule,    
    RouterModule.forRoot(appRoutes),
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    MatInputModule,
    AuthenticationModule,
  ],
  providers: [
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
