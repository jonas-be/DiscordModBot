import {JoinLeave} from "./listeners/join-leave";
import {Messages} from "./listeners/messages";
import {RoleToggler} from "./commands/role-toggler";
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

new RoleToggler(client, config).register()

new CommandUpdater(client, tokenConfig, config, [RoleToggler.command(config)]).update()


client.login(tokenConfig.token);