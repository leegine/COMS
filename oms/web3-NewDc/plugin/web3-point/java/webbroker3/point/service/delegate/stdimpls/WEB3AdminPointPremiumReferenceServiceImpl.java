head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointPremiumReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 景品一覧サービスImpl(WEB3AdminPointPremiumReferenceServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 張学剛(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import java.util.ArrayList;

import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointPremiumDetail;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceRequest;
import webbroker3.point.message.WEB3AdminPointPremiumReferenceResponse;
import webbroker3.point.service.delegate.WEB3AdminPointPremiumReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (景品一覧サービスImpl)<BR>
 * 景品一覧サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointPremiumReferenceServiceImpl implements WEB3AdminPointPremiumReferenceService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointPremiumReferenceServiceImpl.class);

    /**
     * 景品一覧サービス処理を行う。<BR>
     * <BR>
     * シーケンス図「（景品一覧）一覧表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 4192D3D302AC
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);  
        
        if (!(l_request instanceof WEB3AdminPointPremiumReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        WEB3AdminPointPremiumReferenceRequest l_referenceRequest = 
            (WEB3AdminPointPremiumReferenceRequest)l_request;
            
        //1.1 validate( )
        l_referenceRequest.validate();
                
        //1.2 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, false);
        
        //1.4 get景品(String, String)
        //[引数] 
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
        //カテゴリー番号： リクエストデータ.カテゴリー番号 
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        WEB3PointProductManager l_manager = (WEB3PointProductManager)
            Services.getService(WEB3PointProductManager.class);
            
        if (l_manager == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
            
        WEB3PointPremium[] l_premiums = l_manager.getPremiums(l_strInstitutionCode, l_referenceRequest.categoryNo);
        
        int l_intLength = 0;
        if (l_premiums != null)
        {
            l_intLength = l_premiums.length;
        }
        
        //1.5 ArrayList()
        ArrayList l_arrayList = new ArrayList();
        
        //1.6 取得した景品オブジェクト毎にLoop        
        for (int i = 0; i < l_intLength; i++)
        {
            //1.6.1 景品情報明細()
            WEB3AdminPointPremiumDetail l_detail = new WEB3AdminPointPremiumDetail();
            
            //1.6.2 プロパティセット
            //景品番号
            if (l_premiums[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_detail.premiumNo = l_premiums[i].getPremiumNo();
            
            //景品番号
            l_detail.premiumName = l_premiums[i].getPremiumName();
            
            //必要ポイント
            l_detail.requiredPoint = new Long(l_premiums[i].getRequiredPoint()).toString();
            
            //提供開始日時
            l_detail.startDate = l_premiums[i].getStartDateTime();
            
            //提供終了日時
            l_detail.endDate = l_premiums[i].getEndDateTime();
            
            //1.6.3 add(arg0 : Object)
            l_arrayList.add(l_detail);  
        }
        
        //1.7 toArray()
        WEB3AdminPointPremiumDetail[] l_details = new WEB3AdminPointPremiumDetail[l_arrayList.size()];       
        l_arrayList.toArray(l_details);
        
        //1.8 createResponse()
        WEB3AdminPointPremiumReferenceResponse l_response = 
            (WEB3AdminPointPremiumReferenceResponse)l_request.createResponse();
            
        //1.9 プロパティセット       
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_response.premiumList = l_details;
                
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
