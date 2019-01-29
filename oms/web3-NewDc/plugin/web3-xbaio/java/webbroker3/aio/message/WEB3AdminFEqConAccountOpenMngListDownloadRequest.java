head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.44;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminFEqConAccountOpenMngListDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������J�݊Ǘ��ꗗ�_�E�����[�h���N�G�X�g(WEB3AdminFEqConAccountOpenMngListDownloadRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/03/18 ���E(���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�O�������J�݊Ǘ��ꗗ�_�E�����[�h���N�G�X�g)<BR>
 * �O�������J�݊Ǘ��ꗗ�_�E�����[�h���N�G�X�g�N���X
 * 
 * @@author ���E(���u)
 * @@version 1.0
 */
public class WEB3AdminFEqConAccountOpenMngListDownloadRequest extends WEB3AdminFEqConAccountOpenMngListCommonRequest 
{ 
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_con_account_open_mng_list_download";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200503171808L;
    
    /**
     * (�_�E�����[�h����)<BR>
     * �_�E�����[�h����
     */
    public String downloadNumber;
    
    /**
     * @@roseuid 423552EB0177
     */
    public WEB3AdminFEqConAccountOpenMngListDownloadRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFEqConAccountOpenMngListDownloadRequest.class);
    
    /**
     * ���N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�_�E�����[�h����<BR>
     * <BR>
     *   this.�_�E�����[�h���� == null or<BR>
     *   this.�_�E�����[�h���� <= 0<BR>
     * <BR>
     *   �̏ꍇ�A��O���X���[����B
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01950<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01951<BR>
     * <BR>
     * @@roseuid 41F8D53300A3
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�X�[�p�[�N���X��validate()���\�b�h���R�[������B 
        super.validate();
        
        //�Q�j�_�E�����[�h����
        //this.�_�E�����[�h���� == null or 
        //this.�_�E�����[�h���� <= 0
        //�̏ꍇ�A��O���X���[����B
        if(WEB3StringTypeUtility.isEmpty(this.downloadNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01950,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�_�E�����[�h���������w��ł��B");
        }
        
        if(Double.parseDouble(this.downloadNumber) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01951,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "�_�E�����[�h����[" + this.downloadNumber + "]�B");
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O�������J�݊Ǘ��ꗗ�_�E�����[�h���X�|���X�I�u�W�F�N�g��ԋp����B
     * @@return WEB3GenResponse
     * @@roseuid 41E7904C00FA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminFEqConAccountOpenMngListDownloadResponse(this);
    }
}
@
