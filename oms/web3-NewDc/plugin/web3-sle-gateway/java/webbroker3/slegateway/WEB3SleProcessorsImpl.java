head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleProcessorsImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 *Copyright        : (株)大和総研 証券ソリューションシステム第二部
 *File Name        : WEB3SleProcessorsImplクラス
 *Author Name      : Daiwa Institute of Research
 *Revision History : 2006/6/4 孫(FLJ) 新規作成
 */
package webbroker3.slegateway;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * xTrade提供しているデフォルトクエリプロセッサを取得できるクラス
 * 
 * @@author  孫(FLJ)
 * @@version 1.0
 */
public class WEB3SleProcessorsImpl implements WEB3SleProcessors
{

    /**
     * Web3SleProcessorsImplオブジェクトを作成
     */
    public WEB3SleProcessorsImpl()
    {
        super();
    }

    /**
     * xTrade提供しているデフォルトクエリプロセッサを取得する。
     * @@see webbroker3.slegateway.WEB3SleProcessors#getDefaultProcessor()
     */
    public QueryProcessor getDefaultProcessor() throws DataNetworkException, DataFindException
    {
        return Processors.getDefaultProcessor();
    }

}
@
