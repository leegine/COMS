head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.22;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3DocForceLogoutRunStatusStatusDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ログインセッション　@有効フラグ定数定義インタフェイス(WEB3AdminFPTForceLogoutValidityDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/18 孫(FLJ) 新規作成
*/
package webbroker3.docadmin.define;

/**
 * 書面未承諾強制ログアウト実行結果  実行ステータス区分定義インタフェイス<BR>
 * 
 * @@author 孫
 * @@version 1.0
 */
public interface WEB3DocForceLogoutRunStatusStatusDef 
{
	/**
	 * 実行ステータス区分　@0:未処理
	 */
	public final static String NOT_PROCESSED = "0"; 
	
	/**
	 * 実行ステータス区分　@1:処理済
	 */
	public final static String PROCESSED = "1";
	
	/**
	 * 実行ステータス区分　@5:処理中
	 */
	public final static String PROCESSING = "5";
	
	/**
	 * 実行ステータス区分　@9:エラー
	 */
	public final static String PROCESS_ERROR = "9";
}
@
