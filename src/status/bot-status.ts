import {client, config} from "../index";
import {log} from "../utils/log-util";


export class BotStatus {

    register() {
        const user = client.user;
        if (user !== null) {
            // @ts-ignore
            user.setStatus(config.status) // 'online' | 'idle' | 'dnd'
            user.setActivity(config.activity, {type: config.activityType})
            // name?: string; // url?: string; // type?: Exclude<ActivityType, ActivityType.Custom>; // shardId?: number | readonly number[];
        } else {
            log("can not display status")
        }

    }
}