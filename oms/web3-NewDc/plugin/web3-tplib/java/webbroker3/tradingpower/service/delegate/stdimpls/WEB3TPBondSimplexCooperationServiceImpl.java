head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.07.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPBondSimplexCooperationServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券シンプレクス連携サービスImpl(WEB3TPBondSimplexCooperationServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/05/26 安陽(中訊) 新規作成 モデルNo.277,279,280,281
*/
package webbroker3.tradingpower.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RestraintDivDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.service.delegate.WEB3TPBondSimplexCooperationService;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManager;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券シンプレクス連携サービスImpl)<BR>
 * (債券シンプレクス連携サービスImpl)<BR>
 *
 * @@author 安陽
 * @@version 1.0
 */
public class WEB3TPBondSimplexCooperationServiceImpl implements WEB3TPBondSimplexCooperationService
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPBondSimplexCooperationServiceImpl.class);

    /**
     * (拘束金種別)<BR>
     * (定数)<BR>
     * 拘束金種別<BR>
     */
    private static final String RESTRAINT_DIV = WEB3RestraintDivDef.BOND_BUY_AMOUNT;

    /**
     * (save債券買付代金)<BR>
     * （save債券買付代金）<BR>
     * <BR>
     * 債権買付注文の代金を拘束する。<BR>
     * <BR>
     * ※シーケンス図「(債券シンプレックス連携サービス)save債券買付代金」を参照。<BR>
     * ================================================<BR>
     * シーケンス図：((債券シンプレックス連携サービス)save債券買付代金)<BR>
     * 具体位置：(isExist注文No()の戻り値が、trueの場合)<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_03095<BR>
     * ================================================<BR>
     * <BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     * （口座ID）<BR>
     * @@param l_dblRestraint - (債券買付代金)<BR>
     * （債券買付代金）<BR>
     * @@param l_datFinTransactionDate - (トランザクション発生日)<BR>
     * （トランザクション発生日）<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * （受渡日）<BR>
     * @@param l_strOrderNo - (注文No)<BR>
     * （注文No）<BR>
     * @@throws WEB3BaseException
     */
    public void saveBondBuyAmount(long l_lngAccountId, double l_dblRestraint,
        Date l_datFinTransactionDate, Date l_datDeliveryDate, String l_strOrderNo)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "saveBondBuyAmount(long, double, Date, Date, String)";
        log.entering(STR_METHOD_NAME);

        //その他拘束金（仮拘束）テーブルに、
        //引数.注文Noが存在しているかどうかの判別を行う。
        //　@[引数]
        // 　@ 注文No = 引数.注文No
        boolean l_blnIsExistOrderNo = isExistOrderNo(l_strOrderNo);

        //(*)分岐フロー
        //isExist注文No()の戻り値が、trueの場合
        //例外「注文No重複エラー」をスローする。
        if (l_blnIsExistOrderNo)
        {
            log.debug("注文No重複エラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03095,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文No重複エラー");
        }

        //債券買付代金のデータを、その他拘束金（仮拘束）テーブルへ挿入する。
        //　@[引数]
        //　@　@口座ID = 引数.口座ID
        //　@　@拘束金 = 引数.債券買付代金
        //　@　@トランザクション発生日 = 引数.トランザクション発生日
        //　@　@受渡日 = 引数.受渡日
        //　@　@削除キー１ = 引数.注文No
        //　@　@拘束金種別 = this.拘束金種別
        WEB3TPPersistentDataManager.getInstance().saveOtherRestraint(
            l_lngAccountId, l_dblRestraint, l_datFinTransactionDate, l_datDeliveryDate, l_strOrderNo, RESTRAINT_DIV);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (cancel債券買付代金)<BR>
     * （cancel債券買付代金）<BR>
     * <BR>
     * 債権買付注文の代金の拘束を解除する。<BR>
     * <BR>
     * ※シーケンス図「(債券シンプレックス連携サービス)cancel債券買付代金」を参照。<BR>
     * ================================================<BR>
     * シーケンス図：((債券シンプレックス連携サービス)cancel債券買付代金)<BR>
     * 具体位置：(isExist注文No()の戻り値が、falseの場合)<BR>
     * class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_03096<BR>
     * ================================================<BR>
     * <BR>
     * @@param l_strOrderNo - (注文No)<BR>
     * （注文No）<BR>
     * @@throws WEB3BaseException
     */
    public void cancelBondBuyAmount(String l_strOrderNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "cancelBondBuyAmount(String)";
        log.entering(STR_METHOD_NAME);

        //その他拘束金（仮拘束）テーブルに、
        //引数.注文Noが存在しているかどうかの判別を行う。
        //  [引数]
        //    注文No = 引数.注文No
        boolean l_blnIsExistOrderNo = isExistOrderNo(l_strOrderNo);

        //(*)分岐フロー
        //isExist注文No()の戻り値が、falseの場合は
        //例外「取消データなしエラー」をスローする。
        if (!l_blnIsExistOrderNo)
        {
            log.debug("取消データなしエラー");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03096,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "取消データなしエラー");
        }

        //その他拘束金（仮拘束）テーブルを更新（論理削除）する。
        //　@[引数]
        //　@　@拘束金種別 = this.拘束金種別
        //　@　@削除キー１ = 引数.注文No
        WEB3TPPersistentDataManager.getInstance().deleteOtherRestraint(RESTRAINT_DIV, l_strOrderNo);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (isExist注文No)<BR>
     * (isExist注文No)<BR>
     * <BR>
     * その他拘束金（仮拘束）テーブルに引数.注文Noが存在するかどうか判断する。<BR>
     * 　@※レコードが存在する場合は、true を返却する。<BR>
     * 　@　@レコードが存在しない場合は、false を返却する。<BR>
     * <BR>
     * 　@-余力データソースアクセス管理.isExistその他拘束金（仮拘束）()をコールし、<BR>
     * 　@　@戻り値を返却する。<BR>
     * <BR>
     * 　@　@[引数]<BR>
     * 　@　@　@拘束金種別 = this.拘束金種別<BR>
     * 　@　@　@削除キー１ = 引数.注文No<BR>
     * <BR>
     * @@param l_strOrderNo - (注文No)<BR>
     * （注文No）<BR>
     * @@return boolean
     */
    private boolean isExistOrderNo(String l_strOrderNo) throws WEB3BaseException
    {
        //余力データソースアクセス管理.isExistその他拘束金（仮拘束）()をコールし、戻り値を返却する
        return WEB3TPPersistentDataManager.getInstance().isExistOtherRestraint(RESTRAINT_DIV, l_strOrderNo);
    }

    /**
     * (get証拠金残高)<BR>
     * （get証拠金残高）<BR>
     * <BR>
     * 証拠金残高を取得する。<BR>
     * <BR>
     * １）-余力データソースアクセス管理.get顧客勘定残高(マスタ情報)()をコールする。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@　@・口座ID = 顧客.getAccountId()の戻り値<BR>
     * 　@　@・補助口座ID = 顧客.getSubAccount(<BR>
     * 　@　@　@7:株式オプション取引口座（先物証拠金）).getSubAccountId()の戻り値<BR>
     * <BR>
     * 　@　@※顧客.getSubAccount()で補助口座オブジェクトが取得できない場合、0を返却する。<BR>
     * <BR>
     * ２）１）で取得した顧客勘定残高(マスタ情報)Rowの残高を返却する。<BR>
     * <BR>
     * 　@証拠金残高 = 顧客勘定残高(マスタ情報)Row.残高（当日+n日）<BR>
     * <BR>
     * 　@※ｎ = 引数.指定日<BR>
     * 　@※１）の戻り値がnullの場合、0を返却する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * （顧客）<BR>
     * @@param l_intReservedDate - (指定日)<BR>
     * （指定日）<BR>
     * @@return double
     */
    private double getDepositBalance(MainAccount l_mainAccount, int l_intReservedDate)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getDepositBalance(MainAccount, int)";
        log.entering(STR_METHOD_NAME);

        //補助口座ID
        long l_lngSubAccountId = 0L;
        try
        {
            //顧客.getSubAccount(7:株式オプション取引口座（先物証拠金）)
            SubAccount l_subAccount = l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            //補助口座.getSubAccountId()
            l_lngSubAccountId = l_subAccount.getSubAccountId();
        }
        catch (NotFoundException l_ex)
        {
            //※顧客.getSubAccount()で補助口座オブジェクトが取得できない場合、0を返却する。
            return 0.0D;
        }

        //余力データソースアクセス管理.get顧客勘定残高(マスタ情報)()
        TpCashBalanceRow l_tpCashBalanceRow =
            WEB3TPPersistentDataManager.getInstance().getTpCashBalanceRow(
                l_mainAccount.getAccountId(), l_lngSubAccountId);

        double l_dblCash = 0.0D;
        if (l_tpCashBalanceRow != null)
        {
            switch (l_intReservedDate)
            {
                //顧客勘定残高(マスタ情報)Row.残高（当日+０日）
                case WEB3TPSpecifiedPointDef.T_0:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance0();
                    break;

                //顧客勘定残高(マスタ情報)Row.残高（当日+１日）
                case WEB3TPSpecifiedPointDef.T_1:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance1();
                    break;

                //顧客勘定残高(マスタ情報)Row.残高（当日+２日）
                case WEB3TPSpecifiedPointDef.T_2:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance2();
                    break;

                //顧客勘定残高(マスタ情報)Row.残高（当日+３日）
                case WEB3TPSpecifiedPointDef.T_3:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance3();
                    break;

                //顧客勘定残高(マスタ情報)Row.残高（当日+４日）
                case WEB3TPSpecifiedPointDef.T_4:
                    l_dblCash = l_tpCashBalanceRow.getCashBalance4();
                    break;

                //顧客勘定残高(マスタ情報)Row.残高（当日+５日以降）
                default :
                    l_dblCash = l_tpCashBalanceRow.getCashBalance5();
                    break;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_dblCash;
    }

    /**
     * (get資産評価額一覧)<BR>
     * （get資産評価額一覧）<BR>
     * <BR>
     * 資産評価額一覧を作成し、返却する。<BR>
     * <BR>
     * ※シーケンス図「（債券シンプレクス連携サービス）get資産評価額一覧」を参照。<BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * (補助口座)<BR>
     * @@return String
     * @@throws WEB3BaseException
     */
    public String getAssetList(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAssetList(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);

        //引数.補助口座がnullの場合
        if (l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //口座IDを取得
        long l_lngAccountId = l_subAccount.getAccountId();

        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //信用口座を開設しているかどうかの判別を行う。
        //true：　@開設済
        //false：　@未開設
        //[引数]
        //弁済区分：　@0(指定なし)
        boolean l_blnIsMarginAccountEstablished =
            l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //余力計算条件オブジェクトを生成する。
        //[引数]
        //補助口座：　@引数.補助口座
        WEB3TPCalcCondition l_calcCondition =
            WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);

        //預り金評価額
        BigDecimal l_bdAccountAsset = null;
        //株式評価額
        double l_dblEquityAsset = 0.0D;
        //投資信託評価額
        BigDecimal l_bdMutualFundAsset = null;

        //(*)分岐フロー
        //is信用口座開設()の戻り値 = false(現物顧客)
        //の場合は以下の処理を実施する。
        if (!l_blnIsMarginAccountEstablished)
        {
            //余力計算結果<現物顧客>を取得する。
            //[引数]
            //long：　@補助口座.getAccountId()の戻り値
            List l_lisCalcResultEquitys =
                WEB3TPTradingPowerCalcEquity.findCalcResultEquityParams(l_lngAccountId);

            //資産余力情報<現物顧客>オブジェクトを生成する。
            //[引数]
            //余力計算結果Params：　@find余力計算結果<現物顧客>()の戻り値
            //余力計算条件：　@create余力計算条件()の戻り値
            WEB3TPTradingPowerCalcEquity l_calcEquity =
                new WEB3TPTradingPowerCalcEquity(l_lisCalcResultEquitys, l_calcCondition);

            //預り金残高を取得する。
            double l_dblAccountBalance = l_calcEquity.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);

            //証拠金残高を取得する。
            //　@[引数]
            //　@　@顧客 = getMainAccount()の戻り値
            //　@　@指定日 = 5
            double l_dblDepositBalance = getDepositBalance(l_mainAccount, WEB3TPSpecifiedPointDef.T_5);

            //預り金評価額を計算する
            BigDecimal l_bdAccountBalance = new BigDecimal(String.valueOf(l_dblAccountBalance));
            BigDecimal l_bdDepositBalance = new BigDecimal(String.valueOf(l_dblDepositBalance));
            l_bdAccountAsset = l_bdAccountBalance.add(l_bdDepositBalance);

            //余力計算結果詳細<現物顧客>Paramsを取得する。
            TpCalcResultEquityDetailParams l_tpCalcResultEquityDetailParams =
                l_calcEquity.getCalcResultDetailEquity();

            //株式評価額を計算する
            l_dblEquityAsset = l_tpCalcResultEquityDetailParams.getEquityAssetExecuted();

            //投資信託評価額 =
            //    余力計算結果詳細(現物顧客)Params.投資信託評価額(約残)
            //    + 余力計算結果詳細(現物顧客)Params.累積投資評価額(約残)
            double l_dblMutualFundAssetExecuted = l_tpCalcResultEquityDetailParams.getMutualFundAssetExecuted();
            double l_dblRuitoAssetExecuted = l_tpCalcResultEquityDetailParams.getRuitoAssetExecuted();
            BigDecimal l_bdMutualFundAssetExecuted = new BigDecimal(String.valueOf(l_dblMutualFundAssetExecuted));
            BigDecimal l_bdRuitoAssetExecuted = new BigDecimal(String.valueOf(l_dblRuitoAssetExecuted));
            l_bdMutualFundAsset = l_bdMutualFundAssetExecuted.add(l_bdRuitoAssetExecuted);
        }
        //(*)分岐フロー
        //is信用口座開設()の戻り値 = true(信用顧客)
        //の場合は以下の処理を実施する。
        else
        {
            //余力計算結果(List)を取得
            List l_lisCalcResultMargins =
                WEB3TPTradingPowerCalcMargin.findCalcResultMarginParams(l_lngAccountId);

            //資産余力情報
            WEB3TPTradingPowerCalcMargin l_calcMargin =
                new WEB3TPTradingPowerCalcMargin(l_lisCalcResultMargins, l_calcCondition);

            //預り金残高を取得する。
            double l_dblAccountBalance = l_calcMargin.getAccountBalance(WEB3TPSpecifiedPointDef.T_5);

            //証拠金残高を取得する。
            //　@[引数]
            //　@　@顧客 = getMainAccount()の戻り値
            //　@　@指定日 = 5
            double l_dblDepositBalance = getDepositBalance(l_mainAccount, WEB3TPSpecifiedPointDef.T_5);

            //預り金評価額を計算する
            BigDecimal l_bdAccountBalance = new BigDecimal(String.valueOf(l_dblAccountBalance));
            BigDecimal l_bdDepositBalance = new BigDecimal(String.valueOf(l_dblDepositBalance));
            l_bdAccountAsset = l_bdAccountBalance.add(l_bdDepositBalance);

            //余力計算結果詳細<信用顧客>Paramsを取得する。
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                l_calcMargin.getCalcResultDetailMargin();

            //株式評価額を計算する
            l_dblEquityAsset = l_tpCalcResultMarginDetailParams.getEquityAssetExecuted();

            //投資信託評価額 =
            //    余力計算結果詳細(信用顧客)Params.投資信託評価額(約残)
            //    + 余力計算結果詳細(信用顧客)Params.累積投資評価額(約残)
            double l_dblMutualFundAssetExecuted = l_tpCalcResultMarginDetailParams.getMutualFundAssetExecuted();
            double l_dblRuitoAssetExecuted = l_tpCalcResultMarginDetailParams.getRuitoAssetExecuted();
            BigDecimal l_bdMutualFundAssetExecuted = new BigDecimal(String.valueOf(l_dblMutualFundAssetExecuted));
            BigDecimal l_bdRuitoAssetExecuted = new BigDecimal(String.valueOf(l_dblRuitoAssetExecuted));
            l_bdMutualFundAsset = l_bdMutualFundAssetExecuted.add(l_bdRuitoAssetExecuted);
        }

        //資産評価額一覧の作成
        //預り金評価額、株式評価額、投資信託評価額の順に文字列に変換しセットする。
        //※区切り文字はコロン(:)
        //例）預り金評価額300万、株式評価額20万、投資信託評価額105万の場合
        //  [3000000:200000:1050000]
        String l_strAssetList = 
            WEB3StringTypeUtility.formatNumber(l_bdAccountAsset.doubleValue()) + ":"
            + WEB3StringTypeUtility.formatNumber(l_dblEquityAsset)+ ":"
            + WEB3StringTypeUtility.formatNumber(l_bdMutualFundAsset.doubleValue());

        log.exiting(STR_METHOD_NAME);
        return l_strAssetList;
    }

}
@
