head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EquityExecutedMailSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定メール送信サービス(WEB3EquityExecutedMailSenderServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 張坤芳(中訊) 作成
Revesion History : 2007/02/12 唐性峰(中訊) ＤＢ更新仕様 NO.194
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeProductImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.equity.WEB3EquityFinTransactionManager;
import webbroker3.equity.data.EqtypeOrderExecSendMailParams;
import webbroker3.equity.data.EqtypeOrderExecSendMailRow;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.util.WEB3LogUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3StatusDef;

/**
 * （約定メール送信サービスImpl）。<BR>
 * <BR>
 * 約定メール送信サービスの実装クラス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）
 * @@version 1.0
 */
public class WEB3EquityExecutedMailSenderServiceImpl
    implements WEB3EquityExecutedMailSenderService
{

    /**
     * (ログ出力ユーティリティ。)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3EquityExecutedMailSenderServiceImpl.class);

    /**
     * @@roseuid 40B4D1F901F4
     */
    public WEB3EquityExecutedMailSenderServiceImpl()
    {

    }

    /**
     * （sendMailProcess）<BR>
     * <BR>
     * 株式約定メール送信処理を行う。<BR>
     * <BR>
     * 引数.注文単位を判定し、以下のいずれかのメールデータを作成し<BR>
     * 株式約定メール送信テーブルに行を作成する。<BR>
     * <BR>
     * ただし、未約定の場合は<BR>
     * 顧客マスタ.株式未約定メール送信フラグ≠<BR>
     * 「送信要：BooleanEnum.TRUE」<BR>
     * の場合は以下処理を行わず、returnする。<BR>
     * <BR>
     * 一部約定、全部約定の場合は<BR>
     * 顧客マスタ.株式約定メール送信フラグ≠<BR>
     * 「送信要：BooleanEnum.TRUE」<BR>
     * の場合は以下処理を行わず、returnする。<BR>
     * <BR>
     * また、引数の登録済レコード有無確認フラグ＝true（有無を確認する）の場合は、<BR>
     * 主キーが重複するレコードが存在する場合は、以下処理を行わず、returnする。<BR>
     * <BR>
     * １）　@約定（失効）メールデータ作成<BR>
     * 　@引数.失効理由コード≠nullの場合、create失効メール()にて失効メールを作成する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@注文単位：　@注文単位オブジェクト<BR>
     * 　@失効理由コード：　@引数.失効理由コード<BR>
     * <BR>
     * 　@引数.失効理由コード＝nullの場合、create約定メール()にて約定メールを作成する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@注文単位：　@注文単位オブジェクト<BR>
     * <BR>
     * ２）　@メールデータ挿入<BR>
     * 　@insertMail()メソッドをコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@１）の戻り値の株式約定メール送信レコード<BR>
     * <BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位オブジェクト<BR>
     * @@param l_strReasonCode - (失効理由コード)<BR>
     * 失効理由コード。<BR>
     * 約定の場合はnullを指定する。<BR>
     * @@param l_blnConfirmAlreadyIns - (登録済レコード有無確認フラグ)<BR>
     * 登録済レコード有無確認フラグ。<BR>
     * @@roseuid 403DC3D002AF
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode, boolean l_blnConfirmAlreadyIns)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "sendMailProcess(OrderUnit, String, boolean)";
        log.entering(STR_METHOD_NAME);

        // 顧客オブジェクトの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();
        MainAccount l_account = null;
        try
        {
            l_account = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());
        }
        catch (NotFoundException l_exp)
        {
            log.error("顧客オブジェクトが取得できません： account_id = [" + l_orderUnit.getAccountId() + "]", l_exp);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_exp.toString(),
                l_exp);
        }
        MainAccountRow l_accountRow = (MainAccountRow)l_account.getDataSourceObject();
        if (l_strReasonCode == null)
        {
            if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                if (BooleanEnum.TRUE.equals(l_accountRow.getEquityOrderExeMailFlag()) == false)
                {
                    log.debug("口座ID：[" + l_orderUnit.getAccountId() +
                        "]の顧客マスター.株式約定メール送信フラグ≠”送信要”のため、メールを送信しません。");
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
            }
            else
            {
                if (l_orderUnit.isUnexecuted())
                {
                    if (BooleanEnum.TRUE.equals(l_accountRow.getEquityOrderUnexecMailFlag()) == false)
                    {
                        log.debug("口座ID：[" + l_orderUnit.getAccountId() +
                            "]の顧客マスター.株式未約定メール送信フラグ≠”送信要”のため、メールを送信しません。");
                        log.exiting(STR_METHOD_NAME);
                        return;
                    }
                }
                else
                {
                    if (BooleanEnum.TRUE.equals(l_accountRow.getEquityOrderExeMailFlag()) == false)
                    {
                        log.debug("口座ID：[" + l_orderUnit.getAccountId() +
                            "]の顧客マスター.株式約定メール送信フラグ≠”送信要”のため、メールを送信しません。");
                        log.exiting(STR_METHOD_NAME);
                        return;
                    }
                }
            }
        }
        
		if (l_blnConfirmAlreadyIns == true)
        {
        	//レコードが既に登録済かどうかを確認し、
        	//登録済の場合は、メールデータの登録を行わない。
			EqtypeOrderUnitRow l_orderUnitRow =
				(EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
			StringBuffer l_sbWhere = new StringBuffer();
			l_sbWhere.append(" institution_code = ? ");
			l_sbWhere.append(" and branch_code = ? ");
			l_sbWhere.append(" and account_code = ? ");
			l_sbWhere.append(" and order_request_number = ? ");
			if (l_strReasonCode != null)
			{
				//失効メール
				l_sbWhere.append(" and order_exec_status = ? ");
			}
			else
			{
				//約定メール
				l_sbWhere.append(" and order_exec_status <> ?");
			}

			try
			{
				QueryProcessor l_processor = Processors.getDefaultProcessor();
				Object[] l_objWhere =
					{l_account.getInstitution().getInstitutionCode(),
					l_account.getBranch().getBranchCode(),
					l_account.getAccountCode(),
					l_orderUnitRow.getOrderRequestNumber(),
					WEB3OrderExecStatusDef.CLOSE
				};

				List l_lstRecords =
					l_processor.doFindAllQuery(
						EqtypeOrderExecSendMailParams.TYPE,
						l_sbWhere.toString(),
						l_objWhere);
				if ((l_lstRecords != null) && (l_lstRecords.size() > 0))
				{
					log.exiting(STR_METHOD_NAME);
					return;
				}
			}
			catch (Exception l_ex)
			{
				log.error("DBへのアクセスに失敗しました。", l_ex);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80003,
					this.getClass().getName() + STR_METHOD_NAME,
					l_ex.getMessage(), l_ex);
			}
		}
        EqtypeOrderExecSendMailRow l_eqtypeOrderExecSendMailRow = null;
        EqtypeOrderExecSendMailParams l_orderExecSendMailParams = null;
        if (l_strReasonCode != null)
        {
            //失効理由コード≠nullの場合,失効メールを作成
            l_eqtypeOrderExecSendMailRow =
                createCloseMail(l_orderUnit, l_strReasonCode);
        }
        else
        {
            //失効理由コード = nullの場合,約定メールを作成
            l_eqtypeOrderExecSendMailRow = createExecutedMail(l_orderUnit);
        }
        //メールデータ挿入
        l_orderExecSendMailParams =
            new EqtypeOrderExecSendMailParams(l_eqtypeOrderExecSendMailRow);
        insertMail(l_orderExecSendMailParams);

        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * （sendMailProcess）<BR>
	 * <BR>
	 * 株式約定メール送信処理を行う。<BR>
	 * （* this.sendMailProcess(注文単位, String, booleanにdelegateする。）<BR>
	 * <BR>
	 * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位オブジェクト<BR>
     * @@param l_strReasonCode - (失効理由コード)<BR>
     * 失効理由コード。<BR>
     * 約定の場合はnullを指定する。<BR>
     */
	public void sendMailProcess(OrderUnit l_orderUnit, String l_strReasonCode)
		throws WEB3BaseException
	{
		String STR_METHOD_NAME = "sendMailProcess(OrderUnit, String)";
		log.entering(STR_METHOD_NAME);
		
		this.sendMailProcess(l_orderUnit, l_strReasonCode, false);
		log.exiting(STR_METHOD_NAME);
	}

    /**
     * (create約定メール)<BR>
     * 約定メール行（顧客宛て）を作成し返却する。<BR>
     * <BR>
     * 編集内容は、<BR>
     * 「約定メール送信サービス_株式約定メール送信テーブル.xls」の「（DB更新[約定ﾒｰﾙ]）<BR>
     * 約定ﾒｰﾙ送信ﾃｰﾌﾞﾙ」シートを参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位オブジェクト<BR>
     * @@return EqtypeOrderExecSendMailRow
     * @@throws WEB3BaseException
     * @@roseuid 413832980142
     */
    public EqtypeOrderExecSendMailRow createExecutedMail(OrderUnit l_orderUnit)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = "createExecutedMail(OrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (!(l_orderUnit instanceof EqTypeOrderUnit))
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //FinAppの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        EqtypeOrderExecSendMailParams l_sendMailParams =
            new EqtypeOrderExecSendMailParams();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderExecSendMailRow l_orderExecSendMailRow = null;

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityFinTransactionManager l_finTransactionMgr =
                (WEB3EquityFinTransactionManager)l_tradingModule
                    .getFinTransactionManager();
            
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());
            MainAccount l_mainAccount =
                l_accountMgr.getMainAccount(l_orderUnit.getAccountId());
            
            //証券会社コード
            l_sendMailParams.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //部店コード
            l_sendMailParams.setBranchCode(l_branch.getBranchCode());
            //口座コード
            String l_strAccountCode = l_mainAccount.getAccountCode();
            l_sendMailParams.setAccountCode(l_strAccountCode);
            //識別コード
            l_sendMailParams.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //銘柄コード
            EqTypeProductImpl l_product =
                new EqTypeProductImpl(l_orderUnitRow.getProductId());
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();
            l_sendMailParams.setProductCode(l_productRow.getProductCode());
            //市場コード
            Market l_market =
                l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());
            l_sendMailParams.setMarketCode(l_market.getMarketCode());
            //注文履歴ＩＤ
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            int l_length = 0;

            if (l_orderActions != null)
            {
                l_length = l_orderActions.length;
            }
            for (int i = 0; i < l_length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo()
                    == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_sendMailParams.setOrderActionId(
                        l_orderActions[i].getOrderActionId());
                    break;
                }
            }
            //税区分
            l_sendMailParams.setTaxType(l_orderUnit.getTaxType());
            //受注日時
            l_sendMailParams.setReceivedDateTime(
                l_orderUnitRow.getReceivedDateTime());
            //約定区分
            //注文単位.注文状態=="発注済（取消注文）"の場合：
            //"2：約定済"（固定でセット）
            if (OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.EXECUTED);
            }
            //注文単位.isUnexecuted()==trueの場合は、０：未約定。
            else if (l_orderUnit.isUnexecuted())
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.UNEXECUTED);
            }
            //注文単位.isPartiallyExecuted()==trueの場合は、１：一部約定。
            else if (l_orderUnit.isPartiallyExecuted())
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.PARTIALLY_EXECUTED);
            }
            //注文単位.isFullyExecuted()==trueの場合は、２：約定済。
            else if (l_orderUnit.isFullyExecuted())
            {
                l_sendMailParams.setOrderExecStatus(
                    WEB3OrderExecStatusDef.EXECUTED);
            }
            //受渡代金
            double l_dblNetAmount =
                l_finTransactionMgr.getNetAmountTotal((EqTypeOrderUnit)l_orderUnit);
            if (OrderTypeEnum.EQUITY_BUY.equals(l_orderUnit.getOrderType()) ||
                OrderTypeEnum.SWAP_MARGIN_LONG.equals(l_orderUnit.getOrderType()))
            {
                l_sendMailParams.setDelivalyAmount(l_dblNetAmount * -1D);
            }
            else
            {
                l_sendMailParams.setDelivalyAmount(l_dblNetAmount);
            }
            //約定数量
            if (l_orderUnitRow.getExecutedQuantityIsNull())
            {
                l_sendMailParams.setExecutedQuantity(null);
            }
            else
            {
                l_sendMailParams.setExecutedQuantity(
                    l_orderUnitRow.getExecutedQuantity());
            }
            //注文種別
            l_sendMailParams.setOrderType(l_orderUnit.getOrderType());
            //取引コード（SONAR）
            l_sendMailParams.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //電子メール送信ステイタス
            l_sendMailParams.setStatus(WEB3StatusDef.NOT_DEAL);
            //電子メール送信日時
            l_sendMailParams.setSendProcessDateTime(null);
            //再送区分
            l_sendMailParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
            //電子メール再送日時
            l_sendMailParams.setResendProcessDateTime(null);
            //失効理由コード
            l_sendMailParams.setReasonCode(null);
            //指値
            l_sendMailParams.setLimitPrice(l_orderUnitRow.getLimitPrice());
            //emailアドレス
            MainAccountRow l_accountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_sendMailParams.setEmailAddress(l_accountRow.getEmailAddress());
            //注文株数
            l_sendMailParams.setOrderQuantity(l_orderUnitRow.getQuantity());
            //削除フラグ
            l_sendMailParams.setDeleteFlag(BooleanEnum.FALSE);
            //
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            //作成日時
            l_sendMailParams.setCreatedTimestamp(l_timeStamp);
            //更新日時
            l_sendMailParams.setLastUpdatedTimestamp(l_timeStamp);
            l_orderExecSendMailRow =
                (EqtypeOrderExecSendMailRow)l_sendMailParams;

        }
        catch (NotFoundException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderExecSendMailRow;
    }

    /**
     * (create失効メール)<BR>
     * 失効メール行（証券会社宛て）を作成し返却する。<BR>
     * <BR>
     * 編集内容は、<BR>
     * 「約定メール送信サービス_株式約定メール送信テーブル.xls」の<BR>
     * 「（[失効ﾒｰﾙ]）株式約定ﾒｰﾙ送信」シートを参照。<BR>
     * @@param l_orderUnit - (注文単位)<BR>
     * 株式注文単位オブジェクト<BR>
     * @@param l_strReasonCode - (失効理由コード)<BR>
     * 失効理由コード。<BR>
     * 約定の場合はnullを指定する。<BR>
     * @@return EqtypeOrderExecSendMailRow
     * @@throws WEB3BaseException
     * @@roseuid 41383298019C
     */
    public EqtypeOrderExecSendMailRow createCloseMail(
        OrderUnit l_orderUnit,
        String l_strReasonCode)
        throws WEB3BaseException
    {

        String STR_METHOD_NAME =
            "createCloseMail(OrderUnit l_orderUnit,String l_strReasonCode)";
        log.entering(STR_METHOD_NAME);

        if (!(l_orderUnit instanceof EqTypeOrderUnit))
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //FinAppの取得
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        EqtypeOrderExecSendMailParams l_sendMailParams =
            new EqtypeOrderExecSendMailParams();
        EqtypeOrderUnitRow l_orderUnitRow =
            (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();
        EqtypeOrderExecSendMailRow l_orderExecSendMailRow = null;

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());
            MainAccount l_mainAccount =
                l_accountMgr.getMainAccount(l_orderUnit.getAccountId());

            //証券会社コード
            l_sendMailParams.setInstitutionCode(
                l_branch.getInstitution().getInstitutionCode());
            //部店コード
            l_sendMailParams.setBranchCode(l_branch.getBranchCode());
            //口座コード
            String l_strAccountCode = l_mainAccount.getAccountCode();
            l_sendMailParams.setAccountCode(l_strAccountCode);
            //識別コード
            l_sendMailParams.setOrderRequestNumber(
                l_orderUnitRow.getOrderRequestNumber());
            //銘柄コード
            EqTypeProductImpl l_product =
                new EqTypeProductImpl(l_orderUnitRow.getProductId());
            EqtypeProductRow l_productRow =
                (EqtypeProductRow)l_product.getDataSourceObject();
            l_sendMailParams.setProductCode(l_productRow.getProductCode());
            //市場コード
            Market l_market =
                l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());
            l_sendMailParams.setMarketCode(l_market.getMarketCode());
            //注文履歴ＩＤ
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            int l_length = 0;

            if (l_orderActions != null)
            {
                l_length = l_orderActions.length;
            }
            for (int i = 0; i < l_length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo()
                    == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_sendMailParams.setOrderActionId(
                        l_orderActions[i].getOrderActionId());
                    break;
                }
            }
            //税区分
            l_sendMailParams.setTaxType(l_orderUnit.getTaxType());
            //受注日時
            l_sendMailParams.setReceivedDateTime(
                l_orderUnitRow.getReceivedDateTime());
            //約定区分          
            l_sendMailParams.setOrderExecStatus(WEB3OrderExecStatusDef.CLOSE);
            //受渡代金
            l_sendMailParams.setDelivalyAmount(0);
            //約定数量
            l_sendMailParams.setExecutedQuantity(0);
            //注文種別
            l_sendMailParams.setOrderType(l_orderUnit.getOrderType());
            //取引コード（SONAR）
            l_sendMailParams.setSonarTradedCode(l_orderUnitRow.getSonarTradedCode());
            //電子メール送信ステイタス
            l_sendMailParams.setStatus(WEB3StatusDef.NOT_DEAL);
            //電子メール送信日時
            l_sendMailParams.setSendProcessDateTime(null);
            //再送区分
            l_sendMailParams.setResendStatus(WEB3ResendStatusDef.DEFAULT);
            //電子メール再送日時
            l_sendMailParams.setResendProcessDateTime(null);
            //失効理由コード
            l_sendMailParams.setReasonCode(l_strReasonCode);
            //指値
            if(l_orderUnitRow.getLimitPriceIsNull())
            {
                l_sendMailParams.setLimitPrice(null);
            }
            else
            {
                l_sendMailParams.setLimitPrice(l_orderUnit.getLimitPrice());
            }
            
            //emailアドレス
            l_sendMailParams.setEmailAddress(null);
            //注文株数
            l_sendMailParams.setOrderQuantity(l_orderUnit.getQuantity());
                       
            //削除フラグ
            l_sendMailParams.setDeleteFlag(BooleanEnum.FALSE);
            //
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            //作成日時
            l_sendMailParams.setCreatedTimestamp(l_timeStamp);
            //更新日時
            l_sendMailParams.setLastUpdatedTimestamp(l_timeStamp);
            l_orderExecSendMailRow =
                (EqtypeOrderExecSendMailRow)l_sendMailParams;
        }
        catch (NotFoundException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
        return l_orderExecSendMailRow;

    }

    /**
     * 株式約定メール送信テーブルに引数の内容で行を挿入する。<BR>
     * @@param l_orderExecSendMail - (株式約定電子メール送信レコード)<BR>
     * 株式約定メール送信テーブル行<BR>
     * @@throws WEB3BaseException
     * @@roseuid 403EAF5201AB
     */
    public void insertMail(EqtypeOrderExecSendMailParams l_orderExecSendMail)
        throws WEB3BaseException
    {
        //データ挿入する
        String STR_METHOD_NAME = "insertMail(EqtypeOrderExecSendMailParams)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor processor = null;
        try
        {
            processor = Processors.getDefaultProcessor();
            processor.doInsertQuery(l_orderExecSendMail);
        }
        catch (DataFindException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataQueryException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }
        catch (DataNetworkException e)
        {
            String l_strMsg = "データ取得時エラー";
            log.error(l_strMsg, e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                e.toString(),
                e);
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * (undoSendMail)<BR>
     * 引数の注文単位に該当する約定メール（または失効メール）を無効にする。<BR>
     * <BR>
     * 株式約定メール送信テーブルの、以下の条件に当てはまる行を削除する。<BR>
     * <BR>
     * ------------------------------------------------<BR>
     * ＜削除条件＞<BR>
     * <BR>
     * 株式約定メール送信テーブル.証券会社コード = <BR>
     * 　@注文単位.部店ＩＤに該当する部店の証券会社コード <BR>
     * 株式約定メール送信テーブル.部店コード = <BR>
     * 　@注文単位.部店ＩＤに該当する部店の部店コード <BR>
     * 株式約定メール送信テーブル.口座コード = <BR>
     * 　@注文単位.口座ＩＤに該当する顧客の口座コード <BR>
     * 株式約定メール送信テーブル.識別コード = <BR>
     * 　@注文単位.識別コード <BR>
     * <BR>
     * 更新内容は、 <BR>
     * 「約定メール送信サービス_株式約定メール送信テーブル.xls」 <BR>
     * [（株式ﾒｰﾙ送信）取消]シートを参照。 <BR>
     * ------------------------------------------------<BR>
     * @@param l_orderUnit - 注文単位。
     * @@roseuid 40F2664D004C
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoSendMail(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);
        if (!(l_orderUnit instanceof EqTypeOrderUnit))
        {
            log.error("パラメータタイプ不正。");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());
            //throw NotFoundException
            MainAccount l_mainAccount =
                l_accountMgr.getMainAccount(l_orderUnit.getAccountId());
            //throw NotFoundException

            EqtypeOrderUnitRow l_orderUnitRow =
                (EqtypeOrderUnitRow)l_orderUnit.getDataSourceObject();

            QueryProcessor l_processor = Processors.getDefaultProcessor();
            //throw DataFindException, DataNetworkException

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? "); //証券会社コード
            l_sbWhere.append(" and branch_code = ? "); //部店コード
            l_sbWhere.append(" and account_code = ? "); //口座コード
            l_sbWhere.append(" and order_request_number = ? "); //識別コード

            Object[] l_objWhere =
                { l_branch.getInstitution().getInstitutionCode(), //証券会社コード
                l_branch.getBranchCode(), //部店コード
                l_mainAccount.getAccountCode(), //口座コード
                l_orderUnitRow.getOrderRequestNumber() //識別コード
            };

            List l_lstRecords =
                l_processor.doFindAllQuery(
                    EqtypeOrderExecSendMailParams.TYPE,
                    l_sbWhere.toString(),
                    l_objWhere);
            int l_intSize = 0;
            if (!(l_lstRecords == null))
            {
                l_intSize = l_lstRecords.size();
            }

            for (int i = 0; i < l_intSize; i++)
            {
                EqtypeOrderExecSendMailParams l_params =
                    new EqtypeOrderExecSendMailParams(
                        (EqtypeOrderExecSendMailRow)l_lstRecords.get(i));
                l_params.setDeleteFlag(BooleanEnum.TRUE);
                //更新日時    last_updated_timestamp
                //現在日時
                Timestamp l_timeStamp =
                    GtlUtils.getTradingSystem().getSystemTimestamp();
                l_params.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doUpdateQuery((EqtypeOrderExecSendMailRow)l_params);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
