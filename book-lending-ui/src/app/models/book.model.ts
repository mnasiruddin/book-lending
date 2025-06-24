export interface Book {
  id: string;
  title: string;
  author: string;
  available: boolean;
  borrowedBy?: string;
  borrowedByUsername?: string;
}
