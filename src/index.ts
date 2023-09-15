import {JoinLeave} from "./listeners/join-leave";
import {Messages} from "./listeners/messages";
import {RoleToggler} from "./commands/role-toggler";
import {CommandUpdater} from "./utils/command-updater";
import {BotStatus} from "./status/bot-status";
import {log} from "./utils/log-util";
import {DeleteMessages} from "./commands/delete-messages";


const {Client, GatewayIntentBits} = require('discord.js');
const tokenConfig = require('../token-config.json');


export const config = require('../config.json');
export const client = new Client({intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages, GatewayIntentBits.GuildMembers, GatewayIntentBits.MessageContent, GatewayIntentBits.GuildIntegrations]});

client.once('ready', (c: { user: { tag: any; }; }) => {
    log(`Ready! Logged in as ${c.user.tag}`);

    new BotStatus().register()

});


new JoinLeave().register()
new Messages().register()

new RoleToggler().register()
new DeleteMessages().register()

new CommandUpdater(tokenConfig, [RoleToggler.command(), DeleteMessages.command()]).update()


client.login(tokenConfig.token);