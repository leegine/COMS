head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.47.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiSrvRegiExecErrDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : エラー区分(WEB3SrvRegiSrvRegiExecErrDivDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/29 金シュ 新規作成 モデル329
*/

package webbroker3.srvregi.define;

/**
 * エラー区分
 *
 * @@author 金シュ
 * @@version 1.0
 */
public interface WEB3SrvRegiSrvRegiExecErrDivDef
{
    /**
     * 1：未申込エラー有
     */
    public final static String UNAPPLY_ERROR = "1";

    /**
     *  null：エラー無
     */
    public final static String NOT_ERROR = null;
}
@
