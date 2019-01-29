head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatGetStatisticsRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatGetStatisticsRequestクラス(WEB3CacheStatGetStatisticsRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * キャッシュ統計取得リクエストメッセージ
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatGetStatisticsRequest extends Request
{
    
    /** 
     * PTYPE 
     */
    public static final String PTYPE = "cache_stat_get_statistics";
    
    /**
     * 取得する統計情報の種類
     * 
     * @@see webbroker3.cachestat.define.WEB3CacheStatType
     */
    public int statType;
    
    /**
     * 統計情報を取得するテーブルのテーブル名
     */
    public String tableName;

}
@
