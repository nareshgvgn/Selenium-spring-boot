/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SelenimAutomationTestModule } from '../../../test.module';
import { PropertyDtlBeanDeleteDialogComponent } from 'app/entities/property-dtl-bean/property-dtl-bean-delete-dialog.component';
import { PropertyDtlBeanService } from 'app/entities/property-dtl-bean/property-dtl-bean.service';

describe('Component Tests', () => {
    describe('PropertyDtlBean Management Delete Component', () => {
        let comp: PropertyDtlBeanDeleteDialogComponent;
        let fixture: ComponentFixture<PropertyDtlBeanDeleteDialogComponent>;
        let service: PropertyDtlBeanService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SelenimAutomationTestModule],
                declarations: [PropertyDtlBeanDeleteDialogComponent]
            })
                .overrideTemplate(PropertyDtlBeanDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PropertyDtlBeanDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PropertyDtlBeanService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
