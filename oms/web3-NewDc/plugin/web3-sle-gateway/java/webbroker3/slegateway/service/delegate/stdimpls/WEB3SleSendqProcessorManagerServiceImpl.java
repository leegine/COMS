head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.00.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleSendqProcessorManagerServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : SleSendqProcessorManagerServiceImplクラス
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/04/24 李 新規作成
 Revision History : 2006/06/08 李(FLJ)　@innerクラスを別クラスに移転 
 Revision History : 2006/06/09 李(FLJ)  ソース精査
 Revision History : 2006/09/20 李(FLJ)  WEB3実装方針にあわせ
 Revision History : 2006/10/26 李(FLJ)  同期化スレッドで実装するため改修 
 */
package webbroker3.slegateway.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import webbroker3.util.WEB3LogUtility;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.slegateway.message.WEB3ProcessSleSendqRequest;
import webbroker3.slegateway.message.WEB3ProcessSleSendqResponse;
import webbroker3.slegateway.service.delegate.WEB3SleSendqProcessorManagerService;
import webbroker3.gentrade.WEB3GentradeDaemonTriggerManager;
import webbroker3.system.tune.threadpool.WEB3AsynExecuteService;//2006/10/26 非同期化のため追加


/**
 *　@SEND_Qメッセージ送信管理メインクラス
 */
public class WEB3SleSendqProcessorManagerServiceImpl implements WEB3SleSendqProcessorManagerService
{
	/**
	 * ログ出力ユーティリティ
	 */
	private static final WEB3LogUtility m_log = WEB3LogUtility.getInstance(WEB3AsynSleSendqProcessorManagerServiceImpl.class);//←修正 2006/10/11

	/**
	 * デバッグログ出力フラグ
	 */
	private static final boolean DBG = m_log.ison();
	
	/**
	 * デフォルトコンストラクタ。<BR>
	 */
	public WEB3SleSendqProcessorManagerServiceImpl()
	{
	}

	
    /* (非 Javadoc)
     * @@see webbroker3.common.service.delegate.WEB3BusinessService#execute(webbroker3.common.message.WEB3GenRequest)
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
    	
		final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
		m_log.entering(STR_METHOD_NAME);
		
		if (l_request == null)
		{
			m_log.debug("リクエストが未指定(null)です。");
			m_log.exiting(STR_METHOD_NAME);
			throw new WEB3BaseRuntimeException(
				WEB3ErrorCatalog.SYSTEM_ERROR_80017,
				this.getClass().getName() + STR_METHOD_NAME, 
				"リクエストが未指定(null)です。");
		}
		
		// リクエストキャスタ
		WEB3ProcessSleSendqRequest l_sleSendQRequest =
			(WEB3ProcessSleSendqRequest)l_request;
			
		// スレッド開始 追加 2006/10/26
		new WEB3GentradeDaemonTriggerManager().startThread(l_sleSendQRequest.threadNo.longValue());
		
		// 非同期実行
		WEB3AsynExecuteService l_service =
			(WEB3AsynExecuteService) Services.getService(WEB3AsynExecuteService.class);
		l_service.execute(new WEB3AsynSleSendqProcessorManagerServiceImpl(
			l_sleSendQRequest));	
	
    	//createResponse( )
		WEB3ProcessSleSendqResponse l_response = (WEB3ProcessSleSendqResponse)l_request.createResponse();
		l_response.date = new java.sql.Date(System.currentTimeMillis());
        
        return l_response;
    }
}@
