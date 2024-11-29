import { Injectable } from "@angular/core";
import { userData } from "../model/userdata.model";
import { PagedResponse } from "../model/paged.response";
import { Observable } from "rxjs";
import { HttpClient, HttpHeaders} from "@angular/common/http";
import { userLogin } from "../model/userdata.login";

@Injectable({
    providedIn: 'root'
  })

export class userService{
    constructor(private http:HttpClient) {}

    //SIGN UP CONFIGS
    //get url
    private getUrl:string='http://localhost:8080/cadastro/read';

    redAll(): Observable<PagedResponse<userData>>{
        return this.http.get<PagedResponse<userData>>(this.getUrl);
    }

    //post url
    private postUrl:string='http://localhost:8080/auth/register';
    cadastrar(user:userData):Observable<userData>{
        console.log(user)
        const headers = new HttpHeaders({
            'Content-Type': 'application/json',  // Definindo o tipo de conteúdo como JSON
            'Accept': 'application/json'         // Garantindo que aceitamos JSON na resposta
          });
        return this.http.post<userData>(this.postUrl, JSON.stringify(user), { headers });
    }

    //delete url
    private deleteUrl:string='http://localhost:8080/cadastro/delete/';

    delete(email:string): Observable<void>{
        return this.http.delete<void>(`${this.deleteUrl}${email}`);
      }

//SIGN IN CONFIGS
//postUrl
private loginUrl:string='http://localhost:8082/auth/login';
login(user:userLogin):Observable<userLogin>{
  console.log(user)
  const headers = new HttpHeaders({
      'Content-Type': 'application/json',  // Definindo o tipo de conteúdo como JSON
      'Accept': 'application/json'         // Garantindo que aceitamos JSON na resposta
    });
  return this.http.post<userLogin>(this.loginUrl , JSON.stringify(user), { headers });
}

}