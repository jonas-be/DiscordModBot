export type Config = {
    clientId: string,
    guild: string,
    defaultRole: string,
    selectRoleCommand: string,
    selectRoleCommandDescription: string,
    selectRolePlaceholder: string,
    selectRoleContent: string,
    selectRoleItems: {
        label: string,
        description: string,
        value: string,
        roleId: string
    }[],

};

export type TokenConfig = {
    token: string;
};