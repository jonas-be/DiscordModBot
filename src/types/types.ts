export type Config = {
    clientId: string,
    guild: string,
    defaultRole: string,
    toggleRole: {
        command: string,
        commandDescription: string,
        selectorPlaceholder: string,
        messageContent: string,
        roles: {
            label: string,
            description: string,
            value: string,
            roleId: string
        }[],
    }
};

export type TokenConfig = {
    token: string;
};

export type Permission = {
    groups: string[],
    users: string[],
};