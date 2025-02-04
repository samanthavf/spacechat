import { userData } from "./userdata.model";

export class ChatModel{
    user:string='';
    message:string='';

constructor(getName:string, message:string){
    this.user = getName;
    this.message = message;
}

}