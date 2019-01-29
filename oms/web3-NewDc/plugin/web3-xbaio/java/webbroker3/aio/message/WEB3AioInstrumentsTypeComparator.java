head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInstrumentsTypeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 商品タイプComparator(WEB3AioInstrumentsTypeComparator)
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
 * (商品タイプComparator)<BR>
 * 商品タイプComparatorクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioInstrumentsTypeComparator implements Comparator 
{
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioInstrumentsTypeComparator.class);    
    
    /**
     * A：昇順 <BR>
     * D：降順
     */
    private String orderBy;
    
    /**
     * @@roseuid 41B2954B01D4
     */
    public WEB3AioInstrumentsTypeComparator() 
    {
     
    }
    
    /**
     * (商品タイプComparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。
     * @@param orderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 
     * 　@A：昇順  
     * 　@D：降順
     * @@roseuid 416283530358
     */
    public WEB3AioInstrumentsTypeComparator(String orderBy) 
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
     * 商品タイプの比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の預り証券明細１、預り証券明細２を預り証券明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（預り証券明細１.商品タイプ <預り証券明細２.商品タイプ）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（預り証券明細１.商品タイプ = 預り証券明細２.商品タイプ）の場合、0を返却する。 <BR>
     * 　@・（預り証券明細１.商品タイプ > 預り証券明細２.商品タイプ）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（預り証券明細１.商品タイプ <預り証券明細２.商品タイプ）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（預り証券明細１.商品タイプ = 預り証券明細２.商品タイプ）の場合、0を返却する。 <BR>
     * 　@・（預り証券明細１.商品タイプ > 預り証券明細２.商品タイプ）の場合、負の整数（-1）を返却する。
     * @@param l_obj1 - 預り証券明細オブジェクト
     * @@param l_obj2 - 預り証券明細オブジェクト
     * @@return int
     * @@roseuid 41627968030B
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
            if (l_aioSecurityTransferUnit1.instrumentsType == null || l_aioSecurityTransferUnit2.instrumentsType == null)
            {
                if (l_aioSecurityTransferUnit1.instrumentsType == null && l_aioSecurityTransferUnit2.instrumentsType != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_aioSecurityTransferUnit1.instrumentsType == null && l_aioSecurityTransferUnit2.instrumentsType == null)
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
                //（預り証券明細１.商品タイプ < 預り証券明細２.商品タイプ）の場合、負の整数（-1）を返却する。 
                if (l_aioSecurityTransferUnit1.instrumentsType.compareTo(l_aioSecurityTransferUnit2.instrumentsType) < 0)
                {
                    l_intCompare = -1;    
                }
                //（預り証券明細１.商品タイプ = 預り証券明細２.商品タイプ）の場合、0を返却する。 
                else if (l_aioSecurityTransferUnit1.instrumentsType.compareTo(l_aioSecurityTransferUnit2.instrumentsType) == 0)
                {
                    l_intCompare = 0;    
                }
                //（預り証券明細１.商品タイプ > 預り証券明細２.商品タイプ）の場合、正の整数（1）を返却する。 
                else
                {
                    l_intCompare = 1;    
                }
            }            
        }
        //[降順指定の場合（this.orderBy == ”降順”）] 
        else 
        {
            if (l_aioSecurityTransferUnit1.instrumentsType == null || l_aioSecurityTransferUnit2.instrumentsType == null)
            {
                if (l_aioSecurityTransferUnit1.instrumentsType == null && l_aioSecurityTransferUnit2.instrumentsType != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_aioSecurityTransferUnit1.instrumentsType == null && l_aioSecurityTransferUnit2.instrumentsType == null)
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
                //（預り証券明細１.商品タイプ < 預り証券明細２.商品タイプ）の場合、正の整数（1）を返却する。 
                if (l_aioSecurityTransferUnit1.instrumentsType.compareTo(l_aioSecurityTransferUnit2.instrumentsType) < 0)
                {
                    l_intCompare = 1;    
                }
                //（預り証券明細１.商品タイプ = 預り証券明細２.商品タイプ）の場合、0を返却する。 
                else if (l_aioSecurityTransferUnit1.instrumentsType.compareTo(l_aioSecurityTransferUnit2.instrumentsType) == 0)
                {
                    l_intCompare = 0;    
                }
                //（預り証券明細１.商品タイプ > 預り証券明細２.商品タイプ）の場合、負の整数（-1）を返却する。
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
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。 <BR>
     * <BR>
     * スーパークラスのequalsをコールする。
     * @@param l_obj
     * @@return boolean
     * @@roseuid 41627968032A
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
