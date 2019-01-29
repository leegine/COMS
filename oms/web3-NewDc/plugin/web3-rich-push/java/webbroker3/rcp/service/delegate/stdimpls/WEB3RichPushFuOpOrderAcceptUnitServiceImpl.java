head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushFuOpOrderAcceptUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ先物OP注文受付通知サービス実装(WEB3RichPushFuOpOrderAcceptUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/23 李俊(中訊) 新規作成
*/

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcFuOpOrderAcceptPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rcp.data.RichPushIfoOrderAcceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * リッチクライアントプッシュ先物OP注文受付通知サービス実装
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3RichPushFuOpOrderAcceptUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushFuOpOrderAcceptUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushFuOpOrderAcceptUnitServiceImpl.class);

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
        List l_ifoOrderAcceptList
            = l_perService.getIfoOrderAcceptRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 リッチクライアントデータプッシュ
        if (l_ifoOrderAcceptList.size() > 0)
        {

            //コンテクストへ保存
            saveToContext(l_ifoOrderAcceptList);

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
            //先物OP注文受付通知の場合
            if (l_row instanceof RichPushIfoOrderAcceptRow)
            {
                RichPushIfoOrderAcceptRow l_acceptRow =
                    (RichPushIfoOrderAcceptRow) l_row;
                //先物OP注文受付通知
                if (WEB3RichPushDataTypeDef.IFO_ORDER_ACCEPT.equals(
                    l_acceptRow.getType()))
                {
                    WEB3RcFuOpOrderAcceptPush l_pushObj = this.convert( (
                        RichPushIfoOrderAcceptRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応先物OP注文受付通知データ
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
     * @@param l_dataRow RichPushIfoOrderAcceptRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcFuOpOrderAcceptPush
     */
    private WEB3RcFuOpOrderAcceptPush convert(
        RichPushIfoOrderAcceptRow l_dataRow)
    {
        WEB3RcFuOpOrderAcceptPush l_rcFuOpOrderAcceptPush = new
            WEB3RcFuOpOrderAcceptPush();

        //データコード
        l_rcFuOpOrderAcceptPush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcFuOpOrderAcceptPush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcFuOpOrderAcceptPush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcFuOpOrderAcceptPush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcFuOpOrderAcceptPush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcFuOpOrderAcceptPush.setOdid(String.valueOf(l_dataRow.getOrderId()));
        //銘柄コード
        l_rcFuOpOrderAcceptPush.setPdc(l_dataRow.getProductCode());
        //建区分
        OrderTypeEnum l_orderType = l_dataRow.getOrderType() ;
        String l_strContp = null;
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) ||
            OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) ||
            OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType) ||
            OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
                //605：OP新規買建注文　@　@　@　@　@　@、601：先物新規買建注文
                //608：OP買建売返済注文（売返済）、604：先物買建売返済注文（売返済）
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType) ||
                OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType) ||
                OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
                //606：OP新規売建注文　@　@　@　@　@　@、602：先物新規売建注文
                //607：OP売建買返済注文（買返済）、603：先物売建買返済注文（買返済）
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_SELL;
        }
        l_rcFuOpOrderAcceptPush.setContp(l_strContp);
        //取引市場
        l_rcFuOpOrderAcceptPush.setMcd(l_dataRow.getMarketCode());
        //指数種別（原資産銘Q柄コード）
        l_rcFuOpOrderAcceptPush.setTpd(l_dataRow.getUnderlyingProductCode());
        //限月
        l_rcFuOpOrderAcceptPush.setDm(l_dataRow.getMonthOfDelivery());
        //オプション商品区分
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpOrderAcceptPush.setPdtp(WEB3IfoProductTypeDef.CALL_OPTIONS);
        }
        else if ( IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpOrderAcceptPush.setPdtp(WEB3IfoProductTypeDef.PUT_OPTIONS);
        }
        //行使価格
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_dataRow.getDerivativeType()) ||
            IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpOrderAcceptPush.setSpr(WEB3StringTypeUtility.formatNumber(
                l_dataRow.getStrikePrice()));
        }
        //注文受付結果
        l_rcFuOpOrderAcceptPush.setOdacr(l_dataRow.getAcceptStatus());
        //エラーコード
        l_rcFuOpOrderAcceptPush.setErrcd(l_dataRow.getErrorMessage());
        //処理区分
        l_rcFuOpOrderAcceptPush.setTrstp(l_dataRow.getStatus());
        //作成日付
        l_rcFuOpOrderAcceptPush.setCrdt(WEB3DateUtility.formatDate(l_dataRow.
            getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcFuOpOrderAcceptPush.setUpdt(WEB3DateUtility.formatDate(l_dataRow.
            getLastUpdatedTimestamp(), YYYYMMDD));
        //シリアル番号
        l_rcFuOpOrderAcceptPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));

        return l_rcFuOpOrderAcceptPush;
    }

}
@
