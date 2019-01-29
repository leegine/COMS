head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.55.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CfdAccOpenDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : CFD口座開設区分定数定義インタフェイス(WEB3CfdAccOpenDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/24 陸文静 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * CFD口座開設区分定数定義インタフェイス<BR>
 * (顧客マスター仕様のCFD口座開設区分の參照)<BR>
 * <BR>
 * @@author 陸文静 (中訊)
 * @@version 1.0
 */
public interface WEB3CfdAccOpenDivDef
{
    /**
     * 1：口座開設
     */
    public static final String ACCOUNT_OPEN = "1";
}
@
