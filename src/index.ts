import {JoinLeave} from "./join_leave";
import {Messages} from "./messages";
import {RoleSelector} from "./role_selector";
import {CommandUpdater} from "./utils/command_updater";

const { Client, GatewayIntentBits } = require('discord.js');
const { tokenConfig } = require('../token-config.json');
const  config  = require('../config.json');

const client = new Client({ intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages, GatewayIntentBits.GuildMembers, GatewayIntentBits.MessageContent, GatewayIntentBits.GuildIntegrations ] });

client.once('ready', (c: { user: { tag: any; }; }) => {
    console.log(`Ready! Logged in as ${c.user.tag}`);
});


new JoinLeave(client, config).register()
new Messages(client).register()

new RoleSelector(client, config).registerSelector()

new CommandUpdater(client, tokenConfig, config, [RoleSelector.command()]).update()


client.login(tokenConfig);