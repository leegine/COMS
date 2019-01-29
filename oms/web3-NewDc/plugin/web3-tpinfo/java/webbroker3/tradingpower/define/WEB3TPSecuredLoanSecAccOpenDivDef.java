head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.13.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPSecuredLoanSecAccOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 ネットトレードシステム開発部
 File Name        : 証券担保ローン区分定数定義クラス(WEB3TPSecuredLoanSecAccOpenDivDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/09/28 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * 証券担保ローン区分定数定義クラス
 */
public interface WEB3TPSecuredLoanSecAccOpenDivDef
{

    /**
     * 0：未実施
     */
    public static final String NON_ENFORCEMENT = "0";

    /**
     * 1：実施
     */
    public static final String ENFORCEMENT = "1";

}@
