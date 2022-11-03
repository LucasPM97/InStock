package com.lucas.instock.useCases.formatPageContent

import com.lucas.instock.data.model.PageConfig

object Consts {
    object Models {
        val PageConfig = PageConfig(
            id = 0,
            pageName = "Entelequia",
            pageDomain = "https://entelequia.com.ar/",
            rootElementPath = "div#root/div.container-fluid/div.row",
            productNameElementPath = "div.product-info mt-5 mt-sm-0 col-md-6 col-12/h4",
            imageElementPath = "div.text-center active carousel-item/figure.iiz/div/img.iiz__img",
            priceElementPath = "div.product-info mt-5 mt-sm-0 col-md-6 col-12/p.product-price mb-4",
            stockElementPath = "div.cart-control/button.add-to-cart button-loader"
        )
    }

    object HtmlElements {
        const val SearchProductImage = "<div class=\"product-photos mt-lg-5 pt-lg-4 col-md-6 col-12\">\n" +
                "                <div class=\"text-center active carousel-item\">\n" +
                "                  <figure class=\"iiz  \">\n" +
                "                    <div>\n" +
                "                      <img class=\"iiz__img  \" style=\"transition: linear 0ms opacity 0ms, linear 150ms visibility 0ms;\" src=\"https://entelequia.com.ar/api/storage/product_arch/g9ETVt9JpvFnjmNmKbW96dMuJTlKf75D7B8zZRrp.jpg\"/>\n" +
                "                    </div>\n" +
                "                    <span class=\"iiz__btn iiz__hint\">\n" +
                "                    </span>\n" +
                "                  </figure>\n" +
                "                  <p>Text</p>\n" +
                "                </div>\n" +
                "          </div>"
        const val SearchProductPriceAndName = "<div class=\"product-info mt-5 mt-sm-0 col-md-6 col-12\">\n" +
                "    <div class=\"breadcrumb d-none d-sm-block\">\n" +
                "        <a href=\"/productos/mangas\">\n" +
                "            MANGAS\n" +
                "        </a>\n" +
                "        <span class=\"mr-1\">-</span>\n" +
                "        <a href=\"/productos/mangas-seinen\">\n" +
                "            Seinen\n" +
                "        </a>\n" +
                "    </div>\n" +
                "    <h4>\n" +
                "        PERSONA 3 05\n" +
                "    </h4>\n" +
                "    <h4>\n" +
                "        Other h4\n" +
                "    </h4>\n" +
                "    <p class=\"product-price mb-4\">\n" +
                "        ARS\n" +
                "        2800\n" +
                "    </p>\n" +
                "</div>"
        const val SearchProductInStock = "<div class=\"cart-control\">\n" +
                "              <div class=\"input-group quantity-spinner\">\n" +
                "                <span class=\"input-group-btn\">\n" +
                "                  <button type=\"button\" class=\"btn btn-default btn-number\">\n" +
                "                    <span class=\"glyphicon glyphicon-minus\">\n" +
                "                      -\n" +
                "                    </span>\n" +
                "                  </button>\n" +
                "                </span>\n" +
                "                <input type=\"text\" name=\"quantity\" class=\"form-control input-number\" value=\"1\" readonly=\"\" reamin=\"1\" max=\"10\"/>\n" +
                "                <span class=\"input-group-btn\">\n" +
                "                  <button type=\"button\" class=\"btn btn-default btn-number\">\n" +
                "                    <span class=\"glyphicon glyphicon-plus\">\n" +
                "                      +\n" +
                "                    </span>\n" +
                "                  </button>\n" +
                "                </span>\n" +
                "              </div>\n" +
                "              <button class=\"add-to-cart button-loader\" type=\"button\">\n" +
                "                Añadir al carrito\n" +
                "              </button>\n" +
                "            </div>"

        const val BaseHTML = "<div id=\"root\">\n" +
                "    <div class=\"container-fluid\">\n" +
                "        <div class=\"topbar row\">\n" +
                "        </div>\n" +
                "        <div class=\"row\">\n" +
                "${SearchProductImage}\n" +
                "${SearchProductPriceAndName}\n" +
                "${SearchProductInStock}\n" +
                "        </div>\n" +
                "       \n" +
                "    </div>\n" +
                "</div>"
    }

}