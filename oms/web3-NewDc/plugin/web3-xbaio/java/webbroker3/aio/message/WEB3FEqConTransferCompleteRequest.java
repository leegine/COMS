head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3FEqConTransferCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������ւ̐U�֊������N�G�X�g(WEB3FEqConTransferCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/17 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import java.util.Date;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������ւ̐U�֊������N�G�X�g)<BR>
 * �O�������ւ̐U�֊������N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3FEqConTransferCompleteRequest extends WEB3GenRequest 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "feq_con_transfer_complete";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200503171454L;

    /**
     * (�U�֋��z)<BR>
     * �U�֋��z
     */
    public String changeAmt;
    
    /**
     * (�m�F��������)<BR>
     * �m�F��������
     */
    public Date checkDate;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�
     */
    public String password;
    
    /**
     * @@roseuid 42354D78036B
     */
    public WEB3FEqConTransferCompleteRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FEqConTransferCompleteRequest.class);

    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�U�֋��z<BR>
     * <BR>
     *   this.�U�֋��z == null or<BR>
     *   this.�U�֋��z <= 0 or<BR>
     *   this.�U�֋��z.length >= 10<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00759<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00809<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00810<BR>
     * <BR>
     * �Q�j�m�F��������<BR>
     * <BR>
     *   this.�m�F�������� == null<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00078<BR>
     * <BR>
     * @@roseuid 41E3701B0172
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�U�֋��z
        //this.�U�֋��z == null or 
        //this.�U�֋��z <= 0 or 
        //this.�U�֋��z.length >= 10
        //�̏ꍇ�A��O���X���[����B 
        if(WEB3StringTypeUtility.isEmpty(this.changeAmt))
        {
            log.debug("�U�֋��z�����w��ł��B");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00759,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U�֋��z�����w��ł��B");
        }
        
        if(Double.parseDouble(this.changeAmt) <= 0)
        {
            log.debug("�U�֋��z <= 0");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00809,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U�֋��z <= 0");
            
        }
        
        if(this.changeAmt.length() >= 10)
        {
            log.debug("�U�֋��z.length >= 10");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00810,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�U�֋��z.length >= 10");
        }
        
        //�Q�j�m�F�������� 
        //this.�m�F�������� == null
        //�̏ꍇ�A��O���X���[����B 
        if(this.checkDate == null)
        {
            log.debug("�m�F�������� == null");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00078,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�m�F�������� == null");
        
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O�������ւ̐U�֊������X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FEqConTransferCompleteResponse(this);
    }
}
@
