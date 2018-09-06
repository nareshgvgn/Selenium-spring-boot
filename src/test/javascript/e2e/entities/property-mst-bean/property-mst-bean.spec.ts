import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PropertyMstBeanComponentsPage, PropertyMstBeanUpdatePage } from './property-mst-bean.page-object';

describe('PropertyMstBean e2e test', () => {
    let navBarPage: NavBarPage;
    let propertyMstBeanUpdatePage: PropertyMstBeanUpdatePage;
    let propertyMstBeanComponentsPage: PropertyMstBeanComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PropertyMstBeans', () => {
        navBarPage.goToEntity('property-mst-bean');
        propertyMstBeanComponentsPage = new PropertyMstBeanComponentsPage();
        expect(propertyMstBeanComponentsPage.getTitle()).toMatch(/selenimAutomationApp.propertyMstBean.home.title/);
    });

    it('should load create PropertyMstBean page', () => {
        propertyMstBeanComponentsPage.clickOnCreateButton();
        propertyMstBeanUpdatePage = new PropertyMstBeanUpdatePage();
        expect(propertyMstBeanUpdatePage.getPageTitle()).toMatch(/selenimAutomationApp.propertyMstBean.home.createOrEditLabel/);
        propertyMstBeanUpdatePage.cancel();
    });

    it('should create and save PropertyMstBeans', () => {
        propertyMstBeanComponentsPage.clickOnCreateButton();
        propertyMstBeanUpdatePage.setNameInput('name');
        expect(propertyMstBeanUpdatePage.getNameInput()).toMatch('name');
        propertyMstBeanUpdatePage.setModifiedByInput('modifiedBy');
        expect(propertyMstBeanUpdatePage.getModifiedByInput()).toMatch('modifiedBy');
        propertyMstBeanUpdatePage.save();
        expect(propertyMstBeanUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
