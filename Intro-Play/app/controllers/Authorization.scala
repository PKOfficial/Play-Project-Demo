package controllers

import java.io.File

import play.api.data._
import play.api.data.Forms._
import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.i18n.Messages.Implicits._

case class UserData(name: String, pass: String, age: Int, emails: List[String])

/**
  * Created by prabhat on 26/2/16.
  */
class Authorization extends Controller{

  def validate(age: Int) = {
    age match {
      case a if a <= 50 =>
        Some(age)
      case _ =>
        None
    }
  }

  val userForm = Form(
    mapping(
      "name" -> nonEmptyText,
      "pass" -> nonEmptyText,
      "age" -> number.verifying("THis is Errors", value => validate(value).isDefined),
      "emails" -> list(email)
    )(UserData.apply)(UserData.unapply)
  )

  def login() = Action { implicit request =>
    Ok(views.html.login(userForm))
  }

  def failed() = Action { implicit request =>
    BadRequest(views.html.failed())
  }

  def submit() = Action { implicit request =>
    userForm.bindFromRequest.fold(
      formWithErrors => {
        //Redirect("/failed").flashing("error"->"Bad Request")
        BadRequest(views.html.login(formWithErrors))
      },
      data => {
        if (data.name == "pk" && data.pass == "pk") {
          Ok(views.html.success(data.name)(data.emails.isEmpty))
        }
        else {
          Redirect("/failed").flashing("error"->"Unauthorized Access")
        }
      }
    )
  }

}
