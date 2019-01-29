head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3OrderReqNumberHead2ManageService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :識別コード頭２桁特定サービスクラス(WEB3OrderReqNumberHead2ManageServiceImpl.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2004/11/30 劉(FLJ) 新規作成
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * 識別コード頭２桁特定サービスクラス
 *
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3OrderReqNumberHead2ManageService
    extends Service
{

    /**
     * 識別コード頭２桁取得する
     */
    public String getOrderReqNumberHead2();

    public static final String NOT_GET_NUMBER_FLAG ="web3.not.get.number.flag";
}
@
