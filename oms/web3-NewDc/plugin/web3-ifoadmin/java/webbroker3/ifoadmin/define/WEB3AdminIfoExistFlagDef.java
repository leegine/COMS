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
filename	WEB3AdminIfoExistFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 有無フラグ定数定義インタフェイス(WEB3AdminIfoExistFlagDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/03/03 劉剣（中訊）新規作成
*/
package webbroker3.ifoadmin.define;

/**
 * 有無フラグ 定数定義インタフェイス
 *
 * @@author 劉剣
 * @@version 1.0
 */
public interface WEB3AdminIfoExistFlagDef
{
    /**
     * "有"
     */
    public static final String EXIST = "有";

    /**
     * "無"
     */
    public static final String NO = "無";
}
@
