head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionContractInquiryHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : OP建玉照会ハンドラ(WEB3OptionContractInquiryHandler.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/13 張威 (中訊) 新規作成
              001: 2004/07/26 王暁傑 (中訊) 対応バッグ IFO_UT-000015 logの定義を修正、getContract()を修正
*/

package webbroker3.ifo.handler;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.util.WEB3LogUtility;
import webbroker3.ifo.service.delegate.WEB3OptionContractInquiryService;
import webbroker3.ifo.message.WEB3OptionsContractReferenceResponse;
import webbroker3.ifo.message.WEB3OptionsContractReferenceRequest;

/**
 * (OP建玉照会ハンドラ)<BR>
 * 株価指数オプション建玉照会ハンドラクラス<BR>
 * @@author 張威
 * @@version 1.0
 */
public class WEB3OptionContractInquiryHandler implements MessageHandler
{

    /**
     * ログ出力ユーティリティ。
     */

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionContractInquiryHandler.class);    

    /**
     * @@roseuid 40C0754D037A
     */
    public WEB3OptionContractInquiryHandler()
    {

    }

    /**
     * (get建玉)<BR>
     * 株価指数オプション建玉照会処理を行う。<BR>
     * <BR>
     * 株価指数オプション建玉照会サービスを取得し、<BR>
     * execute()メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 株価指数オプション建玉照会リクエストオブジェクト<BR>
     * @@return webbroker3.ifo.message.WEB3OptionsContractReferenceResponse
     * @@roseuid 4057F9AE02C1
     */
    public WEB3OptionsContractReferenceResponse getContract(WEB3OptionsContractReferenceRequest l_request)
    {       
        final String STR_METHOD_NAME =
            getClass().getName() + ".getContract(WEB3OptionsContractReferenceRequest l_request)";           

        
        log.debug(STR_METHOD_NAME + " : ENTER");

        WEB3OptionsContractReferenceResponse l_response = null;
        WEB3OptionContractInquiryService l_service = null;

        try
        {
            //株価指数オプション建玉照会サービスオブジェクトを取得する
            log.debug("株価指数オプション建玉照会サービスオブジェクト: ENTER");

            l_service =
                (WEB3OptionContractInquiryService)Services.getService(
                    WEB3OptionContractInquiryService.class);
            log.debug("株価指数オプション建玉照会サービスオブジェクト: END");
        }
        //サービスで例外が発生した場合は、エラー情報をレスポンスメッセージに設定する
        catch (Exception l_ex)
        {
            l_response = (WEB3OptionsContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
            log.error(l_request, "株価指数オプション建玉照会サービスの取得に失敗しました。", l_response.errorInfo, l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        try
        {
            //株価指数オプション建玉照会サービスオブジェクト.execute（）をコールし、
            //処理結果であるレスポンスオブジェクトを取得する。
            log.debug("株価指数オプション建玉照会サービスオブジェクト.execute（): ENTER");
            l_response = (WEB3OptionsContractReferenceResponse)l_service.execute(l_request);
            log.debug("株価指数オプション建玉照会サービスオブジェクト.execute（): END");
        }
        catch (WEB3BaseException l_ex)
        {
            l_response = (WEB3OptionsContractReferenceResponse)l_request.createResponse();
            l_response.errorInfo = l_ex.getErrorInfo();
            log.error(l_request, "株価指数オプション建玉照会に失敗しました。", l_ex);
            log.debug(STR_METHOD_NAME + " : END");
            return l_response;
        }

        log.debug(STR_METHOD_NAME + " : END");

        //レスポンスオブジェクトを返却する。
        return l_response;
    }
}
@
