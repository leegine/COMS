head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecStatusTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 約定状態区分 定数定義インタフェイス(WEB3FeqExecStatusTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/18 孟東(中訊) 新規作成
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.464
*/

package webbroker3.feq.define;

/**
 *約定状態区分　@定数定義インタフェイス
 *                                                                     
 * @@author 孟東
 * @@version 1.0
 */
public interface WEB3FeqExecStatusTypeDef
{
    /**
     * 0:未約定
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";
     
    /**
     * 1:一部成立
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    
    /**
     * 2:全部成立
     */
    public final static String EXEC_TYPE_ALL_COMPLETE = "2";
    
    /**
     * 3:約定処理中(一部成立)
     */
    public final static String EXEC_PROCESSING_ONE_COMPLETE = "3";
    
    /**
     * 4:約定処理中(全部成立)
     */
    public final static String EXEC_PROCESSING_ALL_COMPLETE = "4";
}
@
