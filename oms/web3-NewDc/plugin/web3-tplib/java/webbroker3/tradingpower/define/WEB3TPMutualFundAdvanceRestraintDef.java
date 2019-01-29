head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.48.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPMutualFundAdvanceRestraintDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「投資信託前受拘束」の定数定義インターフェース(WEB3TPMutualFundAdvanceRestraintDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/10/17 齋藤 栄三(FLJ) 新規作成
 */
package webbroker3.tradingpower.define;


/**
 * (「投資信託前受拘束」の定数定義インターフェース)
 */
public interface WEB3TPMutualFundAdvanceRestraintDef
{
    /**
     * (投資信託前受拘束なし)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (発注日以降から受渡日前日まで投資信託前受拘束あり)<BR>
     */
    public final static String FROM_BIZ_DATE_UNTIL_PRE_DELIVERY_DATE = "1";
    
}
@
