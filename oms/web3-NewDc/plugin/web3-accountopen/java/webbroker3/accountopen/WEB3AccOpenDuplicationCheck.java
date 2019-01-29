head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設重複チェック(WEB3AccOpenDuplicationCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 周捷(中訊) 新規作成
*/
package webbroker3.accountopen;

import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.util.WEB3LogUtility;

/**
 * (口座開設重複チェック)<BR>
 * 各テーブルでの検索対象の重複チェック機@能を実装するユーティリティ・クラス。<BR>
 *   
 * @@author 周捷
 * @@version 1.0
 */
public abstract class WEB3AccOpenDuplicationCheck 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenDuplicationCheck.class);
    
    /**
     * 重複顧客情報・顧客コードキー名。<BR>
     */
    protected final String KEY_ACCOUNT_CODE = "account_code";
    
    /**
     * 重複顧客情報・部店コードキー名。<BR>
     */
    protected final String KEY_BRANCH_CODE = "branch_code";
    
    /**
     * 重複顧客情報・テーブル名キー名<BR>
     */
    protected final String KEY_TAB_NAME = "tab_name";
    
    /**
     * (顧客コード)<BR>
     * 顧客コード。<BR>
     */
    protected String accountCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード。<BR>
     */
    protected String requestNumber;
    
    /**
     * (証券会社コード)<BR>
     * 証券会社コード。<BR>
     */
    protected String institutionCode;
    
    /**
     * (口座開設重複チェック)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * <BR>
     * this.顧客コード = （引数）顧客コード <BR>
     * this.識別コード = （引数）識別コード <BR>
     * this.証券会社コード = （引数）証券会社コード <BR>
     * をセットする。<BR> 
     * @@param l_strAccountCode - (顧客コード) <BR>
     * 口座コード。<BR>
     * @@param l_strRequestNumber - (識別コード) <BR>  
     * 識別コード。<BR>
     * @@param l_strInstitutionCode - (証券会社コード) <BR>
     * 証券会社コード。<BR>
     */
    public WEB3AccOpenDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        this.accountCode = l_strAccountCode;
        this.requestNumber = l_strRequestNumber;
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get顧客コード)<BR>
     * 重複顧客情報から顧客コードを取得する。<BR> 
     * <BR>
     * <BR>
     * [戻り値]<BR> 
     * ((Map)重複アドレス情報.get(this.KEY_ACCOUNT_CODE)).toString()<BR>
     * @@param l_objDuplicationAddressInfo - (重複アドレス情報)<BR>
     * 重複アドレス情報。<BR> 
     * get重複アドレス()の戻り値配列の要素（Map）。<BR> 
     * @@return String
     */
    public String getAccountCode(Object l_objDuplicationAddressInfo)
    {
        final String STR_METHOD_NAME = "getAccountCode(Object)";
        log.entering(STR_METHOD_NAME);
        String l_strAccountCode = null;
        
        Map l_map = (Map)l_objDuplicationAddressInfo;
        if (l_map.get(KEY_ACCOUNT_CODE) != null)
        {
            l_strAccountCode = l_map.get(KEY_ACCOUNT_CODE).toString();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strAccountCode;
    }
    
    /**
     * (get重複顧客情報)<BR>
     * 引数の検索対象と重複する顧客が存在するかチェックを行う。<BR> 
     * 存在する場合、その顧客の部店コードと顧客コード、発見されたテーブル名のセット<BR> 
     * （Map）をObject配列に格納し、返却する。<BR>    
     * （重複顧客が存在する場合はObject配列の長さ>0<BR> 
     * <BR> 
     * サブクラスで実装。<BR> 
     * @@param l_strSearchObject - (検索対象)<BR>
     * 検索対象。<BR>
     * @@return Object[]
     * @@throws WEB3BaseException 
     */
    public abstract Object[] getDuplicationAccountInfo(String l_strSearchObject) throws WEB3BaseException;
    
    /**
     * (get部店コード)<BR>
     * 重複顧客情報から部店コードを取得する。<BR>  
     * <BR>
     * <BR>
     * [戻り値] <BR>
     * ((Map)重複アドレス情報.get(this.KEY_BRANCH_CODE)).toString()<BR> 
     * <BR>
     * @@param l_objDuplicationAddressInfo - (重複アドレス情報)<BR>
     * 重複アドレス情報。<BR>
     * get重複アドレス()の戻り値配列の要素（Map）。<BR>
     * @@return String
     */
    public String getBranchCode(Object l_objDuplicationAddressInfo)
    {
        final String STR_METHOD_NAME = "getBranchCode(Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strBranchCode = null;
        Map l_map = (Map)l_objDuplicationAddressInfo;
        
        if (l_map.get(KEY_BRANCH_CODE) != null)
        {
            l_strBranchCode = l_map.get(KEY_BRANCH_CODE).toString();
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }
    
    /**
     * (getテーブル名)<BR>
     * 重複顧客情報からテーブル名を取得する。 <BR>
     * <BR>
     * <BR>
     * [戻り値] <BR>
     * ((Map)重複アドレス情報.get(this.KEY_TAB_NAME)).toString()<BR>
     * @@param l_objDuplicationAddressInfo - (重複アドレス情報)<BR>
     * 重複アドレス情報。<BR>
     * get重複アドレス()の戻り値配列の要素（Map）。<BR>
     * @@return String
     */
    public String getTableName(Object l_objDuplicationAddressInfo)
    {
        final String STR_METHOD_NAME = "getTableName(Object)";
        log.entering(STR_METHOD_NAME);
        
        String l_strTableName = null;
        Map l_map = (Map)l_objDuplicationAddressInfo;
        if (l_map.get(KEY_TAB_NAME) != null)
        {
            l_strTableName = l_map.get(KEY_TAB_NAME).toString();
        }
        log.exiting(STR_METHOD_NAME);
        return l_strTableName;
    }

}
@
