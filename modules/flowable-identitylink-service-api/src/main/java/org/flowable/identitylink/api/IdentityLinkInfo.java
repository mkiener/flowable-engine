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

package org.flowable.identitylink.api;

/**
 * The identity link information is used for assigning permissions to a scoped object. A scoped object might be an instance (e.g. a case or process instance, etc)
 * or a definition (e.g. a case or process definition, etc).
 *
 * If the identity is linked to an object, the {@link #getScopeType()} defines the type of object it is linked to (e.g. 'case' or 'process', etc) and
 * {@link #getScopeId()} is the id fo the object it is linked to (e.g. the case instance id or the process instance id). In addition,
 * the {@link #getScopeDefinitionId()} might contain the referenced definition id of the linked object (e.g. the case definition id or the process definition id, etc).
 *
 * If the identity is linked to a definition in order to define the permissions needed to start an instance from, the {@link #getScopeType()} defines the type
 * of definition it is liked to (e.g. 'caseDefinition' or 'processDefinition', etc) and the {@link #getScopeDefinitionId()} references the id of the referenced
 * definition. In this case, {@link #getScopeId()} will always be {@code null} as there is no referenced instance.
 *
 * For historic and backwards compatibility reasons, there are still specific referenced object ids like {@link #getTaskId()} in there and at some point, they
 * might be removed. So it is suggested to only use the generic information like type, id and definition id instead of the specific ones like task id, process
 * instance id, etc
 *
 * Once linked to a scoped object or definition, the permission is then specified with the help of {@link #getType()}, {@link #getUserId()}
 * and / or {@link #getGroupId()} where the type specifies the type of permission (see {@link IdentityLinkType} for some permission types available like
 * assignee, owner or the like). If there is a user id linked, ther permission is a personal one, just assigned to a single user, if the group id is given,
 * the permission is linked to group of users, rather than a single user.
 *
 * Examples:
 * To define the assignee of a task, create a identity link with type {@link IdentityLinkType#ASSIGNEE}, the id of the user it is assigned to as the user id,
 * the referenced task id with scope id and the scope type needs to be 'task' in this example. Optionally, you can then also specifiy the task definition id,
 * if needed for querying, etc.
 *
 * @see IdentityLinkType for type of identity links
 * @see org.flowable.common.engine.api.scope.ScopeTypes for types of scoped objects
 */
public interface IdentityLinkInfo {

    /**
     * Returns the type of identity link, e.g. 'assignee' or 'candidate', etc. See {@link IdentityLinkType} for a collection of available types for identities.
     */
    String getType();

    /**
     * If the identity link involves a user, then this will be a non-null id of a user. That userId can be used to query for user information through the
     * UserQuery API.
     */
    String getUserId();

    /**
     * If the identity link involves a group, then this will be a non-null id of a group. That groupId can be used to query for user information through the
     * GroupQuery API.
     */
    String getGroupId();

    /**
     * The id of the task associated with this identity link.
     * @deprecated use {@link #getScopeId()} instead if this is a task related identity link
     */
    @Deprecated
    String getTaskId();

    /**
     * The process instance id associated with this identity link.
     * @deprecated use {@link #getScopeId()} instead if this is a process instance related identity link
     */
    @Deprecated
    String getProcessInstanceId();
    
    /**
     * The id of the linked scoped object or {@code null} if this identity is linked to a definition, rather than a scoped object. If the type is 'task' for
     * instance, this method returns the id of the task instance.
     */
    String getScopeId();
    
    /**
     * The scope type associated with this identity link which must not be {@code null}. For example, if this identity is linked to a task instance, this
     * method returns 'task'. If this identity is linked to a process defintion for example, this method will return 'processDefinition'.
     */
    String getScopeType();
    
    /**
     * The id of the linked definition, if this identity is linked to a definition or the definition id of the scoped object, if this identity is linked to
     * a scoped object. For example, this method will return the process definition id of the process instance, if scope type is 'process' and scope id is
     * the process instance id.
     */
    String getScopeDefinitionId();
}
