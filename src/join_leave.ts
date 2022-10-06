import {Client, GuildMember, Role} from "discord.js";
import {Config} from "./types/types";

export class JoinLeave {
    private client: Client;
    private config: Config;

    constructor(client: Client, config: Config) {
        this.client = client
        this.config = config
    }

    register() {
        this.client.on("guildMemberAdd", async (member: GuildMember) =>{
            console.log(`a user joins a guild: ${member.user.tag}`);

            const role: Role | undefined = member.guild.roles.cache.find((role: Role) => role.id === this.config.defaultRole);

            if (role != undefined) {
                member.roles.add(role);
                console.log(`added ${role.name} to ${member.user.tag}`)
            } else {
                console.log(`adding role to ${member.user.tag} failed!`)
            }
        });
    }
}