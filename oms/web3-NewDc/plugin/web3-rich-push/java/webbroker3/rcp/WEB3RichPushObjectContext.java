head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.24.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushObjectContext.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュオブジェクトソート用コンテクストクラス(WEB3RichPushObjectContext.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/03/01 劉(FLJ) 新規作成
 */

package webbroker3.rcp;

import java.util.*;

/**
 * <p>
 * リッチクライアントプッシュオブジェクトソート用コンテクストクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ劉
 * @@version 1.0
 */

public class WEB3RichPushObjectContext
{
    /**
     * プッシュオブジェクト一覧
     */
    private List pushObjects = new ArrayList();

    /**
     * プッシュオブジェクトとプッシュオ履歴オブジェクのトマッピング
     */
    private Map pushHistoryObjects = new Hashtable();

    /**
     * プッシュオブジェクトとプッシュオ履歴オブジェクのトマッピングを設定する
     *
     * @@param l_mapPushHistoryRecords Map
     */
    public void setPushHistoryObjects(Map l_mapPushHistoryObjetcs)
    {
        this.pushHistoryObjects = l_mapPushHistoryObjetcs;
    }

    /**
     * プッシュオブジェクト一覧を設定する
     *
     * @@param l_lstPushObjects List
     */
    public void setPushObjects(List l_lstPushObjects)
    {
        this.pushObjects = l_lstPushObjects;
    }

    /**
     * プッシュオブジェクト一覧を取得する
     *
     * @@return List
     */
    public List getPushObjects()
    {
        return pushObjects;
    }

    /**
     * プッシュオブジェクトとプッシュオ履歴オブジェクのトマッピングを取得する
     *
     * @@return Map
     */
    public Map getPushHistoryObjects()
    {
        return pushHistoryObjects;
    }

}@
