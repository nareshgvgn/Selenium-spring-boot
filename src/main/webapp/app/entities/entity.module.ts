import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { SelenimAutomationProjectModule } from './project/project.module';
import { SelenimAutomationPropertyMstBeanModule } from './property-mst-bean/property-mst-bean.module';
import { SelenimAutomationPropertyDtlBeanModule } from './property-dtl-bean/property-dtl-bean.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        SelenimAutomationProjectModule,
        SelenimAutomationPropertyMstBeanModule,
        SelenimAutomationPropertyDtlBeanModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class SelenimAutomationEntityModule {}
