import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterComponent } from './register/register.component';
import { AuthenticationRouterModule } from './authentication.router.module';
import { AuthenticationService } from './authentication.service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatDialogModule } from '@angular/material/dialog';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';

@NgModule({
  imports: [
    CommonModule,
    AuthenticationRouterModule, 
    MatButtonModule, 
    MatCardModule,
    MatSnackBarModule,
    MatInputModule,
    MatDialogModule,
    FormsModule  
  ],
  declarations: 
  [
    RegisterComponent,
    LoginComponent    
  ],
  providers: [
    AuthenticationService
  ],
  exports: [
    RegisterComponent,
    LoginComponent
  ]
})
export class AuthenticationModule { }
