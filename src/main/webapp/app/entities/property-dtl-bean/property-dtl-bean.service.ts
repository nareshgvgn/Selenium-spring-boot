import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';

type EntityResponseType = HttpResponse<IPropertyDtlBean>;
type EntityArrayResponseType = HttpResponse<IPropertyDtlBean[]>;

@Injectable({ providedIn: 'root' })
export class PropertyDtlBeanService {
    private resourceUrl = SERVER_API_URL + 'api/property-dtl-beans';

    constructor(private http: HttpClient) {}

    create(propertyDtlBean: IPropertyDtlBean): Observable<EntityResponseType> {
        return this.http.post<IPropertyDtlBean>(this.resourceUrl, propertyDtlBean, { observe: 'response' });
    }

    update(propertyDtlBean: IPropertyDtlBean): Observable<EntityResponseType> {
        return this.http.put<IPropertyDtlBean>(this.resourceUrl, propertyDtlBean, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPropertyDtlBean>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPropertyDtlBean[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
