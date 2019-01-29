head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminIfoUnCancelDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 未解消客区分 定数定義インタフェイス(WEB3AdminIfoUnCancelDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/03 李玉玲（中訊）新規作成
*/
package webbroker3.ifoadmin.define;

/**
 * 未解消客区分 定数定義インタフェイス
 *
 * @@author 李玉玲
 * @@version 1.0
 */
public interface WEB3AdminIfoUnCancelDivDef
{

    /**
     * 0：未解消客
     */
    public static final String UN_CANCEL = "0";

    /**
     * 1：不足発生全顧客
     */
    public static final String SHORT_GENERATION_ALL_ACCOUNT = "1";

}
@
