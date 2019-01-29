head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.49.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMarginOpenApplyDateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「信用新規建可能額適用日」の定数定義インターフェース(WEB3TPMarginOpenApplyDateDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/07/22 nakazato(DIR-ST) 新規作成
 */
package webbroker3.tradingpower.define;

/**
 * (「信用新規建可能額適用日」の定数定義インターフェース)
 */
public interface WEB3TPMarginOpenApplyDateDef
{

    /**
     * (T+1以降で最小の信用新規建可能額を採用する。) <BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (発注日以降で最小の信用新規建可能額を採用する。) <BR>
     */
    public final static String FROM_BIZ_DATE_UNTIL_T5 = "1";

    /**
     * (T+2以降で最小の信用新規建可能額を採用する。) <BR>
     */
    public final static String FROM_T2_UNTIL_T5 = "2";
}@
