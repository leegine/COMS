head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物建日Comparator(WEB3FuturesOpenDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20   王暁傑 (Sinocom) 新規作成 
*/
package webbroker3.ifo.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (先物建日Comparator)<BR>
 */
public class WEB3FuturesOpenDateComparator implements Comparator 
{
    /**
    * ログユーティリティ<BR>
    */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3FuturesOpenDateComparator.class);
            
    /**
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * @@roseuid 40F7AE110109
     */
    public WEB3FuturesOpenDateComparator() 
    {     
    }
    
    /**
     * (先物建日Comparatorのコンスト)<BR>
     * 先物建日Comparatorのコンストラクタ。<BR>
     * <BR>
     * パラメータ.orderByをフィールドのorderByにセットする<BR>
     * @@param l_strOrderBy - ソートキーの昇順降順を示す。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@return Void
     * @@roseuid 40CFEBE90391
     */
    public WEB3FuturesOpenDateComparator(String l_strOrderBy) 
    {
        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) 
            && !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        this.orderBy = l_strOrderBy;
    }
    
    /**
     * 昇順、降順の指定にもとづく建日の比較を行う。<BR>
     * <BR>
     * １）パラメータのオブジェクトの判定<BR>
     * <BR>
     * 　@instanceofにて、引数の明細1、明細2が以下のクラスのどれかを判定する。<BR>
     * <BR>
     *     建玉明細クラス<BR>
     *     株価指数先物建玉照会明細クラス<BR>
     * <BR>
     * ２）比較<BR>
     * <BR>
     * 　@１）明細1と明細2の建日について比較を行う<BR>
     * <BR>
     * 　@[昇順指定の場合(this.orderBy == ”昇順”)]<BR>
     * <BR>
     * 　@・(明細1.建日 < 明細2.建日)の場合、負の整数(-1)を返却する<BR>
     * 　@・(明細1.建日 == 明細2.建日)の場合、0を返却する<BR>
     * 　@・(明細1.建日 > 明細2.建日)の場合、正の整数(1)を返却する<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”)]<BR>
     * <BR>
     * 　@・(明細1.建日 < 明細2.建日)の場合、正の整数(1)を返却する<BR>
     * 　@・(明細1.建日 == 明細2.建日)の場合、0を返却する<BR>
     * 　@・(明細1.建日 > 明細2.建日)の場合、負の整数(-1)を返却する<BR>
     * @@param l_obj1 - (obj1)<BR>
     * @@param l_obj2 - (obj2)<BR>
     * @@return int
     * @@roseuid 40CFEBE90393
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object l_obj1, Object l_obj2)";
        log.entering(STR_METHOD_NAME);
        //obj1の建日
        Date l_obj1OpenDate = null;
        //obj2の建建日
        Date l_obj2OpenDate = null;
        
        if (l_obj1 instanceof WEB3FuturesOptionsContractUnit && l_obj2 instanceof WEB3FuturesOptionsContractUnit)
        {
            //株価指数オプション建玉明細クラス
            l_obj1OpenDate = ((WEB3FuturesOptionsContractUnit)l_obj1).openDate;
            l_obj2OpenDate = ((WEB3FuturesOptionsContractUnit)l_obj2).openDate;            
        }
        else if (l_obj1 instanceof WEB3FuturesContractReferenceUnit && l_obj2 instanceof WEB3FuturesContractReferenceUnit)
        {
            //株価指数先物建玉照会明細クラス
            l_obj1OpenDate = ((WEB3FuturesContractReferenceUnit)l_obj1).openDate;
            l_obj2OpenDate = ((WEB3FuturesContractReferenceUnit)l_obj2).openDate;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3FuturesCloseMarginGroup' または 'WEB3FuturesContractReferenceUnit' 類型。");
        }     
        
        if (l_obj1OpenDate == null || l_obj2OpenDate == null)
        {
            int l_intResult;
               
            if (l_obj1OpenDate == null && l_obj2OpenDate == null)
            {
                l_intResult = 0;
            }
            else if (l_obj1OpenDate == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
                        
            return l_intResult;            
        }

        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            //昇順指定の場合
            return WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate);
        }
        else
        {
            //降順指定の場合
            return -(WEB3DateUtility.compareToDay(l_obj1OpenDate, l_obj2OpenDate));
        }
    }
    
    /**
     * パラメータのオブジェクトがこのコンパレータと等しいかどうかを判定する。<BR>
     * <BR>
     * スーパークラスのequalsをコールする。<BR>
     * @@param l_obj - (obj)
     * @@return boolean
     * @@roseuid 40CFEBE90396
     */
    public boolean equals(Object l_obj) 
    {
        //スーパークラスのequalsをコールする。
        if (l_obj instanceof  WEB3FuturesOpenDateComparator)
        {
            WEB3FuturesOpenDateComparator l_comparator =
                    ( WEB3FuturesOpenDateComparator) l_obj;
            if (this.orderBy.equals(l_comparator.orderBy))
            {
                return true;
            }
        }
        return false; 
    }
}
@
