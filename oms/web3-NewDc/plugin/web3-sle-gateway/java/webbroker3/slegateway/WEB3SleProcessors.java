head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3SleProcessors.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 *Copyright        : (株)大和総研 証券ソリューションシステム第二部
 *File Name        : WEB3SleProcessorsクラス
 *Author Name      : Daiwa Institute of Research
 *Revision History : 2006/6/4 孫(FLJ) 新規作成
 */
package webbroker3.slegateway;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;

/**
 * このクラスは正しいプロセッサを取得するためのメソッドを定義している
 * 
 * @@author  孫(FLJ)
 * @@version 1.0
 */
public interface WEB3SleProcessors
{
    /**
     * すべてのクエリに利用できるデフォルトクエリプロセッサを取得します。
     * @@throws DataNetworkException
     * @@throws DataFindException
     */
    public QueryProcessor getDefaultProcessor() throws DataNetworkException,DataFindException;
    
}
@
