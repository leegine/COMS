head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoUpdateInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/07 金傑（中訊）新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoUpdateInterceptorTest  extends TestBaseForMock
{

    private WEB3IfoUpdateInterceptor l_interceptor = null;

    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoUpdateInterceptorTest.class);
    
    public WEB3IfoUpdateInterceptorTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_interceptor = new WEB3IfoUpdateInterceptor();
    }

    protected void tearDown() throws Exception
    {
        this.l_interceptor = null;
        super.tearDown();
    }
    
    public void testMutate_C0001()
    { 
        final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedExecConditionType(IfoOrderExecutionConditionType.IOC);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderActionParams l_params =new IfoOrderActionParams();
            l_params.setOrderUnitId(1001);
            IfoOrderActionParams l_aterIfoOrderActionParams = this.l_interceptor.mutate(null,null,l_params);
            
            assertEquals(IfoOrderExecutionConditionType.IOC,l_aterIfoOrderActionParams.getConfirmedExecConditionType());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

}
@
