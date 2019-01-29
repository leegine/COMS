head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.47.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoBBStateFileTypeDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ファ@イル種別定義インタフェイス(WEB3IpoBBStateFileTypeDef.java)
Author Name      : Daiwa Institute of Research
Revision History : 2005/08/17 沢田 智朗（SRA）新規作成
*/
package webbroker3.ipo.define;

/**
 * ファ@イル種別 定数定義インタフェイス
 *
 * @@author SRA 沢田
 * @@version 1.0
 */
public interface WEB3IpoBBStateFileTypeDef
{

    /**
     * 0：無効OPデータファ@イル
     */
    public final static String INVALID_OP = "0";

     /**
     * 1：BB状況データファ@イル（余力あり）
     */
    public final static String BB_STATE_TP_ON = "1";

     /**
     * 2：BB状況データファ@イル（余力なし）
     */
    public final static String BB_STATE_TP_OFF = "2";
}@
