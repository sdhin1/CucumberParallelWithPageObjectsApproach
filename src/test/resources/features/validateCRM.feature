Feature: Login to Zoho

  Scenario Outline: Navigate to Zoho.com
    Given launch browser '<browserName>'
    When user navigates to the URL 'http://zoho.com'
    Then user clicks on login

    Examples: 
      | browserName |
      | chrome      |
      | firefox     |
