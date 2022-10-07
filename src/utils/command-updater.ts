import {Client, SlashCommandBuilder} from "discord.js";
import {Config, TokenConfig} from "../types/types";

const {REST, Routes} = require('discord.js');

export class CommandUpdater {
    private clientId: string;
    private guildId: string
    private client: Client;
    private tokenConfig: TokenConfig;
    private slashCommands: SlashCommandBuilder[]

    constructor(client: Client, tokenConfig: TokenConfig, config: Config, slashCommands: SlashCommandBuilder[]) {
        this.client = client
        this.tokenConfig = tokenConfig
        this.slashCommands = slashCommands
        this.clientId = config.clientId
        this.guildId = config.guild
    }

    update() {
        let commands = [];
        for (const slashCommand of this.slashCommands) {
            commands.push(slashCommand.toJSON());
        }

        const rest = new REST({version: '10'}).setToken(this.tokenConfig.token);

        (async () => {
            try {
                console.log(`Started refreshing ${commands.length} application (/) commands.`);

                const data = await rest.put(
                    Routes.applicationGuildCommands(this.clientId, this.guildId),
                    {body: commands},
                );

                console.log(`Successfully reloaded ${data.length} application (/) commands.`);
            } catch (error) {
                console.error(error);
            }
        })();
    }
}