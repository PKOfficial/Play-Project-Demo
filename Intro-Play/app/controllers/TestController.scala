package controllers

import play.api._
import play.api.mvc._

class TestController extends Controller {

  def index(name:String,lname:Int) = Action {
    Ok("<h1>Hello "+name+" "+lname+"</h1>").as(HTML)
  }

}
