import { customValidators } from './../Validators/custom.validators';
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl,Validators } from "@angular/forms";
import { Http } from '@angular/http';
import { AuthenticationService } from '../services/AuthenticationService/authentication.service';
import { Router } from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private router:Router,private auth: AuthenticationService) { }

  ngOnInit() {
  }

  loginForm =new FormGroup({
    email: new FormControl('', [customValidators.containsSpace, customValidators.validateEmail, Validators.required]),
    password: new FormControl('', [customValidators.containsSpace,Validators.required]),
    isRemember:new FormControl('',Validators.required)
  });

  

  login() {
    console.log(this.loginForm.value);
    var isAuthenticated = this.auth.login(this.loginForm.value).subscribe((data:any) =>{
      console.log(data);
      if(data)
      this.router.navigate(['/']);
    });
  }

  
  
}
