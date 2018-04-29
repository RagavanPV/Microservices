import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import { map } from 'rxjs/operator/map';
import { error } from 'selenium-webdriver';
@Injectable()
export class AuthenticationService {
  constructor(private http: HttpClient) { }
login(details) {
  const encodedString = this.encode('admin' + ':' + 'password');
  const headers = new HttpHeaders({
    'Authorization': 'Basic ' + encodedString,
    'Content-Type':  'application/x-www-form-urlencoded'
  });
  const body = new URLSearchParams();
  body.set('grant_type', 'password');
  body.set('client_id', 'admin');
  body.set('username', details.email);
  body.set('password', details.password);
   return this.http.post('http://localhost:8001/oauth/token', body.toString(), {headers: headers}).
    map((data: any) => {
      console.log(data.access_token);
      if(data && data.access_token){
        localStorage.setItem('token',data.access_token);
        return true;
      }
    });
  }
  encode(input) {
    const keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
    let output: String = '';
    let chr1: number, chr2: number, chr3: number;
    let enc1: number, enc2: number, enc3: number, enc4: number;
    let i = 0;
    do {
        chr1 = input.charCodeAt(i++);
        chr2 = input.charCodeAt(i++);
        chr3 = input.charCodeAt(i++);
        enc1 = chr1 >> 2;
        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
        enc4 = chr3 & 63;
        if (isNaN(chr2)) {
            enc3 = enc4 = 64;
        } else if (isNaN(chr3)) {
            enc4 = 64;
        }
        output = output +
            keyStr.charAt(enc1) +
            keyStr.charAt(enc2) +
            keyStr.charAt(enc3) +
            keyStr.charAt(enc4);
        chr1 = chr2 = chr3 = 0;
        enc1 = enc2 = enc3 = enc4 = 0;
    } while (i < input.length);
    return output;
  }

  getToken() {
    return localStorage.getItem("token")
  }

  isLoggedIn() {
    return this.getToken() !== null;
  }
  logout() {
    localStorage.removeItem("token");
  }

}