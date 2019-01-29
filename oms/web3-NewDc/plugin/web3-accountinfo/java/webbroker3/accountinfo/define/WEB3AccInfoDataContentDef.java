head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.27.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoDataContentDef.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : データ内容定義インタフェイス(WEB3AccInfoDataContentDef.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/12 何文敏 (中訊) 新規作成
*/

package webbroker3.accountinfo.define;

/**
 * データ内容定義インタフェイス<BR>
 * <BR>
 * @@author 何文敏
 * @@version 1.0
 */

public interface WEB3AccInfoDataContentDef
{
    /**
     * 00：データ内容未選択
     */
    public final static String DATA_CONTENT_NOT_SELECT = "00";

    /**
     * 01：新規口座開設案内用データ
     */
    public final static String NEW_ACC_OPEN_GUIDANCE_DATA = "01";
    
    /**
     * 02：振込みカード用データ
     */
    public final static String TRANSFER_CARD_DATA = "02";
    
    /**
     * 03：口座移管案内用データ
     */
    public final static String ACCOUNT_TRANSFER_GUIDANCE_DATA = "03";
}



@
