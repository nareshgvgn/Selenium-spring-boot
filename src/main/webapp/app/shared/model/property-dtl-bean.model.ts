import { IPropertySetMstBean } from 'app/shared/model/property-set-mst-bean.model';

export interface IPropertyDtlBean {
    id?: number;
    name?: string;
    value?: string;
    parentKey?: string;
    property?: IPropertySetMstBean;
}

export class PropertyDtlBean implements IPropertyDtlBean {
    constructor(
        public id?: number,
        public name?: string,
        public value?: string,
        public parentKey?: string,
        public property?: IPropertySetMstBean
    ) {}
}
