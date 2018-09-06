import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPropertyDtlBean } from 'app/shared/model/property-dtl-bean.model';
import { PropertyDtlBeanService } from './property-dtl-bean.service';

@Component({
    selector: 'jhi-property-dtl-bean-delete-dialog',
    templateUrl: './property-dtl-bean-delete-dialog.component.html'
})
export class PropertyDtlBeanDeleteDialogComponent {
    propertyDtlBean: IPropertyDtlBean;

    constructor(
        private propertyDtlBeanService: PropertyDtlBeanService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.propertyDtlBeanService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'propertyDtlBeanListModification',
                content: 'Deleted an propertyDtlBean'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-property-dtl-bean-delete-popup',
    template: ''
})
export class PropertyDtlBeanDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ propertyDtlBean }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PropertyDtlBeanDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.propertyDtlBean = propertyDtlBean;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
