import {Client,  Message} from "discord.js";

export class Messages {
    private client: Client;

    constructor(client: Client) {
        this.client = client
    }

    register() {
        this.client.on("messageCreate", async (message: Message) => {
            console.log(`${message.member?.user.tag}: ${message.content}`)
            if (message.content.startsWith("ping")) {
                message.channel.send(`${message.member?.user} pong!`);
            }
        });
    }
}