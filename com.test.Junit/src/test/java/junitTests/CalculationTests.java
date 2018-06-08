package junitTests;
import static org.junit.Assert.assertEquals;
///https://dzone.com/articles/a-guide-to-mocking-with-mockito
import org.junit.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import db.CalculationDao;
import dev.Calculation;
public class CalculationTests {
	

	@Mock
	private CalculationDao repoMock;
	
	@InjectMocks
	Calculation cal=new Calculation();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		System.out.println("Beforeclass");
	
	}
	
	@Before
	public void setUp() throws Exception
	{
		System.out.println("Before running a test");
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testMax() throws Exception
	{
		when(repoMock.checkEmpty()).thenReturn(true); 
		doNothing().when(repoMock).save(new int[] {1});
		assertEquals(2, cal.findMax(new int[] {-3,-4,0,1,2}));
	}
	

	@After
	public void afterSetUp() throws Exception
	{
		System.out.println("After running a test");
	}
	
	@AfterClass
	public static  void setUpAfterClass() throws Exception
	{
		System.out.println("Afterclass");
	}
	

}
