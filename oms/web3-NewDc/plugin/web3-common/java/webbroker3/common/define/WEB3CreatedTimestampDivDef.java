head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.28.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3CreatedTimestampDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 作成日付有無区分 定数定義インタフェイス(WEB3CreatedTimestampDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/29 凌建平(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * 作成日付有無区分 定数定義インタフェイス
 *
 * @@author 凌建平(中訊)
 * @@version 1.0
 */
public interface WEB3CreatedTimestampDivDef
{

    /**
     * 0:無し
     */
    public static final String NONE = "0";

    /**
     * 1:有り
     */
    public static final String EXISTENCE = "1";
}
@
