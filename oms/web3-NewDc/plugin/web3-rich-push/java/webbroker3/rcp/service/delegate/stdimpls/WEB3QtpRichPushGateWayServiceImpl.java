head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.01.43.01;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5584d8aa1a54f65;
filename	WEB3QtpRichPushGateWayServiceImpl.java;

1.1
date	2011.03.17.01.32.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3QtpRichPushGateWayServiceImpl.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@QTPリッチクライアントプッシュゲートウェイサービス実装(WEB3QtpRichPushGateWayServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/24 孫(FLJ) 新規作成
                  : 2009/06/03 毛(FTL) 岩井対応
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.rcp.data.qtp.QtpRichPushEqChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushEqOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityContRow;
import webbroker3.rcp.data.qtp.QtpRichPushEquityLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoChangecancelRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoContRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoLapseRow;
import webbroker3.rcp.data.qtp.QtpRichPushIfoOrderacceptRow;
import webbroker3.rcp.data.qtp.QtpRichPushSwOrderacceptRow;
import webbroker3.rcp.define.WEB3QtpRichPushTextDef;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushEquityMarginOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpChangeCancelUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpContUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpLapseUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushFuOpOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushGateWayService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushSwapOrderAcceptUnitService;
import webbroker3.rcp.service.delegate.WEB3QtpRichPushUnitService;
import webbroker3.util.WEB3LogUtility;

/**
 * QTPリッチクライアントプッシュゲートウェイサービス実装
 * 
 * @@author 孫
 * @@version 1.0
 */
public class WEB3QtpRichPushGateWayServiceImpl implements WEB3QtpRichPushGateWayService
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3QtpRichPushGateWayServiceImpl.class);

    /** HTTP_OK_CODE */
    private static final String HTTP_OK_CODE = "200";

    private static final String ENCODE = "UTF-8";
    
    private static final String DEFAULT_TIMEOUT = "60000";

    /** リッチクライアントプッシュ接続先URL設定キー */
    private final static String STR_RICH_PUSH_URL_KEY = "qtp.rich.push.url.";

    /** 証券会社記述子 */
    private final static String STR_INSTITUTION_DESC = "qtp.rich.push.institution.desc.";

    private final static String STR_RICH_PUSH_MAX_LENGTH = "qtp.rich.push.max.length";

    private final static int DEFAULT_MAX_LENGTH = 4096;

    private final static String STR_RICH_PUSH_PROXY_SET_KEY = "qtp.rich.push.proxy.set";

    private final static String STR_RICH_PUSH_PROXY_HOST_KEY = "qtp.rich.push.proxy.host";

    private final static String STR_RICH_PUSH_PROXY_PORT_KEY = "qtp.rich.push.proxy.port";
    
    private final static String STR_RICH_PUSH_AUTH_USER_NAME = "qtp.rich.push.auth.user.name.";

    private final static String STR_RICH_PUSH_AUTH_USER_PASS = "qtp.rich.push.auth.user.pass.";
    
    private final static String STR_RICH_PUSH_CONN_TIMEOUT = "qtp.rich.push.conn.timeout";

    private final static String STR_RICH_PUSH_READ_TIMEOUT = "qtp.rich.push.read.timeout";

    /** プッシュサービス */
    protected final static HashMap pushServices = new HashMap();

    public WEB3QtpRichPushGateWayServiceImpl()
    {
        synchronized(pushServices)
        {
            if(pushServices.size() == 0)
            {
                // 現物信用注文受付通知
                pushServices.put(QtpRichPushEqOrderacceptRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginOrderAcceptUnitService.class));
                // 現物信用訂正取消通知
                pushServices.put(QtpRichPushEqChangecancelRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginChangeCancelUnitService.class));
                // 現物信用出来通知
                pushServices.put(QtpRichPushEquityContRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginContUnitService.class));
                // 現物信用失効通知
                pushServices.put(QtpRichPushEquityLapseRow.TYPE,
                    Services.getService(WEB3QtpRichPushEquityMarginLapseUnitService.class));
                // 信用現引現渡注文受付通知
                pushServices.put(QtpRichPushSwOrderacceptRow.TYPE,
                    Services.getService(WEB3QtpRichPushSwapOrderAcceptUnitService.class));
                // 先物ＯＰ注文受付通知
                pushServices.put(QtpRichPushIfoOrderacceptRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpOrderAcceptUnitService.class));
                // 先物ＯＰ訂正取消通知
                pushServices.put(QtpRichPushIfoChangecancelRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpChangeCancelUnitService.class));
                // 先物ＯＰ出来通知
                pushServices.put(QtpRichPushIfoContRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpContUnitService.class));
                // 先物ＯＰ失効通知
                pushServices.put(QtpRichPushIfoLapseRow.TYPE,
                    Services.getService(WEB3QtpRichPushFuOpLapseUnitService.class));

                // サービス出来ることのチェック
                Iterator l_iterator = pushServices.entrySet().iterator();
                while(l_iterator.hasNext())
                {
                    Entry l_entry = (Entry)l_iterator.next();
                    if(l_entry.getValue() == null)
                    {
                        throw new NullPointerException("Push service <" + l_entry.getKey() + "> is null!!");
                    }
                }
            }
        }
    }

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@param l_lstPushData
     *            List
     * @@return boolean
     */
    public boolean[] push(String l_strInstitutionCode, List l_lstPushData)
    {
        final String STR_METHOD_NAME = "push(String, List)";
        log.entering(STR_METHOD_NAME);

        if (l_lstPushData == null)
        {
            log.error("プッシュデータがない、送信失敗！");
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //証券会社記述子
        String l_institution = GtlUtils.getTradingSystem().getPreference(STR_INSTITUTION_DESC + l_strInstitutionCode);

        if (l_institution == null || "".equals(l_institution))
        {
            log.error("証券会社記述子を取得できない！Preferenceを追加ください。キー：" + STR_INSTITUTION_DESC + l_strInstitutionCode);
            log.exiting(STR_METHOD_NAME);
            return null;
        }

        //xmlヘッドの（バイトで）長さ
        int l_headLength = getByteLength(WEB3QtpRichPushObjectToXMLConverter.XML_HEAD + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_HEAD
                + l_institution + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_TAIL + WEB3QtpRichPushObjectToXMLConverter.XML_TAIL);

        //xmlメッセージの最大長さを取得、取得できない場合、デフォールトの長さを設定
        int l_maxLength = 0;
        try
        {
            l_maxLength = Integer.parseInt(GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_MAX_LENGTH));
        }
        catch (Exception e)
        {
            log.warn("Get max length from system preference error, will use default max length.", e);
        }
        if (l_maxLength == 0)
        {
            l_maxLength = DEFAULT_MAX_LENGTH;
        }

        //プッシュデータの配列を作成
        WEB3QtpExcutionInformUnit[] l_data = new WEB3QtpExcutionInformUnit[l_lstPushData.size()];
        //レスポンス結果マップ
        Map l_allMap = new HashMap();
        //xml文字列保存用バッファ@
        StringBuffer l_sb = new StringBuffer(l_maxLength);
        for (int i = 0; i < l_lstPushData.size(); i++)
        {
            Row l_row = (Row) l_lstPushData.get(i);
            //Rowデータオブジェクトを取得、データタイプにより、株式とIFOの約定通知プッシュデータを作成する
            WEB3QtpRichPushUnitService l_service = (WEB3QtpRichPushUnitService)pushServices.get(l_row.getRowType()); 
            // サービス未登録の場合、次のRowデータへ行う
            if(l_service == null)
            {
                log.error("unknown data, qtp push skipped: " + l_row.getRowType());
                continue;
            }

            // 取得したサービスより　@WEB3QtpExcutionInformUnitを生成する
            l_data[i] = l_service.createRichPushXmlMessage(l_row);

            //一つのプッシュデータのxml内容を作成
            String l_unitXml = WEB3QtpRichPushObjectToXMLConverter.toXML(l_data[i]);
            //プッシュxmlの合計の長さは最大長さを超える場合、前回までのxml内容を送信する
            if ((getByteLength(l_sb.toString()) + getByteLength(l_unitXml) + l_headLength) > l_maxLength)
            {
                //送信する
                String l_response = push(l_strInstitutionCode, getFullXmlString(l_institution, l_sb.toString()));
                //送信結果を解析
                Map l_map = WEB3QtpRichPushObjectToXMLConverter.parseResult(l_response);
                //全体の結果マップに追加
                l_allMap.putAll(l_map);
                //バッファ@をクリア
                l_sb.setLength(0);
            }
            //今回のxml分を追加
            l_sb.append(l_unitXml);

        }
        //ループ完了した後、残る内容があれば、送信する
        if (l_sb.length() > 0)
        {
            String l_response = push(l_strInstitutionCode, getFullXmlString(l_institution, l_sb.toString()));
            Map l_map = WEB3QtpRichPushObjectToXMLConverter.parseResult(l_response);
            l_allMap.putAll(l_map);
        }
        //実行結果保存用配列
        boolean[] l_ret = new boolean[l_data.length];
        ////プッシュデータの件数分をループ、該当するレスポンス結果を取得。
        for (int i = 0; i < l_data.length; i++)
        {
            String l_result = (String) l_allMap.get(l_data[i]);
            //結果はnullでない且つ"SUCCESS"の場合、正常終了とする。それ以外、処理エラー
            if (l_result != null)
            {
                if (WEB3QtpRichPushTextDef.RESPONSE_SUCCESS.equals(l_result.toUpperCase()))
                {
                    l_ret[i] = true;
                }
                else
                {
                    log.warn("Result is :" + l_result + ". For the data:" + l_data[i]);
                    l_ret[i] = false;
                }
            }
            else
            {
                l_ret[i] = false;
            }
        }

        log.exiting(STR_METHOD_NAME);
        return l_ret;
    }

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     * 
     * Urlなしの場合、Proxyを特定できない場合、HTTP接続エラー、HTTPレスポンス状態200以外の場合、nullを返す
     * 
     * 
     * @@param l_strInstitutionCode
     *            String
     * @@param l_strXmlData
     *            String
     * @@return boolean
     */
    private String push(String l_strInstitutionCode, String l_xmlString)
    {

        final String STR_METHOD_NAME = "push(String,String)";
        log.entering(STR_METHOD_NAME);

        String l_result = null;
        //証券会社コードから送信Urlを取得
        String l_strPushUrl = getPushUrl(l_strInstitutionCode);
        //Urlなしの場合、直接nullを返す
        if (l_strPushUrl == null)
        {
            log.error("プッシュURLが特定できないため、送信失敗！");
            log.exiting(STR_METHOD_NAME);

            return l_result;
        }

        //PROXY 対応
        String l_strPushProxySet = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_PROXY_SET_KEY);
        log.debug("rich client push proxy set >>>" + l_strPushProxySet);
        String l_strPushProxyHost = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_PROXY_HOST_KEY);
        log.debug("rich client push proxy host >>>" + l_strPushProxyHost);
        String l_strPushProxyPort = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_PROXY_PORT_KEY);
        log.debug("rich client push proxy port >>>" + l_strPushProxyPort);
        String l_userName = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_AUTH_USER_NAME+l_strInstitutionCode);
        log.debug("rich client push authorrization username >>>" + l_userName);
        String l_password = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_AUTH_USER_PASS+l_strInstitutionCode);
        log.debug("rich client push authorrization password >>>" + l_password);
        
        String l_cTimeout = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_CONN_TIMEOUT);
        if(l_cTimeout == null)
        {
            l_cTimeout = DEFAULT_TIMEOUT;
        }
        log.debug("rich client connnect timeout >>>" + l_cTimeout);
        String l_rTimeout = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_READ_TIMEOUT);
        if(l_rTimeout == null)
        {
            l_rTimeout = DEFAULT_TIMEOUT;
        }
        log.debug("rich client read timeout >>>" + l_rTimeout);        
        
        synchronized (this)
        {
            if ("true".equals(l_strPushProxySet))
            {
                if (l_strPushProxyHost == null || l_strPushProxyPort == null)
                {
                    log.error("プッシュPORXYサーバが特定できないため、送信失敗！");
                    log.error("rich client push proxy host >>>" + l_strPushProxyHost);
                    log.error("rich client push proxy port >>>" + l_strPushProxyPort);
                    log.exiting(STR_METHOD_NAME);
                    return l_result;
                }

                Properties sys = System.getProperties();
                sys.put("https.proxySet", "true");
                sys.put("https.proxyHost", l_strPushProxyHost);
                sys.put("https.proxyPort", l_strPushProxyPort);
                sys.put("proxySet", "true");
                sys.put("proxyHost", l_strPushProxyHost);
                sys.put("proxyPort", l_strPushProxyPort);
                System.setProperties(sys);

            }
            else
            {
                Properties sys = System.getProperties();
                sys.put("https.proxySet", "false");
                sys.put("https.proxyHost", "");
                sys.put("https.proxyPort", "");
                sys.put("proxySet", "false");
                sys.put("proxyHost", "");
                sys.put("proxyPort", "");

            }
            
            Properties sys = System.getProperties();
            sys.put("sun.net.client.defaultConnectTimeout", l_cTimeout);
            sys.put("sun.net.client.defaultReadTimeout", l_rTimeout);
            
            HttpURLConnection con = null;
            //PrintWriter l_out = null;
            OutputStream l_out = null;
            BufferedReader l_is = null;
            try
            {
                if (log.ison())
                {
                    log.debug("Send Xml to QTP:" + l_xmlString);
                }

                // URLクラスのインスタンスを生成
                ////下記ソースはテスト用のみ
                URL l_postURL;
                if("HTTP".equalsIgnoreCase(GtlUtils.getTradingSystem().getPreference("qtp.rich.push.protocol.type")))
                {
                    l_postURL = new URL(l_strPushUrl);
                }else{
                    l_postURL = new URL(null, l_strPushUrl, new sun.net.www.protocol.https.Handler());
                }

                // 接続します
                con = (HttpURLConnection) l_postURL.openConnection();
                // 出力を行うように設定します
                con.setDoOutput(true);
                // 属性を設定する
                con.setRequestMethod("POST");
                con.setRequestProperty("Content-Type", "text/xml;charset=" + ENCODE);
                con.setRequestProperty("Content-Length", String.valueOf(getByteLength(l_xmlString)));
                con.setRequestProperty("Connection", "Keep - Alive");
                
                if(l_userName != null || l_password != null)
                {
                    String l_basic = l_userName + ":" + l_password;
                    String encoding = new sun.misc.BASE64Encoder().encode(l_basic.getBytes());
                    con.setRequestProperty("Authorization", "Basic " + encoding);    
                }

                // 接続
                con.connect();

                // 出力ストリームを取得
                l_out = con.getOutputStream();
                // 出力ストリームにxml書き込み
                l_out.write(l_xmlString.getBytes(ENCODE));
                l_out.flush();
                l_out.close();
                l_out = null;

                // 送信結果を判定
                String l_response_code = con.getHeaderField(0);
                log.debug("response code:" + l_response_code);
                // httpコード状態が正常である場合
                if (l_response_code != null && l_response_code.indexOf(HTTP_OK_CODE) >= 0)
                {
                    l_is = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODE));

                    StringBuffer l_sb = new StringBuffer();

                    String l_line = null;
                    while ((l_line = l_is.readLine()) != null)
                    {
                        l_sb.append(l_line);
                    }

                    l_result = l_sb.toString();

                }
                else   //エラーの場合、警告を出力
                {   
                    log.warn("response code error:" + l_response_code);
                    l_is = new BufferedReader(new InputStreamReader(con.getInputStream(), ENCODE));

                    StringBuffer l_sb = new StringBuffer();

                    String l_line = null;
                    while ((l_line = l_is.readLine()) != null)
                    {
                        l_sb.append(l_line);
                    }
                    log.warn("response content:"+l_sb.toString());
                }

            }
            catch (Exception e)
            {
                log.error(e.getMessage(), e);

                if (con != null)
                {
                    if (e instanceof IOException)
                    {
                        try
                        {
                            InputStream is = con.getErrorStream();
                            if (is != null)
                            {
                                int ret = 0;
                                // read the response body
                                byte[] buf = new byte[1024];
                                while ((ret = is.read(buf)) > 0)
                                {
                                }
                                // close the errorstream
                                is.close();
                            }
                        }
                        catch (IOException ex)
                        {
                            log.error(ex.getMessage(), ex);
                        }
                    }
                    con.disconnect();
                }
            }
            finally
            {
                if (l_out != null)
                {
                    try
                    {
						l_out.close();
					}
                    catch (IOException e) 
                    {
                        log.error(e.getMessage(), e);
					}
                }
                if (l_is != null)
                {
                    try
                    {
                        l_is.close();
                    }
                    catch (IOException e1)
                    {
                        log.error(e1.getMessage(), e1);
                    }
                }
                //---以下のjavadocにより、disconnect実行しない
                //各 HttpURLConnection インスタンスを使用して単一の要求を作成しますが、
                //HTTP サーバへのネットワーク接続は他のインスタンスと透過的に共有されることがあります。
                //要求後、HttpURLConnection の InputStream または OutputStream で
                // close()
                //メソッドを呼び出すと、このインスタンスに関連したネットワークリソースが解放されますが、
                //共有持続接続には影響ありません。disconnect() メソッドを呼び出すと、
                //持続接続がその時アイドル状態であればソケットが閉じることがあります。

                //connectメソッド説明：
                //この URL が参照するリソースへの通信リンクを確立します (通信リンクが確立されていない場合)。
                //connect メソッドを呼び出したときに、接続がすでに確立されていれば (connected フィールドの値が
                //true であれば)、メソッド呼び出しは無視されます。
//                if (con != null)
//                {
//                    con.disconnect();
//                }
            }
        }

        if (log.ison())
        {
            log.debug("Xml Response from QTP:" + l_result);
        }
        log.exiting(STR_METHOD_NAME);
        return l_result;
    }

    /**
     * 通知送信単位xmlデータの外に通知送信xmlのroot要素を追加。
     * 
     * （一つの送信メッセージに複数の通知送信単位が含まれています。）
     * 
     * @@param l_institution
     * @@param l_unitXml
     * @@return
     */
    private String getFullXmlString(String l_institution, String l_unitXml)
    {
        return WEB3QtpRichPushObjectToXMLConverter.XML_HEAD + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_HEAD + l_institution
                + WEB3QtpRichPushObjectToXMLConverter.XML_DESC_TAIL + l_unitXml + WEB3QtpRichPushObjectToXMLConverter.XML_TAIL;
    }

    /**
     * 文字列のバイト数を計算。UTF-8で計算、エラーがある場合、デフォールトencode
     * 
     * @@param l_str
     * @@return
     */
    private int getByteLength(String l_str)
    {
        int l_res = 0;
        try
        {
            l_res = l_str.getBytes(ENCODE).length;
        }
        catch (UnsupportedEncodingException e)
        {
            log.warn("UTF-8 not supported. Use default encode", e);
            l_res = l_str.getBytes().length;
        }
        if (log.ison())
        {
            log.debug("Byte length:" + l_res);
        }
        return l_res;
    }

    /**
     * getPushUrl 送信urlを取得
     * 
     * @@param String
     *            l_strInstitutionCode String
     * @@return String
     */
    private String getPushUrl(String l_strInstitutionCode)
    {

        final String STR_METHOD_NAME = "getPushUrl()";
        log.entering(STR_METHOD_NAME);

        log.debug("プッシュURLを特定するキー=" + STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);
        String l_strPushUrl = GtlUtils.getTradingSystem().getPreference(STR_RICH_PUSH_URL_KEY + l_strInstitutionCode);

        log.debug("rich.push.url=" + l_strPushUrl);
        log.exiting(STR_METHOD_NAME);
        return l_strPushUrl;

    }

}@


1.1
log
@*** empty log message ***
@
text
@a383 2
                    //proxyの再設定
                    weblogic.net.http.HttpClient.resetProperties();
@

