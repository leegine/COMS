head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.47.27;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3RichPushPersistentDataManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントデータプッシュデータベースアクセス管理サービスインターフェース(WEB3RichPushPersistentDataManager.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate;

import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

/**
 * リッチクライアントデータプッシュデータベースアクセス管理サービスインターフェース
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3RichPushPersistentDataManager
    extends Service
{
    /**
     * リッチクライアントプッシュ株式注文受付データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityOrderAcceptRichPushData(long l_lngFromAccountId,
                                                 long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ株式現引現渡受付データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getSwapOrderAcceptRichPushData(long l_lngFromAccountId,
                                               long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ株式訂正取消通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityChangeCancelRichPushData(long l_lngFromAccountId,
                                                  long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ株式出来通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityContRichPushData(long l_lngFromAccountId,
                                          long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ株式失効通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getEquityLapseRichPushData(long l_lngFromAccountId,
                                           long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ先物OP注文受付通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoOrderAcceptRichPushData(long l_lngFromAccountId,
                                              long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ先物OP訂正取消通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoChangeCancelRichPushData(long l_lngFromAccountId,
                                               long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ先物OP出来通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoContRichPushData(long l_lngFromAccountId,
                                       long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;

    /**
     * リッチクライアントプッシュ先物OP失効通知データ読み込み
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     * @@throws DataNetworkException
     * @@throws DataQueryException
     * @@throws DataCallbackException
     * @@return List
     */
    public List getIfoLapseRichPushData(long l_lngFromAccountId,
                                        long l_lngToAccountId) throws
        DataNetworkException, DataQueryException,
        DataCallbackException;


	/**
	 * リッチクライアントデータプッシュ履歴VIEWの内容を取得する。
	 *
	 * @@throws DataNetworkException
	 * @@throws DataQueryException
	 * @@return Map
	 */
	public Map getRichPushHistoryTop() throws
		DataNetworkException, DataQueryException;
}
@
