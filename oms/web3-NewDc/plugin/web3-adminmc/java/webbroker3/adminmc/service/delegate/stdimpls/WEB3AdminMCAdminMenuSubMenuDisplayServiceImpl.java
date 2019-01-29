head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.27.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者メニュー制御サブメニュー表示サービスImpl(WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/23  温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc.service.delegate.stdimpls;


import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.util.WEB3LogUtility;

import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminMenuSubMenuDisplayService;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse;
import webbroker3.adminmc.message.WEB3AdminMCTransactionCategoryUnit;


/**
 * (管理者メニュー制御サブメニュー表示サービスImpl)<BR>
 * 管理者メニュー制御サブメニュー表示サービス実装クラス<BR>
 * @@author 温顕法@
 * @@version 1.0
 */

public class WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl implements WEB3AdminMCAdminMenuSubMenuDisplayService 
{
    
    /**
     *ログ出力ユーティリティ。
     */
    private WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl.class);
    
    /**
     * @@roseuid 419864100222
     */
    public WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl() 
    {
     
    }
    
    /**
     * 管理者権限グループ一覧／詳細処理を実施する。<BR>
     * <BR>
     * １）　@リクエストデータの型により、以下の通りメソッドをコールする。 <BR>
     * <BR>
     * ○ 引数のリクエストデータが、管理者メニュー制御サブメニュー表示リクエストの場合 <BR>
     * 　@－getサブメニュー()をコールする。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4177693D02DE
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest l_request)";
        log.entering(STR_METHOD_NAME);
        
        WEB3GenResponse l_response = null;
        
        if (l_request instanceof WEB3AdminMCAdminMenuSubMenuDisplayRequest)
        {
            l_response = this.getSubMenu((WEB3AdminMCAdminMenuSubMenuDisplayRequest)l_request);
        
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                                      WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                                       STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (getサブメニュー)<BR>
     * サブメニューで利用できる機@能カテゴリコード一覧を返却する。<BR>
     * <BR>
     * シーケンス図 <BR>
     * 「管理者メニュー制御（サブメニュー表示）getサブメニュー」参照。 <BR>
     * <BR>
     * <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 管理者メニュー制御サブメニュー表示リクエストデータオブジェクト<BR>
     * @@return webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse
     * @@roseuid 417768F00232
     */
    protected WEB3AdminMCAdminMenuSubMenuDisplayResponse getSubMenu(WEB3AdminMCAdminMenuSubMenuDisplayRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getSubMenu(WEB3AdminMCAdminMenuSubMenuDisplayRequest) ";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate()
        l_request.validate();
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_administartor = WEB3Administrator.getInstanceFromLoginInfo();
        //1.3 get権限レベル()
        String l_adminPermissionLevel = l_administartor.getPermissionLevel();
        //1.4 get証券会社コード()
        String l_adminInstitutionCode = l_administartor.getInstitutionCode();
        //1.5 create処理可能機@能カテゴリ一覧        
        WEB3AdminMCAdminPermUnitCreateServiceImpl l_adminPermUnitCreateServiceImpl = new WEB3AdminMCAdminPermUnitCreateServiceImpl();      
        WEB3AdminMCTransactionCategoryUnit[] l_strQueryDataContainers 
            = l_adminPermUnitCreateServiceImpl.createOperatePossibleTransactionCategoryUnit(l_adminInstitutionCode, l_adminPermissionLevel, l_request.transactionCategoryList);     
        //1.6 管理者メニュー制御サブメニュー表示レスポンス(WEB3GenRequest)
        WEB3AdminMCAdminMenuSubMenuDisplayResponse l_response = (WEB3AdminMCAdminMenuSubMenuDisplayResponse)l_request.createResponse();
        //1.7 プロパティセット
        l_response.transactionCategoryUnits = l_strQueryDataContainers;

        log.exiting(STR_METHOD_NAME);  
        return l_response;

    }
}
@
