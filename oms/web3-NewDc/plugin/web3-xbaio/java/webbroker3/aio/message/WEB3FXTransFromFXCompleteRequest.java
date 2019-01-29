head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.06.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FXTransFromFXCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : FX����U�֊������N�G�X�g(WEB3FXTransFromFXCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/20 ���z (���u) �V�K�쐬   
                 : 2006/04/26 ������ (���u) �d�l�ύX�E���f��533
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
 * (FX����U�֊������N�G�X�g) <BR>
 * FX����U�֊������N�G�X�g�N���X <BR>
 * 
 * @@author ���z(���u)
 * @@version 1.0
 */
public class WEB3FXTransFromFXCompleteRequest extends
    WEB3FXResultNoticeCommonRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "fx_trans_from_fx_complete";

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
     * (FX�V�X�e���R�[�h)<BR>
     * FX�V�X�e���R�[�h<BR>
     */
    public String fxSystemCode;
    
    /**
     * (�Ïؔԍ�) <BR>
     * ��ʂ�����͂��ꂽ�Ïؔԍ� <BR>
     */
    public String password;

    /**
     * (�m�F��������) <BR>
     * �m�F�������̔����� <BR>
     * �i��ʕ\���Ȃ��j <BR>
     */
    public Date checkDate;

    /**
     * @@roseuid 41E788110251
     */
    public WEB3FXTransFromFXCompleteRequest()
    {
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FXTransFromFXCompleteRequest.class);

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
     * @@roseuid 41BEC9F1036B
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
                "���N�G�X�g�f�[�^.����ID��null"); 
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
                "���N�G�X�g�f�[�^.�m�F����������null"); 
        }
        
        log.exiting(l_strMethodName);
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E7829802FD
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FXTransFromFXCompleteResponse(this);
    }
}@
