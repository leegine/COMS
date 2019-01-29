head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenDataTransferUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設データ移管UnitServiceImpl(WEB3AdminAccOpenDataTransferUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/18 孟亞南(中訊) 新規作成 モデル 181 ＤＢ更新仕様　@047，048
Revision History : 2009/08/26 孟亞南(中訊) モデル 190
Revision History : 2009/08/31 武波(中訊) モデル 200,201,203
*/
package webbroker3.accountopen.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;

import webbroker3.accountopen.WEB3AccOpenExpAccountOpen;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenTempDao;
import webbroker3.accountopen.data.ExpAccountOpenTempPK;
import webbroker3.accountopen.data.ExpAccountOpenTempParams;
import webbroker3.accountopen.data.ExpAccountOpenTempRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferUnitService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者口座開設データ移管UnitServiceImpl)<BR>
 * 管理者口座開設データ移管UnitService実装クラス<BR>
 * （トランザクション属性=TX_CREATE_NEW）<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminAccOpenDataTransferUnitServiceImpl implements WEB3AdminAccOpenDataTransferUnitService
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminAccOpenDataTransferUnitServiceImpl.class);

    /**
     * @@roseuid 4A8A0835007C
     */
    public WEB3AdminAccOpenDataTransferUnitServiceImpl()
    {
    }

    /**
     * 口座開設移管の処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「口座開設（データ移管一件）process」 参照 <BR>
     * @@param l_expAccountOpen - (口座開設見込客)<BR>
     * 口座開設見込客<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A8278EF02BF
     */
    public void process(WEB3AccOpenExpAccountOpen l_expAccountOpen) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "process(WEB3AccOpenExpAccountOpen,String,String,String) ";
        log.entering(STR_METHOD_NAME);

        //処理区分
        String l_strProcessDiv = WEB3StatusDef.DEALT;
        //エラー情報
        String l_strErrorMessage = null;
        boolean l_blnIsCheckError = false;
        try
        {
            //指定された証券会社コード、部店コードが部店テーブルに存在するかをチェックする。
            validateBranch(l_expAccountOpen.getBranchCode(), l_expAccountOpen.getInstitutionCode());

            //口座開設申込データの入力チェックを行う。 
            //チェックタイプ：　@3：伝票作成 
            //顧客コード自動採番フラグ：　@自動採番を行わない
            l_expAccountOpen.validateAccountOpenRegistInfo(
                WEB3ValidateTypeDef.VOUCHER_CREATED,
                WEB3AccOpenAccountCodeAutoFlagDef.NOT_AUTO);

            //同一顧客が重複して登録されていないかチェックする。
            l_expAccountOpen.validateAccountCode();

            //金融機@関（銀行）マスタを検索し、該当行があるかをチェックする。
            l_expAccountOpen.validateFinInstitution();
        }
        catch (WEB3BaseException l_ex)
        {
            //以上のチェックで、エラーが発生の場合
            log.error("以上のチェックで、エラーが発生の場合", l_ex);

            l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
            l_strErrorMessage = l_ex.getErrorInfo().getErrorMessage();
            l_blnIsCheckError = true;
        }

        if (!l_blnIsCheckError)
        {
            boolean l_blnIsUpdateError = false;
            //以上のチェックで、エラーが発生しない場合、口座開設見込客データを更新する
            try
            {
                QueryProcessor l_queryProcesser =
                    Processors.getDefaultProcessor();

                ExpAccountOpenParams l_expAccountOpenParams =
                    (ExpAccountOpenParams)l_expAccountOpen.getDataSourceObject();
                l_queryProcesser.doInsertQuery(l_expAccountOpenParams);
            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
                l_strErrorMessage = "DBへのアクセスに失敗しました。";
                l_blnIsUpdateError = true;
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);

                l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
                l_strErrorMessage = "DBへのアクセスに失敗しました。";
                l_blnIsUpdateError = true;
            }

            if (!l_blnIsUpdateError)
            {
                try
                {
                    // シーケンス図に以上の処理でエラーが発生しない場合、以下の処理を行う
                    //指定の口座開設見込客の情報より、口座開設伝票を作成する。
                    WEB3AccOpenVoucherCreatedService l_voucherCreatedService = 
                        new WEB3AccOpenVoucherCreatedServiceImpl();
                    l_voucherCreatedService.createAccOpenVoucher(l_expAccountOpen);
                }
                catch (WEB3BaseException l_ex)
                {
                    //口座開設見込客データを更新で、エラーが発生の場合
                    log.error("口座開設伝票を作成するで、エラーが発生の場合", l_ex);

                    l_strProcessDiv = WEB3StatusDef.DATA_ERROR;
                    l_strErrorMessage = l_ex.getErrorInfo().getErrorMessage();
                }
            }
        }

        //当該口座開設見込客に対して口座開設見込客一時データを更新する。
        //証券会社コード：　@口座開設見込客.get証券会社コード()
        //識別コード：　@口座開設見込客.get識別コード()
        //処理区分：
        //シーケンス図に以上の処理でエラーが発生する場合、"9:エラー"と更新する。
        //以外の場合、"1:処理済"と更新する。
        //エラー情報：
        //シーケンス図に以上の処理でエラーが発生する場合、エラーメッセージを格納する。
        //（*）エラー情報の長さが200バイトを超える場合、201以上の内容を切捨てる。
        if (l_strErrorMessage != null && l_strErrorMessage.getBytes().length > 200)
        {
            l_strErrorMessage = new String(l_strErrorMessage.getBytes(), 0, 200);
        }

        this.updateAccOpenExpAccountOpenTemp(l_expAccountOpen.getInstitutionCode(),
            l_expAccountOpen.getRequestNumber(),
            l_strProcessDiv,
            l_strErrorMessage);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate部店)<BR>
     * 指定された証券会社コード、部店コードが部店テーブルに存在するかをチェックする。<BR>
     * <BR>
     * １）　@部店ＤＡＯを利用して部店テーブルを検索する。<BR>
     *  　@　@－該当データがない場合は、以下の例外をスローする。<BR>
     *  　@　@「該当部店データなし」<BR>
     * 　@　@　@　@class      :  WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag      :  BUSINESS_ERROR_01386<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4A827CE201A5
     */
    private void validateBranch(String l_strBranchCode, String l_strInstitutionCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateBranch(String,String) ";
        log.entering(STR_METHOD_NAME);

    	//指定された証券会社コード、部店コードが部店テーブルに存在するかをチェックする。
    	//部店ＤＡＯを利用して部店テーブルを検索する。
    	//－該当データがない場合は、以下の例外をスローする。
    	//「該当部店データなし」
        BranchRow l_branchRow = null;
		try
        {
		    l_branchRow = BranchDao.findRowByInstitutionCodeBranchCode(
                l_strInstitutionCode,
                l_strBranchCode);

            if (l_branchRow == null)
            {
                log.debug("該当部店データなし。");
                log.exiting(STR_METHOD_NAME);

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01386, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当部店データなし。");
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (update口座開設見込客一時)<BR>
     * 当該口座開設見込客に対して口座開設見込客一時データを更新する。<BR>
     * <BR>
     * １）　@口座開設見込客一時paramsを取得する。<BR>
     * 検索条件：<BR>
     * 　@証券会社コード：引数.証券会社コード<BR>
     * 　@識別コード：引数.識別コード<BR>
     * <BR>
     * ２）　@以下のフィールドを更新する。<BR>
     * 　@更新者コード：管理者から管理者コード<BR>
     * 　@更新日時：処理日時<BR>
     * 　@口座開設見込客一時.処理区分：引数.処理区分<BR>
     * 　@口座開設見込客一時.エラー情報：引数.エラー情報<BR>
     * <BR>
     * 　@DB更新仕様「口座開設見込客一時（Update）仕様.xls」参照<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * @@param l_strRequestNumber - (識別コード)<BR>
     * 識別コード<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 処理区分<BR>
     * @@param l_strErrorInfo - (エラー情報)<BR>
     * エラー情報<BR>
     * @@throws WEB3BaseException
     * @@return Void
     * @@roseuid 4A8287A100BB
     */
    private void updateAccOpenExpAccountOpenTemp(
        String l_strInstitutionCode,
        String l_strRequestNumber,
        String l_strProcessDiv,
        String l_strErrorInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = 
            "updateAccOpenExpAccountOpenTemp(String, String, String, String,String) ";
        log.entering(STR_METHOD_NAME);

        ExpAccountOpenTempRow l_expAccountOpenTempRow = null;

        try
        {
            //口座開設見込客一時paramsを取得する。
            l_expAccountOpenTempRow = ExpAccountOpenTempDao.findRowByPk(
                new ExpAccountOpenTempPK(
                    l_strInstitutionCode,
                    l_strRequestNumber));
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //以下のフィールドを更新する。
        ExpAccountOpenTempParams l_expAccountOpenTemParams =
            new ExpAccountOpenTempParams(l_expAccountOpenTempRow);

        //更新者コード：管理者から管理者コード
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        l_expAccountOpenTemParams.setLastUpdater(l_admin.getAdministratorCode());

        //更新日時：処理日時
        l_expAccountOpenTemParams.setLastUpdatedTimestamp(
            GtlUtils.getSystemTimestamp());

        //口座開設見込客一時.処理区分：引数.処理区分
        l_expAccountOpenTemParams.setStatus(l_strProcessDiv);

        //口座開設見込客一時.エラー情報：引数.エラー情報
        l_expAccountOpenTemParams.setErrorInfo(l_strErrorInfo);

        try
        {
            WEB3DataAccessUtility.updateRow(l_expAccountOpenTemParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
}@
