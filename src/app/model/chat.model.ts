export class ChatModel {
    name: string = localStorage.getItem('userName') || 'anonymous';
    message: string = '';
}