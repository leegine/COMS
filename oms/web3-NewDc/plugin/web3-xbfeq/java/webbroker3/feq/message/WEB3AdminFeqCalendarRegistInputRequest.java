head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.30.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqCalendarRegistInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������J�����_�[�o�^���̓��N�G�X�g(WEB3AdminFeqCalendarRegistInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/26 �A�C��(���u) �V�K�쐬
                 : 2005/08/02 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�Ǘ��ҊO�������J�����_�[�o�^���̓��N�G�X�g)<BR>
 * �Ǘ��ҊO�������J�����_�[�o�^���̓��N�G�X�g�N���X
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3AdminFeqCalendarRegistInputRequest extends WEB3GenRequest 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqCalendarRegistInputRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_calendarRegistInput";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L; 
    
    
    /**
     * (�s��R�[�h)<BR>
     * �s��R�[�h
     */
    public String marketCode;
    
    /**
     * (�N��)<BR>
     * �N���iYYYYMM�j
     */
    public String period;
    
    /**
     * @@roseuid 42CE3A000167
     */
    public WEB3AdminFeqCalendarRegistInputRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�s��R�[�h<BR>
     * <BR>
     *    this.�s��R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_00443<BR>
     * <BR>
     * �Q�j�N��<BR>
     * <BR>
     * �Q�|�P�j<BR>
     *    this.�N�� == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02027<BR>
     * <BR>
     * �Q�|�Q�j<BR>
     *    this.�N��.length() != 6<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02028<BR>
     * <BR>
     * �Q�|�R�j<BR>
     *    this.�N�������t�Ƃ��ėL�肦�Ȃ��l<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     * �@@class: WEB3BusinessLayerException<BR>
     * �@@tag:   BUSINESS_ERROR_02029<BR>
     * @@throws WEB3BaseException
     * @@roseuid 421C7286029E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�s��R�[�h
        //   this.�s��R�[�h == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.marketCode))
        {
            log.debug("�s��R�[�h�������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00443,
                getClass().getName() + STR_METHOD_NAME,
                "�s��R�[�h�������͂ł��B");
        }
        
        //�Q�j�N��
        //�Q�|�P�j
        //   this.�N�� == null
        //   �̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(this.period))
        {
            log.debug("�N���������͂ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02027,
                getClass().getName() + STR_METHOD_NAME,
                "�N���������͂ł��B");
        }
        
        //�Q�|�Q�j
        //   this.�N��.length() != 6
        //   �̏ꍇ�A��O���X���[����B
        if (this.period.length() != 6)
        {
            String l_strMessage = "�N����6���ȊO�ł�.�u" + this.period + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02028,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        
        //�Q�|�R�j
        //   this.�N�������t�Ƃ��ėL�肦�Ȃ��l
        //   �̏ꍇ�A��O���X���[����B
        Date l_dat = null;
        try
        {
            l_dat = WEB3DateUtility.getDate(this.period, "yyyyMM");
        }
        catch(Exception l_ex)
        {
            String l_strMessage = "�N�������t�Ƃ��ėL�肦�Ȃ��l�ł�.�u" + this.period + "�v";
            log.error(l_strMessage, l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02029,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage,
                l_ex);
        }
        if (l_dat == null)
        {
            String l_strMessage = "�N�������t�Ƃ��ėL�肦�Ȃ��l�ł�.�u" + this.period + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02029,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqCalendarRegistInputResponse(this);
    }
}
@
