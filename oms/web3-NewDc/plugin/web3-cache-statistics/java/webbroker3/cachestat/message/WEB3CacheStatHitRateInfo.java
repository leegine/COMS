head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatHitRateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatHitRatioInfoクラス(WEB3CacheStatHitRatioInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/18 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * キャッシュ統計・ヒット率情報
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatHitRateInfo extends Message
{
    
    /**
     * テーブル名
     */
    public String tableName;
    
    /**
     * キャッシュタイプ（beans, enum, row）
     */
    public String cacheType;
    
    /**
     * トライ数
     */
    public String tryCount;
    
    /**
     * ヒット数
     */
    public String hitCount;
    
    /**
     * ミス数
     */
    public String missCount;
    
    /**
     * ヒット率
     */
    public String hitRate;
    
    /**
     * ミス率
     */
    public String missRate;
    
    /**
     * 検索時に指定された特別な内容
     */
    public WEB3CacheStatSpecialCase[] specialCases;

}
@
