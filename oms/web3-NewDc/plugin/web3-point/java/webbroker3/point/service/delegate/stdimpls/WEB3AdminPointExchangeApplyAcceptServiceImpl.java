head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.52.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointExchangeApplyAcceptServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ポイント交換受付サービスImpl(WEB3AdminPointExchangeApplyAcceptServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TransactionCategoryDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.point.WEB3AdminPointAcceptDivComparator;
import webbroker3.point.WEB3AdminPointAcceptUserComparator;
import webbroker3.point.WEB3AdminPointAccountCodeComparator;
import webbroker3.point.WEB3AdminPointApplyTimestampComparator;
import webbroker3.point.WEB3AdminPointBranchCodeComparator;
import webbroker3.point.WEB3AdminPointCancelDivComparator;
import webbroker3.point.WEB3AdminPointLastUpdatedTimestampComparator;
import webbroker3.point.WEB3AdminPointPremiumNameComparator;
import webbroker3.point.WEB3AdminPointPremiumNoComparator;
import webbroker3.point.WEB3PointApply;
import webbroker3.point.WEB3PointApplyManager;
import webbroker3.point.WEB3PointPremium;
import webbroker3.point.WEB3PointProductManager;
import webbroker3.point.data.PointApplyRow;
import webbroker3.point.define.WEB3AcceptDivDef;
import webbroker3.point.define.WEB3PointKeyItemDef;
import webbroker3.point.message.WEB3AdminPointApplyDetail;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptRequest;
import webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmRequest;
import webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceRequest;
import webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse;
import webbroker3.point.message.WEB3PointSortKey;
import webbroker3.point.service.delegate.WEB3AdminPointExchangeApplyAcceptService;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ポイント交換受付サービスImpl)<BR>
 * ポイント交換受付サービス実装クラス<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AdminPointExchangeApplyAcceptServiceImpl implements WEB3AdminPointExchangeApplyAcceptService 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3AdminPointExchangeApplyAcceptServiceImpl.class);

    /**
     * ポイント交換受付サービス処理を行う。<BR>
     * <BR>
     * リクエストデータの型により、<BR>
     * <BR>
     *    get一覧画面()<BR>
     *    submit受付()<BR>
     *    validate取消()<BR>
     *    submit取消()<BR>
     *    validate取消解除()<BR>
     *    submit取消解除()<BR>
     * <BR>
     * 上記メソッドをコールする。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return WEB3GenResponse
     * @@roseuid 419B0A74012F
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " execute(WEB3GenRequest )";
        log.entering(STR_METHOD_NAME);
        
        if (l_request instanceof WEB3AdminPointExchangeStateReferenceRequest)
        {
            WEB3AdminPointExchangeStateReferenceResponse l_response = 
                this.getReferenceScreen((WEB3AdminPointExchangeStateReferenceRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;
        }
        else if (l_request instanceof WEB3AdminPointExchangeAcceptRequest)
        {
            WEB3AdminPointExchangeAcceptResponse l_response = 
                this.submitAccept((WEB3AdminPointExchangeAcceptRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelConfirmRequest)
        {
            WEB3AdminPointExchangeCancelConfirmResponse l_response = 
                this.validateCancel((WEB3AdminPointExchangeCancelConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelCompleteRequest)
        {
            WEB3AdminPointExchangeCancelCompleteResponse l_response = 
                this.submitCancel((WEB3AdminPointExchangeCancelCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelReleaseConfirmRequest)
        {
            WEB3AdminPointExchangeCancelReleaseConfirmResponse l_response = 
                this.validateCancelRelease((WEB3AdminPointExchangeCancelReleaseConfirmRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else if (l_request instanceof WEB3AdminPointExchangeCancelReleaseCompleteRequest)
        {
            WEB3AdminPointExchangeCancelReleaseCompleteResponse l_response = 
                this.submitCancelRelease((WEB3AdminPointExchangeCancelReleaseCompleteRequest)l_request);
        
            log.exiting(STR_METHOD_NAME);
            return l_response;           
        }
        else
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * (get一覧画面)<BR>
     * 一覧画面の取得を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント交換受付）get一覧画面」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeStateReferenceResponse
     * @@roseuid 419B0A74014F
     */
    protected WEB3AdminPointExchangeStateReferenceResponse getReferenceScreen(
        WEB3AdminPointExchangeStateReferenceRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " getReferenceScreen(WEB3AdminPointExchangeStateReferenceRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate部店権限(String[])
        l_admin.validateBranchPermission(l_request.branchCode);//WEB3BaseException
        
        //1.5 create取得条件文字列(String[], String)
        String l_strWhere = this.createGetString(l_request.branchCode, l_request.acceptDiv);
        
        //1.6 create取得条件データコンテナ(String, String[], String)
        Object[] l_objBinds = this.createGetContainer(l_admin.getInstitutionCode(), l_request.branchCode, l_request.acceptDiv);
        
        List l_lisApply = null;
        int l_intApplyCount = 0;
        ArrayList l_arrayListApply = null;
        try
        {
            //1.7 getDefaultProcessor( )
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataQueryException, DataNetworkException
            
            //1.8 doFindAllQuery(RowType, String, String, String, Object[], int, int)
            if (l_queryProcessor == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            l_lisApply = l_queryProcessor.doFindAllQuery(
                PointApplyRow.TYPE,
                l_strWhere,
                l_objBinds);
            //1.9 ArrayList( )
            l_arrayListApply = new ArrayList();
                
            //1.10 (*1)取得したレコード毎にLoop処理
            if (l_lisApply != null)
            {
                l_intApplyCount = l_lisApply.size();
            }
            WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
            if (l_productManager == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
    
            for (int i = 0; i < l_intApplyCount; i++)
            {
                PointApplyRow l_applyRow = (PointApplyRow)l_lisApply.get(i);
                if (l_applyRow == null)
                {
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BaseRuntimeException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                        this.getClass().getName() + STR_METHOD_NAME);
                }
                //1.10.1 get景品(String, String)
                WEB3PointPremium l_premium = l_productManager.getPremium(l_applyRow.getInstitutionCode(), l_applyRow.getPremiumNo());//WEB3BaseException
                        
                //1.10. 2ポイント申込明細( )
                WEB3AdminPointApplyDetail l_applyDetail = new WEB3AdminPointApplyDetail();
                    
                Timestamp l_tsApplyAcceptTime = l_applyRow.getApplyAcceptTimestamp();
                Timestamp l_tsApplyCancelTime = l_applyRow.getApplyCancelTimestamp();
                //1.10.3 (*2)プロパティセット
                l_applyDetail.applyId = WEB3StringTypeUtility.formatNumber(l_applyRow.getApplyId());
                l_applyDetail.branchCode = l_applyRow.getBranchCode();
                l_applyDetail.accountCode = l_applyRow.getAccountCode().substring(0, 6);
                l_applyDetail.premiumNo = l_applyRow.getPremiumNo();
                if (l_premium != null)
                {
                    l_applyDetail.premiumName = l_premium.getPremiumName();
                }
                else
                {
                    l_applyDetail.premiumName = null;
                }
                l_applyDetail.applyDate = l_applyRow.getApplyTimestamp();
                l_applyDetail.acceptDiv = l_applyRow.getApplyAcceptDiv();
                if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime == null)
                {
                    l_applyDetail.updateTimeStamp = null;
                }
                else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime == null)
                {
                    l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
                }
                else if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime != null)
                {
                    l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
                }
                else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime != null)
                {
                    if (WEB3DateUtility.compareToSecond(l_tsApplyAcceptTime, l_tsApplyCancelTime) > 0)
                    {
                        l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
                    }
                    else
                    {
                        l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
                    }
                }
                    
                if (l_applyDetail.updateTimeStamp == null)
                {
                    l_applyDetail.acceptUser = null;
                }
                else if (WEB3DateUtility.compareToSecond(l_applyDetail.updateTimeStamp, l_tsApplyAcceptTime) == 0)
                {
                    l_applyDetail.acceptUser = l_applyRow.getApplyAcceptUser();
                }
                else
                {
                    l_applyDetail.acceptUser = l_applyRow.getApplyCancelUser();
                }                
                l_applyDetail.cancelDiv = l_applyRow.getApplyCancelDiv();
                    
                //1.10.4 add(arg0 : Object)
                l_arrayListApply.add(l_applyDetail);
            }
        }
        catch (DataFindException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataQueryException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }
        catch (DataNetworkException l_e)
        {
            log.error(STR_METHOD_NAME,l_e);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003, 
                this.getClass().getName() + STR_METHOD_NAME,
                l_e.getMessage(),
                l_e);
        }

        //1.11 toArray( )
        WEB3AdminPointApplyDetail[] l_applyDetails = new WEB3AdminPointApplyDetail[l_arrayListApply.size()];
        l_arrayListApply.toArray(l_applyDetails);
            
        //1.12 sortポイント申込明細(ポイント申込明細[], ポイント交換一覧ソートキー[])
        WEB3AdminPointApplyDetail[] l_applyDetails_sorted  = this.sortPointApplyDetail(l_applyDetails, l_request.sortKeys);
            
        //1.13 get表示明細(ポイント申込明細[], int, int)
        int l_intPageSize = Integer.parseInt(l_request.pageSize);
        int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
        WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(
            l_applyDetails_sorted,
            l_intPageIndex, 
            l_intPageSize);
        //1.14 createResponse( )
        WEB3AdminPointExchangeStateReferenceResponse l_response = (WEB3AdminPointExchangeStateReferenceResponse)l_request.createResponse();  
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.15 (*4)プロパティセット
        WEB3AdminPointApplyDetail[] l_indicationDetail = (WEB3AdminPointApplyDetail[])l_pageIndexInfo.getArrayReturned(WEB3AdminPointApplyDetail.class); 
        l_response.applyList = l_indicationDetail;
        l_response.pageIndex = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getPageIndex());
        if (l_pageIndexInfo.getTotalRecords() == 0)
        {
            l_response.pageIndex = "0";
        }
        l_response.totalPages = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalPages());
        l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_pageIndexInfo.getTotalRecords());

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit受付)<BR>
     * 受付の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント交換受付）submit受付」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeAcceptResponse
     * @@roseuid 419B0BED0391
     */
    protected WEB3AdminPointExchangeAcceptResponse submitAccept(
        WEB3AdminPointExchangeAcceptRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitAccept(WEB3AdminPointExchangeAcceptRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        //1.5 リクエストデータ.申込IDの各要素毎にLoop処理
        if (l_request.applyId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        int l_intApplyIdCounnt = l_request.applyId.length;
        for (int i = 0; i < l_intApplyIdCounnt; i++)
        {
            //1.5.1 get申込(long)
            if (l_request.applyId[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            if (!WEB3StringTypeUtility.isDigit(l_request.applyId[i]))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                    getClass().getName() + STR_METHOD_NAME);
            }
            WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId[i]));//WEB3BaseException
                        
            //1.5.2 validate部店権限(String)
            if (l_apply == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }

            l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
            
            //1.5.3 save申込受付(ポイント申込, 管理者)
            l_applyManager.saveApplyAccept(l_apply, l_admin);//WEB3BaseException
        }
        
        //1.6 createResponse( )
        WEB3AdminPointExchangeAcceptResponse l_response = (WEB3AdminPointExchangeAcceptResponse)l_request.createResponse();  
        
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * 取消の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント交換受付）validate取消」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelConfirmResponse
     * @@roseuid 419B0A74016E
     */
    protected WEB3AdminPointExchangeCancelConfirmResponse validateCancel(
        WEB3AdminPointExchangeCancelConfirmRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateCancel(WEB3AdminPointExchangeCancelConfirmRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        
        //1.4 get申込(long)
        if (l_request.applyId == null)
        {
            String l_strMessage = "申込IDerror! " + l_request.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01732,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        if (!WEB3StringTypeUtility.isDigit(l_request.applyId))
        {
            String l_strMessage = "申込IDerror! " + l_request.applyId;
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01885,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }

        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        PointApplyRow l_applyRow = (PointApplyRow)l_apply.getDataSourceObject();
        
        //1.5 validate部店権限(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
        if (l_productManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.6 get景品(String, String)
        WEB3PointPremium l_premium = l_productManager.getPremium(l_admin.getInstitutionCode(), l_apply.getPremiumNo());//WEB3BaseException
        
        //1.7 ポイント申込明細( )
        WEB3AdminPointApplyDetail l_applyDetail = new WEB3AdminPointApplyDetail();

        //1.8 プロパティセット
        if (l_applyRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        Timestamp l_tsApplyAcceptTime = l_applyRow.getApplyAcceptTimestamp();
        Timestamp l_tsApplyCancelTime = l_applyRow.getApplyCancelTimestamp();

        l_applyDetail.applyId = WEB3StringTypeUtility.formatNumber(l_applyRow.getApplyId());
        l_applyDetail.branchCode = l_applyRow.getBranchCode();
        l_applyDetail.accountCode = l_applyRow.getAccountCode().substring(0, 6);
        l_applyDetail.premiumNo = l_applyRow.getPremiumNo();
        if (l_premium != null)
        {
            l_applyDetail.premiumName = l_premium.getPremiumName();
        }
        else
        {
            l_applyDetail.premiumName = null;
        }
        l_applyDetail.applyDate = l_applyRow.getApplyTimestamp();
        l_applyDetail.acceptDiv = l_applyRow.getApplyAcceptDiv();
        if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = null;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
        }
        else if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime != null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime != null)
        {
            if (WEB3DateUtility.compareToSecond(l_tsApplyAcceptTime, l_tsApplyCancelTime) > 0)
            {
                l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
            }
            else
            {
                l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
            }
        }
                
        if (l_applyDetail.updateTimeStamp == null)
        {
            l_applyDetail.acceptUser = null;
        }
        else if (WEB3DateUtility.compareToSecond(l_applyDetail.updateTimeStamp, l_tsApplyAcceptTime) == 0)
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyAcceptUser();
        }
        else
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyCancelUser();
        }
        l_applyDetail.cancelDiv = l_applyRow.getApplyCancelDiv();
        
        //1.9 createResponse( )
        WEB3AdminPointExchangeCancelConfirmResponse l_response = (WEB3AdminPointExchangeCancelConfirmResponse)l_request.createResponse();  
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.10 プロパティセット                
        l_response.applyDetail = l_applyDetail;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit取消)<BR>
     * 取消の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント交換受付）submit取消」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelCompleteResponse
     * @@roseuid 419B0A74017D
     */
    protected WEB3AdminPointExchangeCancelCompleteResponse submitCancel(
        WEB3AdminPointExchangeCancelCompleteRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitCancel(WEB3AdminPointExchangeCancelCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.5 get申込(long)
        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1.6 validate部店権限(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        //1.7 save申込取消(ポイント申込, 管理者)
        l_applyManager.saveApplyCancel(l_apply, l_admin);//WEB3BaseException
        
        //1.8 createResponse( )
        WEB3AdminPointExchangeCancelCompleteResponse l_response = (WEB3AdminPointExchangeCancelCompleteResponse)l_request.createResponse();  

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (validate取消解除)<BR>
     * 取消解除の審査を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント交換受付）validate取消解除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseConfirmResponse
     * @@roseuid 419B0B9E0314
     */
    protected WEB3AdminPointExchangeCancelReleaseConfirmResponse validateCancelRelease(
        WEB3AdminPointExchangeCancelReleaseConfirmRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " validateCancelRelease(WEB3AdminPointExchangeCancelReleaseConfirmRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.5 get申込(long)
        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.5 validate部店権限(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        //1.6 validateポイント余力(ポイント申込)
        l_applyManager.validatePointPower(l_apply);//WEB3BaseException
        
        //1.7 get景品(String, String)
        WEB3PointProductManager l_productManager = (WEB3PointProductManager)Services.getService(WEB3PointProductManager.class);
        if (l_productManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointPremium l_premium = l_productManager.getPremium(l_admin.getInstitutionCode(), l_apply.getPremiumNo());//WEB3BaseException
        
        //1.8 ポイント申込明細( )
        WEB3AdminPointApplyDetail l_applyDetail = new WEB3AdminPointApplyDetail();

        //1.9 プロパティセット
        PointApplyRow l_applyRow = (PointApplyRow)l_apply.getDataSourceObject();
        if (l_applyRow == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        Timestamp l_tsApplyAcceptTime = l_applyRow.getApplyAcceptTimestamp();
        Timestamp l_tsApplyCancelTime = l_applyRow.getApplyCancelTimestamp();

        l_applyDetail.applyId = WEB3StringTypeUtility.formatNumber(l_applyRow.getApplyId());
        l_applyDetail.branchCode = l_applyRow.getBranchCode();
        l_applyDetail.accountCode = l_applyRow.getAccountCode().substring(0, 6);
        l_applyDetail.premiumNo = l_applyRow.getPremiumNo();
        if (l_premium != null)
        {
            l_applyDetail.premiumName = l_premium.getPremiumName();
        }
        else
        {
            l_applyDetail.premiumName = null;
        }
        l_applyDetail.applyDate = l_applyRow.getApplyTimestamp();
        l_applyDetail.acceptDiv = l_applyRow.getApplyAcceptDiv();
        if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = null;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime == null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
        }
        else if (l_tsApplyAcceptTime == null && l_tsApplyCancelTime != null)
        {
            l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
        }
        else if (l_tsApplyAcceptTime != null && l_tsApplyCancelTime != null)
        {
            if (WEB3DateUtility.compareToSecond(l_tsApplyAcceptTime, l_tsApplyCancelTime) > 0)
            {
                l_applyDetail.updateTimeStamp = l_tsApplyAcceptTime;
            }
            else
            {
                l_applyDetail.updateTimeStamp = l_tsApplyCancelTime;
            }
        }
                
        if (l_applyDetail.updateTimeStamp == null)
        {
            l_applyDetail.acceptUser = null;
        }
        else if (WEB3DateUtility.compareToSecond(l_applyDetail.updateTimeStamp, l_tsApplyAcceptTime) == 0)
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyAcceptUser();
        }
        else
        {
            l_applyDetail.acceptUser = l_applyRow.getApplyCancelUser();
        }
        l_applyDetail.cancelDiv = l_applyRow.getApplyCancelDiv();
        
        //1.10 createResponse( )
        WEB3AdminPointExchangeCancelReleaseConfirmResponse l_response = (WEB3AdminPointExchangeCancelReleaseConfirmResponse)l_request.createResponse();  
        if (l_response == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.11 プロパティセット                
        l_response.applyDetail = l_applyDetail;

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (submit取消解除)<BR>
     * 取消解除の登録を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「（ポイント交換受付）submit取消解除」参照。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     * 
     * @@return webbroker3.point.message.WEB3AdminPointExchangeCancelReleaseCompleteResponse
     * @@roseuid 419B0B9E0343
     */
    protected WEB3AdminPointExchangeCancelReleaseCompleteResponse submitCancelRelease(
        WEB3AdminPointExchangeCancelReleaseCompleteRequest l_request)
        throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " submitCancelRelease(WEB3AdminPointExchangeCancelReleaseCompleteRequest )";
        log.entering(STR_METHOD_NAME);
        
        //1.1 validate( )
        l_request.validate();

        //1.2 getInstanceFromログイン情報( )
        WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();//WEB3SystemLayerException
        if (l_admin == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.3 validate権限(String, boolean)
        l_admin.validateAuthority(WEB3TransactionCategoryDef.POINT_EXCHANGE, true);//WEB3BaseException
        
        //1.4 validate取引パスワード(String)
        l_admin.validateTradingPassword(l_request.password);//WEB3BaseException

        WEB3PointApplyManager l_applyManager = (WEB3PointApplyManager)Services.getService(WEB3PointApplyManager.class);
        //1.5 get申込(long)
        if (l_applyManager == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        WEB3PointApply l_apply = l_applyManager.getApply(Long.parseLong(l_request.applyId));//WEB3BaseException
        if (l_apply == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //1.6 validate部店権限(String)
        l_admin.validateBranchPermission(l_apply.getBranchCode());//WEB3BaseException
        
        //1.7 validateポイント余力(ポイント申込)
        l_applyManager.validatePointPower(l_apply);//WEB3BaseException
        
        //1.8 save申込取消解除(ポイント申込, 管理者)
        l_applyManager.saveApplyCancelRelease(l_apply, l_admin);//WEB3BaseException

        //1.9 createResponse( )
        WEB3AdminPointExchangeCancelReleaseCompleteResponse l_response = (WEB3AdminPointExchangeCancelReleaseCompleteResponse)l_request.createResponse();  

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }
    
    /**
     * (create取得条件文字列)<BR>
     * リクエストデータから、データ取得条件文字列を生成する。<BR>
     * <BR>
     * １）空の文字列を生成する。<BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 条件文字列： "institution_code=?"<BR>
     * <BR>
     * 上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * ３－１）引数.部店コードの要素数 = 1 の場合<BR>
     * <BR>
     * 条件文字列： " and branch_code=?"<BR>
     * <BR>
     * 上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ３－２）引数.部店コードの要素数 > 1 の場合<BR>
     * <BR>
     * 条件文字列： " and (branch_code=? or branch_code=? <BR>
     * or ... or branch_code=?)"<BR>
     * ※括弧内の「branch_code=?」の数が要素数分あるようにする。<BR>
     * <BR>
     * 上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ４）受付区分<BR>
     * <BR>
     * 引数.受付区分 != 2（すべて）の場合<BR>
     * <BR>
     * 条件文字列： " and apply_accept_div=?"<BR>
     * <BR>
     * 上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ５）更新日時<BR>
     * <BR>
     * 条件文字列：<BR>
     *    " and ((apply_accept_timestamp=null and apply_cancel_timestamp=null) or<BR>
     *       (apply_accept_timestamp=null and apply_cancel_timestamp>=?) or<BR>
     *       (apply_cancel_timestamp=null and apply_accept_timestamp>=?) or<BR>
     *       (apply_accept_timestamp>apply_cancel_timestamp and <BR>
     * apply_accept_timestamp>=?) or<BR>
     *       (apply_accept_timestamp<apply_cancel_timestamp and <BR>
     * apply_cancel_timestamp>=?))"<BR>
     * <BR>
     * 上記文字列を１）の文字列の末尾に追加する。<BR>
     * <BR>
     * ６）生成した文字列を返却する。<BR>
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * 
     * @@param l_strAcceptDiv - (受付区分)<BR>
     * 受付区分<BR>
     * 
     * @@return String
     * @@roseuid 419C0E7C01FD
     */
    protected String createGetString(String[] l_strBranchCodes, String l_strAcceptDiv) 
    {
        final String STR_METHOD_NAME = " createGetString(String, String )";
        log.entering(STR_METHOD_NAME);
        
        // １）空の文字列を生成する。
        String l_strQueryString = "";
        
        // ２）証券会社コード
        l_strQueryString += " institution_code = ? ";
        
        // ３）部店コード
        // ３－１）引数.部店コードの要素数 = 1 の場合
        if (l_strBranchCodes != null && l_strBranchCodes.length == 1)
        {
            l_strQueryString += " and branch_code = ? ";
        }
        //３－２）引数.部店コードの要素数 > 1 の場合
        else if (l_strBranchCodes != null && l_strBranchCodes.length != 0)
        {
            String l_strBranchCodeCondition = "";
            int l_intBranchCodeCount = l_strBranchCodes.length;
            l_strBranchCodeCondition += " and ( ";
        
            for (int i = 0; i < l_intBranchCodeCount; i++)
            {
                if ( i == 0)
                {
                    l_strBranchCodeCondition += " branch_code = ? ";
                }
                else
                {
                    l_strBranchCodeCondition += " or branch_code = ? ";
                }
            }
        
            l_strBranchCodeCondition += " ) ";
            l_strQueryString += l_strBranchCodeCondition;
        }
        
        // ４）受付区分
        if (!WEB3AcceptDivDef.ALL.equals(l_strAcceptDiv))
        {
            l_strQueryString += " and apply_accept_div = ? ";
        }
        
        // ５）更新日時
        l_strQueryString += " and ( " +
            " (apply_accept_timestamp is null and apply_cancel_timestamp is null) " +
            " or (apply_accept_timestamp is null and apply_cancel_timestamp >= ? ) " +
            " or (apply_cancel_timestamp is null and apply_accept_timestamp>=?) " +
            " or (apply_accept_timestamp > apply_cancel_timestamp and apply_accept_timestamp >=? ) " +
            " or (apply_accept_timestamp < apply_cancel_timestamp and apply_cancel_timestamp >=? ) " +
            " ) ";

        log.exiting(STR_METHOD_NAME);

        // ６）生成した文字列を返却する。
        return l_strQueryString;
    }
    
    /**
     * (create取得条件データコンテナ)<BR>
     * リクエストデータから、取得条件のデータリストを生成する。 <BR>
     * <BR>
     * １）空のArrayListを生成する。 <BR>
     * <BR>
     * ２）証券会社コード<BR>
     * <BR>
     * 引数.証券会社コードを１）のリストに追加する。<BR>
     * <BR>
     * ３）部店コード<BR>
     * <BR>
     * 引数.部店コードの各要素を１）のリストに追加する。<BR>
     * <BR>
     * ４）受付区分<BR>
     * <BR>
     * 引数.受付区分 != 2（すべて）の場合<BR>
     * <BR>
     * 引数.受付区分を１）のリストに追加する。<BR>
     * <BR>
     * ５）更新日時<BR>
     * <BR>
     * TradingSystem.getSystemTimestamp() - 7日 の値を１）のリストに追加する。<BR>
     * <BR>
     * ６）リストから配列を取得し、返却する。<BR>
     * @@param l_strInsitutionCode - (証券会社コード)<BR>
     * 証券会社コード<BR>
     * 
     * @@param l_strBranchCodes - (部店コード)<BR>
     * 部店コードの配列<BR>
     * 
     * @@param l_strAcceptDiv - (受付区分)<BR>
     * 受付区分<BR>
     * 
     * @@return Object[]
     * @@roseuid 419C21B000E4
     */
    protected Object[] createGetContainer(
        String l_strInsitutionCode, 
        String[] l_strBranchCodes, 
        String l_strAcceptDiv) 
    {
        final String STR_METHOD_NAME = " createGetContainer(String, String[], String )";
        log.entering(STR_METHOD_NAME);
        
        // １）空のArrayListを生成する。 
        ArrayList l_arrayList = new ArrayList();
        
        // ２）証券会社コード
        l_arrayList.add(l_strInsitutionCode);
        
        // ３）部店コード
        int l_intBranchCodeCount = 0;
        if (l_strBranchCodes != null)
        {
            l_intBranchCodeCount = l_strBranchCodes.length;
        }
        for (int i = 0; i < l_intBranchCodeCount; i++)
        {
            l_arrayList.add(l_strBranchCodes[i]);
        }

        // ４）受付区分
        if (!WEB3AcceptDivDef.ALL.equals(l_strAcceptDiv))
        {
            l_arrayList.add(l_strAcceptDiv);
        }

        // ５）更新日時
        Timestamp l_tsSystemTime = GtlUtils.getTradingSystem().getSystemTimestamp();
        Calendar l_cal = new GregorianCalendar();            
        if (l_tsSystemTime == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }
        l_cal.setTime(l_tsSystemTime);            
        l_cal.add(Calendar.DAY_OF_MONTH, -7);            
        Timestamp l_tsDate = new Timestamp(l_cal.getTimeInMillis());
        
        l_arrayList.add(l_tsDate);
        l_arrayList.add(l_tsDate);
        l_arrayList.add(l_tsDate);
        l_arrayList.add(l_tsDate);

        // ６）リストから配列を取得し、返却する。
        Object[] l_queryContainers = new Object[l_arrayList.size()];
        l_arrayList.toArray(l_queryContainers); 

        log.exiting(STR_METHOD_NAME);
        return l_queryContainers;
    }
    
    /**
     * (sortポイント申込明細)<BR>
     * ポイント申込明細の配列を、リクエストデータ.ソートキーに従いソートする。<BR>
     * <BR>
     * １）空のArrayListを生成する。<BR>
     * <BR>
     * ２）引数.ソートキーの各要素について処理する。<BR>
     * <BR>
     * ２－１）ソートキー.キー項目=”部店コード”の場合<BR>
     * <BR>
     *    部店コードComparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－２）ソートキー.キー項目=”顧客コード”の場合<BR>
     * <BR>
     *    顧客コードComparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－３）ソートキー.キー項目=”景品番号”の場合<BR>
     * <BR>
     *    景品番号Comparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－４）ソートキー.キー項目=”景品名”の場合<BR>
     * <BR>
     *    景品名Comparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－５）ソートキー.キー項目=”申込日時”の場合<BR>
     * <BR>
     *    申込日時Comparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－６）ソートキー.キー項目=”受付区分”の場合<BR>
     * <BR>
     *    受付区分Comparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－７）ソートキー.キー項目=”更新日時”の場合<BR>
     * <BR>
     *    更新日時Comparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－８）ソートキー.キー項目=”受付ユーザ”の場合<BR>
     * <BR>
     *    受付ユーザComparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ２－９）ソートキー.キー項目=”取消区分”の場合<BR>
     * <BR>
     *    取消区分Comparatorインスタンスを生成し、１）のリストに追加する。<BR>
     * <BR>
     *    [コンストラクタにセットする引数]<BR>
     *    orderBy： ソートキー.昇順/降順<BR>
     * <BR>
     * ３）ArrayListからComparatorの配列を取得する。 <BR>
     * <BR>
     * ４）Comparatorの配列順のソート処理を行う。<BR>
     * <BR>
     *    WEB3ArraysUtility.sort(obj, com)<BR>
     * <BR>
     *    [sortにセットする引数]<BR>
     *    obj： 引数.明細<BR>
     *    com： ３）で取得した配列<BR>
     * <BR>
     * ５）ソートされた明細の配列を返却する。<BR>
     * @@param l_pointApplyDetails - (明細)<BR>
     * ポイント申込明細の配列<BR>
     * 
     * @@param l_pointSortKeys - (ソートキー)<BR>
     * ポイント交換一覧ソートキーの配列<BR>
     * 
     * @@return WEB3AdminPointApplyDetail[]
     * @@roseuid 419C29C70180
     */
    protected WEB3AdminPointApplyDetail[] sortPointApplyDetail(
        WEB3AdminPointApplyDetail[] l_pointApplyDetails, 
        WEB3PointSortKey[] l_pointSortKeys) 
    {
        final String STR_METHOD_NAME = " sortPointApplyDetail(WEB3AdminPointApplyDetail[], WEB3PointSortKey[] )";
        log.entering(STR_METHOD_NAME);
        
        // １）空のArrayListを生成する。
        ArrayList l_arrayList = new ArrayList();
        
        // ２）引数.ソートキーの各要素について処理する。
        int l_intSortKeyCount = 0;
        if (l_pointSortKeys != null)
        {
            l_intSortKeyCount = l_pointSortKeys.length;
        }
        for (int i = 0; i < l_intSortKeyCount; i++)
        {
            if (l_pointSortKeys[i] == null)
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BaseRuntimeException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                    this.getClass().getName() + STR_METHOD_NAME);
            }
            String l_strKeyItem = l_pointSortKeys[i].keyItem;
            String l_strAscDesc = l_pointSortKeys[i].ascDesc;
            Comparator l_com = null;

            // ２－１）ソートキー.キー項目=”部店コード”の場合
            if (WEB3PointKeyItemDef.BRANCH_CODE.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointBranchCodeComparator(l_strAscDesc);
            }            
            // ２－２）ソートキー.キー項目=”顧客コード”の場合
            else if (WEB3PointKeyItemDef.ACCOUNT_CODE.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointAccountCodeComparator(l_strAscDesc);
            }
            // ２－３）ソートキー.キー項目=”景品番号”の場合
            else if (WEB3PointKeyItemDef.PREMINUM_NO.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointPremiumNoComparator(l_strAscDesc);
            }
            // ２－４）ソートキー.キー項目=”景品名”の場合
            else if (WEB3PointKeyItemDef.PREMIUM_NAME.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointPremiumNameComparator(l_strAscDesc);
            }
            // ２－５）ソートキー.キー項目=”申込日時”の場合
            else if (WEB3PointKeyItemDef.APPLY_TIMESTAMP.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointApplyTimestampComparator(l_strAscDesc);
            }
            // ２－６）ソートキー.キー項目=”受付区分”の場合
            else if (WEB3PointKeyItemDef.APPLY_ACCEPT_DIV.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointAcceptDivComparator(l_strAscDesc);
            }
            // ２－７）ソートキー.キー項目=”更新日時”の場合
            else if (WEB3PointKeyItemDef.LAST_UPDATED_TIMESTAMP.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointLastUpdatedTimestampComparator(l_strAscDesc);
            }
            // ２－８）ソートキー.キー項目=”受付ユーザ”の場合
            else if (WEB3PointKeyItemDef.APPLY_ACCEPT_USER.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointAcceptUserComparator(l_strAscDesc);
            }
            // ２－９）ソートキー.キー項目=”取消区分”の場合
            else if (WEB3PointKeyItemDef.APPLY_CANCEL_DIV.equals(l_strKeyItem))
            {
                l_com = new WEB3AdminPointCancelDivComparator(l_strAscDesc);
            }

            if (l_com != null)
            {
                l_arrayList.add(l_com);
            }
        }
        // ３）ArrayListからComparatorの配列を取得する。 
        Comparator[] l_comparators = new Comparator[l_arrayList.size()];
        l_arrayList.toArray(l_comparators);
        
        // ４）Comparatorの配列順のソート処理を行う。
        WEB3ArraysUtility.sort(l_pointApplyDetails, l_comparators);

        log.exiting(STR_METHOD_NAME);
        return l_pointApplyDetails;
    }
    
}
@
