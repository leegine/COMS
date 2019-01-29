head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資残高照会Comparator(WEB3MstkBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/07 沢村　@仁士(SRA) 新規作成
*/

package webbroker3.equity;

import java.util.Comparator;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.message.WEB3MstkBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;


/**
 * （株式ミニ投資残高照会Comparator）。<BR>
 * <BR>
 * 株式ミニ投資残高照会Comparator<BR>
 */
public class WEB3MstkBalanceReferenceComparator implements Comparator 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MstkBalanceReferenceComparator.class);
        
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * (比較項目)<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@株式ミニ投資残高照会明細.銘柄コード<BR>
     * 　@株式ミニ投資残高照会明細.口座区分<BR>
     * <BR>
     */
    private String compareItem;
    
    /**
     * @@roseuid 4206CD4903B5<BR>
     */
    public WEB3MstkBalanceReferenceComparator() 
    {
     
    }
    
    /**
     * コンストラクタ。<BR>
     * 引数の値をthis.orderBy，this.比較項目にセットする。<BR>
     * @@param orderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順  <BR>
     * 　@D：降順 <BR>
     * @@param l_strOrderBy - (比較項目) compareにて使用する比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@株式ミニ投資残高照会明細.銘柄コード<BR>
     * 　@株式ミニ投資残高照会明細.口座区分<BR>
     * <BR>
     * @@return WEB3MstkBalanceReferenceComparator<BR>
     * @@roseuid 41C66866001E<BR>
     */
    public WEB3MstkBalanceReferenceComparator(String l_strOrderBy, String l_strCompareItem ) 
    {
        final String STR_METHOD_NAME ="WEB3MstkBalanceReferenceComparator(String, String)";
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
            (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(l_strCompareItem)
            && !WEB3EquityKeyItemDef.ACCOUNTTYPE.equals(l_strCompareItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの値が'銘柄コード'、'口座区分'以外です" );
        }
        this.compareItem = l_strCompareItem;         
        
        log.debug("WEB3EquityBalanceReferenceComparator().....OK>>>>>");
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 株式ミニ投資残高照会明細１，２について、this.比較項目で指定した項目の比較を行う<BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の株式ミニ投資残高照会明細１、株式ミニ投資残高照会明細２を株式ミニ投資残高<BR>
     * <BR>
     * 照会明細型にcastする。 <BR>
     * <BR>
     * ２）　@比較  <BR>
     * 　@１）でcastした株式ミニ投資残高照会明細１、株式ミニ投資残高照会明細２について、<BR>
     * <BR>
     * <BR>
     * this.比較項目に該当する項目の値を比較し、結果を返却する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（株式ミニ投資残高照会明細１.比較項目 < <BR>
     * 株式ミニ投資残高照会明細２.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（株式ミニ投資残高照会明細１.比較項目 = <BR>
     * 株式ミニ投資残高照会明細２.比較項目）の場合、0を返却する。 <BR>
     * 　@・（株式ミニ投資残高照会明細１.比較項目 > <BR>
     * 株式ミニ投資残高照会明細２.比較項目）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（株式ミニ投資残高照会明細１.比較項目 < <BR>
     * 株式ミニ投資残高照会明細２.比較項目）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（株式ミニ投資残高照会明細１.比較項目 = <BR>
     * 株式ミニ投資残高照会明細２.比較項目）の場合、0を返却する。 <BR>
     * 　@・（株式ミニ投資残高照会明細１.比較項目 > <BR>
     * 株式ミニ投資残高照会明細２.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * @@param l_obj1 - (株式ミニ投資残高照会明細１) 株式ミニ投資残高照会明細オブジェクト１<BR>
     * @@param l_obj2 - (株式ミニ投資残高照会明細２) 株式ミニ投資残高照会明細オブジェクト２<BR>
     * @@return int<BR>
     * @@roseuid 41C66866003E<BR>
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        WEB3MstkBalanceReferenceDetailUnit l_unit1 = null;
        WEB3MstkBalanceReferenceDetailUnit l_unit2 = null;

        if((l_obj1 instanceof WEB3MstkBalanceReferenceDetailUnit) && (l_obj2 instanceof WEB3MstkBalanceReferenceDetailUnit))
        {
            l_unit1 = (WEB3MstkBalanceReferenceDetailUnit)l_obj1;
            l_unit2 = (WEB3MstkBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの型がWEB3MstkBalanceReferenceDetailUnit以外です。"); 
        }
        

        String l_strKeyItem1 = null;
        String l_strKeyItem2 = null;
            
        if(WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.compareItem))
        {
            // 比較項目 = 銘柄コード
            l_strKeyItem1 = l_unit1.productCode;
            l_strKeyItem2 = l_unit2.productCode;
        }
        else
        {
            // 比較項目 = 口座区分
            l_strKeyItem1 = l_unit1.taxType;
            l_strKeyItem2 = l_unit2.taxType;
        }
            
        if (l_strKeyItem1 == null && l_strKeyItem2 == null)
        {
            log.exiting(STR_METHOD_NAME);
            return 0;
        }
            
        // 昇順ソートの場合
        if(WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strKeyItem2 == null || (l_strKeyItem1 != null && 
                l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return 1;
            }
            else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
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
        // 降順ソートの場合
        else
        {
            if (l_strKeyItem2 == null || (l_strKeyItem1 != null &&
                l_strKeyItem1.compareTo(l_strKeyItem2) > 0))
            {
                log.exiting(STR_METHOD_NAME);
                return -1;
            }
            else if (l_strKeyItem1 == null || (l_strKeyItem2 != null &&
                l_strKeyItem1.compareTo(l_strKeyItem2) < 0))
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
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * <BR>
     * @@param arg0<BR>
     * @@return boolean<BR>
     * @@roseuid 41C66866005D<BR>
     */
    public boolean equals(Object arg0) 
    {
        return false;
    }
}
@
