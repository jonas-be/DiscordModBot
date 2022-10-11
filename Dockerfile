FROM node:16-alpine

WORKDIR /app
COPY . .

RUN npm install
RUN npm run build
RUN pwd && ls -la && ls -la dist

CMD ["node", "dist/index.js"]

# TODO: Create docker-compose file
# mount config.json && token-sonfig.json on start up
#  sudo docker run
#       -v /home/jonas/Documents/Coding/backend/DiscordModBot/token-config.json:/app/token-config.json
#       -v /home/jonas/Documents/Coding/backend/DiscordModBot/config.json:/app/config.json
#       discord-mod-bot:latest