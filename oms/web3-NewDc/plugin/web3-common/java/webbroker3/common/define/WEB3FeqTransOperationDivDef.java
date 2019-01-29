head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.39.32;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqTransOperationDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3FeqTransOperationDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/10 王蘭芬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * UWG振替状況テーブルの処理区分　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3FeqTransOperationDivDef
{
    /**
     * 01：証券口座から外国株式口座へ　@　@
     */
    public static final String TRANSFER_TO_FEQ = "01";

    /**
     * 02：外国株式口座から証券口座へ　@
     */
    public static final String TRANSFER_FROM_FEQ = "02";
}
@
