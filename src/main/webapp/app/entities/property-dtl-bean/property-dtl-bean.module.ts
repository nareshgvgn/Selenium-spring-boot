import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SelenimAutomationSharedModule } from 'app/shared';
import {
    PropertyDtlBeanComponent,
    PropertyDtlBeanDetailComponent,
    PropertyDtlBeanUpdateComponent,
    PropertyDtlBeanDeletePopupComponent,
    PropertyDtlBeanDeleteDialogComponent,
    propertyDtlBeanRoute,
    propertyDtlBeanPopupRoute
} from './';

const ENTITY_STATES = [...propertyDtlBeanRoute, ...propertyDtlBeanPopupRoute];

@NgModule({
    imports: [SelenimAutomationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PropertyDtlBeanComponent,
        PropertyDtlBeanDetailComponent,
        PropertyDtlBeanUpdateComponent,
        PropertyDtlBeanDeleteDialogComponent,
        PropertyDtlBeanDeletePopupComponent
    ],
    entryComponents: [
        PropertyDtlBeanComponent,
        PropertyDtlBeanUpdateComponent,
        PropertyDtlBeanDeleteDialogComponent,
        PropertyDtlBeanDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SelenimAutomationPropertyDtlBeanModule {}
