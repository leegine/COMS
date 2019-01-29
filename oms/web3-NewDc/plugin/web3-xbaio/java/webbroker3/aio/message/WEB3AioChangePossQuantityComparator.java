head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioChangePossQuantityComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 数量Comparator(WEB3AioChangePossQuantityComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/13 屈陽 (中訊) 新規作成
*/

package webbroker3.aio.message;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (数量Comparator)<BR>
 * 数量Comparatorクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioChangePossQuantityComparator implements Comparator 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioChangePossQuantityComparator.class);    
    
    /**
     * A：昇順 <BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 41B2954C0271
     */
    public WEB3AioChangePossQuantityComparator() 
    {
     
    }
    
    /**
     * (数量Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param orderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 
     * 　@A：昇順  
     * 　@D：降順
     * @@roseuid 416283AC03D5
     */
    public WEB3AioChangePossQuantityComparator(String orderBy) 
    {
        if (orderBy == null || (!WEB3AscDescDef.ASC.equals(orderBy) && !WEB3AscDescDef.DESC.equals(orderBy)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");            
        }
        
        this.orderBy = orderBy; 
    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 数量の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の預り証券明細１、預り証券明細２を預り証券明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（預り証券明細１.数量 <預り証券明細２.数量）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（預り証券明細１.数量 = 預り証券明細２.数量）の場合、0を返却する。 <BR>
     * 　@・（預り証券明細１.数量 > 預り証券明細２.数量）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（預り証券明細１.数量 <預り証券明細２.数量）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（預り証券明細１.数量 = 預り証券明細２.数量）の場合、0を返却する。 <BR>
     * 　@・（預り証券明細１.数量 > 預り証券明細２.数量）の場合、負の整数（-1）を返却する。
     * @@param l_obj1 - 預り証券明細オブジェクト
     * @@param l_obj2 - 預り証券明細オブジェクト
     * @@return int
     * @@roseuid 41627A470369
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        String l_strMethodName = "compare(Object l_obj1, Object l_obj2)";
        log.entering(l_strMethodName);
        
        if (l_obj1 == null || l_obj2 == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + l_strMethodName);
        }
        
        //１）　@引数のcast 
        //　@引数の預り証券明細１、預り証券明細２を預り証券明細型にcastする。 
        WEB3AioSecurityTransferUnit l_aioSecurityTransferUnit1;
        WEB3AioSecurityTransferUnit l_aioSecurityTransferUnit2;
        
        if (l_obj1 instanceof WEB3AioSecurityTransferUnit && 
            l_obj2 instanceof WEB3AioSecurityTransferUnit)
        {
            l_aioSecurityTransferUnit1 = 
                (WEB3AioSecurityTransferUnit)l_obj1;
            l_aioSecurityTransferUnit2 =
                (WEB3AioSecurityTransferUnit)l_obj2;        
        }
        else
        {
            log.debug("預り証券明細１を預り証券明細型にcastする");
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3AioSecurityTransferUnit'類型");
        }

        //２）　@比較
        
        //[昇順指定の場合（this.orderBy == ”昇順”）]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_aioSecurityTransferUnit1.changePossQuantity == null || l_aioSecurityTransferUnit2.changePossQuantity == null)
            {
                if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = 1;    
                }
            }
            else
            {
                long l_lngPossQuantity1 = Long.parseLong(l_aioSecurityTransferUnit1.changePossQuantity);
                long l_lngPossQuantity2 = Long.parseLong(l_aioSecurityTransferUnit2.changePossQuantity);
                
                //（預り証券明細１.数量 < 預り証券明細２.数量）の場合、負の整数（-1）を返却する。 
                if (l_lngPossQuantity1 < l_lngPossQuantity2)
                {
                    l_intCompare = -1;    
                }
                //（預り証券明細１.数量 = 預り証券明細２.数量）の場合、0を返却する。 
                else if (l_lngPossQuantity1 == l_lngPossQuantity2)
                {
                    l_intCompare = 0;    
                }
                //（預り証券明細１.数量 > 預り証券明細２.数量）の場合、正の整数（1）を返却する。 
                else
                {
                    l_intCompare = 1;    
                }
            }
        }
        //[降順指定の場合（this.orderBy == ”降順”）] 
        else 
        {
            if (l_aioSecurityTransferUnit1.changePossQuantity == null || l_aioSecurityTransferUnit2.changePossQuantity == null)
            {
                if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_aioSecurityTransferUnit1.changePossQuantity == null && l_aioSecurityTransferUnit2.changePossQuantity == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = -1;    
                }
            }
            else
            {
                long l_lngPossQuantity1 = Long.parseLong(l_aioSecurityTransferUnit1.changePossQuantity);
                long l_lngPossQuantity2 = Long.parseLong(l_aioSecurityTransferUnit2.changePossQuantity);
                
                //（預り証券明細１.数量 < 預り証券明細２.数量）の場合、正の整数（1）を返却する。 
                if (l_lngPossQuantity1 < l_lngPossQuantity2)
                {
                    l_intCompare = 1;    
                }
                //（預り証券明細１.数量 = 預り証券明細２.数量）の場合、0を返却する。 
                else if (l_lngPossQuantity1 == l_lngPossQuantity2)
                {
                    l_intCompare = 0;    
                }
                //（預り証券明細１.数量 > 預り証券明細２.数量）の場合、負の整数（-1）を返却する。
                else
                {
                    l_intCompare = -1;    
                }
            }            
        }

        log.exiting(l_strMethodName);
        
        return l_intCompare;
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR> 
     * <BR>
     * スーパークラスのequalsをコールする。
     * @@param l_obj
     * @@return boolean
     * @@roseuid 41627A470388
     */
    public boolean equals(Object l_obj) 
    {
        boolean l_boo = super.equals(l_obj);    
        return l_boo;
    }
}
@
