head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenTelNumberDuplicationCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設電話番号重複チェック (WEB3AccOpenTelNumberDuplicationCheck.java)
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
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

/**
 * (口座開設電話番号重複チェック)<BR>
 * 顧客マスタ、顧客マスタ（全部店）、口座開設見込客テーブルでの、<BR>
 * 電話番号及び携帯番号の重複チェック機@能を実装するユーティリティ・クラス。<BR>
 *   
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3AccOpenTelNumberDuplicationCheck extends WEB3AccOpenDuplicationCheck
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AccOpenTelNumberDuplicationCheck.class);
            
   /**
    * 見込客電話番号検索列。
    */
    private String[] expOpenAccExqRow;
    
   /**
    * (get重複顧客情報)<BR>
    * 指定された電話番号が以下のテーブルに登録されているかどうかチェックする。<BR> 
    * 登録済みの場合、その顧客の部店コードと顧客コード、<BR>
    * 発見されたテーブル名のセット（Map）を Object配列に格納し、返却する。<BR>   
    * （重複顧客が存在する場合はObject配列の長さ>0） <BR>
    * <BR>
    * １） 返却値用のArrayListを生成する。 <BR>
    * <BR>
    * ２） 顧客マスタに電話番号が存在するかチェックを行う。<BR> 
    * <BR>
    *　@　@[条件] <BR>
    *　@　@　@顧客マスタ.証券会社コード = super.証券会社コード <BR>
    *　@　@　@AND (顧客マスタ.電話番号 = <BR>
    *　@　@　@（引数）電話番号 OR 顧客マスタ.連絡先電話番号（携帯） = （引数）電話番号)<BR> 
    * <BR>
    *   ２－１） 該当行が存在する場合、以下Mapを生成し、ArrayListに追加する。<BR> 
    * 　@　@　@　@キー名：super.KEY_BRANCH_CODE 値：顧客マスタ.部店コード <BR>
    * 　@　@　@　@キー名：super.KEY_ACCOUNT_CODE 値：顧客マスタ.口座コード <BR>
    * 　@　@　@　@キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_MAIN_ACC<BR> 
    * <BR>
    * <BR>
    * ３） 顧客マスタ（全部店）に電話番号が存在するかチェックを行う。 <BR>
    * <BR>
    * 　@３－１） 顧客マスタとの重複を避ける為、<BR>
    *　@　@　@　@　@部店テーブルに存在する部店コードのリストを取得する。 <BR>
    *　@　@　@　@　@口座開設重複チェックユーティリティ.get部店コードリスト(super.証券会社コード)<BR>
    *　@　@　@　@　@を実行しリストを取得する。<BR> 
    * <BR>
    * 　@３－２） 顧客マスタ（全部店）より検索を行う。<BR> 
    * <BR>
    *　@　@３－２－１） ３－１）の戻り値がnullでない場合 <BR>
    *　@　@　@　@[条件] <BR>
    *　@　@　@　@　@　@顧客マスタ（全部店）.証券会社コード = super.証券会社コード <BR>
    *　@　@　@　@　@　@AND 顧客マスタ（全部店）.部店コード NOT IN ( ３－１）の戻り値 ) <BR>
    *　@　@　@　@　@　@AND  (顧客マスタ（全部店）.電話番号 = （引数）電話番号 <BR>
    *　@　@　@　@　@　@OR 顧客マスタ（全部店）.連絡先・電話番号 = （引数）電話番号) <BR>
    * <BR>
    * <BR>
    *　@　@３－２－２）  ３－１）の戻り値がnullの場合 <BR>
    *　@　@　@　@[条件] <BR>
    *　@　@　@　@　@　@(顧客マスタ（全部店）.電話番号 = （引数）電話番号 <BR>
    *　@　@　@　@　@　@OR 顧客マスタ（全部店）.連絡先・電話番号 = （引数）電話番号)<BR> 
    *　@　@　@　@　@　@AND 顧客マスタ（全部店）.証券会社コード = super.証券会社コード <BR>
    * <BR>
    * <BR>
    * 　@３－３） 該当行が存在する場合、以下Mapを生成し、ArrayListに追加する。 <BR>
    * 　@　@　@　@キー名：super.KEY_BRANCH_CODE 値：顧客マスタ（全部店）.部店コード <BR>
    * 　@　@　@　@キー名：super.KEY_ACCOUNT_CODE 値：顧客マスタ（全部店）.顧客コード <BR>
    * 　@　@　@　@キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_MAIN_ACC_ALL <BR>
    * <BR>
    * <BR>
    * ４） 口座開設見込客テーブルに電話番号が存在するかチェックを行う。 <BR>
    * <BR>
    *　@　@　@[条件] <BR>
    * <BR>
    *　@　@　@見込客ファ@イル.証券会社コード = super.証券会社コード <BR>
    *　@　@　@AND 見込客ファ@イル.識別コード != super.識別コード <BR>
    *　@　@　@AND 見込客ファ@イル.口座コード IS NOT NULL <BR>
    *　@　@　@AND(見込客ファ@イル.電話番号 = （引数）電話番号 <BR>
    *　@　@　@OR 見込客ファ@イル.連絡先電話番号（携帯） = （引数）電話番号)) <BR>
    * <BR>
    * <BR>
    *　@　@４－１） 該当行が存在する場合、以下Mapを生成し、ArrayListに追加する。 <BR>
    *　@　@　@　@　@　@キー名：super.KEY_BRANCH_CODE 値：見込客ファ@イル.部店コード <BR>
    *　@　@　@　@　@　@キー名：super.KEY_ACCOUNT_CODE 値：見込客ファ@イル.口座コード <BR>
    *　@　@　@　@　@　@キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_EXP_ACC_OPEN <BR>
    * <BR>
    * ５） ArrayListをObject配列化し返却する。<BR>
    * @@param l_strTelephone - (電話番号)<BR>
    * 電話番号。<BR>
    * @@return Object[]
    * @@throws WEB3BaseException 
    */
    public Object[] getDuplicationAccountInfo(String l_strTelephone) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getDuplicationAccountInfo(String)";
        log.entering(STR_METHOD_NAME);

		if (WEB3StringTypeUtility.isEmptyOrBlank(l_strTelephone)) return null;

        //１） 返却値用のArrayListを生成する。
        StringBuffer l_sbWhereOne = new StringBuffer();
        Object[] l_objWhereOne = null;
        ArrayList l_lisReturnList = new ArrayList();
        List l_lisRecordsOne = new ArrayList();
        List l_lisRecordsTwo = new ArrayList();
        List l_lisRecordsThree = new ArrayList();
        
        
        //２） 顧客マスタに電話番号が存在するかチェックを行う。 
        //[条件] 
        //  顧客マスタ.証券会社コード = super.証券会社コード 
        //  AND (顧客マスタ.電話番号 like（引数）電話番号 OR 顧客マスタ.連絡先電話番号（携帯）like（引数）電話番号) 

        l_sbWhereOne.append("institution_code = ?");
		l_sbWhereOne.append(" and (telephone like ?");
		l_sbWhereOne.append(" or mobile like ?)");
		l_objWhereOne = new Object[]{super.institutionCode, l_strTelephone + "%", l_strTelephone + "%"};

        try
        {
            //顧客マスタテーブルを検索する
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordsOne = l_queryProcessor.doFindAllQuery(
                MainAccountRow.TYPE,
                l_sbWhereOne.toString(),
                l_objWhereOne);
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
        
        if (l_lisRecordsOne != null && !l_lisRecordsOne.isEmpty())
        {
            for (int i = 0; i < l_lisRecordsOne.size(); i++)
            {
                //２－１） 該当行が存在する場合、電話番号及び携帯電話番号から
                //空白を除いた値が（引数）電話番号と等しい場合、
                //以下Mapを生成し、ArrayListに追加する。 
                //       キー名：super.KEY_BRANCH_CODE 値：顧客マスタ.部店コード 
                //       キー名：super.KEY_ACCOUNT_CODE 値：顧客マスタ.口座コード 
                //       キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_MAIN_ACC
                MainAccountRow l_mainAccountRow = (MainAccountRow) l_lisRecordsOne.get(i);

				//trim(電話番号)又はtrim(携帯電話番号)が（引数）電話番号と等しい場合、
				//重複情報を作成する。
				int l_intInfo = 0;
				String l_strtmpTel = l_mainAccountRow.getTelephone();
				if (l_strtmpTel != null) 
				{
					if (l_strTelephone.equals(l_strtmpTel.trim())) l_intInfo++;				
				}

				String l_strtmpMobile = l_mainAccountRow.getMobile();
				if (l_strtmpMobile != null) 
				{
					if (l_strTelephone.equals(l_strtmpMobile.trim())) l_intInfo++;
				}

				if (l_intInfo > 0)
				{
					Map l_mapRecords = new HashMap();
					l_mapRecords.put(KEY_BRANCH_CODE, l_mainAccountRow.getBranchCode());
					l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountRow.getAccountCode());
					l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_MAIN_ACC);
					l_lisReturnList.add(l_mapRecords);
				}	
            }
        } 

        //３） 顧客マスタ（全部店）に電話番号が存在するかチェックを行う。
        StringBuffer l_sbWhereTwo = new StringBuffer();
        Object[] l_objWhereTwo = null;
        
        //３－１） 顧客マスタとの重複を避ける為、部店テーブルに存在する部店コードのリストを取得する。 
        //   口座開設重複チェックユーティリティ.get部店コードリスト(super.証券会社コード)を実行しリストを取得する。 
        String l_strBranchCode = 
            WEB3AccOpenDuplicationCheckUtility.getBranchCodeList(super.institutionCode);
        
        //３－２） 顧客マスタ（全部店）より検索を行う。 
        if (l_strBranchCode != null)
        {
            //  ３－２－１） ３－１）の戻り値がnullでない場合 
            //   [条件] 
            //       顧客マスタ（全部店）.証券会社コード = super.証券会社コード 
            //       AND 顧客マスタ（全部店）.部店コード NOT IN ( ３－１）の戻り値 ) 
            //       AND  (顧客マスタ（全部店）.電話番号 = （引数）電話番号 
            //       OR 顧客マスタ（全部店）.連絡先・電話番号 = （引数）電話番号)
            l_sbWhereTwo.append("comp_code = ?");
            String[] l_strBranchCodes = l_strBranchCode.split(",");
            l_sbWhereTwo.append(" and br_code not in (");
            
            ArrayList l_lisArray = new ArrayList();
            
            l_lisArray.add(super.institutionCode);
            for (int i = 0; i < l_strBranchCodes.length; i++)
            {
                
                if (i == l_strBranchCodes.length - 1)
                {
                    l_sbWhereTwo.append("?)");
                }
                else
                {
                    l_sbWhereTwo.append("?, ");
                }
                l_lisArray.add(l_strBranchCodes[i]);
            }
            
			l_lisArray.add(l_strTelephone + "%");
			l_lisArray.add(l_strTelephone + "%");
			l_sbWhereTwo.append(" and (telephone like ?");
			l_sbWhereTwo.append(" or contact_address_telephone like ?)");
            l_objWhereTwo = new Object[l_lisArray.size()];
            l_lisArray.toArray(l_objWhereTwo);

        }
        else
        {
            //  ３－２－２）  ３－１）の戻り値がnullの場合 
            //   [条件] 
            //    (顧客マスタ（全部店）.電話番号 = （引数）電話番号 OR 顧客マスタ（全部店）.連絡先・電話番号 = （引数）電話番号) 
            //    AND 顧客マスタ（全部店）.証券会社コード = super.証券会社コード 
			l_sbWhereTwo.append("(telephone like ?");
			l_sbWhereTwo.append(" or contact_address_telephone like ?)");
			l_sbWhereTwo.append(" and comp_code = ?");
			l_objWhereTwo = 
				new Object[]{l_strTelephone + "%", l_strTelephone + "%", super.institutionCode};
        }
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordsTwo = l_queryProcessor.doFindAllQuery(
                MainAccountAllRow.TYPE,
                l_sbWhereTwo.toString(),
                l_objWhereTwo);
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
        
        if (l_lisRecordsTwo != null && !l_lisRecordsTwo.isEmpty())
        {
            for (int i = 0; i < l_lisRecordsTwo.size(); i++)
            {
                //３－３） 該当行が存在する場合、電話番号及び携帯電話番号から
				//空白を除いた値が（引数）電話番号と等しい場合、
				//以下Mapを生成し、ArrayListに追加する。 
                //         キー名：super.KEY_BRANCH_CODE 値：顧客マスタ（全部店）.部店コード 
                //         キー名：super.KEY_ACCOUNT_CODE 値：顧客マスタ（全部店）.顧客コード 
                //         キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_MAIN_ACC_ALL 
                MainAccountAllRow l_mainAccountAllRow = (MainAccountAllRow) l_lisRecordsTwo.get(i);
				//trim(電話番号)又はtrim(携帯電話番号)が（引数）電話番号と等しい場合、
				//重複情報を作成する。
				int l_intInfo = 0;
				String l_strtmpTel = l_mainAccountAllRow.getTelephone();
				if (l_strtmpTel != null) 
				{
					if (l_strTelephone.equals(l_strtmpTel.trim())) l_intInfo++;				
				}

				String l_strtmpMobile = l_mainAccountAllRow.getContactAddressTelephone();
				if (l_strtmpMobile != null) 
				{
					if (l_strTelephone.equals(l_strtmpMobile.trim())) l_intInfo++;
				}

				if (l_intInfo > 0)
				{
					Map l_mapRecords = new HashMap();
	                l_mapRecords.put(KEY_BRANCH_CODE, l_mainAccountAllRow.getBrCode());
	                l_mapRecords.put(KEY_ACCOUNT_CODE, l_mainAccountAllRow.getCustCode());
	                l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_MAIN_ACC_ALL);
	                l_lisReturnList.add(l_mapRecords);
				}
            }
            
        } 

        //４） 口座開設見込客テーブルに電話番号が存在するかチェックを行う。
        //[条件] 
        //見込客ファ@イル.証券会社コード = super.証券会社コード 
        //AND 見込客ファ@イル.識別コード != super.識別コード 
        //AND 見込客ファ@イル.口座コード IS NOT NULL 
        //AND 見込客ファ@イル.口座登録日 IS NULL
        //AND(見込客ファ@イル.電話番号 = （引数）電話番号 OR 見込客ファ@イル.連絡先電話番号（携帯） = （引数）電話番号)) 
        StringBuffer l_sbWhereThree = new StringBuffer();
		ArrayList l_objWhereThree = new ArrayList();
        
        l_sbWhereThree.append("institution_code = ?");
		l_objWhereThree.add(super.institutionCode);
        l_sbWhereThree.append(" and acc_open_request_number <> ?");
		l_objWhereThree.add(super.requestNumber);
        l_sbWhereThree.append(" and account_code is not null");
		l_sbWhereThree.append(" and account_open_date is null");
        l_sbWhereThree.append(" and (telephone = ?");
		l_objWhereThree.add(l_strTelephone);
        
        for(int i = 0; i < expOpenAccExqRow.length; i++){
			l_sbWhereThree.append(" or "+ expOpenAccExqRow[i] +" = ?");
			l_objWhereThree.add(l_strTelephone);
        }
		l_sbWhereThree.append(")");
        
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisRecordsThree = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_sbWhereThree.toString(),
                l_objWhereThree.toArray());
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
        
        if (l_lisRecordsThree != null && !l_lisRecordsThree.isEmpty())
        {
            for (int i = 0; i < l_lisRecordsThree.size(); i++)
            {
                Map l_mapRecords = new HashMap();
                //４－１） 該当行が存在する場合、以下Mapを生成し、ArrayListに追加する。 
                //      キー名：super.KEY_BRANCH_CODE 値：見込客ファ@イル.部店コード 
                //      キー名：super.KEY_ACCOUNT_CODE 値：見込客ファ@イル.口座コード 
                //      キー名：super.TAB_NAME 値：口座開設重複チェックユーティリティ.MST_NM_EXP_ACC_OPEN 
                ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow) l_lisRecordsThree.get(i);
                l_mapRecords.put(KEY_BRANCH_CODE, l_expAccountOpenRow.getBranchCode());
                l_mapRecords.put(KEY_ACCOUNT_CODE, l_expAccountOpenRow.getAccountCode());
                l_mapRecords.put(KEY_TAB_NAME, WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN);
                l_lisReturnList.add(l_mapRecords);
            }

        } 
        
        log.exiting(STR_METHOD_NAME);
        return l_lisReturnList.toArray();
    }
    
    
    /**
	 * @@param exquteRow - 電話番号検索列
	 * 電話番号検索列のセットを行う。
	 */
	public void setExpOpenAccExqRow(String[] l_strExquteRow)
	{
		expOpenAccExqRow = l_strExquteRow;
    }
    
    
    /**
     * (口座開設電話番号重複チェック)<BR>
     * コンストラクタ<BR>
     * <BR>
     *super( <BR>
     *（引数）顧客コード, <BR>
     *（引数）識別コード, <BR>
     *（引数）証券会社 <BR>
     *) <BR>
     * @@param l_strAccountCode - (顧客コード) <BR>
     * 口座コード。<BR>
     * @@param l_strRequestNumber - (識別コード) <BR>  
     * 識別コード。<BR>
     * @@param l_strInstitutionCode - (証券会社コード) <BR>
     * 証券会社コード。<BR>
     */
    public WEB3AccOpenTelNumberDuplicationCheck(
        String l_strAccountCode, String l_strRequestNumber, String l_strInstitutionCode)
    {
        super(l_strAccountCode, l_strRequestNumber, l_strInstitutionCode);
    }
}
@
