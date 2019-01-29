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
filename	WEB3MarginSwapMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡リクエストアダプタ(WEB3MarginSwapMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/20 凌建平 (中訊) 新規作成
*/

package webbroker3.equity.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityPositionManager;
import webbroker3.equity.message.WEB3MarginSwapMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginSwapMarginConfirmRequest;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡リクエストアダプタ）。<BR>
 * <BR>
 * 信用取引現引現渡リクエストアダプタクラス
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3MarginSwapMarginRequestAdapter 
{
    
    /**
     * (リクエストデータ)。<BR>
     */
    public WEB3GenRequest request;
    
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MarginSwapMarginRequestAdapter.class);
    
    /**
     * (コンストラクタ)。<BR>
     * @@roseuid 4142B32B03A9
     */
    protected WEB3MarginSwapMarginRequestAdapter() 
    {
    }
    
    /**
     * (create)。<BR>
     * <BR>
     * 信用取引現引現渡リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * ２）　@生成したインスタンスに引数のリクエストデータをセットする。<BR>
     * ３）　@インスタンスを返却する。<BR>
     * <BR>
     * （デフォルトコンストラクタはprivateとし、<BR>
     * 本メソッドによってインスタンス化するように<BR>
     * 制限する）<BR>
     * <BR>
     * @@param l_request - リクエストデータオブジェクト<BR>
     * @@return WEB3MarginSwapMarginRequestAdapter<BR>
     * @@roseuid 40BBD8680230
     */
    public static WEB3MarginSwapMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        // 本インスタンスを生成しする
        WEB3MarginSwapMarginRequestAdapter l_adapter = new WEB3MarginSwapMarginRequestAdapter();
        l_adapter.request = l_request;

        // インスタンスを返却する
        return l_adapter;
    }
    
    /**
     * (get税区分（現引現渡）)。<BR>
     * <BR>
     * リクエストデータ.現引現渡口座区分より、AP層で使用する税区分（現引現渡）を返却する。 <BR>
     * <BR>
     * １） 一般口座のセット <BR>
     * 　@・リクエストデータ.現引現渡口座区分が”一般”の場合、TaxTypeEnum.”一般”を返却する。 <BR>
     * <BR>
     * ２）　@特定口座のセット <BR>
     * 　@・リクエストデータ.現引先現渡元口座区分が”特定”の場合、 TaxTypeEnum.”特定”を返却する。<BR>
     * <BR>
     * @@return TaxTypeEnum<BR>
     * @@roseuid 40BBD8680232
     */
    public TaxTypeEnum getSwapTaxType()
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "getSwapTaxType( )";
        log.entering(STR_METHOD_NAME);
        String l_strSwapTaxType = null;
        
        if (this.request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            l_strSwapTaxType = ((WEB3MarginSwapMarginConfirmRequest)this.request).swapTaxType;
        }
        else if (this.request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            l_strSwapTaxType = ((WEB3MarginSwapMarginCompleteRequest)this.request).swapTaxType;
        }

        TaxTypeEnum l_taxTypeEnum = null;

        //リクエストデータ.口座区分が”一般”の場合、TaxTypeEnum.”一般”を返却する。
        if (WEB3TaxTypeSpecialDef.NORMAL.equals(l_strSwapTaxType))
        {
            l_taxTypeEnum = TaxTypeEnum.NORMAL;

        }
        //２）　@特定口座のセット
        else if (WEB3TaxTypeSpecialDef.SPECIAL.equals(l_strSwapTaxType))
        {  
			l_taxTypeEnum = TaxTypeEnum.SPECIAL;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_taxTypeEnum;
    }
    
    /**
     * (get注文株数)。<BR>
     * <BR>
     * リクエストデータ.決済順序区分、およびリクエストデータ.注文株数より、<BR>
     * AP層で使用する注文株数を返却する。<BR>
     * <BR>
     * １）　@リクエストデータ.決済順序区分が”ランダム”の場合、0を返却する。<BR>
     * <BR>
     * ２）　@リクエストデータ.決済順序区分が”ランダム”以外の場合、<BR>
     * リクエストデータ.注文株数のdouble値を返却する。<BR>
     * <BR>
     * @@return double
     * @@roseuid 40BFB5A1009D
     */
    public double getOrderQuantity() 
    {
        String l_strOrderQuantity = null;
        String l_strClsOdrDef = null;

        //--------------------
        //注文株数・決済順序を取得
        //--------------------
        if (this.request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            WEB3MarginSwapMarginConfirmRequest l_cnfRequest
                = (WEB3MarginSwapMarginConfirmRequest)this.request;
            l_strOrderQuantity = l_cnfRequest.orderQuantity;
            l_strClsOdrDef = l_cnfRequest.closingOrder;
        }
        else if (this.request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            WEB3MarginSwapMarginCompleteRequest l_cmpRequest
                = (WEB3MarginSwapMarginCompleteRequest)this.request;
            l_strOrderQuantity = l_cmpRequest.orderQuantity;
            l_strClsOdrDef = l_cmpRequest.closingOrder;
        }

        //--------------------
        //決済順序がランダムの場合は０
        //それ以外は注文株数を返す。
        //--------------------
        if (WEB3ClosingOrderDef.RANDOM.equals(l_strClsOdrDef))
        {
            return 0.0;
        }
        else
        {
            return Double.parseDouble(l_strOrderQuantity);
        }
    }
    
    /**
     * (get建株)<BR>
     * 建株オブジェクトを返却する。<BR>
     * <BR>
     * リクエスト.決済建株一覧[0].IDに該当する建株を<BR>
     * 取得し、返却する。<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     */
    public WEB3EquityContract getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getContract()";
        log.entering(STR_METHOD_NAME);
        

        String l_strId = null;
        if (this.request instanceof WEB3MarginSwapMarginConfirmRequest)
        {
            l_strId =
                ((WEB3MarginSwapMarginConfirmRequest)this.request).closeMarginContractUnits[0].id;
        }
        else if (this.request instanceof WEB3MarginSwapMarginCompleteRequest)
        {
            l_strId =
                ((WEB3MarginSwapMarginCompleteRequest)this.request).closeMarginContractUnits[0].id;
        }
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule =
            l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
        WEB3EquityPositionManager l_positionManager =
            (WEB3EquityPositionManager)l_tradingModule.getPositionManager();
        WEB3EquityContract l_contract = null;
        try
        {
            l_contract =
                (WEB3EquityContract)l_positionManager.getContract(
                    Long.parseLong(l_strId));
        }
        catch (NotFoundException l_nfe)
        {
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
}
@
