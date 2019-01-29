head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.19.05.38.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	72c4dad1fc472db;
filename	WEB3OrderReqNumberHead2ManageTestServiceImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :識別コード頭２桁特定サービス実装クラス(WEB3OrderReqNumberHead2ManageServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */

package webbroker3.system.tune.affinity;

/**
 * 識別コード頭２桁特定サービス仮実装クラス
 *
 * @@author 劉
 * @@version 1.0
 */
public class WEB3OrderReqNumberHead2ManageTestServiceImpl
    implements WEB3OrderReqNumberHead2ManageService
{
    /**
     * 識別コード頭２桁取得する
     */
    public String getOrderReqNumberHead2()
    {
        return "00";
    }

}
@
