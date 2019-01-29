head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.51.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPServiceChargeRestraintDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 「サービス利用拘束」の定数定義インターフェース(WEB3TPServiceChargeRestraintDef.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/09/06 堀野 和美(FLJ) 新規作成
 */
package webbroker3.tradingpower.define;


/**
 * (「サービス利用拘束」の定数定義インターフェース)
 */
public interface WEB3TPServiceChargeRestraintDef
{
    /**
     * (T+0あるいは発注日～受渡日前日まで拘束する)<BR>
     */
    public final static String DEFAULT = "0";

    /**
     * (拘束なし)<BR>
     */
    public final static String EXCEPT = "1";
    
}
@
