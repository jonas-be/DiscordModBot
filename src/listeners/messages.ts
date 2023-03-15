import {Message} from "discord.js";
import {client} from "../index";
import {log} from "../utils/log-util";


export class Messages {

    register() {
        client.on("messageCreate", async (message: Message) => {
            if (message.content.startsWith("ping")) {
                message.channel.send(`${message.member?.user} pong!`);
            }
        });
    }
}