head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.32.11;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiSuccBidInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��N�G�X�g(WEB3AdminSrvRegiSuccBidInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 �s�p (���u) �V�K�쐬
*/

package webbroker3.srvregi.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��N�G�X�g)<BR>
 * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��N�G�X�g�N���X<BR>
 * 
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3AdminSrvRegiSuccBidInputRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_srvregi_succBidInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151400L;
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminSrvRegiSuccBidInputRequest.class);        
    
    /**
     * (�T�[�r�X�敪)
     */
    public String serviceDiv;
    
    /**
     * (���I���ID)<BR>
     * �ʔ�<BR>
     */
    public String lotteryId;
    
    /**
     * (�T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 40F4DFB20066
     */
    public WEB3AdminSrvRegiSuccBidInputRequest() 
    {
     
    }
    
    /**
     * (create���X�|���X)<BR>
     * �T�[�r�X���p�Ǘ��҃T�[�r�X���D�z�X�V���̓��X�|���X�𐶐����ĕԋp����B<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40F4ED3103C1
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminSrvRegiSuccBidInputResponse(this);
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B <BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j <BR>
     * <BR>
     * 1) �T�[�r�X�敪�̃`�F�b�N<BR>
     *  1-1) this.ID��null==�ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00080<BR>
     *  1-2) this.ID�̌���!=2���̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00954<BR>
     * <BR>
     * 2) ���I���ID�̃`�F�b�N<BR>
     * �@@this.���I���ID==null�̏ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00957<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F4ED3103E0
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate() ";
        log.entering(STR_METHOD_NAME);
        
        //1) �T�[�r�X�敪�̃`�F�b�N
        //1-1) this.ID��null==�ꍇ�A��O���X���[����B
        if (this.serviceDiv == null || "".equals(this.serviceDiv.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //1-2) this.ID�̌���!=2���̏ꍇ�A��O���X���[����B
        if (this.serviceDiv.length() != 2 )
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00954, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //2) ���I���ID�̃`�F�b�N
        if (this.lotteryId == null || "".equals(this.lotteryId.trim()))
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00957, 
                this.getClass().getName() + STR_METHOD_NAME);
        }
        
        log.entering(STR_METHOD_NAME);
    }
}
@
