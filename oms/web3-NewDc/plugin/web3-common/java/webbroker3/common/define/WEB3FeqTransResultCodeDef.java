head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.25.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FeqTransResultCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 定数定義インタフェイス(WEB3FeqTransResultCodeDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/10 王蘭芬(中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * UWG振替状況テーブルの受付結果コード　@定数定義インタフェイス
 *                                                                     
 * @@author 王蘭芬
 * @@version 1.0
 */
public interface WEB3FeqTransResultCodeDef
{
    /**
     * 0：振替完了
     */
    public static final String TRANSFER_COMPLETE = "0";

    /**
     * 1：振替エラー
     */
    public static final String TRANSFER_ERROR = "1";
}
@
