# DiscordModBot
Discord Bot for modding and much more.

## SetUp

Create a file named `token-config.json` and copy and paste the following code in to it.
You need to insert your token in this config.
```json
{
  "token": "your-token"
}
```
You also need a config file.

**Example `config.json`:**
```json
{
  "clientId": "914132354212175915", // ID from bot
  "status": "online", // online, idle, dnd 
  "activity": "on Kubernetes",
  "activityType": 0, 
        // 0 = Playing {game}
        // 1 = Streaming {details} 
        // 2 = Listening to {name}
        // 3 = Watching {details}
        // 5 = Competing in {name}
  "guild": "843816498049056828", // guildID
  "defaultRole": "914137388362641419", // roleID from the default role
  "toggleRole": {
    "command": "sortinghat",
    "commandDescription": "Speaking hat",
    "commandPermission": {
      "roleIDs": ["914137059495653428"], // You can add more than one, example ["FIRST_ID", "SECOND_ID"] and so on...
      "usersIDs": ["398876120696619008"] // You can add more than one
    },
    "deleteDefaultRole": true, // on role toggle lose default role
    "selectorPlaceholder": "Wähle dein Haus",
    "messageContent": "Zum Freischalten müsst ihr eine dieser Rollen wählen.\nWenn ihr nicht wisst was damit gemeint ist, dann könnt ihr auch von der Farbe her euch entscheiden.\nWelchen Rang ihr wählt spielt keine Rolle und ist theoretisch nur zum Spaß.",
    "roles": [
      {
        "label": "\uD83D\uDFE5 -Gryffindor",
        "description": "Ein Haus in Hogwarts",
        "value": "gryffindor",
        "roleId": "949751202931941447"
      },
      {
        "label": "\uD83D\uDFE8 -Hufflepuff",
        "description": "Ein Haus in Hogwarts",
        "value": "hufflepuff",
        "roleId": "949751287296167967"
      },
      {
        "label": "\uD83D\uDFE6 -Ravenclaw",
        "description": "Ein Haus in Hogwarts",
        "value": "ravenclaw",
        "roleId": "949751323870494751"
      },
      {
        "label": "\uD83D\uDFE9 -Slytherin",
        "description": "Ein Haus in Hogwarts",
        "value": "slytherin",
        "roleId": "949751321538490388"
      }
    ]
  }
}
```