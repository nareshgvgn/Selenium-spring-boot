import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SelenimAutomationSharedModule } from 'app/shared';
import {
    PropertyMstBeanComponent,
    PropertyMstBeanDetailComponent,
    PropertyMstBeanUpdateComponent,
    PropertyMstBeanDeletePopupComponent,
    PropertyMstBeanDeleteDialogComponent,
    propertyMstBeanRoute,
    propertyMstBeanPopupRoute
} from './';

const ENTITY_STATES = [...propertyMstBeanRoute, ...propertyMstBeanPopupRoute];

@NgModule({
    imports: [SelenimAutomationSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        PropertyMstBeanComponent,
        PropertyMstBeanDetailComponent,
        PropertyMstBeanUpdateComponent,
        PropertyMstBeanDeleteDialogComponent,
        PropertyMstBeanDeletePopupComponent
    ],
    entryComponents: [
        PropertyMstBeanComponent,
        PropertyMstBeanUpdateComponent,
        PropertyMstBeanDeleteDialogComponent,
        PropertyMstBeanDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SelenimAutomationPropertyMstBeanModule {}
