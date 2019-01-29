head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.43.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatSizeInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatisticsSizeInfoクラス(WEB3CacheStatSizeInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * キャッシュ統計・サイズ情報
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3CacheStatSizeInfo extends Message
{
    
    /**
     * テーブル名
     */
    public String tableName;
    
    /**
     * キャッシュタイプ（row、enumなど)
     */
    public String cacheType;
    
    /**
     * 容量
     */
    public String capacity ;
    
    /**
     * 平均
     */
    public String averageCount;
    
    /**
     * 最大
     */
    public String maxCount;
    
    /**
     * 標準偏差
     */
    public String standardDeviation;
    
    /**
     * 使用率
     */
    public String useRate;

}
@
