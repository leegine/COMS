head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.24.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTMRateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : レート区分定義インタフェイス(WEB3AdminTMRateDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/10 徐宏偉 (中訊) 新規作成
*/
package webbroker3.trademanagement.define;

/**
 * レート区分定義インタフェイス
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public interface WEB3AdminTMRateDivDef
{
    /**
     * 0：基準為替
     */
    public static final String BASE_EXCHANGE = "0";

    /**
     * 1：約定為替
     */
    public static final String EXECUTED_EXCHANGE = "1";
}
@
