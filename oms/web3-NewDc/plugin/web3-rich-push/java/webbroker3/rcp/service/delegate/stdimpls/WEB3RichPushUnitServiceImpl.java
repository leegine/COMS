head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushUnitServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントデータプッシュ機@能単位サービス共通機@能実装(WEB3RichPushUnitServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.kernel.data.*;
import com.fitechlabs.xtrade.kernel.util.*;
import webbroker3.rcp.*;
import webbroker3.rcp.service.delegate.*;
import webbroker3.util.*;

/**
 * リッチクライアントデータプッシュ機@能単位サービス共通機@能実装
 * @@author 劉
 * @@version 1.0
 */
public abstract class WEB3RichPushUnitServiceImpl
    implements WEB3RichPushUnitService
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RichPushUnitServiceImpl.class);

    /**
     * リッチクライアントデータプッシュ
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public abstract void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException;

    /**
     * リッチクライアントプッシュオブジェクトソート用コンテクストへ保存
     *
     * @@param l_lstPushObjects List
     * @@param l_mapPushHistoryObjects Map
     */
    protected void saveToContext(List l_lstPushObjects)
    {

        final String STR_METHOD_NAME = "saveToContext()";

        if (l_lstPushObjects == null)
        {
            log.error(
                "l_lstPushRecords==nulll! save fail!");
            log.exiting(STR_METHOD_NAME);
            return;
        }
        WEB3RichPushObjectContext l_context = (WEB3RichPushObjectContext)
            ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3RichPushPlugin.PUSH_OBJECT_CONTEXT_ATTRIBUTE_NAME
            );
        //プッシュオブジェクト一覧追加
        List l_lstAllObjects = l_context.getPushObjects();
        l_lstAllObjects.addAll(l_lstPushObjects);

        log.exiting(STR_METHOD_NAME);

    }

}
@
