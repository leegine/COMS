head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminORBondExecRefReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券管理者注文約定照会検索表示リクエストTest(WEB3AdminORBondExecRefReferenceRequestTest)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/11 稲本潤に(中訊) 新規作成   モデルNo.100
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券管理者注文約定照会検索表示リクエストTest)<BR>
 * 債券管理者注文約定照会検索表示リクエストTestクラス
 * @@author 稲本潤に(中訊)
 * @@version 1.0
 */
public class WEB3AdminORBondExecRefReferenceRequestTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminORBondExecRefReferenceRequestTest.class);
    WEB3AdminORBondExecRefReferenceRequest interceptor = null;

    public WEB3AdminORBondExecRefReferenceRequestTest(String arg0)
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
     * testValidate_0001
     */
    public void testValidate_0001()
    {
        final String STR_METHOD_NAME = " testValidate_0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            interceptor = new WEB3AdminORBondExecRefReferenceRequest();
            WEB3AdminOROrderExecutionSortKeyUnit sortKey = new WEB3AdminOROrderExecutionSortKeyUnit();
            sortKey.keyItem = "accountCode";
            sortKey.ascDesc = "A";
            WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys = {
                    sortKey
            };
            interceptor.sortKeys = sortKeys;
            interceptor.pageIndex = "2";
            interceptor.pageSize = "2";
            interceptor.validate();
            log.exiting(STR_METHOD_NAME);
            assertTrue(true);
        }
        catch(Exception e)
        {
            log.error("", e);
            fail();
        }
    }
    
    /**
     * testValidate_0002
     */
    public void testValidate_0002()
    {
        final String STR_METHOD_NAME = " testValidate_0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            interceptor = new WEB3AdminORBondExecRefReferenceRequest();
            WEB3AdminOROrderExecutionSortKeyUnit sortKey = new WEB3AdminOROrderExecutionSortKeyUnit();
            sortKey.keyItem = "dfsdfsafdf";
            sortKey.ascDesc = "D";
            WEB3AdminOROrderExecutionSortKeyUnit[] sortKeys = {
                    sortKey
            };
            interceptor.sortKeys = sortKeys;
            interceptor.pageIndex = "2";
            interceptor.pageSize = "2";
            interceptor.validate();
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch(WEB3BusinessLayerException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00086, e.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch(Exception e)
        {
            log.error("", e);
            fail();
        }
    }
}
@
