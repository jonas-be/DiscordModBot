import {Client, Role, SlashCommandBuilder} from "discord.js";
import {Config} from "./types/types";
import {RoleManager} from "./utils/role-manager";

const {ActionRowBuilder, SelectMenuBuilder} = require('discord.js');

export class RoleSelector {
    private client: Client;
    private config: Config;

    constructor(client: Client, config: Config) {
        this.client = client
        this.config = config
    }

    static command(config: Config) {
        return new SlashCommandBuilder()
            .setName(config.toggleRole.command)
            .setDescription(config.toggleRole.commandDescription)
    }

    register() {
        this.client.on('interactionCreate', async (interaction) => {

            if (interaction.guild != this.client.guilds.cache.get(this.config.guild)) return

            if (interaction.isChatInputCommand()) {
                if (interaction.commandName === this.config.toggleRole.command) {
                    const row = new ActionRowBuilder()
                        .addComponents(
                            new SelectMenuBuilder()
                                .setCustomId('role-select')
                                .setPlaceholder(this.config.toggleRole.selectorPlaceholder)
                                .addOptions(
                                    this.config.toggleRole.roles
                                ),
                        );

                    await interaction.reply({content: this.config.toggleRole.messageContent, components: [row]});
                }
            }

            if (interaction.isSelectMenu()) {
                if (interaction.customId == 'role-select') {
                    let roleAdded = ""
                    //@ts-ignore
                    const roleManager = new RoleManager(interaction.member)
                    let selected = interaction.values
                    for (const roleToToggle of this.config.toggleRole.roles) {
                        if (roleToToggle.value == selected[0]) {
                            roleManager.addRole(roleManager.getRoleById(roleToToggle.roleId))
                            roleAdded = `<@&${roleToToggle.roleId}>`
                        } else {
                            roleManager.removeRole(roleManager.getRoleById(roleToToggle.roleId))
                        }
                    }

                    await interaction.reply({ content: `Deine Rolle wurde zu ${roleAdded} geändert!`, ephemeral: true });
                }
            }
        });
    }
}