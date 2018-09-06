import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { PropertyMstBean } from 'app/shared/model/property-mst-bean.model';
import { PropertyMstBeanService } from './property-mst-bean.service';
import { PropertyMstBeanComponent } from './property-mst-bean.component';
import { PropertyMstBeanDetailComponent } from './property-mst-bean-detail.component';
import { PropertyMstBeanUpdateComponent } from './property-mst-bean-update.component';
import { PropertyMstBeanDeletePopupComponent } from './property-mst-bean-delete-dialog.component';
import { IPropertyMstBean } from 'app/shared/model/property-mst-bean.model';

@Injectable({ providedIn: 'root' })
export class PropertyMstBeanResolve implements Resolve<IPropertyMstBean> {
    constructor(private service: PropertyMstBeanService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((propertyMstBean: HttpResponse<PropertyMstBean>) => propertyMstBean.body);
        }
        return Observable.of(new PropertyMstBean());
    }
}

export const propertyMstBeanRoute: Routes = [
    {
        path: 'property-mst-bean',
        component: PropertyMstBeanComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'selenimAutomationApp.propertyMstBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'property-mst-bean/:id/view',
        component: PropertyMstBeanDetailComponent,
        resolve: {
            propertyMstBean: PropertyMstBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyMstBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'property-mst-bean/new',
        component: PropertyMstBeanUpdateComponent,
        resolve: {
            propertyMstBean: PropertyMstBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyMstBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'property-mst-bean/:id/edit',
        component: PropertyMstBeanUpdateComponent,
        resolve: {
            propertyMstBean: PropertyMstBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyMstBean.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const propertyMstBeanPopupRoute: Routes = [
    {
        path: 'property-mst-bean/:id/delete',
        component: PropertyMstBeanDeletePopupComponent,
        resolve: {
            propertyMstBean: PropertyMstBeanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'selenimAutomationApp.propertyMstBean.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
