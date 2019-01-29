head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.35;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenDuplicationCheckUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設重複チェックユーティリティ(WEB3AccOpenDuplicationCheckUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 周捷(中訊) 新規作成
*/
package webbroker3.accountopen;

import java.util.List;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

/**
 * (口座開設重複チェックユーティリティ)<BR>
 * 口座開設見込客オブジェクト又は <BR>
 * 口座開設重複チェックオブジェクトが<BR> 
 * 各種チェックを行う際に参照を行うユーティリティクラス。<BR>
 *   
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AccOpenDuplicationCheckUtility 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenDuplicationCheckUtility.class);
    
    /**
     * 顧客マスタ（全部店分）テーブル名。<BR>
     */
    public static final String MST_NM_MAIN_ACC_ALL = "main_account_all";
    
    /**
     * 口座情報マスタテーブル名。<BR>
     */
    public static final String MST_NM_ACC_INF_MST = "account_info_mst";
    
    /**
     * 顧客マスタテーブル名。<BR>
     */
    public static final String MST_NM_MAIN_ACC = "main_account";
    
    /**
     * 口座開設見込客テーブル名。<BR>
     */
    public static final String MST_NM_EXP_ACC_OPEN = "exp_account_open";
    
	/**
	 * 口座開設見込客テーブル口座区分顧客。<BR>
	 */
    public static final String EXP_ACC_OPEN_ACCOUNT_DIV_INDV = "0";
	/**
	 * 口座開設見込客テーブル口座区分法@人。<BR>
	 */
    public static final String EXP_ACC_OPEN_ACCOUNT_DIV_COP = "1";
    
    /**
     * (get部店コードリスト)<BR>
     * 部店テーブルに存在する部店コードリストを返す。<BR> 
     * <BR>
     * 部店テーブルに存在する、（引数）証券会社コードの部店コードを取得し、 <BR>
     * ","区切りで部店コードのリストを作成し返却する。。 <BR>
     * <BR>
     * 部店コードが存在しない場合はnullを返却する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード。<BR>
     * @@return String
     * @@throws WEB3BaseException 
     */
    public static String getBranchCodeList(String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranchCodeList(String)";
        log.entering(STR_METHOD_NAME);  

        List l_lisRecord = null;
        String l_strBranchCode = "";
        try 
        {
            l_lisRecord = 
                Processors.getDefaultProcessor().doFindAllQuery(
                    BranchRow.TYPE,
                    "institution_code = ?",
                    null,
                    new Object[]{l_strInstitutionCode});
        } 
        catch (DataException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            log.error(STR_METHOD_NAME,l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                WEB3AccOpenDuplicationCheckUtility.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //部店コードが存在しない場合はnullを返却する。
        if (l_lisRecord == null || l_lisRecord.isEmpty())  
        {
            return null;
        }
        
        //部店テーブルに存在する、（引数）証券会社コードの部店コードを取得し、 
        // ","区切りで部店コードのリストを作成し返却する。。 
        for (int i = 0; i < l_lisRecord.size(); i++)
        {
            BranchRow l_branchRow = (BranchRow)l_lisRecord.get(i);  
            BranchParams l_branchRowParams = new BranchParams(l_branchRow);
            
            if (i == 0)
            {
                l_strBranchCode = l_strBranchCode + l_branchRowParams.getBranchCode();
            }
            else
            {
                l_strBranchCode = l_strBranchCode + ", " + l_branchRowParams.getBranchCode();
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strBranchCode;
    }

}
@
