package com.kata.checkout

import com.kata.catalog.Sku
import spock.lang.Specification

class BasketSpecification extends Specification {

    PriceCalculator priceCalculatorMock = Mock()

    def testSku1 = new Sku("testSku1", 25)
    def testSku2 = new Sku("testSku2", 10)

    def testObj = new Basket()

    def setup() {
        testObj.priceCalculator = priceCalculatorMock
    }

    def "Should increase line item quantity on adding to basket"() {
        when:
        testObj.addItem(testSku1, 1)

        then:
        testObj.lineItems.size() == 1
        testObj.lineItems[0].qty == 1
        testObj.lineItems[0].sku.skuId == "testSku1"
    }

    def "Multiple line items can exist in the basket"() {
        given:
        testObj.lineItems = [new LineItem(testSku1,1)]

        when:
        testObj.addItem(testSku2, 1)

        then:
        testObj.lineItems.size() == 2
        testObj.lineItems[0].sku.skuId == "testSku1"
        testObj.lineItems[1].sku.skuId == "testSku2"
    }

    def "Adding the same item increases its quantity"() {
        given:
        testObj.lineItems = [new LineItem(testSku1,1)]

        when:
        testObj.addItem(testSku1, 1)

        then:
        testObj.lineItems.size() == 1
        testObj.lineItems[0].qty == 2
        testObj.lineItems[0].sku.skuId == "testSku1"

        when:
        testObj.addItem(testSku1, 2)

        then:
        testObj.lineItems.size() == 1
        testObj.lineItems[0].qty == 4
    }

    def "Items added to the basket should be priced"() {
        when:
        testObj.addItem(testSku1, 1)

        then:
        1 * priceCalculatorMock.priceBasket(testObj) >> {}
    }
}
