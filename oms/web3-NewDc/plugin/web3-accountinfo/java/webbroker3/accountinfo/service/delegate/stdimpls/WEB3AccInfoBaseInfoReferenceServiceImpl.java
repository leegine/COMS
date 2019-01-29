head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.22.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoBaseInfoReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報基本情報照会サービス実装クラス(WEB3AccInfoBaseInfoReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/11 李海波 (中訊) 新規作成
*/

package webbroker3.accountinfo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.accountinfo.WEB3AccInfoClientRequestService;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfo;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceRequest;
import webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoBaseInfoReferenceService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * (お客様情報基本情報照会サービスImpl)<BR>
 * お客様情報基本情報照会サービス実装クラス<BR>
 * 
 * @@author 李海波
 * @@version 1.0
 */
public class WEB3AccInfoBaseInfoReferenceServiceImpl extends WEB3AccInfoClientRequestService 
    implements WEB3AccInfoBaseInfoReferenceService 
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoBaseInfoReferenceServiceImpl.class);
        
    /**
     * @@roseuid 418F3A060271
     */
    public WEB3AccInfoBaseInfoReferenceServiceImpl() 
    {
     
    }
    
    /**
     * 顧客基本情報照会処理を行う。<BR>
     * <BR>
     * －get顧客基本情報()をコールする。<BR>
     * @@param l_request - リクエスト
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4163B5F501E7
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);
        
        WEB3AccInfoAccountBaseInfoReferenceResponse l_response;
        if(l_request instanceof WEB3AccInfoAccountBaseInfoReferenceRequest)
        {
            l_response = this.getAccountBaseInfo((WEB3AccInfoAccountBaseInfoReferenceRequest)l_request);
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
    
    /**
     * (get顧客基本情報)<BR>
     * 基本情報照会処理を行う。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「お客様情報（基本情報照会）get顧客基本情報」参照。 <BR>
     * @@param l_request - お客様情報基本情報照会リクエストデータオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoAccountBaseInfoReferenceResponse
     * @@roseuid 4163B5F501F6
     */
    protected WEB3AccInfoAccountBaseInfoReferenceResponse 
        getAccountBaseInfo(WEB3AccInfoAccountBaseInfoReferenceRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getAccountBaseInfo(WEB3AccInfoAccountBaseInfoReferenceRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1) validate注文受付可能( )
        WEB3GentradeTradingTimeManagement.validateOrderAccept();
        
        //2) get顧客( )
        WEB3GentradeMainAccount l_gentradeMainAccount = (WEB3GentradeMainAccount)getMainAccount();
        
        //3) create顧客基本情報(顧客)
        WEB3AccInfoAccountBaseInfoCreatedService l_service = 
            (WEB3AccInfoAccountBaseInfoCreatedService)Services.getService(WEB3AccInfoAccountBaseInfoCreatedService.class);
        WEB3AccInfoAccountBaseInfo l_accInfoAccountBaseInfo = 
            l_service.createAccountBaseInfo(l_gentradeMainAccount);
        
        //4) お客様情報基本情報照会レスポンス(WEB3GenRequest)
        WEB3AccInfoAccountBaseInfoReferenceResponse l_response = 
            (WEB3AccInfoAccountBaseInfoReferenceResponse)l_request.createResponse();
            
        //5) プロパティセット
        l_response.accountBaseInfo = l_accInfoAccountBaseInfo;
        
        log.exiting(STR_METHOD_NAME);
        return l_response; 
    }
}
@
