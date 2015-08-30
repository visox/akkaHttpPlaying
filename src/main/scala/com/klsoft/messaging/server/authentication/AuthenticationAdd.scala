package com.klsoft.messaging.server.authentication

import com.klsoft.messaging.server.structures.UserToken
import com.klsoft.messaging.server.structures.User

case class AuthenticationAdd(token: UserToken, user: User)