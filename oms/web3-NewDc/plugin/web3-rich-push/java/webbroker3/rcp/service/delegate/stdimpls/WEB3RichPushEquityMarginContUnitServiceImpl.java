head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushEquityMarginContUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ株式出来通知サービス実装(WEB3RichPushEquityMarginContUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/15 李俊(中訊) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcEquityMarginContPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.data.RichPushEquityContRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * リッチクライアントデータプッシュ株式出来通知サービス実装
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3RichPushEquityMarginContUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushEquityMarginContUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushEquityMarginContUnitServiceImpl.class);

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
        List l_equityContList
            = l_perService.getEquityContRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 リッチクライアントデータプッシュ
        if (l_equityContList.size() > 0)
        {

            //コンテクストへ保存
            saveToContext(l_equityContList);

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
            //株式出来通知の場合
            if (l_row instanceof RichPushEquityContRow)
            {
                RichPushEquityContRow l_contRow = (RichPushEquityContRow)
                    l_row;
                //株式出来通知
                if (WEB3RichPushDataTypeDef.EQTYPE_CONT.equals(l_contRow.
                    getType()))
                {
                    WEB3RcEquityMarginContPush l_pushObj = this.convert( (
                        RichPushEquityContRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応株式出来通知データ
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_contRow.getType());
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
     * @@param l_dataRow RichPushEquityContRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginContPush
     */
    private WEB3RcEquityMarginContPush convert(
        RichPushEquityContRow l_dataRow)
    {
        WEB3RcEquityMarginContPush l_rcEquityMarginContPush = new
            WEB3RcEquityMarginContPush();

        //データコード
        l_rcEquityMarginContPush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcEquityMarginContPush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcEquityMarginContPush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcEquityMarginContPush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcEquityMarginContPush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcEquityMarginContPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //銘柄コード
        l_rcEquityMarginContPush.setPdc(l_dataRow.getProductCode());
        //市場コード
        l_rcEquityMarginContPush.setMcd(l_dataRow.getMarketCode());
        //約定数量
        l_rcEquityMarginContPush.setEqt(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getExecQuantity()));
        //約定単価
        l_rcEquityMarginContPush.setEpr(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getExecPrice()));
        //約定日時
        l_rcEquityMarginContPush.setExet(WEB3DateUtility.formatDate(l_dataRow.
            getExecTimestamp(), "yyyyMMddHHmmss"));
        //出来通知区分
        l_rcEquityMarginContPush.setCpdv(l_dataRow.getDealedType());
        //処理区分
        l_rcEquityMarginContPush.setTrstp(l_dataRow.getStatus());
        //作成日付
        l_rcEquityMarginContPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcEquityMarginContPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));
        //シリアル番号
        l_rcEquityMarginContPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));

        return l_rcEquityMarginContPush;
    }

}
@
