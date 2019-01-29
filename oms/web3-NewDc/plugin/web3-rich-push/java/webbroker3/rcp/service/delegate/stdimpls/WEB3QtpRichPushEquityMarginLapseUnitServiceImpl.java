head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.31.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushEquityMarginLapseUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : QTPリッチクライアントプッシュ現物信用失効通知サービス実装(WEB3QtpRichPushEquityMarginLapseUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2009/06/02 毛(FTL) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import webbroker3.rcp.WEB3QtpRichPushUtil;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushPersistentDataManager;
import webbroker3.util.WEB3LogUtility;

/**
 * QTPリッチクライアントデータプッシュ株式失効通知サービス実装<br/>
 * 
 * @@author 毛(FTL)
 * @@version 1.0
 */
public class WEB3QtpRichPushEquityMarginLapseUnitServiceImpl
    extends WEB3QtpRichPushUnitServiceImpl
    implements WEB3QtpRichPushEquityMarginLapseUnitService
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3QtpRichPushEquityMarginLapseUnitServiceImpl.class);

    private final WEB3QtpRichPushUtil web3QtpRichPushUtil = new WEB3QtpRichPushUtil();

    /**
     * QTPリッチクライアントデータプッシュ<br/>
     * 現物信用失効通知データ<br/>
     * 
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     */
    public void push( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataQueryException, DataNetworkException
    {
        final String STR_METHOD_NAME = "push( long l_lngFromAccountId, long l_lngToAccountId )";
        log.entering(STR_METHOD_NAME);

        // 1.1. QTPリッチクライアントプッシュデータ読み込み
        WEB3QtpRichPushPersistentDataManager l_perService =
            (WEB3QtpRichPushPersistentDataManager) Services.getService(WEB3QtpRichPushPersistentDataManager.class);
        List l_equityMarginLapseList =
            l_perService.getQtpEquityLapseRichPushData(l_lngFromAccountId, l_lngToAccountId);

        // 1.2 QTPリッチクライアントデータプッシュ
        if (l_equityMarginLapseList.size() > 0)
        {
            // コンテクストへ保存
            saveToContext(l_equityMarginLapseList);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * QTPリッチクライアントプッシュデータをXML変換用オブジェクトへ変換<br/>
     * 現物信用失効通知データ<br/>
     * 
     * @@param l_dataRows
     *            ローデータ
     * @@return WEB3QtpExcutionInformUnit
     */
    public WEB3QtpExcutionInformUnit createRichPushXmlMessage( Row l_dataRow )
    {
        final String STR_METHOD_NAME = "createRichPushXmlMessage()";
        log.entering(STR_METHOD_NAME);

        log.debug("RichPushRow:" + l_dataRow);

        WEB3QtpExcutionInformUnit l_pushObj =
            web3QtpRichPushUtil.createWEB3QtpExcutionInformUnit(l_dataRow);

        if (l_pushObj == null)
        {
            // 未対応データ
            log.error("unsupported RowType:" + (l_dataRow == null ? "null" : l_dataRow.getRowType().toString()));
        }
        else
        {
            log.debug("Push Object created:" + l_pushObj);
        }

        boolean l_isValid = isCommonValid(l_pushObj);
        log.exiting(STR_METHOD_NAME);
        return (l_isValid)?l_pushObj:null;
    }

}
@
