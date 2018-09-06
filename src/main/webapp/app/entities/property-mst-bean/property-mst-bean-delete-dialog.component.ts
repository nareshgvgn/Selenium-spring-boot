import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPropertyMstBean } from 'app/shared/model/property-mst-bean.model';
import { PropertyMstBeanService } from './property-mst-bean.service';

@Component({
    selector: 'jhi-property-mst-bean-delete-dialog',
    templateUrl: './property-mst-bean-delete-dialog.component.html'
})
export class PropertyMstBeanDeleteDialogComponent {
    propertyMstBean: IPropertyMstBean;

    constructor(
        private propertyMstBeanService: PropertyMstBeanService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.propertyMstBeanService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'propertyMstBeanListModification',
                content: 'Deleted an propertyMstBean'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-property-mst-bean-delete-popup',
    template: ''
})
export class PropertyMstBeanDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ propertyMstBean }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PropertyMstBeanDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.propertyMstBean = propertyMstBean;
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
