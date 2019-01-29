head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminToTradeStopListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー注文管理者・取扱停止一覧サービスImpl(WEB3AdminToTradeStopListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/04/04  鄭徳懇(中訊) 新規作成
                 : 2006/04/12  鄭徳懇(中訊) 仕様変更・モデル055
*/

package webbroker3.admintriggerorder.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;

import webbroker3.admintriggerorder.WEB3AdminToDataManager;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopInfoUnit;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListRequest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopListResponse;
import webbroker3.admintriggerorder.service.delegate.WEB3AdminToTradeStopListService;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.triggerorder.base.data.TriggerOrderStopRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (トリガー注文管理者・取扱停止一覧サービスImpl)<BR>
 * トリガー注文管理者・取扱停止一覧サービス実装クラス<BR>
 * 
 * @@author 鄭徳懇
 * @@version 1.0
 */
public class WEB3AdminToTradeStopListServiceImpl extends WEB3ClientRequestService 
    implements WEB3AdminToTradeStopListService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminToTradeStopListServiceImpl.class);

    /**
     * @@roseuid 4430D99F02FD
     */
    public WEB3AdminToTradeStopListServiceImpl() 
    {
     
    }
    
    /**
     * 取扱停止一覧処理を行う。<BR>
     * <BR>
     * this.get一覧画面()をコールする。<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410117401E8
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME);

        if (l_request == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータ値不正。");
        }

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3AdminToTradeStopListRequest)
        {
            l_response = this.getListScreen((WEB3AdminToTradeStopListRequest) l_request);
        }
        else
        {
            log.debug("パラメータタイプ不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                "パラメータタイプ不正。");
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (get一覧画面)<BR>
     * 取扱停止一覧処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（トリガー注文管理者・取扱停止一覧サービス）get一覧画面」参照。<BR>
     * ============================================================== <BR>
     *    シーケンス図：「（トリガー注文管理者・取扱停止一覧サービス）get一覧画面」<BR>
     *    具体位置：1.7.2 (*)上記以外の場合<BR>
     *    「条件に該当するデータが存在しない。」の<BR>
     *    業務エラーをスローする。<BR>
     *    class : WEB3BusinessLayerException<BR>
     *    tag : BUSINESS_ERROR_01037<BR>
     * ============================================================== <BR>
     * @@param l_request - (リクエストデータ)<BR>
     * トリガー注文管理者・取扱停止一覧リクエストオブジェクト<BR>
     * @@return WEB3AdminToTradeStopListResponse
     * @@throws WEB3BaseException
     * @@roseuid 4410124A01F5
     */
    protected WEB3AdminToTradeStopListResponse getListScreen(WEB3AdminToTradeStopListRequest l_request)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getListScreen(WEB3AdminToTradeStopListRequest)";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();
        
        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
        
        //1.3 validate権限(機@能カテゴリコード : String, is更新 : boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.TRIGGER_ORDER_TRADING_STOP, false);
 
        //1.4 validate部店権限(部店コード : String[])
        l_admin.validateBranchPermission(l_request.branchCode);
               
        //1.5 get証券会社コード( )
        String l_strInstitutionCode = l_admin.getInstitutionCode();
        
        //1.6 get特殊執行条件取扱停止一覧(String, String[], String, 
        //String, 取扱停止ソートキー[])
        List l_lisConditions = WEB3AdminToDataManager.getTriggerOrderStopList(
            l_strInstitutionCode,
            l_request.branchCode,
            l_request.tradeStopDiv,
            l_request.productCode,
            l_request.sortKeys);
        
        WEB3AdminToTradeStopListResponse l_response = null;
        //1.7 (*)get特殊執行条件取扱停止一覧()の戻り値 == nullの場合
        if (l_lisConditions == null || l_lisConditions.size() == 0)
        {
            //1.7.1 (*)ページングを行う場合（リクエストデータ.要求ページ番号 != null 
            //かつ リクエストデータ.ページ内表示行数 != null）の場合
            if (WEB3StringTypeUtility.isNotEmpty(l_request.pageIndex)
                && WEB3StringTypeUtility.isNotEmpty(l_request.pageSize))
            {
                //1.7.1.1 createResponse( )
                l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
                l_response.currentTime = GtlUtils.getSystemTimestamp();
                
                //1.7.1.2
                log.exiting(STR_METHOD_NAME);
                return l_response;
            }
           
            //1.7.2 (*)上記以外の場合
            log.debug("条件に該当するデータが存在しない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01037,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "条件に該当するデータが存在しない。");
        }
        
        //1.8 (*)ページングを行う場合（リクエストデータ.要求ページ番号 != null 
        //かつ リクエストデータ.ページ内表示行数 != null）の場合
        WEB3PageIndexInfo l_pageIndexInfo = null;
        TriggerOrderStopRow[] l_rows = null;
        if (WEB3StringTypeUtility.isNotEmpty(l_request.pageIndex)
            && WEB3StringTypeUtility.isNotEmpty(l_request.pageSize))
        {
            //1.8.1 WEB3PageIndexInfo(arg0 : List, arg1 : int, arg2 : int)
            l_pageIndexInfo = new WEB3PageIndexInfo(
                l_lisConditions,
                Integer.parseInt(l_request.pageIndex),
                Integer.parseInt(l_request.pageSize));
            
            //1.8.2 getArrayReturned(arg0 : Class)
            l_rows = (TriggerOrderStopRow[]) l_pageIndexInfo.getArrayReturned(TriggerOrderStopRow.class);
        }
        else
        {
            l_rows = new TriggerOrderStopRow[l_lisConditions.size()];
            l_lisConditions.toArray(l_rows);
        }
        
        //1.9 get証券会社( )
        Institution l_institution = l_admin.getInstitution();
        
        //1.10 create取扱停止情報一覧(証券会社, 特殊執行条件取扱停止Row[])
        WEB3AdminToTradeStopInfoUnit[] l_tradeStopInfoUnits =
            WEB3AdminToDataManager.createTradeStopInfoList(l_institution, l_rows);
        
        //1.11 createResponse( )
        l_response = (WEB3AdminToTradeStopListResponse) l_request.createResponse();
        
        //1.12 (*)プロパティセット
        //現在時刻        ＝　@GtlUtils.getSystemTimestamp()
        l_response.currentTime = GtlUtils.getSystemTimestamp();
        
        if (l_pageIndexInfo != null)
        {
            //総ページ数   ＝　@(*1)WEB3PageIndexInfo.getTotalPages()
            l_response.totalPages = String.valueOf(l_pageIndexInfo.getTotalPages());
            //総レコード数  ＝　@(*1)WEB3PageIndexInfo.getTotalRecords()
            l_response.totalRecords = String.valueOf(l_pageIndexInfo.getTotalRecords());
            //表示ページ番号 ＝　@(*1)WEB3PageIndexInfo.getPageIndex()
            l_response.pageIndex = String.valueOf(l_pageIndexInfo.getPageIndex()); 
        }
        
        //取扱停止情報一覧    ＝　@create取扱停止情報一覧()の戻り値
        l_response.tradeStopInfoList = l_tradeStopInfoUnits;
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
}
@
