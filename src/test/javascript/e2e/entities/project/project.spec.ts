import { browser } from 'protractor';
import { NavBarPage } from './../../page-objects/jhi-page-objects';
import { ProjectComponentsPage, ProjectUpdatePage } from './project.page-object';

describe('Project e2e test', () => {
    let navBarPage: NavBarPage;
    let projectUpdatePage: ProjectUpdatePage;
    let projectComponentsPage: ProjectComponentsPage;

    beforeAll(() => {
        browser.get('/');
        browser.waitForAngular();
        navBarPage = new NavBarPage();
        navBarPage.getSignInPage().autoSignInUsing('admin', 'admin');
        browser.waitForAngular();
    });

    it('should load Projects', () => {
        navBarPage.goToEntity('project');
        projectComponentsPage = new ProjectComponentsPage();
        expect(projectComponentsPage.getTitle()).toMatch(/selenimAutomationApp.project.home.title/);
    });

    it('should load create Project page', () => {
        projectComponentsPage.clickOnCreateButton();
        projectUpdatePage = new ProjectUpdatePage();
        expect(projectUpdatePage.getPageTitle()).toMatch(/selenimAutomationApp.project.home.createOrEditLabel/);
        projectUpdatePage.cancel();
    });

    it('should create and save Projects', () => {
        projectComponentsPage.clickOnCreateButton();
        projectUpdatePage.setNameInput('name');
        expect(projectUpdatePage.getNameInput()).toMatch('name');
        projectUpdatePage.save();
        expect(projectUpdatePage.getSaveButton().isPresent()).toBeFalsy();
    });

    afterAll(() => {
        navBarPage.autoSignOut();
    });
});
