head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondDescendRacCtxInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券下り処理RACコンテキストインタセプタ(WEB3BondDescendRacCtxInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/28 齊珂 (中訊) 新規作成
*/

package webbroker3.bd;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券下り処理RACコンテキストインタセプタ)<BR>
 * 債券下り処理RACコンテキストインタセプタクラス
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondDescendRacCtxInterceptor implements Interceptor
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondDescendRacCtxInterceptor.class);
    
    /**
     * (VID)<BR>
     * VID
     */
    private static long VID = -1;
    
    /**
     * RACコンテキストを設定する。 <BR>
     *  <BR>
     * １）口座IDを宣言し、初期化する。 <BR> 
     * 　@　@口座ID = VID  <BR>
     *      <BR>
     * ２）サービスの引数を判定し、口座IDをセット <BR> 
     *　@　@２－１）サービスの引数.length > 0 かつ サービスの引数[0] instanceof OrderUnitの場合 <BR> 
     *　@　@　@２－１－１）サービスの引数[0]を注文単位オブジェクトにキャストする。  <BR>
     *　@　@　@２－１－２）口座ID = 注文単位.get口座ID()  <BR>
     *  <BR>
     * ３）RAC Contextの設定 <BR> 
     *　@　@３－１）口座IDが0より大きい場合 <BR> 
     *　@　@　@３－１－１）RAC Context サービス(WEB3DescendRacCtxService)を取得する。 <BR> 
     *　@　@　@３－１－２）取得したサービスがnullでない場合、取得したサービス.setAccountIdCtx(口座ID)をコールする <BR> 
     * 　@　@　@　@　@　@　@　@[引数]  <BR>
     * 　@　@　@　@　@　@　@　@口座ID＝口座ID <BR> 
     * @@param l_method - (サービスメソッド)<BR>
     * サービスメソッド<BR>
     * @@param l_serviceParam - (サービスメソッド引数)<BR>
     * サービスメソッド引数<BR>
     * @@return Object                                 
     */
    public Object onCall(Method l_method, Object[] l_serviceParam) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_serviceParam == null)
        {
            log.debug("パラメータNull出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //１）口座IDを宣言し、初期化する。 
        //口座ID = VID 
        long l_lngAccountId = this.VID;
        
        //２）サービスの引数を判定し、口座IDをセット 
        //２－１）サービスの引数.length > 0 かつ サービスの引数[0] instanceof OrderUnitの場合
        if (l_serviceParam.length > 0 && l_serviceParam[0] instanceof OrderUnit)
        {
        	//２－１－１）サービスの引数[0]を注文単位オブジェクトにキャストする。
        	OrderUnit l_orderUnit = (OrderUnit)l_serviceParam[0];
        	
        	//２－１－２）口座ID = 注文単位.get口座ID() 
        	l_lngAccountId = l_orderUnit.getAccountId();
        }
        
        //３）RAC Contextの設定 
        WEB3DescendRacCtxService l_descendRacCtxService = null;
        //３－１）口座IDが0より大きい場合 
        if (l_lngAccountId > 0)
        {
            //　@３－１－１）RAC Context サービス(WEB3DescendRacCtxService)を取得する。 
        	l_descendRacCtxService = 
        		(WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
        	
            if (l_descendRacCtxService != null)
            {
                //３－１－２）取得したサービスがnullでない場合、
            	// 取得したサービス.setAccountIdCtx(口座ID)をコールする 
                //　@[引数] 
                //  口座ID＝口座ID 
            	l_descendRacCtxService.setAccountIdCtx(l_lngAccountId);
            }
        }
               
        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * アイテムの定義<BR>
     * RACコンテキストをクリアする。<BR> 
     * <BR>
     * 　@１）RAC Context サービス(WEB3DescendRacCtxService)を取得する。<BR> 
     *   ２）取得したサービスがnullでない場合、取得したサービス.clearAccountIdCtx()を呼ぶ<BR> 
     * @@param l_context - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_returnValue - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {
        final String STR_METHOD_NAME = "onReturn(Object, Object)";
        log.entering(STR_METHOD_NAME);
        
        //１）RAC Context サービス(WEB3DescendRacCtxService)を取得する。 
    	WEB3DescendRacCtxService l_descendRacCtxService = 
    		(WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
    	
    	//２）取得したサービスがnullでない場合、取得したサービス.clearAccountIdCtx()を呼ぶ
    	if (l_descendRacCtxService != null)
    	{
        	l_descendRacCtxService.clearAccountIdCtx();
    	}
    	
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * RACコンテキストをクリアする。<BR> 
     * <BR>
     * 　@１）RAC Context サービス(WEB3DescendRacCtxService)を取得する。<BR> 
     * 　@２）取得したサービスがnullでない場合、取得したサービス.clearAccountIdCtx()を呼ぶ<BR> 
     * @@param l_obj - (onCallリターン値)<BR>
     * onCallリターン値<BR>
     * @@param l_throwable - (サービスメソッドリターン値)<BR>
     * サービスメソッドリターン値<BR>
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {
        final String STR_METHOD_NAME = "onThrowable(Object, Throwable)";
        log.entering(STR_METHOD_NAME);
        
        //１）RAC Context サービス(WEB3DescendRacCtxService)を取得する。 
    	WEB3DescendRacCtxService l_descendRacCtxService = 
    		(WEB3DescendRacCtxService) Services.getService(WEB3DescendRacCtxService.class);
    	
    	//２）取得したサービスがnullでない場合、取得したサービス.clearAccountIdCtx()を呼ぶ
    	if (l_descendRacCtxService != null)
    	{
    	    l_descendRacCtxService.clearAccountIdCtx();
    	}
    	
        log.exiting(STR_METHOD_NAME);
    }

}
@
