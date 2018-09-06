import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';

@Component({
    selector: 'jhi-property-dtl-bean-detail',
    templateUrl: './property-dtl-bean-detail.component.html'
})
export class PropertyDtlBeanDetailComponent implements OnInit {
    propertyDtlBean: IPropertyDtlBean;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ propertyDtlBean }) => {
            this.propertyDtlBean = propertyDtlBean;
        });
    }

    previousState() {
        window.history.back();
    }
}
