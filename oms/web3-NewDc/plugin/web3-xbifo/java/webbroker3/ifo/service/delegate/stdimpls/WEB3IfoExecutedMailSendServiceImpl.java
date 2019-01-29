head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoExecutedMailSendServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP約定メール送信サービスImpl(WEB3IfoExecutedMailSendServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/17 張宝楠 (中訊) 新規作成
                          001: 2004/08/13 呉艶飛　@(中訊) STBUG(IFO_ST-000079)を対応
Revesion History : 2008/08/18 劉剣 (中訊) IFO小数点対応
*/

package webbroker3.ifo.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManager;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3ResendStatusDef;
import webbroker3.common.define.WEB3TaxationDivDef;
import webbroker3.util.WEB3LogUtility;

import webbroker3.ifo.WEB3IfoFinTransactionManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3OptionOrderManagerImpl;
import webbroker3.ifo.data.IfoOrderExecSendMailParams;
import webbroker3.ifo.data.IfoOrderExecSendMailRow;
import webbroker3.ifo.define.WEB3IfoAttributeNameDef;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;



/**
 * (先物OP約定メール送信サービスImpl)<BR>
 * <BR>
 * 約定メール送信サービス実装クラス<BR>
 * <BR>
 * （トランザクション属性：TransactionalInterceptor.TX_JOIN_EXISTING）<BR>
 *
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3IfoExecutedMailSendServiceImpl implements WEB3IfoExecutedMailSendService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoExecutedMailSendServiceImpl.class);

    /**
     * @@roseuid 40C0753103A9
     */
    public WEB3IfoExecutedMailSendServiceImpl()
    {

    }

    /**
     * 先物OP約定メール送信処理を行う。<BR>
     * <BR>
     * 引数.注文単位を判定し、以下のいずれかのメールデータを作成し<BR>
     * 先物OP約定メール送信テーブルに行を作成する。<BR>
     * <BR>
     * １）　@約定（失効）メールデータ作成<BR>
     * 　@失効理由コードがnullでない場合、create失効メール()にて失効メールを作成する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@注文単位：　@注文単位オブジェクト<BR>
     * 　@失効理由コード：　@失効理由コード<BR>
     * <BR>
     * 　@（失効理由コード == null）の場合、create約定メール()にて約定メールを作成する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@注文単位：　@注文単位オブジェクト<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_strCloseReasonCode - 失効理由コード。<BR>
     * 約定の場合はnullを指定する。<BR>
     * @@roseuid 408483E40028
     */
    public void sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " sendMailProcess(OrderUnit l_orderUnit, String l_strCloseReasonCode)";
        log.entering(STR_METHOD_NAME);

        try
        {
            if (l_strCloseReasonCode != null)   //失効理由コードがnullでない場合
            {
                //create失効メール()にて失効メールを作成する。
                createCloseMail(l_orderUnit, l_strCloseReasonCode);
            }
            else    //失効理由コード == null
            {
                //create約定メール()にて約定メールを作成する。
                createExecutedMail(l_orderUnit);
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);

        }
        catch (DataFindException l_ex)
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

    /**
     * (create約定メール)<BR>
     * <BR>
     * 約定メール行をDBにinsertする。<BR>
     * <BR>
     * 以下の通り、約定区分に応じて先物OP約定メール送信テーブルに行を挿入する。<BR>
     * <BR>
     *  ○ 未約定（注文単位.isUnexecuted() == true））の場合<BR>
     * 　@　@　@－約定メール行オブジェクトを作成し、insertMail()にてDBに挿入する。<BR>
     * <BR>
     * 　@　@未約定メール行オブジェクトの編集内容は、<BR>
     * 　@　@「共通_先物OP約定メール送信テーブル.xls」<BR>
     *  　@[（先物OPﾒｲﾙ送信）未約定メール]シートを参照。<BR>
     * <BR>
     *  ○ 全部約定（注文単位.isFullyExecuted() == true）または、<BR>
     * 一部約定（注文単位.isPartiallyExecuted() == trueの場合<BR>
     * 　@　@　@－約定メール行オブジェクトを作成し、insertMail()にてDBに挿入する。<BR>
     * <BR>
     * 　@　@未約定メール行オブジェクトの編集内容は、<BR>
     * 　@　@「共通_先物OP約定メール送信テーブル.xls」<BR>
     *  　@[（先物OPﾒｲﾙ送信）約定メール]シートを参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408483E4002B
     */
    protected void createExecutedMail(OrderUnit l_orderUnit) throws WEB3BaseException, DataQueryException, DataNetworkException, NotFoundException
    {
        final String STR_METHOD_NAME = " createExecutedMail(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        AccountManager l_accountMgr = l_finApp.getAccountManager();
        Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
        MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

        WEB3IfoFinTransactionManagerImpl l_finTransactionMgr = (WEB3IfoFinTransactionManagerImpl)l_tradingModule.getFinTransactionManager();

        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
        Market l_market = l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());  //throw NotFoundException

        //Product l_product = l_orderUnit.getProduct();
        WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_orderUnitRow.getProductId());
        IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();

		//出来通知時にThreadLocalに設定されているシステム処理時間を取得する
		Timestamp l_realTimestamp = 
			(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute
				(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
		
		if(l_realTimestamp == null)
		{
			l_realTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
		}
				  
        if (l_orderUnit.isUnexecuted())     //未約定（注文単位.isUnexecuted() == true））の場合
        {
            //約定メール行オブジェクトを作成
            IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams();
			
            //（先物OPﾒｲﾙ送信）未約定メール

            l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //証券会社コード
            l_params.setBranchCode(l_branch.getBranchCode());                                       //部店コード
            l_params.setAccountCode(l_mainAccount.getAccountCode());                                //顧客コード

            l_params.setFutureOptionDiv(l_productRow.getFutureOptionDiv());                         //先物／オプション区分
            l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //識別コード
            l_params.setOrderExecStatus(WEB3OrderExecStatusDef.UNEXECUTED);                         //約定区分(０：未約定)
            
            //（注文単位.注文履歴最終通番　@== 注文履歴.注文履歴番号）の注文履歴の注文履歴ＩＤ
            IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingModule.getOrderManager();
            l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            int l_length = 0;
            
            if (l_orderActions != null)
            {
                l_length = l_orderActions.length; 
            }
            
            for (int i = 0; i < l_length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo() == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_params.setOrderActionId(l_orderActions[i].getOrderActionId());         //注文履歴ＩＤ
                    break;
                }
            }

            l_params.setProductCode(l_productRow.getProductCode());                                 //銘柄コード
            l_params.setMarketCode(l_market.getMarketCode());                                       //市場コード
            l_params.setOrderType(l_orderUnit.getOrderType());                                      //注文種別

            //課税区分
            if ((OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())))//注文単位.注文カテゴリ == ”新規建注文”の場合
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.IRRELEVENT);   //0：無関係
            }
            else if((OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))) //注文単位.注文カテゴリ == ”返済注文”の場合
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT); //1：申告分離
            }

            l_params.setReceivedDateTime(l_orderUnitRow.getReceivedDateTime());                       //受注日時
            l_params.setQuantity(l_orderUnitRow.getQuantity());   //注文数量
			l_params.setLimitPrice(l_orderUnitRow.getLimitPrice());   //指値
			l_params.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType().intValue());   //執行条件
			
          
			l_params.setExecutedQuantity(0D);                                        //約定数量
            l_params.setDelivalyAmount(0D);              //受渡代金

            l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                                  //電子メール送信ステイタス
            l_params.setResendStatus(WEB3ResendStatusDef.DEFAULT);                                  //再送区分
            l_params.setDeleteFlag(BooleanEnum.FALSE);                                              //削除フラグ

            l_params.setCreatedTimestamp(l_realTimestamp);                                            //指示日時
            l_params.setLastUpdatedTimestamp(l_realTimestamp);                                        //更新日時

            //insertMail()にてDBに挿入する。
            insertMail(l_params);
        }
        else if (l_orderUnit.isFullyExecuted() || l_orderUnit.isPartiallyExecuted())    //全部約定または、一部約定の場合
        {
            //約定メール行オブジェクトを作成
            IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams();

            //（先物OPﾒｲﾙ送信）約定メール

            l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //証券会社コード
            l_params.setBranchCode(l_branch.getBranchCode());                                       //部店コード
            l_params.setAccountCode(l_mainAccount.getAccountCode());                                //顧客コード


            l_params.setFutureOptionDiv(l_productRow.getFutureOptionDiv());                         //先物／オプション区分
            l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //識別コード
            
            //○注文単位.注文状態==”発注済（変更注文）” or ”発注済（取消注文）”の場合：”約定済”
            if (OrderStatusEnum.MODIFIED.equals(l_orderUnit.getOrderStatus()) || OrderStatusEnum.CANCELLED.equals(l_orderUnit.getOrderStatus()))
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.EXECUTED);   //２：約定済
            }
            //注文単位.isUnexecuted()==trueの場合は、”未約定”
            else if (l_orderUnit.isUnexecuted())
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.UNEXECUTED);   //２：未約定
            }
            //注文単位.isPartiallyExecuted()==trueの場合は、”一部約定”
            else if (l_orderUnit.isPartiallyExecuted()) //一部約定
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.PARTIALLY_EXECUTED);   //１：一部約定
            }
            //注文単位.isFullyExecuted()==trueの場合は、”約定済”
            else if (l_orderUnit.isFullyExecuted())  //全部約定
            {
                l_params.setOrderExecStatus(WEB3OrderExecStatusDef.EXECUTED);  //２：約定済
            }

            //（注文単位.注文履歴最終通番　@== 注文履歴.注文履歴番号）の注文履歴の注文履歴ＩＤ
            IfoOrderManager l_orderManager = (IfoOrderManager)l_tradingModule.getOrderManager();
                        l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            for (int i = 0; i < l_orderActions.length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo() == l_orderUnitRow.getLastOrderActionSerialNo())
                {
                    l_params.setOrderActionId(l_orderActions[i].getOrderActionId());         //注文履歴ＩＤ
                    break;
                }
            }

            l_params.setProductCode(l_productRow.getProductCode());                                 //銘柄コード
            l_params.setMarketCode(l_market.getMarketCode());                                       //市場コード
            l_params.setOrderType(l_orderUnit.getOrderType());

            //課税区分
            if ((OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())))//注文単位.注文カテゴリ == ”新規建注文”の場合
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.IRRELEVENT);   //0：無関係
            }
            else if((OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))) //注文単位.注文カテゴリ == ”返済注文”の場合
            {
                l_params.setTaxationDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT); //1：申告分離
            }

            l_params.setReceivedDateTime(l_orderUnitRow.getReceivedDateTime());                       //受注日時
            l_params.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType().intValue());   //執行条件
            
            double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
            if (Double.isNaN(l_dblExecutedQuantity))
            {
                l_dblExecutedQuantity = 0D;
            }
            l_params.setExecutedQuantity(l_dblExecutedQuantity);                        //約定数量

            double l_dblNetAmount = l_finTransactionMgr.getNetAmount(l_orderUnit);

            BigDecimal l_bdNetAmount = new BigDecimal(l_dblNetAmount + "");
            //注文単位.注文種別 == "OP新規買建注文" or "OP売建買返済注文"の場合
            if (l_orderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN) ||
                l_orderUnit.getOrderType().equals(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE))
            {
                //受渡金額合計値の符号を反転させる
                l_bdNetAmount = l_bdNetAmount.negate();
            }

            l_params.setDelivalyAmount(l_bdNetAmount.doubleValue());              //受渡代金

            l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                                  //電子メール送信ステイタス
            l_params.setResendStatus(WEB3ResendStatusDef.DEFAULT);                                  //再送区分

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            l_params.setEmailAddress(l_mainAccountRow.getEmailAddress());                           //emailアドレス

            l_params.setDeleteFlag(BooleanEnum.FALSE);                                              //削除フラグ
            
			l_params.setCreatedTimestamp(l_realTimestamp);                                            //指示日時
			l_params.setLastUpdatedTimestamp(l_realTimestamp);                                        //更新日時

            //insertMail()にてDBに挿入する。
            insertMail(l_params);
        }

        log.exiting(STR_METHOD_NAME);

    }

    /**
     * (create失効メール)<BR>
     * <BR>
     * 失効メール行（証券会社宛て）を作成し返却する。<BR>
     * <BR>
     * １）　@失効メール行（証券会社宛て）を作成する。<BR>
     * <BR>
     * 編集内容は、<BR>
     * 「共通_先物OP約定メール送信テーブル.xls」<BR>
     *  [（先物OPﾒｲﾙ送信）失効メール]シートを参照。<BR>
     * <BR>
     * ２）　@失効メール行をDBに挿入する。<BR>
     * 　@作成した先物OP約定メール送信行オブジェクトを引数に指定し、<BR>
     * insertMail()メソッドをコールする。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@param l_strCloseReasonCode - 失効理由コード。<BR>
     * 約定の場合はnullを指定する。<BR>
     * @@roseuid 408483E40037
     */
    protected void createCloseMail(OrderUnit l_orderUnit, String l_strCloseReasonCode) throws WEB3BaseException, NotFoundException, DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = " createCloseMail(OrderUnit l_orderUnit, String l_strCloseReasonCode)";
        log.entering(STR_METHOD_NAME);

        if (l_orderUnit == null)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        //TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

        AccountManager l_accountMgr = l_finApp.getAccountManager();
        Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
        MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

        FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();
        Market l_market = l_finObjMgr.getMarket(l_orderUnitRow.getMarketId());  //throw NotFoundException

        Product l_product = l_orderUnit.getProduct();
        IfoProductRow l_productRow = (IfoProductRow)l_product.getDataSourceObject();

        Timestamp l_currentTime = l_finApp.getTradingSystem().getSystemTimestamp();

        //失効メール行（証券会社宛て）を作成する
        IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams();

        l_params.setInstitutionCode(l_branch.getInstitution().getInstitutionCode());            //証券会社コード
        l_params.setBranchCode(l_branch.getBranchCode());                                       //部店コード
        l_params.setAccountCode(l_mainAccount.getAccountCode());                                //顧客コード

        l_params.setFutureOptionDiv(l_productRow.getFutureOptionDiv());                         //先物／オプション区分
        l_params.setOrderRequestNumber(l_orderUnitRow.getOrderRequestNumber());                 //識別コード
        l_params.setOrderExecStatus(WEB3OrderExecStatusDef.CLOSE);                              //約定区分(０：未約定)

        //（注文単位.注文履歴最終通番　@== 注文履歴.注文履歴番号）の注文履歴の注文履歴ＩＤ
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
        WEB3OptionOrderManagerImpl l_orderManager = (WEB3OptionOrderManagerImpl)l_tradingModule.getOrderManager();
        l_orderUnit = l_orderManager.getOrderUnit(l_orderUnit.getOrderUnitId());
        OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
        
        for (int i = 0; i < l_orderActions.length; i++)
        {
            if (l_orderActions[i].getOrderActionSerialNo() == l_orderUnitRow.getLastOrderActionSerialNo())
            {
                l_params.setOrderActionId(l_orderActions[i].getOrderActionId());         //注文履歴ＩＤ
                break;
            }
        }

        l_params.setProductCode(l_productRow.getProductCode());                                 //銘柄コード
        l_params.setMarketCode(l_market.getMarketCode());                                       //市場コード
        l_params.setOrderType(l_orderUnit.getOrderType());                                      //注文種別

        //課税区分
        
        if((OrderCategEnum.IDX_OPTIONS_OPEN.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_OPEN.equals(l_orderUnit.getOrderCateg())))//注文単位.注文カテゴリ == ”新規建注文”の場合
        {
            l_params.setTaxationDiv(WEB3TaxationDivDef.IRRELEVENT);   //0：無関係
        }
        else if((OrderCategEnum.IDX_OPTIONS_CLOSE.equals(l_orderUnit.getOrderCateg())) ||  (OrderCategEnum.IDX_FUTURES_CLOSE.equals(l_orderUnit.getOrderCateg()))) //注文単位.注文カテゴリ == ”返済注文”の場合
        {
            l_params.setTaxationDiv(WEB3TaxationDivDef.SEPARATE_SELF_ACCESSMENT); //1：申告分離
        }

        l_params.setReceivedDateTime(l_orderUnitRow.getReceivedDateTime());                       //受注日時

        //注文数量= 注文単位.注文数量　@－　@注文単位.約定数量
        double l_dblQuantity = l_orderUnit.getQuantity();
        double l_dblExecutedQuantity = l_orderUnit.getExecutedQuantity();
        double l_dblLimitPrice = l_orderUnit.getLimitPrice();
        if (Double.isNaN(l_dblQuantity))
        {
            l_dblQuantity = 0D;
        }
        if (Double.isNaN(l_dblExecutedQuantity))
        {
            l_dblExecutedQuantity = 0D;
        }
        if (Double.isNaN(l_dblLimitPrice))
        {
            l_dblLimitPrice = 0D;
        }
        
        double l_dblExecQuantity = l_dblQuantity - l_dblExecutedQuantity;
        l_params.setQuantity(l_dblExecQuantity);                                                    //注文数量
        l_params.setLimitPrice(l_dblLimitPrice);                                    //指値
               
        l_params.setExecutionConditionType(l_orderUnitRow.getExecutionConditionType().intValue());   //執行条件
        l_params.setExecutedQuantity(0);                                                        //約定数量
        l_params.setDelivalyAmount(0);                                                          //受渡代金

        l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                                  //電子メール送信ステイタス
        l_params.setResendStatus(WEB3ResendStatusDef.DEFAULT);                                  //再送区分
        l_params.setReasonCode(l_strCloseReasonCode);                                           //失効理由コード
        l_params.setDeleteFlag(BooleanEnum.FALSE);                                              //削除フラグ

        l_params.setCreatedTimestamp(l_currentTime);                                            //指示日時
        l_params.setLastUpdatedTimestamp(l_currentTime);                                        //更新日時

        //insertMail()にてDBに挿入する。
        insertMail(l_params);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 先物OP約定メール送信テーブルに引数の内容で行を挿入する。
     * @@param l_ifoExecSendMail - (先物OP約定電子メール送信レコード)<BR>
     * <BR>
     * 先物OP約定メール送信行オブジェクト<BR>
     * @@roseuid 408483E4003A
     */
    protected void insertMail(IfoOrderExecSendMailParams l_ifoExecSendMail) throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = " insertMail(IfoOrderExecSendMailParams l_ifoExecSendMail)";
        log.entering(STR_METHOD_NAME);

        QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

        //行insert
        l_processor.doInsertQuery(l_ifoExecSendMail);    //throw DataFindException, DataQueryException, DataNetworkException

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 引数の注文単位に該当する約定メール（または、失効メール）を無効にする。<BR>
     *
     * 　@先物OP約定メール送信テーブルの以下の条件に当てはまる行に<BR>
     * 「取消区分==BooleanEnum.TRUE（無効）」を更新する。<BR>
     * <BR>
     * [条件]<BR>
     * 先物OP約定メール送信テーブル.証券会社コード = <BR>
     * 　@注文単位.部店ＩＤに該当する部店の証券会社コード<BR>
     * 先物OP約定メール送信テーブル.部店コード = <BR>
     * 　@注文単位.部店ＩＤに該当する部店の部店コード<BR>
     * 先物OP約定メール送信テーブル.口座コード = <BR>
     * 　@注文単位.口座ＩＤに該当する顧客の口座コード<BR>
     * 先物OP約定メール送信テーブル.識別コード = <BR>
     * 　@注文単位.識別コード<BR>
     * <BR>
     * 更新内容は、<BR>
     * 「共通_先物OP約定メール送信テーブル.xls」<BR>
     *  [（先物OPﾒｲﾙ送信）取消]シートを参照。<BR>
     * @@param l_orderUnit - 注文単位オブジェクト
     * @@roseuid 408C9C3F0028
     */
    public void undoSendMail(OrderUnit l_orderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " undoSendMail(OrderUnit l_orderUnit)";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

        try
        {
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            Branch l_branch = l_accountMgr.getBranch(l_orderUnit.getBranchId());    //throw NotFoundException
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(l_orderUnit.getAccountId());    //throw NotFoundException

            IfoOrderUnitRow l_orderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();

            QueryProcessor l_processor = Processors.getDefaultProcessor();  //throw DataFindException, DataNetworkException

            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append(" institution_code = ? ");         //証券会社コード
            l_sbWhere.append(" and branch_code = ? ");          //部店コード
            l_sbWhere.append(" and account_code = ? ");         //口座コード
            l_sbWhere.append(" and order_request_number = ? "); //識別コード

            Object[] l_objWhere = {
                l_branch.getInstitution().getInstitutionCode(), //証券会社コード
                l_branch.getBranchCode(),                       //部店コード
                l_mainAccount.getAccountCode(),                 //口座コード
                l_orderUnitRow.getOrderRequestNumber()          //識別コード
                };

            List l_lstRecords = l_processor.doFindAllQuery(
                IfoOrderExecSendMailRow.TYPE,
                l_sbWhere.toString(),
                l_objWhere
                );

            int l_intSize = l_lstRecords.size();
            
			//出来通知時にThreadLocalに設定されているシステム処理時間を取得する
			Timestamp l_realTimestamp = 
				(Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute
					(WEB3IfoAttributeNameDef.REAL_TIMESTAMP);
					
			if(l_realTimestamp == null)
			{
				l_realTimestamp = l_finApp.getTradingSystem().getSystemTimestamp();
			}

            WEB3OptionOrderManagerImpl l_optionOrderManagerImpl = (WEB3OptionOrderManagerImpl) l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            l_orderUnit = l_optionOrderManagerImpl.getOrderUnit(l_orderUnit.getOrderUnitId());
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_orderUnit.getDataSourceObject();
            OrderAction[] l_orderActions = l_orderUnit.getOrderActions();
            long l_lngOrderActionID = 0L; 
            for (int i = 0; i < l_orderActions.length; i++)
            {
                if (l_orderActions[i].getOrderActionSerialNo() == l_ifoOrderUnitRow.getLastOrderActionSerialNo()) {
                    l_lngOrderActionID = l_orderActions[i].getOrderActionId();
                    break;
                }
            }
            
            for (int i = 0; i < l_intSize; i++)
            {
                IfoOrderExecSendMailParams l_params = new IfoOrderExecSendMailParams((IfoOrderExecSendMailRow)l_lstRecords.get(i));
                l_processor = Processors.getDefaultProcessor();
                l_processor.doDeleteQuery(l_params.getPrimaryKey());
                
                l_params.setOrderActionId(l_lngOrderActionID);     //注文履歴ＩＤ
                l_params.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);                      //電子メール送信ステイタス
                l_params.setSendProcessDateTime(null);                                     //電子メール送信日時
                l_params.setDeleteFlag(BooleanEnum.TRUE);                                   //削除フラグ
	            l_params.setLastUpdatedTimestamp(l_realTimestamp);                            //更新日時
                
                l_processor.doInsertQuery(l_params);  //throw DataQueryException, DataNetworkException                
            }

        }
        catch (NotFoundException l_ex)
        {
            log.error("データ不整合エラー。", l_ex);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
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
