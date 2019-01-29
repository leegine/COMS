head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.09.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultSaveDiv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金不足確定FLAG　@定数定義インターフェース(WEB3IfoDepositFixedIfoDepositFlgDiv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/03 nakazato(ACT) 新規作成
*/

package webbroker3.ifodeposit.define;

/**
 * 証拠金不足確定FLAG　@定数定義インターフェース
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcResultSaveDiv
{
    
    /**
     * 0：未処理 <BR>
     */
    public static final int STATUS_NONE = 0;
    
    /**
     * 1：処理完了 <BR>
     */
    public static final int STATUS_FINISH = 1;
    
    /**
     * 9：処理エラー<BR>
     */
    public static final int STATUS_ERROR = 9;
    
    /**
     * 0018：NK225<BR>
     */
    public static final String NK225 = "0018";
    
    /**
     * 0019：ミニNK225<BR>
     */
    public static final String MINI_NK225 = "0019";
    
    /**
     * ロック処理区分　@0：待機@<BR>
     */
    public static final String LOCK_NONE = "0";
    
    /**
     * ロック処理区分　@1：処理中<BR>
     */
    public static final String LOCK_PROC = "1";
    
    public static final int ERROR_MESSAGE_LENGTH = 100;
    
}
@
