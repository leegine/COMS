head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3DigestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3DigestServiceクラス(WEB3DigestService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/6/07 孫(FLJ) 新規作成
*/
package webbroker3.login.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * WEB3に必要なセキュリティーキーを生成すると認証するサービスのインタフェース。
 */
public interface WEB3DigestService extends Service
{
    /**
     * Web3に必要なキーを作成、取得
     * 
     * @@return キー
     */
    public WEB3DigestKey getRandomKey();
    
    /**
     * 有効なキーであるかをチャックする。
     * 
     * @@param key チャックされるキーオブジェクト
     * @@return 有効であるかどうか。有効の場合:true,無効は:false
     */
    public boolean isValidKey(WEB3DigestKey l_key);
    
}
@
