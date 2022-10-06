export type Config = {
    defaultRole: string;
    jobRoles: {
        label: string,
        description: string,
        value: string,
        roleId: string
    }[];
    clientId: string,
    guild: string,
};

export type TokenConfig = {
    token: string;
};