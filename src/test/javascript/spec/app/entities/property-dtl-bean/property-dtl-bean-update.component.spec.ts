/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { SelenimAutomationTestModule } from '../../../test.module';
import { PropertyDtlBeanUpdateComponent } from 'app/entities/property-dtl-bean/property-dtl-bean-update.component';
import { PropertyDtlBeanService } from 'app/entities/property-dtl-bean/property-dtl-bean.service';
import { PropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';

describe('Component Tests', () => {
    describe('PropertyDtlBean Management Update Component', () => {
        let comp: PropertyDtlBeanUpdateComponent;
        let fixture: ComponentFixture<PropertyDtlBeanUpdateComponent>;
        let service: PropertyDtlBeanService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SelenimAutomationTestModule],
                declarations: [PropertyDtlBeanUpdateComponent]
            })
                .overrideTemplate(PropertyDtlBeanUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(PropertyDtlBeanUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PropertyDtlBeanService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new PropertyDtlBean(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.propertyDtlBean = entity;
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
                    const entity = new PropertyDtlBean();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.propertyDtlBean = entity;
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
