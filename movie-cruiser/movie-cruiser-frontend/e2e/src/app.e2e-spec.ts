import { AppPage } from './app.po';
import { browser, by } from 'protractor';
import { protractor } from 'protractor/built/ptor';
import { element } from '@angular/core/src/render3/instructions';
import { async } from 'rxjs/internal/scheduler/async';

describe('Movie Cruiser Front end App', () => {
  let page: AppPage;
  var username = '';

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('MovieCruiserFrontend');
  });

  it('should redirect to login page', () => {  
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should redirect to register page', () => {  
    username = new Date().getUTCMilliseconds().toString();   
    browser.element(by.buttonText('Register')).click();
    expect(browser.getCurrentUrl()).toContain('/register');
  });

  it('should be able to register user', () => {  
    browser.element(by.name('firstname')).sendKeys('Super');
    browser.element(by.name('lastname')).sendKeys('User');
    browser.element(by.name('user_id')).sendKeys(username);
    browser.element(by.name('password')).sendKeys('password');
    browser.element(by.buttonText('Register')).click();
    expect(browser.getCurrentUrl()).toContain('/login');
  });

  it('should be able to login user and navigate to popular movies', () => {       
    browser.element(by.name('userId')).sendKeys(username);
    browser.element(by.name('password')).sendKeys('password');
    browser.element(by.buttonText('Login')).click();
    expect(browser.getCurrentUrl()).toContain('/popular');
  });

  it('should be able to search for movies', () => {       
    browser.element(by.buttonText('Search')).click();
    expect(browser.getCurrentUrl()).toContain('/search');

    browser.element(by.name('search')).sendKeys('super');
    browser.element(by.name('search')).sendKeys(protractor.Key.ENTER);
    const searchItems = browser.element.all(by.css('.movie-title'));    
   
    expect(searchItems.count()).toBeGreaterThan(10);
    for (let i =0; i<2; i++) {
      expect(searchItems.get(i).getText()).toContain('Super');
    } 
  });

  it('should be able to add movies to watchlist',async () => {       
    browser.driver.manage().window().maximize();
    browser.driver.sleep(1000);
    const searchItems = browser.element.all(by.css('mat-card-title'));   
    expect(searchItems.count()).toBeGreaterThan(10);
    browser.element(by.buttonText('Add to watchlist')).click();
  });


  it('should be able to delete movies from watchlist',async () => {       
    browser.element(by.buttonText('Watchlist')).click();
    expect(browser.getCurrentUrl()).toContain('/watchlist');
    const searchItems = browser.element.all(by.css('.movie-title'));   
    expect(searchItems.count()).toBe(1);
    browser.element(by.buttonText('Delete')).click();
    expect(searchItems.count()).toBe(0);
  });


  it('should be able to logout',async () => {       
    browser.element(by.buttonText('Logout')).click();
    expect(browser.getCurrentUrl()).toContain('/login');   
  });



});
