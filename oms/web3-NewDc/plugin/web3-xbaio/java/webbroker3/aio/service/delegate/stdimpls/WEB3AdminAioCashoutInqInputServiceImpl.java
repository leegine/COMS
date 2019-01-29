head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.28.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqInputServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ入力サービス実装クラス(WEB3AdminAioCashoutInqInputServiceImpl)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 韋念瓊 (中訊) 新規作成    
                   2004/10/26 黄建 (中訊) レビュー   
                   2006/09/04 車進 (中訊) 式樣變更 モデルNo.628                 
*/

package webbroker3.aio.service.delegate.stdimpls;

import webbroker3.aio.message.WEB3AdminAioCashoutInqInputResponse;
import webbroker3.aio.service.delegate.WEB3AdminAioCashoutInqInputService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.util.WEB3LogUtility;

/**
 * (出金申込問合せ入力サービスImpl)<BR>
 * (出金申込問合せ入力サービス実装クラス)
 *
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AdminAioCashoutInqInputServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminAioCashoutInqInputService 
{   
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioCashoutInqInputServiceImpl.class);
        
    /**
     * 出金申込問合せ入力サービス処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（出金申込問合せ入力）入力画面表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 41010350034B
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

        WEB3AdminAioCashoutInqInputResponse l_adminAioCashoutInqInputResponse = null;        
        
        //1.1 管理者インスタンスを取得する。
        WEB3Administrator l_web3Administrator = WEB3Administrator.getInstanceFromLoginInfo();
               
        //1.2 部店コードを取得する。
        String l_strBranchCode = l_web3Administrator.getBranchCode();
        
        //1.3 証券会社コードを取得する。
        String l_strInstitutionCode = l_web3Administrator.getInstitutionCode();
        
        //1.4 指定した証券会社が登録している通貨の通貨コードをすべて取得する。 
        String[] l_strCurrencyCodes = WEB3GentradeCurrency.getCurrencyCodeList(l_strInstitutionCode);     
        
        //1.5 レスポンスデータを生成する。
        l_adminAioCashoutInqInputResponse = (WEB3AdminAioCashoutInqInputResponse) 
            l_request.createResponse();
        
        //1.6  プロパティセット
        //(*) 以下のとおり、プロパティをセットする。
        //レスポンス.部店コード = 管理者.get部店コード()の戻り値
        //レスポンス.外貨コード一覧 = get通貨コード一覧()の戻り値
        l_adminAioCashoutInqInputResponse.branchCode = l_strBranchCode;
        l_adminAioCashoutInqInputResponse.foreignCurrencyCodeList = l_strCurrencyCodes;
        
        log.debug("レスポンス.部店コード = " + 
                l_adminAioCashoutInqInputResponse.branchCode);
        
        log.exiting(STR_METHOD_NAME);
        return l_adminAioCashoutInqInputResponse;
    }
}
@
