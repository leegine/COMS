head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.31.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushEquityMarginChangeCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ株式訂正取消通知サービス実装(WEB3RichPushEquityMarginChangeCancelUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/15 李俊(中訊) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcEquityMarginChangeCancelPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import webbroker3.rcp.data.RichPushEquityChangeCancelRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * リッチクライアントデータプッシュ株式訂正取消通知サービス実装
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3RichPushEquityMarginChangeCancelUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushEquityMarginChangeCancelUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushEquityMarginChangeCancelUnitServiceImpl.class);

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
        List l_equityMarginChangeCancelList
            = l_perService.getEquityChangeCancelRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

		// 1.2 リッチクライアントデータプッシュ
		if (l_equityMarginChangeCancelList.size() > 0)
		{

			//コンテクストへ保存
			saveToContext(l_equityMarginChangeCancelList);

		}

    }

    /**
     * リッチクライアントプッシュデータをXMLへ変換
     *
     * @@param l_dataRows List
     * @@param l_historyTbl Map
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
            //株式訂正取消通知の場合
            if (l_row instanceof RichPushEquityChangeCancelRow)
            {
                RichPushEquityChangeCancelRow l_changeCancelRow = (
                    RichPushEquityChangeCancelRow)
                    l_row;
                //株式訂正取消通知
                if (WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL.equals(
                    l_changeCancelRow.getType()))
                {
                    WEB3RcEquityMarginChangeCancelPush l_pushObj = this.convert( (
                        RichPushEquityChangeCancelRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応株式訂正取消通知データ
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" +
                              l_changeCancelRow.getType());
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
     * @@param l_dataRow RichPushEquityChangeCancelRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginChangeCancelPush
     */
    private WEB3RcEquityMarginChangeCancelPush convert(
        RichPushEquityChangeCancelRow l_dataRow)
    {
        WEB3RcEquityMarginChangeCancelPush l_rcEquityMarginChangeCancelPush = new
            WEB3RcEquityMarginChangeCancelPush();

        //データコード
        l_rcEquityMarginChangeCancelPush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcEquityMarginChangeCancelPush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcEquityMarginChangeCancelPush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcEquityMarginChangeCancelPush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcEquityMarginChangeCancelPush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcEquityMarginChangeCancelPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //銘柄コード
        l_rcEquityMarginChangeCancelPush.setPdc(l_dataRow.getProductCode());
        //市場コード
        l_rcEquityMarginChangeCancelPush.setMcd(l_dataRow.getMarketCode());
        //訂正後数量
        l_rcEquityMarginChangeCancelPush.setAcqt(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getModifiedQuantity()));
        //訂正後指値
        l_rcEquityMarginChangeCancelPush.setAclpr(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getModifiedLimitPrice()));
        //訂正後執行条件（SONAR）
        l_rcEquityMarginChangeCancelPush.setAcectps(l_dataRow.getModifiedExecutionType());
        //訂正後値段条件（SONAR）
        l_rcEquityMarginChangeCancelPush.setAcprctps(l_dataRow.
            getModifiedPriceConditionType());
        //訂正結果コード
        l_rcEquityMarginChangeCancelPush.setCrcd(l_dataRow.getModifiedResult());
        //訂正取消通知区分
        l_rcEquityMarginChangeCancelPush.setCcpdv(l_dataRow.getCanmodReceiptType());
        //処理区分
        l_rcEquityMarginChangeCancelPush.setTrstp(l_dataRow.getStatus());
        //作成日付
        l_rcEquityMarginChangeCancelPush.setCrdt(WEB3DateUtility.formatDate(
            l_dataRow.getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcEquityMarginChangeCancelPush.setUpdt(WEB3DateUtility.formatDate(
            l_dataRow.getLastUpdatedTimestamp(), YYYYMMDD));
        //シリアル番号
        l_rcEquityMarginChangeCancelPush.setSerlnum(String.valueOf(l_dataRow.
            getSerlnum()));

        return l_rcEquityMarginChangeCancelPush;
    }

}
@
