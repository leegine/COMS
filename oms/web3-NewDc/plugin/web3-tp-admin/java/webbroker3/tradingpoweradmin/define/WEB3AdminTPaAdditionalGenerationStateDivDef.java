head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.36.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPaAdditionalGenerationStateDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 追証発生状況定義インターフェース(WEB3AdminTPaAdditionalGenerationStateDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/13 安陽(中訊) 新規作成
*/
package webbroker3.tradingpoweradmin.define;

/**
 * 追証発生状況　@定数定義インタフェイス
 *
 * @@author 安陽
 * @@version 1.0
 */
public interface WEB3AdminTPaAdditionalGenerationStateDivDef
{
    /**
     * 0：追証未発生
     */
    public static final String ADDITIONAL_NOT_OCCUR = "0";

    /**
     * 1：第一水準追証発生
     */
    public static final String FIRST_LEVEL_OCCUR = "1";

    /**
     * 2：第二水準追証発生
     */
    public static final String SECOND_LEVEL_OCCUR = "2";
}
@
