head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.23.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾊﾟｽﾜｰﾄﾞ変更顧客情報.部店コードComparator(WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import java.util.Comparator;

import webbroker3.accountinfo.message.WEB3AccInfoLoginPasswordChangeAccountInfo;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.util.WEB3LogUtility;


/**
 * (ﾊﾟｽﾜｰﾄﾞ変更顧客情報.部店コードComparator)<BR>
 * ﾊﾟｽﾜｰﾄﾞ変更顧客情報.部店コードComparator<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator implements Comparator 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     */
    private String orderBy;
    
    /**
     * (ﾊﾟｽﾜｰﾄﾞ変更顧客情報.部店コードComparator)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * 引数の値をthis.orderByにセットする。 <BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。 <BR>
     * <BR>
     * 　@A：昇順 <BR>
     * 　@D：降順 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator
     * @@roseuid 415A6E40009B
     */
    public WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator(String l_strOrderBy) 
    {
        final String STR_METHOD_NAME = " WEB3AdminAccInfoLoginPasswordChangeAccountInfoBranchCodeComparator(String)";
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
     * 部店コードの比較を行う。 <BR>
     * <BR>
     * １）　@引数のcast <BR>
     * 　@引数のﾊﾟｽﾜｰﾄﾞ変更顧客情報１、ﾊﾟｽﾜｰﾄﾞ変更顧客情報２を<BR>
     * ﾊﾟｽﾜｰﾄﾞ変更顧客情報型にcastする。 <BR>
     * <BR>
     * ２）　@比較 <BR>
     * 　@１）でcastしたﾊﾟｽﾜｰﾄﾞ変更顧客情報１、ﾊﾟｽﾜｰﾄﾞ変更顧客情報２<BR>
     * について <BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.部店コード < ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.部店コード）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.部店コード = ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.部店コード）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.部店コード > ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.部店コード）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.部店コード < ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.部店コード）の場合、<BR>
     * 正の整数（1）を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.部店コード = ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.部店コード）の場合、<BR>
     * 0を返却する。 <BR>
     * 　@・（ﾊﾟｽﾜｰﾄﾞ変更顧客情報１.部店コード > ﾊﾟｽﾜｰﾄﾞ変更顧客情報２.部店コード）の場合、<BR>
     * 負の整数（-1）を返却する。 <BR>
     * @@param l_loginPasswordChangeAccountInfo1 - ﾊﾟｽﾜｰﾄﾞ変更顧客情報１
     * @@param l_loginPasswordChangeAccountInfo2 - ﾊﾟｽﾜｰﾄﾞ変更顧客情報２
     * @@return int
     * @@roseuid 415A6EDC007B
     */
    public int compare(Object l_loginPasswordChangeAccountInfo1, Object l_loginPasswordChangeAccountInfo2) 
    {
        final String STR_METHOD_NAME = " compare(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strBranchCode1 = null;
        String l_strBranchCode2 = null;
         
        if ((l_loginPasswordChangeAccountInfo1 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo) 
            && (l_loginPasswordChangeAccountInfo2 instanceof WEB3AccInfoLoginPasswordChangeAccountInfo))
        {          
            l_strBranchCode1 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo1).branchCode;            
            l_strBranchCode2 = ((WEB3AccInfoLoginPasswordChangeAccountInfo)l_loginPasswordChangeAccountInfo2).branchCode;
        }
        else
        {
            throw new IllegalArgumentException("パラメータの類型が不正、該当する'WEB3AccInfoLoginPasswordChangeAccountInfo'");
        }
        
        int l_intResult;
        
        if (l_strBranchCode1 == null || l_strBranchCode2 == null)
        {
               
            if (l_strBranchCode1 == null && l_strBranchCode2 == null)
            {
                l_intResult = 0;
            }
            else if (l_strBranchCode1 == null)
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
            if (l_strBranchCode1.compareTo(l_strBranchCode2) < 0)
            {
                l_intResult = (WEB3AscDescDef.ASC.equals(this.orderBy)) ? -1 : 1;
            }
            else if (l_strBranchCode1.compareTo(l_strBranchCode2) > 0)
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
     * @@roseuid 415A6EDC008B
     */
    public boolean equals(Object l_obj) 
    {
        return false;
    }
}
@
