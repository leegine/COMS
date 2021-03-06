head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3RecruitCommissionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 募集手数料区分定数定義インタフェイス(WEB3RecruitCommissionDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/10/16 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 募集手数料区分 定数定義インタフェイス
 *
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3RecruitCommissionDivDef
{
    /**
     * 0：なし
     */
    public static final String NOTHING = "0";

    /**
     * 1：内枠
     */
    public static final String WITHIN_THE_LIMIT = "1";

    /**
     * 2：外枠
     */
    public static final String WITHOUT_THE_LIMIT = "2";
}
@
