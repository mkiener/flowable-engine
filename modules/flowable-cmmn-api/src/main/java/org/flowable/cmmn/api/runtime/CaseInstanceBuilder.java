/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.cmmn.api.runtime;

import java.util.Map;

/**
 * Fluent builder API to build a new case instance. You need to provide the case definition through its id or key,
 * everything else is optional. If you want to start a case out of an init form, use the {@link #startWithForm()} method,
 * otherwise use {@link #start()}.
 *
 * @author Joram Barrez
 * @author Tijs Rademakers
 */
public interface CaseInstanceBuilder {

    /**
     * Set the case definition the new case instance should be based on. You can reference the case definition by its
     * id (this method) or its definition key {@link #caseDefinitionKey(String)}. If both are provided, the id takes
     * precedence over the key as it references one particular deployed version whereas with the key, the latest
     * deployed version will be taken.
     *
     * @param caseDefinitionId the id of the case definition to base the new case instance on
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder caseDefinitionId(String caseDefinitionId);

    /**
     * Set the case definition the new case instance should be based on. You can reference the case definition by its
     * key (this method) or its definition id {@link #caseDefinitionId(String)}. If both are provided, the id takes
     * precedence over the key as it references one particular deployed version whereas with the key, the latest
     * deployed version will be taken.
     *
     * @param caseDefinitionKey the key of the case definition to base the new case instance on (will take the latest version)
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder caseDefinitionKey(String caseDefinitionKey);

    /**
     * Optionally set the name for the new case instance to create.
     *
     * @param name the name for the case instance
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder name(String name);

    /**
     * Optionally adds an instance based business key for the new case. For instance, you can later on search case
     * instances by their business key.
     *
     * @param businessKey the business key to attach to the new case instance to create
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder businessKey(String businessKey);

    /**
     * Optionally adds an initial map of variables to the case instance. They will be available immediately during the
     * first evaluation of the CMMN case model (e.g. within sentry evaluation expressions, etc).
     * You can repeatedly add more variables by invoking this method again as it will add the given variables to the
     * existing ones (if there are any).
     *
     * @param variables the map of variables to be added to the case instance
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder variables(Map<String, Object> variables);

    /**
     * Optionally adds a single variable to the map of initial variables for the case instance.
     *
     * @param variableName the name of the new variable to add
     * @param value the value for the variable to add
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder variable(String variableName, Object value);

    /**
     * Optionally adds transient variables to the case instance. Transient variables are not going to be persisted, they
     * might became handy to be used during the first evaluation of the CMMN case model when starting the case instance
     * and are dropped afterwards.
     *
     * @param transientVariables the map of transient variables to add to the case (only available during the first evaluation of the CMMN case model)
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder transientVariables(Map<String, Object> transientVariables);

    /**
     * Optionally adds a single, transient variable to the case instance. Transient variables are not going to be persisted, they
     * might became handy to be used during the first evaluation of the CMMN case model when starting the case instance
     * and are dropped afterwards.
     *
     * @param variableName the name of the transient variable to add
     * @param value the value of the transient variable
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder transientVariable(String variableName, Object value);

    /**
     * Optionally set a tenant id on the case instance which then can be used for fetching instances for a certain tenant.
     *
     * @param tenantId the id of the tenant to set on the new case instance
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder tenantId(String tenantId);

    /**
     * Optionally set the outcome provided by the form service when using an init-form to start the case. If no form was
     * used, the outcome is ignored. Most likely to be used with {@link #startWithForm()}.
     *
     * @param outcome the outcome provided by the init form of a case
     * @return the same builder for method chaining
     */
    CaseInstanceBuilder outcome(String outcome);

    /**
     * Starts the new case instance with the provided information within this builder. At least the definition id or key
     * must be provided in order to be able to start a new case instance.
     *
     * @return the newly created case instance
     */
    CaseInstance start();

    /**
     * Set callback type of the newly created case instance.
     * @param callbackType type of the callback
     * @return case instance builder which creates case instance with defined callback type
     */
    CaseInstanceBuilder callbackType(String callbackType);

    /**
     * Set callback id of the newly created case instance.
     *
     * @param callbackId id of the callback
     * @return case instance builder which creates case instance with defined callback id
     */
    CaseInstanceBuilder callbackId(String callbackId);


    /**
     * Set parent case instanceId of the newly create case instance
     *
     * @param parentCaseInstanceId parent case instance identifier
     * @return modified case instance builder which creates case instance with the reference to parent
     */
    CaseInstanceBuilder parentId(String parentCaseInstanceId);

    CaseInstance startWithForm();

    /**
     * @return the case definition id, if it was set before using this builder
     */
    String getCaseDefinitionId();

    /**
     * @return returns the case definition key if it was set before using this builder
     */
    String getCaseDefinitionKey();

    /**
     * @return the optional name for the case if it was set before using this builder
     */
    String getName();

    /**
     * @return the optional business key for the case if it was set before using this builder
     */
    String getBusinessKey();

    /**
     * @return the variables already added through this builder, might be {@code null}, if not (yet) set
     */
    Map<String, Object> getVariables();

    /**
     * @return the transient variables already added through this builder, might be {@code null}, if not (yet) set
     */
    Map<String, Object> getTransientVariables();

    /**
     * @return the optional tenant id for the case if it was set before using this builder
     */
    String getTenantId();

    /**
     * @return the optional outcome for the case if it was set before using this builder
     */
    String getOutcome();

    String getCallbackType();

    String getCallbackId();

    String getParentId();

}
