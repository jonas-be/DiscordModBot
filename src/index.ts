import { GuildMember, Message, Role,} from "discord.js";

const { Client, GatewayIntentBits } = require('discord.js');
const { token } = require('../token-config.json');
const { config } = require('../config.json');

const client = new Client({ intents: [GatewayIntentBits.Guilds, GatewayIntentBits.GuildMessages, GatewayIntentBits.GuildMembers, GatewayIntentBits.MessageContent ] });

client.once('ready', (c: { user: { tag: any; }; }) => {
    console.log(`Ready! Logged in as ${c.user.tag}`);
});

client.on("guildMemberAdd", function(member: GuildMember){
    console.log(`a user joins a guild: ${member.user.tag}`);

    const role: Role | undefined = member.guild.roles.cache.find((role: Role) => role.id === "1027233143054405723");
    if (role != undefined) {
        member.roles.add(role);
        console.log(`added ${role.name} to ${member.user.tag}`)
    } else {
        console.log(`adding role to ${member.user.tag} failed!`)
    }
});

// Probably not working
client.on('guildMemberRemove', function(member: GuildMember) {
    console.log(`${member.user.tag} left the server :/`)
});

client.on("messageCreate", (message: Message) => {
    console.log(`${message.member?.user.tag}: ${message.content}`)
    if (message.content.startsWith("ping")) {
        message.channel.send(`${message.member?.user} pong!`);
    }
});

client.login(token);