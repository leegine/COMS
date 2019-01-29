head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.33.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8804d8162a91dd2;
filename	WEB3RichPushUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントデータプッシュ機@能単位サービス共通インターフェース(WEB3RichPushUnitService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;

/**
 * （リッチクライアントプッシュ機@能単位サービス）。
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3RichPushUnitService
    extends Service
{
    /**
     * リッチクライアントデータプッシュ機@能単位サービス共通インターフェース
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException;

    /**
     * リッチクライアントプッシュデータをXMLへ変換
     *
     * @@param l_dataRows List
     * @@return String
     */
    public String createRichPushXmlMessage(
        List l_dataRows);
}
@
