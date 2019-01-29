head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerMgrAccessor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : （サーバーサイト管理）サーバーAccessor(Web3ServerAccessor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.affinity;

import java.util.*;

import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.kernel.message.*;

/**
 * （サーバーサイト管理）サーバーAccessor
 * **/
public final class WEB3ServerMgrAccessor
    implements ServerAccessor
{

    /**
     * webbroker3カスタマイズAffinityAccessor
     */
    private Hashtable web3AffinityAccessors;

    /**
     * コンストラクタ
     * @@param affinityAccessors Hashtable
     */
    public WEB3ServerMgrAccessor(Hashtable affinityAccessors)
    {

        this.web3AffinityAccessors = affinityAccessors;

    }

    public void setConnectTimeOut(int timeout)
    {
        AffinityAccessor[] affinityAccessors = getAffinityAccessors();
        for (int j = 0; j < affinityAccessors.length; j++)
        {
            AffinityAccessor affinityAccessor = affinityAccessors[j];
            ServerAccessor accessors[] = affinityAccessor.getServerAccessors();
            for (int i = 0; i < accessors.length; i++)
            {
                ServerAccessor accessor = accessors[i];
                if (accessor instanceof SocketPoolAccessor)
                {
                    SocketPoolAccessor socketPoolAccessor = (SocketPoolAccessor)
                        accessor;
                    socketPoolAccessor.setTimeOut(timeout);
                }
            }
        }
    }

    /**
     * webbroker3カスタマイズAffinityAccessorを取得する
     *
     * @@return AffinityAccessor[]
     */
    public AffinityAccessor[] getAffinityAccessors()
    {

        AffinityAccessor[] affinityAccessors = new AffinityAccessor[web3AffinityAccessors.
            size()];
        if (web3AffinityAccessors == null)
        {
            return null;
        }
        Iterator iterator = web3AffinityAccessors.values().iterator();
        int i = 0;
        while (iterator.hasNext())
        {
            WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor) iterator.next();
            affinityAccessors[i] = affinityAccessor;
            i++;
        }
        return affinityAccessors;

    }

    /**
     * RequestオブジェクトとしてメッセージをxTradeサーバへサブミットし、結果としてResponseオブジェクトを返します。
     *
     * @@param xmlRequest XMLリクエスト電文
     * @@throws CommunicationException サーバとの通信に失敗した場合
     * @@throws ServerException 通信には成功したが、リクエストに対応するアプリケーション固有のハンドラが
     *   何らかの理由で処理を完了できなかった場合
     * @@return リクエストに対するレスポンスオブジェクト
     */
    public Response doRequestO(String xmlRequest) throws CommunicationException,
        ServerException
    {

        for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
        {
            String tagName = WEB3ServerTryOrderProviderCallback.TAGNAMES[i];
            if (hasTagName(xmlRequest, tagName))
            {
                WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor)
                    web3AffinityAccessors.get(tagName);
                return affinityAccessor.doRequestO(xmlRequest);
            }
        }
        WEB3AffinityAccessor defaultAffinityAccessor = getDefaultAffinityAccessor();
        return defaultAffinityAccessor.doRequestO(xmlRequest);
    }

    /**
     * RequestオブジェクトとしてメッセージをxTradeサーバへサブミットし、結果としてResponseオブジェクトを返します。
     *
     * @@param request リクエストオブジェクトインスタンス
     * @@throws CommunicationException サーバとの通信に失敗した場合
     * @@throws ServerException 通信には成功したが、リクエストに対応するアプリケーション固有のハンドラが
     *   何らかの理由で処理を完了できなかった場合
     * @@return リクエストに対するレスポンスオブジェクト
     */
    public Response doRequest(Request request) throws CommunicationException,
        ServerException
    {
        for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
        {
            String tagName = WEB3ServerTryOrderProviderCallback.TAGNAMES[i];
            if (hasTagName(request, tagName))
            {
                WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor)
                    web3AffinityAccessors.get(tagName);
                return affinityAccessor.doRequest(request);
            }
        }
        WEB3AffinityAccessor defaultAffinityAccessor = getDefaultAffinityAccessor();
        return defaultAffinityAccessor.doRequest(request);
    }

    /**
     * RequestオブジェクトとしてメッセージをxTradeサーバへサブミットし、結果としてResponseオブジェクトを返します。
     *
     * @@param xmlRequest XMLリクエスト電文
     * @@throws CommunicationException サーバとの通信に失敗した場合
     * @@throws ServerException 通信には成功したが、リクエストに対応するアプリケーション固有のハンドラが
     *   何らかの理由で処理を完了できなかった場合
     * @@return XMLレスポンス電文
     */
    public String doRequest(String xmlRequest) throws CommunicationException,
        ServerException
    {
        for (int i = 0; i < WEB3ServerTryOrderProviderCallback.TAGNAMES.length; i++)
        {
            String tagName = WEB3ServerTryOrderProviderCallback.TAGNAMES[i];
            if (hasTagName(xmlRequest, tagName))
            {
                WEB3AffinityAccessor affinityAccessor = (WEB3AffinityAccessor)
                    web3AffinityAccessors.get(tagName);
                return affinityAccessor.doRequest(xmlRequest);
            }
        }
        WEB3AffinityAccessor defaultAffinityAccessor = getDefaultAffinityAccessor();
        return defaultAffinityAccessor.doRequest(xmlRequest);
    }

    /**
     * Drfault WEB3AffinityAccessorを取得する
     *
     * @@return WEB3AffinityAccessor
     */
    protected WEB3AffinityAccessor getDefaultAffinityAccessor()
    {
        WEB3AffinityAccessor defaultAffinityAccessor = (WEB3AffinityAccessor)
            web3AffinityAccessors.get(WEB3ServerTryOrderProviderCallback.ACCOUNT_ID);
        return defaultAffinityAccessor;
    }

    /**
     * TagNameをチェックする
     *
     * @@param request Request
     * @@param tagname String
     * @@return boolean
     */
    private boolean hasTagName(Request request, String tagname)
    {

        try
        {
            Object tagValue = request.getClass().getField(tagname).get(request);
            if (tagValue != null)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception ex)
        {
            return false;
        }

    }

    /**
     * TagNameをチェックする
     *
     * @@param xmlRequest String
     * @@param tagname String
     * @@return boolean
     */
    private boolean hasTagName(String xmlRequest, String tagname)
    {
        String taghead = "<" + tagname + ">";
        String tagtail = "</" + tagname + ">";
        int i = xmlRequest.indexOf(taghead);
        if (i < 0)
        {
            return false;
        }
        i += taghead.length();
        int j = xmlRequest.indexOf(tagtail, i);
        if (j < 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
@
