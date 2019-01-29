head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.40.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqMailSenderServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式メール送信サービスImpl(WEB3FeqMailSenderServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
                   2005/07/26 王亞洲(中訊) レビュー
Revesion History : 2008/01/23 柴双紅(中訊) モデルNo.372
*/
package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3EmailStatusDef;
import webbroker3.common.define.WEB3InputOutputActionSettlementDivDef;
import webbroker3.common.define.WEB3OrderConditionOperatorDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SendmailDivDef;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.define.WEB3FeqItemContentsDef;
import webbroker3.feq.define.WEB3FeqItemNameDef;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.ExtMailProcParams;
import webbroker3.gentrade.data.ExtMailProcTempParams;
import webbroker3.gentrade.data.ExtMailProcTempRow;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.gentrade.data.MailInfoRow;
import webbroker3.gentrade.data.MailProcParams;
import webbroker3.gentrade.data.MailProcTempParams;
import webbroker3.gentrade.data.MailProcTempRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式メール送信サービスImpl) <BR>
 * 外国株式メール送信サービス実装クラス
 * @@author 艾興
 * @@version 1.0 
 */
public class WEB3FeqMailSenderServiceImpl implements WEB3FeqMailSenderService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqMailSenderServiceImpl.class);
  
    /**
     * @@roseuid 42CE39F5007D
     */
    public WEB3FeqMailSenderServiceImpl() 
    {
     
    }
    
    /**
     * (create新規注文Mail) <BR>
     * 新規注文の内容を、メール送信テーブル、 <BR>
     * メール送信拡張テーブルに登録する。 <BR>
     *  <BR>
     * １）以下の条件で、メールテーブルからレコードを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1001" <BR>
     *  <BR>
     * ２）メール送信（テンポラリ）テーブル、 <BR>
     * メール送信拡張（テンポラリ）テーブルに登録する。 <BR>
     *  <BR>
     * ２－１）取引時間管理.is市場開局時間帯()の戻り値 == true の場合 <BR>
     *  <BR>
     *    this.createMail()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    メール情報： １）で取得したメールテーブル行の配列 <BR>
     *    注文単位： 引数.注文単位 <BR>
     *  <BR>
     * ２－２）取引時間管理.is市場開局時間帯()の戻り値 == false の場合 <BR>
     *  <BR>
     *    this.createTempMail()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    メール情報： １）で取得したメールテーブル行の配列 <BR>
     *    注文単位： 引数.注文単位 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 4295F452005D
     */
    public void createNewOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createNewOrderMail(FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);
        // １）以下の条件で、メールテーブルからレコードを取得する。 
        //  
        //    [条件] 
        //    証券会社コード = 引数.注文単位.証券会社コード 
        //    送信メール区分 = "1001" 
        //  
        List l_lisMailInfo = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = "institution_code = ? and sendmail_div = ?";
            Object[] l_objWhereValue = new Object[2];
            FeqOrderUnitRow l_feqOrderUnitRow = 
                (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
            l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
            l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
            
            l_lisMailInfo = 
                l_processor.doFindAllQuery(MailInfoRow.TYPE, l_strWhere, l_objWhereValue);
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        // ２）メール送信（テンポラリ）テーブル、 
        // メール送信拡張（テンポラリ）テーブルに登録する。 
        //  
        // ２－１）取引時間管理.is市場開局時間帯()の戻り値 == true の場合 
        //  
        //    this.createMail()をコールする。 
        //  
        //    [引数] 
        //    メール情報： １）で取得したメールテーブル行の配列 
        //    注文単位： 引数.注文単位 
        // 

        if ((l_lisMailInfo != null) && (l_lisMailInfo.size() != 0))
        {
            MailInfoRow[] l_mailInfoRows = new MailInfoRow[l_lisMailInfo.size()];
            l_lisMailInfo.toArray(l_mailInfoRows);
            if (WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                this.createMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            // ２－２）取引時間管理.is市場開局時間帯()の戻り値 == false の場合 
            //  
            //    this.createTempMail()をコールする。 
            //  
            //    [引数] 
            //    メール情報： １）で取得したメールテーブル行の配列 
            //    注文単位： 引数.注文単位 
            else
            {
                this.createTempMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            log.exiting(STR_METHOD_NAME);
        }
        else
        {
            log.error("テーブルに該当するデータがありません");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    
    /**
     * (create訂正注文Mail) <BR>
     * 訂正注文の内容を、メール送信テーブル、 <BR>
     * メール送信拡張テーブルに登録する。 <BR>
     *  <BR>
     * １）取引時間管理.is市場開局時間帯()の戻り値 == false の場合 <BR>
     *  <BR>
     *    原注文のメール送信テーブル、 <BR>
     *    メール送信拡張テーブルのデータを訂正注文 <BR>
     *    にあわせて更新する。 <BR>
     *  <BR>
     *    this.updateTempMail()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    注文単位： 引数.注文単位 <BR>
     *  <BR>
     * ２）取引時間管理.is市場開局時間帯()の戻り値 == true の場合 <BR>
     *  <BR>
     * ２－１）以下の条件で、メールテーブルからレコードを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1002" <BR>
     *  <BR>
     * ２－２）メール送信テーブル、メール送信拡張テーブルに登録する。 <BR>
     *  <BR>
     *    this.createMail()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    メール情報： １）で取得したメールテーブル行の配列 <BR>
     *    注文単位： 引数.注文単位 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 429995B700B5
     */
    public void createChangeOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createChangeOrderMail(FeqOrderUnit l_feqOrderUnit)";
        log.entering(STR_METHOD_NAME);

        try
        {
            // １）取引時間管理.is市場開局時間帯()の戻り値 == false の場合 
            //  
            //    原注文のメール送信テーブル、 
            //    メール送信拡張テーブルのデータを訂正注文 
            //    にあわせて更新する。 
            //  
            //    this.updateTempMail()をコールする。 
            //  
            //    [引数] 
            //    注文単位： 引数.注文単位 
            //  
            if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                this.updateTempMail((WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            // ２）取引時間管理.is市場開局時間帯()の戻り値 == true の場合 
            //  
            // ２－１）以下の条件で、メールテーブルからレコードを取得する。 
            //  
            //    [条件] 
            //    証券会社コード = 引数.注文単位.証券会社コード 
            //    送信メール区分 = "1002" 
            //  
            // ２－２）メール送信テーブル、メール送信拡張テーブルに登録する。 
            //  
            //    this.createMail()をコールする。 
            //  
            //    [引数] 
            //    メール情報： １）で取得したメールテーブル行の配列 
            //    注文単位： 引数.注文単位 
            else
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                String l_strWhere = "institution_code = ? and sendmail_div = ?";
                Object[] l_objWhereValue = new Object[2];
                FeqOrderUnitRow l_feqOrderUnitRow = 
                    (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
                l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
                l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_CHANGE;
                
                List l_lisMailInfo = 
                    l_processor.doFindAllQuery(MailInfoRow.TYPE, l_strWhere, l_objWhereValue);
                if (l_lisMailInfo != null && !l_lisMailInfo.isEmpty())
                {
                    MailInfoRow[] l_mailInfoRows = new MailInfoRow[l_lisMailInfo.size()];
                    l_lisMailInfo.toArray(l_mailInfoRows);
                    this.createMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
                }
                else
                {
                    log.error("テーブルに該当するデータがありません");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            log.exiting(STR_METHOD_NAME);

        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
    }
    
    /**
     * (create取消注文Mail) <BR>
     * 取消注文の内容を、メール送信テーブル、 <BR>
     * メール送信拡張テーブルに登録する。 <BR>
     *  <BR>
     * １）取引時間管理.is市場開局時間帯()の戻り値 == false の場合 <BR>
     *  <BR>
     *    原注文のメール送信テンポラリテーブル、 <BR>
     *    メール送信拡張テンポラリテーブルのデータを論理削除する。 <BR>
     *  <BR>
     *    this.deleteTempMail()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    注文単位： 引数.注文単位 <BR>
     *  <BR>
     * ２）取引時間管理.is市場開局時間帯()の戻り値 == true の場合 <BR>
     *  <BR>
     * ２－１）以下の条件で、メールテーブルからレコードを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1003" <BR>
     *  <BR>
     * ２－２）メール送信テーブル、メール送信拡張テーブルに登録する。 <BR>
     *  <BR>
     *    this.createMail()をコールする。 <BR>
     *  <BR>
     *    [引数] <BR>
     *    メール情報： １）で取得したメールテーブル行の配列 <BR>
     *    注文単位： 引数.注文単位 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 429995B700C5
     */
    public void createCancelOrderMail(FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createCancelOrderMail(FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        // １）取引時間管理.is市場開局時間帯()の戻り値 == false の場合 
        //  
        //    原注文のメール送信テンポラリテーブル、 
        //    メール送信拡張テンポラリテーブルのデータを論理削除する。 
        //  
        //    this.deleteTempMail()をコールする。 
        //  
        //    [引数] 
        //    注文単位： 引数.注文単位 
        //  
        try
        {
            if (!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
            {
                this.deleteTempMail((WEB3FeqOrderUnit)l_feqOrderUnit);
            }
            else
            {
                // ２）取引時間管理.is市場開局時間帯()の戻り値 == true の場合 
                //  
                // ２－１）以下の条件で、メールテーブルからレコードを取得する。 
                //  
                //    [条件] 
                //    証券会社コード = 引数.注文単位.証券会社コード 
                //    送信メール区分 = "1003" 
                //  
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                String l_strWhere = "institution_code = ? and sendmail_div = ?";
                Object[] l_objWhereValue = new Object[2];
                FeqOrderUnitRow l_feqOrderUnitRow = 
                    (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
                l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
                l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_CANCEL;
                
                List l_lisMailInfo = 
                    l_processor.doFindAllQuery(MailInfoRow.TYPE, l_strWhere, l_objWhereValue);
                
                // ２－２）メール送信テーブル、メール送信拡張テーブルに登録する。 
                //  
                //    this.createMail()をコールする。 
                //  
                //    [引数] 
                //    メール情報： １）で取得したメールテーブル行の配列 
                //    注文単位： 引数.注文単位 
                if (l_lisMailInfo != null && !l_lisMailInfo.isEmpty())
                {
                    MailInfoRow[] l_mailInfoRows = new MailInfoRow[l_lisMailInfo.size()];
                    l_lisMailInfo.toArray(l_mailInfoRows);
                    this.createMail(l_mailInfoRows, (WEB3FeqOrderUnit)l_feqOrderUnit);
                }   
                else
                {
                    log.error("テーブルに該当するデータがありません");
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createMail) <BR>
     * メール送信テーブル、メール送信拡張テーブルに登録する。 <BR>
     *  <BR>
     * 引数.メール情報行の要素毎にLoop処理を行う。 <BR>
     *  <BR>
     * １）メール送信テーブルにレコードを登録する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     *    DB更新仕様「外株注文_メール送信テーブル.xls」参照 <BR>
     *  <BR>
     * ２）メール送信拡張テーブルにレコードを登録する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     *    DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 <BR>
     * @@param l_mailInfo - (メール情報) <BR>
     * メールテーブルの行オブジェクトの配列
     * 
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 429D9FAE006C
     */
    protected void createMail(MailInfoRow[] l_mailInfo, 
            WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createMail";
        log.entering(STR_METHOD_NAME);
        // 引数.メール情報行の要素毎にLoop処理を行う。 
        if (l_mailInfo == null || l_feqOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        long l_lngBranchId = l_feqOrderUnit.getBranchId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3FeqProduct l_product = null;
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
            
        try
        {
            l_product = 
                (WEB3FeqProduct)l_tradingModule.getProductManager().getProduct(l_feqOrderUnitRow.getProductId());
            l_branch = l_accountManager.getBranch(l_lngBranchId);
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqOrderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }

        Market l_market = null;        
        if (!l_feqOrderUnitRow.getMarketIdIsNull())
        {
            try
            {
                l_market = l_finObjectManager.getMarket(l_feqOrderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);

            }
        }

		//注文履歴データを取得
		FeqOrderActionRow l_orderActionRow = null;
		l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);

        final String STR_BLANK = " ";
        // // ２）メール送信拡張テーブルにレコードを登録する。START** 
        List l_strItemNames = new ArrayList();
        List l_strItemValues = new ArrayList();
        //1 運用コード    注文単位.運用コード
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EMP_CODE);
        if (l_feqOrderUnitRow.getOrderEmpCode() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getOrderEmpCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //2 注文No    注文単位.注文ID
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_NO);
        l_strItemValues.add("" + l_feqOrderUnitRow.getOrderId());
        //3 伝票No    注文単位.伝票No
        l_strItemNames.add(WEB3FeqItemNameDef.VOUCHER_NO);
        if (l_feqOrderUnitRow.getVoucherNo() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getVoucherNo());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //4 銘柄コード    注文単位.銘柄IDに該当する銘柄コード
        l_strItemNames.add(WEB3FeqItemNameDef.PRODUCT_CODE);
        l_strItemValues.add(l_product.getProductCode());
        //5 発注銘柄コード    注文単位.銘柄IDに該当する現地銘柄コード
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_PRODUCT_CODE);
        l_strItemValues.add(l_product.getOffshoreProductCode());
        //6 銘柄名    注文単位.銘柄IDに該当する銘柄名
        l_strItemNames.add(WEB3FeqItemNameDef.STANDARD_NAME);
        l_strItemValues.add(l_product.getDisplayProductName());
        //7 売買    "注文単位.注文種別 == ”外株買い”の場合、”買付”
        //注文単位.注文種別 == ”外株売り”の場合、”売付”"
        l_strItemNames.add(WEB3FeqItemNameDef.TRADE);
        if (OrderTypeEnum.FEQ_BUY.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BUY);
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SELL);
        }
        //8 決済方法@    "注文単位.決済区分 == ”円貨” の場合、”円貨”
        //注文単位.決済区分 == ”外貨” の場合、”外貨”"
        l_strItemNames.add(WEB3FeqItemNameDef.SETTLE_DIV);
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.JAPANESE_CURRENCY);
        }
        else if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.FOREIGN_CURRENCY);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //9 口座    "注文単位.税区分 == ”一般” の場合、”一般”
        //注文単位.税区分 == ”特定” の場合、”特定”"
        l_strItemNames.add(WEB3FeqItemNameDef.ACCOUNT);
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NORMAL);
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SPECIAL);
        }
        //10 市場コード    注文単位.市場IDに該当する市場コード
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET_CODE);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //11 市場    注文単位.市場IDに該当する市場名
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketName());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //12 執行条件    "注文単位.執行条件 == ”条件なし” の場合、”無条件”
        //注文単位.執行条件 == ”寄り” の場合、”寄付”
        //注文単位.執行条件 == ”引け” の場合、”引け”
        //注文単位.執行条件 == ”不出来引け成行” の場合、”出来ずば引成(不成)”"
        l_strItemNames.add(WEB3FeqItemNameDef.EXECUTION_CONDITION_TYPE);
        if (FeqExecutionConditionType.NONE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NONE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_OPEN.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_OPEN);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //13 注文期限区分    "注文単位.初回注文の注文単位ＩＤ == null の場合、”当日限り”
        //注文単位.初回注文の注文単位ＩＤ != null の場合、”出来るまで注文”"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EXPIRATION_DATE_TYPE);
        if(l_feqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.DAY_LIMIT);
        }
        else
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.CARRIED_ORDER);
        }
        //14 発注条件    "注文単位.発注条件 == ”逆指値” の場合、”逆指値”
        //注文単位.発注条件 == ”W指値” の場合、”W指値”"
        //注文単位.発注条件 == ”DEFAULT（条件指定なし）”の場合、”DEFAULT（条件指定なし）”
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_CONDITION_TYPE);
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.STOP_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.W_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.DEFAULT.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ORDER_CONDITION_DEFAULT);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //15 発注条件演算子    "注文単位.発注条件演算子 == ”以上” の場合、”以上”
        //注文単位.発注条件演算子 == ”以下” の場合、”以下”"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_OPERATOR);
        if (WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ABOVE_BASE_PRICE);
        }
        else if (WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BELOW_BASE_PRICE);
        }
        else 
        {
            l_strItemValues.add(STR_BLANK);
        }
        //16 発注条件単価    注文単位.逆指値基準値
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_PRICE);
        DecimalFormat formatSOPrice = new DecimalFormat("###0.######");
        String SOPrice = formatSOPrice.format(l_feqOrderUnitRow.getStopOrderPrice());
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(SOPrice);
        }

        //17 訂正指値    注文単位.（W指値）訂正指値
        l_strItemNames.add(WEB3FeqItemNameDef.LIMIT_PRICE);
        DecimalFormat formatWLPrice = new DecimalFormat("###0.######");
        String WLPrice = formatWLPrice.format(l_feqOrderUnitRow.getWLimitPrice());
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(WLPrice);
        }

		//18 発注日    注文単位.年月日１
		l_strItemNames.add(WEB3FeqItemNameDef.BIZ_DATE);
		//注文単位.発注日を”YYYY年MM月DD日”の形式で設定
		l_strItemValues.add(WEB3DateUtility.formatDate(WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(),"yyyyMMdd"),"yyyy年MM月dd日"));

		//19 注文日時    注文単位.年月日２
		l_strItemNames.add(WEB3FeqItemNameDef.RECEIVED_DATE_TIME);
		//注文履歴.作成日時を”YYYY年MM月DD日 HH24:MI”の形式で設定
		l_strItemValues.add(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy年MM月dd日 HH:mm"));
		
		//20 注文株数    注文単位.数量
		l_strItemNames.add(WEB3FeqItemNameDef.ORDER_QUANTITY);
		//注文単位.注文数量をカンマ編集して設定
		DecimalFormat formatQuantity = new DecimalFormat("#,##0");
		l_strItemValues.add(formatQuantity.format(l_feqOrderUnitRow.getQuantity()));

		//21 注文単価    注文単位.金額
		l_strItemNames.add(WEB3FeqItemNameDef.PRICE);
		//注文単位.指値をカンマ編集して設定　@注文単位.指値=０の場合は”成行”を設定
		DecimalFormat formatPrice = new DecimalFormat("###0.######");
		String limitPrice = formatPrice.format(l_feqOrderUnitRow.getLimitPrice());
		if(limitPrice.equals(WEB3OrderPriceDivDef.MARKET_PRICE))
		{
			l_strItemValues.add("成行");
		}
		else
		{
			l_strItemValues.add(limitPrice);
		}

        //２）メール送信拡張テーブルにレコードを登録する。END** 
        try
        {    
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            for (int i = 0; i < l_mailInfo.length; i++)
            {   

                // １）メール送信テーブルにレコードを登録する。 
                //  
                //    ※更新内容については、 
                //    DB更新仕様「外株注文_メール送信テーブル.xls」参照 
                //  
                MailProcParams l_mailProcParams = new MailProcParams();
                MailInfoRow l_mailInfoRow = l_mailInfo[i];
                //証券会社コードinstitution_code注文単位.証券会社コード    
                l_mailProcParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                //部店コードbranch_code注文単位.部店IDに該当する部店コード
                l_mailProcParams.setBranchCode(l_branch.getBranchCode());
                //送信メール区分sendmail_divメールテーブル.送信メール区分
                l_mailProcParams.setSendmailDiv(l_mailInfoRow.getSendmailDiv());
                //識別ID    discernment_id    メールテーブル.識別ID
                l_mailProcParams.setDiscernmentId(l_mailInfoRow.getDiscernmentId());
                //口座コードaccount_code    注文単位.口座IDに該当する口座コード（6桁）
                l_mailProcParams.setAccountCode(l_mainAccount.getDisplayAccountCode());
                //メールIDmail_id    注文履歴.注文履歴ID        
				l_mailProcParams.setMailId(l_orderActionRow.getOrderActionId());
                //年月日１date_1    null
                l_mailProcParams.setDate1(null);
                //年月日２date_2 null
                l_mailProcParams.setDate2(null);
                //年月日３date_3    注文単位.注文失効日        
                l_mailProcParams.setDate3(l_feqOrderUnitRow.getExpirationDate());
                //年月日４date_4        null                                        
                l_mailProcParams.setDate4(null);
                //数量quantity    null    
                l_mailProcParams.setQuantity(null);
                //金額amount    null                                            
                l_mailProcParams.setAmount(null);
                //IDorder_id  注文単位.注文単位ID                                                
                l_mailProcParams.setOrderId(l_feqOrderUnitRow.getOrderUnitId());
                //区分division        null            
                l_mailProcParams.setDivision(null);
                //名称1    name_1        注文単位.口座IDに該当する顧客名
                l_mailProcParams.setName1(((WEB3GentradeMainAccount)l_mainAccount).getDisplayAccountName());
                //名称2    name_2        注文単位.通貨コードに該当する通貨略称
                WEB3GentradeCurrency l_genCurrency = l_feqOrderUnit.getCurrency();
                GenCurrencyRow l_currencyRow = (GenCurrencyRow)l_genCurrency.getDataSourceObject();
                l_mailProcParams.setName2(l_currencyRow.getCurrencyShortName());
                //電子メール送信ステイタスstatus            0：未処理（Email未送信）    
                l_mailProcParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
                //電子メール送信日時send_process_date_time        null        
                l_mailProcParams.setSendProcessDateTime(null);
                //電子メール送信エラーコードerror_code        null    
                l_mailProcParams.setErrorCode(null);
                //管理者確認用メール区分    null
                l_mailProcParams.setAdminMailDiv(null);
                //再送区分    null
                l_mailProcParams.setResendStatus(null);
                //電子メール再送日時resend_process_date_time    null
                l_mailProcParams.setResendProcessDateTime(null);
                //emailアドレス    email_address        
                l_mailProcParams.setEmailAddress(l_mailInfoRow.getSendAddress());
                //送信emailアドレスsend_email_address    メールテーブル.差出人
                l_mailProcParams.setSendEmailAddress(l_mailInfoRow.getMailSender());
                //件名subject
				//○新規注文受付メールの場合
				//	運用コード＋半角スペース２つ＋“外国株式取引”＋半角スペース１つ＋“注文伝票”
				//○訂正注文受付メールの場合
				//	運用コード＋半角スペース２つ＋“外国株式取引”＋半角スペース１つ＋“注文訂正伝票”
				//○取消注文受付メールの場合
				//	運用コード＋半角スペース２つ＋“外国株式取引”＋半角スペース１つ＋“注文取消伝票”

				String sendMailDiv = null;
				String subject = null;
				//新規注文受付メールの場合
				if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_ACCEPT))
				{
					sendMailDiv = "注文伝票";
				}
				//訂正注文受付メールの場合
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CHANGE))
				{
					sendMailDiv = "注文訂正伝票";
				}
				//取消注文受付メールの場合
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CANCEL))
				{
					sendMailDiv = "注文取消伝票";
				}
				
				subject = l_feqOrderUnitRow.getOrderEmpCode() + "  外国株式取引 " + sendMailDiv;
				
                l_mailProcParams.setSubject(subject);
                //メール本文mail_text    null
                l_mailProcParams.setMailText(null);
                //削除フラグdelete_flag                            0:FALSE（有効）
                l_mailProcParams.setDeleteFlag(BooleanEnum.FALSE);
                //作成日時created_timestamp                        処理日時    

                l_mailProcParams.setCreatedTimestamp(l_timeStamp);
                //更新日時last_updated_timestamp                    処理日時
                l_mailProcParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doInsertQuery(l_mailProcParams);
                // ２）メール送信拡張テーブルにレコードを登録する。 
                //  
                //    ※更新内容については、 
                //    DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 
                

                for (int j = 0; j < l_strItemNames.size(); j++)
                {
                    ExtMailProcParams l_extMailProcParams = new ExtMailProcParams();
                    //証券会社コードinstitution_codeメール送信テーブル.証券会社コード    
                    l_extMailProcParams.setInstitutionCode(l_mailProcParams.getInstitutionCode());
                    //部店コードbranch_code        メール送信テーブル.部店コード                            
                    l_extMailProcParams.setBranchCode(l_mailProcParams.getBranchCode());
                    //送信メール区分sendmail_div                        メール送信テーブル.送信メール区分        
                    l_extMailProcParams.setSendmailDiv(l_mailProcParams.getSendmailDiv());
                    //識別IDdiscernment_id                    メール送信テーブル.識別ID            
                    l_extMailProcParams.setDiscernmentId(l_mailProcParams.getDiscernmentId());
                    //口座コードaccount_code                    メール送信テーブル.口座コード                
                    l_extMailProcParams.setAccountCode(l_mailProcParams.getAccountCode());
                    //メールIDmail_id                    メール送信テーブル.メールID                    
                    l_extMailProcParams.setMailId(l_mailProcParams.getMailId());
                    //項目名item_name                    シート「登録内容[新規]」参照    
                    l_extMailProcParams.setItemName((String)l_strItemNames.get(j));
                    //項目内容item_contents                        シート「登録内容[新規]」参照        
                    l_extMailProcParams.setItemContents((String)l_strItemValues.get(j));
                    //削除フラグdelete_flag                0:FALSE（有効）    
                    l_extMailProcParams.setDeleteFlag(BooleanEnum.FALSE);
                    //作成日時created_timestamp                                処理日時
                    l_extMailProcParams.setCreatedTimestamp(l_timeStamp);
                    //更新日時last_updated_timestamp                            処理日時
                    l_extMailProcParams.setLastUpdatedTimestamp(l_timeStamp);
                    l_processor.doInsertQuery(l_extMailProcParams);
                }

            }
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);

        }

    }
    
    /**
     * (createTempMail) <BR>
     * メール送信テンポラリテーブル、 <BR>
     * メール送信拡張テンポラリテーブルに登録する。 <BR>
     *  <BR>
     * 引数.メール情報行の要素毎にLoop処理を行う。 <BR>
     *  <BR>
     * １）メール送信テンポラリテーブルにレコードを登録する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     * 　@DB更新仕様「外株注文_メール送信テーブル.xls」参照 <BR>
     *  <BR>
     * ２）メール送信拡張テンポラリテーブルにレコードを登録する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     * 　@DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 <BR>
     * @@param l_mailInfo - (メール情報) <BR>
     * メールテーブルの行オブジェクトの配列
     * 
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 42A0518F0026
     */
    protected void createTempMail(MailInfoRow[] l_mailInfo, WEB3FeqOrderUnit l_feqOrderUnit) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "createTempMail(MailInfoRow[] l_mailInfo, WEB3FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        // 引数.メール情報行の要素毎にLoop処理を行う。 
        
        if (l_mailInfo == null)
        {
            return;
        }        
        FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
        long l_lngBranchId = l_feqOrderUnit.getBranchId();
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();
        Branch l_branch = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3FeqProduct l_product = null;
        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
        WEB3GentradeFinObjectManager l_finObjectManager
            = (WEB3GentradeFinObjectManager) l_finApp.getFinObjectManager();
        try
        {
            l_product = 
                (WEB3FeqProduct)l_tradingModule.getProductManager().getProduct(l_feqOrderUnitRow.getProductId());
            l_branch = l_accountManager.getBranch(l_lngBranchId);
            l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_feqOrderUnit.getAccountId());
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        Market l_market = null;
                
        if (!l_feqOrderUnitRow.getMarketIdIsNull())
        {
            try
            {
                l_market = l_finObjectManager.getMarket(l_feqOrderUnitRow.getMarketId());
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(), l_ex);
            }
        }

		//注文履歴データを取得
		FeqOrderActionRow l_orderActionRow = null;
		l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);
		
        final String STR_BLANK = " ";
        // // ２）メール送信拡張テーブルにレコードを登録する。START** 
        List l_strItemNames = new ArrayList();
        List l_strItemValues = new ArrayList();
        //1 運用コード    注文単位.運用コード
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EMP_CODE);
        if (l_feqOrderUnitRow.getOrderEmpCode() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getOrderEmpCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //2 注文No    注文単位.注文ID
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_NO);
        l_strItemValues.add("" + l_feqOrderUnitRow.getOrderId());
        //3 伝票No    注文単位.伝票No
        l_strItemNames.add(WEB3FeqItemNameDef.VOUCHER_NO);
        if (l_feqOrderUnitRow.getVoucherNo() != null)
        {
            l_strItemValues.add(l_feqOrderUnitRow.getVoucherNo());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //4 銘柄コード    注文単位.銘柄IDに該当する銘柄コード
        l_strItemNames.add(WEB3FeqItemNameDef.PRODUCT_CODE);
        l_strItemValues.add(l_product.getProductCode());
        //5 発注銘柄コード    注文単位.銘柄IDに該当する現地銘柄コード
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_PRODUCT_CODE);
        l_strItemValues.add(l_product.getOffshoreProductCode());
        //6 銘柄名    注文単位.銘柄IDに該当する銘柄名
        l_strItemNames.add(WEB3FeqItemNameDef.STANDARD_NAME);
        l_strItemValues.add(l_product.getDisplayProductName());
        //7 売買    "注文単位.注文種別 == ”外株買い”の場合、”買付”
        //注文単位.注文種別 == ”外株売り”の場合、”売付”"
        l_strItemNames.add(WEB3FeqItemNameDef.TRADE);
        if (OrderTypeEnum.FEQ_BUY.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BUY);
        }
        else if (OrderTypeEnum.FEQ_SELL.equals(l_feqOrderUnitRow.getOrderType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SELL);
        }
        //8 決済方法@    "注文単位.決済区分 == ”円貨” の場合、”円貨”
        //注文単位.決済区分 == ”外貨” の場合、”外貨”"
        l_strItemNames.add(WEB3FeqItemNameDef.SETTLE_DIV);
        if (WEB3InputOutputActionSettlementDivDef.EN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.JAPANESE_CURRENCY);
        }
        else if (WEB3InputOutputActionSettlementDivDef.FOREIGN_SETTLE.equals(l_feqOrderUnitRow.getSettleDiv()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.FOREIGN_CURRENCY);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //9 口座    "注文単位.税区分 == ”一般” の場合、”一般”
        //注文単位.税区分 == ”特定” の場合、”特定”"
        l_strItemNames.add(WEB3FeqItemNameDef.ACCOUNT);
        if (TaxTypeEnum.NORMAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NORMAL);
        }
        else if (TaxTypeEnum.SPECIAL.equals(l_feqOrderUnitRow.getTaxType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.SPECIAL);
        }
        //10 市場コード    注文単位.市場IDに該当する市場コード
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET_CODE);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketCode());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //11 市場    注文単位.市場IDに該当する市場名
        l_strItemNames.add(WEB3FeqItemNameDef.MARKET);
        if (l_market != null)
        {
            l_strItemValues.add(l_market.getMarketName());
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //12 執行条件    "注文単位.執行条件 == ”条件なし” の場合、”無条件”
        //注文単位.執行条件 == ”寄り” の場合、”寄付”
        //注文単位.執行条件 == ”引け” の場合、”引け”
        //注文単位.執行条件 == ”不出来引け成行” の場合、”出来ずば引成(不成)”"
        l_strItemNames.add(WEB3FeqItemNameDef.EXECUTION_CONDITION_TYPE);
        if (FeqExecutionConditionType.NONE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.NONE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_OPEN.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_OPEN);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE);
        }
        else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.equals(l_feqOrderUnitRow.getExecutionConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.AT_MARKET_CLOSE_NOT_EXECUTED);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //13 注文期限区分    "注文単位.初回注文の注文単位ＩＤ == null の場合、”当日限り”
        //注文単位.初回注文の注文単位ＩＤ != null の場合、”出来るまで注文”"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_EXPIRATION_DATE_TYPE);
        if(l_feqOrderUnitRow.getFirstOrderUnitIdIsNull())
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.DAY_LIMIT);
        }
        else
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.CARRIED_ORDER);
        }
        //14 発注条件    "注文単位.発注条件 == ”逆指値” の場合、”逆指値”
        //注文単位.発注条件 == ”W指値” の場合、”W指値”"
        //注文単位.発注条件 == ”DEFAULT（条件指定なし）”の場合、”DEFAULT（条件指定なし）”
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_CONDITION_TYPE);
        if (WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.STOP_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.W_LIMIT_PRICE);
        }
        else if (WEB3OrderingConditionDef.DEFAULT.equals(l_feqOrderUnitRow.getOrderConditionType()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ORDER_CONDITION_DEFAULT);
        }
        else
        {
            l_strItemValues.add(STR_BLANK);
        }
        //15 発注条件演算子    "注文単位.発注条件演算子 == ”以上” の場合、”以上”
        //注文単位.発注条件演算子 == ”以下” の場合、”以下”"
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_OPERATOR);
        if (WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.ABOVE_BASE_PRICE);
        }
        else if (WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.equals(l_feqOrderUnitRow.getOrderCondOperator()))
        {
            l_strItemValues.add(WEB3FeqItemContentsDef.BELOW_BASE_PRICE);
        }
        else 
        {
            l_strItemValues.add(STR_BLANK);
        }
        //16 発注条件単価    注文単位.逆指値基準値
        l_strItemNames.add(WEB3FeqItemNameDef.ORDER_COND_PRICE);
        DecimalFormat formatSOPrice = new DecimalFormat("###0.######");
        String SOPrice = formatSOPrice.format(l_feqOrderUnitRow.getStopOrderPrice());
        if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(SOPrice);
        }

        //17 訂正指値    注文単位.（W指値）訂正指値
        l_strItemNames.add(WEB3FeqItemNameDef.LIMIT_PRICE);
        DecimalFormat formatWLPrice = new DecimalFormat("###0.######");
        String WLPrice = formatWLPrice.format(l_feqOrderUnitRow.getWLimitPrice());
        if (l_feqOrderUnitRow.getWLimitPriceIsNull())
        {
            l_strItemValues.add(STR_BLANK);
        }
        else
        {
            l_strItemValues.add(WLPrice);
        }

		//18 発注日    注文単位.年月日１
		l_strItemNames.add(WEB3FeqItemNameDef.BIZ_DATE);
		//注文単位.発注日を”YYYY年MM月DD日”の形式で設定
		l_strItemValues.add(WEB3DateUtility.formatDate(WEB3DateUtility.getDate(l_feqOrderUnitRow.getBizDate(),"yyyyMMdd"),"yyyy年MM月dd日"));

		//19 注文日時    注文単位.年月日２
		l_strItemNames.add(WEB3FeqItemNameDef.RECEIVED_DATE_TIME);
		//注文履歴.作成日時を”YYYY年MM月DD日 HH24:MI”の形式で設定
		l_strItemValues.add(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy年MM月dd日 HH:mm"));
		
		//20 注文株数    注文単位.数量
		l_strItemNames.add(WEB3FeqItemNameDef.ORDER_QUANTITY);
		//注文単位.注文数量をカンマ編集して設定
		DecimalFormat formatQuantity = new DecimalFormat("#,##0");
		l_strItemValues.add(formatQuantity.format(l_feqOrderUnitRow.getQuantity()));

		//21 注文単価    注文単位.金額
		l_strItemNames.add(WEB3FeqItemNameDef.PRICE);
		//注文単位.指値をカンマ編集して設定　@注文単位.指値=０の場合は”成行”を設定
		DecimalFormat formatPrice = new DecimalFormat("###0.######");

		String limitPrice = formatPrice.format(l_feqOrderUnitRow.getLimitPrice());
		if(limitPrice.equals(WEB3OrderPriceDivDef.MARKET_PRICE))
		{
			l_strItemValues.add("成行");
		}
		else
		{
			l_strItemValues.add(limitPrice);
		}

        //２）メール送信拡張テーブルにレコードを登録する。END** 
        QueryProcessor l_processor = null;

        try
        {
            l_processor = Processors.getDefaultProcessor();
            Timestamp l_timeStamp =
                GtlUtils.getTradingSystem().getSystemTimestamp();
            for (int i = 0; i < l_mailInfo.length; i++)
            {  

                // １）メール送信テーブルにレコードを登録する。 
                //  
                //    ※更新内容については、 
                //    DB更新仕様「外株注文_メール送信テーブル.xls」参照 
                //  
                MailProcTempParams l_mailProcTempParams = new MailProcTempParams();
                MailInfoRow l_mailInfoRow = l_mailInfo[i];
                //証券会社コードinstitution_code注文単位.証券会社コード    
                l_mailProcTempParams.setInstitutionCode(l_feqOrderUnit.getInstitutionCode());
                //部店コードbranch_code注文単位.部店IDに該当する部店コード
                l_mailProcTempParams.setBranchCode(l_branch.getBranchCode());
                //送信メール区分sendmail_divメールテーブル.送信メール区分
                l_mailProcTempParams.setSendmailDiv(l_mailInfoRow.getSendmailDiv());
                //識別ID    discernment_id    メールテーブル.識別ID
                l_mailProcTempParams.setDiscernmentId(l_mailInfoRow.getDiscernmentId());
                //口座コードaccount_code    注文単位.口座IDに該当する口座コード（6桁）
                l_mailProcTempParams.setAccountCode(l_mainAccount.getDisplayAccountCode());
				//メールIDmail_id    注文履歴.注文履歴ID        
				l_mailProcTempParams.setMailId(l_orderActionRow.getOrderActionId());
                //年月日１date_1    null
                l_mailProcTempParams.setDate1(null);
                //年月日２date_2    null
                l_mailProcTempParams.setDate2(null);
                //年月日３date_3    注文単位.注文失効日        
                l_mailProcTempParams.setDate3(l_feqOrderUnitRow.getExpirationDate());
                //年月日４date_4        null                                        
                l_mailProcTempParams.setDate4(null);
                //数量quantity    null    
                l_mailProcTempParams.setQuantity(null);
                //金額amount    null                                            
                l_mailProcTempParams.setAmount(null);
                //IDorder_id  注文単位.注文単位ID                                                
                l_mailProcTempParams.setOrderId(l_feqOrderUnitRow.getOrderUnitId());
                //区分division        null            
                l_mailProcTempParams.setDivision(null);
                //名称1    name_1        注文単位.口座IDに該当する顧客名
                l_mailProcTempParams.setName1(((WEB3GentradeMainAccount)l_mainAccount).getDisplayAccountName());
                //名称2    name_2        注文単位.通貨コードに該当する通貨略称
                WEB3GentradeCurrency l_genCurrency = l_feqOrderUnit.getCurrency();
                GenCurrencyRow l_currencyRow = (GenCurrencyRow)l_genCurrency.getDataSourceObject();
                l_mailProcTempParams.setName2(l_currencyRow.getCurrencyShortName());
                //電子メール送信ステイタスstatus            0：未処理（Email未送信）    
                l_mailProcTempParams.setStatus(WEB3EmailStatusDef.EMAIL_NOT_SEND);
                //電子メール送信日時send_process_date_time        null        
                l_mailProcTempParams.setSendProcessDateTime(null);
                //電子メール送信エラーコードerror_code        null    
                l_mailProcTempParams.setErrorCode(null);
                //管理者確認用メール区分    null
                l_mailProcTempParams.setAdminMailDiv(null);
                //再送区分    null
                l_mailProcTempParams.setResendStatus(null);
                //電子メール再送日時resend_process_date_time    null
                l_mailProcTempParams.setResendProcessDateTime(null);
                //送信emailアドレスsend_email_address  
                l_mailProcTempParams.setSendEmailAddress(l_mailInfoRow.getMailSender());
                //emailアドレス    email_address
                l_mailProcTempParams.setEmailAddress(l_mailInfoRow.getSendAddress());

				//件名subject
				//○新規注文受付メールの場合
				//	運用コード＋半角スペース２つ＋“外国株式取引”＋半角スペース１つ＋“注文伝票”
				//○訂正注文受付メールの場合
				//	運用コード＋半角スペース２つ＋“外国株式取引”＋半角スペース１つ＋“注文訂正伝票”
				//○取消注文受付メールの場合
				//	運用コード＋半角スペース２つ＋“外国株式取引”＋半角スペース１つ＋“注文取消伝票”

				String sendMailDiv = null;
				String subject = null;
				//新規注文受付メールの場合
				if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_ACCEPT))
				{
					sendMailDiv = "注文伝票";
				}
				//訂正注文受付メールの場合
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CHANGE))
				{
					sendMailDiv = "注文訂正伝票";
				}
				//取消注文受付メールの場合
				else if(l_mailInfoRow.getSendmailDiv().equals(WEB3SendmailDivDef.FEQ_ORDER_CANCEL))
				{
					sendMailDiv = "注文取消伝票";
				}
				
				subject = l_feqOrderUnitRow.getOrderEmpCode() + "  外国株式取引 " + sendMailDiv;
				
				l_mailProcTempParams.setSubject(subject);

                //メール本文mail_text    null
                l_mailProcTempParams.setMailText(null);
                //削除フラグdelete_flag                            0:FALSE（有効）
                l_mailProcTempParams.setDeleteFlag(BooleanEnum.FALSE.intValue());
                //作成日時created_timestamp                        処理日時    

                l_mailProcTempParams.setCreatedTimestamp(l_timeStamp);
                //更新日時last_updated_timestamp                    処理日時
                l_mailProcTempParams.setLastUpdatedTimestamp(l_timeStamp);
                l_processor.doInsertQuery(l_mailProcTempParams);
                // ２）メール送信拡張テーブルにレコードを登録する。 
                //  
                //    ※更新内容については、 
                //    DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 
                for (int j = 0; j < l_strItemNames.size(); j++)
                {
                    ExtMailProcTempParams l_extMailProcTempParams = new ExtMailProcTempParams();
                    //証券会社コードinstitution_codeメール送信テーブル.証券会社コード    
                    l_extMailProcTempParams.setInstitutionCode(l_mailProcTempParams.getInstitutionCode());
                    //部店コードbranch_code        メール送信テーブル.部店コード                            
                    l_extMailProcTempParams.setBranchCode(l_mailProcTempParams.getBranchCode());
                    //送信メール区分sendmail_div                        メール送信テーブル.送信メール区分        
                    l_extMailProcTempParams.setSendmailDiv(l_mailProcTempParams.getSendmailDiv());
                    //識別IDdiscernment_id                    メール送信テーブル.識別ID            
                    l_extMailProcTempParams.setDiscernmentId(l_mailProcTempParams.getDiscernmentId());
                    //口座コードaccount_code                    メール送信テーブル.口座コード                
                    l_extMailProcTempParams.setAccountCode(l_mailProcTempParams.getAccountCode());
                    //メールIDmail_id                    メール送信テーブル.メールID                    
                    l_extMailProcTempParams.setMailId(l_mailProcTempParams.getMailId());
                    //項目名item_name                    シート「登録内容[新規]」参照    
                    l_extMailProcTempParams.setItemName((String)l_strItemNames.get(j));
                    //項目内容item_contents                        シート「登録内容[新規]」参照        
                    l_extMailProcTempParams.setItemContents((String)l_strItemValues.get(j));
                    //削除フラグdelete_flag                0:FALSE（有効）    
                    l_extMailProcTempParams.setDeleteFlag(BooleanEnum.FALSE.intValue());
                    //作成日時created_timestamp                                処理日時
                    l_extMailProcTempParams.setCreatedTimestamp(l_timeStamp);
                    //更新日時last_updated_timestamp                            処理日時
                    l_extMailProcTempParams.setLastUpdatedTimestamp(l_timeStamp);
                    l_processor.doInsertQuery(l_extMailProcTempParams);
                }
            }
            
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (updateTempMail) <BR>
     * メール送信テンポラリテーブル、 <BR>
     * メール送信拡張テンポラリテーブルを更新する。 <BR>
     *  <BR>
     * １）メール送信テンポラリテーブルの更新 <BR>
     *  <BR>
     * １－１）以下の条件でメール送信テンポラリテーブルからデータを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1001" <BR>
     *    メールID = 引数.注文履歴.注文履歴ID <BR>
     *  <BR>
     * １－２）メール送信テンポラリテーブルのレコードを更新する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     *    DB更新仕様「外株注文_メール送信テーブル.xls」参照 <BR>
     *    ※複数件取得できた場合は、すべてについて更新を行う。 <BR>
     *  <BR>
     * ２）メール送信拡張テンポラリテーブルの更新 <BR>
     *  <BR>
     * ２－１）以下の条件でメール送信拡張テンポラリテーブルから <BR>
     *    データを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1001" <BR>
     *    メールID = 引数.注文履歴.注文履歴ID <BR>
     *  <BR>
     * ２－２）メール送信拡張テンポラリテーブルのレコードを更新する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     * 　@DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 <BR>
     *    ※すべてのレコードについて更新を行う。 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 42A0564C0362
     */
    protected void updateTempMail(WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "updateTempMail(WEB3FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        // １）メール送信テンポラリテーブルの更新 
        //  
        // １－１）以下の条件でメール送信テンポラリテーブルからデータを取得する。 
        //  
        //    [条件] 
        //    証券会社コード = 引数.注文単位.証券会社コード 
        //    送信メール区分 = "1001" 
        //    メールID = 引数.注文履歴.注文履歴ID 
        // 
        Timestamp l_timeStamp =
            GtlUtils.getTradingSystem().getSystemTimestamp();
        List l_lisMailProcTemp = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();

			//注文履歴データを取得
			FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
			FeqOrderActionRow l_orderActionRow = null;
			l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);
			Long l_prevMailId = null;

            String l_strWhere = "institution_code = ? and sendmail_div = ? and order_id = ?";
            Object[] l_objWhereValue = new Object[3];
            l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
            l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValue[2] = new Long(l_feqOrderUnitRow.getOrderUnitId());

            l_lisMailProcTemp = 
                l_processor.doFindAllQuery(MailProcTempRow.TYPE, l_strWhere, l_objWhereValue);
            if ((l_lisMailProcTemp != null) && (l_lisMailProcTemp.size() != 0))
            {
                // １－２）メール送信テンポラリテーブルのレコードを更新する。 
                //  
                //    ※更新内容については、 
                //    DB更新仕様「外株注文_メール送信テーブル.xls」参照 
                //    ※複数件取得できた場合は、すべてについて更新を行う。 
                //  
                for (int i = 0; i < l_lisMailProcTemp.size(); i++ )
                {
                    MailProcTempRow l_mailProcTempRow = 
                        (MailProcTempRow)l_lisMailProcTemp.get(i);
                    MailProcTempParams l_mailProcTempParams = new MailProcTempParams(l_mailProcTempRow);
					l_prevMailId =  new Long(l_mailProcTempParams.getMailId());
					l_mailProcTempParams.setMailId(l_orderActionRow.getOrderActionId());
                    l_mailProcTempParams.setDate3(l_feqOrderUnitRow.getExpirationDate());
                    l_mailProcTempParams.setLastUpdatedTimestamp(l_timeStamp);
					l_processor.doDeleteAllQuery(MailProcTempRow.TYPE,l_strWhere,l_objWhereValue);
                    l_processor.doInsertQuery(l_mailProcTempParams);
                }
            }
            // ２）メール送信拡張テンポラリテーブルの更新 
            //  
            // ２－１）以下の条件でメール送信拡張テンポラリテーブルから 
            //    データを取得する。 
            //  
            //    [条件] 
            //    証券会社コード = 引数.注文単位.証券会社コード 
            //    送信メール区分 = "1001" 
            //    メールID = 引数.注文履歴.注文履歴ID 
            //  
            // ２－２）メール送信拡張テンポラリテーブルのレコードを更新する。 
            //  
            //    ※更新内容については、 
            // 　@DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 
            //    ※すべてのレコードについて更新を行う。 

			String l_strWhereExt = "institution_code = ? and sendmail_div = ? and mail_id = ?";
			Object[] l_objWhereValueExt = new Object[3];
			l_objWhereValueExt[0] = l_feqOrderUnitRow.getInstitutionCode();
			l_objWhereValueExt[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValueExt[2] = l_prevMailId;
		

            List l_lisExtMailProcTemp = 
                l_processor.doFindAllQuery(ExtMailProcTempRow.TYPE, l_strWhereExt, l_objWhereValueExt);
            if ((l_lisExtMailProcTemp == null) || (l_lisExtMailProcTemp.size() == 0))
            {
                return;
            }
            
            final String STR_BLANK = " ";
            for (int i = 0; i < l_lisExtMailProcTemp.size(); i++)
            {
                ExtMailProcTempRow l_row = (ExtMailProcTempRow)l_lisExtMailProcTemp.get(i);
                ExtMailProcTempParams l_params = new ExtMailProcTempParams(l_row);
                if (WEB3FeqItemNameDef.EXECUTION_CONDITION_TYPE.equals(l_params.getItemName()))
                {
                    //注文単位.執行条件 == ”条件なし” の場合、”無条件”
    
                    if (FeqExecutionConditionType.NONE.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.NONE);
                    }
                    //注文単位.執行条件 == ”寄り” の場合、”寄付”
    
                    else if (FeqExecutionConditionType.AT_MARKET_OPEN.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.AT_MARKET_OPEN);
                    }
                    //注文単位.執行条件 == ”引け” の場合、”引け”
    
                    else if (FeqExecutionConditionType.AT_MARKET_CLOSE.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.AT_MARKET_CLOSE);
                    }
                    //注文単位.執行条件 == ”不出来引け成行” の場合、”出来ずば引成(不成)”
                    else if (FeqExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED.
                            equals(l_feqOrderUnitRow.getExecutionConditionType()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.AT_MARKET_CLOSE_NOT_EXECUTED);
                    }
                    //nullの場合は、ブランク(半角スペース)をセットする。
                    else
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                }
                else if (WEB3FeqItemNameDef.ORDER_COND_OPERATOR.equals(l_params.getItemName()))
                {
                    //注文単位.発注条件演算子 == ”以上” の場合、”以上”
                    if (WEB3OrderConditionOperatorDef.ABOVE_BASE_PRICE.
                            equals(l_feqOrderUnitRow.getOrderCondOperator()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.ABOVE_BASE_PRICE);
                    }
                    //注文単位.発注条件演算子 == ”以下” の場合、”以下”
                    else if (WEB3OrderConditionOperatorDef.BELOW_BASE_PRICE.
                            equals(l_feqOrderUnitRow.getOrderCondOperator()))
                    {
                        l_params.setItemContents(WEB3FeqItemContentsDef.BELOW_BASE_PRICE);
                    }
                    //nullの場合は、ブランク(半角スペース)をセットする。
                    else
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                }
                else if (WEB3FeqItemNameDef.ORDER_COND_PRICE.equals(l_params.getItemName()))
                { 
                    DecimalFormat formatSOPrice = new DecimalFormat("###0.######");
                    String SOPrice = formatSOPrice.format(l_feqOrderUnitRow.getStopOrderPrice());
                    if (l_feqOrderUnitRow.getStopOrderPriceIsNull())
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                    else
                    {
                        l_params.setItemContents(SOPrice);
                    }

                }
                else if (WEB3FeqItemNameDef.LIMIT_PRICE.equals(l_params.getItemName()))
                {
                    DecimalFormat formatWLPrice = new DecimalFormat("###0.######");
                    String WLPrice = formatWLPrice.format(l_feqOrderUnitRow.getWLimitPrice());
                    if (l_feqOrderUnitRow.getWLimitPriceIsNull())
                    {
                        l_params.setItemContents(STR_BLANK);
                    }
                    else
                    {
                        l_params.setItemContents(WLPrice);
                    }
                }
                
                //注文株数
				else if (WEB3FeqItemNameDef.ORDER_QUANTITY.equals(l_params.getItemName()))
				{
					DecimalFormat formatQuantity = new DecimalFormat("#,##0");
					l_params.setItemContents("" + formatQuantity.format(l_feqOrderUnitRow.getQuantity()));
				}
				
				//注文単価
				else if (WEB3FeqItemNameDef.PRICE.equals(l_params.getItemName()))
				{
					//注文単位.指値をカンマ編集して設定　@注文単位.指値=０の場合は”成行”を設定
					DecimalFormat formatPrice = new DecimalFormat("###0.######");
					String limitPrice = formatPrice.format(l_feqOrderUnitRow.getLimitPrice());
					if(limitPrice.equals(WEB3OrderPriceDivDef.MARKET_PRICE))
					{
						l_params.setItemContents("成行");
					}
                    else
					{
                        l_params.setItemContents(limitPrice);
					}
				}
				//注文作成日付
				else if (WEB3FeqItemNameDef.RECEIVED_DATE_TIME.equals(l_params.getItemName()))
				{
					 l_params.setItemContents(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy年MM月dd日 HH:mm"));
				}
				l_params.setMailId(l_orderActionRow.getOrderActionId());
                l_params.setLastUpdatedTimestamp(l_timeStamp);
				l_processor.doDeleteAllQuery(ExtMailProcTempRow.TYPE,l_strWhereExt,l_objWhereValueExt);
                l_processor.doInsertQuery(l_params);                   
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (deleteTempMail) <BR>
     * メール送信テンポラリテーブル、 <BR>
     * メール送信拡張テンポラリテーブルを削除する。 <BR>
     *  <BR>
     * １）メール送信テンポラリテーブルの削除 <BR>
     *  <BR>
     * １－１）以下の条件でメール送信テンポラリテーブルからデータを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1001" <BR>
     *    メールID = 引数.注文履歴.注文履歴ID <BR>
     *  <BR>
     * １－２）メール送信テンポラリテーブルのレコードを論理削除する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     * 　@DB更新仕様「外株注文_メール送信テーブル.xls」参照 <BR>
     *    ※複数件取得できた場合は、すべてについて論理削除を行う。 <BR>
     *  <BR>
     * ２）メール送信拡張テンポラリテーブルの削除 <BR>
     *  <BR>
     * ２－１）以下の条件でメール送信拡張テンポラリテーブルから <BR>
     *    データを取得する。 <BR>
     *  <BR>
     *    [条件] <BR>
     *    証券会社コード = 引数.注文単位.証券会社コード <BR>
     *    送信メール区分 = "1001" <BR>
     *    メールID = 引数.注文履歴.注文履歴ID <BR>
     *  <BR>
     * ２－２）メール送信拡張テンポラリテーブルのレコードを論理削除する。 <BR>
     *  <BR>
     *    ※更新内容については、 <BR>
     *    DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 <BR>
     *    ※すべてのレコードについて論理削除を行う。 <BR>
     * @@param l_feqOrderUnit - (注文単位)
     * @@throws WEB3BaseException
     * @@roseuid 42A058A80093
     */
    protected void deleteTempMail(WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "deleteTempMail(WEB3FeqOrderUnit l_feqOrderUnit) ";
        log.entering(STR_METHOD_NAME);
        Timestamp l_timeStamp =
            GtlUtils.getTradingSystem().getSystemTimestamp();
        // １）メール送信テンポラリテーブルの削除 
        //  
        // １－１）以下の条件でメール送信テンポラリテーブルからデータを取得する。 
        //  
        //    [条件] 
        //    証券会社コード = 引数.注文単位.証券会社コード 
        //    送信メール区分 = "1001" 
        //    メールID = 引数.注文履歴.注文履歴ID 
        //  
        List l_lisMailProcTemp = null;
        try
        {
            QueryProcessor l_processor = Processors.getDefaultProcessor();

			//注文履歴データを取得
			FeqOrderActionRow l_orderActionRow = null;
			l_orderActionRow = getFeqOrderAction(l_feqOrderUnit);
			FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();
			Long l_prevMailId = null;

			String l_strWhere = "institution_code = ? and sendmail_div = ? and order_id = ?";
			Object[] l_objWhereValue = new Object[3];
			l_objWhereValue[0] = l_feqOrderUnitRow.getInstitutionCode();
			l_objWhereValue[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValue[2] = new Long(l_feqOrderUnitRow.getOrderUnitId());

            l_lisMailProcTemp = 
                l_processor.doFindAllQuery(MailProcTempRow.TYPE, l_strWhere, l_objWhereValue);
            // １－２）メール送信テンポラリテーブルのレコードを論理削除する。 
            //  
            //    ※更新内容については、 
            // 　@DB更新仕様「外株注文_メール送信テーブル.xls」参照 
            //    ※複数件取得できた場合は、すべてについて論理削除を行う。 
            if ((l_lisMailProcTemp != null) && (l_lisMailProcTemp.size() != 0))
            {
                for (int i = 0; i < l_lisMailProcTemp.size(); i++)
                {
                    MailProcTempRow l_row = (MailProcTempRow)l_lisMailProcTemp.get(i);
                    MailProcTempParams l_params = new MailProcTempParams(l_row);
					l_prevMailId =  new Long(l_params.getMailId());
					l_params.setMailId(l_orderActionRow.getOrderActionId());
                    l_params.setDeleteFlag(BooleanEnum.TRUE.intValue());
                    l_params.setLastUpdatedTimestamp(l_timeStamp);
					l_processor.doDeleteAllQuery(MailProcTempRow.TYPE,l_strWhere,l_objWhereValue);
					l_processor.doInsertQuery(l_params);
                }
            }
            //  
            // ２）メール送信拡張テンポラリテーブルの削除 
            //  
            // ２－１）以下の条件でメール送信拡張テンポラリテーブルから 
            //    データを取得する。 
            //  
            //    [条件] 
            //    証券会社コード = 引数.注文単位.証券会社コード 
            //    送信メール区分 = "1001" 
            //    メールID = 引数.注文履歴.注文履歴ID 
            //  
            // ２－２）メール送信拡張テンポラリテーブルのレコードを論理削除する。 
            //  
            //    ※更新内容については、 
            //    DB更新仕様「外株注文_メール送信拡張テーブル.xls」参照 
            //    ※すべてのレコードについて論理削除を行う。 

			String l_strWhereExt = "institution_code = ? and sendmail_div = ? and mail_id = ?";
			Object[] l_objWhereValueExt = new Object[3];
			l_objWhereValueExt[0] = l_feqOrderUnitRow.getInstitutionCode();
			l_objWhereValueExt[1] = WEB3SendmailDivDef.FEQ_ORDER_ACCEPT;
			l_objWhereValueExt[2] = l_prevMailId;

            List l_lisExtMailProcTemp = 
                l_processor.doFindAllQuery(ExtMailProcTempRow.TYPE, l_strWhereExt, l_objWhereValueExt);
            // １－２）メール送信テンポラリテーブルのレコードを論理削除する。 
            //  
            //    ※更新内容については、 
            // 　@DB更新仕様「外株注文_メール送信テーブル.xls」参照 
            //    ※複数件取得できた場合は、すべてについて論理削除を行う。 
            if ((l_lisExtMailProcTemp != null) && (l_lisExtMailProcTemp.size() != 0))
            {
                for (int i = 0; i < l_lisExtMailProcTemp.size(); i++)
                {
                    ExtMailProcTempRow l_row = (ExtMailProcTempRow)l_lisExtMailProcTemp.get(i);
                    ExtMailProcTempParams l_params = new ExtMailProcTempParams(l_row);
                    l_params.setDeleteFlag(BooleanEnum.TRUE.intValue());
					//注文作成日付
					if (WEB3FeqItemNameDef.RECEIVED_DATE_TIME.equals(l_params.getItemName()))
					{
						 l_params.setItemContents(WEB3DateUtility.formatDate(l_orderActionRow.getCreatedTimestamp(),"yyyy年MM月dd日 HH:mm"));
					}

					l_params.setMailId(l_orderActionRow.getOrderActionId());
                    l_params.setLastUpdatedTimestamp(l_timeStamp);
					l_processor.doDeleteAllQuery(ExtMailProcTempRow.TYPE,l_strWhereExt,l_objWhereValueExt);
					l_processor.doInsertQuery(l_params);                   

                }
            }

        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        catch(DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(), l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
	/**
	 * (getFeqOrderAction) <BR>
	 * 注文履歴データを取得する。 <BR>
	 *  <BR>
	 * １）以下の条件で注文履歴テーブルから <BR>
	 *    データを取得する。 <BR>
	 *  <BR>
	 *    [条件] <BR>
	 *    注文ID = 引数.注文単位.注文ID <BR>
	 *    注文履歴番号 = 引数.注文単位.注文履歴最終通番 <BR>
	 *  <BR>
	 * @@param l_feqOrderUnit - (注文単位)
     * @@return com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionRow
	 * @@throws WEB3BaseException
	 * @@roseuid 42A058A80093
	 */
	protected FeqOrderActionRow getFeqOrderAction(WEB3FeqOrderUnit l_feqOrderUnit) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = "getFeqOrderAction";
		log.entering(STR_METHOD_NAME);
		FeqOrderUnitRow l_feqOrderUnitRow = (FeqOrderUnitRow)l_feqOrderUnit.getDataSourceObject();

		//注文履歴データを取得
		List l_lisFeqOrderActionRows = null;
		try
		{
			QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
			long l_lngOrderId = l_feqOrderUnitRow.getOrderId();
			int l_intLastOrderActionSerialNo = l_feqOrderUnitRow.getLastOrderActionSerialNo();
            
			String l_strWhereAction = " order_id = ? and order_action_serial_no = ? ";

			Object[] l_objAction = {new Long(l_lngOrderId),
				new Integer(l_intLastOrderActionSerialNo)};
                
			l_lisFeqOrderActionRows = l_queryProcessor.doFindAllQuery(
				FeqOrderActionRow.TYPE, 
				l_strWhereAction,
				l_objAction);//DataNetworkException, DataQueryException


			if (l_lisFeqOrderActionRows == null || l_lisFeqOrderActionRows.isEmpty())
			{
				log.debug("該当する注文単位ID、注文履歴最終通番データがありません。");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80005,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
			if (l_lisFeqOrderActionRows.size() > 1)
			{
				log.debug("該当する注文単位ID、注文履歴最終通番データは不整合ある。");
				log.exiting(STR_METHOD_NAME);
				throw new WEB3SystemLayerException(
					WEB3ErrorCatalog.SYSTEM_ERROR_80006,
					this.getClass().getName() + "." + STR_METHOD_NAME);
			}
		}
		catch (DataQueryException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(), l_ex);
		}
		catch(DataNetworkException l_ex)
		{
			log.error(l_ex.getMessage(), l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(), l_ex);
		}

		FeqOrderActionRow l_orderActionRow = (FeqOrderActionRow) l_lisFeqOrderActionRows.get(0);
		log.exiting(STR_METHOD_NAME);
		
		return l_orderActionRow;
	}
}
@
