/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SelenimAutomationTestModule } from '../../../test.module';
import { PropertyDtlBeanDetailComponent } from 'app/entities/property-dtl-bean/property-dtl-bean-detail.component';
import { PropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';

describe('Component Tests', () => {
    describe('PropertyDtlBean Management Detail Component', () => {
        let comp: PropertyDtlBeanDetailComponent;
        let fixture: ComponentFixture<PropertyDtlBeanDetailComponent>;
        const route = ({ data: of({ propertyDtlBean: new PropertyDtlBean(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SelenimAutomationTestModule],
                declarations: [PropertyDtlBeanDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PropertyDtlBeanDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PropertyDtlBeanDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.propertyDtlBean).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
