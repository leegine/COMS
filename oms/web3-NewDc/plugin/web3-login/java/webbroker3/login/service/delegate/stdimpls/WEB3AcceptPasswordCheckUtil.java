head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.23.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4b44d7ef6783489;
filename	WEB3AcceptPasswordCheckUtil.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright           : (株)大和総研 証券ソリューションシステム第二部
 File Name           : 顧客パスワードチェックサービスUTIL(WEB3AcceptPasswordCheckUtil.java)
 Author Name         : Daiwa Institute of Research
 Revesion History    : 2005/10/28 齋藤　@栄三(FLJ) 新規作成
 */

package webbroker3.login.service.delegate.stdimpls;

import webbroker3.common.*;

public class WEB3AcceptPasswordCheckUtil
{
    public WEB3Crypt web3Crypt = new WEB3Crypt();

    private static WEB3AcceptPasswordCheckUtil _instance = new
        WEB3AcceptPasswordCheckUtil();

    private WEB3AcceptPasswordCheckUtil()
    {
    }

    public static WEB3AcceptPasswordCheckUtil getInstance()
    {
        return _instance;
    }

    /**
     * 文字列の暗号化を行う。
     * 引数が暗号化した文字列の場合は、その文字列をそのまま返す。
     *
     * @@param l_strPlane 暗号化したい文字列
     * @@return 暗号化した文字列
     */
    public synchronized String encrypt(String l_strPlane)
    {
        return web3Crypt.encrypt(l_strPlane);
    }

}
@
