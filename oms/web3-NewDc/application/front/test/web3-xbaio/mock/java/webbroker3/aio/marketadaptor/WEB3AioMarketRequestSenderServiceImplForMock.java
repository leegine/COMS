head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMarketRequestSenderServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出金・入出庫リクエスト送信サービスForMock(WEB3AioMarketRequestSenderServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/29 齊珂 (中訊) 新規作成
*/

package webbroker3.aio.marketadaptor;

import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 入出金・入出庫リクエスト送信サービスForMock
 *
 * @@author 齊珂(中訊)
 * @@version 1.0
 */
public class WEB3AioMarketRequestSenderServiceImplForMock extends
    WEB3AioMarketRequestSenderServiceImpl
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketRequestSenderServiceImplForMock.class);

    /**
     * (Mock)<BR>
     * トリガ発行。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * @@param l_strRequestCode - (データコード)<BR>
     */
    public void submitTrigger(String l_strInstitutionCode, 
        String l_strRequestCode)
    {
        final String STR_METHOD_NAME = "submitTrigger(String, String)-->ForMock";
        log.entering(STR_METHOD_NAME);

        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl",
            "submitTrigger",
            new Class[] {String.class, String.class},
            new Object[]{l_strInstitutionCode,l_strRequestCode});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl",
            "submitTrigger",  
            new Class[] {String.class, String.class}))
        {
            log.debug("webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImplForMock(String,String)");
            
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImpl",
                    "submitTrigger",
                    new Class[] {String.class, String.class}).asVoid();
            return;
        }
        log.exiting(STR_METHOD_NAME);
        super.submitTrigger(l_strInstitutionCode, l_strRequestCode);
    }
    
}
@
