head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.51.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCUserTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義ユーザ種類(WEB3AdminMCUserTypeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/01  王敏 (中訊)　@新規作成
*/
package webbroker3.adminmc.define;

/**
 * ユーザ種類 定数定義インタフェイス
 *                                                                     
 * @@author 王敏
 * @@version 1.0
 */
public interface WEB3AdminMCUserTypeDef
{

    /**
     *    2 ：扱者
     */
    public static final String TRADER = "2";
    /**
     *    3 ：管理者
     */
    public static final String ADMINISTRATOR = "3";


}
@
