import {Guild, GuildMember, Role} from "discord.js";

export class RoleManager {
    private guild: Guild
    private member: GuildMember

    constructor(guild: Guild, member: GuildMember) {
        this.guild = guild
        this.member = member
    }

    addRole(role: Role | undefined) {
        if (role === undefined) return
        this.member.roles.add(role)
        console.log(`+++ ${role.name} => ${this.member.user.username}`)
    }

    removeRole(role: Role | undefined) {
        if (role === undefined) return
        this.member.roles.remove(role)
        console.log(`--- ${role.name} => ${this.member.user.username}`)
    }

    getRoleById(id: string): Role | undefined {
        const role = this.guild.roles.cache.find((role: Role) => role.id === id);
        if (role === undefined)
            console.log(`ERR: No Role found by id: ${id}`)
        return role
    }

    getRoleByName(name: string): Role | undefined {
        const role =this.guild.roles.cache.find((role: Role) => role.name === name);
        if (role === undefined)
            console.log(`ERR: No Role found by name: ${name}`)
        return role
    }


}