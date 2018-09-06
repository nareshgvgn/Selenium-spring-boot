import { element, by, promise, ElementFinder } from 'protractor';

export class PropertyDtlBeanComponentsPage {
    createButton = element(by.id('jh-create-entity'));
    title = element.all(by.css('jhi-property-dtl-bean div h2#page-heading span')).first();

    clickOnCreateButton(): promise.Promise<void> {
        return this.createButton.click();
    }

    getTitle(): any {
        return this.title.getAttribute('jhiTranslate');
    }
}

export class PropertyDtlBeanUpdatePage {
    pageTitle = element(by.id('jhi-property-dtl-bean-heading'));
    saveButton = element(by.id('save-entity'));
    cancelButton = element(by.id('cancel-save'));
    nameInput = element(by.id('field_name'));
    valueInput = element(by.id('field_value'));
    parentKeyInput = element(by.id('field_parentKey'));
    propertySelect = element(by.id('field_property'));

    getPageTitle() {
        return this.pageTitle.getAttribute('jhiTranslate');
    }

    setNameInput(name): promise.Promise<void> {
        return this.nameInput.sendKeys(name);
    }

    getNameInput() {
        return this.nameInput.getAttribute('value');
    }

    setValueInput(value): promise.Promise<void> {
        return this.valueInput.sendKeys(value);
    }

    getValueInput() {
        return this.valueInput.getAttribute('value');
    }

    setParentKeyInput(parentKey): promise.Promise<void> {
        return this.parentKeyInput.sendKeys(parentKey);
    }

    getParentKeyInput() {
        return this.parentKeyInput.getAttribute('value');
    }

    propertySelectLastOption(): promise.Promise<void> {
        return this.propertySelect
            .all(by.tagName('option'))
            .last()
            .click();
    }

    propertySelectOption(option): promise.Promise<void> {
        return this.propertySelect.sendKeys(option);
    }

    getPropertySelect(): ElementFinder {
        return this.propertySelect;
    }

    getPropertySelectedOption() {
        return this.propertySelect.element(by.css('option:checked')).getText();
    }

    save(): promise.Promise<void> {
        return this.saveButton.click();
    }

    cancel(): promise.Promise<void> {
        return this.cancelButton.click();
    }

    getSaveButton(): ElementFinder {
        return this.saveButton;
    }
}
