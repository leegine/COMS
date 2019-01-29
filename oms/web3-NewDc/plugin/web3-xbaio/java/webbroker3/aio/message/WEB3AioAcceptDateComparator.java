head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioAcceptDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付日時Comparatorクラス(WEB3AioAcceptDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 周勇 (中訊) 新規作成   
                   2004/10/25 屈陽 (中訊) レビュー                
*/
package webbroker3.aio.message;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (受付日時Comparator)<BR>
 * (受付日時Comparatorクラス)
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioAcceptDateComparator implements Comparator 
{
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioAcceptDateComparator.class);
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 受付日時の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の入出金明細１、入出金明細２を入出金明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（入出金明細１.受付日時 < 入出金明細２.受付日時）の<BR>
     * 場合、負の整数（-1）を返却する。 <BR>
     * 　@・（入出金明細１.受付日時 = 入出金明細２.受付日時）の<BR>
     * 場合、0を返却する。 <BR>
     * 　@・（入出金明細１.受付日時 > 入出金明細２.受付日時）の<BR>
     * 場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（入出金明細１.受付日時 < 入出金明細２.受付日時）の<BR>
     * 場合、正の整数（1）を返却する。 <BR>
     * 　@・（入出金明細１.受付日時 = 入出金明細２.受付日時）の<BR>
     * 場合、0を返却する。 <BR>
     * 　@・（入出金明細１.受付日時 > 入出金明細２.受付日時）の<BR>
     * 場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * @@param l_cashTransferDetails01 - (入出金明細オブジェクト１)
     * @@param l_cashTransferDetails02 - (入出金明細オブジェクト２)
     * @@return int
     * @@roseuid 410711C903C6
     */
    public int compare(Object l_cashTransferDetails01, Object l_cashTransferDetails02) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_cashTransferDetails01, Object l_cashTransferDetails02)";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashTransferDetails01 == null || l_cashTransferDetails02 == null)
        {
            log.debug("l_cashTransferDetails01 or l_cashTransferDetails02 is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）　@引数のcast 
        //　@引数の入出金明細１、入出金明細２を入出金明細型にcastする。
        WEB3AioCashTransUnit l_aioCashTransUnit01 = (WEB3AioCashTransUnit)l_cashTransferDetails01;
        WEB3AioCashTransUnit l_aioCashTransUnit02 = (WEB3AioCashTransUnit)l_cashTransferDetails02;
        
        //２）　@比較 
        //　@[昇順指定の場合（this.orderBy == ”昇順”）] 

        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_aioCashTransUnit01.receptionDate == null || l_aioCashTransUnit02.receptionDate == null)
            {
                if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                else if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }
            else
            {
                //（入出金明細１.受付日時 < 入出金明細２.受付日時）の場合、負の整数（-1）を返却する。
                if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
                //（入出金明細１.受付日時 = 入出金明細２.受付日時）の場合、0を返却する。
                else if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                //（入出金明細１.受付日時 > 入出金明細２.受付日時）の場合、正の整数（1）を返却する。
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
            }            
        }
        //　@[降順指定の場合（this.orderBy == ”降順”）]
        else if(WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            if (l_aioCashTransUnit01.receptionDate == null || l_aioCashTransUnit02.receptionDate == null)
            {
                if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate != null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                else if (l_aioCashTransUnit01.receptionDate == null && l_aioCashTransUnit02.receptionDate == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                else
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
            else
            {
                //（入出金明細１.受付日時 < 入出金明細２.受付日時）の場合、正の整数（1）を返却する。
                if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) < 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 1;
                }
                //（入出金明細１.受付日時 = 入出金明細２.受付日時）の場合、0を返却する。
                else if(WEB3DateUtility.compareToSecond(l_aioCashTransUnit01.receptionDate, l_aioCashTransUnit02.receptionDate) == 0)
                {
                    log.exiting(STR_METHOD_NAME);
                    return 0;
                }
                //（入出金明細１.受付日時 > 入出金明細２.受付日時）の場合、負の整数（-1）を返却する。
                else 
                {
                    log.exiting(STR_METHOD_NAME);
                    return -1;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return 0;
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 410711D1026E
     */
    public boolean equals(Object l_arg0) 
    {
        return super.equals(l_arg0);
    }
    
    /**
     * (受付日時Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.aio.message.WEB3AioAcceptDateComparator
     * @@roseuid 410711D9025E
     */
    public WEB3AioAcceptDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AioAcceptDateComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");            
        }
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
