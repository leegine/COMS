head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.07.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ChangeableTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 同時訂正可能区分(市場テーブル) 定数定義インタフェイス(WEB3ChangeableTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/01 zhao-lin(sinocom)　@新規作成
*/
package webbroker3.common.define;

/**
 * 同時訂正可能区分(市場テーブル)　@定数定義インタフェイス
 *                                                                     
 * @@author zhao-lin
 * @@version 1.0
 */
public interface WEB3ChangeableTypeDef
{
    /**
     * 0 : 複数項目同時訂正不可
     */
    public static final String CANNOT_CHANGE = "0";

    /**
     * 1 : 複数項目同時訂正可能
     */
    public static final String CAN_CHANGE = "1";

}
@
