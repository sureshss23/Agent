# QE-TC:QE-TC-1171294608AE:v1
import os
import pytest
import allure
from pages.QE_TC_1171294608AE_page import QE_TC_1171294608AE_page

@allure.title("Successful upload of a DOCX document at maximum allowed size")
@pytest.mark.smoke
def test_successful_docx_upload(page):
    """
    QE-TC-1171294608AE: Verify that a user can upload a supported DOCX file that exactly matches the defined maximum file size limit.
    """
    # Arrange
    upload_page = QE_TC_1171294608AE_page(page)
    base_url = os.getenv("QE_BASE_URL")
    assert base_url, "Environment variable QE_BASE_URL must be set"
    docx_path = os.getenv("QE_DOCX_PATH")
    assert docx_path and os.path.isfile(docx_path), "Environment variable QE_DOCX_PATH must point to a valid file"

    # Act
    upload_page.navigate_to(base_url)
    upload_page.upload_document(docx_path)

    # Assert
    upload_page.verify_success_message()
    upload_page.verify_file_in_list(os.path.basename(docx_path))
