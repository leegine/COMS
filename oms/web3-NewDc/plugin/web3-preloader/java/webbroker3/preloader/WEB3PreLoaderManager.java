head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderManager.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderManagerクラス(WEB3PreLoaderManager.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/04/07 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.preloader;

import com.fitechlabs.xtrade.kernel.boot.Service;


/**
 * プリローダーを管理するクラス
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public interface WEB3PreLoaderManager extends Service
{
    
    /**
     * 新しいプリローダーを登録する。
     * 
     * @@param l_preLoader 登録するプリローダー
     */
    public void registerPreLoader(WEB3PreLoader l_preLoader);
    
    /**
     * 指定したテーブル名に関連付けられているプリローダーの配列を取得する。
     * 指定したテーブル名に関連付けられているプリローダーが登録されていない場合は、
     * <code>null</code>を返す。
     * 
     * @@param l_strTableName テーブル名
     * @@return WEB3PreLoaderの配列。
     */
    public WEB3PreLoader[] getPreLoader(String l_strTableName);
    
    /**
     * 指定したテーブル名に関連付けられているプリローダーを実行する。
     * 
     * @@param l_strTableName テーブル名
     */
    public void load(String l_strTableName);
    
    /**
     * 指定したテーブルに関連付けられているプリローダーを実行する。
     * 
     * @@param l_strTableNames テーブル名の配列
     * @@param l_intThreadsSize プリローダーを実行するスレッドの数
     */
    public void load(String[] l_strTableNames, int l_intThreadsSize);
    
    /**
     * 登録されているすべてのプリローダーを実行する。
     */
    public void loadAll();

}
@
