/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SelenimAutomationTestModule } from '../../../test.module';
import { PropertyMstBeanDetailComponent } from 'app/entities/property-mst-bean/property-mst-bean-detail.component';
import { PropertyMstBean } from 'app/shared/model/property-mst-bean.model';

describe('Component Tests', () => {
    describe('PropertyMstBean Management Detail Component', () => {
        let comp: PropertyMstBeanDetailComponent;
        let fixture: ComponentFixture<PropertyMstBeanDetailComponent>;
        const route = ({ data: of({ propertyMstBean: new PropertyMstBean(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SelenimAutomationTestModule],
                declarations: [PropertyMstBeanDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PropertyMstBeanDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PropertyMstBeanDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.propertyMstBean).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
