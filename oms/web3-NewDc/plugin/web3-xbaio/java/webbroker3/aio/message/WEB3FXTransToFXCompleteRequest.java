head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransToFXCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX�ւ̐U�֊������N�G�X�g(WEB3FXTransToFXCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/07/13 ��O�� (���u) �d�l�ύX NO.601,602
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (FX�ւ̐U�֊������N�G�X�g) <BR>
 * FX�ւ̐U�֊������N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransToFXCompleteRequest extends
    WEB3FXResultNoticeCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_to_fx_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;

    /**
     * (����ID) <BR>
     * ����ID <BR>
     */
    public String orderId;
    
    /**
     * (�Ïؔԍ�) <BR>
     * ��ʂ�����͂��ꂽ�Ïؔԍ�  <BR>
     */
    public String password;

    /**
     * (�m�F��������) <BR>
     * �m�F�������̔����� <BR>
     * �i��ʕ\���Ȃ��j <BR>
     */
    public Date checkDate;
    
    /**
     * (FX�V�X�e���R�[�h) <BR>
     * FX�V�X�e���R�[�h <BR>
     */
    public String fxSystemCode;

    /**
     * @@roseuid 41E76933030D
     */
    public WEB3FXTransToFXCompleteRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransToFXCompleteRequest.class);

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B <BR>
     * <BR>
     * �Q�j ����ID�`�F�b�N <BR>
     * this.����ID��null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00600 <BR>
     * <BR>
     * �R�j �m�F���������`�F�b�N <BR>
     * this.�m�F����������null�̏ꍇ�A��O��throw����B <BR>
     * <BR>
     * class: WEB3BusinessLayerException <BR>
     * tag: BUSINESS_ERROR_00078 <BR>
     * <BR>
     * 
     * @@roseuid 41BE50120083
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()";
        log.entering(l_strMethodName);
        
        // �P�j �X�[�p�[�N���X��validate���\�b�h���Ăяo���B
        super.validate(); 
 
        // �Q�j ����ID�`�F�b�N 
        // this.����ID��null�̏ꍇ�A��O��throw����B  
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00600 
        if (WEB3StringTypeUtility.isEmpty(this.orderId))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.����ID = null"); 
        }
 
        // �R�j �m�F���������`�F�b�N 
        // this.�m�F����������null�̏ꍇ�A��O��throw����B 
        // class: WEB3BusinessLayerException 
        // tag: BUSINESS_ERROR_00078 
        if (this.checkDate == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + l_strMethodName,
                "���N�G�X�g�f�[�^.�m�F�������� = null"); 
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransToFXCompleteResponse(this);
    }
}@
