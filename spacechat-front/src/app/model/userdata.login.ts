export class userLogin {
  name: string = '';
  email: string = '';
  senha: string = '';

  constructor();
  constructor(email: string, senha: string);
  constructor(email?: string, senha?: string) {
    this.email = email || ''; // Se não for passado, usa string vazia
    this.senha = senha || '';
  }
}