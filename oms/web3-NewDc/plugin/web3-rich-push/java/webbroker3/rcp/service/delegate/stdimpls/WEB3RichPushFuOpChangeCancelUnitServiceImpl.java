head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.32.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushFuOpChangeCancelUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュ先物OP訂正取消通知サービス実装(WEB3RichPushFuOpChangeCancelUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/23 李俊(中訊) 新規作成
*/

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import jp.co.dir.wb3.rc.xmltrans.response.WEB3RcFuOpChangeCancelPush;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rcp.data.RichPushIfoChangeCancelRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3RichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3RichPushPersistentDataManager;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * リッチクライアントプッシュ先物OP訂正取消通知サービス実装
 * @@author 李俊(中訊)
 * @@version 1.0
 */
public class WEB3RichPushFuOpChangeCancelUnitServiceImpl
    extends WEB3RichPushUnitServiceImpl
    implements WEB3RichPushFuOpChangeCancelUnitService
{
    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String YYYYMMDD = "yyyyMMdd";

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushFuOpChangeCancelUnitServiceImpl.class);

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
        List l_ifoChangeCancelList
            = l_perService.getIfoChangeCancelRichPushData(l_lngFromAccountId,
            l_lngToAccountId);

        // 1.2 リッチクライアントデータプッシュ
        if (l_ifoChangeCancelList.size() > 0)
        {

            //コンテクストへ保存
            saveToContext(l_ifoChangeCancelList);

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
            //先物OP訂正取消通知の場合
            if (l_row instanceof RichPushIfoChangeCancelRow)
            {
                RichPushIfoChangeCancelRow l_changeCancelRow =
                    (RichPushIfoChangeCancelRow) l_row;
                //先物OP訂正取消通知
                if (WEB3RichPushDataTypeDef.IFO_CHANGE_CANCEL.equals(
                    l_changeCancelRow.getType()))
                {
                    WEB3RcFuOpChangeCancelPush l_pushObj = this.convert( (
                        RichPushIfoChangeCancelRow) l_row);
                    String l_strPushXML = WEB3RichPushObjectToXMLConverter.toXML(
                        l_pushObj);

                    l_strbuff.append(l_strPushXML);
                }
                //未対応先物OP訂正取消通知データ
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
     * @@param l_dataRow RichPushIfoChangeCancelRow
     * @@param l_historyRow RichPushHistoryRow
     * @@return WEB3RcFuOpChangeCancelPush
     */
    private WEB3RcFuOpChangeCancelPush convert(
        RichPushIfoChangeCancelRow l_dataRow)
    {
        WEB3RcFuOpChangeCancelPush l_rcFuOpChangeCancelPush = new
            WEB3RcFuOpChangeCancelPush();

        //データコード
        l_rcFuOpChangeCancelPush.setDcd(l_dataRow.getRequestCode());
        //会社コード
        l_rcFuOpChangeCancelPush.setAa_icd(l_dataRow.getInstitutionCode());
        //部店コード
        l_rcFuOpChangeCancelPush.setAa_bcd(l_dataRow.getBranchCode());
        //顧客コード
        l_rcFuOpChangeCancelPush.setAa_accd(l_dataRow.getAccountCode());
        //オペレーターコード
        l_rcFuOpChangeCancelPush.setOptcd(l_dataRow.getTraderCode());
        //注文ID
        l_rcFuOpChangeCancelPush.setOdid(String.valueOf(l_dataRow.getOrderId())); 
        //銘柄コード
        l_rcFuOpChangeCancelPush.setPdc(l_dataRow.getProductCode());
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
        l_rcFuOpChangeCancelPush.setContp(l_strContp);
        //取引市場
        l_rcFuOpChangeCancelPush.setMcd(l_dataRow.getMarketCode());
        //指数種別（原資産銘柄コード）
        l_rcFuOpChangeCancelPush.setTpd(l_dataRow.getUnderlyingProductCode());
        //限月
        l_rcFuOpChangeCancelPush.setDm(l_dataRow.getMonthOfDelivery());
        //オプション商品区分
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpChangeCancelPush.setPdtp(WEB3IfoProductTypeDef.CALL_OPTIONS);
        }
        else if ( IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpChangeCancelPush.setPdtp(WEB3IfoProductTypeDef.PUT_OPTIONS);
        }
        //行使価格
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_dataRow.getDerivativeType()) ||
            IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_dataRow.getDerivativeType()))
        {
            l_rcFuOpChangeCancelPush.setSpr(WEB3StringTypeUtility.formatNumber(
                l_dataRow.getStrikePrice()));
        }
        //訂正後数量
        l_rcFuOpChangeCancelPush.setAcqt(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getModifiedQuantity()));
        //訂正後指値
        l_rcFuOpChangeCancelPush.setAclpr(WEB3StringTypeUtility.formatNumber(
            l_dataRow.getModifiedLimitPrice()));
        //訂正後執行条件
        l_rcFuOpChangeCancelPush.setAcectp(l_dataRow.getModifiedExecutionType());
        //訂正結果コード
        l_rcFuOpChangeCancelPush.setCrcd(l_dataRow.getModifiedResult());
        //訂正取消通知区分
        l_rcFuOpChangeCancelPush.setCcpdv(l_dataRow.getCanmodReceiptType());
        //処理区分
        l_rcFuOpChangeCancelPush.setTrstp(l_dataRow.getStatus());
        //作成日付
        l_rcFuOpChangeCancelPush.setCrdt(WEB3DateUtility.formatDate(
            l_dataRow.getCreatedTimestamp(), YYYYMMDD));
        //更新日付
        l_rcFuOpChangeCancelPush.setUpdt(WEB3DateUtility.formatDate(
            l_dataRow.getLastUpdatedTimestamp(), YYYYMMDD));
        //シリアル番号
        l_rcFuOpChangeCancelPush.setSerlnum(String.valueOf(l_dataRow.getSerlnum()));

        return l_rcFuOpChangeCancelPush;
    }


}
@
