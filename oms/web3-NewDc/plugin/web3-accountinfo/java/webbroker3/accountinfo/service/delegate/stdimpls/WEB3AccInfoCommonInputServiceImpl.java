head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoCommonInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報共通入力サービスImpl(WEB3AccInfoCommonInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoCommonInputRequest;
import webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommonInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報共通入力サービスImpl)<BR>
 * お客様情報共通入力サービス実装クラス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AccInfoCommonInputServiceImpl extends WEB3AccInfoClientRequestService implements WEB3AccInfoCommonInputService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoCommonInputServiceImpl.class);
    
    /**
     * @@roseuid 418F39FD01E4
     */
    public WEB3AccInfoCommonInputServiceImpl() 
    {
     
    }
    
    /**
     * 入力画面表示共通処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、<BR>以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、お客様情報共通入力リクエストの場合 <BR>
     * 　@－get入力画面()をコールする。 <BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41456FFE0191
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AccInfoCommonInputRequest)
        {
            l_response = getInputScreen((WEB3AccInfoCommonInputRequest)l_request);
        }
        else
        {
            log.error("パラメータタイプ不正。");

            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME,
                "[l_request] = " + l_request
                );
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get入力画面)<BR>
     * 入力画面表示共通処理を実施する。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「お客様情報（共通）get入力画面」参照。<BR>
     * 
     * @@param l_request - お客様情報共通入力リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoCommonInputResponse
     * @@roseuid 41456FC300F5
     */
    protected WEB3AccInfoCommonInputResponse getInputScreen(WEB3AccInfoCommonInputRequest l_request) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getInputScreen(WEB3AccInfoCommonInputRequest)";
        log.entering(STR_METHOD_NAME);
        
        //受付時間チェックを行う。  
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        WEB3AccInfoCommonInputResponse l_response = (WEB3AccInfoCommonInputResponse)l_request.createResponse();
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
