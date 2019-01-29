head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradePasswordConvHandler.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 暗証番号変換ハンドラクラス(WEB3GentradePasswordConvHandler)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.handler;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvExpAccOpenResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvMainAccountResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvWeb2TransferResponse;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderRequest;
import webbroker3.gentrade.message.WEB3GentradePasswordConvSonarTraderResponse;
import webbroker3.gentrade.service.delegate.WEB3GentradePasswordConvService;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.handler.MessageHandler;

/**
 * 暗証番号変換ハンドラクラス<br />
 * @@author ＳＲＡ仲川
 */
public class WEB3GentradePasswordConvHandler implements MessageHandler
{
	/**
	 * ログ出力ユーティリティ
	 */
	private static WEB3LogUtility log =
		WEB3LogUtility.getInstance(WEB3GentradeBatoClientHandler.class);
    
	/**
	 * デフォルトコンストラクタ
	 */
	public WEB3GentradePasswordConvHandler() {} 

	/**
	 * 【顧客マスタ中間テーブル暗証番号更新】<br />
	 * SONAR暗号化形式の暗証番号を、xTrade-Hash，WEB3暗号化形式に変換し
	 * 中間テーブルに更新する。<br />
	 * 
	 * @@param l_request リクエストデータ
	 * @@return WEB3GentradePasswordConvMainAccountResponse
	 */
	public WEB3GentradePasswordConvMainAccountResponse convMainAccountBuffer(
		WEB3GentradePasswordConvMainAccountRequest l_request
		)
	{
		final String STR_METHOD_NAME = 
			" convMainAccountBuffer(WEB3GentradePasswordConvMainAccountRequest)";    
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvMainAccountResponse l_response = 
			(WEB3GentradePasswordConvMainAccountResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

    /**
     * 【口座開設見込客テーブル暗証番号更新】<br />
     * SONAR暗号化形式の暗証番号を、WEB3暗号化形式に変換し
     * 口座開設見込客テーブルに更新する。<br />
     * 
     * @@param l_request リクエストデータ
     * @@return WEB3GentradePasswordConvMainAccountResponse
     */
    public WEB3GentradePasswordConvExpAccOpenResponse convExpAccOpenBuffer(
        WEB3GentradePasswordConvExpAccOpenRequest l_request
        )
    {
        final String STR_METHOD_NAME = 
            " convExpAccOpenBuffer(WEB3GentradePasswordConvExpAccOpenRequest)";    
        log.entering(STR_METHOD_NAME);

        WEB3GentradePasswordConvExpAccOpenResponse l_response = 
            (WEB3GentradePasswordConvExpAccOpenResponse)this.serviceCall(
                l_request
                );

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

	/**
	 * 【口座開設 暗証番号伝票（G5511）更新】<br />
	 * WEB3暗号化形式の暗証番号を復号化し、中間テーブルに更新する。<br />
	 * 
	 * @@param l_request リクエストデータ
	 * @@return WEB3GentradePasswordConvAccOpenResponse
	 */
	public WEB3GentradePasswordConvAccOpenResponse convAccOpenBuffer(
		WEB3GentradePasswordConvAccOpenRequest l_request
		) throws WEB3BaseException
	{
		final String STR_METHOD_NAME = 
		" convAccOpenBuffer(WEB3GentradePasswordConvAccOpenRequest)";
		log.entering(STR_METHOD_NAME);
		
		WEB3GentradePasswordConvAccOpenResponse l_response = 
			(WEB3GentradePasswordConvAccOpenResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
	
	/**
	 * 【WEB2暗証番号データ移管】<br />
	 * WEB2暗号化形式の暗証番号を復号化し、中間テーブルに更新する。<br />
	 * 
	 * @@param l_request リクエストデータ
	 * @@return WEB3GentradePasswordConvWeb2TransferResponse
	 */
	public WEB3GentradePasswordConvWeb2TransferResponse transfer(
		WEB3GentradePasswordConvWeb2TransferRequest l_request)
	{
		final String STR_METHOD_NAME = 
		" transfer(WEB3GentradePasswordConvWeb2TransferRequest)";
		log.entering(STR_METHOD_NAME);
		
		WEB3GentradePasswordConvWeb2TransferResponse l_response = 
			(WEB3GentradePasswordConvWeb2TransferResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}

	/**
	 * 関連サービスをコールする
	 * 
	 * @@param l_request リクエストデータ
	 * @@return WEB3BackResponse
	 */
	private WEB3BackResponse serviceCall(WEB3BackRequest l_request)
	{
		WEB3GentradePasswordConvService l_service = null;
		WEB3BackResponse l_response = null;
        
		try
		{
			l_service = (WEB3GentradePasswordConvService)Services.getService(
					WEB3GentradePasswordConvService.class
					);
		} catch (Exception l_ex)
		{
			l_response = l_request.createResponse(); 
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(
				l_request,
				"暗証番号変換サービスの取得に失敗しました。",
				l_response.errorInfo,
				l_ex);
			return l_response;   
		}
        
		try
		{
			l_response = (WEB3BackResponse)l_service.execute(l_request);
		} catch (WEB3BaseException l_ex)
		{
			l_response = (WEB3BackResponse)l_request.createResponse();
			l_response.errorInfo = l_ex.getErrorInfo();
			log.error(l_request, 
				"暗証番号変換サービスに失敗しました。",
				l_ex);
			return l_response;
		} catch (Exception l_ex) 
		{
			l_response = (WEB3BackResponse)l_request.createResponse();
			l_response.errorInfo = WEB3ErrorCatalog.SYSTEM_ERROR_80002;
			log.error(
				l_request,
				"暗証番号変換サービスに失敗しました。",
				l_response.errorInfo,
				l_ex);
		}
		return l_response;
	}

	/**
     * 【中間テーブル（扱者情報）暗証番号追加】
     * 中間テーブル（扱者情報）内のSONAR扱者コードをxTrade、WEB3暗号化形式に変換
     * し、対応項目を更新（DB-update）する。
	 * 
	 * @@param l_request リクエストデータ
     * @@return WEB3GentradePasswordConvSonarTraderResponse
	 */
    public WEB3GentradePasswordConvSonarTraderResponse convSonarTrader(
		WEB3GentradePasswordConvSonarTraderRequest l_request)
	{
		final String STR_METHOD_NAME =
		" convSonarTrader(WEB3GentradePasswordConvSonarTraderRequest)";
		log.entering(STR_METHOD_NAME);

		WEB3GentradePasswordConvSonarTraderResponse l_response = 
			(WEB3GentradePasswordConvSonarTraderResponse)this.serviceCall(
				l_request
				);

		log.exiting(STR_METHOD_NAME);
		return l_response;
	}
}
@
