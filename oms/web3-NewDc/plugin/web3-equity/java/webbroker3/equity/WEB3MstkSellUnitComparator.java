head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkSellUnitComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資売付明細Comparator(WEB3MstkSellUnitComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 李海波 (中訊) 新規作成
                   2004/01/05 岡村和明 (SRA) JavaDoc修正
*/

package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MstkSellUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * （株式ミニ投資売付明細Comparator）。<BR>
 * <BR>
 * 株式ミニ投資売付明細Comparator
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3MstkSellUnitComparator implements Comparator 
{
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MstkSellUnitComparator.class);
    /**
     * （orderBy）。<BR>
     * <BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順<BR>
     * 　@D：降順
     */
    private String orderBy;
    
    /**
     * （比較項目）。<BR>
     * <BR>
     * 以下のどちらかの項目が指定される。<BR>
     * 　@株式ミニ投資売付明細.銘柄コード<BR>
     * 　@株式ミニ投資売付明細.市場コード
     */
    private String compareItem;
    
    /**
     * 
     */
    public WEB3MstkSellUnitComparator() 
    {
     
    }
    
    /**
     * （株式ミニ投資売付明細Comparator）。<BR>
     * <BR>
     * コンストラクタ。<BR>
     * 引数の値をthis.orderBy，this.比較項目にセットする。<BR>
     * @@param l_strOrderBy （orderBy）<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * 　@A：昇順<BR>
     * 　@D：降順
     * @@param l_strCompareItem (比較項目)<BR>
     * compareにて使用する比較項目<BR>
     * <BR>
     * 以下のどちらかの項目が指定される。<BR>
     * 　@株式ミニ投資売付明細.銘柄コード<BR>
     * 　@株式ミニ投資売付明細.市場コード
     */
    public WEB3MstkSellUnitComparator(String l_strOrderBy, String l_strCompareItem) 
    {

        final String STR_METHOD_NAME =" WEB3MstkSellUnitComparator(String, String)";
        log.entering(STR_METHOD_NAME);
        log.debug("引数1の値 = " + l_strOrderBy);
        log.debug("引数2の値 = " + l_strCompareItem);
        
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");
        }
        this.orderBy = l_strOrderBy;
        
        if(l_strCompareItem == null || 
            (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strCompareItem) && !WEB3EquityKeyItemDef.TRADEMARKET.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が'銘柄コード'、'市場コード'以外です");
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3IpoOrderAcceptStatusComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * （compare）。<BR>
     * <BR>
     * （compareの実装）<BR>
     * <BR>
     * 株式ミニ投資売付明細１，２について、<BR>
     * this.比較項目で指定した項目の比較を行う。  <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の株式ミニ投資売付明細１、<BR>
     * 株式ミニ投資売付明細２を株式ミニ投資売付明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * 　@１）でcastした株式ミニ投資売付明細１、<BR>
     * 株式ミニ投資売付明細２について、 <BR>
     * this.比較項目に該当する項目の値を比較し、結果を返却する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * ・（株式ミニ投資売付明細１.比較項目 < <BR>株式ミニ投資売付明細２.比較項目）の場合、<BR>
     *     負の整数（-1）を返却する。 <BR>
     * ・（株式ミニ投資売付明細１.比較項目 = 株式ミニ投資売付明細２.比較項目）の場合、<BR>
     *     0を返却する。 <BR>
     * ・（株式ミニ投資売付明細１.比較項目 > 株式ミニ投資売付明細２.比較項目）の場合、<BR>
     *     正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * ・（株式ミニ投資売付明細１.比較項目 < 株式ミニ投資売付明細２.比較項目）の場合、<BR>
     *    正の整数（1）を返却する。 <BR>
     * ・（株式ミニ投資売付明細１.比較項目 = 株式ミニ投資売付明細２.比較項目）の場合、<BR>
     *    0を返却する。 <BR>
     * ・（株式ミニ投資売付明細１.比較項目 > 株式ミニ投資売付明細２.比較項目）の場合、<BR>
     *    負の整数（-1）を返却する。 <BR>
     * @@param l_obj1 （株式ミニ投資売付明細１）<BR>
     * 株式ミニ投資売付明細オブジェクト１
     * @@param l_obj2 （株式ミニ投資売付明細２）<BR>
     * 株式ミニ投資売付明細オブジェクト２
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = "compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkSellUnit l_unit1 = null;
        WEB3MstkSellUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3MstkSellUnit) && (l_obj2 instanceof WEB3MstkSellUnit))
        {
            l_unit1 = (WEB3MstkSellUnit)l_obj1;
            l_unit2 = (WEB3MstkSellUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3MstkSellUnit'"); 
        }
        

        Comparable l_cmpKeyItem1;
        Comparable l_cmpKeyItem2;
        
        if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
        {
            l_cmpKeyItem1 = l_unit1.productCode;
            l_cmpKeyItem2 = l_unit2.productCode;
        }
        else
        {
            l_cmpKeyItem1 = Integer.valueOf(l_unit1.marketCode);
            l_cmpKeyItem2 = Integer.valueOf(l_unit2.marketCode);
        }
        
        if (l_cmpKeyItem1 == null && l_cmpKeyItem2 == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        if (l_cmpKeyItem1.compareTo(l_cmpKeyItem2) == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_cmpKeyItem2 == null || (l_cmpKeyItem1 != null && 
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_cmpKeyItem1 == null || (l_cmpKeyItem2 != null &&
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) < 0))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
        else
        {
            if (l_cmpKeyItem2 == null || (l_cmpKeyItem1 != null &&
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_cmpKeyItem1 == null || (l_cmpKeyItem2 != null &&
                l_cmpKeyItem1.compareTo(l_cmpKeyItem2) < 0))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return 0;
            }
        }
        
    }
    
    /**
     * （equals）。<BR>
     * <BR>
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。<BR>
     * @@param l_arg0 （arg0）
     * @@return boolean
     */
    public boolean equals(Object l_arg0) 
    {
        return false;
    }
}
@
