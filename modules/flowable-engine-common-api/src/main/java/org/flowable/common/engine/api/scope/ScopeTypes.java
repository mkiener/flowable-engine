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
package org.flowable.common.engine.api.scope;

/**
 * A list of predefined identity link scope types used in identity or variable links.
 *
 * @author Joram Barrez
 * @author Micha Kiener
 */
public interface ScopeTypes {

    /** The scope type for linking to an App, containing any type of models. */
    String APP = "app";

    /** The scope type for linking to a case instance (CMMN). */
    String CMMN = "cmmn";

    /** The scope type for linking to a process instance (BPMN). */
    String BPMN = "bpmn";

    /** The scope type for linking to a task instance (might be an adhoc task or created as part of a case or process model). */
    String TASK = "task";

    /** The scope type for linking to a rule instance (DMN). */
    String DMN = "dmn";

    /** The scope type for linking to a document / content instance. */
    String DOCUMENT = "document";

    /** The scope type for linking to a conversation instance. */
    String CONVERSATION = "conversation";


    /** The scope type for linking to a process instance (BPMN). */
    String BPMN_DEFINITION = "bpmnDefinition";

    /** The scope type for linking to a case instance (CMMN). */
    String CMMN_DEFINITION = "cmmnDefinition";

    /** The scope type for linking to a task instance (might be an adhoc task or created as part of a case or process model). */
    String TASK_DEFINITION = "taskDefinition";

    /** The scope type for linking to a rule instance (DMN). */
    String DMN_DEFINITION = "dmnDefinition";

    /** The scope type for linking to a document / content instance. */
    String DOCUMENT_DEFINITION = "documentDefinition";

    /** The scope type for linking to a conversation instance. */
    String CONVERSATION_DEFINITION = "conversationDefinition";
}
