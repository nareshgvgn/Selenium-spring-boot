/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SelenimAutomationTestModule } from '../../../test.module';
import { PropertyMstBeanUpdateComponent } from 'app/entities/property-mst-bean/property-mst-bean-update.component';
import { PropertyMstBeanService } from 'app/entities/property-mst-bean/property-mst-bean.service';
import { PropertyMstBean } from 'app/shared/model/property-mst-bean.model';

describe('Component Tests', () => {
    describe('PropertyMstBean Management Update Component', () => {
        let comp: PropertyMstBeanUpdateComponent;
        let fixture: ComponentFixture<PropertyMstBeanUpdateComponent>;
        let service: PropertyMstBeanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SelenimAutomationTestModule],
                declarations: [PropertyMstBeanUpdateComponent]
            })
                .overrideTemplate(PropertyMstBeanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PropertyMstBeanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PropertyMstBeanService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PropertyMstBean(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.propertyMstBean = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PropertyMstBean();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.propertyMstBean = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
