FROM node:latest

WORKDIR /app
COPY . .

RUN npm install

CMD ["npm", "run", "start"]

# mount config.json && token-sonfig.json on start up