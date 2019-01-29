head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.55.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccOrderServiceInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文発注サービスインタセプタ(WEB3ToSuccOrderServiceInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/13 凌建平(中訊) 新規作成
Revesion History : 2008/05/05 劉剣(中訊) 仕様変更　@モデルNo.314
*/

package webbroker3.triggerorder;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.util.WEB3LogUtility;

/**
 * (連続注文発注サービスインタセプタ)<BR>
 * 連続注文発注サービスインタセプタ。
 * <BR>
 * @@author 凌建平 <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccOrderServiceInterceptor implements Interceptor 
{
    /**
     * ログオブジェクト
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3ToSuccOrderServiceInterceptor.class);
    
    /**
     * @@roseuid 4348DAA80232
     */
    public WEB3ToSuccOrderServiceInterceptor() 
    {
     
    }
    
    /**
     * サービスメソッド開始時にコールされる。<BR>
     * <BR>
     * －口座をロックする。  <BR>
     * 　@　@拡張アカウントマネージャ.lock口座(<BR>
     * 　@　@　@　@顧客.証券会社コード, 顧客.部店コード, 顧客.口座コード)をコールする。<BR>
     * @@param l_method
     * @@param l_serviceParams
     * @@return Object
     * @@roseuid 43216E380084
     */
    public Object onCall(Method l_method, Object[] l_serviceParams) 
    {
        final String STR_METHOD_NAME = "onCall(Method, Object[])";
        log.entering(STR_METHOD_NAME);

        if (l_serviceParams[0] == null)
        {   
            log.error("パラメータ値不正。");            
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);   
        }
        try
        {
            WEB3GentradeSubAccount l_subAccount = (WEB3GentradeSubAccount)l_serviceParams[0];
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            //口座マネージャを取得する
            WEB3GentradeAccountManager l_accountMananger =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            MainAccount l_mainAcc = l_subAccount.getMainAccount();

            //－口座をロックする。
            //  拡張アカウントマネージャ.lock口座(
            //      顧客.証券会社コード, 顧客.部店コード, 顧客.口座コード)をコールする。
            l_accountMananger.lockAccount(
                l_mainAcc.getInstitution().getInstitutionCode(),
                l_mainAcc.getBranch().getBranchCode(),
                l_mainAcc.getAccountCode());

        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                l_ex.getErrorInfo(),
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
        return null;
    }

    /**
     * サービスメソッド終了時にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_context
     * @@param l_returnValue
     * @@roseuid 43216E380094
     */
    public void onReturn(Object l_context, Object l_returnValue) 
    {

    }
    
    /**
     * サービスメソッドが例外をスローした場合にコールされる。<BR>
     * <BR>
     * ※特に何もしない。<BR>
     * @@param l_obj
     * @@param l_throwable
     * @@roseuid 43216E3800B3
     */
    public void onThrowable(Object l_obj, Throwable l_throwable) 
    {

    }
}
@
