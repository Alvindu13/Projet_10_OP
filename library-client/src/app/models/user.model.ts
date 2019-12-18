export class User {

  id: number;
  roles:any;


  constructor(public username:string, public password: string, public confirmedPassword:string) {
  }
}
