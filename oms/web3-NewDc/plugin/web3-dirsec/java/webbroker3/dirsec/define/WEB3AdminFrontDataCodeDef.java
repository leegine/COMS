head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontDataCodeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : トリガー発行データコード 定数定義インタフェイス(WEB3AdminFrontDataCodeDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115
*/
package webbroker3.dirsec.define;

/**
 * トリガー発行データコード 定数定義インタフェイス
 *
 * @@author SCS 佐藤
 * @@version 1.0
 */
public interface WEB3AdminFrontDataCodeDef {
    
    /**
     * 通番照会要求データコード
     */
    public final static String NUBER_REF_REQ_CODE  = "AX0X1";
    
    /**
     * 通知代行解除要求データコード
     */
    public final static String NOTICEAGENCY_REL_REQ_CODE  = "AXZY1";
    
    /**
     * 通知代行要求データコード
     */
    public final static String NOTICEAGENCY_REQ_CODE = "AX9X1";
    
    /**
     * 通知再送要求データコード
     */
    public final static String NOTICERESEND_REQ_CODE = "AX8X1";
    
    /**
     * 全訂正処理再起動
     */
    public final static String ALLCORR_REQ_CODE = "AX2X1";
    
    /**
     * 株式注文処理起動
     */
    public final static String EQORDER_REQ_CODE = "AI8X2T";
}
@
