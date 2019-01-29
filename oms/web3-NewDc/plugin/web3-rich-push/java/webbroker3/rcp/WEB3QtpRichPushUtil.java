head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QTPリッチクライアントプッシュツール: (WEB3QtpRichPushUtil.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/06/04 毛(FTL) 新規作成
 */

package webbroker3.rcp;

import java.sql.Timestamp;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.define.WEB3IfoProductTypeDef;
import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpExcutionInformUnit;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * このクラスのインスタンスが複数スレッド使っています<br/>
 * final以外の属性の追加要注意<br/>
 * 
 * @@author 毛(FTL)
 * 
 */
public class WEB3QtpRichPushUtil
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushUtil.class);

    /** 日付フォーマットの定数定義:yyyyMMdd */
    private static final String DATE_FORMAT = "yyyyMMdd";

    /** 日時フォーマットの定数定義：yyyyMMddHHmmss */
    private static final String DATETIME_FORMAT = "yyyyMMddHHmmss";

    private static final String QTP_RICH_PUSH_SEND_FTAG = "qtp.rich.push.send.ftag.";

    /**
     * ローデータより　@一件約定通知データ保存　@オブジェクトの生成<br/>
     * 正常場合は共通項目を設定し戻す、それ以外の場合は「NULL」で戻す<br/>
     * 共通項目:<br/>
     * <ul>
     * <li>setSrlnum (通知電文通番)</li>
     * <li>setSid (送信先ＩＤ)</li>
     * <li>setTm (電文生成時刻)</li>
     * <li>setTlgNtcNmsgFtagFatt (フリー属性[岩井対応])</li>
     * </ul>
     * 
     * @@param l_dataRow
     *            ローデータ
     * @@return
     */
    public WEB3QtpExcutionInformUnit createWEB3QtpExcutionInformUnit( Row l_row )
    {
        final String STR_METHOD_NAME = "createWEB3QtpExcutionInformUnit( Row l_row )";
        log.entering(STR_METHOD_NAME);

        if (l_row == null)
        {
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        WEB3QtpExcutionInformUnit l_pushObj = null;
        StringBuffer l_tlgNtcNmsgFtagFatt = new StringBuffer(1024);

        // 現物信用注文受付通知
        if (l_row instanceof QtpRichPushEqOrderacceptRow)
        {
            QtpRichPushEqOrderacceptRow l_dataRow = (QtpRichPushEqOrderacceptRow) l_row;
            // 株式注文受付（信用現引現渡以外） 00
            if (WEB3RichPushDataTypeDef.EQUITY_ORDER_ACCEPT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // 注文受付結果
                    .append("odacr=")
                    .append((null == l_dataRow.getAcceptStatus()) ? "" : l_dataRow.getAcceptStatus())
                    .append(",")
                    // エラーコード
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")
                    // 発注経路区分
                    .append("odrdv=")
                    .append((null == l_dataRow.getSubmitOrderRouteDiv()) ? "" : l_dataRow.getSubmitOrderRouteDiv())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応現物信用受付通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 現物信用訂正取消通知
        else if (l_row instanceof QtpRichPushEqChangecancelRow)
        {
            QtpRichPushEqChangecancelRow l_dataRow = (QtpRichPushEqChangecancelRow) l_row;
            // 株式訂正取消通知 02
            if (WEB3RichPushDataTypeDef.EQTYPE_CHANGE_CANCEL.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // 訂正後数量
                    .append("acqt=")
                    .append((l_dataRow.getModifiedQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedQuantity()))
                    .append(",")
                    // 訂正後指値
                    .append("aclpr=")
                    .append((l_dataRow.getModifiedLimitPriceIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedLimitPrice()))
                    .append(",")
                    // 訂正後執行条件(SONAR)
                    .append("acectps=")
                    .append((null == l_dataRow.getModifiedExecutionType()) ? "" : l_dataRow.getModifiedExecutionType())
                    .append(",")
                    // 訂正後値段条件(SONAR)
                    .append("acprctps=")
                    .append((null == l_dataRow.getModifiedPriceConditionType()) ? "" : l_dataRow.getModifiedPriceConditionType())
                    .append(",")
                    // 訂正結果コード
                    .append("crcd=")
                    .append((null == l_dataRow.getModifiedResult()) ? "" : l_dataRow.getModifiedResult())
                    .append(",")
                    // 訂正取消通知区分
                    .append("ccpdv=")
                    .append((null == l_dataRow.getCanmodReceiptType()) ? "" : l_dataRow.getCanmodReceiptType())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応現物信用訂正取消通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 現物信用出来通知
        else if (l_row instanceof QtpRichPushEquityContRow)
        {
            QtpRichPushEquityContRow l_dataRow = (QtpRichPushEquityContRow) l_row;
            // 株式出来通知 03
            if (WEB3RichPushDataTypeDef.EQTYPE_CONT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // 約定数量
                    .append("eqt=")
                    .append((l_dataRow.getExecQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecQuantity()))
                    .append(",")
                    // 約定単価
                    .append("epr=")
                    .append((l_dataRow.getExecPriceIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecPrice()))
                    .append(",")
                    // 約定日時
                    .append("exet=")
                    .append((null == l_dataRow.getExecTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getExecTimestamp(), DATETIME_FORMAT))
                    .append(",")
                    // 出来通知区分
                    .append("cpdv=")
                    .append((null == l_dataRow.getDealedType()) ? "" : l_dataRow.getDealedType())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応現物信用出来通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 現物信用失効通知
        else if (l_row instanceof QtpRichPushEquityLapseRow)
        {
            QtpRichPushEquityLapseRow l_dataRow = (QtpRichPushEquityLapseRow) l_row;
            // 株式失効通知 04
            if (WEB3RichPushDataTypeDef.EQTYPE_LAPSE.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // 約定数量
                    .append("eqt=")
                    .append((l_dataRow.getExecutedQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecutedQuantity()))
                    .append(",")
                    // 失効理由コード
                    .append("lrcd=")
                    .append((null == l_dataRow.getReasonCode()) ? "" : l_dataRow.getReasonCode())
                    .append(",")
                    // 失効通知区分
                    .append("lpdv=")
                    .append((null == l_dataRow.getCloseNotifyType()) ? "" : l_dataRow.getCloseNotifyType())
                    .append(",")
                    // エラーコード
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応現物信用失効通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 信用現引現渡注文受付通知
        else if (l_row instanceof QtpRichPushSwOrderacceptRow)
        {
            QtpRichPushSwOrderacceptRow l_dataRow = (QtpRichPushSwOrderacceptRow) l_row;
            // 株式信用現引現渡受付 01
            if (WEB3RichPushDataTypeDef.SWAP_ORDER_ACCEPT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")

                    // 注文受付結果
                    .append("odacr=")
                    .append((null == l_dataRow.getAcceptStatus()) ? "" : l_dataRow.getAcceptStatus())
                    .append(",")
                    // エラーコード
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応信用現引現渡注文受付通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 先物ＯＰ注文受付通知
        else if (l_row instanceof QtpRichPushIfoOrderacceptRow)
        {
            QtpRichPushIfoOrderacceptRow l_dataRow = (QtpRichPushIfoOrderacceptRow) l_row;
            // 先物OP注文受付通知 10
            if (WEB3RichPushDataTypeDef.IFO_ORDER_ACCEPT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 建区分
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // 取引市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // 指数種別（原資産銘柄コード）
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // 限月
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // オプション商品区分
                    .append("pdtp=")
                    .append(getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // 行使価格
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // 注文受付結果
                    .append("odacr=")
                    .append((null == l_dataRow.getAcceptStatus()) ? "" : l_dataRow.getAcceptStatus())
                    .append(",")
                    // エラーコード
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応先物ＯＰ注文受付通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 先物ＯＰ訂正取消通知
        else if (l_row instanceof QtpRichPushIfoChangecancelRow)
        {
            QtpRichPushIfoChangecancelRow l_dataRow = (QtpRichPushIfoChangecancelRow) l_row;
            // 先物OP訂正取消通知 12
            if (WEB3RichPushDataTypeDef.IFO_CHANGE_CANCEL.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 建区分
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // 取引市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // 指数種別（原資産銘柄コード）
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // 限月
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // オプション商品区分
                    .append("pdtp=")
                    .append(getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // 行使価格
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // 訂正後数量
                    .append("acqt=")
                    .append(WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedQuantity()))
                    .append(",")
                    // 訂正後指値
                    .append("aclpr=")
                    .append(WEB3StringTypeUtility.formatNumber(l_dataRow.getModifiedLimitPrice()))
                    .append(",")
                    // 訂正後執行条件
                    .append("acectp=")
                    .append((null == l_dataRow.getModifiedExecutionType()) ? "" : l_dataRow.getModifiedExecutionType())
                    .append(",")
                    // 訂正結果コード
                    .append("crcd=")
                    .append((null == l_dataRow.getModifiedResult()) ? "" : l_dataRow.getModifiedResult())
                    .append(",")
                    // 訂正取消通知区分
                    .append("ccpdv=")
                    .append((null == l_dataRow.getCanmodReceiptType()) ? "" : l_dataRow.getCanmodReceiptType())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応先物ＯＰ訂正取消通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 先物ＯＰ出来通知
        else if (l_row instanceof QtpRichPushIfoContRow)
        {
            QtpRichPushIfoContRow l_dataRow = (QtpRichPushIfoContRow) l_row;
            // 先物OP出来通知 13
            if (WEB3RichPushDataTypeDef.IFO_CONT.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 建区分
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // 取引市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // 指数種別（原資産銘柄コード）
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // 限月
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // オプション商品区分
                    .append("pdtp=")
                    .append(getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // 行使価格
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // 約定数量
                    .append("eqt=")
                    .append((l_dataRow.getExecQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecQuantity()))
                    .append(",")
                    // 約定単価
                    .append("epr=")
                    .append((l_dataRow.getExecPriceIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecPrice()))
                    .append(",")
                    // 約定日時
                    .append("exet=")
                    .append((null == l_dataRow.getExecTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getExecTimestamp(), DATETIME_FORMAT))
                    .append(",")
                    // 出来通知区分
                    .append("cpdv=")
                    .append((null == l_dataRow.getDealedType()) ? "" : l_dataRow.getDealedType())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応先物ＯＰ出来通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // 先物ＯＰ失効通知
        else if (l_row instanceof QtpRichPushIfoLapseRow)
        {
            QtpRichPushIfoLapseRow l_dataRow = (QtpRichPushIfoLapseRow) l_row;
            // 先物OP失効通知 14
            if (WEB3RichPushDataTypeDef.IFO_LAPSE.equals(l_dataRow.getType()))
            {
                l_pushObj = new WEB3QtpExcutionInformUnit();
                l_pushObj.setSrlnum(String.valueOf(l_dataRow.getSerlnum()));
                l_pushObj.setSid(l_dataRow.getSid());
                l_pushObj.setTm(WEB3DateUtility.formatDate(new Timestamp(System.currentTimeMillis()), DATETIME_FORMAT));
                if(needFtag(l_dataRow.getInstitutionCode()))
                {
                l_tlgNtcNmsgFtagFatt
                    // データコード
                    .append("dcd=")
                    .append((null == l_dataRow.getRequestCode()) ? "" : l_dataRow.getRequestCode())
                    .append(",")
                    // 会社コード
                    .append("aa_icd=")
                    .append((null == l_dataRow.getInstitutionCode()) ? "" : l_dataRow.getInstitutionCode())
                    .append(",")
                    // 部店コード
                    .append("aa_bcd=")
                    .append((null == l_dataRow.getBranchCode()) ? "" : l_dataRow.getBranchCode())
                    .append(",")
                    // 顧客コード
                    .append("aa_accd=")
                    .append((null == l_dataRow.getAccountCode()) ? "" : l_dataRow.getAccountCode())
                    .append(",")
                    // オペレーターコード
                    .append("optcd=")
                    .append((null == l_dataRow.getTraderCode()) ? "" : l_dataRow.getTraderCode())
                    .append(",")
                    // 注文ＩＤ
                    .append("odid=")
                    .append(String.valueOf(l_dataRow.getOrderId()))
                    .append(",")
                    // 銘柄コード
                    .append("pdc=")
                    .append((null == l_dataRow.getProductCode()) ? "" : l_dataRow.getProductCode())
                    .append(",")
                    // 建区分
                    .append("contp=")
                    .append(getContp(l_dataRow.getOrderType()))
                    .append(",")
                    // 取引市場コード
                    .append("mcd=")
                    .append((null == l_dataRow.getMarketCode()) ? "" : l_dataRow.getMarketCode())
                    .append(",")
                    // 指数種別（原資産銘柄コード）
                    .append("tpd=")
                    .append((null == l_dataRow.getUnderlyingProductCode()) ? "" : l_dataRow.getUnderlyingProductCode())
                    .append(",")
                    // 限月
                    .append("dm=")
                    .append((null == l_dataRow.getMonthOfDelivery()) ? "" : l_dataRow.getMonthOfDelivery())
                    .append(",")
                    // オプション商品区分
                    .append("pdtp=")
                    .append((null == l_dataRow.getDerivativeType()) ? "" : getPdtp(l_dataRow.getDerivativeType()))
                    .append(",")
                    // 行使価格
                    .append("spr=")
                    .append(getSpr(l_dataRow.getDerivativeType(), l_dataRow.getStrikePrice()))
                    .append(",")

                    // 約定数量
                    .append("eqt=")
                    .append((l_dataRow.getExecutedQuantityIsNull()) ? "" : WEB3StringTypeUtility.formatNumber(l_dataRow.getExecutedQuantity()))
                    .append(",")
                    // 失効理由コード
                    .append("lrcd=")
                    .append((null == l_dataRow.getReasonCode()) ? "" : l_dataRow.getReasonCode())
                    .append(",")
                    // 失効通知区分
                    .append("lpdv=")
                    .append((null == l_dataRow.getCloseNotifyType()) ? "" : l_dataRow.getCloseNotifyType())
                    .append(",")
                    // エラーコード
                    .append("errcd=")
                    .append((null == l_dataRow.getErrorMessage()) ? "" : l_dataRow.getErrorMessage())
                    .append(",")

                    // 処理区分
                    .append("trstp=")
                    .append((null == l_dataRow.getStatus()) ? "" : l_dataRow.getStatus())
                    .append(",")
                    // 作成日付
                    .append("crdt=")
                    .append((null == l_dataRow.getCreatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getCreatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // 更新日付
                    .append("updt=")
                    .append((null == l_dataRow.getLastUpdatedTimestamp()) ? "" : WEB3DateUtility.formatDate(l_dataRow.getLastUpdatedTimestamp(), DATE_FORMAT))
                    .append(",")
                    // シリアル番号
                    .append("serlnum=")
                    .append(String.valueOf(l_dataRow.getSerlnum()));
                }
                l_pushObj.setTlgNtcNmsgFtagFatt(l_tlgNtcNmsgFtagFatt.toString());
            }
            // 未対応先物ＯＰ失効通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_dataRow.getType());
            }
        }
        // プッシュデータ作成できない場合、次のRowデータを処理
        else
        {
            log.error("unknown data, qtp push skip." + l_row);
        }

        log.exiting(STR_METHOD_NAME);
        return l_pushObj;

    }

    /**
     * 建区分を取得する<br/>
     * 
     * @@param l_orderType
     *            注文種別
     * @@return
     */
    private String getContp( OrderTypeEnum l_orderType )
    {
        final String STR_METHOD_NAME = "getContp(OrderTypeEnum l_orderType)";
        log.entering(STR_METHOD_NAME);

        String l_strContp = "";
        if (OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE.equals(l_orderType)
                || OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE.equals(l_orderType))
        // 605：OP新規買建注文　@　@　@　@　@　@、601：先物新規買建注文
        // 608：OP買建売返済注文（売返済）、604：先物買建売返済注文（売返済）
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_BUY;
        }
        else if (OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN.equals(l_orderType) || OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE.equals(l_orderType)
                || OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE.equals(l_orderType))
        // 606：OP新規売建注文　@　@　@　@　@　@、602：先物新規売建注文
        // 607：OP売建買返済注文（買返済）、603：先物売建買返済注文（買返済）
        {
            l_strContp = WEB3IfoContractTypeDef.OPEN_SELL;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strContp;
    }

    /**
     * オプション商品区分を取得する<br/>
     * 
     * @@param l_derivativeType
     *            先物オプション商品
     * @@return
     */
    private String getPdtp( IfoDerivativeTypeEnum l_derivativeType )
    {
        final String STR_METHOD_NAME = "getPdtp( IfoDerivativeTypeEnum l_derivativeType )";
        log.entering(STR_METHOD_NAME);

        String l_strPdtp = "";
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_derivativeType))
        {
            l_strPdtp = WEB3IfoProductTypeDef.CALL_OPTIONS;
        }
        else if (IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_derivativeType))
        {
            l_strPdtp = WEB3IfoProductTypeDef.PUT_OPTIONS;
        }

        log.exiting(STR_METHOD_NAME);
        return l_strPdtp;
    }

    /**
     * 行使価格を取得する<br/>
     * 
     * @@param l_derivativeType
     *            先物オプション商品
     * @@param l_strikePrice
     * @@return
     */
    private String getSpr( IfoDerivativeTypeEnum l_derivativeType, double l_strikePrice )
    {
        final String STR_METHOD_NAME = "getSpr( IfoDerivativeTypeEnum l_derivativeType, double l_strikePrice )";
        log.entering(STR_METHOD_NAME);

        String l_strSpr = "";
        if (IfoDerivativeTypeEnum.CALL_OPTIONS.equals(l_derivativeType) || IfoDerivativeTypeEnum.PUT_OPTIONS.equals(l_derivativeType))
        {
            l_strSpr = WEB3StringTypeUtility.formatNumber(l_strikePrice);
        }

        log.exiting(STR_METHOD_NAME);
        return l_strSpr;
    }

    /**
     * フリータグ生成チェック
     * 
     * @@param l_institutionCode
     * @@return フリータグが必要の場合のみ「TRUE」
     */
    private boolean needFtag(String l_institutionCode)
    {
        if("TRUE".equalsIgnoreCase(
            GtlUtils.getTradingSystem().getPreference(QTP_RICH_PUSH_SEND_FTAG + l_institutionCode)
            ))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
@
