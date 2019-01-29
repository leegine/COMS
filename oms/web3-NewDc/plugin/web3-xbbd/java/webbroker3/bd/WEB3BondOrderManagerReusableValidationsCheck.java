head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondOrderManagerReusableValidationsCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券発注審査個別チェック(WEB3BondOrderManagerReusableValidationsCheck.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 徐大方 (中訊) 新規作成
                 : 2006/10/12 齊珂   (中訊) WEBⅢ開発標準の見直しの対応（newBigDecimal部分）
Revesion History : 2007/7/25 武波 (中訊) 仕様変更・モデルNo.223
Revesion History : 2007/7/26 劉立峰 (中訊) 仕様変更・モデルNo.232
*/

package webbroker3.bd;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.stdimpls.BondProductTypeOrderManagerReusableValidations;

import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondBranchRecruitLimitBranchCodeDef;
import webbroker3.common.define.WEB3BondOrderExecStatusDef;
import webbroker3.common.define.WEB3BranchRecruitLimitDivDef;
import webbroker3.common.define.WEB3LockStatusDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券発注審査個別チェック)<BR>
 * 債券発注審査個別チェッククラス<BR>
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0 
 */
public class WEB3BondOrderManagerReusableValidationsCheck extends BondProductTypeOrderManagerReusableValidations
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondOrderManagerReusableValidationsCheck.class);       

    /**
     * @@roseuid 44E336220119
     */
    public WEB3BondOrderManagerReusableValidationsCheck() 
    {
     
    }
    
    /**
     * スーパークラスに自身のインスタンスを登録する。 <BR>
     * <BR>
     * （プラグイン初期化時にコールされる） <BR>
     * <BR>
     * --- <BR>
     * super.setInstance(this); <BR>
     * --- <BR>
     * @@roseuid 4280923D020C
     */
    public static void register() 
    {
        //債券発注審査個別チェック.setInstance()をコールする
        WEB3BondOrderManagerReusableValidationsCheck.setInstance(
            new WEB3BondOrderManagerReusableValidationsCheck());
    }
    
    /**
     * (validate売却可能数量)<BR>
     * validate売却可能数量<BR>
     * <BR>
     * １）債券ポジションマネージャ.get保有資産(口座ID, 補助口座ID, 銘柄ID ,税区分)<BR>
     * 　@より保有資産を取得する。<BR>
     * 　@[引数] <BR>
     * 　@　@口座ID　@　@　@：引数.補助口座.get口座ID <BR>
     * 　@　@補助口座ID　@：引数.補助口座.get補助口座ID <BR>
     * 　@　@銘柄ID　@　@　@：引数.債券銘柄.get銘柄ID <BR>
     * 　@　@税区分　@　@　@：引数.拡張債券新規注文内容.get税区分<BR> 
     * <BR>
     * ２）売却可能数量＝保有資産.get数量－保有資産.getロック中数量<BR>
     * <BR>
     * ３）拡張債券新規注文内容.get数量()＜＝売却可能数量であることをチェックする。<BR>
     * 　@上記以外の場合は、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_01803<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_bondNewOrderSpec - (拡張債券新規注文内容)<BR>
     * 拡張債券新規注文内容<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CC8096008C
     */
    public void validateTransferedPossibleQuantity(
        SubAccount l_subAccount, 
        WEB3BondNewOrderSpec l_bondNewOrderSpec,
        WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            "validateTransferedPossibleQuantity(SubAccount, WEB3BondNewOrderSpec, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
       
        if (l_bondNewOrderSpec == null || l_bondProduct == null || l_subAccount == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }
        
        //１）債券ポジションマネージャ.get保有資産(口座ID, 補助口座ID, 銘柄ID ,税区分)
        //より保有資産を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondPositionManager l_bondPositionManager =
            (WEB3BondPositionManager)l_tradingModule.getPositionManager();
        Asset l_asset = l_bondPositionManager.getAsset(
            l_subAccount.getAccountId(),
            l_subAccount.getSubAccountId(), 
            l_bondProduct.getProductId(), 
            l_bondNewOrderSpec.getTaxType());
        
        //２）売却可能数量＝get保有資産.数量－保有資産.getロック中数量
        double l_dblQuantity = 0;
        if (l_asset != null)
        {    
            l_dblQuantity = 
                new BigDecimal(String.valueOf(l_asset.getQuantity())).subtract(
                    new BigDecimal(String.valueOf(l_asset.getLockedQuantity()))).doubleValue();
        }
        
        //３）拡張債券新規注文内容.get数量()＜＝売却可能数量であることをチェックする。
        if (new BigDecimal(String.valueOf(l_bondNewOrderSpec.getQuantity())).compareTo(
        	new BigDecimal(String.valueOf(l_dblQuantity))) == 1)
        {
            log.debug("残高不足エラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01803,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "残高不足エラー。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate決済区分)<BR>
     * 指定した決済区分の決済が可能であるかチェックする。<BR>
     * <BR>
     * １）引数.決済区分 == 外貨 の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@引数.債券銘柄.is外貨建()の戻り値 == false　@の場合、<BR>
     * 　@例外をスローする。<BR>
     * 　@※「指定の決済方法@では取引できません」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02095<BR>
     * @@param l_strSettlementDiv - (決済区分)<BR>
     * 決済区分<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2E4140195
     */
    public void validateSettlementDiv(String l_strSettlementDiv, WEB3BondProduct l_bondProduct)
        throws WEB3BaseException 
          
    {
        final String STR_METHOD_NAME = "validateSettlementDiv(String, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }
        
        //１）引数.決済区分 == 外貨 の場合、以下のチェックを行なう。
        if (WEB3SettlementDivDef.FOREIGN_CURRENCY.equals(l_strSettlementDiv))
        {
            if (!l_bondProduct.isForeignCurrency())
            {
                log.debug("外貨決済不可能エラー。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02095,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "外貨決済不可能エラー。");
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate数量)<BR>
     * 債券注文数量をvalidateチェック<BR>
     * <BR>
     * １）引数.数量　@＞＝　@引数.債券銘柄.最低額面であること。<BR>
     * 　@上記以外の場合は、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02541<BR>
     * <BR>
     * ２）引数.数量　@＜＝　@引数.債券銘柄.最高額面であること。。(*)<BR>
     * 　@上記以外の場合は、例外をスローする。<BR>
     * 　@(*)引数.債券銘柄.最高額面がnullでない場合のみチェックする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02542<BR>
     * <BR>
     * ３）引数.数量が引数.債券銘柄.get申込単位の整数倍であること。<BR>
     * 　@上記以外の場合は、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02543<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2F5DA0109
     */
    public void validateQuantity(double l_dblOrderQuantity, WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = "validateQuantity(double, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }
        
        //１）引数.数量　@＞＝　@引数.債券銘柄.最低額面であること。
        //上記以外の場合は、例外をスローする。
        if (new BigDecimal(String.valueOf(l_dblOrderQuantity)).compareTo(
            new BigDecimal(String.valueOf(l_bondProduct.getMinFaceAmount()))) == -1)
        {
            log.debug("額面金額が最低額面より小さいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02541,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が最低額面より小さいです。");
        }
        
        //２）引数.数量　@＜＝　@引数.債券銘柄.最高額面であること。
        //上記以外の場合は、例外をスローする。
        //(*)引数.債券銘柄.最高額面がnullでない場合のみチェックする。
        BondProductRow l_bondProductRow = 
            (BondProductRow)l_bondProduct.getDataSourceObject();
        if (!l_bondProductRow.getMaxFaceAmountIsNull())
        {
            if (new BigDecimal(String.valueOf(l_dblOrderQuantity)).compareTo(
                new BigDecimal(String.valueOf(l_bondProduct.getMaxFaceAmount()))) == 1)
            {
                log.debug("額面金額が最高額面を超えています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02542,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "額面金額が最高額面を超えています。");
            }
        }
        
        //３）引数.数量が引数.債券銘柄.get申込単位の整数倍であること。
        //上記以外の場合は、例外をスローする。
        BigDecimal l_bdOrderQuantity = new BigDecimal(String.valueOf(l_dblOrderQuantity));
        BigDecimal l_bdTradeUnit = new BigDecimal(String.valueOf(l_bondProduct.getTradeUnit()));
        BigDecimal l_bdZero = new BigDecimal("0");
        if (l_bdZero.compareTo(
            l_bdOrderQuantity.subtract(
                l_bdOrderQuantity.divide(
                    l_bdTradeUnit, 0,
                    BigDecimal.ROUND_DOWN).multiply(l_bdTradeUnit))) != 0)
        {
            log.debug("額面金額が申込単位の整数倍ではありません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02543,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "額面金額が申込単位の整数倍ではありません。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate自動約定枠)<BR>
     * 注文数量が自動約定枠の範囲内であるかどうかチェックする。<BR>
     * <BR>
     * １）拡張債券注文マネージャ.get債券銘柄約定済残高をコールして、<BR>
     * 　@　@約定済残高を取得する。<BR>
     * <BR>
     * 　@　@[get債券銘柄約定済残高()に渡す引数]<BR>
     * 　@　@　@　@証券会社：　@引数.証券会社<BR>
     * 　@　@　@　@銘柄コード（WEB3)：　@引数.債券銘柄.銘柄コード(WEB3)<BR>
     * <BR>
     * ２）引数.注文数量 == 0 の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@引数.債券銘柄.自動約定枠　@-　@１）で取得した約定済残高 <= 0 の場合、<BR>
     * 　@　@例外：「すでに自動約定枠に達しています。」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02544<BR>
     * <BR>
     * ３）２）以外の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@引数.債券銘柄.自動約定枠　@-　@１）で取得した約定済残高　@-　@引数.注文数量　@＜　@0　@の場合、<BR>
     * 　@　@例外：「自動約定枠を超過しています。」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02545<BR>
     * @@param l_institution - (証券会社)<BR>
     * 証券会社<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_strOrderQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D2F77700CA
     */
    public void validateAutoExecLimit(
        Institution l_institution,
        WEB3BondProduct l_bondProduct, 
        String l_strOrderQuantity) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateAutoExecLimit(Institution, WEB3BondProduct, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }
        
        //１）拡張債券注文マネージャ.get債券銘柄約定済残高をコールして、
        //約定済残高を取得する。
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);
        WEB3BondOrderManager l_bondOrderManager =
            (WEB3BondOrderManager)l_tradingModule.getOrderManager();
        double l_dblBalance = 
            l_bondOrderManager.getBondProductExecutedBalance(l_institution, l_bondProduct.getProductCode());
        
        //２）引数.注文数量 == 0 の場合、以下のチェックを行なう。
        BigDecimal l_bdZero = new BigDecimal("0");
        if (new BigDecimal(l_strOrderQuantity).compareTo(l_bdZero) == 0)
        {
            if (new BigDecimal(String.valueOf(l_bondProduct.getAutoExecLimit())).subtract(
                new BigDecimal(String.valueOf(l_dblBalance))).doubleValue() <= 0)
            {
                log.debug("すでに自動約定枠に達しています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02544,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "すでに自動約定枠に達しています。");
            }
        }
        //３）２）以外の場合、以下のチェックを行なう。
        else
        {
            if (new BigDecimal(String.valueOf(l_bondProduct.getAutoExecLimit())).subtract(
                new BigDecimal(String.valueOf(l_dblBalance))).subtract(
                    new BigDecimal(l_strOrderQuantity)).doubleValue() < 0)
            {
                log.debug("自動約定枠を超過しています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02545,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "自動約定枠を超過しています。");
            }      
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate売却可能日)<BR>
     * 売却可能日かどうかチェックする。<BR>
     * <BR>
     * １）債券銘柄.getIssueDate()>債券約定日情報.get発注日の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02618<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D9567E00CB
     */
    public void validateSellPossibleDate(
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException   
    {
        final String STR_METHOD_NAME = "validateSellPossibleDate(WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondExecuteDateInfo == null || l_bondProduct == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }
        
        //１）債券銘柄.getIssueDate()>債券約定日情報.get発注日の場合、
        //例外をスローする。
        if (WEB3DateUtility.compare(l_bondProduct.getIssueDate(), l_bondExecuteDateInfo.getBizDate()) > 0)
        {
            log.debug("発行日前に売却することはできません。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02618,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発行日前に売却することはできません。");
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate注文取消可能状態)<BR>
     * 注文が取消可能な状態であるかチェックする。<BR>
     * <BR>
     * １）引数.債券注文単位.注文約定区分 == "約定済" 又は、"取消済"の場合、 <BR>
     * 　@　@例外「この注文は取消できません」をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00155<BR>
     * <BR>
     * ２）引数.債券注文単位.注文ロック区分 == "ロック中"の場合、<BR>
     * 　@　@例外「この注文は取消できません」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00155<BR>
     * ３）引数.債券注文単位.債券タイプ == "外国債券"の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@３－１）引数.債券注文単位.発注日 ≠ 取引時間管理.get発注日()の戻り値 の場合、<BR>
     * 　@　@例外「この注文は取消できません」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00155<BR>
     * <BR>
     * ４）引数.債券注文単位.債券タイプ ≠ "外国債券"の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@　@４－１）引数.債券注文単位.発注日 ＜ 取引時間管理.get発注日()の戻り値 の場合、<BR>
     * 　@　@　@　@　@　@例外「この注文は取消できません」をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00155<BR>
     * <BR>
     * @@param l_bondOrderUnit - (債券注文単位)<BR>
     * 債券注文単位<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44DBF46B00A1
     */
    public void validateTransferedPossibleDays(WEB3BondOrderUnit l_bondOrderUnit) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validateTransferedPossibleDays(WEB3BondOrderUnit)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondOrderUnit == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータNull出来ない。");
        }
        
        //１）引数.債券注文単位.注文約定区分 == "約定済" 又は、"取消済"の場合、 
        //例外をスローする。
        if (WEB3BondOrderExecStatusDef.EXECUTED.equals(l_bondOrderUnit.getOrderExecStatus()) || 
            WEB3BondOrderExecStatusDef.CANCELED.equals(l_bondOrderUnit.getOrderExecStatus()))
        {
            log.debug("該当注文が取消不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当注文が取消不可です。");
        }

        //２）引数.債券注文単位.注文ロック区分 == "ロック中"の場合、
        //例外をスローする。
        String l_strLockStatus = l_bondOrderUnit.getLockStatus() + "";
        if (WEB3LockStatusDef.LOCKING.equals(l_strLockStatus))
        {
            log.debug("該当注文が取消不可です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "該当注文が取消不可です。");
        }

        //３）引数.債券注文単位.債券タイプ == "外国債券"の場合、以下のチェックを行なう。
        //３－１）引数.債券注文単位.発注日 ≠ get発注日()の戻り値 の場合、
        //例外「この注文は取消できません」をスローする。
        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
        BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)l_bondOrderUnit.getDataSourceObject();
        if (BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            if (WEB3DateUtility.compareToDay(l_datBizDate,
                WEB3DateUtility.getDate(l_bondOrderUnitRow.getBizDate(), "yyyyMMdd")) != 0)
            {
                log.debug("該当注文が取消不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当注文が取消不可です。");
            }
        }

        //４）引数.債券注文単位.債券タイプ ≠ "外国債券"の場合、以下のチェックを行なう。
        //４－１）引数.債券注文単位.発注日 ＜ 取引時間管理.get発注日()の戻り値 の場合、
        //例外「この注文は取消できません」をスローする。
        if (!BondTypeEnum.FOREIGN_BOND.equals(l_bondOrderUnit.getBondType()))
        {
            if (WEB3DateUtility.compareToDay(l_datBizDate,
                WEB3DateUtility.getDate(l_bondOrderUnitRow.getBizDate(), "yyyyMMdd")) > 0)
            {
                log.debug("該当注文が取消不可です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00155,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "該当注文が取消不可です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate国内債券応募枠)<BR>
     * 注文数量が国内債券応募枠の範囲内であるかどうかチェックする。<BR>
     * <BR>
     * １）債券部店別条件オブジェクトを生成する。<BR>
     * 　@[コンストラクタへの引数]<BR>
     * 　@部店ID：引数.部店ID <BR>
     * <BR>
     * ２）債券部店別条件オブジェクト.get応募枠部店別管理区分が<BR>
     * 　@　@「1：部店別管理する」の場合<BR>
     * <BR>
     * 　@２－１）債券プロダクトマネージャ.create国内債券部店別応募枠情報メソッド<BR>
     * 　@をコールする。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@銘柄ID：引数.債券銘柄.銘柄ID<BR>
     * 　@　@　@証券会社コード：引数.債券銘柄.証券会社コード<BR>
     * 　@　@　@部店コード：引数.部店IDから取得した部店オブジェクト.部店コード<BR>
     * <BR>
     * ３）債券部店別条件オブジェクト.get応募枠部店別管理区分が<BR>
     * 　@　@「0：部店別管理しない」の場合<BR>
     * <BR>
     * 　@３－１）債券プロダクトマネージャ.create国内債券部店別応募枠情報メソッド<BR>
     * 　@をコールする。<BR>
     * 　@　@　@[引数]<BR>
     * 　@　@　@銘柄ID：引数.債券銘柄.銘柄ID<BR>
     * 　@　@　@証券会社コード：引数.債券銘柄.証券会社コード<BR>
     * 　@　@　@部店コード：｢"---":全部店｣<BR>
     * <BR>
     * ４）引数.注文数量 == 0 の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@国内債券部店別応募枠情報[0].WEB3応募枠 - <BR>
     * 　@　@国内債券部店別応募枠情報[0].注文金額合計<= 0 の場合、<BR>
     * 　@例外：「すでに応募枠に達しています。」をスローする。<BR>
     * <BR>
     * ５）４）以外の場合、以下のチェックを行なう。<BR>
     * <BR>
     * 　@国内債券部店別応募枠情報[0].WEB3応募枠 - <BR>
     * 　@　@国内債券部店別応募枠情報[0].注文金額合計 - 引数.注文数量 < 0 の場合、<BR>
     * 　@例外：「応募枠を超過しています。」をスローする。 <BR>
     * <BR>
     * @@param l_lngBranchId - (部店ID)<BR>
     * 部店ID<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_dblQuantity - (注文数量)<BR>
     * 注文数量<BR>
     * @@throws WEB3BaseException
     */
    public void validateDomesticBondRecruitLimit(
        long l_lngBranchId,
        WEB3BondProduct l_bondProduct,
        double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDomesticBondRecruitLimit(long, WEB3BondProduct, double)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）債券部店別条件オブジェクトを生成する。
        WEB3BondBranchCondition l_branchCondition = new WEB3BondBranchCondition(l_lngBranchId);

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        String l_branchCode = null;
        //２）債券部店別条件オブジェクト.get応募枠部店別管理区分が「1：部店別管理する」の場合
        if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT.equals(
            l_branchCondition.getBranchRecruitLimitDiv()))
        {
            //部店コード：引数.部店IDから取得した部店オブジェクト.部店コード
            WEB3GentradeAccountManager l_accountManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            try
            {
                l_branchCode = l_accountManager.getBranch(l_lngBranchId).getBranchCode();
            }
            catch (NotFoundException l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
        }
        //３）債券部店別条件オブジェクト.get応募枠部店別管理区分が「0：部店別管理しない」の場合
        if (WEB3BranchRecruitLimitDivDef.BRANCH_LIMIT_NOT.equals(
            l_branchCondition.getBranchRecruitLimitDiv()))
        {
            //部店コード：｢"---":全部店｣
            l_branchCode = WEB3BondBranchRecruitLimitBranchCodeDef.ALL_BRANCH;
        }

        WEB3BondDomesticBranchRecruitLimitInfo[] l_bondDomesticBranchRecruitLimitInfos = null;
        if (l_branchCode != null)
        {
            //債券プロダクトマネージャ.create国内債券部店別応募枠情報メソッドをコールする。
            //  [引数]
            //      銘柄ID：引数.債券銘柄.銘柄ID
            //      証券会社コード：引数.債券銘柄.証券会社コード
            //      部店コード：引数.部店IDから取得した部店オブジェクト.部店コード
            WEB3BondProductManager l_bondProductManager =
                (WEB3BondProductManager)l_finApp.getTradingModule(
                    ProductTypeEnum.BOND).getProductManager();
            l_bondDomesticBranchRecruitLimitInfos =
                l_bondProductManager.createAdminBondDomesticRecruitLimitInfo(
                    l_bondProduct.getProductId(),
                    l_bondProduct.getInstitution().getInstitutionCode(),
                    l_branchCode);
        }

        //４）引数.注文数量 == 0 の場合、以下のチェックを行なう。
        //国内債券部店別応募枠情報[0].WEB3応募枠 - 国内債券部店別応募枠情報[0].注文金額合計 <= 0 の場合、
        //例外：「すでに応募枠に達しています。」をスローする。
        if (l_bondDomesticBranchRecruitLimitInfos.length == 0)
        {
            log.error("テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                getClass().getName() + STR_METHOD_NAME,
                "テーブルに該当するデータがありません");
        }
        BigDecimal l_bdZero = new BigDecimal("0");
        BigDecimal l_bdQuantity = new BigDecimal(l_dblQuantity);
        BigDecimal l_bdWeb3RecruitLimit = new BigDecimal(l_bondDomesticBranchRecruitLimitInfos[0].web3RecruitLimit);
        BigDecimal l_bdOrderAmountTotal = new BigDecimal(l_bondDomesticBranchRecruitLimitInfos[0].orderAmountTotal);
        if (l_bdQuantity.compareTo(l_bdZero) == 0)
        {
            if (l_bdWeb3RecruitLimit.subtract(
                l_bdOrderAmountTotal).compareTo(l_bdZero) <= 0)
            {
                log.debug("すでに応募枠に達しています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02888,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "すでに応募枠に達しています。");
            }
        }
        //５）４）以外の場合、以下のチェックを行なう。
        //国内債券部店別応募枠情報[0].WEB3応募枠 - 国内債券部店別応募枠情報[0].注文金額合計 - 引数.注文数量 < 0 の場合、
        //例外：「応募枠を超過しています。」をスローする。
        else
        {
            if (l_bdWeb3RecruitLimit.subtract(
                l_bdOrderAmountTotal).subtract(l_bdQuantity).compareTo(l_bdZero) < 0)
            {
                log.debug("応募枠を超過しています。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02889,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "応募枠を超過しています。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
