head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSTradingTimeManagementForTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3EquityPTSTradingTimeManagementForTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/25 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.equity;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranchMarketPTSDealtCond;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSTradingTimeManagementForTest extends WEB3EquityPTSTradingTimeManagement
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeBranchMarketPTSDealtCond.class);
    
    private static String caseNum = "0";
    
    public void setCaseNum(String caseNum)
    {
        this.caseNum = caseNum;
    }
    
    public static Date getOrderBizDate() throws WEB3SystemLayerException
    {
        if ("0".equals(caseNum))
        {
            return WEB3DateUtility.getDate("20071225", "yyyyMMdd");
        }
        else
        {
            return null;
        }
        
    }

    public static Date getOrderBizDate(Date l_datConfirmBizDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getOrderBizDate(Date)";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = getOrderBizDate();
        if (l_datBizDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return l_datConfirmBizDate;
        }

        if (WEB3DateUtility.compareToDay(l_datBizDate, l_datConfirmBizDate) != 0)
        {
            log.debug("発注日が変わりました。お手数ですが、もう一度入力し直してください。");
            log.exiting(STR_METHOD_NAME);
            //取得した発注日と引数の確認時発注日が違う日付であれば例外をスローする
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00205,
                WEB3EquityPTSTradingTimeManagement.class.getName() + "." + STR_METHOD_NAME,
               "発注日が変わりました。お手数ですが、もう一度入力し直してください。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_datConfirmBizDate;
    }
    
}
@
