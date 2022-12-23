import {SlashCommandBuilder} from "discord.js";
import {TokenConfig} from "../types/config-types";
import {config} from "../index";
import {log} from "../utils/log-util";


const {REST, Routes} = require('discord.js');

export class CommandUpdater {
    private clientId: string;
    private guildId: string
    private tokenConfig: TokenConfig;
    private slashCommands: SlashCommandBuilder[]

    constructor(tokenConfig: TokenConfig, slashCommands: SlashCommandBuilder[]) {
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
                log(`Started refreshing ${commands.length} application (/) commands.`);

                const data = await rest.put(
                    Routes.applicationGuildCommands(this.clientId, this.guildId),
                    {body: commands},
                );

                log(`Successfully reloaded ${data.length} application (/) commands.`);
            } catch (error) {
                console.error(error);
            }
        })();
    }
}