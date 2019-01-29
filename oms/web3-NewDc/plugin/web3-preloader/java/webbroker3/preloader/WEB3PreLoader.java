head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoader.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderクラス(WEB3PreLoader.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.RowType;

/**
 * プリロード処理を行うクラスが実装するインターフェース
 * 
 * @@author Takuji Yamada (FLJ)
 */
public interface WEB3PreLoader
{
    
    /**
     * プリロードを行うテーブルの<code>RowType</code>を取得する。
     * 
     * @@return プリロードを行うテーブルの<code>RowType</code>
     */
    public RowType getRowType();
    
    /**
     * プリロードを行う。
     */
    public void execute();
    
}
@
