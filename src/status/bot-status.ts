import {ActivityType, Client} from "discord.js";
import {Config} from "../types/config-types";

export class BotStatus {
    private client: Client;
    private config: Config;

    constructor(client: Client, config: Config) {
        this.client = client
        this.config = config
    }

    register() {
        const user = this.client.user;
        if (user !== null) {
            // @ts-ignore
            user.setStatus(this.config.status) // 'online' | 'idle' | 'dnd'
            user.setActivity(this.config.activity, {type: this.config.activityType})
            // name?: string; // url?: string; // type?: Exclude<ActivityType, ActivityType.Custom>; // shardId?: number | readonly number[];
        } else {
            console.log("can not display status")
        }

    }
}