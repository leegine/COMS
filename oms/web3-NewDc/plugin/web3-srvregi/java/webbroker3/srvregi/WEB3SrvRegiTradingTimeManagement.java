head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiTradingTimeManagement.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サービス利用取引時間管理(WEB3SrvRegiTradingTimeManagement.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/21 張威 (中訊) 新規作成
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;


/**
 * (サービス利用取引時間管理)<BR>
 * サービス利用取引時間管理クラス<BR>
 *                                                                 
 * @@author 張威
 * @@version 1.0
 */
public class WEB3SrvRegiTradingTimeManagement extends WEB3GentradeTradingTimeManagement 
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiTradingTimeManagement.class);

    /**
     * @@roseuid 416F4A9C029F
     */
    public WEB3SrvRegiTradingTimeManagement() 
    {
     
    }
    
    /**
     * (is営業日)<BR>
     * 対象日付が営業日かどうか判定する。
     *
     *1) 取引時間管理.営業日区分()により、対象日付の営業日区分を取得する。
     *
     *　@[引数]
     *　@　@日付：引数.対象日付
     *
     *2) 取得した営業日区分が、非営業日の場合はfalseを返却する。
     *　@　@それ以外の場合は、trueを返却する。
     * <BR>
     * @@param l_tsObjectTimestamp - (対象日付)<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 411CA984013A
     */
    public static boolean isBizDate(Timestamp l_tsObjectTimestamp) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isBizDate(Timestamp)";
        
        log.entering(STR_METHOD_NAME);
        
        String l_strClassName = WEB3SrvRegiTradingTimeManagement.class.getName();
        
        if (l_tsObjectTimestamp == null)
        {
            log.debug(l_strClassName + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                l_strClassName + STR_METHOD_NAME);
        }

        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsObjectTimestamp)))
        {
            log.debug("非営業日の場合は、falseを返却する。");
            return false;
        }
        else 
        {
            log.debug("営業日の場合は、trueを返却する。");
            return true;
        }
    }
}
@
