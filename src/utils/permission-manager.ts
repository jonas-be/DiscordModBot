import {Permission} from "../types/types";
import {GuildMember, Role} from "discord.js";
import {RoleManager} from "./role-manager";

export class PermissionManager {
    private permission: Permission

    constructor(permission: Permission) {
        this.permission = permission
    }

    hasUserPermission(member: GuildMember): boolean {
        const roleManager = new RoleManager(member)
        for (const roleId of this.permission.roleIDs) {
            const role: Role | undefined = roleManager.getRoleById(roleId)
            if (role !== undefined) {
                if (roleManager.hasRole(role)) return true
            }
        }
        return this.permission.usersIDs.includes(member.id)
    }
}