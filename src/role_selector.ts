import {Client, GuildMember, Role, SlashCommandBuilder} from "discord.js";
import {Config} from "./types/types";

const {ActionRowBuilder, SelectMenuBuilder} = require('discord.js');

export class RoleSelector {
    private client: Client;
    private config: Config;

    constructor(client: Client, config: Config) {
        this.client = client
        this.config = config
    }

    static command() {
        return new SlashCommandBuilder()
            .setName('jobrole')
            .setDescription('Replies with role Selector')
    }

    registerSelector() {
        this.client.on('interactionCreate', async (interaction) => {

            if (interaction.guild != this.client.guilds.cache.get(this.config.guild)) return

            if (interaction.isChatInputCommand()) {
                if (interaction.commandName === 'jobrole') {
                    const row = new ActionRowBuilder()
                        .addComponents(
                            new SelectMenuBuilder()
                                .setCustomId('select')
                                .setPlaceholder('Wähle dein Beruf aus')
                                .addOptions(
                                    this.config.jobRoles
                                ),
                        );

                    await interaction.reply({content: 'Deine Rolle:', components: [row]});
                }
            }

            if (interaction.isSelectMenu()) {
                if (interaction.customId == 'select') {
                    console.log(interaction.values);
                    let selected = interaction.values
                    let roleId = this.config.jobRoles.filter(
                        i => { if (i.value == selected[0]) return i.roleId })[0];
                    const role: Role | undefined = interaction.guild.roles.cache.find((role: Role) => role.id === roleId.roleId);
                    if (interaction.member instanceof GuildMember && role != undefined) {
                        const member: GuildMember = interaction.member
                        member.roles.add(role);
                        console.log(`added ${role.name} to ${member.user.username}`)
                    } else {
                        console.log("failed adding role")
                    }
                }
            }
        });
    }
}