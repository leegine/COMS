head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAccountDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客区分定義インタフェイス(WEB3FeqAccountDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/06 孟東 (中訊) 新規作成
*/
package webbroker3.feq.define;

/**
 * 顧客区分定義インタフェイス
 *
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqAccountDivDef
{
    /**
     * 0：一般
     */
    public static final String NORMAL = "0";
    
    /**
     * 1：同業者
     */
    public static final String FELLOW_TRADER = "1";

    /**
     * 2：自己
     */
    public static final String SELF = "2";
}
@
