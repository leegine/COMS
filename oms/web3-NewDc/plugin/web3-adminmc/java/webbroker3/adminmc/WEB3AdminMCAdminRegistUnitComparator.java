head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminRegistUnitComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者登録情報Comparator(WEB3AdminMCAdminRegistUnitComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/14  温 顕 法@ (中訊) 新規作成
*/
package webbroker3.adminmc;


import java.util.Comparator;
import java.util.Date;

import webbroker3.adminmc.define.WEB3AdminMCAdminRegistUnitDef;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistUnit;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者登録情報Comparator)<BR>
 * 管理者登録情報Comparator<BR>
 */
public class WEB3AdminMCAdminRegistUnitComparator implements Comparator 
{
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminRegistUnitComparator.class);
     
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
     * 　@管理者登録情報.部店コード <BR>
     * 　@管理者登録情報.管理者コード <BR>
     * 　@管理者登録情報.メールアドレス <BR>
     * 　@管理者登録情報.権限レベルコード<BR>
     * 　@管理者登録情報.エラー回数<BR>
     * 　@管理者登録情報.更新日時<BR>
     * 　@管理者登録情報.更新者コード<BR>
     * <BR>
     */
    private String keyItem;
    
    /**
     * @@roseuid 41BE403A0119
     */
    public WEB3AdminMCAdminRegistUnitComparator() 
    {
     
    }
    
    /**
     * (管理者登録情報Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数の値をthis.orderBy，キー項目にセットする。 <BR>
     * <BR>
     * @@param l_strOrderBy - (orderBy)<BR>
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR> 
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * <BR>
     * @@param l_strKeyItem - (キー項目)<BR>
     * ソートキーのキー項目<BR>
     * <BR>
     * 以下のいずれか。<BR>
     * <BR>
     * 　@管理者登録情報.部店コード <BR>
     * 　@管理者登録情報.管理者コード <BR>
     * 　@管理者登録情報.メールアドレス <BR>
     * 　@管理者登録情報.権限レベルコード<BR>
     * 　@管理者登録情報.エラー回数<BR>
     * 　@管理者登録情報.更新日時<BR>
     * 　@管理者登録情報.更新者コード<BR>
     * <BR>
     * @@return webbroker3.adminmc.WEB3AdminMCAdminRegistUnitComparator
     * @@roseuid 41B6879C00B5
     */
    public WEB3AdminMCAdminRegistUnitComparator(String l_strOrderBy, String l_strKeyItem) 
    {
        final String STR_METHOD_NAME = " WEB3AdminMCAdminRegistUnitComparator(String l_strOrderBy, String l_strKeyItem) ";
        log.entering(STR_METHOD_NAME );
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            String l_strErrorInfo = "パラメータの値が'A：昇順'、'D：降順'以外です。";
            log.error(STR_METHOD_NAME + l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);

        }

        if (l_strKeyItem == null || 
            (!(WEB3AdminMCAdminRegistUnitDef.BRANCHCODE.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.ADMINISTRATORCODE.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.MAILADDRESS.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.PERMISSIONLEVEL.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.ERRORCOUNT.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.UPDATETIMESTAMP.equals(l_strKeyItem)) &&
             !(WEB3AdminMCAdminRegistUnitDef.UPDATERCODE.equals(l_strKeyItem))))
        {
            String l_strErrorInfo = "ソートキー.キー項目の値が管理者登録情報以外の場合のエラー.";
            log.error(STR_METHOD_NAME + l_strErrorInfo);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorInfo);

        }
  
        this.orderBy = l_strOrderBy;
        this.keyItem = l_strKeyItem;
        
        log.exiting(STR_METHOD_NAME);
    	
     
    }
    
    /**
     * 管理者登録情報項目値の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数の管理者登録情報１、管理者登録情報２を管理者登録情報型にcastする。 <BR>
     * <BR>
     * ２）　@項目値取得<BR>
     * 　@this.キー項目が示す項目値を、管理者登録情報１、管理者登録情報２よりそれぞれ取得する。<BR>
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
     * @@param l_adminRegistUnit1 - (管理者登録情報１)<BR>
     * 管理者登録情報１<BR>
     * @@param l_adminRegistUnit2 - (管理者登録情報２)<BR>
     * 管理者登録情報２<BR>
     * @@return int
     * @@roseuid 41B6879501DE
     */
    public int compare(Object l_adminRegistUnit1, Object l_adminRegistUnit2) 
    {
        final String STR_METHOD_NAME = " compare(Object l_adminRegistUnit1, Object l_adminRegistUnit2) ";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminMCAdminRegistUnit l_unit1 = null;
        WEB3AdminMCAdminRegistUnit l_unit2 = null;
        
        if (l_adminRegistUnit1 == null || l_adminRegistUnit2 == null)
        {
            log.debug("input parameters are null. ");
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        else if ((l_adminRegistUnit1 instanceof WEB3AdminMCAdminRegistUnit) && (l_adminRegistUnit2 instanceof WEB3AdminMCAdminRegistUnit))
        {
            l_unit1 = (WEB3AdminMCAdminRegistUnit)l_adminRegistUnit1;
            l_unit2 = (WEB3AdminMCAdminRegistUnit)l_adminRegistUnit2;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new IllegalArgumentException(
                "パラメータの類型が不正、該当する'WEB3AdminMCAdminRegistUnit'"); 
        }
        
        Object l_keyItem1 = null;
        Object l_keyItem2 = null;        
        
        if (WEB3AdminMCAdminRegistUnitDef.BRANCHCODE.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.branchCode;
            l_keyItem2 = l_unit2.branchCode;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.ADMINISTRATORCODE.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.administratorCode;
            l_keyItem2 = l_unit2.administratorCode;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.MAILADDRESS.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.mailAddress;
            l_keyItem2 = l_unit2.mailAddress;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.PERMISSIONLEVEL.equals(this.keyItem))
        {
            l_keyItem1 = new Integer(l_unit1.permissionLevel);
            l_keyItem2 = new Integer(l_unit2.permissionLevel);
        }
        else if (WEB3AdminMCAdminRegistUnitDef.ERRORCOUNT.equals(this.keyItem))
        {
            if (l_unit1.errorCount == null)
            {
                l_keyItem1 = new Integer(0);
            }
            else
            {
                l_keyItem1 = new Integer(l_unit1.errorCount);
            }
            if (l_unit2.errorCount == null)
            {
                l_keyItem2 = new Integer(0);
            }
            else
            {
                l_keyItem2 = new Integer(l_unit2.errorCount);
            }
        }
        else if (WEB3AdminMCAdminRegistUnitDef.UPDATETIMESTAMP.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.updateTimeStamp;
            l_keyItem2 = l_unit2.updateTimeStamp;
        }
        else if (WEB3AdminMCAdminRegistUnitDef.UPDATERCODE.equals(this.keyItem))
        {
            l_keyItem1 = l_unit1.updaterCode;
            l_keyItem2 = l_unit2.updaterCode;
        }
        
        int l_intResult;
        
        if (l_keyItem1 == null || l_keyItem2 == null)
        {
               
            if (l_keyItem1 == null && l_keyItem2 == null)
            {
                l_intResult = 0;
            }
            else if (l_keyItem1 == null)
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
            if (compareObj(l_keyItem1, l_keyItem2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (compareObj(l_keyItem1, l_keyItem2) > 0)
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
     * @@l_intResult boolean
     * @@roseuid 41B6879501E1
     */
    public boolean equals(Object l_obj) 
    {
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
