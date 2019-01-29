head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.29.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddressDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス重複チェック(WEB3AccOpenMailAddressDuplicationCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/06/08 周捷(中訊) 新規作成
                   2006/06/23 猪俣（SCS）仕様変更・モデル070
*/

package webbroker3.accountopen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * (口座開設メールアドレス重複チェック)<BR>
 * 見込客テーブルでのメールアドレスの重複チェック機@能を実装するユーティリティ・クラス。<BR>
 *   
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AccOpenMailAddressDuplicationCheck extends WEB3AccOpenDuplicationCheck
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenMailAddressDuplicationCheck.class);
    
    /**
     * (get重複顧客情報)<BR>
     * 重複アドレスチェック <BR>
     * <BR>
     * 入力されたメールアドレスが既に登録されているかをチェックする。 <BR>
     * 登録済みの場合、その顧客の部店コードと顧客コード、<BR>
     * 発見されたテーブル名のセット（Map）をObject配列に格納し、返却する。<BR>   
     * （重複顧客が存在する場合はObject配列の長さ>0） <BR>
     * <BR>
     * <BR>
     * １） 返却値用のArrayListを生成する。 <BR>
     * <BR>
     * ２） 口座開設見込客テーブルより以下の条件で重複アドレス検索を行う。<BR> 
     * <BR>
     *　@　@[重複アドレス検索条件] <BR>
     * <BR>
     *　@　@　@見込客ファ@イル.emailアドレス = （引数）メールアドレス <BR>
     *　@　@　@AND 見込客ファ@イル.口座コード IS NOT NULL <BR>
     *　@　@　@AND 見込客ファ@イル.識別コード != super.識別コード <BR>
     *　@　@　@AND 見込客ファ@イル.証券会社コード = super.証券会社コード <BR>
     * <BR>
     * ３） 該当行が存在する場合、以下Mapを生成し、ArrayListに追加する。 <BR>
     *　@　@　@　@　@キー名：super.KEY_BRANCH_CODE 値：見込客ファ@イル.部店コード <BR>
     *　@　@　@　@　@キー名：super.KEY_ACCOUNT_CODE 値：見込客ファ@イル.口座コード <BR>
     *　@　@　@　@　@キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_EXP_ACC_OPEN <BR>
     * <BR>
     * <BR>
     * ４） ArrayListをObject配列化し返却する。<BR>
     * @@param l_strMailAddress - (メールアドレス)<BR>
     * メールアドレス。<BR>
     * @@return Object[]
     * @@throws WEB3BaseException 
     */
    public Object[] getDuplicationAccountInfo(String l_strMailAddress) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDuplicationAccountInfo(String)";
        log.entering(STR_METHOD_NAME);

		if (WEB3StringTypeUtility.isEmptyOrBlank(l_strMailAddress)) return null;

        //１） 返却値用のArrayListを生成する。
        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        List l_lisRecords = new ArrayList();
        
        //２） 口座開設見込客テーブルより以下の条件で重複アドレス検索を行う。
        //[重複アドレス検索条件]
        //見込客ファ@イル.emailアドレス = （引数）メールアドレス 
        //AND 見込客ファ@イル.口座コード IS NOT NULL 
        //AND 見込客ファ@イル.識別コード != super.識別コード 
        //AND 見込客ファ@イル.証券会社コード = super.証券会社コード 
		//AND 見込客ファ@イル.口座登録日 IS NULL
        l_sbWhere.append("email_address = ?");
        l_sbWhere.append(" and account_code is not null");
        l_sbWhere.append(" and acc_open_request_number <> ?");
        l_sbWhere.append(" and institution_code = ?");
		//仕様変更20060623SCSInomata-->
		l_sbWhere.append(" and account_open_date is null");
		//<--仕様変更20060623SCSInomata        
        l_objWhere = 
            new Object[]{
                l_strMailAddress, super.requestNumber, super.institutionCode};
        
        try
        {
            //口座開設見込客テーブルを検索する
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        
        int l_intLength = 0;
        if (l_lisRecords != null && !l_lisRecords.isEmpty())
        {
            l_intLength = l_lisRecords.size();
        }
        else
        {
            return null;
        }

        //３） 該当行が存在する場合、以下Mapを生成し、ArrayListに追加する。 
        //      キー名：super.KEY_BRANCH_CODE 値：見込客ファ@イル.部店コード 
        //      キー名：super.KEY_ACCOUNT_CODE 値：見込客ファ@イル.口座コード 
        //      キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_EXP_ACC_OPEN 
        ArrayList l_arrayList = new ArrayList();
        for (int i = 0; i < l_intLength; i++)
        {
            Map l_mapRecords = new HashMap();
            ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow) l_lisRecords.get(i);
            l_mapRecords.put(KEY_BRANCH_CODE, l_expAccountOpenRow.getBranchCode());
            l_mapRecords.put(KEY_ACCOUNT_CODE, l_expAccountOpenRow.getAccountCode());
            l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN);
            l_arrayList.add(l_mapRecords);
        }
        
        //４） ArrayListをObject配列化し返却する。
        log.exiting(STR_METHOD_NAME);
        return l_arrayList.toArray();
    }
    
    /**
     * (口座開設メールアドレス重複チェック)<BR>
     * コンストラクタ <BR>
     * <BR>
     * super(<BR> 
     * （引数）顧客コード,<BR> 
     * （引数）識別コード,<BR>
     * （引数）証券会社 <BR>
     * )<BR>
     * @@param l_strAccountCode - (顧客コード) <BR>
     * 口座コード。<BR>
     * @@param l_strRequestNumber - (識別コード) <BR>  
     * 識別コード。
     * @@param l_strInstitutionCode - (証券会社コード) <BR>
     * 証券会社コード。
     */
    public WEB3AccOpenMailAddressDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        super(l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
    }

}
@
