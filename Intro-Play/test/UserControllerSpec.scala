import models.{User, UserServiceApi, UserService}
import org.specs2.mock.Mockito
import org.mockito.Mockito._

import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import controllers.UserController
import play.api.test._
import play.api.test.Helpers._

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by prabhat on 27/2/16.
  */
@RunWith(classOf[JUnitRunner])
class UserControllerSpec extends Specification with Mockito{

  val service = mock[UserService]
  val controller = new UserController(service)

  "UserController" should {
    "render fetch page" in new WithApplication{
      when(service.getAllUsers()).thenReturn(
        Future(
          Nil
        )
      )

      val userPage = call(controller.getUser(), FakeRequest(GET,"/fetch"))//route(FakeRequest(GET,"/fetch")).get
      status(userPage) must equalTo(OK)
//      val some = Await.result(userPage,1.second)
//      val body = some.body
//      body.map{case a => a foreach println}
      contentType(userPage) must beSome.which(_ == "text/html")
      contentAsString(userPage) must contain ("User List")
    }
  }

}
