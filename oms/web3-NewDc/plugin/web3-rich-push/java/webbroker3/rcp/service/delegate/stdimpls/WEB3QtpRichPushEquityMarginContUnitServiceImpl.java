head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushEquityMarginContUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QTPリッチクライアントデータプッシュ株式出来通知サービス実装(WEB3QtpRichPushEquityMarginContUnitServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/10 孫(FLJ) 新規作成
                 : 2009/06/03 毛(FTL) 岩井対応
*/

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.rcp.WEB3QtpRichPushUtil;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.define.WEB3QtpRichPushQtpCommandParamDef;
import webbroker3.rcp.define.WEB3QtpRichPushTextDef;
import webbroker3.rcp.define.WEB3RichPushDataTypeDef;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * QTPリッチクライアントデータプッシュ株式出来通知サービス実装
 * 
 * @@author 孫(FLJ)
 * @@version 1.0
 */
public class WEB3QtpRichPushEquityMarginContUnitServiceImpl
    extends WEB3QtpRichPushUnitServiceImpl
    implements WEB3QtpRichPushEquityMarginContUnitService
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushEquityMarginContUnitServiceImpl.class);

    private final WEB3QtpRichPushUtil web3QtpRichPushUtil = new WEB3QtpRichPushUtil();

    /**
     * QTPリッチクライアントデータプッシュ<br/>
     * 現物信用出来通知データ<br/>
     * 
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "push(long l_lngFromAccountId, long l_lngToAccountId)";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTPリッチクライアントプッシュデータ読み込み
        WEB3QtpRichPushPersistentDataManager l_perService = (WEB3QtpRichPushPersistentDataManager) Services
                .getService(WEB3QtpRichPushPersistentDataManager.class);
        List l_equityContList = l_perService.getQtpEquityContRichPushData(l_lngFromAccountId, l_lngToAccountId);

        // 1.2 QTPリッチクライアントデータプッシュ
        if (l_equityContList.size() > 0)
        {
            //コンテクストへ保存
            saveToContext(l_equityContList);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * QTPリッチクライアントプッシュデータをXML変換用オブジェクトへ変換<br/>
     * 現物信用出来通知データ<br/>
     * 
     * @@param l_dataRows
     *            ローデータ
     * @@return WEB3QtpExcutionInformUnit
     */
    public WEB3QtpExcutionInformUnit createRichPushXmlMessage(Row l_dataRow)
    {
        final String STR_METHOD_NAME = "createRichPushXmlMessage()";
        log.entering(STR_METHOD_NAME);

        WEB3QtpExcutionInformUnit l_pushObj = null;

        log.debug("RichPushRow:" + l_dataRow);
        //株式出来通知の場合
        if (l_dataRow instanceof QtpRichPushEquityContRow)
        {
            QtpRichPushEquityContRow l_contRow = (QtpRichPushEquityContRow) l_dataRow;
            //株式出来通知
            if (WEB3RichPushDataTypeDef.EQTYPE_CONT.equals(l_contRow.getType()))
            {
                l_pushObj =
                    web3QtpRichPushUtil.createWEB3QtpExcutionInformUnit(l_dataRow);

                if (l_pushObj != null)
                {
                    l_pushObj.setUrl(getUrlFromOrderType(l_contRow.getOrderType()));
                    l_pushObj.setTitle(WEB3QtpRichPushTextDef.TITLE_EXECUTION);

                    String l_productCode = WEB3QtpRichPushQtpCommandParamDef.QUOTE_PREFIX + l_contRow.getProductCode() + getQtpMarketCodeFromMarket(l_contRow.getMarketCode());
                    l_pushObj.setUrlParams(l_productCode);
                    l_pushObj.setQcodeParams(l_productCode + WEB3QtpRichPushQtpCommandParamDef.EQTYPE_MENU);
                    l_pushObj.setQcodeName(l_contRow.getProductName() + "(" + l_contRow.getProductCode() + getQtpMarketNameFromMarket(l_contRow.getMarketCode()) + ")");
                    l_pushObj.setOrderTypeName(getOrderTypeNameFromOrderType(l_contRow.getOrderType()) + WEB3QtpRichPushTextDef.PRICE_TITLE);
                    String l_prc = formatNumber(l_contRow.getExecPrice());
                    if("0".equals(l_prc) && (OrderTypeEnum.IntValues.SWAP_MARGIN_LONG == l_contRow.getOrderType().intValue() || OrderTypeEnum.IntValues.SWAP_MARGIN_SHORT == l_contRow.getOrderType().intValue()))
                    {
                        l_prc = WEB3QtpRichPushTextDef.PRICE_ZERO;
                    }
                    l_pushObj.setPrc( l_prc + WEB3QtpRichPushTextDef.PRICE_UNIT);
                    l_pushObj.setQuantityText(WEB3QtpRichPushTextDef.QUANTITY_TITLE);
                    l_pushObj.setVol(formatNumber(l_contRow.getExecQuantity()) + WEB3QtpRichPushTextDef.QUANTITY_UNIT_EQ);
                    l_pushObj.setLnkText(WEB3QtpRichPushTextDef.LINK_TITLE);
                }
            }
            //未対応株式出来通知データ
            else
            {
                log.error("unsupported WEB3RichPushDataType:" + l_contRow.getType());
            }
        }

        if (l_pushObj == null)
        {
            // 未対応データ
            log.error("unsupported RowType:" + (l_dataRow == null ? "null" : l_dataRow.getRowType().toString()));
        }
        else
        {
            log.debug("Push Object created:" + l_pushObj);
        }

        boolean l_isValid = isAllValid(l_pushObj);
        log.exiting(STR_METHOD_NAME);
        return (l_isValid)?l_pushObj:null;
    }

}
@
