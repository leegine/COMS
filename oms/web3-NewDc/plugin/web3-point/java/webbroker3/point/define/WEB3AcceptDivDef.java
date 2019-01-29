head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.03.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AcceptDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 受付区分  定数定義インタフェイス(WEB3AcceptDivDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/05 鄭海良(中訊) 新規作成
*/
package webbroker3.point.define;

/**
 * 受付区分 定数定義インタフェイス
 *
 * @@author 鄭海良(中訊)
 * @@version 1.0
 */
public interface WEB3AcceptDivDef                
{

    /**
     *  0:受付未済 
     */
    public static final String  NO_FINISHED = "0";

    /**
     *  1:受付済み
     */
    public static final String  FINISHED = "1";

    /**
     *  2:全て表示
     */
    public static final String  ALL = "2";
}
@
