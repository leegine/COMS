head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.41.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3UpdatedDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 更新区分定数定義インタフェイス(WEB3UpdatedDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/24 陸文静 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 更新区分定数定義インタフェイス<BR>
 * (ログイン拒否IPの更新区分の參照)<BR>
 * <BR>
 * @@author 陸文静 (中訊)
 * @@version 1.0
 */
public interface WEB3UpdatedDivDef
{
    /**
     * 0：デーモン
     */
    public static final String DAEMON = "0";

    /**
     * 1：管理者
     */
    public static final String MANAGER = "1";
}
@
