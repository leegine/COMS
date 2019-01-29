head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.38.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqProductInfoUpdateServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄情報更新サービスImpl(WEB3AdminFeqProductInfoUpdateServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/27 鄭海良(中訊) 新規作成
                 : 2005/08/02 韋念瓊(中訊) レビュー       
*/

package webbroker3.feq.service.delegate.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.WEB3FeqProduct;
import webbroker3.feq.WEB3FeqProductManager;
import webbroker3.feq.WEB3FeqTradedProduct;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputRequest;
import webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductInfoUpdateService;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3DataAccessUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式銘柄情報更新サービスImpl)<BR>
 * 外国株式銘柄情報更新サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminFeqProductInfoUpdateServiceImpl implements WEB3AdminFeqProductInfoUpdateService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqProductInfoUpdateServiceImpl.class);
    
    /**
     * @@roseuid 42CE39F300FA
     */
    public WEB3AdminFeqProductInfoUpdateServiceImpl() 
    {
     
    }
    
    /**
     * 外国株式銘柄情報更新サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、以下のメソッドをコールする。<BR>
     * <BR>
     *    get入力画面<BR>
     *    get明細入力画面()<BR>
     *    validate更新()<BR>
     *    submit更新()<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5F6025B
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminFeqProductInformationUpdateInputRequest)
        {
            //get入力画面()
            l_response = 
                this.getInputScreen((WEB3AdminFeqProductInformationUpdateInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqProductInformationUpdateDetailsInputRequest)
        {
            //get明細入力画面
            l_response = 
                this.getDetailInputScreen((WEB3AdminFeqProductInformationUpdateDetailsInputRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqProductInformationUpdateConfirmRequest)
        {
            //validate更新()
            l_response = 
                this.validateUpdate((WEB3AdminFeqProductInformationUpdateConfirmRequest)l_request);
        }
        else if (l_request instanceof WEB3AdminFeqProductInformationUpdateCompleteRequest)
        {
            //submit更新()
            l_response = 
                this.submitUpdate((WEB3AdminFeqProductInformationUpdateCompleteRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示データを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）銘柄情報更新）get入力画面」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）銘柄情報更新」(（管）銘柄情報更新）get入力画面)<BR>
     * 　@　@:  1.5 get外国株式銘柄(long)<BR> 
     * 　@　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）銘柄情報更新」(（管）銘柄情報更新）get入力画面)<BR>
     * 　@　@:  1.6　@get取引銘柄( )<BR> 
     *   　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 42BF94440239
     */
    protected WEB3AdminFeqProductInformationUpdateInputResponse getInputScreen(
        WEB3AdminFeqProductInformationUpdateInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getInputScreen(WEB3AdminFeqProductInformationUpdateInputRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }

        //1.1 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        //1.2 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.3 createResponse( )
        WEB3AdminFeqProductInformationUpdateInputResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get明細入力画面)<BR>
     * 明細入力画面表示データを取得する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）銘柄情報更新）get明細入力画面」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）銘柄情報更新」(（管）銘柄情報更新）get明細入力画面)<BR>
     * 　@　@:  1.5 get外国株式銘柄(Institution, String)<BR> 
     * 　@　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * 　@ （管）銘柄情報更新」(（管）銘柄情報更新）get明細入力画面)<BR>
     * 　@　@:  1.6　@get取引銘柄( )<BR> 
     *   　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateDetailsInputResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5AB021C
     */
    protected WEB3AdminFeqProductInformationUpdateDetailsInputResponse getDetailInputScreen(
        WEB3AdminFeqProductInformationUpdateDetailsInputRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " getDetailInputScreen(WEB3AdminFeqProductInformationUpdateDetailsInputRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.4 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 get外国株式銘柄(Institution, String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "外国株式プロダクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getFeqProduct(l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                + l_request.productCode + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqProduct == null)
        {
            String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                + l_request.productCode + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.6 get取引銘柄( )
        WEB3FeqTradedProduct l_feqTradedProduct = l_feqProduct.getFeqTradedProduct();//WEB3BaseException
        if (l_feqTradedProduct == null)
        {
            String l_strMessage = "外国株式取引銘柄が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
            
        //1.7 createResponse()
        WEB3AdminFeqProductInformationUpdateDetailsInputResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateDetailsInputResponse)l_request.createResponse();
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME, 
                "レスポンスがnullです。");
        }    
        
        //1.8 プロパティセット
        FeqProductRow l_feqProductRow = (FeqProductRow)l_feqProduct.getDataSourceObject();
        FeqTradedProductRow l_feqTradedProductRow = (FeqTradedProductRow)l_feqTradedProduct.getDataSourceObject();
        //銘柄コード： 外国株式銘柄.銘柄コード
        l_response.productCode = l_feqProductRow.getProductCode();
        //銘柄名（カナ）： 外国株式銘柄.銘柄名（カナ）
        l_response.productNameKana = l_feqProductRow.getStandardNameKana();
        //銘柄名（漢字）： 外国株式銘柄.銘柄名（漢字）
        l_response.productNameKanji = l_feqProductRow.getStandardNameKanji();
        //市場コード： 外国株式銘柄.市場コード
        l_response.marketCode = l_feqProductRow.getMarketCode();
        //買付停止区分： 外国株式取引銘柄.買付停止
        if (!l_feqTradedProductRow.getBuyStopIsNull())
        {
            l_response.buyStopDiv = l_feqTradedProductRow.getBuyStop() + "";
        }
        //売付停止区分： 外国株式取引銘柄.売付停止
        if (!l_feqTradedProductRow.getSellStopIsNull())
        {
            l_response.sellStopDiv = l_feqTradedProductRow.getSellStop() + "";
        }
        //現地銘柄コード： 外国株式銘柄.現地銘柄コード
        l_response.localProductCode = l_feqProductRow.getOffshoreProductCode();
        //買付単位： 外国株式取引銘柄.買付単位
        l_response.buyUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProduct.getBuyOrderLotSize());
        //最低買付単位： 外国株式取引銘柄.最低買付注文数量
        l_response.minBuyUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProductRow.getBuyMinQty());
        //売付単位： 外国株式取引銘柄.売付単位
        l_response.sellUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProduct.getSellOrderLotSize());
        //最低売付単位： 外国株式取引銘柄.最低売付注文数量
        l_response.minSellUnit = 
            WEB3StringTypeUtility.formatNumber(l_feqTradedProductRow.getSellMinQty());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate更新)<BR>
     * 更新内容の確認を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）銘柄情報更新）validate更新」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）銘柄情報更新」(（管）銘柄情報更新）validate更新)<BR>
     * 　@　@:  1.5 get外国株式銘柄(long)<BR> 
     * 　@　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) /<BR>
     * 　@ （管）銘柄情報更新」(（管）銘柄情報更新）validate更新)<BR>
     * 　@　@:  1.6　@get取引銘柄( )<BR> 
     *   　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateConfirmResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5AB022C
     */
    protected WEB3AdminFeqProductInformationUpdateConfirmResponse validateUpdate(
        WEB3AdminFeqProductInformationUpdateConfirmRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.4 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 get外国株式銘柄(証券会社 : Institution, 銘柄コード : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "外国株式プロダクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getFeqProduct(l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                + l_request.productCode + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqProduct == null)
        {
            String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                + l_request.productCode + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.6 get取引銘柄( )
        WEB3FeqTradedProduct l_feqTradedProduct = l_feqProduct.getFeqTradedProduct();//WEB3BaseException
        if (l_feqTradedProduct == null)
        {
            String l_strMessage = "外国株式取引銘柄が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.7 createResponse()
        WEB3AdminFeqProductInformationUpdateConfirmResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateConfirmResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit更新)<BR>
     * DBの更新を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（（管）銘柄情報更新）submit更新」 参照<BR>
     * ========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）銘柄情報更新」(（管）銘柄情報更新）submit更新)<BR>
     * 　@　@:  1.5 get外国株式銘柄(long)<BR> 
     * 　@　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02142<BR>
     * ==========================================================<BR>
     * ==========================================================<BR>
     *  シーケンス図(「(外国株式サービスモデル) / <BR>
     * 　@（管）銘柄情報更新」(（管）銘柄情報更新）submit更新)<BR>
     * 　@　@:  1.6　@get取引銘柄( )<BR> 
     *   　@取得できなかった場合、例外をスローする。<BR> 
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:   BUSINESS_ERROR_02088<BR>
     * ==========================================================<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.feq.message.WEB3AdminFeqProductInformationUpdateCompleteResponse
     * @@throws WEB3BaseException
     * @@roseuid 421AB5AB024B
     */
    protected WEB3AdminFeqProductInformationUpdateCompleteResponse submitUpdate(
        WEB3AdminFeqProductInformationUpdateCompleteRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("リクエストが未指定(null)です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME, 
                "リクエストが未指定(null)です。");
        }
        
        //1.1 validate( )
        l_request.validate();//WEB3BaseException
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException        
        if (l_admin == null)
        {
            String l_strMessage = "管理者のログイン情報が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.FEQ_PRODUCT_MANAGE, true);//WEB3BaseException
        
        //1.4 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.5 get外国株式銘柄(証券会社 : Institution, 銘柄コード : String)
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager)l_tradingModule.getProductManager();
        if (l_productManager == null)
        {
            String l_strMessage = "外国株式プロダクトマネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqProduct l_feqProduct = null;
        try
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getFeqProduct(l_institution, l_request.productCode);
        }
        catch (NotFoundException l_ex)
        {
            String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                + l_request.productCode + "」";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_feqProduct == null)
        {
            String l_strMessage = "外国株式銘柄が存在しない。銘柄コード「" 
                + l_request.productCode + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02142,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //1.6 get取引銘柄( )
        WEB3FeqTradedProduct l_feqTradedProduct = l_feqProduct.getFeqTradedProduct();//WEB3BaseException
        if (l_feqTradedProduct == null)
        {
            String l_strMessage = "外国株式取引銘柄が存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02088,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //1.7 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
        //1.8 update外株銘柄(String, 外国株式銘柄Params, String, String)
        FeqProductParams l_feqProductParams = 
            new FeqProductParams((FeqProductRow)l_feqProduct.getDataSourceObject());
        this.updateFeqProduct(
            l_admin.getAdministratorCode(),
            l_feqProductParams,
            l_request.productNameKanji,
            l_request.localProductCode);//WEB3BaseException
            
        //1.9 update外株取引銘柄(String, 外国株式取引銘柄Params, String, String, String, String, String, String)
        FeqTradedProductParams l_feqTradedProductParams = 
            new FeqTradedProductParams((FeqTradedProductRow)l_feqTradedProduct.getDataSourceObject());
        this.updateFeqTradedProduct(
            l_admin.getAdministratorCode(),
            l_feqTradedProductParams,
            l_request.buyStopDiv,
            l_request.sellStopDiv,
            l_request.buyUnit,
            l_request.minBuyUnit,
            l_request.sellUnit,
            l_request.minSellUnit);//WEB3BaseException
        
        //1.10 createResponse()
        WEB3AdminFeqProductInformationUpdateCompleteResponse l_response = 
            (WEB3AdminFeqProductInformationUpdateCompleteResponse)l_request.createResponse();

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (update外株銘柄)<BR>
     * 外株銘柄のDB更新を行う。<BR>
     * <BR>
     * １）行オブジェクトのクローンを生成する。<BR>
     * <BR>
     * ２）クローンにプロパティをセットする。<BR>
     * <BR>
     *    銘柄名（漢字）： 引数.銘柄名（漢字）<BR>
     *    現地銘柄コード： 引数.現地銘柄コード<BR>
     *    更新者コード： 引数.管理者コード<BR>
     *    更新日付： システムタイムスタンプ<BR>
     * <BR>
     * ３）DBを更新する。<BR>
     * <BR>
     *    WEB3DataAccessUtility.updateRow()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    l_orw： 行オブジェクトのクローン<BR>
     * @@param l_strAdminCode - (管理者コード)
     * 管理者コード
     * 
     * @@param l_productParams - (銘柄行)<BR>
     * 外国株式銘柄行オブジェクト<BR>
     * @@param l_strStandardNameKanji - (銘柄名（漢字）)<BR>
     * 銘柄名（漢字）<BR>
     * 
     * @@param l_strOffshoreProductCode - (現地銘柄コード)<BR>
     * 現地銘柄コード<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2BDEF034B
     */
    protected void updateFeqProduct(
        String l_strAdminCode, 
        FeqProductParams l_productParams, 
        String l_strStandardNameKanji, 
        String l_strOffshoreProductCode) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        if (l_productParams == null)
        {
            String l_strMessage = "パラメータ値不正。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //１）行オブジェクトのクローンを生成する。
        FeqProductParams l_params = new FeqProductParams(l_productParams);

        //２）クローンにプロパティをセットする。
        //   銘柄名（漢字）： 引数.銘柄名（漢字）
        //   現地銘柄コード： 引数.現地銘柄コード
        //   更新者コード： 引数.管理者コード
        //   更新日付： システムタイムスタンプ
        l_params.setStandardNameKanji(l_strStandardNameKanji);
        l_params.setOffshoreProductCode(l_strOffshoreProductCode);
        l_params.setLastUpdater(l_strAdminCode);
        Timestamp l_currentTime = GtlUtils.getSystemTimestamp();
        l_params.setLastUpdatedTimestamp(l_currentTime);

        //３）DBを更新する。
        //   WEB3DataAccessUtility.updateRow()をコールする。
        //   [引数]
        //   l_orw： 行オブジェクトのクローン
        try
        {
            WEB3DataAccessUtility.updateRow(l_params);
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
        catch (DataQueryException l_ex)
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
     * (update外株取引銘柄)<BR>
     * 外株取引銘柄のDB更新を行う。<BR>
     * <BR>
     * １）行オブジェクトのクローンを生成する。<BR>
     * <BR>
     * ２）クローンにプロパティをセットする。<BR>
     * <BR>
     *    買付停止： 引数.買付停止区分<BR>
     *    売付停止： 引数.売付停止区分<BR>
     *    買付単位： 引数.買付単位<BR>
     *    最低買付注文数量： 引数.最低買付単位<BR>
     *    売付単位： 引数.売付単位<BR>
     *    最低売付注文数量： 引数.最低売付単位<BR>
     *    更新者コード： 引数.管理者コード<BR>
     *    更新日付： システムタイムスタンプ<BR>
     * <BR>
     * ３）DBを更新する。<BR>
     * <BR>
     *    WEB3DataAccessUtility.updateRow()をコールする。<BR>
     * <BR>
     *    [引数]<BR>
     *    l_orw： 行オブジェクトのクローン<BR>
     * @@param l_strAdminCode - (管理者コード)<BR>
     * 管理者コード<BR>
     * 
     * @@param l_tradeProductParams - (取引銘柄行)<BR>
     * 外国株式取引銘柄行オブジェクト<BR>
     * @@param l_strBuyStopDiv - (買付停止区分)<BR>
     * 買付停止区分<BR>
     * 
     * @@param l_strSellStopDiv - (売付停止区分)<BR>
     * 売付停止区分<BR>
     * 
     * @@param l_strBuyLotSize - (買付単位)<BR>
     * 買付単位<BR>
     * 
     * @@param l_strMinBuyLotSize - (最低買付単位)<BR>
     * 最低買付単位<BR>
     * 
     * @@param l_strSellLotSize - (売付単位)<BR>
     * 売付単位<BR>
     * 
     * @@param l_strMinSellLotSize - (最低売付単位)<BR>
     * 最低売付単位<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42B2BF2503B8
     */
    protected void updateFeqTradedProduct(
        String l_strAdminCode, 
        FeqTradedProductParams l_tradeProductParams, 
        String l_strBuyStopDiv, 
        String l_strSellStopDiv, 
        String l_strBuyLotSize, 
        String l_strMinBuyLotSize, 
        String l_strSellLotSize, 
        String l_strMinSellLotSize) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " validateUpdate(WEB3AdminFeqProductInformationUpdateConfirmRequest )";
        log.entering(STR_METHOD_NAME);

        //１）行オブジェクトのクローンを生成する。
        FeqTradedProductParams l_params = new FeqTradedProductParams(l_tradeProductParams);
        
        //２）クローンにプロパティをセットする。
        //   買付停止： 引数.買付停止区分
        //   売付停止： 引数.売付停止区分
        //   買付単位： 引数.買付単位
        //   最低買付注文数量： 引数.最低買付単位
        //   売付単位： 引数.売付単位
        //   最低売付注文数量： 引数.最低売付単位
        //   更新者コード： 引数.管理者コード
        //   更新日付： システムタイムスタンプ
        if (l_strBuyStopDiv != null)
        {
            l_params.setBuyStop(Integer.parseInt(l_strBuyStopDiv));
        }
        if (l_strSellStopDiv != null)
        {
            l_params.setSellStop(Integer.parseInt(l_strSellStopDiv));
        }
        if (l_strBuyLotSize != null)
        {
            l_params.setBuyLotSize(Double.parseDouble(l_strBuyLotSize));
        }
        if (l_strMinBuyLotSize != null)
        {
            l_params.setBuyMinQty(Double.parseDouble(l_strMinBuyLotSize));
        }
        if (l_strSellLotSize != null)
        {
            l_params.setSellLotSize(Double.parseDouble(l_strSellLotSize));
        }
        if (l_strMinSellLotSize != null)
        {
            l_params.setSellMinQty(Double.parseDouble(l_strMinSellLotSize));
        }

        l_params.setLastUpdater(l_strAdminCode);
        Timestamp l_currentTime = GtlUtils.getSystemTimestamp();
        l_params.setLastUpdatedTimestamp(l_currentTime);
        
        //３）DBを更新する。
        //   WEB3DataAccessUtility.updateRow()をコールする。
        //   [引数]
        //   l_orw： 行オブジェクトのクローン
        try
        {
            WEB3DataAccessUtility.updateRow(l_params);
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
        catch (DataQueryException l_ex)
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
