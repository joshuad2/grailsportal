/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.apache.shiro.authc.AccountException
import org.apache.shiro.authc.IncorrectCredentialsException
import org.apache.shiro.authc.UnknownAccountException
import org.apache.shiro.authc.SimpleAccount
import org.apache.shiro.authz.permission.WildcardPermission
import com.grailsPortal.domain.*

class ShiroDbRealm {
    static authTokenClass = org.apache.shiro.authc.UsernamePasswordToken

    def credentialMatcher
    def shiroPermissionResolver

    def authenticate(authToken) {
        log.info "Attempting to authenticate ${authToken.username} in DB realm..."
        def username = authToken.username

        // Null username is invalid
        if (username == null) {
            throw new AccountException("Null usernames are not allowed by this realm.")
        }

        // Get the user with the given username. If the user is not
        // found, then they don't have an account and we throw an
        // exception.
        def user = JsecUser.findByUsername(username)
        if (!user) {
            throw new UnknownAccountException("No account found for user [${username}]")
        }

        log.info "Found user '${user.username}' in DB"

        // Now check the user's password against the hashed value stored
        // in the database.
        def account = new SimpleAccount(username, user.passwordHash, "ShiroDbRealm")
        if (!credentialMatcher.doCredentialsMatch(authToken, account)) {
            log.info "Invalid password (DB realm)"
            throw new IncorrectCredentialsException("Invalid password for user '${username}'")
        }

        return account
    }

    def hasRole(principal, roleName) {
     def results=JsecUser.executeQuery("select distinct ju.username from JsecUser as ju,"+
	                                   " JsecUserRoleRel as jrl, JsecRole as rl where jrl.user=ju" +
	                                   " and jrl.role=rl and " +
	                                   "ju.username=? and rl.name=?",principal,roleName)
									
     if (results==null || results.size==0){
	    return 0	
	}else{
	  return 1
	}
    }

    def hasAllRoles(principal, roles) {
        def r = JsecUser.withCriteria {
            roles {
                'in'("name", roles)
            }
            eq("username", principal)
        }

        return r.size() == roles.size()
    }

    def isPermitted(principal, requiredPermission) {
        // Does the user have the given permission directly associated
        // with himself?
        //
        // First find all the permissions that the user has that match
        // the required permission's type and project code.
        def user = JsecUser.findByUsername(principal)
        def permissions = user.permissions

        // Try each of the permissions found and see whether any of
        // them confer the required permission.
        def retval = permissions?.find { permString ->
            // Create a real permission instance from the database
            // permission.
            def perm = shiroPermissionResolver.resolvePermission(permString)

            // Now check whether this permission implies the required
            // one.
            if (perm.implies(requiredPermission)) {
                // User has the permission!
                return true
            }
            else {
                return false
            }
        }

        if (retval != null) {
            // Found a matching permission!
            return true
        }

        // If not, does he gain it through a role?
        //
        // Get the permissions from the roles that the user does have.
        def results = JsecUser.executeQuery("select distinct p from JsecUser as user join user.roles as role join role.permissions as p where user.username = '$principal'")
        println "Results: $results"

        // There may be some duplicate entries in the results, but
        // at this stage it is not worth trying to remove them. Now,
        // create a real permission from each result and check it
        // against the required one.
        retval = results.find { permString ->
            // Create a real permission instance from the database
            // permission.
            def perm = shiroPermissionResolver.resolvePermission(permString)

            // Now check whether this permission implies the required
            // one.
            if (perm.implies(requiredPermission)) {
                // User has the permission!
                return true
            }
            else {
                return false
            }
        }

        if (retval != null) {
            // Found a matching permission!
            return true
        }
        else {
            return false
        }
    }
}
