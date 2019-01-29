head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.57.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOrderDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 注文日時Comparatorクラス(WEB3AioOrderDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/25 王蘭芬(中訊) レビュー                                       
*/

package webbroker3.aio.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (注文日時Comparator)<BR>
 * 注文日時Comparatorクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */

public class WEB3AioOrderDateComparator implements Comparator 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioOrderDateComparator.class);
        
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * (コンストラクタ)<BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.aio.message.WEB3AioOrderDateComparator
     * @@roseuid 4109ADFB0311
     */
    public WEB3AioOrderDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME =
            "WEB3AioOrderDateComparator(String l_strOrderBy)";
        log.entering(STR_METHOD_NAME);

        if (l_strOrderBy == null || 
            (!l_strOrderBy.equals(WEB3AscDescDef.ASC) && 
            !l_strOrderBy.equals(WEB3AscDescDef.DESC)))
        {
            throw new IllegalArgumentException(
                "パラメータの値が'A：昇順'、'D：降順'以外です。");
        }        
        this.orderBy = l_strOrderBy;
        log.exiting(STR_METHOD_NAME);       
    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 注文日時の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の出金申込問合せ明細１、出金申込問合せ明細２を出金申込問合せ<BR>
     * 明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * 
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（出金申込問合せ明細１.注文日時 < <BR>
     * 出金申込問合せ明細２.注文日時）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（出金申込問合せ明細１.注文日時 = <BR>
     * 出金申込問合せ明細２.注文日時）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（出金申込問合せ明細１.注文日時 > <BR>
     * 出金申込問合せ明細２.注文日時）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（出金申込問合せ明細１.注文日時 < <BR>
     * 出金申込問合せ明細２.注文日時）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（出金申込問合せ明細１.注文日時 = <BR>
     * 出金申込問合せ明細２.注文日時）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（出金申込問合せ明細１.注文日時 > <BR>
     * 出金申込問合せ明細２.注文日時）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * <BR>
     * @@param l_obj1 - (出金申込問合せ明細オブジェクト１)
     * @@param l_obj2 - (出金申込問合せ明細オブジェクト２)
     * @@return int
     * @@roseuid 4109ADFB030C
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
         
        Date l_datVal1 = null;
        Date l_datVal2 = null;
        
        //１）引数の出金申込問合せ明細１、出金申込問合せ明細２を出金申込問合せ<BR>
        //明細型にcastする。
        if ((l_obj1 instanceof WEB3AioCashoutInqUnit)    //出金申込問合せ明細
            && (l_obj2 instanceof WEB3AioCashoutInqUnit))
        {
            l_datVal1 = ((WEB3AioCashoutInqUnit)l_obj1).orderDate;
            l_datVal2 = ((WEB3AioCashoutInqUnit)l_obj2).orderDate;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する" +
                        "'WEB3AioCashoutInqUnit'類型。");
        }
        //２）比較
        int l_intReturn = 0;
        
        if (l_datVal1 == null || l_datVal2 == null)
        {
            if (l_datVal1 == null && l_datVal2 == null)
            {
                l_intReturn = 0;       
            }
            else if (l_datVal2 == null && l_datVal1 != null)
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
                {
                    l_intReturn = 1;
                }
                else                                            //降順指定の場合
                {
                    l_intReturn = -1;
                }
            }
            else
            {
                if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
                {
                    l_intReturn = -1;
                }
                else                                            //降順指定の場合
                {
                    l_intReturn = 1;
                }
            }
        }

       
        if (WEB3DateUtility.compareToSecond(l_datVal1, l_datVal2) == 0)
        {
            l_intReturn = 0;
        }
        else if (WEB3DateUtility.compareToSecond(l_datVal1, l_datVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
            {
                l_intReturn = 1;
            }
            else                                            //降順指定の場合
            {
                l_intReturn = -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))    //昇順指定の場合
            {
                l_intReturn = -1;
            }
            else                                            //降順指定の場合
            {
                l_intReturn = 1;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_intReturn;       
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 4109ADFB030F
     */
    public boolean equals(Object l_obj) 
    {
        return super.equals(l_obj);
    }
}
@
