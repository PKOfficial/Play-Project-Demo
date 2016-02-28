import org.specs2.mutable._
import org.specs2.runner._
import org.junit.runner._

import play.api.test._
import play.api.test.Helpers._
/**
  * Created by prabhat on 27/2/16.
  */
@RunWith(classOf[JUnitRunner])
class AuthorizationSpec extends Specification {

  "Authorization" should {

    "send 404 on a bad request" in new WithApplication {
      route(FakeRequest(GET, "/thiswillnotrun")) must beSome.which(status(_) == NOT_FOUND)
    }

    "render login page" in new WithApplication{
      val loginPage = route(FakeRequest(GET,"/login")).get
      status(loginPage) must equalTo(OK)
      contentType(loginPage) must beSome.which(_ == "text/html")
      contentAsString(loginPage) must contain ("Login Form")
    }

    "render failed page" in new WithApplication{
      val loginPage = route(FakeRequest(GET,"/failed")).get
      status(loginPage) must equalTo(BAD_REQUEST)
      contentType(loginPage) must beSome.which(_ == "text/html")
      contentAsString(loginPage) must contain ("Unauthorized Access")
    }

    "login with valid data"in new WithApplication{
      val loginPage = route(FakeRequest(POST,"/submit").withFormUrlEncodedBody("name"->"pk","pass"->"pk","age"->"12")).get
      status(loginPage) must equalTo(OK)
      contentType(loginPage) must beSome.which(_ == "text/html")
      contentAsString(loginPage) must contain ("Success")
    }

    "login with in-valid password"in new WithApplication{
      val loginPage = route(FakeRequest(POST,"/submit").withFormUrlEncodedBody("name"->"pk","pass"->"pk123","age"->"12")).get
      status(loginPage) must equalTo(303)
    }

    "login with failed validation"in new WithApplication{
      val loginPage = route(FakeRequest(POST,"/submit").withFormUrlEncodedBody("name"->"pk","pass"->"pk123","age"->"122")).get
      status(loginPage) must equalTo(400)
      contentType(loginPage) must beSome.which(_ == "text/html")
      contentAsString(loginPage) must contain ("Login Form")
    }

  }

}
