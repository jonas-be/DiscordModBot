import {GuildMember, Interaction, SlashCommandBuilder, SlashCommandIntegerOption} from "discord.js";
import {PermissionManager} from "../utils/permission-manager";
import {client, config} from "../index";
import {log} from "../utils/log-util";

const {ActionRowBuilder, SelectMenuBuilder} = require('discord.js');

export class DeleteMessages {

    static command() {
        return new SlashCommandBuilder()
            .setName(config.deleteMessages.command)
            .setDescription(config.deleteMessages.commandDescription)
            .addIntegerOption(new SlashCommandIntegerOption()
                .setName(config.deleteMessages.amountOption)
                .setDescription(config.deleteMessages.amountOptionDescription)
                .setRequired(true)
            ).toJSON()
    }


    register() {
        client.on('interactionCreate', async (interaction: Interaction) => {

            if (interaction.guild != client.guilds.cache.get(config.guild)) return

            if (interaction.isChatInputCommand()) {
                if (interaction.commandName === config.deleteMessages.command && interaction.member instanceof GuildMember) {
                    if (new PermissionManager(config.deleteMessages.commandPermission).hasUserPermission(interaction.member)) {
                        //@ts-ignore
                        const amount = interaction.options.get(config.deleteMessages.amountOption).value;
                        //@ts-ignore
                        const messages = await interaction.channel.messages.fetch({
                            limit: amount
                        })
                        interaction.reply({content: '🗑️ ' + amount + ' ...', ephemeral: true});
                        for (const message of messages.values()) {
                            await message.delete()
                            //@ts-ignore
                            log(`**${interaction.member.displayName}** 🗑️ *~~${message.member.displayName}~~* \`\`\`${message.content}\`\`\``)
                        }
                    } else {
                        await interaction.reply({
                            content: `Du hast nicht die nötigen Rechte!`,
                            ephemeral: true
                        })
                    }
                }
            }

        });
    }
}
