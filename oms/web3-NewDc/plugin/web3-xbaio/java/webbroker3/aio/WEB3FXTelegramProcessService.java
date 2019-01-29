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
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : FX�d�������T�[�r�X(WEB3FXTelegramProcessService)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/20 ����(���u) �V�K�쐬
 */

package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Service;
import webbroker3.aio.message.WEB3FXGftAskingTelegramUnit;
import webbroker3.aio.message.WEB3FXGftResultNoticeTelegramUnit;
import webbroker3.common.WEB3BaseException;

/**
 * (FX�d�������T�[�r�X) <BR>
 * FX�d�������T�[�r�X�C���^�[�t�F�C�X <BR>
 * 
 * @@author ����(���u)
 * @@version 1.0
 */

public interface WEB3FXTelegramProcessService extends Service
{
    /**
     * (createGFT�d���n�b�V���l) <BR>
     * GFT�˗��d���̓��e����n�b�V���l�𐶐����A�ԋp����B <BR>
     * 
     * @@param l_fxGftAskingTelegramUnit - GFT�˗��d������
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300A2
     */
    public String createGFTTelegramHashValue(
        WEB3FXGftAskingTelegramUnit l_fxGftAskingTelegramUnit)
        throws WEB3BaseException;

    /**
     * (createGFT�d���n�b�V���l) <BR>
     * GFT���ʒʒm�d���̓��e����n�b�V���l�𐶐����A�ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300A4
     */
    public String createGFTTelegramHashValue(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;

    /**
     * (isGFT�d�����ڐݒ�) <BR>
     * GFT���ʒʒm�d�����ׂ̕K�{���ڂɒl���ݒ肳��Ă��邩�𔻒肷��B <BR>
     * �ݒ肳��Ă���ꍇ�Atrue���A�ݒ肳��Ă��Ȃ��ꍇ�Afalse��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C8028301A9
     */
    public boolean isGFTTelegramSet(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;

    /**
     * (isGFT�d������������v) <BR>
     * GFT���ʒʒm�d�����ׂ̓��L���ځiGFT�ݒ荀�ځj�ɂ��āA<BR>
     * �����Ƒ������t�H�[�}�b�g�ƈ�v���Ă��邩���肷��B <BR>
     * ��v���Ă���ꍇ�Atrue���A�ȊO�Afalse��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300B2
     */
    public boolean isGFTTelegramLengthPropSame(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;

    /**
     * (isGFT�d������M���ڈ�v) <BR>
     * GFT���ʒʒm�d�����ׂ̍��ڒl���˗��d���̍��ڒl�ƈ�v���Ă��邩���肷��B <BR>
     * ��v���Ă���ꍇ�Atrue���A�ȊO�Afalse��ԋp����B <BR>
     * 
     * @@param l_fxGftResultNoticeTelegramUnit - GFT���ʒʒm�d������
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300B4
     */
    public boolean isGFTTelegramSendAndReceiveValueSame(
        WEB3FXGftResultNoticeTelegramUnit l_fxGftResultNoticeTelegramUnit)
        throws WEB3BaseException;
        
    /**
     * (mask�p�X���[�h ) <BR>
     * �p�X���[�h�Ƀ}�X�N��������B<BR>
     * 
     * @@param l_strMaskPassword  String
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 41C172A300B4
     */
    public String maskPassword(String l_strMaskPassword)
        throws WEB3BaseException;
}@
