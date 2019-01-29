head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushEquityMarginLapseUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ現物信用失効通知サービス実装(WEB3RichPushEquityMarginLapseUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/15 李俊(中訊) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcEquityMarginLapsePush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.data.RichPushEquityLapseRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * リッチクライアントデータプッシュ株式失効通知サービス実装
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3RichPushEquityMarginLapseUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushEquityMarginLapseUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushEquityMarginLapseUnitServiceImpl.class);

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
        List l_equityMarginLapseList
            = l_perService.getEquityLapseRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 リッチクライアントデータプッシュ
        if (l_equityMarginLapseList.size() > 0)
        {

            //コンテクストへ保存
            saveToContext(l_equityMarginLapseList);
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
            log.debug("RichPushRow:" + l_row);
            //株式失効通知の場合
            if (l_row instanceof RichPushEquityLapseRow)
            {
                RichPushEquityLapseRow l_lapseRow = (RichPushEquityLapseRow)
                    l_row;
                //株式失効通知
                if (WEB3RichPushDataTypeDef.EQTYPE_LAPSE.equals(l_lapseRow.
                    getType()))
                {
                    WEB3RcEquityMarginLapsePush l_pushObj = this.convert( (
                        RichPushEquityLapseRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応株式失効通知データ
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_lapseRow.getType());
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
     * @@param l_dataRow RichPushEquityLapseRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginLapsePush
     */
    private WEB3RcEquityMarginLapsePush convert(
        RichPushEquityLapseRow l_dataRow)
    {
        WEB3RcEquityMarginLapsePush l_rcEquityMarginLapsePush = new
            WEB3RcEquityMarginLapsePush();

        //データコード
        l_rcEquityMarginLapsePush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcEquityMarginLapsePush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcEquityMarginLapsePush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcEquityMarginLapsePush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcEquityMarginLapsePush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcEquityMarginLapsePush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //銘柄コード
        l_rcEquityMarginLapsePush.setPdc(l_dataRow.getProductCode());
        //市場コード
        l_rcEquityMarginLapsePush.setMcd(l_dataRow.getMarketCode());
        //約定数量
        l_rcEquityMarginLapsePush.setEqt(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getExecutedQuantity()));
        //失効理由コード
        l_rcEquityMarginLapsePush.setLrcd(l_dataRow.getReasonCode());
        //失効通知区分
        l_rcEquityMarginLapsePush.setLpdv(l_dataRow.getCloseNotifyType());
        //エラーコード
        l_rcEquityMarginLapsePush.setErrcd(l_dataRow.getErrorMessage());
        //処理区分
        l_rcEquityMarginLapsePush.setTrstp(l_dataRow.getStatus());
        //作成日付
        l_rcEquityMarginLapsePush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcEquityMarginLapsePush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));
        //シリアル番号
        l_rcEquityMarginLapsePush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));

        return l_rcEquityMarginLapsePush;
    }

}
@
