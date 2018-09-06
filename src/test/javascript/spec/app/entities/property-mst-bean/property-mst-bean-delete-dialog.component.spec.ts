/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SelenimAutomationTestModule } from '../../../test.module';
import { PropertyMstBeanDeleteDialogComponent } from 'app/entities/property-mst-bean/property-mst-bean-delete-dialog.component';
import { PropertyMstBeanService } from 'app/entities/property-mst-bean/property-mst-bean.service';

describe('Component Tests', () => {
    describe('PropertyMstBean Management Delete Component', () => {
        let comp: PropertyMstBeanDeleteDialogComponent;
        let fixture: ComponentFixture<PropertyMstBeanDeleteDialogComponent>;
        let service: PropertyMstBeanService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [SelenimAutomationTestModule],
                declarations: [PropertyMstBeanDeleteDialogComponent]
            })
                .overrideTemplate(PropertyMstBeanDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PropertyMstBeanDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(PropertyMstBeanService);
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
