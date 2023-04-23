import { Role } from "./role";

export interface User {
  id: number;
  username: string;
  firstName: string;
  lastName: string;
  role: Role;
}
