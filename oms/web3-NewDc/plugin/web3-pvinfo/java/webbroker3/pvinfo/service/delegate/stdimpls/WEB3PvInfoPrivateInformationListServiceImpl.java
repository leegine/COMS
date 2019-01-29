head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.10.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoPrivateInformationListServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスImpl(WEB3PvInfoPrivateInformationListServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 王亞洲(中訊) 新規作成
Revesion History : 2004/10/25 李弘毅(中訊) 作成
Revesion History : 2005/08/16 劉(FLJ), 沢村(SRA) 未取込No.016(パフォーマンス改善)
Revesion History : 2005/08/29 川添(SRA) パスワード変更定時変更メッセージ対応
Revesion History : 2005/10/07 沈迪(中訊) 仕様変更No.056~058
Revesion History : 2006/05/22 肖志偉(中訊) 仕様変更 モデル063
Revesion History : 2006/07/27 齊珂(中訊) 仕様変更 モデル068、069
Revesion History : 2006/09/12 張騰宇(中訊) 仕様変更モデル070
Revesion History : 2007/02/26 金傑(中訊) 仕様変更モデル073
Revesion History : 2007/06/27 武波(中訊) 仕様変更モデル079
Revesion History : 2007/06/29 武波(中訊) 仕様変更モデル081
Revision History : 2007/07/13 謝旋(中訊) 仕様変更モデル082
Revision History : 2007/09/07 トウ鋒鋼(中訊) 仕様変更モデル091
Revision History : 2007/09/13 トウ鋒鋼(中訊) 仕様変更モデル093
Revision History : 2007/12/07 孟亞南(中訊) 仕様変更モデル094
Revision History : 2008/02/18 武波(中訊) 仕様変更モデル102
Revision History : 2008/10/07 許丹(中訊) 仕様変更モデル110
Revision History : 2008/10/07 劉仁和(中訊) 仕様変更モデル107
Revision History : 2009/01/14 武波(中訊) 仕様変更モデル114
*/
package webbroker3.pvinfo.service.delegate.stdimpls;
    
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.tc.gentrade.CommonOrderValidator;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3LoginAttributeKeyDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3PaymentStopDivDef;
import webbroker3.common.define.WEB3PvInfoBlinkDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoConditionDef;
import webbroker3.common.define.WEB3PvInfoDeleteFlagDef;
import webbroker3.common.define.WEB3PvInfoDisplayDeviceDef;
import webbroker3.common.define.WEB3PvInfoEffectiveFlagDef;
import webbroker3.common.define.WEB3PvInfoLastUpdateTimeDisplayFlagDef;
import webbroker3.common.define.WEB3PvInfoReadFlagDef;
import webbroker3.common.define.WEB3PwdChangeDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ifodeposit.WEB3IfoDepositCalc;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.pvinfo.WEB3PvInfoDataManager;
import webbroker3.pvinfo.WEB3PvInfoDisplayMessageAccrualDateComparator;
import webbroker3.pvinfo.WEB3PvInfoDisplayTermToComparator;
import webbroker3.pvinfo.WEB3PvInfoFinalModTimeStampComparator;
import webbroker3.pvinfo.WEB3PvInfoReadUnReadComparator;
import webbroker3.pvinfo.WEB3PvInfoRegistMethodComparator;
import webbroker3.pvinfo.data.BrowseHistoryParams;
import webbroker3.pvinfo.data.BrowseHistoryRow;
import webbroker3.pvinfo.data.DisplayContentsParams;
import webbroker3.pvinfo.data.DisplayContentsRow;
import webbroker3.pvinfo.define.WEB3PvInfoConditionNoIntDef;
import webbroker3.pvinfo.define.WEB3PvInfoProductDivDef;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayContentsUnit;
import webbroker3.pvinfo.message.WEB3PvInfoDisplayMessageUnit;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionRequest;
import webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse;
import webbroker3.pvinfo.message.WEB3PvInfoIpoProductUnit;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateRequest;
import webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse;
import webbroker3.pvinfo.message.WEB3PvInfoTradeCountUnit;
import webbroker3.pvinfo.message.WEB3PvInfoTradePriceUnit;
import webbroker3.pvinfo.service.delegate.WEB3PvInfoPrivateInformationListService;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.define.WEB3TPShortfallGenerationStateDivDef;
import webbroker3.tradingpower.define.WEB3TPTradingStopDivDef;
import webbroker3.util.WEB3ArraysUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3PageIndexInfo;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービスImpl)<BR>
 * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス実装クラス<BR>
 * @@author 王亞洲
 * @@version 1.00
 */
public class WEB3PvInfoPrivateInformationListServiceImpl extends WEB3ClientRequestService implements WEB3PvInfoPrivateInformationListService
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PvInfoPrivateInformationListServiceImpl.class);
    /**
     * ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧処理を行う。<BR>
     * <BR>
     * 引数の型により、以下のメソッドを呼び分ける。<BR>
     * <BR>
     * ○顧客連絡リクエストの場合<BR>
     * 　@・get顧客連絡画面()メソッドをコール<BR>
     * <BR>
     * ○証券会社連絡リクエストの場合<BR>
     * 　@・get証券会社連絡画面()メソッドをコール<BR>
     * <BR>
     * ○注文約定状況リクエストの場合<BR>
     * 　@・get注文約定状況画面()メソッドをコール<BR>
     * @@param l_request - リクエスト<BR>
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException
     * @@roseuid 4145297302DD
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " execute(WEB3GenRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3GenResponse l_response = null;
        if (l_request instanceof WEB3PvInfoAccountConnectionRequest)
        {
            //顧客連絡リクエストの場合、get顧客連絡画面()メソッドをコール。
            l_response = getAccountConnectionScreen((WEB3PvInfoAccountConnectionRequest)l_request);
        }
        else if (l_request instanceof WEB3PvInfoInstitutionConnectionRequest)
        {
            //証券会社連絡リクエストの場合、get証券会社連絡画面()メソッドをコール。
            l_response = getInstitutionConnectionScreen((WEB3PvInfoInstitutionConnectionRequest)l_request);
        }
        else if(l_request instanceof WEB3PvInfoOrderExecStateRequest)
        {
            //注文約定状況リクエストの場合、get注文約定状況画面()メソッドをコール。
            l_response = getOrderExecStateScreen((WEB3PvInfoOrderExecStateRequest)l_request);
        }
        else
        {
            String l_strErrorMessage =
                "パラメータの類型が不正、該当するWEB3PvInfoAccountConnectionRequest," +
                "WEB3PvInfoInstitutionConnectionRequest,WEB3PvInfoOrderExecStateRequest類型。";
            log.error(l_strErrorMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_strErrorMessage);
        }

        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get顧客連絡画面)<BR>
     * 顧客連絡画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス)get顧客連絡画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 顧客連絡リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoAccountConnectionResponse
     * @@roseuid 414529B002FC
     */
    protected WEB3PvInfoAccountConnectionResponse getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getAccountConnectionScreen(WEB3PvInfoAccountConnectionRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoAccountConnectionResponse l_response = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1.1 validate()
        l_request.validate();

        //1.2 get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 getMainAccount()
        l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        ///////////////////////////////////////////////////////追加
        //1.5 get閲覧履歴ParamsTbl(顧客)
        Hashtable tbl= getBrowseHistoryParamsTbl(l_mainAccount);
        ///////////////////////////////////////////////////////
        
        //1.6 get表示条件番号一覧－管理対象( )
        String[] l_strManageDispConditionNoLists = this.getManagedDispCondNoList();

        // create検索条件文字列for表示内容(String[], Hashtable)
        String l_strQueryStringForDispContents =
            this.createQueryStringForDispContents(l_strManageDispConditionNoLists, tbl);

        // create検索条件データコンテナfor表示内容(顧客, String[], Hashtable)
        String[] l_strQueryDataContainerForDispContents =
            this.createQueryDataContainerForDispContents(l_mainAccount, l_strManageDispConditionNoLists, tbl);

        //1.9 get表示内容Params一覧(String, String[], String)
        List l_lisDisplayContentsParams = l_dataManager.getDisplayContentsParamsList(l_strQueryStringForDispContents, l_strQueryDataContainerForDispContents, null);

        //1.11 ArrayList()
        List l_lisArrayList = new ArrayList();
        
        if (l_lisDisplayContentsParams != null)
        {
            //1.10 get表示内容Params一覧－表示対象(顧客, 補助口座, 表示内容Params[])
            DisplayContentsParams[] l_displayContensParamLists = new DisplayContentsParams[l_lisDisplayContentsParams.size()];
            l_lisDisplayContentsParams.toArray(l_displayContensParamLists);
            l_lisDisplayContentsParams = getDisplayContensInDisplayTerm(l_mainAccount, l_subAccount, l_displayContensParamLists);     
                 
            if (l_lisDisplayContentsParams != null)
            {

                //1.12 (*) get表示内容Params一覧()の戻り値の要素数分Loop処理を実施する。
                int l_intDispContParamsCnt = l_lisDisplayContentsParams.size();
                log.debug("get表示内容Params一覧()の戻り値の要素数 = " + l_intDispContParamsCnt);
                for (int i = 0; i < l_intDispContParamsCnt; i++)
                {
                    //1.12.1 get閲覧履歴Params(顧客, long)
                    DisplayContentsParams l_dispContentsParams = (DisplayContentsParams)l_lisDisplayContentsParams.get(i);
        
                    //修正///////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    //BrowseHistoryParams l_browseHistoryParams = l_dataManager.getBrowseHistoryParams(l_mainAccount, l_dispContentsParams.display_contents_id);
                    Long l_lngConditionId = new Long(l_dispContentsParams.getDisplayContentsId());
                    BrowseHistoryParams l_browseHistoryParams = null;
                    if(tbl!=null){
                        l_browseHistoryParams = (BrowseHistoryParams) tbl.get(l_lngConditionId);
                    }
                    //修正///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
                    //1.12.2 ダイレクト指定チェック
                    if (WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_dispContentsParams.condition_no) && l_browseHistoryParams == null)
                    {
                        continue;
                    }
        
                    //1.12.3 削除フラグチェック
                    if (l_browseHistoryParams != null && WEB3PvInfoDeleteFlagDef.DELETE_YES.equals(l_browseHistoryParams.delete_flag))
                    {
                        continue;
                    }
                    //1.12.6 表示メッセージ情報()
                    WEB3PvInfoDisplayMessageUnit l_dispMessage = new WEB3PvInfoDisplayMessageUnit();
        
                    //1.12.4 分岐フロー      
                    Date l_datClaimGenDate = null;  
                    Date l_datAdvanceGenDate = null;  
                    if ((WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN.equals(l_dispContentsParams.condition_no) 
                            || WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE.equals(l_dispContentsParams.condition_no)))
                    {
                        //1.12.4.1 get入金請求発生日(補助口座 : 補助口座)
                        l_datClaimGenDate = l_dataManager.getPayClaimGenDate((WEB3GentradeSubAccount)l_subAccount);
                        l_dispMessage.displayMessageDate = l_datClaimGenDate;
                    }
        
                    //1.12.5 分岐フロー
                    else if (WEB3PvInfoConditionDef.ADVANCE_GENERATION.equals(l_dispContentsParams.condition_no))
                    {
                        //1.12.5.1 get立替金発生日(補助口座 : 補助口座)
                        l_datAdvanceGenDate = l_dataManager.getAdvanceGenDate((WEB3GentradeSubAccount)l_subAccount);
                        l_dispMessage.displayMessageDate = l_datAdvanceGenDate;
                    }
                    else
                    {
                        l_dispMessage.displayMessageDate = GtlUtils.getTradingSystem().getBizDate();
                    }
        
                    //1.12.7 プロパティセット
                    l_dispMessage.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
                    log.debug("表示メッセージ情報.displayContentsId = " + l_dispMessage.displayContentsId);
                    l_dispMessage.conditionNumber = l_dispContentsParams.condition_no;
                    log.debug("表示メッセージ情報.conditionNumber = " + l_dispMessage.conditionNumber);
                    l_dispMessage.priorityDiv = l_dispContentsParams.priority_div;
                    log.debug("表示メッセージ情報.priorityDiv = " + l_dispMessage.priorityDiv);
                    l_dispMessage.listStartDate = null;
                    log.debug("表示メッセージ情報.listStartDate = " + l_dispMessage.listStartDate);
                    l_dispMessage.listEndDate = null;
                    log.debug("表示メッセージ情報.listEndDate = " + l_dispMessage.listEndDate);
                    l_dispMessage.displayTitle = l_dispContentsParams.display_title;
                    log.debug("表示メッセージ情報.displayTitle = " + l_dispMessage.displayTitle);
                    l_dispMessage.displayMessage = null;
                    log.debug("表示メッセージ情報.displayMessage = " + l_dispMessage.displayMessage);
                    l_dispMessage.displayColor = l_dispContentsParams.display_color;
                    log.debug("表示メッセージ情報.displayColor = " + l_dispMessage.displayColor);
        
                    if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
                    {
                        l_dispMessage.blinkDisplayFlag = true;
                    }
                    else
                    {
                        l_dispMessage.blinkDisplayFlag = false;
                    }
                    log.debug("表示メッセージ情報.blinkDisplayFlag = " + l_dispMessage.blinkDisplayFlag);
        
                    l_dispMessage.displayUrl = null;
                    log.debug("表示メッセージ情報.displayUrl = " + l_dispMessage.displayUrl);
        
                    if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
                    {
                        l_dispMessage.lastUpdateTimeDisplayFlag = false;
                    }
                    else
                    {
                        l_dispMessage.lastUpdateTimeDisplayFlag = true;
                    }
                    log.debug("表示メッセージ情報.lastUpdateTimeDisplayFlag = " + l_dispMessage.lastUpdateTimeDisplayFlag);
        
                    if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
                    {
                        l_dispMessage.effectiveFlag = false;
                    }
                    else
                    {
                        l_dispMessage.effectiveFlag = true;
                    }
                    log.debug("表示メッセージ情報.effectiveFlag = " + l_dispMessage.effectiveFlag);
        
                    l_dispMessage.displayDevice = l_dispContentsParams.display_device;
                    log.debug("表示メッセージ情報.displayDevice = " + l_dispMessage.displayDevice);
                    l_dispMessage.lastUpdateMember = null;
                    log.debug("表示メッセージ情報.lastUpdateMember = " + l_dispMessage.lastUpdateMember);
                    l_dispMessage.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
                    log.debug("表示メッセージ情報.lastUpdatedTimestamp = " + l_dispMessage.lastUpdatedTimestamp);
                    //閲覧履歴ID ＝　@get閲覧履歴Params()の戻り値 == nullの場合は、nullをセット。
                    if (l_browseHistoryParams == null)
                    {
                        l_dispMessage.browseHistoryId = null;
                    }
                    //以外、get閲覧履歴Params()の戻り値.閲覧履歴IDをセット。
                    else
                    {
                        l_dispMessage.browseHistoryId = "" + l_browseHistoryParams.getBrowseHistoryId();
                    }
        
                    log.debug("表示メッセージ情報.displayMessageDate = " + l_dispMessage.displayMessageDate);
                    if (l_browseHistoryParams == null)
                    {
                        l_dispMessage.readFlag = false;
                    }
                    else if (WEB3PvInfoReadFlagDef.READ_YES.equals(l_browseHistoryParams.read_flag))
                    {
                       l_dispMessage.readFlag = true;
                    }
                    else
                    {
                        l_dispMessage.readFlag = false;
                    }
                    log.debug("表示メッセージ情報.readFlag = " + l_dispMessage.readFlag);
        
                    //1.12.8 add(表示メッセージ情報 : Object)
                    l_lisArrayList.add(l_dispMessage);
                }
            }
        }
        //1.13 toArray()
        WEB3PvInfoDisplayMessageUnit[] l_dispMessageUnits = new WEB3PvInfoDisplayMessageUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_dispMessageUnits);
        
        //1.14 sort表示メッセージ情報
        sortDispMessageUnit(l_dispMessageUnits);

        //1.15 createResponse()
        l_response = (WEB3PvInfoAccountConnectionResponse)l_request.createResponse();

        //1.16 分岐フロー: toArray()の戻り値.length == 0の場合
        if (l_dispMessageUnits.length == 0)
        {    
            // プロパティセット
            //顧客名
            l_response.accountName = l_mainAccount.getNameDetails().getFamilyName();
            //総ページ数 ＝　@1
            l_response.totalPages = "1";
            //総レコード数  ＝　@0
            l_response.totalRecords = "0";
            //表示ページ番号 ＝　@1
            l_response.pageIndex = "1";
            //表示メッセージ情報一覧   ＝　@null
            l_response.displayMessageUnits = null;

            log.debug("l_response.accountName = " + l_response.accountName);
            log.debug("l_response.totalPages = " + l_response.totalPages);
            log.debug("l_response.totalRecords = " + l_response.totalRecords);
            log.debug("l_response.pageIndex = " + l_response.pageIndex);
            log.debug("l_response.displayMessageUnits = " + l_response.displayMessageUnits);
            log.exiting(STR_METHOD_NAME);
            return l_response;

        }
        //1.17 分岐フロー: toArray()の戻り値.length != 0の場合
        else 
        {
            //プロパティセット
            //顧客名
            l_response.accountName = l_mainAccount.getNameDetails().getFamilyName();

            //仕様変更:総レコード数 = toArray()の戻り値.length
            l_response.totalRecords = WEB3StringTypeUtility.formatNumber(l_dispMessageUnits.length);

            int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
            int l_intPageSize = Integer.parseInt(l_request.pageSize);

            WEB3PageIndexInfo l_pageIndexInfo = new WEB3PageIndexInfo(l_dispMessageUnits, l_intPageIndex, l_intPageSize);
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            l_response.displayMessageUnits = (WEB3PvInfoDisplayMessageUnit[])l_pageIndexInfo.getArrayReturned(WEB3PvInfoDisplayMessageUnit.class);

            log.debug("l_response.accountName" + "=" + l_response.accountName);
            log.debug("l_response.totalPages" + "=" + l_response.totalPages);
            log.debug("l_response.pageIndex" + "=" + l_response.pageIndex);
            log.debug("l_response.totalRecords" + "=" + l_response.totalRecords);
            log.debug("l_response.displayMessageUnits" + "=" + l_response.displayMessageUnits.length);
        }
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get証券会社連絡画面)<BR>
     * 証券会社連絡画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス)get証券会社連絡画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 証券会社連絡リクエストオブジェクト<BR>
     * @@return webbroker3.pvinfo.message.WEB3PvInfoInstitutionConnectionResponse
     * @@roseuid 414529B0031B
     */
    protected WEB3PvInfoInstitutionConnectionResponse getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest l_request) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getInstitutionConnectionScreen(WEB3PvInfoInstitutionConnectionRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoInstitutionConnectionResponse l_response = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1.1 validate()
        l_request.validate();

        //1.2 get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.3 getMainAccount()
        l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.4 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.5 get表示条件番号一覧－管理対象外( )
        String[] l_strManageDispConditionNoLists = this.getManagedOutDispCondNoList();

        //1.6 create検索条件文字列for表示内容(String[])
        String l_strQueryStringForDispContents = this.createQueryStringForDispContents(l_strManageDispConditionNoLists);

        //1.7 create検索条件データコンテナfor表示内容(顧客, String[])
        String[] l_strQueryDataContainerForDispContents = this.createQueryDataContainerForDispContents(l_mainAccount, l_strManageDispConditionNoLists);

        //1.8 get表示内容Params一覧(String, String[], String)
        String l_strCondition = "priority_div asc,"
            + "last_updated_timestamp desc,"
            + "term_to desc";
        List l_lisDisplayContentsParams = l_dataManager.getDisplayContentsParamsList(l_strQueryStringForDispContents, l_strQueryDataContainerForDispContents, l_strCondition);
        log.debug("get表示内容Params一覧(String, String[], String)を執行します");

        //1.10 ArrayList()
        List l_lisArrayList = new ArrayList();

        WEB3PageIndexInfo l_pageIndexInfo = null;
        
        if (l_lisDisplayContentsParams != null)
        {
            //1.9 get表示内容Params一覧－表示対象(顧客, 補助口座, 表示内容Params[])    
            DisplayContentsParams[] l_displayContensParamLists = new DisplayContentsParams[l_lisDisplayContentsParams.size()];
            l_lisDisplayContentsParams.toArray(l_displayContensParamLists);
//          *** 未取込No.016 START ***
            l_lisDisplayContentsParams = this.getDisplayContensInDisplayTerm(l_mainAccount, l_subAccount, l_displayContensParamLists);
//          *** 未取込No.016 END ***
            if (l_lisDisplayContentsParams != null)
            {

                //1.11 get表示内容Params一覧()の戻り値のうち、表示対象行（fromIndex ～ toIndex）の間Loop処理を実施する。
                DisplayContentsParams[] l_dcParams = new DisplayContentsParams[l_lisDisplayContentsParams.size()];
                l_lisDisplayContentsParams.toArray(l_dcParams);
                int l_intPageIndex = Integer.parseInt(l_request.pageIndex);
                int l_intPageSize = Integer.parseInt(l_request.pageSize);
                l_pageIndexInfo = new WEB3PageIndexInfo(l_dcParams, l_intPageIndex, l_intPageSize);
                DisplayContentsParams[] l_dcParamsReturns = (DisplayContentsParams[])l_pageIndexInfo.getArrayReturned(DisplayContentsParams.class);

                if (l_dcParamsReturns != null)
                {
                
                    int l_intDispContParamsCnt = l_dcParamsReturns.length;
                    for (int i = 0; i < l_intDispContParamsCnt; i++)
                    {
                        DisplayContentsParams l_dispContentsParams = l_dcParamsReturns[i];
            
                        //1.11.1 表示内容情報()
                        WEB3PvInfoDisplayContentsUnit l_dispContentsUnits = new WEB3PvInfoDisplayContentsUnit();
            
                        //1.11.2 プロパティセット
                        l_dispContentsUnits.displayContentsId = WEB3StringTypeUtility.formatNumber(l_dispContentsParams.display_contents_id);
                        log.debug("表示内容情報.displayContentsId" + "=" + l_dispContentsUnits.displayContentsId);
                        l_dispContentsUnits.conditionNumber = l_dispContentsParams.condition_no;
                        log.debug("表示内容情報.conditionNumber" + "=" + l_dispContentsUnits.conditionNumber);
                        l_dispContentsUnits.priorityDiv = l_dispContentsParams.priority_div;
                        log.debug("表示内容情報.priorityDiv" + "=" + l_dispContentsUnits.priorityDiv);
                        l_dispContentsUnits.listStartDate = null;
                        log.debug("表示内容情報.listStartDate" + "=" + l_dispContentsUnits.listStartDate);
                        l_dispContentsUnits.listEndDate = null;
                        log.debug("表示内容情報.listEndDate" + "=" + l_dispContentsUnits.listEndDate);
                        l_dispContentsUnits.displayTitle = l_dispContentsParams.display_title;
                        log.debug("表示内容情報.displayTitle" + "=" + l_dispContentsUnits.displayTitle);
                        l_dispContentsUnits.displayMessage = null;
                        log.debug("表示内容情報.displayMessage" + "=" + l_dispContentsUnits.displayMessage);
                        l_dispContentsUnits.displayColor = l_dispContentsParams.display_color;
                        log.debug("表示内容情報.displayColor" + "=" + l_dispContentsUnits.displayColor);
            
                        if (WEB3PvInfoBlinkDisplayFlagDef.BLINK_DISP_YES.equals(l_dispContentsParams.blink_display_flag))
                        {
                            l_dispContentsUnits.blinkDisplayFlag = true;
                        }
                        else
                        {
                            l_dispContentsUnits.blinkDisplayFlag = false;
                        }
                        log.debug("表示内容情報.blinkDisplayFlag" + "=" + l_dispContentsUnits.blinkDisplayFlag);
            
                        l_dispContentsUnits.displayUrl = null;
                        log.debug("表示内容情報.displayUrl" + "=" + l_dispContentsUnits.displayUrl);
            
                        if (WEB3PvInfoLastUpdateTimeDisplayFlagDef.DISPLAY_YES.equals(l_dispContentsParams.last_update_time_display_flag))
                        {
                            l_dispContentsUnits.lastUpdateTimeDisplayFlag = false;
                        }
                        else
                        {
                            l_dispContentsUnits.lastUpdateTimeDisplayFlag = true;
                        }
                        log.debug("表示内容情報.lastUpdateTimeDisplayFlag" + "=" + l_dispContentsUnits.lastUpdateTimeDisplayFlag);
            
                        if (WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES.equals(l_dispContentsParams.effective_flag))
                        {
                            l_dispContentsUnits.effectiveFlag = false;
                        }
                        else
                        {
                            l_dispContentsUnits.effectiveFlag = true;
                        }
                        log.debug("表示内容情報.effectiveFlag" + "=" + l_dispContentsUnits.effectiveFlag);
            
                        l_dispContentsUnits.displayDevice = l_dispContentsParams.display_device;
                        log.debug("表示内容情報.displayDevice" + "=" + l_dispContentsUnits.displayDevice);
                        l_dispContentsUnits.lastUpdateMember = null;
                        log.debug("表示内容情報.lastUpdateMember" + "=" + l_dispContentsUnits.lastUpdateMember);
                        l_dispContentsUnits.lastUpdatedTimestamp = l_dispContentsParams.last_updated_timestamp;
                        log.debug("表示内容情報.lastUpdatedTimestamp" + "=" + l_dispContentsUnits.lastUpdatedTimestamp);
            
                        //1.11.3 add(表示内容情報オブジェクト : Object)
                        l_lisArrayList.add(l_dispContentsUnits);
                    }
                }
            }
        }    

        //1.12  toArray()
        WEB3PvInfoDisplayContentsUnit[] l_dispContentsUnits = new WEB3PvInfoDisplayContentsUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_dispContentsUnits);

        //1.13  createResponse()
        l_response = (WEB3PvInfoInstitutionConnectionResponse)l_request.createResponse();

        //1.14 分岐フロー :toArray()の戻り値.length == 0の場合
        if (l_dispContentsUnits.length == 0)
        {
            //1.14.1 プロパティセット
            //証券会社名 ＝　@getMainAccount()の戻り値.getInstitution().会社名
            l_response.institutionName = l_mainAccount.getInstitution().getInstitutionName();
            //総ページ数 ＝　@1
            l_response.totalPages = "1";
            //総レコード数 ＝　@0
            l_response.totalRecords = "0";
            //表示ページ番号 ＝　@1
            l_response.pageIndex = "1";
            //表示内容情報一覧 ＝　@null
            l_response.displayContentsUnits = null;
        }
        //1.15 分岐フロー : toArray()の戻り値.length != 0の場合
        else
        {   
            l_response.institutionName = l_mainAccount.getInstitution().getInstitutionName();
            l_response.totalRecords = l_pageIndexInfo.getTotalRecords() + "";
            l_response.totalPages = l_pageIndexInfo.getTotalPages() + "";
            l_response.pageIndex = l_pageIndexInfo.getPageIndex() + "";
            l_response.displayContentsUnits = l_dispContentsUnits;
    
            log.debug("***l_response.institutionName" + "=" + l_response.institutionName);
            log.debug("***l_response.totalPages" + "=" + l_response.totalPages);
            log.debug("***l_response.totalRecords" + "=" + l_response.totalRecords);
            log.debug("***l_response.pageIndex" + "=" + l_response.pageIndex);
            log.debug("***l_response.displayContentsUnits" + "=" + l_response.displayContentsUnits.length);
            log.exiting(STR_METHOD_NAME);
        }    
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (get注文約定状況画面)<BR>
     * 注文約定状況画面表示処理を行う。<BR>
     * <BR>
     * シーケンス図<BR>
     * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス)get注文約定状況画面」参照<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * 注文約定状況リクエストオブジェクト<BR>
     *
     * @@return webbroker3.pvinfo.message.WEB3PvInfoOrderExecStateResponse
     * @@roseuid 414529B0032B
     */
    protected WEB3PvInfoOrderExecStateResponse getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest l_request) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " getOrderExecStateScreen(WEB3PvInfoOrderExecStateRequest)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoOrderExecStateResponse l_response = null;
        WEB3GentradeMainAccount l_mainAccount = null;
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //1.1 get補助口座(SubAccountTypeEnum)
        SubAccount l_subAccount = this.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        //1.2 getMainAccount()
        l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //1.3 validate注文受付可能()
        WEB3GentradeTradingTimeManagement.validateOrderAccept();

        //1.4 create検索条件文字列for注文件数()
        String l_strQueryStringForOrderCnt = this.createQueryStringForOrderCnt();

        //1.5 create検索条件データコンテナfor注文件数(顧客)
        String[] l_strQueryDataContainerForOrderCnts = this.createQueryDataContainerForOrderCnt(l_mainAccount);

        //1.6 注文・約定件数取得対象の商品区分の数分Loop処理
        int[][] l_intCounts = new int[5][3];

        for (int i = 0; i <= 4; i++)
        {
            //1.6.1 get注文件数(String, boolean, String, String[])
            l_intCounts[i][0] = l_dataManager.getOrderCnt(WEB3StringTypeUtility.formatNumber(i), true, l_strQueryStringForOrderCnt, l_strQueryDataContainerForOrderCnts);

            //1.6.2 get注文件数(String, boolean, String, String[])
            l_intCounts[i][1] = l_dataManager.getOrderCnt(WEB3StringTypeUtility.formatNumber(i), false, l_strQueryStringForOrderCnt, l_strQueryDataContainerForOrderCnts);

            //1.6.3 get約定件数(顧客, String)
            l_intCounts[i][2] = l_dataManager.getExecuteCnt(l_mainAccount, WEB3StringTypeUtility.formatNumber(i));
            

        }
        
        //以下の商品区分についてLoopする。
        //・"0：現物"
        //・"1：信用"
        //・"2：先物"
        //・"3：オプシ
        //1.7 売買代金取得対象の商品区分の数分Loop処理
        double[][] l_dblPrice = new double[4][1];
        String[] l_strProductDivDef = {WEB3PvInfoProductDivDef.PD_SPOT,WEB3PvInfoProductDivDef.PD_CREDIT,WEB3PvInfoProductDivDef.PD_FUTURE,WEB3PvInfoProductDivDef.PD_OPTION};
        for (int i = 0; i < l_strProductDivDef.length; i++)
        {
            //1.7.1 get売買代金(商品区分 : String, 検索条件文字列 : String, 検索条件データコンテナ : String[])
           l_dblPrice[i][0] = l_dataManager.getTradePrice(l_strProductDivDef[i],l_strQueryStringForOrderCnt,l_strQueryDataContainerForOrderCnts);
        }
        //l_dataManager.getTradePrice()
        //1.8 取引件数情報()
        WEB3PvInfoTradeCountUnit l_todayTradeCountUnit = new WEB3PvInfoTradeCountUnit();
        WEB3PvInfoTradeCountUnit l_tomorrowTradeCountUnit = new WEB3PvInfoTradeCountUnit();
        WEB3PvInfoTradeCountUnit l_todayOrderTradeCountUnit = new WEB3PvInfoTradeCountUnit();

        //1.9 プロパティセット

        l_todayTradeCountUnit.equityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[0][0]);
        log.debug("当日注文取引件数情報.equityTradeCount" + "=" + l_todayTradeCountUnit.equityTradeCount);
        l_todayTradeCountUnit.marginTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[1][0]);
        log.debug("当日注文取引件数情報.marginTradeCount" + "=" + l_todayTradeCountUnit.marginTradeCount);
        l_todayTradeCountUnit.futuresTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[2][0]);
        log.debug("当日注文取引件数情報.futuresTradeCount" + "=" + l_todayTradeCountUnit.futuresTradeCount);
        l_todayTradeCountUnit.optionsTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[3][0]);
        log.debug("当日注文取引件数情報.optionsTradeCount" + "=" + l_todayTradeCountUnit.optionsTradeCount);
        //外国株取引件数 ＝　@取得した当日注文件数(外国株)
        l_todayTradeCountUnit.foreignEquityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[4][0]);

        l_tomorrowTradeCountUnit.equityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[0][1]);
        log.debug("翌日注文取引件数情報.equityTradeCount" + "=" + l_tomorrowTradeCountUnit.equityTradeCount);
        l_tomorrowTradeCountUnit.marginTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[1][1]);
        log.debug("翌日注文取引件数情報.marginTradeCount" + "=" + l_tomorrowTradeCountUnit.marginTradeCount);
        l_tomorrowTradeCountUnit.futuresTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[2][1]);
        log.debug("翌日注文取引件数情報.futuresTradeCount" + "=" + l_tomorrowTradeCountUnit.futuresTradeCount);
        l_tomorrowTradeCountUnit.optionsTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[3][1]);
        log.debug("翌日注文取引件数情報.optionsTradeCount" + "=" + l_tomorrowTradeCountUnit.optionsTradeCount);
        //外国株取引件数   ＝　@取得した翌日注文件数(外国株)
        l_tomorrowTradeCountUnit.foreignEquityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[4][1]);

        l_todayOrderTradeCountUnit.equityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[0][2]);
        log.debug("当日注文約定取引件数情報.equityTradeCount" + "=" + l_todayOrderTradeCountUnit.equityTradeCount);
        l_todayOrderTradeCountUnit.marginTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[1][2]);
        log.debug("当日注文約定取引件数情報.marginTradeCount" + "=" + l_todayOrderTradeCountUnit.marginTradeCount);
        l_todayOrderTradeCountUnit.futuresTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[2][2]);
        log.debug("当日注文約定取引件数情報.futuresTradeCount" + "=" + l_todayOrderTradeCountUnit.futuresTradeCount);
        l_todayOrderTradeCountUnit.optionsTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[3][2]);
        log.debug("当日注文約定取引件数情報.optionsTradeCount" + "=" + l_todayOrderTradeCountUnit.optionsTradeCount);
        //外国株取引件数   ＝　@取得した当日約定件数(外国株)
        l_todayOrderTradeCountUnit.foreignEquityTradeCount = WEB3StringTypeUtility.formatNumber(l_intCounts[4][2]);

        //1.10 売買代金情報インスタンスを生成する。
        WEB3PvInfoTradePriceUnit l_tradePriceUnit = new WEB3PvInfoTradePriceUnit();

        //1.11 生成した売買代金情報オブジェクトにプロパティをセットする。
        //株式売買代金        ＝　@取得した当日売買代金(株式)
        l_tradePriceUnit.equityTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[0][0]);
        //信用取引売買代金        ＝　@取得した当日売買代金(信用)
        l_tradePriceUnit.marginTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[1][0]);
        //先物取引売買代金        ＝　@取得した当日売買代金(先物)
        l_tradePriceUnit.futuresTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[2][0]);
        //オプション取引売買代金 ＝　@取得した当日売買代金(オプション)
        l_tradePriceUnit.optionsTradePrice = WEB3StringTypeUtility.formatNumber(l_dblPrice[3][0]);
        //1.12 getIPO申告Params(顧客)
        List l_lisIpoOrderParams = l_dataManager.getIpoOrderParams(l_mainAccount);

        //1.13 ArrayList()
        List l_lisArrayList = new ArrayList();

        //1.14 getIPO申告Params()の戻り値の数分Loop処理
        if (l_lisIpoOrderParams != null)
        {
            int l_intIpoOrderParamsCnt = l_lisIpoOrderParams.size();
            log.debug("getIPO申告Params()の戻り値の要素数 = " + l_intIpoOrderParamsCnt);
            for (int i = 0; i < l_intIpoOrderParamsCnt; i++)
            {
                //1.14.1 getIPO銘柄Params(IPO銘柄ID : long)
                IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_lisIpoOrderParams.get(i);
                IpoProductParams  l_productParams = null;
                
                boolean l_isAdvancedElection = true;
                // 当選の場合
                if (WEB3LotResultDef.ELECTION.equals(l_ipoOrderParams.getLotResult()))
                {
                    l_isAdvancedElection = false;
                }
                
                l_productParams = l_dataManager.getIpoProductParams(l_ipoOrderParams.ipo_product_id, l_isAdvancedElection);
                if (l_productParams == null)
                {
                    continue;
                }
                //1.14.2 IPO当選銘柄情報()
                WEB3PvInfoIpoProductUnit l_ipoProductUnit = new WEB3PvInfoIpoProductUnit();

                //1.14.3 プロパティセット
                l_ipoProductUnit.lotWinProductName = l_productParams.standard_name;
                log.debug("IPO当選銘柄情報.lotWinProductName" + "=" + l_ipoProductUnit.lotWinProductName);
                 l_ipoProductUnit.lotWinCount = l_ipoOrderParams.elected_quantity.toString();
                log.debug("IPO当選銘柄情報.lotWinCount" + "=" + l_ipoProductUnit.lotWinCount);
                //1.14.4 add(IPO当選銘柄情報 : Object)
                l_lisArrayList.add(l_ipoProductUnit);
            }
        }
        //1.15 toArray()
        WEB3PvInfoIpoProductUnit[] l_ipoProductUnits = new WEB3PvInfoIpoProductUnit[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_ipoProductUnits);

        //1.16 is信用口座開設(String)
        boolean l_blnMarginAccountEstablished = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);

        //1.17 is先物OP口座開設(String)
        boolean l_blnIfoAccountOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.FUTURES);

        //1.18 is先物OP口座開設(String)
        boolean l_blnIfoOptionAccountOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
        //1.19 営業日の一覧を取得する。
        Date[] l_datBizDateLists = l_dataManager.getBizDateList();
        //1.20 creatResponse()
        l_response = (WEB3PvInfoOrderExecStateResponse)l_request.createResponse();

        //1.21 プロパティセット
        l_response.bizDateList = l_datBizDateLists;
        l_response.marginAccOpen = l_blnMarginAccountEstablished;
        l_response.futuresAccOpen = l_blnIfoAccountOpen;
        l_response.optionsAccOpen = l_blnIfoOptionAccountOpen;
        l_response.orderCountsToday = l_todayTradeCountUnit;
        l_response.orderCountsTomorrow = l_tomorrowTradeCountUnit;
        l_response.execCountsToday = l_todayOrderTradeCountUnit;
        l_response.ipoProductUnits = l_ipoProductUnits;
        l_response.tradePriceUnit = l_tradePriceUnit;

        log.debug("l_response.marginAccOpen" + "=" + l_response.marginAccOpen);
        log.debug("l_response.futuresAccOpen" + "=" + l_response.futuresAccOpen);
        log.debug("l_response.optionsAccOpen" + "=" + l_response.optionsAccOpen);
        log.exiting(STR_METHOD_NAME);
        return l_response;
    }

    /**
     * (create検索条件文字列for注文件数)<BR>
     * 注文件数取得用の検索条件文字列(共通部分)を作成する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列を作成する。<BR>
     * 　@[検索条件]<BR>
     * 　@----------------------------------------------<BR>
     * 　@　@口座ID = 顧客.getAccountId()　@かつ<BR>
     * 　@　@注文状態 != "6：発注失敗(新規注文)" かつ<BR>
     * 　@　@(失効区分 != "3：マーケット拒否" or  <BR>
     * 　@　@(約定数量 is not null and 約定数量 > 0))　@かつ  <BR>
     * 　@　@注文訂正取消区分 != "3：全部取消完了"<BR>
     * 　@----------------------------------------------<BR>
     * 上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "account_id = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "order_status != ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(expiration_status != ? or "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(executed_quantity is not null and executed_quantity > ?)) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "modify_cancel_type != ? "<BR>
     * <BR>
     * 2）作成した検索条件文字列を返却する。<BR>
     * @@return String
     * @@roseuid 41456B320202
     */
    protected String createQueryStringForOrderCnt() throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createQueryStringForOrderCnt()";
        log.entering(STR_METHOD_NAME );

        //検索条件を基に、検索条件文字列を作成する。
        String l_strQueryString = " account_id = ? and "
                                        + "order_status != ? and "
                                        + "(expiration_status != ? or "
                                        + "(executed_quantity is not null and executed_quantity > ?)) and "
                                        + "modify_cancel_type != ? ";

        log.debug("検索条件文字列:l_strQueryString" + "=" + l_strQueryString);
        log.exiting(STR_METHOD_NAME);
        return l_strQueryString;
    }

    /**
     * (create検索条件データコンテナfor注文件数)<BR>
     * 注文件数取得用の検索条件文字列(共通部分)の“?”部分にセットする<BR>
     * パラメータリスト(文字列配列)を作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）以下の順番で顧客情報をArrayListに追加する。<BR>
     * ※以降、ArrayListに追加する際には、String型に変換してから追加すること。<BR>
     * 　@　@・パラメータ.顧客.getAccountId()<BR>
     * 　@　@・"6：発注失敗(新規注文)"<BR>
     * 　@　@・"3：マーケット拒否"<BR>
     * 　@　@・"0"<BR>
     * 　@　@・"3：全部取消完了"<BR>
     * <BR>
     * ６）ArrayList.toArray()の戻り値を返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return String[]
     * @@roseuid 41456B320221
     */
    protected String[] createQueryDataContainerForOrderCnt(WEB3GentradeMainAccount l_mainAccount)
    {

        final String STR_METHOD_NAME = " createQueryDataContainerForOrderCnt(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME );

        //1.ArrayList
        List l_lisArrayList = new ArrayList();

        //２.顧客情報をArrayListに追加する。
        //パラメータ.顧客.getAccountId()
        l_lisArrayList.add(WEB3StringTypeUtility.formatNumber(l_mainAccount.getAccountId()));
        //"6：発注失敗(新規注文)"
        l_lisArrayList.add(new Integer(OrderStatusEnum.NOT_ORDERED.intValue()).toString());
        //"3：マーケット拒否"
        l_lisArrayList.add(new Integer(OrderExpirationStatusEnum.INVALIDATED_BY_MKT.intValue()).toString());
        //"0"
        l_lisArrayList.add("0");
        //"3：全部取消完了"
        l_lisArrayList.add(WEB3ModifyCancelTypeDef.CANCELED);

        //ArrayList.toArray()の戻り値を返却する。
        String[] l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);

        log.exiting(STR_METHOD_NAME);
        return l_strArrayLists;
    }

    /**
     * (create検索条件文字列for表示内容)<BR>
     * 表示内容取得用の検索条件文字列を作成する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列を作成する。<BR>
     * 　@[検索条件]<BR>
     * 　@----------------------------------------------<BR>
     * 　@　@証券会社コード = 顧客.証券会社コード　@かつ<BR>
     * 　@　@現在状況 = "0：有効"　@かつ<BR>
     * 　@　@(表示期間From <= 現在時刻　@または<BR>
     * 　@　@　@表示期間From IS null)　@かつ<BR>
     * 　@　@(表示期間To >= 現在時刻　@または<BR>
     * 　@　@　@表示期間To IS null)　@かつ<BR>
     * 　@　@表示媒体 = (ログインチャネルによる)<BR>
     * 　@----------------------------------------------<BR>
     * 上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "institution_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "effective_flag = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(term_from <= to_date(?, 'YYYYMMDDHH24MI') <BR>
     *                               or term_from is null) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(term_to >= to_date(?, 'YYYYMMDDHH24MI') <BR>
     *                               or term_to is null) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "display_device in (?, ?) "<BR>
     * <BR>
     * ２）パラメータ.表示条件番号一覧 != nullの場合、以下の処理を行う。<BR>
     * 　@２－１）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and condition_no in ("<BR>
     * <BR>
     * 　@２－２）パラメータ.表示条件番号一覧.lengthの数分、<BR>
     * 　@　@　@以下の処理を繰り返す。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "?"<BR>
     * 　@　@　@※2回目からは"?"ではなく、", ?"を追加する。<BR>
     * <BR>
     * 　@２－３）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += ") "<BR>
     * <BR>
     * ６）作成した検索条件文字列を返却する。<BR>
     * @@param l_strDispConditonNoList - (表示条件番号一覧)<BR>
     * 表示条件番号一覧<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     * @@return String
     * @@roseuid 41459B280108
     */
    protected String createQueryStringForDispContents(String[] l_strDispConditonNoList) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " createQueryStringForDispContents(String[])";
        log.entering(STR_METHOD_NAME );

        //１）検索条件を表す、検索条件文字列を作成する。
        //検索条件を基に、検索条件文字列を作成する
        String l_strQueryStringForDispContents = " institution_code = ? and "
                                                        + "effective_flag = ? and "
                                                        + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                                                        + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                                                        + "display_device in (?, ?) ";

        //2.パラメータ.表示条件番号一覧 != nullの場合、以下の処理を行う。
        if (l_strDispConditonNoList != null)
        {
            //２－１）検索条件文字列に以下の条件を追加する。
            l_strQueryStringForDispContents += " and condition_no in (";

            //２－２）パラメータ.表示条件番号一覧.lengthの数分、以下の処理を繰り返す。
            int l_intDispConditionNoListCnt = l_strDispConditonNoList.length;
            l_strQueryStringForDispContents += "?";

            for (int i = 1; i < l_intDispConditionNoListCnt; i++)
            {
                l_strQueryStringForDispContents += ", ?";
            }
            l_strQueryStringForDispContents += ")";
        }

        log.debug("検索条件文字列:l_strQueryStringForDispContents" + "=" + l_strQueryStringForDispContents);
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringForDispContents;
    }

    /**
     * (create検索条件文字列for表示内容)<BR>
     * 表示内容取得用の検索条件文字列を作成する。<BR>
     * <BR>
     * １）以下の検索条件を表す、検索条件文字列を作成する。<BR>
     * 　@[検索条件]<BR>
     * 　@----------------------------------------------<BR>
     * 　@　@証券会社コード = 顧客.証券会社コード　@かつ<BR>
     * 　@　@現在状況 = "0：有効"　@かつ<BR>
     * 　@　@(表示期間From <= 現在時刻　@または<BR>
     * 　@　@　@表示期間From IS null)　@かつ<BR>
     * 　@　@(表示期間To >= 現在時刻　@または<BR>
     * 　@　@　@表示期間To IS null)　@かつ<BR>
     * 　@　@表示媒体 = (ログインチャネルによる)<BR>
     * 　@----------------------------------------------<BR>
     * 上記検索条件を基に、検索条件文字列を作成する。<BR>
     * <BR>
     * 　@　@検索条件文字列 = "( institution_code = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "effective_flag = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(to_char(term_from, 'YYYYMMDDHH24') <= ? <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@or term_from is null) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(to_char(term_to, 'YYYYMMDDHH24') >= ? <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@or term_to is null) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "display_device in (?, ?) "<BR>
     * <BR>
     * ２）パラメータ.表示条件番号一覧 != nullの場合、以下の処理を行う。<BR>
     * 　@２－１）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += " and condition_no in ("<BR>
     * <BR>
     * 　@２－２）パラメータ.表示条件番号一覧より"0000：ダイレクト指定"を除いた<BR>
     * 　@　@　@パラメータ.表示条件番号一覧.lengthの数分、以下の処理を繰り返す。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "?"<BR>
     * 　@　@　@※2回目からは"?"ではなく、", ?"を追加する。<BR>
     * <BR>
     * 　@２－３）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += ") "<BR>
     * <BR>
     * 　@２－４）パラメータ.閲覧履歴Tbl != nullの場合、以下の処理を行う。<BR>
     * <BR>
     * 　@　@２－４－１）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += ") or ( effective_flag = ? and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(to_char(term_from, 'YYYYMMDDHH24') <= ? <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@or term_from is null) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "(to_char(term_to, 'YYYYMMDDHH24') >= ? <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@or term_to is null) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "display_device in (?, ?) and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "condition_no = '0000' and "<BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@+ "display_contents_id in ("<BR>
     * <BR>
     * 　@　@２－４－２）パラメータ.閲覧履歴Tblの要素数分、<BR>
     * 　@　@　@以下の処理を繰り返す。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += "?"<BR>
     * 　@　@　@※2回目からは"?"ではなく、", ?"を追加する。<BR>
     * <BR>
     * 　@　@２－４－３）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += ")"<BR>
     * <BR>
     * ３）検索条件文字列に以下の条件を追加する。<BR>
     * <BR>
     * 　@　@　@検索条件文字列 += ")"<BR>
     * <BR>
     * ４）作成した検索条件文字列を返却する。<BR>
     * @@param l_strDispConditionNoList - (表示条件番号一覧)<BR>
     * 表示条件番号一覧<BR>
     * <BR>
     * @@param l_htBrowseHistory - (閲覧履歴Tbl)<BR>
     * 閲覧履歴Tbl<BR>
     * <BR>
     * @@return String
     */
    protected String createQueryStringForDispContents(
        String[] l_strDispConditionNoList,
        Hashtable l_htBrowseHistory)
    {

        final String STR_METHOD_NAME = " createQueryStringForDispContents(String[], Hashtable)";
        log.entering(STR_METHOD_NAME);

        //１）以下の検索条件を表す、検索条件文字列を作成する。
        String l_strQueryStringForDispContents =
            "( institution_code = ? and "
            + "effective_flag = ? and "
            + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
            + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
            + "display_device in (?, ?) ";

        //２）パラメータ.表示条件番号一覧 != nullの場合、以下の処理を行う。
        if (l_strDispConditionNoList != null)
        {
            //２－１）検索条件文字列に以下の条件を追加する。
            //検索条件文字列 += " and condition_no in ("
            l_strQueryStringForDispContents += " and condition_no in (";

            //２－２）パラメータ.表示条件番号一覧より"0000：ダイレクト指定"を除いた
            //パラメータ.表示条件番号一覧.lengthの数分、以下の処理を繰り返す。
            int l_intDispConditionNoListCnt = l_strDispConditionNoList.length;
            List l_lisArrayList = new ArrayList();
            for (int i = 0; i < l_intDispConditionNoListCnt; i++)
            {
                if (!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_strDispConditionNoList[i]))
                {
                    l_lisArrayList.add(l_strDispConditionNoList[i]);
                }
            }

            String[] l_strArrayLists;
            l_strArrayLists = new String[l_lisArrayList.size()];
            l_lisArrayList.toArray(l_strArrayLists);

            l_intDispConditionNoListCnt = l_strArrayLists.length;
            //検索条件文字列 += "?"
            l_strQueryStringForDispContents += "?";

            for (int i = 1; i < l_intDispConditionNoListCnt; i++)
            {
                //※2回目からは"?"ではなく、", ?"を追加する。
                l_strQueryStringForDispContents += ", ?";
            }

            //２－３）検索条件文字列に以下の条件を追加する。
            //検索条件文字列 += ") "
            l_strQueryStringForDispContents += ")";

            //２－４）パラメータ.閲覧履歴Tbl != nullの場合、以下の処理を行う。
            if (l_htBrowseHistory != null)
            {
                //２－４－１）検索条件文字列に以下の条件を追加する。
                l_strQueryStringForDispContents += ") or ( effective_flag = ? and "
                    + "(to_char(term_from, 'YYYYMMDDHH24') <= ? or term_from is null) and "
                    + "(to_char(term_to, 'YYYYMMDDHH24') >= ? or term_to is null) and "
                    + "display_device in (?, ?) and "
                    + "condition_no = '0000' and "
                    + "display_contents_id in (";

                //２－４－２）パラメータ.閲覧履歴Tblの要素数分、
                //以下の処理を繰り返す。
                //検索条件文字列 += "?"
                int l_intBrowseHistorySize = l_htBrowseHistory.size();
                l_strQueryStringForDispContents += "?";

                //※2回目からは"?"ではなく、", ?"を追加する。
                for (int i = 1; i < l_intBrowseHistorySize; i++)
                {
                    //※2回目からは"?"ではなく、", ?"を追加する。
                    l_strQueryStringForDispContents += ", ?";
                }

                //２－４－３）検索条件文字列に以下の条件を追加する。
                //検索条件文字列 += ")"
                l_strQueryStringForDispContents += ")";
            }
        }

        //３）検索条件文字列に以下の条件を追加する。
        //検索条件文字列 += ")"
        l_strQueryStringForDispContents += ")";

        //４）作成した検索条件文字列を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strQueryStringForDispContents;
    }

    /**
     * (create検索条件データコンテナfor表示内容)<BR>
     * 検索条件文字列の“?”部分にセットするパラメータリスト(文字列配列)を<BR>
     * 作成する。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）以下の順番でパラメータをArrayListに追加する。<BR>
     * 　@　@※以降、ArrayListに追加する際には、String型に変換してから追加<BR>
     * すること。<BR>
     * 　@　@・パラメータ.顧客.証券会社コード<BR>
     * 　@　@・"0：有効"<BR>
     * 　@　@・現在時刻(*1)　@※フォーマット：yyyyMMddHHmm<BR> 　@　@
     * 　@　@・現在時刻　@※フォーマット：yyyyMMddHHmm<BR>
     *     ・"0：DEFAULT(PC&モバイル両方)"<BR>
     * 　@　@・表示媒体<BR>
     * 　@　@　@　@[this.getログインチャネル()の戻り値 == "3：モバイル"の場合]<BR>
     * 　@　@　@　@　@"2：モバイル"をセット。<BR>
     * 　@　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@　@"1：PC"をセット。<BR>
     * 　@　@・パラメータ.表示条件番号一覧の要素全て<BR>
     * <BR>
     * ３）ArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute<BR>
     * (取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     *
     * @@param l_strDispConditionNoList - (表示条件番号一覧)<BR>
     * 表示条件番号一覧<BR>
     * <BR>
     * 0000：　@ダイレクト指定<BR>
     * 1001：　@入金請求発生&信用口座開設<BR>
     * 1002：　@入金請求発生&信用口座未開設<BR>
     * 1003：　@立替金発生<BR>
     * 1004：　@立替金実績<BR>
     * 1005：　@証拠金不足<BR>
     * 1006：　@決済期限間近（一ヶ月前）の建玉保有<BR>
     * 1007：　@決済期限間近（一週間前）の建玉保有<BR>
     * 1008：　@信用口座開設<BR>
     * 1009：　@信用口座未開設<BR>
     * 1010：　@オプション口座開設<BR>
     * 1011：　@株式保有<BR>
     * 1012：　@信用建玉保有<BR>
     * 1013：　@投信保有<BR>
     * 1014：　@累投保有<BR>
     * 1015：　@オプション建玉保有<BR>
     * 1016：　@ミニ株保有<BR>
     * 1017：　@先物保有<BR>
     * 1018：　@預り金有り&証券残無し<BR>
     * 1019：　@預り金無し&証券残無し<BR>
     * 1020：　@株式・信用注文発生（当日）<BR>
     * 1021：　@株式・信用注文発生（翌日）<BR>
     * 1022：　@株式・信用約定発生<BR>
     * 1023：　@全顧客<BR>
     * 1024：　@メールアドレス未登録<BR>
     * 1025：　@IPO当選<BR>
     * 1026：　@IPO繰上げ当選<BR>
     * 1027：　@取引停止<BR>
     * @@return String[]
     * @@roseuid 41459B280127
     */
    protected String[] createQueryDataContainerForDispContents(WEB3GentradeMainAccount l_mainAccount, String[] l_strDispConditionNoList)
    {

        final String STR_METHOD_NAME = " createQueryDataContainerForsDispContents(WEB3GentradeMainAccount, String[])";
        log.entering(STR_METHOD_NAME );

        //１）ArrayListを生成する。
        List l_lisArrayList = new ArrayList();

        //以下の順番でパラメータをArrayListに追加する。
        Timestamp l_tsNowDateTime = (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        l_lisArrayList.add(l_mainAccount.getInstitution().getInstitutionCode());
        l_lisArrayList.add(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));
        l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE);

        if (WEB3ChannelDef.MOBILE.equals(this.getLoginChannel()))
        {
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE);
        }
        else
        {
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.PC_DEVICE);
        }
        int l_intDispConditionNoListCnt = l_strDispConditionNoList.length;
        for (int i = 0; i < l_intDispConditionNoListCnt; i++)
        {
            l_lisArrayList.add(l_strDispConditionNoList[i]);
        }

        //ArrayList.toArray()の戻り値を返却する。
        String[] l_strArrayLists;
        l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);

        log.exiting(STR_METHOD_NAME);
        return l_strArrayLists;
    }

    /**
     * (create検索条件データコンテナfor表示内容)<BR>
     * 検索条件文字列の"?"部分にセットするパラメータリスト(文字列配列)を作成する。 <BR>
     * <BR>
     * １）ArrayListを生成する。 <BR>
     * <BR>
     * ２）以下の順番でパラメータをArrayListに追加する。 <BR>
     * 　@　@※以降、ArrayListに追加する際には、String型に変換してから追加すること。 <BR>
     * 　@　@・パラメータ.顧客.証券会社コード <BR>
     * 　@　@・"0：有効" <BR>
     * 　@　@・現在時刻(*1)　@※フォーマット：yyyyMMddHH<BR>
     * 　@　@・現在時刻　@※フォーマット：yyyyMMddHH <BR>
     * 　@　@・"0：DEFAULT(PC&モバイル両方)" <BR>
     * 　@　@・表示媒体 <BR>
     * 　@　@　@　@[this.getログインチャネル()の戻り値 == "3：モバイル"の場合]<BR>
     * 　@　@　@　@　@"2：モバイル"をセット。 <BR>
     * 　@　@　@　@[上記以外の場合] <BR>
     * 　@　@　@　@　@"1：PC"をセット。 <BR>
     * 　@　@・パラメータ.表示条件番号一覧より"0000：ダイレクト指定"を除いた要素全て<BR>
     * <BR>
     * ３）パラメータ.閲覧履歴Tbl != nullの場合、以下の順番でパラメータをArrayListに追加する。<BR>
     * 　@　@・"0：有効" <BR>
     * 　@　@・現在時刻(*1)　@※フォーマット：yyyyMMddHH<BR>
     * 　@　@・現在時刻　@※フォーマット：yyyyMMddHH <BR>
     * 　@　@・"0：DEFAULT(PC&モバイル両方)" <BR>
     * 　@　@・表示媒体 <BR>
     * 　@　@　@　@[this.getログインチャネル()の戻り値 == "3：モバイル"の場合]<BR>
     * 　@　@　@　@　@"2：モバイル"をセット。 <BR>
     * 　@　@　@　@[上記以外の場合] <BR>
     * 　@　@　@　@　@"1：PC"をセット。 <BR>
     * 　@　@・パラメータ.閲覧履歴Tblのkey全て<BR>
     * <BR>
     * ４）ArrayList.toArray()の戻り値を返却する。<BR>
     * <BR>
     * (*1)現在時刻<BR>
     * 　@ThreadLocalSystemAttributesRegistry.getAttribute(<BR>
     * 　@　@取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_strDispConditionNoList - (表示条件番号一覧)<BR>
     * 表示条件番号一覧<BR>
     * <BR>
     * @@param l_htBrowseHistory - (閲覧履歴Tbl)<BR>
     * 閲覧履歴Tbl<BR>
     * <BR>
     * @@return String[]
     */
    protected String[] createQueryDataContainerForDispContents(
        WEB3GentradeMainAccount l_mainAccount,
        String[] l_strDispConditionNoList,
        Hashtable l_htBrowseHistory)
    {

        final String STR_METHOD_NAME =
            " createQueryDataContainerForsDispContents(WEB3GentradeMainAccount, String[], Hashtable)";
        log.entering(STR_METHOD_NAME);
        //１）ArrayListを生成する。
        List l_lisArrayList = new ArrayList();

        //２）以下の順番でパラメータをArrayListに追加する。
        //(*1)現在時刻
        //ThreadLocalSystemAttributesRegistry.getAttribute(取引時間管理.TIMESTAMP_TAG)にて取得した現在時刻
        Timestamp l_tsNowDateTime =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);

        //・パラメータ.顧客.証券会社コード
        l_lisArrayList.add(l_mainAccount.getInstitution().getInstitutionCode());

        //・"0：有効"
        l_lisArrayList.add(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);

        //・現在時刻(*1)　@※フォーマット：yyyyMMddHH
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

        //・現在時刻　@※フォーマット：yyyyMMddHH
        l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

        //・"0：DEFAULT(PC&モバイル両方)"
        l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE);

        //[this.getログインチャネル()の戻り値 == "3：モバイル"の場合]
        if (WEB3ChannelDef.MOBILE.equals(this.getLoginChannel()))
        {
            //"2：モバイル"をセット。
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE);
        }
        else
        {
            //"1：PC"をセット。
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.PC_DEVICE);
        }

        //パラメータ.表示条件番号一覧より"0000：ダイレクト指定"を除いた要素全て
        int l_intDispConditionNoListCnt = l_strDispConditionNoList.length;
        for (int i = 0; i < l_intDispConditionNoListCnt; i++)
        {
            if (!WEB3PvInfoConditionDef.DIRECT_ASSIGN.equals(l_strDispConditionNoList[i]))
            {
                l_lisArrayList.add(l_strDispConditionNoList[i]);
            }
        }

        //３）パラメータ.閲覧履歴Tbl != nullの場合、以下の順番でパラメータをArrayListに追加する。
        if (l_htBrowseHistory != null)
        {
            //・"0：有効"
            l_lisArrayList.add(WEB3PvInfoEffectiveFlagDef.EFFECTIVE_YES);

            //・現在時刻(*1)　@※フォーマット：yyyyMMddHH
            l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

            //・現在時刻　@※フォーマット：yyyyMMddHH
            l_lisArrayList.add(WEB3DateUtility.formatDate(l_tsNowDateTime, "yyyyMMddHH"));

            //・"0：DEFAULT(PC&モバイル両方)"
            l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.DEFAULT_DEVICE);

            //[this.getログインチャネル()の戻り値 == "3：モバイル"の場合]
            if (WEB3ChannelDef.MOBILE.equals(this.getLoginChannel()))
            {
                //"2：モバイル"をセット。
                l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.MOBILE_DEVICE);
            }
            else
            {
                //"1：PC"をセット。
                l_lisArrayList.add(WEB3PvInfoDisplayDeviceDef.PC_DEVICE);
            }

            //・パラメータ.閲覧履歴Tblのkey全て
            Set l_setBrowseHistory = l_htBrowseHistory.keySet();
            Iterator l_iteratorBrowseHistory = l_setBrowseHistory.iterator();
            while (l_iteratorBrowseHistory.hasNext())
            {
                l_lisArrayList.add(l_iteratorBrowseHistory.next().toString());
            }
        }

        //ArrayList.toArray()の戻り値を返却する。
        String[] l_strArrayLists;
        l_strArrayLists = new String[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_strArrayLists);
        log.exiting(STR_METHOD_NAME);
        return l_strArrayLists;
    }

    /**
     * (sort表示メッセージ情報)<BR>
     * 引数の表示メッセージ情報一覧をソートする。<BR>
     * <BR>
     * １）ArrayListを生成する。<BR>
     * <BR>
     * ２）以下のComparatorを生成し、上から順にArrayListに追加する。<BR>
     * 　@　@・未読既読Comparator<BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@　@　@　@orderBy：　@"A：昇順"<BR>
     * 　@　@・登録方法@Comparator<BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@　@　@　@orderBy：　@"A：昇順"<BR>
     * 　@　@・最終更新日時Comparator<BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@　@　@　@orderBy：　@"D：降順"<BR>
     * 　@　@・表示期間ToComparator<BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@　@　@　@orderBy：　@"D：降順"<BR>
     * 　@　@・表示メッセージ発生日Comparator<BR>
     * 　@　@　@　@[コンストラクタにセットするパラメータ]<BR>
     * 　@　@　@　@　@orderBy：　@"A：昇順"<BR>
     * <BR>
     * ３）WEB3ArraysUtility.sort()メソッドをコールする。<BR>
     * <BR>
     * 　@[sort()にセットするパラメータ]<BR>
     * 　@　@obj：　@パラメータ.表示メッセージ情報一覧<BR>
     * 　@　@com：　@生成したArrayList.toArray()の戻り値<BR>
     * @@param l_dispMessageUnitList - (表示メッセージ情報一覧)<BR>
     * 表示メッセージ情報の配列<BR>
     * @@roseuid 414675BC0273
     */
    protected void sortDispMessageUnit(WEB3PvInfoDisplayMessageUnit[] l_dispMessageUnitList)
    {

        final String STR_METHOD_NAME = " sortDispMessageUnit(WEB3PvInfoDisplayMessageUnit[])";
        log.entering(STR_METHOD_NAME );

        //１）ArrayListを生成する。
        List l_lisArrayList = new ArrayList();

        // ２）Comparatorを生成し、上から順にArrayListに追加する。
        // 未読既読Comparator
        WEB3PvInfoReadUnReadComparator l_readUnReadComparator = new WEB3PvInfoReadUnReadComparator(WEB3AscDescDef.ASC);
        l_lisArrayList.add(l_readUnReadComparator);

        //登録方法@Comparator
        WEB3PvInfoRegistMethodComparator l_registmehtodComparator = new WEB3PvInfoRegistMethodComparator(WEB3AscDescDef.ASC);
        l_lisArrayList.add(l_registmehtodComparator);

        //最終更新日時Comparator
        WEB3PvInfoFinalModTimeStampComparator l_lastUpdateTimeStampComparator = new WEB3PvInfoFinalModTimeStampComparator(WEB3AscDescDef.DESC);
        l_lisArrayList.add(l_lastUpdateTimeStampComparator);

        //表示期間ToComparator
        WEB3PvInfoDisplayTermToComparator l_displayTermToComparator = new WEB3PvInfoDisplayTermToComparator(WEB3AscDescDef.DESC);
        l_lisArrayList.add(l_displayTermToComparator);

        //表示メッセージ発生日Comparator
        WEB3PvInfoDisplayMessageAccrualDateComparator l_dispMessageAccrualDateComparator = new WEB3PvInfoDisplayMessageAccrualDateComparator(WEB3AscDescDef.ASC);
        l_lisArrayList.add(l_dispMessageAccrualDateComparator);

        Comparator[] l_cmpArray = new Comparator[l_lisArrayList.size()];
        l_lisArrayList.toArray(l_cmpArray);

        //３）WEB3ArraysUtility.sort()メソッドをコールする。
        WEB3ArraysUtility.sort(l_dispMessageUnitList, l_cmpArray);

        log.exiting(STR_METHOD_NAME );

    }

    /**
     * (is入金請求発生)<BR>
     * 入金請求が発生している顧客か判別する。<BR>
     * 発生している場合はtrue、以外falseを返却する。<BR>
     * <BR>
     * １）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get入金請求発生日()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[get入金請求発生日()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * <BR>
     * ２）１）の戻り値 == nullの場合、falseを返却する。<BR>
     * 　@以外、trueを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@return boolean<BR>
     * @@roseuid 41457A9803D7
     */
    protected boolean isDepositRequestGen(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isDepositRequestGen(WEB3PvInfoDisplayMessageUnit[])";
        log.entering(STR_METHOD_NAME );
        //１）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get入金請求発生日()
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        Date l_genDate = l_dataManager.getPayClaimGenDate(l_subAccount);

        //２）１）の戻り値 == nullの場合、falseを返却する。以外、trueを返却する。
        log.exiting(STR_METHOD_NAME );
        if (l_genDate == null)
        {
            return false;
        }
        log.exiting(STR_METHOD_NAME );
        return true;
    }

    /**
     * (is取引停止顧客)<BR>
     * 取引停止顧客か判別する。<BR>
     * 取引停止顧客の場合はtrue、以外falseを返却する。<BR>
     * <BR>
     * １）GtlUtils.getCommonOrderValidator()メソッドをコールし、<BR>
     * 　@注文チェックオブジェクトを取得する。<BR>
     * <BR>
     * ２）注文チェック.validate取引可能顧客()メソッドをコールする。<BR>
     * <BR>
     * 　@[validate取引可能顧客()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * <BR>
     * ３）１）の結果、例外がスローされた場合はtrueを返却する。<BR>
     * 　@以外、falseを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@return boolean
     * @@roseuid 414688980012
     */
    protected boolean isSuspensionAccount(WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = " isSuspensionAccount(WEB3GentradeSubAccount)";
        log.entering(STR_METHOD_NAME );

        //１）GtlUtils.getCommonOrderValidator()メソッドをコールし、注文チェックオブジェクトを取得する。
        CommonOrderValidator l_commonOrderValidator = GtlUtils.getCommonOrderValidator();

        //２）注文チェック.validate取引可能顧客()メソッドをコールする。
        OrderValidationResult l_orderValidationResult = l_commonOrderValidator.validateSubAccountForTrading(l_subAccount);

        //３）１）の結果、例外がスローされた場合はtrueを返却する。
        if (l_orderValidationResult.getProcessingResult().isSuccessfulResult())
        {
            log.exiting(STR_METHOD_NAME );
            return false;
        }
        else
        {
            log.exiting(STR_METHOD_NAME );
            return true;
        }

    }

    /**
     * (is証拠金不足)<BR>
     * 証拠金が不足しているか判別する。<BR>
     * 不足している場合はtrue、以外falseを返却する。<BR>
     * <BR>
     * １）パラメータ.顧客.getSubAccount()をコールする。<BR>
     * <BR>
     *     [getSubAccount()にセットするパラメータ]<BR>
     *     補助口座タイプ：SubAccountTypeEnum.株式オプション取引口座（先物証拠金）<BR>
     * <BR>
     *     取得出来なかった場合、falseを返却する。<BR>
     * <BR>
     * ２）WEB3IfoDepositCalcServiceを生成する。<BR>
     * <BR>
     * ３）WEB3IfoDepositCalcService.getIfoDepositCalc()をコールする<BR>
     * <BR>
     *     [getIfoDepositCalc()にセットするパラメータ]<BR>
     *     補助口座：上記１）で取得した補助口座<BR>
     * <BR>
     * ４）上記３）で取得した証拠金計算.calcNonPayAmount()をコールする<BR>
     * <BR>
     *     メソッドの戻り値 > 0の場合はtrue、以外falseを返却する。<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@return boolean
     * @@roseuid 4146AE5801F6
     */
    protected boolean isIfoDepositShortage(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isIfoDepositShortage(WEB3GentradeMainAccount)";
        log.entering(STR_METHOD_NAME );

        if (l_mainAccount == null)
        {
            log.debug("パラメータNull出来ない");
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータNull出来ない");
        }
        
        //１）パラメータ.顧客.getSubAccount()をコールする。
        WEB3GentradeSubAccount l_subAccount = null;
        try
        {
            l_subAccount = 
                (WEB3GentradeSubAccount)l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
        }
        catch (NotFoundException l_nfe)
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
        
        //２）WEB3IfoDepositCalcServiceを生成する。
        WEB3IfoDepositCalcService l_service = 
            (WEB3IfoDepositCalcService)Services.getService(WEB3IfoDepositCalcService.class);
        
        //３）WEB3IfoDepositCalcService.getIfoDepositCalc()をコールする
        WEB3IfoDepositCalc l_calc = l_service.getIfoDepositCalc(l_subAccount);
        
        //４）上記３）で取得した証拠金計算.calcNonPayAmount()をコールする
        double l_dblAmount = l_calc.calcNonPayAmount();
        
        if (l_dblAmount > 0)
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (is預り金残高有り)<BR>
     * 顧客の預り金残高(T+0)が存在する(プラスである)か判別する。<BR>
     * 存在する場合はtrue、以外falseを返却する。<BR>
     * １）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get資産余力情報()<BR>
     * 　@をコールする。<BR>
     * 　@[get資産余力情報()にセットするパラメータ]<BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * ２）パラメータ.is信用口座開設が、<BR>
     * 　@[falseの場合]<BR>
     * 　@　@①@１）の戻り値を資産余力情報<現物顧客>型にキャストする。<BR>
     * 　@　@②資産余力情報<現物顧客>.get預り金残高()メソッドをコールする。<BR>
     * 　@　@　@[get預り金残高()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@0<BR>
     * 　@　@③②の戻り値 > 0の場合、trueを返却する。<BR>
     * 　@[上記以外の場合]<BR>
     * 　@　@①@１）の戻り値を資産余力情報<信用顧客>型にキャストする。<BR>
     * 　@　@②資産余力情報<信用顧客>.get預り金残高()メソッドをコールする。<BR>
     * 　@　@　@[get預り金残高()にセットするパラメータ]<BR>
     * 　@　@　@　@指定日：　@0<BR>
     * 　@　@③②の戻り値 > 0の場合、trueを返却する。<BR>
     * ４）falseを返却する。　@※預り金残高 <= 0である為。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座オブジェクト<BR>
     * @@param l_isOpenMarginAccount - (is信用口座開設)<BR>
     * 顧客が信用口座を開設しているかどうかのフラグ<BR>
     * <BR>
     * false：　@未開設<BR>
     * true：　@開設済<BR>
     * @@return boolean<BR>
     * @@roseuid 415A643F0288
     */
    protected boolean isAccountBalanceHas(WEB3GentradeSubAccount l_subAccount, boolean l_isMarginAccountOpen) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isAccountBalanceHas(WEB3GentradeSubAccount, boolean)";
        log.entering(STR_METHOD_NAME );
        //１）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get資産余力情報()
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        Object l_objPowerInfo = l_dataManager.getTradingPowerInfo(l_subAccount);
        
        WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
        boolean l_blnIsMarginAccount = l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
        // ２）パラメータ.is信用口座開設が、[falseの場合]
        if (!l_blnIsMarginAccount)
        {
            //①@１）の戻り値を資産余力情報<現物顧客>型にキャストする。
            WEB3TPTradingPowerCalcEquity l_powerCalcEquity = (WEB3TPTradingPowerCalcEquity)l_objPowerInfo;
            //②資産余力情報<現物顧客>.get預り金残高()メソッドをコールする。
            double l_dblBalance = l_powerCalcEquity.getAccountBalance(0);
            //③②の戻り値 > 0の場合、trueを返却する。
            if (l_dblBalance > 0)
            { 
                return true;
            }
        }
        else
        {
            //①@１）の戻り値を資産余力情報<信用顧客>型にキャストする。
            WEB3TPTradingPowerCalcMargin l_powerCalcMargin = (WEB3TPTradingPowerCalcMargin) l_objPowerInfo;
            //②資産余力情報<信用顧客>.get預り金残高()メソッドをコールする。
            double l_dblBalance = l_powerCalcMargin.getAccountBalance(0);
            //③②の戻り値 > 0の場合、trueを返却する。
            if (l_dblBalance > 0)
            { 
                return true;
            }
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * (isIPO当選)<BR>
     * 顧客がIPOに当選しているか判別する。<BR>
     * 当選している場合はtrue、以外falseを返却する。<BR>
     * <BR>
     * １）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.getIPO申告Params()を<BR>
     * 　@コールする。<BR>
     * <BR>
     * 　@[getIPO申告Params()にセットするパラメータ]<BR>
     * 　@　@顧客：　@パラメータ.顧客<BR>
     * <BR>
     * 　@メソッドの戻り値 == nullの場合はfalseを返却する。<BR>
     * <BR>
     * ２）１）のメソッドの戻り値の要素数(IPO申告Params)分<BR>
     * 　@以下の処理を繰り返す。<BR>
     * <BR>
     * 　@２－１）当選しているかのチェック<BR>
     * 　@　@　@　@[パラメータ.is繰上げ当選 == falseの場合]<BR>
     * 　@　@　@　@　@処理対象のIPO申告Params.抽選結果 != "1：当選"の場合<BR>
     * 　@　@　@　@　@次の要素へ処理を移行する。(continue;)<BR>
     * <BR>
     * 　@　@　@　@[上記以外の場合]<BR>
     * 　@　@　@　@　@処理対象のIPO申告Params.抽選結果(繰上) != "1：当選"の場合<BR>
     * 　@　@　@　@　@次の要素へ処理を移行する。(continue;)<BR>
     * <BR>
     * 　@２－２）購入申込終了日時以内であるかのチェック<BR>
     * 　@　@　@　@ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.getIPO銘柄Params()を<BR>
     * 　@　@　@　@コールする。<BR>
     * <BR>
     * 　@　@　@　@[getIPO銘柄Params()にセットするパラメータ]<BR>
     * 　@　@　@　@　@IPO銘柄ID：　@処理対象のIPO申告Params.IPO銘柄ID<BR>
     * <BR>
     * 　@　@　@　@上記メソッドの戻り値 != nullの場合、trueを返却する。<BR>
     * <BR>
     * ３）falseを返却する。　@※当選データ無しの為<BR>
     * @@param l_mainAccount - (顧客)<BR>
     * 顧客オブジェクト<BR>
     * @@param l_isAdvancedElection - (is繰上げ当選)<BR>
     * 繰上げ当選かどうかを判別するフラグ<BR>
     * <BR>
     * false：　@繰上げ当選でない<BR>
     * true：　@繰上げ当選<BR>
     * @@return boolean
     * @@roseuid 415A58A70362
     */
    protected boolean isIpoElection(WEB3GentradeMainAccount l_mainAccount, boolean l_isAdvancedElection) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " isIpoElection(WEB3GentradeMainAccount, boolean)";
        log.entering(STR_METHOD_NAME );

        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);

        //１）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.getIPO申告Params()をコールする。
        List l_lisIpoOrderParams = l_dataManager.getIpoOrderParams(l_mainAccount);
        if (l_lisIpoOrderParams == null)
        {
            log.info("IPO申告Paramsがnull");
            log.exiting(STR_METHOD_NAME );
            return false;
        }

        //２）１）のメソッドの戻り値の要素数(IPO申告Params)分以下の処理を繰り返す。
        int l_intIpoOrderParamsCnt = l_lisIpoOrderParams.size();
        log.debug("getIPO申告Paramsの戻り値の要素数 = " + l_intIpoOrderParamsCnt);
        for (int i = 0; i < l_intIpoOrderParamsCnt; i++)
        {
            //２－１）当選しているかのチェック
            IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_lisIpoOrderParams.get(i);
            if (!l_isAdvancedElection)
            {
                if (!WEB3LotResultDef.ELECTION.equals(l_ipoOrderParams.lot_result))
                {
                    continue;
                }
            }
            else
            {
                if (!WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderParams.lot_result_retry))
                {
                    continue;
                }
            }

            //２－２）購入申込終了日時以内であるかのチェック
            IpoProductParams l_ipoProductParams = l_dataManager.getIpoProductParams(l_ipoOrderParams.ipo_product_id, l_isAdvancedElection);
            if (l_ipoProductParams != null)
            {
                log.debug("IpoProductParams is not null.");
                log.exiting(STR_METHOD_NAME);
                return true;
            }
        }

        log.exiting(STR_METHOD_NAME );
        return false;
    }
    
    /**
     * (is立替金発生)<BR>
     * 立替金が発生している顧客か判別する。<BR>
     * 発生している場合はtrue、以外falseを返却する。<BR>
     * <BR>
     * １）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get立替金発生日()<BR>
     * をコールする。<BR>
     * 　@[get立替金発生日()にセットするパラメータ <BR>
     * 　@　@補助口座：　@パラメータ.補助口座<BR>
     * <BR>
     * ２）１）の戻り値 == nullの場合、falseを返却する <BR>
     * 　@以外、trueを返却する。<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    protected boolean isAdvanceGen(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isAdvanceGen(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME );
        //１）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get立替金発生日()
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        Date l_datGenDate = l_dataManager.getAdvanceGenDate(l_subAccount);
        log.exiting(STR_METHOD_NAME);
        
        //２）１）の戻り値 == nullの場合、falseを返却する
        if (l_datGenDate == null)
        {
            log.exiting(STR_METHOD_NAME );
            return false;
        }
        
        log.exiting(STR_METHOD_NAME );
        //以外、trueを返却する。
        return true;
    }
    
    /**
     * (is立替金実績)<BR>
     * 立替金実績がある顧客か判別する。<BR>
     * 実績がある場合はtrue、以外falseを返却する。<BR>
     * <BR>
     * １）パラメータ.補助口座.getMainAccount()をコールし、<BR>
     * 　@顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get余力取引停止区分()<BR>
     * 　@をコールする。<BR>
     * <BR>
     * 　@[get余力取引停止区分()にセットするパラメータ]<BR>
     * 　@　@顧客：　@１）で取得した顧客オブジェクト<BR>
     * <BR>
     * ３）上記２）の戻り値が、<BR>
     * 　@[ 1の場合]<BR>
     * 　@　@trueを返却する。<BR>
     * <BR>
     * 　@[ 1以外の場合]<BR>
     * 　@　@falseを返却する。<BR>
     * @@param l_subAccount<BR>
     * @@return<BR>
     */
    protected boolean isAdvancePerformance(WEB3GentradeSubAccount l_subAccount) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " isAdvancePerformance(WEB3GentradeSubAccount l_subAccount)";
        log.entering(STR_METHOD_NAME );

        //１）パラメータ.補助口座.getMainAccount()をコールし、
        //顧客オブジェクトを取得する。
        WEB3GentradeMainAccount l_account = (WEB3GentradeMainAccount)l_subAccount.getMainAccount();

        //２）ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ.get余力取引停止区分()
        //をコールする。
        //get余力取引停止区分()にセットするパラメータ]
        //顧客：　@１）で取得した顧客オブジェクト
        WEB3PvInfoDataManager l_dataManager =
            (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        String l_strTPTradingStop = l_dataManager.getTPTradingStop(l_account);

        //３）上記２）の戻り値が、
        if (WEB3TPTradingStopDivDef.TRADING_STOP.equals(l_strTPTradingStop))
        {
            //[ 1の場合]
            //trueを返却する。
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            //[ 1以外の場合]
            //falseを返却する。
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    /**
     * (get閲覧履歴ParamsTbl)<BR>
     * 該当顧客の閲覧履歴rowにkey（表示内容ID）を<BR>
     * 付加したHashtableにして返却する。<BR>
     * <BR>
     * １）QueryProcessor.doFindAllQuery()メソッドをコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@閲覧履歴rowtype<BR>
     * 　@arg1：　@"institution_code=? and branch_code=? and account_code=?"<BR>
     * 　@arg2：　@null<BR>
     * 　@arg3：　@new Object[]<BR>
     * 　@arg4：　@{<BR>
     * 　@　@　@　@　@　@パラメータ.顧客.getInstitution().getInstitutionCode(),<BR>
     * 　@　@　@　@　@　@パラメータ.顧客.getBranch().getBranchCode(),<BR>
     * 　@　@　@　@　@　@パラメータ.顧客.getAccountCode().substring(0, 6)<BR>
     * 　@　@　@　@　@}<BR>
     * <BR>
     * 　@エラーの場合は、例外をスローする。<BR>
     * <BR>
     * ２）上記１）で取得した閲覧履歴rowの要素数分、以下の処理を繰り返す<BR>
     * <BR>
     * 　@２－１）閲覧履歴row.getDisplayContentsId()と、<BR>
     * 　@　@　@　@　@閲覧履歴rowを<BR>
     * 　@　@　@　@　@Hashtableに追加する<BR>
     * <BR>
     * ３）上記２）で作成したHashtableを返却する。<BR>  
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * @@return tbl <BR>
     * @@throws WEB3BaseException <BR>
     */
    public Hashtable getBrowseHistoryParamsTbl(
        WEB3GentradeMainAccount l_mainAccount
        ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " getBrowseHistoryParams(WEB3GentradeMainAccount, long)";
        log.entering(STR_METHOD_NAME);

        //パラメータ.顧客をチェックする。
        if (l_mainAccount == null)
        {
            log.error("パラメータ.顧客Null出来ない。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ.顧客Null出来ない。");
        }

        String l_strInstitutionCode = l_mainAccount.getInstitution().getInstitutionCode();
        String l_strBranchCode = l_mainAccount.getBranch().getBranchCode();
        //※顧客コードは先頭6byteで比較すること。
        String l_strAccountCode = l_mainAccount.getAccountCode().substring(0, 6);

        //検索結果が取得できなかった場合はnullを返却する。
        List list = null;
        try
        {
            list = findRowByInstitutionCodeBranchCodeAccountCode(
                l_strInstitutionCode,
                l_strBranchCode,
                l_strAccountCode
                );

        }
        catch (DataFindException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        if (list.size() == 0)return null;

        Hashtable tbl = new Hashtable();
        for (int i = 0; i < list.size(); i++)
        {
            BrowseHistoryRow row = (BrowseHistoryRow) list.get(i);
            tbl.put(new Long(row.getDisplayContentsId()), row);
        }
        return tbl;
    }

    public List findRowByInstitutionCodeBranchCodeAccountCode(String p_institutionCode,
        String p_branchCode, String p_accountCode) throws DataNetworkException,
        DataFindException, DataQueryException
    {
        QueryProcessor qp = Processors.getDefaultProcessor();
        List list = qp.doFindAllQuery(
            BrowseHistoryRow.TYPE,
            "institution_code=? and branch_code=? and account_code=?",
            null,
            new Object[]
            {p_institutionCode, p_branchCode, p_accountCode});
        return list;
    }


// *** 未取込No.016 START ***
    /**
     * (get表示内容Params一覧－表示対象)<BR>
     * 表示内容Params一覧を年月日時分で絞り込んだ後、更に表示対象か判定<BR>
     * <BR> 
     * １）表示内容Paramsの要素数分、Loop処理<BR>
     * <BR> 
     * 　@（１）ArrayListを生成する。<BR>
     * <BR> 
     * 　@（２）以下の条件に該当しない場合、Loop処理の先頭に戻る。<BR>
     * <BR>
     *      [条件]<BR>
     * 　@   表示内容Params.表示期間From <= システム日付 かつ<BR>
     * 　@   表示内容Params.表示期間To >= システム日付<BR>
     *      ※比較精度は分までとする。<BR>
     * <BR> 
     *   （３）this.is表示対象判定()をコールし戻り値がfalseの場合、Loop処理の先頭に戻る。<BR>
     * 　@　@　@[引数]<BR>
     *　@　@   顧客：パラメータ.顧客<BR>
     *　@　@   補助口座：パラメータ.補助口座<BR>
     *　@　@   表示内容Params：表示内容Params[Index]<BR>
     * <BR>  
     * 　@（４）建玉強制処分の重複を削除する<BR> 
     * 　@　@（４－１）表示内容Params[Index].表示条件番号 = "1041" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1045")<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1046")<BR> 
     * 　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する<BR> 
     *<BR> 
     * 　@　@（４－２）表示内容Params[Index].表示条件番号 = "1042" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1047")<BR> 
     * 　@　@　@　@をコールし戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する<BR> 
     * <BR> 
     * 　@　@（４－３）表示内容Params[Index].表示条件番号 = "1043" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1045") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1046") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1047") <BR>
     * 　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する<BR> 
     * <BR> 
     * 　@　@（４－４）表示内容Params[Index].表示条件番号 = "1044" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1045") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1046") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1047") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1048") <BR>
     * 　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する<BR> 
     * <BR> 
     * 　@　@（４－５）表示内容Params[Index].表示条件番号 = "1045" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1041") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1043") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") <BR>
     * 　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、Loop処理の先頭に戻る。<BR> 
     * <BR> 
     * 　@　@（４－６）表示内容Params[Index].表示条件番号 = "1046" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1041") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1043") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") <BR>
     * 　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、Loop処理の先頭に戻る。<BR> 
     * <BR> 
     * 　@　@（４－７）表示内容Params[Index].表示条件番号 = "1047" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1042") <BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1043") <BR>
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") <BR>
     * 　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、Loop処理の先頭に戻る。<BR> 
     * <BR> 
     * 　@　@（４－８）表示内容Params[Index].表示条件番号 = "1048" の場合<BR> 
     * 　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") <BR>
     * 　@　@　@　@をコールし戻り値が 0 以上の場合、Loop処理の先頭に戻る。<BR> 
     * <BR> 
     * 　@（５）ArrayListに追加する。<BR>
     * <BR>
     * @@param l_mainAccount - (顧客)<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * @@param l_lisDisplayContens - (表示内容Params一覧)<BR>
     * @@return List<BR>
     * @@throws WEB3BaseException<BR> 
     */
    protected List getDisplayContensInDisplayTerm(
        WEB3GentradeMainAccount l_mainAccount,
        SubAccount l_subAccount,
        DisplayContentsParams[] l_lisDisplayContens)
        throws WEB3BaseException
    {
        String STR_METHOD_NAME = " getDisplayContensInDisplayTerm(WEB3GentradeMainAccount, " +
            "SubAccount, " +
            "DisplayContentsParams[])";
        log.entering(STR_METHOD_NAME);
        
        if (l_lisDisplayContens == null || l_lisDisplayContens.length == 0)
        {
            return null;
        }
        
        ArrayList l_lisRet = new ArrayList();
        Timestamp l_tsSystemTimestamp =
            (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
        
        for (int i = 0; i < l_lisDisplayContens.length; i++)
        {
            DisplayContentsParams l_displayContensParams =
                (DisplayContentsParams)l_lisDisplayContens[i];
            Timestamp l_tsTermFrom = l_displayContensParams.getTermFrom();
            Timestamp l_tsTermTo = l_displayContensParams.getTermTo();
            
            if (l_tsTermFrom != null && WEB3DateUtility.compareToMinute(l_tsSystemTimestamp, l_tsTermFrom) < 0)
            {
                continue;
            }
            if (l_tsTermTo != null && WEB3DateUtility.compareToMinute(l_tsSystemTimestamp, l_tsTermTo) > 0)
            {
                continue;
            }
            
            //this.is表示対象判定()をコールし戻り値がfalseの場合、Loop処理の先頭に戻る。
            if (!this.isDisplayObject(l_mainAccount, (WEB3GentradeSubAccount)l_subAccount, l_displayContensParams))
            {
                continue;
            }
            
            //（４）建玉強制処分の重複を削除する 
            //（４－１）表示内容Params[Index].表示条件番号 = "1041" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1045") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1046") 
            //　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する             
            int l_intConditonNo1045 = 0;
            int l_intConditonNo1046 = 0;
            if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1045 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2TO4DAY);
                if (l_intConditonNo1045 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1045); 
                }
                
                l_intConditonNo1046 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_5DAY);                
                if (l_intConditonNo1046 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1046); 
                }
            }

            //　@　@（４－２）表示内容Params[Index].表示条件番号 = "1042" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1047") 
            //　@　@　@　@をコールし戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する 
            int l_intConditonNo1047 = 0;
                
            if (WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1047 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_6DAY);
                if (l_intConditonNo1047 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1047);
                }
            }

            //　@　@（４－３）表示内容Params[Index].表示条件番号 = "1043" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1045") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1046") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1047") 
            //　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する 
            if (WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1045 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2TO4DAY);
                if (l_intConditonNo1045 >= 0) 
                {
                    l_lisRet.remove(l_intConditonNo1045);
                }
                
                l_intConditonNo1046 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_5DAY);                              
                if (l_intConditonNo1046 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1046); 
                }
                
                l_intConditonNo1047 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_6DAY);
                if (l_intConditonNo1047 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1047); 
                }
            }

            //　@　@（４－４）表示内容Params[Index].表示条件番号 = "1044" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1045") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1046") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1047") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1048") 
            //　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、ArrayList[戻り値]のデータを削除する 
            int l_intConditonNo1048 = 0;                
            if (WEB3PvInfoConditionDef.BREAK_3DAY_OVER.equals(
                l_displayContensParams.getConditionNo()))
            {
                l_intConditonNo1045 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2TO4DAY);
                if (l_intConditonNo1045 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1045);
                }
                
                l_intConditonNo1046 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_5DAY);                               
                if (l_intConditonNo1046 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1046);
                }
                
                l_intConditonNo1047 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_6DAY);
                if (l_intConditonNo1047 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1047);
                }
                
                l_intConditonNo1048 =
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_7DAY_OVER);
                if (l_intConditonNo1048 >= 0)
                {
                    l_lisRet.remove(l_intConditonNo1048);
                }
            }

            //　@　@（４－５）表示内容Params[Index].表示条件番号 = "1045" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1041") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1043") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") 
            //　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、Loop処理の先頭に戻る。 
            int l_intConditonNo1041 = 0; 
            int l_intConditonNo1043 = 0;
            int l_intConditonNo1044 = 0; 
            if (WEB3PvInfoConditionDef.BREAK_2TO4DAY.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1041 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
                l_intConditonNo1043 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1041 >= 0 ||
                        l_intConditonNo1043 >= 0 ||
                        l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }
 
            //　@　@（４－６）表示内容Params[Index].表示条件番号 = "1046" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1041") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1043") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") 
            //　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、Loop処理の先頭に戻る。 
            if (WEB3PvInfoConditionDef.BREAK_5DAY.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1041 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
                l_intConditonNo1043 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1041 >= 0 ||
                        l_intConditonNo1043 >= 0 ||
                        l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }

            //　@　@（４－７）表示内容Params[Index].表示条件番号 = "1047" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1042") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1043") 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") 
            //　@　@　@　@をコールしいずれかの戻り値が 0 以上の場合、Loop処理の先頭に戻る。 
            int l_intConditonNo1042 = 0;
            if (WEB3PvInfoConditionDef.BREAK_6DAY.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1042 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY);
                l_intConditonNo1043 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1042 >= 0 ||
                        l_intConditonNo1043 >= 0 ||
                        l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }

            //　@　@（４－８）表示内容Params[Index].表示条件番号 = "1048" の場合 
            //　@　@　@　@this.get指定表示条件番号(ArrayList, "1044") 
            //　@　@　@　@をコールし戻り値が 0 以上の場合、Loop処理の先頭に戻る。 
            if (WEB3PvInfoConditionDef.BREAK_7DAY_OVER.equals(
                l_displayContensParams.getConditionNo())) 
            {
                l_intConditonNo1044 = 
                    this.getAssignedConditonNo(l_lisRet, WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
                
                if (l_intConditonNo1044 >= 0)
                {
                    continue;
                }
            }

            l_lisRet.add(l_displayContensParams);
        }
        
        if (l_lisRet.size() == 0)
        {
            l_lisRet = null;
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_lisRet;
    }
// *** 未取込No.016 END ***

   /**
    * (isパスワード変更要 (顧客 顧客))<BR>
    * パスワードの変更が必要な客か判別する。<BR> 
    * 変更要の場合はtrue、以外falseを返却する。<BR> 
    * <BR>
    * １） OpLoginAdminServiceを生成する。<BR> 
    * <BR>
    * ２） 顧客.getログインID()をコールする<BR>
    * <BR>
    * ３） OpLoginAdminService.getLoginInfo()をコールする<BR> 
    * <BR>
    * [getLoginInfo()にセットするパラメータ]<BR> 
    * ログインID：上記２）の戻り値<BR>
    * <BR>
    * ４） OpLoginAdminService.getLoginTypeAttributes()をコールする<BR> 
    * <BR>
    * [getLoginTypeAttributes()にセットするパラメータ]<BR> 
    * ログインタイプID：上記３）の戻り値.getLoginTypeId()<BR>
    * <BR>
    * ５） ログインタイプ属性.get("一定期間経過後パスワード変更実施フラグ")をコールする。<BR> 
    * <BR>
    * ６） 上記５）の戻り値がnullの場合、falseを返却する。<BR> 
    * <BR>
    * ７） 上記５）の戻り値が"1：変更必要"の場合、falseを返却する。<BR> 
    * <BR>
    * ８） ログインタイプ属性.get("パスワード有効期間")をコールする。<BR> 
    * <BR>
    * ９） 上記８）の戻り値がnullの場合、falseを返却する。<BR> 
    * <BR>
    * １０） 上記８）の戻り値が"0"の場合、falseを返却する。<BR> 
    * <BR>
    * １１） 上記８）の戻り値が上記以外の場合、以下の処理を行う。<BR>
    * <BR>
    * １１－１） OpLoginAdminService.getLoginAttributes()をコールする <BR>
    * <BR>
    * [getLoginAttributes()にセットするパラメータ]<BR> 
    * ログインID：上記２）の戻り値 <BR>
    * <BR>
    * １１－２） ログイン属性.get("最終ログイン時刻")をコールする。<BR> 
    * <BR>
    * １１－３） 初回ログイン（＝上記１１－２）の戻り値が時刻に変換出来ない）の場合、falseを返却する。<BR> 
    * <BR>
    * １１－４） ログイン属性.get("前回パスワード変更日付")を取得する。<BR> 
    * <BR>
    * １１－５） 上記１１－４）の戻り値がnullの場合、falseを返却する。<BR> 
    * <BR>
    * １１－６） 上記１１－４）の戻り値が上記以外の場合、戻り値に上記８）の値を月数として加算する。<BR> 
    * <BR>
    * １１－７） 上記１１－６）の値より、現在日時（＝new Date()）が超過している場合はtrueを返却する。<BR>
    * <BR>
    * １１－８） 上記１１－７）でない場合は、falseを返却する。<BR>
    * @@param l_mainAccount - (顧客)<BR>
    * 顧客オブジェクト<BR>
    * <BR>
    * @@return boolean
    */
   protected boolean isPasswordChange(WEB3GentradeMainAccount l_mainAccount) throws WEB3BaseException
   {
       final String STR_METHOD_NAME = " isPasswordChange(WEB3GentradeMainAccount)";
       log.entering(STR_METHOD_NAME );

       if (l_mainAccount == null)
       {
           log.debug("顧客が未指定です。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BaseRuntimeException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80017,
               this.getClass().getName() + STR_METHOD_NAME,
               "顧客が未指定です。");
       }

       // OpLoginAdminServiceを生成する。
       OpLoginAdminService l_opLoginSec =
           (OpLoginAdminService) Services.getService(OpLoginAdminService.class);

       // 顧客.getログインID()をコールする
       long l_lngLoginId = l_mainAccount.getLoginId();

       // OpLoginAdminService.getLoginInfo()をコールする
       // [getLoginInfo()にセットするパラメータ]
       // ログインID：顧客.getLoginId()の戻り値
       LoginInfo l_loginInfo = l_opLoginSec.getLoginInfo(l_lngLoginId);
       if (l_loginInfo == null)
       {
           log.debug("LoginInfoが存在しない。");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3SystemLayerException(
               WEB3ErrorCatalog.SYSTEM_ERROR_80005,
               this.getClass().getName() + STR_METHOD_NAME,
               "LoginInfoが存在しない。");
       }

       // OpLoginAdminService.getLoginTypeAttributes()をコールする
       // [getLoginTypeAttributes()にセットするパラメータ]
       // ログインタイプID：ログイン情報.getLoginTypeId()の戻り値
       Map l_loginTypeAttributes = l_opLoginSec.getLoginTypeAttributes(l_loginInfo.getLoginTypeId());

       // ログインタイプ属性.get("一定期間経過後パスワード変更実施フラグ")をコールする。
       String l_strPWDChangeIntervalFlag =
           (String)l_loginTypeAttributes.get(WEB3LoginTypeAttributeKeyDef.PWDCHANGE_INTERVAL_FLAG);

       // 一定期間経過後パスワード変更実施フラグがnullの場合、falseを返却する。
       if (l_strPWDChangeIntervalFlag == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // 一定期間経過後パスワード変更実施フラグが"1：変更必要"の場合、falseを返却する。
       if (WEB3PwdChangeDef.REQUIRED.equals(l_strPWDChangeIntervalFlag))
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // ログインタイプ属性.get("パスワード有効期間")をコールする。
       Object l_objPWDInterval =
           l_loginTypeAttributes.get(WEB3LoginTypeAttributeKeyDef.PASSWORD_INTERVAL);

       // パスワード有効期間がnullの場合、falseを返却する。
       if (l_objPWDInterval == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }
       int l_intPWDInterval = Integer.parseInt((String)l_objPWDInterval);
       // パスワード有効期間が0の場合、falseを返却する。
       if (l_intPWDInterval == 0)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // OpLoginAdminService.getLoginAttributes()をコールする
       // [getLoginAttributes()にセットするパラメータ]
       // ログインID：顧客.getLoginId()の戻り値
       Map l_loginAttributes = l_opLoginSec.getLoginAttributes(l_lngLoginId);

       // ログイン属性.get("最終ログイン時刻")をコールする。
       Object l_objLastLogin = l_loginAttributes.get(WEB3LoginAttributeKeyDef.LAST_LOGIN);

       // 初回ログイン（＝最終ログイン時刻の値が時刻に変換出来ない）の場合、falseを返却する。
       Date l_datChange = WEB3DateUtility.getDate((String)l_objLastLogin, "yyyy.MM.dd HH:mm:ss");

       if (l_datChange == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // ログイン属性.get("前回パスワード変更日付")を取得する。
       Date l_datLastPWDChange =
           WEB3DateUtility.getDate(
               (String)l_loginAttributes.get(WEB3LoginAttributeKeyDef.LAST_PWDCHANGE),
               "yyyy.MM.dd HH:mm:ss");

       // 前回パスワード変更日付がnullの場合、falseを返却する。
       if (l_datLastPWDChange == null)
       {
           log.exiting(STR_METHOD_NAME );
           return false;
       }

       // 前回パスワード変更日付の値に、パスワード有効期間の値を月数として加算する。
       Calendar l_cal = new GregorianCalendar();
       l_cal.setTime(l_datLastPWDChange);
       l_cal.add(Calendar.MONTH, l_intPWDInterval);

       Date l_datAfterAdd = l_cal.getTime();

       // 加算結果より、現在日時（＝new Date()）が超過している場合はtrueを返却する。
       Date l_datnow = new Date();

       int l_intCompare =
           WEB3DateUtility.compareToSecond(l_datnow, l_datAfterAdd);
       if (l_intCompare > 0)
       {
           log.exiting(STR_METHOD_NAME );
           return true;
       }

       // 上記以外は、falseを返却する。
       log.exiting(STR_METHOD_NAME );
       return false;
   }
   
   /**
    * (get表示条件番号一覧－管理対象)<BR>
    * 未読既読管理対象の表示条件番号の一覧を返却する。<BR>
    * <BR>
    * 返却する表示条件番号は以下の通り<BR>
    * 　@"1001"（入金請求発生＆信用口座開設）<BR>
    * 　@"1002"（入金請求発生＆信用口座未開設）<BR>
    * 　@"1007"（決済期限間近(一週間前)の建玉保有）<BR>
    * 　@"1003"（立替金発生）<BR>
    * 　@"1005"（証拠金不足）<BR>
    * 　@"1041"（20％割れ1日＆30％割れ5日以下）<BR> 
    * 　@"1042"（20％割れ1日＆30％割れ6日）<BR> 
    * 　@"1043"（20％割れ2日＆30％割れ6日以下）<BR> 
    * 　@"1044"（20％割れ3日以上）<BR> 
    * 　@"1045"（30％割れ2～4日）<BR> 
    * 　@"1046"（30％割れ5日）<BR> 
    * 　@"1047"（30％割れ6日）<BR> 
    * 　@"1048"（30％割れ7日以上）<BR>
    * 　@"1049"（一部出金停止）<BR>
    * 　@"1050"（全額出金停止）<BR>
    *   "1051"（手数料割引キャンペーン）<BR>
    * 　@"1054"（不足金発生＆信用口座未開設）<BR>
    * 　@"1055"（不足金発生＆信用口座開設）<BR>
    * 　@"1056"（第一水準追証発生）<BR>
    * 　@"1057"（第二水準追証発生）<BR>
    * 　@"0000"（ダイレクト指定）<BR>
    * @@return String[]<BR>
    */
   protected String[] getManagedDispCondNoList()
   {
       final String STR_METHOD_NAME = " getManagedDispCondNoList()";
       log.entering(STR_METHOD_NAME);
       
       List l_lisArrayLists = new ArrayList();
       //返却する表示条件番号は以下の通り
       //"1001"（入金請求発生＆信用口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN);
       //"1002"（入金請求発生＆信用口座未開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE);
       //"1007"（決済期限間近(一週間前)の建玉保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SETTLE_BEF_AWEEK_CONTRACT_HAS);
       //"1003"（立替金発生）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ADVANCE_GENERATION);
       //"1005"（証拠金不足）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.IFO_DEPOSIT_SHORTAGE);
       //"1041"（20％割れ1日＆30％割れ5日以下)
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_1DAY_AND_5DAY_DOWN);
       //　@"1042"（20％割れ1日＆30％割れ6日） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_1DAY_AND_6DAY);
       //　@"1043"（20％割れ2日＆30％割れ6日以下） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_2DAY_AND_6DAY_DOWN);
       //　@"1044"（20％割れ3日以上） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_3DAY_OVER);
       //　@"1045"（30％割れ2～4日） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_2TO4DAY);
       //　@"1046"（30％割れ5日） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_5DAY);
       //　@"1047"（30％割れ6日） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_6DAY);
       //　@"1048"（30％割れ7日以上）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.BREAK_7DAY_OVER);
       //"1049"（一部出金停止） 
       l_lisArrayLists.add(WEB3PvInfoConditionDef.PART_PAYMENT_STOP);
       //"1050"（全額出金停止）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FULL_PAYMENT_STOP);
       //"1051"（手数料割引キャンペーン）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.COMMISSION_DISCOUNT_CAMPAIGN);
       //"1054"（不足金発生＆信用口座未開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE);
       //"1055"（不足金発生＆信用口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN);
       //"1056"（第一水準追証発生）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR);
       //"1057"（第二水準追証発生）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR);
       //"0000"（ダイレクト指定）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.DIRECT_ASSIGN);
       String[] l_strConLists = new String[l_lisArrayLists.size()];
       l_lisArrayLists.toArray(l_strConLists);
       
       log.exiting(STR_METHOD_NAME);
       return l_strConLists;
   }
   
   /**
    * (get表示条件番号一覧－管理対象外)<BR>
    * 未読既読管理対象外の表示条件番号の一覧を返却する。<BR>
    * <BR>
    * 返却する表示条件番号は以下の通り<BR>
    * 　@"1006"（決済期限間近(一ヶ月前)の建玉保有）<BR>
    * 　@"1027"（取引停止）<BR>
    * 　@"1004"（立替金実績）<BR>
    * 　@"1008"（信用口座開設）<BR>
    * 　@"1009"（信用口座未開設）<BR>
    * 　@"1010"（オプション口座開設）<BR>
    * 　@"1029"（外国証券口座開設）<BR>
    * 　@"1011"（株式保有）<BR>
    * 　@"1012"（信用建玉保有）<BR>
    * 　@"1013"（投信保有）<BR>
    * 　@"1014"（累投保有）<BR>
    * 　@"1015"（オプション建玉保有）<BR>
    * 　@"1016"（ミニ株保有）<BR>
    * 　@"1017"（先物保有）<BR>
    * 　@"1030"（外株保有）<BR>
    * 　@"1018"（預り金有り＆証券残無し）<BR>
    * 　@"1019"（預り金無し＆証券残無し）<BR>
    * 　@"1020"（株式・信用注文発生(当日)）<BR>
    * 　@"1021"（株式・信用注文発生(翌日)）<BR>
    * 　@"1022"（株式・信用約定発生）<BR>
    * 　@"1031"（外株注文発生(当日)）<BR>
    * 　@"1032"（外株注文発生(翌日)）<BR>
    * 　@"1033"（外株約定発生）<BR>
    * 　@"1023"（全顧客）<BR>
    * 　@"1024"（メールアドレス未登録）<BR>
    * 　@"1025"（IPO当選）<BR>
    * 　@"1026"（IPO繰上げ当選）<BR>
    * 　@"1028"（パスワード変更要）<BR>
    * 　@"1034"（モバイル専用口座開設）<BR>
    * 　@"1035"（モバイル専用口座未開設）<BR>
    *　@ "1036"（証券担保ローン口座開設）<BR>
    * 　@"1037"（書面交付日より11ヶ月経過）<BR>
    * 　@"1038"（PTS口座開設）<BR>
    * 　@"1039"（PTS口座未開設）<BR>
    * 　@"1058"（CFD口座開設）<BR>
    * 　@"1059"（CFD口座未開設）<BR>
    * @@return String[]<BR>
    */
   protected String[] getManagedOutDispCondNoList()
   {
       final String STR_METHOD_NAME = " getManagedOutDispCondNoList()";
       log.entering(STR_METHOD_NAME);
       
       List l_lisArrayLists = new ArrayList();
       //返却する表示条件番号は以下の通り
       //"1006"（決済期限間近(一ヶ月前)の建玉保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SETTLE_BEF_AMONTH_CONTRACT_HAS);
       //"1027"（取引停止）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.TRADE_SUSPENSION);
       //"1004"（立替金実績）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ADVANCE_RESULTS);
       //"1008"（信用口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MARGIN_ACCOUNT_OPEN);
       //"1009"（信用口座未開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MARGIN_ACCOUNT_CLOSE);
       //"1010"（オプション口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.OPTION_ACCOUNT_OPEN);
       //"1029"（外国証券口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_ACCOUNT_OPEN);
       //"1011"（株式保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCKS_HAS);
       //"1012"（信用建玉保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MARGIN_CONTRACT_HAS);
       //"1013"（投信保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MUTUAL_HAS);
       //"1014"（累投保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.RUITO_HAS);
       //"1015"（オプション建玉保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.OPTION_CONTRACT_HAS);
       //"1016"（ミニ株保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MINI_STOCK_HAS);
       //"1017"（先物保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FUTURE_HAS);
       //"1030"（外株保有）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_HAS);
       //"1018"（預り金有り＆証券残無し）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ACCOUNT_BAL_NON_SECURITIES_BAL);
       //"1019"（預り金無し＆証券残無し）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.NON_ACCOUNT_BAL_NON_SECURITIES_BAL);
       //"1020"（株式・信用注文発生(当日)）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCK_MARGIN_ORDER_GENERATION_TODAY);
       //"1021"（株式・信用注文発生(翌日)）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY);
       //"1022"（株式・信用約定発生）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.STOCK_MARGIN_EXECUTE_GENERATION);
       //"1031"（外株注文発生(当日)）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_ORDER_GENERATION_TODAY);
       //"1032"（外株注文発生(翌日)）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_ORDER_GENERATION_NEXT_DAY);
       //"1033"（外株約定発生）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FEQ_EXECUTE_GENERATION);
       //"1023"（全顧客）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ALL_ACCOUNT);
       //"1024"（メールアドレス未登録）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.MAIL_ADDRESS_NON_REGIST);
       //"1025"（IPO当選）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.IPO_ELECTION);
       //"1026"（IPO繰上げ当選）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.IPO_RETRY_ELECTION);
       //"1028"（パスワード変更要）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.LOGIN_PASSWORD_CHANGE);
       //"1034"（モバイル専用口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ONLY_MOBILE_OPEN);
       //"1035"（モバイル専用口座未開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.ONLY_MOBILE_NOT_OPEN);
       //"1036"（証券担保ローン口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.SECURED_LOAN_ACCOUNT_OPEN);
       //"1037"（書面交付日より11ヶ月経過）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.FROM_DELIVERY_DATE_11MONTH);
       //"1038"（PTS口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.PTS_ACCOUNT_OPEN);
       //"1039"（PTS口座未開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.PTS_ACCOUNT_CLOSE);
       //"1058"（CFD口座開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.CFD_ACCOUNT_OPEN);
       //"1059"（CFD口座未開設）
       l_lisArrayLists.add(WEB3PvInfoConditionDef.CFD_ACCOUNT_CLOSE);
       String[] l_strConLists = new String[l_lisArrayLists.size()];
       l_lisArrayLists.toArray(l_strConLists);
       
       log.exiting(STR_METHOD_NAME);
       return l_strConLists;
   }
   
   /**
    * (is表示対象判定)<BR>
    * 引数.表示内容Paramsが、表示対象か判定する。<BR>
    * 表示対象である場合true、以外falseを返却する<BR>
    * <BR>
    * <BR>
    * シーケンス図<BR>
    * 「(ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝ一覧サービス)is表示対象判定<BR>
    * @@param l_mainAccount - (顧客)<BR>
    * 顧客オブジェクト<BR>
    * @@param l_subAccount - (補助口座)<BR>
    * 補助口座オブジェクト<BR>
    * @@param l_displayContentsParams - (表示内容Params)<BR>
    * 表示内容Paramsオブジェクト<BR>
    * @@return boolean<BR>
    * @@throws WEB3BaseException<BR>
    */
    protected boolean isDisplayObject(
        WEB3GentradeMainAccount l_mainAccount,
        WEB3GentradeSubAccount l_subAccount,
        DisplayContentsParams l_displayContentsParams)
        throws WEB3BaseException
   {
        final String STR_METHOD_NAME = 
            " isDisplayObject(WEB3GentradeMainAccount, " +
            "WEB3GentradeSubAccount, " +
            "DisplayContentsParams)";
        log.entering(STR_METHOD_NAME);
        
        if ((l_mainAccount == null) || (l_subAccount == null) || (l_displayContentsParams == null))
        {
            log.error("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        WEB3PvInfoDataManager l_pvInfoDataMgr = 
            (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        
        boolean l_blnMarginAccountEstablished = false;
        boolean l_blnIsDepositRequestGen = false;
        boolean l_blnIsAssetHasEquity = false;
        boolean l_blnIsAccountBalanceHas = false;
        boolean l_blnIsAdvanceGen = false;
        boolean l_blnIsAdvancePerformance = false;
        boolean l_blnIsIfoDepositShortage = false;
        boolean l_blnIsIfoAccountOpen = false;
        boolean l_blnIsForeignAccountOpen = false;
        boolean l_blnIsContractHasMargin = false;
        boolean l_blnIsAssetHasMutual = false;
        boolean l_blnIsAssetHasRuito = false;
        boolean l_blnIsContractHasOption = false;
        boolean l_blnIsAssetHasMini = false;
        boolean l_blnIsContractHasFuture = false;
        boolean l_blnIsAssetHasFeq = false;
        boolean l_blnIsIpoElection = false;
        boolean l_blnIsIpoElectionRetry = false;
        boolean l_blnIsSuspensionAccount = false;
        boolean l_blnIsPasswordChange = false;         
        List l_lisGetSettleContractLists = null;
        
        // create検索条件データコンテナfor注文件数(顧客)
        String[] l_strQueryDataContainerForOrderCnts =
            createQueryDataContainerForOrderCnt(l_mainAccount);
        // create検索条件文字列for注文件数()
        String l_strQueryStringForOrderCnt = createQueryStringForOrderCnt();
        String[] l_strProductDivDef = 
            {WEB3PvInfoProductDivDef.PD_SPOT, WEB3PvInfoProductDivDef.PD_CREDIT};
        int l_intProductDivDefLength = l_strProductDivDef.length;
        
        WEB3PvInfoDataManager l_dataManager = (WEB3PvInfoDataManager)Services.getService(WEB3PvInfoDataManager.class);
        
        boolean l_blnReturn = false;
        int l_intConditionNo = Integer.parseInt(l_displayContentsParams.condition_no);
        switch (l_intConditionNo)
        {
            // 表示条件番号：1001（入金請求発生＆信用口座開設）
            case WEB3PvInfoConditionNoIntDef.DEPOSIT_REQUEST_MARGIN_ACC_OPEN:
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is入金請求発生(補助口座)
                l_blnIsDepositRequestGen = isDepositRequestGen(l_subAccount);
                l_blnReturn = l_blnIsDepositRequestGen && l_blnMarginAccountEstablished;
                break;
            // 表示条件番号：1002（入金請求発生＆信用口座未開設）
            case WEB3PvInfoConditionNoIntDef.DEPOSIT_REQUEST_MARGIN_ACC_CLOSE:
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is入金請求発生(補助口座)
                l_blnIsDepositRequestGen = isDepositRequestGen(l_subAccount);
                l_blnReturn = l_blnIsDepositRequestGen && (!l_blnMarginAccountEstablished);
                break;
            // 表示条件番号：1006（決済期限間近（一ヶ月前）の建玉保有）
            case WEB3PvInfoConditionNoIntDef.SETTLE_BEF_AMONTH_CONTRACT_HAS:
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                if (l_blnMarginAccountEstablished)
                {
                    // get決済期限間近建玉一覧(顧客 : 顧客, is決済一週間前 : boolean)               
                    l_lisGetSettleContractLists = l_pvInfoDataMgr.getSettleContractList(l_mainAccount, false);
                    l_blnReturn = (l_blnMarginAccountEstablished) && (l_lisGetSettleContractLists != null);
                }
                break;
            // 表示条件番号：1007（決済期限間近（一週間前）の建玉保有
            case WEB3PvInfoConditionNoIntDef.SETTLE_BEF_AWEEK_CONTRACT_HAS:
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                if (l_blnMarginAccountEstablished)
                {
                    // get決済期限間近建玉一覧(顧客 : 顧客, is決済一週間前 : boolean)
                    l_lisGetSettleContractLists = l_pvInfoDataMgr.getSettleContractList(l_mainAccount, true);
                    l_blnReturn = (l_blnMarginAccountEstablished) && (l_lisGetSettleContractLists != null);
                }
                break;
            // 表示条件番号：1008（信用口座開設）
            case WEB3PvInfoConditionNoIntDef.MARGIN_ACCOUNT_OPEN:
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                l_blnReturn = l_blnMarginAccountEstablished;
                break;
            // 表示条件番号：1009（信用口座未開設）
            case WEB3PvInfoConditionNoIntDef.MARGIN_ACCOUNT_CLOSE:
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                l_blnReturn = !l_blnMarginAccountEstablished;
                break;
            // 表示条件番号：1011（株式保有）
            case WEB3PvInfoConditionNoIntDef.STOCKS_HAS:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasEquity = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, false);
                l_blnReturn = l_blnIsAssetHasEquity;
                break;
            // 表示条件番号：1018（預り金有り＆証券残無し）
            case WEB3PvInfoConditionNoIntDef.ACCOUNT_BAL_NON_SECURITIES_BAL:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasEquity = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, false);
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is預り金残高有り(補助口座, boolean)
                l_blnIsAccountBalanceHas = isAccountBalanceHas(l_subAccount, l_blnMarginAccountEstablished);
                l_blnReturn = l_blnIsAccountBalanceHas && (!l_blnIsAssetHasEquity);
                break;
            // 表示条件番号：1019（預り金無し＆証券残無し）
            case WEB3PvInfoConditionNoIntDef.NON_ACCOUNT_BAL_NON_SECURITIES_BAL:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasEquity = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, false);
                // is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                     l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                // is預り金残高有り(補助口座, boolean)
                l_blnIsAccountBalanceHas = isAccountBalanceHas(l_subAccount, l_blnMarginAccountEstablished);
                l_blnReturn = (!l_blnIsAccountBalanceHas) && (!l_blnIsAssetHasEquity);
                break;
            // 表示条件番号：1003（立替金発生）
            case WEB3PvInfoConditionNoIntDef.ADVANCE_GENERATION:
                // is立替金発生(補助口座)
                l_blnIsAdvanceGen = isAdvanceGen(l_subAccount);
                l_blnReturn = l_blnIsAdvanceGen;
                break;
            // 表示条件番号：1004（立替金実績）
            case WEB3PvInfoConditionNoIntDef.ADVANCE_RESULTS:
                // is立替金実績(補助口座)
                l_blnIsAdvancePerformance = isAdvancePerformance(l_subAccount);
                l_blnReturn = l_blnIsAdvancePerformance;
                break;
            // 表示条件番号：1005（証拠金不足）
            case WEB3PvInfoConditionNoIntDef.IFO_DEPOSIT_SHORTAGE:
                // is証拠金不足(顧客)
                l_blnIsIfoDepositShortage = isIfoDepositShortage(l_mainAccount);
                l_blnReturn = l_blnIsIfoDepositShortage;
                break;
            // 表示条件番号：1010（オプション口座開設）
            case WEB3PvInfoConditionNoIntDef.OPTION_ACCOUNT_OPEN:
                // is先物OP口座開設(先物／オプション区分 : String)
                l_blnIsIfoAccountOpen = l_mainAccount.isIfoAccountOpen(WEB3FuturesOptionDivDef.OPTION);
                l_blnReturn = l_blnIsIfoAccountOpen;
                break;
            // 表示条件番号：1029（外国証券口座開設）
            case WEB3PvInfoConditionNoIntDef.FEQ_ACCOUNT_OPEN:
                // is外国証券口座開設()
                l_blnIsForeignAccountOpen = l_mainAccount.isForeignAccountOpen();
                l_blnReturn = l_blnIsForeignAccountOpen;
                break;
            // 表示条件番号：1012（信用建玉保有）
            case WEB3PvInfoConditionNoIntDef.MARGIN_CONTRACT_HAS:
                // is建玉保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String)
                l_blnIsContractHasMargin = 
                    l_pvInfoDataMgr.isContractHas(l_mainAccount, ProductTypeEnum.EQUITY, null);
                l_blnReturn = l_blnIsContractHasMargin;
                break;
            // 表示条件番号：1013（投信保有）
            case WEB3PvInfoConditionNoIntDef.MUTUAL_HAS:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasMutual = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.MUTUAL_FUND, false);
                l_blnReturn = l_blnIsAssetHasMutual;
                break;
            // 表示条件番号：1014（累投保有）
            case WEB3PvInfoConditionNoIntDef.RUITO_HAS:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasRuito = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.RUITO, false);
                l_blnReturn = l_blnIsAssetHasRuito;
                break;
            // 表示条件番号：1015（オプション建玉保有）
            case WEB3PvInfoConditionNoIntDef.OPTION_CONTRACT_HAS:
                //1.10.1 is建玉保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String)
                l_blnIsContractHasOption = 
                    l_pvInfoDataMgr.isContractHas(l_mainAccount, ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.OPTION);
                l_blnReturn = l_blnIsContractHasOption;
                break;
            // 表示条件番号：1016（ミニ株保有）
            case WEB3PvInfoConditionNoIntDef.MINI_STOCK_HAS:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasMini = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.EQUITY, true);
                l_blnReturn = l_blnIsAssetHasMini;
                break;
            // 表示条件番号：1017（先物保有）
            case WEB3PvInfoConditionNoIntDef.FUTURE_HAS:
                // is建玉保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, 先物／オプション区分 : String)
                l_blnIsContractHasFuture = 
                    l_pvInfoDataMgr.isContractHas(l_mainAccount, ProductTypeEnum.IFO, WEB3FuturesOptionDivDef.FUTURES);
                l_blnReturn = l_blnIsContractHasFuture;
                break;
            // 表示条件番号：1030（外株保有）
            case WEB3PvInfoConditionNoIntDef.FEQ_HAS:
                // is資産保有(顧客 : 顧客, 銘柄タイプ : ProductTypeEnum, isミニ株 : boolean)
                l_blnIsAssetHasFeq = l_pvInfoDataMgr.isAssetHas(l_mainAccount, ProductTypeEnum.FOREIGN_EQUITY, false);
                l_blnReturn = l_blnIsAssetHasFeq;
                break;
            // 表示条件番号：1020（株式・信用注文発生（当日））
            case WEB3PvInfoConditionNoIntDef.STOCK_MARGIN_ORDER_GENERATION_TODAY:
                int l_intGetOrderCntTodayStockMargin = 0;
                // 商品区分の数分Loop処理
                for (int i = 0; i < l_intProductDivDefLength; i++)
                {
                    // get注文件数(商品区分 : String, is当日注文 : boolean,
                    //   検索条件文字列 : String, 検索条件データコンテナ : String[])
                    l_intGetOrderCntTodayStockMargin = 
                        l_intGetOrderCntTodayStockMargin + l_pvInfoDataMgr.getOrderCnt(
                            l_strProductDivDef[i],
                            true,
                            l_strQueryStringForOrderCnt,
                            l_strQueryDataContainerForOrderCnts);
                }
                if (l_intGetOrderCntTodayStockMargin > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // 表示条件番号：1021（株式・信用注文発生（翌日））
            case WEB3PvInfoConditionNoIntDef.STOCK_MARGIN_ORDER_GENERATION_NEXT_DAY:
                int l_intGetOrderCntNextDayStockMargin = 0;
                // 商品区分の数分Loop処理
                for (int i = 0; i < l_intProductDivDefLength; i++)
                {
                    // get注文件数(商品区分 : String, is当日注文 : boolean,
                    //   検索条件文字列 : String, 検索条件データコンテナ : String[])
                    l_intGetOrderCntNextDayStockMargin = 
                        l_intGetOrderCntNextDayStockMargin + l_pvInfoDataMgr.getOrderCnt(
                            l_strProductDivDef[i],
                            false,
                            l_strQueryStringForOrderCnt,
                            l_strQueryDataContainerForOrderCnts);
                }
                if (l_intGetOrderCntNextDayStockMargin > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // 表示条件番号：1022（株式・信用約定発生）
            case WEB3PvInfoConditionNoIntDef.STOCK_MARGIN_EXECUTE_GENERATION:
                int l_intGetExecuteCntStockMargin = 0;
                // 商品区分の数分Loop処理
                for (int i = 0; i < l_intProductDivDefLength; i++)
                {
                    //
                    l_intGetExecuteCntStockMargin = 
                        l_intGetExecuteCntStockMargin +
                        l_pvInfoDataMgr.getExecuteCnt(l_mainAccount, l_strProductDivDef[i]);
                }
                if (l_intGetExecuteCntStockMargin > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // 表示条件番号：1031（外株注文発生（当日））
            case WEB3PvInfoConditionNoIntDef.FEQ_ORDER_GENERATION_TODAY:
                int l_intGetOrderCntTodayFeq = 0;
                // get注文件数(商品区分 : String, is当日注文 : boolean,
                //   検索条件文字列 : String, 検索条件データコンテナ : String[])
                l_intGetOrderCntTodayFeq = l_pvInfoDataMgr.getOrderCnt(
                    WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY,
                    true,
                    l_strQueryStringForOrderCnt,
                    l_strQueryDataContainerForOrderCnts);
                if (l_intGetOrderCntTodayFeq > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // 表示条件番号：1032（外株注文発生（翌日））
            case WEB3PvInfoConditionNoIntDef.FEQ_ORDER_GENERATION_NEXT_DAY:
                int l_intGetOrderCntNextDayFeq = 0;
                // get注文件数(商品区分 : String, is当日注文 : boolean,
                //   検索条件文字列 : String, 検索条件データコンテナ : String[])
                l_intGetOrderCntNextDayFeq = l_pvInfoDataMgr.getOrderCnt(
                    WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY,
                    false,
                    l_strQueryStringForOrderCnt,
                    l_strQueryDataContainerForOrderCnts);
                if (l_intGetOrderCntNextDayFeq > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // 表示条件番号：1033（外株約定発生）
            case WEB3PvInfoConditionNoIntDef.FEQ_EXECUTE_GENERATION:
                int l_intGetExecuteCntFeq = 0;
                // get約定件数(顧客 : 顧客, 商品区分 : String)
                l_intGetExecuteCntFeq = l_pvInfoDataMgr.getExecuteCnt(l_mainAccount, WEB3PvInfoProductDivDef.PD_FOREIGN_EQUITY);
                if (l_intGetExecuteCntFeq > 0)
                {
                    l_blnReturn = true;
                }
                break;
            // 表示条件番号：1025（IPO当選）
            case WEB3PvInfoConditionNoIntDef.IPO_ELECTION:
                // isIPO当選(顧客, boolean)
                l_blnIsIpoElection = isIpoElection(l_mainAccount, false);
                l_blnReturn = l_blnIsIpoElection;
                break;
            // 表示条件番号：1026（IPO繰上げ当選）
            case WEB3PvInfoConditionNoIntDef.IPO_RETRY_ELECTION:
                //1.16.1 isIPO当選(顧客, boolean)
                l_blnIsIpoElectionRetry = isIpoElection(l_mainAccount, true);
                l_blnReturn = l_blnIsIpoElectionRetry;
                break;
            // 表示条件番号：1027（取引停止）
            case WEB3PvInfoConditionNoIntDef.TRADE_SUSPENSION:
                // is取引停止顧客(補助口座)
                l_blnIsSuspensionAccount = isSuspensionAccount(l_subAccount);
                l_blnReturn = l_blnIsSuspensionAccount;
                break;
            // 表示条件番号：1028（パスワード変更要）
            case WEB3PvInfoConditionNoIntDef.LOGIN_PASSWORD_CHANGE:
                //1.18.1 isパスワード変更要(顧客)
                l_blnIsPasswordChange = isPasswordChange(l_mainAccount);
                l_blnReturn = l_blnIsPasswordChange;
                break;
            // 表示条件番号：1024（メールアドレス未登録）
            case WEB3PvInfoConditionNoIntDef.MAIL_ADDRESS_NON_REGIST:
                String l_strEmailAddress = null;
                long l_lngInstitutionId = l_mainAccount.getInstitution().getInstitutionId();
                long l_lngBranchId = l_mainAccount.getBranch().getBranchId();
                String l_strAccountCode = l_mainAccount.getAccountCode();
                try
                {
                    l_strEmailAddress = WEB3GentradeMainAccount.getMainAccountRow(                    
                        l_lngInstitutionId, l_lngBranchId, l_strAccountCode).getEmailAddress();
                }
                catch (DataQueryException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                catch (DataNetworkException l_ex)
                {
                    log.error("DBへのアクセスに失敗しました。");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                        WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                        this.getClass().getName() + "." + STR_METHOD_NAME,
                        l_ex.getMessage(),
                        l_ex);
                }
                if (l_strEmailAddress == null)
                {
                    l_blnReturn = true;
                }
                break;
                
            // 表示条件番号：1041（20％割れ1日＆30％割れ5日以下）
            case WEB3PvInfoConditionNoIntDef.BREAK_1DAY_AND_5DAY_DOWN:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1042（20％割れ1日＆30％割れ6日）
            case WEB3PvInfoConditionNoIntDef.BREAK_1DAY_AND_6DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号：  1043（20％割れ2日＆30％割れ6日以下） 
            case WEB3PvInfoConditionNoIntDef.BREAK_2DAY_AND_6DAY_DOWN:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1044（20％割れ3日以上）
            case WEB3PvInfoConditionNoIntDef.BREAK_3DAY_OVER:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1045（30％割れ2～4日）
            case WEB3PvInfoConditionNoIntDef.BREAK_2TO4DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1046（30％割れ5日） 
            case WEB3PvInfoConditionNoIntDef.BREAK_5DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1047（30％割れ6日） 
            case WEB3PvInfoConditionNoIntDef.BREAK_6DAY:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1048（30％割れ7日以上）
            case WEB3PvInfoConditionNoIntDef.BREAK_7DAY_OVER:
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT) &&
                        l_dataManager.isContractEnforcedDisposal(
                            l_mainAccount, l_displayContentsParams.condition_no))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1049（一部出金停止）
            case WEB3PvInfoConditionNoIntDef.PART_PAYMENT_STOP:
                if (l_dataManager.isCashoutStop(l_mainAccount,WEB3PaymentStopDivDef.PART))
                {
                    l_blnReturn = true;
                }
                break;
                
            //表示条件番号： 1050（全額出金停止）
            case WEB3PvInfoConditionNoIntDef.FULL_PAYMENT_STOP:
                if (l_dataManager.isCashoutStop(l_mainAccount,WEB3PaymentStopDivDef.FULL))
                {
                    l_blnReturn = true;
                }
                break; 

            //表示条件番号： 1051（手数料割引キャンペーン）
            case WEB3PvInfoConditionNoIntDef.ACC_INFO_CAMPAIGN:
                if (l_dataManager.isAccInfoCampaign(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break; 

            //表示条件番号： 1034（モバイル専用口座開設）
            case WEB3PvInfoConditionNoIntDef.ONLY_MOBILE_OPEN:
                if (l_dataManager.isOnlyMobileOpen(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号： 1035（モバイル専用口座未開設）
            case WEB3PvInfoConditionNoIntDef.ONLY_MOBILE_NOT_OPEN:
                if (!l_dataManager.isOnlyMobileOpen(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号： 1036（証券担保ローン口座開設）
            case WEB3PvInfoConditionNoIntDef.SECURED_LOAN_ACCOUNT_OPEN:
                if (l_mainAccount.isSecuredLoanAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号： 1037（書面交付日より11ヶ月経過）
            case WEB3PvInfoConditionNoIntDef.FROM_DELIVERY_DATE_11MONTH:
                if (l_dataManager.isDeliveryDate(l_mainAccount))
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号：1038（PTS口座開設）
            //isPTS口座開設() == true
            case WEB3PvInfoConditionNoIntDef.PTS_ACCOUNT_OPEN:
                if (l_mainAccount.isPTSAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号：1039（PTS口座未開設）
            //isPTS口座開設() == false
            case WEB3PvInfoConditionNoIntDef.PTS_ACCOUNT_CLOSE:
                if (!l_mainAccount.isPTSAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号：1054（不足金発生＆信用口座未開設）
            case WEB3PvInfoConditionNoIntDef.SHORT_FALL_GENERATION_MARGIN_ACC_CLOSE:
            //get不足金発生状況() == "1"
                if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_ACCOUNT.equals(
                    l_pvInfoDataMgr.getShortfallGenerationStatus(l_mainAccount)))
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号：1055（不足金発生＆信用口座開設）
            case WEB3PvInfoConditionNoIntDef.SHORT_FALL_GENERATION_MARGIN_ACC_OPEN:
                //get不足金発生状況() == "2"
                if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_ACCOUNT.equals(
                    l_pvInfoDataMgr.getShortfallGenerationStatus(l_mainAccount)))
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号：1056（第一水準追証発生）
            case WEB3PvInfoConditionNoIntDef.FIRST_ADDITIONAL_DEPOSIT_OCCUR:
                //is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                //is信用口座開設()==trueの場合
                if(l_blnMarginAccountEstablished)
                {
                    //get追証発生状況() == "1"
                    if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_EQUITY_ACCOUNT.equals(
                        l_pvInfoDataMgr.getAdddepositGenerationStatus(l_mainAccount)))
                    {
                        l_blnReturn = true;
                    }
                }
                break;

            //表示条件番号：1057（第二水準追証発生）
            case WEB3PvInfoConditionNoIntDef.SECOND_ADDITIONAL_DEPOSIT_OCCUR:
                //is信用口座開設(弁済区分 : String)
                l_blnMarginAccountEstablished =
                    l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT);
                //is信用口座開設()==trueの場合
                if(l_blnMarginAccountEstablished)
                {
                    //get追証発生状況() == "2"
                    if(WEB3TPShortfallGenerationStateDivDef.SHORTFALL_GENERATION_MARGIN_ACCOUNT.equals(
                        l_pvInfoDataMgr.getAdddepositGenerationStatus(l_mainAccount)))
                    {
                        l_blnReturn = true;
                    }
                }
                break;

            //表示条件番号：1058（CFD口座開設）
            //isCFD口座開設() == true
            case WEB3PvInfoConditionNoIntDef.CFD_ACCOUNT_OPEN:
                if (l_mainAccount.isCFDAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            //表示条件番号：1059（CFD口座未開設）
            //isCFD口座開設() == false
            case WEB3PvInfoConditionNoIntDef.CFD_ACCOUNT_CLOSE:
                if (!l_mainAccount.isCFDAccountOpen())
                {
                    l_blnReturn = true;
                }
                break;

            // 表示条件番号：0000（ダイレクト指定）
            case WEB3PvInfoConditionNoIntDef.DIRECT_ASSIGN:
                l_blnReturn = true;
                break;
            // 表示条件番号：1023（全顧客）
            case WEB3PvInfoConditionNoIntDef.ALL_ACCOUNT:
                l_blnReturn = true;
                break;
            // 上記以外はなにもしない
            default:
                break;
        }

        log.exiting(STR_METHOD_NAME);
        return l_blnReturn;
   }
    
    /**
     * (get指定表示条件番号)<BR>
     * 表示内容Params一覧から指定した表示条件番号の有無を返却する<BR> 
     * <BR>
     * １）表示内容Params一覧の要素数分、Loop処理<BR> 
     * <BR>
     * 　@（１）表示内容Params一覧[Index].表示条件番号 = パラメータ.表示条件番号 の場合<BR> 
     * <BR>
     * 　@　@　@Index を返却する<BR> 
     * <BR>
     * ２）Loop処理終了後<BR> 
     * <BR>
     * 　@　@　@-1 を返却する<BR>
     * <BR>
     * @@param l_lisDisplayContens - (表示内容Params一覧)
     * 表示内容Paramsオブジェクトを格納したリスト<BR>
     * @@param l_strConditionNo - (表示条件番号)
     * 表示内容Params一覧から探し出したい項目の表示条件番号<BR>
     * @@return int
     */
    private int getAssignedConditonNo(ArrayList l_lisDisplayContens, String l_strConditionNo)
    {
        final String STR_METHOD_NAME = 
            "getAssignedConditonNo(ArrayList l_lisDisplayContens, String l_strConditionNo)";
        log.entering(STR_METHOD_NAME);
        
        for (int i = 0; i < l_lisDisplayContens.size(); i ++) 
        {
            //表示内容Params一覧[Index].表示条件番号 = パラメータ.表示条件番号 の場合
            DisplayContentsRow l_displayContensRow =
                (DisplayContentsRow)l_lisDisplayContens.get(i);          
            if (l_strConditionNo.equals(l_displayContensRow.getConditionNo())) 
            {
                //Index を返却する
                log.exiting(STR_METHOD_NAME);
                return i;
            }
        }
        
        //-1 を返却する
        log.exiting(STR_METHOD_NAME);
        return -1;
    }
}@
