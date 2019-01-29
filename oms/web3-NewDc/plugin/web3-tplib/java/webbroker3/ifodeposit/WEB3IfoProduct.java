head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP銘柄情報クラス(WEB3IfoProduct.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/10/19 山田　@卓司(FLJ) 新規作成
 Revesion History : 2007/06/27 hijikata(SRA) 夕場対応 
                        モデルNo.061①@, No.062, No.077, No.080, No.090, No.091
Revesion History : 2007/10/18 k.yamashita(SRA) 未取込案件No.014                        
 */
package webbroker3.ifodeposit;

import java.util.Date;
import java.sql.Timestamp;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeBizDate;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteData;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteDataSupplierService;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoTradedProductImpl;
import webbroker3.ifodeposit.define.WEB3IfoReservedDateDef;

/**
 * (先物OP銘柄情報)<BR>
 * 保有/注文している先物OP銘柄の情報を表すクラス。<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3IfoProduct
{

    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoProduct.class);

    /**
     * 銘柄ID。
     */
    public long productId;

    /**
     * 市場ID。
     */
    public long marketId;

    /**
     * 銘柄コード。
     */
    public String productCode;

    /**
     * 原資産銘柄コード。
     */
    public String underlyingProductCode;

    /**
     * (先物オプション商品区分)<BR>
     * 1：先物<BR>
     * 2：コールオプション<BR>
     * 3：プットオプション<BR>
     */
    public IfoDerivativeTypeEnum ifoDerivativeType;

    /**
     * (指数乗数)<BR>
     * 
     * TOPIX：10000<BR>
     * 日経225：1000<BR>
     * 日経300：10000<BR>
     */
    public double unitSize;

    /**
     * (時価)<BR>
     * 
     * ・当日清算値発表後は、当日清算値。<BR>
     * ・当日清算値発表前で、リアル時価証拠金計算を実施している場合は、リアル時価(*)。<
     * BR>
     * ・当日清算値発表前で、リアル時価証拠金計算を実施していない場合、前日清算値(*)。<
     * BR>
     * 
     * (*)ただし、市場閉局後～当日清算値発表前までの場合は、当日終値<BR>
     */
    public double currentPrice;

    /**
     * (当日清算値)
     */
    public double currentBizDateLiquidationPrice;
    
    /**
     * (売買最終日)
     */
    public Date lastTradingDate;    
    
    /**
     * @@roseuid 4158CAEB01D2
     */
    public WEB3IfoProduct()
    {

    }

    /**
     * (create先物OP銘柄情報)<BR>
     * 
     * 先物OP銘柄情報を生成する。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 4112EC470177
     */
    public static WEB3IfoProduct create()
    {
        return new WEB3IfoProduct();
    }

    /**
     * (set先物OP銘柄情報<当日清算値使用>)<BR>
     * 
     * 時価に当日清算値を使用して先物OP銘柄情報のプロパティセットを行う。<BR>
     * 
     * 下記プロパティセットを行う。<BR>
     * 　@
     * 　@・this.銘柄ID：　@引数.先物OP銘柄Params.銘柄ID<BR>
     * 　@・this.市場ID：　@引数.先物OP取引銘柄UpdqParams.市場ID<BR>
     * 　@・this.銘柄コード：　@引数.先物OP銘柄Params.銘柄コード<BR>
     * 　@・this.原資産銘柄コード：　@引数.先物OP銘柄Params.原資産銘柄コード<BR>
     * 　@・this.先物オプション商品区分：　@引数.先物OP銘柄Params.先物オプション商品<BR>
     * 　@・this.指数乗数：　@引数.先物OP取引銘柄UpdqParams.1単位当り乗数<BR>
     * 　@・this.時価：　@引数.先物OP取引銘柄UpdqParams.清算値<BR>
     *　@ ・this.売買最終日：　@引数.先物OP取引銘柄UpdqParams.売買最終日<BR> 
     *  　@
     * （*1）this.時価
     *       ①@ 引数.先物OP取引銘柄UpdqParams.清算値 == 0の場合
     *　@　@　@    引数.先物OP取引銘柄Params.清算値　@…（前日清算値をセット）
     *
     *       ② ①@ 以外の場合　@
     *　@　@    　@引数.先物OP取引銘柄UpdqParams.清算値　@…（当日清算値をセット） 
     * 　@
     * @@param l_ifoProductParams - (先物OP銘柄Params)<BR>
     * @@param l_ifoTradedProductUpdqParams - 先物OP取引銘柄UpdqParams
     * @@param l_ifoTradedProductParams     - 先物OP取引銘柄Params
     * @@roseuid 4122C72002DE
     */
    public void setIfoProductWithCurrentBizDateLiquidationPrice(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductUpdqParams.getMarketId());
        setUnitSize(l_ifoTradedProductUpdqParams.getUnitSize());
        
        // 引数.先物OP取引銘柄UpdqParams.清算値 == 0の場合
        if (l_ifoTradedProductUpdqParams.getLiquidationPrice() == 0)
        {
            // 引数.先物OP取引銘柄Params.清算値
            setCurrentPrice(l_ifoTradedProductParams.getLiquidationPrice());    
        }
        // 以外の場合
        else
        {   // 引数.先物OP取引銘柄UpdqParams.清算値
            setCurrentPrice(l_ifoTradedProductUpdqParams.getLiquidationPrice());
        }        
        
        setLastTradingDate(l_ifoTradedProductUpdqParams.getLastTradingDate());
        
        
    }

    /**
     * (set先物OP銘柄情報<前日清算値使用>)<BR>
     * 
     * 時価に前日清算値を使用して先物OP銘柄情報のプロパティセットを行う。<BR>
     * 
     * 下記プロパティセットを行う。<BR>
     * 　@
     * 　@・this.銘柄ID：　@引数.先物OP銘柄Params.銘柄ID<BR>
     * 　@・this.市場ID：　@引数.先物OP取引銘柄Params.市場ID<BR>
     * 　@・this.銘柄コード：　@引数.先物OP銘柄Params.銘柄コード<BR>
     * 　@・this.原資産銘柄コード：　@引数.先物OP銘柄Params.原資産銘柄コード<BR>
     * 　@・this.先物オプション商品区分：　@引数.先物OP銘柄Params.先物オプション商品<BR>
     * 　@・this.指数乗数：　@引数.先物OP取引銘柄Params.1単位当り乗数<BR>
     * 　@・this.時価：　@引数.先物OP取引銘柄Params.清算値<BR>
     *   ・this.売買最終日：　@引数.先物OP取引銘柄Params.売買最終日<BR>
     * 
     * 　@
     * 　@
     * @@param l_ifoProductParams - 先物OP銘柄Params
     * @@param l_ifoTradedProductParams - 先物OP取引銘柄Params
     * @@roseuid 4122C752037A
     */
    public void setIfoProductWithPreBizDateLiquidationPrice(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductParams.getMarketId());
        setUnitSize(l_ifoTradedProductParams.getUnitSize());
        setCurrentPrice(l_ifoTradedProductParams.getLiquidationPrice());
        setLastTradingDate(l_ifoTradedProductParams.getLastTradingDate());
    }

    /**
     * (set先物OP銘柄情報<リアル時価使用>)<BR>
     * 
     * 時価にリアル時価を使用して先物OP銘柄情報のプロパティセットを行う。<BR>
     * 
     * １）　@先物OP取引銘柄の生成<BR>
     * 　@　@　@先物OP取引銘柄(*)を生成する。<BR>
     * 　@　@　@コンストラクタの引数には、引数.先物OP取引銘柄ParamsをIfoTradedProductRow型
     * にキャストして設定する。<BR>
     * 　@　@　@(*)xTradeのcomパッケージ下のIfoTradedProductオブジェクトを生成する。<BR>
     *
     * ２）　@リアル時価の取得<BR>
     * 　@　@　@this.get時価(先物OP取引銘柄,当日清算値)によりリアル時価を取得する。　@　@ <BR>
     * 　@　@   　@［get時価()の引数設定] 
     *           先物OP取引銘柄：　@引数.先物OP取引銘柄Params 
     *           当日清算値：　@null 
     * 
     * ３）　@下記プロパティセットを行う。<BR>
     * 　@
     * 　@　@・this.銘柄ID：　@引数.先物OP銘柄Params.銘柄ID<BR>
     * 　@　@・this.市場ID：　@引数.先物OP取引銘柄Params.市場ID<BR>
     * 　@　@・this.銘柄コード：　@引数.先物OP銘柄Params.銘柄コード<BR>
     * 　@　@・this.原資産銘柄コード：　@引数.先物OP銘柄Params.原資産銘柄コード<BR>
     * 　@　@・this.先物オプション商品区分：　@引数.先物OP銘柄Params.先物オプション商品<BR>
     * 　@　@・this.指数乗数：　@引数.先物OP取引銘柄Params.1単位当り乗数<BR>
     * 　@　@・this.時価：　@this.get時価( )の戻り値<BR>
     * 　@　@・this.売買最終日：　@引数.先物OP取引銘柄Params.売買最終日<BR>
     * 　@
     * @@param l_ifoProductParams - 先物OP銘柄Params
     * @@param l_ifoTradedProductParams - 先物OP取引銘柄Params
     * @@roseuid 4122F5BE037A
     */
    public void setIfoProductWithCurrentPrice(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        double l_dblCurrentPrice = 0.0;
        try
        {
            IfoTradedProduct l_tp =
                new IfoTradedProductImpl(l_ifoTradedProductParams);
            l_dblCurrentPrice = getCurrentPrice(l_tp, null);
        } 
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "setIfoProductWithCurrentPrice(IfoProductParams, IfoTradedProductParams)",
                    dfe.getMessage(), dfe);

        }

        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductParams.getMarketId());
        setUnitSize(l_ifoTradedProductParams.getUnitSize());
        setCurrentPrice(l_dblCurrentPrice);
        setLastTradingDate(l_ifoTradedProductParams.getLastTradingDate());
    }

    /**
     * (set先物OP銘柄情報<リアル時価使用-夕場>)<BR>
     * 
     * 時価にリアル時価を使用して先物OP銘柄情報のプロパティセットを行う。<BR>
     * 
     * １）　@先物OP取引銘柄の生成<BR>
     * 　@　@　@先物OP取引銘柄(*)を生成する。<BR>
     * 　@　@　@コンストラクタの引数には、引数.先物OP取引銘柄ParamsをIfoTradedProductRow型
     * にキャストして設定する。<BR>
     * 　@　@　@(*)xTradeのcomパッケージ下のIfoTradedProductオブジェクトを生成する。<BR>
     *
     * ２）　@リアル時価の取得<BR>
     * 　@　@　@this.get時価(先物OP取引銘柄,当日清算値)によりリアル時価を取得する。　@　@ <BR>
     * 　@　@   　@［get時価()の引数設定]
     * 
     *     ２）-１　@引数.先物OP取引銘柄UpdqParams.清算値　@==　@0の場合
     *              先物OP取引銘柄：　@引数.先物OP取引銘柄Params
     *　@　@          当日清算値：　@  null　@(*)get時価()で時価が取得できない場合、前日清算値をセットするため
     *
     *     ２）-２　@２-１）以外の場合
     *　@          先物OP取引銘柄：　@引数.先物OP取引銘柄Params
     *            当日清算値：　@引数.先物OP取引銘柄UpdqParams.清算値
     * 
     * ３）　@下記プロパティセットを行う。<BR>
     * 　@
     * 　@　@・this.銘柄ID：　@引数.先物OP銘柄Params.銘柄ID<BR>
     * 　@　@・this.市場ID：　@引数.先物OP取引銘柄Params.市場ID<BR>
     * 　@　@・this.銘柄コード：　@引数.先物OP銘柄Params.銘柄コード<BR>
     * 　@　@・this.原資産銘柄コード：　@引数.先物OP銘柄Params.原資産銘柄コード<BR>
     * 　@　@・this.先物オプション商品区分：　@引数.先物OP銘柄Params.先物オプション商品<BR>
     * 　@　@・this.指数乗数：　@引数.先物OP取引銘柄Params.1単位当り乗数<BR>
     * 　@　@・this.時価：　@this.get時価( )の戻り値<BR>
     *     ・this.当日清算値：　@(*1) <BR>               
     * 　@　@・this.売買最終日：　@引数.先物OP取引銘柄Params.売買最終日<BR>
     * 
     * (*1)this.当日清算値
     * 　@　@　@引数.証拠金不足メール送信フラグ==falseの場合のみセットする。<BR>
     *         ①@ 引数.先物OP取引銘柄UpdqParams.清算値 == 0の場合 <BR>
     *　@　@　@      引数.先物OP取引銘柄Params．清算値  …(前日清算値をセット) <BR>
     *
     *　@       ② ①@以外の場合　@<BR>
     *            引数.先物OP取引銘柄UpdqParams.清算値　@…(当日清算値をセット) <BR>
     * 　@
     * @@param l_ifoProductParams - 先物OP銘柄Params
     * @@param l_ifoTradedProductParams - 先物OP取引銘柄Params
     * @@param l_blnIfoDepositMailFlag - 証拠金不足メール送信フラグ
     * @@param l_ifoTradedProductUpdqParams - 先物OP取引銘柄UpdqParams
     * @@roseuid 4122F5BE037A
     */
    public void setIfoProductWithCurrentPriceEvening(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams,
        boolean l_blnIfoDepositMailFlag,
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams)
    {
        double l_dblCurrentPrice = 0.0;
        double l_dblLiquidationPrice = l_ifoTradedProductUpdqParams.liquidation_price;
        
        try
        {
            IfoTradedProduct l_tp =
                new IfoTradedProductImpl(l_ifoTradedProductParams);
            
            // 引数.先物OP取引銘柄UpdqParams.清算値　@==　@0の場合    
            if (l_dblLiquidationPrice == 0)
            {
                // get時価()の引数.当日清算値： null
                l_dblCurrentPrice = getCurrentPrice(l_tp, null);    
            }
            // 以外の場合
            else
            {
                // get時価()の引数.引数の当日清算値：引数.先物OP取引銘柄UpdqParams.清算値
                l_dblCurrentPrice = getCurrentPrice(l_tp, new Double(l_dblLiquidationPrice));
            }
            
        } 
        catch (DataFindException dfe)
        {
            log.error(dfe.getMessage(), dfe);
            throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "setIfoProductWithCurrentPrice(IfoProductParams, IfoTradedProductParams)",
                    dfe.getMessage(), dfe);

        }

        setProductId(l_ifoProductParams.getProductId());
        setProductCode(l_ifoProductParams.getProductCode());
        setUnderlyingProductCode(l_ifoProductParams.getUnderlyingProductCode());
        setIfoDerivativeType(l_ifoProductParams.getDerivativeType());
        setMarketId(l_ifoTradedProductParams.getMarketId());
        setUnitSize(l_ifoTradedProductParams.getUnitSize());
        setCurrentPrice(l_dblCurrentPrice);
        if (!l_blnIfoDepositMailFlag){
            // 当日清算値は引数.証拠金不足メール送信フラグ==falseの場合のみセット
            
            // 引数.先物OP取引銘柄UpdqParams.清算値　@==　@0の場合 
            if  (l_dblLiquidationPrice == 0)
            {
                // 引数.先物OP取引銘柄Params.清算値をセット
                this.currentBizDateLiquidationPrice = l_ifoTradedProductParams.getLiquidationPrice();
            }
            else
            {
                // 引数.先物OP取引銘柄UpdqParams.清算値をセット      
                this.currentBizDateLiquidationPrice = l_dblLiquidationPrice;
            }
        }
        setLastTradingDate(l_ifoTradedProductParams.getLastTradingDate());
    }


    /**
     * (get先物OP銘柄情報＜立会時間帯-日中＞)<BR>
     * 
     * （staticメソッド）<BR>
     * 先物OP銘柄情報オブジェクトを作成する。<BR>
     * 
     * リアル時価証拠金計算を実施するかどうかにより、<BR>
     * セットする時価の内容が異なるため、下記メソッドを呼び分ける。<BR>
     * 
     * 　@・set先物OP銘柄情報<リアル時価使用><BR>
     * 　@・set先物OP銘柄情報<前日清算値使用><BR>
     *       
     * １）　@オブジェクトの生成<BR>
     * 　@    先物OP銘柄情報オブジェクトを生成する。<BR>
     * 
     * ２）　@リアル時価証拠金計算実施による分岐　@<BR>
     * 　@２－１）　@リアル時価証拠金計算を実施している場合(引数.isリアル時価証拠金計算実
     * 施 == true)<BR>
     * 
     * 　@　@　@　@　@　@生成した先物OP銘柄情報.set先物OP銘柄情報<リアル時価使用>( 
     * )をコールする。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@　@　@　@　@・先物OP取引銘柄Params：　@引数.先物OP取引銘柄Params<BR>
     *   
     * 　@２－２）　@リアル時価証拠金計算を実施していない場合(引数.isリアル時価証拠金計算
     * 実施 == false)<BR>
     * 
     * 　@　@　@　@　@　@生成した先物OP銘柄情報.set先物OP銘柄情報<前日清算値使用>( 
     * )をコールする。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@　@　@　@　@・先物OP取引銘柄Params：　@引数.先物OP取引銘柄Params<BR>
     * 
     * ３）　@プロパティセットした先物OP銘柄情報を返却する。<BR>
     * @@param l_ifoProductParams - 先物OP銘柄Params
     * @@param l_ifoTradedProductParams - 先物OP取引銘柄Params
     * @@param l_blnIsRealPriceIfoDepositCalc - (isリアル時価証拠金計算実施)<BR>
     * 
     * リアル時価証拠金計算を実施している場合、true。以外、false。<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 412407390067
     */
    public static WEB3IfoProduct getOnSessionIfoProduct(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams,
        boolean l_blnIsRealPriceIfoDepositCalc)
    { 
        WEB3IfoProduct l_product = create();
        if (l_blnIsRealPriceIfoDepositCalc)
        {
            l_product.setIfoProductWithCurrentPrice(
                l_ifoProductParams,
                l_ifoTradedProductParams);
        } else
        {
            l_product.setIfoProductWithPreBizDateLiquidationPrice(
                l_ifoProductParams,
                l_ifoTradedProductParams);
        }
        return l_product;
    }

    /**
     * (get先物OP銘柄情報＜立会時間帯-夕場＞)<BR>
     * 
     * （staticメソッド）<BR>
     * 先物OP銘柄情報オブジェクトを作成する。<BR>
     * 
     * リアル時価証拠金計算を実施するかどうかにより、<BR>
     * セットする時価の内容が異なるため、下記メソッドを呼び分ける。<BR>
     * 
     * 　@・set先物OP銘柄情報<リアル時価使用><BR>
     * 　@・set先物OP銘柄情報<当日清算値使用><BR>
     *       
     * １）　@オブジェクトの生成<BR>
     * 　@    先物OP銘柄情報オブジェクトを生成する。<BR>
     * 
     * ２）　@リアル時価証拠金計算実施による分岐　@<BR>
     * 　@２－１）　@リアル時価証拠金計算を実施している場合(引数.isリアル時価証拠金計算実
     * 施 == true)<BR>
     * 
     * 　@　@　@生成した先物OP銘柄情報.set先物OP銘柄情報<リアル時価使用-夕場>( )をコールする。 <BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@　@　@　@　@・先物OP取引銘柄Params：　@引数.先物OP取引銘柄Params<BR>
     *             　@・証拠金不足メール送信フラグ：　@引数.証拠金不足メール送信フラグ 
     * 　@　@　@　@　@　@　@・先物OP取引銘柄UpdqParams：　@引数.先物OP取引銘柄UpdqParams<BR>
     *   
     * 　@２－２）　@リアル時価証拠金計算を実施していない場合(引数.isリアル時価証拠金計算
     * 実施 == false)<BR>
     * 
     * 　@　@　@　@　@　@生成した先物OP銘柄情報.set先物OP銘柄情報<当日清算値使用>( 
     * )をコールする。<BR>
     * 
     * 　@　@　@　@　@　@[引数の設定]<BR>
     * 　@　@　@　@　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@　@　@　@　@・先物OP取引銘柄UpdqParams：　@引数.先物OP取引銘柄UpdqParams<BR>
     * 　@　@　@　@　@　@　@・先物OP取引銘柄Params    ：　@引数.先物OP取引銘柄Params<BR>
     * 
     * ３）　@プロパティセットした先物OP銘柄情報を返却する。<BR>
     * @@param l_ifoProductParams - 先物OP銘柄Params
     * @@param l_ifoTradedProductUpdqParams - 先物OP取引銘柄UpdqParams
     * @@param l_blnIsRealPriceIfoDepositCalc - (isリアル時価証拠金計算実施)<BR>
     *           リアル時価証拠金計算を実施している場合、true。以外、false。<BR>
     * @@param l_blnIfoDepositMailFlag - (証拠金不足メール送信フラグ)<BR>
     * @@param l_ifoTradedProductParams - 先物OP取引銘柄Params
     * 
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 412407390067
     */
    public static WEB3IfoProduct getOnEveningSessionIfoProduct(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams,
        boolean l_blnIsRealPriceIfoDepositCalc,
        boolean l_blnIfoDepositMailFlag,
        IfoTradedProductParams l_ifoTradedProductParams)
    {
        
        WEB3IfoProduct l_product = create();
        if (l_blnIsRealPriceIfoDepositCalc)
        {
            l_product.setIfoProductWithCurrentPriceEvening(
                l_ifoProductParams,
                l_ifoTradedProductParams,
                l_blnIfoDepositMailFlag,
                l_ifoTradedProductUpdqParams);
        } else
        {
            l_product.setIfoProductWithCurrentBizDateLiquidationPrice(
                l_ifoProductParams,
                l_ifoTradedProductUpdqParams,
                l_ifoTradedProductParams);
        }
        return l_product;
    }

    /**
     * (get先物OP銘柄情報＜立会時間外＞)<BR>
     * 
     * （staticメソッド）<BR>
     * 先物OP銘柄情報オブジェクトを作成する。<BR>
     * 
     * 後場引け後～夕場開局までの時間帯かどうか、 
     * バッチ終了～市場開局までの時間帯かどうか、 
     * 当日清算値発表済かどうかにより<BR>
     * セットする時価の内容が異なるため、下記メソッドを呼び分ける。<BR>
     * 
     * 　@・set先物OP銘柄情報<前日清算値使用><BR>
     * 　@・set先物OP銘柄情報<当日清算値使用><BR>
     * 　@・set先物OP銘柄情報<リアル時価使用><BR>
     * 
     * １）　@オブジェクトの生成<BR>
     * 　@    先物OP銘柄情報オブジェクトを生成する。<BR>
     * 
     * ２）　@先物OP取引銘柄Paramsの検索(有効日指定)<BR>
     * 　@証拠金計算データソースアクセス管理.get取引銘柄Params()により<BR>
     *   先物OP取引銘柄Paramsを取得する。<BR>
     * 
     * 　@　@[引数の設定]<BR>
     * 　@　@　@・銘柄ID：　@引数.先物OP銘柄Params.銘柄ID<BR>
     * 　@　@　@・市場ID：　@引数.先物OP銘柄Params.対象市場ID<BR>
     * 　@　@　@・有効日：　@引数.発注日<BR>
     * 
     * ３）　@バッチ終了～市場開局までの場合<BR>
     *   （２）にて先物OP取引銘柄Paramsが取得できた、かつ、<BR> 
     *     閉局時間帯（取引時間管理.is市場開局時間帯==false）場合） <BR>
     * 　@生成した先物OP銘柄情報.set先物OP銘柄情報<前日清算値使用>( )をコールする。<BR>
     * 
     * 　@　@[引数の設定]<BR>
     * 　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@・先物OP取引銘柄Params：　@取得した先物OP取引銘柄Params<BR>
     * 
     * ４）　@後場引け後～夕場開局、または市場閉局～バッチ開始までの場合<BR> 
     *  （３）以外の場合））<BR>
     * 
     * 　@４－１）　@先物OP取引銘柄UpdqParamsの検索<BR>
     * 　@　@　@証拠金計算データソースアクセス管理.get取引銘柄一時Params()により <BR> 
     *      先物OP取引銘柄UpdqParamsを取得する。<BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@　@・銘柄ID：　@銘柄ID一覧.銘柄ID<BR>
     * 　@　@　@　@・市場ID：　@先物OP銘柄Params.対象市場ID<BR>
     * 　@　@　@　@・証券会社コード：　@先物OP銘柄Params.証券会社コード<BR>
     * 　@　@　@　@・有効日：　@引数.発注日<BR>
     * 
     * 　@４－２）　@当日清算値が発表済の場合（先物OP取引銘柄UpdqParams.清算値 != 0）<BR>
     * 　@　@　@生成した先物OP銘柄情報.setOP銘柄情報<当日清算値使用>( )をコールする。<BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@　@・先物OP取引銘柄updqParams：　@取得した先物OP取引銘柄UpdqParams<BR>
     * 　@　@　@　@・先物OP取引銘柄Params    ：　@取得した先物OP取引銘柄Params<BR> 
     * 
     * 　@４－３）　@当日清算値が未発表の場合（先物OP取引銘柄UpdqParams.清算値 == 0）<BR>
     * 　@　@　@生成した先物OP銘柄情報.setOP銘柄情報<リアル時価使用>( )をコールする。<BR>
     * 
     * 　@　@　@[引数の設定]<BR>
     * 　@　@　@　@・先物OP銘柄Params：　@引数.先物OP銘柄Params<BR>
     * 　@　@　@　@・先物OP取引銘柄Params：　@引数.先物OP取引銘柄Params<BR>
      *  
     * ５）　@プロパティセットした先物OP銘柄情報を返却する。<BR>
     * @@param l_ifoProductParams - 先物OP銘柄Params
     * @@param l_ifoTradedProductParams - 先物OP取引銘柄Params
     * @@param l_datOrderBizDate - 発注日
     * @@return webbroker3.ifodeposit.WEB3IfoProduct
     * @@roseuid 4122C5C403C8
     */
    public static WEB3IfoProduct getOffSessionIfoProduct(
        IfoProductParams l_ifoProductParams,
        IfoTradedProductParams l_ifoTradedProductParams,
        Date l_datOrderBizDate)
    {
        // 指定日の先物OP取引銘柄取得
        WEB3IfoProduct l_product = WEB3IfoProduct.create();
        IfoTradedProductParams l_tradedProductParams =
            WEB3IfoDepositPersistentDataManager.getTradedProductParams(
                l_ifoProductParams.getProductId(),
                l_ifoProductParams.getTargetMarketId(),
                l_datOrderBizDate);
        try{
        if (l_tradedProductParams != null &&
            !WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone())
        {
            // 指定日の先物OP取引銘柄が存在した場合
            l_product.setIfoProductWithPreBizDateLiquidationPrice(
                l_ifoProductParams,
                l_tradedProductParams);
        } else
        {
            // 指定日の先物OP取引銘柄が存在しない場合
            // 指定日の先物OP取引銘柄UPDQ取得
            IfoTradedProductUpdqParams l_tradedProductUpdqParams =
                WEB3IfoDepositPersistentDataManager.getTradedProductUpdqParams(
                    l_ifoProductParams.getProductId(),
                    l_ifoProductParams.getTargetMarketId(),
                    l_ifoProductParams.getInstitutionCode(),
                    l_datOrderBizDate);
            if (l_tradedProductUpdqParams.getLiquidationPrice() != 0)
            {
                // 当日清算値が設定されている場合
                l_product.setIfoProductWithCurrentBizDateLiquidationPrice(
                    l_ifoProductParams,
                    l_tradedProductUpdqParams,
                    l_ifoTradedProductParams);
            } else
            {
                // 当日清算値が設定されていない場合
                l_product.setIfoProductWithCurrentPrice(
                    l_ifoProductParams,
                    l_ifoTradedProductParams);
            }
        }
        }
        catch(WEB3BaseException be)
        {
            log.error(be.getMessage(), be);
            throw new WEB3BaseRuntimeException(be.getErrorInfo(), 
              be.getErrorMethod(), be.getErrorMessage(), be.getException());
        }
        return l_product;
    }

    /**
     * (is先物)<BR>
     * 
     * 該当銘柄が先物であるかを判定する。<BR>
     * 
     * this.先物オプション区分==”先物”の場合、trueを返却する。以外、falseを返却する。
     * <BR>
     * @@return boolean
     * @@roseuid 4122BD7A02C3
     */
    public boolean isFutures()
    {
        if (IfoDerivativeTypeEnum.FUTURES.equals(getIfoDerivativeType()))
        {
            return true;
        } else
        {
            return false;
        }
    }

    /**
     * 時価を取得する。
     * 
     * @@return　@時価
     */
    public double getCurrentPrice()
    {
        return currentPrice;
    }

    /**
     * 先物オプション商品区分を取得する。
     * 
     * @@return　@先物オプション商品区分
     */
    public IfoDerivativeTypeEnum getIfoDerivativeType()
    {
        return ifoDerivativeType;
    }

    /**
     * 市場IDを取得する。
     * 
     * @@return　@市場ID
     */
    public long getMarketId()
    {
        return marketId;
    }

    /**
     * 銘柄コードを取得する。
     * 
     * @@return　@銘柄コード
     */
    public String getProductCode()
    {
        return productCode;
    }

    /**
     * 銘柄IDを取得する。
     * 
     * @@return　@銘柄ID
     */
    public long getProductId()
    {
        return productId;
    }

    /**
     * 原資産銘柄コードを取得する。
     * 
     * @@return　@原資産銘柄コード
     */
    public String getUnderlyingProductCode()
    {
        return underlyingProductCode;
    }

    /**
     * 指数乗数を取得する。
     * 
     * @@return　@指数乗数
     */
    public double getUnitSize()
    {
        return unitSize;
    }
    
    /**
     * 売買最終日を取得する。
     * 
     * @@return　@売買最終日
     */
    public Date getLastTradingDate()
    {        
        return lastTradingDate;
    }

    /**
     * 時価を設定する。
     * 
     * @@param l_dblCurrentPrice　@時価
     */
    public void setCurrentPrice(double l_dblCurrentPrice)
    {
        currentPrice = l_dblCurrentPrice;
    }

    /**
     * 先物オプション商品区分を設定する。
     * 
     * @@param l_ifoDerivativeType　@先物オプション商品区分
     */
    public void setIfoDerivativeType(IfoDerivativeTypeEnum l_ifoDerivativeType)
    {
        ifoDerivativeType = l_ifoDerivativeType;
    }

    /**
     * 市場IDを設定する。
     * 
     * @@param l_lngMarketId　@市場ID
     */
    public void setMarketId(long l_lngMarketId)
    {
        marketId = l_lngMarketId;
    }

    /**
     * 銘柄コードを設定する。
     * 
     * @@param l_strProductCode　@銘柄コード
     */
    public void setProductCode(String l_strProductCode)
    {
        productCode = l_strProductCode;
    }

    /**
     * 銘柄IDを設定する。
     * 
     * @@param l_lngProductId　@銘柄ID
     */
    public void setProductId(long l_lngProductId)
    {
        productId = l_lngProductId;
    }

    /**
     * 原資産銘柄コードを設定する。
     * 
     * @@param l_strUnderlyingProductCode　@原資産銘柄コード
     */
    public void setUnderlyingProductCode(String l_strUnderlyingProductCode)
    {
        underlyingProductCode = l_strUnderlyingProductCode;
    }

    /**
     * 指数乗数を設定する。
     * 
     * @@param l_dblUnitSize　@指数乗数
     */
    public void setUnitSize(double l_dblUnitSize)
    {
        unitSize = l_dblUnitSize;
    }
    
    /**
     * 売買最終日を設定する。
     * 
     * @@param l_datLastTradingDate　@売買最終日
     */           
    public void setLastTradingDate(Date l_datLastTradingDate)
    {
        lastTradingDate = l_datLastTradingDate;
    }

    /**
     * WEB3IfoProductの文字列表現を返す。
     * 
     * @@see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer l_sb = new StringBuffer();
        l_sb.append("WEB3IfoProduct={");
        l_sb.append("productId=").append(getProductId());
        l_sb.append(",marketId=").append(getMarketId());
        l_sb.append(",productCode=").append(getProductCode());
        l_sb.append(",underlyingProductCode=").append(getUnderlyingProductCode());
        l_sb.append(",ifoDerivativeType=").append(getIfoDerivativeType());
        l_sb.append(",unitSize=").append(getUnitSize());
        l_sb.append(",currentPrice=").append(getCurrentPrice());
        l_sb.append(",currentBizDateLiquidationPrice=").append(currentBizDateLiquidationPrice);
        l_sb.append(",lastTradingDate=").append(lastTradingDate);
        l_sb.append("}");
        return l_sb.toString();
    }
    
    /**
     * (is有効ポジション)<BR>
     * 
     * 該当銘柄が、ポジションとして有効であるか判定する。 <BR>
     *
     * 1）　@引数．証拠金計算条件．is証拠金SQ日銘柄ポジション非計上()＝falseの場合、trueを返す。 <BR>
     *      それ以外の場合、2）以降を実施する。 <BR>
     *　@
     * 2）証拠金計算基準日(*1) = 1の場合 <BR>
     *
     *   2-1）　@「 営業日[T+0](*2)　@<=  売買最終日(*3) + 1営業日 」の場合、trueを返す。 <BR>
     *   2-2）　@1-1）以外の場合、falseを返す。 <BR>
     *
     * 3）証拠金計算基準日 != 1の場合 <BR>
     * 
     *   3-1）　@「 営業日[T+0](*2)　@<=  売買最終日(*3) 」の場合の場合、trueを返す。 <BR>
     *   3-2）　@2-1）以外の場合、falseを返す。 <BR>
     * 
     * (*1) 証拠金計算基準日 ・・・　@引数．証拠金計算条件．get証拠金計算基準日 () <BR>
     * (*2) 営業日[T+0]         ・・・　@引数．証拠金計算条件．get営業日[T+0] <BR>
     * (*3) 売買最終日          ・・・　@this．売買最終日 <BR>
     *
     * [補足] <BR>
     * 証拠金SQ日銘柄ポジション非計上の部店(is証拠金SQ日銘柄ポジション非計上()＝true)の場合 <BR>
     * ポジションは、SQ日（売買最終日+1営業日）の日中時間帯まで有効である。 <BR>
     * よって、以下の場合に有効となる。 <BR>
     *
     * ・日中（証拠金計算基準日＝1）の場合 <BR>
　@   * →「営業日[T+0]　@<=  売買最終日 + 1営業日」は有効 <BR>
     *
     * ・夕場、翌注（証拠金計算基準日 != 1）の場合 <BR>
     * 　@→「営業日[T+0]　@<=  売買最終日」は有効 <BR>
     *
     * @@return boolean 
     * @@param l_objIfoDepositCalcCondition 証拠金計算条件
     * @@throws WEB3BaseException
     */           
    public boolean isPosition(WEB3IfoDepositCalcCondition l_objIfoDepositCalcCondition) throws WEB3BaseException
    {
        
        // 引数．証拠金計算条件．is証拠金SQ日銘柄ポジション非計上()＝falseの場合、trueを返す。
        if (l_objIfoDepositCalcCondition.isIfodepositNonCalcSqProductFlag() == false)
        {
            return true;
        }
        
        // 証拠金計算基準日を取得
        int l_ifoDepositCalcBaseDate = l_objIfoDepositCalcCondition.getIfoDepositBaseDate();
        
        // 営業日[T+0]の取得
        Date l_currentBizDate = l_objIfoDepositCalcCondition.getCurrentBizDate();
        
        // 売買最終日の翌営業日（SQ日）を取得
        Date l_sqDate = new WEB3GentradeBizDate(new Timestamp(this.lastTradingDate.getTime())).roll(1);
        
        // 1）証拠金計算基準日(*1) == 1の場合
        if ( l_ifoDepositCalcBaseDate == WEB3IfoReservedDateDef.T_1 )
        {
            // 1-1）「 営業日[T+0]  <=  売買最終日 + 1営業日 」の場合、trueを返す。            
            if ( WEB3DateUtility.compareToDay(l_currentBizDate, l_sqDate) <= 0 )
            {
                return true;
            }
            // 1-2）  1-1）以外の場合、falseを返す。
            else
            {
                return false;
            }
        }
        // 2）証拠金計算基準日 != 1の場合
        else
        {            
            // 2-1）  「 営業日[T+0]  <=  売買最終日 」の場合の場合、trueを返す。
            if ( WEB3DateUtility.compareToDay(l_currentBizDate, this.lastTradingDate) <= 0 )
            {
                return true;
            }            
            // 2-2）  2-1）以外の場合、falseを返す。
            else
            {
                return false;                
            }
        }
    }    

    // private methods ---------------------------------------------------------

    /**
     * (get時価)<BR>
     * 
     * 時価を取得する。 <BR>
     * 
     * 時価サービス(QuoteDataSupplierService)より、時価情報(IfoQuoteData）を取得する。 
     * <BR>
     * 以下の優先順位で、取得できる（0でない）単価を時価として返却する。 <BR>
     * 
     * １．現在値(IfoQuoteData.getCurrentPrice( )) <BR>
     * ２．売気配値(IfoQuoteData.getBidPrice( )) <BR>
     * ３．買気配値(IfoQuoteData.getAskPrice( )) <BR>
     * ４．清算値（引数.取引銘柄.清算値)<BR>
     *          引数.当日清算値==NULLの場合、 
     *          引数.先物OP取引銘柄.清算値。 
     *          以外は引数.当日清算値をセット。
     * @@param l_ifoTradedProduct - (取引銘柄)<BR>
     *                  取引銘柄オブジェクト。<BR>
     * @@param l_dblLiquidationPriceToday - (当日清算値)<BR>
     * @@return double
     * @@roseuid 4122BD3500EE
     */
    private double getCurrentPrice(IfoTradedProduct l_ifoTradedProduct,
        Double l_dblLiquidationPriceToday)
    {
        IfoQuoteDataSupplierService l_supplier =
            (IfoQuoteDataSupplierService) GtlUtils
                .getTradingModule(ProductTypeEnum.IFO)
                .getQuoteDataSupplierService();
        try
        {
            IfoQuoteData l_quoteData =
                l_supplier.getIfoQuote(l_ifoTradedProduct);

            log.debug("IfoQuoteData found.");

            if (l_quoteData.getCurrentPrice() != 0)
            {
                return l_quoteData.getCurrentPrice();
            } else if (l_quoteData.getBidPrice() != 0)
            {
                return l_quoteData.getBidPrice();
            } else if (l_quoteData.getAskPrice() != 0)
            {
                return l_quoteData.getAskPrice();
            }
        } catch (NotFoundException nfe)
        {
            log.error(
                "IfoQuoteData not found, use liquidation price with current price.",
                nfe);
        }
        if (l_dblLiquidationPriceToday != null){
        	return l_dblLiquidationPriceToday.doubleValue();
        }
        Object l_tradedProductRow = l_ifoTradedProduct.getDataSourceObject();
        if (l_tradedProductRow instanceof IfoTradedProductUpdqRow)
        {
            return ((IfoTradedProductUpdqRow) l_tradedProductRow)
                .getLiquidationPrice();
        } else
        {
            return ((IfoTradedProductRow) l_tradedProductRow)
                .getLiquidationPrice();
        }
    }

}
@
