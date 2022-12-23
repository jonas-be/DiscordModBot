import {GuildMember} from "discord.js";
import {RoleManager} from "../utils/role-manager";
import {client, config} from "../index";
import {log} from "../utils/log-util";

export class JoinLeave {

    register() {
        client.on("guildMemberAdd", async (member: GuildMember) => {
            log(`a user joins a guild: ${member.user.tag}`);

            const roleManager = new RoleManager(member)

            roleManager.addRole(roleManager.getRoleById(config.defaultRole))
        });
    }
}