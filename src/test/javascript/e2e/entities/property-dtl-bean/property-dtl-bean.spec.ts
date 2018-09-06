import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { PropertyDtlBeanComponentsPage, PropertyDtlBeanUpdatePage } from './property-dtl-bean.page-object';

describe('PropertyDtlBean e2e test', () => {
    let navBarPage: NavBarPage;
    let propertyDtlBeanUpdatePage: PropertyDtlBeanUpdatePage;
    let propertyDtlBeanComponentsPage: PropertyDtlBeanComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load PropertyDtlBeans', () => {
        navBarPage.goToEntity('property-dtl-bean');
        propertyDtlBeanComponentsPage = new PropertyDtlBeanComponentsPage();
        expect(propertyDtlBeanComponentsPage.getTitle()).toMatch(/selenimAutomationApp.propertyDtlBean.home.title/);
    });

    it('should load create PropertyDtlBean page', () => {
        propertyDtlBeanComponentsPage.clickOnCreateButton();
        propertyDtlBeanUpdatePage = new PropertyDtlBeanUpdatePage();
        expect(propertyDtlBeanUpdatePage.getPageTitle()).toMatch(/selenimAutomationApp.propertyDtlBean.home.createOrEditLabel/);
        propertyDtlBeanUpdatePage.cancel();
    });

    it('should create and save PropertyDtlBeans', () => {
        propertyDtlBeanComponentsPage.clickOnCreateButton();
        propertyDtlBeanUpdatePage.setNameInput('name');
        expect(propertyDtlBeanUpdatePage.getNameInput()).toMatch('name');
        propertyDtlBeanUpdatePage.setValueInput('value');
        expect(propertyDtlBeanUpdatePage.getValueInput()).toMatch('value');
        propertyDtlBeanUpdatePage.setParentKeyInput('parentKey');
        expect(propertyDtlBeanUpdatePage.getParentKeyInput()).toMatch('parentKey');
        propertyDtlBeanUpdatePage.propertySelectLastOption();
        propertyDtlBeanUpdatePage.save();
        expect(propertyDtlBeanUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
