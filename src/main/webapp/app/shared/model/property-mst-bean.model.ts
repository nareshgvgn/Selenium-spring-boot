export interface IPropertyMstBean {
    id?: number;
    name?: string;
    modifiedBy?: string;
}

export class PropertyMstBean implements IPropertyMstBean {
    constructor(public id?: number, public name?: string, public modifiedBy?: string) {}
}
