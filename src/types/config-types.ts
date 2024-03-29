export type Config = {
    clientId: string,
    status: string,
    activity: string,
    activityType: number,
    guild: string,
    logChannel: string,
    defaultRole: string,
    toggleRole: {
        command: string,
        commandDescription: string,
        commandPermission: Permission,
        deleteDefaultRole: boolean,
        selectorPlaceholder: string,
        messageContent: string,
        roles: {
            label: string,
            description: string,
            value: string,
            roleId: string
        }[],
    }
    deleteCommand: {
        command: string,
        commandDescription: string,
        commandPermission: Permission,
        amountOption: string,
        amountOptionDescription: string,
    }
};

export type TokenConfig = {
    token: string;
};

export type Permission = {
    roleIDs: string[],
    usersIDs: string[],
};