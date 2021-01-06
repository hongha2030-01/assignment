Feature: Search weather in your city

  Background:
    Given I get weather in the city with the URI "/data/2.5/find"

  @smock
  @regression
  @all
  Scenario Outline: Verify that user can get weather in the city
    When I put the parameter query "q" with data <requestCity>
    And I put the parameter query "appid" with data <appid>
    And I put the parameter query "callback" with data <callback>
    And I put the parameter query "type" with data <type>
    And I put the parameter query "sort" with data <sort>
    Then I send the request and expect that the status code should be <statusCode>
    And responseCity <responseCity>, responseCountry <responseCountry>, geoCoords <geoCoords> should be correct

    Examples:
      | requestCity       | appid                            | callback                                 | type | sort       | statusCode | responseCity          | responseCountry | geoCoords         |
      | Ho Chi Minh, VN   | 439d4b804bc8187953eb36d2a8c26a02 |                                          |      |            | 200        | Thanh pho Ho Chi Minh | VN              | 10.75, 106.6667   |
      | Ho Chi Minh       | 439d4b804bc8187953eb36d2a8c26a02 |                                          |      |            | 200        | Thanh pho Ho Chi Minh | VN              | 10.75, 106.6667   |
      | Hồ Chí Minh, VN   | 439d4b804bc8187953eb36d2a8c26a02 |                                          |      |            | 200        | Thanh pho Ho Chi Minh | VN              | 10.75, 106.6667   |
      | Hà Nội, Việt Name | 439d4b804bc8187953eb36d2a8c26a02 | jQuery19106330571120330595_1609390738001 | like | population | 200        | Ha Noi                | VN              | 21.0245, 105.8412 |

  @regression
  @all
  Scenario Outline: Verify that system returns correct when inputting not found or invalid city
    When I put the parameter query "q" with data <requestCity>
    When I put the parameter query "appid" with data <appid>
    Then  I send the request and expect that the status code should be <statusCode>
    And no city is returned

    Examples:
      | requestCity | appid                            | statusCode |
      | Ha Noi. VN  | 439d4b804bc8187953eb36d2a8c26a02 | 200        |
      | net, VN     | 439d4b804bc8187953eb36d2a8c26a02 | 200        |
      | ,           | 439d4b804bc8187953eb36d2a8c26a02 | 200        |
      | *,          | 439d4b804bc8187953eb36d2a8c26a02 | 200        |

  @all
  Scenario Outline: Verify that system doesn't return error when input empty or too long text
    When I put the parameter query "q" with the text having length <requestCityLength>
    Then  I send the request and expect that the status code should be <statusCode>
    And no city is returned

    Examples:
      | requestCityLength | statusCode |
      | 0                 | 200        |
      | 255               | 200        |

  @all
  Scenario Outline: Verify that system returns correct code when missing or inputting invalid parameter value
    When I put the parameter query "q" with data <requestCity>
    And I put the parameter query "appid" with data <appid>
    And I put the parameter query "callback" with data <callback>
    And I put the parameter query "type" with data <type>
    And I put the parameter query "sort" with data <sort>
    Then  I send the request and expect that the status code should be <statusCode>

    Examples:
      | requestCity       | appid                            | callback | type | sort | statusCode |
      |                   | 439d4b804bc8187953eb36d2a8c26a02 |          |      |      | 400        |
      | Ho Chi Minh       |                                  |          |      |      | 401        |
      | Hồ Chí Minh, VN   | abc                              |          |      |      | 401        |
      | Hà Nội, Việt Name | 439d4b804bc8187953eb36d2a8c26a02 | any      | any  | any  | 200        |

