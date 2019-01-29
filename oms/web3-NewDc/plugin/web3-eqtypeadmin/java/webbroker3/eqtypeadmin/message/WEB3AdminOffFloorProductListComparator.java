head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminOffFloorProductListComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
  Copyright        : (株)大和総研 証券ソリューションシステム第二部
  File Name        : 管理者立会外分売銘柄一覧Comparator(WEB3AdminOffFloorProductListComparator.java)
  Author Name      : Daiwa Institute of Research
  Revesion History : 2006/04/03 鈴木(SRA) 新規作成
 */
package webbroker3.eqtypeadmin.message;

import java.util.Comparator;
import java.util.Date;

import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;

/**
  * 管理者立会外分売銘柄一覧Comparator
  * @@version 1.0
  */
 
public class WEB3AdminOffFloorProductListComparator implements Comparator
{
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。  
     * 
     * 　@A：昇順   
     * 　@D：降順
     */
    private String orderBy;

    /**
     * 比較項目<BR>
     * 以下のいづれかの値がセットされる。<BR>
     * 　@・銘柄コード<BR>
     * 　@・市場コード<BR>
     * 　@・受付開始日時<BR>
     * 　@・受付終了日時<BR>
     */
    private String compareItem;

    /**
     * （compareの実装）<BR>
     * <BR>
     * 明細１，２について、this.比較項目で指定した項目の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast <BR>
     * パラメータ.明細1および2を、管理者立会外分売銘柄明細型にcastする。<BR>
     * <BR>
     * ３）　@比較  <BR>
     * 　@２）でcastした明細1、明細2について  <BR>
     * 　@this.比較項目に該当する項目の値を比較し、結果を返却する。 <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]  <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。<BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。<BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]  <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。<BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。<BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。<BR>
     * @@param l_obj1 - (明細１)<BR>
     * 管理者立会外分売銘柄明細オブジェクト１<BR>
     * @@param l_obj2 - (明細２)<BR>
     * 管理者立会外分売銘柄明細オブジェクト２<BR>
     * @@return int
     */
    public int compare(Object l_obj1, Object l_obj2)
    {
        // １）　@引数のcast 
        // パラメータ.明細1および2を、管理者立会外分売銘柄明細型にcastする。 
        WEB3AdminOffFloorProductGroup l_adminOffFloorProductGroup1 = (WEB3AdminOffFloorProductGroup)l_obj1;
        WEB3AdminOffFloorProductGroup l_adminOffFloorProductGroup2 = (WEB3AdminOffFloorProductGroup)l_obj2;
        int l_compare = 0;
       
        // ３）　@比較  
        // 　@２）でcastした明細1、明細2について  
        // 　@this.比較項目に該当する項目の値を比較し、結果を返却する。 
        // 
        // 　@[昇順指定の場合（this.orderBy == ”昇順”）]  
        // 　@・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。  
        // 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。  
        // 　@・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。  
        //
        // 　@[降順指定の場合（this.orderBy == ”降順”）]  
        // 　@・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。  
        // 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。  
        // 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。
          
        //　@比較項目 == 銘柄コード の場合<BR>
        if ("productCode".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.productKey.productCode,
                                    l_adminOffFloorProductGroup2.productKey.productCode);
        }
        //　@比較項目 == 市場コード の場合<BR>
        else if ("marketCode".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.productKey.marketCode,
                                    l_adminOffFloorProductGroup2.productKey.marketCode);
        }
        //　@比較項目 == 受付開始日時 の場合<BR>
        else if ("orderStartDatetime".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.orderStartDatetime,
                                    l_adminOffFloorProductGroup2.orderStartDatetime);
        }
        //　@比較項目 == 受付終了日時 の場合<BR>
        else if ("orderEndDatetime".equals(this.compareItem))
        {
            l_compare = compare(l_adminOffFloorProductGroup1.productKey.orderEndDatetime,
                                    l_adminOffFloorProductGroup2.productKey.orderEndDatetime);
        }
        else
        {
            throw new IllegalArgumentException("パラメータの型が'WEB3AdminOffFloorProductGroup' 以外です。");
        }
        return l_compare;
    }

    /**
     * （compareの実装）<BR> 
     * 昇順、降順の指定にもとづくString型の比較を行う。<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.l_strVal1がパラメータl_strVal2より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_strVal1がパラメータ.l_strVal2より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.l_strVal1がパラメータ.l_strVal2より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_strVal1がパラメータ.l_strVal2より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_strVal1 - 比較項目1
     * @@param l_strVal2 - 比較項目2
     * @@return int
     */
    private int compare(String l_strVal1,String l_strVal2)
    {
        if (l_strVal1.equals(l_strVal2))
        {
            return 0;
        }
        else if (l_strVal1.compareTo(l_strVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * （compareの実装）<BR> 
     * 昇順、降順の指定にもとづくDate型の比較を行う。<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.l_datVal1がパラメータl_datVal2より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_datVal1がパラメータ.l_datVal2より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.l_datVal1がパラメータ.l_datVal2より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_datVal1がパラメータ.l_datVal2より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_datVal1 - 比較項目1
     * @@param l_datVal2 - 比較項目2
     * @@return int
     */
    private int compare(Date l_datVal1 ,Date l_datVal2)
    {
        if (WEB3DateUtility.compareToMinute(l_datVal1, l_datVal2) == 0)
        {
            return 0;
        }
        else if (WEB3DateUtility.compareToMinute(l_datVal1,l_datVal2) > 0)
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;        
            }
            else
            {
                return 1;
            }
        }
    }
    
    /**
     * （compareの実装）<BR> 
     * 昇順、降順の指定にもとづくdouble型の比較を行う。<BR>
     * <BR>
     * 昇順指定の場合、<BR>
     * パラメータ.l_dblVal1がパラメータl_dblVal2より小さい場合は負の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_dblVal1がパラメータ.l_dblVal2より大きい場合は正の整数を返却する<BR>
     * <BR>
     * 降順指定の場合、<BR>
     * パラメータ.l_dblVal1がパラメータ.l_dblVal2より小さい場合は正の整数、<BR>
     * 両方が等しい場合は0、<BR>
     * パラメータ.l_dblVal1がパラメータ.l_dblVal2より大きい場合は負の整数を返却する<BR>
     * <BR>
     * 昇降順の判定はコンストラクタでセットされたorderByの値を用いる<BR>
     * @@param l_dblVal1 - 比較項目1
     * @@param l_dblVal2 - 比較項目2
     * @@return int
     */
    private int compare(double l_dblVal1, double l_dblVal2)
    {       
        if (l_dblVal1 == l_dblVal2)
        {
            return 0;
        }
        else if (l_dblVal1 > l_dblVal2) 
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return 1;
            }
            else 
            {
                return -1; 
            }                       
        }
        else
        {
            if (WEB3AscDescDef.ASC.equals(this.orderBy))
            {
                return -1;
            }
            else 
            {
                return 1; 
            }                                     
        }         
    }

    /**
     * （equalsの実装）<BR>
     * 未使用<BR>
     * <BR>
     * falseを返却する。<BR>
     * @@param arg0
     * @@return boolean
     */
    public boolean equals(java.lang.Object arg0)
    {
        return false;
    }

    /**
     * (管理者立会外分売銘柄一覧Comparator)<BR>
     * コンストラクタ。<BR>
     * 引数の値をthis.orderBy、比較項目にセットする。<BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>  
     *  <BR>
     *　@A：昇順 <BR>
     *　@D：降順 <BR>
     * @@param l_strComarator - 比較項目<BR>
     *<BR>
     * 以下のいづれかの値がセットされる。<BR>
     *　@・銘柄コード<BR>
     *　@・市場コード<BR>
     *　@・受付開始日時<BR>
     *　@・受付終了日時<BR>
     */
    public WEB3AdminOffFloorProductListComparator(
        String l_strOrderBy,
        String l_strComparator)
    {
        this.orderBy = l_strOrderBy;
        this.compareItem = l_strComparator;
    }
}
@
