head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.33;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AcctIdTryOrderMapInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :アカウントIDトライオーダマップ情報クラス(Web3AcctIdTryOrderMapInfo.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */

package webbroker3.system.tune.affinity;

import java.io.*;

/**
 * アカウントIDトライオーダマップ情報
 * **/
public class WEB3AcctIdTryOrderMapInfo
    implements Serializable
{
    /**
     * アカウントレンジ開始ID
     */
    private long accountIdStart;

    /**
     * アカウントレンジ終了ID
     */
    private long accountIdEnd;

    /**
     * APPサーバトライオーダ
     */
    private int[] appServerTryOrder;

    public WEB3AcctIdTryOrderMapInfo()
    {

    }

    /**
     * TryOrderMapInfo
     *
     * @@param accountIdStart long
     * @@param accountIdEnd long
     * @@param appServerTryOrder int[]
     */
    public WEB3AcctIdTryOrderMapInfo(long accountIdStart, long accountIdEnd,
                                     int[] appServerTryOrder
                                     )
    {
        this.accountIdStart = accountIdStart;
        this.accountIdEnd = accountIdEnd;
        this.appServerTryOrder = appServerTryOrder;

    }

    /**
     * アカウントレンジ開始IDを設定する。
     *
     * @@param accountIdStart アカウントレンジ開始ID
     */
    public void setAccountIdStart(long accountIdStart)
    {
        this.accountIdStart = accountIdStart;
    }

    /**
     * アカウントレンジ開始IDを取得する
     *
     * @@return アカウントレンジ開始ID
     */
    public long getAccountIdStart()
    {
        return accountIdStart;
    }

    /**
     * アカウントレンジ終了IDを取得する
     *
     * @@return アカウントレンジ終了ID
     */
    public long getAccountIdEnd()
    {
        return accountIdEnd;
    }

    /**
     * アカウントレンジ設定IDを設定する。
     *
     * @@param accountIdEnd アカウントレンジ設定ID
     */
    public void setAccountIdEnd(long accountIdEnd)
    {
        this.accountIdEnd = accountIdEnd;
    }

    /**
     * APPサーバトライオーダを取得する。
     *
     * @@return サーバトライオーダ
     */
    public int[] getAppServerTryOrder()
    {
        return appServerTryOrder;
    }

    /**
     * APPサーバトライオーダを設定する。
     *
     * @@param appServerTryOrder サーバトライオーダ
     */
    public void setAppServerTryOrder(int[] appServerTryOrder)
    {
        this.appServerTryOrder = appServerTryOrder;
    }

}
@
