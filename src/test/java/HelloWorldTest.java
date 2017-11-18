import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tu.kielce.pl.HelloWorld;

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
		Response helloResponse = testedEntity.helloWorld();
		assertEquals(Response.Status.OK.getStatusCode(), helloResponse.getStatus());
		assertEquals("Hello World!", helloResponse.getEntity());
	}

}
