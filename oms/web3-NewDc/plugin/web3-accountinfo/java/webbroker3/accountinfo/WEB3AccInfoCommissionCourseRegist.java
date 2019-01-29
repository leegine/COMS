head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.25.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommissionCourseRegist.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 委託手数料コース変更申込(WEB3AccInfoCommissionCourseRegist)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 張宝楠 (中訊) 新規作成
                   2006/06/30 丁昭奎 (中訊) 仕様変更管理No.112
*/

package webbroker3.accountinfo;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

import webbroker3.accountinfo.data.CommissionCourseRegistDao;
import webbroker3.accountinfo.data.CommissionCourseRegistParams;
import webbroker3.accountinfo.data.CommissionCourseRegistRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (委託手数料コース変更申込)<BR>
 * 委託手数料コース変更申込クラス<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCommissionCourseRegist implements BusinessObject
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommissionCourseRegist.class);

    /**
     * (委託手数料コース変更申込行)<BR>
     * 委託手数料コース変更申込行オブジェクト<BR>
     * <BR>
     * ※ 委託手数料コース変更申込ParamsクラスはDDLより自動生成する。<BR>
     */
    private CommissionCourseRegistParams commissionCourseRegistParams;

    /**
     * (委託手数料コース変更申込)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 指定した委託手数料コース変更申込ＩＤに該当する行を<BR>
     * 委託手数料コース変更申込テーブルより検索する。 <BR>
     * 検索結果の委託手数料コース変更申込行オブジェクトを引数に指定して、<BR>
     * コンストラクタをコールする。 <BR>
     * コンストラクタの戻り値を返却する。 <BR>
     * @@param l_lngCommissionCourseRegistID - 委託手数料コース変更申込ＩＤ
     *
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist
     * @@roseuid 413EBFFF037F
     */
    public WEB3AccInfoCommissionCourseRegist(long l_lngCommissionCourseRegistID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " WEB3AccInfoCommissionCourseRegist(long)";
        log.entering(STR_METHOD_NAME);

        //証券会社コード，手数料商品コード，手数料コースコードに該当する行を、
        //委託手数料コースマスタテーブルより検索する。
        try
        {
            CommissionCourseRegistRow l_commissionCourseRegistRow =
                CommissionCourseRegistDao.findRowByPk(l_lngCommissionCourseRegistID);

            this.commissionCourseRegistParams = (CommissionCourseRegistParams)l_commissionCourseRegistRow;

        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (委託手数料コース変更申込)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 行指定行オブジェクトをプロパティにセットし、インスタンスを生成する。 <BR>
     * @@param l_commissionCourseRegistParams - 委託手数料コース変更申込行オブジェクト<BR>
     * <BR>
     * ※　@委託手数料コース変更申込ParamsはDDLより自動生成する。<BR>
     * @@roseuid 413D999E0057
     */
    public WEB3AccInfoCommissionCourseRegist(CommissionCourseRegistParams l_commissionCourseRegistParams)
    {
        this.commissionCourseRegistParams = l_commissionCourseRegistParams;
    }

    /**
     * （getDataSourceObjectの実装） <BR>
     * <BR>
     * this.委託手数料コース変更申込行を返却する。 <BR>
     * @@return Object
     * @@roseuid 413D6DBA0151
     */
    public Object getDataSourceObject()
    {
        return this.commissionCourseRegistParams;
    }

    /**
     * 　@this.委託手数料コース変更申込行をコピーして、同じ内容の別インスタンス<BR>
     * を作成する（clone）。 <BR>
     * 作成したコピーを自身のthis.委託手数料コース変更申込行にセットする。 <BR>
     * @@roseuid 413D97E40364
     */
    public void createForUpdateParams()
    {
        CommissionCourseRegistParams l_params = new CommissionCourseRegistParams(this.commissionCourseRegistParams);
        this.commissionCourseRegistParams = l_params;
    }

    /**
     * (get部店)<BR>
     * 部店オブジェクトを取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.部店ＩＤに該当する部店オブジェクトを返却する。<BR>
     * @@return WEB3GentradeBranch
     * @@roseuid 413E8E68017C
     */
    public WEB3GentradeBranch getBranch() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getBranch()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngBranchId = this.commissionCourseRegistParams.getBranchId();
        WEB3GentradeBranch l_branch = null;

        try
        {
            l_branch = (WEB3GentradeBranch)l_accountMgr.getBranch(l_lngBranchId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_branch;
    }

    /**
     * (get顧客)<BR>
     * 顧客オブジェクトを取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.口座ＩＤに該当する顧客オブジェクトを返却する。<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 413E8E7A0312
     */
    public WEB3GentradeMainAccount getMainAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getMainAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();

        long l_lngAccountId = this.commissionCourseRegistParams.getAccountId();
        WEB3GentradeMainAccount l_mainAccount = null;

        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);

        return l_mainAccount;
    }

    /**
     * (get手数料商品コード)<BR>
     * 手数料商品コードを取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.手数料商品コードを返却する。<BR>
     * @@return String
     * @@roseuid 41511B3001D2
     */
    public String getCommissionProductCode()
    {
        return this.commissionCourseRegistParams.getCommProductCode();
    }

    /**
     * (get手数料コースコード)<BR>
     * 手数料コースコードを取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.手数料コースコードを返却する。<BR>
     * @@return String
     * @@roseuid 413E8EF70082
     */
    public String getCommissionCourseCode()
    {
        return this.commissionCourseRegistParams.getCommissionCourseDiv();
    }

    /**
     * (get申込日)<BR>
     * 申込日を取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.申込日時の日付部分（YYYYMMDD）を返却する。<BR>
     * @@return Date
     * @@roseuid 413E8F430285
     */
    public Date getRegistDate()
    {
        return WEB3DateUtility.toDay(this.commissionCourseRegistParams.getRegistTimestamp());
    }

    /**
     * (get適用開始日)<BR>
     * 適用開始日を取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.適用開始日時の日付部分（YYYYMMDD）を返却する。<BR>
     * @@return Date
     * @@roseuid 413E8F710063
     */
    public Date getAppliStartDate()
    {
        return WEB3DateUtility.toDay(this.commissionCourseRegistParams.getAppliStartDatetime());
    }

    /**
     * (get適用終了日)<BR>
     * 適用終了日を取得する。<BR>
     * <BR>
     * this.委託手数料変更申込行.適用終了日時の日付部分（YYYYMMDD）を返却する。<BR>
     * @@return Date
     * @@roseuid 413E8F84014D
     */
    public Date getAppliEndDate()
    {
        return WEB3DateUtility.toDay(this.commissionCourseRegistParams.getAppliEndDatetime());
    }

    /**
     * (is取消可能)<BR>
     * 取消可能かを判定する。<BR>
     * <BR>
     * １）　@削除済みかの判定<BR>
     * 　@－（this.委託手数料変更申込行.削除フラグ == BooleanEnum.TRUE）の場合、<BR>
     * falseを返却する。<BR>
     * <BR>
     * ２）　@締切日時による判定<BR>
     * 　@－（this.委託手数料変更申込行.変更申込締切日時 > 処理日時(*1)）の場合true，<BR>
     * 　@－以外、falseを返却する。<BR>
     * <BR>
     * 　@(*1) 処理日時<BR>
     * 　@TradingSystem.getSystemTimestamp()<BR>
     * @@return boolean
     * @@roseuid 413DA8BD0345
     */
    public boolean isCancelPossible()
    {
        //削除済みかの判定
        BooleanEnum l_deleteFlag = this.commissionCourseRegistParams.getDeleteFlag();

        //（this.委託手数料変更申込行.削除フラグ == BooleanEnum.TRUE）の場合、falseを返却する。
        if (BooleanEnum.TRUE.equals(l_deleteFlag))
        {
            return false;
        }

        //締切日時による判定

        //処理日時
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();
        //変更申込締切日時
        Date l_datRegistEndDate = this.commissionCourseRegistParams.getRegistEndTimestamp();

        //（this.委託手数料変更申込行.変更申込締切日時 > 処理日時(*1)）の場合true
        if (WEB3DateUtility.compare(l_datRegistEndDate, l_datProcessDate) > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

    /**
     * (isダウンロード済)<BR>
     * ダウンロード済かを判定する。<BR>
     * <BR>
     * （this.委託手数料変更申込行.ダウンロード済フラグ == BooleanEnum.TRUE）の場合true，<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 413DA971021C
     */
    public boolean isDownloaded()
    {
        BooleanEnum l_downloadFlag = this.commissionCourseRegistParams.getDownloadFlag();

        if (BooleanEnum.TRUE.equals(l_downloadFlag))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * (createNew委託手数料コース変更申込)<BR>
     * （static メソッド）<BR>
     * 委託手数料コース変更申込新規行を生成する。<BR>
     * <BR>
     * １）　@行オブジェクト生成<BR>
     * 　@委託手数料コース変更申込Paramsオブジェクトを生成する。<BR>
     * <BR>
     * 　@※委託手数料コース変更申込ParamsはDDLより自動生成する。<BR>
     * <BR>
     * ２）　@行オブジェクトにプロパティをセットする。 <BR>
     * 　@１）で生成した委託手数料コース変更申込Paramsオブジェクトのプロパティに、<BR>
     * 以下の通りセットを行う。 <BR>
     * <BR>
     * 　@委託手数料コース変更申込Params.委託手数料コース変更申込ＩＤ = <BR>
     * 新規採番(*1)<BR>
     * 　@委託手数料コース変更申込Params.証券会社コード = <BR>
     * 委託手数料コースマスタ.get証券会社コード()<BR>
     * 　@委託手数料コース変更申込Params.部店ID = <BR>
     * 顧客.getBranch().getBranchId()<BR>
     * 　@委託手数料コース変更申込Params.口座ID = <BR>
     * 顧客.getAccountId()<BR>
     * 　@委託手数料コース変更申込Params.手数料商品コード = <BR>
     * 委託手数料コースマスタ.get手数料商品コード()<BR>
     * 　@委託手数料コース変更申込Params.適用開始日時 = <BR>
     * 委託手数料コースマスタ.get適用開始日時()<BR>
     * 　@委託手数料コース変更申込Params.適用終了日時 = <BR>
     * 委託手数料コースマスタ.get適用終了日時()<BR>
     * 　@委託手数料コース変更申込Params.手数料コースコード = <BR>
     * 委託手数料コースマスタ.get手数料コースコード()<BR>
     * 　@委託手数料コース変更申込Params.申込日時 = <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * 　@委託手数料コース変更申込Params.変更申込締切日時 = <BR>
     * 委託手数料コースマスタ.get変更申込締切日時()<BR>
     * 　@委託手数料コース変更申込Params.ダウンロード済フラグ = <BR>
     * BooleanEnum.FALSE<BR>
     * 　@委託手数料コース変更申込Params.削除フラグ = <BR>
     * BooleanEnum.FALSE<BR>
     * 　@委託手数料コース変更申込Params.更新者コード = <BR>
     * 顧客.getAccountCode()<BR>
     * 　@委託手数料コース変更申込Params.作成日時 = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * 　@委託手数料コース変更申込Params.更新日時 = <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * <BR>
     * 　@(*1) 委託手数料コース変更申込ＩＤの新規採番 <BR>
     * 　@委託手数料コース変更申込DAO.newPkValue()メソッドにて取得する。 <BR>
     * 　@※ 委託手数料コース変更申込DAOクラスはDDLより自動生成する。 <BR>
     * <BR>
     * ３）　@委託手数料コース変更申込オブジェクト返却<BR>
     * 　@行オブジェクトを指定し、委託手数料コース変更申込オブジェクトを生成し返却する。<BR>
     * <BR>
     * 　@[コンストラクタの引数]<BR>
     * 　@委託手数料コース変更申込行：　@（２）で生成した行オブジェクト）<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     *
     * @@param l_commissionCourseMaster - 委託手数料コースマスタオブジェクト
     *
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist
     * @@roseuid 413D991B01ED
     */
    public static WEB3AccInfoCommissionCourseRegist createNewCommissionCourseRegist(WEB3GentradeMainAccount l_mainAccount, WEB3AccInfoCommissionCourseMaster l_commissionCourseMaster) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createNewCommissionCourseRegist(WEB3GentradeMainAccount, WEB3AccInfoCommissionCourseMaster)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null || l_commissionCourseMaster == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount + "[l_commissionCourseMaster] = " + l_commissionCourseMaster
                );
        }

        CommissionCourseRegistParams l_params = new CommissionCourseRegistParams();

        //処理日時
        Date l_datProcessDate = GtlUtils.getSystemTimestamp();

        long l_lngNewId;
        try
        {
            l_lngNewId = CommissionCourseRegistDao.newPkValue();
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //委託手数料コース変更申込Params.委託手数料コース変更申込ＩＤ = 新規採番(*1)
        l_params.setCommissionCourseRegistId(l_lngNewId);

        //委託手数料コース変更申込Params.証券会社コード = 委託手数料コースマスタ.get証券会社コード()
        l_params.setInstitutionCode(l_commissionCourseMaster.getInstitutionCode());

        //委託手数料コース変更申込Params.部店ID = 顧客.getBranch().getBranchId()
        l_params.setBranchId(l_mainAccount.getBranch().getBranchId());

        //委託手数料コース変更申込Params.口座ID = 顧客.getAccountId()
        l_params.setAccountId(l_mainAccount.getAccountId());

        //委託手数料コース変更申込Params.手数料商品コード = 委託手数料コースマスタ.get手数料商品コード()
        l_params.setCommProductCode(l_commissionCourseMaster.getCommissionProductCode());

        //委託手数料コース変更申込Params.適用開始日時 = 委託手数料コースマスタ.get適用開始日時()
        l_params.setAppliStartDatetime(l_commissionCourseMaster.getAppliStartTimestamp());

        //委託手数料コース変更申込Params.適用終了日時 = 委託手数料コースマスタ.get適用終了日時()
        l_params.setAppliEndDatetime(l_commissionCourseMaster.getAppliEndTimestamp());

        //委託手数料コース変更申込Params.手数料コースコード = 委託手数料コースマスタ.get手数料コースコード()
        l_params.setCommissionCourseDiv(l_commissionCourseMaster.getCommissionCourseCode());

        //委託手数料コース変更申込Params.申込日時 = TradingSystem.getSystemTimestamp()
        l_params.setRegistTimestamp(l_datProcessDate);

        //委託手数料コース変更申込Params.変更申込締切日時 = 委託手数料コースマスタ.get変更申込締切日時()
        l_params.setRegistEndTimestamp(l_commissionCourseMaster.getRegistEndTimestamp());

        //委託手数料コース変更申込Params.ダウンロード済フラグ = BooleanEnum.FALSE
        l_params.setDownloadFlag(BooleanEnum.FALSE);

        //委託手数料コース変更申込Params.削除フラグ = BooleanEnum.FALSE
        l_params.setDeleteFlag(BooleanEnum.FALSE);

        //委託手数料コース変更申込Params.更新者コード = 顧客.getAccountCode()
        l_params.setLastUpdater(l_mainAccount.getAccountCode());

        //委託手数料コース変更申込Params.作成日時 = TradingSystem.getSystemTimestamp()
        l_params.setCreatedTimestamp(l_datProcessDate);

        //委託手数料コース変更申込Params.更新日時 = TradingSystem.getSystemTimestamp()
        l_params.setLastUpdatedTimestamp(l_datProcessDate);

        log.exiting(STR_METHOD_NAME);

        //行オブジェクトを指定し、委託手数料コース変更申込オブジェクトを生成し返却する。
        return new WEB3AccInfoCommissionCourseRegist(l_params);
    }

    /**
     * (get委託手数料コース変更申込)<BR>
     * （static メソッド）<BR>
     * 顧客に該当する株式委託手数料コース変更申込を取得する。<BR>
     * <BR>
     * １）　@委託手数料コース変更申込テーブル検索<BR>
     * 　@以下の条件で、委託手数料コース変更申込テーブルを検索する。<BR>
     * <BR>
     * 　@[条件]<BR>
     * 　@証券会社コード = 顧客.getInstitution().getInstitutionCode() And<BR>
     * 　@部店ID = 顧客.getBranch().getBranchId() And<BR>
     * 　@口座ID = 顧客.getAccountId() And<BR>
     * 　@手数料商品コード = 手数料商品コード And<BR>
     * 　@適用開始日時 > 処理日時(*1) And<BR>
     * 　@削除フラグ = BooleanEnum.FALSE<BR>
     * <BR>
     * 　@[取得順]<BR>
     *　@適用開始日時　@昇順（：asc）<BR>
     * <BR>
     * 　@(*1) 処理日時<BR>
     * 　@TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * ２）　@委託手数料コース変更申込オブジェクト返却<BR>
     * 　@取得した各行オブジェクトについて、委託手数料コース変更申込オブジェクト<BR>
     * を生成し配列で返却する。<BR>
     * 　@行が取得できなかった場合は、nullを返却する。<BR>
     * @@param l_mainAccount - 顧客オブジェクト
     *
     * @@param l_strCommissionProductCode - 手数料商品コード
     * @@return webbroker3.accountinfo.WEB3AccInfoCommissionCourseRegist[]
     * @@roseuid 413DA53E0141
     */
    public static WEB3AccInfoCommissionCourseRegist[] getCommissionCourseRegist(WEB3GentradeMainAccount l_mainAccount, String l_strCommissionProductCode)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommissionCourseRegist(WEB3GentradeMainAccount, String)";
        log.entering(STR_METHOD_NAME);

        if (l_mainAccount == null)
        {
            log.error("パラメータNull出来ない。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                "[l_mainAccount] = " + l_mainAccount
                );
        }

        List l_lisRecords = null;

        try
        {

            //以下の条件で、委託手数料コース変更申込テーブルを検索する。

            //[条件]
            //証券会社コード = 顧客.getInstitution().getInstitutionCode() And
            //部店ID = 顧客.getBranch().getBranchId() And
            //口座ID = 顧客.getAccountId() And
            //手数料商品コード = 手数料商品コード And
            //適用開始日時 > 処理日時(*1) And
            //削除フラグ = BooleanEnum.FALSE

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            //処理日時
            Date l_datProcessDate = GtlUtils.getSystemTimestamp();

            StringBuffer l_sbQueryString = new StringBuffer();
            l_sbQueryString.append("institution_code = ?");
            l_sbQueryString.append(" and branch_id = ?");
            l_sbQueryString.append(" and account_id = ?");
            l_sbQueryString.append(" and comm_product_code = ?");
            l_sbQueryString.append(" and appli_start_datetime > ?");
            l_sbQueryString.append(" and delete_flag = ?");


            Object[] l_queryDataContainer = new Object[] {
                l_mainAccount.getInstitution().getInstitutionCode(),
                "" + l_mainAccount.getBranch().getBranchId(),
                "" + l_mainAccount.getAccountId(),
                l_strCommissionProductCode,
                l_datProcessDate,
                BooleanEnum.FALSE
                };

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseRegistRow.TYPE,
                l_sbQueryString.toString(),
                "appli_start_datetime ASC",
                null,
                l_queryDataContainer);
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        WEB3AccInfoCommissionCourseRegist[] l_commissionCourseRegists =
            new WEB3AccInfoCommissionCourseRegist[l_intSize];

        for (int i = 0; i < l_intSize; i++)
        {
            l_commissionCourseRegists[i] =
                new WEB3AccInfoCommissionCourseRegist((CommissionCourseRegistParams)l_lisRecords.get(i));
        }

        log.exiting(STR_METHOD_NAME);
        return l_commissionCourseRegists;
    }

    /**
     * (get委託手数料コース変更申込)<BR>
     * （static メソッド）<BR>
     * 指定に該当する委託手数料変更申込オブジェクトのListを取得する。<BR>
     * <BR>
     * １）　@QueryProcessor.doFindAllQuery( )により、委託手数料変更申込行<BR>
     * オブジェクトのListを取得する。 <BR>
     * <BR>
     * 　@　@[doFindAllQuery()に指定する引数]<BR>
     * 　@　@rowType：　@委託手数料変更申込Row.TYPE<BR>
     * 　@　@where：　@検索条件文字列<BR>
     * 　@　@orderBy：　@ソート条件<BR>
     * 　@　@conditions：　@null<BR>
     * 　@　@bindVars：　@検索条件データコンテナ<BR>
     * <BR>
     * ２）　@検索結果の行オブジェクトで委託手数料変更申込オブジェクトを生成し、<BR>
     * Listで返却する。 <BR>
     * @@param l_strQueryString - 検索条件文字列
     * @@param l_queryContainer - 検索条件データコンテナ
     * @@param l_strSortCond - ソート条件
     * @@return List
     * @@roseuid 415111250145
     */
    public static List getCommissionCourseRegist(String l_strQueryString, String[] l_queryContainer, String l_strSortCond)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getCommissionCourseRegist(String, String[], String)";
        log.entering(STR_METHOD_NAME);

        List l_lisRecords = null;

        try
        {

            //QueryProcessor.doFindAllQuery( )により、委託手数料変更申込行オブジェクトのListを取得する。
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();

            l_lisRecords = l_queryProcessor.doFindAllQuery(
                CommissionCourseRegistRow.TYPE,
                l_strQueryString,
                l_strSortCond,
                null,
                l_queryContainer
                );
        }
        catch (DataFindException l_ex)
        {
            log.error("予期しないシステムエラーが発生しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                WEB3AccInfoCommissionCourseRegist.class.getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        int l_intSize = l_lisRecords.size();

        if (l_intSize == 0)
        {
            return null;
        }

        List l_lisCommissionCourseRegists = new Vector();

        for (int i = 0; i < l_intSize; i++)
        {
            l_lisCommissionCourseRegists.add(new WEB3AccInfoCommissionCourseRegist((CommissionCourseRegistParams)l_lisRecords.get(i)));
        }

        log.exiting(STR_METHOD_NAME);
        return l_lisCommissionCourseRegists;
    }
    
    /**
     * (get証券会社コード)<BR>
     * this.委託手数料コースマスタ行.証券会社コードを返却する。<BR>
     * @@return String <BR>
     * @@roseuid 413DA9DF0193<BR>
     */
    public String getInstitutionCode()
    {
        return this.commissionCourseRegistParams.getInstitutionCode();
    }
}
@
