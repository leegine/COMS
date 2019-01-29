head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeMailAddressDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メールアドレス重複チェッククラス(WEB3GentradeMailAddressDuplicationCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/19 凌建平 (中訊) 新規作成
                   2006/06/12 猪俣（SCS）　@仕様変更・モデル197
                   2006/06/16 凌建平（中訊）仕様変更・モデル198
                   2006/06/23 猪俣（SCS）仕様変更・モデル199
Revesion History : 2010/02/23 趙林鵬 (中訊) 仕様変更 モデル350
*/

package webbroker3.gentrade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountinfoMultiMailaddressFlagDef;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.gentrade.data.AccountMailAddressRow;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (メールアドレス重複チェッククラス)<BR>
 * メールアドレスの重複チェック機@能を実装するユーティリティ・クラス<BR>
 *
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3GentradeMailAddressDuplicationCheck
{

    /**
     * ログ出力<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
        	WEB3GentradeMailAddressDuplicationCheck.class);

    /**
     * (部店コードキー名)<BR>
     * 部店コードキー名<BR>
     */
    private static String KEY_BRANCH_CODE = "branch_code";

    /**
     * (顧客コードキー名)<BR>
     * 顧客コードキー名<BR>
     */
    private static String KEY_ACCOUNT_CODE = "account_code";

    /**
     * (get重複アドレス)<BR>
     * 重複アドレスチェック<BR>
     * 顧客マスタ又は顧客メールアドレステーブルより以下の条件で重複アドレス検索を行う。<BR>
     * ※大文字、小文字の差異も同じとみなす<BR>
     * <BR>
     * １)部店.get複数メールアドレス対応実施()にてプリファ@レンスの値を取得する。<BR>
     * <BR>
     * [引数]<BR>
     * 　@部店コード　@=　@引数.部店コード<BR>
     * 　@証券会社コード　@=　@引数.証券会社コード<BR>
     * 　@プリファ@レンス名： "accountinfo.multi.mailaddress.flag"<BR>
     * 　@プリファ@レンス名の連番： 1<BR>
     * <BR>
     * ２）顧客メールアドレステーブルより以下の条件で重複アドレス検索を行う。<BR>
     * 　@１）で戻り値==”２”の場合以下処理を行う。<BR>
     * <BR>
     * [重複アドレス検索条件]<BR>
     * email_address=引数：メールアドレス AND institution_code=引数：証券会社コード<BR>
     * <BR>
     * 　@２－１）以下にいずれか条件を満たしている場合<BR>
     * <BR>
     * 　@①@　@部店コード <>（引数）部店コード<BR>
     *   ②　@口座コード <>（引数）顧客コード<BR>
     * <BR>
     * 　@[戻り値]<BR>
     * 　@重複顧客の部店コードと顧客コードのセット（Map）をObject配列に格納し、返却する。<BR>
     * 　@（重複顧客が存在する場合はObject配列の長さ>0）<BR>
     * 　@※リスト中で重複の部店コードと顧客コード削除する。<BR>
     * <BR>
     * 　@２－２）２－１）以外の場合、そのレコードは無視する。<BR>
     * <BR>
     * ３）顧客マスタテーブルより以下の条件で重複アドレス検索を行う。<BR>
     * 　@１）で戻り値！=”２”の場合以下処理を行う。<BR>
     * <BR>
     * [重複アドレス検索条件]<BR>
     * <BR>
     * ①@[isRenewal()==FALSEの場合]<BR>
     * 　@ email_address=引数：メールアドレス AND institution_code=引数：証券会社コード<BR>
     * <BR>
     * ②[isRenewal()==TRUEの場合]<BR>
     * 　@SUBSTR(account_code,1,6)<>（引数）顧客コード.substring(0,6)<BR>
     * 　@AND email_address=（引数）メールアドレス<BR>
     * 　@AND institution_code=（引数）証券会社コード<BR>
     * <BR>
     * [戻り値]<BR>
     * 重複顧客の部店コードと顧客コードのセット（Map）をObject配列に格納し、返却する。<BR>
     * （重複顧客が存在する場合はObject配列の長さ>0）<BR>
     * <BR>
     * @@param l_strMailAddress - メールアドレス
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return Object
     * @@roseuid 406937AB0203
     */
    public static Object[] getDuplicateAddress(
        String l_strMailAddress,
        String l_strAccountCode,
        String l_strBranchCode,
        String l_strInstitutionCode)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDuplicateAddress(String, String, String, String)";
        log.entering(STR_METHOD_NAME);
		//仕様変更20060623SCSInomata-->
		if (l_strMailAddress == null) return null;
		//<--仕様変更20060623SCSInomata

        //１)部店.get複数メールアドレス対応実施()にてプリファ@レンスの値を取得する。
        WEB3GentradeInstitution l_institution = null;
        WEB3GentradeBranch l_gentradeBranch = null;
        try
        {
            l_institution = new WEB3GentradeInstitution(l_strInstitutionCode);
            l_gentradeBranch = new WEB3GentradeBranch(l_institution, l_strBranchCode);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //[引数]
       //部店コード　@=　@引数.部店コード
       //証券会社コード　@=　@引数.証券会社コード
       //プリファ@レンス名： "accountinfo.multi.mailaddress.flag"
       //プリファ@レンス名の連番： 1
        String l_strMultiMailAddressEnforcement = l_gentradeBranch.getMultiMailAddressEnforcement(
            l_strBranchCode, 
            l_strInstitutionCode,
            WEB3BranchPreferencesNameDef.ACCOUNTINFO_MULTI_MAILADDRESS_FLAG,
            1);

        StringBuffer l_sbWhere = new StringBuffer();
        Object[] l_objWhere = null;
        List l_lisRecords = new ArrayList();

        //２）顧客メールアドレステーブルより以下の条件で重複アドレス検索を行う。
        // １）で戻り値==”２”の場合以下処理を行う。
        if (WEB3AccountinfoMultiMailaddressFlagDef.EXECUTE_T1.equals(l_strMultiMailAddressEnforcement))
        {
            //[重複アドレス検索条件] 
            //email_address=引数：メールアドレス AND institution_code=引数：証券会社コード
            l_sbWhere.append(" lower(email_address) = ? ");
            l_sbWhere.append(" and institution_code = ? ");

            l_objWhere = new Object[]
            {
                l_strMailAddress.trim().toLowerCase(),
                l_strInstitutionCode.trim()
            };

            try
            {
                //顧客メールアドレステーブルを検索する
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    AccountMailAddressRow.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    WEB3GentradeMailAddressDuplicationCheck.class.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            // [戻り値] 
            //  重複顧客の部店コードと顧客コードのセット（Map）をObject配列に格納し、 
            //  返却する。
            //  （重複顧客が複数の場合はObject配列の長さ>0）
            int l_intLength = 0;
            if (l_lisRecords != null && l_lisRecords.size() != 0)
            {
                l_intLength = l_lisRecords.size();
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                return null;
            }

            List l_lisContains = new ArrayList();
            
            for (int i = 0; i < l_intLength; i++)
            {
                Map l_mapRecords = new HashMap();
                AccountMailAddressRow l_accountMailAddressRow = (AccountMailAddressRow)l_lisRecords.get(i);

                //２－１）以下にいずれか条件を満たしている場合
                //①@　@部店コード <>（引数）部店コード
                //②　@口座コード <>（引数）顧客コード
                String l_strBranchCodeDiff = l_accountMailAddressRow.getBranchCode();
                String l_strAccountCodeDiff = l_accountMailAddressRow.getAccountCode();

                if (l_strBranchCodeDiff.equals(l_strBranchCode)
                    && l_strAccountCodeDiff.equals(l_strAccountCode))
                {
                    continue;
                }

                l_mapRecords.put(KEY_BRANCH_CODE, l_strBranchCodeDiff);
                l_mapRecords.put(KEY_ACCOUNT_CODE, l_strAccountCodeDiff.substring(0,6));

                //　@※リスト中で重複の部店コードと顧客コード削除する。
                if (!l_lisContains.contains(l_mapRecords))
                {
                    l_lisContains.add(l_mapRecords);
                }
            }

            Object[] l_objRecords = new Object[l_lisContains.size()];
            l_lisContains.toArray(l_objRecords);

            log.exiting(STR_METHOD_NAME);
            return l_objRecords;
        }

        //３）顧客マスタテーブルより以下の条件で重複アドレス検索を行う。
        //　@１）で戻り値！=”２”の場合以下処理を行う。
        boolean l_blnIsRenewal = WEB3GentradeMailAddressDuplicationCheck.isRenewal(
        	l_strAccountCode, 
        	l_strBranchCode, 
        	l_strInstitutionCode);

        //重複アドレス検索条件
        //顧客マスタより以下の条件で重複アドレス検索を行う。
        if (!l_blnIsRenewal)
        {
        	//①@[isRenewal()==FALSEの場合] 
        	// email_address=引数：メールアドレス AND institution_code=引数：証券会社コード

            //メールアドレス
            l_sbWhere.append(" email_address = ? ");

            //証券会社コード
            l_sbWhere.append(" and institution_code = ? ");

            l_objWhere = new Object[]{
            	l_strMailAddress.trim(),
            	l_strInstitutionCode.trim()};
        }
        else
        {
        	//②[isRenewal()==TRUEの場合] 
        	// SUBSTR(account_code1,6)<>引数：顧客コード AND email_address=引数：
        	//メールアドレス AND institution_code=引数：証券会社コード 
            //顧客コード
            l_sbWhere.append(" SUBSTR(account_code, 1, 6) <> ? ");

            //メールアドレス
            l_sbWhere.append(" and email_address = ? ");

            //証券会社コード
            l_sbWhere.append(" and institution_code = ? ");
            
//			仕様変更***2006.06.12 SCS Inomata--> 
//			l_objWhere = new Object[]{
//				l_strAccountCode.trim(),
//				l_strMailAddress.trim(), 
//				l_strInstitutionCode.trim()};
			l_objWhere = new Object[]{
				//l_strAccountCode.trim(),
				l_strAccountCode.trim().substring(0,6),
				l_strMailAddress.trim(), 
				l_strInstitutionCode.trim()};
//			<--仕様変更***2006.06.12 SCS Inomata
        }

        try
        {
            //顧客マスタテーブルを検索する
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
            	MainAccountRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                WEB3GentradeMailAddressDuplicationCheck.class.getName()
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
                WEB3GentradeMailAddressDuplicationCheck.class.getName()
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
                WEB3GentradeMailAddressDuplicationCheck.class.getName()
                    + "."
                    + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        
        // [戻り値] 
		//  重複顧客の部店コードと顧客コードのセット（Map）をObject配列に格納し、 
		//  返却する。 
		//  （重複顧客が複数の場合はObject配列の長さ>0）
        int l_intLength = 0;
        if (l_lisRecords != null && l_lisRecords.size() != 0)
        {
        	l_intLength = l_lisRecords.size();
        }
        else
        {
        	return null;
        }

        Object[] l_objRecords = new Object[l_intLength];
        for (int i = 0; i < l_intLength; i++)
        {
            Map l_mapRecords = new HashMap();
        	MainAccountRow l_mainAccountRow = (MainAccountRow) l_lisRecords.get(i);
            l_mapRecords.put(KEY_BRANCH_CODE, l_mainAccountRow.getBranchCode());
//			仕様変更***2006.06.12 SCS Inomata--> 
			//l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountRow.getAccountCode());
            l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountRow.getAccountCode().substring(0,6));
//			<--仕様変更***2006.06.12 SCS Inomata
            l_objRecords[i] = l_mapRecords;
        }

        return l_objRecords;
    }

    /**
     * (isRenewa)<BR>
     * <BR>
     * 顧客コード＆部店コード＆証券会社を条件として、<BR>
     * 拡張アカウントマネージャーから顧客オブジェクトを生成し、<BR>
     * account_idを取得する。<BR>
     * <BR>
     * 1) （引数）顧客コード == null の場合、FALSEを返却する。<BR>
     * <BR>
     * 2）（引数）顧客コード != null の場合、以下処理を行う。<BR>
     * <BR>
     * account_idを元に顧客マスタにメールアドレスが登録されているかどうか<BR>
     * （email_address!=null AND email_address!="" ）であるかどうかを判定する。<BR>
     * <BR>
     * [戻り値] <BR>
     * メールアドレス登録済み⇒TRUE <BR>
     * メールアドレス未登録⇒FALSE <BR>
     * <BR>
     * @@param l_strAccountCode - 顧客コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strInstitutionCode - 証券会社コード
     * @@return boolean
     * @@roseuid 406937AB0203
     */
    private static boolean isRenewal(
        String l_strAccountCode,
        String l_strBranchCode,
        String l_strInstitutionCode)
    	throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isRenewal(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //1) （引数）顧客コード == null の場合、FALSEを返却する。
        if (l_strAccountCode == null)
        {
    		log.exiting(STR_METHOD_NAME);
    		return false;
        }

        //2）（引数）顧客コード != null の場合、以下処理を行う。
        try
        {
        	//顧客コード＆部店コード＆証券会社を条件として
        	
//			仕様変更***2006.06.12 SCS Inomata--> 	
//        	MainAccountRow l_mainAccountRow = MainAccountDao.findRowByInstitutionCodeBranchCodeAccountCode(
//        		l_strInstitutionCode,
//        		l_strBranchCode,
//        		l_strAccountCode);
			// get顧客(String, String, String)
			FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
			WEB3GentradeAccountManager l_accountManager = 
					 (WEB3GentradeAccountManager)l_finApp.getAccountManager();
			WEB3GentradeMainAccount l_gentradMainAccount = l_accountManager.getMainAccount(
				l_strInstitutionCode.trim(), l_strBranchCode.trim(), l_strAccountCode.trim());
			MainAccountRow l_mainAccountRow = MainAccountDao.findRowByAccountId(l_gentradMainAccount.getAccountId());
//			<--仕様変更***2006.06.12 SCS Inomata
        	
        	//顧客マスタにメールアドレスが登録されているかどうか 
        	//（email_address!=null AND email_address!="" ）であるかどうかを判定する。 
        	//メールアドレス登録済み⇒TRUE 
        	//メールアドレス未登録⇒FALSE
        	if (l_mainAccountRow != null
        		&& l_mainAccountRow.getEmailAddress() != null
        		&& !"".equals(l_mainAccountRow.getEmailAddress()))
        	{
        		log.exiting(STR_METHOD_NAME);
        		return true;
        	}
        	else
        	{
        		log.exiting(STR_METHOD_NAME);
        		return false;
        	}
        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3GentradeMailAddressDuplicationCheck.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get顧客コード)<BR>
     * 重複顧客情報から顧客コードを取得する。<BR>
     * <BR>
     * @@param l_objDuplicationAccInfo - 重複アドレス情報
     * 	get重複アドレス()の戻り値配列の要素（Map）。
     * @@return String
     * @@roseuid 406937AB0203
     */
    public static String getAccountCode(Object l_objDuplicationAccInfo)
    {
        return ((Map) l_objDuplicationAccInfo).get(KEY_ACCOUNT_CODE).toString();
    }

    /**
     * (get部店コード )<BR>
     * 重複顧客情報から部店コードを取得する。<BR>
     * <BR>
     * @@param l_objDuplicationAccInfo - 重複アドレス情報
     * 	get重複アドレス()の戻り値配列の要素（Map）。
     * @@return String
     * @@roseuid 406937AB0203
     */
    public static String getBranchCode(Object l_objDuplicationAccInfo)
    {
        return ((Map) l_objDuplicationAccInfo).get(KEY_BRANCH_CODE).toString();
    }
    
	/**
	 * (remove重複情報)<BR>
	 * （引数）重複顧客情報から（引数）顧客コード、（引数）部店コードに該当する<BR>
	 * セットの削除を行う。<BR>
	 * <BR>
	 * （引数）重複顧客情報内に（引数）顧客コードと（引数）部店コードのセットが<BR>
	 * 存在する場合は削除を行い、削除済みの重複顧客情報を返却する。<BR>
	 * 顧客コードと部店コードのセットを削除した結果、重複情報が無い場合は、<BR>
	 * nullを返却する
	 * <BR>
	 * @@param l_duploArgs - 重複顧客情報
	 * @@param l_accCode - （引数）問合せ元顧客コード
	 * @@param l_brCode - （引数）問合せ元部店コード
	 * @@return Object[] - 重複顧客情報
     * @@roseuid 406937AB0203
	 */
	public static Object[] removeDuplicationInfo(
		Object[] l_duploArgs, 
		String l_accCode, 
		String l_brCode)
	{
		ArrayList l_duploList = new ArrayList();
		String l_strAccountCode = null;
		String l_strBranchCode = null;
		for (int i = 0; i < l_duploArgs.length; i++)
		{
			if (l_duploArgs[i] != null) 
			{
				l_strAccountCode = 
					WEB3GentradeMailAddressDuplicationCheck.getAccountCode(l_duploArgs[i]);
				l_strBranchCode = 
					WEB3GentradeMailAddressDuplicationCheck.getBranchCode(l_duploArgs[i]);
					
				if (l_strAccountCode.equals(l_accCode) && l_strBranchCode.equals(l_brCode))
				{
				} else
				{
					l_duploList.add(l_duploArgs[i]); 
				}

			}
		}
		if (l_duploList.size() < 1) return null;
		return l_duploList.toArray();
	}
}
@
