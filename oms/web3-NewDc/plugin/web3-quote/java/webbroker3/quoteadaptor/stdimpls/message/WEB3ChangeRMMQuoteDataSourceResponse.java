head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.45.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3ChangeRMMQuoteDataSourceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ChangeRMMQuoteDataSourceResponseクラス(WEB3ChangeRMMQuoteDataSourceResponseResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2009/06/26 齋藤　@栄三(FLJ) 新規作成
 */
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Response;


/**
 * RMMデータソース同士で切り替えるレスポンスメッセージ
 *
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public class WEB3ChangeRMMQuoteDataSourceResponse extends Response 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "change_rmm_datasource";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200906261700L;

    /**
     * information<BR>
     */
    public String info;
}
@
