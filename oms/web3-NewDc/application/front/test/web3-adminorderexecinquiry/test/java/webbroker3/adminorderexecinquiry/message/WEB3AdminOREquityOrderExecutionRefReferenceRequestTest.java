head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.04.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminOREquityOrderExecutionRefReferenceRequestTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・株式注文約定照会リクエストTest(WEB3AdminOREquityOrderExecutionRefReferenceRequestTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/30 趙林鵬(中訊) 新規作成
*/

package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminOREquityOrderExecutionRefReferenceRequestTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminOREquityOrderExecutionRefReferenceRequestTest.class);

    public WEB3AdminOREquityOrderExecutionRefReferenceRequestTest(String arg0)
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
     * validate
     * this.市場コード" = JNX-PTS"
       正常結束。
     */
    public void testValidateCase0001()
    {
        final String STR_METHOD_NAME = "testValidateCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminOREquityOrderExecutionRefReferenceRequest l_request =
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            
            l_request.branchCode = new String[]{"381"};
            WEB3AdminOROrderExecutionSortKeyUnit[] l_units = new WEB3AdminOROrderExecutionSortKeyUnit[1];
            
            WEB3AdminOROrderExecutionSortKeyUnit l_unit = new WEB3AdminOROrderExecutionSortKeyUnit();
            l_unit.ascDesc = "A";
            l_unit.keyItem = "branchCode";
            l_units[0] = l_unit;
            l_request.sortKeys = l_units;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.marketCode = "11";

            l_request.validate();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate
     * this.市場コードに下記の項目以外が設定されていたら、 
　@　@　@　@「市場コードが未定義の値」の例外をスローする。 
　@　@　@　@・"東京" 
　@　@　@　@・"大阪" 
　@　@　@　@・"名古屋" 
　@　@　@　@・"福岡" 
　@　@　@　@・"札幌" 
　@　@　@　@・"NNM" 
　@　@　@　@・"JASDAQ" 
　@　@　@　@・"JNX-PTS" 
     */
    public void testValidateCase0002()
    {
        final String STR_METHOD_NAME = "testValidateCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AdminOREquityOrderExecutionRefReferenceRequest l_request =
                new WEB3AdminOREquityOrderExecutionRefReferenceRequest();
            
            l_request.branchCode = new String[]{"381"};
            WEB3AdminOROrderExecutionSortKeyUnit[] l_units = new WEB3AdminOROrderExecutionSortKeyUnit[1];
            
            WEB3AdminOROrderExecutionSortKeyUnit l_unit = new WEB3AdminOROrderExecutionSortKeyUnit();
            l_unit.ascDesc = "A";
            l_unit.keyItem = "branchCode";
            l_units[0] = l_unit;
            l_request.sortKeys = l_units;
            l_request.pageIndex = "1";
            l_request.pageSize = "1";
            l_request.marketCode = "123";

            l_request.validate();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00608);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
