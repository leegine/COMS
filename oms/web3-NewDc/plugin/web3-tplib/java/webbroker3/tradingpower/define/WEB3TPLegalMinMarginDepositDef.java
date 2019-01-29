head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.52.12;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPLegalMinMarginDepositDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 法@定最低必要保証金　@定数定義インターフェース(WEB3TPLegalMinMarginDepositDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/19 nakazato(ACT) 新規作成
*/
package webbroker3.tradingpower.define;

/**
 *法@定最低必要保証金　@定数定義インタフェイス<BR>
 * @@author  nakazato(ACT)
 * @@version 1.0
 */
public interface WEB3TPLegalMinMarginDepositDef 
{
    /**
     * 法@定最低必要保証金：300000
     */
    public final static double MIN_MARGIN_DEPOSIT =300000.0;
}
@
