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
filename	WEB3GentradeBatoFunctionDivDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （電子鳩）機@能区分　@定数定義インタフェイス(WEB3GentradeBatoFunctionDivDef)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/15 仲川(ＳＲＡ) 新規作成
Revesion History : 2008/06/17 趙林鵬(中訊) ＤＢレイアウト No.633
*/
package webbroker3.gentrade.define;

/**
 * （電子鳩）機@能区分　@定数定義インタフェイス
 */
public interface WEB3GentradeBatoFunctionDivDef
{
    /**
     * 00：電子鳩承諾チェック
     */
    public static final String BATO_SERVICE_REG_SERVICE = "00";
    
    /**
     * 01：目論見書閲覧チェック
     */
    public static final String BATO_PROSPECTUS_SERVICE = "01";
    
    /**
     * 02：取引報告書実施チェック
     */
    public static final String BATO_TRAN_HIST_SERVICE = "02";

    /**
     * 03：複数銘柄目論見書閲覧チェック
     */
    public static final String BATO_MULTI_PROSPECTUS_SERVICE = "03";
}
@
