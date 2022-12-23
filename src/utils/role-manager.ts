import {Guild, GuildMember, Role} from "discord.js";
import {log} from "../utils/log-util";


export class RoleManager {
    private guild: Guild
    private member: GuildMember

    constructor(member: GuildMember) {
        this.guild = member.guild
        this.member = member
    }

    addRole(role: Role | undefined) {
        if (role === undefined) return
        this.member.roles.add(role)
            .then(r => log(`+++ ${role.name} => ${r.user.username}`))
    }

    removeRole(role: Role | undefined) {
        if (role === undefined) return
        this.member.roles.remove(role)
            .then(r => log(`--- ${role.name} => ${r.user.username}`))
    }

    getRoleById(id: string): Role | undefined {
        const role = this.guild.roles.cache.find((role: Role) => role.id === id);
        if (role === undefined)
            log(`ERR: No Role found by id: ${id}`)
        return role
    }

    getRoleByName(name: string): Role | undefined {
        const role = this.guild.roles.cache.find((role: Role) => role.name === name);
        if (role === undefined)
            log(`ERR: No Role found by name: ${name}`)
        return role
    }

    hasRole(role: Role): boolean {
        return this.member.roles.cache.some(roleFromCache => roleFromCache == role)
    }
}