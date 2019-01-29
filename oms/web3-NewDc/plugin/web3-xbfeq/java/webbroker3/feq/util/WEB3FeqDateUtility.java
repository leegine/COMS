head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqDateUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 日付型のデータのユーティリティ(WEB3FeqDateUtility)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/11 王暁傑 (中訊) 新規作成
*/
package webbroker3.feq.util;

import java.sql.Timestamp;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;

/**
 * 日付型のデータの処理を行う関数を持つユーティリティクラス。<BR>
 * <BR>
 * 
 * @@author 王暁傑(中訊)
 * @@version 1.0
 */
public class WEB3FeqDateUtility extends WEB3DateUtility
{
    /**
     * 入力された日付が外国株式発注日の場合<BR>
     * trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     *
     * @@param l_tsInputDate 日付
     * @@return 入力されたstrは指定したフォーマットの日付なら、<BR>
     */
    public static boolean isFeqBizDate(Timestamp l_tsInputDate)
        throws WEB3BaseException
    {
        //営業日区分取得
        String l_strCommonBizDateType = 
            WEB3GentradeTradingTimeManagement.getBizDateType(l_tsInputDate);
        //海外の営業日区分取得
        String l_strFeqBizDateType = 
            WEB3GentradeTradingTimeManagement.getFeqBizDateType(l_tsInputDate);
        
        if (WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strCommonBizDateType)
                || WEB3BizDateTypeDef.NO_BIZ_DATE.equals(l_strFeqBizDateType))
        {
            return false;
        }
        else
        {
            return true;
        }
        
    }
    
}
@
