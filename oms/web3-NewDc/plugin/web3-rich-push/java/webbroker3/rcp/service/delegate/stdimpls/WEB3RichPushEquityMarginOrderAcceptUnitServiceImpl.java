head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ株式注文受付通知サービス実装(WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcEquityMarginOrderAcceptPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.data.RichPushEquityOrderAcceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * リッチクライアントデータプッシュ株式注文受付通知サービスサービス実装
 * @@author 劉
 * @@version 1.0
 */
public class WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushEquityMarginOrderAcceptUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushEquityMarginOrderAcceptUnitServiceImpl.class);

    /**
     * リッチクライアントデータプッシュ
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException
    {

        // 1.1. リッチクライアントプッシュデータ読み込み
        WEB3RichPushPersistentDataManager l_perService =
            (WEB3RichPushPersistentDataManager) Services.getService(
            WEB3RichPushPersistentDataManager.class);
        List l_equityOrderAcceptList
            = l_perService.getEquityOrderAcceptRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 リッチクライアントデータプッシュ
        if (l_equityOrderAcceptList.size() > 0)
        {

            //コンテクストへ保存
            saveToContext(l_equityOrderAcceptList);
        }

    }

    /**
     * リッチクライアントプッシュデータをXMLへ変換
     *
     * @@param l_dataRows List
     * @@return String
     */
    public String createRichPushXmlMessage(
        List l_dataRows)
    {
        final String STR_METHOD_NAME = "createRichPushXmlMessage()";
        log.entering(STR_METHOD_NAME);

        StringBuffer l_strbuff = new StringBuffer();

        log.debug("リッチクライアントプッシュデータXMLへ変換：件数 = " + l_dataRows.size());
        for (int i = 0; i < l_dataRows.size(); i++)
        {
            log.debug("リッチクライアントプッシュデータXMLへ変換：Loop処理 i = " + i);
            Row l_row = (Row) l_dataRows.get(i);
            //log.debug("RichPushRow:" + l_row);
            //株式注文受付通知の場合
            if (l_row instanceof RichPushEquityOrderAcceptRow)
            {
                RichPushEquityOrderAcceptRow l_acceptRow = (RichPushEquityOrderAcceptRow)
                    l_row;
                //株式注文受付通知
                if (WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT.equals(l_acceptRow.
                    getType()))
                {
                    WEB3RcEquityMarginOrderAcceptPush l_pushObj = this.convert( (
                        RichPushEquityOrderAcceptRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応注文受付通知データ
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_acceptRow.getType());
                }
            }
            //未対応データ
            else
            {
                log.error("unsupported RowType:" + l_row.getRowType());
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_strbuff.toString();

    }

    /**
     * プッシュデータオブジェクト変換
     *
     * @@param l_dataRow RichPushEquityOrderAcceptRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginOrderAcceptPush
     */
    private WEB3RcEquityMarginOrderAcceptPush convert(
        RichPushEquityOrderAcceptRow l_dataRow)
    {
        WEB3RcEquityMarginOrderAcceptPush l_rcEquityMarginOrderAcceptPush = new
            WEB3RcEquityMarginOrderAcceptPush();

        //シリア番号
        l_rcEquityMarginOrderAcceptPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));
        //データコード
        l_rcEquityMarginOrderAcceptPush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcEquityMarginOrderAcceptPush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcEquityMarginOrderAcceptPush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcEquityMarginOrderAcceptPush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcEquityMarginOrderAcceptPush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcEquityMarginOrderAcceptPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //注文受付結果
        l_rcEquityMarginOrderAcceptPush.setOdacr(l_dataRow.getAcceptStatus());
        //エラーコード
        l_rcEquityMarginOrderAcceptPush.setErrcd(l_dataRow.getErrorMessage());
        //発注経路
        l_rcEquityMarginOrderAcceptPush.setOdrdv(l_dataRow.getSubmitOrderRouteDiv());
        //処理区分
        l_rcEquityMarginOrderAcceptPush.setTrstp(l_dataRow.getStatus());
        //市場コード
        l_rcEquityMarginOrderAcceptPush.setMcd(l_dataRow.getMarketCode());
        //銘柄コード
        l_rcEquityMarginOrderAcceptPush.setPdc(l_dataRow.getProductCode());
        //作成日付
        l_rcEquityMarginOrderAcceptPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcEquityMarginOrderAcceptPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));

        return l_rcEquityMarginOrderAcceptPush;
    }

}
@
