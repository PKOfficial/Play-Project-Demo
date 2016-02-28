package controllers

import com.google.inject.Inject
import models.{UserServiceApi}
import play.api.mvc._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by prabhat on 27/2/16.
  */
class UserController @Inject() (user:UserServiceApi) extends Controller{

  def getUser() = Action.async {implicit request=>
    user.getAllUsers.map{ res => Ok(views.html.userList(res))}
  }

}
