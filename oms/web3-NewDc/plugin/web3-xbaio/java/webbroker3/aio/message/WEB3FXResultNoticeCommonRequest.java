head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.23;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXResultNoticeCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX���ʒʒm���ʃ��N�G�X�g(WEB3FXResultNoticeCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (FX���ʒʒm���ʃ��N�G�X�g) <BR>
 * FX���ʒʒm���ʃ��N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXResultNoticeCommonRequest extends WEB3GenRequest
{
    /**
     * (GFT���ʒʒm�d������) <BR>
     * GFT���ʒʒm�d���̖��� <BR>
     */
    public WEB3FXGftResultNoticeTelegramUnit fxGftResultNoticeTelegramUnit;

    /**
     * @@roseuid 41E7693203B9
     */
    public WEB3FXResultNoticeCommonRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXResultNoticeCommonRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j GFT���ʒʒm�d�����׃`�F�b�N <BR>
     * this.GFT���ʒʒm�d�����ׁ�null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_01309 <BR>
     * <BR>
     * ��GFT���ʒʒm�d�����ׂ̓��e�ɂ��ẮA�T�[�r�X���ɂă`�F�b�N���s���B <BR>
     * 
     * @@roseuid 41BE4C720015
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j GFT���ʒʒm�d�����׃`�F�b�N 
        // this.GFT���ʒʒm�d�����ׁ�null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_01309 
        if (this.fxGftResultNoticeTelegramUnit == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01309,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.GFT���ʒʒm�d������ = null"); 
        }
        
        log.exiting(l_strMethodName);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E76933002E
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}@
