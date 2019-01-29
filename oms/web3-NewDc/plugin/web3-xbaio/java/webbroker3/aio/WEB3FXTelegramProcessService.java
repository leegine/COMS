head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.25.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3FXTelegramProcessService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : FX電文処理サービス(WEB3FXTelegramProcessService)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 黄建(中訊) 新規作成
 */

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (FX電文処理サービス) <BR>
 * FX電文処理サービスインターフェイス <BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public interface WEB3FXTelegramProcessService extends Service
{
    /**
     * (createGFT電文ハッシュ値) <BR>
     * GFT依頼電文の内容からハッシュ値を生成し、返却する。 <BR>
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT依頼電文明細
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300A2
     */
    public String createGFTTelegramHashValue(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException;

    /**
     * (createGFT電文ハッシュ値) <BR>
     * GFT結果通知電文の内容からハッシュ値を生成し、返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300A4
     */
    public String createGFTTelegramHashValue(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;

    /**
     * (isGFT電文項目設定) <BR>
     * GFT結果通知電文明細の必須項目に値が設定されているかを判定する。 <BR>
     * 設定されている場合、trueを、設定されていない場合、falseを返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C8028301A9
     */
    public boolean isGFTTelegramSet(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;

    /**
     * (isGFT電文桁数属性一致) <BR>
     * GFT結果通知電文明細の特有項目（GFT設定項目）について、<BR>
     * 桁数と属性がフォーマットと一致しているか判定する。 <BR>
     * 一致している場合、trueを、以外、falseを返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300B2
     */
    public boolean isGFTTelegramLengthPropSame(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;

    /**
     * (isGFT電文送受信項目一致) <BR>
     * GFT結果通知電文明細の項目値が依頼電文の項目値と一致しているか判定する。 <BR>
     * 一致している場合、trueを、以外、falseを返却する。 <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT結果通知電文明細
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300B4
     */
    public boolean isGFTTelegramSendAndReceiveValueSame(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;
        
    /**
     * (maskパスワード ) <BR>
     * パスワードにマスクをかける。<BR>
     * 
     * @@param l_strMaskPassword  String
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300B4
     */
    public String maskPassword(String l_strMaskPassword)
        throws WEB3BaseException;
}@
