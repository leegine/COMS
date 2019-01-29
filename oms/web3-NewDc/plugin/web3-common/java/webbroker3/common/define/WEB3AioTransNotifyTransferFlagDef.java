head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AioTransNotifyTransferFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3AioTransNotifyTransferFlagDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 王蘭芬 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 代用振替強制キューテーブルの預り区分(受入先の代用区分) 定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3AioTransNotifyTransferFlagDef 
{

    /**
     * 01:保護預り
     */
    public static final String SAFE_DEPOSIT = "01";
    
    /**
     * 04:代用
     */
    public static final String COLLATERAL = "04";

}
@
