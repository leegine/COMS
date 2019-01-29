head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.45.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3ChangeRMMQuoteDataSourceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ChangeRMMQuoteDataSourceRequestクラス(WEB3ChangeRMMQuoteDataSourceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/04/24 齋藤　@栄三(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Request;


/**
 * RMMデータソース同士で切り替えるリクエストメッセージ
 *
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3ChangeRMMQuoteDataSourceRequest extends Request
{
    
    /**
     * このメッセージのPTYPE
     */
    public static final String PTYPE = "change_rmm_datasource";

}
@
