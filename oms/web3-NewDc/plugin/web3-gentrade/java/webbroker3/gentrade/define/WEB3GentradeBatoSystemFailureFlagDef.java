head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeBatoSystemFailureFlagDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （電子鳩）システム障害フラグ　@定数定義インタフェイス(WEB3GentradeBatoSystemFailureFlagDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
*/
package webbroker3.gentrade.define;

/**
 * （電子鳩）システム障害フラグ　@定数定義インタフェイス
 */
public interface WEB3GentradeBatoSystemFailureFlagDef
{
   
    /**
     * 0： 稼動中
     */
    public static final String WORKING = "0";
   
    /**
     * 1： 障害中
     */
    public static final String NOT_WORKING = "1";

}
@
