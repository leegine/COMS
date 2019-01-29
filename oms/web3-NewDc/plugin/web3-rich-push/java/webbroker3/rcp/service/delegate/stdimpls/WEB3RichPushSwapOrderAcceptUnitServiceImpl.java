head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushSwapOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ株式現引現渡受付サービス実装(WEB3RichPushSwapOrderAcceptUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcSwapOrderAcceptPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.data.RichPushSwapOrderAcceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * リッチクライアントプッシュ株式現引現渡受付サービス実装
 * @@author 劉
 * @@version 1.0
 */
public class WEB3RichPushSwapOrderAcceptUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushSwapOrderAcceptUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushSwapOrderAcceptUnitServiceImpl.class);

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
        List l_equitySwapOrderAcceptList
            = l_perService.getSwapOrderAcceptRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 リッチクライアントデータプッシュ
        if (l_equitySwapOrderAcceptList.size() > 0)
        {

            //コンテクストへ保存
            saveToContext(l_equitySwapOrderAcceptList);
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
            //株式信用現引現渡受付の場合
            if (l_row instanceof RichPushSwapOrderAcceptRow)
            {
                RichPushSwapOrderAcceptRow l_AcceptRow = (RichPushSwapOrderAcceptRow)
                    l_row;
                //株式注文受付(信用現引現渡以外)
                if (WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT.equals(l_AcceptRow.
                    getType()))
                {
                    WEB3RcSwapOrderAcceptPush l_pushObj = this.convert( (
                        RichPushSwapOrderAcceptRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応注文受付データ
                else
                {
                    log.error("unsupported WEB3RichPushDataType:" + l_AcceptRow.getType());
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
     * @@param l_dataRow RichPushSwapOrderAcceptRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcEquityMarginOrderAcceptPush
     */
    private WEB3RcSwapOrderAcceptPush convert(
        RichPushSwapOrderAcceptRow l_dataRow)
    {
        WEB3RcSwapOrderAcceptPush l_rcSwapOrderAcceptPush = new
            WEB3RcSwapOrderAcceptPush();

        //シリア番号
        l_rcSwapOrderAcceptPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));
        //データコード
        l_rcSwapOrderAcceptPush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcSwapOrderAcceptPush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcSwapOrderAcceptPush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcSwapOrderAcceptPush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcSwapOrderAcceptPush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcSwapOrderAcceptPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //注文受付結果
        l_rcSwapOrderAcceptPush.setOdacr(l_dataRow.getAcceptStatus());
        //エラーコード
        l_rcSwapOrderAcceptPush.setErrcd(l_dataRow.getErrorMessage());

        //発注経路
        //l_rcSwapOrderAcceptPush.setOdrdv(l_dataRow.getSubmitOrderRouteDiv());

        //市場コード
        l_rcSwapOrderAcceptPush.setMcd(l_dataRow.getMarketCode());
        //銘柄コード
        l_rcSwapOrderAcceptPush.setPdc(l_dataRow.getProductCode());

        //処理区分
        l_rcSwapOrderAcceptPush.setTrstp(l_dataRow.getStatus());
        //作成日付
        l_rcSwapOrderAcceptPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcSwapOrderAcceptPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));

        return l_rcSwapOrderAcceptPush;
    }

}
@
