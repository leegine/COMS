head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsCloseMarginContractUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 建玉返済時の条件を表す(WEB3FuturesOptionsCloseMarginContractUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/14 李海波 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import com.fitechlabs.xtrade.kernel.message.Message;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (返済建玉)<BR>
 * 建玉返済時の条件を表すクラス<BR>
 * @@author 李海波
 * @@version 1.0 
 */
public class WEB3FuturesOptionsCloseMarginContractUnit extends Message
{
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOptionsCloseMarginContractUnit.class);
    /**
     * 建玉ＩＤ
     */
    public String id;
   
    /**
     * (数量)<BR>
     * 一括返済時に「ランダムモード」の場合設定<BR>
     */
    public String contractOrderQuantity;
   
    /**
     * (決済順位)<BR>
     * 一括返済時に「ランダムモード」の場合設定<BR>
     */
    public String settlePriority;
    
    /**
     * @@roseuid 40C0A8F302EE
     */
    public WEB3FuturesOptionsCloseMarginContractUnit() 
    {
    
    }
   
    /**
     * 当リクエストデータの整合性チェックを行う。
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）
     * 
     * １）　@ＩＤチェック
     * 　@this.ＩＤがnullの値であれば例外をスローする。
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00080<BR>
     * ２）　@数量チェック
     * 　@２－１）this.決済順位がnull以外の値でかつ
     * 　@　@　@　@this.数量がnullの値であれば場合例外をスローする。
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00180<BR>
     * 　@２－２）this.決済順位がnull以外の値でかつ
     * 　@　@　@　@this.数量が数字以外の値であれば場合例外をスローする。
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00901<BR>
     * 　@２－３）this.決済順位がnull以外の値でかつ
     * 　@　@　@　@this.数量＜０の値であれば場合例外をスローする。
     * 　@　@  class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00902<BR>
     * ３）　@決済順位チェック
     * 　@３－１）this.決済順位がnull以外の値でかつ
     * 　@　@　@　@this.決済順位が数字以外の値であれば場合例外をスローする。
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00329<BR>
     * 　@３－２）this.決済順位がnull以外の値でかつ
     * 　@　@　@　@this.決済順位＜０の値であれば場合例外をスローする。
     * 　@　@  class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00246<BR>
     * @@throws WEB3BaseException
     * @@roseuid 407DF83A0075
     */
    public void validate() throws WEB3BaseException 
    {
        //ＩＤチェック
        log.debug("this.ＩＤがnullの値であれば例外をスローする。");
        log.debug("this.id = " + this.id);
        if(this.id == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080,
                getClass().getName() + "validate"
            );
        }
        log.debug("ＩＤチェック.....OK>>>>>");
        log.debug("this.決済順位がnull以外の値でかつthis.数量がnullの値であれば場合例外をスローする。");
        log.debug("this.settlePriority = " + this.settlePriority);
        log.debug("this.contractOrderQuantity = " + this.contractOrderQuantity);
        //数量チェック(２－１)
        if(this.settlePriority != null && this.contractOrderQuantity == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00180,
                getClass().getName() + "validate"
            );
        }
        log.debug("数量チェック(1).....OK>>>>>");
        log.debug("this.決済順位がnull以外の値でかつthis.数量が数字以外の値であれば場合例外をスローする");
        //数量チェック(２－２)
        if(this.settlePriority != null && !(WEB3StringTypeUtility.isNumber(this.contractOrderQuantity))) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                getClass().getName() + "validate"
            );
        }
        log.debug("数量チェック(2).....OK>>>>>");
        log.debug("this.決済順位がnull以外の値でかつthis.数量＜０の値であれば場合例外をスローする。");
        //数量チェック(２－３)
        if(this.settlePriority != null && Long.parseLong(this.contractOrderQuantity) < 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00902,
                getClass().getName() + "validate"
            );
        }
        log.debug("数量チェック(3).....OK>>>>>");
		log.debug("this.決済順位がnull以外の値でかつthis.決済順位が数字以外の値であれば場合例外をスローする");
		//数量チェック(２－２)
		if(this.settlePriority != null && !(WEB3StringTypeUtility.isNumber(this.settlePriority))) 
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00329,
				getClass().getName() + "validate"
			);
		}
		log.debug("決済順位チェック(1).....OK>>>>>");
		log.debug("this.決済順位がnull以外の値でかつthis.決済順位＜０の値であれば場合例外をスローする。");
		//数量チェック(２－３)
		if(this.settlePriority != null && Long.parseLong(this.settlePriority) < 0)
		{
			throw new WEB3BusinessLayerException(
				WEB3ErrorCatalog.BUSINESS_ERROR_00246,
				getClass().getName() + "validate"
			);
		}
		log.debug("決済順位チェック(2).....OK>>>>>");
    }
}
@
