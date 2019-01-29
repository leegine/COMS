head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatGetStatisticsResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatGetStatisticsResponseクラス(WEB3CacheStatGetStatisticsResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Response;


/**
 * キャッシュ統計取得レスポンスメッセージ
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatGetStatisticsResponse extends Response
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "cache_stat_get_statistics";
    
    /**
     * リクエストメッセージで設定された統計情報種類
     * 
     * @@see webbroker3.cachestat.define.WEB3CacheStatType
     */
    public int statType;
    
    /**
     * リクエストメッセージで設定されたテーブル名
     */
    public String tableName;
    
    /**
     * サイズ情報
     */
    public WEB3CacheStatSizeInfo[] sizeInfo;
    
    /**
     * ヒット率情報
     */
    public WEB3CacheStatHitRateInfo[] hitRateInfo;
    
    /**
     * インバリデーション情報
     */
    public WEB3CacheStatInvInfo[] invInfo;
    
    /**
     * タイミング情報
     */
    public WEB3CacheStatTimingInfo[] timingInfo;
    

}
@
