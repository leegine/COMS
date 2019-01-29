head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.27.30;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginExecuteReferenceServiceImplForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引注文約定照会サービスImplForMock(WEB3MarginExecuteReferenceServiceImplForMock.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 信用取引注文約定照会サービスImplForMock
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3MarginExecuteReferenceServiceImplForMock
    extends WEB3MarginExecuteReferenceServiceImpl
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3MarginExecuteReferenceServiceImplForMock.class);

    /**
     * (create検索条件データコンテナ)<BR>
     * 索条件（where以下指定の文字列）のパラメータの文字列配列を作成する。<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * @@param l_strMarketCode - (市場コード)<BR>
     * @@param l_datOrderBizDate - (発注日)<BR>
     * @@return String[]<BR>
     */
    protected String[] createSearchCondDataContainers(
        String l_strProductCode,
        String l_strMarketCode,
        Date l_datOrderBizDate,
        String l_strOrderConditionDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "createSearchCondDataContainers(String ,String ,Date, String)";
        log.entering(STR_METHOD_NAME);


        //1）參數驗證
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl",
            "createSearchCondDataContainers",
            new Class[] {String.class, String.class, Date.class, String.class},
            new Object[]{l_strProductCode, l_strMarketCode, l_datOrderBizDate});

        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
            "webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl",
            "createSearchCondDataContainers",
            new Class[] {String.class, String.class, Date.class, String.class}))
        {
            log.exiting(STR_METHOD_NAME);
            //2）MockFor --〉 asWEB3BaseException
            TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl",
                "createSearchCondDataContainers",
                new Class[] {String.class, String.class, Date.class, String.class}).asWEB3BaseException();

            //3)MockFor --〉 asObject
            return (String[])TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                "webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl",
                "createSearchCondDataContainers",
                new Class[] {String.class, String.class, Date.class, String.class}).asObject();
        }

        log.exiting(STR_METHOD_NAME);
        return super.createSearchCondDataContainers(
            l_strProductCode, l_strMarketCode, l_datOrderBizDate, l_strOrderConditionDiv);
    }
}
@
