import { customValidators } from './../Validators/custom.validators';
import { Component, OnInit } from '@angular/core';
import { FormGroup,FormControl,Validators } from "@angular/forms";
import { Http } from '@angular/http';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private auth: AuthenticationService) { }

  ngOnInit() {
  }

  loginForm =new FormGroup({
    email: new FormControl('', [customValidators.containsSpace, customValidators.validateEmail, Validators.required]),
    password: new FormControl('', [customValidators.containsSpace,Validators.required]),
    isRemember:new FormControl('',Validators.required)
  });

  

  login() {
    console.log(this.loginForm.value);
    this.auth.login(this.loginForm.value);
  }
  
}
