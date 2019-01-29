head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.47.39;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3QtpRichPushPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QTPリッチクライアントデータプッシュデータベースアクセス管理サービスインターフェース(WEB3QtpRichPushPersistentDataManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/10 孫(FLJ) 新規作成
                   2009/06/02 毛(FTL) 岩井対応
 */

package webbroker3.rcp.service.delegate;

import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * QTPリッチクライアントデータプッシュデータベースアクセス管理サービスインターフェース
 * 
 * @@author 孫
 * @@version 1.0
 */
public interface WEB3QtpRichPushPersistentDataManager
        extends Service
{
    /**
     * QTP株式注文受付リッチクライアントプッシュデータ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityOrderAcceptRichPushData(long l_lngFromAccountId, long l_lngToAccountId)
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ株式現引現渡受付データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpSwapOrderAcceptRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ株式訂正取消通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityChangeCancelRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ株式出来通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityContRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ株式失効通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpEquityLapseRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ先物OP注文受付通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoOrderAcceptRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ先物OP訂正取消通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoChangeCancelRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ先物OP出来通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoContRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントプッシュ先物OP失効通知データ読み込み<br/>
     *
     * @@param l_lngFromAccountId
     *            下限顧客ＩＤ
     * @@param l_lngToAccountId
     *            上限顧客ＩＤ
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getQtpIfoLapseRichPushData( long l_lngFromAccountId, long l_lngToAccountId )
        throws DataNetworkException, DataQueryException, DataCallbackException;

    /**
     * QTPリッチクライアントデータプッシュ履歴VIEWの内容を取得する。
     * 
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@return Map
     */
    public Map getQtpRichPushHistoryTop()
        throws DataNetworkException, DataQueryException;

}
@
