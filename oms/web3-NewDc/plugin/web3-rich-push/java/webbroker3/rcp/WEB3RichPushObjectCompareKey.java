head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushObjectCompareKey.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュオブジェクトソート用キークラス(WEB3RichPushObjectCompareKey.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/01 劉(FLJ) 新規作成
 */

package webbroker3.rcp;

import java.sql.*;

/**
 * <p>
 * リッチクライアントプッシュオブジェクトソート用キークラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */
public class WEB3RichPushObjectCompareKey
    implements Comparable
{

    /**
     * 口座ID
     */
    private long account_id;

    /**
     * ラスト更新日時
     */
    private Timestamp last_updated_timestamp;

    /**
     * プッシュデータタイプ
     */
    private String type;

    /**
     * プッシュデータROWID
     */
    private String tpk;

    /**
     * プッシュデータROWIDを取得する
     *
     * @@return Timestamp
     */
    public String getTpk()
    {
        return tpk;
    }

    /**
     * プッシュデータタイプを取得する
     *
     * @@return Timestamp
     */
    public String getType()
    {
        return type;
    }

    /**
     * ラスト更新日時を取得する
     *
     * @@return Timestamp
     */
    public Timestamp getLastUpdatedTimestamp()
    {
        return last_updated_timestamp;
    }

    /**
     * 口座IDを取得する
     *
     * @@return long
     */
    public long getAccountId()
    {
        return account_id;
    }

    /**
     * このオブジェクトと指定されたオブジェクトの順序を比較します。
     * 順序 ：（口座ID,ラスト更新日時,プッシュデータタイプ） 昇順
     * @@param o Object
     * @@return int
     */
    public int compareTo(Object o)
    {
        if (! (o instanceof WEB3RichPushObjectCompareKey))
        {
            throw new RuntimeException("illegal parameter:" + o);

        }
        WEB3RichPushObjectCompareKey n = (WEB3RichPushObjectCompareKey) o;
        int accountIdCmp = (account_id < n.getAccountId() ? -1 :
                            (account_id == n.getAccountId() ? 0 : 1));
        if (accountIdCmp != 0)
        {
            return accountIdCmp;
        }
        else
        {
            int timeCmp = last_updated_timestamp.compareTo(n.getLastUpdatedTimestamp());
            if (timeCmp != 0)
            {
                return timeCmp;
            }
            else
            {
                int typeCmp = type.compareTo(n.getType());
                return typeCmp != 0 ? typeCmp :
                    tpk.compareTo(n.getTpk());
            }
        }
    }

    /**
     * constructor
     */
    public WEB3RichPushObjectCompareKey(long l_lngAccountId,
                                        Timestamp l_lastUpdatedTimestamp,
                                        String l_strType,
                                        String l_strTpk)
    {
        this.account_id = l_lngAccountId;
        this.last_updated_timestamp = l_lastUpdatedTimestamp;
        this.type = l_strType;
        this.tpk = l_strTpk;
    }

}
@
