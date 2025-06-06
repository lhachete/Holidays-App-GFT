import { Injectable } from '@angular/core';
import User from '../models/User';
import { ApiService } from './api.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private usersUrl: string;
  private loginUrl: string;
  private registerUrl: string;

  constructor(private api: ApiService) {
    this.usersUrl  = `${this.api.getApiUrl()}/users`;
    this.loginUrl  = `${this.api.getApiUrl()}/users/login`;
    this.registerUrl  = `${this.api.getApiUrl()}/users/register`;
  }

  // Login via POST /login 
  login(usernameOrEmail: string, password: string): Promise<User> {
    return this.api.post<{ data: User }>(this.loginUrl, { usernameOrEmail, password }).then(res => res.data);
  }

  getAllUsers(): Promise<User[]> {
    return this.api.get<User[]>(this.usersUrl);
  }

  getUserById(id: number): Promise<User> {
    return this.api.get<User>(`${this.usersUrl}/${id}`);
  }

  getUsersByNameAndLastName(fullName: string): Promise<User[]> {
    return this.api.get<User[]>(`${this.usersUrl}?nameAndLastName=${fullName}`);
  }

  addUser(user: User): Promise<User> {
    return this.api.post<User>(this.registerUrl, user);
  }

  updateUser(user: User): Promise<User> {
    return this.api.put<User>(`${this.usersUrl}/${user.userId}`, user);
  }

  deleteUser(id: number): Promise<void> {
    return this.api.delete<void>(`${this.usersUrl}/${id}`);
  }
}
