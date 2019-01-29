head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCCCOperatorRegistUnitComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright          : (株)大和総研 証券ソリューションシステム第二部
File Name          : CCオペレータ登録情報Comparator(WEB3AdminMCCCOperatorRegistUnitComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14 範慧琴 (中訊) 新規作成
*/

package webbroker3.adminmc;

import java.util.Comparator;
import java.util.Date;

import webbroker3.adminmc.define.WEB3AdminMCCCOperatorRegistUnitDef;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistUnit;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (CCオペレータ登録情報Comparator)<BR>
 * CCオペレータ登録情報Comparator<BR>
 * @@author 範慧琴 <BR>
 * @@version 1.0 <BR>
 */
public class WEB3AdminMCCCOperatorRegistUnitComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。
     */
    private  static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCCCOperatorRegistUnitComparator.class);    

    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * <BR>
     */
    private String orderBy;
    
    /**
     * (キー項目)<BR>
     * ソートキーのキー項目<BR>
     * <BR>
     * 以下のいずれか。<BR>
     * <BR>
     *  CCオペレータ登録情報.部店コード <BR>
     *  CCオペレータ登録情報.オペレータコード <BR>
     *  CCオペレータ登録情報.代行注文区分<BR>
     *  CCオペレータ登録情報.所属コード<BR>
     *  CCオペレータ登録情報.エラー回数<BR>
     *  CCオペレータ登録情報.更新日時<BR>
     *  CCオペレータ登録情報.更新者コード<BR>
     * <BR>
     */
    private String keyItem;
    
    /**
     * @@roseuid 41BE403A004E
     */
    public WEB3AdminMCCCOperatorRegistUnitComparator() 
    {
     
    }
    
    /**
     * (CCオペレータ登録情報Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderBy，キー項目にセットする。 <BR>
     * <BR>
     * @@param l_strOrderBy - (orderby)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * <BR>
     * @@param l_strKeyItem - (キー項目)<BR>
     * ソートキーのキー項目<BR>
     * <BR>
     * 以下のいずれか。<BR>
     * <BR>
     *  CCオペレータ登録情報.部店コード <BR>
     *  CCオペレータ登録情報.オペレータコード <BR>
     *  CCオペレータ登録情報.代行注文区分<BR>
     *  CCオペレータ登録情報.所属コード<BR>
     *  CCオペレータ登録情報.エラー回数<BR>
     *  CCオペレータ登録情報.更新日時<BR>
     *  CCオペレータ登録情報.更新者コード<BR>
     * <BR>
     * @@return webbroker3.adminmc.WEB3AdminMCCCOperatorRegistUnitComparator
     * @@roseuid 41B68EAF02CD
     */
    public WEB3AdminMCCCOperatorRegistUnitComparator(String l_strOrderBy, String l_strKeyItem) 
    {
        final  String  STR_METHOD_NAME = " WEB3AdminMCCCOperatorRegistUnitComparator(String l_strOrderBy, String l_strKeyItem)";
        log.entering(STR_METHOD_NAME);
        
        // 引数の値をthis.orderBy，キー項目にセットする。 
        if(l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {           
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");            
        }
        else if (l_strKeyItem == null ||
            (!WEB3AdminMCCCOperatorRegistUnitDef.BRANCH_CODE.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.OPERATOR_CODE.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.ACCOUNT_ORDER_DIV.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.DEPARTMENT_CODE.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.ERROR_COUNT.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.UPDATE_TIME_STAMP.equals(l_strKeyItem)&&
             !WEB3AdminMCCCOperatorRegistUnitDef.UPDATER_CODE.equals(l_strKeyItem)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException("パラメータの値が未定義の値である.");                         
        }
               
        this.orderBy = l_strOrderBy; 
        this.keyItem = l_strKeyItem;  
        
        log.exiting(STR_METHOD_NAME);  
         
    }
    
    /**
     * CCオペレータ登録情報項目値の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数のCCオペレータ登録情報１、CCオペレータ登録情報２をCCオペレータ登録情報型にcastする。 <BR>
     * <BR>
     * ２）　@項目値取得<BR>
     * 　@this.キー項目が示す項目値を、CCオペレータ登録情報１、CCオペレータ登録情報２よりそれぞれ取得する。<BR>
     * <BR>
     * ３）　@項目値比較<BR>
     * 　@２）で取得した項目値１、項目値２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（項目値１ < 項目値２）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（項目値１ = 項目値２）の場合、0を返却する。 <BR>
     * 　@・（項目値１ > 項目値２）の場合、正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（項目値１ < 項目値２）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（項目値１ = 項目値２）の場合、0を返却する。 <BR>
     * 　@・（項目値１ > 項目値２）の場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * @@param l_ccOperatorRegistUnit1 - (CCオペレータ登録情報１)<BR>
     * CCオペレータ登録情報１<BR>
     * @@param l_ccOperatorRegistUnit2 - (CCオペレータ登録情報２)<BR>
     * CCオペレータ登録情報２<BR>
     * @@return int
     * @@roseuid 41B68EAF02C8
     */
    public int compare(Object l_ccOperatorRegistUnit1, Object l_ccOperatorRegistUnit2) 
    {
        final  String  STR_METHOD_NAME = " compare(Object l_ccOperatorRegistUnit1, Object l_ccOperatorRegistUnit2) ";
        log.entering(STR_METHOD_NAME);
        
        Object l_item1 = null;
        Object l_item2 = null;
        
        // １）　@引数のcast 
        // 　@引数のCCオペレータ登録情報１、CCオペレータ登録情報２をCCオペレータ登録情報型にcastする。
        if (l_ccOperatorRegistUnit1 == null || l_ccOperatorRegistUnit2 == null)
        {
            log.debug("input parameters are null. ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if ((l_ccOperatorRegistUnit1 instanceof WEB3AdminMCCCOperatorRegistUnit)&&(l_ccOperatorRegistUnit2 instanceof WEB3AdminMCCCOperatorRegistUnit))
        {
            WEB3AdminMCCCOperatorRegistUnit l_unit1 = (WEB3AdminMCCCOperatorRegistUnit)l_ccOperatorRegistUnit1;
            WEB3AdminMCCCOperatorRegistUnit l_unit2 = (WEB3AdminMCCCOperatorRegistUnit)l_ccOperatorRegistUnit2;
            
            // ２）　@項目値取得
            // 　@this.キー項目が示す項目値を、CCオペレータ登録情報１、CCオペレータ登録情報２よりそれぞれ取得する。
            if (WEB3AdminMCCCOperatorRegistUnitDef.BRANCH_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.branchCode;
                l_item2 = l_unit2.branchCode;    
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.OPERATOR_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.operatorCode;
                l_item2 = l_unit2.operatorCode;                    
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.ACCOUNT_ORDER_DIV.equals(this.keyItem))
            {
                l_item1 = l_unit1.accountOrderDiv;
                l_item2 = l_unit2.accountOrderDiv;                
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.DEPARTMENT_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.departmentCode;
                l_item2 = l_unit2.departmentCode;                                
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.ERROR_COUNT.equals(this.keyItem))
            {
                if (l_unit1.errorCount == null)
                {
                    l_item1 = new Integer(0);
                }
                else
                {
                    l_item1 = new Integer(l_unit1.errorCount);
                }
                if (l_unit2.errorCount == null)
                {
                    l_item2 = new Integer(0);
                }
                else
                {
                    l_item2 = new Integer(l_unit2.errorCount);
                }
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.UPDATE_TIME_STAMP.equals(this.keyItem))
            {
                l_item1 = l_unit1.updateTimeStamp;
                l_item2 = l_unit2.updateTimeStamp;
            }
            else if (WEB3AdminMCCCOperatorRegistUnitDef.UPDATER_CODE.equals(this.keyItem))
            {
                l_item1 = l_unit1.updaterCode;
                l_item2 = l_unit2.updaterCode;                
            }            
            //WEB3DateUtility
        }
        else
        {
            log.debug("parameter type is wrong. ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,STR_METHOD_NAME);    
        }

        int l_intResult;
        
        if (l_item1 == null || l_item2 == null)
        {
               
            if (l_item1 == null && l_item2 == null)
            {
                l_intResult = 0;
            }
            else if (l_item1 == null)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }            
                        
        } 
        else
        {
            if (compareObj(l_item1, l_item2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (compareObj(l_item1, l_item2) > 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
            else
            {
                l_intResult = 0;
            }
                
        }
        return l_intResult;             
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * <BR>
     * @@param l_obj - (arg0)<BR>
     * @@return boolean
     * @@roseuid 41B68EAF02CB
     */
    public boolean equals(Object l_obj) 
    {
        final  String  STR_METHOD_NAME = " equals(Object l_obj) ";
        log.entering(STR_METHOD_NAME);
        log.exiting(STR_METHOD_NAME);
        return false;
    }
    
    /**
     * 二つのObjectの比較を行う。
     *
     * @@param l_obj1
     * @@param l_obj2
     * @@return 
     */
    private int compareObj(Object l_obj1, Object l_obj2)
    {        
        int l_intResult;
        
        if ((l_obj1 instanceof String) && (l_obj2 instanceof String))
        {
            l_intResult = ((String)l_obj1).compareTo((String)l_obj2);
        }
        else if ((l_obj1 instanceof Date) && (l_obj2 instanceof Date))
        {
            l_intResult = WEB3DateUtility.compareToSecond((Date)l_obj1, (Date)l_obj2);
        }
        else if ((l_obj1 instanceof Integer) && (l_obj2 instanceof Integer))
        {
            l_intResult = ((Integer)l_obj1).compareTo((Integer)l_obj2);
        }
        else
        {
            throw new IllegalArgumentException("[Error]Parameter type is wrong! [l_obj1]=" + l_obj1 + " [l_obj2]" + l_obj2);
        }  
        
        return l_intResult;      
    }
}
@
