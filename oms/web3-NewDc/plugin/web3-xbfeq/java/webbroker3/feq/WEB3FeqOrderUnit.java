head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文単位(WEB3FeqOrderUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 王煜 (中訊) 新規作成
                   2005/07/27 艾興　@(中訊) レビュー
Revesion History : 2007/08/13 韓斌 (中訊) モデル　@No.355対応
Revesion History : 2008/01/16 柴双紅(中訊) モデルNo.372
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.466
*/

package webbroker3.feq;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3TemporaryExecutionFlagDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderStatusDef;
import webbroker3.feq.data.FeqOrderChangeStatusDao;
import webbroker3.feq.data.FeqOrderChangeStatusRow;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqExpirationStatusDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式注文単位)<BR>
 * 外国株式注文単位<BR>
 *
 * @@author 王煜(中訊)
 * @@version 1.0
 */
public class WEB3FeqOrderUnit extends FeqOrderUnitImpl
{
    /**
     * ログユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqOrderUnit.class);

    /**
     * コンストラクタ<BR>
     */
    protected WEB3FeqOrderUnit(long l_orderUnitId)
        throws DataQueryException, DataNetworkException
    {
        super(l_orderUnitId);
    }

    /**
     * コンストラクタ<BR>
     */
    protected WEB3FeqOrderUnit(FeqOrderUnitRow row)
    {
        super(row);
    }

    /**
     * (isHOST発注)<BR>
     * HOSTから入力された注文かを判定する。<BR>
     * <BR>
     * （this.注文単位行.注文経路区分 == 9：HOST）<BR>
     * の場合true、以外はfalseを返却する。<BR>
     * @@return boolean
     * @@roseuid 428D6DE9006D
     */
    public boolean isHOSTOrder()
    {
        return (WEB3OrderRootDivDef.HOST.equals(
            this.m_row.getOrderRootDiv())) ? true : false;
    }

    /**
     * (get約定最終通番)<BR>
     * 約定最終通番を取得する。<BR>
     * <BR>
     * this.注文単位行.約定最終通番 を返却する。<BR>
     * @@return int
     * @@roseuid 429291AD004B
     */
    public int getLastExecutionSerialNo()
    {
        return this.m_row.getLastExecutionSerialNo();
    }

    /**
     * (get補助口座)<BR>
     * 補助口座を取得する。<BR>
     * <BR>
     * this.getSubAccountId()に該当する補助口座を返却する。<BR>
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException
     * @@roseuid 4292A9190321
     */
    public WEB3GentradeSubAccount getSubAccount() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = (WEB3GentradeSubAccount)l_accountManager.getSubAccount(
                this.getAccountId(),
                this.getSubAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }

    /**
     * (get市場)<BR>
     * 市場を取得する。<BR>
     * <BR>
     * this.注文単位行.市場IDに該当する市場オブジェクトを返却する。<BR>
     * @@return WEB3GentradeMarket
     * @@throws WEB3BaseException
     * @@roseuid 4292A9650275
     */
    public WEB3GentradeMarket getMarket() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeMarket l_market = null;
        try
        {
            l_market = (WEB3GentradeMarket)l_finApp.getFinObjectManager().getMarket(this.m_row.getMarketId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_market;
    }

    /**
     * (get通貨)<BR>
     * 通貨を取得する。<BR>
     * <BR>
     * this.getProduct().get通貨()を返却する。<BR>
     * @@return WEB3GentradeCurrency
     * @@throws WEB3BaseException
     * @@roseuid 4292AB7A0014
     */
    public WEB3GentradeCurrency getCurrency() throws WEB3BaseException
    {
        return ((WEB3FeqProduct)this.getProduct()).getCurrency();
    }

    /**
     * (get証券会社コード)<BR>
     * 証券会社コードを取得する。<BR>
     * <BR>
     * this.注文単位行.証券会社コードを返却する。<BR>
     * @@return String
     * @@roseuid 4292C54F015C
     */
    public String getInstitutionCode()
    {
        return this.m_row.getInstitutionCode();
    }

    /**
     * (get部店コード)<BR>
     * 部店コードを取得する。<BR>
     * <BR>
     * this.getBranchId()に該当する部店.getBranchCode()を返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 4292C5F20091
     */
    public String getBranchCode() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getBranchCode()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        try
        {
            l_branch = l_accountManager.getBranch(this.getBranchId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return l_branch.getBranchCode();
    }

    /**
     * (get口座コード)<BR>
     * 口座コードを取得する。<BR>
     * <BR>
     * this.getMainAccount().getAccountCode()を返却する。<BR>
     * @@return String
     * @@roseuid 4292C64302B4
     */
    public String getAccountCode()
    {
        return this.getMainAccount().getAccountCode();
    }

    /**
     * (get顧客)<BR>
     * 顧客オブジェクトを取得する。<BR>
     * <BR>
     * this.getMainAccount()を返却する。<BR>
     * @@return WEB3GentradeMainAccount
     * @@roseuid 42943CF902D7
     */
    public MainAccount getMainAccount()
    {
        return (WEB3GentradeMainAccount)super.getMainAccount();
    }

    /**
     * (get運用コード)<BR>
     * 運用コードを取得する。<BR>
     * <BR>
     * this.注文単位行.運用コードを返却する。<BR>
     * @@return String
     * @@roseuid 42A5663101EB
     */
    public String getOrderEmpCode()
    {
        return this.m_row.getOrderEmpCode();
    }

    /**
     * (get発注日)<BR>
     * 発注日を取得する。<BR>
     * <BR>
     * this.注文単位行.発注日を返却する。<BR>
     * @@return String
     * @@roseuid 42A5663101EB
     */
    public String getBizDate()
    {
        return this.m_row.getBizDate();
    }

    /**
     * (is指値)<BR>
     * （isLimitOrder）<BR>
     * <BR>
     * 指値かを取得する。<BR>
     * <BR>
     * （this.getLimitPrice() == 0）の場合false、以外trueを返却する。<BR>
     * @@return boolean
     * @@roseuid 4292AC400014
     */
    public boolean isLimitOrder()
    {
        return (this.getLimitPrice() == 0) ? false : true;
    }

    /**
     * (is買付)<BR>
     * 買付かを判定する。<BR>
     * <BR>
     * this.getSide() == ”買い”の場合true、以外falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 4292BFB602C3
     */
    public boolean isBuy()
    {
        return (SideEnum.BUY.equals(this.getSide())) ? true : false;
    }

    /**
     * (is円貨決済)<BR>
     * 円貨決済かを判定する。<BR>
     * <BR>
     * this.注文単位行.決済区分 == ”0：円貨決済”の場合、trueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 42A57036014E
     */
    public boolean isJpySettle()
    {
        return (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(
            this.m_row.getSettleDiv())) ? true : false;
    }

    /**
     * (get受付区分)<BR>
     * 引数の注文単位の受付区分を返却する。<BR>
     * <BR>
     * 返却値<BR>
     * 0：受付未済<BR>
     * 1：受付済<BR>
     * 2：受付エラー<BR>
     * <BR>
     * １）　@受付区分判別<BR>
     * 　@[①@注文単位.市場から確認済の数量==NaNの場合]<BR>
     * 　@　@[②パラメータ.注文単位.注文状態=="発注失敗(新規注文)"の場合]<BR>
     * 　@　@　@"受付エラー"を返却する。<BR>
     * <BR>
     * 　@　@[②以外の場合]<BR>
     * 　@　@（発注に失敗していない場合）<BR>
     * 　@　@　@"受付未済"を返却する。<BR>
     * <BR>
     * 　@[①@以外の場合]<BR>
     * 　@（市場から確認済みの数量に値が入っている場合）<BR>
     * 　@　@"受付済"を返却する。<BR>
     * @@return String
     * @@roseuid 42A52F5B018B
     */
    private String getAcceptDiv()
    {
        final String STR_METHOD_NAME = "getAcceptDiv()";
        log.entering(STR_METHOD_NAME);

        // １）　@受付区分判別
        String l_strReturn;
        if (this.m_row.getConfirmedQuantityIsNull())
        {
            if (OrderStatusEnum.NOT_ORDERED.equals(this.m_row.getOrderStatus()))
            {
                l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR;
            }
            else
            {
                l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN;
            }
        }
        else
        {
            l_strReturn = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN;
        }

        log.debug(STR_METHOD_NAME + ".get受付区分 : " + l_strReturn);
        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get約定状態区分)<BR>
     * 約定状態区分を返す。<BR>
     * <BR>
     * 返却値<BR>
     * 0：未約定<BR>
     * 1：一部成立<BR>
     * 2：全部成立<BR>
     * 3：約定処理中（一部成立）<BR>
     * 4：約定処理中（全部成立）<BR>
     * <BR>
     * １）　@注文単位より仮約定フラグを取得する。<BR>
     * <BR>
     * ２）　@仮約定フラグが”0：DEFAULT”の場合<BR>
     * <BR>
     * 　@２?１）　@一部成立（this.isPartiallyExecuted( ) == true）の場合、<BR>
     * 　@　@　@　@　@　@”一部成立”を返却する。<BR>
     * <BR>
     * 　@２?２）　@全部成立（this.isFullyExecuted( ) == true）の場合、<BR>
     * 　@　@　@　@　@　@”全部成立”を返却する。<BR>
     * <BR>
     * ３）　@仮約定フラグが”1：仮約定”の場合<BR>
     * <BR>
     * 　@３?１）　@一部成立（this.isPartiallyExecuted( ) == true）の場合、<BR>
     * 　@　@　@　@　@　@”約定処理中（一部成立）”を返却する。<BR>
     * <BR>
     * 　@３?２）　@全部成立（this.isFullyExecuted( ) == true）の場合、<BR>
     * 　@　@　@　@　@　@”約定処理中（全部成立）”を返却する。<BR>
     * <BR>
     * ４）　@上記以外の場合、”未約定”を返却する。<BR>
     * @@return String
     * @@roseuid 42A52F5B018D
     */
    public String getExecStatusDiv()
    {
        final String STR_METHOD_NAME = "getExecStatusDiv()";
        log.entering(STR_METHOD_NAME);
        
        String l_strReturn = null;
        // 仮約定フラグが”0：DEFAULT”の場合
        if (WEB3TemporaryExecutionFlagDef.DEFAULT.equals(this.m_row.getTemporaryExecutionFlag()))
        {
            if (this.isPartiallyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE;
            }
            else if (this.isFullyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE;
            }            
        }
        // 仮約定フラグが”1：仮約定”の場合
        else if (WEB3TemporaryExecutionFlagDef.TEMPORARY_EXEC.equals(this.m_row.getTemporaryExecutionFlag()))
        {
            if (this.isPartiallyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE;
            }
            else if (this.isFullyExecuted())
            {
                l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE;
            }
        }
        
        // 上記以外の場合、”未約定”を返却する。
        if (l_strReturn == null)
        {
            l_strReturn = WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE;
        }
        
        log.debug(STR_METHOD_NAME + ".get約定状態区分 : " + l_strReturn); 
        log.exiting(STR_METHOD_NAME);
        
        return l_strReturn;

    }

    /**
     * (get注文状態区分)<BR>
     * 注文状態区分を返す。<BR>
     * <BR>
     * 戻り値の注文状態区分：<BR>
     * 0：その他<BR>
     * 1：受付済（新規注文） 2：発注中（新規注文） <BR>
     * 3：発注済（新規注文） 6：発注失敗（新規注文）<BR>
     * 7：受付済（変更注文） 8：発注中（変更注文）<BR>
     * 10：発注済（変更注文） 11：発注失敗（変更注文）<BR>
     * 12：受付済（取消注文） 13：発注中（取消注文）<BR>
     * 14：発注済（取消注文） 15：発注失敗（取消注文）<BR>
     * 20：一部失効 21：全部失効 22：無効<BR>
     * 50：繰越済 51：繰越失敗<BR>
     * <BR>
     * １）　@外国株式注文マネージャ.is繰越注文単位(this)の戻り値 == true、<BR>
     * 　@　@　@かつ　@this.注文状態 == ACCEPTED<BR>
     * 　@　@　@（受付済（新規注文））の場合、"繰越済"を返す。<BR>
     * <BR>
     * ２）　@外国株式注文マネージャ.is出来るまで注文単位(this)<BR>
     * 　@　@　@の戻り値 == true、<BR>
     * 　@　@　@かつ　@this.注文失効日付≧業務日付<BR>
     *      （GtlUtils.getTradingSystem().getBizDate()）、<BR>
     * 　@　@　@かつ　@this.注文有効状態 ==CLOSED（クローズ）、<BR>
     * 　@　@　@かつ　@this.失効区分 == EXPIRED（失効済）、<BR>
     * 　@　@　@かつ　@this.注文エラー理由コード != "0000：正常"<BR>
     * 　@　@　@の場合、"繰越失敗"を返す。<BR>
     * <BR>
     * ３）　@this.isUnexecuted( ) == true、<BR>
     * 　@　@　@かつ　@this.注文有効状態 == CLOSED（クローズ）、<BR>
     * 　@　@　@かつ　@this.失効区分 == INVALIDATED_BY_MKT<BR>
     *      （マーケット拒否）の場合、<BR>
     * 　@　@　@"全部失効"を返す。<BR>
     * <BR>
     * ４）　@this.isPartiallyExecuted( ) == true、<BR>
     * 　@　@　@かつ　@this.注文有効状態 == CLOSED（クローズ）、<BR>
     * 　@　@　@かつ　@this.失効区分 == INVALIDATED_BY_MKT<BR>
     *     （マーケット拒否）の場合、<BR>
     * 　@　@　@"一部失効"を返す。<BR>
     * <BR>
     * ５）　@this.注文有効状態 == CLOSED（クローズ）、<BR>
     * 　@　@　@かつ　@this.失効区分 == EXPIRED（失効済）の場合、"無効"を返す。<BR>
     * 　@　@　@※出来終了通知で注文が失効した場合<BR>
     * <BR>
     * ６）　@上記以外の場合は、this.注文状態.intValueを文字列で返す。<BR>
     * @@return String
     * @@roseuid 42A52F5B018F
     */
    public String getOrderStatusDiv()
    {
        final String STR_METHOD_NAME = "getOrderStatusDiv()";
        log.entering(STR_METHOD_NAME);

        String l_strReturn;
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqOrderManager l_orderManager =
            (WEB3FeqOrderManager)l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY).getOrderManager();
        boolean l_blnIsCarryOverOrderUnit = l_orderManager.isCarryOverOrderUnit(this);
        boolean l_blnIsCarriedOrderUnit = l_orderManager.isCarriedOrderUnit(this);
        Timestamp l_datBizDate = new Timestamp (GtlUtils.getTradingSystem().getBizDate().getTime());
        OrderStatusEnum l_orderStsEnum = this.getOrderStatus();
        OrderOpenStatusEnum l_ordOpenStsEnum = this.getOrderOpenStatus();
        OrderExpirationStatusEnum l_ordExpStsEnum = this.getExpirationStatus();
        
        // １）　@外国株式注文マネージャ.is繰越注文単位(this)の戻り値 == true、
        // 　@　@　@かつ　@this.注文状態 == ACCEPTED（受付済（新規注文））の場合、"繰越済"を返す。
        if (l_blnIsCarryOverOrderUnit &&
            OrderStatusEnum.ACCEPTED.equals(l_orderStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.TRANSFERED;
        }

        // ２）　@外国株式注文マネージャ.is出来るまで注文単位(this)の戻り値 == true、
        // 　@　@　@かつ　@this.注文失効日付≧業務日付（GtlUtils.getTradingSystem().getBizDate()）、
        // 　@　@　@かつ　@this.注文有効状態 ==CLOSED（クローズ）、
        // 　@　@　@かつ　@this.失効区分 == EXPIRED（失効済）、
        // 　@　@　@かつ　@this.注文エラー理由コード != "0000：正常"の場合、"繰越失敗"を返す。
        else if (l_blnIsCarriedOrderUnit &&
            WEB3DateUtility.compareToDay(this.m_row.getExpirationDate(), l_datBizDate) >= 0 &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.EXPIRED.equals(l_ordExpStsEnum) &&
            !WEB3ErrorReasonCodeDef.NORMAL.equals(this.m_row.getErrorReasonCode()))
        {
            l_strReturn = WEB3OrderStatusDef.NOT_TRANSFERED;
        }

        // ３）　@this.isUnexecuted( ) == true、
        // 　@　@　@かつ　@this.注文有効状態 == CLOSED（クローズ）、
        // 　@　@　@かつ　@this.失効区分 == INVALIDATED_BY_MKT（マーケット拒否）の場合、
        // 　@　@　@"全部失効"を返す。
        else if (this.isUnexecuted() &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.FULL_INAFFECTED;
        }

        // ４）　@this.isPartiallyExecuted( ) == true、
        // 　@　@　@かつ　@this.注文有効状態 == CLOSED（クローズ）、
        // 　@　@　@かつ　@this.失効区分 == INVALIDATED_BY_MKT（マーケット拒否）の場合、
        // 　@　@　@"一部失効"を返す。
        else if (this.isPartiallyExecuted() &&
            OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.PART_INAFFECTED;
        }

        // ５）　@this.注文有効状態 == CLOSED（クローズ）、
        // 　@　@　@かつ　@this.失効区分 == EXPIRED（失効済）の場合、"無効"を返す。
        // 　@　@　@※出来終了通知で注文が失効した場合
        else if (OrderOpenStatusEnum.CLOSED.equals(l_ordOpenStsEnum) &&
            OrderExpirationStatusEnum.EXPIRED.equals(l_ordExpStsEnum))
        {
            l_strReturn = WEB3OrderStatusDef.CLOSED;
        }

        // ６）　@上記以外の場合は、this.注文状態.intValueを文字列で返す。
        else
        {
            l_strReturn = new Integer(l_orderStsEnum.intValue()).toString();
        }

        log.debug(STR_METHOD_NAME + ".get注文状態区分 : " + l_strReturn);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (get処理状況区分)<BR>
     * 処理状況区分を返却する。<BR>
     * <BR>
     * 戻り値の処理状況区分：<BR>
     * ※コード値はメッセージ定義フォルダ以下の<BR>
     * 「ﾒｯｾｰｼﾞ定義書_外国株式(共通).xls」の処理状況区分定義を参照。<BR>
     * <BR>
     * １）　@this.get受付区分()をコールし、<BR>
     * 　@受付区分を取得する。<BR>
     * <BR>
     * ２）　@this.get約定状態区分()をコールし、<BR>
     * 　@約定区分を取得する。<BR>
     * <BR>
     * ３）　@失効区分を判定する。<BR>
     * 　@　@this.get注文状態区分()をコールする。<BR>
     * 　@　@　@・this.get注文状態区分() == "一部失効"の場合は、"1：一部失効"。<BR>
     * 　@　@　@・this.get注文状態区分() == "全部失効"の場合は、"2：全部失効"。<BR>
     * 　@　@　@・上記以外の場合は、"0：失効なし"。<BR>
     * <BR>
     * ４）　@処理状況区分返却<BR>
     * 　@[１）で取得した受付区分 == "受付エラー"の場合]<BR>
     * 　@　@"受付エラー"を処理区分として返す。<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@１）の戻り値<BR>
     * 　@　@+ ２）の戻り値<BR>
     * 　@　@+ ３）の判定結果<BR>
     * 　@　@+ this.注文訂正・取消区分を返却する。<BR>
     * 　@※一覧に存在しない組み合わせでもそのまま返却する。<BR>
     * @@return String
     * @@roseuid 42A52F5B01AA
     */
    public String getTransactionStateType()
    {
        final String STR_METHOD_NAME = "getTransactionStateType()";
        log.entering(STR_METHOD_NAME);

        // １）　@this.get受付区分()をコールし、受付区分を取得する。
        String l_strAcceptDiv = this.getAcceptDiv();

        // ２）　@this.get約定状態区分()をコールし、約定区分を取得する。
        String l_strExecStatusDiv = this.getExecStatusDiv();

        // ３）　@失効区分を判定する。
        // this.get注文状態区分()をコールする。
        String l_strOrderStatusDiv = this.getOrderStatusDiv();
        String l_strExpStsDiv;

        //this.get注文状態区分() == "一部失効"の場合は、"1：一部失効"。
        if (WEB3OrderStatusDef.PART_INAFFECTED.equals(l_strOrderStatusDiv))
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_ONE_COMPLETE;
        }

        //this.get注文状態区分() == "全部失効"の場合は、"2：全部失効"。
        else if (WEB3OrderStatusDef.FULL_INAFFECTED.equals(l_strOrderStatusDiv))
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_ALL_COMPLETE;
        }

        //上記以外の場合は、"0：失効なし"。
        else
        {
            l_strExpStsDiv = WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE;
        }

        // ４）　@処理状況区分返却
        // [１）で取得した受付区分 == "受付エラー"の場合]
        String l_strReturn;
        if (WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR.equals(l_strAcceptDiv))
        {
            l_strReturn =
                WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR +
                WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE +
                WEB3FeqExpirationStatusDef.EXPIRATION_TYPE_NOT_PROMISE +
                WEB3ModifyCancelTypeDef.INITIAL_VALUE;
        }
        
        // [上記以外の場合]
        else
        {
            l_strReturn =
                l_strAcceptDiv +
                l_strExecStatusDiv +
                l_strExpStsDiv +
                this.m_row.getModifyCancelType();
        }

        log.debug(STR_METHOD_NAME + ".get処理状況区分 : " + l_strReturn);

        log.exiting(STR_METHOD_NAME);
        return l_strReturn;
    }

    /**
     * (is注文受付可能)<BR>
     * 指定の変更後受付区分を指定可能かを判定する。<BR>
     * <BR>
     * <BR>
     * １）　@失効区分取得<BR>
     * 　@this.getExpirationStatus()にて失効区分を取得する。<BR>
     * <BR>
     * ２）　@失効区分の比較<BR>
     * 　@（失効区分 == 3：マーケット拒否）<BR>
     * の場合、失効の取消（変更後受付区分 == ”03：注文受付済取消”）<BR>
     * であればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@※（失効区分 != 3：マーケット拒否）の場合、３）以降の処理を行う。<BR>
     * <BR>
     * ３）　@注文状態取得<BR>
     * 　@this.getOrderStatus()にて注文状態を取得する。<BR>
     * <BR>
     * ４）　@状態の比較<BR>
     * 　@○　@（注文状態 == 2:発注中（新規注文）10:発注済（変更注文 閉局時間帯））の場合<BR>
     * 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@　@01：注文受付済<BR>
     * 　@　@02：注文受付エラー<BR>
     * 　@　@31：出来ず<BR>
     * <BR>
     * 　@○　@（注文状態 == 13:発注中（取消注文））の場合<BR>
     * 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@　@21：取消済<BR>
     * 　@　@22：取消エラー<BR>
     * 　@　@31：出来ず<BR>
     * <BR>
     * 　@○　@（注文状態 == 8:発注中（変更注文））の場合<BR>
     * 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@　@11：訂正済<BR>
     * 　@　@12：訂正エラー<BR>
     * 　@　@31：出来ず<BR>
     * <BR>
     * 　@○　@（注文状態 == 3:発注済（新規注文） Or 10:発注済（変更注文））の場合<BR>
     * 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@　@03：注文受付済取消<BR>
     * 　@　@31：出来ず<BR>
     * <BR>
     * 　@○　@（注文状態 == 14:発注済（取消注文））の場合<BR>
     * 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。<BR>
     * <BR>
     * 　@　@03：注文受付済取消<BR>
     * <BR>
     * 　@○　@（注文状態 == 11:発注失敗（変更注文） Or<BR>
     * 　@　@ 15:発注失敗（取消注文））の場合<BR>
     * 　@　@変更後受付区分が以下の何れかであればtrue、<BR>
     * 　@　@以外はfalseを返却する。<BR>
     * <BR>
     * 　@　@31：出来ず<BR>
     * <BR>
     * 　@○ 以外の場合、falseを返却する。<BR>
     * @@param l_strAfterChangeAcceptDiv - (変更後受付区分)<BR>
     * 変更後受付区分<BR>
     * <BR>
     * 01：注文受付済<BR>
     * 02：注文受付エラー<BR>
     * 03：注文受付済取消<BR>
     * <BR>
     * 11：訂正済<BR>
     * 12：訂正エラー<BR>
     * <BR>
     * 21：取消済<BR>
     * 22：取消エラー<BR>
     * <BR>
     * 31：出来ず<BR>
     *
     * @@return boolean
     * @@roseuid 42A560A003B0
     */
    public boolean isOrderAcceptPoss(String l_strAfterChangeAcceptDiv)
    {
        final String STR_METHOD_NAME = "isOrderAcceptPoss(String)";
        log.entering(STR_METHOD_NAME);

        // １）　@失効区分取得
        OrderExpirationStatusEnum l_ordExpStsEnum = this.getExpirationStatus();

        // ２）　@失効区分の比較
        boolean l_blnReturn = false;
        // （失効区分 == 3：マーケット拒否）
        if (OrderExpirationStatusEnum.INVALIDATED_BY_MKT.equals(l_ordExpStsEnum))
        {
            // 失効の取消（変更後受付区分 == ”03：注文受付済取消”）であればtrue、以外はfalseを返却する。
            l_blnReturn = WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv);

            log.debug(STR_METHOD_NAME + ".is注文受付可能 : " + l_blnReturn);
            if (!l_blnReturn)
            {
                log.error(STR_METHOD_NAME + ".is注文受付可能がfalseの場合");
                log.error(STR_METHOD_NAME + "失効区分 == 3：マーケット拒否の場合");
                log.error(STR_METHOD_NAME + "受付区分:" + l_strAfterChangeAcceptDiv);
            }
            log.exiting(STR_METHOD_NAME);
            return l_blnReturn;
        }

        // ３）　@注文状態取得
        OrderStatusEnum l_ordStsEnum = this.getOrderStatus();
        boolean l_confirmedPrice = this.isConfirmedPriceMarketOrder();

        // ４）　@状態の比較
        // ○　@（注文状態 == 2:発注中（新規注文）又は　@10:発注済（変更注文 閉局時間帯））の場合
        if (OrderStatusEnum.ORDERING.equals(l_ordStsEnum) ||
        (OrderStatusEnum.MODIFIED.equals(l_ordStsEnum) && l_confirmedPrice))
        {
            // 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。
            // 　@　@01：注文受付済
            // 　@　@02：注文受付エラー
            // 　@　@31：出来ず
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // ○　@（注文状態 == 13:発注中（取消注文））の場合
        else if (OrderStatusEnum.CANCELLING.equals(l_ordStsEnum))
        {
            // 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。
            // 　@　@21：取消済
            // 　@　@22：取消エラー
            // 　@　@31：出来ず
            l_blnReturn =
                WEB3FeqAcceptTypeDef.CANCEL.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CANCEL_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // ○　@（注文状態 == 8:発注中（変更注文））の場合
        else if (OrderStatusEnum.MODIFYING.equals(l_ordStsEnum))
        {
            // 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。
            // 　@　@11：訂正済
            // 　@　@12：訂正エラー
            // 　@　@31：出来ず
            l_blnReturn =
                WEB3FeqAcceptTypeDef.CHANGED.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.CHANGE_ERROR.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // 　@○　@（注文状態 == 3:発注済（新規注文） Or 10:発注済（変更注文））の場合
        else if (OrderStatusEnum.ORDERED.equals(l_ordStsEnum) ||
            (OrderStatusEnum.MODIFIED.equals(l_ordStsEnum) && !l_confirmedPrice))
        {
            // 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。
            // 　@　@03：注文受付済取消
            // 　@　@31：出来ず
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv) ||
                WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // 　@○　@（注文状態 == 14:発注済（取消注文））の場合
        else if (OrderStatusEnum.CANCELLED.equals(l_ordStsEnum))
        {
            // 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。
            // 　@　@03：注文受付済取消
            l_blnReturn =
                WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE_CANCEL.equals(l_strAfterChangeAcceptDiv);
        }

        // ○　@（注文状態 == 11:発注失敗（変更注文） Or 15:発注失敗（取消注文））の場合
        else if (OrderStatusEnum.NOT_MODIFIED.equals(l_ordStsEnum) ||
            OrderStatusEnum.NOT_CANCELLED.equals(l_ordStsEnum))
        {
            // 　@　@変更後受付区分が以下の何れかであればtrue、以外はfalseを返却する。
            // 　@　@31：出来ず
            l_blnReturn = WEB3FeqAcceptTypeDef.NOT_EXECUTED.equals(l_strAfterChangeAcceptDiv);
        }

        // ○ 以外の場合、falseを返却する。

        log.debug(STR_METHOD_NAME + ".is注文受付可能 : " + l_blnReturn);

        if (!l_blnReturn)
        {
            log.error(STR_METHOD_NAME + ".is注文受付可能がfalseの場合");
            log.error(STR_METHOD_NAME + "注文状態:" + l_ordStsEnum);
            log.error(STR_METHOD_NAME + "受付区分:" + l_strAfterChangeAcceptDiv);
        }
        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
    }

    /**
     * (is訂正注文)<BR>
     * 訂正注文かを判定する。<BR>
     * <BR>
     * 以下の条件で外株訂正状況テーブルからレコードを取得する。<BR>
     * <BR>
     *    [条件]<BR>
     *    口座ID = this.口座ID<BR>
     *    新規注文ID = this.注文ID<BR>
     * <BR>
     * レコードが取得できた場合は true を、<BR>
     * 取得できなかった場合は false を返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 42A94C1A030B
     */
    public boolean isChangeOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isChangeOrder()";
        log.entering(STR_METHOD_NAME);

        try
        {
            FeqOrderChangeStatusRow l_row = FeqOrderChangeStatusDao.findRowByAccountIdNewOrderId(
                this.getAccountId(),
                new Long(this.getOrderId()));
            

            // レコードが取得できた場合は true を、取得できなかった場合は false を返却する。
            log.exiting(STR_METHOD_NAME);
            return (l_row == null) ? false : true;
        }
        catch (DataFindException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error(this.getClass().getName() + "." + STR_METHOD_NAME, l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }

    /**
     * (is出来終了)<BR>
     * 出来終了処理済みかを判定する。<BR>
     * <BR>
     * （注文単位行.出来終了処理日時 == null）の場合、false<BR>
     * （注文単位行.出来終了処理日時 != null）の場合、true<BR>
     * をそれぞれ返却する。<BR>
     * @@return boolean
     * @@roseuid 42AFDEA903A8
     */
    public boolean isExecEnd()
    {
        return (this.m_row.getExecEndTimestamp() == null) ? false : true;
    }
    
    /**
     * (get約定SEQ)<BR>
     * 約定SEQを取得する。<BR>
     * <BR>
     * this．注文単位行．約定SEQを返却する。<BR>
     * <BR>
     * @@return String
     */
    public String getExecutionSeqNo()
    {
        if (this.m_row.getExecutionSeqNoIsNull())
        {
            return null;
        }

        return String.valueOf(this.m_row.getExecutionSeqNo());
    }
}
@
