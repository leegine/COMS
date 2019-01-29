head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeUtils.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ビジネス共通のユーティリティ(WEB3GentradeUtils)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/17 張宝楠 (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BizDateTypeDef;

/**
 * ビジネス共通のユーティリティ。<BR>
 * <BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3GentradeUtils
{
    /**
     * 営業日を求め返します。
     *
     * @@param l_datBaseDate - 基準日
     * @@return 営業日
     */
    public static Timestamp getBizDate(Date l_datBaseDate) throws WEB3BaseException
    {
        return getBizDate(l_datBaseDate, 0);
    }

    /**
     * 基準日から加算・減算した営業日を求め返します。
     *
     * @@param l_datBaseDate - 基準日
     * @@param l_intRoll - 加算・減算日数
     * @@return 営業日
     */
    public static Timestamp getBizDate(Date l_datBaseDate, int l_intRoll) throws WEB3BaseException
    {        
        Timestamp l_tsBaseDate;
        if (l_datBaseDate instanceof Timestamp)
        {
            l_tsBaseDate = (Timestamp)l_datBaseDate;
        }
        else
        {
            l_tsBaseDate = new Timestamp(l_datBaseDate.getTime());   
        }
        
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(l_tsBaseDate);         
        
        String l_strBizDateType =
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsBaseDate);
            
        if ((WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strBizDateType)) && (l_intRoll == 0))
        {
            l_intRoll = 1;
        }
        
        return l_bizDate.roll(l_intRoll);
    }
}
@
