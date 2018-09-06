import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IPropertyMstBean } from 'app/shared/model/property-mst-bean.model';
import { PropertyMstBeanService } from './property-mst-bean.service';

@Component({
    selector: 'jhi-property-mst-bean-update',
    templateUrl: './property-mst-bean-update.component.html'
})
export class PropertyMstBeanUpdateComponent implements OnInit {
    private _propertyMstBean: IPropertyMstBean;
    isSaving: boolean;

    constructor(private propertyMstBeanService: PropertyMstBeanService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ propertyMstBean }) => {
            this.propertyMstBean = propertyMstBean;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.propertyMstBean.id !== undefined) {
            this.subscribeToSaveResponse(this.propertyMstBeanService.update(this.propertyMstBean));
        } else {
            this.subscribeToSaveResponse(this.propertyMstBeanService.create(this.propertyMstBean));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPropertyMstBean>>) {
        result.subscribe((res: HttpResponse<IPropertyMstBean>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get propertyMstBean() {
        return this._propertyMstBean;
    }

    set propertyMstBean(propertyMstBean: IPropertyMstBean) {
        this._propertyMstBean = propertyMstBean;
    }
}
