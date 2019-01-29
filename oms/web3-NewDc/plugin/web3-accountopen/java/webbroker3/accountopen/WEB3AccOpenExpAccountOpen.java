head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenExpAccountOpen.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設見込客(WEB3AccOpenExpAccountOpen)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/10 張宝楠 (中訊) 新規作成
                   2006/06/09 肖志偉 (中訊) 仕様変更 モデル048,050
                   2006/06/23 猪俣（SCS）仕様変更・モデル070
                   2006/07/10 周捷 (中訊) 仕様変更 モデル026 ＤＢ更新仕様007
                   2006/08/14 李俊 (中訊) 仕様変更 モデル090
                   2006/11/30 何文敏 (中訊) 仕様変更 モデル116
Revision History : 2007/11/23 武波 (中訊) 仕様変更 モデル No.149
Revision History : 2008/12/12 劉仁和 (中訊) 仕様変更 モデル No.158
Revision History : 2009/02/16 柴双紅 (中訊) 仕様変更 ＤＢレイアウト No.052
Revision History : 2009/03/16 柴双紅 (中訊) 仕様変更 ＤＢレイアウト No.051
Revision History : 2009/08/13 柴双紅 (中訊) 仕様変更 モデルNo.170,No.179 ＤＢ更新仕様No.041,No.042,No.049,No.050
Revision History : 2009/09/09 張騰宇 (中訊) 仕様変更 ＤＢレイアウト No.065 No.066
Revision History : 2009/09/15 張騰宇 (中訊) 仕様変更 モデル No.214
Revision History : 2010/02/21 張騰宇 (中訊) 仕様変更 モデル No.220
*/

package webbroker3.accountopen;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.dbind.ListPage;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.enumerated.EnumeratedManager;
import com.fitechlabs.xtrade.plugin.security.oplogin.IllegalSessionStateException;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.accountopen.data.AccOpenItemMasterRow;
import webbroker3.accountopen.data.AccOpenVoucherStatusRow;
import webbroker3.accountopen.data.AccOpenWaitingParams;
import webbroker3.accountopen.data.ExpAccountOpenDao;
import webbroker3.accountopen.data.ExpAccountOpenParams;
import webbroker3.accountopen.data.ExpAccountOpenRow;
import webbroker3.accountopen.data.YCustomerParams;
import webbroker3.accountopen.data.YCustomerRow;
import webbroker3.accountopen.define.WEB3AccOpenAccountCodeAutoFlagDef;
import webbroker3.accountopen.define.WEB3AccOpenAnnualIncomeDivValueDef;
import webbroker3.accountopen.define.WEB3AccOpenInvestmentExperienceDef;
import webbroker3.accountopen.define.WEB3AccOpenUpdateItemDef;
import webbroker3.accountopen.define.WEB3AccountOpenExpAccountOpenSymbolNameDef;
import webbroker3.accountopen.define.WEB3AccountOpenStatusDivDef;
import webbroker3.accountopen.message.WEB3AccOpenApplyInfo;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3Crypt;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccOpenAccountDivDef;
import webbroker3.common.define.WEB3AppliMotivatDivDef;
import webbroker3.common.define.WEB3CheckDivDef;
import webbroker3.common.define.WEB3CompanyFormationDivDef;
import webbroker3.common.define.WEB3DuplicationDivDef;
import webbroker3.common.define.WEB3ExperienceDivDef;
import webbroker3.common.define.WEB3FamilyRelationshipDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.common.define.WEB3FinTransferDivDef;
import webbroker3.common.define.WEB3FundBudgetDivDef;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3IdConfirmDocDivDef;
import webbroker3.common.define.WEB3InvestPurposeDivDef;
import webbroker3.common.define.WEB3ItemCheckModeDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3MoneyAmountDivDef;
import webbroker3.common.define.WEB3OccupationDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PrintFlagDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.common.define.WEB3ReviewCodeDef;
import webbroker3.common.define.WEB3SexDef;
import webbroker3.common.define.WEB3TaxTypeDivDef;
import webbroker3.common.define.WEB3TransCommissionDef;
import webbroker3.common.define.WEB3TransferDivDef;
import webbroker3.common.define.WEB3ValidateTypeDef;
import webbroker3.common.define.WEB3VoucherStatusDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeEra;
import webbroker3.gentrade.WEB3GentradeMailAddressDuplicationCheck;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3PasswordUtility;
import webbroker3.gentrade.data.AccountInfoMstRow;
import webbroker3.gentrade.data.EraRow;
import webbroker3.gentrade.data.FinInstitutionBankDao;
import webbroker3.gentrade.data.MainAccountAllRow;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (口座開設見込客)<BR>
 * 口座開設見込客<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccOpenExpAccountOpen implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenExpAccountOpen.class);

    /**
     * (口座開設見込客行)<BR>
     * 口座開設見込客行<BR>
     * <BR>
     * ※ 口座開設見込客ParamsクラスはDDLより自動生成する。<BR>
     */
    private ExpAccountOpenParams expAccountOpenParams;

    /**
     * (口座開設伝票作成ステータス)<BR>
     * 各伝票の作成ステータス配列。<BR>
     */
    private WEB3AccOpenVoucherCreatedStatus[] accOpenVoucherCreatedStatuses;

    /**
     * (Y客マスタ行)<BR>
     * Y客マスタ行<BR> 
     * <BR>
     * ※ Y客マスタParamsクラスはDDLより自動生成する。<BR> 
     */
    private YCustomerParams yCustomerParams;
    
    /**
     * (口座開設審査待ち情報リスト)<BR>
     * 口座開設審査待ち情報リスト<BR>
     */
    private ArrayList accOpenJudgeWaitingInfoList;
    
    /**
     * (口座開設見込客)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 口座開設見込客を生成する。<BR>
     * <BR>
     * 条件１、または条件２の内容で、口座開設見込客テーブルを検索する。<BR>
     * <BR>
     * 　@[条件１]　@※部店コード未指定（部店コード == null）の場合<BR>
     * 　@口座開設見込客.証券会社コード = 証券会社コード<BR>
     * 　@口座開設見込客.口座コード = 口座コード<BR>
     * <BR>
     * 　@[条件２]　@※部店コード指定（部店コード != null）の場合<BR>
     * 　@口座開設見込客.証券会社コード = 証券会社コード<BR>
     * 　@口座開設見込客.部店コード = 部店コード<BR>
     * 　@口座開設見込客.口座コード = 口座コード<BR>
     * <BR>
     * 検索結果が２件以上存在する場合は、顧客が重複して登録されていると<BR>
     * 判定し例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01313<BR>
     * 検索結果に一致する行が存在しない場合は、nullを返却する。<BR>
     * <BR>
     * 検索結果が１件の場合、検索結果の口座開設見込客行オブジェクトを<BR>
     * 引数に指定して、コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strAccountCode - 口座コード
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 419C3469032F
     */
    public WEB3AccOpenExpAccountOpen(String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenExpAccountOpen(String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //条件１、または条件２の内容で、口座開設見込客テーブルを検索する。

            //[条件１]　@※部店コード未指定（部店コード == null）の場合
            //口座開設見込客.証券会社コード = 証券会社コード
            //口座開設見込客.口座コード = 口座コード

            //[条件２]　@※部店コード指定（部店コード != null）の場合
            //口座開設見込客.証券会社コード = 証券会社コード
            //口座開設見込客.部店コード = 部店コード
            //口座開設見込客.口座コード = 口座コード

            String l_strQueryString = null;
            Object[] l_objQueryDataContainer = null;

            if (l_strBranchCode != null)
            {
                l_strQueryString = "institution_code = ? and branch_code = ? and account_code = ?";
                l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strBranchCode, l_strAccountCode};
            }
            else
            {
                l_strQueryString = "institution_code = ? and account_code = ?";
                l_objQueryDataContainer = new Object[]{l_strInstitutionCode, l_strAccountCode};
            }

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer
            );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //検索結果に一致する行が存在しない場合は、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            throw new NotFoundException("検索結果に一致する行が存在しない");
        }


        //検索結果が２件以上存在する場合は、
        //顧客が重複して登録されていると判定し例外をスローする。
        if (l_lisRecords.size() >= 2)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                this.getClass().getName() + STR_METHOD_NAME,
                "検索結果が２件以上存在する");
        }

        ExpAccountOpenParams l_expAccountOpenParams = (ExpAccountOpenParams)l_lisRecords.get(0);
        init(l_expAccountOpenParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (口座開設見込客)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * 口座開設見込客を生成する。<BR>
     * <BR>
     * 以下の条件で口座開設見込客テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@口座開設見込客.証券会社コード = 証券会社コード<BR>
     * 　@口座開設見込客.識別コード = 識別コード<BR>
     * <BR>
     * 検索結果の口座開設見込客行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strRequestNumber - 識別コード
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 41919DEA0256
     */
    public WEB3AccOpenExpAccountOpen(String l_strInstitutionCode, String l_strRequestNumber)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenExpAccountOpen(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //以下の条件で口座開設見込客テーブルを検索する。
            //[条件]
            //口座開設見込客.証券会社コード = 証券会社コード
            //口座開設見込客.識別コード = 識別コード

            ExpAccountOpenParams l_expAccountOpenParams = (ExpAccountOpenParams)ExpAccountOpenDao.findRowByPk(
                l_strInstitutionCode,
                l_strRequestNumber);

            init(l_expAccountOpenParams);

        }
        catch (DataFindException l_ex)
        {
            throw new NotFoundException("検索結果に一致する行が存在しない");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (口座開設見込客)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * １）　@オブジェクト生成<BR>
     * 　@口座開設見込客オブジェクトを生成する。<BR>
     * <BR>
     * ２）　@口座開設伝票作成ステータスプロパティのセット<BR>
     * 　@口座開設伝票作成ステータス.get口座開設伝票作成ステータス()の戻り値を<BR>
     * 生成したオブジェクトのプロパティにセットする。<BR>
     * <BR>
     * 　@[get口座開設伝票作成ステータス()に指定する引数]<BR>
     * 　@証券会社コード：　@口座開設見込行.証券会社コード<BR>
     * 　@識別コード：　@口座開設見込行.識別コード<BR>
     * <BR>
     * ２）　@口座開設見込客行プロパティのセット<BR>
     * 　@口座開設見込行を生成したオブジェクトのプロパティにセットし、返却する。<BR>
     * <BR>
     * ※ 口座開設見込客ParamsクラスはDDLより自動生成する。<BR>
     * @@param l_expAccountOpenParams - 口座開設見込客行<BR>
     * <BR>
     * ※ 口座開設見込客ParamsクラスはDDLより自動生成する。<BR>
     *
     * @@return webbroker3.accountopen.WEB3AccOpenExpAccountOpen
     * @@roseuid 41919DAA0302
     */
    public WEB3AccOpenExpAccountOpen(ExpAccountOpenParams l_expAccountOpenParams)
        throws WEB3BaseException
    {
        init(l_expAccountOpenParams);
    }

    /**
     * (口座開設見込客)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * 口座開設見込客を生成する。 <BR>
     * 以下の条件で口座開設見込客テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@口座開設見込客.証券会社コード = 証券会社コード <BR>
     * 　@引数.部店コード != nullの場合は、andで下記を追加 <BR>
     * 　@口座開設見込客.部店コード = 部店コード <BR>
     * 　@引数.識別コード != nullの場合は、andで下記を追加 <BR>
     * 　@口座開設見込客.識別コード = 識別コード <BR>
     * 　@引数.口座コード != nullの場合は、andで下記を追加 <BR>
     * 　@口座開設見込客.口座コード = 口座コード <BR>
     * <BR>
     * <BR>
     * 検索結果が２件以上存在する場合は、顧客が重複して登録されていると判定し例外をスローする。 <BR>
     * 検索結果に一致する行が存在しない場合は、nullを返却する。 <BR>
     * <BR>
     * 検索結果が１件の場合、検索結果の口座開設見込客行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。<BR>
     * @@param l_strInstitutionCode - 証券会社コード
     * @@param l_strBranchCode - 部店コード
     * @@param l_strRequestNumber - 識別コード
     * @@param l_strAccountCode - 口座コード
     * @@return WEB3AccOpenExpAccountOpen
     * @@throws WEB3BaseException, NotFoundException
     */
    public WEB3AccOpenExpAccountOpen(
        String l_strInstitutionCode, String l_strBranchCode, String l_strRequestNumber, String l_strAccountCode)
        throws WEB3BaseException, NotFoundException
    {
        final String STR_METHOD_NAME = " WEB3AccOpenExpAccountOpen(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            String l_strQueryString = null;

            Object[] l_objQueryDataContainer = null;
            List l_lisQueryDataContainer = new ArrayList();

            l_strQueryString = "institution_code = ? ";
            l_lisQueryDataContainer.add(l_strInstitutionCode);

            //引数.部店コード != nullの場合は、andで下記を追加 口座開設見込客.部店コード = 部店コード
            if (l_strBranchCode != null)
            {
                l_strQueryString = l_strQueryString + " and branch_code = ? ";
                l_lisQueryDataContainer.add(l_strBranchCode);
            }
            //引数.識別コード != nullの場合は、andで下記を追加 口座開設見込客.識別コード = 識別コード
            if (l_strRequestNumber != null)
            {
                l_strQueryString = l_strQueryString + " and acc_open_request_number = ? ";
                l_lisQueryDataContainer.add(l_strRequestNumber);
            }
            //引数.口座コード != nullの場合は、andで下記を追加 口座開設見込客.口座コード = 口座コード
            if (l_strAccountCode != null)
            {
                l_strQueryString = l_strQueryString + " and account_code = ? ";
                l_lisQueryDataContainer.add(l_strAccountCode);
            }

            l_objQueryDataContainer = new Object[l_lisQueryDataContainer.size()];
            l_lisQueryDataContainer.toArray(l_objQueryDataContainer);
            l_lisRecords = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_objQueryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //検索結果に一致する行が存在しない場合は、nullを返却する。
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            throw new NotFoundException("検索結果に一致する行が存在しない");
        }

        //検索結果が２件以上存在する場合は、
        //顧客が重複して登録されていると判定し例外をスローする。
        if (l_lisRecords.size() >= 2)
        {
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                this.getClass().getName() + STR_METHOD_NAME,
                "検索結果が２件以上存在する");
        }

        ExpAccountOpenParams l_expAccountOpenParams = (ExpAccountOpenParams)l_lisRecords.get(0);
        init(l_expAccountOpenParams);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (get伝票ステータス)<BR>
     * 各口座開設伝票のステータスを取得する。<BR>
     * <BR>
     * this.口座開設伝票ステータス[]を返却する。<BR>
     * @@return webbroker3.accountopen.WEB3AccOpenVoucherCreatedStatus[]
     * @@roseuid 419196370081
     */
    public WEB3AccOpenVoucherCreatedStatus[] getVoucherStatus()
    {
        return this.accOpenVoucherCreatedStatuses;
    }

    /**
     * (get口座開設状況区分)<BR>
     * 口座開設状況区分を取得する。<BR>
     * <BR>
     * 口座開設状況区分<BR>
     * 　@0：　@DEFAULT（未開設）<BR>
     * 　@1：　@開設中<BR>
     * 　@2：　@エラー発生<BR>
     * 　@3：　@開設済<BR>
     * <BR>
     * <BR>
     * １）　@完了済の判定<BR>
     * 　@口座開設完了済（this.口座開設見込客行.口座登録日 != null）の場合、<BR>
     * ”開設済”を返却する。<BR>
     * <BR>
     * ２）　@未開設，エラー発生，解説中の判定<BR>
     * <BR>
     * 　@２－１）　@伝票作成ステータスコード取得<BR>
     * 　@　@（this.口座開設伝票ステータス[] == null）の場合、<BR>
     * ”DEFAULT（未開設）”を返却する。<BR>
     * 　@　@this.口座開設伝票ステータス[]各要素の伝票作成ステータスを<BR>
     * 配列で取得する。<BR>
     * <BR>
     * 　@２－２）　@ステータス判定<BR>
     * 　@　@取得した伝票作成ステータスコードについて、以下の判定を行う。<BR>
     * <BR>
     * 　@　@－すべての要素の伝票作成ステータスが”DEFAULT（伝票未作成）”の場合、<BR>
     * ”DEFAULT（未開設）”を返却する。<BR>
     * 　@　@－伝票作成ステータスに”送信エラー”，または”受信エラー”が<BR>
     * １つでも含まれる場合、”エラー発生”を返却する。<BR>
     * 　@　@－上記以外、”開設中”を返却する。<BR>
     * @@return String
     * @@roseuid 419C6F580022
     */
    public String getAccountOpenStatusDiv()
    {
        final String STR_METHOD_NAME = " getAccountOpenStatusDiv()";
        log.entering(STR_METHOD_NAME);


        //完了済の判定
        //口座開設完了済（this.口座開設見込客行.口座登録日 != null）の場合、”開設済”を返却する。
        if (this.expAccountOpenParams.getAccountOpenDate() != null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenStatusDivDef.OPEN_COMPLETE;
        }

        //（this.口座開設伝票ステータス[] == null）の場合、”DEFAULT（未開設）”を返却する。
        if (this.accOpenVoucherCreatedStatuses == null)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenStatusDivDef.DEFAULT;
        }

        boolean l_blnIsAllDefault = true;

        for (int i = 0; i < this.accOpenVoucherCreatedStatuses.length; i++)
        {
            String l_strVoucherStatus = accOpenVoucherCreatedStatuses[i].getVoucherStatus();
            if (!WEB3VoucherStatusDef.DEFAULT.equals(l_strVoucherStatus))
            {
                l_blnIsAllDefault = false;
            }

            //伝票作成ステータスに”送信エラー”，または”受信エラー”が１つでも含まれる場合、”エラー発生”を返却する。
            if (WEB3VoucherStatusDef.SEND_ERROR.equals(l_strVoucherStatus)
                || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_strVoucherStatus))
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountOpenStatusDivDef.ERROR;
            }
        }

        if (l_blnIsAllDefault)
        {
            //すべての要素の伝票作成ステータスが”DEFAULT（伝票未作成）”の場合、”DEFAULT（未開設）”を返却する。
            log.exiting(STR_METHOD_NAME);
            return WEB3AccountOpenStatusDivDef.DEFAULT;
        }

        //上記以外、”開設中”を返却する。
        log.exiting(STR_METHOD_NAME);
        return WEB3AccountOpenStatusDivDef.OPENING;
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * this.口座開設見込客行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 4192CD5C015F
     */
    public String getInstitutionCode()
    {
        return this.expAccountOpenParams.getInstitutionCode();
    }

    /**
     * (get部店コード)<BR>
     * 部店コードを取得する。<BR>
     * <BR>
     * this.口座開設見込客行.部店コードを返却する。<BR>
     * @@return String
     * @@roseuid 4193443902D5
     */
    public String getBranchCode()
    {
        return this.expAccountOpenParams.getBranchCode();
    }

    /**
     * (get口座コード)<BR>
     * 口座コードを取得する。<BR>
     * <BR>
     * this.口座開設見込客行.口座コードを返却する。<BR>
     * @@return String
     * @@roseuid 41945E0C035D
     */
    public String getAccountCode()
    {
        return this.expAccountOpenParams.getAccountCode();
    }

    /**
     * (get口座区分)<BR>
     * 口座区分を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.口座区分を返却する。<BR>
     * @@return String
     * @@roseuid 4194624900FC
     */
    public String getAccountDiv()
    {
        return this.expAccountOpenParams.getAccountDiv();
    }

    /**
     * (get識別コード)<BR>
     * 識別コードを取得する。<BR>
     * <BR>
     * this.口座開設見込客行.識別コードを返却する。<BR>
     * @@return String
     * @@roseuid 4192CE200065
     */
    public String getRequestNumber()
    {
        return this.expAccountOpenParams.getAccOpenRequestNumber();
    }

    /**
     * (get項目値)<BR>
     * this.口座開設見込客行より、引数の列物理名が示す項目の値を取得し返却する。<BR>
     * @@param l_strColumnName - 列物理名<BR>
     * <BR>
     * ※口座開設見込客テーブルの列物理名<BR>
     *
     * @@return Object
     * @@roseuid 4192D56A0314
     */
    public Object getItemValue(String l_strColumnName)
    {
        return this.expAccountOpenParams.getColumn(l_strColumnName);
    }

    /**
     * (is伝票作成可能)<BR>
     * 伝票作成が可能な状態かを判定する。<BR>
     * <BR>
     * 口座開設完了済（this.口座開設見込客行.口座登録日 != null）の場合、<BR>
     * falseを返却する。<BR>
     * 以外の場合、以下の判定を行う。<BR>
     * <BR>
     * ○ ONLINE時間帯の場合（取引時間管理.isトリガ発行() == true）<BR>
     * <BR>
     * 　@　@口座開設伝票作成ステータス.get伝票作成ステータス()が以下の<BR>
     * 何れかである場合、trueを返却する。<BR>
     * <BR>
     * 　@　@　@DEFAULT（伝票未作成）<BR>
     * 　@　@　@送信エラー<BR>
     * 　@　@　@受信エラー<BR>
     * <BR>
     * 　@　@但し、｛送信保留中，作成済｝の場合、顧客登録（G11）伝票の<BR>
     * ステータスを取得(*1)、<BR>
     * 　@　@顧客登録（G11）伝票のステータスが、｛DEFAULT（伝票未作成），<BR>
     * 送信エラー，受信エラー｝の<BR>
     * 　@　@何れかだった場合は、trueを返却する。<BR>
     * <BR>
     * 　@　@以外は、falseを返却する。<BR>
     * <BR>
     * 　@　@(*1) 顧客登録（G11）伝票のステータスの取得<BR>
     * <BR>
     * 　@　@this.口座開設伝票作成ステータス[]より、（伝票コード == 顧客登録）<BR>
     * である最初の要素を取得する。<BR>
     * 　@　@取得した要素の伝票作成ステータスを顧客登録（G11）伝票の<BR>
     * ステータスとする。<BR>
     * 　@　@但し、（伝票コード == 顧客登録）の要素が１件もなかった場合は、<BR>
     * DEFAULT（伝票未作成）とする。<BR>
     * <BR>
     * ○ OFFLINE時間帯の場合（取引時間管理.isトリガ発行() == false）<BR>
     * <BR>
     * 　@　@口座開設伝票作成ステータス.get伝票作成ステータス()が以下の<BR>
     * 何れかである場合、trueを返却する。<BR>
     * <BR>
     * 　@　@　@DEFAULT（伝票未作成）<BR>
     * 　@　@　@作成済<BR>
     * 　@　@　@送信保留中<BR>
     * 　@　@　@送信エラー<BR>
     * 　@　@　@受信エラー<BR>
     * <BR>
     * 　@　@以外、falseを返却する。<BR>
     * @@param l_accOpenVoucherCreatedStatus - 口座開設伝票作成ステータス
     * @@return boolean
     * @@roseuid 419331D0020A
     */
    public boolean isVoucherCreatedPossible(WEB3AccOpenVoucherCreatedStatus l_accOpenVoucherCreatedStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isVoucherCreatedPossible(WEB3AccOpenVoucherCreatedStatus)";
        log.entering(STR_METHOD_NAME);
        
        if (l_accOpenVoucherCreatedStatus == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (this.expAccountOpenParams != null && this.expAccountOpenParams.getAccountOpenDate() != null)
        {
            log.debug("口座開設完了済（this.口座開設見込客行.口座登録日 != null）の場合、falseを返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        if (WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
        {
            log.debug("ONLINE時間帯の場合（取引時間管理.isトリガ発行() == true）");
            
            if (WEB3VoucherStatusDef.DEFAULT.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus()))
            {
                log.debug("口座開設伝票作成ステータス.get伝票作成ステータス()が(DEFAULT（伝票未作成）, 送信エラー, 受信エラー)の何れかである場合、trueを返却する。");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else if (WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_RESERVING.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus()))
            {
                //（伝票コード == 顧客登録）の要素が１件もなかった場合は、DEFAULT（伝票未作成）とする。
                String l_strRegVoucherStatus = WEB3VoucherStatusDef.DEFAULT;

                if (this.accOpenVoucherCreatedStatuses != null)
                {
                    int l_intLength = this.accOpenVoucherCreatedStatuses.length;
                    
                    //this.口座開設伝票作成ステータス[]より、（伝票コード == 顧客登録）である最初の要素を取得する。 
                    //取得した要素の伝票作成ステータスを顧客登録（G11）伝票のステータスとする。
                    for (int i = 0; i < l_intLength; i++)
                    {
                        if (WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST.equals(
                            ((AccOpenVoucherStatusRow) this.accOpenVoucherCreatedStatuses[i].getDataSourceObject()).getRequestCode()))
                        {
                            log.debug("伝票コード == 顧客登録");
                            l_strRegVoucherStatus = this.accOpenVoucherCreatedStatuses[i].getVoucherStatus();
                            break;
                        }
                    }
                }
                
                if (WEB3VoucherStatusDef.DEFAULT.equals(l_strRegVoucherStatus)
                        || WEB3VoucherStatusDef.SEND_ERROR.equals(l_strRegVoucherStatus)
                        || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_strRegVoucherStatus))
                {
                    log.debug("顧客登録（G11）伝票のステータスが、｛DEFAULT（伝票未作成），送信エラー，受信エラー｝の何れかだった場合は、trueを返却する。");
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
            
            log.debug("falseを返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        else
        {
            log.debug("OFFLINE時間帯の場合（取引時間管理.isトリガ発行() == false）");
            
            if (WEB3VoucherStatusDef.DEFAULT.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_RESERVING.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.SEND_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus())
                || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_accOpenVoucherCreatedStatus.getVoucherStatus()))
            {
                log.debug("口座開設伝票作成ステータス.get伝票作成ステータス()が(DEFAULT（伝票未作成）,作成済,送信保留中,送信エラー,受信エラー)の何れかである場合、trueを返却する。");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                log.debug("falseを返却する。");
                log.exiting(STR_METHOD_NAME);
                return false;
            }
        }
    }

    /**
     * (is伝票作成可能)<BR>
     * 口座開設見込客に関連する伝票について、すべて作成不可であればfalse、<BR>
     * どれか１つでも作成できればtrueを返却する。<BR>
     * <BR>
     * １）  口座開設審査区分判定 <BR>
     *   １－１） 審査区分が「審査待ち」「否認」（this.get口座開設審査区分() == 0 OR 2）の場合、 <BR>
     *            falseを返却する。 <BR>
     * <BR>
     *   １－２）  審査区分が「認可」（this.get口座開設審査区分() == 1）の場合、 <BR>
     *             this.口座開設伝票ステータスの伝票作成ステータス列に存在する値が <BR>
     *             (「伝票未作成」、「作成済」、「送信保留中」のみ)  && 
	 *		 	   (「作成済」件数 > 0 の場合、または「送信保留中」件数 > 0 )の場合 <BR>
     *             falseを返却する。 <BR>
     * <BR>
     * <BR>
     * ２）  各伝票作成可否判定 <BR>
     * this.口座開設伝票作成ステータス[]の各要素毎に、<BR>
     * this.is伝票作成可能(.口座開設伝票作成ステータス[index])をコールする。<BR>
     * コールした結果の戻り値が、すべてfalseだった場合はfalseを返却する。<BR>
     * １件でもtrueであれば、trueを返却する。<BR>
     * <BR>
     * 但し、（this.口座開設伝票作成ステータス[] == null）の場合、trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 419337500045
     */
    public boolean isVoucherCreatedPossible() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isVoucherCreatedPossible()";
        log.entering(STR_METHOD_NAME);
        
        //１）  口座開設審査区分判定 
		//１－１） 審査区分が「審査待ち」「否認」（this.get口座開設審査区分() == 0 OR 2）の場合、 
		//		 falseを返却する。
		String l_strAccountOpenStatusJudgeDiv = this.getAccountOpenStatusJudgeDiv();
        
        if (WEB3CheckDivDef.CHECK_WAITING.equals(l_strAccountOpenStatusJudgeDiv)
            || WEB3CheckDivDef.DISAGREE.equals(l_strAccountOpenStatusJudgeDiv))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
		//１－２）  審査区分が「認可」（this.get口座開設審査区分() == 1）の場合、 
		//		   this.口座開設伝票ステータスの伝票作成ステータス列に存在する値が 
		//		   (「伝票未作成」、「作成済」、「送信保留中」のみ)  && 
		//		   (「作成済」件数 > 0 の場合、または「送信保留中」件数 > 0 )の場合、 
		//		   falseを返却する。
        if (WEB3CheckDivDef.AGREE.equals(l_strAccountOpenStatusJudgeDiv))
        {
			if (this.accOpenVoucherCreatedStatuses != null)
			{
				int l_intLength = this.accOpenVoucherCreatedStatuses.length;
            	boolean l_statusCheckFlag = true;
            	// 作成済伝票カウンタ
            	int l_intCreateComplete = 0;
            	// 送信保留中カウンタ
				int l_intSendReserving = 0;
				for (int i = 0; i < l_intLength; i++)
				{
					String l_strVaucherStatus = 
					    this.accOpenVoucherCreatedStatuses[i].getVoucherStatus();

                    // 「伝票未作成」「作成済」「送信保留中」以外は ２）各伝票作成可否判定 へ
					if (!WEB3VoucherStatusDef.DEFAULT.equals(l_strVaucherStatus) 
					       && !WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_strVaucherStatus)
					       && !WEB3VoucherStatusDef.SEND_RESERVING.equals(l_strVaucherStatus))
					{
						l_statusCheckFlag = false;
						break;
					}
					// 「作成済」ステータスカウント
					if (WEB3VoucherStatusDef.CREATE_COMPLETE.equals(l_strVaucherStatus))
					{
						l_intCreateComplete++;
					}
					// 「送信保留中」ステータスカウント
					if (WEB3VoucherStatusDef.SEND_RESERVING.equals(l_strVaucherStatus))
					{
						l_intSendReserving++;
					}

				}
				if ((l_statusCheckFlag && l_intCreateComplete > 0) || 
								(l_statusCheckFlag && l_intSendReserving > 0) )
				{
					log.exiting(STR_METHOD_NAME);
					return false;
				}
			}
        }
        
        //２）  各伝票作成可否判定
        if (this.accOpenVoucherCreatedStatuses == null 
                  || this.accOpenVoucherCreatedStatuses.length == 0)
        {
            log.debug("（this.口座開設伝票作成ステータス[] == null）の場合、trueを返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (this.accOpenVoucherCreatedStatuses != null)
        {
            int l_intLength = this.accOpenVoucherCreatedStatuses.length;
            
            for (int i = 0; i < l_intLength; i++)
            {
                if (this.isVoucherCreatedPossible(this.accOpenVoucherCreatedStatuses[i]))
                {
                    log.debug("１件でもtrueであれば、trueを返却する。");
                    log.exiting(STR_METHOD_NAME);
                    return true;
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (is更新可能)<BR>
     * 口座開設見込客に関連する伝票について、すべて未作成であればtrue、<BR>
     * 以外はfalseを返却する。<BR>
     * <BR>
     * １）　@口座開設済判定<BR>
     * 　@口座開設完了済（this.口座開設見込客行.口座登録日 != null）の場合、<BR>
     * 　@falseを返却する。<BR>
     * <BR>
     * ２）  口座開設状況審査区分判定<BR> 
     *   　@　@審査区分が「審査中」「却下」<BR>
     *   　@（this.get口座開設状況審査区分() == "4" OR "5"）の場合、<BR>
     *   　@　@falseを返却する。<BR> 
     * <BR>
     * ３）　@伝票作成ステータスチェック<BR> 
     * <BR>
     * 　@◎　@（this.口座開設伝票作成ステータス[] == null）の場合、trueを返却する。<BR>
     * 　@◎　@this.口座開設伝票作成ステータス[]各要素について、<BR>
     * 　@　@　@get伝票作成ステータス()にて伝票作成ステータスを取得する。<BR>
     * 　@　@　@すべての伝票作成ステータスがDEFAULT（伝票未作成）の場合、<BR>
     * 　@　@　@trueを返却する。<BR>
     * 　@◎　@以外、falseを返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException 
     * @@roseuid 419338C40007
     */
    public boolean isUpdatedPossible() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isUpdatedPossible()";
        log.entering(STR_METHOD_NAME);

        //１）　@口座開設済判定
        if (this.expAccountOpenParams != null && this.expAccountOpenParams.getAccountOpenDate() != null)
        {
            log.debug("口座開設完了済（this.口座開設見込客行.口座登録日 != null）の場合、falseを返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）  口座開設状況審査区分判定 
        //審査区分が「審査中」「却下」の場合
        if (WEB3AccountOpenStatusDivDef.JUDGEING.equals(this.getAccountOpenStatusCheckDiv())
            || WEB3AccountOpenStatusDivDef.REJECTION.equals(this.getAccountOpenStatusCheckDiv())) 
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //３）　@伝票作成ステータスチェック
        if (this.accOpenVoucherCreatedStatuses == null || this.accOpenVoucherCreatedStatuses.length == 0)
        {
            log.debug("（this.口座開設伝票作成ステータス[] == null）の場合、trueを返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            int l_intLength = this.accOpenVoucherCreatedStatuses.length;
            int l_intStatusCnt = 0;
            for (int i = 0; i < l_intLength; i++)
            {
                if (WEB3VoucherStatusDef.DEFAULT.equals(this.accOpenVoucherCreatedStatuses[i].getVoucherStatus()))
                {
                    l_intStatusCnt += 1;
                }
                else
                {
                    log.debug("falseを返却する。");
                    log.exiting(STR_METHOD_NAME);
                    return false;
                }
            }

            if (l_intLength != 0 && l_intLength == l_intStatusCnt)
            {
                log.debug("すべての伝票作成ステータスがDEFAULT（伝票未作成）の場合、trueを返却する。");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            
            log.debug("falseを返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is取消可能)<BR>
     * <BR>
     * 口座開設見込客に関連する伝票について、すべて作成可能であればtrue、どれか１つでも不可であればfalseを返却する。<BR>
     * <BR>
     * this.口座開設伝票作成ステータス[]の各要素毎に、<BR>
     * this.is伝票作成可能(.口座開設伝票作成ステータス[index])をコールする。<BR>
     * コールした結果の戻り値が、すべてtrueだった場合はtrueを返却する。<BR>
     * １件でもfalseであれば、falseを返却する。<BR>
     * <BR>
     * 但し、（this.口座開設伝票作成ステータス[] == null）の場合、<BR>
     * または、（this.口座開設伝票作成ステータス[] がすべて 0:DEFAULT）の場合、falseを返却する。<BR>
     * <BR>
     * また、falseの場合で以下の条件に当てはまる場合もtrueを返却する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@○ OFFLINE時間帯の場合（取引時間管理.is市場開局時間帯() == false）<BR>
     * 　@　@顧客登録（G11）伝票のステータス(*1)が”送信済”で<BR>
     * 　@　@且つ、顧客登録（G11）伝票以外の伝票ステータスがすべて以下の何れかの場合。<BR>
     * <BR>
     * 　@　@　@DEFAULT（伝票未作成）<BR>
     * 　@　@　@作成済<BR>
     * 　@　@　@送信保留中<BR>
     * 　@　@　@送信エラー<BR>
     * 　@　@　@受信エラー<BR>
     * <BR>
     * 　@　@(*1) 顧客登録（G11）伝票のステータスの取得<BR>
     * <BR>
     * 　@　@this.口座開設伝票作成ステータス[]より、（伝票コード == 顧客登録）である最初の要素を取得する。<BR>
     * 　@　@取得した要素の伝票作成ステータスを顧客登録（G11）伝票のステータスとする。<BR>
     * 　@　@但し、（伝票コード == 顧客登録）の要素が１件もなかった場合は、DEFAULT（伝票未作成）とする。<BR>
     * <BR>
     * @@return boolean
     * @@roseuid 419C1A6303AC
     */
    public boolean isCanceledPossible() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isCanceledPossible()";
        log.entering(STR_METHOD_NAME);

        if (this.accOpenVoucherCreatedStatuses == null || 
            this.accOpenVoucherCreatedStatuses.length == 0)
        {
            log.debug("伝票ステータスがない場合（伝票を作成していない場合）、" +
                "[取消不可]を返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        int l_intLength = this.accOpenVoucherCreatedStatuses.length;
        int l_intFalseCount = 0;
        int l_intDefaultCount = 0;
        String l_strAccRegStatus = WEB3VoucherStatusDef.DEFAULT;
        
        for (int i = 0; i < l_intLength; i++)
        {
            WEB3AccOpenVoucherCreatedStatus l_status =
                accOpenVoucherCreatedStatuses[i];
            
            if (WEB3VoucherStatusDef.DEFAULT.equals(
                l_status.getVoucherStatus()))
            {
                l_intDefaultCount ++;
            }
            
            AccOpenVoucherStatusRow l_statusRow =
                (AccOpenVoucherStatusRow)l_status.getDataSourceObject();
            String l_strCode = l_statusRow.getRequestCode();
            
            if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone() &&
                WEB3HostRequestCodeDef.ACCOPEN_ACCOUNT_REGIST.equals(l_strCode))
            {
                l_strAccRegStatus = l_status.getVoucherStatus();
            }

            if (!this.isVoucherCreatedPossible(l_status))
            {
                l_intFalseCount ++;
            }
        }
        
        if (l_intLength == l_intDefaultCount)
        {
            log.debug("伝票ステータスがすべてDefaultの場合（伝票取消済の場合）、" +
                "[取消不可]を返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        if (l_intFalseCount == 1 && 
            WEB3VoucherStatusDef.SEND_COMPLETE.equals(l_strAccRegStatus)) {
            log.debug("OFFLINE && 顧客登録（G11）が送信済、他のステータスがtrueの場合、" +
                "[取消可能]を返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        
        if (l_intFalseCount == 0) {
            log.debug("すべての伝票ステータスがtrueの場合、" +
                "[取消可能]を返却する。");
            log.exiting(STR_METHOD_NAME);
            return true;
        } else {
            log.debug("伝票ステータスに１つでもfalseが含まれる場合、" +
                "[取消不可]を返却する。");
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
    }

    /**
     * (validate口座開設申込情報)<BR>
     * 口座開設申込データの入力チェックを行う。<BR>
     * <BR>
     * 各社カスタマイズがあれば、口座開設項目マスタテーブルに<BR>
     * 指定されたチェックを行う。<BR>
     * 各社カスタマイズがなければ、DBレイアウト 「口座開設見込客テーブル.xls」に<BR>
     * 記述されたデフォルトチェックを行う。<BR>
     * <BR>
     * this.口座開設見込客行のチェック対象項目(*1)すべてについて、<BR>
     * 以下１）～４）のチェックを行う。<BR>
     * <BR>
     * ただし、以下の条件の場合、１）～４）のチェックは行わない。<BR> 
     * チェック対象項目(*1) = 口座コード(account_code) <BR>
     * かつ、チェックタイプ = 3：伝票作成 <BR>
     * かつ、顧客コード自動採番フラグ = 1：自動採番を行う<BR> 
     * <BR>
     * 上記の条件を満たす場合、チェック対象項目(*1)の値の未入力チェックを行う。 <BR>
     * 入力があった場合、「自動採番指定時に、顧客コードが入力されています。」の例外をスローする <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_02527<BR>
     * <BR>
     * １）　@口座開設項目マスタ生成<BR>
     * 　@口座開設項目マスタを生成する。<BR>
     * 　@引数①@でnullが返却された場合は、引数②で生成する。<BR>
     * <BR>
     * 　@[コンストラクタの引数①@]<BR>
     * 　@証券会社コード：　@get証券会社コード()<BR>
     * 　@部店コード：　@get部店コード()<BR>
     * 　@口座区分：　@get口座区分()<BR>
     * 　@チェックタイプ：　@チェックタイプ<BR>
     * 　@項目物理名：　@チェック対象項目(*1)<BR>
     * <BR>
     * 　@[コンストラクタの引数②]<BR>
     * 　@証券会社コード：　@get証券会社コード()<BR>
     * 　@部店コード：　@”000”<BR>
     * 　@口座区分：　@get口座区分()<BR>
     * 　@チェックタイプ：　@チェックタイプ<BR>
     * 　@項目物理名：　@チェック対象項目(*1)<BR>
     * <BR>
     * 　@引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断し、<BR>
     * 　@口座開設項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。<BR>
     * <BR>
     * 　@[getDefault項目マスタ()に指定する引数]<BR>
     * 　@必須項目フラグ：　@<BR>
     * チェック対象項目(*1)が【Null】項目であればBooleanEnum.FALSE、<BR>
     * 　@【NotNull】項目であれば、BooleanEnum.TRUEを指定する。<BR>
     * 　@項目最大長：　@チェック対象項目(*1)の【SIZE】<BR>
     * 　@項目チェック方式：　@チェック対象項目(*1)の【項目チェック方式（WEB3デフォルト）】<BR>
     * <BR>
     * ２）　@必須項目チェック<BR>
     * 　@１）で生成した口座開設項目マスタ.validate必須項目()をコールする。<BR>
     * 　@falseが返却された場合は、対応する例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01309<BR>
     *          追加文字列: [項目名]<BR>
     * <BR>
     * 　@[validate必須項目()に指定する引数]<BR>
     * 　@項目値：　@チェック対象項目(*1)の値<BR>
     * <BR>
     * ３）　@データレングスチェック<BR>
     * 　@１）で生成した口座開設項目マスタ.validateレングス()をコールする。<BR>
     * 　@falseが返却された場合は、対応する例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01310<BR>
     *          追加文字列: [項目名]<BR>
     * <BR>
     * 　@[validateレングス()に指定する引数]<BR>
     * 　@項目値：　@チェック対象項目(*1)の値<BR>
     * <BR>
     * ４）　@有効値チェック<BR>
     * 　@○　@有効コード値チェックの場合（口座開設項目マスタ.is有効コードチェック() == true）<BR>
     * <BR>
     * 　@　@口座開設項目属性オブジェクトを生成する。<BR>
     * 　@　@引数①@でnullが返却された場合は、引数②で生成する。<BR>
     * <BR>
     * 　@　@[コンストラクタの引数①@]<BR>
     * 　@　@証券会社コード：　@get証券会社コード()<BR>
     * 　@　@部店コード：　@get部店コード()<BR>
     * 　@　@口座区分：　@get口座区分()<BR>
     * 　@　@項目物理名：　@チェック対象項目(*1)<BR>
     * <BR>
     * 　@　@[コンストラクタの引数②]<BR>
     * 　@　@証券会社コード：　@get証券会社コード()<BR>
     * 　@　@部店コード：　@”000”<BR>
     * 　@　@口座区分：　@get口座区分()<BR>
     * 　@　@項目物理名：　@チェック対象項目(*1)<BR>
     * <BR>
     * 　@　@引数①@，②で該当データがない場合は、各社カスタマイズデータが<BR>
     * ないと判断しWEB3デフォルトチェックを行う。<BR>
     * 　@　@チェック対象項目(*1)の値が存在するコード値でなければ、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01311<BR>
     *          追加文字列: [項目名]<BR>
     * 　@　@※チェック対象項目(*1)の【説明（有効コード、意味）】参照。<BR>
     * <BR>
     * 　@　@オブジェクトが生成できた場合は、各社カスタマイズデータがあると判定し、<BR>
     * 　@　@口座開設項目属性.validate有効コード値()をコールする。<BR>
     * Q&A:WEB3-ACCOUNTOPEN-A-UT-0023
     * 　@　@falseが返却された場合は、対応する例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01738<BR>
     *          追加文字列: [項目名]<BR>
     * <BR>
     * 　@　@[validate有効コード値()に指定する引数]<BR>
     * 　@　@項目値：　@チェック対象項目(*1)の値<BR>
     * <BR>
     * 　@○　@有効コード値チェック以外の場合（口座開設項目マスタ.is有効コードチェック() == false）<BR>
     * 　@　@１）で生成した口座開設項目マスタ.validate有効値()をコールする。<BR>
     * 　@　@falseが返却された場合は、対応する例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01312<BR>
     *          追加文字列: [項目名]<BR>
     * <BR>
     * 　@　@[validate有効値()に指定する引数]<BR>
     * 　@　@項目値：　@チェック対象項目(*1)の値<BR>
     * <BR>
     * <BR>
     * (*1) チェック対象項目<BR>
     * 　@DBレイアウト 「口座開設見込客テーブル.xls」内の【項目チェック方式】列に<BR>
     * 記載がある項目が対象。<BR>
     * @@param l_strValidateType - チェックタイプ<BR>
     * <BR>
     * 0：DEFAULT（顧客申込）　@<BR>
     * 1：管理者申込　@<BR>
     * 2：申込更新　@<BR>
     * 3：伝票作成<BR>
     * 4：口座開設アップロード<BR>
     * @@param l_strAccountCodeAutoFlag - チェックタイプ<BR>
     * <BR>
     * 1：　@自動採番を行う <BR>
     * 0 または null：　@自動採番を行わない<BR>
     * @@roseuid 4194616501F6
     */
    public void validateAccountOpenRegistInfo(String l_strValidateType, String l_strAccountCodeAutoFlag) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateAccountOpenRegistInfo(String,String)";
        log.entering(STR_METHOD_NAME);

        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strBranchCode = this.getBranchCode();
        String l_strAccountDiv = this.getAccountDiv();
        String l_strAccountCode = this.getAccountCode();

        WEB3Crypt l_crypt = new WEB3Crypt();
        MethodSets l_methodSets = new MethodSets();
        Iterator l_checkItems = l_methodSets.methodMap.keySet().iterator();
        
        //１）　@口座開設項目マスタ生成 
        String l_strCheckItem = null;
        ItemCheckMethod l_itemCheckMethod = null;
        WEB3AccOpenItemMaster l_accOpenItemMaster = null;
        Object l_checkItemValue = null;
        boolean initialPasswordCheckFlag = false;
        
        while (l_checkItems.hasNext())
        {
            //チェック対象項目(*1)
            l_strCheckItem = (String)l_checkItems.next();
            
            //ただし、以下の条件の場合、１）～４）のチェックは行わない。
            // チェック対象項目(*1) = 口座コード(account_code)
            // かつ、チェックタイプ = 3：伝票作成 
            // かつ、顧客コード自動採番フラグ = 1：自動採番を行う 
            // 上記の条件を満たす場合、チェック対象項目(*1)の値の未入力チェックを行う。 
            // 入力があった場合、「自動採番指定時に、顧客コードが入力されています。」の例外をスローする 
            if (WEB3AccountOpenExpAccountOpenSymbolNameDef.ACCOUNT_CODE.equals(l_strCheckItem)
                && WEB3ValidateTypeDef.VOUCHER_CREATED.equals(l_strValidateType)
                && WEB3AccOpenAccountCodeAutoFlagDef.AUTO.equals(l_strAccountCodeAutoFlag))
            {
                if (!WEB3StringTypeUtility.isEmpty((String)expAccountOpenParams.getColumn(l_strCheckItem)))
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02527,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "自動採番指定時に、顧客コードが入力されています。");  
                }
            }
            else
	        {
	            l_itemCheckMethod = (ItemCheckMethod) l_methodSets.methodMap.get(l_strCheckItem);
	            
	            log.debug("チェック対象項目(*1) ===================== " + l_strCheckItem);
	            
	            boolean l_blnItemFoundFlag = false;
	            if (l_itemCheckMethod.customizing)
	            {
	                try
	                {
	                    //コンストラクタの引数1] 
	                    log.debug("[コンストラクタの引数1]");
	                    l_accOpenItemMaster = new WEB3AccOpenItemMaster(l_strInstitutionCode,
	                        l_strBranchCode, l_strAccountDiv, l_strValidateType, l_strCheckItem);
	                    l_blnItemFoundFlag = true;
	                }
	                catch (NotFoundException l_ex)
	                {
	                    l_blnItemFoundFlag = false;
	                }
	                
	                if (!l_blnItemFoundFlag)
	                {
	                    try
	                    {
	                        //[コンストラクタの引数2] 
	                        log.debug("[コンストラクタの引数2]");
	                        l_accOpenItemMaster = new WEB3AccOpenItemMaster(l_strInstitutionCode, "000",
	                            l_strAccountDiv, l_strValidateType, l_strCheckItem);
	                        l_blnItemFoundFlag = true;
	                    }
	                    catch (NotFoundException l_nfe)
	                    {
	                        if ("initial_password".equals(l_strCheckItem))
	                        {
	                            initialPasswordCheckFlag = true;
	                        }
	                        
	                        if (l_itemCheckMethod.itemCheckMode == null)
	                        {
	                            //カスタマイズがない && 項目チェック方式 == null
	                            log.debug("カスタマイズがない && 項目チェック方式 == null");                            
	                            continue;
	                        }
	                        l_blnItemFoundFlag = false;
	                    }
	                }
	            }
	            
	            if (!l_itemCheckMethod.customizing || !l_blnItemFoundFlag)
	            {
	                //口座開設項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。
	                log.debug("引数①@，②で該当データがない場合は、各社カスタマイズデータがないと判断し、口座開設項目マスタ.getDefault項目マスタ()にてインスタンスを生成する。");
	                l_accOpenItemMaster = WEB3AccOpenItemMaster.getDefaultAccOpenItemMaster(
	                    l_itemCheckMethod.necessaryItemFlag, l_itemCheckMethod.size,
	                    l_itemCheckMethod.itemCheckMode);
	            }
	
	            //エラーレスポンスで返却する項目名の取得
	            AccOpenItemMasterRow l_itemMasterRow = (AccOpenItemMasterRow)l_accOpenItemMaster.getDataSourceObject();
	            String l_columnName = l_itemMasterRow.getItemName();
	            if (l_columnName == null || l_columnName.trim().length() == 0) 
	            {
	                l_columnName = l_itemCheckMethod.columnName;
	            }
	            
	            //項目値：　@チェック対象項目(*1)の値
	            l_checkItemValue = expAccountOpenParams.getColumn(l_strCheckItem);
	            
	            if ("initial_password".equals(l_strCheckItem))
	            {
	                l_checkItemValue = l_crypt.decrypt((String) l_checkItemValue);
	            }
	            
	            //２）　@必須項目チェック 
	            if (!l_accOpenItemMaster.validateNecessaryItem(l_checkItemValue))
	            {
	                log.debug("１）で生成した口座開設項目マスタ.validate必須項目()をコールする。falseが返却された場合は、対応する例外をスローする。");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01309,
	                    this.getClass().getName() + STR_METHOD_NAME,
	                    l_columnName);
	            }
	            
	            //３）　@データレングスチェック
	            if (!l_accOpenItemMaster.validateLength(l_checkItemValue))
	            {
	                log.debug("１）で生成した口座開設項目マスタ.validateレングス()をコールする。falseが返却された場合は、対応する例外をスローする。");
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3SystemLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01310,
	                    this.getClass().getName() + STR_METHOD_NAME,
	                    l_columnName);
	            }
	            
	            //４）　@有効値チェック 
	            if (l_accOpenItemMaster.isValidCodeCheck())
	            {
	                log.debug("○　@有効コード値チェックの場合（口座開設項目マスタ.is有効コードチェック() == true）");
	                
	                WEB3AccOpenItemAttribute l_itemAttribute = null;
	                
	                try
	                {
	                    log.debug("[コンストラクタの引数1] ");
	                    l_itemAttribute = new WEB3AccOpenItemAttribute(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strCheckItem);
	                }
	                catch (NotFoundException l_ex)
	                {
	                    try
	                    {
	                        log.debug("[コンストラクタの引数2] ");
	                        l_itemAttribute = new WEB3AccOpenItemAttribute(l_strInstitutionCode, "000", l_strAccountDiv, l_strCheckItem);
	                    }
	                    catch (NotFoundException l_nfe)
	                    {
	                        if (!isValidCodeValue(l_strCheckItem, l_checkItemValue))
	                        {
	                            log.debug("チェック対象項目(*1)の値が存在するコード値でなければ、例外をスローする。");
	                            log.exiting(STR_METHOD_NAME);
	                            throw new WEB3SystemLayerException(
	                                WEB3ErrorCatalog.BUSINESS_ERROR_01311,
	                                this.getClass().getName() + STR_METHOD_NAME,
	                                l_columnName);
	                        }
	                    }
	                }
	                //Q&A:WEB3-ACCOUNTOPEN-A-UT-0023:例外はスローされます。
	                if (l_itemAttribute != null && !l_itemAttribute.validateValidCodeValue(l_checkItemValue == null ? null : l_checkItemValue.toString()))
	                {
	                    log.debug("口座開設項目属性.validate有効コード値()をコールする。falseが返却された場合は、対応する例外をスローする。");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3SystemLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01738,
	                        this.getClass().getName() + STR_METHOD_NAME,
	                        l_columnName);
	                }
	            }
	            else
	            {
	                if (!l_accOpenItemMaster.validateValidValue(l_checkItemValue))
	                {
	                    log.debug("１）で生成した口座開設項目マスタ.validate有効値()をコールする。falseが返却された場合は、対応する例外をスローする。");
	                    log.exiting(STR_METHOD_NAME);
	                    throw new WEB3SystemLayerException(
	                        WEB3ErrorCatalog.BUSINESS_ERROR_01312,
	                        this.getClass().getName() + STR_METHOD_NAME,
	                        l_columnName);
	                }
	            }
	     
	        
	        if (initialPasswordCheckFlag)
	        {
	            //初期パスワードチェック
	            OpLoginSecurityService l_opLoginSec =
	                (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);
	        
	            long l_lngLoginId = 0;
	            try
	            {
	                l_lngLoginId = l_opLoginSec.getLoginInfo().getLoginId();//IllegalSessionStateException
	                if (l_lngLoginId != 0)
	                {
	                    WEB3GentradeAccountManager l_gentradeAccountManager = 
	                        (WEB3GentradeAccountManager)GtlUtils.getAccountManager(); 
	                
	                    Branch l_branch = null;
	                    try
	                    {
	                        l_branch = l_gentradeAccountManager.getWeb3GenBranch(l_strInstitutionCode, l_strBranchCode);//NotFoundException
	                    }
	                    catch(NotFoundException l_nfd)
	                    {  
	                        log.error(getClass().getName() + STR_METHOD_NAME, l_nfd);
	                        log.exiting(STR_METHOD_NAME);
	                        throw new WEB3SystemLayerException(
	                            WEB3ErrorCatalog.SYSTEM_ERROR_80006,
	                            this.getClass().getName() + STR_METHOD_NAME);  
	                    }
	                
	                    BranchRow l_branchRow = (BranchRow)l_branch.getDataSourceObject();
	
	                    OpLoginAdminService l_opLoginAdminService =
	                                   (OpLoginAdminService)Services.getService(OpLoginAdminService.class);
	
	                    Map l_loginTypeAttr = l_opLoginAdminService.getLoginTypeAttributes(l_branchRow.getAccountTypeId());
	
	                    // checkCode(int, int, String, String)
	
	                    int l_intCodeMin = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MIN_LENGTH));
	                    int l_intCodeMax = Integer.parseInt((String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_MAX_LENGTH));
	                    String l_strChkMode = (String)l_loginTypeAttr.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_CHECK_MODE);
	                    String l_strCodeString = (String) expAccountOpenParams.getColumn("initial_password");
	                    if (l_strCodeString != null) {
	                        int l_intChckcd = WEB3PasswordUtility.checkCode(l_intCodeMin, l_intCodeMax, l_strChkMode, l_crypt.decrypt(l_strCodeString));
	                     
	                        //パスワード文字列が不正な場合（checkCode() != CHECK_NORMAL）、例外をスローする
	                        switch (l_intChckcd)
	                        {
	                            case WEB3PasswordUtility.CHECK_ERROR_LENGTH:
	                                throw new WEB3BusinessLayerException(
	                                    WEB3ErrorCatalog.BUSINESS_ERROR_01915,
	                                    getClass().getName() + STR_METHOD_NAME);
	                        
	                            case WEB3PasswordUtility.CHECK_ERROR_CTYPE:
	                                throw new WEB3BusinessLayerException(
	                                    WEB3ErrorCatalog.BUSINESS_ERROR_01916,
	                                    getClass().getName() + STR_METHOD_NAME);
	                        
	                            case WEB3PasswordUtility.CHECK_ERROR_CONFIG:
	                                throw new WEB3BusinessLayerException(
	                                    WEB3ErrorCatalog.BUSINESS_ERROR_01914,
	                                    getClass().getName() + STR_METHOD_NAME);
	                        }
	                    }
	                }
	            }
	            catch (IllegalSessionStateException l_e)
	            {
	                //管理者以外
	            }
	        }
	        
	        //生年月日 暦上日チェック
	        String l_strEraBorn = (String) expAccountOpenParams.getColumn("era_born");
	        String l_strBornDate = (String) expAccountOpenParams.getColumn("born_date");
	        
	        if (l_strEraBorn != null && l_strBornDate != null)
	        {
                Date l_datOnCalendar = WEB3GentradeEra.toDate(l_strEraBorn, l_strBornDate);
	            if (l_datOnCalendar == null)
	            {
	                log.debug("生年月日年号 = " + l_strEraBorn + ", 生年月日 = " + l_strBornDate);
	                log.exiting(STR_METHOD_NAME);
	                throw new WEB3BusinessLayerException(
	                    WEB3ErrorCatalog.BUSINESS_ERROR_01312,
	                    this.getClass().getName() + STR_METHOD_NAME,
	                    "生年月日");
	            }
	        }
	    } 
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate重複顧客)<BR>
     * 同一顧客が重複して登録されていないかチェックする。<BR> 
     * 既に登録されている場合、以下２つの処理を行う。 <BR>
     * ①@警告文を返却する。 <BR>
     * ②部店プリファ@レンスの値>1の場合口座開設審査待ち情報リストへ重複顧客情報を保存する。<BR> 
     * (set口座開設審査待ち情報リスト()) <BR>
     * <BR>
     * <BR>
     * １)　@重複顧客チェック(口座開設見込客行.口座区分が"個人アカウント"の場合)<BR> 
     * 　@１－１）以下の5項目が全てNULLでない場合のみ、 <BR>
     * 　@以降の処理を実施する。 <BR>
     * 　@　@・this.get証券会社コード()の戻り値<BR> 
     * 　@　@・口座開設見込客行.生年月日年号 <BR>
     * 　@　@・口座開設見込客行.生年月日 <BR>
     * 　@　@・口座開設見込客行.顧客姓(カナ) <BR>
     * 　@　@・口座開設見込客行.顧客名(カナ) <BR>
     * <BR>
     * 　@１－２）　@重複顧客警告の判定（見込客）<BR> 
     * 　@　@【口座開設見込客テーブル】を以下の条件で検索する。<BR> 
     * <BR>
     * 　@　@[条件]<BR> 
     * 　@　@　@証券会社コード=this.get証券会社コード() AND<BR> 
     * 　@　@　@生年月日年号=口座開設見込客行.生年月日年号 AND <BR>
     * 　@　@　@生年月日=口座開設見込客行.生年月日 AND <BR>
     * 　@　@　@顧客名（カナ）姓=口座開設見込客行.顧客姓（カナ） AND<BR>  
     * 　@　@　@顧客名（カナ）名=口座開設見込客行.顧客名（カナ） AND  <BR>
     * 　@　@　@口座コード IS NOT NULL AND <BR>
     * 　@　@　@識別コード !=口座開設見込客行.識別コード <BR>
     *       口座登録日 IS NULL <BR>
     * <BR>
     * 　@　@１－２－１）  １－２）に於いて 該当行が存在する場合<BR> 
     * 　@　@　@１－２－１－１）（引数）部店プリファ@レンス > 1の場合、<BR>  
     *       　@　@　@　@　@　@　@　@　@重複顧客情報を保存する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@(set口座開設審査待ち情報リスト()）。<BR> 
     *              <BR>
     * 　@　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数]<BR> 
     * 　@　@　@　@　@　@　@審査種別："2" <BR>
     * 　@　@　@　@　@　@　@重複区分："1" <BR>
     * 　@　@　@　@　@　@　@重複部店コード：見込客テーブル.部店コード<BR> 
     * 　@　@　@　@　@　@　@重複顧客コード：見込客テーブル.顧客コード <BR>
     * 　@　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@　@管理部店コード：null<BR> 
     * 　@　@　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@　@　@管理No．：null <BR>
     * <BR>
     * 　@１－３）　@重複顧客警告の判定（顧客マスタ）<BR> 
     * 　@　@１－３－１）　@カナ変換 <BR>
     * 　@　@　@this.口座開設見込客行.顧客姓（カナ）を大文字カナに変換する。<BR> 
     * 　@　@　@this.口座開設見込客行.顧客名（カナ）を大文字カナに変換する。 <BR>
     * 　@　@　@（WEB3StringTypeUtility#toUpperWbyteKana()を使用する） <BR>
     * <BR>
     * 　@　@１－３－２）　@顧客マスタ行取得<BR> 
     * 　@　@　@【顧客マスタ】を以下の条件で検索する。<BR> 
     * <BR>
     * 　@　@　@[条件]<BR> 
     * 　@　@　@　@証券会社コード = this.get証券会社コード() And<BR> 
     * 　@　@　@　@生年月日年号 = 口座開設見込客行.生年月日年号 And <BR>
     * 　@　@　@　@生年月日 = 口座開設見込客行.生年月日 And <BR>
     * 　@　@　@　@名前（苗字１）※顧客名（カナ）として使用 like ’%口座開設見込客行.顧客姓（カナ）%’ And <BR>
     * 　@　@　@　@名前（苗字１）※顧客名（カナ）として使用 like ’%口座開設見込客行.顧客名（カナ）%’ <BR>
     * <BR>
     * 　@　@　@※ 顧客姓（カナ），顧客名（カナ）は、１－３－１）で変換した文字列 <BR>
     * <BR>
     * 　@　@１－３－３）　@顧客名判定<BR> 
     * 　@　@　@該当行が存在した場合、以下の処理を行う。 <BR>
     * 　@　@　@－顧客マスタ.名前（苗字１）文字列より、全角Space文字，半角Space文字を削除する。<BR> 
     * 　@　@　@－口座開設見込客行.顧客姓（カナ），顧客名（カナ）を連結<BR>
     * 　@　@　@　@（顧客姓（カナ）+顧客名（カナ））し、<BR> 
     * 　@　@　@　@Spaceを削除した顧客マスタ.名前（苗字１）文字列と比較する。 <BR>
     * 　@　@ <BR>
     * 　@　@１－３－４）　@１－３－３）に於いて値が一致している場合<BR> 
     * 　@　@　@１－３－４－１）　@（引数）部店プリファ@レンス > 1の場合、<BR> 
     * 　@　@　@　@　@重複顧客情報を保存する。(set口座開設審査待ち情報リスト()）。<BR> 
     * <BR>
     * 　@　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数]<BR> 
     * 　@　@　@　@　@　@審査種別："1" <BR>
     * 　@　@　@　@　@　@重複区分："1" <BR>
     * 　@　@　@　@　@　@重複部店コード：顧客マスタ.部店コード<BR> 
     * 　@　@　@　@　@　@重複顧客コード：顧客マスタ.顧客コード <BR>
     * 　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@管理部店コード：null<BR> 
     * 　@　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@　@管理No．：null <BR>
     * <BR>
     * <BR>
     * 　@１－４）　@重複顧客警告の判定（全部店顧客マスタ）<BR> 
     * 　@　@【顧客マスタ（全部店分）】を以下の条件で検索する。<BR> 
     * <BR>
     * 　@　@１－４－１）　@カナ変換<BR> 
     * 　@　@　@this.口座開設見込客行.顧客姓（カナ）を大文字カナに変換する。<BR> 
     * 　@　@　@this.口座開設見込客行.顧客名（カナ）を大文字カナに変換する。 <BR>
     * 　@　@　@（WEB3StringTypeUtility#toUpperWbyteKana()を使用する） <BR>
     * <BR>
     * 　@　@１－４－２）　@部店テーブルより部店コードのリストを取得。<BR> 
     * 　@　@　@顧客マスタとの重複を避ける為、部店テーブルに存在する指定した<BR>
     * 　@　@　@証券会社の部店コードのリストを<BR> 
     * 　@　@　@口座開設重複チェックユーティリティ.get部店コードリスト<BR>
     * 　@　@　@(this.get証券会社コード())を実行し取得する。<BR> 
     * <BR>
     * 　@　@１－４－３）　@顧客マスタ（全部店分）行取得<BR>  
     * 　@　@　@【顧客マスタ（全部店分）】を以下の条件で検索する。<BR>  
     * <BR>
     * 　@　@  １－４－３－１） 　@１－４－２）での戻り値がnullではない場合<BR> 
     * <BR>
     * 　@　@　@  [条件]<BR>  
     * 　@　@　@　@　@証券会社コード = this.get証券会社コード() And<BR>  
     * 　@　@　@　@　@部店コード  NOT IN ( １－４－２）で取得した部店コードリスト ) And <BR>
     * 　@　@　@　@　@生年月日年号 = 口座開設見込客行.生年月日年号 And  <BR>
     * 　@　@　@　@　@生年月日 = 口座開設見込客行.生年月日 And  <BR>
     * 　@　@　@　@　@顧客名（カナ） like ’%口座開設見込客行.顧客姓（カナ）%’ And<BR>  
     * 　@　@　@　@　@顧客名（カナ） like ’%口座開設見込客行.顧客名（カナ）%’  <BR>
     * <BR>
     * 　@　@　@    ※ 顧客姓（カナ），顧客名（カナ）は、１－４－１）で変換した文字列<BR>  
     * <BR>
     * 　@　@  １－４－３－２） 　@１－４－２）での戻り値がnullの場合<BR> 
     * <BR>
     * 　@　@　@  [条件]<BR>  
     * 　@　@　@　@　@証券会社コード = this.get証券会社コード() And<BR>  
     * 　@　@　@　@　@生年月日年号 = 口座開設見込客行.生年月日年号 And  <BR>
     * 　@　@　@　@　@生年月日 = 口座開設見込客行.生年月日 And  <BR>
     * 　@　@　@　@　@顧客名（カナ） like ’%口座開設見込客行.顧客姓（カナ）%’ And<BR>  
     * 　@　@　@　@　@顧客名（カナ） like ’%口座開設見込客行.顧客名（カナ）%’  <BR>
     * <BR>
     * 　@　@　@    ※ 顧客姓（カナ），顧客名（カナ）は、１－４－１）で変換した文字列<BR>  
     * <BR>
     * 　@　@１－４－４）　@顧客名判定<BR>  
     * 　@　@　@該当行が存在した場合、以下の処理を行う。<BR>  
     * 　@　@　@－顧客マスタ（全部店）.顧客名（カナ）文字列より、全角Space文字，<BR>
     * 　@　@　@半角Space文字を削除する。<BR>  
     * 　@　@　@－口座開設見込客行.顧客姓（カナ），顧客名（カナ）を連結<BR>
     * 　@　@　@（顧客姓（カナ）+顧客名（カナ））し、<BR>  
     * 　@　@　@Spaceを削除した顧客マスタ（全部店）.顧客名（カナ）文字列と比較する。  <BR>
     * <BR>
     * 　@　@１－４－５）　@１－４－４） に於いて値が一致している場合<BR> 
     * 　@　@　@１－４－５－１）（引数）部店プリファ@レンス > 1の場合、 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@重複顧客情報を保存する。(set口座開設審査待ち情報リスト()）。<BR> 
     *              <BR>
     * 　@　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数]<BR> 
     * 　@　@　@　@　@　@　@審査種別："1" <BR>
     * 　@　@　@　@　@　@　@重複区分："1" <BR>
     * 　@　@　@　@　@　@　@重複部店コード：顧客マスタ（全部店分）.部店コード <BR>
     * 　@　@　@　@　@　@　@重複顧客コード：顧客マスタ（全部店分）.顧客コード <BR>
     * 　@　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@　@管理部店コード：null<BR> 
     * 　@　@　@　@　@　@　@業務区分：null <BR>
     *　@　@　@　@　@　@ 　@管理No．：null <BR>
     * <BR>
     * <BR>
     * ２)　@重複顧客チェック(口座開設見込客行.口座区分が"法@人アカウント"の場合)<BR> 
     * <BR>
     * 　@(法@人の場合、顧客姓(カナ)、顧客名(カナ)には、<BR>
     * 　@判定に不要な文字が含まれる場合があるので編集を行う)<BR> 
     * <BR>
     * 　@２－１）　@顧客姓(カナ)、顧客名(カナ)から判定に不要な文字を削除する。<BR> 
     * 　@　@　@－this.口座開設見込客行.顧客姓(カナ)、顧客名(カナ)を取得する。 <BR>
     * 　@　@　@－顧客姓(カナ)・顧客名(カナ)から、設立形態区分(※)、全角Space文字、<BR>
     * 　@　@　@半角Space文字を削除する。<BR> 
     * 　@　@　@　@（WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[]を使用する）<BR> 
     * <BR>
     * 　@２－２）　@重複顧客警告の判定（見込客） <BR>
     * 　@　@　@【口座開設見込客テーブル】を以下の条件で検索する。<BR> 
     * <BR>
     * 　@ ２－２－１）　@－顧客姓(カナ)、顧客名(カナ)は、設立形態区分(※)、全角・半角Space等の <BR>
     *                 不要文字をreplace()関数で、削除する <BR>
     *                 －顧客姓名、代表者姓名、取引責任者姓名については、論理和( or )を使用 <BR>
     * <BR>
     * 　@　@　@[条件] <BR>
     * 　@　@　@証券会社コード = this.get証券会社コード() And <BR>
     * 　@　@　@(replace(顧客姓(カナ),『不要文字』) || replace(顧客名(カナ),『不要文字』)　@・・・(*1)<BR> 
     * 　@　@　@　@　@= 口座開設見込客行.顧客姓（カナ） + 口座開設見込客行.顧客名（カナ） Or　@・・・(*2)<BR> 
     * 　@　@　@各社拡張項目（テキスト７） || 各社拡張項目（テキスト８） <BR>
     * 　@　@　@　@　@= 口座開設見込客行.各社拡張項目（テキスト７） <BR>
     * 　@　@　@　@　@　@+ 口座開設見込客行.各社拡張項目（テキスト８） Or<BR> 
     * 　@　@　@各社拡張項目（テキスト９） || 各社拡張項目（テキスト１０）<BR> 
     * 　@　@　@　@　@= 口座開設見込客行.各社拡張項目（テキスト９） <BR>
     * 　@　@　@　@　@　@+ 口座開設見込客行.各社拡張項目（テキスト１０）） And <BR>
     *　@　@　@ 識別コード != 口座開設見込客行.識別コード And <BR>
     *　@　@　@ 口座コード IS NOT NULL <BR>
     * <BR>
     * 　@　@　@(*1WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[]の<BR> 
     * 　@　@　@　@要素数分ループ処理を行い、replace関数により、判定不要文字の削除を行う。<BR> 
     * 　@　@　@(*2)顧客姓(カナ)，顧客名(カナ)は、２－１）で編集した文字列 <BR>
     * <BR>
     * 　@　@(※)判定に不要な会社設立形態区分文字は以下の6つ（全て全角大文字） <BR>
     * 　@　@　@　@"カ）" <BR>
     * 　@　@　@　@"（カ" <BR>
     * 　@　@　@　@"ユ）" <BR>
     * 　@　@　@　@"（ユ" <BR>
     * 　@　@　@　@"シ）" <BR>
     * 　@　@　@　@"（シ" <BR>
     * <BR>
     * 　@ ２－２－２）　@２－２－１）に於いて該当行が存在する場合 <BR>
     * 　@   ２－２－２－１）　@（引数）部店プリファ@レンス > 1の場合、<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@重複顧客情報を保存する。(set口座開設審査待ち情報リスト()）。<BR> 
     *              <BR>
     * 　@　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数] <BR>
     * 　@　@　@　@　@　@　@審査種別："2" <BR>
     * 　@　@　@　@　@　@　@重複区分："1" <BR>
     * 　@　@　@　@　@　@　@重複部店コード：見込客テーブル.部店コード <BR>
     * 　@　@　@　@　@　@　@重複顧客コード：見込客テーブル.顧客コード <BR>
     * 　@　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@　@管理部店コード：null <BR>
     * 　@　@　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@　@　@管理No．：null <BR>
     * <BR>
     * <BR>
     * 　@２－３）　@重複顧客警告の判定（口座情報マスタ）  <BR>
     * 　@　@２－３－１）　@２－１）で編集した顧客姓(カナ)、顧客名(カナ)を大文字に変換する。<BR> 
     * 　@　@　@－口座開設見込客行.顧客姓（カナ）を大文字カナに変換する。  <BR>
     * 　@　@　@－口座開設見込客行.顧客名（カナ）を大文字カナに変換する。  <BR>
     * 　@　@　@（WEB3StringTypeUtility#toUpperWbyteKana()を使用する） <BR>
     * <BR>
     * 　@　@２－３－２）　@口座情報マスタ行取得 <BR>
     * 　@　@　@【口座情報マスタ】を以下の条件で検索する。 <BR>
     * <BR>
     * 　@　@　@　@－法@人名(カナ)は、設立形態区分(※)、<BR>
     * 　@　@　@　@全角・半角Space等の不要文字をreplace()関数で、削除する<BR> 
     * 　@　@　@　@－法@人名、代表者姓名、取引責任者姓名については、論理和( or )を使用 <BR>
     * <BR>
     * 　@　@　@[条件]  <BR>
     * 　@　@　@証券会社コード = this.get証券会社コード() And <BR> 
     * 　@　@　@(replace(法@人名(カナ),『不要文字』)　@・・・(*3) <BR>
     * 　@　@　@　@　@= 口座開設見込客行.顧客姓（カナ） + 口座開設見込客行.顧客名（カナ）<BR>
     * 　@　@　@　@　@　@ Or　@・・・(*4)<BR> 
     * 　@　@　@代表者名（カナ）姓 || 代表者名（カナ）名 <BR>
     * 　@　@　@　@　@= 口座開設見込客行.各社拡張項目（テキスト７）<BR>
     * 　@　@　@　@　@　@ + 口座開設見込客行.各社拡張項目（テキスト８） Or<BR>  
     * 　@　@　@取引責任者名（カナ）姓 || 取引責任者名（カナ）名 <BR>
     * 　@　@　@　@　@= 口座開設見込客行.各社拡張項目（テキスト９）<BR>
     * 　@　@　@　@　@　@ + 口座開設見込客行.各社拡張項目（テキスト１０））<BR> 
     * <BR>
     * 　@　@　@(*3WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[]の <BR>
     * 　@　@　@　@要素数分ループ処理を行い、replace関数により、判定不要文字の削除を行う。<BR> 
     * 　@　@　@(*4)顧客姓(カナ)，顧客名(カナ)は、２－３－１）で変換した文字列  <BR>
     * <BR>
     * 　@  ２－３－３）　@２－３－２）に於いて該当行が存在する場合 <BR>
     * 　@    ２－３－３－１）　@（引数）部店プリファ@レンス > 1の場合、<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@重複顧客情報を保存する。(set口座開設審査待ち情報リスト()）。<BR> 
     *              <BR>
     * 　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数]<BR> 
     * 　@　@　@　@　@　@審査種別："1" <BR>
     * 　@　@　@　@　@　@重複区分："1" <BR>
     * 　@　@　@　@　@　@重複部店コード：口座情報マスタ.部店コード <BR>
     * 　@　@　@　@　@　@重複顧客コード：口座情報マスタ.顧客コード <BR>
     * 　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@管理部店コード：null <BR>
     * 　@　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@　@管理No．：null <BR>
     * <BR>
     * <BR>
     * ３）  戻り値 <BR>
     * <BR>
     * 　@３－１）　@上記、１－２－１）、１－３－４）、１－４－５）、２－２－２）、２－３－３）の <BR>
     * 　@　@　@　@　@　@いずれかに於いて該当行が存在した場合、警告メッセージを返却する。<BR> 
     * 　@　@　@　@　@　@「WARNING_60001：　@顧客重複登録の可能性あり。」  <BR>
     * 　@　@　@　@　@　@　@tag:WARNING_60001<BR>
     * <BR>
     * 　@３－２）　@上記、１－２－１）、１－３－４）、１－４－５）、２－２－２）、２－３－３）の<BR> 
     * 　@　@　@　@　@　@全てに於いて該当行が存在しない場合、nullを返却する。<BR>
     * @@param l_intBranchPreference - (部店プリファ@レンス)<BR>
     * 部店プリファ@レンス値<BR> 
     * <BR>
     * [設定値] <BR>
     * 0：チェック不要-アラート表示無-審査待ちUPDATE無 <BR>
     * 1：チェック実行-アラート表示有-審査待ちUPDATE無 <BR>
     * 2：チェック実行-アラート表示無-審査待ちUPDATE有 <BR>
     * 3：チェック実行-アラート表示有-審査待ちUPDATE有 <BR>    
     * @@return String
     * @@roseuid 419453DB0169
     */
    public String validateDuplicateAccount(int l_intBranchPreference) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAccount()";
        log.entering(STR_METHOD_NAME);
      
        boolean l_blnDupulicateFlag = false;
        
        try
        {
            String l_strInstitutionCode = this.getInstitutionCode();
       
            String l_strEraBorn = this.expAccountOpenParams.getEraBorn();
            String l_strBornDate = this.expAccountOpenParams.getBornDate();
            String l_strFamilyName = this.expAccountOpenParams.getFamilyNameAlt1();
            String l_strGivenName = this.expAccountOpenParams.getGivenNameAlt1();            
            String l_strAccountDiv = this.expAccountOpenParams.getAccountDiv();                     
            
            if (WEB3AccOpenAccountDivDef.INDIVIDUAL_ACCOUNT.equals(l_strAccountDiv))
            { 
                //１)　@重複顧客チェック(口座開設見込客行.口座区分が"個人アカウント"の場合)
                //１－１）以下の5項目が全てNULLでない場合のみ、以降の処理を実施する。
                //・this.get証券会社コード()の戻り値
                //・口座開設見込客行.生年月日年号
                //・口座開設見込客行.生年月日
                //・口座開設見込客行.顧客姓(カナ)
                //・口座開設見込客行.顧客名(カナ) 
                
                if (l_strInstitutionCode == null || l_strEraBorn == null || 
                    l_strBornDate == null || l_strFamilyName == null || 
                    l_strGivenName == null) 
                {
                    return null;
                }
                           
                //１－２）重複顧客警告の判定（見込客） 
                StringBuffer l_sbWhere = new StringBuffer();
                List l_lisDataBind = new ArrayList();

                l_sbWhere.append("institution_code = ?");
                l_lisDataBind.add(l_strInstitutionCode);            
                        
                l_sbWhere.append(" and era_born = ?");
                l_lisDataBind.add(l_strEraBorn);
                        
                l_sbWhere.append(" and born_date = ?");
                l_lisDataBind.add(l_strBornDate);
            
                l_sbWhere.append(" and family_name_alt1 = ? and given_name_alt1 = ?");
                l_lisDataBind.add(l_strFamilyName);
                l_lisDataBind.add(l_strGivenName);
            
                l_sbWhere.append(" and account_code is not null");
                
                l_sbWhere.append(" and acc_open_request_number <> ?");
                l_lisDataBind.add(this.getRequestNumber());

				//仕様変更20060623SCSInomata-->
				l_sbWhere.append(" and account_open_date is null");
				//<--仕様変更20060623SCSInomata
            
                Object[] l_objExpAccOpen = new Object[l_lisDataBind.size()];
                l_lisDataBind.toArray(l_objExpAccOpen);
            
                List l_lisExpAccOpenRow = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenRow.TYPE, l_sbWhere.toString(), l_objExpAccOpen);

                //１－２－１）  １－２）に於いて 該当行が存在する場合 
                if (l_lisExpAccOpenRow != null && l_lisExpAccOpenRow.size() > 0)
                {
                    l_blnDupulicateFlag = true;
                    
                    //１－２－１－１）  （引数）部店プリファ@レンス > 1の場合、
                    //重複顧客情報を保存する。 
                    if (l_intBranchPreference > 1)
                    {
                        for (int i = 0; i < l_lisExpAccOpenRow.size(); i++)
                        {
							ExpAccountOpenRow l_row = (ExpAccountOpenRow)l_lisExpAccOpenRow.get(i);
							this.setAccOpenJudgeWaitingInfoList(
								WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP, 
								WEB3DuplicationDivDef.NAME_BORN_DATE,
								l_row.getBranchCode(),
								l_row.getAccountCode(),
								null,
								null,
								null,
								null);
                        }
                    }
                }

                //１－３）　@重複顧客警告の判定（顧客マスタ） 
                //１－３－１）　@カナ変換
                String l_strFamilyNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strFamilyName);

                String l_strGivenNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strGivenName);

                //１－３－２）　@顧客マスタ行取得
                StringBuffer l_sbMainAccountWhereAgain = new StringBuffer();
                List l_lisMainAccountAgain = new ArrayList();
            
                l_sbMainAccountWhereAgain.append("institution_code = ?");
                l_lisMainAccountAgain.add(l_strInstitutionCode);
            
                l_sbMainAccountWhereAgain.append(" and era_born = ?");
                l_lisMainAccountAgain.add(l_strEraBorn);
                    
                l_sbMainAccountWhereAgain.append(" and born_date = ?");
                l_lisMainAccountAgain.add(l_strBornDate);
                
                l_sbMainAccountWhereAgain.append(" and family_name_alt1 like ? and family_name_alt1 like ?");
                l_lisMainAccountAgain.add("%" + l_strFamilyNameAlt + "%");
                l_lisMainAccountAgain.add("%" + l_strGivenNameAlt + "%");
            
                Object[] l_objMainAccountAgain = new Object[l_lisMainAccountAgain.size()];
                l_lisMainAccountAgain.toArray(l_objMainAccountAgain);
            
                List l_lisMainAccountRowAgain = Processors.getDefaultProcessor().doFindAllQuery(
                    MainAccountRow.TYPE, l_sbMainAccountWhereAgain.toString(), l_objMainAccountAgain);
            
                //１－３－３）　@顧客名判定 
                if (l_lisMainAccountRowAgain != null && l_lisMainAccountRowAgain.size() > 0)
                {
                    int l_intLength = l_lisMainAccountRowAgain.size();
                    String l_strFamilyNameAlt1 = null;
                    StringBuffer l_sbFamilyNameAlt1 = new StringBuffer();
                    for (int i = 0; i < l_intLength; i++)
                    {
                        MainAccountRow l_row = (MainAccountRow)l_lisMainAccountRowAgain.get(i);
                        l_strFamilyNameAlt1 = l_row.getFamilyNameAlt1();
                        int l_intNameLength = l_strFamilyNameAlt1.length();

                        for (int j = 0; j < l_intNameLength; j++)
                        {
                            if (Character.isWhitespace(l_strFamilyNameAlt1.charAt(j)))
                            {
                                continue;
                            }
                            l_sbFamilyNameAlt1.append(l_strFamilyNameAlt1.charAt(j));
                        }

                        //１－３－４）　@１－３－３）に於いて値が一致している場合
                        if (l_sbFamilyNameAlt1.toString().equals(l_strFamilyNameAlt + l_strGivenNameAlt))
                        {
                            l_blnDupulicateFlag = true;
                            
                            //１－３－４－１）　@（引数）部店プリファ@レンス > 1の場合、
                            //重複顧客情報を保存する。
                            if (l_intBranchPreference > 1)
                            {
                                this.setAccOpenJudgeWaitingInfoList(
                                    WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
                                    WEB3DuplicationDivDef.NAME_BORN_DATE,
                                    l_row.getBranchCode(),
                                    l_row.getAccountCode(),
                                    null,
                                    null,
                                    null,
                                    null);
                            }
                        }
                    }
                }
                
                //１－４）　@重複顧客警告の判定（全部店顧客マスタ）
                //１－４－１）　@カナ変換
                
                //１－４－２）　@部店テーブルより部店コードのリストを取得。 
                String l_strBranchCodeList = 
                    WEB3AccOpenDuplicationCheckUtility.getBranchCodeList(this.getInstitutionCode());
                
                //　@　@１－４－３）　@顧客マスタ（全部店分）行取得  
                //　@　@　@【顧客マスタ（全部店分）】を以下の条件で検索する。  
                StringBuffer l_sbMainAccountAllQueryString = new StringBuffer();
                List l_lisMainAccountAllContainer = new ArrayList(); 
                
                l_sbMainAccountAllQueryString.append("comp_code = ?");
                l_lisMainAccountAllContainer.add(l_strInstitutionCode);
                
                if (l_strBranchCodeList != null) 
                {
                    String[] l_strBranchCodes = l_strBranchCodeList.split(",");
                    StringBuffer l_sbBranchCode = new StringBuffer();
                    l_sbBranchCode.append(" and br_code not in (");
                    
                    for (int i= 0; i < l_strBranchCodes.length; i++) 
                    {
                        l_sbBranchCode.append("?,");
                        l_lisMainAccountAllContainer.add(l_strBranchCodes[i]);
                    }
                    
                    l_sbMainAccountAllQueryString.append(l_sbBranchCode.substring(0,l_sbBranchCode.length() - 1));
                    l_sbMainAccountAllQueryString.append(")");
                    
                }
                
                l_sbMainAccountAllQueryString.append(" and era_born = ?");
                l_lisMainAccountAllContainer.add(l_strEraBorn);
                
                l_sbMainAccountAllQueryString.append(" and born_date = ?");
				//仕様変更20060623SCSInomata-->
				if (l_strBornDate.length() == 6 )
				{ 
					l_lisMainAccountAllContainer.add("00" + l_strBornDate);
				} 
				else
				{
					l_lisMainAccountAllContainer.add(l_strBornDate);
				}
				//<--仕様変更20060623SCSInomata                
                l_sbMainAccountAllQueryString.append(" and family_name_alt1 like ? and family_name_alt1 like ?");
                l_lisMainAccountAllContainer.add("%" + l_strFamilyNameAlt + "%");
                l_lisMainAccountAllContainer.add("%" + l_strGivenNameAlt + "%");
                
                Object[] l_objMainAccountAll = new Object[l_lisMainAccountAllContainer.size()];
                l_lisMainAccountAllContainer.toArray(l_objMainAccountAll);
                
                List l_lisMainAccountAllRow = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        MainAccountAllRow.TYPE, 
                        l_sbMainAccountAllQueryString.toString(), 
                        l_objMainAccountAll);
                
                //　@　@１－４－４）　@顧客名判定  
                //　@　@　@該当行が存在した場合、以下の処理を行う。  
                //　@　@　@－顧客マスタ（全部店）.顧客名（カナ）文字列より、全角Space文字，半角Space文字を削除する。  
                //　@　@　@－口座開設見込客行.顧客姓（カナ），顧客名（カナ）を連結（顧客姓（カナ）+顧客名（カナ））し、  
                //　@　@　@　@Spaceを削除した顧客マスタ（全部店）.顧客名（カナ）文字列と比較する。    
                if (l_lisMainAccountAllRow != null && l_lisMainAccountAllRow.size() != 0) 
                {
                    int l_intLength = l_lisMainAccountAllRow.size();
                    String l_strFamilyNameAlt1 = null;
                    StringBuffer l_sbFamilyNameAlt1 = new StringBuffer();
                    for (int i = 0; i < l_intLength; i++)
                    {
                        MainAccountAllRow l_row = (MainAccountAllRow)l_lisMainAccountAllRow.get(i);
                        l_strFamilyNameAlt1 = l_row.getFamilyNameAlt1();
                        int l_intNameLength = l_strFamilyNameAlt1.length();

                        for (int j = 0; j < l_intNameLength; j++)
                        {
                            if (Character.isWhitespace(l_strFamilyNameAlt1.charAt(j)))
                            {
                                continue;
                            }
                            l_sbFamilyNameAlt1.append(l_strFamilyNameAlt1.charAt(j));
                        }
             
                        if (l_sbFamilyNameAlt1.toString().equals(l_strFamilyNameAlt + l_strGivenNameAlt))
                        {
                            l_blnDupulicateFlag = true;
                            
                            //　@　@１－４－５）　@１－４－４－３） に於いて値が一致している場合 
                            //      １－４－５－１）（引数）部店プリファ@レンス > 1の場合、 
                            if (l_intBranchPreference > 1)
                            {
                                this.setAccOpenJudgeWaitingInfoList(
                                    WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
                                    WEB3DuplicationDivDef.NAME_BORN_DATE,
                                    l_row.getBrCode(),
                                    l_row.getCustCode(),
                                    null,
                                    null,
                                    null,
                                    null);
                            }
                        }
                    }
                }        
            }  
            
            //２)　@重複顧客チェック(口座開設見込客行.口座区分が"法@人アカウント"の場合)
            else if(WEB3AccOpenAccountDivDef.CORPORATE_ACCOUNT.equals(l_strAccountDiv))
            {           

                StringBuffer l_sbWhereExpAccOpen = new StringBuffer();
                List l_lisDataBindExpAccOpen = new ArrayList();

                //２－１）　@顧客姓(カナ)、顧客名(カナ)から判定に不要な文字を削除する。
                //－this.口座開設見込客行.顧客姓(カナ)、顧客名(カナ)を取得する。
                String l_strExtItemText7 = changeStr(this.expAccountOpenParams.getExtItemText7());
                String l_strExtItemText8 = changeStr(this.expAccountOpenParams.getExtItemText8());
                String l_strExtItemText9 = changeStr(this.expAccountOpenParams.getExtItemText9());
                String l_strExtItemText10 = changeStr(this.expAccountOpenParams.getExtItemText10());
                
                int l_intComformDivLength = WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST.length;

                //見込客行.顧客姓(カナ)から不要文字＆全半角スペースを削除する
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_strFamilyName = l_strFamilyName.replaceAll(WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i], "");
                    l_strGivenName = l_strGivenName.replaceAll(WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i], "");
                }

                //２－２）　@重複顧客警告の判定（見込客） 
                l_sbWhereExpAccOpen.append("institution_code = ?");
                l_lisDataBindExpAccOpen.add(l_strInstitutionCode);        

                //法@人姓名 or 代表者姓名 or 取引責任者姓名の重複検索条件start
                l_sbWhereExpAccOpen.append(" and (");

                //見込客TBL.顧客姓(カナ)
                l_sbWhereExpAccOpen.append("(");
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append("replace(");
                }

                l_sbWhereExpAccOpen.append("family_name_alt1");

                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append(",'" + WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i] + "')");
                }
                l_sbWhereExpAccOpen.append(")");

                //見込客TBL.顧客名(カナ)
                l_sbWhereExpAccOpen.append(" || (");
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append("replace(");
                }

                l_sbWhereExpAccOpen.append("given_name_alt1");

                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereExpAccOpen.append(",'" + WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i] + "')");
                }
                l_sbWhereExpAccOpen.append(") = ?");
                l_lisDataBindExpAccOpen.add(l_strFamilyName + l_strGivenName);

                l_sbWhereExpAccOpen.append(" or (ext_item_text7 || ext_item_text8) = ?");
                l_lisDataBindExpAccOpen.add(l_strExtItemText7 + l_strExtItemText8);

                l_sbWhereExpAccOpen.append(" or (ext_item_text9 || ext_item_text10) = ?");
                l_lisDataBindExpAccOpen.add(l_strExtItemText9 + l_strExtItemText10);

                //法@人姓名 or 代表者姓名 or 取引責任者姓名の重複検索条件end
                l_sbWhereExpAccOpen.append(")");

                l_sbWhereExpAccOpen.append(" and acc_open_request_number <> ?");            
                l_lisDataBindExpAccOpen.add(this.getRequestNumber());
                                
                l_sbWhereExpAccOpen.append(" and account_code is not null");
               
                Object[] l_objExpAccOpen = new Object[l_lisDataBindExpAccOpen.size()];
                l_lisDataBindExpAccOpen.toArray(l_objExpAccOpen);

                List l_lisExpAccOpenRow = Processors.getDefaultProcessor().doFindAllQuery(
                    ExpAccountOpenRow.TYPE, l_sbWhereExpAccOpen.toString(), l_objExpAccOpen);
                    
                // ２－２－２）　@２－２－１）に於いて該当行が存在する場合 
                if (l_lisExpAccOpenRow != null && l_lisExpAccOpenRow.size() > 0)
                {
                    l_blnDupulicateFlag = true;
                    
                    //  ２－２－２－１）　@（引数）部店プリファ@レンス > 1の場合、 
                    if (l_intBranchPreference > 1)
                    {
                        for (int i = 0; i < l_lisExpAccOpenRow.size(); i++) 
                        {
                            ExpAccountOpenRow l_row = 
                                (ExpAccountOpenRow)l_lisExpAccOpenRow.get(i);
                            this.setAccOpenJudgeWaitingInfoList(
                                WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP, 
                                WEB3DuplicationDivDef.NAME_BORN_DATE,
                                l_row.getBranchCode(),
                                l_row.getAccountCode(),
                                null,
                                null,
                                null,
                                null);
                        }
                    }
                }

                //２－３）　@重複顧客警告の判定（口座情報マスタ） 
                //２－３－１）　@２－１）で編集した顧客姓(カナ)、顧客名(カナ)を大文字に変換する。
                String l_strFamilyNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strFamilyName);
                String l_strGivenNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strGivenName);

                //２－３－２）　@口座情報マスタ行取得
                StringBuffer l_sbWhereAccInfoMst = new StringBuffer();
                List l_lisDataBindAccInfoMst = new ArrayList();

                l_sbWhereAccInfoMst.append("institution_code = ?");
                l_lisDataBindAccInfoMst.add(l_strInstitutionCode);

                //法@人姓名 or 代表者姓名 or 取引責任者姓名の重複検索条件start
                l_sbWhereAccInfoMst.append(" and (");

                //口座情報マスタTBL.顧客名(法@人名)カナ
                l_sbWhereAccInfoMst.append("(");
                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereAccInfoMst.append("replace(");
                }

                l_sbWhereAccInfoMst.append("family_name_alt1");

                for (int i = 0; i < l_intComformDivLength; i++)
                {
                    l_sbWhereAccInfoMst.append(",'" + WEB3CompanyFormationDivDef.COMPANY_FORMATION_DIV_AND_SPACE_LIST[i] + "')");
                }
                l_sbWhereAccInfoMst.append(") = ?");
                l_lisDataBindAccInfoMst.add(l_strFamilyNameAlt + l_strGivenNameAlt);

                l_sbWhereAccInfoMst.append(" or (represent_family_name_alt1 || represent_given_name_alt1) = ?");
                l_lisDataBindAccInfoMst.add(l_strExtItemText7 + l_strExtItemText8);

                l_sbWhereAccInfoMst.append(" or (director_family_name_alt1 || director_given_name_alt1) = ?");
                l_lisDataBindAccInfoMst.add(l_strExtItemText9 + l_strExtItemText10);

                //法@人姓名 or 代表者姓名 or 取引責任者姓名の重複検索条件end
                l_sbWhereAccInfoMst.append(")");

                Object[] l_objAccountInfoMstAgain = new Object[l_lisDataBindAccInfoMst.size()];
                l_lisDataBindAccInfoMst.toArray(l_objAccountInfoMstAgain);
            
                List l_lisAccountInfoMstRow = Processors.getDefaultProcessor().doFindAllQuery(
                    AccountInfoMstRow.TYPE, l_sbWhereAccInfoMst.toString(), l_objAccountInfoMstAgain);                                         

                // ２－３－３）　@２－３－２）に於いて該当行が存在する場合 
                if (l_lisAccountInfoMstRow != null && l_lisAccountInfoMstRow.size() > 0)
                {
                    l_blnDupulicateFlag = true;
                    
                    //２－３－３－１）　@（引数）部店プリファ@レンス > 1の場合、
                    if (l_intBranchPreference > 1)
                    {
                        for (int i = 0; i < l_lisAccountInfoMstRow.size(); i++) 
                        {
                            AccountInfoMstRow l_row = 
                                (AccountInfoMstRow) l_lisAccountInfoMstRow.get(i);
                            this.setAccOpenJudgeWaitingInfoList(
                                WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
                                WEB3DuplicationDivDef.NAME_BORN_DATE,
                                l_row.getBranchCode(),
                                l_row.getAccountCode(),
                                null,
                                null,
                                null,
                                null);
                        }
                    }         
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        //３）  戻り値 
        //　@３－１）　@上記、１－２－１）、１－３－４）、１－４－５）、２－２－２）、２－３－３）の 
        //             いずれかに於いて該当行が存在した場合、警告メッセージを返却する。 
        //　@          「WARNING_60001：　@顧客重複登録の可能性あり。」 
        if (l_blnDupulicateFlag) 
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60001.getErrorCode();
        }
        
        //３－２）上記、全てに於いて該当行が存在しない場合、nullを返却する。
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (validateＹ客)<BR>
     * 申込顧客がＹ客に載っていないかチェックする。<BR>
     * 既に登録されている場合、以下２つの処理を行う。 <BR>
     * ①@警告文を返却する。 <BR>
     * ②部店プリファ@レンスの値>1の場合口座開設審査待ち情報リストへ重複顧客情報を保存する。<BR> 
     * (set口座開設審査待ち情報リスト()) <BR>
     * <BR>
     * １)以下の5項目が全てNULLでない場合のみ、<BR> 
　@   *    以降の処理を実施する。<BR> 
　@　@ * ・口座開設見込客行.証券会社コード <BR>
　@　@ * ・口座開設見込客行.生年月日年号 <BR>
　@　@ * ・口座開設見込客行.生年月日 <BR>
　@　@ * ・口座開設見込客行.顧客姓(カナ) <BR>
　@　@ * ・口座開設見込客行.顧客名(カナ) <BR>
     * <BR>
     * ２）カナ変換後、文字列連結<BR>
     * 　@this.口座開設見込客行.顧客姓（カナ）を大文字カナに変換する。<BR>
     * 　@this.口座開設見込客行.顧客名（カナ）を大文字カナに変換する。<BR>
     * 　@（WEB3StringTypeUtility#toUpperWbyteKana()を使用する）<BR>
     * 　@カナ変換後のthis.口座開設見込客行.顧客姓（カナ）と<BR> 
     * 　@this.口座開設見込客行.顧客名（カナ）を文字列連結する。<BR>
     * <BR>
     * ３）　@Ｙ客マスタ検索<BR>
     * 　@以下の条件で、Ｙ客マスタテーブルを検索する。<BR>
     * 　@該当行が存在しない場合は、nullを返却し処理を終了する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@Ｙ客マスタ.証券会社コード = 口座開設見込客行.証券会社コード And<BR>
     * 　@Ｙ客マスタ.生年月日年号 = 口座開設見込客行.生年月日年号 And<BR>
     * 　@Ｙ客マスタ.生年月日 = 口座開設見込客行.生年月日 And<BR>
     * 　@（Ｙ客マスタ.顧客名（カナ）から"　@"," "を除く） = ２）で変換した文字列<BR> 
     * <BR>
     * ４）  ３）に於いて該当行が存在する場合<BR> 
     * <BR>
     * 　@４－１）  （引数）部店プリファ@レンス > 1の場合Y客情報を取得し、<BR>
     * 　@　@　@　@　@　@　@重複顧客情報を保存する。<BR> 
     * <BR>
     * 　@  ４－１－１）  this.Y客マスタ行に、該当行をセットする。<BR> 
     * <BR>
     * 　@  ４－１－２）  重複顧客情報を取得・保存する。(set口座開設審査待ち情報リスト()）。<BR> 
     *              <BR>
     *             [set口座開設審査待ち情報リスト()に指定する引数] <BR>
     *               審査種別："3" <BR>
     *               重複区分：null <BR>
     *               重複部店コード:null <BR>
     *               重複顧客コード:null <BR>
     *               Y客ID：Y客マスタ行.Y客ID <BR>
     *               管理部店コード：Y客マスタ行.管理部店コード <BR>
     *               業務区分：Y客マスタ行.業務区分 <BR>
     *               管理No．：Y客マスタ行.管理No． <BR>
     *               <BR>
     * 　@　@　@　@　@　@　@（※Y客ID及び管理No．はLongオブジェクトに変換）<BR> 
     * <BR>
     * <BR>
     * 　@４－２）「WARNING_60002：　@Y客の可能性あり」を返却。<BR> 
     *          tag:   WARNING_60002<BR>
     * @@param l_intBranchPreference - (部店プリファ@レンス)<BR>
     * 部店プリファ@レンス値<BR> 
     * <BR>
     * [設定値] <BR>
     * 0：チェック不要-アラート表示無-審査待ちUPDATE無 <BR>
     * 1：チェック実行-アラート表示有-審査待ちUPDATE無 <BR>
     * 2：チェック実行-アラート表示無-審査待ちUPDATE有 <BR>
     * 3：チェック実行-アラート表示有-審査待ちUPDATE有 <BR>
     * @@return String
     * @@roseuid 41945D1B00BE
     */
    public String validateYellowAccount(int l_intBranchPreference) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateYellowAccount()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //１)以下の5項目が全てNULLでない場合のみ、以降の処理を実施する。
            //・口座開設見込客行.証券会社コード
            //・口座開設見込客行.生年月日年号
            //・口座開設見込客行.生年月日
            //・口座開設見込客行.顧客姓(カナ)
            //・口座開設見込客行.顧客名(カナ)
            String l_strInstitutionCode = this.expAccountOpenParams.getInstitutionCode();
            String l_strEraBorn = this.expAccountOpenParams.getEraBorn();
            String l_strBornDate = this.expAccountOpenParams.getBornDate();
            String l_strFamilyNameAlt1 = this.expAccountOpenParams.getFamilyNameAlt1();
            String l_strGivenNameAlt1 = this.expAccountOpenParams.getGivenNameAlt1();
            
            if (l_strInstitutionCode == null || l_strEraBorn == null || 
                l_strBornDate == null || l_strFamilyNameAlt1 == null ||
                l_strGivenNameAlt1 == null)
            {
                return null;
            }
            
            //２） カナ変換 文字列連結 
            String l_strFamilyNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strFamilyNameAlt1);

            String l_strGivenNameAlt = WEB3StringTypeUtility.toUpperWbyteKana(l_strGivenNameAlt1);

            //カナ変換後のthis.口座開設見込客行.顧客姓（カナ）と 
            //this.口座開設見込客行.顧客名（カナ）を文字列連結する
            String l_strNameAlt = l_strFamilyNameAlt.concat(l_strGivenNameAlt);
            
            //３） Ｙ客マスタ検索
            StringBuffer l_sbWhere = new StringBuffer();
            List l_lisDataBind = new ArrayList();
            
            l_sbWhere.append("institution_code = ?");
            l_lisDataBind.add(l_strInstitutionCode);
            
            l_sbWhere.append(" and era_born = ?");
            l_lisDataBind.add(l_strEraBorn);
   
            l_sbWhere.append(" and born_date = ?");
            l_lisDataBind.add(l_strBornDate);
        
            l_sbWhere.append(" and replace(replace(name_kana, ' ', ''), '　@', '') = ?");
            l_lisDataBind.add(l_strNameAlt);
            
            Object[] l_obj = new Object[l_lisDataBind.size()];
            l_lisDataBind.toArray(l_obj);
        
            List l_lisRow = Processors.getDefaultProcessor().doFindAllQuery(
                YCustomerRow.TYPE, l_sbWhere.toString(), l_obj);
            
            if (l_lisRow == null || l_lisRow.size() == 0)
            {
                log.debug("該当行が存在しない場合は、nullを返却し処理を終了する。");
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //４）  ３）に於いて該当行が存在する場合
            else
            {
                //４－１）  （引数）部店プリファ@レンス > 1の場合Y客情報を取得し、
                //重複顧客情報を保存する。
                if (l_intBranchPreference > 1) 
                {    
                    for (int i = 0 ; i < l_lisRow.size(); i++)
                    {
                        //４－１－１）  this.Y客マスタ行に、該当行をセットする。
                        this.yCustomerParams = 
                            (YCustomerParams) l_lisRow.get(i);
                        
                        //４－１－２）  重複顧客情報を取得・保存する。(set口座開設審査待ち情報リスト()）。
                        this.setAccOpenJudgeWaitingInfoList(
                            WEB3ReviewCodeDef.YELLOW_ACCOUNT_CHECK, 
                            null,
                            null,
                            null,
                            new Long(this.yCustomerParams.getYCustomerId()),
                            this.getControlBranchCode(),
                            this.getOperationDiv(),
                            new Long(this.yCustomerParams.getControlNumber()));                      
                    }

                }
                
                //４－２）「WARNING_60002：　@Y客の可能性あり」を返却。 
                log.exiting(STR_METHOD_NAME);
                return WEB3ErrorCatalog.WARNING_60002.getErrorCode();
                
            }
        }
        catch (DataFindException l_ex)
        {
            //３） Ｙ客マスタ検索
            log.debug("該当行が存在しない場合は、nullを返却し処理を終了する。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (validate金融機@関)<BR>
     * 金融機@関（銀行）マスタを検索し、該当行があるかをチェックする。<BR>
     * <BR>
     * this.口座開設見込客行.銀行コード，this.口座開設見込客行.支店コードの<BR>
     * 両方がnullであれば、当該処理は実施しない。（return;）<BR>
     * <BR>
     * 以下の条件で金融機@関（銀行）マスタテーブルを検索する。<BR>
     * 該当行が存在しない場合は、存在しない金融機@関と判定し、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01314<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@金融機@関（銀行）マスタ.銀行コード = this.口座開設見込客行.銀行コード And<BR>
     * 　@金融機@関（銀行）マスタ.支店コード = this.口座開設見込客行.支店コード<BR>
     * @@roseuid 41946025035D
     */
    public void validateFinInstitution() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateFinInstitution()";
        log.entering(STR_METHOD_NAME);
        
        if (this.expAccountOpenParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        try
        {
            if (this.expAccountOpenParams.getFinInstitutionCode() == null
                || this.expAccountOpenParams.getFinBranchCode() == null)
            {
                log.debug("両方がnullであれば、当該処理は実施しない。（return;）");
                log.exiting(STR_METHOD_NAME);
                return;
            }
        
            FinInstitutionBankDao.findRowByPk(this.expAccountOpenParams.getFinInstitutionCode(), 
                this.expAccountOpenParams.getFinBranchCode());
        }
        catch (DataFindException l_ex)
        {
            log.error("該当行が存在しない場合は、存在しない金融機@関と判定し、例外をスローする。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01314,
                this.getClass().getName() + STR_METHOD_NAME,
                "該当行が存在しない場合は、存在しない金融機@関と判定し、例外をスローする。"
                + "銀行コード: " + this.expAccountOpenParams.getFinInstitutionCode()
                + " 支店コード: " + this.expAccountOpenParams.getFinBranchCode());
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate顧客名サイズ)<BR>
     * 顧客名のサイズが伝票項目上の最大値を越えていないかチェックする。<BR>
     * <BR>
     * １）　@カナ変換<BR>
     * 　@this.口座開設見込客行.顧客姓（カナ）を半角ｶﾅに変換する。<BR>
     * 　@this.口座開設見込客行.顧客名（カナ）を半角カナに変換する。<BR>
     * 　@（WEB3StringTypeUtility#to1byteKana()を使用する）<BR>
     * <BR>
     * ２）　@カナのチェック<BR>
     * 　@this.口座開設見込客行.顧客姓（カナ） + ’　@’ + <BR>
     * this.口座開設見込客行.顧客名（カナ）<BR>
     * 　@の文字数が9文字より大きい場合（size > 19）、警告メッセージコードを返却する。<BR>
     * <BR>
     * 　@※ 顧客姓（カナ），顧客名（カナ）は、１）で変換した文字列<BR>
     * 　@警告メッセージコード：　@顧客名文字サイズが超過しているため、<BR>
     * 伝票上切り捨てられる。<BR>
     *          tag:   WARNING_60003<BR>
     * <BR>
     * ３）　@漢字のチェック<BR>
     * 　@this.口座開設見込客行.顧客姓（漢字） + ’　@’ + <BR>
     * this.口座開設見込客行.顧客名（漢字）<BR>
     * 　@の文字数が9文字より大きい場合（size > 9）、警告メッセージコードを返却する。<BR>
     * <BR>
     * 　@警告メッセージコード：　@顧客名文字サイズが超過しているため、<BR>
     * 伝票上切り捨てられる。<BR>
     *          tag:   WARNING_60003<BR>
     * @@return String
     * @@roseuid 4194755C0244
     */
    public String validateAccountNameSize()
    {
        final String STR_METHOD_NAME = " validateAccountNameSize()";
        log.entering(STR_METHOD_NAME);

        if (this.expAccountOpenParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@カナ変換
        String l_strNameAlt = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getFamilyNameAlt1())
            + " " + WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getGivenNameAlt1());
        
        String l_strName = this.expAccountOpenParams.getFamilyName()
            + " " + this.expAccountOpenParams.getGivenName();
        
        //２）　@カナのチェック
        if (l_strNameAlt.length() > 19)
        {
            log.debug("２）　@カナのチェック:警告メッセージコード：　@顧客名文字サイズが超過しているため、伝票上切り捨てられる。");
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60003.getErrorCode();
        }
        
        //３）　@漢字のチェック
        if (l_strName.length() > 9)
        {
            log.debug("３）　@漢字のチェック:警告メッセージコード：　@顧客名文字サイズが超過しているため、伝票上切り捨てられる。");
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60003.getErrorCode();
        }
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * (validate住所サイズ)<BR>
     * 住所文字列のサイズが伝票項目上の最大値を越えていないかチェックする。<BR>
     * <BR>
     * １）　@カナ変換<BR>
     * 　@this.口座開設見込客行.住所１（カナ）を半角ｶﾅに変換する。<BR>
     * 　@this.口座開設見込客行.住所２（カナ）を半角カナに変換する。<BR>
     * 　@this.口座開設見込客行.住所３（カナ）を半角カナに変換する。<BR>
     * 　@（WEB3StringTypeUtility#to1byteKana()を使用する）<BR>
     * <BR>
     * ２）　@返却値List（：ArrayList）生成 <BR>
     * 　@返却値List（：ArrayList）を生成する。 <BR>
     * <BR>
     * ３）　@サイズ比較 <BR>
     * 　@半角ｶﾅに変換した住所１（カナ）が、伝票項目サイズよりも大きい場合（size > 30）、<BR>
     * 警告メッセージコード(*1)を返却値Listに追加する。 <BR>
     * 　@半角ｶﾅに変換した住所２（カナ）が、伝票項目サイズよりも大きい場合（size > 30）、<BR>
     * 警告メッセージコード(*2)を返却値Listに追加する。 <BR>
     * 　@半角ｶﾅに変換した住所３（カナ）が、伝票項目サイズよりも大きい場合（size > 30）、<BR>
     * 警告メッセージコード(*3)を返却値Listに追加する。 <BR>
     * 　@this.口座開設見込客行.住所１が伝票項目サイズよりも大きい場合（size > 32）、<BR>
     * 警告メッセージコード(*4)を返却値Listに追加する。 <BR>
     * 　@this.口座開設見込客行.住所２が伝票項目サイズよりも大きい場合（size > 32）、<BR>
     * 警告メッセージコード(*5)を返却値Listに追加する。 <BR>
     * 　@this.口座開設見込客行.住所３が伝票項目サイズよりも大きい場合（size > 32）、<BR>
     * 警告メッセージコード(*6)を返却値Listに追加する。 <BR>
     * <BR>
     * 　@警告メッセージコード(*1)：　@住所１（カナ）文字サイズが超過しているため、伝票上切り捨てられる。 <BR>
     *          tag:   WARNING_60004<BR>
     * 　@警告メッセージコード(*2)：　@住所２（カナ）文字サイズが超過しているため、伝票上切り捨てられる。 <BR>
     *          tag:   WARNING_60005<BR>
     * 　@警告メッセージコード(*3)：　@住所３（カナ）文字サイズが超過しているため、伝票上切り捨てられる。 <BR>
     *          tag:   WARNING_60006<BR>
     * 　@警告メッセージコード(*4)：　@住所１文字サイズが超過しているため、伝票上切り捨てられる。 <BR>
     *          tag:   WARNING_60007<BR>
     * 　@警告メッセージコード(*5)：　@住所２文字サイズが超過しているため、伝票上切り捨てられる。 <BR>
     *          tag:   WARNING_60008<BR>
     * 　@警告メッセージコード(*6)：　@住所３文字サイズが超過しているため、伝票上切り捨てられる。 <BR>
     *          tag:   WARNING_60009<BR>
     * <BR>
     * ４）　@全体サイズ比較 <BR>
　@   * 半角ｶﾅに変換した住所１（カナ）＋住所２（カナ）＋住所３（カナ）の合計サイズが68文字より大きい場合（size > 68）、<BR> 
     * 警告メッセージコード(*7)を返却Listに追加する。<BR>
     * <BR>
　@   * 警告メッセージコード(*7)：　@住所文字サイズが超過しているため、伝票上切り捨てられる。<BR>
     *        tag:   WARNING_60010<BR>
     * <BR>
     * 5）　@警告メッセージコード返却 <BR>
     * 　@（返却値List（：ArrayList）.size() == 0）の場合、nullを返却する。 <BR>
     * 　@以外、返却値List（：ArrayList）.toArray()を返却する。 <BR>
     * @@return String[]
     * @@roseuid 419477DE0215
     */
    public String[] validateAddressSize()
    {
        final String STR_METHOD_NAME = " validateAddressSize()";
        log.entering(STR_METHOD_NAME);
        
        if (this.expAccountOpenParams == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //１）　@カナ変換
        String l_straddressLine1Kana = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getAddressLine1Kana());
        String l_straddressLine2Kana = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getAddressLine2Kana());
        String l_straddressLine3Kana = WEB3StringTypeUtility.to1byteKana(this.expAccountOpenParams.getAddressLine3Kana());
        
        //２）　@返却値List（：ArrayList）生成 
        List l_lisWarningInfo = new ArrayList();
        
        //３）　@サイズ比較 
        if (l_straddressLine1Kana != null && l_straddressLine1Kana.length() > 30)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60004.getErrorCode());
        }
        
        if (l_straddressLine2Kana != null && l_straddressLine2Kana.length() > 30)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60005.getErrorCode());
        }
        
        if (l_straddressLine3Kana != null && l_straddressLine3Kana.length() > 30)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60006.getErrorCode());
        }
        
        if (this.expAccountOpenParams.getAddressLine1() != null && this.expAccountOpenParams.getAddressLine1().getBytes().length > 32)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60007.getErrorCode());
        }
        
        if (this.expAccountOpenParams.getAddressLine2() != null && this.expAccountOpenParams.getAddressLine2().getBytes().length > 32)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60008.getErrorCode());
        }
        
        if (this.expAccountOpenParams.getAddressLine3() != null && this.expAccountOpenParams.getAddressLine3().getBytes().length > 32)
        {
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60009.getErrorCode());
        }
        
        //４）　@全体サイズ比較 
        StringBuffer l_all = new StringBuffer();
        if (l_straddressLine1Kana != null)
        {
            l_all.append(l_straddressLine1Kana);
        }
        if (l_straddressLine2Kana != null)
        {
            l_all.append(l_straddressLine2Kana);
        }
        if (l_straddressLine3Kana != null)
        {
            l_all.append(l_straddressLine3Kana);
        }
        
        String l_strAll = l_all.toString();
        
        if (l_strAll.length() > 68)
        {
            //警告メッセージコード(*7)：　@住所文字サイズが超過しているため、伝票上切り捨てられる。
            l_lisWarningInfo.add(WEB3ErrorCatalog.WARNING_60010.getErrorCode());
        }
        
        // 5）　@警告メッセージコード返却 
        if (l_lisWarningInfo.size() == 0)
        {
            log.debug("（返却値List（：ArrayList）.size() == 0）の場合、nullを返却する。");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        else
        {
            log.debug("以外、返却値List（：ArrayList）.toArray()を返却する。");
            String[] l_strWarnings = new String[l_lisWarningInfo.size()];
            l_lisWarningInfo.toArray(l_strWarnings);
            
            log.exiting(STR_METHOD_NAME);
            return l_strWarnings;
        }
    }

    /**
     * (saveNew口座開設見込客)<BR>
     * 口座開設見込客テーブルを更新する。<BR>
     * <BR>
     * １） 口座開設見込客行オブジェクト取得 <BR>
     * 　@口座開設見込客.getDataSourceObject()にて口座開設見込客行を取得する。 <BR>
     * <BR>
     * ２） 更新情報をセットする。 <BR>
     * 　@口座開設見込客行の入力データ以外の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「口座開設見込客DB更新（Insert）仕様.xls」参照<BR>
     * <BR>
     * ３） DB更新 <BR>
     * 　@口座開設見込客行オブジェクトの内容で、口座開設見込客テーブルを<BR>
     * 更新（insert）する。 <BR>
     * @@param l_strValidateType - チェックタイプ<BR>
     * <BR>
     * 0：DEFAULT（顧客申込）<BR>
     * 1：管理者申込<BR>
     * 2：申込更新<BR>
     * 3：伝票作成<BR>
     * @@param l_strUpdaterCode - 更新者コード
     * @@param l_strCreatorCode - 作成者コード
     * @@param l_strRequestNumber - 識別コード<BR>
     * <BR>
     * ※新規採番した値<BR>
     * @@roseuid 4194856D035D
     */
    public void saveNewExpAccountOpen(String l_strValidateType, String l_strUpdaterCode, String l_strCreatorCode, String l_strRequestNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveNewExpAccountOpen(String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.getDataSourceObject());

            String l_strInstitutionCode = l_expAccountOpenParams.getInstitutionCode();
            String l_strBranchCode = l_expAccountOpenParams.getBranchCode();
            String l_strAccountDiv = l_expAccountOpenParams.getAccountDiv();
            
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            Institution l_institution = l_accountManager.getInstitution(l_strInstitutionCode);
            long l_lngBranchId = l_accountManager.getBranch(l_institution, l_strBranchCode).getBranchId();
            
            //証券会社ID:証券会社コードに該当する証券会社ID。
            l_expAccountOpenParams.setInstitutionId(l_institution.getInstitutionId());
            
            //部店ＩＤ:証券会社，部店コードに該当する部店ID。
            l_expAccountOpenParams.setBranchId(l_lngBranchId);
            
            //識別コード:識別コード（※get新規識別コード()にて取得した識別コード）
            l_expAccountOpenParams.setAccOpenRequestNumber(l_strRequestNumber);
            
            //口座登録日:null
            l_expAccountOpenParams.setAccountOpenDate(null);
            
            //振替区分
            //（振込先銀行情報 != null）の場合、振替区分に「1：銀行振込」をセットする。
            //（郵便振替情報 != null）の場合、振替区分に「5：郵貯振込」をセットする。
            WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService) Services.getService(WEB3AccOpenInfoCreatedService.class);
            WEB3AccOpenApplyInfo l_applyInfo = l_service.toAccOpenApplyInfo(this);
            if (l_applyInfo.transferBankInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.BANK_TRANSFER); 
            }
            else if (l_applyInfo.postalTransferInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.POST_TRANSFER); 
            }
            
            //現物株式フラグ:投資経験.現物株式のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.TRUE);
            }
            
            //信用取引フラグ:投資経験.信用取引のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivMargin()))
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.TRUE);
            }
            
            //債券フラグ:投資経験.債券のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivBond()))
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.TRUE);
            }
            
            //転換社債フラグ:投資経験.転換社債のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivWt()))
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.TRUE);
            }            
            
            //投資信託（株式）フラグ:投資経験.投資信託（株式）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundSk()))
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.TRUE);
            }            
            
            //投資信託（公社債）フラグ:投資経験.投資信託（公社債）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundBd()))
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.TRUE);
            }            
            
            //先物・オプションフラグ:投資経験.投資信託（先物・オプション）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFo()))
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.TRUE);
            }            
            
            //外国証券フラグ:投資経験.投資信託（外国証券）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.TRUE);
            }            
            
            //その他フラグ:投資経験.投資信託（その他）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEtc()))
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.TRUE);
            }            

            //Q&A: WEB3-ACCOUNTOPEN-A-UT-0024 
            boolean l_blnIsCustomized = false;
            
            //経験年数（自）:※投資経験.現物株式より計算する。
            //経験年数（至）:※投資経験.現物株式より計算する。
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "experience_div_equity");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "experience_div_equity");
                
                l_expAccountOpenParams.setExperienceFrom(l_strRanges[0]);
                l_expAccountOpenParams.setExperienceTo(l_strRanges[1]);
            }
            else
            {
                String l_strExperienceDivEquity = l_expAccountOpenParams.getExperienceDivEquity();
                
                String l_strExperienceFrom = null;
                String l_strExperienceTo = null;
                
                if (WEB3ExperienceDivDef.WITHOUT.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.ONE_YEAR;
                }
                else if (WEB3ExperienceDivDef.TWO_TO_THREE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.TWO_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.THREE_YEARS;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FOUR_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                }
                else if (WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                    l_strExperienceTo = null;
                }
                
                l_expAccountOpenParams.setExperienceFrom(l_strExperienceFrom);
                l_expAccountOpenParams.setExperienceTo(l_strExperienceTo);
            }
            
            //年収（自）:※年収区分より計算する。
            //年収（至）:※年収区分より計算する。
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "annual_income_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "annual_income_div");
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAnnualIncomeDiv());
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strIncomes[1]);
            }
            
            //金融資産（自）:※金融資産区分より計算する。
            //金融資産（至）:※金融資産区分より計算する。
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "asset_value_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "asset_value_div");
                
                l_expAccountOpenParams.setAssetValueFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAssetValueDiv());
                
                l_expAccountOpenParams.setAssetValueFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strIncomes[1]);
            }
            
            //更新者コード:
            l_expAccountOpenParams.setLastUpdater(l_strUpdaterCode);           
            
            //作成日時:処理日時
            l_expAccountOpenParams.setCreatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());
            
            //更新日時:処理日時
            l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());                                    
            
            //作成者コード
            l_expAccountOpenParams.setCreator(l_strCreatorCode);
            
            //専用振込先口座番号:null
            l_expAccountOpenParams.setExclusiveUseAccountNo(null);
            
            //HULFT送信日時:null
            l_expAccountOpenParams.setSendTimestamp(null);

            Processors.getDefaultProcessor().doInsertQuery(l_expAccountOpenParams);

            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (save口座開設見込客)<BR>
     * 口座開設見込客テーブルを更新する。<BR>
     * <BR>
     * １） 口座開設見込客行オブジェクト取得 <BR>
     * 　@口座開設見込客.getDataSourceObject()にて口座開設見込客行を取得する。 <BR>
     * <BR>
     * ２） 更新情報をセットする。 <BR>
     * 　@口座開設見込客行の入力データ以外の項目に値をセットする。 <BR>
     * <BR>
     * 　@DB更新仕様「口座開設見込客DB更新（Update）仕様.xls」参照<BR>
     * <BR>
     * ３） DB更新 <BR>
     * 　@口座開設見込客行オブジェクトの内容で、口座開設見込客テーブルを<BR>
     * 更新（update）する。 <BR>
     * @@param l_strValidateType - チェックタイプ<BR>
     * <BR>
     * 0：DEFAULT（顧客申込）<BR>
     * 1：管理者申込<BR>
     * 2：申込更新<BR>
     * 3：伝票作成<BR>
     * @@param l_strUpdaterCode - 更新者コード
     * @@roseuid 4194858B0179
     */
    public void saveExpAccountOpen(String l_strValidateType, String l_strUpdaterCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " saveExpAccountOpen(String, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            ExpAccountOpenParams l_expAccountOpenParams = new ExpAccountOpenParams((ExpAccountOpenRow)this.getDataSourceObject());

            String l_strInstitutionCode = l_expAccountOpenParams.getInstitutionCode();
            String l_strAccOpenRequestNumber = l_expAccountOpenParams.getAccOpenRequestNumber();
            
            String l_strQueryString = "institution_code = ? and acc_open_request_number = ?";
            Object[] l_queryDataContainer = new Object[]{l_strInstitutionCode, l_strAccOpenRequestNumber};
            
            List l_lisRecords = Processors.getDefaultProcessor().doFindAllQuery(ExpAccountOpenRow.TYPE, l_strQueryString, l_queryDataContainer);

            if (l_lisRecords != null && l_lisRecords.size() > 0)
            {
                //既存値シート
                ExpAccountOpenRow l_expAccountOpenRowInDB = (ExpAccountOpenRow) l_lisRecords.get(0);
                l_expAccountOpenParams.setInstitutionId(l_expAccountOpenRowInDB.getInstitutionId());
                l_expAccountOpenParams.setOrderDiv(l_expAccountOpenRowInDB.getOrderDiv());
                l_expAccountOpenParams.setAccountOpenDate(l_expAccountOpenRowInDB.getAccountOpenDate());
                l_expAccountOpenParams.setCreatedTimestamp(l_expAccountOpenRowInDB.getCreatedTimestamp());
                l_expAccountOpenParams.setCreator(l_expAccountOpenRowInDB.getCreator());
                l_expAccountOpenParams.setExclusiveUseAccountNo(l_expAccountOpenRowInDB.getExclusiveUseAccountNo());
                l_expAccountOpenParams.setSendTimestamp(l_expAccountOpenRowInDB.getSendTimestamp());
            }

            String l_strBranchCode = l_expAccountOpenParams.getBranchCode();
            String l_strAccountDiv = l_expAccountOpenParams.getAccountDiv();
            
            AccountManager l_accountManager = GtlUtils.getAccountManager();
            long l_lngBranchId = l_accountManager.getBranch(
                l_accountManager.getInstitution(l_strInstitutionCode),
                l_strBranchCode).getBranchId();
            
            //部店ＩＤ:証券会社，部店コードに該当する部店ID。
            l_expAccountOpenParams.setBranchId(l_lngBranchId);
            
            //振替区分
            //（振込先銀行情報 != null）の場合、振替区分に「1：銀行振込」をセットする。
            //（郵便振替情報 != null）の場合、振替区分に「5：郵貯振込」をセットする。
            WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService) Services.getService(WEB3AccOpenInfoCreatedService.class);
            WEB3AccOpenApplyInfo l_applyInfo = l_service.toAccOpenApplyInfo(this);
            if (l_applyInfo.transferBankInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.BANK_TRANSFER); 
            }
            else if (l_applyInfo.postalTransferInfo != null)
            {
                l_expAccountOpenParams.setTransferDiv(WEB3FinTransferDivDef.POST_TRANSFER); 
            }
            
            //現物株式フラグ:投資経験.現物株式のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEquity(BooleanEnum.TRUE);
            }
            
            //信用取引フラグ:投資経験.信用取引のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivMargin()))
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagMargin(BooleanEnum.TRUE);
            }
            
            //債券フラグ:投資経験.債券のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivBond()))
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagBond(BooleanEnum.TRUE);
            }
            
            //転換社債フラグ:投資経験.転換社債のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivWt()))
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagWt(BooleanEnum.TRUE);
            }            
            
            //投資信託（株式）フラグ:投資経験.投資信託（株式）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundSk()))
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundSk(BooleanEnum.TRUE);
            }            
            
            //投資信託（公社債）フラグ:投資経験.投資信託（公社債）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFundBd()))
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFundBd(BooleanEnum.TRUE);
            }            
            
            //先物・オプションフラグ:投資経験.投資信託（先物・オプション）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFo()))
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFo(BooleanEnum.TRUE);
            }            
            
            //外国証券フラグ:投資経験.投資信託（外国証券）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivFEquity()))
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagFEquity(BooleanEnum.TRUE);
            }            
            
            //その他フラグ:投資経験.投資信託（その他）のコード値に対応した値。
            if (WEB3ExperienceDivDef.WITHOUT.equals(l_expAccountOpenParams.getExperienceDivEtc()))
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.FALSE);
            }
            else
            {
                l_expAccountOpenParams.setExperienceFlagEtc(BooleanEnum.TRUE);
            }            
            
            //Q&A: WEB3-ACCOUNTOPEN-A-UT-0024 
            boolean l_blnIsCustomized = false;
            
            //経験年数（自）:※投資経験.現物株式より計算する。
            //経験年数（至）:※投資経験.現物株式より計算する。
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "experience_div_equity");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "experience_div_equity");
                
                l_expAccountOpenParams.setExperienceFrom(l_strRanges[0]);
                l_expAccountOpenParams.setExperienceTo(l_strRanges[1]);
            }
            else
            {
                String l_strExperienceDivEquity = l_expAccountOpenParams.getExperienceDivEquity();
                
                String l_strExperienceFrom = null;
                String l_strExperienceTo = null;
                
                if (WEB3ExperienceDivDef.WITHOUT.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.NO_EXPERIENCE;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.ONE_YEAR;
                }
                else if (WEB3ExperienceDivDef.TWO_TO_THREE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.TWO_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.THREE_YEARS;
                }
                else if (WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FOUR_YEARS;
                    l_strExperienceTo = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                }
                else if (WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS.equals(l_strExperienceDivEquity))
                {
                    l_strExperienceFrom = WEB3AccOpenInvestmentExperienceDef.FIVE_YEARS;
                    l_strExperienceTo = null;
                }
                
                l_expAccountOpenParams.setExperienceFrom(l_strExperienceFrom);
                l_expAccountOpenParams.setExperienceTo(l_strExperienceTo);
            }
            
            //年収（自）:※年収区分より計算する。
            //年収（至）:※年収区分より計算する。
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "annual_income_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "annual_income_div");
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAnnualIncomeDiv());
                
                l_expAccountOpenParams.setAnnualIncomeFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAnnualIncomeTo(l_strIncomes[1]);
            }
            
            //金融資産（自）:※金融資産区分より計算する。
            //金融資産（至）:※金融資産区分より計算する。
            l_blnIsCustomized = isCustomized(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strValidateType, "asset_value_div");
            
            if (l_blnIsCustomized)
            {
                String[] l_strRanges = this.getCustomizedValues(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, "asset_value_div");
                
                l_expAccountOpenParams.setAssetValueFrom(l_strRanges[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strRanges[1]);
            }
            else
            {
                String[] l_strIncomes = this.getFinancialValues(l_expAccountOpenParams.getAssetValueDiv());
                
                l_expAccountOpenParams.setAssetValueFrom(l_strIncomes[0]);
                l_expAccountOpenParams.setAssetValueTo(l_strIncomes[1]);
            }
            
            //更新者コード:管理者.管理者コード。
            l_expAccountOpenParams.setLastUpdater(l_strUpdaterCode);
            
            //更新日時:処理日時
            l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getTradingSystem().getSystemTimestamp());

            Processors.getDefaultProcessor().doUpdateQuery(l_expAccountOpenParams);
            
            //U01059
            this.expAccountOpenParams = l_expAccountOpenParams;
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get口座開設見込客)<BR>
     * （static メソッド）<BR>
     * 指定に該当する口座開設見込客オブジェクトのListを取得する。 <BR>
     * <BR>
     * １）　@QueryProcessor.doFindAllQuery( )により、口座開設見込客行<BR>
     * オブジェクトのListを取得する。 <BR>
     * 　@該当データがない場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00398<BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数] <BR>
     * 　@　@rowType：　@口座開設見込客Row.TYPE <BR>
     * 　@　@where：　@検索条件文字列 <BR>
     * 　@　@orderBy：　@ソート条件 <BR>
     * 　@　@conditions：　@null <BR>
     * 　@　@bindVars：　@検索条件データコンテナ <BR>
     * <BR>
     * ２）　@返却値List（：ArrayList）生成<BR>
     * 　@返却値List（：ArrayList）を生成する。<BR>
     * <BR>
     * ３）　@追加条件判定<BR>
     * 　@１）で取得した各要素について、３－１）～３－６）を実施する。<BR>
     * <BR>
     * 　@３－１）　@口座開設見込客オブジェクト生成<BR>
     * 　@　@対象要素（：口座開設見込客Params）を指定し、口座開設見込客<BR>
     * オブジェクトを生成する。<BR>
     * <BR>
     * 　@　@[コンストラクタの引数]<BR>
     * 　@　@口座開設見込客行：　@（対象要素）<BR>
     * <BR>
     * 　@３－２)　@伝票ステータス取得<BR>
     * 　@　@口座開設見込客.get伝票ステータス()にて、口座開設伝票作成ステータスの<BR>
     * 配列を取得する。<BR>
     * <BR>
     * 　@３－３）　@SONAR送信日（自）の判定<BR>
     * 　@※　@SONAR送信日（自）に指定がある場合（SONAR送信日（自） != null）のみ<BR>
     * <BR>
     * 　@　@取得した各伝票ステータス(**1)のうち、以下の条件に当てはまる要素が<BR>
     * １つも存在しない場合、<BR>
     * 　@　@当該要素について、以降の処理を実施しない（continue;）。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@SONAR送信日（自） < 口座開設伝票作成ステータス[index].送信日時<BR>
     * <BR>
     * 　@　@(**1)　@各伝票ステータス<BR>
     * 　@　@引数で（口座開設状況区分 == ”エラー発生”）が指定されている場合は、<BR>
     * 　@　@口座開設伝票作成ステータス[index].伝票作成ステータス == ”送信エラー”または、
     * 　@　@口座開設伝票作成ステータス[index].伝票作成ステータス == ”受信エラー”<BR>
     * 　@　@の伝票作成ステータス行のみを対象とする。<BR>
     * 　@　@以外、get伝票ステータス()にて取得できたすべての行が対象。<BR>
     * <BR>
     * 　@３－４）　@SONAR送信日（至）の判定<BR>
     * 　@※　@SONAR送信日（至）に指定がある場合（SONAR送信日（至） != null）のみ<BR>
     * <BR>
     * 　@　@取得した各伝票ステータス(**1)のうち、以下の条件に当てはまる要素が<BR>
     * １つも存在しない場合、<BR>
     * 　@　@当該要素について、以降の処理を実施しない（continue;）。<BR>
     * <BR>
     * 　@　@[条件]<BR>
     * 　@　@SONAR送信日（至） > 口座開設伝票作成ステータス[index].送信日時<BR>
     * <BR>
     * 　@３－５）　@口座開設状況区分の判定<BR>
     * 　@※　@口座開設状況区分に指定がある場合（引数の口座開設状況区分 != null）のみ<BR>
     * <BR>
     * 　@　@口座開設状況区分が指定の値と違う場合<BR>
     * （口座開設見込客.get口座開設状況区分 != 口座開設状況区分）、<BR>
     * 　@　@当該要素について、以降の処理を実施しない（continue;）。<BR>
     * <BR>
     * 　@３－６）　@返却値List（：ArrayList）に追加<BR>
     * 　@　@３－１）で生成した口座開設見込客オブジェクトを、<BR>
     * 返却値List（：ArrayList）に追加（add()）する。<BR>
     * <BR>
     * ４）　@返却値List（：ArrayList）を返却する。<BR>
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@param l_datSonarSendDateFrom - SONAR送信日（自）
     * @@param l_datSonarSendDateTo - SONAR送信日（至）
     * @@param l_strAccountOpenStatusDiv - 口座開設状況区分<BR>
     * <BR>
     * 　@0：　@DEFAULT（未開設）<BR>
     * 　@1：　@開設中<BR>
     * 　@2：　@エラー発生<BR>
     * 　@3：　@開設済<BR>
     *
     * @@return List
     * @@roseuid 419C6107039D
     */
    public static List getExpAccountOpen(String l_strQueryString, String[] l_queryContainer, String l_strSortCond, Date l_datSonarSendDateFrom, Date l_datSonarSendDateTo, String l_strAccountOpenStatusDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExpAccountOpen(String, String[], String, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //１）　@QueryProcessor.doFindAllQuery( )により、口座開設見込客行オブジェクトのListを取得する。
            List l_lisRow = Processors.getDefaultProcessor().doFindAllQuery(ExpAccountOpenRow.TYPE,
                l_strQueryString, l_strSortCond, null, l_queryContainer);
            
            if (l_lisRow == null || l_lisRow.size() == 0)
            {
                log.debug("該当データがない場合、例外をスローする。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                    "該当データがない場合、例外をスローする。");
            }
            
            //２）　@返却値List（：ArrayList）生成 
            List l_lisReturnList = new ArrayList();
            
            //３）　@追加条件判定
            int l_intLength = l_lisRow.size();
            //１）で取得した各要素について、３－１）～３－６）を実施する。
            for (int i = 0; i < l_intLength; i++)
            {
                //３－１）　@口座開設見込客オブジェクト生成 
                WEB3AccOpenExpAccountOpen l_accOpenExpAccountOpen = new WEB3AccOpenExpAccountOpen(
                    new ExpAccountOpenParams((ExpAccountOpenRow) l_lisRow.get(i)));
                
                //３－２)　@伝票ステータス取得 
                WEB3AccOpenVoucherCreatedStatus[] l_vocherCreatedStatus = l_accOpenExpAccountOpen.getVoucherStatus();                

                //３－３）　@SONAR送信日（自）の判定 
                //※　@SONAR送信日（自）に指定がある場合（SONAR送信日（自） != null）のみ
                if (l_datSonarSendDateFrom != null)
                {
                    log.debug("※　@SONAR送信日（自）に指定がある場合（SONAR送信日（自） != null）のみ");
                    int l_intCount = 0;
                    
                    if (l_vocherCreatedStatus == null)
                    {
                        continue;
                    }
                    
                    int l_intStatusLength = l_vocherCreatedStatus.length;
                    
                    for (int j = 0; j < l_intStatusLength; j++)
                    {
                        Timestamp l_tsSendTimestamp = ((AccOpenVoucherStatusRow) l_vocherCreatedStatus[j].getDataSourceObject()).getSendTimestamp();
                        
                        if (l_tsSendTimestamp == null)
                        {
                            continue;
                        }
                        //引数で（口座開設状況区分 == ”エラー発生”）が指定されている場合は
                        if (WEB3AccountOpenStatusDivDef.ERROR.equals(l_strAccountOpenStatusDiv))
                        {
                            log.debug("引数で（口座開設状況区分 == ”エラー発生”）が指定されている場合は");
                            //口座開設伝票作成ステータス[index].伝票作成ステータス == ”送信エラー”または、 
                            //口座開設伝票作成ステータス[index].伝票作成ステータス == ”受信エラー” 
                            if (WEB3VoucherStatusDef.SEND_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()) || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()))
                            {
                                log.debug("口座開設伝票作成ステータス[index].伝票作成ステータス == ”送信エラー”または、口座開設伝票作成ステータス[index].伝票作成ステータス == ”受信エラー”");
                                //SONAR送信日（自） > 口座開設伝票作成ステータス[index].送信日時
                                //QA:WEB3-ACCOUNTOPEN-A-FT-0042
                                if (WEB3DateUtility.compareToSecond(l_datSonarSendDateFrom, l_tsSendTimestamp) <= 0)
                                {
                                    log.debug("SONAR送信日（自） < 口座開設伝票作成ステータス[index].送信日時");
                                    l_intCount = l_intCount + 1;
                                }
                            }
                        }
                        else
                        {
                            log.debug("引数で（口座開設状況区分 != ”エラー発生”）が指定されている場合は");
                            //SONAR送信日（自） > 口座開設伝票作成ステータス[index].送信日時
                            if (WEB3DateUtility.compareToSecond(l_datSonarSendDateFrom, l_tsSendTimestamp) <= 0)
                            {
                                log.debug("SONAR送信日（自） < 口座開設伝票作成ステータス[index].送信日時");
                                l_intCount = l_intCount + 1;
                            }
                        }
                    }
                    
                    //取得した各伝票ステータス(**1)のうち、以下の条件に当てはまる要素が１つも存在しない場合、 
                    //当該要素について、以降の処理を実施しない（continue;）。 
                    if (l_intCount == 0)
                    {
                        log.debug("取得した各伝票ステータス(**1)のうち、以下の条件に当てはまる要素が１つも存在しない場合、当該要素について、以降の処理を実施しない（continue;）。");
                        continue;
                    }
                }
                
                //３－４）　@SONAR送信日（至）の判定 
                if (l_datSonarSendDateTo != null)
                {
                    log.debug("３－４）　@SONAR送信日（至）!= null");
                    int l_intCount = 0;
                    
                    if (l_vocherCreatedStatus == null)              
                    {               
                        continue;               
                    }               
                                
                    int l_intStatusLength = l_vocherCreatedStatus.length;               

                    
                    for (int j = 0; j < l_intStatusLength; j++)
                    {
                        Timestamp l_tsSendTimestamp = ((AccOpenVoucherStatusRow) l_vocherCreatedStatus[j].getDataSourceObject()).getSendTimestamp();
                        
                        if (l_tsSendTimestamp == null)
                        {
                            continue;
                        }
                        //引数で（口座開設状況区分 == ”エラー発生”）が指定されている場合は
                        if (WEB3AccountOpenStatusDivDef.ERROR.equals(l_strAccountOpenStatusDiv))
                        {
                            log.debug("引数で（口座開設状況区分 == ”エラー発生”）が指定されている場合は");
                            //口座開設伝票作成ステータス[index].伝票作成ステータス == ”送信エラー”または、 
                            //口座開設伝票作成ステータス[index].伝票作成ステータス == ”受信エラー” 
                            if (WEB3VoucherStatusDef.SEND_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()) || WEB3VoucherStatusDef.RECEIVE_ERROR.equals(l_vocherCreatedStatus[j].getVoucherStatus()))
                            {
                                log.debug("口座開設伝票作成ステータス[index].伝票作成ステータス == ”送信エラー”または、口座開設伝票作成ステータス[index].伝票作成ステータス == ”受信エラー”");
                                //SONAR送信日（至） < 口座開設伝票作成ステータス[index].送信日時 
                                if (l_datSonarSendDateTo.compareTo(l_tsSendTimestamp) >= 0)
                                {
                                    log.debug("SONAR送信日（至） > 口座開設伝票作成ステータス[index].送信日時 ");
                                    l_intCount = l_intCount + 1;
                                }
                            }
                        }
                        else
                        {
                            //SONAR送信日（至） < 口座開設伝票作成ステータス[index].送信日時 
                            if (WEB3DateUtility.compareToSecond(l_datSonarSendDateTo, l_tsSendTimestamp) >= 0)
                            {
                                log.debug("SONAR送信日（至） > 口座開設伝票作成ステータス[index].送信日時");
                                l_intCount = l_intCount + 1;
                            }
                        }
                    }
                    
                    //取得した各伝票ステータス(**1)のうち、以下の条件に当てはまる要素が１つも存在しない場合、 
                    //当該要素について、以降の処理を実施しない（continue;）。 
                    if (l_intCount == 0)
                    {
                        log.debug("取得した各伝票ステータス(**1)のうち、以下の条件に当てはまる要素が１つも存在しない場合、当該要素について、以降の処理を実施しない（continue;）。");
                        continue;
                    }
                }

                //３－５）　@口座開設状況区分の判定 
                if (l_strAccountOpenStatusDiv != null)
                {
                    if (!l_strAccountOpenStatusDiv.equals(l_accOpenExpAccountOpen.getAccountOpenStatusDiv()))
                    {
                        log.debug("口座開設状況区分が指定の値と違う場合（口座開設見込客.get口座開設状況区分 != 口座開設状況区分）、当該要素について、以降の処理を実施しない（continue;）。");
                        continue;
                    }
                }
                
                //３－６）　@返却値List（：ArrayList）に追加
                log.debug("３－６）　@返却値List（：ArrayList）に追加");
                l_lisReturnList.add(l_accOpenExpAccountOpen);
            }
            
            //４）　@返却値List（：ArrayList）を返却する。 
            log.exiting(STR_METHOD_NAME);
            return l_lisReturnList;
        }
        catch (DataFindException l_ex)
        {
            log.debug("該当データがない場合、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                "該当データがない場合、例外をスローする。");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (get口座開設見込客伝票)<BR>
     * （static メソッド）<BR>
     *  (状況問合せ一覧用メソッド)<BR>
     * 指定に該当する口座開設見込客オブジェクトのListを取得する。 <BR>
     * <BR>
     * １）　@SONAR送信日(自)、SONAR送信日(至)を定数に定義する。<BR>
     * <BR>
     * 　@　@    final String SONAR_DATE_FROM = "SONAR_DATE_FROM"<BR>
     *         final String SONAR_DATE_TO = "SONAR_DATE_TO"<BR>
     * <BR>
     * ２）　@引数.口座開設状況 != nullの場合、検索条件に追加する。<BR>
     * 　@２－１）　@口座開設状況区分：”開設済”の場合<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列<BR>
     * 　@　@　@　@　@　@" and account_open_date is not null "<BR>
     * <BR>
     * 　@２－２）　@口座開設状況区分：”未開設”の場合<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列<BR>
     * 　@　@　@　@　@　@" and acc_open_request_number not in "<BR>
     *             + "(select distinct exp_account_open.acc_open_request_number from acc_open_voucher_status b "<BR>
     *             + "where exp_account_open.acc_open_request_number = b.acc_open_request_number and b.voucher_status > ?"<BR>
     *             + " and b.institution_code = ? )"<BR>
     * 　@　@　@　@　@　@<BR>
     * 　@　@　@　@　@検索条件データ<BR>
     * 　@　@　@　@　@　@伝票作成ステータス：０「未開設」<BR>
     * 　@　@　@　@　@　@引数.データコンテナ[0]<BR>
     * <BR>
     * 　@２－３）　@口座開設状況区分：”開設中”の場合<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列<BR>
     *             " and account_open_date is null "<BR>
     *             + " and acc_open_request_number in "<BR>
     *             + "(select distinct acc_open_request_number from acc_open_voucher_status "<BR>
     *             + "where voucher_status not in (?, ?, ?) "<BR>
     *             + "and institution_code = ? "<BR>
     *             + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")"<BR>
     * 　@　@　@　@　@検索条件データ<BR>
     * 　@　@　@　@　@　@伝票作成ステータス：０「未開設」<BR>
     * 　@　@　@　@　@　@伝票作成ステータス：５「送信エラー」<BR>
     * 　@　@　@　@　@　@伝票作成ステータス：６「受信エラー」<BR>
     * 　@　@　@　@　@　@引数.データコンテナ[0]<BR>
     * <BR>
     * 　@２－４）　@口座開設状況区分：”エラー発生”の場合<BR>
     * <BR>
     * 　@　@　@　@　@検索条件文字列<BR>
     * 　@　@　@　@　@　@" and acc_open_request_number in "<BR>
     * 　@　@　@　@　@　@+ " (select distinct acc_open_request_number from acc_open_voucher_status "<BR>
     *             + "where voucher_status in (?, ?) "<BR>
     *             + "and institution_code = ? "<BR>
     *             + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")"<BR>
     * 　@　@　@　@　@検索条件データ<BR>
     * 　@　@　@　@　@　@伝票作成ステータス：５「送信エラー」<BR>
     * 　@　@　@　@　@　@伝票作成ステータス：６「受信エラー」<BR>
     * 　@　@　@　@　@　@引数.データコンテナ[0]<BR>
     * <BR>
     * ３）　@引数.SONAR送信日(自) != nullの場合、検索条件に追加する。<BR>
     * 　@３－１）　@SONAR送信日(自) != null かつ、口座開設状況区分 == 開設中 or エラー発生の場合、<BR>
     * 　@　@　@　@　@　@検索条件文字列のSONAR_DATE_FROMを<BR>
     * 　@　@　@　@　@　@" and send_timestamp >= ?"<BR>
     * 　@　@　@　@　@　@に置換する。<BR>
     * 　@３－２）　@SONAR送信日(自) != null かつ、口座開設状況区分 == null or 未開設 or 開設済の場合、<BR>
     * 　@　@　@　@　@　@検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@　@" and acc_open_request_number in "<BR>
     * 　@　@　@　@　@　@+ "(select acc_open_request_number from acc_open_voucher_status "<BR>
     * 　@　@　@　@　@　@+ "where send_timestamp >= ? )"<BR>
     * 　@３－３）　@３－１）、３－２）の時、<BR>
     * 　@　@　@　@　@　@検索条件データに引数.SONAR送信日（自）を追加する。<BR>
     * 　@３－４）　@SONAR送信日(自) == null かつ、口座開設状況区分 == 開設中 or エラー発生の場合、<BR>
     * 　@　@　@　@　@　@検索条件文字列のSONAR_DATE_FROMを空文字""に置換する。<BR>
     * <BR>
     * ４）　@引数.SONAR送信日(至) != nullの場合、検索条件に追加する。<BR>
     * 　@４－１）　@SONAR送信日(至) != null かつ、口座開設状況区分 == 開設中 or エラー発生の場合、<BR>
     * 　@　@　@　@　@　@検索条件文字列のSONAR_DATE_TOを<BR>
     * 　@　@　@　@　@　@" and send_timestamp < ?"<BR>
     * 　@　@　@　@　@　@に置換する。<BR>
     * 　@４－２）　@SONAR送信日(至) != null かつ、口座開設状況区分 == null or 未開設 or 開設済の場合、<BR>
     * 　@　@　@　@　@　@検索条件文字列に追加する。<BR>
     * 　@　@　@　@　@　@" and acc_open_request_number in "<BR>
     * 　@　@　@　@　@　@+ "(select acc_open_request_number from acc_open_voucher_status "<BR>
     * 　@　@　@　@　@　@+ "where send_timestamp < ? )"<BR>
     * 　@４－４）　@４－１）、４－２）の時、<BR>
     * 　@　@　@　@　@　@検索条件データに引数.SONAR送信日（至）の翌日を追加する。<BR>
     * 　@４－４）　@SONAR送信日(至) == null かつ、口座開設状況区分 == 開設中 or エラー発生の場合、<BR>
     * 　@　@　@　@　@　@検索条件文字列のSONAR_DATE_TOを空文字""に置換する。<BR>
     * <BR>
     * ５）　@QueryProcessor.doFindAllQuery( )により、口座開設見込客行オブジェクトのListPageを取得する。 <BR>
     * 　@５－１）口座開設見込客行オブジェクトのListPageを取得する。<BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数] <BR>
     * 　@　@rowType：　@口座開設見込客Row.TYPE <BR>
     * 　@　@where：　@検索条件文字列 <BR>
     * 　@　@orderBy：　@ソート条件 <BR>
     * 　@　@conditions：　@null <BR>
     * 　@　@bindVars：　@検索条件データコンテナ<BR>
     * 　@　@pageSize：　@ページ内表示行数<BR>
     * 　@　@pageNumber：　@要求ページ番号<BR>
     * <BR>
     * 　@５－２）該当データがない場合、口座開設見込客行オブジェクトのListPageを取得する<BR>
     * 　@　@　@　@　@リクエスト.要求ページ番号が最終ページを超えた場合、<BR>
     * 　@　@　@　@　@口座開設見込客行オブジェクトの最終ページのListを取得する。<BR>
     * <BR>
     * 　@　@　@　@　@　@　@[doFindAllQuery()に指定する引数] <BR>
     * 　@　@　@　@　@　@　@rowType：　@口座開設見込客Row.TYPE <BR>
     * 　@　@　@　@　@　@　@where：　@検索条件文字列 <BR>
     * 　@　@　@　@　@　@　@orderBy：　@ソート条件 <BR>
     * 　@　@　@　@　@　@　@conditions：　@null <BR>
     * 　@　@　@　@　@　@　@bindVars：　@検索条件データコンテナ<BR>
     * 　@　@　@　@　@　@　@pageSize：　@ページ内表示行数<BR>
     * 　@　@　@　@　@　@　@pageNumber：　@５－１）で取得した最終ページ<BR>
     * <BR>
     * 　@※該当データがない場合、例外をスローする。<BR>
     * <BR>
     * ６）　@返却値ListPageを返却する。<BR>
     * @@param l_intPageIndex - 要求ページ番号
     * @@param l_intPageSize - ページ内表示行数
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@param l_datSonarSendDateFrom - SONAR送信日（自）
     * @@param l_datSonarSendDateTo - SONAR送信日（至）
     * @@param l_strAccountOpenStatusDiv - 口座開設状況区分
     * <BR>
     * 　@0：　@DEFAULT（未開設）<BR>
     * 　@1：　@開設中<BR>
     * 　@2：　@エラー発生<BR>
     * 　@3：　@開設済<BR>
     *
     * @@return List
     */
    public static ListPage getExpAccountOpenVoucher(int l_intPageIndex, int l_intPageSize, String l_strQueryString, String[] l_queryContainer, String l_strSortCond, Date l_datSonarSendDateFrom, Date l_datSonarSendDateTo, String l_strAccountOpenStatusDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExpAccountOpenVoucher(int, int, String, String[], String, Date, Date, String)";
        log.entering(STR_METHOD_NAME);
        final String SONAR_DATE_FROM = "SONAR_DATE_FROM";
        final String SONAR_DATE_TO = "SONAR_DATE_TO";
        
        ArrayList l_lstBind = new ArrayList();
        for (int i = 0; i < l_queryContainer.length; i++)
        {
            l_lstBind.add(l_queryContainer[i]);
        }
        

        //パラメータ.口座開設状況区分 != nullの場合、検索条件に追加する。
        if (l_strAccountOpenStatusDiv != null)
        {
            if (WEB3AccountOpenStatusDivDef.OPEN_COMPLETE.equals(l_strAccountOpenStatusDiv))
            {
                //口座開設状況区分："開設済"
                l_strQueryString += " and account_open_date is not null ";
            }
            else if (WEB3AccountOpenStatusDivDef.DEFAULT.equals(l_strAccountOpenStatusDiv))
            {
                //口座開設状況区分："未開設"
                l_strQueryString += " and acc_open_request_number not in "
                    + "(select distinct exp_account_open.acc_open_request_number from acc_open_voucher_status b "
                    + "where exp_account_open.acc_open_request_number = b.acc_open_request_number and b.voucher_status > ? "
                    + " and b.institution_code = ? )";
                    
                l_lstBind.add(WEB3VoucherStatusDef.DEFAULT);
                l_lstBind.add(l_queryContainer[0]);
            }
            else if (WEB3AccountOpenStatusDivDef.OPENING.equals(l_strAccountOpenStatusDiv))
            {
                //口座開設状況区分："開設中"
                l_strQueryString += " and account_open_date is null "
                    + " and acc_open_request_number in "
                    + "(select distinct acc_open_request_number from acc_open_voucher_status "
                    + "where voucher_status not in (?, ?, ?) "
                    + "and institution_code = ? "
                    + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")";

                l_lstBind.add(WEB3VoucherStatusDef.DEFAULT);
                l_lstBind.add(WEB3VoucherStatusDef.SEND_ERROR);
                l_lstBind.add(WEB3VoucherStatusDef.RECEIVE_ERROR);
                l_lstBind.add(l_queryContainer[0]);
            }
            else
            {
                //口座開設状況区分："エラー発生"
                l_strQueryString += " and acc_open_request_number in "
                    + " (select distinct acc_open_request_number from acc_open_voucher_status "
                    + "where voucher_status in (?, ?) "
                    + "and institution_code = ? "
                    + SONAR_DATE_FROM + " " + SONAR_DATE_TO + ")";

                l_lstBind.add(WEB3VoucherStatusDef.SEND_ERROR);
                l_lstBind.add(WEB3VoucherStatusDef.RECEIVE_ERROR);
                l_lstBind.add(l_queryContainer[0]);
            }
        }

        // SONAR送信日(自)を検索条件に追加する。
        if (l_datSonarSendDateFrom != null)
        {
            if (l_strQueryString.indexOf(SONAR_DATE_FROM) > 0)
            {
                //SONAR送信日(自) != null かつ、口座開設状況区分 == 開設中 or エラー発生
                l_strQueryString = l_strQueryString.replaceAll(SONAR_DATE_FROM, " and send_timestamp >= ?");
            }
            else
            {
                //SONAR送信日(自) != null かつ、口座開設状況区分 == null or 未開設 or 開設済
                l_strQueryString += " and acc_open_request_number in (select acc_open_request_number from acc_open_voucher_status where send_timestamp >= ?)";
            }
            
            //SONAR送信日(自)を追加する。
            l_lstBind.add(l_datSonarSendDateFrom);
        }
        else
        {
            if (l_strQueryString.indexOf(SONAR_DATE_FROM) > 0)
            {
                //SONAR送信日(自) == null かつ、口座開設状況区分 == 開設中 or エラー発生
                //検索条件文字列
                l_strQueryString = l_strQueryString.replaceAll("SONAR_DATE_FROM", "");
            }
        }

        //SONAR送信日(至)を検索条件に追加する。
        //※パラメータ.SONAR送信日(至)の翌日未満を対象とする。
        if (l_datSonarSendDateTo != null)
        {
            if (l_strQueryString.indexOf(SONAR_DATE_TO) > 0)
            {
                //SONAR送信日(至) != null かつ、口座開設状況区分 == 開設中 or エラー発生
                l_strQueryString = l_strQueryString.replaceAll(SONAR_DATE_TO, " and send_timestamp < ? ");
            }
            else
            {
                //SONAR送信日(至) != null かつ、口座開設状況区分 == null or 未開設 or 開設済
                l_strQueryString += " and acc_open_request_number in (select acc_open_request_number from acc_open_voucher_status where send_timestamp < ?)";
            }
            
            //SONAR送信日(至)の翌日を追加する。
            Date l_datSonarSendNextDate = WEB3DateUtility.addDay(l_datSonarSendDateTo, 1);
            l_lstBind.add(l_datSonarSendNextDate);
        }
        else
        {
            if (l_strQueryString.indexOf(SONAR_DATE_TO) > 0)
            {
                //SONAR送信日(至) == null かつ、口座開設状況区分 == 開設中 or エラー発生
                //検索条件文字列
                l_strQueryString = l_strQueryString.replaceAll("SONAR_DATE_TO", "");
            }
        }

        
        //pageNumberは0から始まる為、リクエスト.要求ページ番号を変換する。
        int l_intPageNumber = l_intPageIndex - 1;
        
        try
        {
            log.debug("口座開設見込客行オブジェクトのListを取得ここから");

            QueryProcessor l_queryProcessor;
            l_queryProcessor = Processors.getDefaultProcessor();
            //口座開設見込客行オブジェクトのListを取得する。
            ListPage l_lisRow = l_queryProcessor.doFindAllQuery(
                ExpAccountOpenRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_lstBind.toArray(),
                l_intPageSize,
                l_intPageNumber);

            log.debug("List取得ここまで　@取得見込客行件数： " + l_lisRow.size());
            
            if (l_lisRow == null || l_lisRow.size() == 0)
            {
                if (l_lisRow.totalSize() != 0
                    && l_lisRow.totalPages() < l_intPageIndex)
                {
                    //最終ページ表示用要求ページ番号
                    int l_intLastPageIndex = l_lisRow.totalPages() - 1;

                    //リクエスト.要求ページ番号が最終ページを超えた場合、
                    //口座開設見込客行オブジェクトの最終ページのListを取得する。
                    l_lisRow = l_queryProcessor.doFindAllQuery(
                        ExpAccountOpenRow.TYPE,
                        l_strQueryString,
                        l_strSortCond,
                        null,
                        l_lstBind.toArray(),
                        l_intPageSize,
                        l_intLastPageIndex);
                    
                    log.debug("最終ページ見込客行件数： " + l_lisRow.size());
                    
                    if (l_lisRow == null || l_lisRow.size() == 0)
                    {
                        //見込客に該当データがない場合、例外をスローする。
                        throw new WEB3BusinessLayerException(
                            WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                            WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                            "見込客に該当データがない場合、例外をスローする。");
                    }
                }
                else
                {

                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                    WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                    "見込客に該当データがない場合、例外をスローする。");
                }
            }

            //返却値PageListを返却する。
            log.exiting(STR_METHOD_NAME);
            return l_lisRow; 

        }
        catch (DataFindException l_ex)
        {
            log.debug("該当データがない場合、例外をスローする。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                "該当データがない場合、例外をスローする。");
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccOpenExpAccountOpen.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }


    /**
     * @@return Object
     * @@roseuid 41B45E6A01A5
     */
    public Object getDataSourceObject()
    {
        return this.expAccountOpenParams;
    }

    /**
     * 口座開設見込客オブジェクトを初期化する。<BR>
     * <BR>
     * １）　@口座開設伝票作成ステータスプロパティのセット<BR>
     * 　@口座開設伝票作成ステータス.get口座開設伝票作成ステータス()の戻り値を<BR>
     * 生成したオブジェクトのプロパティにセットする。<BR>
     * <BR>
     * 　@[get口座開設伝票作成ステータス()に指定する引数]<BR>
     * 　@証券会社コード：　@口座開設見込行.証券会社コード<BR>
     * 　@識別コード：　@口座開設見込行.識別コード<BR>
     * <BR>
     * ２）　@口座開設見込客行プロパティのセット<BR>
     * 　@口座開設見込行を生成したオブジェクトのプロパティにセットし、返却する。<BR>
     * <BR>
     * ※ 口座開設見込客ParamsクラスはDDLより自動生成する。<BR>
     * @@param l_expAccountOpenParams - 口座開設見込客行<BR>
     * <BR>
     * ※ 口座開設見込客ParamsクラスはDDLより自動生成する。<BR>
     *
     */
    private void init(ExpAccountOpenParams l_expAccountOpenParams)
        throws WEB3BaseException
    {
        this.expAccountOpenParams = l_expAccountOpenParams;
        this.accOpenVoucherCreatedStatuses = WEB3AccOpenVoucherCreatedStatus.getAccOpenVoucherCreatedStatus(
            l_expAccountOpenParams.getInstitutionCode(),        //証券会社コード
            l_expAccountOpenParams.getAccOpenRequestNumber()    //識別コード
            );
    }

    /**
     * 各社カスタマイズの判断
     */
    private boolean isCustomized(String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountDiv, String l_strValidateType, String l_strCheckItem) throws WEB3BaseException
    {
        try
        {
            new WEB3AccOpenItemMaster(l_strInstitutionCode, l_strBranchCode, l_strAccountDiv,
                l_strValidateType, l_strCheckItem);
        }
        catch (NotFoundException l_ex)
        {
            try
            {
                new WEB3AccOpenItemMaster(l_strInstitutionCode, "000", l_strAccountDiv,
                    l_strValidateType, l_strCheckItem);
            }
            catch (NotFoundException l_nfe)
            {
                log.debug("各社カスタマイズデータがないする");
                return false;
            }
        }

        log.debug("各社カスタマイズデータがする");
        return true;
    }

    /**
     * カスタマイズ値の獲得
     */
    private String[] getCustomizedValues(String l_strInstitutionCode, String l_strBranchCode,
        String l_strAccountDiv, String l_strCheckItem) throws WEB3BaseException
    {
        try
        {
            WEB3AccOpenItemAttribute l_itemAttribute = new WEB3AccOpenItemAttribute(
                l_strInstitutionCode, l_strBranchCode, l_strAccountDiv, l_strCheckItem);
            
            Object l_objCheckItemValue = expAccountOpenParams.getColumn(l_strCheckItem);

            if (l_objCheckItemValue != null)
            {
                Double l_rangeFrom = l_itemAttribute.getRangeFrom(l_objCheckItemValue.toString());
                Double l_rangeTo =  l_itemAttribute.getRangeTo(l_objCheckItemValue.toString());
                
                return new String[] { l_rangeFrom == null ? null : WEB3StringTypeUtility.formatNumber(l_rangeFrom.doubleValue()),
                    l_rangeTo == null ? null : WEB3StringTypeUtility.formatNumber(l_rangeTo.doubleValue()) };
            }
        }
        catch (NotFoundException l_ex)
        {
            if (l_strBranchCode.equals("000")) {
                return new String[] { null, null };
            } else {
                return getCustomizedValues(l_strInstitutionCode, "000",
                        l_strAccountDiv, l_strCheckItem);
            }
        }
        
        return new String[] { null, null };
    }

    /**
     * 非カスタマイズ値の獲得
     */
    private String[] getFinancialValues(String l_strExperienceDivEquity)
    {
        String l_strFrom = null;
        String l_strTo = null;
        
        if (WEB3MoneyAmountDivDef.DEFAULT.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.NO_ANSWERD;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.NO_ANSWERD;
        }
        else if (WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.NO_ANSWERD;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.THREE_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.THREE_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.FIVE_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.FIVE_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.TEN_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.TEN_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.THIRTY_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.THIRTY_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.FIFTY_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.FIFTY_MILLION_YUAN;
            l_strTo = WEB3AccOpenAnnualIncomeDivValueDef.ONE_HUNDRED_MILLION_YUAN;
        }
        else if (WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION.equals(l_strExperienceDivEquity))
        {
            l_strFrom = WEB3AccOpenAnnualIncomeDivValueDef.ONE_HUNDRED_MILLION_YUAN;
            l_strTo = null;
        }
        
        return new String[]{l_strFrom, l_strTo};
    }
    
    /**
     * WEB3デフォルトチェックのチェック対象項目の有効値チェック 
     */
    private boolean isValidCodeValue(String l_strCheckItem, Object l_checkItemValue)
    throws WEB3BaseException
    {
        ValidCodeValueSets l_validcodeValueSets = new ValidCodeValueSets();
        Iterator l_checkItems = l_validcodeValueSets.validCodeValueMap.keySet().iterator();
        String l_strCheckItemsHolder = null;

        if (l_checkItemValue == null)
        {
            return true;
        }
        
        while (l_checkItems.hasNext())
        {
            l_strCheckItemsHolder = (String) l_checkItems.next();
            if (l_strCheckItemsHolder.equals(l_strCheckItem))
            {
                if (!(l_checkItemValue instanceof String))
                {
                    //不正確の有効値,応String類型
                    return false;
                }
                else
                {
                    String[] l_strValidCodeValues = (String[]) l_validcodeValueSets.validCodeValueMap.get(l_strCheckItemsHolder);
                    int l_intLength = l_strValidCodeValues.length;
                    
                    for (int i = 0; i < l_intLength; i++)
                    {
                        if (l_strValidCodeValues[i].equals(l_checkItemValue))
                        {
                            return true;
                        }
                    }
                    
                    //不正確の有効値
                    return false;
                }
            }
        }
        
        return false;
    }
    
    /**
     * チェック対象項目(有効ｺｰﾄﾞﾁｪｯｸ)<BR>
     */
    private class ValidCodeValueSets
    {
        public Map validCodeValueMap;
        
        public ValidCodeValueSets() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "ValidCodeValueSets()";
            log.entering(STR_METHOD_NAME);

            validCodeValueMap = new HashMap();
            validCodeValueMap.put("sex", new String[] { WEB3SexDef.CORPORATE, WEB3SexDef.MALE,
                WEB3SexDef.UNKNOW, WEB3SexDef.WOMAN });

            List l_lisResults = null;
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                l_lisResults = l_queryProcessor.doFindAllQuery(EraRow.TYPE);
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

            String[] l_strEras = null;
            if (l_lisResults.isEmpty())
            {
                l_strEras = new String[]{};
            }

            Set l_resultSet = new HashSet();
            for (int i = 0; i < l_lisResults.size(); i++)
            {
                EraRow l_eraRow = (EraRow)l_lisResults.get(i);
                l_resultSet.add(l_eraRow.getJapaneseEraDiv() + "");
            }

            if (l_resultSet.size() != 0)
            {
                l_strEras = new String[l_resultSet.size()];
                l_resultSet.toArray(l_strEras);
            }

            validCodeValueMap.put("era_born", l_strEras);
            validCodeValueMap.put("occupation_div", new String[] {
                WEB3OccupationDivDef.AGRICULTURE_FORESTRY_FISHERY, WEB3OccupationDivDef.DOCTOR,
                WEB3OccupationDivDef.INDEPENDENT_BUSINESS,
                WEB3OccupationDivDef.JOBLESSNESS_HOUSEWIFE,
                WEB3OccupationDivDef.JOBLESSNESS_MANDATORY_RETIREMENT,
                WEB3OccupationDivDef.JOBLESSNESS_OTHER, WEB3OccupationDivDef.JOBLESSNESS_STUDENT,
                WEB3OccupationDivDef.LAWYER_ACCOUNTANT,
                WEB3OccupationDivDef.LISTED_COMPANY_MANAGER,
                WEB3OccupationDivDef.LISTED_COMPANY_OFFICER,
                WEB3OccupationDivDef.LISTED_COMPANY_STAFF, WEB3OccupationDivDef.OTHER,
                WEB3OccupationDivDef.OTHER_SALARY_EARNER });
            validCodeValueMap.put("family_relationship", new String[] {
                WEB3FamilyRelationshipDef.CHILD, WEB3FamilyRelationshipDef.IDENTICAL_PERSON,
                WEB3FamilyRelationshipDef.MATE, WEB3FamilyRelationshipDef.OTHER });
            validCodeValueMap.put("householder_occupation_div", new String[] {
                WEB3OccupationDivDef.AGRICULTURE_FORESTRY_FISHERY, WEB3OccupationDivDef.DOCTOR,
                WEB3OccupationDivDef.INDEPENDENT_BUSINESS,
                WEB3OccupationDivDef.JOBLESSNESS_HOUSEWIFE,
                WEB3OccupationDivDef.JOBLESSNESS_MANDATORY_RETIREMENT,
                WEB3OccupationDivDef.JOBLESSNESS_OTHER, WEB3OccupationDivDef.JOBLESSNESS_STUDENT,
                WEB3OccupationDivDef.LAWYER_ACCOUNTANT,
                WEB3OccupationDivDef.LISTED_COMPANY_MANAGER,
                WEB3OccupationDivDef.LISTED_COMPANY_OFFICER,
                WEB3OccupationDivDef.LISTED_COMPANY_STAFF, WEB3OccupationDivDef.OTHER,
                WEB3OccupationDivDef.OTHER_SALARY_EARNER });
            validCodeValueMap.put("resident", new String[] { WEB3ResidentDef.NON_RESIDENT,
                WEB3ResidentDef.RESIDENT, WEB3ResidentDef.SPE_NON_RESIDENT });
            validCodeValueMap.put("transfer_div", new String[] { WEB3TransferDivDef.BANK_TRANSFER,
                WEB3TransferDivDef.POSTAL_TRANSFER });
            validCodeValueMap.put("fin_save_div", new String[] {
                WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE, WEB3FinSaveDivDef.GENERAL_FIN_SAVE,
                WEB3FinSaveDivDef.OTHER, WEB3FinSaveDivDef.SAVING_FIN_SAVE });
            validCodeValueMap.put("trans_commission", new String[] { WEB3TransCommissionDef.OTHER,
                WEB3TransCommissionDef.OTHER_TRADE, WEB3TransCommissionDef.SAME_TRADE,
                WEB3TransCommissionDef.SAME_TRADE_STORE_CHARGE,
                WEB3TransCommissionDef.SAME_TRADE_STORE_FREE });
            validCodeValueMap.put("experience_div_equity", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_margin", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_bond", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_wt", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_fund_sk", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_fund_bd", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_fo", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_f_equity", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("experience_div_etc", new String[] {
                WEB3ExperienceDivDef.LESS_THAN_FIVE_YEARS, WEB3ExperienceDivDef.LESS_THAN_ONE_YEAR,
                WEB3ExperienceDivDef.MORE_THAN_FIVE_YEARS, WEB3ExperienceDivDef.TWO_TO_THREE_YEARS,
                WEB3ExperienceDivDef.WITHOUT });
            validCodeValueMap.put("invest_purpose_div", new String[] {
                WEB3InvestPurposeDivDef.LONG_TERM_PURPOSE,
                WEB3InvestPurposeDivDef.MEDIUM_TERM_PURPOSE,
                WEB3InvestPurposeDivDef.SHORT_TERM_PURPOSE });
            validCodeValueMap.put("appli_motivat_div", new String[] {
                WEB3AppliMotivatDivDef.HOMEPAGE, WEB3AppliMotivatDivDef.MAGAZINE,
                WEB3AppliMotivatDivDef.NEWSPAPER, WEB3AppliMotivatDivDef.OTHER,
                WEB3AppliMotivatDivDef.SEMINAR, WEB3AppliMotivatDivDef.TV });
            validCodeValueMap.put("annual_income_div", new String[] {
                WEB3MoneyAmountDivDef.DEFAULT,
                WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION,
                WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION,
                WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION,
                WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION,
                WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION });
            validCodeValueMap.put("asset_value_div", new String[] { WEB3MoneyAmountDivDef.DEFAULT,
                WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION,
                WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION,
                WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION,
                WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION,
                WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION });
            validCodeValueMap.put("fund_budget_amount_div", new String[] {
                WEB3MoneyAmountDivDef.DEFAULT,
                WEB3MoneyAmountDivDef.FIFTY_MILLION_TO_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.FIVE_MILLION_TO_TEN_MILLION,
                WEB3MoneyAmountDivDef.LESS_THAN_THREE_MILLION,
                WEB3MoneyAmountDivDef.MORE_THAN_ONE_HUNDRED_MILLION,
                WEB3MoneyAmountDivDef.TEN_MILLION_TO_THIRTY_MILLION,
                WEB3MoneyAmountDivDef.THIRTY_MILLION_TO_FIFTY_MILLION,
                WEB3MoneyAmountDivDef.THREE_MILLION_TO_FIVE_MILLION });
            validCodeValueMap.put("fund_budget_div", new String[] {
                WEB3FundBudgetDivDef.BORROWED_FUNDS, WEB3FundBudgetDivDef.OTHER,
                WEB3FundBudgetDivDef.RETIREMENT_ALLOWANCE, WEB3FundBudgetDivDef.SURPLUS_FUNDS });
            validCodeValueMap.put("id_confirm_doc_div", new String[] {
                WEB3IdConfirmDocDivDef.ALIEN_REGISTRATION_CERTIFICATE,
                WEB3IdConfirmDocDivDef.DRIVING_LICENSE,
                WEB3IdConfirmDocDivDef.HEALTH_INSURANCE_CARD, WEB3IdConfirmDocDivDef.OTHER,
                WEB3IdConfirmDocDivDef.RESIDENT_CARD, WEB3IdConfirmDocDivDef.SEAL_CERTIFICATE });
            validCodeValueMap.put("special_acc", new String[] { WEB3TaxTypeDivDef.NORMAL,
                WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE, WEB3TaxTypeDivDef.SPECIAL_SOURCE });
            validCodeValueMap.put("special_acc_margin", new String[] { WEB3TaxTypeDivDef.NORMAL,
                WEB3TaxTypeDivDef.SPECIAL_NO_SOURCE, WEB3TaxTypeDivDef.SPECIAL_SOURCE });
            
            //ＤＢレイアウト No.026
            //（上場外株）預金区分
            //1：普通預金　@2：当座預金　@3：その他　@4：貯蓄預金
            validCodeValueMap.put("stk_fin_save_div", new String[] {
                    WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE, WEB3FinSaveDivDef.GENERAL_FIN_SAVE,
                    WEB3FinSaveDivDef.OTHER, WEB3FinSaveDivDef.SAVING_FIN_SAVE });   
            //ＤＢレイアウト No.029
            validCodeValueMap.put("foreign_save_div", new String[] {
                    WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE, WEB3FinSaveDivDef.GENERAL_FIN_SAVE});  

            validCodeValueMap.put("print_flag", new String[] {
                WEB3PrintFlagDef.DEFAULT,
                WEB3PrintFlagDef.ENABLE_PRINT,
                WEB3PrintFlagDef.PRINT_COMPLETE});
        }
    }
    
    /**
     * getDefault項目マスタ()に指定する引数<BR>
     */
    private class ItemCheckMethod
    {
        /**
         * 列名<BR>
         */
        public String columnName;
        
        /**
         * 必須項目フラグ<BR>
         * 【Null】項目であればBooleanEnum.FALSE<BR>
         * 【NotNull】項目であれば、BooleanEnum.TRUEを指定する。<BR>
         */
        public BooleanEnum necessaryItemFlag;
        
        /**
         * 項目最大長<BR>
         * 【SIZE】<BR>
         */
        public int size;
        
        /**
         * 【項目チェック方式（WEB3デフォルト）】 <BR>
         */
        public String itemCheckMode;
        
        /**
         * カスタマイズ <BR>
         */
        public boolean customizing;
    }
    
    /**
     * チェック対象項目<BR>
     */
    private class MethodSets
    {
        public Map methodMap;
        
        /**
         * チェック対象項目<BR>
         * コンストラクタ。<BR>
         */
        public MethodSets()
        {
            methodMap = new HashMap();
            ItemCheckMethod l_itemCheckMethod1 = new ItemCheckMethod();
            l_itemCheckMethod1.columnName = "部店コード";
            l_itemCheckMethod1.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod1.size = 3;
            l_itemCheckMethod1.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod1.customizing = true;
            methodMap.put("branch_code", l_itemCheckMethod1);
            ItemCheckMethod l_itemCheckMethod2 = new ItemCheckMethod();
            l_itemCheckMethod2.columnName = "口座コード";
            l_itemCheckMethod2.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod2.size = 7;
            l_itemCheckMethod2.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod2.customizing = true;
            methodMap.put("account_code", l_itemCheckMethod2);
            ItemCheckMethod l_itemCheckMethod3 = new ItemCheckMethod();
            l_itemCheckMethod3.columnName = "扱者コード（SONAR）";
            l_itemCheckMethod3.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod3.size = 5;
            l_itemCheckMethod3.itemCheckMode = null;
            l_itemCheckMethod3.customizing = true;
            methodMap.put("sonar_trader_code", l_itemCheckMethod3);
            ItemCheckMethod l_itemCheckMethod4 = new ItemCheckMethod();
            l_itemCheckMethod4.columnName = "既存口座フラグ";
            l_itemCheckMethod4.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod4.size = 1;
            l_itemCheckMethod4.itemCheckMode = null;
            l_itemCheckMethod4.customizing = true;
            methodMap.put("ex_account_flag", l_itemCheckMethod4);
            ItemCheckMethod l_itemCheckMethod5 = new ItemCheckMethod();
            l_itemCheckMethod5.columnName = "既存口座部店名";
            l_itemCheckMethod5.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod5.size = 32;
            l_itemCheckMethod5.itemCheckMode = null;
            l_itemCheckMethod5.customizing = true;
            methodMap.put("ex_branch_name", l_itemCheckMethod5);
            ItemCheckMethod l_itemCheckMethod6 = new ItemCheckMethod();
            l_itemCheckMethod6.columnName = "既存口座コード";
            l_itemCheckMethod6.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod6.size = 7;
            l_itemCheckMethod6.itemCheckMode = null;
            l_itemCheckMethod6.customizing = true;
            methodMap.put("ex_account_code", l_itemCheckMethod6);
            ItemCheckMethod l_itemCheckMethod7 = new ItemCheckMethod();
            l_itemCheckMethod7.columnName = "口座区分";
            l_itemCheckMethod7.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod7.size = 1;
            l_itemCheckMethod7.itemCheckMode = null;
            l_itemCheckMethod7.customizing = true;
            methodMap.put("account_div", l_itemCheckMethod7);
            ItemCheckMethod l_itemCheckMethod8 = new ItemCheckMethod();
            l_itemCheckMethod8.columnName = "入力区分";
            l_itemCheckMethod8.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod8.size = 1;
            l_itemCheckMethod8.itemCheckMode = null;
            l_itemCheckMethod8.customizing = true;
            methodMap.put("order_div", l_itemCheckMethod8);
            ItemCheckMethod l_itemCheckMethod9 = new ItemCheckMethod();
            l_itemCheckMethod9.columnName = "資料請求日時";
            l_itemCheckMethod9.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod9.size = 0;
            l_itemCheckMethod9.itemCheckMode = null;
            l_itemCheckMethod9.customizing = true;
            methodMap.put("infomation_claim_datetime", l_itemCheckMethod9);
            ItemCheckMethod l_itemCheckMethod10 = new ItemCheckMethod();
            l_itemCheckMethod10.columnName = "口座登録日";
            l_itemCheckMethod10.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod10.size = 0;
            l_itemCheckMethod10.itemCheckMode = null;
            l_itemCheckMethod10.customizing = true;
            methodMap.put("account_open_date", l_itemCheckMethod10);
            ItemCheckMethod l_itemCheckMethod11 = new ItemCheckMethod();
            l_itemCheckMethod11.columnName = "初期パスワード";
            l_itemCheckMethod11.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod11.size = 32;
            l_itemCheckMethod11.itemCheckMode = null;
            l_itemCheckMethod11.customizing = true;
            methodMap.put("initial_password", l_itemCheckMethod11);
            ItemCheckMethod l_itemCheckMethod12 = new ItemCheckMethod();
            l_itemCheckMethod12.columnName = "顧客姓（漢字）";
            l_itemCheckMethod12.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod12.size = 40;
            l_itemCheckMethod12.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod12.customizing = true;
            methodMap.put("family_name", l_itemCheckMethod12);
            ItemCheckMethod l_itemCheckMethod13 = new ItemCheckMethod();
            l_itemCheckMethod13.columnName = "顧客名（漢字）";
            l_itemCheckMethod13.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod13.size = 40;
            l_itemCheckMethod13.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod13.customizing = true;
            methodMap.put("given_name", l_itemCheckMethod13);
            ItemCheckMethod l_itemCheckMethod14 = new ItemCheckMethod();
            l_itemCheckMethod14.columnName = "顧客姓（カナ）";
            l_itemCheckMethod14.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod14.size = 40;
            l_itemCheckMethod14.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod14.customizing = true;
            methodMap.put("family_name_alt1", l_itemCheckMethod14);
            ItemCheckMethod l_itemCheckMethod15 = new ItemCheckMethod();
            l_itemCheckMethod15.columnName = "顧客名（カナ）";
            l_itemCheckMethod15.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod15.size = 40;
            l_itemCheckMethod15.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod15.customizing = true;
            methodMap.put("given_name_alt1", l_itemCheckMethod15);
            ItemCheckMethod l_itemCheckMethod16 = new ItemCheckMethod();
            l_itemCheckMethod16.columnName = "性別";
            l_itemCheckMethod16.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod16.size = 1;
            l_itemCheckMethod16.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod16.customizing = true;
            methodMap.put("sex", l_itemCheckMethod16);
            ItemCheckMethod l_itemCheckMethod17 = new ItemCheckMethod();
            l_itemCheckMethod17.columnName = "生年月日年号";
            l_itemCheckMethod17.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod17.size = 1;
            l_itemCheckMethod17.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod17.customizing = true;
            methodMap.put("era_born", l_itemCheckMethod17);
            ItemCheckMethod l_itemCheckMethod18 = new ItemCheckMethod();
            l_itemCheckMethod18.columnName = "emailアドレス";
            l_itemCheckMethod18.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod18.size = 100;
            l_itemCheckMethod18.itemCheckMode = WEB3ItemCheckModeDef.MAIL_ADDRESS;
            l_itemCheckMethod18.customizing = true;
            methodMap.put("email_address", l_itemCheckMethod18);
            ItemCheckMethod l_itemCheckMethod19 = new ItemCheckMethod();
            l_itemCheckMethod19.columnName = "emailアドレス１";
            l_itemCheckMethod19.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod19.size = 100;
            l_itemCheckMethod19.itemCheckMode = WEB3ItemCheckModeDef.MAIL_ADDRESS;
            l_itemCheckMethod19.customizing = true;
            methodMap.put("email_address_alt1", l_itemCheckMethod19);
            ItemCheckMethod l_itemCheckMethod20 = new ItemCheckMethod();
            l_itemCheckMethod20.columnName = "郵便番号";
            l_itemCheckMethod20.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod20.size = 7;
            l_itemCheckMethod20.itemCheckMode = WEB3ItemCheckModeDef.ZIP_CODE;
            l_itemCheckMethod20.customizing = true;
            methodMap.put("zip_code", l_itemCheckMethod20);
            ItemCheckMethod l_itemCheckMethod21 = new ItemCheckMethod();
            l_itemCheckMethod21.columnName = "住所１";
            l_itemCheckMethod21.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod21.size = 34;
            l_itemCheckMethod21.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod21.customizing = true;
            methodMap.put("address_line1", l_itemCheckMethod21);
            ItemCheckMethod l_itemCheckMethod22 = new ItemCheckMethod();
            l_itemCheckMethod22.columnName = "住所２";
            l_itemCheckMethod22.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod22.size = 34;
            l_itemCheckMethod22.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod22.customizing = true;
            methodMap.put("address_line2", l_itemCheckMethod22);
            ItemCheckMethod l_itemCheckMethod23 = new ItemCheckMethod();
            l_itemCheckMethod23.columnName = "住所３";
            l_itemCheckMethod23.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod23.size = 34;
            l_itemCheckMethod23.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod23.customizing = true;
            methodMap.put("address_line3", l_itemCheckMethod23);
            ItemCheckMethod l_itemCheckMethod24 = new ItemCheckMethod();
            l_itemCheckMethod24.columnName = "住所１（カナ）";
            l_itemCheckMethod24.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod24.size = 60;
            l_itemCheckMethod24.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod24.customizing = true;
            methodMap.put("address_line1_kana", l_itemCheckMethod24);
            ItemCheckMethod l_itemCheckMethod25 = new ItemCheckMethod();
            l_itemCheckMethod25.columnName = "住所２（カナ）";
            l_itemCheckMethod25.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod25.size = 60;
            l_itemCheckMethod25.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod25.customizing = true;
            methodMap.put("address_line2_kana", l_itemCheckMethod25);
            ItemCheckMethod l_itemCheckMethod26 = new ItemCheckMethod();
            l_itemCheckMethod26.columnName = "住所３（カナ）";
            l_itemCheckMethod26.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod26.size = 60;
            l_itemCheckMethod26.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod26.customizing = true;
            methodMap.put("address_line3_kana", l_itemCheckMethod26);
            ItemCheckMethod l_itemCheckMethod27 = new ItemCheckMethod();
            l_itemCheckMethod27.columnName = "電話番号";
            l_itemCheckMethod27.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod27.size = 16;
            l_itemCheckMethod27.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod27.customizing = true;
            methodMap.put("telephone", l_itemCheckMethod27);
            ItemCheckMethod l_itemCheckMethod28 = new ItemCheckMethod();
            l_itemCheckMethod28.columnName = "連絡先電話番号（携帯）";
            l_itemCheckMethod28.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod28.size = 16;
            l_itemCheckMethod28.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod28.customizing = true;
            methodMap.put("mobile", l_itemCheckMethod28);
            ItemCheckMethod l_itemCheckMethod29 = new ItemCheckMethod();
            l_itemCheckMethod29.columnName = "ＦＡＸ番号";
            l_itemCheckMethod29.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod29.size = 16;
            l_itemCheckMethod29.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod29.customizing = true;
            methodMap.put("fax", l_itemCheckMethod29);
            ItemCheckMethod l_itemCheckMethod30 = new ItemCheckMethod();
            l_itemCheckMethod30.columnName = "職業区分";
            l_itemCheckMethod30.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod30.size = 2;
            l_itemCheckMethod30.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod30.customizing = true;
            methodMap.put("occupation_div", l_itemCheckMethod30);
            ItemCheckMethod l_itemCheckMethod31 = new ItemCheckMethod();
            l_itemCheckMethod31.columnName = "勤務先名称";
            l_itemCheckMethod31.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod31.size = 50;
            l_itemCheckMethod31.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod31.customizing = true;
            methodMap.put("office", l_itemCheckMethod31);
            ItemCheckMethod l_itemCheckMethod32 = new ItemCheckMethod();
            l_itemCheckMethod32.columnName = "勤務先郵便番号";
            l_itemCheckMethod32.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod32.size = 7;
            l_itemCheckMethod32.itemCheckMode = WEB3ItemCheckModeDef.ZIP_CODE;
            l_itemCheckMethod32.customizing = true;
            methodMap.put("office_zip_code", l_itemCheckMethod32);
            ItemCheckMethod l_itemCheckMethod33 = new ItemCheckMethod();
            l_itemCheckMethod33.columnName = "勤務先住所";
            l_itemCheckMethod33.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod33.size = 100;
            l_itemCheckMethod33.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod33.customizing = true;
            methodMap.put("office_address", l_itemCheckMethod33);
            ItemCheckMethod l_itemCheckMethod34 = new ItemCheckMethod();
            l_itemCheckMethod34.columnName = "勤務先電話番号";
            l_itemCheckMethod34.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod34.size = 16;
            l_itemCheckMethod34.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod34.customizing = true;
            methodMap.put("office_telephone", l_itemCheckMethod34);
            ItemCheckMethod l_itemCheckMethod35 = new ItemCheckMethod();
            l_itemCheckMethod35.columnName = "勤務先FAX番号";
            l_itemCheckMethod35.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod35.size = 16;
            l_itemCheckMethod35.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod35.customizing = true;
            methodMap.put("office_fax", l_itemCheckMethod35);
            ItemCheckMethod l_itemCheckMethod36 = new ItemCheckMethod();
            l_itemCheckMethod36.columnName = "所属部署";
            l_itemCheckMethod36.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod36.size = 50;
            l_itemCheckMethod36.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod36.customizing = true;
            methodMap.put("department", l_itemCheckMethod36);
            ItemCheckMethod l_itemCheckMethod37 = new ItemCheckMethod();
            l_itemCheckMethod37.columnName = "役職";
            l_itemCheckMethod37.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod37.size = 36;
            l_itemCheckMethod37.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod37.customizing = true;
            methodMap.put("post", l_itemCheckMethod37);
            ItemCheckMethod l_itemCheckMethod38 = new ItemCheckMethod();
            l_itemCheckMethod38.columnName = "連絡先住所";
            l_itemCheckMethod38.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod38.size = 100;
            l_itemCheckMethod38.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod38.customizing = true;
            methodMap.put("contact_address", l_itemCheckMethod38);
            ItemCheckMethod l_itemCheckMethod39 = new ItemCheckMethod();
            l_itemCheckMethod39.columnName = "連絡先電話番号";
            l_itemCheckMethod39.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod39.size = 16;
            l_itemCheckMethod39.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod39.customizing = true;
            methodMap.put("contact_telephone", l_itemCheckMethod39);
            ItemCheckMethod l_itemCheckMethod40 = new ItemCheckMethod();
            l_itemCheckMethod40.columnName = "続柄区分";
            l_itemCheckMethod40.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod40.size = 1;
            l_itemCheckMethod40.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod40.customizing = true;
            methodMap.put("family_relationship", l_itemCheckMethod40);
            ItemCheckMethod l_itemCheckMethod41 = new ItemCheckMethod();
            l_itemCheckMethod41.columnName = "続柄区分（その他）";
            l_itemCheckMethod41.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod41.size = 40;
            l_itemCheckMethod41.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod41.customizing = true;
            methodMap.put("family_relationship_etc", l_itemCheckMethod41);
            ItemCheckMethod l_itemCheckMethod42 = new ItemCheckMethod();
            l_itemCheckMethod42.columnName = "世帯主名（漢字）";
            l_itemCheckMethod42.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod42.size = 40;
            l_itemCheckMethod42.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod42.customizing = true;
            methodMap.put("householder", l_itemCheckMethod42);
            ItemCheckMethod l_itemCheckMethod43 = new ItemCheckMethod();
            l_itemCheckMethod43.columnName = "世帯主名（カナ）";
            l_itemCheckMethod43.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod43.size = 40;
            l_itemCheckMethod43.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod43.customizing = true;
            methodMap.put("householder_kana", l_itemCheckMethod43);
            ItemCheckMethod l_itemCheckMethod44 = new ItemCheckMethod();
            l_itemCheckMethod44.columnName = "世帯主職業区分";
            l_itemCheckMethod44.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod44.size = 2;
            l_itemCheckMethod44.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod44.customizing = true;
            methodMap.put("householder_occupation_div", l_itemCheckMethod44);
            ItemCheckMethod l_itemCheckMethod45 = new ItemCheckMethod();
            l_itemCheckMethod45.columnName = "世帯主勤務先";
            l_itemCheckMethod45.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod45.size = 50;
            l_itemCheckMethod45.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod45.customizing = true;
            methodMap.put("householder_office", l_itemCheckMethod45);
            ItemCheckMethod l_itemCheckMethod46 = new ItemCheckMethod();
            l_itemCheckMethod46.columnName = "世帯主勤務先住所";
            l_itemCheckMethod46.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod46.size = 100;
            l_itemCheckMethod46.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod46.customizing = true;
            methodMap.put("householder_office_address", l_itemCheckMethod46);
            ItemCheckMethod l_itemCheckMethod47 = new ItemCheckMethod();
            l_itemCheckMethod47.columnName = "世帯主所属部署";
            l_itemCheckMethod47.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod47.size = 50;
            l_itemCheckMethod47.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod47.customizing = true;
            methodMap.put("householder_department", l_itemCheckMethod47);
            ItemCheckMethod l_itemCheckMethod48 = new ItemCheckMethod();
            l_itemCheckMethod48.columnName = "世帯主勤務先電話番号";
            l_itemCheckMethod48.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod48.size = 16;
            l_itemCheckMethod48.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod48.customizing = true;
            methodMap.put("householder_office_tel", l_itemCheckMethod48);
            ItemCheckMethod l_itemCheckMethod49 = new ItemCheckMethod();
            l_itemCheckMethod49.columnName = "世帯主勤務先FAX番号";
            l_itemCheckMethod49.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod49.size = 16;
            l_itemCheckMethod49.itemCheckMode = WEB3ItemCheckModeDef.TELEPHONE_MOBILE_NUMBER;
            l_itemCheckMethod49.customizing = true;
            methodMap.put("householder_office_fax", l_itemCheckMethod49);
            ItemCheckMethod l_itemCheckMethod50 = new ItemCheckMethod();
            l_itemCheckMethod50.columnName = "世帯主役職";
            l_itemCheckMethod50.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod50.size = 36;
            l_itemCheckMethod50.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod50.customizing = true;
            methodMap.put("householder_post", l_itemCheckMethod50);
            ItemCheckMethod l_itemCheckMethod51 = new ItemCheckMethod();
            l_itemCheckMethod51.columnName = "居住／非居住区分";
            l_itemCheckMethod51.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod51.size = 1;
            l_itemCheckMethod51.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod51.customizing = true;
            methodMap.put("resident", l_itemCheckMethod51);
            ItemCheckMethod l_itemCheckMethod52 = new ItemCheckMethod();
            l_itemCheckMethod52.columnName = "振替区分";
            l_itemCheckMethod52.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod52.size = 1;
            l_itemCheckMethod52.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod52.customizing = true;
            methodMap.put("transfer_div", l_itemCheckMethod52);
            ItemCheckMethod l_itemCheckMethod53 = new ItemCheckMethod();
            l_itemCheckMethod53.columnName = "銀行コード";
            l_itemCheckMethod53.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod53.size = 4;
            l_itemCheckMethod53.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod53.customizing = true;
            methodMap.put("fin_institution_code", l_itemCheckMethod53);
            ItemCheckMethod l_itemCheckMethod54 = new ItemCheckMethod();
            l_itemCheckMethod54.columnName = "銀行名";
            l_itemCheckMethod54.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod54.size = 32;
            l_itemCheckMethod54.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod54.customizing = true;
            methodMap.put("fin_institution_name", l_itemCheckMethod54);
            ItemCheckMethod l_itemCheckMethod55 = new ItemCheckMethod();
            l_itemCheckMethod55.columnName = "支店コード";
            l_itemCheckMethod55.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod55.size = 5;
            l_itemCheckMethod55.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod55.customizing = true;
            methodMap.put("fin_branch_code", l_itemCheckMethod55);
            ItemCheckMethod l_itemCheckMethod56 = new ItemCheckMethod();
            l_itemCheckMethod56.columnName = "支店名";
            l_itemCheckMethod56.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod56.size = 32;
            l_itemCheckMethod56.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod56.customizing = true;
            methodMap.put("fin_branch_name", l_itemCheckMethod56);
            ItemCheckMethod l_itemCheckMethod57 = new ItemCheckMethod();
            l_itemCheckMethod57.columnName = "預金区分";
            l_itemCheckMethod57.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod57.size = 1;
            l_itemCheckMethod57.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod57.customizing = true;
            methodMap.put("fin_save_div", l_itemCheckMethod57);
            ItemCheckMethod l_itemCheckMethod58 = new ItemCheckMethod();
            l_itemCheckMethod58.columnName = "口座番号";
            l_itemCheckMethod58.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod58.size = 8;
            l_itemCheckMethod58.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod58.customizing = true;
            methodMap.put("fin_account_no", l_itemCheckMethod58);
            ItemCheckMethod l_itemCheckMethod59 = new ItemCheckMethod();
            l_itemCheckMethod59.columnName = "通帳記号";
            l_itemCheckMethod59.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod59.size = 5;
            l_itemCheckMethod59.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod59.customizing = true;
            methodMap.put("postal_save_code", l_itemCheckMethod59);
            ItemCheckMethod l_itemCheckMethod60 = new ItemCheckMethod();
            l_itemCheckMethod60.columnName = "通帳番号";
            l_itemCheckMethod60.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod60.size = 8;
            l_itemCheckMethod60.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod60.customizing = true;
            methodMap.put("postal_save_no", l_itemCheckMethod60);
            ItemCheckMethod l_itemCheckMethod61 = new ItemCheckMethod();
            l_itemCheckMethod61.columnName = "口座名義人";
            l_itemCheckMethod61.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod61.size = 60;
            l_itemCheckMethod61.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod61.customizing = true;
            methodMap.put("fin_account_name", l_itemCheckMethod61);
            ItemCheckMethod l_itemCheckMethod62 = new ItemCheckMethod();
            l_itemCheckMethod62.columnName = "振替手数料区分";
            l_itemCheckMethod62.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod62.size = 1;
            l_itemCheckMethod62.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod62.customizing = true;
            methodMap.put("trans_commission", l_itemCheckMethod62);
            ItemCheckMethod l_itemCheckMethod63 = new ItemCheckMethod();
            l_itemCheckMethod63.columnName = "現物株式";
            l_itemCheckMethod63.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod63.size = 1;
            l_itemCheckMethod63.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod63.customizing = true;
            methodMap.put("experience_div_equity", l_itemCheckMethod63);
            ItemCheckMethod l_itemCheckMethod64 = new ItemCheckMethod();
            l_itemCheckMethod64.columnName = "信用取引";
            l_itemCheckMethod64.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod64.size = 1;
            l_itemCheckMethod64.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod64.customizing = true;
            methodMap.put("experience_div_margin", l_itemCheckMethod64);
            ItemCheckMethod l_itemCheckMethod65 = new ItemCheckMethod();
            l_itemCheckMethod65.columnName = "債券";
            l_itemCheckMethod65.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod65.size = 1;
            l_itemCheckMethod65.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod65.customizing = true;
            methodMap.put("experience_div_bond", l_itemCheckMethod65);
            ItemCheckMethod l_itemCheckMethod66 = new ItemCheckMethod();
            l_itemCheckMethod66.columnName = "転換社債";
            l_itemCheckMethod66.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod66.size = 1;
            l_itemCheckMethod66.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod66.customizing = true;
            methodMap.put("experience_div_wt", l_itemCheckMethod66);
            ItemCheckMethod l_itemCheckMethod67 = new ItemCheckMethod();
            l_itemCheckMethod67.columnName = "投資信託（株式）";
            l_itemCheckMethod67.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod67.size = 1;
            l_itemCheckMethod67.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod67.customizing = true;
            methodMap.put("experience_div_fund_sk", l_itemCheckMethod67);
            ItemCheckMethod l_itemCheckMethod68 = new ItemCheckMethod();
            l_itemCheckMethod68.columnName = "投資信託（公社債）";
            l_itemCheckMethod68.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod68.size = 1;
            l_itemCheckMethod68.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod68.customizing = true;
            methodMap.put("experience_div_fund_bd", l_itemCheckMethod68);
            ItemCheckMethod l_itemCheckMethod69 = new ItemCheckMethod();
            l_itemCheckMethod69.columnName = "先物・オプション";
            l_itemCheckMethod69.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod69.size = 1;
            l_itemCheckMethod69.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod69.customizing = true;
            methodMap.put("experience_div_fo", l_itemCheckMethod69);
            ItemCheckMethod l_itemCheckMethod70 = new ItemCheckMethod();
            l_itemCheckMethod70.columnName = "外国証券";
            l_itemCheckMethod70.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod70.size = 1;
            l_itemCheckMethod70.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod70.customizing = true;
            methodMap.put("experience_div_f_equity", l_itemCheckMethod70);
            ItemCheckMethod l_itemCheckMethod71 = new ItemCheckMethod();
            l_itemCheckMethod71.columnName = "その他";
            l_itemCheckMethod71.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod71.size = 1;
            l_itemCheckMethod71.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod71.customizing = true;
            methodMap.put("experience_div_etc", l_itemCheckMethod71);
            ItemCheckMethod l_itemCheckMethod72 = new ItemCheckMethod();
            l_itemCheckMethod72.columnName = "現物株式フラグ";
            l_itemCheckMethod72.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod72.size = 1;
            l_itemCheckMethod72.itemCheckMode = null;
            l_itemCheckMethod72.customizing = true;
            methodMap.put("experience_flag_equity", l_itemCheckMethod72);
            ItemCheckMethod l_itemCheckMethod73 = new ItemCheckMethod();
            l_itemCheckMethod73.columnName = "信用取引フラグ";
            l_itemCheckMethod73.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod73.size = 1;
            l_itemCheckMethod73.itemCheckMode = null;
            l_itemCheckMethod73.customizing = true;
            methodMap.put("experience_flag_margin", l_itemCheckMethod73);
            ItemCheckMethod l_itemCheckMethod74 = new ItemCheckMethod();
            l_itemCheckMethod74.columnName = "債券フラグ";
            l_itemCheckMethod74.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod74.size = 1;
            l_itemCheckMethod74.itemCheckMode = null;
            l_itemCheckMethod74.customizing = true;
            methodMap.put("experience_flag_bond", l_itemCheckMethod74);
            ItemCheckMethod l_itemCheckMethod75 = new ItemCheckMethod();
            l_itemCheckMethod75.columnName = "転換社債フラグ";
            l_itemCheckMethod75.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod75.size = 1;
            l_itemCheckMethod75.itemCheckMode = null;
            l_itemCheckMethod75.customizing = true;
            methodMap.put("experience_flag_wt", l_itemCheckMethod75);
            ItemCheckMethod l_itemCheckMethod76 = new ItemCheckMethod();
            l_itemCheckMethod76.columnName = "投資信託（株式）フラグ";
            l_itemCheckMethod76.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod76.size = 1;
            l_itemCheckMethod76.itemCheckMode = null;
            l_itemCheckMethod76.customizing = true;
            methodMap.put("experience_flag_fund_sk", l_itemCheckMethod76);
            ItemCheckMethod l_itemCheckMethod77 = new ItemCheckMethod();
            l_itemCheckMethod77.columnName = "投資信託（公社債）フラグ";
            l_itemCheckMethod77.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod77.size = 1;
            l_itemCheckMethod77.itemCheckMode = null;
            l_itemCheckMethod77.customizing = true;
            methodMap.put("experience_flag_fund_bd", l_itemCheckMethod77);
            ItemCheckMethod l_itemCheckMethod78 = new ItemCheckMethod();
            l_itemCheckMethod78.columnName = "先物・オプションフラグ";
            l_itemCheckMethod78.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod78.size = 1;
            l_itemCheckMethod78.itemCheckMode = null;
            l_itemCheckMethod78.customizing = true;
            methodMap.put("experience_flag_fo", l_itemCheckMethod78);
            ItemCheckMethod l_itemCheckMethod79 = new ItemCheckMethod();
            l_itemCheckMethod79.columnName = "外国証券フラグ";
            l_itemCheckMethod79.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod79.size = 1;
            l_itemCheckMethod79.itemCheckMode = null;
            l_itemCheckMethod79.customizing = true;
            methodMap.put("experience_flag_f_equity", l_itemCheckMethod79);
            ItemCheckMethod l_itemCheckMethod80 = new ItemCheckMethod();
            l_itemCheckMethod80.columnName = "その他フラグ";
            l_itemCheckMethod80.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod80.size = 1;
            l_itemCheckMethod80.itemCheckMode = null;
            l_itemCheckMethod80.customizing = true;
            methodMap.put("experience_flag_etc", l_itemCheckMethod80);
            ItemCheckMethod l_itemCheckMethod81 = new ItemCheckMethod();
            l_itemCheckMethod81.columnName = "経験年数（自）";
            l_itemCheckMethod81.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod81.size = 2;
            l_itemCheckMethod81.itemCheckMode = null;
            l_itemCheckMethod81.customizing = true;
            methodMap.put("experience_from", l_itemCheckMethod81);
            ItemCheckMethod l_itemCheckMethod82 = new ItemCheckMethod();
            l_itemCheckMethod82.columnName = "経験年数（至）";
            l_itemCheckMethod82.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod82.size = 2;
            l_itemCheckMethod82.itemCheckMode = null;
            l_itemCheckMethod82.customizing = true;
            methodMap.put("experience_to", l_itemCheckMethod82);
            ItemCheckMethod l_itemCheckMethod83 = new ItemCheckMethod();
            l_itemCheckMethod83.columnName = "現物株式フラグ";
            l_itemCheckMethod83.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod83.size = 1;
            l_itemCheckMethod83.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod83.customizing = true;
            methodMap.put("interest_flag_equity", l_itemCheckMethod83);
            ItemCheckMethod l_itemCheckMethod84 = new ItemCheckMethod();
            l_itemCheckMethod84.columnName = "株式ミニ投資フラグ";
            l_itemCheckMethod84.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod84.size = 1;
            l_itemCheckMethod84.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod84.customizing = true;
            methodMap.put("interest_flag_ministock", l_itemCheckMethod84);
            ItemCheckMethod l_itemCheckMethod85 = new ItemCheckMethod();
            l_itemCheckMethod85.columnName = "信用取引フラグ";
            l_itemCheckMethod85.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod85.size = 1;
            l_itemCheckMethod85.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod85.customizing = true;
            methodMap.put("interest_flag_margin", l_itemCheckMethod85);
            ItemCheckMethod l_itemCheckMethod86 = new ItemCheckMethod();
            l_itemCheckMethod86.columnName = "債券フラグ";
            l_itemCheckMethod86.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod86.size = 1;
            l_itemCheckMethod86.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod86.customizing = true;
            methodMap.put("interest_flag_bond", l_itemCheckMethod86);
            ItemCheckMethod l_itemCheckMethod87 = new ItemCheckMethod();
            l_itemCheckMethod87.columnName = "投資信託フラグ";
            l_itemCheckMethod87.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod87.size = 1;
            l_itemCheckMethod87.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod87.customizing = true;
            methodMap.put("interest_flag_fund", l_itemCheckMethod87);
            ItemCheckMethod l_itemCheckMethod88 = new ItemCheckMethod();
            l_itemCheckMethod88.columnName = "先物・オプションフラグ";
            l_itemCheckMethod88.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod88.size = 1;
            l_itemCheckMethod88.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod88.customizing = true;
            methodMap.put("interest_flag_fo", l_itemCheckMethod88);
            ItemCheckMethod l_itemCheckMethod89 = new ItemCheckMethod();
            l_itemCheckMethod89.columnName = "外国証券フラグ";
            l_itemCheckMethod89.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod89.size = 1;
            l_itemCheckMethod89.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod89.customizing = true;
            methodMap.put("interest_flag_f_equity", l_itemCheckMethod89);
            ItemCheckMethod l_itemCheckMethod90 = new ItemCheckMethod();
            l_itemCheckMethod90.columnName = "その他フラグ";
            l_itemCheckMethod90.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod90.size = 1;
            l_itemCheckMethod90.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod90.customizing = true;
            methodMap.put("interest_flag_etc", l_itemCheckMethod90);
            ItemCheckMethod l_itemCheckMethod91 = new ItemCheckMethod();
            l_itemCheckMethod91.columnName = "投資目的区分";
            l_itemCheckMethod91.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod91.size = 1;
            l_itemCheckMethod91.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod91.customizing = true;
            methodMap.put("invest_purpose_div", l_itemCheckMethod91);
            ItemCheckMethod l_itemCheckMethod92 = new ItemCheckMethod();
            l_itemCheckMethod92.columnName = "取引動機@区分";
            l_itemCheckMethod92.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod92.size = 1;
            l_itemCheckMethod92.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod92.customizing = true;
            methodMap.put("appli_motivat_div", l_itemCheckMethod92);
            ItemCheckMethod l_itemCheckMethod93 = new ItemCheckMethod();
            l_itemCheckMethod93.columnName = "年収区分";
            l_itemCheckMethod93.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod93.size = 1;
            l_itemCheckMethod93.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod93.customizing = true;
            methodMap.put("annual_income_div", l_itemCheckMethod93);
            ItemCheckMethod l_itemCheckMethod94 = new ItemCheckMethod();
            l_itemCheckMethod94.columnName = "年収（自）";
            l_itemCheckMethod94.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod94.size = 6;
            l_itemCheckMethod94.itemCheckMode = null;
            l_itemCheckMethod94.customizing = true;
            methodMap.put("annual_income_from", l_itemCheckMethod94);
            ItemCheckMethod l_itemCheckMethod95 = new ItemCheckMethod();
            l_itemCheckMethod95.columnName = "年収（至）";
            l_itemCheckMethod95.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod95.size = 6;
            l_itemCheckMethod95.itemCheckMode = null;
            l_itemCheckMethod95.customizing = true;
            methodMap.put("annual_income_to", l_itemCheckMethod95);
            ItemCheckMethod l_itemCheckMethod96 = new ItemCheckMethod();
            l_itemCheckMethod96.columnName = "金融資産区分";
            l_itemCheckMethod96.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod96.size = 1;
            l_itemCheckMethod96.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod96.customizing = true;
            methodMap.put("asset_value_div", l_itemCheckMethod96);
            ItemCheckMethod l_itemCheckMethod97 = new ItemCheckMethod();
            l_itemCheckMethod97.columnName = "金融資産（自）";
            l_itemCheckMethod97.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod97.size = 6;
            l_itemCheckMethod97.itemCheckMode = null;
            l_itemCheckMethod97.customizing = true;
            methodMap.put("asset_value_from", l_itemCheckMethod97);
            ItemCheckMethod l_itemCheckMethod98 = new ItemCheckMethod();
            l_itemCheckMethod98.columnName = "金融資産（至）";
            l_itemCheckMethod98.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod98.size = 6;
            l_itemCheckMethod98.itemCheckMode = null;
            l_itemCheckMethod98.customizing = true;
            methodMap.put("asset_value_to", l_itemCheckMethod98);
            ItemCheckMethod l_itemCheckMethod99 = new ItemCheckMethod();
            l_itemCheckMethod99.columnName = "運用予定額";
            l_itemCheckMethod99.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod99.size = 1;
            l_itemCheckMethod99.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod99.customizing = true;
            methodMap.put("fund_budget_amount_div", l_itemCheckMethod99);
            ItemCheckMethod l_itemCheckMethod100 = new ItemCheckMethod();
            l_itemCheckMethod100.columnName = "資金の性格";
            l_itemCheckMethod100.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod100.size = 1;
            l_itemCheckMethod100.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod100.customizing = true;
            methodMap.put("fund_budget_div", l_itemCheckMethod100);
            ItemCheckMethod l_itemCheckMethod101 = new ItemCheckMethod();
            l_itemCheckMethod101.columnName = "資金の性格（その他）";
            l_itemCheckMethod101.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod101.size = 40;
            l_itemCheckMethod101.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod101.customizing = true;
            methodMap.put("fund_budget_etc", l_itemCheckMethod101);
            ItemCheckMethod l_itemCheckMethod102 = new ItemCheckMethod();
            l_itemCheckMethod102.columnName = "検索用区分";
            l_itemCheckMethod102.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod102.size = 1;
            l_itemCheckMethod102.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod102.customizing = true;
            methodMap.put("id_confirm_flag", l_itemCheckMethod102);
            ItemCheckMethod l_itemCheckMethod103 = new ItemCheckMethod();
            l_itemCheckMethod103.columnName = "本人確認書類区分";
            l_itemCheckMethod103.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod103.size = 2;
            l_itemCheckMethod103.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod103.customizing = true;
            methodMap.put("id_confirm_doc_div", l_itemCheckMethod103);
            ItemCheckMethod l_itemCheckMethod104 = new ItemCheckMethod();
            l_itemCheckMethod104.columnName = "本人確認書類区分（その他）";
            l_itemCheckMethod104.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod104.size = 40;
            l_itemCheckMethod104.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod104.customizing = true;
            methodMap.put("id_confirm_doc_etc", l_itemCheckMethod104);
            ItemCheckMethod l_itemCheckMethod105 = new ItemCheckMethod();
            l_itemCheckMethod105.columnName = "特定口座区分";
            l_itemCheckMethod105.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod105.size = 1;
            l_itemCheckMethod105.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod105.customizing = true;
            methodMap.put("special_acc", l_itemCheckMethod105);
            ItemCheckMethod l_itemCheckMethod106 = new ItemCheckMethod();
            l_itemCheckMethod106.columnName = "信用取引特定口座区分";
            l_itemCheckMethod106.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod106.size = 1;
            l_itemCheckMethod106.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod106.customizing = true;
            methodMap.put("special_acc_margin", l_itemCheckMethod106);
            ItemCheckMethod l_itemCheckMethod107 = new ItemCheckMethod();
            l_itemCheckMethod107.columnName = "内部者登録フラグ";
            l_itemCheckMethod107.necessaryItemFlag = BooleanEnum.TRUE;
            l_itemCheckMethod107.size = 1;
            l_itemCheckMethod107.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod107.customizing = true;
            methodMap.put("insider_flag", l_itemCheckMethod107);
            ItemCheckMethod l_itemCheckMethod108 = new ItemCheckMethod();
            l_itemCheckMethod108.columnName = "内部者銘柄名";
            l_itemCheckMethod108.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod108.size = 50;
            l_itemCheckMethod108.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod108.customizing = true;
            methodMap.put("product_name", l_itemCheckMethod108);
            ItemCheckMethod l_itemCheckMethod109 = new ItemCheckMethod();
            l_itemCheckMethod109.columnName = "送付先郵便番号";
            l_itemCheckMethod109.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod109.size = 7;
            l_itemCheckMethod109.itemCheckMode = WEB3ItemCheckModeDef.ZIP_CODE;
            l_itemCheckMethod109.customizing = true;
            methodMap.put("send_zip_code", l_itemCheckMethod109);
            ItemCheckMethod l_itemCheckMethod110 = new ItemCheckMethod();
            l_itemCheckMethod110.columnName = "送付先住所１";
            l_itemCheckMethod110.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod110.size = 34;
            l_itemCheckMethod110.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod110.customizing = true;
            methodMap.put("send_address_line1", l_itemCheckMethod110);
            ItemCheckMethod l_itemCheckMethod111 = new ItemCheckMethod();
            l_itemCheckMethod111.columnName = "送付先住所２";
            l_itemCheckMethod111.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod111.size = 34;
            l_itemCheckMethod111.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod111.customizing = true;
            methodMap.put("send_address_line2", l_itemCheckMethod111);
            ItemCheckMethod l_itemCheckMethod112 = new ItemCheckMethod();
            l_itemCheckMethod112.columnName = "送付先住所３";
            l_itemCheckMethod112.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod112.size = 34;
            l_itemCheckMethod112.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod112.customizing = true;
            methodMap.put("send_address_line3", l_itemCheckMethod112);
            ItemCheckMethod l_itemCheckMethod113 = new ItemCheckMethod();
            l_itemCheckMethod113.columnName = "各社拡張項目（区分１）";
            l_itemCheckMethod113.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod113.size = 4;
            l_itemCheckMethod113.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod113.customizing = true;
            methodMap.put("ext_item_div1", l_itemCheckMethod113);
            ItemCheckMethod l_itemCheckMethod114 = new ItemCheckMethod();
            l_itemCheckMethod114.columnName = "各社拡張項目（区分２）";
            l_itemCheckMethod114.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod114.size = 4;
            l_itemCheckMethod114.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod114.customizing = true;
            methodMap.put("ext_item_div2", l_itemCheckMethod114);
            ItemCheckMethod l_itemCheckMethod115 = new ItemCheckMethod();
            l_itemCheckMethod115.columnName = "各社拡張項目（区分３）";
            l_itemCheckMethod115.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod115.size = 4;
            l_itemCheckMethod115.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod115.customizing = true;
            methodMap.put("ext_item_div3", l_itemCheckMethod115);
            ItemCheckMethod l_itemCheckMethod116 = new ItemCheckMethod();
            l_itemCheckMethod116.columnName = "各社拡張項目（区分４）";
            l_itemCheckMethod116.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod116.size = 4;
            l_itemCheckMethod116.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod116.customizing = true;
            methodMap.put("ext_item_div4", l_itemCheckMethod116);
            ItemCheckMethod l_itemCheckMethod117 = new ItemCheckMethod();
            l_itemCheckMethod117.columnName = "各社拡張項目（区分５）";
            l_itemCheckMethod117.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod117.size = 4;
            l_itemCheckMethod117.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod117.customizing = true;
            methodMap.put("ext_item_div5", l_itemCheckMethod117);
            ItemCheckMethod l_itemCheckMethod118 = new ItemCheckMethod();
            l_itemCheckMethod118.columnName = "各社拡張項目（区分６）";
            l_itemCheckMethod118.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod118.size = 4;
            l_itemCheckMethod118.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod118.customizing = true;
            methodMap.put("ext_item_div6", l_itemCheckMethod118);
            ItemCheckMethod l_itemCheckMethod119 = new ItemCheckMethod();
            l_itemCheckMethod119.columnName = "各社拡張項目（区分７）";
            l_itemCheckMethod119.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod119.size = 4;
            l_itemCheckMethod119.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod119.customizing = true;
            methodMap.put("ext_item_div7", l_itemCheckMethod119);
            ItemCheckMethod l_itemCheckMethod120 = new ItemCheckMethod();
            l_itemCheckMethod120.columnName = "各社拡張項目（区分８）";
            l_itemCheckMethod120.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod120.size = 4;
            l_itemCheckMethod120.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod120.customizing = true;
            methodMap.put("ext_item_div8", l_itemCheckMethod120);
            ItemCheckMethod l_itemCheckMethod121 = new ItemCheckMethod();
            l_itemCheckMethod121.columnName = "各社拡張項目（区分９）";
            l_itemCheckMethod121.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod121.size = 4;
            l_itemCheckMethod121.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod121.customizing = true;
            methodMap.put("ext_item_div9", l_itemCheckMethod121);
            ItemCheckMethod l_itemCheckMethod122 = new ItemCheckMethod();
            l_itemCheckMethod122.columnName = "各社拡張項目（区分１０）";
            l_itemCheckMethod122.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod122.size = 4;
            l_itemCheckMethod122.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod122.customizing = true;
            methodMap.put("ext_item_div10", l_itemCheckMethod122);
            ItemCheckMethod l_itemCheckMethod123 = new ItemCheckMethod();
            l_itemCheckMethod123.columnName = "各社拡張項目（フラグ1）";
            l_itemCheckMethod123.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod123.size = 1;
            l_itemCheckMethod123.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod123.customizing = true;
            methodMap.put("ext_item_flag1", l_itemCheckMethod123);
            ItemCheckMethod l_itemCheckMethod124 = new ItemCheckMethod();
            l_itemCheckMethod124.columnName = "各社拡張項目（フラグ2）";
            l_itemCheckMethod124.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod124.size = 1;
            l_itemCheckMethod124.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod124.customizing = true;
            methodMap.put("ext_item_flag2", l_itemCheckMethod124);
            ItemCheckMethod l_itemCheckMethod125 = new ItemCheckMethod();
            l_itemCheckMethod125.columnName = "各社拡張項目（フラグ3）";
            l_itemCheckMethod125.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod125.size = 1;
            l_itemCheckMethod125.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod125.customizing = true;
            methodMap.put("ext_item_flag3", l_itemCheckMethod125);
            ItemCheckMethod l_itemCheckMethod126 = new ItemCheckMethod();
            l_itemCheckMethod126.columnName = "各社拡張項目（フラグ4）";
            l_itemCheckMethod126.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod126.size = 1;
            l_itemCheckMethod126.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod126.customizing = true;
            methodMap.put("ext_item_flag4", l_itemCheckMethod126);
            ItemCheckMethod l_itemCheckMethod127 = new ItemCheckMethod();
            l_itemCheckMethod127.columnName = "各社拡張項目（フラグ5）";
            l_itemCheckMethod127.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod127.size = 1;
            l_itemCheckMethod127.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod127.customizing = true;
            methodMap.put("ext_item_flag5", l_itemCheckMethod127);
            ItemCheckMethod l_itemCheckMethod128 = new ItemCheckMethod();
            l_itemCheckMethod128.columnName = "各社拡張項目（フラグ６）";
            l_itemCheckMethod128.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod128.size = 1;
            l_itemCheckMethod128.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod128.customizing = true;
            methodMap.put("ext_item_flag6", l_itemCheckMethod128);
            ItemCheckMethod l_itemCheckMethod129 = new ItemCheckMethod();
            l_itemCheckMethod129.columnName = "各社拡張項目（フラグ７）";
            l_itemCheckMethod129.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod129.size = 1;
            l_itemCheckMethod129.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod129.customizing = true;
            methodMap.put("ext_item_flag7", l_itemCheckMethod129);
            ItemCheckMethod l_itemCheckMethod130 = new ItemCheckMethod();
            l_itemCheckMethod130.columnName = "各社拡張項目（フラグ８）";
            l_itemCheckMethod130.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod130.size = 1;
            l_itemCheckMethod130.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod130.customizing = true;
            methodMap.put("ext_item_flag8", l_itemCheckMethod130);
            ItemCheckMethod l_itemCheckMethod131 = new ItemCheckMethod();
            l_itemCheckMethod131.columnName = "各社拡張項目（フラグ９）";
            l_itemCheckMethod131.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod131.size = 1;
            l_itemCheckMethod131.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod131.customizing = true;
            methodMap.put("ext_item_flag9", l_itemCheckMethod131);
            ItemCheckMethod l_itemCheckMethod132 = new ItemCheckMethod();
            l_itemCheckMethod132.columnName = "各社拡張項目（フラグ１０）";
            l_itemCheckMethod132.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod132.size = 1;
            l_itemCheckMethod132.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod132.customizing = true;
            methodMap.put("ext_item_flag10", l_itemCheckMethod132);
            ItemCheckMethod l_itemCheckMethod133 = new ItemCheckMethod();
            l_itemCheckMethod133.columnName = "各社拡張項目（テキスト１）";
            l_itemCheckMethod133.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod133.size = 50;
            l_itemCheckMethod133.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod133.customizing = true;
            methodMap.put("ext_item_text1", l_itemCheckMethod133);
            ItemCheckMethod l_itemCheckMethod134 = new ItemCheckMethod();
            l_itemCheckMethod134.columnName = "各社拡張項目（テキスト２）";
            l_itemCheckMethod134.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod134.size = 50;
            l_itemCheckMethod134.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod134.customizing = true;
            methodMap.put("ext_item_text2", l_itemCheckMethod134);
            ItemCheckMethod l_itemCheckMethod135 = new ItemCheckMethod();
            l_itemCheckMethod135.columnName = "各社拡張項目（テキスト３）";
            l_itemCheckMethod135.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod135.size = 50;
            l_itemCheckMethod135.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod135.customizing = true;
            methodMap.put("ext_item_text3", l_itemCheckMethod135);
            ItemCheckMethod l_itemCheckMethod136 = new ItemCheckMethod();
            l_itemCheckMethod136.columnName = "各社拡張項目（テキスト４）";
            l_itemCheckMethod136.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod136.size = 50;
            l_itemCheckMethod136.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod136.customizing = true;
            methodMap.put("ext_item_text4", l_itemCheckMethod136);
            ItemCheckMethod l_itemCheckMethod137 = new ItemCheckMethod();
            l_itemCheckMethod137.columnName = "各社拡張項目（テキスト５）";
            l_itemCheckMethod137.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod137.size = 50;
            l_itemCheckMethod137.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod137.customizing = true;
            methodMap.put("ext_item_text5", l_itemCheckMethod137);
            ItemCheckMethod l_itemCheckMethod138 = new ItemCheckMethod();
            l_itemCheckMethod138.columnName = "各社拡張項目（テキスト６）";
            l_itemCheckMethod138.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod138.size = 50;
            l_itemCheckMethod138.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod138.customizing = true;
            methodMap.put("ext_item_text6", l_itemCheckMethod138);
            ItemCheckMethod l_itemCheckMethod139 = new ItemCheckMethod();
            l_itemCheckMethod139.columnName = "各社拡張項目（テキスト７）";
            l_itemCheckMethod139.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod139.size = 50;
            l_itemCheckMethod139.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod139.customizing = true;
            methodMap.put("ext_item_text7", l_itemCheckMethod139);
            ItemCheckMethod l_itemCheckMethod140 = new ItemCheckMethod();
            l_itemCheckMethod140.columnName = "各社拡張項目（テキスト８）";
            l_itemCheckMethod140.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod140.size = 50;
            l_itemCheckMethod140.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod140.customizing = true;
            methodMap.put("ext_item_text8", l_itemCheckMethod140);
            ItemCheckMethod l_itemCheckMethod141 = new ItemCheckMethod();
            l_itemCheckMethod141.columnName = "各社拡張項目（テキスト９）";
            l_itemCheckMethod141.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod141.size = 50;
            l_itemCheckMethod141.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod141.customizing = true;
            methodMap.put("ext_item_text9", l_itemCheckMethod141);
            ItemCheckMethod l_itemCheckMethod142 = new ItemCheckMethod();
            l_itemCheckMethod142.columnName = "各社拡張項目（テキスト１０）";
            l_itemCheckMethod142.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod142.size = 50;
            l_itemCheckMethod142.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod142.customizing = true;
            methodMap.put("ext_item_text10", l_itemCheckMethod142);
            //ＤＢレイアウト No.010
            ItemCheckMethod l_itemCheckMethod143 = new ItemCheckMethod();
            l_itemCheckMethod143.columnName = "生年月日";
            l_itemCheckMethod143.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod143.size = 6;
            l_itemCheckMethod143.itemCheckMode = WEB3ItemCheckModeDef.AGE;
            l_itemCheckMethod143.customizing = true;
            methodMap.put("born_date", l_itemCheckMethod143);
            
            //ＤＢレイアウト No.026
            ItemCheckMethod l_itemCheckMethod144 = new ItemCheckMethod();
            l_itemCheckMethod144.columnName = "顧客正式名称作成区分";
            l_itemCheckMethod144.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod144.size = 1;
            l_itemCheckMethod144.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod144.customizing = true;
            methodMap.put("real_name_voucher_div", l_itemCheckMethod144);
            
            ItemCheckMethod l_itemCheckMethod145 = new ItemCheckMethod();
            l_itemCheckMethod145.columnName = "顧客正式名称１";
            l_itemCheckMethod145.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod145.size = 40;
            l_itemCheckMethod145.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod145.customizing = true;
            methodMap.put("real_name1", l_itemCheckMethod145);
            
            ItemCheckMethod l_itemCheckMethod146 = new ItemCheckMethod();
            l_itemCheckMethod146.columnName = "顧客正式名称２";
            l_itemCheckMethod146.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod146.size = 40;
            l_itemCheckMethod146.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod146.customizing = true;
            methodMap.put("real_name2", l_itemCheckMethod146);
            
            ItemCheckMethod l_itemCheckMethod147 = new ItemCheckMethod();
            l_itemCheckMethod147.columnName = "（内部者）作成区分";
            l_itemCheckMethod147.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod147.size = 1;
            l_itemCheckMethod147.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod147.customizing = true;
            methodMap.put("insider_voucher_div", l_itemCheckMethod147);
            
            ItemCheckMethod l_itemCheckMethod148 = new ItemCheckMethod();
            l_itemCheckMethod148.columnName = "（内部者）銘柄コード";
            l_itemCheckMethod148.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod148.size = 5;
            l_itemCheckMethod148.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod148.customizing = true;
            methodMap.put("insider_product_code", l_itemCheckMethod148);
            
            ItemCheckMethod l_itemCheckMethod149 = new ItemCheckMethod();
            l_itemCheckMethod149.columnName = "（内部者）関係区分";
            l_itemCheckMethod149.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod149.size = 1;
            l_itemCheckMethod149.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod149.customizing = true;
            methodMap.put("insider_relation_div", l_itemCheckMethod149);
            
            ItemCheckMethod l_itemCheckMethod150 = new ItemCheckMethod();
            l_itemCheckMethod150.columnName = "（内部者）役員名";
            l_itemCheckMethod150.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod150.size = 18;
            l_itemCheckMethod150.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod150.customizing = true;
            methodMap.put("insider_officer_name", l_itemCheckMethod150);
            
            ItemCheckMethod l_itemCheckMethod151 = new ItemCheckMethod();
            l_itemCheckMethod151.columnName = "（内部者）役職名コード";
            l_itemCheckMethod151.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod151.size = 2;
            l_itemCheckMethod151.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod151.customizing = true;
            methodMap.put("insider_post_code", l_itemCheckMethod151);
            
            ItemCheckMethod l_itemCheckMethod152 = new ItemCheckMethod();
            l_itemCheckMethod152.columnName = "（内部者）役職名";
            l_itemCheckMethod152.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod152.size = 20;
            l_itemCheckMethod152.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod152.customizing = true;
            methodMap.put("insider_post_name", l_itemCheckMethod152);
            
            ItemCheckMethod l_itemCheckMethod153 = new ItemCheckMethod();
            l_itemCheckMethod153.columnName = "（ＧＰ）作成区分";
            l_itemCheckMethod153.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod153.size = 1;
            l_itemCheckMethod153.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod153.customizing = true;
            methodMap.put("gp_voucher_div", l_itemCheckMethod153);
            
            ItemCheckMethod l_itemCheckMethod154 = new ItemCheckMethod();
            l_itemCheckMethod154.columnName = "（ＧＰ）コース";
            l_itemCheckMethod154.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod154.size = 1;
            l_itemCheckMethod154.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod154.customizing = true;
            methodMap.put("gp_course", l_itemCheckMethod154);
            
            ItemCheckMethod l_itemCheckMethod155 = new ItemCheckMethod();
            l_itemCheckMethod155.columnName = "（ＧＰ）プラン";
            l_itemCheckMethod155.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod155.size = 1;
            l_itemCheckMethod155.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod155.customizing = true;
            methodMap.put("gp_plan", l_itemCheckMethod155);
            
            ItemCheckMethod l_itemCheckMethod156 = new ItemCheckMethod();
            l_itemCheckMethod156.columnName = "（ＧＰ）目標額";
            l_itemCheckMethod156.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod156.size = 4;
            l_itemCheckMethod156.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod156.customizing = true;
            methodMap.put("gp_target_figure", l_itemCheckMethod156);
            
            ItemCheckMethod l_itemCheckMethod157 = new ItemCheckMethod();
            l_itemCheckMethod157.columnName = "（ＧＰ）目標年";
            l_itemCheckMethod157.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod157.size = 2;
            l_itemCheckMethod157.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod157.customizing = true;
            methodMap.put("gp_target_year", l_itemCheckMethod157);
            
            ItemCheckMethod l_itemCheckMethod158 = new ItemCheckMethod();
            l_itemCheckMethod158.columnName = "（ＧＰ）目標月";
            l_itemCheckMethod158.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod158.size = 2;
            l_itemCheckMethod158.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod158.customizing = true;
            methodMap.put("gp_target_month", l_itemCheckMethod158);
            
            ItemCheckMethod l_itemCheckMethod159 = new ItemCheckMethod();
            l_itemCheckMethod159.columnName = "（ＧＰ）積立額";
            l_itemCheckMethod159.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod159.size = 7;
            l_itemCheckMethod159.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod159.customizing = true;
            methodMap.put("gp_installment_figure", l_itemCheckMethod159);
            
            ItemCheckMethod l_itemCheckMethod160 = new ItemCheckMethod();
            l_itemCheckMethod160.columnName = "（ＧＰ）入金周期";
            l_itemCheckMethod160.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod160.size = 1;
            l_itemCheckMethod160.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod160.customizing = true;
            methodMap.put("gp_deposit_cycle", l_itemCheckMethod160);
            
            ItemCheckMethod l_itemCheckMethod161 = new ItemCheckMethod();
            l_itemCheckMethod161.columnName = "（ＧＰ）受渡経路";
            l_itemCheckMethod161.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod161.size = 1;
            l_itemCheckMethod161.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod161.customizing = true;
            methodMap.put("gp_payment_root", l_itemCheckMethod161);
            
            ItemCheckMethod l_itemCheckMethod162 = new ItemCheckMethod();
            l_itemCheckMethod162.columnName = "（ＧＰ）再投資区分";
            l_itemCheckMethod162.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod162.size = 1;
            l_itemCheckMethod162.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod162.customizing = true;
            methodMap.put("gp_reinvest_div", l_itemCheckMethod162);
            
            ItemCheckMethod l_itemCheckMethod163 = new ItemCheckMethod();
            l_itemCheckMethod163.columnName = "（ＧＰ）税区分";
            l_itemCheckMethod163.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod163.size = 1;
            l_itemCheckMethod163.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod163.customizing = true;
            methodMap.put("gp_tax_div", l_itemCheckMethod163);
            
            ItemCheckMethod l_itemCheckMethod164 = new ItemCheckMethod();
            l_itemCheckMethod164.columnName = "（ＧＰ）（優）限度額";
            l_itemCheckMethod164.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod164.size = 3;
            l_itemCheckMethod164.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod164.customizing = true;
            methodMap.put("gp_taxfree_limit", l_itemCheckMethod164);
            
            ItemCheckMethod l_itemCheckMethod165 = new ItemCheckMethod();
            l_itemCheckMethod165.columnName = "（ＧＰ）（特優）限度額";
            l_itemCheckMethod165.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod165.size = 3;
            l_itemCheckMethod165.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod165.customizing = true;
            methodMap.put("gp_special_taxfree_limit", l_itemCheckMethod165);
            
            ItemCheckMethod l_itemCheckMethod166 = new ItemCheckMethod();
            l_itemCheckMethod166.columnName = "（ＧＰ）加入摘要";
            l_itemCheckMethod166.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod166.size = 1;
            l_itemCheckMethod166.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod166.customizing = true;
            methodMap.put("gp_subscr_summary", l_itemCheckMethod166);
            
            ItemCheckMethod l_itemCheckMethod167 = new ItemCheckMethod();
            l_itemCheckMethod167.columnName = "（ＧＰ）銘柄コード";
            l_itemCheckMethod167.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod167.size = 1;
            l_itemCheckMethod167.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod167.customizing = true;
            methodMap.put("gp_product_code", l_itemCheckMethod167);
            
            ItemCheckMethod l_itemCheckMethod168 = new ItemCheckMethod();
            l_itemCheckMethod168.columnName = "（ＧＰ）担保客";
            l_itemCheckMethod168.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod168.size = 1;
            l_itemCheckMethod168.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod168.customizing = true;
            methodMap.put("gp_mortgage_customer", l_itemCheckMethod168);
            
            ItemCheckMethod l_itemCheckMethod169 = new ItemCheckMethod();
            l_itemCheckMethod169.columnName = "（ＧＰ）ミックス客";
            l_itemCheckMethod169.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod169.size = 1;
            l_itemCheckMethod169.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod169.customizing = true;
            methodMap.put("gp_mix_customer", l_itemCheckMethod169);
            
            ItemCheckMethod l_itemCheckMethod170 = new ItemCheckMethod();
            l_itemCheckMethod170.columnName = "（ＧＰ）契約書";
            l_itemCheckMethod170.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod170.size = 1;
            l_itemCheckMethod170.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod170.customizing = true;
            methodMap.put("gp_contract", l_itemCheckMethod170);
            
            ItemCheckMethod l_itemCheckMethod171 = new ItemCheckMethod();
            l_itemCheckMethod171.columnName = "（上場外株）作成区分";
            l_itemCheckMethod171.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod171.size = 1;
            l_itemCheckMethod171.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod171.customizing = true;
            methodMap.put("stk_voucher_div", l_itemCheckMethod171);
            
            ItemCheckMethod l_itemCheckMethod172 = new ItemCheckMethod();
            l_itemCheckMethod172.columnName = "（上場外株）譲渡";
            l_itemCheckMethod172.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod172.size = 1;
            l_itemCheckMethod172.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod172.customizing = true;
            methodMap.put("stk_taxation_tran_div", l_itemCheckMethod172);
            
            ItemCheckMethod l_itemCheckMethod173 = new ItemCheckMethod();
            l_itemCheckMethod173.columnName = "（上場外株）住所（カナ）";
            l_itemCheckMethod173.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod173.size = 70;
            l_itemCheckMethod173.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod173.customizing = true;
            methodMap.put("stk_address_line_kana", l_itemCheckMethod173);
            
            ItemCheckMethod l_itemCheckMethod174 = new ItemCheckMethod();
            l_itemCheckMethod174.columnName = "（上場外株）送金";
            l_itemCheckMethod174.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod174.size = 1;
            l_itemCheckMethod174.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod174.customizing = true;
            methodMap.put("stk_transfer_div", l_itemCheckMethod174);
            
            ItemCheckMethod l_itemCheckMethod175 = new ItemCheckMethod();
            l_itemCheckMethod175.columnName = "（上場外株）銀行コード";
            l_itemCheckMethod175.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod175.size = 4;
            l_itemCheckMethod175.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod175.customizing = true;
            methodMap.put("stk_fin_institution_code", l_itemCheckMethod175);
            
            ItemCheckMethod l_itemCheckMethod176 = new ItemCheckMethod();
            l_itemCheckMethod176.columnName = "（上場外株）支店コード";
            l_itemCheckMethod176.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod176.size = 3;
            l_itemCheckMethod176.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod176.customizing = true;
            methodMap.put("stk_fin_branch_code", l_itemCheckMethod176);
            
            ItemCheckMethod l_itemCheckMethod177 = new ItemCheckMethod();
            l_itemCheckMethod177.columnName = "（上場外株）預金区分";
            l_itemCheckMethod177.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod177.size = 1;
            l_itemCheckMethod177.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod177.customizing = true;
            methodMap.put("stk_fin_save_div", l_itemCheckMethod177);
            
            ItemCheckMethod l_itemCheckMethod178 = new ItemCheckMethod();
            l_itemCheckMethod178.columnName = "（上場外株）口座番号";
            l_itemCheckMethod178.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod178.size = 7;
            l_itemCheckMethod178.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod178.customizing = true;
            methodMap.put("stk_fin_account_no", l_itemCheckMethod178);
            
            ItemCheckMethod l_itemCheckMethod179 = new ItemCheckMethod();
            l_itemCheckMethod179.columnName = "仲介業扱者コード";
            l_itemCheckMethod179.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod179.size = 5;
            l_itemCheckMethod179.itemCheckMode = WEB3ItemCheckModeDef.HALF_NUMBER;
            l_itemCheckMethod179.customizing = true;
            methodMap.put("brokerage_trader_code", l_itemCheckMethod179);
            
            ItemCheckMethod l_itemCheckMethod180 = new ItemCheckMethod();
            l_itemCheckMethod180.columnName = "各社拡張項目（区分1１）";
            l_itemCheckMethod180.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod180.size = 10;
            l_itemCheckMethod180.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod180.customizing = true;
            methodMap.put("ext_item_div11", l_itemCheckMethod180);
            
            ItemCheckMethod l_itemCheckMethod181 = new ItemCheckMethod();
            l_itemCheckMethod181.columnName = "各社拡張項目（区分１2）";
            l_itemCheckMethod181.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod181.size = 10;
            l_itemCheckMethod181.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod181.customizing = true;
            methodMap.put("ext_item_div12", l_itemCheckMethod181);
            
            ItemCheckMethod l_itemCheckMethod182 = new ItemCheckMethod();
            l_itemCheckMethod182.columnName = "各社拡張項目（区分１3）";
            l_itemCheckMethod182.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod182.size = 10;
            l_itemCheckMethod182.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod182.customizing = true;
            methodMap.put("ext_item_div13", l_itemCheckMethod182);
            
            ItemCheckMethod l_itemCheckMethod183 = new ItemCheckMethod();
            l_itemCheckMethod183.columnName = "各社拡張項目（区分１4）";
            l_itemCheckMethod183.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod183.size = 10;
            l_itemCheckMethod183.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod183.customizing = true;
            methodMap.put("ext_item_div14", l_itemCheckMethod183);
            
            ItemCheckMethod l_itemCheckMethod184 = new ItemCheckMethod();
            l_itemCheckMethod184.columnName = "各社拡張項目（区分１5）";
            l_itemCheckMethod184.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod184.size = 10;
            l_itemCheckMethod184.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod184.customizing = true;
            methodMap.put("ext_item_div15", l_itemCheckMethod184);
            
            ItemCheckMethod l_itemCheckMethod185 = new ItemCheckMethod();
            l_itemCheckMethod185.columnName = "口座番号（外貨）";
            l_itemCheckMethod185.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod185.size = 20;
            l_itemCheckMethod185.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod185.customizing = true;
            methodMap.put("foreign_account_no", l_itemCheckMethod185);
            
            ItemCheckMethod l_itemCheckMethod186 = new ItemCheckMethod();
            l_itemCheckMethod186.columnName = "口座名義人（外貨）";
            l_itemCheckMethod186.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod186.size = 60;
            l_itemCheckMethod186.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod186.customizing = true;
            methodMap.put("foreign_account_name", l_itemCheckMethod186);
            
            ItemCheckMethod l_itemCheckMethod187 = new ItemCheckMethod();
            l_itemCheckMethod187.columnName = "口座名義人英数（外貨）";
            l_itemCheckMethod187.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod187.size = 30;
            l_itemCheckMethod187.itemCheckMode = WEB3ItemCheckModeDef.DEFAULT;
            l_itemCheckMethod187.customizing = true;
            methodMap.put("foreign_account_name_eng", l_itemCheckMethod187);
            
            ItemCheckMethod l_itemCheckMethod188 = new ItemCheckMethod();
            l_itemCheckMethod188.columnName = "預金区分（外貨）";
            l_itemCheckMethod188.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod188.size = 1;
            l_itemCheckMethod188.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod188.customizing = true;
            methodMap.put("foreign_save_div", l_itemCheckMethod188);

            ItemCheckMethod l_itemCheckMethod189 = new ItemCheckMethod();
            l_itemCheckMethod189.columnName = "削除フラグ";
            l_itemCheckMethod189.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod189.size = 1;
            l_itemCheckMethod189.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod189.customizing = true;
            methodMap.put("delete_flag", l_itemCheckMethod189);

            ItemCheckMethod l_itemCheckMethod190 = new ItemCheckMethod();
            l_itemCheckMethod190.columnName = "印刷フラグ";
            l_itemCheckMethod190.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod190.size = 1;
            l_itemCheckMethod190.itemCheckMode = WEB3ItemCheckModeDef.VALID_CODE_CHECK;
            l_itemCheckMethod190.customizing = true;
            methodMap.put("print_flag", l_itemCheckMethod190);

            ItemCheckMethod l_itemCheckMethod191 = new ItemCheckMethod();
            l_itemCheckMethod191.columnName = "受領フラグ";
            l_itemCheckMethod191.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod191.size = 1;
            l_itemCheckMethod191.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod191.customizing = true;
            methodMap.put("receipt_flag", l_itemCheckMethod191);

            ItemCheckMethod l_itemCheckMethod192 = new ItemCheckMethod();
            l_itemCheckMethod192.columnName = "承諾フラグ";
            l_itemCheckMethod192.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod192.size = 1;
            l_itemCheckMethod192.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod192.customizing = true;
            methodMap.put("agreement_flag", l_itemCheckMethod192);

            ItemCheckMethod l_itemCheckMethod193 = new ItemCheckMethod();
            l_itemCheckMethod193.columnName = "外国人フラグ";
            l_itemCheckMethod193.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod193.size = 1;
            l_itemCheckMethod193.itemCheckMode = WEB3ItemCheckModeDef.BOOLEAN_FLAG;
            l_itemCheckMethod193.customizing = true;
            methodMap.put("foreign_flag", l_itemCheckMethod193);

            ItemCheckMethod l_itemCheckMethod194 = new ItemCheckMethod();
            l_itemCheckMethod194.columnName = "フリガナ1";
            l_itemCheckMethod194.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod194.size = 120;
            l_itemCheckMethod194.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod194.customizing = true;
            methodMap.put("agency_acc_name_kana1", l_itemCheckMethod194);

            ItemCheckMethod l_itemCheckMethod195 = new ItemCheckMethod();
            l_itemCheckMethod195.columnName = "フリガナ2";
            l_itemCheckMethod195.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod195.size = 120;
            l_itemCheckMethod195.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod195.customizing = true;
            methodMap.put("agency_acc_name_kana2", l_itemCheckMethod195);

            ItemCheckMethod l_itemCheckMethod196 = new ItemCheckMethod();
            l_itemCheckMethod196.columnName = "名称1";
            l_itemCheckMethod196.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod196.size = 120;
            l_itemCheckMethod196.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod196.customizing = true;
            methodMap.put("agency_acc_name1", l_itemCheckMethod196);

            ItemCheckMethod l_itemCheckMethod197 = new ItemCheckMethod();
            l_itemCheckMethod197.columnName = "名称2";
            l_itemCheckMethod197.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod197.size = 60;
            l_itemCheckMethod197.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod197.customizing = true;
            methodMap.put("agency_acc_name2", l_itemCheckMethod197);

            ItemCheckMethod l_itemCheckMethod198 = new ItemCheckMethod();
            l_itemCheckMethod198.columnName = "住所1";
            l_itemCheckMethod198.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod198.size = 96;
            l_itemCheckMethod198.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod198.customizing = true;
            methodMap.put("agency_address_line1", l_itemCheckMethod198);

            ItemCheckMethod l_itemCheckMethod199 = new ItemCheckMethod();
            l_itemCheckMethod199.columnName = "住所2";
            l_itemCheckMethod199.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod199.size = 48;
            l_itemCheckMethod199.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod199.customizing = true;
            methodMap.put("agency_address_line2", l_itemCheckMethod199);

            ItemCheckMethod l_itemCheckMethod200 = new ItemCheckMethod();
            l_itemCheckMethod200.columnName = "代表者の役職";
            l_itemCheckMethod200.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod200.size = 40;
            l_itemCheckMethod200.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod200.customizing = true;
            methodMap.put("agency_rep_post", l_itemCheckMethod200);

            ItemCheckMethod l_itemCheckMethod201 = new ItemCheckMethod();
            l_itemCheckMethod201.columnName = "代表者のフリガナ1";
            l_itemCheckMethod201.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod201.size = 120;
            l_itemCheckMethod201.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod201.customizing = true;
            methodMap.put("agency_rep_name_kana1", l_itemCheckMethod201);

            ItemCheckMethod l_itemCheckMethod202 = new ItemCheckMethod();
            l_itemCheckMethod202.columnName = "代表者のフリガナ2";
            l_itemCheckMethod202.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod202.size = 120;
            l_itemCheckMethod202.itemCheckMode = WEB3ItemCheckModeDef.ADDRESS_GIVEN_NAME_KANA;
            l_itemCheckMethod202.customizing = true;
            methodMap.put("agency_rep_name_kana2", l_itemCheckMethod202);

            ItemCheckMethod l_itemCheckMethod203 = new ItemCheckMethod();
            l_itemCheckMethod203.columnName = "代表者の氏名1";
            l_itemCheckMethod203.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod203.size = 60;
            l_itemCheckMethod203.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod203.customizing = true;
            methodMap.put("agency_rep_name1", l_itemCheckMethod203);

            ItemCheckMethod l_itemCheckMethod204 = new ItemCheckMethod();
            l_itemCheckMethod204.columnName = "代表者の氏名2";
            l_itemCheckMethod204.necessaryItemFlag = BooleanEnum.FALSE;
            l_itemCheckMethod204.size = 60;
            l_itemCheckMethod204.itemCheckMode = WEB3ItemCheckModeDef.FULL_CHARACTER;
            l_itemCheckMethod204.customizing = true;
            methodMap.put("agency_rep_name2", l_itemCheckMethod204);
        }
    }

    private String trimWhitespace(String l_str)
    {
        final String STR_METHOD_NAME = " trimWhitespace(String)";
        log.entering(STR_METHOD_NAME);
               
        StringBuffer l_sbStr = new StringBuffer();
        String l_str1 = null;
        int l_intNameLength = l_str.length();

        for (int j = 0; j < l_intNameLength; j++)
        {
            if (Character.isWhitespace(l_str.charAt(j)))
            {
                continue;
            }
            l_sbStr.append(l_str.charAt(j));
        }
        l_str1 = l_sbStr.toString();
        
        log.exiting(STR_METHOD_NAME);
        return l_str1;
    }
    
    /**
     * (getemailアドレス)<BR>
     * emailアドレスを取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.emailアドレスを返却する。<BR> 
     * @@return String
     */
    public String getEmailAddress()
    {
        return this.expAccountOpenParams.getEmailAddress();   
    }
    
    /**
     * (getY客ID )<BR>
     * Y客IDを取得する。<BR> 
     * <BR>
     * this.Y客マスタ行.Y客IDを返却する。<BR>
     * @@return String
     */
    public String getYCustomerId()
    {
        return String.valueOf(this.yCustomerParams.getYCustomerId());       
    }
    
    /**
     * (getY客管理No)<BR>
     * Y客管理Noを取得する。<BR> 
     * <BR>
     * this.Y客マスタ行.管理Noを返却する。<BR>
     * @@return String
     */
    public String getControlNumber()
    {
        return String.valueOf(this.yCustomerParams.getControlNumber());    
    }
    
    /**
     * (getY客管理部店コード)<BR>
     * Y客管理部店コードを取得する。<BR> 
     * <BR>
     * this.Y客マスタ行.管理部店コードを返却する。<BR>
     * @@return String
     */
    public String getControlBranchCode()
    {
        return this.yCustomerParams.getControlBranchCode();     
    }
    
    /**
     * (getY客業務区分)<BR>
     * Y客業務区分を取得する。<BR> 
     * <BR>
     * this.Y客マスタ行.業務区分を返却する。<BR>
     * @@return String
     */
    public String getOperationDiv()
    {
        return this.yCustomerParams.getOperationDiv();
    }
    
    /**
     * (get顧客姓（カナ）)<BR>
     * 顧客姓（カナ）を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.顧客姓（カナ）を返却する。<BR> 
     * @@return String
     */
    public String getFamilyNameAlt1()
    {
        return this.expAccountOpenParams.getFamilyNameAlt1();    
    }
    
    /**
     * (get顧客名（カナ）)<BR>
     * 顧客名（カナ）を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.顧客名（カナ）を返却する。<BR> 
     * @@return String
     */
    public String getGivenNameAlt1()
    {
        return this.expAccountOpenParams.getGivenNameAlt1();    
    }
    
    /**
     * (get住所１)<BR>
     * 住所１を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.住所１を返却する。<BR> 
     * @@return String
     */
    public String getAddressLine1()
    {
        return this.expAccountOpenParams.getAddressLine1();   
    }
    
    /**
     * (get住所２)<BR>
     * 住所２を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.住所２を返却する。<BR> 
     * @@return String
     */
    public String getAddressLine2()
    {
        return this.expAccountOpenParams.getAddressLine2();     
    }
    
    /**
     * (get住所３)<BR>
     * 住所３を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.住所３を返却する。<BR> 
     * @@return String
     */
    public String getAddressLine3()
    {
        return this.expAccountOpenParams.getAddressLine3();       
    }
    
    /**
     * (get生年月日)<BR>
     * 生年月日を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.生年月日を返却する。<BR> 
     * @@return String
     */
    public String getBornDate()
    {
        return this.expAccountOpenParams.getBornDate();        
    }
    
    /**
     * (get生年月日年号)<BR>
     * 生年月日年号を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.生年月日年号を返却する。<BR> 
     * @@return String
     */
    public String getEraBorn()
    {
        return this.expAccountOpenParams.getEraBorn();   
    }
    
    /**
     * (get電話番号)<BR>
     * 電話番号を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.電話番号を返却する。 <BR>
     * @@return String
     */
    public String getTelephone()
    {
        return this.expAccountOpenParams.getTelephone();    
    }
    
    /**
     * (get連絡先電話番号（携帯）)<BR>
     * 連絡先電話番号（携帯）を取得する。<BR> 
     * <BR>
     * this.口座開設見込客行.連絡先電話番号（携帯）を返却する。<BR>
     * @@return String 
     */
    public String getMobile()
    {
        return this.expAccountOpenParams.getMobile();       
    }
    
	/**
	 * (getFAX番号)<BR>
	 * FAX番号を取得する。<BR> 
	 * <BR>
	 * this.口座開設見込客行.FAX番号を返却する。 <BR>
	 * @@return String
	 */
	public String getFax()
	{
		return this.expAccountOpenParams.getFax();    
	}
    
	/**
	 * (get世帯主勤務先電話番号)<BR>
	 * 世帯主勤務先電話番号を取得する。<BR> 
	 * <BR>
	 * this.口座開設見込客行.世帯主勤務先電話番号を返却する。<BR>
	 * @@return String 
	 */
	public String getHouseholder_Office_Tel()
	{
		return this.expAccountOpenParams.getHouseholderOfficeTel();       
	}
    
    /**
     * (validate顧客コード)<BR>
     * 同一顧客が重複して登録されていないかチェックする。<BR> 
     * <BR>
     * <BR>
     * １）　@口座コード重複チェック（見込客） <BR>
     * 　@以下の条件で口座開設見込客テーブルを検索する。 <BR>
     * 該当行が存在し、該当行の識別コードがthis.get識別コード()と異なる場合、<BR> 
     * 顧客が重複して登録されていると判定し例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01313<BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@口座開設見込客.証券会社コード = this.get証券会社コード()<BR> 
     * 　@口座開設見込客.部店コード = this.get部店コード() <BR>
     * 　@口座開設見込客.口座コード = this.get口座コード()<BR>
     * <BR>
     * ２）　@口座コード重複チェック（顧客マスタ） <BR>
     * 　@以下の条件で顧客マスタテーブルを検索する。 <BR>
     * 該当行が存在し、該当行の口座登録日がnullでない、かつ、該当行の口座登録日が処理日以外の場合、<BR>
     * 顧客が重複して登録されていると判定し、例外をスローする。<BR> 
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01313<BR>
     * <BR>         
     * 　@[条件] <BR>
     * 　@顧客マスタ.証券会社コード = this.get証券会社コード() <BR>
     * 　@顧客マスタ.部店コード = this.get部店コード() <BR>
     * 　@顧客マスタ.口座コード like this.get口座コード() + '%' <BR>
     * @@throws WEB3BaseException 
     */
    public void validateAccountCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateAccountCode()";
        log.entering(STR_METHOD_NAME);
        
        String l_strInstitutionCode = this.getInstitutionCode();
        String l_strBranchCode = this.getBranchCode();
        String l_strAccountCode = this.getAccountCode();
        try 
        {
            //１）　@口座コード重複チェック（見込客） 
            String l_strWhere = null;
            Object[] l_obj = null;
            
            if (l_strAccountCode != null && l_strAccountCode.length() != 0)
            {
                l_strWhere = 
                    "institution_code = ? and branch_code = ? and account_code = ? and acc_open_request_number <> ?";
                l_obj = 
                    new Object[]{ l_strInstitutionCode, l_strBranchCode, l_strAccountCode, this.getRequestNumber() };
    
                List l_lisRow = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        ExpAccountOpenRow.TYPE, 
                        l_strWhere, l_obj);
    
                if (l_lisRow != null && l_lisRow.size() > 0)
                {
                    log.debug("顧客が重複して登録されていると判定し例外をスローする。");
    
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                        this.getClass().getName() + STR_METHOD_NAME,
                        "顧客が重複して登録されていると判定し例外をスローする。");
                }
                
                //２） 口座コード重複チェック（顧客マスタ）
                String l_strMainAccountWhere = 
                    " institution_code = ? and branch_code = ? and account_code like ? ";
    
                Object[] l_objMainAccount = 
                    { l_strInstitutionCode, l_strBranchCode, l_strAccountCode + "%" };
                
                List l_lisMainAccountRow = 
                    Processors.getDefaultProcessor().doFindAllQuery(
                        MainAccountRow.TYPE, 
                        l_strMainAccountWhere, 
                        l_objMainAccount);

                // 該当行が存在し、該当行の口座登録日がnullでない、かつ、
                // 該当行の口座登録日が処理日以外の場合、
                Date l_datProcessDate = GtlUtils.getSystemTimestamp();

                if (l_lisMainAccountRow != null && l_lisMainAccountRow.size() > 0)
                {
                    int l_intLength = l_lisMainAccountRow.size();
                    for (int i = 0; i < l_intLength; i++)
                    {
                        Date l_datAccountOpenDate =
                            ((MainAccountRow) l_lisMainAccountRow.get(i)).getAccountOpenDate();
                        if ( l_datAccountOpenDate != null
                            && WEB3DateUtility.compareToDay(l_datAccountOpenDate, l_datProcessDate) != 0)
                        {
                            log.debug("顧客が重複して登録されていると判定し、例外をスローする。");
    
                            log.exiting(STR_METHOD_NAME);
                            throw new WEB3BusinessLayerException(
                                WEB3ErrorCatalog.BUSINESS_ERROR_01313,
                                this.getClass().getName() + STR_METHOD_NAME,
                                "該当行の口座登録日がnullでない場合" + "顧客が重複して登録されていると判定し例外をスローする。");
                        }
                    }
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get口座開設審査待ち情報リスト)<BR>
     * 口座開設審査待ち情報リストを取得する。<BR>
     * @@return ArrayList 
     */
    public ArrayList getAccOpenJudgeWaitingInfoList()
    {
        return this.accOpenJudgeWaitingInfoList;      
    }
    
    /**
     * (set口座開設審査待ち情報リスト)<BR>
     * 口座開設審査待ち情報リストに審査待ち情報を追加する。<BR> 
     * <BR>
     * １）口座開設審査待ちParamsオブジェクトを生成しプロパティを設定する。<BR> 
     * <BR>
     * 証券会社コード：this.get証券会社コード()<BR>  
     * 識別コード：this.get識別コード() <BR>
     * 通番：口座開設審査待ち情報リストの長さ+1<BR> 
     * 部店コード：null <BR>
     * 顧客コード：null <BR>
     * 審査種別：（引数）審査種別 <BR>
     * 重複区分：（引数）重複区分 <BR>
     * 重複対象部店コード：（引数）重複部店コード <BR>
     * 重複対象顧客コード：（引数）重複顧客コード (左6桁)<BR>
     * Y客情報・Y客ID：（引数）Y客ID <BR>
     * Y客情報・管理部店コード：（引数）管理部店コード <BR>
     * Y客情報・業務区分：（引数）業務区分 <BR>
     * Y客情報・管理No．：（引数）管理No． <BR>
     * 審査区分："0" <BR>
     * 審査者コード：null <BR>
     * <BR>
     * ２）口座開設審査待ちParamsオブジェクトを口座開設審査待ち情報リストへ追加する。<BR>
     * @@param l_strReviewCode - (審査種別)<BR>
     * 審査種別。<BR>
     * @@param l_strDuplicateDiv - (重複区分)<BR>
     * 重複区分。<BR>
     * @@param l_strDuplicateBranchCode - (重複部店コード)<BR>
     * 重複部店コード。<BR>
     * @@param l_strDuplicateAccountCode - (重複顧客コード)<BR>
     * 重複顧客コード。<BR>
     * @@param l_yCustomerId - (Y客ID)<BR>
     * Y客情報・Y客ID。<BR>
     * @@param l_strControlBranchCode - (管理部店コード)<BR>
     * Y客情報・管理部店コード。<BR>
     * @@param l_strOperationDiv - (業務区分)<BR>
     * Y客情報・業務区分。<BR>
     * @@param l_controlNumber - (管理No.)<BR>
     * Y客情報・管理No.<BR>
     */
    private void setAccOpenJudgeWaitingInfoList(
        String l_strReviewCode,
        String l_strDuplicateDiv,
        String l_strDuplicateBranchCode,
        String l_strDuplicateAccountCode,
        Long l_yCustomerId,
        String l_strControlBranchCode,
        String l_strOperationDiv,
        Long l_controlNumber)
    {
        final String STR_METHOD_NAME = 
            "setAccOpenValidationWaitingInfoList(String,String,String,String,Long,String,Long)";
        log.entering(STR_METHOD_NAME);
        
        if (this.accOpenJudgeWaitingInfoList == null) 
        {
            this.accOpenJudgeWaitingInfoList = new ArrayList();
        }
        
        //１）口座開設審査待ちParamsオブジェクトを生成しプロパティを設定する。
        AccOpenWaitingParams l_waitingParams = new AccOpenWaitingParams();
        
        //証券会社コード：this.get証券会社コード()
        l_waitingParams.setInstitutionCode(this.getInstitutionCode());
        
        //識別コード：this.get識別コード() 
        l_waitingParams.setAccOpenRequestNumber(this.getRequestNumber());
        
        //通番：口座開設審査待ち情報リストの長さ+1 
        l_waitingParams.setSerialNo(String.valueOf(this.accOpenJudgeWaitingInfoList.size() + 1));
        
        //部店コード：null 
        l_waitingParams.setBranchCode(null);
        
        //顧客コード：null 
        l_waitingParams.setAccountCode(null);
        
        //審査種別：（引数）審査種別 
        l_waitingParams.setReviewCode(l_strReviewCode);
        
        //重複区分：（引数）重複区分 
        l_waitingParams.setDuplicationDiv(l_strDuplicateDiv);
        
        //重複対象部店コード：（引数）重複部店コード 
        l_waitingParams.setDiploBranchCode(l_strDuplicateBranchCode);
        
        //重複対象顧客コード：（引数）重複顧客コード 
        if (l_strDuplicateAccountCode != null && l_strDuplicateAccountCode.length() > 6)
        {
			l_waitingParams.setDiploAccountCode(l_strDuplicateAccountCode.substring(0, 6));
        }
        else
        {
			l_waitingParams.setDiploAccountCode(l_strDuplicateAccountCode);
        }
        
        //Y客情報・Y客ID：（引数）Y客ID 
        l_waitingParams.setYCustomerId(l_yCustomerId);
        
        //Y客情報・管理部店コード：（引数）管理部店コード 
        l_waitingParams.setControlBranchCode(l_strControlBranchCode);
        
        //Y客情報・業務区分：（引数）業務区分 
        l_waitingParams.setOperationDiv(l_strOperationDiv);
        
        //Y客情報・管理No．：（引数）管理No． 
        if (l_controlNumber != null) 
        {
            l_waitingParams.setControlNumber(l_controlNumber.intValue());   
        }
        
        //審査区分："0"
        l_waitingParams.setCheckDiv(WEB3CheckDivDef.CHECK_WAITING);
        
        //審査者コード：null 
        l_waitingParams.setCheckerCode(null);
        
        //２）口座開設審査待ちParamsオブジェクトを口座開設審査待ち情報リストへ追加する。
        this.accOpenJudgeWaitingInfoList.add(l_waitingParams);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate電話番号)<BR>
     * 入力した電話番号＆携帯電話番号が登録されていないかチェックする。<BR> 
     * 既に登録されている場合、以下２つの処理を行う。 <BR>
     * ①@警告文を返却する。 <BR>
     * ②部店プリファ@レンスの値>1の場合口座開設審査待ち情報リストへ重複顧客情報を保存する。<BR> 
     * (set口座開設審査待ち情報リスト()) <BR>
     * <BR>
     * １） 戻り値用ArrayListを生成する。 <BR>
     * <BR>
     * ２） 電話番号の重複確認を行う。<BR> 
     * 　@２－１） 口座開設電話番号重複チェッククラスを生成する。<BR> 
     * <BR>
     * 　@　@[口座開設共通サービス#to口座開設電話番号重複チェック()に指定する引数]<BR> 
     * 　@　@　@顧客コード：this.口座コード()の戻り値 <BR>
     * 　@　@　@識別コード：this.識別コード()の戻り値 <BR>
     * 　@　@　@証券会社コード：this.get証券会社コード()の戻り値<BR> 
     *        <BR>
     *   ２－２） 見込客電話番号検索列の設定<BR>
     *            (20060728現在0E仕様。会社により検索列に変更がある場合は要修正)<BR>
     * <BR>
     *     ２－２－１） （引数）口座区分が個人の場合<BR>
     *  <BR>
     *          [口座開設電話番号重複チェック#set見込客電話番号検索列()に指定する引数]<BR>
     *            電話番号検索列：長さ の"mobile"を要素としたString配列<BR>
     * <BR>
     *     ２－２－２） （引数）口座区分が法@人の場合<BR>
     * <BR>
     *          [口座開設電話番号重複チェック#set見込客電話番号検索列()に指定する引数]<BR>
     *            電話番号検索列：長さ3 の"mobile","fax","householder_office_tel"を要素としたString配列<BR>
     * <BR>
     *   ２－３） 電話番号の重複チェックを行う。<BR>
     * 
     *      [口座開設電話番号重複チェック#get重複顧客情報()に指定する引数]<BR>
     *         電話番号：this.get電話番号()の戻り値<BR>
     * <BR>
     *   ２－４） 重複電話番号が存在する（口座開設電話番号重複チェック#get重複顧客情報()の戻り値の長さ>0）場合、<BR>
     *     ２－４－１） （引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する。<BR>
     *               (set口座開設審査待ち情報リスト()）。
     * <BR>
     *        ２－４－１－１） 審査種別の判定<BR>
     *           口座開設電話番号重複チェック#getテーブル名()の戻り値が<BR>
     *           口座開設重複チェックユーティリティ.MST_NM_EXP_ACC_OPENの場合、"2"<BR>
     *           それ以外の場合は"1"を設定する。<BR>
     * <BR>
     *        ２－４－２－２） 重複顧客情報を保存<BR>
     * <BR>
     *           [set口座開設審査待ち情報リスト()に指定する引数]<BR>
     *             審査種別： ２－３－１） の判定値<BR>
     *             重複区分："3"<BR>
     *             重複部店コード:口座開設電話番号重複チェック#get部店コード()の戻り値<BR>
     *             重複顧客コード:口座開設電話番号重複チェック#get顧客コード()の戻り値<BR>
     *             Y客ID：null<BR>
     *             管理部店コード：null<BR>
     *             業務区分：null<BR>
     *             管理No．：null<BR>
     * <BR>
     *   ２－５）   ２－４）において重複電話番号が存在する場合、「WARNING_60012：電話番号重複の可能性あり」、<BR>
     *              の警告メッセージをArrayListに追加する。<BR>
     * <BR>
     * <BR>
     * ３） 携帯電話番号の重複確認を行う。<BR> 
     * 　@３－１） 携帯電話番号の重複チェックを行う。<BR> 
     * <BR>
     * 　@　@[口座開設電話番号重複チェック#get重複顧客情報()に指定する引数] <BR>
     * 　@　@　@電話番号：this.get連絡先電話番号（携帯）()の戻り値 <BR>
     * <BR>
     * 　@３－２） 重複電話番号が存在する<BR>
     * 　@（口座開設電話番号重複チェック#get重複顧客情報()の戻り値の長さ>0）場合、<BR> 
     * 　@　@３－２－１） （引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する。<BR> 
     * 　@　@　@　@　@　@　@　@　@(set口座開設審査待ち情報リスト()）。 <BR>
     * <BR>
     * 　@　@　@３－２－１－１） 審査種別の判定 <BR>
     * 　@　@　@　@口座開設電話番号重複チェック#getテーブル名()の戻り値が <BR>
     * 　@　@　@　@口座開設重複チェックユーティリティ.MST_NM_EXP_ACC_OPENの場合、"2"<BR> 
     * 　@　@　@　@それ以外の場合は"1"を設定する。 <BR>
     * <BR>
     * 　@　@　@３－２－２－２） 重複顧客情報を保存<BR> 
     *              <BR>
     * 　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数]<BR> 
     * 　@　@　@　@　@審査種別： ３－２－１） の判定値 <BR>
     * 　@　@　@　@　@重複区分："3" <BR>
     * 　@　@　@　@　@重複部店コード:口座開設電話番号重複チェック#get部店コード()の戻り値<BR> 
     * 　@　@　@　@　@重複顧客コード:口座開設電話番号重複チェック#get顧客コード()の戻り値 <BR>
     * 　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@管理部店コード：null<BR> 
     * 　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@管理No．：null <BR>
     * <BR>
     *   ３－３）  ３－２）において重複電話番号が存在する場合、<BR>
     *   　@　@　@「WARNING_60013：　@携帯電話番号重複の可能性あり」、<BR> 
     * 　@　@　@　@　@の警告メッセージをArrayListに追加する。<BR> 
     * 
     * <BR>
     * ４） ArrayListを返却する。<BR>
     * @@param l_intBranchPreference - (部店プリファ@レンス)<BR>
     * @@param l_strAccountDiv - (口座区分)
     * 部店プリファ@レンス値 <BR>
     * <BR>
     * [設定値] <BR>
     * 0：チェック不要-アラート表示無-審査待ちUPDATE無 <BR>
     * 1：チェック実行-アラート表示有-審査待ちUPDATE無 <BR>
     * 2：チェック実行-アラート表示無-審査待ちUPDATE有 <BR>
     * 3：チェック実行-アラート表示有-審査待ちUPDATE有<BR>
     * @@return ArrayList
     * @@throws WEB3BaseException 
     */
    public ArrayList validateTelephone(int l_intBranchPreference, String l_strAccountDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateTelephone(int)";
        log.entering(STR_METHOD_NAME);
       
        //１） 戻り値用ArrayListを生成する。 
        ArrayList l_lisWarnMsg = new ArrayList();
        
        //２） 電話番号の重複確認を行う。 
        //  ２－１） 口座開設電話番号重複チェッククラスを生成する。 
        WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
            Services.getService(WEB3AccOpenInfoCreatedService.class); 
        WEB3AccOpenTelNumberDuplicationCheck l_accOpenTelNumberDuplicationCheck = 
            l_service.toAccOpenTelNumberDuplicationCheck(
                this.getAccountCode(),
                this.getRequestNumber(),
                this.getInstitutionCode());
        
		//  ２－２） 見込客電話番号検索列の設定
		//		     (20060728現在0E仕様。会社により検索列に変更がある場合は要修正)
		if (WEB3AccOpenDuplicationCheckUtility.EXP_ACC_OPEN_ACCOUNT_DIV_INDV.equals(l_strAccountDiv))
		{
			//    ２－２－１） （引数）口座区分が個人の場合
			String[] l_strRow = {"mobile"};
			l_accOpenTelNumberDuplicationCheck.setExpOpenAccExqRow(l_strRow);
		}
		else if (WEB3AccOpenDuplicationCheckUtility.EXP_ACC_OPEN_ACCOUNT_DIV_COP.equals(l_strAccountDiv))
		{
			//    ２－２－２） （引数）口座区分が法@人の場合
			String[] l_strRow = {"mobile","fax","householder_office_tel"};
			l_accOpenTelNumberDuplicationCheck.setExpOpenAccExqRow(l_strRow);			
		}
        
        //   ２－３） 電話番号の重複チェックを行う。 
        //      [口座開設電話番号重複チェック#get重複顧客情報()に指定する引数] 
        //        電話番号：this.get電話番号()の戻り値 
        Object[] l_objDuplicatedTels = 
            l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(this.getTelephone());
        
        //   ２－４） 重複電話番号が存在する場合、
        if (l_objDuplicatedTels != null && l_objDuplicatedTels.length > 0)
        {                 
            //２－４－１） （引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する
            if (l_intBranchPreference > 1) 
            {
                int l_intLength = l_objDuplicatedTels.length;
                for (int i = 0; i < l_intLength; i++)
                {
                    String l_strTableName = 
                        l_accOpenTelNumberDuplicationCheck.getTableName(l_objDuplicatedTels[i]);
                    String l_strAccountCode = 
                        l_accOpenTelNumberDuplicationCheck.getAccountCode(l_objDuplicatedTels[i]);
                    String l_strBranchCode = 
                        l_accOpenTelNumberDuplicationCheck.getBranchCode(l_objDuplicatedTels[i]);
                    
                    //２－４－１－１） 審査種別の判定 
                    String l_strReviewCode = null;
                    if (WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN.equals(l_strTableName)) 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP;
                    }
                    else 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN;
                    }

                    //２－４－２－２） 重複顧客情報を保存 
                    //(set口座開設審査待ち情報リスト()）。
                    this.setAccOpenJudgeWaitingInfoList(
                        l_strReviewCode, 
                        WEB3DuplicationDivDef.TELEPHONE_NO,
                        l_strBranchCode,
                        l_strAccountCode,
                        null,
                        null,
                        null,
                        null);
                }
            }
        
            //   ２－５）   ２－４）において重複電話番号が存在する場合、 
            //  「WARNING_60012：　@電話番号重複の可能性あり」の警告メッセージをArrayListに追加する。 
            l_lisWarnMsg.add(WEB3ErrorCatalog.WARNING_60012.getErrorCode());
        }
        
        //３） 携帯電話番号の重複確認を行う。 
        //  ３－１） 携帯電話番号の重複チェックを行う。 
		ArrayList l_objDupliMobileList=new ArrayList();
		//    [口座開設電話番号重複チェック#get重複顧客情報()に指定する引数] 
		//      電話番号：this.get連絡先電話番号（携帯）()の戻り値 
		Object[] l_tempArray = 
				l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(this.getMobile());
		if (l_tempArray != null) l_objDupliMobileList.addAll(Arrays.asList(l_tempArray));
		
		if (WEB3AccOpenDuplicationCheckUtility.EXP_ACC_OPEN_ACCOUNT_DIV_COP.equals(l_strAccountDiv))
		{
			//    ３－１－１） （引数）口座区分が法@人の場合、以下処理を追加
			//		     (20060728現在0E仕様。会社により検索列に変更がある場合は要修正)
			
			//    [口座開設電話番号重複チェック#get重複顧客情報()に指定する引数] 
			//      電話番号：this.getFAX番号()の戻り値 
			l_tempArray = l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(this.getFax());
			if (l_tempArray != null) l_objDupliMobileList.addAll(Arrays.asList(l_tempArray));
			//    [口座開設電話番号重複チェック#get重複顧客情報()に指定する引数] 
			//      電話番号：this.get世帯主勤務先電話番号()の戻り値 
			l_tempArray = l_accOpenTelNumberDuplicationCheck.getDuplicationAccountInfo(
					this.getHouseholder_Office_Tel());
			if (l_tempArray != null) l_objDupliMobileList.addAll(Arrays.asList(l_tempArray));
		}
        
		Object[] l_objDuplicatedMobiles=l_objDupliMobileList.toArray();

        //  ３－２） 重複電話番号が存在する場合、
        if (l_objDuplicatedMobiles != null && l_objDuplicatedMobiles.length > 0) 
        {             
            //３－２－１） （引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する。
            if (l_intBranchPreference > 1)
            {
                for (int i = 0; i < l_objDuplicatedMobiles.length; i++)
                {
                    String l_strTableName = 
                        l_accOpenTelNumberDuplicationCheck.getTableName(l_objDuplicatedMobiles[i]);
                    
                    String l_strAccountCode = 
                        l_accOpenTelNumberDuplicationCheck.getAccountCode(l_objDuplicatedMobiles[i]);
                    String l_strBranchCode = 
                        l_accOpenTelNumberDuplicationCheck.getBranchCode(l_objDuplicatedMobiles[i]);
                    
                    //３－２－１－１） 審査種別の判定
                    String l_strReviewCode = null;
                    if (WEB3AccOpenDuplicationCheckUtility.MST_NM_EXP_ACC_OPEN.equals(l_strTableName)) 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP;
                    }
                    else 
                    {
                        l_strReviewCode = WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN;
                    }

                    //３－２－２－２） 重複顧客情報を保存  
                    //(set口座開設審査待ち情報リスト()）。
                    this.setAccOpenJudgeWaitingInfoList(
                        l_strReviewCode, 
                        WEB3DuplicationDivDef.TELEPHONE_NO,
                        l_strBranchCode,
                        l_strAccountCode,
                        null,
                        null,
                        null,
                        null);
                }
            }
        
            //  ３－３）  ３－２）において重複電話番号が存在する場合、 
            l_lisWarnMsg.add(WEB3ErrorCatalog.WARNING_60013.getErrorCode());
        }
        
        //４） ArrayListを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_lisWarnMsg;         
    }
    

	/**
	 * (get口座開設審査区分)<BR>
	 * 口座開設審査待ちテーブル上で証券会社コード、識別コードをキーとして <BR>
     * レコードを検索し、審査区分を取得し返却する。 <BR>
     * レコードが存在しない場合はnullを返却する。 <BR>
	 * <BR>
	 * <BR>
	 * (1) 口座開設情報作成サービス.to口座開設審査待ち()<BR>
	 * 　@　@から口座開設審査待ちオブジェクトを生成する。<BR> 
	 * <BR>
	 * (2) this.get識別コード()を条件とし、<BR>
	 * 　@　@口座開設審査待ちテーブルより該当顧客のレコードを取得する。<BR> 
	 * 　@　@　@(2-1) 取得した識別コードを長さ１のString配列の要素に設定する。<BR>
	 * 　@　@　@(2-2)口座開設審査待ち.select審査対象一覧(get証券会社コード(), (2-1)で作成したString配列)<BR>  
	 * <BR>
	 * (3) 口座開設審査待ちレコードが存在する場合、（口座開設審査待ち.get口座開設審査待ち行数() > 0 ）<BR> 
	 *      取得した行の1レコード目の審査区分を取得（口座開設審査待ち.get審査区分(0)）し、返却する。 <BR>
	 * <BR>
	 * (4) 口座開設審査待ちレコードが存在しない場合 <BR>
	 *     （口座開設審査待ち.get口座開設審査待ち行数() == 0 ）、nullを返却する。<BR>
	 * <BR>
	 * <BR>
     * @@return String                 
     * @@throws WEB3BaseException 
 	 */
    public String getAccountOpenStatusJudgeDiv() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "getAccountOpenStatusJudgeDiv()";
		log.entering(STR_METHOD_NAME);
       
		//(1) 口座開設情報作成サービス.to口座開設審査待ち()から
		//　@　@口座開設審査待ちオブジェクトを生成する。 
		WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
		Services.getService(WEB3AccOpenInfoCreatedService.class); 
        
		WEB3AccOpenJudgeWaiting l_accOpenJudgeWaiting = 
			     l_service.toAccOpenJudgeWaiting();
    
		//(2) this.get識別コード()を条件とし、
		//口座開設審査待ちテーブルより該当顧客のレコードを取得する。 
		//(2-1) 取得した識別コードを長さ１のString配列の要素に設定する。
		String[] l_strRequestNumbers = new String[1];
		l_strRequestNumbers[0] = this.getRequestNumber();
        
		//(2-2)口座開設審査待ち.select審査対象一覧(get証券会社コード, (2-1)で作成したString配列)
		l_accOpenJudgeWaiting.selectJudgeObjectList(
				  this.getInstitutionCode(), l_strRequestNumbers);
        
		//(3) 口座開設審査待ちレコードが存在する場合、（口座開設審査待ち.get口座開設審査待ち行数() > 0 ） 
		//取得した行の1レコード目の審査区分を取得（口座開設審査待ち.get審査区分(0)）し、返却する。 
		//(4) 口座開設審査待ちレコードが存在しない場合 
		//	（口座開設審査待ち.get口座開設審査待ち行数() == 0 ）、nullを返却する。
		int l_intRecord = l_accOpenJudgeWaiting.getAccOpenWaitingParamsNumber();
        
		if (l_intRecord > 0) 
		{
			log.exiting(STR_METHOD_NAME);
			return l_accOpenJudgeWaiting.getCheckDiv(0);
		}
		else 
		{
			log.exiting(STR_METHOD_NAME);
			return null;  
		}
    }
    
    
    
    /**
     * (get口座開設状況審査区分)<BR>
     * 口座開設状況を判定する。<BR>
     * <BR>
     * [戻り値]<BR>
     * 　@0：　@DEFAULT（未開設)<BR>
     * 　@1：　@開設中<BR>
     * 　@2：　@エラー発生<BR>
     * 　@3：　@開設済<BR>
     * 　@4：　@審査中<BR>
     * 　@5：　@却下<BR>
     * <BR>
     * <BR>
     * (1) 審査待ちレコードを検索（this.口座開設審査区分()）取得した審査区分により以下処理を行う。 <BR> 
     * <BR>
     * ・審査区分が "0"の場合⇒"4"を返却する（審査待ち）。 <BR> 
     * ・審査区分が "2"の場合⇒"5"を返却する（却下）。 <BR> 
     * ・審査区分が"1" or null の場合⇒口座開設見込客.口座開設状況区分()の戻り値を返却する。<BR> 
     * @@return String                 
     * @@throws WEB3BaseException 
     */
    public String getAccountOpenStatusCheckDiv() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAccountOpenStatusJudgeDiv()";
        log.entering(STR_METHOD_NAME);
       
            String l_strCheckDiv = getAccountOpenStatusJudgeDiv();
            
            //(1) 審査待ちレコードを検索（this.口座開設審査区分()）取得した審査区分により以下処理を行う。 
            //     ・審査区分が "0"の場合⇒"4"を返却する（審査待ち）。 
            //     ・審査区分が "2"の場合⇒"5"を返却する（却下）。 
            //     ・審査区分が"1"又は 
            //       審査区分が"1" or null の場合⇒口座開設見込客.口座開設状況区分()の戻り値を返却する。 
 
            if (WEB3CheckDivDef.CHECK_WAITING.equals(l_strCheckDiv)) 
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountOpenStatusDivDef.JUDGEING;
            }
            else if (WEB3CheckDivDef.DISAGREE.equals(l_strCheckDiv)) 
            {
                log.exiting(STR_METHOD_NAME);
                return WEB3AccountOpenStatusDivDef.REJECTION;
            } 
            else
            {
                log.exiting(STR_METHOD_NAME);
                return this.getAccountOpenStatusDiv();  
            }
    }

    /**
     * (validateメールアドレス )<BR>
     * 入力したメールアドレスが登録されていないかチェックする。<BR> 
     * 既に登録されている場合、以下２つの処理を行う。<BR> 
     * ①@警告文を返却する。 <BR>
     * ②部店プリファ@レンスの値>1の場合口座開設審査待ち情報リストへ重複顧客情報を保存する。<BR> 
     * (set口座開設審査待ち情報リスト()) <BR>
     * <BR>
     * １） 顧客マスタにメールアドレスが存在するかチェックする。<BR> 
     * 　@１－１） webbroker3.gentrade.メールアドレス重複チェッククラスを使用する。<BR> 
     * <BR>
     * 　@　@[メールアドレス重複チェック.get重複アドレス()に指定する引数]<BR> 
     * 　@　@　@メールアドレス：this.getemailアドレス()の戻り値 <BR>
     * 　@　@　@顧客コード：this.口座コード()の戻り値 <BR>
     * 　@　@　@部店コード：this.get部店コード()の戻り値 <BR>
     * 　@　@　@証券会社コード：this.get証券会社コード()の戻り値 <BR>
     * <BR>
     *   １－２） 重複アドレスが存在する（メールアドレス重複チェック.get重複アドレス()<BR>
     *   　@　@　@　@の戻り値の長さ>0）場合 <BR>
     *     １－２－１） （引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する。<BR> 
     *                   (set口座開設審査待ち情報リスト()）。 <BR>
     *              <BR>
     * 　@　@　@　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数] <BR>
     * 　@　@　@　@　@　@　@　@　@審査種別："1" <BR>
     * 　@　@　@　@　@　@　@　@　@重複区分："2" <BR>
     * 　@　@　@　@　@　@　@　@　@重複部店コード:メールアドレス重複チェック.get部店コード()の戻り値<BR> 
     * 　@　@　@　@　@　@　@　@　@重複顧客コード:メールアドレス重複チェック.get顧客コード()の戻り値 <BR>
     * 　@　@　@　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@　@　@　@管理部店コード：null <BR>
     * 　@　@　@　@　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@　@　@　@　@管理No．：null <BR>
     * <BR>
     * <BR>
     * ２） 見込客ファ@イルにメールアドレスが存在するかチェックする <BR>
     * 　@２－１） 口座開設メールアドレス重複チェッククラスを生成する。<BR> 
     * <BR>
     * 　@　@[口座開設共通サービス#to口座開設メールアドレス重複チェック()に指定する引数]<BR> 
     * 　@　@　@顧客コード：this.口座コード()の戻り値 <BR>
     * 　@　@　@識別コード：this.識別コード()の戻り値 <BR>
     * 　@　@　@証券会社コード：this.get証券会社コード()の戻り値<BR> 
     *        <BR>
     * 　@２－２） 重複メールアドレスのチェックを行う。<BR>
     * 　@　@　@　@　@（口座開設メールアドレス重複チェック#get重複顧客情報()）<BR> 
     * <BR>
     * 　@　@[口座開設メールアドレス重複チェック#get重複顧客情報()に指定する引数] <BR>
     * 　@　@　@メールアドレス：this.getemailアドレス()の戻り値 <BR>
     * <BR>
     * 　@２－３） 重複アドレスが存在する（口座開設メールアドレス重複チェック.get重複顧客情報()<BR>
     *   　@　@　@　@　@の戻り値の長さ>0）場合 <BR>
     * 　@　@２－３－１） （引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する。<BR> 
     *                  (set口座開設審査待ち情報リスト()）。 <BR>
     *              <BR>
     * 　@　@　@　@　@　@　@[set口座開設審査待ち情報リスト()に指定する引数] <BR>
     * 　@　@　@　@　@　@　@　@審査種別："2" <BR>
     * 　@　@　@　@　@　@　@　@重複区分："2" <BR>
     * 　@　@　@　@　@　@　@　@重複部店コード:口座開設メールアドレス重複チェック#get部店コード()の戻り値<BR> 
     * 　@　@　@　@　@　@　@　@重複顧客コード:口座開設メールアドレス重複チェック#get顧客コード()の戻り値<BR> 
     * 　@　@　@　@　@　@　@　@Y客ID：null <BR>
     * 　@　@　@　@　@　@　@　@管理部店コード：null <BR>
     * 　@　@　@　@　@　@　@　@業務区分：null <BR>
     * 　@　@　@　@　@　@　@　@管理No．：null <BR>
     * <BR>
     * ３） 戻り値 <BR>
     * <BR>
     *   ３－１）  １－２）、２－３）のいづれかに於いて重複アドレスが存在した場合、<BR> 
     * 　@　@　@「WARNING_60011：　@メールアドレス重複の可能性あり」を返却する。 <BR>
     * <BR>
     *   ３－２）  １－２）、２－３）の全てに於いて、重複アドレスが存在しなかった場合、<BR>
     *   　@　@nullを返却する。<BR>
     * @@param l_intBranchPreference - (部店プリファ@レンス)<BR>
     * 部店プリファ@レンス値 <BR>
     * <BR>
     * [設定値] <BR>
     * 0：チェック不要-アラート表示無-審査待ちUPDATE無<BR> 
     * 1：チェック実行-アラート表示有-審査待ちUPDATE無<BR>
     * 2：チェック実行-アラート表示無-審査待ちUPDATE有<BR> 
     * 3：チェック実行-アラート表示有-審査待ちUPDATE有<BR>
     * @@return String 
     * @@throws WEB3BaseException 
     */
    public String validateMailAddress(int l_intBranchPreference) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMailAddress(int)";
        log.entering(STR_METHOD_NAME);
       
        //１） 顧客マスタにメールアドレスが存在するかチェックする。
        // １－１）メールアドレス重複チェック.get重複アドレス()
			//仕様変更20060623SCSInomata-->
//	        Object[] l_objDuplicatedAddresses =
//            WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
//                this.getEmailAddress(),
//                this.getAccountCode(),
//                this.getBranchCode(),
//                this.getInstitutionCode());
			Object[] l_objDupliAdd =
			WEB3GentradeMailAddressDuplicationCheck.getDuplicateAddress(
				this.getEmailAddress(),
				null,
				this.getBranchCode(),
				this.getInstitutionCode());
			//<--仕様変更20060623SCSInomata
		       
        boolean l_blnDuplicateFlag = false;
        
        String l_strAccountCode = null;
        String l_strBranchCode = null;
        
        //  １－２） 重複アドレスが存在する場合 
        if (l_objDupliAdd != null && l_objDupliAdd.length > 0) 
        {   
			//仕様変更20060623SCSInomata-->
			//メールアドレス重複チェッククラスへ顧客コードが全てnullで渡される為
			//顧客マスタに対する検索条件が証券会社コード&&メールアドレスとなり、<BR>
			//問合せ元情報が含まれる可能性があるので問合せ元重複情報の削除を行う。
			Object[] l_objDuplicatedAddresses = 
				WEB3GentradeMailAddressDuplicationCheck.removeDuplicationInfo(
				l_objDupliAdd, 
				this.getAccountCode(), 
				this.getBranchCode());
			//戻り値がnullで無い場合処理続行
			if (l_objDuplicatedAddresses != null)
			{
			//<--仕様変更20060623SCSInomata
				l_blnDuplicateFlag = true;
	            
				if (l_intBranchPreference > 1) 
				{         
					for (int i = 0; i < l_objDuplicatedAddresses.length; i++)
					{
						if (l_objDuplicatedAddresses[i] != null) 
						{
							l_strAccountCode = 
								WEB3GentradeMailAddressDuplicationCheck.getAccountCode(
									l_objDuplicatedAddresses[i]);
							l_strBranchCode = 
								WEB3GentradeMailAddressDuplicationCheck.getBranchCode(
									l_objDuplicatedAddresses[i]);
						}
						//set口座開設審査待ち情報リスト()
						this.setAccOpenJudgeWaitingInfoList(
							WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_MAIN, 
							WEB3DuplicationDivDef.MAIL_ADDRESS,
							l_strBranchCode,
							l_strAccountCode,
							null,
							null,
							null,
							null);
					}
				}
			}
        }
        
        //２） 見込客ファ@イルにメールアドレスが存在するかチェックする 
        //  ２－１） 口座開設メールアドレス重複チェッククラスを生成する。 
        WEB3AccOpenInfoCreatedService l_service = (WEB3AccOpenInfoCreatedService)
            Services.getService(WEB3AccOpenInfoCreatedService.class); 
        WEB3AccOpenMailAddressDuplicationCheck l_accOpenMailAddressDuplicationCheck =
            l_service.toAccOpenMailAddressDuplicationCheck(
                this.getAccountCode(),
                this.getRequestNumber(),
                this.getInstitutionCode());
        
        //  ２－２） 重複メールアドレスのチェックを行う。（口座開設メールアドレス重複チェック#get重複顧客情報()） 
        Object[] l_objDuplicatedMailAddress = 
            l_accOpenMailAddressDuplicationCheck.getDuplicationAccountInfo(this.getEmailAddress());
        
        //  ２－３） 重複アドレスが存在する場合 
        if (l_objDuplicatedMailAddress != null && l_objDuplicatedMailAddress.length > 0) 
        {
            l_blnDuplicateFlag = true;
            
            // ２－３－１）（引数）部店プリファ@レンス > 1の場合、重複顧客情報を保存する。 
            if (l_intBranchPreference > 1) 
            {
                for (int i = 0; i < l_objDuplicatedMailAddress.length; i++)
                {       
                    if (l_objDuplicatedMailAddress[i] != null) 
                    {
                        l_strAccountCode = 
                            WEB3GentradeMailAddressDuplicationCheck.getAccountCode(
                            	l_objDuplicatedMailAddress[i]);
                        l_strBranchCode = 
                            WEB3GentradeMailAddressDuplicationCheck.getBranchCode(
                            	l_objDuplicatedMailAddress[i]);
                    }
            
                    //set口座開設審査待ち情報リスト()
                    this.setAccOpenJudgeWaitingInfoList(
                        WEB3ReviewCodeDef.DUPLICATE_ACCOUNT_CHECK_EXP, 
                        WEB3DuplicationDivDef.MAIL_ADDRESS,
                        l_strBranchCode,
                        l_strAccountCode,
                        null,
                        null,
                        null,
                        null);
                }
            }     
        }
     
        //３） 戻り値 
        //  ３－１）  １－２）、２－３）のいづれかに於いて重複アドレスが存在した場合、 
        //     「WARNING_60011：　@メールアドレス重複の可能性あり」を返却する。 
        if (l_blnDuplicateFlag)
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3ErrorCatalog.WARNING_60011.getErrorCode();
        }
        
        //  ３－２）  １－２）、２－３）の全てに於いて、重複アドレスが存在しなかった場合、nullを返却する。 
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (set口座コード)<BR>
     * 口座コードをセットする。<BR>
     * this.口座開設見込客行.口座コードに引数.口座コードをセットする <BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード。<BR>
     */
    public void setAccountCode(String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(String)";
        log.entering(STR_METHOD_NAME);
        this.expAccountOpenParams.setAccountCode(l_strAccountCode);   
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (delete口座開設見込客)<BR>
     * 口座開設見込客テーブルのレコードを物理削除する。<BR>
     * <BR>
     * １）　@口座開設見込客行オブジェクト取得<BR>
     * 　@口座開設見込客.getDataSourceObject()にて口座開設見込客行を取得する。<BR>
     * <BR>
     * ２）　@DB削除<BR>
     * 　@口座開設見込客テーブルより口座開設見込客行オブジェクトを削除（delete）する。<BR>
     * @@throws WEB3BaseException
     */
    public void deleteAccOpenExpAccountOpen() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteAccOpenExpAccountOpen()";
        log.entering(STR_METHOD_NAME);

        //１）口座開設見込客行オブジェクト取得
        //　@　@口座開設見込客.getDataSourceObject()にて口座開設見込客行を取得する。
        ExpAccountOpenRow l_expAccountOpenRow = (ExpAccountOpenRow)this.getDataSourceObject();

        try
        {
            //２）DB削除
            //　@　@口座開設見込客テーブルより口座開設見込客行オブジェクトを削除（delete）する。
            WEB3DataAccessUtility.deleteRow(l_expAccountOpenRow);
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
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (is削除可能)<BR>
     * 口座開設見込客データが削除可能を判定する。<BR>
     * <BR>
     * ○　@口座開設未開設（this.get口座開設状況区分() が 0：　@DEFAULT（未開設））の場合、trueを返却する。<BR>
     * 　@　@以外の場合、falseを返却する。<BR>
     * @@return boolean
     */
    public boolean isDeletePossible()
    {
        final String STR_METHOD_NAME = "isDeletePossible()";
        log.entering(STR_METHOD_NAME);

        // 口座開設未開設（this.get口座開設状況区分() が 0：　@DEFAULT（未開設））の場合、trueを返却する。
        // 以外の場合、falseを返却する。
        if (WEB3AccountOpenStatusDivDef.DEFAULT.equals(this.getAccountOpenStatusDiv()))
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

    /**
     * (get顧客姓（漢字）)<BR>
     * 顧客姓（漢字）を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.顧客姓（漢字）を返却する。<BR>
     * @@return String
     */
    public String getAccountFamilyName()
    {
        return this.expAccountOpenParams.getFamilyName();
    }

    /**
     * (get顧客名（漢字）)<BR>
     * 顧客名（漢字）を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.顧客名（漢字）を返却する。<BR>
     * @@return String
     */
    public String getAccountName()
    {
        return this.expAccountOpenParams.getGivenName();
    }

    /**
     * (get住所１（カナ）)<BR>
     * 住所１（カナ）を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.住所１（カナ）を返却する。<BR>
     * @@return String
     */
    public String getAddressKana1()
    {
        return this.expAccountOpenParams.getAddressLine1Kana();
    }

    /**
     * (get住所２（カナ）)<BR>
     * 住所２（カナ）を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.住所２（カナ）を返却する。<BR>
     * @@return String
     */
    public String getAddressKana2()
    {
        return this.expAccountOpenParams.getAddressLine2Kana();
    }

    /**
     * (get住所３（カナ）)<BR>
     * 住所３（カナ）を取得する。<BR>
     * <BR>
     * this.口座開設見込客行.住所３（カナ）を返却する。<BR>
     * @@return String
     */
    public String getAddressKana3()
    {
        return this.expAccountOpenParams.getAddressLine3Kana();
    }

    /**
     * (is削除済み)<BR>
     * 口座開設見込客データが削除済かどうかを判定する。 <BR>
     * <BR>
     * １）口座開設見込客を取得する。<BR>
     * 　@以下の条件で口座開設見込客テーブルを検索する。 <BR>
     * <BR>
     * 　@[条件] <BR>
     * 　@証券会社コード = this.口座開設見込客行.証券会社コード<BR>
     * 　@識別コード =this.口座開設見込客行.識別コード<BR>
     * <BR>
     * 　@検索結果が取得できなかった場合、エラーをスローする。<BR>
     * <BR>
     * ２）取得した口座開設見込客行.削除フラグが「1：TRUE/無効（削除）」の場合、trueを返却する。<BR>
     * 　@　@以外の場合、falseを返却する。 <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isDeleted() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isDeleted()";
        log.entering(STR_METHOD_NAME);

        ExpAccountOpenRow l_expAccountOpenRow = null;
        try
        {
            //[条件]
            //証券会社コード = this.口座開設見込客行.証券会社コード
            //識別コード =this.口座開設見込客行.識別コード
            l_expAccountOpenRow = ExpAccountOpenDao.findRowByPk(
                    this.expAccountOpenParams.getInstitutionCode(),
                    this.expAccountOpenParams.getAccOpenRequestNumber());
        }
        catch (DataFindException l_ex)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                " 口座開設見込客がnullである。");   
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //２）取得した口座開設見込客行.削除フラグが「1：TRUE/無効（削除）」の場合、trueを返却する
        if (BooleanEnum.TRUE.equals(l_expAccountOpenRow.getDeleteFlag()))
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

    /**
     * (save口座開設見込客)<BR>
     * 口座開設見込客テーブルを更新する。<BR>
     * <BR>
     * １） 口座開設見込客行オブジェクト取得<BR>
     * 　@口座開設見込客.getDataSourceObject()にて口座開設見込客行を取得する。<BR>
     * <BR>
     * ２） 更新情報をセットする。<BR>
     * 　@２－１） 引数.更新項目が「1：印刷フラグ」の場合、<BR>
     * 　@　@口座開設見込客行.印刷フラグに引数.更新値をセットする。<BR>
     * 　@２－２） 引数.更新項目が「2：受領フラグ」の場合、<BR>
     * 　@　@口座開設見込客行.受領フラグに引数.更新値をセットする。<BR>
     * 　@２－３） 引数.更新項目が「3：削除フラグ」の場合、<BR>
     * 　@　@口座開設見込客行.削除フラグに引数.更新値、<BR>
     * 　@　@口座開設見込客行.削除日時に現在日時をセットする。<BR>
     * 　@DB更新仕様「口座開設見込客DB更新（Update）仕様.xls」参照<BR>
     * <BR>
     * ３） DB更新<BR>
     * 　@口座開設見込客行オブジェクトの内容で、口座開設見込客テーブルを更新（update）する。<BR>
     * @@param l_strUpdateItem - (更新項目)<BR>
     * 更新項目<BR>
     * @@param l_strUpdateValue - (更新値)<BR>
     * 更新値<BR>
     * @@param l_strUpdateCode - (更新者コード)<BR>
     * 更新者コード<BR>
     * @@throws WEB3BaseException
     */
    public void saveExpAccountOpen(
        String l_strUpdateItem,
        String l_strUpdateValue,
        String l_strUpdateCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveExpAccountOpen(String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１） 口座開設見込客行オブジェクト取得
        //口座開設見込客.getDataSourceObject()にて口座開設見込客行を取得する。
        ExpAccountOpenParams l_expAccountOpenParams =
            (ExpAccountOpenParams)this.getDataSourceObject();
        BooleanEnum l_booleanEnum =
            (BooleanEnum)EnumeratedManager.getInstance().valueFromInt(
                BooleanEnum.class, Integer.parseInt(l_strUpdateValue));
        //２） 更新情報をセットする。
        //DB更新仕様「口座開設見込客DB更新（Update）仕様.xls」参照
        //２－１） 引数.更新項目が「1：印刷フラグ」の場合、
        //口座開設見込客行.印刷フラグに引数.更新値をセットする。
        if (WEB3AccOpenUpdateItemDef.PRINT_CHANGE.equals(l_strUpdateItem))
        {
            l_expAccountOpenParams.setPrintFlag(l_strUpdateValue);
        }
        //２－２） 引数.更新項目が「2：受領フラグ」の場合、
        //口座開設見込客行.受領フラグに引数.更新値をセットする。
        else if (WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE.equals(l_strUpdateItem))
        {
            l_expAccountOpenParams.setReceiptFlag(l_booleanEnum);
        }
        //２－３） 引数.更新項目が「3：削除フラグ」の場合、
        //口座開設見込客行.削除フラグに引数.更新値、
        //口座開設見込客行.削除日時に現在日時をセットする。
        else if (WEB3AccOpenUpdateItemDef.DELETE_CHANGE.equals(l_strUpdateItem))
        {
            l_expAccountOpenParams.setDeleteFlag(l_booleanEnum);
            //削除日時:現在日時
            if (BooleanEnum.TRUE.equals(l_booleanEnum))
            {
                l_expAccountOpenParams.setDeleteTimestamp(GtlUtils.getSystemTimestamp());
            }
            else
            {
                l_expAccountOpenParams.setDeleteTimestamp(null);
            }
        }

        //更新者コード:管理者.管理者コード
        l_expAccountOpenParams.setLastUpdater(l_strUpdateCode);

        //更新日時:現在日時
        l_expAccountOpenParams.setLastUpdatedTimestamp(GtlUtils.getSystemTimestamp());

        //３） DB更新
        //口座開設見込客行オブジェクトの内容で、口座開設見込客テーブルを更新（update）する。
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(l_expAccountOpenParams);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 文字列の変換
     * @@param l_str
     * @@return
     */
    private String changeStr(String l_str)
    {
    	if(l_str == null)
    	{
    		l_str = "";
    	}
    	return l_str;
    }
}@
