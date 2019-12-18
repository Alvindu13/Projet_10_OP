export class Book {

  id: bigint;
  available: boolean;

  constructor(public name: string, public author: string, public genre: string, public price: any) {
  }
}
