Feature: Super Admin

  Scenario: Create unverified company
    Given click on signup
    Then enter credentials of new user
    Then login as super-admin again
    Then verify the company is created

  Scenario: Super-Admin tab visibility when user is not Super Admin
    Given validate super-admin is not present

  Scenario: Verifying an Unverified company
    Given click on signup
    Then enter credentials of new user
    Then login as super-admin again
    Then verify the company is created
    Then verify it has been marked as verified

  Scenario: Unverifying an Verified company
    Given login as super-admin
    Then verify company has been marked as unverified

  Scenario: Total companies count validation
    Given login as super-admin
    Then validate total count of company

  Scenario: Changing pages validation
    Given login as super-admin
    Then Check if page switcher is enabled
    Then click on > and < and >> and <<

  Scenario: Number of companies visible on page check
    Given login as super-admin
    Then From bottom right of the table change number of companies visible on screen

  Scenario: Sorting check
    Given login as super-admin
    Then check sorting of super-admin page

  Scenario: Filter Option Validation (All Checked)
    Given login as super-admin
    Then Check select all option from dropdown

  Scenario: Register Company
    Given login as super-admin
    Then register a company

  Scenario Outline: Register company (Company name check)
    Given login as super-admin
    Then Enter a company name that already exists <alert>
    Examples:
      | alert                       |
      | Company Name Already Exists |

  Scenario Outline: Register company (domain check)
    Given login as super-admin
    Then Enter a domain name that already exists <alert>
    Examples:
      | alert                                      |
      | Domain already exists with another company |

  Scenario Outline: Register company (Empty fields)
    Given login as super-admin
    Then Enter empty fields <alert>
    Examples:
      | alert                          |
      | Fill out the mandatory fields! |

  Scenario: Delete company admins
    Given login as super-admin
    Then delete company admin

  Scenario Outline: Unverify Company (Admin and user status)
    Given login as super-admin
    Then unverify company <alert>
    Then verify company again <alert2>
    Examples:
      | alert                    | alert2                           |
      | apple is now unverified! | Company verified successfully !! |

  Scenario Outline: Edit company and unblock users
    Given login as super-admin
    Then unblock a user <alert>
    Examples:
      | alert                        |
      | Users Unblocked Successfully |

  Scenario: Projects visibility (Admin Screen)
    Given login as admin
    Then check visibility of projects on admin screen

  Scenario: Projects Edit (Admin Screen)
    Given login as admin
    Then edit field in admin

  Scenario: Delete Project (Admin screen)
    Given click on admin
    Then validate the project has been created on grid
    Then delete the same project

  Scenario: Verify a unverified company (Domain already exists with a verified company)
    Given login as super-admin
    Then register a company
    Then unverify the company and create new company with same domain

  Scenario: Company Admins visibility
    Given login as super-admin
    Then verify admins are present

  Scenario: Add Company Admins
    Given login as super-admin
    Then add company admin

  Scenario Outline: Unblock User (verified company)
    Given login as super-admin
    Then unblock a user <alert>
    Examples:
      | alert                        |
      | Users Unblocked Successfully |



















































