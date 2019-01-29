head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerTryOrderProviderCallbackStdImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :サーバトライオーダ取得するコールバックインタフェースの標準実装(Web3ServerTryOrderProviderCallbackStdImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
                    2005/03/17 劉(FLJ) Accountレンジ快速検索方法@追加
 */

package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.comm.client.*;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.*;
import webbroker3.system.tune.affinity.util.*;

/**
 * サーバトライオーダ取得するコールバックインタフェースの標準実装
 * **/
public class WEB3ServerTryOrderProviderCallbackStdImpl
    implements
    WEB3ServerTryOrderProviderCallback
{
    /**
     * アカウントIDトライオーダマップ情報
     */
    private WEB3AcctIdTryOrderMapInfo acctIdTryOrderMapInfos[];

    /**
     * アカウントIDレンジトライオーダマップ情報
     */
    private WEB3AcctIdRangeTryOrderMapInfo acctIdRangeTryOrderMapInfos[];

    /**
     * 注文識別コードトライオーダマップ情報
     */
    private WEB3ReqNumTryOrderMapInfo reqNumTryOrderMapInfos[];

    /**
     * コンストラクタ
     */
    public WEB3ServerTryOrderProviderCallbackStdImpl()
    {
    }

    /**
     * 特定のtagNameとTagValueに対してトライオーダを提供するコールバックメソッドです。
     *
     * @@param tagName tagValue取得のために利用されるRequest電文のField名（XML電文の場合、tagName）
     * @@param tagValue String
     * @@return ServerAccessorのトライオーダ
     */
    public int[] getServerTryOrder(String tagName, String tagValue)
    {
        System.out.println("getServerTryOrder(String tagName, String tagValue)" + tagName +
                           "," + tagValue);
        if (SESSION_ID.equals(tagName))
        {
            long accountId = deriveAccountIdFromSessionId(tagValue);
            return getServerAccIdTryOrder(accountId);
        }
        else if (ACCOUNT_ID.equals(tagName))
        {
            if (tagValue == null)
            {
                return null;
            }
            long accountId = Long.parseLong(tagValue);
            return getServerAccIdTryOrder(accountId);
        }
        else if (ORDER_REQUEST_NUMBER.equals(tagName))
        {
            return getServerTryOrderFromRequestNumber(tagValue);
        }
        else if (ACCOUNT_ID_RANGE.equals(tagName))
        {
            return getServerAccIdRangeTryOrder(getServerAccIdRange(tagValue, true),
                                               getServerAccIdRange(tagValue, false));
        }
        else
        {
            return null;
        }

    }

    /**
     * セッションIDからアカウントIDを取得するオーバライドされたメソッドです。
     *
     * @@param tagValue セッションID
     * @@return アカウントID
     */
    private long deriveAccountIdFromSessionId(String sessionId)
    {
        return PasswordTools.decodeAccountId(sessionId);
    }

    /**
     * 特定のアカウントIDに対してトライオーダを提供するコールバックメソッドです。
     *
     * @@param accountId アカウントID
     * @@return トライオーダ
     */
    private int[] getServerAccIdTryOrder(long accountId)
    {
        if (accountId == 0L)
        {
            return null;
        }
        else
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = binarySearch(acctIdTryOrderMapInfos,
                accountId);
            if (mapInfo == null)
            {
                return null;
            }
            else
            {
                return mapInfo.getAppServerTryOrder();
            }
        }
    }

    /**
     * 特定のアカウントIDレンジに対してトライオーダを提供するコールバックメソッドです。
     *
     * @@param accountIdStart アカウントレンジ開始ID
     * @@param accountIdEnd アカウントレンジ終了ID
     * @@return トライオーダ
     */
    private int[] getServerAccIdRangeTryOrder(long accountIdStart, long accountIdEnd)
    {
        if (accountIdStart == 0L || accountIdEnd == 0L)
        {
            return null;
        }
        else
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = StdRangeSearch(
                acctIdRangeTryOrderMapInfos,
                accountIdStart, accountIdEnd);
            if (mapInfo == null)
            {
                return null;
            }
            else
            {
                return mapInfo.getAppServerTryOrder();
            }
        }
    }

    private long getServerAccIdRange(String AccIdRange, boolean isStart)
    {
        if (isStart)
        {
            return Long.parseLong(AccIdRange.substring(0, AccIdRange.indexOf(",")));
        }
        else
        {
            return Long.parseLong(AccIdRange.substring(AccIdRange.indexOf(",") + 1,
                AccIdRange.length()));
        }
    }

    /**
     * 注文識別頭2桁に対してトライオーダを提供するコールバックメソッドです。
     *
     * @@param head2OrderRequestNumber 注文識別頭2桁
     * @@return トライオーダ
     */
    private int[] getServerTryOrderFromRequestNumber(String head2OrderRequestNumber)
    {
        if (head2OrderRequestNumber == null || head2OrderRequestNumber.length() < 2)
        {
            return null;
        }
        else
        {
            for (int i = 0; i < reqNumTryOrderMapInfos.length; i++)
            {
                WEB3ReqNumTryOrderMapInfo mapInfo =
                    reqNumTryOrderMapInfos[i];
                if (head2OrderRequestNumber.equals(mapInfo.getHead2OfOrderRequestNumber()))
                {
                    return mapInfo.getAppServerTryOrder();
                }
            }
            return null;
        }
    }

    /**
     * アカウントIDトライオーダマップ情報を設定する。
     *
     * @@param tryOrderMapInfos アカウントIDトライオーダマップ情報
     */
    public void setAccIdTryOrderMapInfos(WEB3AcctIdTryOrderMapInfo[]
                                         tryOrderMapInfos)
    {
        this.acctIdTryOrderMapInfos = tryOrderMapInfos;
    }

    /**
     * アカウントIDレンジトライオーダマップ情報を設定する。
     *
     * @@param tryOrderMapInfos アカウントIDトライオーダマップ情報
     */
    public void setAccIdRangeTryOrderMapInfos(WEB3AcctIdRangeTryOrderMapInfo[]
                                              tryOrderMapInfos)
    {
        this.acctIdRangeTryOrderMapInfos = tryOrderMapInfos;
    }

    /**
     * 注文識別コードトライオーダマップ情報を設定する。
     *
     * @@param tryOrderMapInfos 注文識別コードトライオーダマップ情報
     */
    public void setReqNumTryOrderMapInfos(
        WEB3ReqNumTryOrderMapInfo[]
        tryOrderMapInfos)
    {
        this.reqNumTryOrderMapInfos =
            tryOrderMapInfos;
    }

    private WEB3AcctIdTryOrderMapInfo StdSearch(WEB3AcctIdTryOrderMapInfo
                                                acctIdTryOrderMapInfos[],
                                                long accountId)
    {
        if (acctIdTryOrderMapInfos.length == 0)
        {
            ClientLogging.debug("acctIdTryOrderMapInfos.length == 0");
            return null;
        }

        for (int i = 0; i < acctIdTryOrderMapInfos.length; i++)
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = acctIdTryOrderMapInfos[i];
            if (accountId >= mapInfo.getAccountIdStart() &&
                accountId < mapInfo.getAccountIdEnd())
            {
                ClientLogging.debug(
                    "accountId=" + accountId + "," +
                    ">= mapInfo.getAccountIdStart()= " + mapInfo.getAccountIdStart() +
                    "," +
                    "< mapInfo.getAccountIdEnd()= " + mapInfo.getAccountIdEnd() + "," +
                    "  mapInfo.getAppServerTryOrder()= " +
                    WEB3SystemObjectPrint.print(mapInfo.getAppServerTryOrder())
                    );

                return mapInfo;
            }
        }
        return null;
    }

    private WEB3AcctIdTryOrderMapInfo StdRangeSearch(WEB3AcctIdRangeTryOrderMapInfo
        acctIdTryOrderMapInfos[],
        long accountIdStart, long accountIdEnd)
    {
        if (acctIdTryOrderMapInfos.length == 0)
        {
            ClientLogging.debug("acctIdTryOrderMapInfos.length == 0");
            return null;
        }

        for (int i = 0; i < acctIdTryOrderMapInfos.length; i++)
        {
            WEB3AcctIdTryOrderMapInfo mapInfo = acctIdTryOrderMapInfos[i];
            if (accountIdStart >= mapInfo.getAccountIdStart() &&
                accountIdEnd <= mapInfo.getAccountIdEnd())
            {
                ClientLogging.debug(
                    "accountIdStart=" + accountIdStart + "," +
                    "accountIdEnd=" + accountIdEnd + "," +
                    "accountIdStart>= mapInfo.getAccountIdStart()= " +
                    mapInfo.getAccountIdStart() +
                    "," +
                    "accountIdEnd< mapInfo.getAccountIdEnd()= " + mapInfo.getAccountIdEnd() +
                    "," +
                    "  mapInfo.getAppServerTryOrder()= " +
                    WEB3SystemObjectPrint.print(mapInfo.getAppServerTryOrder())
                    );

                return mapInfo;
            }
        }
        return null;
    }

    private WEB3AcctIdTryOrderMapInfo binarySearch(WEB3AcctIdTryOrderMapInfo
        acctIdTryOrderMapInfos[],
        long accountId)
    {

        if (acctIdTryOrderMapInfos.length == 0)
        {
            ClientLogging.debug("acctIdTryOrderMapInfos.length == 0");
            return null;
        }

        int low = 0;
        int high = acctIdTryOrderMapInfos.length - 1;

        while (low <= high)
        {
            int mid = (low + high) >> 1;
            WEB3AcctIdTryOrderMapInfo mapInfo = acctIdTryOrderMapInfos[mid];
            if (accountId >= mapInfo.getAccountIdStart() &&
                accountId < mapInfo.getAccountIdEnd())
            {
                ClientLogging.debug(
                    "accountId=" + accountId + "," +
                    ">= mapInfo.getAccountIdStart()= " + mapInfo.getAccountIdStart() +
                    "," +
                    "< mapInfo.getAccountIdEnd()= " + mapInfo.getAccountIdEnd() + "," +
                    "  mapInfo.getAppServerTryOrder()= " +
                    WEB3SystemObjectPrint.print(mapInfo.getAppServerTryOrder())
                    );

                return mapInfo;
            }
            else if (accountId >= mapInfo.getAccountIdEnd())
            {
                low = mid + 1;
            }
            else
            {
                high = mid;
            }
        }
        return null; //not found

    }

}
@
