head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoOrderValidator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPO注文チェック(WEB3IpoOrderValidator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 斉麟 (中訊) 新規作成
Revesion History : 2004/12/27 坂上(SRA) 残対応>>>036
Revesion History : 2005/01/05 坂上(SRA) 残対応>>>054
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>069
Revesion History : 2006/01/26 郭英（中訊）仕様変更・モデル118
Revesion History : 2006/09/07 徐宏偉（中訊）仕様変更・モデル155
Revesion History : 2007/07/19 趙林鵬 (中訊) モデル  No.175
*/


package webbroker3.ipo;

import java.math.BigDecimal;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ProvisionalValueDivDef;
import webbroker3.common.define.WEB3ResidentDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeOrderValidator;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * IPO注文チェッククラス<BR>
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3IpoOrderValidator extends WEB3GentradeOrderValidator implements OrderValidator
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoOrderValidator.class);
    
    /**
     * @@roseuid 4112FBCD019A
     */
    public WEB3IpoOrderValidator() 
    {
     
    }
    
    /**
     * (validate数量)<BR>
     * (validateQuantity)<BR>
     * 数量のチェックを行う。<BR>
     * <BR>
     * １）　@0またはマイナス値のチェック<BR>
     * 　@引数の数量が0またはマイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00531<BR>
     * <BR>
     * ２）　@単位数量チェック<BR>
     * 　@引数の数量が、購入申込単位(*1)の整数倍でない場合<BR>
     * （数量 % 購入申込単位 != 0）、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00532<BR>
     * <BR>
     * (*1) 購入申込単位の取得<BR>
     * IPO銘柄.IPO銘柄行.購入申込単位<BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * @@param l_dblQuantity - 数量<BR>
     * @@roseuid 40D79A4503A4
     */
    public void validateQuantity(WEB3IpoProductImpl l_ipoProduct, double l_dblQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateQuantity(WEB3IpoProductImpl, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        if (l_dblQuantity <= 0)  //0またはマイナス値のチェック
        {
            log.exiting(STR_METHOD_NAME);
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00531, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        double l_dblUnit = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getLotSize();
        
        log.debug("UnitQuantity = !" + (l_dblQuantity % l_dblUnit));
        if (l_dblQuantity % l_dblUnit != 0)  //単位数量チェック
        {
            log.exiting(STR_METHOD_NAME);
            //例外
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00532, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate単価)<BR>
     * （validatePrice）<BR>
     * <BR>
     * １）　@成行の判定<BR>
     * 　@引数の単価が0の場合、成行と判定し、以降の処理を実施せず処理を終了する。<BR>
     * <BR>
     * ２）　@仮条件区分によるレンジチェック<BR>
     * 　@○ 仮条件区分(*1) == ”実価格（円）”の場合<BR>
     * 　@　@－引数の単価のサイズが8byteより大きい場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00533<BR>
     * 　@　@－引数の単価が整数値でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00534<BR>
     * <BR>
     * 　@○ 仮条件区分(*1) == ”ディスカウント率（％）”の場合<BR>
     * 　@　@－引数の単価のサイズが整数部2桁／少数部2桁以内のレンジでない場合、<BR>例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00535<BR>
     * <BR>
     * (*2) 仮条件区分<BR>
     * IPO銘柄.IPO銘柄行.仮条件区分<BR>
     * <BR>
     * ３）　@仮条件上限／下限値のチェック<BR>
     * 　@引数の単価が以下の条件に一致しない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@仮条件下限値(*2) <= 単価 <= 仮条件上限値(*3)<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00536<BR>
     * <BR>
     * (*2) 仮条件下限値<BR>
     * IPO銘柄.IPO銘柄行.仮条件下限値<BR>
     * (*3) 仮条件上限値<BR>
     * IPO銘柄.IPO銘柄行.仮条件上限値<BR>
     * <BR>
     * ３）　@刻みのチェック<BR>
     * 　@引数の単価が、刻み単位になっているかをチェックする。<BR>
     * 　@以下の条件に一致しない場合、例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@(単価 - 仮条件下限値(*2)) % 刻み(*4) == 0<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00537<BR>
     * <BR>
     * (*4) 刻みの取得<BR>
     * IPO銘柄.IPO銘柄行.刻み
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * 
     * @@param l_dblUnitPrice - 単価<BR>
     * 　@成行の場合0。
     * @@roseuid 40D79B750327
     */
    public void validatePrice(WEB3IpoProductImpl l_ipoProduct, double l_dblUnitPrice) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validatePrice(WEB3IpoProductImpl, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        
        if (l_dblUnitPrice == 0)  //成行の判定
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            String l_strDiv = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalValueDiv();
            
            //仮条件区分 == ”実価格（円）”の場合
            if ((WEB3ProvisionalValueDivDef.TRUE_VALUE).equals(l_strDiv))
            {
                String l_str = WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
                
                if (WEB3StringTypeUtility.getByteLength(l_str) > 8)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00533, this.getClass().getName() + STR_METHOD_NAME);
                }
                
                if (!(Math.floor(l_dblUnitPrice) == l_dblUnitPrice))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00534, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            //仮条件区分 == ”ディスカウント率（％）”の場合
            if ((WEB3ProvisionalValueDivDef.DISCOUNT_RATIO).equals(l_strDiv))
            {
                String l_str = WEB3StringTypeUtility.formatNumber(l_dblUnitPrice);
                int l_dbltmp1 = WEB3StringTypeUtility.getIntegerDigits(l_str);
                int l_dbltmp2 = WEB3StringTypeUtility.getFractionDigits(l_str);
                
                if (l_dbltmp1 > 2 || l_dbltmp2 > 2)
                {
                    log.debug("整数部 = " + l_dbltmp1);
                    log.debug("少数部 = " + l_dbltmp2);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00535, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
            
            //仮条件上限／下限値のチェック
            double l_dblMiniValue = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMinValue();
            double l_dblMaxValue = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProvisionalMaxValue();
            if (l_dblUnitPrice < l_dblMiniValue || l_dblUnitPrice > l_dblMaxValue)
            {
                log.debug("MiniValue = " + l_dblMiniValue);
                log.debug("MaxValue = " + l_dblMaxValue);
                log.debug("UnitPrice = " + l_dblUnitPrice);
                log.exiting(STR_METHOD_NAME);
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00536, this.getClass().getName() + STR_METHOD_NAME);                
            }
            //浮動小数点の丸め誤差による判定ミスの回避
			BigDecimal bd = new BigDecimal("100");
			BigDecimal l_dblMiniValueD = new BigDecimal(l_dblMiniValue).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(bd);
			BigDecimal l_dblUnitPriceD = new BigDecimal(l_dblUnitPrice).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(bd);
            double l_dblTick = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getTickValue();
			BigDecimal l_dblTickD = new BigDecimal(l_dblTick).setScale(2, BigDecimal.ROUND_HALF_EVEN).multiply(bd);
			long l_dblMiniValuel = l_dblMiniValueD.longValue();
			long l_dblUnitPricel = l_dblUnitPriceD.longValue();
			long l_dblTickl      = l_dblTickD.longValue();
			if (l_dblTickl == 0)
			{
				log.exiting(STR_METHOD_NAME);
				//例外
				throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00537, this.getClass().getName() + STR_METHOD_NAME);                
			}
            long l_dblTmp = (l_dblUnitPricel - l_dblMiniValuel) % l_dblTickl;
            if (l_dblTmp != 0)
            {
                log.debug("(l_dblUnitPrice - l_dblMiniValue) % l_dblTick = " + l_dblTmp);
                log.exiting(STR_METHOD_NAME);
                //例外
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00537, this.getClass().getName() + STR_METHOD_NAME);                
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate二重申告)<BR>
     * 二重申告チェックを行う。<BR>
     * <BR>
     * １）　@IPO申告マネージャ.get有効IPO申告()にて、既存のIPO申告行を取得する。<BR>
     * 取得できない場合（getIPO申告() == null）、処理を終了（return;）する。<BR>
     * <BR>
     * [getIPO申告()に指定する引数]<BR>
     * 補助口座：　@補助口座<BR>
     * IPO銘柄ＩＤ：　@IPO銘柄.IPO銘柄ＩＤ<BR>
     * <BR>
     * ２）　@二重申告判定<BR>
     * 　@取得したIPO申告が取消でない場合（IPO申告.get取得行ブックビルディング<BR>申告状態() != 
     * OrderStatusEnum.CANCELLED（取消））、<BR>
     * 該当顧客は既にブックビルディング申告済であると判定し、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00538<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * @@roseuid 40D7A02F0375
     */
    public void validateDuplicateOrder(SubAccount l_subAccount, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateOrder(SubAccount, WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        WEB3IpoOrderManagerImpl l_ipoOrderManagerImpl = (WEB3IpoOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IPO).getOrderManager();
        long l_lngIpoProductId = l_ipoProduct.getProductId();
        
        WEB3IpoOrderImpl l_ipoOrder = l_ipoOrderManagerImpl.getOrderUnit(l_subAccount, l_lngIpoProductId);
        
        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //二重申告判定
            IpoOrderRow l_ipoOrderRow = (IpoOrderRow)(((WEB3IpoOrderImpl)l_ipoOrder).getDataSourceObject());
            OrderStatusEnum l_orderStatus = l_ipoOrderRow.getOrderStatus();
            if (l_orderStatus != null && l_orderStatus != OrderStatusEnum.CANCELLED)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00538, this.getClass().getName() + STR_METHOD_NAME);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validateブックビルディング銘柄)<BR>
     * IPO銘柄がブックビルディング取扱中であることをチェックする。<BR>
     * <BR>
     * －以下の条件に当てはまらない場合は例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@IPO銘柄.isブックビルディング申告可能() == ture<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00539<BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * @@roseuid 40D7A34302BA
     */
    public void validateBookbuildingProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateBookBuildingProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //ブックビルディング銘柄のチェック
        if (!l_ipoProduct.isBookbuildingOrderPossible())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00539, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    /**
//     * (validate目論見書既読)<BR>
//     * 目論見書電子交付済かをチェックする。<BR>
//     * <BR>
//     * －ドキュメントシステム接続I/Fサービスを取得し、is目論見書既読()をコールする。<BR>
//     * <BR>
//     * 　@[is目論見書既読()に指定する引数]<BR>
//     * 　@顧客：　@補助口座.getMainAccount()<BR>
//     * 　@銘柄タイプ：　@IPO銘柄.get銘柄タイプ<BR>
//     * 　@銘柄コード：　@IPO銘柄.IPO銘柄行.銘柄コード<BR>
//     * <BR>
//     * －（is目論見書既読() == false）の場合、目論見書電子交付未済と判定し<BR>例外をスローする。<BR>
//     *         class: WEB3BusinessLayerException<BR>
//     *         tag:   BUSINESS_ERROR_00540<BR>
//     * @@param l_subAccount - (補助口座)<BR>
//     * 補助口座オブジェクト
//     * @@param l_ipoProduct - (IPO銘柄)<BR>
//     * IPO銘柄オブジェクト
//     * @@roseuid 40D7BA9C0181
//     */
//    public void validateProspectusExistingRead(SubAccount l_subAccount, WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
//    {
//        final String STR_METHOD_NAME = " validateProspectusExistingRead(SubAccount, WEB3IpoProductImpl)";
//        log.entering(STR_METHOD_NAME);
//        
//        if (l_subAccount ==null || l_ipoProduct == null)
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
//        }
//        
//        //目論見書電子交付済のチェック
//        WEB3GentradeDocumentSystemConnectService l_service = (WEB3GentradeDocumentSystemConnectService)Services.getService(WEB3GentradeDocumentSystemConnectService.class);
//        
//        MainAccount l_MainAccount = l_subAccount.getMainAccount();
//        if (!(l_MainAccount instanceof WEB3GentradeMainAccount))
//        {
//            log.exiting(STR_METHOD_NAME);
//            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
//        }
//        else
//        {
//            WEB3GentradeMainAccount l_web3MainAccount = (WEB3GentradeMainAccount)l_MainAccount;
//            ProductTypeEnum l_productTypeEnum = l_ipoProduct.getProductType();
//            String l_strProductCode = ((IpoProductRow)l_ipoProduct.getDataSourceObject()).getProductCode();
//        
//            if (!l_service.isProspectusAccept(l_web3MainAccount, l_productTypeEnum, l_strProductCode))
//            {
//                log.exiting(STR_METHOD_NAME);
//                throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00540, this.getClass().getName() + STR_METHOD_NAME);
//            }
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /**
     * (validate二重申込・辞退)<BR>
     * 二重購入申込／辞退のチェックを行う。<BR>
     * <BR>
     * 　@引数のIPO申告オブジェクトが以下の状態であれば、二重申告／または辞退と<BR>判断し、例外をスローする。<BR>
     * <BR>
     * 　@[Errorの条件]<BR>
     * 　@（IPO申告.is辞退() == true） Or <BR>
     * 　@（IPO申告.is購入申込() == true）<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00541<BR>
     * @@param l_ipoOrder - (IPO申告)<BR>
     * IPO申告オブジェクト
     * @@roseuid 40DB9C140012
     */
    public void validateDuplicateAppDecline(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDuplicateAppDecline(WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //二重購入申込／辞退のチェック
        if (l_ipoOrder.isDecline() || l_ipoOrder.isOffered())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00541, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate購入申込可能銘柄)<BR>
     * IPO銘柄が購入申込期間中であることをチェックする。<BR>
     * <BR>
     * －以下の条件に当てはまらない場合は例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@IPO銘柄.is購入申込可能() == ture<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00542<BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * @@roseuid 40DBB5970287
     */
    public void validateOfferPossibleProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOfferPossibleProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //IPO銘柄が購入申込期間中のチェック
        if (!l_ipoProduct.isOfferPossible())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00542, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate辞退可能銘柄)<BR>
     * IPO銘柄が辞退可能期間中（新規抽選終了～購入申込終了）であることを<BR>チェックする。<BR>
     * <BR>
     * －以下の条件に当てはまらない場合は例外をスローする。<BR>
     * <BR>
     * 　@[正しい条件]<BR>
     * 　@IPO銘柄.is辞退可能() == ture<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00543<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@roseuid 40DBB65E00C5
     */
    public void validateDeclinePossibleProduct(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDeclinePossibleProduct(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //PO銘柄が辞退可能期間中のチェック
        if (!l_ipoProduct.isDeclinePossible())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00543, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate購入申込・辞退可能)<BR>
     * 落選していないことをチェックする。<BR>
     * <BR>
     * －以下の条件に当てはまらない場合は例外をスローする。<BR>
     * 　@[正しい条件]<BR>
     * 　@IPO申告.is購入申込・辞退可能() == ture<BR>
     * @@param l_ipoOrder - (IPO申告)<BR>
     * IPO申告オブジェクト
     * @@roseuid 40DBB7FB0037
     */
    public void validateOfferDeclinePossible(WEB3IpoOrderImpl l_ipoOrder) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateOfferDeclinePossible(WEB3IpoOrderImpl)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (!l_ipoOrder.isOfferDeclinePossible())
        {
            String l_strMessage = "該当IPO申告は当選／補欠当選でない場合、あるいは、該当顧客は繰上抽選で落選している場合、購入申込／辞退可能が不可です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00544,
                this.getClass().getName() + STR_METHOD_NAME, 
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate特定口座)<BR>
     * 指定の税区分が特定口座の場合、該当顧客が特定口座を開設しているか<BR>チェックを行う。<BR>
     * <BR>
     * １）　@税区分判定<BR>
     * 　@引数の税区分 == TaxTypeEnum.”一般”の場合、<BR>
     * 以下のチェックを行わず処理を終了する。<BR>
     * <BR>
     * ２）　@顧客の税区分判定<BR>
     * 　@引数の補助口座.getMainAccount()にて顧客オブジェクトを取得する。<BR>
     * 取得した顧客.is特定口座開設() == falseの場合、<BR>
     * 該当顧客は特定口座を開設していないと判定し、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00545<BR>
     * <BR>
     * [is特定口座開設()に指定する引数]<BR>
     * 受渡日：　@引数の受渡日<BR>
     * 補助口座：　@引数の補助口座<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト
     * @@param l_taxType - 税区分
     * @@param l_datDeliveryDate - 受渡日
     * @@roseuid 40DBC9230137
     */
    public void validateSpecialAccount(SubAccount l_subAccount, TaxTypeEnum l_taxType, Date l_datDeliveryDate) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateSpecialAccount(SubAccount, TaxTypeEnum, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_datDeliveryDate == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        if (l_taxType == TaxTypeEnum.NORMAL)  //税区分判定
        {
            log.exiting(STR_METHOD_NAME);
            return;
        }
        else
        {
            //顧客の税区分判定
            MainAccount l_MainAccount = l_subAccount.getMainAccount();
            if (!(l_MainAccount instanceof WEB3GentradeMainAccount))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
            }
            else
            {
                WEB3GentradeMainAccount l_web3MainAccount = (WEB3GentradeMainAccount)l_MainAccount;
                if (!l_web3MainAccount.isSpecialAccountEstablished(l_datDeliveryDate, l_subAccount))
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00026, this.getClass().getName() + STR_METHOD_NAME);
                }
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (validate購入申込数量)<BR>
     * 購入申込数量のチェックを行う。<BR>
     * <BR>
     * １）　@0またはマイナス値のチェック<BR>
     * ２）　@単位数量チェック<BR>
     * 　@this.validate数量()をコールする。<BR>
     * <BR>
     * 　@[validate数量()に指定する引数]<BR>
     * 　@IPO銘柄：　@IPO銘柄<BR>
     * 　@数量：　@購入申込数量<BR>
     * <BR>
     * ３）　@当選数量との比較<BR>
     * 　@当選数量より大きい値が購入申込数量に指定されていた場合は、例外をスローする。<BR>
     * <BR>
     * 　@[Error条件]<BR>
     * 　@購入申込数量 > 当選数量<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00546<BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * 
     * @@param l_dblApplicationQuantity - 購入申込数量
     * @@param l_dblElectedQuantity - 当選数量
     * @@roseuid 40DBCE7601A3
     */
    public void validateApplicationQuantity(WEB3IpoProductImpl l_ipoProduct, double l_dblApplicationQuantity, double l_dblElectedQuantity) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateApplicationQuantity(WEB3IpoProductImpl, double, double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //単位数量チェック
        this.validateQuantity(l_ipoProduct, l_dblApplicationQuantity);
        
        //当選数量との比較
        if (l_dblApplicationQuantity > l_dblElectedQuantity)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00546, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate居住者)<BR>
     * 居住者チェックを行う。<BR>
     * <BR>
     * １）パラメータ.補助口座から取得した顧客オブジェクト.居住／非居住区分<BR>
     * が「1：特別非居住者」 or 「2：非居住者」の場合は、例外をスローする。<BR>
     * 
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_02344<BR>
     * 
     * @@param l_subAccount - (補助口座)<BR>
     * @@throws WEB3BaseException
     */
    public void validateResident(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateResident(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null)
        {
            log.debug("補助口座 = null。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                 WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "補助口座 = null。");
        }
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);        
        WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager) l_finApp.getAccountManager();
        
        WEB3GentradeMainAccount l_mainAccount = null;        
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount) 
                l_accountMgr.getMainAccount(l_subAccount.getAccountId());//NotFoundException
        }
        catch (NotFoundException l_ex)
        {
            log.error("顧客テーブルに該当するデータがありません。", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        MainAccountRow l_row = (MainAccountRow) l_mainAccount.getDataSourceObject();
        
        //１）パラメータ.補助口座から取得した顧客オブジェクト.居住／非居住区分 
        //が「1：特別非居住者」 or 「2：非居住者」の場合は、例外をスローする。
        if (WEB3ResidentDef.SPE_NON_RESIDENT.equals(l_row.getResident()) || 
            WEB3ResidentDef.NON_RESIDENT.equals(l_row.getResident()))
        {
            log.debug("居住／非居住区分:「1：特別非居住者」 or 「2：非居住者」");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02344,
                this.getClass().getName() + STR_METHOD_NAME,
                "居住／非居住区分:「1：特別非居住者」 or 「2：非居住者」");
        }
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (validate上限申告株数)<BR>
     * 申告上限株数が「公募数量 + 売出数量」の範囲内であるか <BR>
     * チェックを行う。<BR> 
     * <BR>
     * １）　@引数:IPO銘柄から公募数量と売出数量を取得 <BR>
     * <BR>
     * ２）　@「公募数量 + 売出数量」 < 引数:数量 の場合、例外をスローする。<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト<BR>
     * @@param l_dblQuantity - 申告数量<BR>
     * @@throws WEB3BaseException
     */
    public void validateMaxDemandProductCount(
        WEB3IpoProductImpl l_ipoProduct, 
        double l_dblQuantity) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validateMaxDemandProductCount(WEB3IpoProductImpl, Double)";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        // 申告上限株数が「公募数量 + 売出数量」の範囲内であるか 
        // チェックを行う。
        // １）　@引数:IPO銘柄から公募数量と売出数量を取得 
        //公募数量を取得
        double l_dblPublicOfferingQuantity = 
            ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getPublicOfferingQuantity(); 
        
        //売出数量を取得 
        double l_dblPublicSalesQuantity = 
            ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getPublicSalesQuantity(); 
        
        // ２）　@「公募数量 + 売出数量」 < 引数:数量 の場合、例外をスローする。
        double l_dblAnswer = l_dblPublicOfferingQuantity + l_dblPublicSalesQuantity;        
        
        if (l_dblAnswer < l_dblQuantity)
        {
            log.debug("申告上限株数が「公募数量 + 売出数量」の範囲外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02645, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (validate購入申込代金)<BR>
     * 購入申込代金をチェックする。<BR>
     * <BR>
     * １）取引余力サービス.getその他商品買付可能額～0補正無し～()をコールし、<BR>
     * 　@　@その他商品買付可能額（0補正無し）を取得する。<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@補助口座： 引数.補助口座<BR>
     * 　@受渡日： 引数.IPO銘柄.IPO銘柄行.公開日<BR>
     * <BR>
     * ２）購入申込代金をチェックする。<BR>
     * <BR>
     * 　@２－１）引数.IPO申告.is当選者()をコールし、当選者か否かを判定する。<BR>
     * <BR>
     * 　@２－２）当選者（is当選者() == true）の場合<BR>
     * <BR>
     * 　@　@２－２－１）その他商品買付可能額（0補正無し） < 0 の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02878       <BR>
     * <BR>
     * 　@２－３）その他の場合<BR>
     * <BR>
     * 　@　@２－３－１）購入申込代金を算出する。<BR>
     * <BR>
     * 　@　@　@購入申込代金 = 引数.購入申込数量 * 引数.IPO銘柄.IPO銘柄行.公開価格<BR>
     * <BR>
     * 　@　@２－３－２）その他商品買付可能額（0補正無し） < 購入申込代金 の場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02879       <BR>
     * <BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_ipoProduct - IPO銘柄<BR>
     * IPO銘柄<BR>
     * @@param l_ipoOrder - IPO申告<BR>
     * IPO申告<BR>
     * @@param l_dblApplicationQuantity - (購入申込数量)<BR>
     * 購入申込数量<BR>
     * @@throws WEB3BaseException
     */
    public void validatePayAmount(
        SubAccount l_subAccount,
        WEB3IpoProductImpl l_ipoProduct,
        WEB3IpoOrderImpl l_ipoOrder,
        double l_dblApplicationQuantity)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            "validatePayAmount(SubAccount, WEB3IpoProductImpl, WEB3IpoOrderImpl, double)";
        log.entering(STR_METHOD_NAME);

        if (l_ipoProduct == null || l_ipoOrder == null || l_subAccount == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." +  STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）取引余力サービス.getその他商品買付可能額～0補正無し～()をコールし、
        //その他商品買付可能額（0補正無し）を取得する。
        //[引数]
        //補助口座： 引数.補助口座
        //受渡日： TradingSystem.getBizDate()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_trdSys = l_finApp.getTradingSystem();

        WEB3TPTradingPowerService l_tPTradingPowerService =
            (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

        double l_dblOtherTradingPower = l_tPTradingPowerService.getOtherTradingPowerForCheck(
            (WEB3GentradeSubAccount)l_subAccount,
            l_trdSys.getBizDate());

        //２）購入申込代金をチェックする。
        //２－１）引数.IPO申告.is当選者()をコールし、当選者か否かを判定する。
        boolean l_blnIsElected = l_ipoOrder.isElected();

        //２－２）当選者（is当選者() == true）の場合
        if (l_blnIsElected)
        {
            //２－２－１）その他商品買付可能額（0補正無し） < 0 の場合、例外をスローする。
            if (l_dblOtherTradingPower < 0)
            {
                log.debug("その他商品買付可能額（0補正無し）が0より小さい値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02878,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "その他商品買付可能額（0補正無し）が0より小さい値です。");
            }
        }
        //２－３）その他の場合
        else
        {
            //２－３－１）購入申込代金を算出する。
            //購入申込代金 = 引数.購入申込数量 * 引数.IPO銘柄.IPO銘柄行.公開価格
            IpoProductRow l_ipoProductRow = (IpoProductRow)l_ipoProduct.getDataSourceObject();

            BigDecimal l_bdApplicationQuantity =
                new BigDecimal(String.valueOf(l_dblApplicationQuantity));

            BigDecimal l_bdPublicPrice =
                new BigDecimal(String.valueOf(l_ipoProductRow.getPublicPrice()));

            double l_dblPayAmount = (l_bdApplicationQuantity.multiply(l_bdPublicPrice)).doubleValue();

            //２－３－２）その他商品買付可能額（0補正無し） < 購入申込代金 の場合、例外をスローする。
            if (l_dblOtherTradingPower < l_dblPayAmount)
            {
                log.debug("その他商品買付可能額（0補正無し）が購入申込代金より小さいです。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02879,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "その他商品買付可能額（0補正無し）が購入申込代金より小さいです。");
            }
        }
        log.exiting(STR_METHOD_NAME);
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator#validateTradedProductForTrading(com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount, com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum, com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct)
     */
    public OrderValidationResult validateTradedProductForTrading(SubAccount arg0, OrderTypeEnum arg1, TradedProduct arg2)
    {
        return null;
    }

    /* (非 Javadoc)
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.OrderValidator#validateMarket(com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum, com.fitechlabs.xtrade.plugin.tc.gentrade.Market)
     */
    public OrderValidationResult validateMarket(OrderTypeEnum arg0, Market arg1)
    {
        return null;
    }
}
@
