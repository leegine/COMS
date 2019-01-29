head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.19.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3PreLoaderCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3PreLoaderCallbackクラス(WEB3PreLoaderCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;

/**
 * WEB3DefaultPreLoaderで一括検索により取得された各レコードに対する
 * 処理を定義するコールバッククラス。
 * 
 * @@author Takuji Yamada (FLJ)
 */
public interface WEB3PreLoaderCallback
{
    
    /**
     * 一括検索で取得されたレコードを処理する。 
     * 
     * @@param l_row 一括検索で取得されたレコード
     */    
    public void process(Row l_row) throws DataException;
    
}@
