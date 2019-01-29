head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引注文入力通知データアダプタ(WEB3MarginOrderNotifyDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/09 艾興 (中訊) 新規作成
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityProductManager;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文入力通知データアダプタ）。<BR>
 * <BR>
 * 信用取引注文入力通知データアダプタクラス
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyDataAdapter
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginOrderNotifyDataAdapter.class);

    /**
     * (株式注文入力通知キューParams)<BR>
     * 【株式注文入力通知キューテーブル】の１レコード。
     */
    private HostEqtypeOrderReceiptParams eqtypeOrderReceiptParams;

    /**
     * @@roseuid 4142B32E00A1
     */
    private WEB3MarginOrderNotifyDataAdapter()
    {

    }

    /**
     * (create)<BR>
     * 信用取引注文入力通知データアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * ２）　@生成したインスタンスに引数のキューデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     *  本メソッドによってインスタンス化するように制限する）<BR>
     * @@param l_eqtypeOrderReceiptParams - (株式注文入力通知キューParams)<BR>
     * 【株式注文入力通知キューテーブル】データオブジェクト<BR>
     * @@return WEB3MarginOrderNotifyDataAdapter<BR>
     * @@roseuid 40EA67B70278<BR>
     */
    public static WEB3MarginOrderNotifyDataAdapter create(HostEqtypeOrderReceiptParams l_eqtypeOrderReceiptParams)
    {
        final String STR_METHOD_NAME =
            "create(HostEqtypeOrderReceiptParams l_eqtypeOrderReceiptParams) ";
        log.entering(STR_METHOD_NAME);
        WEB3MarginOrderNotifyDataAdapter l_adapter =
            new WEB3MarginOrderNotifyDataAdapter();
        l_adapter.eqtypeOrderReceiptParams = l_eqtypeOrderReceiptParams;
        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }

    /**
     * (get市場コード)<BR>
     * 【株式注文入力通知キューテーブル】市場コード（SONAR）より、<BR>
     * WebⅢの市場コードを返す。 <BR>
     * <BR>
     * １）　@拡張金融オブジェクトマネージャ.get市場BySONAR( )により、<BR>
     * 市場オブジェクトを取得する。<BR>
     * <BR>
     * ----------------------------------------------------------<BR>
     * ＜get市場BySONAR( )：引数設定仕様＞<BR>
     * <BR>
     * 証券会社コード：　@株式注文入力通知キューParams.証券会社コード<BR>
     * 市場コード（SONAR）：　@株式注文入力通知キューParams.市場コード（SONAR）<BR>
     * ----------------------------------------------------------<BR>
     * <BR>
     * ２）　@取得した市場オブジェクト.市場コード を返す。 <BR>
     * <BR>
     * @@return String<BR>
     * @@roseuid 40EA67B7027A
     */
    public String getMarketCode() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarketCode()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        Market l_market = l_gentradeFinObjectManager.getMarketBySONAR(
            this.eqtypeOrderReceiptParams.getInstitutionCode(),
            this.eqtypeOrderReceiptParams.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_market.getMarketCode();
    }

    /**
     * (get執行条件)<BR>
     * 【株式注文入力通知キューテーブル】執行条件(SONAR)に応じた<BR>
     * EqTypeExecutionConditionTypeを返す。<BR>
     * 
     * 拡張株式注文マネージャ.get執行条件(株式注文通知キューParams.執行条件(SONAR))に<BR>
     * delegateする。<BR>
     * <BR>
     * @@return EqTypeExecutionConditionType<BR>
     * @@roseuid 40EA67B70268
     */
    public EqTypeExecutionConditionType getExecutionCondition() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getExecutionCondition() ";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3EquityOrderManager l_orderManager =
            (WEB3EquityOrderManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getOrderManager();
                
        log.exiting(STR_METHOD_NAME);
        return l_orderManager.getExecutionConditionType(eqtypeOrderReceiptParams.getExecutionCondition());
    }

    /**
     * (get税区分)<BR>
     * 【株式注文入力通知キューテーブル】税区分（特定口座区分）<BR>
     * （以下「税区分」）より、<BR>
     * AP層で使用する税区分（TaxTypeEnum）を返却する。<BR>
     * <BR>
     * １） 一般口座のセット<BR>
     * 　@税区分＝”一般”の場合、TaxTypeEnum.”一般”を返却する。<BR>
     * <BR>
     * ２）　@特定口座のセット<BR>
     * 税区分＝”特定”の場合、顧客オブジェクト(*1).get受渡日信用取引税区分( )(*2)により<BR>
     * 税区分を取得する。<BR>
     * 取得した信用取引税区分がTaxTypeEnum.”特定”または、<BR>
     * TaxTypeEnum.”特定口座かつ源泉徴収”であれば、TaxTypeEnum.”特定”を返却する。 <BR>
     * 以外は例外をスローする。<BR> 
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_01703<BR>
     * <BR>
     * (*1) 顧客オブジェクトは、拡張アカウントマネージャ.getMainAccount(証券会社ID, 部店コード,<BR>
     * 口座コード)により取得する。<BR>
     * 証券会社IDは【株式注文入力通知キューテーブル】証券会社コードに該当する<BR>
     * 証券会社.証券会社IDを、<BR>
     * 部店コード、口座コードは【株式注文入力通知キューテーブル】の同項目を、<BR>
     * それぞれセットする。<BR>
     * <BR>
     * (*2) get受渡日信用取引税区分( )：引数の受渡日の取得方法@ <BR>
     * 拡張プロダクトマネージャ.get取引銘柄(証券会社, this.株式注文入力通知キューParams.銘柄コード,<BR>
     * this.get市場コード()).getDailyDeliveryDate( )をセット。<BR>
     * 証券会社オブジェクトは、拡張アカウントマネージャ.getInstitution(<BR>
     * this.株式注文入力通知キューParams.証券会社コード)で取得。<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@roseuid 40EA67B70277
     */
    public TaxTypeEnum getTaxType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxType() ";
        log.entering(STR_METHOD_NAME);
        TaxTypeEnum l_taxTypeEnum = null;
        // １） 一般口座のセット
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(
                Integer.toString(eqtypeOrderReceiptParams.getTaxType())))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;
        }
        else
        {
            try
            {
                FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

                AccountManager l_accountManager = l_finApp.getAccountManager();                
                    
                Institution l_institution =
                    l_accountManager.getInstitution(
                        eqtypeOrderReceiptParams.getInstitutionCode());
                // (*1)顧客オブジェクト取得
                WEB3GentradeMainAccount l_mainAccount =
                    (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                        l_institution.getInstitutionId(),
                        eqtypeOrderReceiptParams.getBranchCode(),
                        eqtypeOrderReceiptParams.getAccountCode());

                // (*2)get受渡日信用取引税区分( )：引数の受渡日の取得方法@
                WEB3EquityProductManager l_productManager = 
                    (WEB3EquityProductManager)l_finApp.getTradingModule(ProductTypeEnum.EQUITY).getProductManager();
                // 引数の受渡日の取得
                Date l_deliveryDate = l_productManager.getTradedProduct(
                    l_institution,
                    this.eqtypeOrderReceiptParams.product_code,
                    this.getMarketCode()).getDailyDeliveryDate();
                
                //信用取引税区分の取得
                l_taxTypeEnum = l_mainAccount.getDeliveryDateMarginTaxType(l_deliveryDate);
                
                // ２）　@特定口座のセット
                if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum) ||
                    TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
                {
                    l_taxTypeEnum = TaxTypeEnum.SPECIAL;
                }
                else
                {
                    log.debug(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_01703,
                        STR_METHOD_NAME);
                }
            }
            catch (NotFoundException l_nfe)
            {
                log.debug(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    STR_METHOD_NAME);
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }

    /**
     * (get弁済区分)<BR>
     * 【株式注文入力通知キューテーブル】弁済区分より、<BR>
     *  AP層で使用する弁済区分を返却する。<BR>
     * <BR>
     * [弁済区分　@＝　@”1：制度信用<BR>
     *  （店頭以外または店頭の弁済期間が6ヶ月）”の場合]<BR>
     * 　@"1"(制度信用)を返却する。<BR>
     * <BR>
     * [弁済区分　@>=　@”3：一般信用（全市場）”の場合]<BR>
     * 　@"2"(一般信用)を返却する。<BR>
     * <BR>
     * [弁済区分が上記以外の場合]<BR>
     * 　@例外をthrowする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00641<BR>
     * <BR>
     * @@return String<BR>
     * @@roseuid 40EA8C4F0023
     */
    public String getRepaymentType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentType() ";
        log.entering(STR_METHOD_NAME);
        String l_strPayType = eqtypeOrderReceiptParams.getSonarRepaymentType();
        String l_strRepaymentType = null;
        final int l_iSonnarRepayDivMgnSys = 1;
        final int l_iSonnarRepayDivMgnSysAllMarket = 3;
        int l_iSonnarRepayDiv;
        
        try
        {
            l_iSonnarRepayDiv = Integer.parseInt(l_strPayType);
        }
        catch (NumberFormatException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);
        }
       
        if (l_iSonnarRepayDiv == l_iSonnarRepayDivMgnSys)
        {
            l_strRepaymentType = WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS;
        }
        else if (l_iSonnarRepayDiv >= l_iSonnarRepayDivMgnSysAllMarket)
        {
            l_strRepaymentType = WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_GEN;
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00641,
                STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_strRepaymentType;
    }

    /**
     * (get弁済期限値)<BR>
     * 【株式注文入力通知キューテーブル】弁済区分より、<BR>
     *  AP層で使用する弁済期限を返却する。<BR>
     * <BR>
     * １）　@（部店市場弁済別）取扱条件オブジェクト(*1)を取得する。<BR>
     * <BR>
     * ２）　@取得した取扱条件オブジェクトの数分だけ以下の処理をループする。<BR>
     * <BR>
     * 　@２－１）以下の条件に該当する取扱条件オブジェクトを取得する。<BR>
     * 　@　@[条件]<BR>
     * 　@　@取得した取扱条件オブジェクト.証券会社コード　@＝　@this.株式注文入力通<BR>
     * 知キューParams.証券会社コード<BR>
     * 　@　@取得した取扱条件オブジェクト.部店コード　@　@　@＝　@this.株式注文入力通<BR>
     * 知キューParams.get部店コード<BR>
     * 　@　@取得した取扱条件オブジェクト.市場コード　@　@　@＝　@this.get市場コード()<BR>
     * 　@　@取得した取扱条件オブジェクト.弁済区分（SONAR）＝　@this.株式注文入<BR>
     * 力通知キューParams.弁済区分<BR>
     * <BR>
     * ３）　@２－１）にて取得したレコード件数 >= <BR>
     *       2（＝該当データが複数件あり）の場合は、<BR>
     * 　@　@　@例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00661<BR>
     * 　@　@　@以外、取得した取扱条件オブジェクト.弁済期限値を返却する。<BR>
     * <BR>
     * (*1)<BR>
     * （部店市場弁済別）取扱条件オブジェクト.get（部店市場弁済別）<BR>
     *  取扱条件( )により取得<BR>
     * <BR>
     * [get（部店市場弁済別）取扱条件( )に指定する引数]<BR>
     * 部店：　@拡張アカウントマネージャ.get部店<BR>
     *  (this.証券会社コード, this.部店コード)<BR>
     * <BR>
     * @@return double<BR>
     * @@roseuid 40EAAA260060
     */
    public double getRepaymentNum() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentNum()";
        log.entering(STR_METHOD_NAME);
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager)l_finApp.getAccountManager();
        try
        {
            WEB3GentradeInstitution l_institution =
                (WEB3GentradeInstitution)l_accountManager.getInstitution(
                    eqtypeOrderReceiptParams.institution_code);

            WEB3GentradeBranch l_branch =
                (WEB3GentradeBranch)l_accountManager.getBranch(
                    l_institution,
                    eqtypeOrderReceiptParams.branch_code);
            WEB3GentradeBranchMarketRepayDealtCond[] l_conditions =
                WEB3GentradeBranchMarketRepayDealtCond
                    .getBranchMarketRepayDealtCond(
                    l_branch);
            //* ２）　@取得した取扱条件オブジェクトの数分だけ以下の処理をループする。<BR>
            //    * <BR>
            //    * 　@２－１）以下の条件に該当する取扱条件オブジェクトを取得する。<BR>
            //    * 　@　@[条件]<BR>
            //    * 　@　@取得した取扱条件オブジェクト.証券会社コード　@＝　@this.株式注文入力通<BR>
            //    * 知キューParams.証券会社コード<BR>
            //    * 　@　@取得した取扱条件オブジェクト.部店コード　@　@　@＝　@this.株式注文入力通<BR>
            //    * 知キューParams.get部店コード<BR>
            //    * 　@　@取得した取扱条件オブジェクト.市場コード　@　@　@＝　@this.get市場コード()<BR>
            //    * 　@　@取得した取扱条件オブジェクト.弁済区分（SONAR）＝　@this.株式注文入<BR>
            //    * 力通知キューParams.弁済区分<BR>
            int l_intIndex = -1;
            int l_intCount = 0;
            for (int i = 0; i < l_conditions.length; i++)
            {
                BranchMarketRepayDealtCondRow l_conditionRow =
                    (BranchMarketRepayDealtCondRow)l_conditions[i]
                        .getDataSourceObject();
                if (l_conditionRow
                    .getInstitutionCode()
                    .equals(eqtypeOrderReceiptParams.institution_code)
                    && l_conditionRow.getBranchCode().equals(
                        eqtypeOrderReceiptParams.branch_code)
                    && l_conditionRow.getMarketCode().equals(this.getMarketCode())
                    && l_conditionRow.getSonarRepaymentType().equals(
                        eqtypeOrderReceiptParams.sonar_repayment_type))
                {
                    l_intIndex = i;
                    l_intCount++;
                }
            }
            if (l_intCount == 0 || l_intCount >= 2)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                    STR_METHOD_NAME);
            }
            BranchMarketRepayDealtCondRow l_conditionRow =
                (BranchMarketRepayDealtCondRow)l_conditions[l_intIndex]
                    .getDataSourceObject();
            return l_conditionRow.getRepaymentNum();
        }
        catch (NotFoundException l_nex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_nex.getMessage(),
                l_nex);
        }
        catch (WEB3SystemLayerException l_slex)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME,
                l_slex.getMessage(),
                l_slex);
        }
    }

    /**
     * (is買建)<BR>
     * 【株式注文入力通知キューテーブル】売買区分より、<BR>
     *  AP層で使用するis買建を返却する。<BR>
     * <BR>
     * 売買区分＝"2：買建"　@and　@<BR>
     * （取引コード(SONAR)＝"51：信用建" or "52：信用埋"）<BR>
     * の場合はtrueを、<BR>
     * 売買区分＝"1：売建"　@and　@<BR>
     * （取引コード(SONAR)＝"51：信用建" or "52：信用埋"）<BR>
     * の場合はfalseを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * 上記以外の場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00651<BR>
     * @@return boolean<BR>
     * @@roseuid 40EA67B70269
     */
    public boolean isLong() 
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        boolean l_isLong = true;
        if (WEB3TradeTypeDef
            .OPEN_LONG_MARGIN
            .equals(eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isLong = true;
            }
        }
        else if (
            WEB3TradeTypeDef.OPEN_SHORT_MARGIN.equals(
                eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isLong = false;
            }
        }
        else
        {
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00651,
                STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_isLong;
    }

    /**
     * (is売建)<BR>
     * 【株式注文入力通知キューテーブル】売買区分より、<BR>
     *  AP層で使用するis売建を返却する。<BR>
     * 売買区分＝"2：買建"　@and　@<BR>
     * （取引コード(SONAR)＝"51：信用建" or "52：信用埋"）<BR>
     * の場合はfalseを、<BR>
     * 売買区分＝"1：売建"　@and　@<BR>
     * （取引コード(SONAR)＝"51：信用建" or "52：信用埋"）<BR>
     * の場合はtrueを、<BR>
     * それぞれ返却する。<BR>
     * <BR>
     * 上記以外の場合は例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00651<BR>
     * @@return boolean<BR>
     * @@roseuid 40F4D42F0375
     */
    public boolean isShort() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isShort()";
        log.entering(STR_METHOD_NAME);
        boolean l_isShort = true;
        if (WEB3TradeTypeDef
            .OPEN_LONG_MARGIN
            .equals(eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isShort = false;
            }
        }
        else if (
            WEB3TradeTypeDef.OPEN_SHORT_MARGIN.equals(
                eqtypeOrderReceiptParams.getTradeType()))
        {
            if (WEB3TransactionTypeSONARDef
                .OPEN_CONTRACT
                .equals(eqtypeOrderReceiptParams.getSonarTradedCode())
                || WEB3TransactionTypeSONARDef.SETTLE_CONTRACT.equals(
                    eqtypeOrderReceiptParams.getSonarTradedCode()))
            {
                l_isShort = true;
            }
        }
        else
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00651,
                STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
        return l_isShort;
    }
    /**
     * (is新規建注文)<BR>
     * 【株式注文入力通知キューテーブル】取引コード（SONAR）より、<BR>
     * AP層で使用するis新規建注文を返却する。<BR>
     * <BR>
     * 取引コード（SONAR）＝"51：信用建"の場合はtrueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * <BR>
     * @@return boolean<BR>
     * @@roseuid 40EA6A3D01DB
     */
    public boolean isOpenMarginOrder()
    {
        final String STR_METHOD_NAME = "isOpenMarginOrder()";
        log.entering(STR_METHOD_NAME);
        boolean l_isOpenMarginOrder = true;
        if (WEB3TransactionTypeSONARDef
            .OPEN_CONTRACT
            .equals(eqtypeOrderReceiptParams.getSonarTradedCode()))
        {
            l_isOpenMarginOrder = true;
        }
        else
        {
            l_isOpenMarginOrder = false;
        }
        log.exiting(STR_METHOD_NAME);
        return l_isOpenMarginOrder;
    }
    /**
     * (is返済注文)<BR>
     * 【株式注文入力通知キューテーブル】取引コード（SONAR）より、<BR>
     * AP層で使用するis返済注文を返却する。<BR>
     * <BR>
     * 取引コード（SONAR）＝"52：信用埋"の場合はtrueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * <BR>
     * @@return boolean<BR>
     * @@roseuid 40F653640146
     */
    public boolean isCloseMarginOrder()
    {
        final String STR_METHOD_NAME = "isCloseMarginOrder()";
        log.entering(STR_METHOD_NAME);
        boolean l_isCloseMarginOrder = true;
        if (WEB3TransactionTypeSONARDef
            .SETTLE_CONTRACT
            .equals(eqtypeOrderReceiptParams.getSonarTradedCode()))
        {
            l_isCloseMarginOrder = true;
        }
        else
        {
            l_isCloseMarginOrder = false;
        }
        log.exiting(STR_METHOD_NAME);
        return l_isCloseMarginOrder;
    }
    
    public HostEqtypeOrderReceiptParams getDataSourseObject()
    {
        return eqtypeOrderReceiptParams;
    }
    
    /**
     * (get値段条件)<BR>
     * 【株式注文入力通知キューテーブル】値段条件(SONAR)に応じた<BR>
     * WEBⅢにおける値段条件を返す。<BR>
     * <BR>
     * 拡張株式注文マネージャ.get値段条件(株式注文通知キューParams.値段条件(SONAR))に<BR>
     * delegateする。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getPriceConditionType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getPriceConditionType()";
        log.entering(STR_METHOD_NAME);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityOrderManager l_orderMgr =
            (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

        String l_strPriceConditionType =
            l_orderMgr.getPriceConditionType(this.eqtypeOrderReceiptParams.price_condition_type);

        log.exiting(STR_METHOD_NAME);
        return l_strPriceConditionType;
    }
    
    /**
     * (is空売り規制対象)<BR>
     * 【株式注文入力通知キューテーブル】空売フラグより、<BR>
     * 空売り規制対象かどうかを返却する。<BR>
     * <BR>
     * 空売フラグ＝"5：価格規制対象"の場合はtrueを返却する。<BR>
     * 以外、falseを返却する。<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public boolean isShortSellingRestraint() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isShortSellingRestraint()";
        log.entering(STR_METHOD_NAME);

        boolean l_isShortSellingRestraint = false;
        if (eqtypeOrderReceiptParams.getShortSellOrderFlag().equals(
                WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT))
        {
            l_isShortSellingRestraint = true;
        }
        else
        {
            l_isShortSellingRestraint = false;
        }

        log.exiting(STR_METHOD_NAME);
        return l_isShortSellingRestraint;
    }
    
}
@
