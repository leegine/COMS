head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.20.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d91339b43f9;
filename	WEB3DefaultPreLoaderCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3DefaultPreLoaderCallbackクラス(WEB3DefaultPreLoaderCallback.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/21 山田　@卓司 (FLJ) 新規作成
 */
package webbroker3.preloader;

import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * WEB3PreLoaderCallbackインターフェースのデフォルト実装クラス<BR>
 * 
 * @@author Takuji Yamada (FLJ)
 */
public abstract class WEB3DefaultPreLoaderCallback implements WEB3PreLoaderCallback
{
    
    /**
     * この実装クラスでは、QueryProcessor#doFindByPrimaryKey(PrimaryKey)による
     * PrimaryKeyでの検索を行う。<BR>
     * PrimaryKeyには、パラメータで指定されたRowオブジェクトのPrimaryKeyを
     * 設定する。<BR>
     * PrimaryKeyでの検索を実行後、WEB3DefaultPreLoaderCallback#load(Row)メソッドを
     * 実行する。
     * 
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        
        PrimaryKey l_pk = l_row.getPrimaryKey();
        QueryProcessor l_qp = Processors.getDefaultProcessor();
        l_qp.doFindByPrimaryKeyQuery(l_pk);
        
		load(l_row);
        
    }
    
    protected abstract void load(Row l_row) throws DataException;
    
}@
