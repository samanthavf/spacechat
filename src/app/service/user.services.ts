import { Injectable } from "@angular/core";
import { userData } from "../model/userdata.model";
import { PagedResponse } from "../model/paged.response";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { userLogin } from "../model/userdata.login";

@Injectable({
  providedIn: 'root'
})

export class userService {
  constructor(private http: HttpClient) { }

  private getUrl: string = 'http://localhost:8080/cadastro/read';

  redAll(): Observable<PagedResponse<userData>> {
    return this.http.get<PagedResponse<userData>>(this.getUrl);
  }

  private postUrl: string = 'http://localhost:8080/auth/register';
  cadastrar(user: userData): Observable<userData> {
    console.log(user)
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.post<userData>(this.postUrl, JSON.stringify(user), { headers });
  }

  private deleteUrl: string = 'http://localhost:8080/cadastro/delete/';

  delete(email: string): Observable<void> {
    return this.http.delete<void>(`${this.deleteUrl}${email}`);
  }


  private loginUrl: string = 'http://localhost:8082/auth/login';
  login(user: userLogin): Observable<userLogin> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    });
    return this.http.post<userLogin>(this.loginUrl, JSON.stringify(user), { headers });
  }

}