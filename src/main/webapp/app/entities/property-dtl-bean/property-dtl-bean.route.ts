import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { PropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';
import { PropertyDtlBeanService } from './property-dtl-bean.service';
import { PropertyDtlBeanComponent } from './property-dtl-bean.component';
import { PropertyDtlBeanDetailComponent } from './property-dtl-bean-detail.component';
import { PropertyDtlBeanUpdateComponent } from './property-dtl-bean-update.component';
import { PropertyDtlBeanDeletePopupComponent } from './property-dtl-bean-delete-dialog.component';
import { IPropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';

@Injectable({ providedIn: 'root' })
export class PropertyDtlBeanResolve implements Resolve<IPropertyDtlBean> {
    constructor(private service: PropertyDtlBeanService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((propertyDtlBean: HttpResponse<PropertyDtlBean>) => propertyDtlBean.body);
        }
        return Observable.of(new PropertyDtlBean());
    }
}

export const propertyDtlBeanRoute: Routes = [
    {
        path: 'property-dtl-bean',
        component: PropertyDtlBeanComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'selenimAutomationApp.propertyDtlBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'property-dtl-bean/:id/view',
        component: PropertyDtlBeanDetailComponent,
        resolve: {
            propertyDtlBean: PropertyDtlBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyDtlBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'property-dtl-bean/new',
        component: PropertyDtlBeanUpdateComponent,
        resolve: {
            propertyDtlBean: PropertyDtlBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyDtlBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'property-dtl-bean/:id/edit',
        component: PropertyDtlBeanUpdateComponent,
        resolve: {
            propertyDtlBean: PropertyDtlBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyDtlBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const propertyDtlBeanPopupRoute: Routes = [
    {
        path: 'property-dtl-bean/:id/delete',
        component: PropertyDtlBeanDeletePopupComponent,
        resolve: {
            propertyDtlBean: PropertyDtlBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyDtlBean.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
