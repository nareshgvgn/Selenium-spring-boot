import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPropertyMstBean } from 'app/shared/model/property-mst-bean.model';

@Component({
    selector: 'jhi-property-mst-bean-detail',
    templateUrl: './property-mst-bean-detail.component.html'
})
export class PropertyMstBeanDetailComponent implements OnInit {
    propertyMstBean: IPropertyMstBean;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ propertyMstBean }) => {
            this.propertyMstBean = propertyMstBean;
        });
    }

    previousState() {
        window.history.back();
    }
}
