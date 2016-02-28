package models

import com.google.inject.ImplementedBy

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext
/**
  * Created by prabhat on 27/2/16.
  */
case class User(name:String,age:Int)
@ImplementedBy(classOf[UserService])
trait UserServiceApi {

  def getAllUsers(): Future[List[User]]

}

class UserService extends UserServiceApi {

  def getAllUsers(): Future[List[User]] ={
    println("List of Future")
    Future{
      List(
        User("Prabhat",23),
        User("Akash Singh Sethi",24),
        User("Ravi",18)
      )
    }
  }
}

