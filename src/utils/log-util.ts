import {client, config} from "../index";
import {Channel} from "discord.js";

export function log(message: string) {
    console.log(message)
    const channel = client.channels.cache.find((channel: Channel) => channel.id === config.logChannel)
    if (channel !== undefined)
        channel.send(message)
}