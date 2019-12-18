import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from './login/login.component';
import {AppComponent} from './app.component';
import {AdminBooksComponent} from './admin-books/admin-books.component';
import {BookFormComponent} from './book-list/book-form/book-form.component';
import {SingleBookComponent} from './book-list/single-book/single-book.component';
import {BookReserveComponent} from './book-list/book-reserve/book-reserve.component';
import {BookListComponent} from './book-list/book-list.component';
import {BookSearchComponent} from './book-list/book-search/book-search.component';
import {HomePageComponent} from './home-page/home-page.component';
import {FourOhFourComponent} from './four-oh-four/four-oh-four.component';
import {AuthGuard} from './services/auth-guard.service';
import {HeaderComponent} from './header/header.component';
import {RegisterComponent} from './register/register.component';


const routes: Routes = [
  { path: '', component: HeaderComponent },
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'books', canActivate: [AuthGuard], component: BookListComponent},
  {path: 'home', component: HomePageComponent},
  {path: 'books/new', component: BookFormComponent},
  {path: 'books/:id', component: SingleBookComponent},
  {path: 'books/view/search', component: BookSearchComponent},
  {path: 'books/user/reserve', component: BookReserveComponent},
  { path: 'not-found', component: FourOhFourComponent },
  { path: '**', redirectTo: 'not-found' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
