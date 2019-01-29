head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.36.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ExclusiveUseAccountStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ステータス定数定義インタフェイス(WEB3ExclusiveUseAccountStatusDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/07 孟東(中訊)　@新規作成
*/
package webbroker3.common.define;

/**
 * 専用振込先口座条件テーブルのステータス 定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3ExclusiveUseAccountStatusDef
{
    /**
     * 0：未使用レコード
     */
    public static final String UNUSED_RECORD = "0";

    /**
     * 1：使用済みレコード
     */
    public static final String USED_RECORD = "1";
}
@
