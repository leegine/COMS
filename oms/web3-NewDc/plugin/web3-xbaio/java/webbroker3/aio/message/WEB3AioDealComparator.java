head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.18.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioDealComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引Comparatorクラス(WEB3AioDealComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/14 周勇 (中訊) 新規作成    
                   2004/10/25 屈陽 (中訊) レビュー               
*/
package webbroker3.aio.message;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (取引Comparator)<BR>
 * (取引Comparatorクラス)
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3AioDealComparator implements Comparator 
{
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioDealComparator.class);

    /**
     * （compareの実装） <BR>
     * <BR>
     * 取引の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の入出金明細１、入出金明細２を入出金明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（入出金明細１.取引 < 入出金明細２.取引）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（入出金明細１.取引 = 入出金明細２.取引）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（入出金明細１.取引 > 入出金明細２.取引）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（入出金明細１.取引 < 入出金明細２.取引）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（入出金明細１.取引 = 入出金明細２.取引）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（入出金明細１.取引 > 入出金明細２.取引）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * @@param l_cashTransferDetails01 - (入出金明細１)<BR>
     * 入出金明細オブジェクト１
     * @@param l_cashTransferDetails02 - (入出金明細２)<BR>
     * 入出金明細オブジェクト２
     * @@return int
     * @@throws WEB3SystemLayerException
     * @@roseuid 4107144F003C
     */
    public int compare(Object l_cashTransferDetails01, Object l_cashTransferDetails02) 
    {
        
        final String STR_METHOD_NAME =
            "execute(WEB3GenRequest l_request) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_cashTransferDetails01 == null || l_cashTransferDetails02 == null)
        {
            log.debug("l_cashTransferDetails01 or l_cashTransferDetails02 is null ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "the parameter method is null");
        }
        
        //１）　@引数のcast 
        //　@引数の入出金明細１、入出金明細２を入出金明細型にcastする。 
        WEB3AioCashTransUnit l_aioCashTransUnit01 = (WEB3AioCashTransUnit)l_cashTransferDetails01;
        WEB3AioCashTransUnit l_aioCashTransUnit02 = (WEB3AioCashTransUnit)l_cashTransferDetails02;
        
        //２）　@比較
        //　@[昇順指定の場合（this.orderBy == ”昇順”）] 
        //(this.pageIndex < 1) ? 1 : this.pageIndex;
        int l_inttradeType01 = (l_aioCashTransUnit01.tradingType == null) ? -1
            : Integer.parseInt(l_aioCashTransUnit01.tradingType);
        int l_inttradeType02 = (l_aioCashTransUnit02.tradingType == null) ? -1
            : Integer.parseInt(l_aioCashTransUnit02.tradingType);
        
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            // 入出金明細１.取引 < 入出金明細２.取引）の場合、負の整数（-1）を返却する。
            if(l_inttradeType01 < l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
                
            }            
            // (入出金明細１.取引 = 入出金明細２.取引）の場合、0を返却する。
            else if(l_inttradeType01 == l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
                
            }
            // (入出金明細１.取引 > 入出金明細２.取引）の場合、正の整数（1）を返却する。
            else if(l_inttradeType01 > l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
                
            }
            
        }        
        //　@[降順指定の場合（this.orderBy == ”降順”）]
        else if(WEB3AscDescDef.DESC.equals(this.orderBy))
        {
            //（入出金明細１.取引 < 入出金明細２.取引）の場合、正の整数（1）を返却する。
            if(l_inttradeType01 < l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
                
            }
            //（入出金明細１.取引 = 入出金明細２.取引）の場合、0を返却する。
            else if(l_inttradeType01 == l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
            //（入出金明細１.取引 > 入出金明細２.取引）の場合、負の整数（-1）を返却する
            else if(l_inttradeType01 > l_inttradeType02)
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
        }
        
        return 0;
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_arg0
     * @@return boolean
     * @@roseuid 4107144F003F
     */
    public boolean equals(Object l_arg0) 
    {
        final String STR_METHOD_NAME = "equals(Object l_obj) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_arg0 instanceof WEB3AioDealComparator)
        {
            WEB3AioDealComparator l_aioDealComparator = 
                (WEB3AioDealComparator)l_arg0;

            if (this.orderBy.equals(l_aioDealComparator.orderBy))
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }
        
        log.exiting(STR_METHOD_NAME);        
        return false;
    }
    
    /**
     * (取引Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     * @@roseuid 4107144F0041
     */
    public WEB3AioDealComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AioDealComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);
        
        this.orderBy = l_strOrderBy;
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
