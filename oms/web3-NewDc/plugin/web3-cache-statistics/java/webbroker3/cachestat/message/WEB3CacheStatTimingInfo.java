head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatTimingInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatTimingInfoクラス(WEB3CacheStatTimingInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/20 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * キャッシュ統計・タイミング情報
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatTimingInfo extends Message
{
    
    /**
     * テーブル名
     */
    public String tableName;
    
    /**
     * キャッシュタイプ（beans, enumu, row）
     */
    public String cacheType;
    
    /**
     * Where句内容
     */
    public String condition;
    
    /**
     * 詳細（hit, miss, etc）
     */
    public String detail;
    
    /**
     * 処理回数
     */
    public String count;
    
    /**
     * 平均処理時間（秒）
     */
    public String averageTime;
    
    /**
     * 最大処理時間（秒）
     */
    public String maxTime;
    
    /**
     * 処理時間の標準偏差（秒）
     */
    public String standardDeviation;
    
    /**
     * 累積処理時間（秒）
     */
    public String totalTime;

}
@
