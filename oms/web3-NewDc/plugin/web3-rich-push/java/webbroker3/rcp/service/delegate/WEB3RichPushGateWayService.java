head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.17.01.47.52;	author liu-lei;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8604d8168182f90;
filename	WEB3RichPushGateWayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        :　@リッチクライアントプッシュゲートウェイサービスインターフェース(WEB3RichPushGateWayService.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/02/06 劉(FLJ) 新規作成
 */

package webbroker3.rcp.service.delegate;

import java.util.*;

import com.fitechlabs.xtrade.kernel.boot.*;

/**
 * リッチクライアントプッシュゲートウェイサービスインターフェース
 * @@author 劉
 * @@version 1.0
 */
public interface WEB3RichPushGateWayService
    extends Service
{

    /**
     * ゲートウェイ経由リッチクライアントへデータプッシュ
     *
     * @@param l_strInstitutionCode String
     * @@param l_lstPushData List
     * @@return boolean
     */
    public boolean push(String l_strInstitutionCode, List l_lstPushData);

}
@
