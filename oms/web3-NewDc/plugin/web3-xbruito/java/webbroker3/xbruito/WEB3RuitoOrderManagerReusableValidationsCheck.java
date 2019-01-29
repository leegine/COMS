head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3RuitoOrderManagerReusableValidationsCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 累投発注審査個別チェッククラス(WEB3RuitoOrderManagerReusableValidationsCheck)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 韋念瓊 (中訊) 新規作成
                   2004/12/02 韋念瓊 (中訊) 残対応
*/
package webbroker3.xbruito;


import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbruito.RuitoTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbruito.data.RuitoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbruito.stdimpls.RuitoProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BankAccountRegiDef;
import webbroker3.common.define.WEB3CumulativeAccountDivDef;
import webbroker3.common.define.WEB3DesignateMethodDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3StopDef;
import webbroker3.common.define.WEB3TradedDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 累投発注審査個別チェッククラス<BR>
 */
public class WEB3RuitoOrderManagerReusableValidationsCheck
    extends RuitoProductTypeOrderManagerReusableValidations
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3RuitoOrderManagerReusableValidationsCheck.class);

    String l_strMessage;

    /**
     * 顧客が累投取引口座を開設しているかをチェックする。<BR>
     * 引数.補助口座.getMainAccount().getDataSourceObject()<BR>
     *     .get累投口座開設区分()<BR>
     *     の戻り値が”1：累投口座未開設”の場合は例外をスローする。<BR>
     *     エラータグ：BUSINESS_ERROR_00256<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406B741200CE
     */
    public void validateRuitoTradedAccountEstablish(SubAccount l_subAccount)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateRuitoTradedAccountEstablish(" +
            "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);
    
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        log.entering(STR_METHOD_NAME);
        log.debug("AccountId = " + l_subAccount.getAccountId());
        
        //get累投口座開設区分()
        MainAccountRow l_mainAccountRow =
            (MainAccountRow) l_subAccount.getMainAccount().getDataSourceObject();
        if (WEB3CumulativeAccountDivDef.NOT_ESTABLISH.equals(
            l_mainAccountRow.getRuitoAccOpenDiv())) 
        {
            log.debug("累積投資口座が開設なし。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00256,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "累積投資口座が開設なし。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * （validateTradedProductのオーバーロード）<BR>
     * <BR>
     * 取引可能銘柄のチェックを行う。 <BR>
     * <BR>
     * １）　@拡張累投取引銘柄を取得する。 <BR>
     * <BR>
     * 　@－拡張累投銘柄マネージャを取得する。<BR>
     * 　@－拡張累投銘柄マネージャ.get累投取引銘柄()をコールして<BR>
     *      拡張累投取引銘柄を取得する。<BR>
     * 　@　@　@［get累投取引銘柄に渡すパラメタ］<BR>
     * 　@　@　@　@証券会社： 引数.拡張累投銘柄.getInstitution()<BR>
     * 　@　@　@　@銘柄コード： 引数.拡張累投銘柄.getProductCode()<BR>
     * 　@－取得できない場合は、例外をスローする。 <BR>
     *  　@　@エラータグ：<BR>BUSINESS_ERROR_00250 <BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException <BR>
     * <BR>
     * ２）　@取引停止チェック <BR>
     *  －引数.is買付がtrueの場合（買付の場合）、拡張累投銘柄.get買付可能()=false <BR>
     *      であれば例外をスローする。 <BR>
     *  －引数.is買付がfalseの場合（解約の場合）、拡張累投銘柄.get解約可能()=false <BR>
     *      であれば例外をスローする。 <BR>
     * <BR>
     * ３）　@取引規制チェック<BR>
     * 　@－引数.is買付がtrueの場合（買付の場合）、<BR>
     *         拡張累投取引銘柄.get買付停止()の値が”<BR>
     *          1：停止中”であれば例外をスローする。 <BR>
     * 　@－引数.is買付がfalseの場合（解約の場合）、<BR>
     *        拡張累投取引銘柄.get解約停止()の値が”<BR>
     *          1：停止中”であれば例外をスローする。<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00251<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ４）　@取得した拡張累投取引銘柄を返す。<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@param l_isBuy - 買付の場合は true を、解約の場合は false を指定する<BR>
     * @@return webbroker3.xbruito.WEB3RuitoExpansionRuitoTradedProductImpl
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406BBED3026C
     */
    public WEB3RuitoTradedProduct validateTradedProduct(
        WEB3RuitoProduct l_ruitoProduct,
        boolean isBuy)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedProduct(" +
            "WEB3RuitoProduct l_ruitoProduct, boolean l_isBuy)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        WEB3RuitoTradedProduct l_ruitoTradeProduct = null;
        try
        {
            //１）　@拡張累投取引銘柄を取得する。
            //－拡張累投銘柄マネージャを取得する。
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);   
            WEB3RuitoProductManager l_ruitoProductManager =   
               (WEB3RuitoProductManager) l_finApp.getTradingModule(
                       ProductTypeEnum.RUITO).getProductManager();
            
            log.debug("InstitutionCode = " + 
                    l_ruitoProduct.getInstitution().getInstitutionCode());
            log.debug("ProductCode = " + l_ruitoProduct.getProductCode());

            l_ruitoTradeProduct = (WEB3RuitoTradedProduct)
                 l_ruitoProductManager.getRuitoTradedProduct(
                    l_ruitoProduct.getInstitution(),
                    l_ruitoProduct.getProductCode());
                       
            //２）　@取引停止チェック
            //－引数.is買付がtrueの場合（買付の場合）、拡張累投銘柄.get買付可能()=false 
            //であれば例外をスローする。 
            Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            //WEB3-XBRUITO-REMAIN-A-CD-0004
            log.debug("is買付 = " + isBuy);
            
            if (isBuy)
            {
                if (!l_ruitoProduct.isBuyPossible(l_datBizDate))
                {
                    log.debug("取引停止チェックエラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00389,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            //－引数.is買付がfalseの場合（解約の場合）、拡張累投銘柄.get解約可能()=false 
            //であれば例外をスローする。 
            else 
            {
                if (!l_ruitoProduct.isSellPossible(l_datBizDate))
                {
                    log.debug("取引停止チェックエラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00389,
                        this.getClass().getName() + "." + STR_METHOD_NAME);
                }
            }
            
            //３）取引規制チェック
            if (isBuy)
            {
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradeProduct.getBuyStop()))
                {
                    //－引数.is買付がtrueの場合（買付の場合）、拡張累投取引銘柄.get買付停止()の値が
                    //”1：停止中”であれば例外をスローする。  
                    log.debug("取引規制チェックエラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00251,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "買付の場合, 拡張累投取引銘柄.get買付停止()の値が”1：停止中”で");                    
                }
            }
            else 
            {
                if (WEB3StopDef.STOP_INSIDE.equals(l_ruitoTradeProduct.getSellStop()))
                {
                    //－引数.is買付がfalseの場合（解約の場合）、拡張累投取引銘柄.get解約停止()の値が
                    //”1：停止中”であれば例外をスローする。
                    log.debug("取引規制チェックエラー");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00251,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "解約の場合, 拡張累投取引銘柄.get解約停止()の値が”1：停止中”で");                    
                }
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__");
                
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //４）　@取得した拡張累投取引銘柄を返す。  
        log.exiting(STR_METHOD_NAME);
        return l_ruitoTradeProduct;
    }

    /**
     * (validate取引金額)
     * 売買金額が取引金額の範囲内かチェックを行う。<BR>
     * <BR>
     * 取引金額上限　@≧　@引数.売買金額　@≧　@取引金額下限<BR>
     * <BR>
     * １）上限チェックを行う<BR>
     * 　@－取引金額上限の取得<BR>
     * 　@　@　@(*) 引数.is買付の値がtrueの場合、
     *           引数.拡張累投銘柄.get買付上限金額()の戻り値を使用。<BR>
     * 　@　@　@(*) 引数.is買付の値がfalseの場合、
     *           引数.拡張累投銘柄.get解約上限金額()の戻り値を使用。<BR>
     * 　@－取引金額上限　@＜　@引数.売買金額の場合、例外をスローする。<BR>
     * 　@　@　@（取引金額上限エラー）<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00252<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ２）下限チェックを行う<BR>
     * 　@－取引金額下限の取得<BR>
     * 　@　@　@(*) 引数.is買付の値がtrueの場合、<BR>
     *          引数.拡張累投銘柄.get買付下限金額()の戻り値を使用。<BR>
     * 　@　@　@(*) 引数.is買付の値がfalseの場合、<BR>
     *          引数.拡張累投銘柄.get解約下限金額()の戻り値を使用。<BR>
     * 　@－取引金額下限　@＞　@引数.売買金額の場合、例外をスローする。<BR>
     * 　@　@　@（取引金額下限エラー）<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00253<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * @@param l_tradedPrice - 入力された売買金額<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@param isBuy - 買付の場合はtrueを、解約の場合はfalseを指定する。<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406BC0030192
     */
    public void validateTradedPrice(
        double l_dbTradedPrice,
        WEB3RuitoProduct l_ruitoProduct,
        boolean isBuy)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateTradedPrice(double l_tradedPrice, " +
            "WEB3RuitoProduct l_ruitoProduct, boolean isBuy)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //１）上限チェックを行う
        double l_dblMaxPrice;
        if (isBuy)
        {
            l_dblMaxPrice = l_ruitoProduct.getBuyMaxPrice();
        }
        else
        {
            l_dblMaxPrice = l_ruitoProduct.getSellMaxPrice();
        }
        if (l_dblMaxPrice < l_dbTradedPrice)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00252,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //２）下限チェックを行う
        double l_dblMinPrice;
        if (isBuy)
        {
            l_dblMinPrice = l_ruitoProduct.getBuyMinPrice();
        }
        else
        {
            l_dblMinPrice = l_ruitoProduct.getSellMinPrice();
        }
        if (l_dblMinPrice > l_dbTradedPrice)
        {
            log.debug("取引金額下限　@＞　@引数.売買金額の場合");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00253,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 同一注文日の売買が既に注文されていないかチェックし、<BR>
     *     既注文があった場合、例外をスローする。<BR>
     * <BR>
     * １）　@発注日の取得<BR>
     * 　@－取引時間管理.get発注日()をコールして、発注日を取得する。<BR>
     * 　@－取得した発注日をyyyyMMdd形式の文字列に変換する。<BR>
     * <BR>
     * ２）　@引数.売買区分の値が”1：買付”の場合<BR>
     * 　@以下の条件で累投注文単位テーブルを検索し、<BR>
     *     該当するレコードがあった場合は例外をスローする。<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00254<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 　@　@　@銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 　@　@　@注文種別 = OrderTypeEnum.RUITO_SELL AND<BR>
     * 　@　@　@(注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 　@　@　@　@注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 　@　@　@発注日 = 取得した発注日（yyyyMMdd形式） AND<BR>
     * 　@　@　@累投タイプ = 引数.累投タイプ<BR>
     * <BR>
     * ３）　@引数.売買区分の値が”3：一部解約”の場合<BR>
     * 　@－以下の条件で累投注文単位テーブルを検索し、<BR>
     *        該当するレコードがあった場合は例外をスローする。<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00254<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 　@　@　@銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 　@　@　@注文種別 = OrderTypeEnum.RUITO_BUY AND<BR>
     * 　@　@　@(注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 　@　@　@　@注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 　@　@　@発注日 = 取得した発注日（yyyyMMdd形式） AND<BR>
     * 　@　@　@累投タイプ = 引数.累投タイプ<BR>
     * <BR>
     * 　@－以下の条件で累投注文単位テーブルを検索し、<BR>
     *         該当するレコードがあった場合は例外をスローする。<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00254<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 　@　@　@銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 　@　@　@注文種別 = OrderTypeEnum.RUITO_SELL AND<BR>
     * 　@　@　@(注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 　@　@　@　@注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 　@　@　@発注日 = 取得した発注日（yyyyMMdd形式） AND<BR>
     * 　@　@　@累投タイプ = 引数.累投タイプ AND<BR>
     * 　@　@　@累投解約区分 = ”2：全部”<BR>
     * <BR>
     * ４）　@引数.売買区分の値が”2：全部解約”の場合<BR>
     * 　@－以下の条件で累投注文単位テーブルを検索し、<BR>
     *      該当するレコードがあった場合は例外をスローする。<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00254<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 　@　@　@銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 　@　@　@注文種別 = OrderTypeEnum.RUITO_BUY AND<BR>
     * 　@　@　@(注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 　@　@　@　@注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 　@　@　@発注日 = 取得した発注日（yyyyMMdd形式） AND<BR>
     * 　@　@　@累投タイプ = 引数.累投タイプ<BR>
     * <BR>
     * 　@－以下の条件で累投注文単位テーブルを検索し、<BR>
     *      該当するレコードがあった場合は例外をスローする。<BR>
     *  　@　@エラータグ：BUSINESS_ERROR_00254<BR>
     *  　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 　@　@　@銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 　@　@　@注文種別 = OrderTypeEnum.RUITO_SELL AND<BR>
     * 　@　@　@(注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 　@　@　@　@注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 　@　@　@発注日 = 取得した発注日（yyyyMMdd形式） AND<BR>
     * 　@　@　@累投タイプ = 引数.累投タイプ<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_strTradedDiv - 売買区分<BR>
     * <BR>
     * 1：買付<BR>
     * 2：全部解約<BR>
     * 3：一部解約<BR>
     * <BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 406BD19D00E6
     */
    public void validateSameOrderDateTrade(
        SubAccount l_subAccount,
        String l_strTradedDiv,
        WEB3RuitoProduct l_ruitoProduct)
        throws OrderValidationException, WEB3BaseException    
    {

        String STR_METHOD_NAME =
            "validateTradedPrice(double l_tradedPrice, " +
            "WEB3RuitoProduct l_ruitoProduct, boolean isBuy)";
        log.entering(STR_METHOD_NAME);
            
        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }
        try
        {             
            //１）　@発注日の取得
            String l_strBizDate = WEB3DateUtility.formatDate(
                    WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd");
            RuitoTypeEnum l_ruitoType = l_ruitoProduct.getRuitoType();     
            
            String l_strWhereClause = "";
            
            // ２）　@引数.売買区分の値が”1：買付”の場合
            if (WEB3TradedDivDef.BUY.equals(l_strTradedDiv))
            {
                //引数.売買区分の値が”1：買付”の場合                
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND "
                        + "product_id = ? AND order_type = ? AND "
                        + "(order_status = ? OR order_status = ?) AND "
                        + "biz_date = ? AND ruito_type = ?";
                log.debug("SQLWhere1 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_SELL.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);

                Object l_bindVars1[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_SELL,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType};

                //DataNetworkException
                List l_lisRows =                    
                    Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_bindVars1);
                    
                if (l_lisRows.size() != 0)
                {
                    log.debug("同一注文日の売買が既に注文されています");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "同一注文日の売買が既に注文されています");
                }
            }
            //３）　@引数.売買区分の値が”3：一部解約”の場合
            else if (WEB3TradedDivDef.PARTIALLY_SELL.equals(l_strTradedDiv))
            {
                //［検索条件］1
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND "
                        + "product_id = ? AND order_type = ? AND "
                        + "(order_status = ? OR order_status = ?) AND "
                        + "biz_date = ? AND ruito_type = ?";
                        
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_BUY.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);
                    
                Object l_bindVars2[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_BUY,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType};

                List l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars2);
                
                if (l_lisRows.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME, 
                        "同一注文日の売買が既に注文されています");
                }
                //［検索条件］2
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND " +
                         "product_id = ? AND order_type = ? AND " +
                         "(order_status = ? OR order_status = ?) AND " +
                         "biz_date = ? AND ruito_type = ? AND " +
                         "gp_sell_div = ?";
                         
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_SELL.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType +
                    " AND gp_sell_div = " + WEB3SellDivDef.ALL_DESIGNATE);
                    
                l_lisRows = null;
                Object l_bindVars3[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_SELL,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType,
                        WEB3SellDivDef.ALL_DESIGNATE };

                l_lisRows =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars3);
                if (l_lisRows.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同一注文日の売買が既に注文されています");
                }
            }
            //４）　@引数.売買区分の値が”2：全部解約”の場合
            else if (WEB3TradedDivDef.ALL_SELL.equals(l_strTradedDiv))
            {
                //［検索条件］1
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND"
                        + " product_id = ? AND order_type = ? AND"
                        + " (order_status = ? OR order_status = ?) AND"
                        + " biz_date = ? AND ruito_type = ?";
               
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_BUY.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);
                    
                Object l_bindVars2[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_BUY,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType};

                List l_lisRowsAllSell1 =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars2);
                if (l_lisRowsAllSell1.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同一注文日の売買が既に注文されています");
                }
                //［検索条件］2
                l_strWhereClause =
                    "account_id = ? AND sub_account_id = ? AND"
                        + " product_id = ? AND order_type = ? AND"
                        + " (order_status = ? OR order_status = ?) AND"
                        + " biz_date = ? AND ruito_type = ?"; 
                        
                        
                log.debug("SQLWhere2 = account_id = " + l_subAccount.getAccountId() + 
                    " AND sub_account_id = " + l_subAccount.getSubAccountId() + 
                    " AND product_id = " + l_ruitoProduct.getProductId() + 
                    " AND order_type = " + OrderTypeEnum.RUITO_SELL.intValue() + 
                    " AND (order_status = " + OrderStatusEnum.ACCEPTED.intValue() +
                    " OR order_status = " + OrderStatusEnum.ORDERED + 
                    " AND biz_date = " + l_strBizDate + 
                    " AND ruito_type = " + l_ruitoType);
                    
                List l_lisRowsAllSell2 = null;
                Object l_bindVars3[] =
                    {
                        new Long(l_subAccount.getAccountId()),
                        new Long(l_subAccount.getSubAccountId()),
                        new Long(l_ruitoProduct.getProductId()),
                        OrderTypeEnum.RUITO_SELL,
                        OrderStatusEnum.ACCEPTED,
                        OrderStatusEnum.ORDERED,
                        l_strBizDate,
                        l_ruitoType };                      

                l_lisRowsAllSell2 =
                    Processors.getDefaultProcessor().doFindAllQuery(
                        RuitoOrderUnitRow.TYPE,
                        l_strWhereClause,
                        l_bindVars3);
                if (l_lisRowsAllSell2.size() != 0)
                {
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00254,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同一注文日の売買が既に注文されています");
                }
            }
        }
        catch (DataQueryException l_ex)
        {
            log.error("__DataQueryException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("__DataNetworkException__");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 累積投資口座の開設状況をチェックするために、<BR>
     *      指定された銘柄の残高ファ@イルにレコードがあるかチェックする。<BR>
     * <BR>
     * １）　@保有資産オブジェクトを取得する。<BR>
     * 　@－拡張累投ポジションマネージャを取得する。<BR>
     * 　@－拡張累投ポジションマネージャ.getAsset()をコールする。<BR>
     * 　@　@［.getAssetに渡すパラメータ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@銘柄： 引数.拡張累投銘柄<BR>
     * 　@－getAsset()が例外をスローした場合は、例外をスローする。<BR>
     * エラータグ：BUSINESS_ERROR_00204<BR>
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407E5DDA03A9
     */
    public void validateRuitoAccount(SubAccount l_subAccount, 
        WEB3RuitoProduct l_ruitoProduct)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateRuitoAccount(" +
            "SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3RuitoPositionManager l_web3RuitoPositionManager =
                (WEB3RuitoPositionManager) l_finApp.getTradingModule(
                    ProductTypeEnum.RUITO).getPositionManager();

            log.debug("l_subAccount.getSubAccountId() = " + l_subAccount.getSubAccountId());
            log.debug("l_ruitoProduct.getProductId() = " + l_ruitoProduct.getProductId());
            
            l_web3RuitoPositionManager.getAsset(l_subAccount, l_ruitoProduct);

        }
        catch (NotFoundException l_ex)
        {
            log.error("該当する保有資産が不存在");                
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 顧客の振込先銀行口座登録がされているかチェックする。<BR>
     * <BR>
     * 引数.補助口座.getMainAccount().getDataSourseObject().getBankAccountRegi()<BR>
     *   の戻り値が”0：未登録”の場合は、例外をスローする。<BR> 
     *  （振込先銀行口座エラー）<BR>
     * エラータグ：BUSINESS_ERROR_00366<BR>
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407E86C2031C
     */
    public void validateBankTransferAccountInsert(SubAccount l_subAccount)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = "validateBankTransferAccountInsert(" +
            "SubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccount == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        MainAccount l_mainAccount = 
            l_subAccount.getMainAccount();
        MainAccountRow l_mainAccountRow = 
            (MainAccountRow) l_mainAccount.getDataSourceObject();
        String l_strBankAccountRegi = l_mainAccountRow.getBankAccountRegi();

        log.debug("補助口座 = " + l_subAccount.getAccountId());
        log.debug("振込先銀行口座 = " + l_strBankAccountRegi);

        if (WEB3BankAccountRegiDef.NOT_REGISTER.equals(l_strBankAccountRegi))
        {
            log.debug("振込先銀行口座エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00366,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "振込先銀行口座エラー");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 注文数量が解約可能残高（累投保有資産明細.解約可能残高）を<BR>
     *      超えていないかチェックする。<BR>
     * <BR>
     * １）　@解約可能残高取得<BR>
     * 　@指定銘柄の解約可能残高を算出する。<BR>
     * <BR>
     * 　@－拡張累投ポジションマネージャー.getAseetをコールして、<BR>
     *        指定銘柄の保有資産オブジェクトを取得する。<BR>
     * 　@　@［getAssetに渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@銘柄： 引数.拡張累投銘柄<BR>
     * 　@　@getAsset()が例外をスローした場合は、例外をスローする。<BR>
     * <BR>
     * 　@－累投保有資産明細オブジェクトを生成し、<BR>
     *        保有資産の情報を設定する。<BR>
     * 　@　@［累投保有資産明細オブジェクトに設定する値］<BR>
     * 　@　@　@　@銘柄ID： 保有資産オブジェクト.getProduct().<BR>
     *            getProductId()の戻り値<BR>
     * 　@　@　@　@残高： 保有資産オブジェクト.getQuantity()の戻り値<BR>
     * 　@　@　@　@30日未経過残高口数：
     *                保有資産オブジェクト.getDataSourceObject().<BR>
     *                         get30日未経過残高口数の戻り値<BR>
     * <BR>
     * 　@－累投拡張ポジションマネージャ.get累投保有資産明細()をコールし、<BR>
     * 　@　@銘柄単位の保有資産明細を取得する。<BR>
     * 　@　@［get累投保有資産明細に渡すパラメタ］<BR>
     * 　@　@　@補助口座： 引数.補助口座<BR>
     * 　@　@　@累投保有資産明細： 生成した累投保有資産明細<BR>
     *       2004-06-18 modify,the third param delete
     * 　@　@　@注文取引受付可否フラグ：<BR>
     * 　@　@　@(*) 引数.拡張累投銘柄.getRuitoType()の値が<BR>
     *               RuitoTypeEnum.中期国債ファ@ンドの場合は”<BR>
     *            2：中期国債ファ@ンド”を取得する。<BR>
     * 　@　@　@(*) 引数.拡張累投銘柄.getRuitoType()の値が<BR>
     *                  RuitoTypeEnum.MMFの場合は”<BR>
     *                3：MMF”を取得する。<BR>
     * <BR>
     * ２）　@解約可能残高算出<BR>
     * 　@－拡張累投ポジションマネージャ.calc解約可能残高()をコールし、<BR>
     *              解約可能残高を取得する。<BR>
     * 　@　@［calc解約可能残高に渡すパラメタ］<BR>
     * 　@　@　@累投保有資産明細： 生成した累投保有資産明細<BR>
     * <BR>
     * ３）　@解約可能残高チェック<BR>
     * 　@　@全部解約以外の場合、注文数量が解約可能かチェックする。<BR>
     * 　@　@（引数.指定方法@≠1）<BR>
     * <BR>
     * 　@－取得した解約可能残高　@＜　@引数.注文数量の場合は、<BR>
     *            チェックエラーとし例外をスローする。<BR>
     * （解約可能残高超過エラー）<BR>
     * エラータグ：BUSINESS_ERROR_00258<BR> 
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@param l_dblOrderQuantity - 注文数量<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 407F2F6603DC
     */
    public void validateSellPossibleBalance(
        SubAccount l_subAccount,
        WEB3RuitoProduct l_ruitoProduct,
        double l_dblOrderQuantity)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateSellPossibleBalance(" +
            "SubAccount l_subAccount,WEB3RuitoProduct l_ruitoProduct, " +
            "double l_dblOrderQuantity)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ruitoProduct == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }

        //解約可能残高取得
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        WEB3RuitoPositionManager l_web3RuitoPositionManager =
            (WEB3RuitoPositionManager) l_finApp
                .getTradingModule(ProductTypeEnum.RUITO)
                .getPositionManager();

        double l_dblSellPossibleBalance =
            l_web3RuitoPositionManager.getSellPossibleBalance(l_subAccount, l_ruitoProduct);
        
        // ２）　@解約可能残高チェック
        if (l_dblSellPossibleBalance < l_dblOrderQuantity)
        {
            log.debug("解約可能残高超過エラー");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00258,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "解約可能残高超過エラー");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 指定された注文が取り消し可能かのチェックを行う。<BR>
     * <BR>
     * １）　@累投注文単位.発注日と現在の発注日が等しいかチェックする。<BR>
     * <BR>
     * 　@－引数.累投注文単位.getDataSourceObject()をコールし、<BR>
     *         累投注文単位Paramsオブジェクトを取得する。<BR>
     * 　@－累投注文単位Params.getBizDate()をコールし、<BR>
     *          累投注文単位の発注日を取得する。<BR>
     * 　@－取引時間管理.get発注日()をコールし、現在の発注日を取得する。<BR>
     * 　@－累投注文単位の発注日と現在の発注日が異なる場合は<BR>
     *          例外をスローする。<BR>
     * エラータグ：BUSINESS_ERROR_00259<BR>
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ２）　@トリガ発行可能な時間帯の場合、指定された注文の注文状態が”<BR>
     *           3：発注済（新規注文）”になっているかチェックする。<BR>
     * 　@－取引時間管理.isトリガ発行()をコールし、<BR>
     *         戻り値がtrueの場合は以降の処理を行う。<BR>
     * 　@　@［isトリガ発行に渡すパラメタ］<BR>
     * 　@　@　@発注条件： ”0：DEFAULT”<BR>
     * 　@－累投注文単位Params.getOrderStatus()の戻り値が<BR>
     *          OrderStatusEnum.発注済（新規注文）でない場合、<BR>
     *         例外をスローする。<BR>
     * エラータグ：BUSINESS_ERROR_00260<BR>
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ３）　@指定された注文が既に取り消されていないかチェックする。<BR>
     * 　@－累投注文単位Params.getOrderStatus()の戻り値が<BR>
     *        以下の値の何れかと等しい場合、<BR>
     *          例外をスローする。<BR>
     * 　@　@　@OrderStatusEnum.受付済（取消注文）<BR>
     * 　@　@　@OrderStatusEnum.発注済（取消注文）<BR>
     *      OrderStatusEnum.発注失敗（取消注文）<BR>
     * エラータグ：BUSINESS_ERROR_00261<BR>
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ４）　@指定された注文の注文状態が”6：発注失敗（新規注文）”の場合、例外をスローする。<BR>
     * @@param l_ruitoOrderUnit - 累投注文単位<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40834ABA0172
     */
    public void validateCancelPossible(RuitoOrderUnit l_ruitoOrderUnit)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME = 
            "validateCancelPossible(RuitoOrderUnit l_ruitoOrderUnit)";
        log.entering(STR_METHOD_NAME);

        if (l_ruitoOrderUnit == null)
        {
            log.debug(" パラメータ値がNULL ");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        //1）累投注文単位.発注日と現在の発注日が等しいかチェックする。
        RuitoOrderUnitParams l_ruitoOrderUnitParams =
            ((RuitoOrderUnitParams) l_ruitoOrderUnit.getDataSourceObject());
        //累投注文単位の発注日を取得する
        String l_strRuitoBizDate = l_ruitoOrderUnitParams.getBizDate();
        //現在の発注日を取得する
        
        String l_strNowBizDate = WEB3DateUtility.formatDate(
            WEB3GentradeTradingTimeManagement.getOrderBizDate(), "yyyyMMdd");

        log.debug("累投注文単位の発注日: " + l_strRuitoBizDate);
        log.debug("現在の発注日: " + l_strNowBizDate);
        
        if (!l_strRuitoBizDate.equals(l_strNowBizDate))
        {
            log.debug("累投注文単位の発注日 != 現在の発注日");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00259,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "累投注文単位の発注日と現在の発注日が異なる場合");
        }
        
		//2）SONARからの注文については取消不可とする。 
		//  累投注文単位Params.get注文経路区分()の戻り値が「HOST」の場合は例外をスローする。
		if(WEB3OrderRootDivDef.HOST.equals(l_ruitoOrderUnitParams.getOrderRootDiv()))
		{
			log.debug("SONARから入力された注文の場合、例外をスローする");
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00155,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				"SONARから入力された注文");
		}
		
        //3）トリガ発行可能な時間帯の場合
        boolean l_blnIsSubmit = 
            WEB3GentradeTradingTimeManagement.isSubmitMarketTrigger(
            WEB3OrderingConditionDef.DEFAULT);
        log.debug("l_blnIsSubmit = " + l_blnIsSubmit);
        if (l_blnIsSubmit)
        {
            log.debug("isSubmitMarketTrigger = true");
            log.debug("OrderStatus = " + l_ruitoOrderUnitParams.getOrderStatus());
            if (!OrderStatusEnum.ORDERED.equals(
                l_ruitoOrderUnitParams.getOrderStatus()))
            {
                log.debug("OrderStatus != 3");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00260,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "累投注文単位Params.getOrderStatus()の戻り値が" +
                    "OrderStatusEnum.発注済（新規注文）でない場合");
            }
        }
        //4）指定された注文が既に取り消されていないかチェックする。
        if (OrderStatusEnum.CANCEL_ACCEPTED.equals(l_ruitoOrderUnitParams.getOrderStatus())
            || OrderStatusEnum.CANCELLED.equals(l_ruitoOrderUnitParams.getOrderStatus())
            || OrderStatusEnum.NOT_CANCELLED.equals(l_ruitoOrderUnitParams.getOrderStatus()))
        {
            log.debug("OrderStatus = 12 || OrderStatus = 14 || OrderStatus = 15");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00261,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "指定された注文が既に取り消されています");
        }
        if (OrderStatusEnum.NOT_ORDERED.equals(l_ruitoOrderUnitParams.getOrderStatus()))
            {
                log.debug("OrderStatus = 6");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00261,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "指定された注文が既に取り消されています");
        }
        log.exiting(STR_METHOD_NAME);

    }

    /**
     * 全部解約が指定されている場合、<BR>
     * 既に累投解約注文がだされていないかチェックし、<BR>
     * 既注文を検出したら例外をスローする。<BR>
     * <BR>
     * １）解約の注文（保有資産テーブルの数量に計上されていない注文）<BR>
     *     があるか、累投注文単位テーブルを検索し、<BR>
     *     該当するレコードがあった場合は例外をスローする。<BR>
     *     （解約注文済エラー）<BR>
     * エラータグ：BUSINESS_ERROR_00262<BR>
     * スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * 　@　@［検索条件］<BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 　@　@　@補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 　@　@　@銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 　@　@　@注文種別 = OrderTypeEnum.RUITO_SELL AND<BR>
     * 　@　@　@(注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 　@　@　@　@注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 　@　@　@残高計上フラグ = 0  AND<BR>
     * 　@　@　@累投タイプ = 引数.拡張累投銘柄.getRuitoType()<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A0397E02DC
     */
    public void validateAllSell(
        SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateAllSell(SubAccount l_subAccount, WEB3RuitoProduct l_ruitoProduct)";
        log.entering(STR_METHOD_NAME);
    
        if (l_subAccount == null || l_ruitoProduct == null)
        {
            log.error("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータ値がNULL");
        }

        String l_strWhereClause =
            "account_id = ? AND sub_account_id = ? AND "
                + "product_id = ? AND order_type = ? AND "
                + "(order_status = ? OR order_status = ?) AND "
                + "balance_add_flag = ? AND ruito_type = ?";
                
        long l_lngAccountId = l_subAccount.getAccountId();
        long l_lngSubAccountId = l_subAccount.getSubAccountId();
        long l_lngProdcutId = l_ruitoProduct.getProductId();
        RuitoTypeEnum l_ruitoType = l_ruitoProduct.getRuitoType();
        
        //===================
        log.debug("SQLWhere = " + 
            "account_id = 266255550025 AND sub_account_id = 26625555002501 AND "
            + "product_id = 9001999999999 AND order_type = 502 AND "
            + "(order_status = 1 OR order_status = 2) AND "
            + "balance_add_flag = 0 AND ruito_type = 1");
        //====================
        
        log.debug("SQLWhere = "
            + "account_id = " + l_lngAccountId + " AND sub_account_id = " + l_lngSubAccountId  
            + " AND product_id = " + l_lngProdcutId + " AND order_type = " + OrderTypeEnum.RUITO_SELL  
            + " AND (order_status = " + OrderStatusEnum.ACCEPTED + "OR order_status = " 
            + OrderStatusEnum.ORDERED + ") AND balance_add_flag = 0 "
            + "AND ruito_type = " + l_ruitoType);
       
        try
        {
            Object l_bindVars[] =
                {
                    new Long(l_lngAccountId),
                    new Long(l_lngSubAccountId),
                    new Long(l_lngProdcutId),          
                    OrderTypeEnum.RUITO_SELL,
                    OrderStatusEnum.ACCEPTED,
                    OrderStatusEnum.ORDERED,
                    "0",
                    l_ruitoType};
            
            List l_lisRows = null;
            l_lisRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    RuitoOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);
            
            if (l_lisRows.size() != 0)
            {
                log.debug("該当するレコードがあった場合");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00262,
                    this.getClass().getName() + "." + STR_METHOD_NAME, 
                    "該当するレコードがあった場合");
            }
            log.debug("l_lisRows.size = " + l_lisRows.size());

        }
        catch (DataQueryException l_ex)
        {
            log.debug("__DataQueryException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.debug("__DataNetworkException__", l_ex);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 引数.指定方法@が引数.拡張累投銘柄で設定されている指定方法@と<BR>
     * 合致しているかのチェックを行う。<BR>
     * <BR>
     * １）　@引数.拡張累投銘柄.get指定方法@（買付）()の戻り値が”<BR>
     *           3：金額指定”の場合、<BR>
     * 　@　@引数.指定方法@の値が”4：口数”であれば例外をスローする。<BR>
     * 　@　@エラータグ：BUSINESS_ERROR_00248<BR>
     * 　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * <BR>
     * ２）　@引数.拡張累投銘柄.get指定方法@（買付）()の戻り値が”<BR>
     *        4：口数指定”の場合、<BR>
     * 　@　@引数.指定方法@の値が”3：金額”であれば例外をスローする。<BR>
     * 　@　@エラータグ：BUSINESS_ERROR_00248<BR>
     * 　@　@スローすべき例外クラス：WEB3BusinessLayerException<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * @@throws OrderValidationException, WEB3BaseException
     * @@roseuid 40C07246024E
     */
    public void validateDesignateMethod(
        WEB3RuitoProduct l_ruitoProduct,
        String l_strDesignateMethod)
        throws OrderValidationException, WEB3BaseException
    {
        String STR_METHOD_NAME =
            "validateDesignateMethod(" +
            "WEB3RuitoProduct l_ruitoProduct, String l_strDesignateMethod)";
        log.entering(STR_METHOD_NAME);
    
        if (l_ruitoProduct == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値がNULL");
        }
        log.debug("l_ruitoProduct.getProductCode()" + l_ruitoProduct.getProductCode());
        log.debug("引数.拡張累投銘柄.get指定方法@（買付） = " + 
                l_ruitoProduct.getPaymentMethodBuy());
        log.debug("引数.指定方法@ = " + l_strDesignateMethod);
        
        //１）　@引数.拡張累投銘柄.get指定方法@（買付）()の戻り値が”3：金額指定”の場合、
        //     引数.指定方法@の値が”4：口数”であれば例外をスローする。
        if (WEB3DesignateMethodDef.AMOUNT.equals(
            l_ruitoProduct.getPaymentMethodBuy()) && 
            (WEB3DesignateMethodDef.NUMBER.equals(
            l_strDesignateMethod)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00248,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "累積投資の指定方法@が合致ない");
        }
        //２）　@引数.拡張累投銘柄.get指定方法@（買付）()の戻り値が”4：口数指定”の場合、
        //     引数.指定方法@の値が”3：金額”であれば例外をスローする。 
        if (WEB3DesignateMethodDef.NUMBER.equals(
            l_ruitoProduct.getPaymentMethodBuy()) && 
            WEB3DesignateMethodDef.AMOUNT.equals(
            l_strDesignateMethod))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00248,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "累積投資の指定方法@が合致ない");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate同一日解約<BR>
     * <BR>
     * 約が指定されている場合、同一日に異なる指定方法@で<BR>
     * 累投解約注文がだされていないかチェックし、<BR>
     * 既注文を検出したら例外をスローする。<BR>
     * <BR>
     * １）解約の注文（保有資産テーブルの数量に計上されていない注文）があるか、<BR>
     *     累投注文単位テーブルを検索し、該当するレコードがあった場合は例外をスローする。<BR> 
     * <BR>
     * ［検索条件］ <BR>
     * 口座ID = 引数.補助口座.getAccountId() AND<BR>
     * 補助口座ID = 引数.補助口座.getSubAccountId() AND<BR>
     * 銘柄ID = 引数.拡張累投銘柄.getProductId()　@AND<BR>
     * 注文種別 = OrderTypeEnum.RUITO_SELL AND<BR>
     * (注文状態 = OrderStatusEnum.受付済（新規注文） OR<BR>
     * 注文状態 = OrderStatusEnum.発注済（新規注文）) AND<BR>
     * 残高計上フラグ = 0 AND<BR>
     * 累投タイプ = 引数.拡張累投銘柄.getRuitoType()　@AND<BR>
     * 注文数量タイプ != 指定方法@　@(※)<BR>
     * <BR>
     * (※)引数.指定方法@=3（金額）の場合、指定方法@は「2」で検索<BR>
     *                  4（口数）の場合、指定方法@は「1」で検索<BR>
     *<BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_ruitoProduct - 拡張累投銘柄<BR>
     * @@param l_strDesignateMethod - 指定方法@<BR>
     * @@throws WEB3BaseException
     */
    public void validateSameOrderDateSell(
    		SubAccount l_subAccount,
			WEB3RuitoProduct l_ruitoProduct,
			String l_strDesignateMethod)
			throws WEB3BaseException
	{
		String STR_METHOD_NAME ="validateSameOrderDateSell";
		log.entering(STR_METHOD_NAME);
		
		if (l_subAccount == null)
		{
			log.debug(" パラメータ値がNULL ");
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"パラメータ値がNULL");
		}
		
		QuantityTypeEnum l_QuantityTypeEnum = null;
		// 指定方法@ = 3（金額）の場合、指定方法@は「2」
		if(l_strDesignateMethod.equals(WEB3SellDivDef.MONEY_DESIGNATE))
		{
			l_QuantityTypeEnum = QuantityTypeEnum.AMOUNT;
		}
		// 指定方法@ = 4（口数）の場合、指定方法@は「1」
		else if(l_strDesignateMethod.equals(WEB3SellDivDef.COUNT_DESIGNATE))
		{
			l_QuantityTypeEnum = QuantityTypeEnum.QUANTITY;
		}
		
		// 検索条件
		String l_strWhereClause =
			"account_id = ? AND sub_account_id = ? AND "
				+ "product_id = ? AND order_type = ? AND "
				+ "(order_status = ? OR order_status = ?) AND "
				+ "balance_add_flag = ? AND ruito_type = ? AND "
				+ "quantity_type != ?";
                        
		log.debug("SQLWhere = account_id = " + l_subAccount.getAccountId() + 
			" AND sub_account_id = " + l_subAccount.getSubAccountId() + 
			" AND product_id = " + l_ruitoProduct.getProductId() + 
			" AND order_type = " + OrderTypeEnum.RUITO_SELL + 
			" AND (order_status = " + OrderStatusEnum.ACCEPTED +
			" OR order_status = " + OrderStatusEnum.ORDERED +")"+ 
			" AND balance_add_flag = " + "0" + 
			" AND ruito_type = " + l_ruitoProduct.getRuitoType()+
			" AND quantity_type != "+ l_QuantityTypeEnum);
                    
		Object l_bindVars[] =
			{
				new Long(l_subAccount.getAccountId()),
				new Long(l_subAccount.getSubAccountId()),
				new Long(l_ruitoProduct.getProductId()),
				OrderTypeEnum.RUITO_SELL,
				OrderStatusEnum.ACCEPTED,
				OrderStatusEnum.ORDERED,
				"0",
				l_ruitoProduct.getRuitoType(),
				new Long(l_QuantityTypeEnum.intValue())};
		
		// 累投注文単位テーブルを検索		
		List l_lisRows = null;
		try
		{
			l_lisRows =
				Processors.getDefaultProcessor().doFindAllQuery(
					RuitoOrderUnitRow.TYPE,
					l_strWhereClause,
					l_bindVars);
		}
		catch (DataQueryException l_ex)
		{
			log.debug("__DataQueryException__", l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
		catch(DataNetworkException l_ex)
		{
			log.debug("__DataNetworkException__", l_ex);
			throw new WEB3SystemLayerException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80003,
				this.getClass().getName() + "." + STR_METHOD_NAME,
				l_ex.getMessage(),
				l_ex);
		}
         
		// 該当するレコードがあった場合は例外をスローする。      
		if (l_lisRows.size() != 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_02010,
				this.getClass().getName() + "." + STR_METHOD_NAME, 
				"同一発注日に口数指定解約と金額指定解約の両方の注文はできません。");
		}
		log.exiting(STR_METHOD_NAME);
	}

    public static void register()
    {
        WEB3RuitoOrderManagerReusableValidationsCheck.setInstance(
            new WEB3RuitoOrderManagerReusableValidationsCheck());
    }

}
@
