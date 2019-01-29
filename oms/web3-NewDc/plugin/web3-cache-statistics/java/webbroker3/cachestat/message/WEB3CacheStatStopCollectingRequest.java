head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatStopCollectingRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatStopCollectingRequestクラス(WEB3CacheStatStopCollectingRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/17 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * キャッシュ統計情報収集終了メッセージ
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatStopCollectingRequest extends Request
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "cache_stat_stop_collecting";

    /**
     * 開始前に収集済みの統計情報をクリアする場合<code>true</code>
     */
    public boolean clearStat;
    
}
@
