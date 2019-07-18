import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';

const userRoutes: Routes = [
    {
        path: 'user',
        children: [       
            {
                path: '',
                redirectTo: 'login',
                pathMatch: 'full'
            },    
            {
                path: 'register',
                component: RegisterComponent,                
            },
            {
                path: 'login',
                component: LoginComponent,                
            }
        ] 
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(userRoutes),      
    ],
    exports: [
        RouterModule
    ]
})

export class AuthenticationRouterModule { }