head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連絡入力レスポンス(WEB3InformInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 艾興 (中訊) 新規作成
*/
package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (連絡入力レスポンス)<BR>
 * 連絡入力レスポンスクラス
 */
public class WEB3InformInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
    /**
     * (メールアドレス)<BR>
     * メールアドレス
     */
    public String mailAddress;
    
    /**
     * @@roseuid 41EE625E0290
     */
    public WEB3InformInputResponse() 
    {
     
    }
    public WEB3InformInputResponse(WEB3InformInputRequest l_request)
    {
        super(l_request);
    }
}
@
