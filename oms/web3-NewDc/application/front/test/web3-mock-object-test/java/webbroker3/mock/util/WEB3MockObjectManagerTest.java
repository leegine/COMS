head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mock.util;


import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestAllMockClass;
import webbroker3.mock.TestAllMockClassForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;


public class WEB3MockObjectManagerTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MockObjectManagerTest.class);
    
    private WEB3MockObjectManager objectManager = 
        new WEB3MockObjectManagerImpl();
    public WEB3MockObjectManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Object test
     */
    public void testObjectManager_returnObject()
    {
        objectManager.setIsMockUsed(true);
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asObject",
            new Class[]{},
            null);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asObject",
            new Class[]{}));
        
        assertNull((objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asObject",new Class[]{}).asObject()));
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asObject",
            new Class[]{},
            "asObject");
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asObject",
            new Class[]{}));
        assertEquals("asObject", 
            (String)(objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asObject",new Class[]{}).asObject()));
        
    }

    /**
     * int test
     */
    public void testObjectManager_returnInt()
    {
        objectManager.setIsMockUsed(true);
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asInt",
            new Class[]{},
            null);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asInt",
            new Class[]{}));
        assertEquals(0, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asInt",new Class[]{}).asInt()); 
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asInt",
            new Class[]{},
            new Integer(123));
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asInt",
            new Class[]{}));
        assertEquals(123, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asInt",new Class[]{}).asInt());
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnLong()
    {
        objectManager.setIsMockUsed(true);
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asLong",
            new Class[]{},
            null);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asLong",
            new Class[]{}));
        assertEquals(0, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asLong",new Class[]{}).asLong()); 
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asLong",
            new Class[]{},
            new Long(1234567890));
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asLong",
            new Class[]{}));
        assertEquals(1234567890L, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asLong",new Class[]{}).asLong());
    }
    
    /**
     * double test
     */
    public void testObjectManager_returnDouble()
    {
        objectManager.setIsMockUsed(true);
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asDouble",
            new Class[]{},
            null);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asDouble",
            new Class[]{}));
        assertEquals(0.0D, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asDouble",new Class[]{}).asDouble(),0); 
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asDouble",
            new Class[]{},
            new Double("1234567890.123456789"));
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asDouble",
            new Class[]{}));
        assertEquals(1234567890.123456789D, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asDouble",new Class[]{}).asDouble(),0.000000001);
    }
    
    /**
     * float test
     */
    public void testObjectManager_returnFloat()
    {
        objectManager.setIsMockUsed(true);
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asFloat",
            new Class[]{},
            null);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asFloat",
            new Class[]{}));
        assertEquals(0.0F, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asFloat",new Class[]{}).asFloat(),0.0); 
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asFloat",
            new Class[]{},
            new Float(0.123456789));
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asFloat",
            new Class[]{}));
        assertEquals(0.123456789F, 
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asFloat",new Class[]{}).asFloat(), 0.000000001);
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnBoolean()
    {
        objectManager.setIsMockUsed(true);
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asBoolean",
            new Class[]{},
            null);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asBoolean",
            new Class[]{}));
        assertFalse(
            objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asBoolean",new Class[]{}).asBoolean()); 
        
        objectManager.setMethodReturnValue(
            "webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asBoolean",
            new Class[]{},
            Boolean.TRUE);
        
        assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
            "asBoolean",
            new Class[]{}));
        assertTrue(objectManager.getMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue","asBoolean",new Class[]{}).asBoolean());
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnException_asVoid()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asVoid",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asVoid",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asVoid",new Class[]{}).asVoid();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    /**
     * long test
     */
    public void testObjectManager_returnException_asInt()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asInt",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asInt",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asInt",new Class[]{}).asInt();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnException_asLong()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asLong",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asLong",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asLong",new Class[]{}).asLong();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnException_asFloat()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asFloat",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asFloat",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asFloat",new Class[]{}).asFloat();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnException_asDouble()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asDouble",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asDouble",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asDouble",new Class[]{}).asDouble();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnException_asBoolean()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asBoolean",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asBoolean",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asBoolean",new Class[]{}).asBoolean();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    /**
     * long test
     */
    public void testObjectManager_returnException_asObject()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseRuntimeException l_runtimeException = 
            new WEB3BaseRuntimeException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asObject",
                new Class[]{},
                l_runtimeException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asObject",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asObject",new Class[]{}).asObject();
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_runtimeException, l_ex.getCause());
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }

    /**
     * long test
     */
    public void testObjectManager_returnWEB3BaseException()
    {
        objectManager.setIsMockUsed(true);
        WEB3BaseException l_baseException = 
            new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,"");        
        try
        {
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asWEB3BaseException",
                new Class[]{},
                null);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asWEB3BaseException",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asWEB3BaseException",new Class[]{}).asWEB3BaseException();

            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asWEB3BaseException",
                new Class[]{},
                "1");
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asWEB3BaseException",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asWEB3BaseException",new Class[]{}).asWEB3BaseException();

            
            objectManager.setMethodReturnValue(
                "webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asWEB3BaseException",
                new Class[]{},
                l_baseException);
            
            assertTrue(objectManager.isMockUsed("webbroker3.mock.util.WEB3MockObjectReturnValue",
                "asWEB3BaseException",
                new Class[]{}));
            objectManager.getMethodReturnValue(
                    "webbroker3.mock.util.WEB3MockObjectReturnValue","asWEB3BaseException",new Class[]{}).asWEB3BaseException();
            fail();
        }
        catch (WEB3BaseException l_ex) {
            log.debug("", l_ex);
            assertEquals(l_baseException, l_ex);
        }
        catch (Exception l_ex) 
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    public void testParamsValue()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class},
            null);            
        TestAllMockClassForMock l_testAllMockClassForMock = new TestAllMockClassForMock();
        l_testAllMockClassForMock.method5("method5");
        
        
        WEB3MockObjectParamsValue l_paramsVaue = 
            TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
            "webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class});
        
        assertEquals("method5", (l_paramsVaue.getFirstCalled()[0]));
        
        l_testAllMockClassForMock.method5("method51");
        l_testAllMockClassForMock.method5("method52");
        
        assertEquals("method51", (l_paramsVaue.getCalled(1)[0]));
        assertEquals("method52", (l_paramsVaue.getCalled(2)[0]));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class, int.class, long.class},
            null);                    
        l_testAllMockClassForMock.method5("method5", 10, 1000);
        
        l_paramsVaue = 
            TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
            "webbroker3.mock.TestAllMockClass",
            "method5",
            new Class[]{String.class, int.class, long.class});
        
        assertEquals("method5", (l_paramsVaue.getFirstCalled()[0]));
        assertEquals(new Integer(10), (l_paramsVaue.getFirstCalled()[1]));
        assertEquals(new Long(1000), (l_paramsVaue.getFirstCalled()[2]));
    }
    
    public void testMethod6()
    {
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        TestAllMockClass l_testAllMockClassForMock = 
            new TestAllMockClassForMock();
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class, int.class, long.class},
                null);  
            
            assertNull(l_testAllMockClassForMock.method6("method6",2,100));            
            
            WEB3MockObjectParamsValue l_paramsVaue = 
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class, int.class, long.class});
            
            assertEquals("method6", l_paramsVaue.getFirstCalled()[0]);
            assertEquals(new Integer(2), l_paramsVaue.getFirstCalled()[1]);
            assertEquals(new Long(100), l_paramsVaue.getFirstCalled()[2]);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class, int.class, long.class},
                "method6");  
            
            assertEquals("method6", l_testAllMockClassForMock.method6("method62",20,101));            
            
            l_paramsVaue = 
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.mock.TestAllMockClass",
                "method6",
                new Class[]{String.class, int.class, long.class});
            
            assertEquals("method62", l_paramsVaue.getCalled(1)[0]);
            assertEquals(new Integer(20), l_paramsVaue.getCalled(1)[1]);
            assertEquals(new Long(101), l_paramsVaue.getCalled(1)[2]);
            
            try
            {
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.mock.TestAllMockClass",
                    "method6",
                    new Class[]{String.class, int.class, long.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,""));  
                
                assertEquals("method6", l_testAllMockClassForMock.method6("method62",20,101));            
                
                l_paramsVaue = 
                    TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.mock.TestAllMockClass",
                    "method6",
                    new Class[]{String.class, int.class, long.class});
                
                assertEquals("method62", l_paramsVaue.getCalled(2)[0]);
                assertEquals(new Integer(20), l_paramsVaue.getCalled(2)[1]);
                assertEquals(new Long(101), l_paramsVaue.getCalled(2)[2]);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug("", l_ex);
                assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());            
            }
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();            
        }
    }
}
@
