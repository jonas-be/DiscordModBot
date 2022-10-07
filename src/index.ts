import {JoinLeave} from "./join-leave";
import {Messages} from "./messages";
import {RoleSelector} from "./role-selector";
import {CommandUpdater} from "./utils/command-updater";

const { Client, GatewayIntentBits } = require('discord.js');
const tokenConfig  = require('../token-config.json');
const config  = require('../config.json');

const client = new Client({ intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages, GatewayIntentBits.GuildMembers, GatewayIntentBits.MessageContent, GatewayIntentBits.GuildIntegrations ] });

client.once('ready', (c: { user: { tag: any; }; }) => {
    console.log(`Ready! Logged in as ${c.user.tag}`);
});


new JoinLeave(client, config).register()
new Messages(client).register()

new RoleSelector(client, config).register()

new CommandUpdater(client, tokenConfig, config, [RoleSelector.command(config)]).update()


client.login(tokenConfig.token);