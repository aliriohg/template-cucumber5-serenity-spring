Feature: employee registration
  *unique email
  *unique ID

  Background:
    Given The employee database is clean

  @Automation
  Scenario: the company register a employee
    Given the company "pepito" registers new employees
      | userId   | userName | gender | rol | startDate                     |
      | 12345678 | pepito   | M      | QA  | 2020-06-11T00:30:00.000-05:00 |
    When Get Employees Registered by
      | company | gender  | rol | from                          | to                            |
      | pepito  | [blank] |     | 2020-06-11T00:00:00.000-05:00 | 2020-09-11T00:30:00.000-05:00 |
    Then I have the following employees
      | userId   | userName | gender | rol | startDate                     |
      | 12345678 | pepito   | M      | QA  | 2020-06-11T00:30:00.000-05:00 |

  @Automation
  Scenario: the company register employees
    Given the company "pepito" registers new employees
      | userId   | userName | gender | rol | startDate                     |
      | 12345678 | pepito   | M      | QA  | 2020-06-11T00:30:00.000-05:00 |
      | 98765432 | pepita   | F      | DEV | 2020-07-11T00:30:00.000-05:00 |
    When Get Employees Registered by
      | company | gender  | rol     | from                          | to                            |
      | pepito  | [blank] | [blank] | 2020-06-11T00:00:00.000-05:00 | 2020-09-11T00:30:00.000-05:00 |
    Then I have the following employees
      | userId   | userName | gender | rol | startDate                     |
      | 12345678 | pepito   | M      | QA  | 2020-06-11T00:30:00.000-05:00 |
      | 98765432 | pepita   | F      | DEV | 2020-07-11T00:30:00.000-05:00 |


