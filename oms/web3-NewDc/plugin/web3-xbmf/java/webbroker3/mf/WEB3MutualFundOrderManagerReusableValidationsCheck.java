head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundOrderManagerReusableValidationsCheck.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託発注審査個別チェッククラス(WEB3MutualFundOrderManagerReusableValidationsCheck)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/06 周勇 (中訊) 新規作成
Revesion History : 2004/08/20 黄建 (中訊) レビュー  
Revesion History : 2004/12/06 于美麗 (中訊) 残対応 
Revesion History : 2005/10/20 韋念瓊 (中訊) フィデリティ対応
Revesion History : 2006/04/05 玉岡 (SRA) 本番障害H00120対応
Revesion History : 2006/06/19 鈴木 (SRA) 本番障害H00126対応
Revesion History : 2006/09/11 周捷 仕様変更・モデル488、495、502、507
Revesion History : 2006/10/25 徐大方 仕様変更・モデル515
Revesion History : 2006/11/07 山下（SRA）受入障害U02934
Revesion History : 2007/01/26 車進 (中訊) 仕様変更・モデル521
Revesion History : 2006/11/07 山下（SRA）受入障害U02934
Revesion History : 2007/02/05 唐性峰 (中訊) 仕様変更モデル530
Revesion History : 2008/02/13 武波 (中訊) 仕様変更モデル590,モデル592
*/
package webbroker3.mf; 

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Asset;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.stdimpls.MutualFundProductTypeOrderManagerReusableValidations;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.common.define.WEB3CommisionProductCodeDef;
import webbroker3.common.define.WEB3ExecStatusDef;
import webbroker3.common.define.WEB3MfRecruitMqSendDivDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3SessionAttributeDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.data.BranchPreferencesDao;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.InstBranchProductDao;
import webbroker3.gentrade.data.InstBranchProductRow;
import webbroker3.mf.data.MutualFundDayBalanceRow;
import webbroker3.mf.define.WEB3MFIncludeDivDef;
import webbroker3.mf.define.WEB3ProcessDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 投資信託発注審査個別チェッククラス<BR>
 * 
 * @@author 周勇(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundOrderManagerReusableValidationsCheck
    extends MutualFundProductTypeOrderManagerReusableValidations
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3MutualFundOrderManagerReusableValidationsCheck.class);
            
    /**
     * (投信発注審査個別チェック)<BR>
     * デフォルトコンストラクタ。<BR>
     * @@throws com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidationException
     * @@roseuid 40CD35430203
     */
    public WEB3MutualFundOrderManagerReusableValidationsCheck()
    {
    }
    
    /**
     * (validate緊急停止)<BR>
     * 当該銘柄の取引が緊急停止されていないかチェックする。 <BR>
     * <BR>
     * １）　@拡張投信取引銘柄を取得する。 <BR>
     * <BR>
     * 　@－拡張投信銘柄マネージャを取得する。<BR>
     * 　@－拡張投信銘柄マネージャ.get投信取引銘柄()をコールして拡張投信取引銘柄を取得する。<BR>
     * 　@　@　@［get投信取引銘柄に渡すパラメタ］<BR>
     * 　@　@　@　@証券会社： 引数.拡張投信銘柄.getInstitution()<BR>
     * 　@　@　@　@銘柄コード： 引数.拡張投信銘柄.getProductCode()<BR>
     * 　@－取得できない場合は、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00372<BR>
     * <BR>
     * ２）　@取扱可能チェック<BR>
     * 　@－引数.処理区分が”1：買付”の場合、<BR>
     * 拡張投信取引銘柄.is買付可能()の戻り値がfalseの場合例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00373<BR>
     * 　@－引数.処理区分が”2：解約”または”4：買取”の場合、<BR>
     * 拡張投信取引銘柄.is解約可能()の戻り値がfalseの場合例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00374<BR>
     * 　@－引数.処理区分が”3：乗換”の場合、<BR>
     * 拡張投信取引銘柄.is乗換可能()の戻り値がfalseの場合例外をスローする。<BR>
     *           class: WEB3BusinessLayerExceptiosn<BR>
     *           tag:   BUSINESS_ERROR_00375<BR>
     *   －引数.処理区分が”5：募集”の場合、<BR>
     * 拡張投信取引銘柄.is募集可能()の戻り値がfalseの場合例外をスローする。 <BR>
     *           class: WEB3BusinessLayerExceptiosn<BR>
     *           tag:   BUSINESS_ERROR_02239<BR>
     * 
     * @@param l_mutualFundProduct - 拡張投信銘柄<BR>
     * @@param l_strProcessDiv - (処理区分)<BR>
     * 1：買付　@2：解約　@3：乗換　@4：買取　@5：募集<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public void validateEmergencyStop(
        WEB3MutualFundProduct l_mutualFundProduct,
        String l_strProcessDiv)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateEmergencyStop(WEB3MutualFundProduct l_mutualFundProduct,String l_strProcessDiv)";
        log.entering(STR_METHOD_NAME);
        if (l_mutualFundProduct == null || l_strProcessDiv == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）　@拡張投信取引銘柄を取得する。
        //－拡張投信銘柄マネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
            ProductTypeEnum.MUTUAL_FUND).getProductManager();
        WEB3MutualFundTradedProduct l_mutualFundTradedProduct = null;
        //拡張投信取引銘柄
        try
        {
            log.debug("l_mutualFundProduct.getInstitution() = " + l_mutualFundProduct.getInstitution());
            log.debug("l_mutualFundProduct.getInstitution().getInstitutionId() = " + l_mutualFundProduct.getInstitution().getInstitutionId());
            log.debug("l_mutualFundProduct.getInstitution().getInstitutionCode() = " + l_mutualFundProduct.getInstitution().getInstitutionCode());
            log.debug("l_mutualFundProduct.getProductCode() = " + l_mutualFundProduct.getProductCode());
            //－拡張投信銘柄マネージャ.get投信取引銘柄()をコールして拡張投信取引銘柄を取得する
            l_mutualFundTradedProduct =
                (WEB3MutualFundTradedProduct) l_mutualFundProductManager.getMutualFundTradedProduct(
                    l_mutualFundProduct.getInstitution(),
                    l_mutualFundProduct.getProductCode());        
        }
        catch (NotFoundException l_ex)
        {
            log.error("no found MutualFundTradedProduct");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00372,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません");
        }
        if (l_mutualFundTradedProduct == null
            || "".equals(l_mutualFundTradedProduct))
        {
            log.debug(
                "拡張投信銘柄マネージャ.get投信取引銘柄()に該当する拡張投信取引銘柄がありません。"
                    + "with Institution Code = "
                    + l_mutualFundProduct.getInstitution().getInstitutionCode()
                    + " ProductCode = "
                    + l_mutualFundProduct.getProductCode());
            log.debug("l_mutualFundTradedProduct is null ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "テーブルに該当するデータがありません");
        }
        //２）　@取扱可能チェック
        //－引数.処理区分が”1：買付”の場合、<BR>
        // 拡張投信取引銘柄.is買付可能()の戻り値がfalseの場合例外をスローする。
        if (WEB3ProcessDivDef.BUY.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isAcquiredPossible())
            {
                log.debug("拡張投信取引銘柄.is買付可能()の戻り値がfalse");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00373,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張投信取引銘柄.is買付可能()の戻り値がfalse");
            }
        }
        //－引数.処理区分が”2：解約”または”4：買取”の場合、<BR>
        //拡張投信取引銘柄.is解約可能()の戻り値がfalseの場合例外をスローする
        else if (WEB3ProcessDivDef.SELL.equals(l_strProcessDiv) 
            || WEB3ProcessDivDef.PURCHASE.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isSellPossible())
            {
                log.debug("拡張投信取引銘柄.is解約可能()の戻り値がfalse");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00374,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張投信取引銘柄.is解約可能()の戻り値がfalse");
            }
        }
        //－引数.処理区分が”3：乗換”の場合、<BR>
        //拡張投信取引銘柄.is乗換可能()の戻り値がfalseの場合例外をスローする
        else if (WEB3ProcessDivDef.SWITCHING.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isSwitchingPossible())
            {
                log.debug("拡張投信取引銘柄.is乗換可能()の戻り値がfalse");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00375,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張投信取引銘柄.is乗換可能()の戻り値がfalse");
            }
        }
        //－引数.処理区分が”5：募集”の場合
        //拡張投信取引銘柄.is募集可能()の戻り値がfalseの場合例外をスローする。 
        else if (WEB3ProcessDivDef.RECRUIT.equals(l_strProcessDiv))
        {
            if (!l_mutualFundTradedProduct.isRecruitPossible())
            {
                log.debug("拡張投信取引銘柄.is募集可能()の戻り値がfalse");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02239,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張投信取引銘柄.is募集可能()の戻り値がfalse");
            }
        }
        else
        {
            log.debug("処理区分不是1：買付，2：解約，3：乗換, 4：買取, 5：募集");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "処理区分不是1：買付，2：解約，3：乗換, 4：買取, 5：募集");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate取消可能)<BR>
     * 指定された注文が取消可能かのチェックを行う。 <BR>
     * <BR>
     * <BR>
     * １）　@投信銘柄取得<BR>
     * 　@－拡張投信銘柄マネージャを取得する。<BR>
     * <BR>
     * 　@－拡張投信銘柄マネージャ.getProduct()をコールし、銘柄オブジェクトを取得する。 <BR>
     * 　@　@［getProductに渡すパラメタ］ <BR>
     * 　@　@　@銘柄ID： 投信注文単位Params.getProductId()の戻り値 <BR>
     * 　@<BR>
     *   －拡張投信銘柄マネージャ.to銘柄()をコールし、拡張投信銘柄を取得する。<BR> 
     * 　@　@［to銘柄に渡すパラメタ］ <BR>
     * 　@　@　@銘柄Row： 取得した銘柄オブジェクト.getDataSourceObject()の戻り値 <BR>
     * 　@　@取得できない場合は例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00377<BR>
     * <BR>
     * 　@－銘柄コードの取得<BR>
     * 　@　@拡張投信銘柄.get銘柄コードをコールし、銘柄コードを取得する。<BR>
     * <BR>
     * <BR>
     * ２）　@売買区分（投信）の取得  <BR>
     * 　@－拡張投信注文マネージャ.get売買区分（投信）( )をコールし、<BR>
     *      売買区分（投信）を取得する。 <BR>
     * 　@　@［get売買区分（投信）に渡すパラメタ］ <BR>
     * 　@　@投信注文単位：引数の投信注文単位 <BR>
     * <BR>
     * <BR>
     * ３） SONARから入力された注文は取消不可とする。<BR> 
　@   *   投信注文単位Params.get注文経路区分()の戻り値が「HOST」の場合は例外をスローする。 <BR>
     * <BR>
     * <BR>
     * ４）　@緊急停止チェック <BR>
     *  －投信発注審査個別チェック.validate緊急停止()をコールし、チェックエラーの場合は、<BR>
     *  例外をスローする。（緊急停止エラー）。 <BR>
     * 　@［validate緊急停止に渡すパラメタ］<BR>
     * 　@　@　@拡張投信銘柄： １）で取得した拡張投信銘柄 <BR>
     * 　@　@　@処理区分： ２）で取得した売買区分（投信） <BR>
     * <BR>
     * <BR>
     * ５）　@取引停止時間チェック<BR>
     * 　@現在の時刻が当該銘柄の受付時間区分タイプの取引時間に該当するか、<BR>
     * 　@また緊急停止されていないか、あるいはバッチ処理中でないかチェックする。<BR>
     * <BR>
     * 　@－投信取引時間管理.validate注文受付可能()をコールし、チェックエラーの場合は例外を<BR>
     *      スローする。（取引停止時間エラー）<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00365<BR>
     * <BR>
     * <BR>
     * ６)　@発注日チェック<BR>
     * 　@投信注文単位.発注日と現在の発注日が等しいかチェックする。 <BR>
     * 　@６－1)　@投信注文単位Params.getBizDate()をコールし、投信注文単位の<BR>
     *           発注日を取得する。 <BR>
     * <BR>
     * ６－２）　@２）で取得した売買区分（投信）が乗換の場合<BR>
     * <BR>
     * ６－２－１)　@投信取引時間管理.get乗換発注日()をコールし、現在の乗換発注日を取得する。<BR>
     *     　@　@　@　@　@[引数]<BR>
     *                乗換元銘柄コード：　@１）で取得した銘柄コード<BR>
     *                乗換先銘柄コード：　@投信注文単位.銘柄コード（乗換先）<BR>
     * <BR>
     * ６－２－２)　@投信注文単位の発注日と現在の乗換発注日が異なる場合は例外をスローする。<BR>
     * <BR>
     *  ６－３）  ６－２）以外の場合 <BR>
     *　@　@　@　@　@　@　@「乗換以外の発注日チェック」 <BR>
     * <BR>              
     * 　@　@　@　@　@　@６－３－１） 部店プリファ@レンスから「投信募集注文一括送信区分」を取得する。<BR> 
     * 　@　@　@　@　@　@　@　@　@　@[部店プリファ@レンスの取得条件] <BR>
     * 　@　@　@　@　@　@　@　@　@　@部店ID             ＝ 投信注文単位.get部店ID <BR>
     * 　@　@　@　@　@　@　@　@　@　@プリファ@レンス名   ＝ 'mf.recruit.mq.send.div' <BR>
     * 　@　@　@　@　@　@　@　@　@　@プリファ@レンス連番 ＝ 1 <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@（*）レコードが取得できなかった場合、「投信募集注文一括送信区分」は"一括送信する"とする。<BR> 
     * <BR>
     * 　@　@　@　@　@　@６－３－２）  ①@～③のいずれかの場合、<BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@投信取引時間管理.get投信発注日(注文種別, 一括区分)をコールし、発注日を取得する。<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@[get投信発注日の引数] <BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@注文種別  ： ２）で取得した売買区分（投信） (OrderTypeEnumに変換) <BR> 
     * 　@　@　@　@　@　@　@　@　@　@　@　@一括区分 ： true <BR> 
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@①@ 「２）で取得した売買区分（投信）="募集"」 かつ<BR>　@
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@「投信募集注文一括送信区分 = "一括送信する"」<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@② 「２）で取得した売買区分（投信）="買付"」 かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@「取得した拡張投信銘柄．is特定日取引銘柄 = true」<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@③ 「２）で取得した売買区分（投信）="解約"」 かつ<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@「取得した拡張投信銘柄．is特定日取引銘柄 = true」<BR>
     * <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@※[is特定日取引銘柄のパラメタ] <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@注文種別  ： ２）で取得した売買区分（投信） (OrderTypeEnumに変換) <BR>
     * <BR>
     * 　@　@　@　@　@　@６－３－３） 　@６－３－２）  ①@～③以外の場合<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@投信取引時間管理.get投信発注日(引数なし)をコールし、発注日を取得する。<BR>                             
     * <BR>
     * 　@　@　@　@　@　@６－３－４)　@投信注文単位の発注日と発注日が異なる場合は例外をスローする。<BR> 
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     *　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@tag:   BUSINESS_ERROR_00378<BR>
     * <BR>
     * <BR>
     * ７)　@約定状態チェック<BR>
     * 　@投信注文単位の約定状態が"約定中"または"約定済"の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00379<BR>
     * <BR>
     * <BR>
     * ８)　@注文状態チェック<BR>
     * ※６－３－２）  ①@の場合は、「８)　@注文状態チェック」を行わない。<BR>
     * <BR>
     *  トリガ発行可能な時間帯の場合、指定された注文の注文状態が"発注済（新規注文)"に<BR>
     *  なっているかチェックする。<BR> 
     *　@現在日付＜注文単位の発注日の場合は"受付済（新規注文）"であることをチェックする。<BR>
     *<BR>
     *　@８－１)　@投信取引時間管理.isトリガ発行()をコールし、戻り値がtrueの場合は以降の処理を行う。 <BR>
     *　@　@　@　@[isトリガ発行に渡すパラメタ］ <BR>
     *　@　@　@　@　@発注条件: "DEFAULT"<BR> 
     *<BR>
     *　@８－２)　@現在日付＜注文単位の発注日の場合、投信注文単位Params.getOrderStatus()の戻り値が<BR>
     *　@　@　@　@OrderStatusEnum.受付済（新規注文)でない場合、例外をスローする。<BR> 
     *<BR>
     *　@８－３)　@それ以外の場合、投信注文単位Params.getOrderStatus()の戻り値が<BR>
     *　@　@　@　@OrderStatusEnum.発注済（新規注文)でない場合、例外をスローする。<BR> 
     * <BR>
     * <BR>
     * ９） 注文有効状態チェック<BR>
     * 　@注文有効状態が、"クローズ"でないかチェックする。<BR>
     * 　@投信注文単位Params.getOrderOpenStatus()の戻り値が、<BR>
     * 　@"クローズ"（OrderOpenStatusEnum.CLOSED）の場合、例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00382<BR>
     * 　@※クローズは、注文取消失敗の場合と、約定済の場合がある<BR>
     * <BR>
     *  <BR>
     * １０）　@緊急停止チェック <BR>
     * 　@２）で取得した売買区分（投信）が乗換の場合に、乗換先銘柄の緊急停止チェックを実施<BR>
     *   する。<BR>
     * <BR>
     * 　@－拡張投信銘柄マネージャ.get投信銘柄()をコールし、乗換先の銘柄オブジェクトを取得する。 <BR>
     * 　@　@［get投信銘柄に渡すパラメタ］<BR>
     * 　@　@　@証券会社：補助口座.getInstitution（）の戻り値<BR> 
     * 　@　@　@銘柄コード： 投信注文単位Params.get銘柄コード（乗換先）()の戻り値 <BR>
     * <BR>
     * 　@－投信発注審査個別チェック.validate緊急停止()をコールし、チェックエラーの場合は、<BR>
     * 　@　@ 例外をスローする。（緊急停止エラー）。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00012<BR>
     * 　@　@［validate緊急停止に渡すパラメタ］ <BR>
     * 　@　@　@拡張投信銘柄： 取得した拡張投信銘柄 <BR>
     * 　@　@　@処理区分： 買付<BR>
     *  <BR>
     * <BR>
     * １１）　@乗換先銘柄の取引停止時間チェック<BR>
     * 　@２）で取得した売買区分（投信）が乗換の場合に、乗換先銘柄の取引停止時間チェックを<BR>
     *   実施する。<BR>
     * <BR>
     * 　@－投信取引時間管理.reset銘柄コード()をコールし、銘柄コードを乗換先銘柄のものに変更<BR>
     *      する。 <BR>
     * 　@　@［reset銘柄コードに渡すパラメタ］ <BR>
     * 　@　@　@銘柄コード： 投信注文単位Params.get銘柄コード（乗換先）()の戻り値<BR> 
     * <BR>
     * 　@－受付日時、日付ロールをセットする。 <BR>
     * 　@　@　@投信取引時間管理.setTimestamp()をコールする。 <BR>
     * <BR>
     * 　@－投信取引時間管理.validete注文受付可能()をコールし、チェックエラーの場合は、例外<BR>
     *      をスローする。 （取引停止時間エラー） <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00365<BR>
     * <BR>
     * 　@－投信取引時間管理.reset銘柄コード()をコールし、銘柄コードを<BR>
     *      １）で取得した銘柄コードに変更する。<BR>
     * 　@　@［reset銘柄コードに渡すパラメタ］<BR>
     * 　@　@　@銘柄コード： １）で取得した銘柄コード<BR>
     * <BR>
     * 　@－受付日時、日付ロールをセットする。<BR>
     * 　@　@　@投信取引時間管理.setTimestamp()をコールする。<BR>
     * @@param l_subAccount - 補助口座ID<BR>
     * @@param l_mutualFundOrderUnit - 投信注文単位オブジェクト<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B572D302E8
     */
    public void validateCancelPossible(
        SubAccount l_subAccount,
        MutualFundOrderUnit l_mutualFundOrderUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateCancelPossible(SubAccount l_subAccount,MutualFundOrderUnit l_mutualFundOrderUnit)";
        log.entering(STR_METHOD_NAME);
        if (l_subAccount == null || l_mutualFundOrderUnit == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）　@投信銘柄取得
        //－拡張投信銘柄マネージャを取得する
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundProductManager l_mutualFundProductManager =
            (WEB3MutualFundProductManager) l_finApp.getTradingModule(
            ProductTypeEnum.MUTUAL_FUND).getProductManager();
        if (l_mutualFundProductManager == null
            || "".equals(l_mutualFundProductManager))
        {
            log.debug("l_mutualFundProductManager is null or empty");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //－拡張投信銘柄マネージャ.getProduct()をコールし、銘柄オブジェクトを取得する
        MutualFundOrderUnitParams l_params =
            (MutualFundOrderUnitParams) l_mutualFundOrderUnit
                .getDataSourceObject();
        if(l_params == null)
        {   
            log.debug("l_params is null");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME);     
        }
        //Product l_product = null;
        try
        {
            Product l_product =
                l_mutualFundProductManager.getProduct(l_params.getProductId());
            log.debug("l_product = " + l_product);    
            //－拡張投信銘柄マネージャ.to銘柄()をコールし、拡張投信銘柄を取得する

            Row l_row = (Row) l_product.getDataSourceObject(); 
            log.debug("l_row.getClass().getName() = " + l_row.getClass().getName());
            if (l_row == null)
            {
                log.debug("l_row == null");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00377,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "拡張投信銘柄取得できない場合エラー");
            }
            //WEB3MutualFundProduct l_mutualFundProduct = null; //拡張投信銘柄
            WEB3MutualFundProduct l_mutualFundProduct =
                (WEB3MutualFundProduct) l_mutualFundProductManager.toProduct(
                    l_row);
            //－銘柄コードの取得
            String l_strProductCode = l_mutualFundProduct.getProductCode();
            //２）　@売買区分（投信）の取得
            WEB3MutualFundOrderManager l_mutualFundOrderManager =
                (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
            
            //－拡張投信注文マネージャ.get売買区分（投信）( )をコールし、売買区分（投信）を取得する
            String l_strMutualTradeDiv =
                l_mutualFundOrderManager.getMutualTradeDiv(
                    l_mutualFundOrderUnit);
            if(l_strMutualTradeDiv == null || "".equals(l_strMutualTradeDiv ))
            {        
                log.debug("l_strMutualTradeDiv is null or empty");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME);
            }
            
            //３） SONARから入力された注文の場合
            //投信注文単位Params.get注文経路区分()の戻り値が「HOST」の場合は例外をスローする。
            if (WEB3OrderRootDivDef.HOST.equals(l_params.getOrderRootDiv()))
            {
				log.debug("SONARから入力された注文の場合、例外をスローする");
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00155,
					this.getClass().getName() + "." + STR_METHOD_NAME,
					"SONARから入力された注文");
            }   

            //４）　@緊急停止チェック
            //投信発注審査個別チェック.validate緊急停止()をコールし、チェックエラーの場合は、
            //例外をスローする。（緊急停止エラー）。
            WEB3MutualFundOrderManagerReusableValidationsCheck l_mutualFundOrderManagerReusableValidationsCheck =
                new WEB3MutualFundOrderManagerReusableValidationsCheck();
            try
            {
                log.debug("l_mutualFundProduct = " + l_mutualFundProduct);
                log.debug("l_strMutualTradeDiv = " + l_strMutualTradeDiv);
                l_mutualFundOrderManagerReusableValidationsCheck.validateEmergencyStop(
                    l_mutualFundProduct,
                    l_strMutualTradeDiv);
            }
            catch (WEB3BaseException l_ex)
            {
                log.error(
                    "信発注審査個別チェック.validate緊急停止()をコールし、チェックエラーの場合は例外をスローする");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            //５）　@取引停止時間チェック
            WEB3MutualFundTradingTimeManagement.validateOrderAccept();
            
            //６)　@発注日チェック
            //６－1)　@投信注文単位Params.getBizDate()をコールし、投信注文単位の
            //発注日を取得する。 <BR>
            String l_strBizDate = l_params.getBizDate();
            boolean l_blnCond = false;
            
            //６－２)  ２）で取得した売買区分（投信）が乗換の場合
            if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
            {
                //６－２－１)　@投信取引時間管理.get乗換発注日()をコールし、現在の乗換発注日を取得する。
                //　@          [引数]
                //             乗換元銘柄コード：　@１）で取得した銘柄コード
                //             乗換先銘柄コード：　@投信注文単位.銘柄コード（乗換先）
                Date l_SwtOrderBizDate =
                    WEB3MutualFundTradingTimeManagement.getSwtOrderBizDate(l_strProductCode, l_params.getSwtProductCode());
                //６－２－２)　@投信注文単位の発注日と現在の乗換発注日が異なる場合は例外をスローする。
                if (!GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat().format(l_SwtOrderBizDate).equals(l_strBizDate))
                {
                    log.debug("投信注文単位の発注日と現在の発注日が異なる場合は例外をスローする");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00378,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "投信注文単位の発注日と現在の発注日が異なる");
                }
            }
            // ６－３）  ６－２）以外の場合 
            //「乗換以外の発注日チェック」
            else
            {
                //６－３－１） 部店プリファ@レンスから「投信募集注文一括送信区分」を取得する。 
                //  [部店プリファ@レンスの取得条件] 
                //  部店ID             ＝ 投信注文単位.get部店ID 
                //  プリファ@レンス名   ＝ 'mf.recruit.mq.send.div' 
                //  プリファ@レンス連番 ＝ 1 
                //　@（*）レコードが取得できなかった場合、「投信募集注文一括送信区分」は"一括送信する"とする。
            	BranchPreferencesRow l_branchPreferencesRow = null;

        		try
        		{
                    l_branchPreferencesRow = 
        			    BranchPreferencesDao.findRowByBranchIdNameNameSerialNo(l_params.getBranchId(),
        			    	WEB3BranchPreferencesNameDef.MF_RECRUIT_MQ_SEND_DIV,
                            1);
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
        		
        		String l_strValue = "";
        		if (l_branchPreferencesRow != null)
        		{
        			l_strValue = l_branchPreferencesRow.getValue();
        		}
        		else
        		{
        			l_strValue = WEB3MfRecruitMqSendDivDef.DEFAULT;
        		}
                
                //６－３－２）  ①@～③のいずれかの場合、 
                //投信取引時間管理.get投信発注日(注文種別, 一括区分)をコールし、発注日を取得する。
                //　@[get投信発注日の引数]
                //　@注文種別  ： ２）で取得した売買区分（投信） (OrderTypeEnumに変換)
                //　@一括区分 ： true
                //　@※[is特定日取引銘柄のパラメタ] 
                //  注文種別  ： ２）で取得した売買区分（投信） (OrderTypeEnumに変換) 
                Date l_datOrderBizDate = null;
                //　@①@ 「２）で取得した売買区分（投信）="募集"」 かつ　@「投信募集注文一括送信区分 = "一括送信する"」
                if (WEB3ProcessDivDef.RECRUIT.equals(l_strMutualTradeDiv) && 
                    WEB3MfRecruitMqSendDivDef.DEFAULT.equals(l_strValue))
                {
                    l_datOrderBizDate = 
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(OrderTypeEnum.MF_RECRUIT, true);
                    l_blnCond = true;
                }
                //　@② 「２）で取得した売買区分（投信）="買付"」 かつ　@「取得した拡張投信銘柄．is特定日取引銘柄 = true」
                else if (WEB3ProcessDivDef.BUY.equals(l_strMutualTradeDiv) && 
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_BUY))
                {
                    l_datOrderBizDate =
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(OrderTypeEnum.MF_BUY, true);
                }
                //　@③ 「２）で取得した売買区分（投信）="解約"」 かつ　@「取得した拡張投信銘柄．is特定日取引銘柄 = true」
                else if (WEB3ProcessDivDef.SELL.equals(l_strMutualTradeDiv) && 
                    l_mutualFundProduct.isUnitTypeProduct(OrderTypeEnum.MF_SELL))
                {
                    l_datOrderBizDate = 
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate(OrderTypeEnum.MF_SELL, true);
                        
                    //特定日取引銘柄の場合の発注日取得 
                    long l_lngUnitProductId = l_mutualFundProduct.getProductId();
                    if (l_lngUnitProductId == 3303910181800L || l_lngUnitProductId == 3303911181800L)
                    {
                        MutualFundProductRow l_mfProductRow = 
                            (MutualFundProductRow) l_mutualFundProduct.getDataSourceObject();
                        l_datOrderBizDate = l_mfProductRow.getSellSwtEndDate();
                    }
                }

                //６－３－３） 　@６－３－２）  ①@～③以外の場合 
                //投信取引時間管理.get投信発注日(引数なし)をコールし、発注日を取得する。 
                else
                {
                    l_datOrderBizDate =
                        WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();
                }


            	//６－３－４)　@投信注文単位の発注日と現在の発注日が異なる場合は例外をスローする。 
        		if (!WEB3DateUtility.getDate(l_strBizDate, "yyyyMMdd").equals(l_datOrderBizDate))
        		{
                    log.debug("投信注文単位の発注日と現在の発注日が異なる場合は例外をスローする");
                    log.exiting(STR_METHOD_NAME); 
                    throw new WEB3BusinessLayerException(
						WEB3ErrorCatalog.BUSINESS_ERROR_00378,
						this.getClass().getName() + "." + STR_METHOD_NAME,
                        	"投信注文単位の発注日と現在の発注日が異なる");                        
        		}
        	}
            
            
            //７)　@約定状態チェック
            if (WEB3ExecStatusDef.EXECUTED_COMPLETE.equals(l_params.exec_status)
                  || WEB3ExecStatusDef.EXECUTED_IN_PROCESS.equals(l_params.exec_status))
            {
                log.debug("投信注文単位の約定状態が約定中または約定済の場合、例外をスローする");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00379,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "投信注文単位の約定状態が約定中または約定済");
            }
			//８)　@注文状態チェック
            //     ※６－３－２）  ①@の場合は、「８)　@注文状態チェック」を行わない。
			//     トリガ発行可能な時間帯の場合、指定された注文の注文状態が"発注済（新規注文)"に
			//     なっているかチェックする。
			//     現在日付＜注文単位の発注日の場合は"受付済（新規注文）"であることをチェックする。
			//８－１)　@投信取引時間管理.isトリガ発行()をコールし、戻り値がtrueの場合は以降の処理を行う 
            if (!l_blnCond)
            {
    			if (WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT))
    			{
    				log.debug("entry::WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(WEB3OrderingConditionDef.DEFAULT)");
    				//８－２)　@現在日付＜注文単位の発注日の場合、投信注文単位Params.getOrderStatus()の戻り値が
    				//         OrderStatusEnum.受付済（新規注文)でない場合、例外をスローする。
    				Timestamp l_tsSystemTimestamp = GtlUtils.getSystemTimestamp();
    				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    				String l_stSystemTimestamp = formatter.format(l_tsSystemTimestamp);
                    
    				if(l_stSystemTimestamp.compareTo(l_strBizDate) < 0)
    				{                   
    					if (!OrderStatusEnum.ACCEPTED.equals(l_params.getOrderStatus()))
    					{
    						log.debug(
    							"投信注文単位Params.getOrderStatus()の戻り値がOrderStatusEnum.受付済（新規注文)でない場合、例外をスローする。");
    						throw new WEB3BusinessLayerException(
    							WEB3ErrorCatalog.BUSINESS_ERROR_00380,
    							this.getClass().getName() + "." + STR_METHOD_NAME,
    							"投信注文単位Params.getOrderStatus()の戻り値がOrderStatusEnum.受付済（新規注文)でない");
    					}
    				}
    				//８－３)　@それ以外の場合、投信注文単位Params.getOrderStatus()の戻り値が
    				//         OrderStatusEnum.発注済（新規注文)でない場合、例外をスローする。
    				else
    				{
    					if (!OrderStatusEnum.ORDERED.equals(l_params.getOrderStatus()))
    					{
    						log.debug(
    							"投信注文単位Params.getOrderStatus()の戻り値がOrderStatusEnum.発注済（新規注文)でない場合、例外をスローする。");
    						throw new WEB3BusinessLayerException(
    							WEB3ErrorCatalog.BUSINESS_ERROR_00380,
    							this.getClass().getName() + "." + STR_METHOD_NAME,
    							"投信注文単位Params.getOrderStatus()の戻り値がOrderStatusEnum.発注済（新規注文)でない");
    					}
    				}
    			}
            }
            //９） 注文有効状態チェック
            //注文有効状態が、"クローズ"でないかチェックする
            if (OrderOpenStatusEnum.CLOSED.equals(l_params.getOrderOpenStatus()))
            {
                log.debug(
                    "投信注文単位Params.getOrderOpenStatus()の戻り値が,クローズ（OrderOpenStatusEnum.CLOSED）の場合、例外をスローする。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00382,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "投信注文単位Params.getOrderOpenStatus()の戻り値が,クローズ（OrderOpenStatusEnum.CLOSED）である");
            }
            //１０）　@緊急停止チェック
            if (WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv))
            {
                //－拡張投信銘柄マネージャ.get投信銘柄()をコールし、乗換先の銘柄オブジェクトを取得する。
                WEB3MutualFundProduct l_swtProduct = 
                    (WEB3MutualFundProduct)l_mutualFundProductManager.getMutualFundProduct(
                    l_subAccount.getInstitution(),
                    l_params.getSwtProductCode());                    
                //－投信発注審査個別チェック.validate緊急停止()をコールし、チェックエラーの場合は例外をスローする。
                try
                {
                    l_mutualFundOrderManagerReusableValidationsCheck.validateEmergencyStop(
                        l_swtProduct,WEB3ProcessDivDef.BUY);
                }
                catch (WEB3BaseException l_ex)
                {
                    log.error(
                        "投信発注審査個別チェック.validate緊急停止()をコールし、チェックエラーの場合は例外をスローする");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_00012,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
            }   
            //１１）　@乗換先銘柄の取引停止時間チェック
            if((WEB3ProcessDivDef.SWITCHING.equals(l_strMutualTradeDiv)))
            {
                //－投信取引時間管理.reset銘柄コード()をコールし、銘柄コードを乗換先銘柄のものに変更する。
                WEB3MutualFundTradingTimeManagement.resetProductCode(l_params.getSwtProductCode());
                //－受付日時、日付ロールをセットする
                //投信取引時間管理.setTimestamp()をコールする。
                WEB3MutualFundTradingTimeManagement.setTimestamp();
                
                //－投信取引時間管理.validete注文受付可能()をコールし、チェックエラーの場合は、例外をスローする。
                WEB3MutualFundTradingTimeManagement.validateOrderAccept();
                
                //－投信取引時間管理.reset銘柄コード()をコールし、銘柄コードを１）で取得した銘柄コードに変更する。
                WEB3MutualFundTradingTimeManagement.resetProductCode(l_strProductCode);
                //－受付日時、日付ロールをセットする。
                WEB3MutualFundTradingTimeManagement.setTimestamp();
            }
        }
        catch (NotFoundException l_ex)
        {
            log.error("no found MutualFundProduct ");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        log.exiting(STR_METHOD_NAME);        
    }
    /**
     * 当クラスのインスタンスを登録するstaticメソッド。<BR>
     * <BR>
     * １） 投信発注審査個別チェック.setInstance()をコールする。<BR>
     * 　@［setInstanceに渡すパラメタ］<BR>
     * 　@　@インスタンス： new 投信発注審査個別チェック()<BR>
     * @@roseuid 40C6A9540350
     */
    public static void register()
    {
        final String STR_METHOD_NAME = "register()";
        log.entering(STR_METHOD_NAME);
        //１） 投信発注審査個別チェック.setInstance()をコールする
        WEB3MutualFundOrderManagerReusableValidationsCheck.setInstance(
            new WEB3MutualFundOrderManagerReusableValidationsCheck());
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is買取請求可能)<BR>
     * 当銘柄が指定された発注日に買取請求可能かをチェックする。<BR>
     * <BR>
     * １）　@買取請求開始日を取得する。<BR>
     * 　@拡張投信銘柄.get買取請求開始日()をコールして、買取請求開始日を取得する。<BR>
     * <BR>
     * ２）　@買取請求終了日を取得する。<BR>
     * 　@拡張投信銘柄.get買取請求終了日()をコールして、買取請求終了日を取得する。<BR>
     * <BR>
     * ３）　@買取請求開始日と買取請求終了日が設定されていない場合は<BR>
     *  false を返す。<BR>
     * <BR>
     * ４）　@買取請求開始日、買取請求終了日、引数.発注日を<BR>
     * YYYYMMDD形式の文字列に変換する。<BR>
     * <BR>
     * ５）　@４）で変換した文字列が以下の条件に合致しない場合は false を返す。<BR>
     * <BR>
     * 　@　@買取請求開始日 ≦ 発注日 ≦ 買取請求終了日<BR>
     * <BR>
     * ６）　@取得日別残高テーブルを検索し、検索条件に該当するデータを検出した場合は false、<BR>
     *      検出しなかった場合は true を返却する。 <BR>
     * <BR>
     * 　@[検索条件] <BR>
     * 　@証券会社コード＝拡張投信銘柄.証券会社コード <BR>
     * 　@部店コード＝補助口座.部店IDに対応する部店コード（*1） <BR>
     * 　@顧客コード＝補助口座.口座IDに対応する口座コードの6桁目までの値（*2）(6桁） <BR>
     * 　@銘柄コード＝拡張投信銘柄.銘柄コード <BR>
     * 　@買取請求算入区分＝"0" <BR>
     * <BR>
     * 　@(*1)部店コード <BR>
     * 　@　@アカウントマネージャ.getBranch（部店ID）.getBranchCode() <BR>
     * 　@(*2)顧客コード <BR>
     * 　@　@補助口座.getMainAccount().getAccountCode()<BR>
     * <BR>
     * @@param l_datOrderBizDate - 発注日
     * @@param l_subAccount - 補助口座
     * @@param l_mfProduct - 拡張投信銘柄
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40CE8CEE0151
     */
    public boolean isBuyingRequestPossible(
            Date l_datOrderBizDate,
            SubAccount l_subAccount,
            WEB3MutualFundProduct l_mfProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isBuyingRequestPossible(Date l_datOrderBizDate)";
        log.entering(STR_METHOD_NAME);

        if (l_datOrderBizDate == null 
                || l_subAccount == null
                || l_mfProduct == null)
        {
            log.debug(" __parameter_error__");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }

        //１）　@買取請求開始日を取得する
        Timestamp l_buyClaimStartDate =
            l_mfProduct.getBuyClaimStartDate();

        //２）　@買取請求終了日を取得する
        Timestamp l_buyClaimEndDate =
            l_mfProduct.getBuyClaimEndDate();

        //３）　@買取請求開始日と買取請求終了日が設定されていない場合
        if (l_buyClaimStartDate == null || l_buyClaimEndDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //４）　@買取請求開始日、買取請求終了日、引数.発注日をYYYYMMDD形式の文字列に変換する
        SimpleDateFormat l_dateFormat =
            GtlUtils.getThreadSafeYYYYMMDDSimpleDateFormat();
        String l_strOrderBizDate = l_dateFormat.format(l_datOrderBizDate);
        String l_strClaimStartDate = l_dateFormat.format(l_buyClaimStartDate);
        String l_strClaimEndDate = l_dateFormat.format(l_buyClaimEndDate);

        //５） 買取請求開始日 ≦ 発注日 ≦ 買取請求終了日 合致しない場合
        if (!(l_strOrderBizDate.compareTo(l_strClaimStartDate) >= 0
            && l_strClaimEndDate.compareTo(l_strOrderBizDate) >= 0))
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        // ６）　@取得日別残高テーブルを検索し、検索条件に該当するデータを検出した場合は false、
        //      検出しなかった場合は true を返却する。 
        // 　@[検索条件] 
        // 　@証券会社コード＝拡張投信銘柄.証券会社コード 
        // 　@部店コード＝補助口座.部店IDに対応する部店コード（*1）
        // 　@顧客コード＝補助口座.口座IDに対応する口座コードの6桁目までの値（*2）(6桁）
        // 　@銘柄コード＝拡張投信銘柄.銘柄コード 
        // 　@買取請求算入区分＝"0" 
        String l_strWhere = "institution_code = ? and " +
            " branch_code = ? and " +
            " account_code = ? and " +
            " product_code = ? and " +
            " include_div = ?" ;

        String l_strBranchCode = 
            l_subAccount.getMainAccount().getBranch().getBranchCode();

        Object[] l_bindVars = {
            l_mfProduct.getInstitution().getInstitutionCode(), 
            l_strBranchCode, 
            l_subAccount.getMainAccount().getAccountCode().substring(0,6), 
            l_mfProduct.getProductCode(), 
            WEB3MFIncludeDivDef.NOT_INCLUDE};
        
        List l_lisMFDayBalanceRows = null;
        try
        {            
            QueryProcessor l_queryProcessor = 
                Processors.getDefaultProcessor();
            l_lisMFDayBalanceRows = 
                l_queryProcessor.doFindAllQuery(
                    MutualFundDayBalanceRow.TYPE, 
                    l_strWhere, 
                    l_bindVars);
                    
        } 
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        } 
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました when search MutualFundProductCategory");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        if(l_lisMFDayBalanceRows == null || l_lisMFDayBalanceRows.size() == 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }
    
    /**
     * (validate全部解約乗換可能)<BR>
     * 当該顧客が全部解約・全部乗換可能かをチェックする。 <BR>
     * <BR>
     * １）金額指定解約乗換注文の存在チェック <BR>
     * <BR>
     * 以下の条件で注文単位テーブルを検索する。<BR> 
     * <BR>
     * 　@[検索条件]<BR>
     * 　@口座ID = 引数.補助口座.getAccountId() and<BR>
     * 　@補助口座ID = 引数.補助口座.getSubAccountId() and<BR>
     * 　@銘柄ID = 引数.拡張投信銘柄.getProductId() and <BR>
     * 　@注文数量タイプ = QuantityTypeEnum.金額 and <BR>
     * 　@（注文状態 = OrderStatusEnum.受付済（新規注文） or<BR>
     * 　@注文状態 = OrderStatusEnum.発注済（新規注文）） and <BR>
     * 　@税区分 = 引数.税区分 and<BR>
     * 　@（（注文種別 = OrderTypeEnum.投資信託売注文 and 約定状態 != ”約定済”) or<BR> 
     * 　@（注文種別 = OrderTypeEnum.投資信託乗換注文 and <BR>
     * 　@（約定状態 is null or （約定状態 = ”約定中” and 約定日 = 乗換元約定日）））<BR> 
     * 　@※発注日の昇順で検索する。<BR>
     * <BR>
     * レコードが取得できた場合はチェック結果=true、取得できなかった場合はチェック結果=false とする。<BR> 
     * <BR>
     * ２）１）のチェック結果 == trueの場合<BR>  
     * <BR>
     * 　@　@２－１）<BR> 
     * 　@　@　@１）で取得した注文単位.発注日 >= 投信取引時間管理.get投信発注日() の場合、<BR>
     * 　@　@　@”同日金額指定注文取消要求” の例外をスローする。 <BR>
     * <BR>
     * 　@　@２－２）<BR>
     * 　@　@　@１）で取得した注文単位.発注日 ＜投信取引時間管理.get投信発注日() の場合、<BR>
     * 　@　@　@”約定後再発注要求” の例外をスローする。<BR>
     * <BR>
     * <BR>
     * ＝＝＝<BR>
     * ※エラーメッセージ<BR>
     * <BR>
     * 約定後再発注要求：<BR>
     *    「約定中の注文が存在するため、全部解約ができません。約定後に再度全部解約を行ってください。」<BR>
     * 同日金額指定注文取消要求：<BR>
     *    「金額指定解約後の全部解約はできません。既に注文済みの金額指定解約注文を取消、<BR>
     *    もしくは取消後口数指定解約への変更を行った後に、再度全部解約を行ってください。」<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * @@param l_taxType - 税区分<BR>
     * @@param l_orderBizDate - 発注日<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public void validateAllSellSwtPoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        TaxTypeEnum l_taxType,
        Date l_orderBizDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateAllSellSwtPoss(SubAccount, WEB3MutualFundProduct, TaxTypeEnum, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null || l_taxType == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        boolean l_blnAmtCheck = false;
      
        List l_lisOrderUnit1Rows = new Vector();        
        
        MutualFundOrderUnitRow l_mfOrderUnitRow1 = null;        
        
        //１）金額指定解約乗換注文の存在チェック 
        
        //以下の条件で注文単位テーブルを検索する。 

        //[検索条件] 
        //口座ID = 引数.補助口座.getAccountId() and 
        //補助口座ID = 引数.補助口座.getSubAccountId() and 
        //銘柄ID = 引数.拡張投信銘柄.getProductId() and 
        //注文数量タイプ = QuantityTypeEnum.金額 and 
        //（注文状態 = OrderStatusEnum.受付済（新規注文） or 
        //  注文状態 = OrderStatusEnum.発注済（新規注文）） and 
        //税区分 = 引数.税区分 and 
        //（（注文種別 = OrderTypeEnum.投資信託売注文 and 約定状態 != ”約定済”) or
        // （注文種別 = OrderTypeEnum.投資信託乗換注文 and
        //   （約定状態 is null or （約定状態 = ”約定中” and 約定日 = 乗換元約定日））））
        //※発注日の昇順で検索する。

        try
        {            
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                  "and product_id = ? " +
                  "and quantity_type = ? and (order_status = ? or order_status = ?) " +
                  "and tax_type = ? " +
                  "and ((order_type = ? and (exec_status != ? or exec_status is null)) or " +
                  "(order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date)))) ";
            
            Object l_bindVars[] = { 
                new Long(l_subAccount.getAccountId()), 
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mfProduct.getProductId()),
                QuantityTypeEnum.AMOUNT,
                OrderStatusEnum.ACCEPTED,
                OrderStatusEnum.ORDERED,
                l_taxType,                
                OrderTypeEnum.MF_SELL,
                WEB3ExecStatusDef.EXECUTED_COMPLETE,
                OrderTypeEnum.MF_SWITCHING,
                WEB3ExecStatusDef.EXECUTED_IN_PROCESS
            };
            
            String l_strOrderBy = "biz_date";
            
            l_lisOrderUnit1Rows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_strOrderBy,
                    null,
                    l_bindVars);

            //レコードが取得できた場合はチェック結果=true、取得できなかった場合はチェック結果=false とする。
            
            if (l_lisOrderUnit1Rows == null || l_lisOrderUnit1Rows.size() == 0)
            {
                log.debug("１）注文単位テーブルが取得できた場合はチェック結果 = false");
                l_blnAmtCheck = false;
            }
            else
            {
                log.debug("１）注文単位テーブルが取得できた場合はチェック結果 = true");
                l_blnAmtCheck = true;     
                l_mfOrderUnitRow1 = (MutualFundOrderUnitRow)l_lisOrderUnit1Rows.get(0);
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

        //チェック結果 == trueの場合
        if (l_blnAmtCheck)
        {
                //１）で取得した注文単位.発注日 >= 投信取引時間管理.get投信発注日() の場合、
                //”同日金額指定注文取消要求” の例外をスローする。
                Date l_datBizDate = 
                    WEB3DateUtility.getDate(l_mfOrderUnitRow1.getBizDate(), "yyyyMMdd");

                Date l_datMfBizDate = 
                    WEB3MutualFundTradingTimeManagement.getMutualOrderBizDate();

                log.debug("注文単位.発注日 = " + l_datBizDate);
                log.debug("投信取引時間管理.get投信発注日 = " + l_datMfBizDate);

                if (WEB3DateUtility.compareToDay(l_datBizDate, l_datMfBizDate) >= 0)
                {
                    log.debug("同日金額指定注文取消要求。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02278,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同日金額指定注文取消要求。");
                }

                //１）で取得した注文単位.発注日 < 投信取引時間管理.get投信発注日() の場合、
                //”約定後再発注要求” の例外をスローする。
                else
                {
                    log.debug("約定後再発注要求");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02279,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "約定後再発注要求。");
                }
            }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (is金額指定解約中)<BR>
     * 当該顧客が現在金額指定の解約を行っているかをチェックする。 <BR>
     * <BR>
     * １）　@以下の条件で投信注文単位テーブルを検索する。 <BR>
     * 　@　@［検索条件］ <BR>
     * 　@　@　@口座ID = 引数.補助口座.getAccountId() <BR>
     * 　@　@　@AND 補助口座ID = 引数.補助口座.getSubAccountId() <BR>
     * 　@　@　@AND 銘柄ID = 引数.拡張投信銘柄.getProductId() <BR>
     * 　@　@　@AND 注文数量タイプ = QuantityTypeEnum.金額 <BR>
     * 　@　@　@AND (注文状態 = OrderStatusEnum.受付済（新規注文） OR <BR>
     *           注文状態 = OrderStatusEnum.発注済（新規注文）) <BR>
     * 　@　@　@AND 税区分 = 引数.税区分 <BR>
     * 　@　@　@AND（（注文種別 = OrderTypeEnum.投資信託売注文 AND 約定状態 != ”約定済”) OR<BR>
     *         （注文種別 = OrderTypeEnum.投資信託乗換注文 AND<BR>
     *         （約定状態 is null OR （約定状態 = ”約定中” AND 約定日 = 乗換元約定日）））) and<BR>
     * 　@　@　@　@　@　@　@(投信タイプ != ”外貨MMF” or 決済区分 != ”外貨”)<BR>
     * <BR>
     * ２）　@該当する注文のレコードが存在する場合はtrueを、<BR>
     * 存在しない場合はfalseを返却する。 <BR>
     * <BR>
     * @@param l_subAccunt - 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * @@param l_taxType - 税区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public boolean isPriceDesignateCancelling(
        SubAccount l_subAccunt, 
        WEB3MutualFundProduct l_mfProduct, 
        TaxTypeEnum l_taxType)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isPriceDesignateCancelling(SubAccount, WEB3MutualFundProduct, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccunt == null || l_mfProduct == null || l_taxType == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        List l_lisOrderUnitRows = new Vector();        
        
        //１）　@以下の条件で投信注文単位テーブルを検索する。 
        //[検索条件] 
        //口座ID = 引数.補助口座.getAccountId() and 
        //補助口座ID = 引数.補助口座.getSubAccountId() and 
        //銘柄ID = 引数.拡張投信銘柄.getProductId() and 
        //注文数量タイプ = QuantityTypeEnum.金額 and 
        //（注文状態 = OrderStatusEnum.受付済（新規注文） or 
        //  注文状態 = OrderStatusEnum.発注済（新規注文）） and 
        //税区分 = 引数.税区分 and 
        //（（注文種別 = OrderTypeEnum.投資信託売注文 and 約定状態 != ”約定済”) or
        // （注文種別 = OrderTypeEnum.投資信託乗換注文 and
        //   （約定状態 is null or （約定状態 = ”約定中” and 約定日 = 乗換元約定日）））)
        // and (投信タイプ != ”外貨MMF” or 決済区分 != ”外貨”)

        try
        {            
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
            "and product_id = ? " +
            "and quantity_type = ? and (order_status = ? or order_status = ?) " +
            "and tax_type = ? " +
            "and ((order_type = ? and (exec_status != ? or exec_status is null)) or " +
            "(order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date)))) "
            + " and (fund_type <> ? or settlement_div <> ?) ";
        
	        Object l_bindVars[] = { 
	            new Long(l_subAccunt.getAccountId()), 
	            new Long(l_subAccunt.getSubAccountId()),
	            new Long(l_mfProduct.getProductId()),
	            QuantityTypeEnum.AMOUNT,
	            OrderStatusEnum.ACCEPTED,
	            OrderStatusEnum.ORDERED,
	            l_taxType,
	            OrderTypeEnum.MF_SELL,
	            WEB3ExecStatusDef.EXECUTED_COMPLETE,
	            OrderTypeEnum.MF_SWITCHING,
	            WEB3ExecStatusDef.EXECUTED_IN_PROCESS,
                MutualFundTypeEnum.FOREIGN_MMF,
                WEB3SettlementDivDef.FOREIGN_CURRENCY
	        };
            
            l_lisOrderUnitRows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_bindVars);

            //２）　@該当する注文のレコードが存在する場合はtrueを、存在しない場合はfalseを返却する。 
            
            if (l_lisOrderUnitRows == null || l_lisOrderUnitRows.size() == 0)
            {
                log.exiting(STR_METHOD_NAME);    
                log.debug("is金額指定解約中 = false");
                return  false;
            }
            else
            {
                log.exiting(STR_METHOD_NAME);
                log.debug("is金額指定解約中 = true");
                return true;                
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
    }
    
    /**
     * (is解約口数拘束率超過)<BR>
     * 金額指定解約（乗換）時に、概算売買口数（保有資産未反映注文）が、 <BR>
     * 保有資産の解約口数拘束率に相当する口数を超えているかチェックする。 <BR>
     * 超過している場合はtrue、超過していない場合はfalseを返却する。  <BR>
     * ①@ 保有資産テーブルより残高を取得する。  <BR> 
     *    ［getAssetに渡すパラメタ］  <BR>
     *     アセットID： 引数.資産ID  <BR>
     * －getAsset()がNotFoundExceptionをスローした場合は、例外をスローする。 <BR> 
     * －取得した保有資産オブジェクト.getQuantity()をコールして残高を取得する。 <BR>
     * ② 会社部店テーブルから、解約口数拘束率を取得する。 <BR>
     *    ［findRowByPkに渡すパラメタ］  <BR>
     * 部店ID： 引数.補助口座オブジェクト<BR>
     * .getMainAccount().getBranch().getBranchId()の戻り値 <BR>
     * 手数料商品コード： ”20：投資信託”  <BR>
     * ③ 今回注文の概算売買口数を算出する。 <BR>
     *   ［calc概算受渡代金に渡すパラメタ］  <BR>
     *    補助口座： 引数.補助口座オブジェクト  <BR>
     *    銘柄： 引数.拡張投信銘柄オブジェクト <BR>
     *    銘柄（乗換先）： 引数.銘柄（乗換先） <BR>
     *    処理区分： 引数.処理区分  <BR>
     *    注文数量： 引数.数量  <BR>
     *    指定方法@： 引数.指定方法@ <BR>
     *    決済方法@： 引数.決済方法@  <BR>
     *    請求方法@： 引数.請求方法@  <BR>
     *    口座区分： 引数.口座区分 <BR>
     *    注文チャネル： セッションから取得した注文チャネル  <BR>
     *    発注日： 引数.発注日  <BR>
     * ④ 保有資産 ＊ 取得したInstBranchProductRow.get解約口数拘束率() ／ 100 ＜  <BR>
     *    （保有資産 － 解約可能残高口数 ＋概算売買口 <BR>
     *    数）　@の場合、trueを返却する。  <BR>
     * 　@　@それ以外は、falseを返却する。 <BR>
     *  <BR>
     * 保有資産：getAseet().getQuantity()の戻り値 <BR>
     * 解約可能残高口数：引数.解約可能残高口数 <BR>
     * 概算売買口数：<BR>
     *    calc概算受渡代金()の戻り値の概算受渡代金オブジェクト.概算売買口数 <BR>
     * <BR>
     * @@param l_subAccunt - 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * @@param l_swtProduct - 銘柄（乗換先）<BR>
     * @@param l_strAssetId - 資産ID<BR>
     * @@param l_dblSellPossQty - 解約可能残高口数<BR>
     * @@param l_strProcessDiv - 処理区分<BR>
     * @@param l_dblQuantity - 注文数量<BR>
     * @@param l_strSpecifyMethod - 指定方法@<BR>
     * @@param l_strSettleMethod - 決済方法@<BR>
     * @@param l_strRequestMethod - 請求方法@<BR>
     * @@param l_strAccountDiv - 口座区分<BR>
     * @@param l_datBizDate - 発注日<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public boolean isSellQtyLimitRateExcess(
        SubAccount l_subAccunt, 
        WEB3MutualFundProduct l_mfProduct, 
        WEB3MutualFundProduct l_swtProduct, 
        String l_strAssetId, 
        double l_dblSellPossQty, 
        String l_strProcessDiv, 
        double l_dblQuantity, 
        String l_strSpecifyMethod, 
        String l_strSettleMethod, 
        String l_strRequestMethod, 
        String l_strAccountDiv, 
        Date l_datBizDate)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "isSellQtyLimitRateExcess(SubAccount, WEB3MutualFundProduct, " +
            "WEB3MutualFundProduct, String, double, String, double, " +
            "String, String, String, String, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccunt == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);  
        }
                
        //①@ 保有資産テーブルより残高を取得する。  

        //　@［getAssetに渡すパラメタ］  
        //　@　@アセットID： 引数.資産ID  

        //－getAsset()がNotFoundExceptionをスローした場合は、例外をスローする。  
        //－取得した保有資産オブジェクト.getQuantity()をコールして残高を取得する。  
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3MutualFundPositionManager l_mfPositionMgr =
            (WEB3MutualFundPositionManager) l_finApp.getTradingModule
                (ProductTypeEnum.MUTUAL_FUND).getPositionManager();
        
        AssetRow l_assetRow = null;
        try
        {
            Asset l_asset = l_mfPositionMgr.getAsset(Long.parseLong(l_strAssetId));
            l_assetRow = (AssetRow) l_asset.getDataSourceObject();            
        }
        catch (NotFoundException l_ex)
        {
            log.error("__NotFoundException__ ");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00204,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        //保有資産.getQuantity()
        BigDecimal l_bdAssetQuantity =
            new BigDecimal(Double.toString(l_assetRow.getQuantity()));
        log.debug("保有資産.getQuantity() = " + l_bdAssetQuantity);
        
        //② 会社部店テーブルから、解約口数拘束率を取得する。 

        //　@［findRowByPkに渡すパラメタ］ 
        //　@　@部店ID： 引数.補助口座オブジェクト.getMainAccount().getBranch().getBranchId()の戻り値 
        //　@　@手数料商品コード： ”20：投資信託”
        InstBranchProductRow l_instBranchProductRow = null;
        try
        {
            l_instBranchProductRow =
	            InstBranchProductDao.findRowByPk(
	                l_subAccunt.getMainAccount().getBranch().getBranchId(),
	                WEB3CommisionProductCodeDef.MUTUAL_FUND);
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
        
        //InstBranchProductRow.get解約口数拘束率() 
        BigDecimal l_bdCancelPriceRestraintRate = 
            new BigDecimal (Double.toString(l_instBranchProductRow.getCancelPriceRestraintRate()));
        
        log.debug("InstBranchProductRow.get解約口数拘束率() = " + l_bdCancelPriceRestraintRate);
        
        //③ 今回注文の概算売買口数を算出する。 

        //　@［calc概算受渡代金に渡すパラメタ］ 
        //　@　@補助口座： 引数.補助口座オブジェクト 
        //　@　@銘柄： 引数.拡張投信銘柄オブジェクト 
        //   銘柄（乗換先）： 引数.銘柄（乗換先） 
        //　@　@処理区分： 引数.処理区分 
        //　@　@注文数量： 引数.数量 
        //　@　@指定方法@： 引数.指定方法@ 
        //　@　@決済方法@： 引数.決済方法@ 
        //　@　@請求方法@： 引数.請求方法@ 
        //　@　@口座区分： 引数.口座区分 
        //　@　@注文チャネル： セッションから取得した注文チャネル 
        //　@　@発注日： 引数.発注日 
        WEB3MutualFundOrderManager l_mutualFundOrderManager =
            (WEB3MutualFundOrderManager) l_finApp.getTradingModule(
                ProductTypeEnum.MUTUAL_FUND).getOrderManager();
        
        OpLoginSecurityService l_opLoginSec =
            (OpLoginSecurityService) Services.getService(OpLoginSecurityService.class);  
        String l_strOrderChanel = 
            l_opLoginSec.getSessionProperty(WEB3SessionAttributeDef.ORDER_CHANNEL);        
        
        WEB3MutualFundEstimatedPrice l_mfEstimatedPrice = 
            l_mutualFundOrderManager.calcEstimateDeliveryAmount(
                l_subAccunt, 
                l_mfProduct,
                l_swtProduct, 
                l_strProcessDiv, 
                l_dblQuantity, 
                l_strSpecifyMethod, 
                l_strSettleMethod, 
                l_strRequestMethod, 
                l_strAccountDiv, 
                l_strOrderChanel, 
                l_datBizDate);
        
        //概算売買口数
        BigDecimal l_bdEstimatedQty =
            new BigDecimal(Double.toString(l_mfEstimatedPrice.getEstimatedQty()));
        //引数.解約可能残高口数
        BigDecimal l_bdSellPossQty =
            new BigDecimal(Double.toString(l_dblSellPossQty));
        log.debug("概算売買口数 = " + l_bdEstimatedQty);
        log.debug("引数.解約可能残高口数 = " + l_bdSellPossQty);

        //④ 保有資産 ＊ 取得したInstBranchProductRow.get解約口数拘束率() ／ 100 ＜ 
        // （保有資産 － 解約可能残高口数 ＋概算売買口数）　@の場合、trueを返却する。 
        //　@それ以外は、falseを返却する。 
        
        //　@保有資産：getAseet().getQuantity()の戻り値         
        //　@解約可能残高口数：引数.解約可能残高口数         
        //　@概算売買口数：calc概算受渡代金()の戻り値の概算受渡代金オブジェクト.概算売買口数
       
        BigDecimal l_bdNumber = new BigDecimal("100");
        if ((l_bdAssetQuantity.multiply(l_bdCancelPriceRestraintRate).divide(
                l_bdNumber, 10, BigDecimal.ROUND_HALF_UP)).compareTo(
                    l_bdAssetQuantity.subtract(l_bdSellPossQty).add(l_bdEstimatedQty)) < 0)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("is解約口数拘束率超過 = true");
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("is解約口数拘束率超過 = false");
            return false;
        }
    }

    /**
     * (validate外貨MMF二重注文)<BR>
     * 当該顧客で、すでに外貨MMFの注文が存在するかチェックする。<BR>
     * <BR>
     * １）引数.拡張投信銘柄.is外貨MMF() ＝ falseの場合、処理を抜ける。<BR>
     * <BR>
     * ２）以下の条件で投信注文単位テーブルの検索してレコードが取得できた場合、<BR>
     * 　@"外貨MMF二重注文エラー"の例外をスローする。<BR>
     * 　@　@　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@　@tag:   BUSINESS_ERROR_02733<BR>
     * <BR>
     * [検索条件]<BR>
     * 口座ID     ＝ 引数.補助口座.getAccountId() and<BR>
     * 補助口座ID ＝ 引数.補助口座.getSubAccountId() and<BR>
     * 銘柄ID     ＝ 引数.拡張投信銘柄.getProductId() and<BR>
     * 発注日     ＝ 引数.発注日 and<BR>
     * 注文状態  ＝ 1：受付済（新規注文）<BR>
     * @@param l_subAccunt - 補助口座<BR>
     * 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * 拡張投信銘柄<BR>
     * @@param l_datBizDate - 発注日<BR>
     * 発注日<BR>
     * @@throws WEB3BaseException 
     */
    public void validateFrgnMmfDoubleOrder(
        SubAccount l_subAccunt,
        WEB3MutualFundProduct l_mfProduct,
        Date l_datBizDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateFrgnMmfDoubleOrder(SubAccount, WEB3MutualFundProduct, Date)";
        log.entering(STR_METHOD_NAME);

        if (l_subAccunt == null || l_mfProduct == null)
        {
            log.debug("パラメータ値不正。");
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１）引数.拡張投信銘柄.is外貨MMF() ＝ falseの場合、処理を抜ける。
       if (!l_mfProduct.isFrgnMmf())
       {
           log.exiting(STR_METHOD_NAME);
           return;
       }

       List l_lisMutualFundOrderUnitRows = new Vector(); 
       //２）以下の条件で投信注文単位テーブルの検索してレコードが取得できた場合、
       //　@"外貨MMF二重注文エラー"の例外をスローする。
       //  [検索条件]
       //    口座ID     ＝ 引数.補助口座.getAccountId() and
       //    補助口座ID ＝ 引数.補助口座.getSubAccountId() and
       //    銘柄ID     ＝ 引数.拡張投信銘柄.getProductId() and
       //    発注日     ＝ 引数.発注日 and
       //    注文状態  ＝ 1：受付済（新規注文）
       String l_strWhereClause =
           "account_id = ? and " +
           "sub_account_id = ? and " +
           "product_id = ? and " +
           "biz_date = ? and " +
           "order_status = ?";

       Object l_bindVars[] = {
           new Long(l_subAccunt.getAccountId()),
           new Long(l_subAccunt.getSubAccountId()),
           new Long(l_mfProduct.getProductId()),
           WEB3DateUtility.formatDate(l_datBizDate, "yyyyMMdd"),
           OrderStatusEnum.ACCEPTED};

       try
       {
           l_lisMutualFundOrderUnitRows =
               Processors.getDefaultProcessor().doFindAllQuery(
                  MutualFundOrderUnitRow.TYPE,
                  l_strWhereClause,
                  l_bindVars);
       }
       catch (DataQueryException l_ex)
       {
           log.error("DBへのアクセスに失敗しました。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       } 
       catch (DataNetworkException l_ex)
       {
           log.error("DBへのアクセスに失敗しました。", l_ex);
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80003,
               this.getClass().getName() + "." + STR_METHOD_NAME,
               l_ex.getMessage(),
               l_ex);
       }

       if (l_lisMutualFundOrderUnitRows != null && l_lisMutualFundOrderUnitRows.size() != 0)
       {
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02733,
               getClass().getName() + "." + STR_METHOD_NAME,
               "外貨MMF二重注文エラー。");
       }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate特定日取引銘柄全部解約可能)<BR>
     * 当該顧客が全部解約・全部乗換可能かをチェックする。  <BR>
     * <BR>
     * １）金額指定解約乗換注文の存在チェック  <BR>
     * <BR>
     * 以下の条件で注文単位テーブルを検索する。<BR> 
     * <BR>
     * 　@[検索条件]<BR>
     * 　@口座ID = 引数.補助口座.getAccountId() and<BR>
     * 　@補助口座ID = 引数.補助口座.getSubAccountId() and<BR>
     * 　@銘柄ID = 引数.拡張投信銘柄.getProductId() and <BR>
     * 　@注文数量タイプ = QuantityTypeEnum.金額 and <BR>
     * 　@（注文状態 = OrderStatusEnum.受付済（新規注文） or<BR>
     * 　@注文状態 = OrderStatusEnum.発注済（新規注文）） and <BR>
     * 　@税区分 = 引数.税区分 and<BR>
     * 　@（（注文種別 = OrderTypeEnum.投資信託売注文 and 約定状態 != ”約定済”) or<BR> 
     * 　@（注文種別 = OrderTypeEnum.投資信託乗換注文 and <BR>
     * 　@（約定状態 is null or （約定状態 = ”約定中” and 約定日 = 乗換元約定日）））<BR> 
     * 　@※発注日の昇順で検索する。<BR>
     * <BR>
     * レコードが取得できた場合はチェック結果=true、取得できなかった場合はチェック結果=false とする。<BR> 
     * <BR>
     * ２）１）のチェック結果 == trueの場合<BR>  
     * <BR>
     * 　@　@２－１）<BR> 
     * 　@　@　@１）で取得した注文単位.発注日 >= 投信銘柄マスタ.解約乗換終了日 の場合、<BR>
     * 　@　@　@”同日金額指定注文取消要求” の例外をスローする。 <BR>
     * <BR>
     * 　@　@２－２）<BR>
     * 　@　@　@１）で取得した注文単位.発注日 ＜投信銘柄マスタ.解約乗換終了日 の場合、<BR>
     * 　@　@　@”約定後再発注要求” の例外をスローする。<BR>
     * <BR>
     * <BR>
     * ＝＝＝<BR>
     * ※エラーメッセージ<BR>
     * <BR>
     * 約定後再発注要求：<BR>
     *    「約定中の注文が存在するため、全部解約ができません。約定後に再度全部解約を行ってください。」<BR>
     * 同日金額指定注文取消要求：<BR>
     *    「金額指定解約後の全部解約はできません。既に注文済みの金額指定解約注文を取消、<BR>
     *    もしくは取消後口数指定解約への変更を行った後に、再度全部解約を行ってください。」<BR>
     * <BR>
     * @@param l_subAccount - 補助口座<BR>
     * @@param l_mfProduct - 拡張投信銘柄<BR>
     * @@param l_taxType - 税区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40B1B47D0280
     */
    public void validateUnitTypeProductAllSellPoss(
        SubAccount l_subAccount, 
        WEB3MutualFundProduct l_mfProduct, 
        TaxTypeEnum l_taxType)
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validateUnitTypeProductAllSellPoss(SubAccount, WEB3MutualFundProduct, TaxTypeEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_mfProduct == null || l_taxType == null)
        {
            log.debug("the parameter of method is null");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        boolean l_blnAmtCheck = false;
      
        List l_lisOrderUnit1Rows = new Vector();        
        
        MutualFundOrderUnitRow l_mfOrderUnitRow1 = null;        
        
        //１）金額指定解約乗換注文の存在チェック 
        
        //以下の条件で注文単位テーブルを検索する。 

        //[検索条件] 
        //口座ID = 引数.補助口座.getAccountId() and 
        //補助口座ID = 引数.補助口座.getSubAccountId() and 
        //銘柄ID = 引数.拡張投信銘柄.getProductId() and 
        //注文数量タイプ = QuantityTypeEnum.金額 and 
        //（注文状態 = OrderStatusEnum.受付済（新規注文） or 
        //  注文状態 = OrderStatusEnum.発注済（新規注文）） and 
        //税区分 = 引数.税区分 and 
        //（（注文種別 = OrderTypeEnum.投資信託売注文 and 約定状態 != ”約定済”) or
        // （注文種別 = OrderTypeEnum.投資信託乗換注文 and
        //   （約定状態 is null or （約定状態 = ”約定中” and 約定日 = 乗換元約定日））））
        //※発注日の昇順で検索する。

        try
        {            
            String l_strWhereClause = "account_id = ? and sub_account_id = ? " +
                  "and product_id = ? " +
                  "and quantity_type = ? and (order_status = ? or order_status = ?) " +
                  "and tax_type = ? " +
                  "and ((order_type = ? and (exec_status != ? or exec_status is null)) or " +
                  "(order_type = ? and (exec_status is null or (exec_status = ? and exec_date = swt_exec_date)))) ";
            
            Object l_bindVars[] = { 
                new Long(l_subAccount.getAccountId()), 
                new Long(l_subAccount.getSubAccountId()),
                new Long(l_mfProduct.getProductId()),
                QuantityTypeEnum.AMOUNT,
                OrderStatusEnum.ACCEPTED,
                OrderStatusEnum.ORDERED,
                l_taxType,                
                OrderTypeEnum.MF_SELL,
                WEB3ExecStatusDef.EXECUTED_COMPLETE,
                OrderTypeEnum.MF_SWITCHING,
                WEB3ExecStatusDef.EXECUTED_IN_PROCESS
            };
            
            String l_strOrderBy = "biz_date";
            
            l_lisOrderUnit1Rows =
                Processors.getDefaultProcessor().doFindAllQuery(
                    MutualFundOrderUnitRow.TYPE,
                    l_strWhereClause,
                    l_strOrderBy,
                    null,
                    l_bindVars);

            //レコードが取得できた場合はチェック結果=true、取得できなかった場合はチェック結果=false とする。
            
            if (l_lisOrderUnit1Rows == null || l_lisOrderUnit1Rows.size() == 0)
            {
                log.debug("１）注文単位テーブルが取得できた場合はチェック結果 = false");
                l_blnAmtCheck = false;
            }
            else
            {
                log.debug("１）注文単位テーブルが取得できた場合はチェック結果 = true");
                l_blnAmtCheck = true;     
                l_mfOrderUnitRow1 = (MutualFundOrderUnitRow)l_lisOrderUnit1Rows.get(0);
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

        //チェック結果 == trueの場合
        if (l_blnAmtCheck)
        {
        	//１）で取得した注文単位.発注日 >= 投信銘柄マスタ.解約乗換終了日 の場合、
                //”同日金額指定注文取消要求” の例外をスローする。
                Date l_datBizDate = 
                    WEB3DateUtility.getDate(l_mfOrderUnitRow1.getBizDate(), "yyyyMMdd");
                //投信銘柄マスタ．解約乗換終了日を取得する。
                MutualFundProductRow l_mfProductRow = 
                    (MutualFundProductRow) l_mfProduct.getDataSourceObject();
                Date l_datSellSwtEndDate = l_mfProductRow.getSellSwtEndDate();
                
                log.debug("注文単位.発注日 = " + l_datBizDate);
                log.debug("投信銘柄マスタ．解約乗換終了日 = " + l_datSellSwtEndDate);

                if (WEB3DateUtility.compareToDay(l_datBizDate, l_datSellSwtEndDate) >= 0)
                {
                    log.debug("同日金額指定注文取消要求。");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02278,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "同日金額指定注文取消要求。");
                }

                //１）で取得した注文単位.発注日 < 投信銘柄マスタ.解約乗換終了日 の場合、
                //”約定後再発注要求” の例外をスローする。
                else
                {
                    log.debug("約定後再発注要求");
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02279,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        "約定後再発注要求。");
                }
            }

        log.exiting(STR_METHOD_NAME);
    }


}
@
