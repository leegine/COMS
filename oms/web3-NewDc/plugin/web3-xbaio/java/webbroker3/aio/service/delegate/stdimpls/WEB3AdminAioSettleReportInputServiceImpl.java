head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.31.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioSettleReportInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 決済連携レポート入力サービス実装クラス(WEB3AdminAioSettleReportInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15 韋念瓊 (中訊) 新規作成  
                   2004/10/27 周勇(中訊) レビュー                  
*/

package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.aio.WEB3AioMultiBankSettleControlService;
import webbroker3.aio.message.WEB3AdminAioSettleReportInputResponse;
import webbroker3.aio.message.WEB3AioSettleInstitutionUnit;
import webbroker3.aio.service.delegate.WEB3AdminAioSettleReportInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * (決済連携レポート入力サービスImpl)<BR>
 * 決済連携レポート入力サービス実装クラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioSettleReportInputServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioSettleReportInputService 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSettleReportInputServiceImpl.class);
    
    /**
     * 決済連携レポート入力サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（決済連携レポート入力）入力画面表示データ取得」 参照<BR>
     * @@param l_request - リクエストデータ
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4100FF47030D
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        if (l_request == null)
        {
            log.debug("パラメータ値がNULL");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        WEB3AdminAioSettleReportInputResponse l_adminSettleReportInputResponse = null;        
        
        //1.1 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.2 証券会社コードを取得する。
        String l_strInstitutionCode = null;
        l_strInstitutionCode = l_web3Administrator.getInstitutionCode();

        //1.3 部店コードを取得する。
        String l_strBranchCode = null;        
        l_strBranchCode = l_web3Administrator.getBranchCode();
        
        //1.4 提携決済機@関明細の配列を取得する。
        WEB3AioMultiBankSettleControlService l_aioMultiBankSettleControlService =
            (WEB3AioMultiBankSettleControlService) Services.getService(
                WEB3AioMultiBankSettleControlService.class);     
        
        WEB3AioSettleInstitutionUnit[] l_aioSelectSettleInstitutionUnits = 
            l_aioMultiBankSettleControlService.getAffiliatedPaySchemeDetails(l_strInstitutionCode);
        
        //1.5 レスポンスデータを生成する。
        l_adminSettleReportInputResponse = (WEB3AdminAioSettleReportInputResponse) 
            l_request.createResponse();
        
        //1.6  プロパティセット
        //(*) 以下のとおりに、プロパティをセットする。

        //レスポンス.部店コード = 管理者.get部店コード()の戻り値        
        l_adminSettleReportInputResponse.branchCode = l_strBranchCode;
        
        //レスポンス.決済機@関一覧 = マルチバンク決済制御サービスImpl.get提携決済機@関明細()の戻り値
        l_adminSettleReportInputResponse.settleInstitutionUnits = l_aioSelectSettleInstitutionUnits;

        log.debug("レスポンス.部店コード = " + l_adminSettleReportInputResponse.branchCode);
        log.debug("レスポンス.決済機@関一覧 = " + l_adminSettleReportInputResponse.settleInstitutionUnits);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminSettleReportInputResponse;
    }
}
@
