head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.30.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d885dc169b7;
filename	SleGLRejectTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : GL SLEの拒否タイプ定義インタフェイス(SleGLRejectTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/12/13 李 (FTL) 新規作成
*/

package webbroker3.slebase.utils;


/**
 * GL SLEの拒否タイプ定義インタフェイス
 *
 * @@author 李（FTL)
 * @@version 1.0
 */
public interface SleGLRejectTypeDef
{
	/**
	 * GL022:全約定後訂正注文拒否
	 */
	public static final String CHANGE_REJECT_AF_ALL_EXEC = "GL 022";
    
	/**
	 * GL007：一部約定後訂正注文拒否
	 */
	public static final String CHANGE_REJECT_AF_PART_EXEC = "GL 007";

	/**
	 * M****：Suspendエラーコード1桁目
	 */
	public static final String FIRST_CHAR_SUSPEND = "M";

}
@
