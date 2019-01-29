head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.35.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminExecTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminExecTypeDef(WEB3AdminExecTypeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2008/10/02 大澤(SRA) 【管理者注文約定照会】仕様変更管理台帳（モデル）No.128、129、130
*/
package webbroker3.adminorderexecinquiry.define;

/**
 * 約定状態区分 定数定義インタフェイス<BR>
 * WEB3AdminExecTypeDef
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public interface WEB3AdminExecTypeDef
{
    /**
     * EXEC_TYPE_NOT_PROMISE
     */
    public final static String EXEC_TYPE_NOT_PROMISE = "0";

    /**
     * EXEC_TYPE_ONE_COMPLETE
     */
    public final static String EXEC_TYPE_ONE_COMPLETE = "1";
    /**
     * EXEC_TYPE_ALL_COMPLETE
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

}@
