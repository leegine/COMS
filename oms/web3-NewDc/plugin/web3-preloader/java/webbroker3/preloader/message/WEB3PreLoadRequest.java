head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.23.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d9134a0474d;
filename	WEB3PreLoadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderRequestクラス(WEB3PreLoadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * プリロードを実行するメッセージ。
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoadRequest extends Request
{
    
    public static final String PTYPE = "preload";
    
    /**
     * テーブル名
     * 
     * @@deprecated
     */
    public String tableName;
    
    /**
     * テーブル名の配列 
     */
    public String[] tableNames;
    
    /**
     * スレッド数
     */
    public int threadsSize;

}
@
