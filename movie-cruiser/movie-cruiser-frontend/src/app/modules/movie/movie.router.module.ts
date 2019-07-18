import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { TmdbComponent } from './components/tmdb/tmdb.component';
import { WatchlistComponent } from './components/watchlist/watchlist.component';
import { SearchComponent } from './components/search/search.component';
import { AuthGuard } from '../../auth.guard.service';

const movieRoutes: Routes = [
    {
        path: 'movies',
        children: [
            {
                path: '',
                redirectTo: '/movies/popular',
                pathMatch: 'full',
                canActivate: [AuthGuard]
            },
            {
                path: 'popular',
                component: TmdbComponent,
                canActivate: [AuthGuard],
                data: {
                    movieType: 'popular',
                    header: 'Popular Movies'
                }
            },
            {
                path: 'top-rated',
                component: TmdbComponent,
                canActivate: [AuthGuard],
                data: {
                    movieType: 'top_rated',
                    header: 'Top rated Movies'
                }
            },
            {
                path: 'watchlist',
                component: WatchlistComponent,
                canActivate: [AuthGuard],
                data: {
                    movieType: 'watchlist',
                    header: 'Watchlist'
                }
            },
            {
                path: 'search',
                component: SearchComponent,
                canActivate: [AuthGuard],
                data: {
                    movieType: 'search',
                    header: 'Search Movies'
                }
            }
        ] 
    }
];
@NgModule({
    imports: [
        RouterModule.forChild(movieRoutes),      
    ],
    exports: [
        RouterModule
    ]
})

export class MovieRouterModule { }