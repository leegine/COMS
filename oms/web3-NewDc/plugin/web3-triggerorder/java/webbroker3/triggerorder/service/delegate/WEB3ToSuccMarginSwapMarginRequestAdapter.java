head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.04.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccMarginSwapMarginRequestAdapter.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）信用取引現引現渡リクエストアダプタ(WEB3ToSuccMarginSwapMarginRequestAdapter.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/08 沈迪(中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginRequestAdapter;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccMarginSwapConfirmRequest;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3LogUtility;

/**
 * (（連続）信用取引現引現渡リクエストアダプタ)<BR>
 * （連続）信用取引現引現渡リクエストアダプタ。<BR>
 * 
 * @@author 沈迪
 * @@version 1.0
 */
public class WEB3ToSuccMarginSwapMarginRequestAdapter extends WEB3MarginSwapMarginRequestAdapter 
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccMarginSwapMarginRequestAdapter.class);
    
    /**
     * (親注文の注文単位)<BR>
     * 親注文の注文単位オブジェクト<BR>
     */
    public EqTypeOrderUnit parentOrderUnit;
    
    /**
     * @@roseuid 436ACF6301B5
     */
    public WEB3ToSuccMarginSwapMarginRequestAdapter() 
    {
     
    }
    
    /**
     * （連続）信用取引現引現渡リクエストアダプタインスタンスを生成する。<BR>
     * <BR>
     * １）　@本インスタンスを生成しする。<BR>
     * <BR>
     * ２）　@親注文の注文単位オブジェクトを取得する。<BR>
     * 　@　@連続注文マネージャImpl.get株式親注文の注文単位(<BR>
     * 　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。<BR>
     * <BR>
     * ３）　@生成したインスタンスに、引数のリクエストデータ、<BR>
     * 　@　@及び取得した親注文の注文単位オブジェクトをセットする。<BR>
     * <BR>
     * ４）　@インスタンスを返却する。<BR>
     * <BR>
     * @@param l_request - (リクエスト)<BR>
     * リクエストデータオブジェクト。<BR>
     * @@return WEB3MarginSwapMarginRequestAdapter
     * @@roseuid 4344DC080153
     */
    public static WEB3MarginSwapMarginRequestAdapter create(WEB3GenRequest l_request) 
    {
        final String STR_METHOD_NAME = " create(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@本インスタンスを生成しする。
        WEB3ToSuccMarginSwapMarginRequestAdapter l_adapter = new WEB3ToSuccMarginSwapMarginRequestAdapter();
        
        //２）　@親注文の注文単位オブジェクトを取得する。
        //　@　@連続注文マネージャImpl.get株式親注文の注文単位(
        //　@　@リクエスト.連続注文共通情報.（親注文）注文ID)をコールする。
        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (l_request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) l_request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (l_request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) l_request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginSwapMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }

        WEB3ToSuccOrderManagerImpl l_orderManager = 
            (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
        long l_lngParentOrderId = Long.parseLong(l_succCommonInfo.parentOrderId);
        EqTypeOrderUnit l_orderUnit = l_orderManager.getEqtypeParentOrderUnit(l_lngParentOrderId);
        
        //３）　@生成したインスタンスに、引数のリクエストデータ、
        //　@　@及び取得した親注文の注文単位オブジェクトをセットする。
        l_adapter.request = l_request;
        l_adapter.parentOrderUnit = l_orderUnit;
        
        //４）　@インスタンスを返却する。
        log.exiting(STR_METHOD_NAME);   
        return l_adapter;
    }
    
    /**
     * (get建株)<BR>
     * 建株オブジェクトを返却する。<BR>
     * （継承元クラスの同名メソッドのオーバーライド）<BR>
     * <BR>
     * １）　@this.is反対売買() == trueの場合、<BR>
     * 　@連続注文マネージャImpl.create建株()をコールし、<BR>
     * 　@戻り値を返却する。<BR>
     * <BR>
     * 　@[create建株()に指定する引数]<BR>
     * 　@　@注文単位：　@this.親注文の注文単位<BR>
     * 　@　@<BR>
     * ２）　@既存残に対する現引現渡（１）以外）の場合、<BR>
     * 　@super.get建株()をコールし、戻り値を返却する。<BR>
     * @@return WEB3EquityContract
     * @@throws WEB3BaseException
     * @@roseuid 4344DD1D01C0
     */
    public WEB3EquityContract getContract() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getContract() ";
        log.entering(STR_METHOD_NAME);
        
        WEB3EquityContract l_contract = null;
        
        //１）　@this.is反対売買() == trueの場合、
        //　@連続注文マネージャImpl.create建株()をコールし、
        //　@戻り値を返却する。
        if (this.isReversingOrder())
        {
            WEB3ToSuccOrderManagerImpl l_orderManager = 
                (WEB3ToSuccOrderManagerImpl) WEB3TriggerOrderTradingModel.getOrderManager();
            
            l_contract = l_orderManager.createContract(this.parentOrderUnit);
        }
        //２）　@既存残に対する現引現渡（１）以外）の場合、
        //　@super.get建株()をコールし、戻り値を返却する。
        else
        {
            l_contract = super.getContract();
        }
        log.exiting(STR_METHOD_NAME);
        return l_contract;
    }
    
    /**
     * (is反対売買)<BR>
     * 反対売買かどうか判別する。<BR>
     * <BR>
     * リクエストデータ.連続注文共通情報.連続注文取引区分=="現引現渡（前提注文）"の場合は、<BR>
     * trueを返却する。<BR>
     * <BR>
     * 以外、falseを返却する。<BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 4344DD31000B
     */
    public boolean isReversingOrder() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isReversingOrder() ";
        log.entering(STR_METHOD_NAME);

        WEB3SuccCommonInfo l_succCommonInfo = null;
        if (super.request instanceof WEB3SuccMarginSwapConfirmRequest)
        {
            WEB3SuccMarginSwapConfirmRequest l_confirmRequest = 
                (WEB3SuccMarginSwapConfirmRequest) super.request;
            l_succCommonInfo = l_confirmRequest.succCommonInfo;
        }
        else if (super.request instanceof WEB3SuccMarginSwapCompleteRequest)
        {
            WEB3SuccMarginSwapCompleteRequest l_completeRequest = 
                (WEB3SuccMarginSwapCompleteRequest) super.request;
            l_succCommonInfo = l_completeRequest.succCommonInfo;
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                "WEB3ToSuccMarginSwapMarginRequestAdapter" + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        if (WEB3ReserveOrderTradingTypeDef.SWAP_CONTRACT_ASSUMPTION_ORDER.equals(
            l_succCommonInfo.succTradingType))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }
}
@
