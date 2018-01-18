import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  private resultsGet;
  private resultPost;
  private getUrl = "http://localhost:8080/TestProject/api/responseWithHeader";
  private postUrl = "https://jsonplaceholder.typicode.com/posts";

  constructor(private http: HttpClient) { }

  private getHttp() {
    this.http.get(this.getUrl).subscribe(data => {
      this.resultsGet = data;
    }, (err: HttpErrorResponse) => {
      if (err.error instanceof Error) {
        // A client-side or network error occurred. Handle it accordingly.
        console.log('An error occurred:', err.error.message);
      }
      else {
        // The backend returned an unsuccessful response code.
        // The response body may contain clues as to what went wrong,
        if(err.status == 200){
        	this.resultsGet = err.error;
        }
        console.log(`Backend returned code ${err.status}, body was: ${err.error}`);
      }
    });
  }

  private postHttp() {
    this.http.post(this.postUrl, {
      title: 'foo',
      body: 'bar',
      userId: 1
    })
      .subscribe(
      res => {
        this.resultPost = res;
      },
      err => {
        console.log("Error occured");
      }
      );
  }


  ngOnInit(): void {
    // ngOnInit sie wykonuje za kazdym razem kiedy strona jest wczytywana
    this.getHttp();
    this.postHttp();
  }

}
