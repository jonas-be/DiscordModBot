import {GuildMember, Interaction, SlashCommandBuilder} from "discord.js";
import {RoleManager} from "../utils/role-manager";
import {PermissionManager} from "../utils/permission-manager";
import {client, config} from "../index";
import {log} from "../utils/log-util";

const {ActionRowBuilder, SelectMenuBuilder} = require('discord.js');

export class RoleToggler {


    static command() {
        return new SlashCommandBuilder()
            .setName(config.toggleRole.command)
            .setDescription(config.toggleRole.commandDescription)
    }

    register() {
        client.on('interactionCreate', async (interaction: Interaction) => {

            if (interaction.guild != client.guilds.cache.get(config.guild)) return

            if (interaction.isChatInputCommand()) {
                if (interaction.commandName === config.toggleRole.command && interaction.member instanceof GuildMember) {
                    if (new PermissionManager(config.toggleRole.commandPermission).hasUserPermission(interaction.member)) {
                        const row = new ActionRowBuilder()
                            .addComponents(
                                new SelectMenuBuilder()
                                    .setCustomId('role-select')
                                    .setPlaceholder(config.toggleRole.selectorPlaceholder)
                                    .addOptions(
                                        config.toggleRole.roles
                                    ),
                            );

                        await interaction.reply({
                            content: config.toggleRole.messageContent,
                            components: [row]
                        });
                    } else {
                        await interaction.reply({
                            content: `Du hast nicht die nötigen Rechte!`,
                            ephemeral: true
                        })
                    }
                }
            }

            if (interaction.isSelectMenu()) {
                if (interaction.customId == 'role-select') {
                    let roleAdded = ""
                    //@ts-ignore
                    const roleManager = new RoleManager(interaction.member)
                    let selected = interaction.values
                    for (const roleToToggle of config.toggleRole.roles) {
                        if (roleToToggle.value == selected[0]) {
                            roleManager.addRole(roleManager.getRoleById(roleToToggle.roleId))
                            roleAdded = `<@&${roleToToggle.roleId}>`
                        } else {
                            roleManager.removeRole(roleManager.getRoleById(roleToToggle.roleId))
                        }
                    }
                    if (config.toggleRole.deleteDefaultRole) {
                        log("delete join role")
                        roleManager.removeRole(roleManager.getRoleById(config.defaultRole))
                    }
                    await interaction.reply({content: `Deine Rolle wurde zu ${roleAdded} geändert!`, ephemeral: true});
                }
            }
        });
    }
}