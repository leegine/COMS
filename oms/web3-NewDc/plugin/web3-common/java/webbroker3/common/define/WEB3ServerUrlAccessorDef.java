head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.04.49.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ServerUrlAccessorDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : サーバーURL定数定義インタフェイス(WEB3ServerUrlAccessorDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/20 凌建平 (中訊) 新規作成
*/

package webbroker3.common.define;

/**
 * サーバーURL 定数定義インタフェイス
 *                                                                      
 * @@author 凌建平
 * @@version 1.0
 */
public interface WEB3ServerUrlAccessorDef
{
    /**
     * cluster.urls：サーバーURL
     */
    public static final String  CLUSTER_URLS = "cluster.urls";

    /**
     * web3-static-cluster：サーバーACCESSOR
     */
    public static final String  WEB3_STATIC_CLUSTER = "web3-static-cluster";
}
@
