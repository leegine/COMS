head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾊﾟｽﾜｰﾄﾞ変更顧客情報.更新日Comparator(WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import java.util.Comparator;
import java.util.Date;

import webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (ﾊﾟｽﾜｰﾄﾞ変更顧客情報.更新日Comparator)<BR>
 * ﾊﾟｽﾜｰﾄﾞ変更顧客情報.更新日Comparator<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator.class);   
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * (ﾊﾟｽﾜｰﾄﾞ変更顧客情報.更新日Comparator)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator
     * @@roseuid 416B96CD0088
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3AdminAccInfoLoginPasswordChangeAccountInfoUpdatedDateComparator(String)";
        log.entering(STR_METHOD_NAME);

        
        if (l_strOrderBy == null || (!WEB3AscDescDef.ASC.equals(l_strOrderBy) && !WEB3AscDescDef.DESC.equals(l_strOrderBy)))
        {
            throw new IllegalArgumentException("パラメータの値が'A：昇順'、'D：降順'以外です");            
        }
        
        this.orderBy = l_strOrderBy;        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （compareの実装） <BR>
     * <BR>
     * 更新日の比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数のﾊﾟｽﾜｰﾄﾞ変更顧客情報１、ﾊﾟｽﾜｰﾄﾞ変更顧客情報２を<BR>
     * ﾊﾟｽﾜｰﾄﾞ変更顧客情報型にcastする。 <BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたﾊﾟｽﾜｰﾄﾞ変更顧客情報１、ﾊﾟｽﾜｰﾄﾞ変更顧客情報２について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.更新日 < ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.更新日）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.更新日 = ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.更新日）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.更新日 > ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.更新日）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.更新日 < ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.更新日）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.更新日 = ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.更新日）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.更新日 > ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.更新日）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * @@param l_loginPasswordChangeAccountInfo1 - ﾊﾟｽﾜｰﾄﾞ変更顧客情報１
     * @@param l_loginPasswordChangeAccountInfo2 - ﾊﾟｽﾜｰﾄﾞ変更顧客情報２
     * @@return int
     * @@roseuid 416B96CD008A
     */
    public int compare(Object l_loginPasswordChangeAccountInfo1, Object l_loginPasswordChangeAccountInfo2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        Date l_datUpdatedDate1 = null;
        Date l_datUpdatedDate2 = null;
         
        if ((l_loginPasswordChangeAccountInfo1 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo) 
            && (l_loginPasswordChangeAccountInfo2 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo))
        {          
            l_datUpdatedDate1 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo1).updateDate;            
            l_datUpdatedDate2 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo2).updateDate;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3AccInfoLoginPasswordChangeAccountInfo'");
        }
        
        int l_intResult;
        
        if (l_datUpdatedDate1 == null || l_datUpdatedDate2 == null)
        {
               
            if (l_datUpdatedDate1 == null && l_datUpdatedDate2 == null)
            {
                l_intResult = 0;
            }
            else if (l_datUpdatedDate1 == null)
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
            if (WEB3DateUtility.compareToSecond(l_datUpdatedDate1, l_datUpdatedDate2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (WEB3DateUtility.compareToSecond(l_datUpdatedDate1, l_datUpdatedDate2) > 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? 1 : -1;
            }
            else
            {
                l_intResult = 0;
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_intResult;
    }
    
    /**
     * （equalsの実装） <BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。 <BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 416B96CD008D
     */
    public boolean equals(Object l_obj) 
    {
        return false;
    }

}
@
