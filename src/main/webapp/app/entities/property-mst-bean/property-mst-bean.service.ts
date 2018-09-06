import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IPropertyMstBean } from 'app/shared/model/property-mst-bean.model';

type EntityResponseType = HttpResponse<IPropertyMstBean>;
type EntityArrayResponseType = HttpResponse<IPropertyMstBean[]>;

@Injectable({ providedIn: 'root' })
export class PropertyMstBeanService {
    private resourceUrl = SERVER_API_URL + 'api/property-mst-beans';

    constructor(private http: HttpClient) {}

    create(propertyMstBean: IPropertyMstBean): Observable<EntityResponseType> {
        return this.http.post<IPropertyMstBean>(this.resourceUrl, propertyMstBean, { observe: 'response' });
    }

    update(propertyMstBean: IPropertyMstBean): Observable<EntityResponseType> {
        return this.http.put<IPropertyMstBean>(this.resourceUrl, propertyMstBean, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IPropertyMstBean>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IPropertyMstBean[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
