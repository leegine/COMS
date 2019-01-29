head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointCategoryReferenceServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : カテゴリー一覧サービスImpl(WEB3AdminPointCategoryReferenceServiceImpl.java)
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
import webbroker3.point.WEB3PointCategory;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceRequest;
import webbroker3.point.message.WEB3AdminPointCategoryReferenceResponse;
import webbroker3.point.message.WEB3AdminPointCategoryUnit;
import webbroker3.point.service.delegate.WEB3AdminPointCategoryReferenceService;
import webbroker3.util.WEB3LogUtility;

/**
 * (カテゴリー一覧サービスImpl)<BR>
 * カテゴリー一覧サービス実装クラス<BR>
 *
 * @@author 張学剛
 * @@version 1.0
 */
public class WEB3AdminPointCategoryReferenceServiceImpl implements WEB3AdminPointCategoryReferenceService 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointCategoryReferenceServiceImpl.class);

    /**
     * カテゴリー一覧サービス処理を行う。<BR>
     * <BR>
     * シーケンス図「（カテゴリー一覧）一覧表示データ取得」 参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 41877C33032C
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);  
        
        if (!(l_request instanceof WEB3AdminPointCategoryReferenceRequest))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.1 getInstanceFromログイン情報()
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        if (l_admin == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.2 validate権限(String, boolean) 
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_PREMIUM_MANAGE, false);
        
        //1.3 getカテゴリー(String)
        //証券会社コード： 管理者.get証券会社コード()の戻り値 
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
        
        WEB3PointCategory[] l_pointCategorys = l_manager.getCategories(l_strInstitutionCode);

        //1.4 ArrayList()
        ArrayList l_arrayList = new ArrayList();
        
        int l_intLength = 0;
                
        if (l_pointCategorys != null )
        {
            l_intLength = l_pointCategorys.length;
        }
        
        //1.5 取得したカテゴリーオブジェクト毎にLoop 
        for(int i = 0; i < l_intLength; i++)
        {
        
            //1.5.1 カテゴリー明細()
            WEB3AdminPointCategoryUnit l_pointCategoryUnit = new WEB3AdminPointCategoryUnit();
            
            //1.5.2 プロパティセット
            //カテゴリー名
            l_pointCategoryUnit.categoryName = l_pointCategorys[i].getCategoryName();
            
            //カテゴリー番号
            l_pointCategoryUnit.categoryNo = new Long(l_pointCategorys[i].getCategoryNo()).toString();
            
            //カテゴリー概要
            l_pointCategoryUnit.categoryOutline = l_pointCategorys[i].getCategoryOutline();
            
            //1.5.3  add(arg0 : Object)
            l_arrayList.add(l_pointCategoryUnit);
        }
        
        //1.6 toArray()       
        WEB3AdminPointCategoryUnit[] l_pointCategoryUnit = new WEB3AdminPointCategoryUnit[l_arrayList.size()];
        l_arrayList.toArray(l_pointCategoryUnit);
        
        //1.7 createResponse()
        WEB3AdminPointCategoryReferenceRequest l_referenceRequest = 
            (WEB3AdminPointCategoryReferenceRequest)l_request;
        
        WEB3AdminPointCategoryReferenceResponse l_response = 
            (WEB3AdminPointCategoryReferenceResponse)l_referenceRequest.createResponse();
        if (l_response == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //(*2)プロパティセット
        l_response.categoryList = l_pointCategoryUnit;
        
        log.exiting(STR_METHOD_NAME);
        
        return l_response;
    }
}
@
