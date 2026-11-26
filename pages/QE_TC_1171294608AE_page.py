# QE-TC:QE-TC-1171294608AE:v1
import os
import allure
from playwright.sync_api import Page, expect

class QE_TC_1171294608AE_page:
    """Page Object for the Document Upload page."""

    def __init__(self, page: Page):
        self.page = page
        # Selectors – adjust as needed for the actual application
        self.upload_input_selector = 'input[type="file"]'
        self.upload_button_selector = 'button:has-text("Upload")'
        self.success_message_selector = 'text=File uploaded successfully'
        self.file_name_in_list_selector_template = "text={filename}"

    @allure.step("Navigate to the upload page")
    def navigate_to(self, base_url: str):
        """Navigate to the base URL where the upload form is located."""
        self.page.goto(base_url)

    @allure.step("Upload document {file_path}")
    def upload_document(self, file_path: str):
        """Select a file and click the upload button."""
        # Ensure the file exists
        assert os.path.isfile(file_path), f"File not found: {file_path}"
        self.page.set_input_files(self.upload_input_selector, file_path)
        self.page.click(self.upload_button_selector)

    @allure.step("Verify the success message is displayed")
    def verify_success_message(self):
        """Wait for and assert the success message after upload."""
        expect(self.page.locator(self.success_message_selector)).to_be_visible(timeout=15000)

    @allure.step("Verify uploaded file {filename} appears in the document list")
    def verify_file_in_list(self, filename: str):
        """Check that the uploaded file appears in the user's document list."""
        file_locator = self.page.locator(self.file_name_in_list_selector_template.format(filename=filename))
        expect(file_locator).to_be_visible(timeout=10000)
