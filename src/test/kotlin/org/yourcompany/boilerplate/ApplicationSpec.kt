/*
 * Copyright (C)2018 - Deny Prasetyo <jasoet87@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.yourcompany.boilerplate

import org.amshove.kluent.shouldEqual
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import org.yourcompany.boilerplate.serializer.ProductExample
import org.yourcompany.boilerplate.serializer.toJson

object ApplicationSpec : Spek({
    describe("A ProductExample") {
        val product = ProductExample("12", "Sepatu Naga", 200000, "Sepatu penyembur api")
        context("when converted to Json String") {
            it("Should return correct Result") {
                val expectedResult =
                    "{\"id\":\"12\",\"name\":\"Sepatu Naga\",\"price\":200000,\"description\":\"Sepatu penyembur api\"}"

                val result = product.toJson()
                result shouldEqual expectedResult
            }
        }
    }
})
