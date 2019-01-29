head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.17.06.52.10;	author liu-lei;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8d04d81af9a1914;
filename	WEB3RichPushGateWayServiceImpl.java;

1.1
date	2011.03.17.01.32.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushGateWayServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュゲートウェイサービス実装(WEB3RichPushGateWayServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.io.*;
import java.net.*;
import java.util.*;

import com.fitechlabs.dbind.*;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import webbroker3.rcp.data.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * リッチクライアントプッシュゲートウェイサービス実装
 * @@author 劉
 * @@version 1.0
 */
public class WEB3RichPushGateWayServiceImpl
    implements WEB3RichPushGateWayService
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushGateWayServiceImpl.class);

    /** HTTP_OK_CODE */
    private static final String HTTP_OK_CODE =
        "200";

    /** リッチクライアントプッシュ接続先URL設定キー */
    private final static String STR_RICH_PUSH_URL_KEY =
        "rich.push.url.";

    private final static String STR_RICH_PUSH_PROXY_SET_KEY = "rich.push.proxy.set";

    private final static String STR_RICH_PUSH_PROXY_HOST_KEY = "rich.push.proxy.host";

    private final static String STR_RICH_PUSH_PROXY_PORT_KEY = "rich.push.proxy.port";

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushData List
     * @@return boolean
     */
    public boolean push(String l_strInstitutionCode, List l_lstPushData)
    {
        StringBuffer l_strbuff = new StringBuffer();
        l_strbuff.append(WEB3RichPushObjectToXMLConverter.XML_HEAD);
        l_strbuff.append(WEB3RichPushObjectToXMLConverter.XML_MAIN_TAG_NAME_S);

        ArrayList l_lstTmp = new ArrayList();
        for (int i = 0; i < l_lstPushData.size(); i++)
        {
            l_lstTmp.clear();
            Row l_row = (Row) l_lstPushData.get(i);
            l_lstTmp.add(l_row);
            if (l_row instanceof RichPushEquityOrderAcceptRow)
            {
                WEB3RichPushEquityMarginOrderAcceptUnitService l_service =
                    (WEB3RichPushEquityMarginOrderAcceptUnitService) Services.getService(
                    WEB3RichPushEquityMarginOrderAcceptUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushSwapOrderAcceptRow)
            {
                WEB3RichPushSwapOrderAcceptUnitService l_service =
                    (WEB3RichPushSwapOrderAcceptUnitService) Services.getService(
                    WEB3RichPushSwapOrderAcceptUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushEquityChangeCancelRow)
            {
                WEB3RichPushEquityMarginChangeCancelUnitService l_service =
                    (WEB3RichPushEquityMarginChangeCancelUnitService) Services.getService(
                    WEB3RichPushEquityMarginChangeCancelUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushEquityContRow)
            {
                WEB3RichPushEquityMarginContUnitService l_service =
                    (WEB3RichPushEquityMarginContUnitService) Services.getService(
                    WEB3RichPushEquityMarginContUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushEquityLapseRow)
            {
                WEB3RichPushEquityMarginLapseUnitService l_service =
                    (WEB3RichPushEquityMarginLapseUnitService) Services.getService(
                    WEB3RichPushEquityMarginLapseUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoOrderAcceptRow)
            {
                WEB3RichPushFuOpOrderAcceptUnitService l_service =
                    (WEB3RichPushFuOpOrderAcceptUnitService) Services.getService(
                    WEB3RichPushFuOpOrderAcceptUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoChangeCancelRow)
            {

                WEB3RichPushFuOpChangeCancelUnitService l_service =
                    (WEB3RichPushFuOpChangeCancelUnitService) Services.getService(
                    WEB3RichPushFuOpChangeCancelUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoContRow)
            {
                WEB3RichPushFuOpContUnitService l_service =
                    (WEB3RichPushFuOpContUnitService) Services.getService(
                    WEB3RichPushFuOpContUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }
            else if (l_row instanceof RichPushIfoLapseRow)
            {
                WEB3RichPushFuOpLapseUnitService l_service =
                    (WEB3RichPushFuOpLapseUnitService) Services.getService(
                    WEB3RichPushFuOpLapseUnitService.class);
                String l_strXml = l_service.createRichPushXmlMessage(l_lstTmp
                    );
                l_strbuff.append(l_strXml);
            }

        }
        l_strbuff.append(WEB3RichPushObjectToXMLConverter.XML_MAIN_TAG_NAME_E);
        boolean l_ret = push(l_strInstitutionCode, l_strbuff.toString());
        return l_ret;
    }

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     *
     * @@param l_strInstitutionCode String
     * @@param l_strXmlData String
     * @@return boolean
     */
    private boolean push(String l_strInstitutionCode, String l_strXmlData)
    {

        final String STR_METHOD_NAME = "push()";
        log.entering(STR_METHOD_NAME);

        log.debug("rich client push data >>>" + l_strInstitutionCode + ":" + l_strXmlData);
        String l_strPushUrl = getPushUrl(l_strInstitutionCode);

        if (l_strPushUrl == null)
        {
            log.error("プッシュURLが特定できないため、送信失敗！");
            log.exiting(STR_METHOD_NAME);
            return false;
        }

        //PROXY 対応
        String l_strPushProxySet = GtlUtils.getTradingSystem().getPreference(
            STR_RICH_PUSH_PROXY_SET_KEY);
        log.debug("rich client push proxy set >>>" + l_strPushProxySet);
        if ("true".equals(l_strPushProxySet))
        {
            String l_strPushProxyHost = GtlUtils.getTradingSystem().getPreference(
                STR_RICH_PUSH_PROXY_HOST_KEY);
            log.debug("rich client push proxy host >>>" + l_strPushProxyHost);
            String l_strPushProxyPort = GtlUtils.getTradingSystem().getPreference(
                STR_RICH_PUSH_PROXY_PORT_KEY);
            log.debug("rich client push proxy port >>>" + l_strPushProxyPort);
            if (l_strPushProxyHost == null || l_strPushProxyPort == null)
            {
                log.error("プッシュPORXYサーバが特定できないため、送信失敗！");
                log.error("rich client push proxy host >>>" + l_strPushProxyHost);
                log.error("rich client push proxy port >>>" + l_strPushProxyPort);
                log.exiting(STR_METHOD_NAME);
                return false;
            }

            Properties sys = System.getProperties();
            sys.put("http.proxyHost", l_strPushProxyHost);
            sys.put("http.proxyPort", l_strPushProxyPort);
        }

        try
        {
            // URLクラスのインスタンスを生成
            URL l_postURL =
                new URL(l_strPushUrl);

            // 接続します
            URLConnection con = l_postURL.openConnection();
            // 出力を行うように設定します
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "text/XML;charset=utf-8");

            // 出力ストリームを取得
            PrintWriter out = new PrintWriter(con.getOutputStream());
            // 出力ストリームにxml書き込み
            out.print(l_strXmlData);
            out.close();

            // 送信結果を判定
            String l_response_code = con.getHeaderField(0);
            log.debug("response code:" + l_response_code);
            if (l_response_code != null && l_response_code.indexOf(HTTP_OK_CODE) >= 0)
            {
                log.exiting(STR_METHOD_NAME);
                return true;
            }

        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
        }
        log.exiting(STR_METHOD_NAME);
        return false;
    }

    /**
     * getPushUrl
     *
     * @@param String l_strInstitutionCode String
     * @@return String
     */
    private String getPushUrl(String l_strInstitutionCode)
    {

        final String STR_METHOD_NAME = "getPushUrl()";
        log.entering(STR_METHOD_NAME);

        log.debug("プッシュURLを特定するキー=" + STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);
        String l_strPushUrl = GtlUtils.getTradingSystem().getPreference(
            STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);

        if (l_strPushUrl == null)
        {
            log.error("プッシュURLが存在しません！");
            log.exiting(STR_METHOD_NAME);
            return null;
        }
        log.debug("rich.push.url=" + l_strPushUrl);
        log.exiting(STR_METHOD_NAME);
        return l_strPushUrl;

    }

}
@


1.1
log
@*** empty log message ***
@
text
@a19 1
//import weblogic.net.http.HttpClient;
d203 2
a204 16
            sys.put("proxySet", "true");
            sys.put("proxyHost", l_strPushProxyHost);
            sys.put("proxyPort", l_strPushProxyPort);
            System.setProperties(sys);

            log.debug("＜設定前＞");
            log.debug("proxyHost: " + HttpClient.proxyHost);
            log.debug("proxyPortt: " + HttpClient.proxyPort);

            //proxyの再設定
            HttpClient.resetProperties();

            log.debug("＜設定後＞");
            log.debug("proxyHost: " + HttpClient.proxyHost);
            log.debug("proxyPortt: " + HttpClient.proxyPort);

@

