head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.48.05;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3QtpRichPushUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : QTPリッチクライアントプッシュ機@能単位サービス(WEB3QtpRichPushUnitService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/03/10 孫(FLJ) 新規作成
*/

package webbroker3.rcp.service.delegate;

import webbroker3.rcp.service.delegate.stdimpls.WEB3QtpExcutionInformUnit;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.data.*;

/**
 * （QTPリッチクライアントプッシュ機@能単位サービス）。
 * 
 * @@author 孫
 * @@version 1.0
 */
public interface WEB3QtpRichPushUnitService
    extends Service
{
    /**
     * リッチクライアントデータプッシュ機@能単位サービス共通インターフェース
     *
     * @@param l_lngFromAccountId long
     * @@param l_lngToAccountId long
     */
    public void push(long l_lngFromAccountId, long l_lngToAccountId) throws
        DataQueryException, DataNetworkException;

    /**
     * リッチクライアントプッシュデータをXMLへ変換
     *
     * @@param l_dataRows List
     * @@return String
     */
    public WEB3QtpExcutionInformUnit createRichPushXmlMessage(
        Row l_dataRows);
    
    
}
@
