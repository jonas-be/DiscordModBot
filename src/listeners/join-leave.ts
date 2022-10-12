import {Client, GuildMember, Role} from "discord.js";
import {Config} from "../types/types";
import {RoleManager} from "../utils/role-manager";

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

            const roleManager = new RoleManager(member)

            roleManager.addRole(roleManager.getRoleById(this.config.defaultRole))
        });
    }
}