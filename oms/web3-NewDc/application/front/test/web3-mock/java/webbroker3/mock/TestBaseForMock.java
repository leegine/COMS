head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.19.05.38.51;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	72c4dad1feb735a;
filename	TestBaseForMock.java;

1.1
date	2011.04.07.02.37.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	TestBaseForMock.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.mock;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

import test.util.JunitTestBase;
import webbroker3.WEB3MockObjectAppPlugin;
//import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.util.WEB3MockObjectManager;
import webbroker3.mock.util.WEB3MockObjectManagerImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 王暁傑
 * @@version 1.0
 */
public class TestBaseForMock extends JunitTestBase
{    
    /** logger. */
    private static final Logit m_log = Logit.getInstance(TestBaseForMock.class);
    
    private final String sqlPath = "plugin/web3-xbfeq/test/sql/";
    
    private TransactionalInterceptor l_transactionalInterceptor = 
        new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING);
    private Object l_transaction = null;
    
    /**
     * Logger
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(TestBaseForMock.class);
    
    public static final WEB3MockObjectManager MOCK_MANAGER = new WEB3MockObjectManagerImpl();
    
    static 
    {
        try
        {
            WEB3MockObjectAppPlugin.plug();
        } catch (Exception l_ex)
        {
            m_log.error(l_ex);
            fail();
        }
    }
    /**
     * @@param name
     */
    public TestBaseForMock(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.clear();
        ThreadLocalSystemAttributesRegistry.clearAttributes();
//        WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
        l_transaction = l_transactionalInterceptor.onCall(null,null);
      
    }

    protected void tearDown() throws Exception
    {

        try
        {
            super.tearDown();
            MOCK_MANAGER.clear();
            ThreadLocalSystemAttributesRegistry.clearAttributes();
            l_transactionalInterceptor.onReturn(l_transaction,null);
        }
        catch (Throwable l_ex)
        {
        }
        finally
        {
            try
            {
                
                l_transactionalInterceptor.onThrowable(l_transaction,null);
            }
            catch (Throwable l_ex)
            {
                
            }
        }

    }
    
    protected void checkMethodValue()
    {
        List l_lisCheckMethodParamsError = MOCK_MANAGER.checkMethodParamsValue();
        if(!l_lisCheckMethodParamsError.isEmpty())
        {
            StringBuffer l_strBr = new StringBuffer();
            int l_intCheckedMethodParamsError = l_lisCheckMethodParamsError.size();
            for (int i = 0; i < l_intCheckedMethodParamsError; i++)
            {
                l_strBr.append(l_lisCheckMethodParamsError.get(i) + "\n");
            }
            assertEquals("沒有調用getMethodParamsValue（）這個方法@ :","", l_strBr.toString());
        }
    }
            
    /**/
    public void dataLoader()
    {
        final String l_strMethodName = "dataLoader()";
        m_log.info(l_strMethodName + ": ENTER");

        String l_strCallMethodName = '/' + getName() + ".sql";
        String l_strCallClassName = getClass().getName();

        l_strCallClassName = l_strCallClassName.replace('.', '/');
        String l_strSqlFile = sqlPath + l_strCallClassName + l_strCallMethodName;
        
        File l_file = new File(l_strSqlFile);
        
        if(!l_file.exists())
        {
            System.out.println("当該テスト用SQL文("+ l_strSqlFile +")無し!!!");
            fail();
        }
        
        try
        {
            String l_strSql = new String(); 
            Process p = 
                Runtime.getRuntime().exec("sqlplus feqwxj/feqwxj@@daiwa @@" 
                    + l_strSqlFile);
            
            BufferedReader bufferedReader = 
                new BufferedReader(new InputStreamReader(p.getInputStream())); 
            
            while ( (l_strSql=bufferedReader.readLine()) != null) 
                System.out.println(l_strSql);
            p.waitFor();
        }
        catch(IOException ie)
        {
            System.out.println("OracleのSqlplusがnot found!!!");
            fail();
        }
        catch(InterruptedException ie)
        {
            System.out.println("Process wait error!!!");
            fail();
        } 

        m_log.info(l_strMethodName + ": EXIT");
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d15 1
a15 1
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
d67 1
a67 1
        WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
@

