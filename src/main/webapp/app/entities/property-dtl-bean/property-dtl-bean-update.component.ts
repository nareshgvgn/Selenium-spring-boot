import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IPropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';
import { PropertyDtlBeanService } from './property-dtl-bean.service';
import { IPropertySetMstBean } from 'app/shared/model/property-set-mst-bean.model';
import { PropertySetMstBeanService } from 'app/entities/property-set-mst-bean';

@Component({
    selector: 'jhi-property-dtl-bean-update',
    templateUrl: './property-dtl-bean-update.component.html'
})
export class PropertyDtlBeanUpdateComponent implements OnInit {
    private _propertyDtlBean: IPropertyDtlBean;
    isSaving: boolean;

    propertysetmstbeans: IPropertySetMstBean[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private propertyDtlBeanService: PropertyDtlBeanService,
        private propertySetMstBeanService: PropertySetMstBeanService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ propertyDtlBean }) => {
            this.propertyDtlBean = propertyDtlBean;
        });
        this.propertySetMstBeanService.query().subscribe(
            (res: HttpResponse<IPropertySetMstBean[]>) => {
                this.propertysetmstbeans = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.propertyDtlBean.id !== undefined) {
            this.subscribeToSaveResponse(this.propertyDtlBeanService.update(this.propertyDtlBean));
        } else {
            this.subscribeToSaveResponse(this.propertyDtlBeanService.create(this.propertyDtlBean));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPropertyDtlBean>>) {
        result.subscribe((res: HttpResponse<IPropertyDtlBean>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackPropertySetMstBeanById(index: number, item: IPropertySetMstBean) {
        return item.id;
    }
    get propertyDtlBean() {
        return this._propertyDtlBean;
    }

    set propertyDtlBean(propertyDtlBean: IPropertyDtlBean) {
        this._propertyDtlBean = propertyDtlBean;
    }
}
