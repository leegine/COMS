head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.56.31;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4e44d8854634b20;
filename	WEB3AdminPointTradeBonusPlanReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�N�G�X�g(WEB3AdminPointTradeBonusPlanReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/06/14 �s�p(���u) �V�K�쐬
*/

package webbroker3.point.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�N�G�X�g)<BR>
 * �Ǘ��҃g���[�h�{�[�i�X�v�����Ɖ�N�G�X�g�N���X<BR>
 */
public class WEB3AdminPointTradeBonusPlanReferenceRequest extends WEB3GenRequest
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminPointTradeBonusPlanReferenceRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_point_tradeBonusPlanReference";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200506141000L;
    
    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;
    
    /**
     * @@roseuid 42AE3533035B
     */
    public WEB3AdminPointTradeBonusPlanReferenceRequest() 
    {
     
    }
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j���X�R�[�h<BR>
     * <BR>
     *    this.���X�R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * �Q�j�ڋq�R�[�h<BR>
     * <BR>
     * �Q�|�P�j<BR>
     * <BR>
     *    this.�ڋq�R�[�h == null<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * <BR>
     * �Q�|�Q�j<BR>
     * <BR>
     *    this.�ڋq�R�[�h.length() != 6<BR>
     * <BR>
     *    �̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * @@roseuid 42A4FC8000AA
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME); 
        
        //�P�j���X�R�[�h
        if (this.branchCode == null)
        {
            String l_strMessage = "���X�R�[�h = null";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
            
        //�Q�j�ڋq�R�[�h
        //�Q�|�P�jthis.�ڋq�R�[�h == null
        if (this.accountCode == null)
        {
            String l_strMessage = "�ڋq�R�[�h = null";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
        //�Q�|�Q�jthis.�ڋq�R�[�h.length() != 6
        if (this.accountCode.length() != 6)
        {
            String l_strMessage = "�ڋq�R�[�h.length() != 6";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME); 
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                getClass().getName() + STR_METHOD_NAME,
                l_strMessage );
        }
            
        log.exiting(STR_METHOD_NAME);     
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41D125500148
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminPointTradeBonusPlanReferenceResponse(this);
    }
}
@
