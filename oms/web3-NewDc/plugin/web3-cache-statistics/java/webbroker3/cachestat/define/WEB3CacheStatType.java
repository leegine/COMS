head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3CacheStatType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3CacheStatTypeクラス(WEB3CacheStatType.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/02/16 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.cachestat.define;


/**
 * 統計情報の種類を表す定数クラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public interface WEB3CacheStatType
{
    
    /**
     * 全種類
     */
    public static final int ALL = 0;
    
    /**
     * サイズ情報
     */
    public static final int SIZE = 1;
    
    /**
     * ヒット率
     */
    public static final int HIT_RATE = 2;
    
    /**
     * インバリデーション情報
     */
    public static final int INVALIDATION = 3;
    
    /**
     * タイミング情報
     */
    public static final int TIMING = 4;

}
@
