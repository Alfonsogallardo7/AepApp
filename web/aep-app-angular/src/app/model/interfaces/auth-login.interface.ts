// To parse this data:
//
//   import { Convert, AuthLoginResponse } from "./file";
//
//   const authLoginResponse = Convert.toAuthLoginResponse(json);

export interface AuthLoginResponse {
    nombre:     string;
    apellidos:  string;
    email:      string;
    username:   string;
    fotoPerfil: string;
    rol:        string;
    token:      string;
}

// Converts JSON strings to/from your types
export class Convert {
    public static toAuthLoginResponse(json: string): AuthLoginResponse {
        return JSON.parse(json);
    }

    public static authLoginResponseToJson(value: AuthLoginResponse): string {
        return JSON.stringify(value);
    }
}
