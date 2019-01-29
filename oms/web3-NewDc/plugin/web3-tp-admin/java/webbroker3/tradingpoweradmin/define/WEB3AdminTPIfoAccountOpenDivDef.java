head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.51;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPIfoAccountOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP口座開設区分インターフェース(WEB3AdminTPIfoAccountOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/6/07 堀野 和美(FLJ) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * WEB3AdminTPIfoAccountOpenDivDefインターフェース。
 * 先物OP口座開設区分を定義する。
 * @@author 堀野 和美(FLJ)
 * @@version 1.0
 *
 */
public interface WEB3AdminTPIfoAccountOpenDivDef 
{
    /**
     * 0：DEFAULT（口座なし）
     */
    public static final String DEFAULT = "0";

    /**
     * 1：OP口座開設　@
     */
    public static final String OP_OPEN = "1";
    
    /**
     * 2：先物口座開設 
     */
    public static final String FUTURE_OPEN = "2";

    /**
     * 3：先物OP口座開設     
     */ 
    public static final String OP_FUTURE_OPEN = "3";
    
}
@
