import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/AuthenticationService/authentication.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private router:Router,private auth: AuthenticationService) { }

  ngOnInit() {
  }
  logout(){
    this.auth.logout();
    this.router.navigate(["login"]);
  }
}
