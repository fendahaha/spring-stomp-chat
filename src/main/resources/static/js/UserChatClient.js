class UserChatClient {
    stompClient;
    connected = false;

    constructor(brokerURL, roomId, user, onMsg) {
        this.user = user;
        this.roomId = roomId;
        const self = this;
        let stompClient = new StompJs.Client({
            brokerURL: brokerURL,
            connectionTimeout: 30 * 1000,
            reconnectDelay: 5 * 1000,
            connectHeaders: {
                roomId: roomId
            },
            onConnect: (frame) => {
                self.connected = true;
                stompClient.subscribe(`/topic/room/${roomId}`, (greeting) => {
                    let chatMsg = JSON.parse(greeting.body);
                    onMsg && onMsg(chatMsg);
                });
            },
            onDisconnect: (frame) => {
                self.connected = false
            },
            onWebSocketError: (error) => {
                console.error('Error with websocket', error);
            },
            onStompError: (frame) => {
                console.error('Broker reported error: ' + frame.headers['message']);
                console.error('Additional details: ' + frame.body);
            },
            debug: (frame) => console.log(frame)
        });
        this.stompClient = stompClient;
    }

    connect() {
        this.stompClient.activate();
    }

    disconnect() {
        this.stompClient.deactivate();
    }

    sendMsg(msg) {
        if (this.connected) {
            this.stompClient.publish({
                destination: `/app/room/${this.roomId}`,
                body: JSON.stringify({
                    'user': this.user,
                    'text': msg
                })
            });
            return true
        }
    }

    static generateRandomUsername() {
        const getRandomElement = (arr) => arr[Math.floor(Math.random() * arr.length)];
        const adjectives = ["Cool", "Super", "Fast", "Smart", "Brave", "Clever"];
        const nouns = ["Tiger", "Dragon", "Eagle", "Lion", "Panther", "Wolf"];
        const numbers = Math.floor(Math.random() * 1000);
        return `${getRandomElement(adjectives)}${getRandomElement(nouns)}${numbers}`;
    }
}