head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiOtherOrgInfoAdmin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外部連携情報管理(WEB3SrvRegiOtherOrgInfoAdmin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/03 柴双紅 (中訊) 新規作成・モデルNo.330, No.342, No.345
Revision History : 2008/03/12 周墨洋 (中訊) 仕様変更・モデルNo.348
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OtherOrgStatusDef;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.srvregi.data.OtherOrgInfoAdminParams;
import webbroker3.srvregi.data.OtherOrgInfoAdminRow;
import webbroker3.util.WEB3LogUtility;

/**
 * 外部連携情報管理<BR>
 * 外部連携情報管理クラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3SrvRegiOtherOrgInfoAdmin implements BusinessObject
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SrvRegiOtherOrgInfoAdmin.class);

    /**
     * (外部連携情報管理行)<BR>
     * 外部連携情報管理行
     */
    private OtherOrgInfoAdminParams otherOrgInfoAdminParams;

    /**
     * （getDataSourceObjectの実装）<BR>
     * <BR>
     * this.外部連携情報管理行を返却する。<BR>
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        // this.外部連携情報管理行を返却する
        return this.otherOrgInfoAdminParams;
    }

    /**
     * 更新行用Paramsのクローン行を生成して返却する。<BR>
     * <BR>
     * this.外部連携情報管理行をコピーして、同じ内容の別インスタンスを作成する（clone）<BR>
     * 作成したコピーを自身のthis.外部連携情報管理行にセットする。<BR>
     */
    public void createForUpdateParams()
    {
        final String STR_METHOD_NAME = "createForUpdateParams()";
        log.entering(STR_METHOD_NAME);

        // this.外部連携情報管理行をコピーして、同じ内容の別インスタンスを作成する（clone）
        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams(this.otherOrgInfoAdminParams);

        // 作成したコピーを自身のthis.外部連携情報管理行にセットする
        this.otherOrgInfoAdminParams = l_otherOrgInfoAdminParams;

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (外部連携情報管理)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 当オブジェクトを生成し、<BR>
     * 引数.外部連携情報管理Rowをthis.外部連携情報管理行にセットする。<BR>
     * <BR>
     * @@param l_otherOrgInfoAdminRow - (外部連携情報管理Row)<BR>
     * 外部連携情報管理Row
     */
    public WEB3SrvRegiOtherOrgInfoAdmin(
        OtherOrgInfoAdminRow l_otherOrgInfoAdminRow)
    {
        // 引数.外部連携情報管理Rowをthis.外部連携情報管理行にセットする。
        this.otherOrgInfoAdminParams = new OtherOrgInfoAdminParams(l_otherOrgInfoAdminRow);
    }

    /**
     * (get通番)<BR>
     * 通番を返す。<BR>
     * <BR>
     * this.外部連携情報管理.get通番()の戻り値を返す。<BR>
     * @@return long
     */
    public long getSequenceNumber()
    {
        // this.外部連携情報管理.get通番()の戻り値を返す
        return this.otherOrgInfoAdminParams.getSequenceNumber();
    }

    /**
     * (getサービス区分)<BR>
     * サービス区分を返す。<BR>
     * <BR>
     * this.外部連携情報管理行.getサービス区分()の戻り値を返す。<BR>
     * @@return String
     */
    public String getSrvDiv()
    {
        // this.外部連携情報管理行.getサービス区分()の戻り値を返す
        return this.otherOrgInfoAdminParams.getSrvDiv();
    }

    /**
     * (setID)<BR>
     * IDの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.setID()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.ID<BR>
     * <BR>
     * @@param l_strId - (ID)<BR>
     * ID<BR>
     */
    public void setId(String l_strId)
    {
        // 1) this.外部連携情報管理行.setID()をコールする。
        // [引数]
        // 　@ID=引数.ID
        this.otherOrgInfoAdminParams.setId(l_strId);
    }

    /**
     * (getID)<BR>
     * IDを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.getID()の戻り値を返す。<BR>
     * @@return String
     */
    public String getId()
    {
        // this.外部連携情報管理行.getID()の戻り値を返す
        return this.otherOrgInfoAdminParams.getId();
    }

    /**
     * (setパスワード)<BR>
     * パスワードの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.setパスワード()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.パスワード<BR>
     * <BR>
     * @@param l_strPassword - (パスワード)<BR>
     * パスワード<BR>
     */
    public void setPassword(String l_strPassword)
    {
        // 1) this.外部連携情報管理行.setパスワード()をコールする。
        // [引数]
        // 　@ID=引数.パスワード
        this.otherOrgInfoAdminParams.setPassword(l_strPassword);
    }

    /**
     * (getパスワード)<BR>
     * パスワードを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.getパスワード()の戻り値を返す。<BR>
     * @@return String
     */
    public String getPassword()
    {
        // this.外部連携情報管理行.getパスワード()の戻り値を返す
        return this.otherOrgInfoAdminParams.getPassword();
    }

    /**
     * (setステータス)<BR>
     * ステータスの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.setステータス()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.ステータス<BR>
     * <BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     */
    public void setStatus(String l_strStatus)
    {
        // 1) this.外部連携情報管理行.setステータス()をコールする。
        // [引数]
        // 　@ID=引数.ステータス
        this.otherOrgInfoAdminParams.setStatus(l_strStatus);
    }

    /**
     * (getステータス)<BR>
     * ステータスを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.getステータス()の戻り値を返す。<BR>
     * @@return String
     */
    public String getStatus()
    {
        // this.外部連携情報管理行.getステータス()の戻り値を返す
        return this.otherOrgInfoAdminParams.getStatus();
    }

    /**
     * (set証券会社コード)<BR>
     * 証券会社コードの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.set証券会社コード()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.証券会社コード<BR>
     * <BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public void setInstitutionCode(String l_strInstitutionCode)
    {
        // 1) this.外部連携情報管理行.set証券会社コード()をコールする。
        // [引数]
        // 　@ID=引数.証券会社コード
        this.otherOrgInfoAdminParams.setInstitutionCode(l_strInstitutionCode);
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.get証券会社コード()の戻り値を返す。<BR>
     * @@return String
     */
    public String getInstitutionCode()
    {
        // this.外部連携情報管理行.get証券会社コード()の戻り値を返す
        return this.otherOrgInfoAdminParams.getInstitutionCode();
    }

    /**
     * (set部店コード)<BR>
     * 部店コードの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.set部店コード()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.部店コード<BR>
     * <BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     */
    public void setBranchCode(String l_strBranchCode)
    {
        // 1) this.外部連携情報管理行.set部店コード()をコールする。
        // [引数]
        // 　@ID=引数.部店コード
        this.otherOrgInfoAdminParams.setBranchCode(l_strBranchCode);
    }

    /**
     * (get部店コード)<BR>
     * 部店コードを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.get部店コード()の戻り値を返す。<BR>
     * @@return String
     */
    public String getBranchCode()
    {
        // this.外部連携情報管理行.get部店コード()の戻り値を返す
        return this.otherOrgInfoAdminParams.getBranchCode();
    }

    /**
     * (set口座コード)<BR>
     * 口座コードの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.set口座コード()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.口座コード<BR>
     * <BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     */
    public void setAccountCode(String l_strAccountCode)
    {
        // 1) this.外部連携情報管理行.set口座コード()をコールする。
        // [引数]
        // 　@ID=引数.口座コード
        this.otherOrgInfoAdminParams.setAccountCode(l_strAccountCode);
    }

    /**
     * (get口座コード)<BR>
     * 口座コードを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.get口座コード()の戻り値を返す。<BR>
     * @@return String
     */
    public String getAccountCode()
    {
        // this.外部連携情報管理行.get口座コード()の戻り値を返す
        return this.otherOrgInfoAdminParams.getAccountCode();
    }

    /**
     * (set適用期間From)<BR>
     * 適用期間Fromの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.set適用期間From()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.適用期間From<BR>
     * <BR>
     * @@param l_tsAppliStartDate - (適用期間From)<BR>
     * 適用期間From<BR>
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate)
    {
        // 1) this.外部連携情報管理行.set適用期間From()をコールする。
        // [引数]
        // 　@ID=引数.適用期間From
        this.otherOrgInfoAdminParams.setAppliStartDate(l_tsAppliStartDate);
    }

    /**
     * (get適用期間From)<BR>
     * 適用期間Fromを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.get適用期間From()の戻り値を返す。<BR>
     * @@return Timestamp
     */
    public Timestamp getAppliStartDate()
    {
        // this.外部連携情報管理行.get適用期間From()の戻り値を返す
        return this.otherOrgInfoAdminParams.getAppliStartDate();
    }

    /**
     * (set適用期間To)<BR>
     * 適用期間Toの設定を行う。<BR>
     * <BR>
     * this.外部連携情報管理行.set適用期間To()をコールする。<BR>
     * [引数]<BR>
     * 　@ID=引数.適用期間To<BR>
     * <BR>
     * @@param l_tsAppliEndDate - (適用期間To)<BR>
     * 適用期間To<BR>
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate)
    {
        // 1) this.外部連携情報管理行.set適用期間To()をコールする。
        // [引数]
        // 　@ID=引数.適用期間To
        this.otherOrgInfoAdminParams.setAppliEndDate(l_tsAppliEndDate);
    }

    /**
     * (get適用期間To)<BR>
     * 適用期間Toを返す。<BR>
     * <BR>
     * this.外部連携情報管理行.get適用期間To()の戻り値を返す。<BR>
     * @@return Timestamp
     */
    public Timestamp getAppliEndDate()
    {
        // this.外部連携情報管理行.get適用期間To()の戻り値を返す
        return this.otherOrgInfoAdminParams.getAppliEndDate();
    }

    /**
     * (createNew外部連携情報管理)<BR>
     * 新規に外部連携情報管理オブジェクトを生成して返却する。<BR>
     * <BR>
     * 1) 外部連携情報管理Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 2) 外部連携情報管理Params.set通番()をコールする。<BR>
     * [引数]<BR>
     * 　@通番=引数.通番<BR>
     * <BR>
     * 3) 外部連携情報管理Params.setサービス区分()をコールする。<BR>
     * [引数]<BR>
     * 　@サービス区分=引数.サービス区分<BR>
     * <BR>
     * 4) 外部連携情報管理のコンストラクタをコールし、生成した<BR>
     * 　@外部連携情報管理オブジェクトを返却する。<BR>
     * [引数]<BR>
     * 　@外部連携情報管理Row=生成した外部連携情報管理Params<BR>
     * <BR>
     * @@param l_lngSequenceNumber - (通番)<BR>
     * 通番<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分<BR>
     * @@return Timestamp
     */
    public static WEB3SrvRegiOtherOrgInfoAdmin createNewOtherOrgInfoAdmin(
        long l_lngSequenceNumber,
        String l_strSrvDiv)
    {
        final String STR_METHOD_NAME = "createNewOtherOrgInfoAdmin(long, String)";
        log.entering(STR_METHOD_NAME);

        // 1) 外部連携情報管理Paramsオブジェクトを生成する。
        OtherOrgInfoAdminParams l_otherOrgInfoAdminParams =
            new OtherOrgInfoAdminParams();

        // 2) 外部連携情報管理Params.set通番()をコールする。
        // [引数]
        // 　@通番=引数.通番
        l_otherOrgInfoAdminParams.setSequenceNumber(l_lngSequenceNumber);

        // 3) 外部連携情報管理Params.setサービス区分()をコールする。
        // [引数]
        // 　@サービス区分=引数.サービス区分
        l_otherOrgInfoAdminParams.setSrvDiv(l_strSrvDiv);

        // 4) 外部連携情報管理のコンストラクタをコールし、生成した
        // 　@外部連携情報管理オブジェクトを返却する。
        // [引数]
        // 　@外部連携情報管理Row=生成した外部連携情報管理Params
        WEB3SrvRegiOtherOrgInfoAdmin l_srvRegiOtherOrgInfoAdmin =
            new WEB3SrvRegiOtherOrgInfoAdmin(l_otherOrgInfoAdminParams);

        log.exiting(STR_METHOD_NAME);
        return l_srvRegiOtherOrgInfoAdmin;
    }

    /**
     * (save外部連携情報管理)<BR>
     * this.外部連携情報管理行オブジェクトの情報をデータベースに反映させる。(Update)<BR>
     * <BR>
     * 1) this.外部連携情報管理行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード = <*1><BR>
     * 　@更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * <*1><BR>
     * ・セッション情報が管理者の場合、管理者コード<BR>
     * ・セッション情報が顧客の場合、顧客コード<BR>
     * ・セッション情報がコールセンターの場合、扱い者コード<BR>
     * <BR>
     * 2) this.外部連携情報管理行オブジェクトの内容で、<BR>
     * 　@外部連携情報管理テーブルを更新（Update）する。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void saveOtherOrgInfoAdmin() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveOtherOrgInfoAdmin()";
        log.entering(STR_METHOD_NAME);

        // 1) this.外部連携情報管理行オブジェクトに以下の値をセットする。

        // セッション情報が管理者の場合、管理者コード
        WEB3Administrator l_administrator;
        try
        {
            //セッションより管理者が取得
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //セッションより管理者が取得できた場合
        if (l_administrator != null)
        {
            this.otherOrgInfoAdminParams.setLastUpdater(
                l_administrator.getAdministratorCode());
        }
        else
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            long l_loginId = l_opLoginSec.getLoginInfo().getLoginId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_objectManager = l_finApp.getFinObjectManager();
            Trader l_trader = null;

            try
            {
                l_trader = l_objectManager.getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException l_ex)
            {
                l_trader = null;
            }

            // コールセンターからの入力の場合
            if (l_trader != null)
            {
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_trader.getTraderCode());
            }
            else
            {
                MainAccount l_mainAccount = null;

                long l_accountId = l_opLoginSec.getAccountId();

                AccountManager l_accountManager = l_finApp.getAccountManager();
                try
                {
                    l_mainAccount = l_accountManager.getMainAccount(l_accountId);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //セッションより顧客が取得できた場合
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_mainAccount.getAccountCode().substring(0, 6));
            }
        }

        // 更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doUpdateQuery(
                this.otherOrgInfoAdminParams);
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
     * (saveNew外部連携情報管理)<BR>
     * this.外部連携情報管理行オブジェクトの情報をデータベースに反映させる。(Insert)<BR>
     * <BR>
     * 1) this.外部連携情報管理行オブジェクトに以下の値をセットする。<BR>
     * 　@更新者コード = <*1><BR>
     * 　@作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * 　@更新日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値<BR>
     * <BR>
     * <*1><BR>
     * ・セッション情報が管理者の場合、管理者コード<BR>
     * ・セッション情報が顧客の場合、顧客コード<BR>
     * ・セッション情報がコールセンターの場合、扱い者コード<BR>
     * <BR>
     * 2) this.外部連携情報管理行オブジェクトの内容で、<BR>
     * 　@外部連携情報管理テーブルを更新（Insert）する。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void saveNewOtherOrgInfoAdmin() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveNewOtherOrgInfoAdmin()";
        log.entering(STR_METHOD_NAME);

        // 1) this.外部連携情報管理行オブジェクトに以下の値をセットする。

        // セッション情報が管理者の場合、管理者コード
        WEB3Administrator l_administrator;
        try
        {
            //セッションより管理者が取得
            l_administrator = WEB3Administrator.getInstanceFromLoginInfo();
        }
        catch (Exception l_ex)
        {
            l_administrator = null;
        }

        //セッションより管理者が取得できた場合
        if (l_administrator != null)
        {
            this.otherOrgInfoAdminParams.setLastUpdater(
                l_administrator.getAdministratorCode());
        }
        else
        {
            OpLoginSecurityService l_opLoginSec =
                (OpLoginSecurityService)Services.getService(
                    OpLoginSecurityService.class);
            long l_loginId = l_opLoginSec.getLoginInfo().getLoginId();
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            FinObjectManager l_objectManager = l_finApp.getFinObjectManager();
            Trader l_trader = null;

            try
            {
                l_trader = l_objectManager.getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException l_ex)
            {
                l_trader = null;
            }

            // コールセンターからの入力の場合
            if (l_trader != null)
            {
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_trader.getTraderCode());
            }
            else
            {
                MainAccount l_mainAccount = null;

                long l_accountId = l_opLoginSec.getAccountId();

                AccountManager l_accountManager = l_finApp.getAccountManager();
                try
                {
                    l_mainAccount = l_accountManager.getMainAccount(l_accountId);
                }
                catch (NotFoundException l_ex)
                {
                    log.error("テーブルに該当するデータがありません。", l_ex);
                    log.exiting(STR_METHOD_NAME);

                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }

                //セッションより顧客が取得できた場合
                this.otherOrgInfoAdminParams.setLastUpdater(
                    l_mainAccount.getAccountCode().substring(0, 6));
            }
        }

        // 作成日付=GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.otherOrgInfoAdminParams.setCreatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        // 更新日付 = GtlUtils.getTradingSystem( ).getSystemTimestamp( )の戻り値
        this.otherOrgInfoAdminParams.setLastUpdatedTimestamp(
            GtlUtils.getTradingSystem().getSystemTimestamp());

        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_queryProcessor.doInsertQuery(
                this.otherOrgInfoAdminParams);
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
     * (isステータス変更可能)<BR>
     * 当該サービスのステータス変更ルールに則っているか判定し、<BR>
     * 対応するbooleanを返却する。<BR>
     * （true:変更可　@false:変更不可）<BR>
     * <BR>
     * 1) this.外部連携情報管理行がnullの場合、falseを返却する。<BR>
     * <BR>
     * 2) this.外部連携情報管理行がnullではない場合、以下の処理を実施する。<BR>
     * 　@2-1) 引数.ステータス == this.外部連携情報管理行.getステータス()の戻り値<BR>
     * 　@　@　@　@の場合、<BR>
     * 　@　@　@　@falseを返却する。<BR>
     * 　@2-2) 2-1)以外で、引数.ステータス == '1無効（削除）' 又は<BR>
     * 　@　@　@　@this.外部連携情報管理行.getステータス()の戻り値 == '9：未使用'<BR>
     * 　@　@　@　@の場合、trueを返却する。<BR>
     * 　@2-3) 上記以外の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_03050<BR>
     * <BR>
     * <BR>
     * ステータス変更ルールは下記の「ステータス」項目コメントを参照。<BR>
     *【ｘTrade】補足資料.DB更新<BR>
     *「サービス利用管理者・外部連携ID照会アップロード_外部連携情報管理テーブル仕様.xls」<BR>
     *「ステータス変更」シート<BR>
     * <BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     */
    public boolean isStatusChangeable(String l_strStatus) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isStatusChangeable(String)";
        log.entering(STR_METHOD_NAME);

        // 1) this.外部連携情報管理行がnullの場合、falseを返却する。
        if (this.otherOrgInfoAdminParams == null)
        {
            return false;
        }
        else
        {
            // 2) this.外部連携情報管理行がnullではない場合、以下の処理を実施する。
            if (this.otherOrgInfoAdminParams.getStatus().equals(l_strStatus))
            {
                // 2-1) 引数.ステータス == this.外部連携情報管理行.getステータス()の戻り値の場合、
                log.exiting(STR_METHOD_NAME);
                return false;
            }
            else if (WEB3OtherOrgStatusDef.INVALIDITY.equals(l_strStatus)
                || WEB3OtherOrgStatusDef.DEFAULT.equals(this.otherOrgInfoAdminParams.getStatus()))
            {
                // 2-2) 2-1)以外で、引数.ステータス == '1無効（削除）'
                // 又はthis.外部連携情報管理行.getステータス()の戻り値 == '9：未使用'の場合、
                // trueを返却する。
                log.exiting(STR_METHOD_NAME);
                return true;
            }
            else
            {
                // 2-3) 上記以外の場合、例外をスローする。
                String l_strErrorMessage = "ステータスの値が不正です。";
                log.debug(l_strErrorMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03050,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
    }

}
@
