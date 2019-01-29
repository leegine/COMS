head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.38.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccTransactionStateDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 処理状況定数定義インタフェイス(WEB3ToSuccTransactionStateDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 孟東(中訊)　@新規作成
*/
package webbroker3.triggerorder.define;

/**
 * 処理状況分定数定義インタフェイス
 * 
 * @@author Meng-Dong
 * @@version 1.0
 */
public class WEB3ToSuccTransactionStateDef
{
    /**
     * 0003：全部取消完了
     */
    public static final String ALL_CANCELED = "0003";

    /**
     * 3000：予約
     */
    public static final String RESERVATION = "3000";

    /**
     * 3020：無効
     */
    public static final String INEFFECTIVE = "3020";
}
@
