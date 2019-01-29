head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.47.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FutOpTradeRegistDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  先物OP取引登録　@定数定義インタフェイス(WEB3FutOpTradeRegistDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 張麗維 (中訊) 新規作成
*/
package webbroker3.common.define;

/**
 * 先物OP取引登録　@定数定義インタフェイス。
 *
 * @@author 張麗維
 * @@version 1.0
 */
public interface WEB3FutOpTradeRegistDef
{
    /**
      *0：　@登録なし 
      */
    public static final String NOT_REGIST = "0";
    
    /**
      *1：　@登録済み（OP買建取引） 
      */
    public static final String OP_REGIST = "1";
    
    /**
      *2：　@登録済み（先物取引）
      */
    public static final String FUT_REGIST = "2";
    
    /**
      *3：　@登録済み（先物／OP取引）
      */
    public static final String FUT_OP_REGIST = "3";

}
@
