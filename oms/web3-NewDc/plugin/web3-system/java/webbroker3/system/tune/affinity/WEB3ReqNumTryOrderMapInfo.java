head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.23.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3ReqNumTryOrderMapInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :注文識別コードトライオーダマップ情報クラス(Web3ReqNumTryOrderMapInfo.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.affinity;

import java.io.*;

/**
 * 注文識別コードトライオーダマップ情報
 * **/
public class WEB3ReqNumTryOrderMapInfo
    implements Serializable
{
    private String head2OfOrderRequestNumber;
    private int[] appServerTryOrder;

    public WEB3ReqNumTryOrderMapInfo()
    {

    }

    /**
     * 注文識別コードトライオーダマップ情報
     *
     * @@param head2OfOrderRequestNumber 注文識別コード頭２桁
     * @@param appServerTryOrder APPサーバトライオーダ
     */
    public WEB3ReqNumTryOrderMapInfo(String head2OfOrderRequestNumber,
                                     int[] appServerTryOrder
                                     )
    {
        this.head2OfOrderRequestNumber = head2OfOrderRequestNumber;
        this.appServerTryOrder = appServerTryOrder;

    }

    /**
     * 注文識別コード頭２桁を設定する。
     *
     * @@param head2OfOrderRequestNumber 注文識別コード頭２桁
     */
    public void setHead2OfOrderRequestNumber(String head2OfOrderRequestNumber)
    {
        this.head2OfOrderRequestNumber = head2OfOrderRequestNumber;
    }

    /**
     * 注文識別コード頭２桁を取得する。
     *
     * @@return 注文識別コード頭２桁
     */
    public String getHead2OfOrderRequestNumber()
    {
        return head2OfOrderRequestNumber;
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
