head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatInvInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatInvalidationInfoUnitクラス(WEB3CacheStatInvalidationInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * キャッシュ統計・インバリデーション情報
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatInvInfo extends Message
{
    
    /**
     * テーブル名
     */
    public String tableName;
    
    /**
     * 処理タイプ（ins, upd, del）
     */
    public String mutatorType;
    
    /**
     * テーブル毎の件数
     */
    public String count;
    
    /**
     * テーブル毎の平均処理時間（秒）
     */
    public String averageTime;
    
    /**
     * テーブル毎の最大処理時間（秒）
     */
    public String maxTime;
    
    /**
     * テーブル毎の標準偏差（秒）
     */
    public String standardDeviation;
    
    /**
     * テーブル毎の累積処理時間（秒）
     */
    public String totalTime;

}
@
