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
filename	WEB3MarginSwapOrderNotifyDataAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡入力通知データアダプタ(WEB3MarginSwapOrderNotifyDataAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 森川 (SRA) 新規作成
*/
package webbroker3.equity.service.delegate;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3SwapTradeTypeDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeBranchMarketRepayDealtCond;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.gentrade.WEB3GentradeFinObjectManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡入力通知データアダプタ）。<BR>
 * <BR>
 * 信用取引現引現渡入力通知データアダプタクラス
 * @@version 1.0
 */
public class WEB3MarginSwapOrderNotifyDataAdapter
{
    /**
     * (ログ出力ユーティリティ)。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyDataAdapter.class);


    /**
     * (株式現引現渡入力通知キューParams)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】の１レコード。<BR>
     */
    private HostEqtypeSwapReceiptParams eqtypeSwapReceiptParams;


    /**
     * (コンストラクタ)。<BR>
     */
    private WEB3MarginSwapOrderNotifyDataAdapter()
    {
    }


    /**
     * (create)。<BR>
     * <BR>
     * 信用取引現引現渡入力通知データアダプタインスタンスを生成する。 <BR>
     * <BR>
     * １）　@本インスタンスを生成する。 <BR>
     * ２）　@生成したインスタンスに引数のキューデータをセットする。 <BR>
     * ３）　@インスタンスを返却する。 <BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     * 　@本メソッドによってインスタンス化するように制限する） <BR>
     * <BR>
     * @@param eqtypeSwapReceiptParams - 【現引現渡入力通知キューテーブル】データオブジェクト<BR>
     * 【株式現引現渡入力通知キューテーブル】データオブジェクト<BR>
     * @@return WEB3MarginSwapOrderNotifyDataAdapter<BR>
     */
    public static WEB3MarginSwapOrderNotifyDataAdapter create(HostEqtypeSwapReceiptParams l_eqtypeSwapReceiptParams)
    {
        final String STR_METHOD_NAME = "create(HostEqtypeSwapReceiptParams) ";
        log.entering(STR_METHOD_NAME);

        WEB3MarginSwapOrderNotifyDataAdapter l_adapter = new WEB3MarginSwapOrderNotifyDataAdapter();
        l_adapter.eqtypeSwapReceiptParams = l_eqtypeSwapReceiptParams;

        log.exiting(STR_METHOD_NAME);
        return l_adapter;
    }


    /**
     * (get市場)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】市場コード（SONAR）より、 <BR>
     * AP層で使用する市場オブジェクトを取得する。 <BR>
     * <BR>
     * 拡張金融オブジェクトマネージャ.get市場BySONAR( )に委譲する。 <BR>
     * <BR>
     * ------------------------------------------------------------- <BR>
     * ＜拡張金融オブジェクトマネージャ.get市場BySONAR( )：引数設定仕様＞ <BR>
     * <BR>
     * 証券会社コード：　@現引現渡入力通知キューParams.証券会社コード <BR>
     * 市場コード(SONAR)：　@現引現渡入力通知キューParams.市場コード(SONAR) <BR>
     * ------------------------------------------------------------- <BR>
     * <BR>
     * @@return Market<BR>
     * @@throws WEB3SystemLayerException
     */
    public Market getMarket() throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = "getMarket()";
        log.entering(STR_METHOD_NAME);

        //--------------------
        //拡張金融オブジェクトマネージャを取得する。
        //--------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeFinObjectManager l_gentradeFinObjectManager =
            (WEB3GentradeFinObjectManager)l_finApp.getFinObjectManager();

        //--------------------
        //拡張金融オブジェクトマネージャ.get市場BySONAR( )に委譲する。
        //--------------------
        Market l_market = l_gentradeFinObjectManager.getMarketBySONAR(
            this.eqtypeSwapReceiptParams.getInstitutionCode(),
            this.eqtypeSwapReceiptParams.getSonarMarketCode());
        
        log.exiting(STR_METHOD_NAME);
        return l_market;
    }


    /**
     * (get税区分)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】税区分（特定口座区分）（以下「税区分」）より、 <BR>
     * AP層で使用する税区分（TaxTypeEnum）を返却する。 <BR>
     * <BR>
     * １） 一般口座のセット <BR>
     * 　@税区分＝”一般”の場合、TaxTypeEnum.”一般”を返却する。 <BR>
     * <BR>
     * ２）　@特定口座のセット <BR>
     * 　@税区分＝”特定”の場合、以下の処理を行う。 <BR>
     * <BR>
     * ２－１）　@受渡日を取得する。 <BR>
     * <BR>
     * 　@　@　@　@拡張プロダクトマネージャ.get取引銘柄( )の戻り値.getDailyDeliveryDate( )を <BR>
     * 　@　@　@　@受渡日として使用する。 <BR>
     * <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@　@＜get取引銘柄( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@　@証券会社：　@拡張アカウントマネージャ.getInstitution( <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@現引現渡入力通知キューParams.証券会社コード) <BR>
     * 　@　@　@　@銘柄コード：　@現引現渡入力通知キューParams.銘柄コード <BR>
     * 　@　@　@　@市場コード：　@this.get市場().getMarketCode() <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２－２）　@顧客.get受渡日信用取引税区分( )にて、受渡日の信用取引税区分を取得する。 <BR>
     * <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@　@＜get受渡日信用取引税区分( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@　@受渡日：　@２－１）で取得した受渡日 <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * 　@　@　@　@(*)顧客オブジェクトの取得：拡張アカウントマネージャ.get顧客( <BR>
     * 　@　@　@　@　@　@　@現引現渡入力通知キューParams.証券会社コード,  <BR>
     * 　@　@　@　@　@　@　@現引現渡入力通知キューParams.口座コード)の戻り値オブジェクトを使用する。 <BR>
     * <BR>
     * ２－３）　@２－２）で取得した信用取引税区分が、 <BR>
     * 　@　@　@　@　@（TaxTypeEnum.SPECIAL（特定）、SPECIAL_WITHHOLD（特定かつ源泉徴収））の <BR>
     * 　@　@　@　@　@いずれかの場合は、 <BR>
     * 　@　@　@　@　@TaxTypeEnum.SPECIAL（特定）を返す。<BR>
     * 　@　@　@　@　@以外は「信用の特定口座開設なし」の例外をthrowする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_01703<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@throws WEB3BaseException
     */
    public TaxTypeEnum getTaxType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getTaxType() ";
        log.entering(STR_METHOD_NAME);
        

        //--------------------
        // １） 一般口座の場合
        //--------------------
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(this.eqtypeSwapReceiptParams.getTaxType()))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.NORMAL;
        }


        //--------------------
        // ２） 特定口座の場合
        //--------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();


        //--------------------
        // ２－１） 受渡日を取得する
        //--------------------
        Institution l_institution               = null;

        //証券会社を取得
        try {
            l_institution = l_accountManager
                .getInstitution(eqtypeSwapReceiptParams.getInstitutionCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //受渡日を取得
        Date l_datDeliveryDate = WEB3DateUtility.getDate(
            eqtypeSwapReceiptParams.getDeliveryDate(), "yyyyMMdd");
        
        
        //--------------------
        // ２－２） 顧客．get受渡日信用取引税区分()にて受渡日の信用取引税区分を取得する
        //--------------------
        WEB3GentradeMainAccount l_mainAccount   = null;
        try {
            //顧客オブジェクト取得
            l_mainAccount
                = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    eqtypeSwapReceiptParams.getBranchCode(),
                    eqtypeSwapReceiptParams.getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //受渡日信用取引税区分取得
        TaxTypeEnum l_taxTypeEnum = l_mainAccount.getDeliveryDateMarginTaxType(l_datDeliveryDate);
        

        //--------------------
        // ２－３） TaxTypeEnum.SPECIAL（特定）を返す。 
        //--------------------
        if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.SPECIAL;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01703,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }


    /**
     * (get税区分（現引現渡）)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】税区分（現引現渡現物特定口座区分）（以下「税区分」）より、 <BR>
     * AP層で使用する税区分（TaxTypeEnum）を返却する。 <BR>
     * <BR>
     * １） 一般口座のセット <BR>
     * 　@税区分＝”一般”の場合、TaxTypeEnum.”一般”を返却する。 <BR>
     * <BR>
     * ２）　@特定口座のセット <BR>
     * 　@税区分＝”特定”の場合、以下の処理を行う。 <BR>
     * <BR>
     * ２－１）　@受渡日を取得する。 <BR>
     * <BR>
     * 　@　@　@　@拡張プロダクトマネージャ.get取引銘柄( )の戻り値.getDailyDeliveryDate( )を <BR>
     * 　@　@　@　@受渡日として使用する。 <BR>
     * <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@　@＜get取引銘柄( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@　@証券会社：　@拡張アカウントマネージャ.getInstitution( <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@現引現渡入力通知キューParams.証券会社コード) <BR>
     * 　@　@　@　@銘柄コード：　@現引現渡入力通知キューParams.銘柄コード <BR>
     * 　@　@　@　@市場コード：　@this.get市場().getMarketCode() <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * ２－２）　@顧客.get受渡日税区分( )にて、受渡日の税区分を取得する。 <BR>
     * <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * 　@　@　@　@＜get受渡日税区分( )：引数設定仕様＞ <BR>
     * <BR>
     * 　@　@　@　@受渡日：　@２－１）で取得した受渡日 <BR>
     * 　@　@　@　@---------------------------------------------------------- <BR>
     * <BR>
     * 　@　@　@　@(*)顧客オブジェクトの取得：拡張アカウントマネージャ.get顧客( <BR>
     * 　@　@　@　@　@　@　@現引現渡入力通知キューParams.証券会社コード,  <BR>
     * 　@　@　@　@　@　@　@現引現渡入力通知キューParams.口座コード)の戻り値オブジェクトを使用する。 <BR>
     * <BR>
     * ２－３）　@２－２）で取得した税区分が、 <BR>
     * 　@　@　@　@　@（TaxTypeEnum.SPECIAL（特定）、SPECIAL_WITHHOLD（特定かつ源泉徴収））の <BR>
     * 　@　@　@　@　@いずれかの場合は、 <BR>
     * 　@　@　@　@　@TaxTypeEnum.SPECIAL（特定）を返す。  <BR>
     * 　@　@　@　@　@以外は「現物の特定口座開設なし」の例外をthrowする。 <BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_00637<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@throws WEB3BaseException
     */
    public TaxTypeEnum getSwapTaxType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSwapTaxType() ";
        log.entering(STR_METHOD_NAME);
        

        //--------------------
        // １） 一般口座の場合
        //--------------------
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(this.eqtypeSwapReceiptParams.getSwapTaxType()))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.NORMAL;
        }

        //--------------------
        // ２） 特定口座の場合
        //--------------------
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountManager = l_finApp.getAccountManager();


        //--------------------
        // ２－１） 受渡日を取得する
        //--------------------
        Institution l_institution               = null;

        //証券会社を取得
        try {
            l_institution = l_accountManager
                .getInstitution(eqtypeSwapReceiptParams.getInstitutionCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //受渡日を取得
        Date l_datDeliveryDate = WEB3DateUtility.getDate(
            eqtypeSwapReceiptParams.getDeliveryDate(), "yyyyMMdd");
        
        
        //--------------------
        // ２－２） 顧客．get受渡日税区分()にて受渡日の税区分を取得する
        //--------------------
        WEB3GentradeMainAccount l_mainAccount   = null;
        try {
            //顧客オブジェクト取得
            l_mainAccount
                = (WEB3GentradeMainAccount) l_accountManager.getMainAccount(
                    l_institution.getInstitutionId(),
                    eqtypeSwapReceiptParams.getBranchCode(),
                    eqtypeSwapReceiptParams.getAccountCode());
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //受渡日税区分取得
        TaxTypeEnum l_taxTypeEnum = l_mainAccount.getDeliveryDateTaxType(l_datDeliveryDate);
        

        //--------------------
        // ２－３） TaxTypeEnum.SPECIAL（特定）を返す。 
        //--------------------
        if (TaxTypeEnum.SPECIAL.equals(l_taxTypeEnum)
            || TaxTypeEnum.SPECIAL_WITHHOLD.equals(l_taxTypeEnum))
        {
            log.exiting(STR_METHOD_NAME);
            return TaxTypeEnum.SPECIAL;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00637,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }
    

    /**
     * (get弁済区分)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】弁済区分（SONAR）より、 <BR>
     * AP層で使用する弁済区分を返却する。 <BR>
     * <BR>
     * [弁済区分（SONAR）　@＝　@”1：制度信用（店頭以外または店頭の弁済期間が6ヶ月）”の場合] <BR>
     * 　@"1"(制度信用)を返却する。 <BR>
     * <BR>
     * [弁済区分（SONAR）　@>=　@”3：一般信用（全市場）”の場合] <BR>
     * 　@"2"(一般信用)を返却する。 <BR>
     * <BR>
     * [弁済区分（SONAR）が上記以外の場合] <BR>
     * 　@例外をthrowする。 <BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:　@ WEB3ErrorCatalog.BUSINESS_ERROR_00641<BR>
     * <BR>
     * @@return String<BR>
     * @@throws WEB3BaseException
     */
    public String getRepaymentType() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentType() ";
        log.entering(STR_METHOD_NAME);
        final int l_iSonnarRepayDivMgnSys = 1;
        final int l_iSonnarRepayDivMgnSysAllMarket = 3;
        int l_iSonnarRepayDiv;


        //--------------------
        //弁済区分(Sonnar)を取得
        //--------------------
        String l_strRepaymentType = eqtypeSwapReceiptParams.getSonarRepaymentType();
        try
        {
            l_iSonnarRepayDiv = Integer.parseInt(l_strRepaymentType);
        }
        catch (NumberFormatException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                STR_METHOD_NAME);
        }
        

        //--------------------
        //弁済区分(Sonnar)の値により弁済区分を返す。
        //--------------------
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
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00641,
                STR_METHOD_NAME);
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_strRepaymentType;
    }
    

    /**
     * (get弁済期限値)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】弁済区分（SONAR）より、 <BR>
     * AP層で使用する弁済期限値を返却する。 <BR>
     * <BR>
     * １）　@（部店市場弁済別）取扱条件オブジェクト(*1)を取得する。 <BR>
     * <BR>
     * ２）　@取得した取扱条件オブジェクトの数分だけ以下の処理をループする。 <BR>
     * <BR>
     * 　@２－１）以下の条件に該当する取扱条件オブジェクトを取得する。 <BR>
     * 　@　@[条件] <BR>
     * 　@　@取得した取扱条件オブジェクト.証券会社コード<BR>
     * 　@　@　@　@＝　@this.現引現渡入力通知キューParams.証券会社コード <BR>
     * 　@　@取得した取扱条件オブジェクト.部店コード<BR>
     * 　@　@　@　@＝　@this.現引現渡入力通知キューParams.get部店コード <BR>
     * 　@　@取得した取扱条件オブジェクト.市場コード<BR>
     * 　@　@　@　@＝　@this.get市場().getMarketCode() <BR>
     * 　@　@取得した取扱条件オブジェクト.弁済区分（SONAR）<BR>
     * 　@　@　@　@＝　@this.現引現渡入力通知キューParams.弁済区分（SONAR） <BR>
     * <BR>
     * ３）　@該当データなし（レコード件数==0）の場合は、 <BR>
     * 　@　@　@「弁済期限値特定不可」の例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00661<BR>
     * 　@　@　@また、２－１）にて取得したレコード件数 >= 2（＝該当データが複数件あり）の場合も、 <BR>
     * 　@　@　@「弁済期限値特定不可」の例外をスローする。 <BR>
     * 　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag:   BUSINESS_ERROR_00661<BR>
     * 　@　@　@以外、取得した取扱条件オブジェクト.弁済期限値を返却する。 <BR>
     * <BR>
     * (*1) <BR>
     * （部店市場弁済別）取扱条件オブジェクト.get（部店市場弁済別）取扱条件( )により取得 <BR>
     * <BR>
     * [get（部店市場弁済別）取扱条件( )に指定する引数] <BR>
     * 部店：　@拡張アカウントマネージャ.get部店(this.証券会社コード, this.部店コード) <BR>
     * <BR>
     * @@return double<BR>
     * @@throws WEB3BaseException<BR>
     */
    public double getRepaymentNum() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getRepaymentNum()";
        log.entering(STR_METHOD_NAME);
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager =
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        

        //--------------------
        //（部店市場弁済別）取扱条件オブジェクトを取得する。
        //--------------------
        WEB3GentradeBranch l_branch = null;
        WEB3GentradeBranchMarketRepayDealtCond[] l_conditionList = null;

        try
        {
            l_branch = (WEB3GentradeBranch) l_accountManager.getWeb3GenBranch(
                eqtypeSwapReceiptParams.institution_code, eqtypeSwapReceiptParams.branch_code);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                STR_METHOD_NAME);
        }
        l_conditionList = WEB3GentradeBranchMarketRepayDealtCond
            .getBranchMarketRepayDealtCond(l_branch);
        

        //--------------------
        //取得した（部店市場弁済別）取扱条件オブジェクトの数分だけループする
        //--------------------
        BranchMarketRepayDealtCondRow l_conditionRow = null;
        BranchMarketRepayDealtCondRow l_retConditionRow = null;
        int l_iConditionsCount;

        //--------------------
        //該当データ無しの場合は弁済期限値特定不可例外をスローする
        //--------------------
        if (l_conditionList == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                STR_METHOD_NAME);
        }
        
        l_iConditionsCount = l_conditionList.length;
        for (int i = 0; i < l_iConditionsCount; i++)
        {
            l_conditionRow
                = (BranchMarketRepayDealtCondRow) l_conditionList[i].getDataSourceObject();
            
            //--------------------
            //以下条件に該当する取扱条件オブジェクトを取得する
            //--------------------
            if (l_conditionRow.getInstitutionCode().equals(eqtypeSwapReceiptParams.institution_code)
                && l_conditionRow.getBranchCode().equals(eqtypeSwapReceiptParams.branch_code)
                && l_conditionRow.getMarketCode().equals(this.getMarket().getMarketCode())
                && l_conditionRow.getSonarRepaymentType().equals(eqtypeSwapReceiptParams.sonar_repayment_type))
            {
                if (l_retConditionRow != null)
                {
                    //--------------------
                    //該当データが2件以上の場合も弁済期限値特定不可例外をスローする
                    //--------------------
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                        STR_METHOD_NAME);
                }
                else
                {
                    l_retConditionRow = l_conditionRow;
                }
            }
        }

        //--------------------
        //該当データ無しの場合は弁済期限値特定不可例外をスローする
        //--------------------
        if (l_retConditionRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00661,
                STR_METHOD_NAME);
        }
        

        log.exiting(STR_METHOD_NAME);
        return l_retConditionRow.getRepaymentNum();
    }
    

    /**
     * (is買建)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】売買区分より、AP層で使用するis買建を返却する。 <BR>
     * <BR>
     * 売買区分＝"2：現引"の場合はtrueを、 <BR>
     * 売買区分＝"1：現渡"の場合はfalseを、 <BR>
     * それぞれ返却する。 <BR>
     * <BR>
     * 上記以外の場合は例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00651<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isLong() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        boolean l_blnIsLong;
        String l_strTradeType = eqtypeSwapReceiptParams.getTradeType();
        
        //売買区分="2:現引"のときはtrue
        if (WEB3SwapTradeTypeDef.RECIPT.equals(l_strTradeType))
        {
            l_blnIsLong = true;
        }
        //売買区分="1:現渡"のときはfalse
        else if (WEB3SwapTradeTypeDef.DELIVERY.equals(l_strTradeType))
        {
            l_blnIsLong = false;
        }
        //上記以外の場合は例外をスロー
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00651,
                STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsLong;
    }
    

    /**
     * (is売建)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】売買区分より、AP層で使用するis売建を返却する。 <BR>
     * <BR>
     * 売買区分＝"1：現渡"の場合はtrueを、 <BR>
     * 売買区分＝"2：現引"の場合はfalseを、 <BR>
     * それぞれ返却する。 <BR>
     * <BR>
     * 上記以外の場合は例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00652<BR>
     * <BR>
     * @@return boolean<BR>
     * @@throws WEB3BaseException
     */
    public boolean isShort() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isLong()";
        log.entering(STR_METHOD_NAME);
        
        boolean l_blnIsLong = this.isLong();
        
        log.exiting(STR_METHOD_NAME);
        return l_blnIsLong ? false : true;
    }


    /**
     * (getDataSourseObject)。<BR>
     * <BR>
     * 【現引現渡入力通知キューテーブル】レコードを返す。
     * <BR>
     * @@return HostEqtypeSwapReceiptParams<BR>
     */
    public HostEqtypeSwapReceiptParams getDataSourseObject()
    {
        return eqtypeSwapReceiptParams;
    }
}
@
