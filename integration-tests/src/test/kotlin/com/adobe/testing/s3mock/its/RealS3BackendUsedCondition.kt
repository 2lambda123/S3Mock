/*
 *  Copyright 2017-2022 Adobe.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.adobe.testing.s3mock.its

import org.junit.jupiter.api.extension.ConditionEvaluationResult
import org.junit.jupiter.api.extension.ExecutionCondition
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.platform.commons.util.AnnotationUtils

/**
 * ExecutionCondition that evaluates if a test should be disabled.
 * When running integration tests, endpoint can be overwritten by setting
 * "it.s3mock.endpoint".
 * Disable test annotated with {@link S3VerifiedFailure} when test runs against S3.
 */
class RealS3BackendUsedCondition : ExecutionCondition {
    override fun evaluateExecutionCondition(context: ExtensionContext): ConditionEvaluationResult {
        val failure = AnnotationUtils.findAnnotation(context.element, S3VerifiedFailure::class.java)
        if (failure.isPresent) {
            if (System.getProperty("it.s3mock.endpoint", null) != null) {
                return ConditionEvaluationResult.disabled(failure.get().reason)
            }
        }
        return ConditionEvaluationResult.enabled("")
    }
}
