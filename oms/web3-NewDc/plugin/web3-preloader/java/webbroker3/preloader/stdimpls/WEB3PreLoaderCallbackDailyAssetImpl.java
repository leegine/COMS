head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.29.01.22.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	46c4d913412457d;
filename	WEB3PreLoaderCallbackDailyAssetImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3PreLoaderCallbackDailyAssetImplクラス(WEB3PreLoaderCallbackDailyAssetImpl.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/26 山田　@卓司(FLJ) 新規作成
                   2005/05/19 齋藤　@栄三(FLJ) 検索条件を変更
 */
package webbroker3.preloader.stdimpls;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.Processors;

import webbroker3.gentrade.data.DailyAssetRow;
import webbroker3.preloader.WEB3PreLoaderCallback;


/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3PreLoaderCallbackDailyAssetImpl implements
        WEB3PreLoaderCallback
{

    /**
     * @@see webbroker3.preloader.WEB3PreLoaderCallback#process(com.fitechlabs.dbind.Row)
     */
    public void process(Row l_row) throws DataException
    {
        DailyAssetRow l_daRow = (DailyAssetRow) l_row;
        Processors.getDefaultProcessor().doFindAllQuery(
                DailyAssetRow.TYPE, 
                "account_id=?", 
                new Object[] {new Long(l_daRow.getAccountId())}
                );
    }

}
@
