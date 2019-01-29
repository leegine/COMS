head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondOrderExecInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定情報(WEB3AdminBondOrderExecInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/03/12 徐大方 (中訊) 新規作成
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (約定情報)<BR>
 * 約定情報クラス
 * <BR>
 * @@author 徐大方
 * @@version 1.0
 */
public class WEB3AdminBondOrderExecInfoTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3AdminBondOrderExecInfoTest.class);

    public WEB3AdminBondOrderExecInfoTest(String name)
    {
        super(name);
    }

    public void testValidateCase1()
    {
        final String STR_METHOD_NAME = "testValidateCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminBondOrderExecInfo l_info = new WEB3AdminBondOrderExecInfo();
        l_info.domesticExecutionDate = WEB3DateUtility.getDate("20070312", "yyyyMMdd");
        l_info.foreignDeliveryPrice = "12345678901";
        try
        {
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateCase2()
    {
        final String STR_METHOD_NAME = "testValidateCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminBondOrderExecInfo l_info = new WEB3AdminBondOrderExecInfo();
        l_info.domesticExecutionDate = WEB3DateUtility.getDate("20070312", "yyyyMMdd");
        l_info.foreignDeliveryPrice = "123456789012";
        try
        {
            l_info.validate();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02567, l_ex.getErrorInfo());
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
