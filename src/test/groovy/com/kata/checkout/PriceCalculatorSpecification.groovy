package com.kata.checkout

import com.kata.catalog.Sku
import com.kata.promotions.MultiBuyDiscount
import com.kata.promotions.MultiBuyPercentageReduction
import spock.lang.Specification

class PriceCalculatorSpecification extends Specification {

    def testSku1 = new Sku("testSku1", 50)
    def testSku2 = new Sku("testSku2", 30)
    def buyThreeGetTwentyFiveOff = new MultiBuyDiscount(3, "Buy 3 Save 25p", 25);
    def buyTwoGetAQuarterOff = new MultiBuyPercentageReduction(2, "Buy one, get the second half price", 0.25);

    def testObj = new PriceCalculator()

    def "Single item in basket is priced and basket total calculated all in pounds"() {
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku1, 1)]

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 0.50
        basket.basketTotal == 0.50
    }

    def "Multiple items in basket are priced and basket total calculated"() {
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku1, 1), new LineItem(testSku2,1)]

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 0.50
        basket.lineItems[1].lineItemTotal == 0.30
        basket.basketTotal == 0.8
    }

    def "Single item with multiple quantity in basket is priced and basket total calculated"() {
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku1, 3)]

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 1.5
        basket.basketTotal == 1.5
    }

    def "SKUs with a discount have their price calculated correctly"(){
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku1, 3)]
        testSku1.offer = buyThreeGetTwentyFiveOff

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 1.25
    }

    def "SKUs with a percentage off have their price calculated correctly"(){
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku2, 2)]
        testSku2.offer = buyTwoGetAQuarterOff

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 0.45
    }

    def "SKUs with more than the threshold do not see the discount applied to the extras"(){
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku1, 5)]
        testSku1.offer = buyThreeGetTwentyFiveOff

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 2.25
    }

    def "SKUs with many more than the threshold do not see the discount applied to the extras"(){
        given:
        def basket = new Basket()
        basket.lineItems = [new LineItem(testSku1, 7)]
        testSku1.offer = buyThreeGetTwentyFiveOff

        when:
        testObj.priceBasket(basket)

        then:
        basket.lineItems[0].lineItemTotal == 3.0
    }
}
