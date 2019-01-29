head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.57.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3OtherOrgStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ステータス定数定義インタフェイス(WEB3OtherOrgStatusDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/19 栄イ(中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 外部連携情報管理テーブルのステータス 定数定義インタフェイス
 * 
 * @@author 栄イ(中訊)
 * @@version 1.0
 */
public interface WEB3OtherOrgStatusDef
{
    /**
     * 0：使用中
     */
    public final static String USING = "0";

    /**
     * 1：無効（削除）
     */
    public final static String INVALIDITY = "1";

    /**
     * 9：未使用（DEFAULT）
     */
    public final static String DEFAULT = "9";
}
@
