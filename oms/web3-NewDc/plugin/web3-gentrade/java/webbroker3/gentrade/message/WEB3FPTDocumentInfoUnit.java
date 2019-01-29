head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.30.05.57.24;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1944d92c6286688;
filename	WEB3FPTDocumentInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研ビジネス・イノベーション
 File Name           : 書面情報クラス(WEB3FPTDocumentInfoUnit.java)
 Author Name         : Daiwa Institute of Research Business Innovation
 Revision History    : 2010/11/17 劉レイ(北京中訊) 新規作成 仕様変更モデルNo.354
 */
package webbroker3.gentrade.message;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (書面情報)<BR>
 * 書面情報クラス<BR>
 * <BR>
 * @@author 劉レイ(北京中訊)
 * @@version 1.0 
 */
public class WEB3FPTDocumentInfoUnit extends Message
{
    /**
     * (書面区分)<BR>
     * 書面区分<BR>
     */
    public String documentDiv;

    /**
     * (書面種類)<BR>
     * 書面種類<BR>
     */
    public String documentCategory;

    /**
     * (電子鳩銘柄コード)<BR>
     * 電子鳩銘柄コード<BR>
     */
    public String batoProductCode;

    /**
     * (交付済フラグ)<BR>
     * 交付済フラグ<BR>
     */
    public String deliverFlag;

    /**
     * コンストラクタ<BR>
     */
    public WEB3FPTDocumentInfoUnit()
    {

    }
}
@
