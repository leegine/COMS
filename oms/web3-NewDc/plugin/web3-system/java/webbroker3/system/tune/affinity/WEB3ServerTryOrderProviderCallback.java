head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ServerTryOrderProviderCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :サーバトライオーダ取得するコールバックインタフェース(Web3ServerTryOrderProviderCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */

package webbroker3.system.tune.affinity;

import java.io.*;

/**
 * サーバトライオーダ取得するコールバックインタフェース
 * **/
public interface WEB3ServerTryOrderProviderCallback
    extends Serializable
{

    /**
     * セッションIDのTagName
     */
    final public static String SESSION_ID = "session_id";

    /**
     * アカウントIDのTagName
     */
    final public static String ACCOUNT_ID = "account_id";

    /**
     * 識別コード頭２桁のTagName
     */
    final public static String ORDER_REQUEST_NUMBER = "head2_order_request_number";

    /**
     * 顧客IDレンジのTagName
     */
    final public static String ACCOUNT_ID_RANGE = "account_id_range";

    /**
     * 対応済みTagName
     */
    final public static String[] TAGNAMES =
        {
        SESSION_ID, ACCOUNT_ID, ORDER_REQUEST_NUMBER, ACCOUNT_ID_RANGE};

    /**
     * 特定のtagNameとTagValueに対してトライオーダを提供するコールバックメソッドです。
     *
     * @@param tagName tagValue取得のために利用されるRequest電文のField名（XML電文の場合、tagName）
     * @@param tagValue String
     * @@return ServerAccessorのトライオーダ
     */
    public abstract int[] getServerTryOrder(String tagName, String tagValue);

}
@
