import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.*;

import tu.kielce.pl.photoGallery.rest.HelloWorld;

public class HelloWorldTest {

	private HelloWorld testedEntity;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		testedEntity = new HelloWorld();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testHelloWorld() {
		Response helloResponse = testedEntity.helloWorldResponse();
		assertEquals(Response.Status.OK.getStatusCode(), helloResponse.getStatus());
		assertEquals("Hello World!", helloResponse.getEntity());
	}

}
